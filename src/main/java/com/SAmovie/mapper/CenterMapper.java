package com.SAmovie.mapper;

import com.SAmovie.model.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by scamonia on 2018/5/9.
 */
@Repository
public interface CenterMapper {
    String selectNameByuid(String uid);

    UserInfo selectInfoByuid(String uid);

    void updateUserinfoByuid(@Param("uid") String uid,@Param("gender") String gender,@Param("birthday") String birthday,@Param("introself") String introself);

    List<MovieList> selectMlistBycid(String cid);

    List<Movie> selectMoviecontentByMid(int[] arraymid);

    void updateMlistByMlid(@Param("mlid") int delmlid,@Param("delmovieid") String delmovieid);

    String selectNewMidlist(int delmlid);

    void deleteMlistBymlid(int mlid);

    List<Comment> selectCommentsbycid(String cid);

    void deleteCommentBycmtid(int cmtid);

    void updateCmtnumBymid(int mid);

    String selectNowmidlist(int mlid);

    //用户个人推荐
    Typelist selectTypelist(String tmpid);

    List<Movie> selectTopdflmovies(int num);

    Movie selectMovieByTag(String onetag);

    Map selectlikedMidByUid(String tmpid);

    Movie selectlikedMovieByTag(@Param("likedmid") int likedmid,@Param("taglike") List<String> taglike);

    Movie selectFirstMovieByOnetag(@Param("mid") int likedmid,@Param("onetag") String onerandom);

    MovieList selectotherUserlist(@Param("tmpid") String tmpid,@Param("likedmid") String likedmid);

    Movie selectThirdMovie(@Param("threetag") List<String> threetag,@Param("existmid") List<Integer> recmovieid);

    String selectNuidByTypeval(@Param("fourth") String fourthCL,@Param("nearval") int fourthNearvalue);

    MovieList selectNearList(@Param("nid") String nearuserid,@Param("type") String type);

    Movie selectFivemovie(@Param("recmid") List<Integer> recmovieid);
}
