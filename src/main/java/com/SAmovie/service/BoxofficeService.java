package com.SAmovie.service;

import com.SAmovie.model.BoxofficeDetail;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by scamonia on 2018/4/15.
 */
public interface BoxofficeService {

    Map<String,String> querygeneralInfo();

    ArrayList<BoxofficeDetail> querydetailInfo();
}
