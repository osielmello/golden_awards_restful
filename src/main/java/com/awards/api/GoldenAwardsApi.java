package com.awards.api;

import com.awards.model.dto.AwardDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;

public interface GoldenAwardsApi {

    @GetMapping(value = "/api/v1/golden-awards", produces = MediaType.APPLICATION_JSON_VALUE)
    AwardDto getAwards();
}
