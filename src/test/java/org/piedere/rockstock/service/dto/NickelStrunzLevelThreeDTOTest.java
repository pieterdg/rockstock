package org.piedere.rockstock.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import org.piedere.rockstock.web.rest.TestUtil;

public class NickelStrunzLevelThreeDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(NickelStrunzLevelThreeDTO.class);
        NickelStrunzLevelThreeDTO nickelStrunzLevelThreeDTO1 = new NickelStrunzLevelThreeDTO();
        nickelStrunzLevelThreeDTO1.setId(1L);
        NickelStrunzLevelThreeDTO nickelStrunzLevelThreeDTO2 = new NickelStrunzLevelThreeDTO();
        assertThat(nickelStrunzLevelThreeDTO1).isNotEqualTo(nickelStrunzLevelThreeDTO2);
        nickelStrunzLevelThreeDTO2.setId(nickelStrunzLevelThreeDTO1.getId());
        assertThat(nickelStrunzLevelThreeDTO1).isEqualTo(nickelStrunzLevelThreeDTO2);
        nickelStrunzLevelThreeDTO2.setId(2L);
        assertThat(nickelStrunzLevelThreeDTO1).isNotEqualTo(nickelStrunzLevelThreeDTO2);
        nickelStrunzLevelThreeDTO1.setId(null);
        assertThat(nickelStrunzLevelThreeDTO1).isNotEqualTo(nickelStrunzLevelThreeDTO2);
    }
}
