package org.piedere.rockstock.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class StorageLocationMapperTest {

    private StorageLocationMapper storageLocationMapper;

    @BeforeEach
    public void setUp() {
        storageLocationMapper = new StorageLocationMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(storageLocationMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(storageLocationMapper.fromId(null)).isNull();
    }
}
