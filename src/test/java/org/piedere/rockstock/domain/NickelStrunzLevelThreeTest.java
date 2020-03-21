package org.piedere.rockstock.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import org.piedere.rockstock.web.rest.TestUtil;

public class NickelStrunzLevelThreeTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(NickelStrunzLevelThree.class);
        NickelStrunzLevelThree nickelStrunzLevelThree1 = new NickelStrunzLevelThree();
        nickelStrunzLevelThree1.setId(1L);
        NickelStrunzLevelThree nickelStrunzLevelThree2 = new NickelStrunzLevelThree();
        nickelStrunzLevelThree2.setId(nickelStrunzLevelThree1.getId());
        assertThat(nickelStrunzLevelThree1).isEqualTo(nickelStrunzLevelThree2);
        nickelStrunzLevelThree2.setId(2L);
        assertThat(nickelStrunzLevelThree1).isNotEqualTo(nickelStrunzLevelThree2);
        nickelStrunzLevelThree1.setId(null);
        assertThat(nickelStrunzLevelThree1).isNotEqualTo(nickelStrunzLevelThree2);
    }
}
