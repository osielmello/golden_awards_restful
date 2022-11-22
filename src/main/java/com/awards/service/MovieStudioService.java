package com.awards.service;

import com.awards.entity.MovieEntity;
import com.awards.entity.MovieStudioEntity;
import com.awards.entity.StudioEntity;
import com.awards.repository.MovieRepository;
import com.awards.repository.MovieStudioRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Serviço que encapsula as regras de negócio e serve de camada para o repositório de estúdios de filmes.
 *
 * @author Osiel
 */
@Service
@Slf4j
public class MovieStudioService {

    private final MovieStudioRepository movieStudioRepository;

    public MovieStudioService(MovieStudioRepository movieStudioRepository) {
        this.movieStudioRepository = movieStudioRepository;
    }

    /**
     * Salva todos os estúdios do filme.
     *
     * @param movie   {@link MovieEntity}
     * @param studios {@link List<StudioEntity>}
     */
    public void saveStudios(MovieEntity movie, List<StudioEntity> studios) {
        List<MovieStudioEntity> movieStudios = studios.stream()
                .map(s -> MovieStudioEntity.builder().movie(movie).studio(s).build())
                .collect(Collectors.toList());

        this.movieStudioRepository.saveAll(movieStudios);
    }
}
