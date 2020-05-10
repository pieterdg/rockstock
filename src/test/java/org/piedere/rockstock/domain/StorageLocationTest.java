package org.piedere.rockstock.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import org.piedere.rockstock.web.rest.TestUtil;

public class StorageLocationTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(StorageLocation.class);
        StorageLocation storageLocation1 = new StorageLocation();
        storageLocation1.setId(1L);
        StorageLocation storageLocation2 = new StorageLocation();
        storageLocation2.setId(storageLocation1.getId());
        assertThat(storageLocation1).isEqualTo(storageLocation2);
        storageLocation2.setId(2L);
        assertThat(storageLocation1).isNotEqualTo(storageLocation2);
        storageLocation1.setId(null);
        assertThat(storageLocation1).isNotEqualTo(storageLocation2);
    }
}
