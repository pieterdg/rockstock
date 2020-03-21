package org.piedere.rockstock.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import org.piedere.rockstock.web.rest.TestUtil;

public class SeriesDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SeriesDTO.class);
        SeriesDTO seriesDTO1 = new SeriesDTO();
        seriesDTO1.setId(1L);
        SeriesDTO seriesDTO2 = new SeriesDTO();
        assertThat(seriesDTO1).isNotEqualTo(seriesDTO2);
        seriesDTO2.setId(seriesDTO1.getId());
        assertThat(seriesDTO1).isEqualTo(seriesDTO2);
        seriesDTO2.setId(2L);
        assertThat(seriesDTO1).isNotEqualTo(seriesDTO2);
        seriesDTO1.setId(null);
        assertThat(seriesDTO1).isNotEqualTo(seriesDTO2);
    }
}
