package org.piedere.rockstock.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the {@link org.piedere.rockstock.domain.Series} entity.
 */
public class SeriesDTO implements Serializable {
    
    private Long id;

    @NotNull
    private String name;

    private String description;

    private Set<SpecimenDTO> specimen = new HashSet<>();
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<SpecimenDTO> getSpecimen() {
        return specimen;
    }

    public void setSpecimen(Set<SpecimenDTO> specimen) {
        this.specimen = specimen;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SeriesDTO seriesDTO = (SeriesDTO) o;
        if (seriesDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), seriesDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SeriesDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", specimen='" + getSpecimen() + "'" +
            "}";
    }
}
