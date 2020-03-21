package org.piedere.rockstock.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class MineralMapperTest {

    private MineralMapper mineralMapper;

    @BeforeEach
    public void setUp() {
        mineralMapper = new MineralMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(mineralMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(mineralMapper.fromId(null)).isNull();
    }
}
