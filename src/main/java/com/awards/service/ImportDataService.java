package com.awards.service;

import com.awards.enums.YesOrNo;
import com.awards.model.entity.MovieEntity;
import com.awards.model.entity.ProducerEntity;
import com.awards.model.entity.StudioEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Serviço para análise e importação do arquivo de CSV para o H2.
 *
 * @author Osiel.
 */
@Slf4j
@Service
public class ImportDataService {

    @Value("${app.filename}")
    String fileName;

    private static final String SEMICOLON_DELIMITER = ";";
    private static final String COMMA_DELIMITER = ",";

    private static final Integer IDX_YEAR = 0;
    private static final Integer IDX_TITLE = 1;
    private static final Integer IDX_STUDIOS = 2;
    private static final Integer IDX_PRODUCERS = 3;
    private static final Integer IDX_WINNER = 4;

    private final ResourceLoader resourceLoader;
    private final MovieService movieService;
    private final ProducerService producerService;
    private final StudioService studioService;
    private final MovieProducerService movieProducerService;
    private final MovieStudioService movieStudioService;

    public ImportDataService(ResourceLoader resourceLoader, MovieService movieService, ProducerService producerService, StudioService studioService,
                             MovieProducerService movieProducerService, MovieStudioService movieStudioService) {
        this.resourceLoader = resourceLoader;
        this.movieService = movieService;
        this.producerService = producerService;
        this.studioService = studioService;
        this.movieProducerService = movieProducerService;
        this.movieStudioService = movieStudioService;
    }

    @PostConstruct
    private void importClassPathResource() {
        Resource resource = this.resourceLoader.getResource("classpath:/data/" + fileName);
        this.importResource(resource);
    }

    /**
     * Importa o resource inserindo os dados no banco.
     *
     * @param resource {@link Resource}
     */
    public void importResource(Resource resource) {
        if (resource.exists()) {
            try {
                log.info("Lendo arquivo de filmes");

                List<String> lines = Files.readAllLines(resource.getFile().toPath(), StandardCharsets.UTF_8).stream().toList();
                lines.forEach((line) -> {

                    log.info("Gravando a linha: {}", line);

                    String[] values = line.split(SEMICOLON_DELIMITER);

                    int year = Integer.parseInt(values[IDX_YEAR]);
                    String titleMovie = values[IDX_TITLE];
                    String studiosName = values[IDX_STUDIOS];
                    String producersName = values[IDX_PRODUCERS].replaceAll("\\s+and\\s+", ",");
                    boolean winner = false;

                    /*
                     * Neste caso tem a coluna 'winner'
                     */
                    if (values.length == 5) {
                        winner = YesOrNo.YES.getValue().equals(values[IDX_WINNER]);
                    }


                    List<ProducerEntity> producers = this.producerService.saveProducers(Arrays.stream(producersName.split(COMMA_DELIMITER)).collect(Collectors.toList()));
                    List<StudioEntity> studios = this.studioService.saveStudios(Arrays.stream(studiosName.split(COMMA_DELIMITER)).collect(Collectors.toList()));
                    MovieEntity movie = this.movieService.saveMovie(titleMovie, year, winner);

                    this.movieProducerService.saveProducers(movie, producers);
                    this.movieStudioService.saveStudios(movie, studios);

                });
                log.info("Importação de filmes finalizados. Total de filmes: {}", lines.size());
            } catch (IOException e) {
                log.error("Não foi possível ler o arquivo", e);
            }
        } else {
            log.error("Arquivo 'movie_list.csv' não encontrado.");
        }
    }

}
