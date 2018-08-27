package com.SAmovie.controller;

import com.SAmovie.model.Movie;
import com.SAmovie.service.StoreService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by scamonia on 2018/5/14.
 */
@Controller
public class StoreController {

    @Resource
    StoreService storeService;

    @RequestMapping(value = "/store")
    public String Store(){
        return "/storehouse/store";
    }

    @ResponseBody
    @RequestMapping(value = "/store/gettypemovie",produces = "application/text; charset=utf-8")
    public String Gettypemovie(@RequestParam(value = "typelist",required = false) String[] tylist){
        List<Movie> movies = null;
        if(tylist != null) {
            System.out.println(tylist[0]);
             movies = storeService.gettypemovies(tylist);
        }else {
             System.out.println("进入默认store列表");
             movies = storeService.getalltypemovies();
        }
        System.out.println(movies.size());
        String results = JSONObject.toJSONString(movies);
        return results;
    }
}
