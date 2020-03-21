package org.piedere.rockstock.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class NickelStrunzLevelTwoMapperTest {

    private NickelStrunzLevelTwoMapper nickelStrunzLevelTwoMapper;

    @BeforeEach
    public void setUp() {
        nickelStrunzLevelTwoMapper = new NickelStrunzLevelTwoMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(nickelStrunzLevelTwoMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(nickelStrunzLevelTwoMapper.fromId(null)).isNull();
    }
}
