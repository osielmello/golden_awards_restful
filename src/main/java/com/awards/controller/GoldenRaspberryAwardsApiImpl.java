package com.awards.controller;

import com.awards.api.GoldenRaspberryAwardsApi;
import com.awards.model.dto.AwardDto;
import com.awards.service.AwardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class GoldenRaspberryAwardsApiImpl implements GoldenRaspberryAwardsApi {

    private final AwardService awardService;

    public GoldenRaspberryAwardsApiImpl(AwardService awardService) {
        this.awardService = awardService;
    }

    @Override
    public AwardDto getAwards() {
        return this.awardService.getAwardBreak();
    }
}
