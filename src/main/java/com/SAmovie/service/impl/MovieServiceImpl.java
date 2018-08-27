package com.SAmovie.service.impl;

import com.SAmovie.mapper.MovieMapper;
import com.SAmovie.model.Comment;
import com.SAmovie.model.Movie;
import com.SAmovie.model.Typelist;
import com.SAmovie.service.MovieService;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Created by scamonia on 2018/5/3.
 */
@Service
public class MovieServiceImpl implements MovieService{
    @Resource
    MovieMapper movieMapper;

    @Override
    public List<Movie> searchMoviesByName(String moviename) {
        System.out.println("已进入searchMovieByName");
        List<Movie> searchlist = movieMapper.selectByNameLike(moviename);
        System.out.println(searchlist.toString());
        return searchlist;
    }

    @Override
    public Movie getMovieByMid(int mid) {
        Movie movieitem = movieMapper.selectByMid(mid);
        return movieitem;
    }

    @Override
    public int judgelikedByMid(String likemid, String uid) {
        Map result = movieMapper.selectBylikemid(likemid,uid);
        if (result != null){
            return 1;
        }
        return 0;
    }

    @Override
    public float judgestarByMidandUid(int mid, String uid) {
        Map result = movieMapper.selectByMidandUid(mid,uid);
        if (result != null){
            System.out.println("查到为"+result.get("score").toString());
            return Float.parseFloat(result.get("score").toString());
        }
        return -1;
    }

    @Override
    public void updateUserLiked(String movieid, int boollike, String uid){
        System.out.println("进入用户默认喜欢啦");
        movieMapper.updateUserLikelist(movieid,boollike,uid);
    }

    @Override
    public int insertScore(String uid, int mid, float score) {
        int result = movieMapper.insertScoreinfo(uid,mid,score);
        return result;
    }

    @Override
    public int insertCmt(Comment comment) {
        int result = movieMapper.insertComment(comment);
        return 0;
    }

    @Override
    public List<Comment> getCommentsByMid(int mid, int start, int size) throws ParseException {
        List<Comment> results = movieMapper.selectComments(mid,start,size);
        System.out.println("你好评论获取");
        for (int i=0;i<results.size();i++){
            Comment comment = results.get(i);
            SimpleDateFormat sdf1= new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);

            SimpleDateFormat sdf2= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String trans = String.valueOf(comment.getCmtime());
            System.out.println("trans"+trans);
            comment.setPagetime(sdf2.format(sdf1.parse(trans)));
        }
        return results;
    }

    @Override
    public void updateCmtitem(int mid) {
        movieMapper.updateCommentitem(mid);
    }

    @Override
    public void collectMovieintoMList(int mlid, String midlist) {
        movieMapper.updateMlistcontent(mlid,midlist);
    }

    @Override
    public void increaseScoreitem(int mid) {
        movieMapper.updateScoreitem(mid);
    }

    @Override
    public void updateTypelistinfo(Typelist typelist) {
        movieMapper.updateUserTypelist(typelist);
    }
}
