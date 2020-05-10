package org.piedere.rockstock.repository;

import org.piedere.rockstock.domain.StorageLocation;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the StorageLocation entity.
 */
@SuppressWarnings("unused")
@Repository
public interface StorageLocationRepository extends JpaRepository<StorageLocation, Long> {
}
