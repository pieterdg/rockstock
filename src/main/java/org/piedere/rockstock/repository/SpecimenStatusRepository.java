package org.piedere.rockstock.repository;

import org.piedere.rockstock.domain.SpecimenStatus;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the SpecimenStatus entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SpecimenStatusRepository extends JpaRepository<SpecimenStatus, Long> {

}
