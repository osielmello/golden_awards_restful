package com.awards;

import com.awards.base.AbstractTest;
import com.awards.model.dto.AwardBreak;
import com.awards.model.dto.AwardDto;
import com.awards.model.dto.MovieDto;
import com.awards.model.dto.ProducerDto;
import com.awards.model.dto.StudioDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.util.Assert;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Teste completo de toda a API.
 *
 * @author Osiel.
 */
public class RestFulApiTests extends AbstractTest {

    @BeforeEach
    public void init() throws Exception {
        this.setup();
        this.testProducers();
    }

    @Test
    public void testProducers() throws Exception {
        ProducerDto producerDto = ProducerDto.builder().name("Lemuel Forest").build();
        this.post("/api/v1/producers", this.toJson(producerDto));
        this.checkResult(HttpStatus.OK.value());

        Long id = this.getResponseAs(ProducerDto.class).getId();
        this.get("/api/v1/producers/{id}", id);
        this.checkResult(HttpStatus.OK.value());

        producerDto = this.getResponseAs(ProducerDto.class);
        producerDto.setName("Paulo Henrique");

        this.put("/api/v1/producers/{id}", this.toJson(producerDto), id);
        this.checkResult(HttpStatus.OK.value());

        ProducerDto saved = this.getResponseAs(ProducerDto.class);
        Assert.isTrue(producerDto.getName().equals(saved.getName()), "Nome diferente do esperado.");

        this.delete("/api/v1/producers/{id}", id);
        this.checkResult(HttpStatus.OK.value());

        this.get("/api/v1/producers/{id}", id);
        this.checkResult(HttpStatus.OK.value());

        Assert.isNull(this.getResponseAs(ProducerDto.class), "Producer não foi excluído.");
    }

    @Test
    public void testStudios() throws Exception {
        StudioDto studioDto = StudioDto.builder().name("AWR Lmtda").build();
        this.post("/api/v1/studios", this.toJson(studioDto));
        this.checkResult(HttpStatus.OK.value());

        Long id = this.getResponseAs(StudioDto.class).getId();
        this.get("/api/v1/studios/{id}", id);
        this.checkResult(HttpStatus.OK.value());

        studioDto = this.getResponseAs(StudioDto.class);
        studioDto.setName("AMG PRODUCERS");

        this.put("/api/v1/studios/{id}", this.toJson(studioDto), id);
        this.checkResult(HttpStatus.OK.value());

        StudioDto saved = this.getResponseAs(StudioDto.class);
        Assert.isTrue(studioDto.getName().equals(saved.getName()), "Nome diferente do esperado.");

        this.delete("/api/v1/studios/{id}", id);
        this.checkResult(HttpStatus.OK.value());

        this.get("/api/v1/studios/{id}", id);
        this.checkResult(HttpStatus.OK.value());

        Assert.isNull(this.getResponseAs(StudioDto.class), "Estúdio não foi excluído.");
    }

    @Test
    public void moviesTest() throws Exception {
        MovieDto movieDto = MovieDto.builder()
                .title("The Bird")
                .year(2022)
                .winner(true)
                .producers(Arrays.asList("Evandro Simpson", "Matheus Hernandes"))
                .studios(Arrays.asList("MGM", "Warner", "Marvel"))
                .build();

        this.post("/api/v1/movies", this.toJson(movieDto));
        this.checkResult(HttpStatus.OK.value());

        Long id = this.getResponseAs(MovieDto.class).getId();
        this.get("/api/v1/movies/{id}", id);
        this.checkResult(HttpStatus.OK.value());

        movieDto = this.getResponseAs(MovieDto.class);
        movieDto.setYear(1995);
        movieDto.setProducers(Collections.singletonList("Henrique Jorge"));

        this.put("/api/v1/movies/{id}", this.toJson(movieDto), id);
        this.checkResult(HttpStatus.OK.value());

        MovieDto saved = this.getResponseAs(MovieDto.class);
        Assert.isTrue(movieDto.getYear().equals(saved.getYear()), "Ano diferente do esperado.");
        Assert.isTrue(movieDto.getProducers().size() == saved.getProducers().size(), "Qtd produtores diferente do esperado.");
        Assert.isTrue(movieDto.getProducers().get(0).equals(saved.getProducers().get(0)), "Produtores diferente do esperado.");

        this.delete("/api/v1/movies/{id}", id);
        this.checkResult(HttpStatus.OK.value());

        this.get("/api/v1/movies/{id}", id);
        this.checkResult(HttpStatus.OK.value());
        Assert.isNull(this.getResponseAs(MovieDto.class), "Filme não foi excluído.");
    }

    @Test
    public void goldenAwardApiTest() throws Exception {
        MovieDto movieDto = MovieDto.builder()
                .title("The Bird One")
                .year(2017)
                .winner(true)
                .producers(Arrays.asList("Ricardo Martins", "Anderson Freire"))
                .studios(Arrays.asList("MGM", "Warner", "Marvel"))
                .build();

        this.post("/api/v1/movies", this.toJson(movieDto));
        this.checkResult(HttpStatus.OK.value());

        movieDto = MovieDto.builder()
                .title("The Bird One 2")
                .year(2018)
                .winner(true)
                .producers(Arrays.asList("Ricardo Martins", "Cristiano Freire"))
                .studios(Arrays.asList("MGM 2", "Warner 3", "Marvel"))
                .build();

        this.post("/api/v1/movies", this.toJson(movieDto));
        this.checkResult(HttpStatus.OK.value());

        movieDto = MovieDto.builder()
                .title("The Bird One 3")
                .year(2019)
                .winner(true)
                .producers(Arrays.asList("Ricardo Martins", "Junior Melo"))
                .studios(List.of("Marvel"))
                .build();

        this.post("/api/v1/movies", this.toJson(movieDto));
        this.checkResult(HttpStatus.OK.value());

        movieDto = MovieDto.builder()
                .title("The lost")
                .year(1900)
                .winner(true)
                .producers(Arrays.asList("Samuel Marcel", "Mariano Cib"))
                .studios(List.of("Marvel"))
                .build();

        this.post("/api/v1/movies", this.toJson(movieDto));
        this.checkResult(HttpStatus.OK.value());

        movieDto = MovieDto.builder()
                .title("The forest")
                .year(2022)
                .winner(true)
                .producers(Arrays.asList("Samuel Marcel", "Amorin"))
                .studios(List.of("Marvel"))
                .build();

        this.post("/api/v1/movies", this.toJson(movieDto));
        this.checkResult(HttpStatus.OK.value());


        this.get("/api/v1/golden-awards");
        AwardDto awardDto = this.getResponseAs(AwardDto.class);

        Assert.isTrue(awardDto.getMin().size() > 0, "O menor intervalo deve ter valor.");
        AwardBreak min = awardDto.getMin().get(0);

        Assert.isTrue(min.getProducer().equals("Joel Silver"), "Produtor diferente do esperado.");
        Assert.isTrue(min.getInterval() == 1, "Intervalo diferente do esperado.");
        Assert.isTrue(min.getPreviousWin() == 1990, "Menor ano diferente do esperado.");
        Assert.isTrue(min.getFollowingWin() == 1991, "Maior ano diferente do esperado.");

        Assert.isTrue(awardDto.getMax().size() > 0, "O menor intervalo deve ter valor.");
    }
}
