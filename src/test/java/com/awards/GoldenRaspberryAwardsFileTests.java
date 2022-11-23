package com.awards;

import com.awards.base.AbstractTest;
import com.awards.model.dto.AwardDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

/**
 * Teste simples de validação dos resultados obtidos através da importação do arquivo 'movie_list.csv'.
 * <p>
 * Um segundo teste foi realizado persistindo os dados via API.
 *
 * @author Osiel
 */
class GoldenRaspberryAwardsFileTests extends AbstractTest {

    @BeforeEach
    public void init() {
        this.setup();
    }

    /**
     * Teste simples para o arquivo importado da classpath 'movie_list.csv'
     *
     * @throws Exception ex
     */
    @Test
    public void awardFileTest() throws Exception {
        this.get("/api/v1/golden-awards");
        AwardDto awardDto = this.getResponseAs(AwardDto.class);
        Assert.notNull(awardDto, "Premiação não encontrada.");
    }

}
