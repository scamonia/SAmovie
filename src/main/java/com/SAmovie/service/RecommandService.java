package com.SAmovie.service;

import com.SAmovie.model.Movie;

import java.util.List;

/**
 * Created by scamonia on 2018/5/17.
 */
public interface RecommandService {
    Movie getTargetMovie(int mid);

    List<Movie> getSimimovies(List<String> taglike);

    List<Movie> getOthermovie(int mid,String onetag, int othernum);
}
