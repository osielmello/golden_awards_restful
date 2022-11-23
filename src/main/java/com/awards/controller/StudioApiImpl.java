package com.awards.controller;

import com.awards.api.MovieApi;
import com.awards.api.StudioApi;
import com.awards.controller.base.AbstractCrud;
import com.awards.model.dto.MovieDto;
import com.awards.model.dto.ProducerDto;
import com.awards.model.dto.StudioDto;
import com.awards.model.entity.MovieEntity;
import com.awards.model.entity.ProducerEntity;
import com.awards.model.entity.StudioEntity;
import com.awards.repository.MovieRepository;
import com.awards.repository.StudioRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

/**
 * Crud de estúdios.
 *
 * @author Osiel
 */
@Slf4j
@RestController
public class StudioApiImpl extends AbstractCrud<StudioEntity, StudioDto, Long> implements StudioApi {

    public StudioApiImpl(StudioRepository studioRepository) {
        super(studioRepository);
    }

    @Override
    public StudioDto create(StudioDto dto) {
        return super.create(dto);
    }

    @Override
    public StudioDto update(Long id, StudioDto dto) {
        return super.update(id, dto);
    }

    @Override
    public StudioDto read(Long id) {
        return super.read(id);
    }

    @Override
    public void delete(Long id) {
        super.delete(id);
    }

    @Override
    protected StudioEntity toEntity(StudioDto studioDto) {
        return StudioEntity.builder()
                .id(studioDto.getId())
                .name(studioDto.getName())
                .build();
    }

    @Override
    protected StudioDto toDto(StudioEntity studioEntity) {
        return StudioDto.builder()
                .id(studioEntity.getId())
                .name(studioEntity.getName())
                .build();
    }
}
