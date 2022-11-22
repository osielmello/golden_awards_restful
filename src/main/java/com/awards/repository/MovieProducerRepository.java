package com.awards.repository;

import com.awards.entity.MovieEntity;
import com.awards.entity.MovieProducerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieProducerRepository extends JpaRepository<MovieProducerEntity, Long> {

}
