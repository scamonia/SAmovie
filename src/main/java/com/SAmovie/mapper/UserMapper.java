package com.SAmovie.mapper;

import com.SAmovie.model.MovieList;
import com.SAmovie.model.Typelist;
import com.SAmovie.model.User;
import com.SAmovie.model.UserInfo;
import org.springframework.stereotype.Repository;
import java.util.Map;

/**
 * Created by scamonia on 2018/4/23.
 */
@Repository
public interface UserMapper {
    User queryUserByname(String username);

    Map selectUsername(String username);

    int insertUser(User user);

    int insertTypelist(Typelist typelist);

    int insertMovielist(MovieList likedList);

    int insertUserinfo(UserInfo userInfo);
}
