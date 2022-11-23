package com.awards.repository;

import com.awards.model.entity.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<MovieEntity, Long> {

    MovieEntity findByTitle(String title);

    List<MovieEntity> findAllByWinnerIsTrue();
}
