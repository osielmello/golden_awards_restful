package com.awards.api;

import com.awards.api.base.Api;
import com.awards.model.dto.MovieDto;
import com.awards.model.dto.StudioDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


public interface StudioApi extends Api<StudioDto, Long> {

    @Override
    @PostMapping(value = "/api/v1/studios", produces = MediaType.APPLICATION_JSON_VALUE)
    StudioDto create(@RequestBody StudioDto dto);

    @Override
    @PutMapping(value = "/api/v1/studios/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    StudioDto update(@PathVariable Long id, @RequestBody StudioDto dto);

    @Override
    @GetMapping(value = "/api/v1/studios/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    StudioDto read(@PathVariable Long id);

    @Override
    @DeleteMapping("/api/v1/studios/{id}")
    void delete(@PathVariable Long id);
}
