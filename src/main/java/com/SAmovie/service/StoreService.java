package com.SAmovie.service;

import com.SAmovie.model.Movie;

import java.util.List;

/**
 * Created by scamonia on 2018/5/15.
 */
public interface StoreService {
    List<Movie> gettypemovies(String[] tylist);

    List<Movie> getalltypemovies();
}
