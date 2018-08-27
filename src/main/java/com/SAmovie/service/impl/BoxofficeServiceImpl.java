package com.SAmovie.service.impl;

import com.SAmovie.mapper.BoxofficeMapper;
import com.SAmovie.model.BoxofficeDetail;
import com.SAmovie.service.BoxofficeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by scamonia on 2018/4/15.
 */
@Service
public class BoxofficeServiceImpl implements BoxofficeService {

    @Resource
    BoxofficeMapper boxofficeMapper;

    @Override
    public Map<String,String> querygeneralInfo(){
        System.out.println("已进入generalservice");
        Map<String,String> result = boxofficeMapper.generalinfo();
        return result;
    }
    @Override
    public ArrayList<BoxofficeDetail> querydetailInfo(){
        System.out.println("已进入detailservice");
        ArrayList<BoxofficeDetail> result = boxofficeMapper.detailinfo();
        return  result;
    }
}
