package com.SAmovie.model;

import java.util.List;

/**
 * Created by scamonia on 2018/5/11.
 */
public class MlistandMovie {
    List<MovieList> movieLists;
    List<Movie> movies;

    public MlistandMovie(List<MovieList> movieLists, List<Movie> movies) {
        this.movieLists = movieLists;
        this.movies = movies;
    }

    public List<MovieList> getMovieLists() {
        return movieLists;
    }

    public void setMovieLists(List<MovieList> movieLists) {
        this.movieLists = movieLists;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }
}
