package com.awards.model.dto;

import com.awards.model.dto.base.AbstractDto;
import com.awards.model.entity.base.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@AllArgsConstructor
@Builder
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class StudioDto extends AbstractDto<Long> {

    private Long id;

    private String name;
}
