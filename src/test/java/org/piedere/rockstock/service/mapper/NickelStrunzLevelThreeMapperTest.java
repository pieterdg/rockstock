package org.piedere.rockstock.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class NickelStrunzLevelThreeMapperTest {

    private NickelStrunzLevelThreeMapper nickelStrunzLevelThreeMapper;

    @BeforeEach
    public void setUp() {
        nickelStrunzLevelThreeMapper = new NickelStrunzLevelThreeMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(nickelStrunzLevelThreeMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(nickelStrunzLevelThreeMapper.fromId(null)).isNull();
    }
}
