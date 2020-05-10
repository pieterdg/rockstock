package org.piedere.rockstock.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * A Specimen.
 */
@Entity
@Table(name = "specimen")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Specimen implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "code", nullable = false)
    private String code;

    @Column(name = "acquired_date")
    private LocalDate acquiredDate;

    @Column(name = "acquired_at")
    private String acquiredAt;

    @Column(name = "acquired_price")
    private Float acquiredPrice;

    @Column(name = "acquired_by")
    private String acquiredBy;

    @Column(name = "acquired_from")
    private String acquiredFrom;

    @Column(name = "remark")
    private String remark;

    @Column(name = "fluorescent")
    private Boolean fluorescent;

    @ManyToOne
    @JsonIgnoreProperties("specimen")
    private SpecimenStatus status;

    @ManyToOne
    @JsonIgnoreProperties("specimen")
    private StorageLocation storageLocation;

    @ManyToOne
    @JsonIgnoreProperties("specimen")
    private Location location;

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "specimen_minerals",
               joinColumns = @JoinColumn(name = "specimen_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "minerals_id", referencedColumnName = "id"))
    private Set<Mineral> minerals = new HashSet<>();

    @ManyToMany(mappedBy = "specimen")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JsonIgnore
    private Set<Series> series = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public Specimen code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDate getAcquiredDate() {
        return acquiredDate;
    }

    public Specimen acquiredDate(LocalDate acquiredDate) {
        this.acquiredDate = acquiredDate;
        return this;
    }

    public void setAcquiredDate(LocalDate acquiredDate) {
        this.acquiredDate = acquiredDate;
    }

    public String getAcquiredAt() {
        return acquiredAt;
    }

    public Specimen acquiredAt(String acquiredAt) {
        this.acquiredAt = acquiredAt;
        return this;
    }

    public void setAcquiredAt(String acquiredAt) {
        this.acquiredAt = acquiredAt;
    }

    public Float getAcquiredPrice() {
        return acquiredPrice;
    }

    public Specimen acquiredPrice(Float acquiredPrice) {
        this.acquiredPrice = acquiredPrice;
        return this;
    }

    public void setAcquiredPrice(Float acquiredPrice) {
        this.acquiredPrice = acquiredPrice;
    }

    public String getAcquiredBy() {
        return acquiredBy;
    }

    public Specimen acquiredBy(String acquiredBy) {
        this.acquiredBy = acquiredBy;
        return this;
    }

    public void setAcquiredBy(String acquiredBy) {
        this.acquiredBy = acquiredBy;
    }

    public String getAcquiredFrom() {
        return acquiredFrom;
    }

    public Specimen acquiredFrom(String acquiredFrom) {
        this.acquiredFrom = acquiredFrom;
        return this;
    }

    public void setAcquiredFrom(String acquiredFrom) {
        this.acquiredFrom = acquiredFrom;
    }

    public String getRemark() {
        return remark;
    }

    public Specimen remark(String remark) {
        this.remark = remark;
        return this;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Boolean isFluorescent() {
        return fluorescent;
    }

    public Specimen fluorescent(Boolean fluorescent) {
        this.fluorescent = fluorescent;
        return this;
    }

    public void setFluorescent(Boolean fluorescent) {
        this.fluorescent = fluorescent;
    }

    public SpecimenStatus getStatus() {
        return status;
    }

    public Specimen status(SpecimenStatus specimenStatus) {
        this.status = specimenStatus;
        return this;
    }

    public void setStatus(SpecimenStatus specimenStatus) {
        this.status = specimenStatus;
    }

    public StorageLocation getStorageLocation() {
        return storageLocation;
    }

    public Specimen storageLocation(StorageLocation storageLocation) {
        this.storageLocation = storageLocation;
        return this;
    }

    public void setStorageLocation(StorageLocation storageLocation) {
        this.storageLocation = storageLocation;
    }

    public Location getLocation() {
        return location;
    }

    public Specimen location(Location location) {
        this.location = location;
        return this;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Set<Mineral> getMinerals() {
        return minerals;
    }

    public Specimen minerals(Set<Mineral> minerals) {
        this.minerals = minerals;
        return this;
    }

    public Specimen addMinerals(Mineral mineral) {
        this.minerals.add(mineral);
        mineral.getSpecimens().add(this);
        return this;
    }

    public Specimen removeMinerals(Mineral mineral) {
        this.minerals.remove(mineral);
        mineral.getSpecimens().remove(this);
        return this;
    }

    public void setMinerals(Set<Mineral> minerals) {
        this.minerals = minerals;
    }

    public Set<Series> getSeries() {
        return series;
    }

    public Specimen series(Set<Series> series) {
        this.series = series;
        return this;
    }

    public Specimen addSeries(Series series) {
        this.series.add(series);
        series.getSpecimen().add(this);
        return this;
    }

    public Specimen removeSeries(Series series) {
        this.series.remove(series);
        series.getSpecimen().remove(this);
        return this;
    }

    public void setSeries(Set<Series> series) {
        this.series = series;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Specimen)) {
            return false;
        }
        return id != null && id.equals(((Specimen) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Specimen{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", acquiredDate='" + getAcquiredDate() + "'" +
            ", acquiredAt='" + getAcquiredAt() + "'" +
            ", acquiredPrice=" + getAcquiredPrice() +
            ", acquiredBy='" + getAcquiredBy() + "'" +
            ", acquiredFrom='" + getAcquiredFrom() + "'" +
            ", remark='" + getRemark() + "'" +
            ", fluorescent='" + isFluorescent() + "'" +
            "}";
    }
}
