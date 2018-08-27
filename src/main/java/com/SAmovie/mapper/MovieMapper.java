package com.SAmovie.mapper;

import com.SAmovie.model.Comment;
import com.SAmovie.model.Movie;
import com.SAmovie.model.Typelist;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by scamonia on 2018/5/3.
 */
@Repository
public interface MovieMapper {

    List<Movie> selectByNameLike(String moviename);

    Movie selectByMid(int mid);

    Map selectBylikemid(String likemid, String uid);

    Map selectByMidandUid(int mid, String uid);

    void updateUserLikelist(@Param("mid") String movieid, @Param("bool") int boollike,@Param("uid") String uid);

    int insertScoreinfo(@Param("uid") String uid,@Param("mid") int mid,@Param("score") float score);

    int insertComment(Comment comment);

    List<Comment> selectComments(int mid, int start, int size);

    void updateCommentitem(int mid);

    void updateMlistcontent(@Param("mlid") int mlid,@Param("midlist") String midlist);

    void updateScoreitem(int mid);

    void updateUserTypelist(Typelist typelist);
}
