package org.piedere.rockstock.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import org.piedere.rockstock.web.rest.TestUtil;

public class SpecimenStatusTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SpecimenStatus.class);
        SpecimenStatus specimenStatus1 = new SpecimenStatus();
        specimenStatus1.setId(1L);
        SpecimenStatus specimenStatus2 = new SpecimenStatus();
        specimenStatus2.setId(specimenStatus1.getId());
        assertThat(specimenStatus1).isEqualTo(specimenStatus2);
        specimenStatus2.setId(2L);
        assertThat(specimenStatus1).isNotEqualTo(specimenStatus2);
        specimenStatus1.setId(null);
        assertThat(specimenStatus1).isNotEqualTo(specimenStatus2);
    }
}
