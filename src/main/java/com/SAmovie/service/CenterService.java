package com.SAmovie.service;

import com.SAmovie.model.*;

import java.text.ParseException;
import java.util.List;

/**
 * Created by scamonia on 2018/5/9.
 */
public interface CenterService {
    String getCnameByuid(String uid);

    UserInfo getPageinfoByuid(String uid);

    void updateUserinfo(String uid, String gender, String birthday, String introself);

    List<MovieList> getmovielistBycid(String cid);

    List<Movie> selectMoviesByMid(int[] arraymid);

    void delMidBymlid(int delmlid, String delmovieid);

    String getNewMidlist(int delmlid);

    void deleteMlistBymlid(int mlid);

    List<Comment> getCommentsBycid(String cid) throws ParseException;

    void delCommentBycmtid(int cmtid);

    void changeMovieCmtnum(int mid);

    String getRealmidlist(int mlid);

    Typelist getTypelistBycid(String tmpid);

    List<Movie> getTopdflmovies(int num);

    Movie selectMoviesByTag(String onetag);

    int getGoodScoremidByuid(String tmpid);

    Movie getSimimovies(int likedmid,List<String> taglike);

    Movie getFirstMovieByOnetag(int likedmid, String onerandom);

    MovieList getMovielistlikeUser(String tmpid, String likedmid);

    Movie getThirdMovie(List<String> threetag, List<Integer> recmovieid);

    String getNuidByTypeval(String fourthCL, int fourthNearvalue);

    MovieList getnearDFList(String nearuserid, String defaultlike);

    Movie getRandomMovie(List<Integer> recmovieid);
}
