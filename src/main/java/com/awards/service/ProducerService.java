package com.awards.service;

import com.awards.entity.ProducerEntity;
import com.awards.repository.ProducerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Serviço que encapsula as regras de negócio e serve de camada para o repositório de producer.
 *
 * @author Osiel
 */
@Service
@Slf4j
public class ProducerService {

    private final ProducerRepository producerRepository;

    public ProducerService(ProducerRepository producerRepository) {
        this.producerRepository = producerRepository;
    }

    /**
     * Salva e retorna a lista de produtores.
     *
     * @param producersName {@link List<String>}
     * @return produtores {@link List<ProducerEntity>}
     */
    public List<ProducerEntity> saveProducers(List<String> producersName) {
        List<ProducerEntity> producers = new ArrayList<>();

        for (String producerName : producersName) {
            producerName = producerName.trim();
            if (producerName.length() > 0) {
                ProducerEntity producerEntity = this.producerRepository.findByName(producerName);
                if (producerEntity == null) {
                    producerEntity = ProducerEntity.builder()
                            .name(producerName)
                            .build();

                    producers.add(this.producerRepository.save(producerEntity));
                } else {
                    producers.add(producerEntity);
                }
            }
        }

        return producers;
    }

}
