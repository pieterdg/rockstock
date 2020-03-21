package org.piedere.rockstock.repository;

import org.piedere.rockstock.domain.Mineral;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Mineral entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MineralRepository extends JpaRepository<Mineral, Long> {

}
