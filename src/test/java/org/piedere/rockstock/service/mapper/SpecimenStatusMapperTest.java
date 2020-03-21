package org.piedere.rockstock.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class SpecimenStatusMapperTest {

    private SpecimenStatusMapper specimenStatusMapper;

    @BeforeEach
    public void setUp() {
        specimenStatusMapper = new SpecimenStatusMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(specimenStatusMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(specimenStatusMapper.fromId(null)).isNull();
    }
}
