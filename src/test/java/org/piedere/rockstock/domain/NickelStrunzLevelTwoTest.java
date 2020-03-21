package org.piedere.rockstock.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import org.piedere.rockstock.web.rest.TestUtil;

public class NickelStrunzLevelTwoTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(NickelStrunzLevelTwo.class);
        NickelStrunzLevelTwo nickelStrunzLevelTwo1 = new NickelStrunzLevelTwo();
        nickelStrunzLevelTwo1.setId(1L);
        NickelStrunzLevelTwo nickelStrunzLevelTwo2 = new NickelStrunzLevelTwo();
        nickelStrunzLevelTwo2.setId(nickelStrunzLevelTwo1.getId());
        assertThat(nickelStrunzLevelTwo1).isEqualTo(nickelStrunzLevelTwo2);
        nickelStrunzLevelTwo2.setId(2L);
        assertThat(nickelStrunzLevelTwo1).isNotEqualTo(nickelStrunzLevelTwo2);
        nickelStrunzLevelTwo1.setId(null);
        assertThat(nickelStrunzLevelTwo1).isNotEqualTo(nickelStrunzLevelTwo2);
    }
}
