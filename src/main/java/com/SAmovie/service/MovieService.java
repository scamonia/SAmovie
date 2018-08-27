package com.SAmovie.service;

import com.SAmovie.model.Comment;
import com.SAmovie.model.Movie;
import com.SAmovie.model.Typelist;

import java.text.ParseException;
import java.util.List;

/**
 * Created by scamonia on 2018/5/3.
 */
public interface MovieService {
    List<Movie> searchMoviesByName(String moviename);

    Movie getMovieByMid(int mid);

    int judgelikedByMid(String likemid, String uid);

    float judgestarByMidandUid(int mid, String uid);

    void updateUserLiked(String movieid, int boollike, String uid);

    int insertScore(String uid, int mid, float score);

    int insertCmt(Comment comment);

    List<Comment> getCommentsByMid(int mid, int start, int size) throws ParseException;

    void updateCmtitem(int mid);

    void collectMovieintoMList(int mlid, String midlist);

    void increaseScoreitem(int mid);

    void updateTypelistinfo(Typelist typelist);
}
