package com.SAmovie.controller;

import com.SAmovie.model.BoxofficeDetail;
import com.SAmovie.service.BoxofficeService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Map;


/**
 * Created by scamonia on 2018/4/12.
 */

@Controller
public class BoxofficeController {

    @Resource
    BoxofficeService boxofficeService;

    @RequestMapping("/boxoffice")
    public String Boxoffice(){
        System.out.println("已进入boxoffice");
        return "boxoffice/boxoffice";
    }

    @ResponseBody
    @RequestMapping(value = "/boxoffice/general")
    public String BoxofficeGerenal(){
        System.out.println("已进入general");
        Map<String,String> generalInfo = boxofficeService.querygeneralInfo();
        System.out.println("结果为"+generalInfo);
        String result = JSONObject.toJSONString(generalInfo);
        System.out.println(result);
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/boxoffice/detail",produces = "application/json; charset=utf-8")
    public String BoxofficeDetail(){
        System.out.println("已进入detail");
        ArrayList<BoxofficeDetail> detailInfo = boxofficeService.querydetailInfo();
        String result = JSONObject.toJSONString(detailInfo);
        return result;
    }


}
