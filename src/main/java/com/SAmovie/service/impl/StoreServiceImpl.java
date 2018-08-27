package com.SAmovie.service.impl;

import com.SAmovie.mapper.StoreMapper;
import com.SAmovie.model.Movie;
import com.SAmovie.service.StoreService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by scamonia on 2018/5/15.
 */
@Service
public class StoreServiceImpl implements StoreService{
    @Resource
    StoreMapper storeMapper;

    @Override
    public List<Movie> gettypemovies(String[] tylist) {
        List<Movie> movies = storeMapper.selectBytylist(tylist);
        return movies;
    }

    @Override
    public List<Movie> getalltypemovies() {
        List<Movie> movies = storeMapper.selectAlltypeMovie();
        return movies;
    }
}
