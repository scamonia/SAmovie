package com.SAmovie.mapper;

import com.SAmovie.model.Movie;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by scamonia on 2018/5/17.
 */

@Repository
public interface RecommandMapper {
    Movie selectTargetMovie(int mid);

    List<Movie> selectSimiMovie(List<String> taglike);

    List<Movie> selectOtherMovie(@Param("mid") int mid,@Param("onetag") String onetag,@Param("othernum") int othernum);
}
