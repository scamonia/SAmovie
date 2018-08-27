package com.SAmovie.mapper;

import com.SAmovie.model.BoxofficeDetail;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by scamonia on 2018/4/15.
 */
@Repository
public interface BoxofficeMapper {

    Map<String,String> generalinfo();

    ArrayList<BoxofficeDetail> detailinfo();
}
