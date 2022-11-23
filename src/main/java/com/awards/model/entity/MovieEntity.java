package com.awards.model.entity;

import com.awards.model.entity.base.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@AllArgsConstructor
@Builder
@Data
@EqualsAndHashCode(callSuper = true)
@Entity(name = "movies")
@NoArgsConstructor
public class MovieEntity extends AbstractEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "year_movie")
    private Integer year;

    @Column(name = "winner")
    private Boolean winner;
}
