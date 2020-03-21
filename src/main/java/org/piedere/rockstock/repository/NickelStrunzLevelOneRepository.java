package org.piedere.rockstock.repository;

import org.piedere.rockstock.domain.NickelStrunzLevelOne;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the NickelStrunzLevelOne entity.
 */
@SuppressWarnings("unused")
@Repository
public interface NickelStrunzLevelOneRepository extends JpaRepository<NickelStrunzLevelOne, Long> {

}
