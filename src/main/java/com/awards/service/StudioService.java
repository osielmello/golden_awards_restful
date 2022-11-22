package com.awards.service;

import com.awards.entity.StudioEntity;
import com.awards.repository.StudioRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Serviço que encapsula as regras de negócio e serve de camada para o repositório de studio.
 *
 * @author Osiel
 */
@Service
@Slf4j
public class StudioService {

    private final StudioRepository studioRepository;

    public StudioService(StudioRepository studioRepository) {
        this.studioRepository = studioRepository;
    }

    /**
     * Salva e retorna a lista de estúdios.
     *
     * @param studiosName {@link List<String>}
     * @return estúdios {@link List<Long>}
     */
    public List<StudioEntity> saveStudios(List<String> studiosName) {
        List<StudioEntity> studios = new ArrayList<>();

        for (String studio : studiosName) {
            studio = studio.trim();
            if (studio.length() > 0) {
                StudioEntity studioEntity = this.studioRepository.findByName(studio);
                if (studioEntity == null) {
                    studioEntity = StudioEntity.builder()
                            .name(studio)
                            .build();

                    studios.add(this.studioRepository.save(studioEntity));
                } else {
                    studios.add(studioEntity);
                }
            }
        }

        return studios;
    }

}
