package com.awards.controller;

import com.awards.api.GoldenAwardsApi;
import com.awards.model.dto.AwardDto;
import com.awards.service.AwardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class GoldenAwardsApiImpl implements GoldenAwardsApi {

    private final AwardService awardService;

    public GoldenAwardsApiImpl(AwardService awardService) {
        this.awardService = awardService;
    }

    @Override
    public AwardDto getAwards() {
        return this.awardService.getAwardBreak();
    }
}
