package org.piedere.rockstock.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import org.piedere.rockstock.web.rest.TestUtil;

public class MineralDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MineralDTO.class);
        MineralDTO mineralDTO1 = new MineralDTO();
        mineralDTO1.setId(1L);
        MineralDTO mineralDTO2 = new MineralDTO();
        assertThat(mineralDTO1).isNotEqualTo(mineralDTO2);
        mineralDTO2.setId(mineralDTO1.getId());
        assertThat(mineralDTO1).isEqualTo(mineralDTO2);
        mineralDTO2.setId(2L);
        assertThat(mineralDTO1).isNotEqualTo(mineralDTO2);
        mineralDTO1.setId(null);
        assertThat(mineralDTO1).isNotEqualTo(mineralDTO2);
    }
}
