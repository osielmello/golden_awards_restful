package com.awards.service;

import com.awards.model.entity.MovieEntity;
import com.awards.model.entity.MovieProducerEntity;
import com.awards.model.entity.ProducerEntity;
import com.awards.repository.MovieProducerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Serviço que encapsula as regras de negócio e serve de camada para o repositório de produtores de filmes.
 *
 * @author Osiel
 */
@Service
@Slf4j
public class MovieProducerService {

    private final MovieProducerRepository movieProducerRepository;

    public MovieProducerService(MovieProducerRepository movieProducerRepository) {

        this.movieProducerRepository = movieProducerRepository;
    }

    /**
     * Salva todos os estúdios do filme.
     *
     * @param movie     {@link MovieEntity}
     * @param producers {@link List < ProducerEntity >}
     */
    public void saveProducers(MovieEntity movie, List<ProducerEntity> producers) {
        List<MovieProducerEntity> movieStudios = producers.stream()
                .map(p -> MovieProducerEntity.builder().movie(movie).producer(p).build())
                .collect(Collectors.toList());

        this.movieProducerRepository.saveAll(movieStudios);
    }


    /**
     * Retorna todos os produtores com filmes vencedores.
     *
     * @return filmes e produtores {@link List}
     */
    public List<MovieProducerEntity> findAllByMovieIsWinner() {
        return this.movieProducerRepository.findAllByMovieIsWinner();
    }

    /**
     * Busca por filme.
     *
     * @param movie {@link MovieEntity}
     * @return producers
     */
    public List<MovieProducerEntity> findByMovie(MovieEntity movie) {
        return this.movieProducerRepository.findByMovie(movie);
    }

    /**
     * Exclui todos do filme.
     *
     * @param movie {@link MovieEntity}
     */
    public void deleteByMovie(MovieEntity movie) {
        this.movieProducerRepository.deleteByMovie(movie);
    }
}
