package com.awards.api.base;

import com.awards.model.dto.base.AbstractDto;

import java.util.List;

public interface Api<DTO extends AbstractDto<ID>, ID> {

    DTO create(DTO dto);

    DTO update(ID id, DTO dto);

    DTO read(ID id);

    void delete(ID id);
}
