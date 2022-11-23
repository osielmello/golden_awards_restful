package com.awards.model.dto;

import com.awards.model.dto.base.AbstractDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class MovieProducerDto extends AbstractDto<Long> {

    private Long id;

    private MovieDto movie;

    private ProducerDto producer;
}
