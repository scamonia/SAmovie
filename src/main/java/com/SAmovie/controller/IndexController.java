package com.SAmovie.controller;

import com.SAmovie.model.Movie;
import com.SAmovie.service.IndexService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by scamonia on 2018/5/6.
 */
@Controller
public class IndexController {
    @Resource
    IndexService indexService;

    @RequestMapping(value = "/index")
    public String Getindex(){
        return "index/index";
    }

    @ResponseBody
    @RequestMapping(value = "/index/movieinfo",produces = "application/json; charset=utf-8")
    public String GetIndex(HttpServletRequest httpServletRequest){
        List<Movie> movies = indexService.getFivemovies();
        String results = JSONObject.toJSONString(movies);
        return results;
    }
}