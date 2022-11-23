package com.awards.repository;

import com.awards.model.entity.StudioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudioRepository extends JpaRepository<StudioEntity, Long> {

    StudioEntity findByName(String name);

}
