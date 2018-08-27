package com.SAmovie.service;

import com.SAmovie.model.MovieList;
import com.SAmovie.model.Typelist;
import com.SAmovie.model.User;
import com.SAmovie.model.UserInfo;

/**
 * Created by scamonia on 2018/4/23.
 */
public interface UserService {
    User queryuserByname(String username);

    int judgeusername(String username);

    int adduser(User user);

    int addtypelist(Typelist typelist);

    int addmovielist(MovieList likedList);

    int adduserinfo(UserInfo userInfo);
}
