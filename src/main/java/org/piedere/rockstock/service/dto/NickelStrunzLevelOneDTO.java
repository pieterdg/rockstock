package org.piedere.rockstock.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link org.piedere.rockstock.domain.NickelStrunzLevelOne} entity.
 */
public class NickelStrunzLevelOneDTO implements Serializable {

    private Long id;

    @NotNull
    private String code;

    @NotNull
    private String name;


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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        NickelStrunzLevelOneDTO nickelStrunzLevelOneDTO = (NickelStrunzLevelOneDTO) o;
        if (nickelStrunzLevelOneDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), nickelStrunzLevelOneDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "NickelStrunzLevelOneDTO{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", name='" + getName() + "'" +
            "}";
    }
}
