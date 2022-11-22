package com.awards.entity;

import com.awards.entity.base.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@AllArgsConstructor
@Builder
@Data
@Entity(name = "movie_studios")
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class MovieStudioEntity extends AbstractEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "movie_id")
    private MovieEntity movie;

    @OneToOne
    @JoinColumn(name = "studio_id")
    private StudioEntity studio;
}
