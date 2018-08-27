package com.SAmovie.controller;

import com.SAmovie.model.*;
import com.SAmovie.service.CenterService;
import com.SAmovie.service.MovieService;
import com.SAmovie.service.RecommandService;
import com.SAmovie.service.UserService;
import com.SAmovie.util.sqoop.SqoopParams;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.*;

/**
 * Created by scamonia on 2018/5/8.
 */
@Controller
public class CenterController {
    @Resource
    CenterService centerService;
    @Resource
    UserService userService;
    @Resource
    MovieService movieService;
    @Resource
    RecommandService recommandService;
    static String command = null;

    static {
        SqoopParams sqoopParams = new SqoopParams();
        sqoopParams.setDatabaseType("mysql");
        sqoopParams.setHost("192.168.169.1");
        sqoopParams.setDatabasePort("3306");
        sqoopParams.setDatabaseName("samovie");
        sqoopParams.setUserName("root");
        sqoopParams.setPassword("rza19961204");
        command = "sqoop import --connect " + sqoopParams.getJdbcUrl();
    }

    @RequestMapping(value = "/userhome")
    public String Userhome(){
        return "/usercenter/center";
    }

    @ResponseBody
    @RequestMapping(value = "/profile",method = RequestMethod.POST)
    public String Profile(HttpServletRequest httpServletRequest,@RequestParam("uid") String uid){
        System.out.println("用户页面的uid为"+uid);
        String tmpid = (String) httpServletRequest.getSession().getAttribute("uid");
        System.out.println("测试id为"+tmpid);
        //获取当前主页的用户名称
        String centername = centerService.getCnameByuid(uid);
        UserInfo pageinfo = centerService.getPageinfoByuid(uid);

        //用户个人推荐
        if(tmpid.equals(uid)){
            //推荐五部电影
            List<Movie> recMovies = new ArrayList<Movie>();
            Random random = new Random();
            //先获取用户默认喜欢影单
            List<MovieList> userMlist = centerService.getmovielistBycid(tmpid);
            MovieList dflMlist=null;
            //用户个人指数数据
            Typelist usertypelist = centerService.getTypelistBycid(tmpid);
            //每一项值存储
            List<SerREC> userRECs = usertypelist.RECtype();
            //用户个人指数总和
            int usertypesum = usertypelist.Sumvalue();
            for (int i=0;i<userMlist.size();i++){
                if(userMlist.get(i).getMltype().equals("defaultlike")){
                    dflMlist = userMlist.get(i);
                }
            }
            System.out.println("用户个人推荐默认影单为"+dflMlist.toString());
            if(dflMlist.getMidlist().length()==3){
                //可能为新用户 进一步判断
                if (usertypesum%24==0){
                //新注册用户
                    //是否为空类别或全类别用户
                    if(usertypesum==0||usertypesum==384){
                        recMovies = centerService.getTopdflmovies(5);
                    }else{
                        //根据选择获得推荐
                        //获取用户注册时的中文标签
                        List<String> taglike = new ArrayList<>();
                        for (SerREC serREC:userRECs){
                            if (serREC.getTypeval()==24){
                                taglike.add(serREC.getChtype());
                            }
                        }

                        recMovies = recommandService.getSimimovies(taglike);
                        //不足五部 填充
                        if(recMovies.size()<5){
                            int othernum = 5-recMovies.size();

                            for (int i=0;i<othernum;i++){
                                String onetag = taglike.get(random.nextInt(taglike.size()));
                                Movie onemovie = centerService.selectMoviesByTag(onetag);
                                recMovies.add(onemovie);
                            }
                        }
                    }
                }
            }else{
                //有一定行为指数的用户
                    //采取默认推荐的数量
                    int dflrec = 1;
                    //存储电影id的数组 确保不重复
                    List<Integer> recmovieid = new ArrayList<Integer>();
                    //1.用户评分的好电影 选择相似的一部
                    int likedmid = centerService.getGoodScoremidByuid(tmpid);

                    if (likedmid==-1){
                        System.out.println("该用户尚未评论");
                        dflrec = dflrec + 1;
                    }else {
                        System.out.println("好电影有多少"+likedmid);
                        Movie firstMovies = null;
                        Movie targetMovie = recommandService.getTargetMovie(likedmid);
                        System.out.println(targetMovie.toString());
                        String movietag = targetMovie.getTag();
                        if(movietag.indexOf("/") != -1){
                            //多标签型电影
                            String[] tags = movietag.split("/");
                            List<String> taglike = new ArrayList<>();
                            for (String tag:tags){
                                taglike.add(tag);
                            }
                            Movie findfirstmovie = centerService.getSimimovies(likedmid,taglike);
                            if (findfirstmovie != null){
                                System.out.println("相似电影为"+findfirstmovie.toString());
                            }else {
                                System.out.println("没找到");
                                //获取一个随机标签查找
                                String onerandom = taglike.get(random.nextInt(tags.length));
                                findfirstmovie = centerService.getFirstMovieByOnetag(likedmid,onerandom);
                                System.out.println("随机单标签"+findfirstmovie.toString());
                            }
                            firstMovies = findfirstmovie;
                        }else{
                            System.out.println("单标签");
                            Movie findonetagmovie = centerService.getFirstMovieByOnetag(likedmid,movietag);
                            firstMovies = findonetagmovie;
                        }
                        if (firstMovies!=null) {
                            recmovieid.add(firstMovies.getMid());
                            recmovieid.add(likedmid);
                            recMovies.add(firstMovies);
                            System.out.println("第一部电影为" + firstMovies.toString());
                        }else {
                            dflrec = dflrec + 1;
                        }
                    }
                    //第一部电影
                    //第二部电影 根据用户评分喜欢的电影找出同样的用户并推荐相似用户喜欢的电影
                        String stringlikemid = String.valueOf(likedmid);
                        MovieList movieList = centerService.getMovielistlikeUser(tmpid,stringlikemid);//获得一个喜欢这部电影的其他用户的影单
                        if(movieList!=null&&movieList.getMidlist().split(",").length!=2){
                            int secondmoviemid=0;//第二部电影的id
                            System.out.println("该用户喜欢的影单为"+movieList.toString());
                            String[] thisusermidlist = movieList.getMidlist().split(",");
                            for (int i=1;i<thisusermidlist.length;i++){
                                if(recmovieid.contains(Integer.parseInt(thisusermidlist[i]))){

                                }else {
                                    secondmoviemid = Integer.parseInt(thisusermidlist[i]);
                                    recmovieid.add(secondmoviemid);
                                    System.out.println("第二部电影id为"+secondmoviemid);
                                    break;
                                }
                            }
                                    Movie secondmovie = movieService.getMovieByMid(secondmoviemid);
                                    System.out.println("第二部电影为"+secondmovie.toString());
                                    recMovies.add(secondmovie);
                        }else {
                            dflrec = dflrec + 1;
                        }
                    //第二部电影
                    //第三部电影 根据用户行为指数推荐相应类别的电影
                        List<String> twotag = new ArrayList<String>();
                        Collections.sort(userRECs, new Comparator<SerREC>() {
                            @Override
                            public int compare(SerREC o1, SerREC o2) {
                                SerREC serREC1 = o1;
                                SerREC serREC2 = o2;
                                if (serREC1.getTypeval() > serREC2.getTypeval()) {
                                    return 1;
                                } else if (serREC1.getTypeval() == serREC2.getTypeval()) {
                                    return 0;
                                } else {
                                    return -1;
                                }
                            }
                        });
                        System.out.println(userRECs.toString());
                        twotag.add(userRECs.get(15).getChtype());
                        twotag.add(userRECs.get(14).getChtype());


                        Movie thirdmovie = centerService.getThirdMovie(twotag,recmovieid);
                        if (thirdmovie!=null) {
                            System.out.println("第三部电影" + thirdmovie.toString());
                            recMovies.add(thirdmovie);
                            recmovieid.add(thirdmovie.getMid());
                            System.out.println("目前四部电影的id" + recmovieid.toString());
                        }else {
                            dflrec = dflrec+1;
                        }
                    //第三部电影
                    //第四部电影 根据当前用户的第三标签 找到与之相近值的用户 获得推荐
                        SerREC fourthinfo = userRECs.get(14);
                        String fourthCL = fourthinfo.getEntype();
                        int fourthNearvalue = fourthinfo.getTypeval();
                        String nearuserid = centerService.getNuidByTypeval(fourthCL,fourthNearvalue);
                        if (nearuserid!=null) {
                            System.out.println("相近用户id为" + nearuserid);
                            MovieList nearUserDFList = centerService.getnearDFList(nearuserid, "defaultlike");
                            System.out.println("第四部电影相似用户的默认影单"+nearUserDFList);
                            if (nearUserDFList!=null&&!nearUserDFList.getMidlist().equals("dfl")){
                                int fourthmovieid = 0;
                                String[] thisusermidlist = nearUserDFList.getMidlist().split(",");
                                System.out.println("进入查找相似用户影单");
                                for (int i=1;i<thisusermidlist.length;i++){
                                    if(recmovieid.contains(Integer.parseInt(thisusermidlist[i]))){
                                        System.out.println("进入第四部推荐");
                                    }else {
                                        fourthmovieid = Integer.parseInt(thisusermidlist[i]);
                                        recmovieid.add(fourthmovieid);
                                        System.out.println("第四部电影id为"+fourthmovieid);
                                        break;
                                    }
                                }
                                if (fourthmovieid!=0){
                                    Movie fourthmovie = movieService.getMovieByMid(fourthmovieid);
                                    System.out.println("第四部电影为"+fourthmovie.toString());
                                    recMovies.add(fourthmovie);
                                }else {
                                    dflrec =dflrec + 1;
                                }
                            }else {
                                dflrec =dflrec + 1;
                            }
                        }else {
                            dflrec = dflrec+1;
                        }
                    //第四部电影
                    //第五部电影 补全计划
                    for (int i=0;i<dflrec;i++){
                        Movie fivemovie =  centerService.getRandomMovie(recmovieid);
                        recmovieid.add(fivemovie.getMid());
                        recMovies.add(fivemovie);
                        System.out.println("添加默认第"+i+"部");
                    }
                    System.out.println("需要随机推荐的电影数量为"+dflrec);
            }

            System.out.println(recMovies.toString()+recMovies.size());
            httpServletRequest.getSession().setAttribute("recmovies",recMovies);
        }
        //用户个人推荐

        httpServletRequest.getSession().setAttribute("centerid",uid);
        httpServletRequest.getSession().setAttribute("cname",centername);
        httpServletRequest.getSession().setAttribute("pageinfo",pageinfo);

        return "success";
    }

    @ResponseBody
    @RequestMapping(value = "/updateuserinfo")
    public String Updateinfo(HttpServletRequest httpServletRequest,@RequestParam("uid") String uid,@RequestParam(value = "gender",required = false) String gender,
                             @RequestParam(value = "birthday",required = false) String birthday,@RequestParam(value = "introself",required = false) String introself){

        if (birthday !=null && gender != null && introself !=null){
            System.out.println("修改内容为"+uid+"gender"+gender+"birthday"+birthday+"intro"+introself);
            centerService.updateUserinfo(uid,gender,birthday,introself);
            return "success";
        }
        return "false";

    }

    @ResponseBody
    @RequestMapping(value = "/getmovielist", produces = "application/text; charset=utf-8")
    public String Getmovielist(HttpServletRequest httpServletRequest,@RequestParam("cid") String cid){
        System.out.println("已进入影单获得");
        List<MovieList> movieLists = centerService.getmovielistBycid(cid);
        List<Movie> movies = null;
        String dflmlist = "fail";
        for (int i = 0;i<movieLists.size();i++){
            MovieList amovielist = movieLists.get(i);
            System.out.println(amovielist.toString());
            amovielist.setCreator(centerService.getCnameByuid(amovielist.getCreator()));
            if(amovielist.getMltype().equals("defaultlike")){
                dflmlist = amovielist.getMidlist();
                httpServletRequest.getSession().setAttribute("nowmlid",amovielist.getMlid());
            }
        }
        //查找默认
        System.out.println(dflmlist);
        if(dflmlist.length() != 3){
            dflmlist = dflmlist.substring(4);
            System.out.println(dflmlist);
            String[] dflmovie = dflmlist.split(",");
            int[] midarray = new int[dflmovie.length];
            for (int i=0;i<midarray.length;i++){
                midarray[i] = Integer.parseInt(dflmovie[i]);
                System.out.println(midarray[i]);
            }


            movies = centerService.selectMoviesByMid(midarray);
            System.out.println("有这么多部电影"+movies.size());
        }
        MlistandMovie mlistandMovies = new MlistandMovie(movieLists,movies);
        String allresult = JSONObject.toJSONString(mlistandMovies);
        return allresult;
    }

    @ResponseBody
    @RequestMapping(value = "/getonemovielist", produces = "application/text; charset=utf-8")
    public String Getonemovielist(HttpServletRequest httpServletRequest,@RequestParam("mlid") int mlid,@RequestParam("midlist") String midlist,@RequestParam("mltype") String mltype){
        int justmlid = (int)httpServletRequest.getSession().getAttribute("nowmlid");
        System.out.println("上一个mlid为"+justmlid);
        httpServletRequest.getSession().setAttribute("nowmlid",mlid);
        System.out.println("当前选择的影单序号为"+mlid);
        System.out.println("点击获取影单"+midlist+mltype);
        midlist = centerService.getRealmidlist(mlid);
        if (midlist.length()==3){
            return null;
        }
        String queryMidlist = midlist.substring(4);

        System.out.println("当前影单截取后"+queryMidlist.length());

        String[] MidListstring = queryMidlist.split(",");
        int[] MidListint = new int[MidListstring.length];
        for (int i=0;i<MidListstring.length;i++){
            MidListint[i] = Integer.parseInt(MidListstring[i]);
            System.out.println(MidListint[i]);
        }

        List<Movie> movies = centerService.selectMoviesByMid(MidListint);
        String moviesinfo = JSONObject.toJSONString(movies);

        return moviesinfo;
    }

    @ResponseBody
    @RequestMapping(value = "/delonemovie", produces = "application/text; charset=utf-8")
    public String delOneMovie(HttpServletRequest httpServletRequest,@RequestParam("mid") String mid){
        int delmlid = (int)httpServletRequest.getSession().getAttribute("nowmlid");
        String delmovieid = ","+mid;

        centerService.delMidBymlid(delmlid,delmovieid);
        String newMidlist = centerService.getNewMidlist(delmlid);
        String midlistType = newMidlist.substring(0,3);
        if(midlistType.equals("dfl")){
            String tmpid = (String) httpServletRequest.getSession().getAttribute("uid");
            int likemid = Integer.parseInt(mid);
            Movie movieitem = movieService.getMovieByMid(likemid);
            String tagtype = movieitem.getTag();
            String[] thismovietag;
            if(tagtype.indexOf("/") != -1){
                //多标签
                thismovietag = tagtype.split("/");
            }else{
                //单标签
                thismovietag = new String[]{tagtype};
            }
            Typelist typelist = new Typelist();
            String userid = (String) httpServletRequest.getSession().getAttribute("uid");
            typelist.setId(userid);
            /*减少这类指数*/
            typelist.useractvalue(thismovietag,-6);
            System.out.println(typelist.toString());
            movieService.updateTypelistinfo(typelist);
        }
        if(newMidlist.length()==3){
            return null;
        }
        String queryMidlist = newMidlist.substring(4);
        //假如当前影单为空 的处理
        String[] MidListstring = queryMidlist.split(",");
        int[] MidListint = new int[MidListstring.length];
        for (int i=0;i<MidListstring.length;i++){
            MidListint[i] = Integer.parseInt(MidListstring[i]);
        }
        List<Movie> movies = centerService.selectMoviesByMid(MidListint);
        String moviesinfo = JSONObject.toJSONString(movies);
        return moviesinfo;
    }

    @ResponseBody
    @RequestMapping(value = "/addNewMlist", produces = "application/text; charset=utf-8")
    public String AddNewMlist(@RequestParam("uid") String uid,@RequestParam("mlname") String mlname){
        System.out.println("新建影单");
        MovieList newmovieList = new MovieList(uid,mlname,"own","own",uid);
        int mlistresult = userService.addmovielist(newmovieList);

        List<MovieList> movieLists = centerService.getmovielistBycid(uid);
        String newmlist = JSONObject.toJSONString(movieLists);
        return newmlist;
    }

    @ResponseBody
    @RequestMapping(value = "/deleteMlist",produces = "application/json; charset=utf-8")
    public String DeleteMlist(HttpServletRequest httpServletRequest,@RequestParam("mlid") int mlid){
        String tmpuid = (String) httpServletRequest.getSession().getAttribute("uid");
        centerService.deleteMlistBymlid(mlid);
        List<MovieList> movieLists = centerService.getmovielistBycid(tmpuid);
        String newmlist = JSONObject.toJSONString(movieLists);
        return newmlist;
    }

    @ResponseBody
    @RequestMapping(value = "/getuserMlist",produces = "application/json; charset=utf-8")
    public String GetuserMlist(@RequestParam("uid") String uid){
        System.out.println("电影页面添加至用户影单");
        List<MovieList> movieLists = centerService.getmovielistBycid(uid);

        String result = JSONObject.toJSONString(movieLists);
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/getcentercmt",produces = "application/json; charset=utf-8")
    public String GetCenterCmt(@RequestParam("centerid") String cid) throws ParseException {
        System.out.println("进入获取用户评论"+cid);
        List<Comment> comments = centerService.getCommentsBycid(cid);
        String result = JSONObject.toJSONString(comments);
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/delcentercmt",produces = "application/json; charset=utf-8")
    public String DelCenterCmt(@RequestParam("mid") int mid,@RequestParam("centerid") String cid,@RequestParam("cmtid") int cmtid) throws ParseException {
        System.out.println("进入删除用户评论"+cid+"评论编号"+cmtid);
        //删除评论
        centerService.delCommentBycmtid(cmtid);
        centerService.changeMovieCmtnum(mid);
        List<Comment> comments = centerService.getCommentsBycid(cid);
        String result = JSONObject.toJSONString(comments);
        return result;
    }

}
