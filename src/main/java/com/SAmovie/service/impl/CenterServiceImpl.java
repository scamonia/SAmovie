package com.SAmovie.service.impl;

import com.SAmovie.mapper.CenterMapper;
import com.SAmovie.model.*;
import com.SAmovie.service.CenterService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Created by scamonia on 2018/5/9.
 */
@Service
public class CenterServiceImpl implements CenterService {
    @Resource
    CenterMapper centerMapper;

    @Override
    public String getCnameByuid(String uid) {
        String cname = centerMapper.selectNameByuid(uid);
        return cname;
    }

    @Override
    public UserInfo getPageinfoByuid(String uid) {
        UserInfo info = centerMapper.selectInfoByuid(uid);
        return info;
    }

    @Override
    public void updateUserinfo(String uid, String gender, String birthday, String introself) {
        centerMapper.updateUserinfoByuid(uid,gender,birthday,introself);
    }

    @Override
    public List<MovieList> getmovielistBycid(String cid) {
        List<MovieList> movieLists = centerMapper.selectMlistBycid(cid);
        return movieLists;
    }

    @Override
    public List<Movie> selectMoviesByMid(int[] arraymid) {
        List<Movie> movies = centerMapper.selectMoviecontentByMid(arraymid);
        return movies;
    }

    @Override
    public void delMidBymlid(int delmlid, String delmovieid) {
        centerMapper.updateMlistByMlid(delmlid,delmovieid);
    }

    @Override
    public String getNewMidlist(int delmlid) {
        String newMidlist = centerMapper.selectNewMidlist(delmlid);
        return newMidlist;
    }

    @Override
    public void deleteMlistBymlid(int mlid) {
        centerMapper.deleteMlistBymlid(mlid);
    }

    @Override
    public List<Comment> getCommentsBycid(String cid) throws ParseException {
        List<Comment> results = centerMapper.selectCommentsbycid(cid);
        for (int i=0;i<results.size();i++){
            Comment comment = results.get(i);
            SimpleDateFormat sdf1= new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);

            SimpleDateFormat sdf2= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String trans = String.valueOf(comment.getCmtime());
            System.out.println("trans"+trans);
            comment.setPagetime(sdf2.format(sdf1.parse(trans)));
            System.out.println(comment.toString());
        }
        return results;
    }

    @Override
    public void delCommentBycmtid(int cmtid) {
        centerMapper.deleteCommentBycmtid(cmtid);
    }

    @Override
    public void changeMovieCmtnum(int mid) {
        centerMapper.updateCmtnumBymid(mid);
    }

    @Override
    public String getRealmidlist(int mlid) {
        String realmidlist = centerMapper.selectNowmidlist(mlid);
        return realmidlist;
    }

    @Override
    public Typelist getTypelistBycid(String tmpid) {
        Typelist typelist = centerMapper.selectTypelist(tmpid);
        return typelist;
    }
    /*推荐*/
    @Override
    public List<Movie> getTopdflmovies(int num) {
        List<Movie> result = centerMapper.selectTopdflmovies(num);
        return result;
    }

    @Override
    public Movie selectMoviesByTag(String onetag) {
        Movie result = centerMapper.selectMovieByTag(onetag);
        return result;
    }

    @Override
    public int getGoodScoremidByuid(String tmpid) {
        Map result = centerMapper.selectlikedMidByUid(tmpid);
        if (result==null){
            return -1;
        }else {
            return (Integer)result.get("mid");
        }
    }

    @Override
    public Movie getSimimovies(int likedmid, List<String> taglike) {
        Movie result = centerMapper.selectlikedMovieByTag(likedmid,taglike);
        return result;
    }

    @Override
    public Movie getFirstMovieByOnetag(int likedmid, String onerandom) {
        Movie result = centerMapper.selectFirstMovieByOnetag(likedmid,onerandom);
        return result;
    }

    @Override
    public MovieList getMovielistlikeUser(String tmpid, String likedmid) {
        MovieList movieList = centerMapper.selectotherUserlist(tmpid,likedmid);
        return movieList;
    }

    @Override
    public Movie getThirdMovie(List<String> threetag, List<Integer> recmovieid) {
        Movie result = centerMapper.selectThirdMovie(threetag,recmovieid);
        return result;
    }

    @Override
    public String getNuidByTypeval(String fourthCL, int fourthNearvalue) {
        String Nuid = centerMapper.selectNuidByTypeval(fourthCL,fourthNearvalue);
        return Nuid;
    }

    @Override
    public MovieList getnearDFList(String nearuserid, String type) {
        MovieList result = centerMapper.selectNearList(nearuserid,type);
        return result;
    }

    @Override
    public Movie getRandomMovie(List<Integer> recmovieid) {
        Movie result = centerMapper.selectFivemovie(recmovieid);
        return result;
    }

}
