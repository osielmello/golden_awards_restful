package com.awards.service;

import com.awards.model.entity.MovieEntity;
import com.awards.repository.MovieRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Serviço que encapsula as regras de negócio e serve de camada para o repositório de movie.
 *
 * @author Osiel
 */
@Service
@Slf4j
public class MovieService {

    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    /**
     * Salva e retorna o filme.
     *
     * @param title  {@link String]}
     * @param year   {@link Integer}
     * @param winner {@link Boolean}
     * @return filme salvo {@link MovieEntity]}
     */
    public MovieEntity saveMovie(String title, int year, boolean winner) {
        MovieEntity movie = MovieEntity.builder()
                .title(title)
                .year(year)
                .winner(winner)
                .build();

        return this.movieRepository.save(movie);
    }

    public List<MovieEntity> findAllWinning() {
        return this.movieRepository.findAllByWinnerIsTrue();
    }

}
