package org.piedere.rockstock.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the {@link org.piedere.rockstock.domain.Picture} entity.
 */
public class PictureDTO implements Serializable {
    
    private Long id;

    
    @Lob
    private byte[] data;

    private String dataContentType;
    private String description;


    private Long specimenId;

    private String specimenCode;

    private Long locationId;

    private String locationShortName;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public String getDataContentType() {
        return dataContentType;
    }

    public void setDataContentType(String dataContentType) {
        this.dataContentType = dataContentType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getSpecimenId() {
        return specimenId;
    }

    public void setSpecimenId(Long specimenId) {
        this.specimenId = specimenId;
    }

    public String getSpecimenCode() {
        return specimenCode;
    }

    public void setSpecimenCode(String specimenCode) {
        this.specimenCode = specimenCode;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PictureDTO pictureDTO = (PictureDTO) o;
        if (pictureDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), pictureDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PictureDTO{" +
            "id=" + getId() +
            ", data='" + getData() + "'" +
            ", description='" + getDescription() + "'" +
            ", specimenId=" + getSpecimenId() +
            ", specimenCode='" + getSpecimenCode() + "'" +
            ", locationId=" + getLocationId() +
            ", locationShortName='" + getLocationShortName() + "'" +
            "}";
    }
}
