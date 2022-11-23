package com.awards.model.dto;

import com.awards.model.dto.base.AbstractDto;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Builder
@Data
public class AwardDto {

    private List<AwardBreak> min;

    private List<AwardBreak> max;
}
