package com.awards.service;

import com.awards.model.dto.AwardBreak;
import com.awards.model.dto.AwardDto;
import com.awards.model.entity.MovieProducerEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Encapsula a regra de premiações.
 *
 * @author Osiel.
 */
@Service
public class AwardService {

    private final MovieProducerService movieProducerService;

    public AwardService(MovieProducerService movieProducerService) {
        this.movieProducerService = movieProducerService;
    }

    /**
     * Busca os produtores que tiveram prêmios e seus respectivos filmes, agrupa por produtores com mais de 1 prêmio, na sequência,
     * calcula os intervalos e devolve um {@link AwardDto}
     *
     * @return dto contendo as informações {@link AwardDto}
     */
    public AwardDto getAwardBreak() {
        Map<Long, List<MovieProducerEntity>> producersMap = new HashMap<>();
        List<MovieProducerEntity> winningMoviesProducer = this.movieProducerService.findAllByMovieIsWinner();

        winningMoviesProducer.forEach(movieProducer -> producersMap.computeIfAbsent(movieProducer.getProducer().getId(), k -> new ArrayList<>()).add(movieProducer));

        /*
         * Produtores com mais de um filme.
         */
        Map<Long, List<MovieProducerEntity>> producersWithMoreThanOneFilm = producersMap.entrySet().stream()
                .filter(e -> e.getValue().size() > 1)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));


        List<AwardBreak> awardBreaks = new ArrayList<>();

        producersWithMoreThanOneFilm.forEach((key, movies) -> {

            /*
             * Calcula os intervalos e guarda em uma lista de premiações.
             */
            for (int idx = 1; idx < movies.size(); idx++) {
                var previousMovieProducer = movies.get(idx - 1);
                var movieProducer = movies.get(idx);
                int previousWin = previousMovieProducer.getMovie().getYear();
                int followingWin = movieProducer.getMovie().getYear();

                var awardBreak = AwardBreak.builder()
                        .followingWin(previousWin)
                        .previousWin(followingWin)
                        .interval(followingWin - previousWin)
                        .producer(movieProducer.getProducer().getName())
                        .build();
                awardBreaks.add(awardBreak);
            }
        });

        awardBreaks.sort(Comparator.comparing(AwardBreak::getInterval));

        int maxInterval = awardBreaks.stream().map(AwardBreak::getInterval).max(Integer::compare).orElse(0);

        return AwardDto.builder()
                .min(awardBreaks.stream().filter(a -> a.getInterval() == 1).collect(Collectors.toList()))
                .max(maxInterval == 0 ? Collections.emptyList() : awardBreaks.stream().filter(a -> a.getInterval() == maxInterval).collect(Collectors.toList()))
                .build();
    }
}
