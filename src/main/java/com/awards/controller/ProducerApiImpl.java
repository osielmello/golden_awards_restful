package com.awards.controller;

import com.awards.api.ProducerApi;
import com.awards.controller.base.AbstractCrud;
import com.awards.model.dto.ProducerDto;
import com.awards.model.entity.ProducerEntity;
import com.awards.repository.ProducerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

/**
 * Crud de produtores.
 *
 * @author Osiel
 */
@Slf4j
@RestController
public class ProducerApiImpl extends AbstractCrud<ProducerEntity, ProducerDto, Long> implements ProducerApi {

    public ProducerApiImpl(ProducerRepository producerRepository) {
        super(producerRepository);
    }

    @Override
    public ProducerDto create(ProducerDto dto) {
        return super.create(dto);
    }

    @Override
    public ProducerDto update(Long id, ProducerDto dto) {
        return super.update(id, dto);
    }

    @Override
    public ProducerDto read(Long id) {
        return super.read(id);
    }

    @Override
    public void delete(Long id) {
        super.delete(id);
    }

    @Override
    protected ProducerEntity toEntity(ProducerDto producerDto) {
        return ProducerEntity.builder()
                .id(producerDto.getId())
                .name(producerDto.getName())
                .build();
    }

    @Override
    protected ProducerDto toDto(ProducerEntity producerEntity) {
        return ProducerDto.builder()
                .id(producerEntity.getId())
                .name(producerEntity.getName())
                .build();
    }
}
