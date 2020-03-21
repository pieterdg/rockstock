package org.piedere.rockstock.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import org.piedere.rockstock.web.rest.TestUtil;

public class NickelStrunzLevelOneTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(NickelStrunzLevelOne.class);
        NickelStrunzLevelOne nickelStrunzLevelOne1 = new NickelStrunzLevelOne();
        nickelStrunzLevelOne1.setId(1L);
        NickelStrunzLevelOne nickelStrunzLevelOne2 = new NickelStrunzLevelOne();
        nickelStrunzLevelOne2.setId(nickelStrunzLevelOne1.getId());
        assertThat(nickelStrunzLevelOne1).isEqualTo(nickelStrunzLevelOne2);
        nickelStrunzLevelOne2.setId(2L);
        assertThat(nickelStrunzLevelOne1).isNotEqualTo(nickelStrunzLevelOne2);
        nickelStrunzLevelOne1.setId(null);
        assertThat(nickelStrunzLevelOne1).isNotEqualTo(nickelStrunzLevelOne2);
    }
}
