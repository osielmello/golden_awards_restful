package com.awards.repository;

import com.awards.model.entity.MovieEntity;
import com.awards.model.entity.MovieProducerEntity;
import com.awards.model.entity.MovieStudioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieProducerRepository extends JpaRepository<MovieProducerEntity, Long> {

    List<MovieProducerEntity> findByMovie(MovieEntity movie);

    void deleteByMovie(MovieEntity movie);

    @Query(value = "select mp.id, mp.movie_id, producer_id from movie_producers mp left join movies m on(mp.movie_id=m.id) where m.winner=true", nativeQuery = true)
    List<MovieProducerEntity> findAllByMovieIsWinner();
}
