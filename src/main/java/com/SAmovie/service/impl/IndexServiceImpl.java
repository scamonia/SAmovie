package com.SAmovie.service.impl;

import com.SAmovie.mapper.IndexMapper;
import com.SAmovie.model.Movie;
import com.SAmovie.service.IndexService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by scamonia on 2018/5/14.
 */
@Service
public class IndexServiceImpl implements IndexService {
    @Resource
    IndexMapper indexMapper;

    @Override
    public List<Movie> getFivemovies() {
        List<Movie> movies = indexMapper.selectFiveMovies();
        return movies;
    }
}
