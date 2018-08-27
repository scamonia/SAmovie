package com.SAmovie.mapper;

import com.SAmovie.model.Movie;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by scamonia on 2018/5/15.
 */

@Repository
public interface StoreMapper {
    List<Movie> selectBytylist(String[] tylist);

    List<Movie> selectAlltypeMovie();
}
