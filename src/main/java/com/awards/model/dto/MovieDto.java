package com.awards.model.dto;

import com.awards.model.dto.base.AbstractDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@Builder
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class MovieDto extends AbstractDto<Long> {

    private Long id;

    private String title;

    private Integer year;

    private Boolean winner;

    private List<String> producers;

    private List<String> studios;
}
