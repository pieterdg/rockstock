package org.piedere.rockstock.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import org.piedere.rockstock.web.rest.TestUtil;

public class NickelStrunzLevelOneDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(NickelStrunzLevelOneDTO.class);
        NickelStrunzLevelOneDTO nickelStrunzLevelOneDTO1 = new NickelStrunzLevelOneDTO();
        nickelStrunzLevelOneDTO1.setId(1L);
        NickelStrunzLevelOneDTO nickelStrunzLevelOneDTO2 = new NickelStrunzLevelOneDTO();
        assertThat(nickelStrunzLevelOneDTO1).isNotEqualTo(nickelStrunzLevelOneDTO2);
        nickelStrunzLevelOneDTO2.setId(nickelStrunzLevelOneDTO1.getId());
        assertThat(nickelStrunzLevelOneDTO1).isEqualTo(nickelStrunzLevelOneDTO2);
        nickelStrunzLevelOneDTO2.setId(2L);
        assertThat(nickelStrunzLevelOneDTO1).isNotEqualTo(nickelStrunzLevelOneDTO2);
        nickelStrunzLevelOneDTO1.setId(null);
        assertThat(nickelStrunzLevelOneDTO1).isNotEqualTo(nickelStrunzLevelOneDTO2);
    }
}
