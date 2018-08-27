package com.SAmovie.controller;

import com.SAmovie.model.Movie;
import com.SAmovie.service.RecommandService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by scamonia on 2018/5/17.
 */

@Controller
public class RecommandController {
    @Resource
    RecommandService recommandService;

    @ResponseBody
    @RequestMapping(value = "/getSimi",produces = "application/json; charset=utf-8")
    public String Getsimi(@RequestParam("mid") int mid){
        System.out.println("相似查询电影id为"+mid);
        List<Movie> simiMovies = null;
        Movie targetMovie = recommandService.getTargetMovie(mid);
        System.out.println(targetMovie.toString());
        String movietag = targetMovie.getTag();
        Random random = new Random();

        if(movietag.indexOf("/") != -1){
            //多标签型电影
            String[] tags = movietag.split("/");
            List<String> taglike = new ArrayList<>();
            for (String tag:tags){
                taglike.add(tag);
            }
            simiMovies = recommandService.getSimimovies(taglike);
            System.out.println(simiMovies.toString()+simiMovies.size());
            //删除自身重复
            for (Movie getmovie:simiMovies){
                if (getmovie.getMid()==mid){
                    simiMovies.remove(getmovie);
                    break;
                }
            }
            //不足5部时添加其余
            if(simiMovies.size()<5){
                System.out.println("不足五部");
                String onetag = taglike.get(random.nextInt(tags.length));
                int othernum = 5-simiMovies.size();
                List<Movie> othermovies = recommandService.getOthermovie(mid,onetag,othernum);
                for (int i = 0;i<othermovies.size();i++){
                    System.out.println("尝试添加");
                    simiMovies.add(othermovies.get(i));
                }
            }
        }else{
            System.out.println("单标签");
            List<Movie> othermovies = recommandService.getOthermovie(mid,movietag,5);
            for (int i = 0;i<othermovies.size();i++){
                System.out.println("尝试添加");
                simiMovies.add(othermovies.get(i));
            }
        }
        System.out.println(simiMovies.toString());
        String simimovieresults = JSONObject.toJSONString(simiMovies);
        return simimovieresults;
    }

}
