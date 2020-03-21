package org.piedere.rockstock.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import org.piedere.rockstock.web.rest.TestUtil;

public class SpecimenStatusDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SpecimenStatusDTO.class);
        SpecimenStatusDTO specimenStatusDTO1 = new SpecimenStatusDTO();
        specimenStatusDTO1.setId(1L);
        SpecimenStatusDTO specimenStatusDTO2 = new SpecimenStatusDTO();
        assertThat(specimenStatusDTO1).isNotEqualTo(specimenStatusDTO2);
        specimenStatusDTO2.setId(specimenStatusDTO1.getId());
        assertThat(specimenStatusDTO1).isEqualTo(specimenStatusDTO2);
        specimenStatusDTO2.setId(2L);
        assertThat(specimenStatusDTO1).isNotEqualTo(specimenStatusDTO2);
        specimenStatusDTO1.setId(null);
        assertThat(specimenStatusDTO1).isNotEqualTo(specimenStatusDTO2);
    }
}
