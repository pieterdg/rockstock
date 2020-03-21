package org.piedere.rockstock.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class SeriesMapperTest {

    private SeriesMapper seriesMapper;

    @BeforeEach
    public void setUp() {
        seriesMapper = new SeriesMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(seriesMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(seriesMapper.fromId(null)).isNull();
    }
}
