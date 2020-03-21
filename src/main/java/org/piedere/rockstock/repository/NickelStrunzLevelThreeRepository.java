package org.piedere.rockstock.repository;

import org.piedere.rockstock.domain.NickelStrunzLevelThree;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the NickelStrunzLevelThree entity.
 */
@SuppressWarnings("unused")
@Repository
public interface NickelStrunzLevelThreeRepository extends JpaRepository<NickelStrunzLevelThree, Long> {

}
