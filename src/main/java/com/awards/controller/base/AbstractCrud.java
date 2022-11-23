package com.awards.controller.base;

import com.awards.model.dto.base.AbstractDto;
import com.awards.model.entity.base.AbstractEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Crud
 *
 * @param <ENTITY>
 * @param <DTO>
 * @param <ID>
 * @author Osiel
 */
public abstract class AbstractCrud<ENTITY extends AbstractEntity<ID>, DTO extends AbstractDto<ID>, ID> {

    protected final JpaRepository<ENTITY, ID> repository;

    public AbstractCrud(JpaRepository<ENTITY, ID> repository) {
        this.repository = repository;
    }

    public DTO create(DTO dto) {
        return this.toDto(this.repository.save(this.toEntity(dto)));
    }

    public DTO update(ID id, DTO dto) {
        if (!this.repository.existsById(id)) {
            throw new RuntimeException("Not found [" + id + "]");
        }
        return this.toDto(this.repository.save(this.toEntity(dto)));
    }

    public DTO read(ID id) {
        ENTITY entity = this.repository.findById(id).orElse(null);
        return entity != null ? this.toDto(entity) : null;
    }

    public void delete(ID id) {
        this.repository.deleteById(id);
    }

    protected abstract ENTITY toEntity(DTO dto);

    protected abstract DTO toDto(ENTITY entity);
}
