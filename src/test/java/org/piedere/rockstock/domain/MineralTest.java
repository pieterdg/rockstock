package org.piedere.rockstock.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import org.piedere.rockstock.web.rest.TestUtil;

public class MineralTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Mineral.class);
        Mineral mineral1 = new Mineral();
        mineral1.setId(1L);
        Mineral mineral2 = new Mineral();
        mineral2.setId(mineral1.getId());
        assertThat(mineral1).isEqualTo(mineral2);
        mineral2.setId(2L);
        assertThat(mineral1).isNotEqualTo(mineral2);
        mineral1.setId(null);
        assertThat(mineral1).isNotEqualTo(mineral2);
    }
}
