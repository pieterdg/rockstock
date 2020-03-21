package org.piedere.rockstock.repository;

import org.piedere.rockstock.domain.NickelStrunzLevelTwo;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the NickelStrunzLevelTwo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface NickelStrunzLevelTwoRepository extends JpaRepository<NickelStrunzLevelTwo, Long> {
}
