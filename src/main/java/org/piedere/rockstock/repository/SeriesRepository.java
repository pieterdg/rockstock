package org.piedere.rockstock.repository;

import org.piedere.rockstock.domain.Series;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the Series entity.
 */
@Repository
public interface SeriesRepository extends JpaRepository<Series, Long> {

    @Query(value = "select distinct series from Series series left join fetch series.specimen",
        countQuery = "select count(distinct series) from Series series")
    Page<Series> findAllWithEagerRelationships(Pageable pageable);

    @Query("select distinct series from Series series left join fetch series.specimen")
    List<Series> findAllWithEagerRelationships();

    @Query("select series from Series series left join fetch series.specimen where series.id =:id")
    Optional<Series> findOneWithEagerRelationships(@Param("id") Long id);

}
