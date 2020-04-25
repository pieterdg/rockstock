package org.piedere.rockstock.repository;

import java.util.List;
import java.util.Optional;

import org.piedere.rockstock.domain.Specimen;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data repository for the Specimen entity.
 */
@Repository
public interface SpecimenRepository extends JpaRepository<Specimen, Long> {

	@Query(value = "select distinct specimen from Specimen specimen left join fetch specimen.minerals", countQuery = "select count(distinct specimen) from Specimen specimen")
	Page<Specimen> findAllWithEagerRelationships(Pageable pageable);

	@Query("select distinct specimen from Specimen specimen left join fetch specimen.minerals")
	List<Specimen> findAllWithEagerRelationships();

	@Query("select specimen from Specimen specimen left join fetch specimen.minerals where specimen.id =:id")
	Optional<Specimen> findOneWithEagerRelationships(@Param("id") Long id);
}