package com.awards.repository;

import com.awards.entity.MovieProducerEntity;
import com.awards.entity.MovieStudioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieStudioRepository extends JpaRepository<MovieStudioEntity, Long> {

}
