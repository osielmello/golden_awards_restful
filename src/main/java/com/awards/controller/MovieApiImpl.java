package com.awards.controller;

import com.awards.api.MovieApi;
import com.awards.controller.base.AbstractCrud;
import com.awards.model.dto.MovieDto;
import com.awards.model.entity.MovieEntity;
import com.awards.model.entity.MovieProducerEntity;
import com.awards.model.entity.MovieStudioEntity;
import com.awards.model.entity.ProducerEntity;
import com.awards.model.entity.StudioEntity;
import com.awards.repository.MovieRepository;
import com.awards.service.MovieProducerService;
import com.awards.service.MovieStudioService;
import com.awards.service.ProducerService;
import com.awards.service.StudioService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Crud de filmes.
 *
 * @author Osiel
 */
@Slf4j
@RestController
public class MovieApiImpl extends AbstractCrud<MovieEntity, MovieDto, Long> implements MovieApi {

    private final StudioService studioService;
    private final ProducerService producerService;

    private final MovieProducerService movieProducerService;

    private final MovieStudioService movieStudioService;

    public MovieApiImpl(MovieRepository movieRepository, StudioService studioService, ProducerService producerService,
                        MovieProducerService movieProducerService, MovieStudioService movieStudioService) {
        super(movieRepository);
        this.studioService = studioService;
        this.producerService = producerService;
        this.movieProducerService = movieProducerService;
        this.movieStudioService = movieStudioService;
    }

    @Transactional
    @Override
    public MovieDto create(MovieDto dto) {
        MovieEntity movieEntity = this.repository.save(this.toEntity(dto));

        MovieDto movieDto = this.toDto(movieEntity);
        saveOthers(dto, movieEntity);

        movieDto.setProducers(dto.getProducers());
        movieDto.setStudios(dto.getStudios());

        return movieDto;
    }

    @Transactional
    @Override
    public MovieDto update(Long id, MovieDto dto) {
        MovieEntity movieEntity = this.repository.findById(id).orElse(null);
        if (movieEntity == null) {
            throw new RuntimeException("Not found [" + id + "]");
        }

        this.movieStudioService.deleteByMovie(movieEntity);
        this.movieProducerService.deleteByMovie(movieEntity);

        movieEntity = this.repository.save(this.toEntity(dto));

        MovieDto movieDto = this.toDto(movieEntity);
        saveOthers(dto, movieEntity);

        movieDto.setProducers(dto.getProducers());
        movieDto.setStudios(dto.getStudios());

        return movieDto;
    }

    @Override
    public MovieDto read(Long id) {
        MovieEntity movieEntity = this.repository.findById(id).orElse(null);
        MovieDto movieDto = null;
        if (movieEntity != null) {
            movieDto = this.toDto(movieEntity);
            List<MovieStudioEntity> studios = this.movieStudioService.findByMovie(movieEntity);
            movieDto.setStudios(studios.stream().map(m -> m.getStudio().getName()).collect(Collectors.toList()));

            List<MovieProducerEntity> producers = this.movieProducerService.findByMovie(movieEntity);
            movieDto.setProducers(producers.stream().map(m -> m.getProducer().getName()).collect(Collectors.toList()));
        }
        return movieDto;
    }

    @Transactional
    @Override
    public void delete(Long id) {
        MovieEntity movieEntity = this.repository.findById(id).orElse(null);
        if (movieEntity == null) {
            throw new RuntimeException("Not found [" + id + "]");
        }

        this.movieStudioService.deleteByMovie(movieEntity);
        this.movieProducerService.deleteByMovie(movieEntity);

        super.delete(id);
    }

    @Override
    protected MovieEntity toEntity(MovieDto movieDto) {
        return MovieEntity.builder()
                .id(movieDto.getId())
                .title(movieDto.getTitle())
                .winner(movieDto.getWinner())
                .year(movieDto.getYear())
                .build();
    }

    @Override
    protected MovieDto toDto(MovieEntity movieEntity) {
        return MovieDto.builder()
                .id(movieEntity.getId())
                .title(movieEntity.getTitle())
                .winner(movieEntity.getWinner())
                .year(movieEntity.getYear())
                .build();
    }

    /**
     * Salva os dados de produtores e estúdios.
     *
     * @param dto         {@link MovieDto}
     * @param movieEntity MovieEntity
     */
    private void saveOthers(MovieDto dto, MovieEntity movieEntity) {
        if (dto.getProducers() != null && !dto.getProducers().isEmpty()) {
            List<ProducerEntity> producerEntities = this.producerService.saveProducers(dto.getProducers());
            this.movieProducerService.saveProducers(movieEntity, producerEntities);
        }
        if (dto.getStudios() != null && !dto.getStudios().isEmpty()) {
            List<StudioEntity> studioEntities = this.studioService.saveStudios(dto.getStudios());
            this.movieStudioService.saveStudios(movieEntity, studioEntities);
        }
    }
}
