package org.piedere.rockstock.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class NickelStrunzLevelOneMapperTest {

    private NickelStrunzLevelOneMapper nickelStrunzLevelOneMapper;

    @BeforeEach
    public void setUp() {
        nickelStrunzLevelOneMapper = new NickelStrunzLevelOneMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(nickelStrunzLevelOneMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(nickelStrunzLevelOneMapper.fromId(null)).isNull();
    }
}
