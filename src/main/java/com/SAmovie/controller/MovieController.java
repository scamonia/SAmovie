package com.SAmovie.controller;

import com.SAmovie.model.Comment;
import com.SAmovie.model.Movie;
import com.SAmovie.model.Typelist;
import com.SAmovie.service.MovieService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by scamonia on 2018/5/2.
 */
@Controller
public class MovieController {

    @Resource
    MovieService movieService;

    @RequestMapping("/movieinfo")
    public String Movieinfo(){
        System.out.println("已进入movieinfo");
        return "movie/movieinfo";
    }

    @ResponseBody
    @RequestMapping(value = "/search", method = RequestMethod.POST,produces = "application/json; charset=utf-8")
    public String Search(HttpServletRequest request){
        String moviename = request.getParameter("search_text");
        List<Movie> searchlist = movieService.searchMoviesByName(moviename);
        System.out.println("search内容为"+moviename);
        for (int i=0;i<searchlist.size();i++){
            System.out.println(searchlist.get(i).toString());
        }
        String result = JSONObject.toJSONString(searchlist);
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/movieinfo/description", method = RequestMethod.POST)
    public String Description(HttpServletRequest httpServletRequest) throws ParseException {
        int mid = Integer.parseInt(httpServletRequest.getParameter("mid"));
        String uid = (String) httpServletRequest.getSession().getAttribute("uid");
        //查找电影信息
        Movie movieitem = movieService.getMovieByMid(mid);
        //增加阅读量
        movieService.increaseScoreitem(mid);
        String forsearch = movieitem.getName().split(" ")[0];
        System.out.println("forsearch"+forsearch);

        //查找电影评论
        List<Comment> comments = movieService.getCommentsByMid(mid, 0, 5);
        String cmts = JSONObject.toJSONString(comments);
        httpServletRequest.getSession().setAttribute("movieinfo",movieitem);
        httpServletRequest.getSession().setAttribute("moviebaike",forsearch);
        httpServletRequest.getSession().setAttribute("cmts",cmts);

        System.out.println("mid为"+movieitem.toString()+cmts);
        //判断是否登录且对该电影喜欢、评分与否并增加用户指数
        if(uid != null){
            //判断是否喜欢
            String likemid = String.valueOf(mid);
            int likedresult = movieService.judgelikedByMid(likemid,uid);
            System.out.println("喜欢状态为"+likedresult);
            httpServletRequest.getSession().setAttribute("likedstatus",likedresult);
            //判断是否有评分
            float userstar = movieService.judgestarByMidandUid(mid,uid);
            httpServletRequest.getSession().setAttribute("userstar",userstar*2);
            System.out.println("评分为"+userstar);

            //增加用户指数
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
            typelist.setId(uid);
            typelist.useractvalue(thismovietag,2);
            System.out.println(typelist.toString());
            movieService.updateTypelistinfo(typelist);

        }
        return "success";
    }

    @ResponseBody
    @RequestMapping(value = "/likedmovie",method = RequestMethod.POST)
    public String Likedmovie(@RequestParam("movieid") int mid,@RequestParam("boollike") int boollike,@RequestParam("userid") String uid){
        System.out.println("likedmovie"+mid+"收藏状态为"+boollike);
        String movieid = ","+String.valueOf(mid);

        movieService.updateUserLiked(movieid,boollike,uid);

        Movie movieitem = movieService.getMovieByMid(mid);
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
        typelist.setId(uid);
        if(boollike==0){
            /*减少这类指数*/
            typelist.useractvalue(thismovietag,-6);
            System.out.println(typelist.toString());
            movieService.updateTypelistinfo(typelist);
        }else {
            /*增加这类指数*/
            typelist.useractvalue(thismovietag,6);
            System.out.println(typelist.toString());
            movieService.updateTypelistinfo(typelist);
        }
        return "success";
    }

    @ResponseBody
    @RequestMapping(value = "/sendscore",method = RequestMethod.POST, produces = "application/text; charset=utf-8")
    public String Sendscore(@RequestParam("userid") String uid,@RequestParam("movieid") int mid,@RequestParam("star") float score){
        int scoreresult = movieService.insertScore(uid,mid,score);
        System.out.println("sendscore"+uid+mid+"score"+score);
        return "评分成功";
    }

    @ResponseBody
    @RequestMapping(value = "/mycmt",produces = "application/text; charset=utf-8")
    public String Mycmt(@RequestParam("uid") String uid, @RequestParam("mid") int mid, @RequestParam("uname") String uname, @RequestParam("comment") String content, @RequestParam("cmttime") String cmttime) throws ParseException {

        System.out.println("评论内容为"+uid+mid+uname+content+cmttime);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date cmtime = format.parse(cmttime);
        Comment comment = new Comment(mid,uid,uname,content,cmtime);
        //增加评论数量
        movieService.updateCmtitem(mid);
        int result = movieService.insertCmt(comment);

        //更新评论数据
        List<Comment> comments = movieService.getCommentsByMid(mid, 0, 5);
        String newresults = JSONObject.toJSONString(comments);
        return newresults;
    }

    //分页获得数据
    @ResponseBody
    @RequestMapping(value = "/pagecmt",produces = "application/text; charset=utf-8")
    public String Pagecmt(@RequestParam("mid") int mid,@RequestParam("pagenum") int pagenum,@RequestParam("pagesize") int size) throws ParseException {

        System.out.println("页码"+mid+"页号"+pagenum+"大小"+size);
        List<Comment> comments = movieService.getCommentsByMid(mid, pagenum*size, size);
        String cmts = JSONObject.toJSONString(comments);
        return cmts;

    }

    //收藏到用户影单
    @ResponseBody
    @RequestMapping(value = "/addintoUMlist",produces = "application/text; charset=utf-8")
    public String AddinfoUMlist(HttpServletRequest httpServletRequest,@RequestParam("mid") String mid,@RequestParam("mlid") int mlid,@RequestParam("midlist") String midlist){
        System.out.println("用户影单信息为"+"mid"+mid+"mlid"+mlid+"midlist"+midlist);
        if(midlist.indexOf(mid) != -1){
            return "alreadyhave";
        }
        String mlisttype = midlist.substring(0,3);
        if (mlisttype.equals("dfl")){
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
            /*增加这类指数*/
                typelist.useractvalue(thismovietag,6);
                System.out.println(typelist.toString());
                movieService.updateTypelistinfo(typelist);

        }
        midlist = midlist+","+mid;
        movieService.collectMovieintoMList(mlid,midlist);
        if (mlisttype.equals("dfl")){
            return "dflsuccess";
        }else {
            return "ownsuccess";
        }
    }
}
