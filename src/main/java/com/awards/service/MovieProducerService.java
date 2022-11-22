package com.awards.service;

import com.awards.entity.MovieEntity;
import com.awards.entity.MovieProducerEntity;
import com.awards.entity.ProducerEntity;
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

}
