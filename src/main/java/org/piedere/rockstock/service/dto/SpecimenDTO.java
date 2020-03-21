package org.piedere.rockstock.service.dto;

import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the {@link org.piedere.rockstock.domain.Specimen} entity.
 */
public class SpecimenDTO implements Serializable {

    private Long id;

    @NotNull
    private String code;

    private LocalDate acquiredDate;

    private String acquiredAt;

    private Float acquiredPrice;

    private String acquiredBy;

    private String acquiredFrom;

    private String remark;

    private Boolean fluorescent;


    private Long statusId;

    private String statusName;

    private Long locationId;

    private String locationShortName;

    private Set<MineralDTO> minerals = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDate getAcquiredDate() {
        return acquiredDate;
    }

    public void setAcquiredDate(LocalDate acquiredDate) {
        this.acquiredDate = acquiredDate;
    }

    public String getAcquiredAt() {
        return acquiredAt;
    }

    public void setAcquiredAt(String acquiredAt) {
        this.acquiredAt = acquiredAt;
    }

    public Float getAcquiredPrice() {
        return acquiredPrice;
    }

    public void setAcquiredPrice(Float acquiredPrice) {
        this.acquiredPrice = acquiredPrice;
    }

    public String getAcquiredBy() {
        return acquiredBy;
    }

    public void setAcquiredBy(String acquiredBy) {
        this.acquiredBy = acquiredBy;
    }

    public String getAcquiredFrom() {
        return acquiredFrom;
    }

    public void setAcquiredFrom(String acquiredFrom) {
        this.acquiredFrom = acquiredFrom;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Boolean isFluorescent() {
        return fluorescent;
    }

    public void setFluorescent(Boolean fluorescent) {
        this.fluorescent = fluorescent;
    }

    public Long getStatusId() {
        return statusId;
    }

    public void setStatusId(Long specimenStatusId) {
        this.statusId = specimenStatusId;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String specimenStatusName) {
        this.statusName = specimenStatusName;
    }

    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    public String getLocationShortName() {
        return locationShortName;
    }

    public void setLocationShortName(String locationShortName) {
        this.locationShortName = locationShortName;
    }

    public Set<MineralDTO> getMinerals() {
        return minerals;
    }

    public void setMinerals(Set<MineralDTO> minerals) {
        this.minerals = minerals;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SpecimenDTO specimenDTO = (SpecimenDTO) o;
        if (specimenDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), specimenDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SpecimenDTO{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", acquiredDate='" + getAcquiredDate() + "'" +
            ", acquiredAt='" + getAcquiredAt() + "'" +
            ", acquiredPrice=" + getAcquiredPrice() +
            ", acquiredBy='" + getAcquiredBy() + "'" +
            ", acquiredFrom='" + getAcquiredFrom() + "'" +
            ", remark='" + getRemark() + "'" +
            ", fluorescent='" + isFluorescent() + "'" +
            ", statusId=" + getStatusId() +
            ", statusName='" + getStatusName() + "'" +
            ", locationId=" + getLocationId() +
            ", locationShortName='" + getLocationShortName() + "'" +
            "}";
    }
}
