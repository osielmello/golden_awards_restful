package com.awards.api;

import com.awards.api.base.Api;
import com.awards.model.dto.MovieDto;
import com.awards.model.dto.ProducerDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface ProducerApi extends Api<ProducerDto, Long> {

    @Override
    @PostMapping(value = "/api/v1/producers", produces = MediaType.APPLICATION_JSON_VALUE)
    ProducerDto create(@RequestBody ProducerDto dto);

    @Override
    @PutMapping(value = "/api/v1/producers/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ProducerDto update(@PathVariable Long id, @RequestBody ProducerDto dto);

    @Override
    @GetMapping(value = "/api/v1/producers/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ProducerDto read(@PathVariable Long id);

    @Override
    @DeleteMapping("/api/v1/producers/{id}")
    void delete(@PathVariable Long id);
}
