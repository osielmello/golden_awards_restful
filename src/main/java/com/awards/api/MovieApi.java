package com.awards.api;

import com.awards.api.base.Api;
import com.awards.model.dto.MovieDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface MovieApi extends Api<MovieDto, Long> {

    @Override
    @PostMapping(value = "/api/v1/movies", produces = MediaType.APPLICATION_JSON_VALUE)
    MovieDto create(@RequestBody MovieDto dto);

    @Override
    @PutMapping(value = "/api/v1/movies/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    MovieDto update(@PathVariable Long id, @RequestBody MovieDto dto);

    @Override
    @GetMapping(value = "/api/v1/movies/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    MovieDto read(@PathVariable Long id);

    @Override
    @DeleteMapping("/api/v1/movies/{id}")
    void delete(@PathVariable Long id);
}
