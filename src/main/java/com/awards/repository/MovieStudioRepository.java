package com.awards.repository;

import com.awards.model.entity.MovieEntity;
import com.awards.model.entity.MovieStudioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieStudioRepository extends JpaRepository<MovieStudioEntity, Long> {

    List<MovieStudioEntity> findByMovie(MovieEntity movie);

    void deleteByMovie(MovieEntity movie);
}
