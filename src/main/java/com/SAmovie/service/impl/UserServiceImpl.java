package com.SAmovie.service.impl;

import com.SAmovie.mapper.UserMapper;
import com.SAmovie.model.MovieList;
import com.SAmovie.model.Typelist;
import com.SAmovie.model.User;
import com.SAmovie.model.UserInfo;
import com.SAmovie.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by scamonia on 2018/4/23.
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    UserMapper userMapper;

    @Override
    public User queryuserByname(String username){
        User resultuser = userMapper.queryUserByname(username);
        return  resultuser;
    }

    @Override
    public int judgeusername(String username){
        int result = 0;
        Map checkresult = userMapper.selectUsername(username);

        if(checkresult != null){
            System.out.println("checkresult不为空");
            result = 1;
        }
        System.out.println("此时此刻result查重为"+result);
        return result;
    }

    @Override
    public int adduser(User user){
        System.out.println("已进入adduser");
        int result = userMapper.insertUser(user);
        return result;
    }

    @Override
    public int addtypelist(Typelist typelist){
        System.out.println("已进入addtypelist");
        int result = userMapper.insertTypelist(typelist);
        return result;
    }

    @Override
    public int addmovielist(MovieList likedList) {
        System.out.println("已进入addmovielist");
        int result = userMapper.insertMovielist(likedList);
        return result;
    }

    @Override
    public int adduserinfo(UserInfo userInfo) {
        int result = userMapper.insertUserinfo(userInfo);
        return result;
    }
}
