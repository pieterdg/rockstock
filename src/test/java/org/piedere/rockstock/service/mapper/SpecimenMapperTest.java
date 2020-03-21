package org.piedere.rockstock.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class SpecimenMapperTest {

    private SpecimenMapper specimenMapper;

    @BeforeEach
    public void setUp() {
        specimenMapper = new SpecimenMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(specimenMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(specimenMapper.fromId(null)).isNull();
    }
}
