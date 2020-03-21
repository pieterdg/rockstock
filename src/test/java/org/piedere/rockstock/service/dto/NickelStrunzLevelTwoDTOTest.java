package org.piedere.rockstock.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import org.piedere.rockstock.web.rest.TestUtil;

public class NickelStrunzLevelTwoDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(NickelStrunzLevelTwoDTO.class);
        NickelStrunzLevelTwoDTO nickelStrunzLevelTwoDTO1 = new NickelStrunzLevelTwoDTO();
        nickelStrunzLevelTwoDTO1.setId(1L);
        NickelStrunzLevelTwoDTO nickelStrunzLevelTwoDTO2 = new NickelStrunzLevelTwoDTO();
        assertThat(nickelStrunzLevelTwoDTO1).isNotEqualTo(nickelStrunzLevelTwoDTO2);
        nickelStrunzLevelTwoDTO2.setId(nickelStrunzLevelTwoDTO1.getId());
        assertThat(nickelStrunzLevelTwoDTO1).isEqualTo(nickelStrunzLevelTwoDTO2);
        nickelStrunzLevelTwoDTO2.setId(2L);
        assertThat(nickelStrunzLevelTwoDTO1).isNotEqualTo(nickelStrunzLevelTwoDTO2);
        nickelStrunzLevelTwoDTO1.setId(null);
        assertThat(nickelStrunzLevelTwoDTO1).isNotEqualTo(nickelStrunzLevelTwoDTO2);
    }
}
