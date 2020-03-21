package org.piedere.rockstock.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import org.piedere.rockstock.web.rest.TestUtil;

public class SpecimenDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SpecimenDTO.class);
        SpecimenDTO specimenDTO1 = new SpecimenDTO();
        specimenDTO1.setId(1L);
        SpecimenDTO specimenDTO2 = new SpecimenDTO();
        assertThat(specimenDTO1).isNotEqualTo(specimenDTO2);
        specimenDTO2.setId(specimenDTO1.getId());
        assertThat(specimenDTO1).isEqualTo(specimenDTO2);
        specimenDTO2.setId(2L);
        assertThat(specimenDTO1).isNotEqualTo(specimenDTO2);
        specimenDTO1.setId(null);
        assertThat(specimenDTO1).isNotEqualTo(specimenDTO2);
    }
}
