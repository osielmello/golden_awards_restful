package com.awards.model.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AwardBreak {

    private String producer;

    private int interval;

    private int previousWin;

    private int followingWin;
}
