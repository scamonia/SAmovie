package com.SAmovie.service.impl;

import com.SAmovie.mapper.RecommandMapper;
import com.SAmovie.model.Movie;
import com.SAmovie.service.RecommandService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by scamonia on 2018/5/17.
 */

@Service
public class RecommandServiceImpl implements RecommandService {
    @Resource
    RecommandMapper recommandMapper;

    @Override
    public Movie getTargetMovie(int mid) {
        Movie result = recommandMapper.selectTargetMovie(mid);
        return result;
    }

    @Override
    public List<Movie> getSimimovies(List<String> taglike) {
        List<Movie> results =recommandMapper.selectSimiMovie(taglike);
        return results;
    }

    @Override
    public List<Movie> getOthermovie( int mid,String onetag, int othernum) {
        List<Movie> othermovies = recommandMapper.selectOtherMovie(mid,onetag,othernum);
        return othermovies;
    }
}
