package org.piedere.rockstock.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import org.piedere.rockstock.web.rest.TestUtil;

public class SpecimenTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Specimen.class);
        Specimen specimen1 = new Specimen();
        specimen1.setId(1L);
        Specimen specimen2 = new Specimen();
        specimen2.setId(specimen1.getId());
        assertThat(specimen1).isEqualTo(specimen2);
        specimen2.setId(2L);
        assertThat(specimen1).isNotEqualTo(specimen2);
        specimen1.setId(null);
        assertThat(specimen1).isNotEqualTo(specimen2);
    }
}
