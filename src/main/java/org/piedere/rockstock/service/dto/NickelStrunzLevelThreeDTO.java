package org.piedere.rockstock.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link org.piedere.rockstock.domain.NickelStrunzLevelThree} entity.
 */
public class NickelStrunzLevelThreeDTO implements Serializable {

    private Long id;

    @NotNull
    private String code;

    @NotNull
    private String name;

    private String shortName;


    private Long parentId;

    private String parentName;

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

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long nickelStrunzLevelTwoId) {
        this.parentId = nickelStrunzLevelTwoId;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String nickelStrunzLevelTwoName) {
        this.parentName = nickelStrunzLevelTwoName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        NickelStrunzLevelThreeDTO nickelStrunzLevelThreeDTO = (NickelStrunzLevelThreeDTO) o;
        if (nickelStrunzLevelThreeDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), nickelStrunzLevelThreeDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "NickelStrunzLevelThreeDTO{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", name='" + getName() + "'" +
            ", shortName='" + getShortName() + "'" +
            ", parentId=" + getParentId() +
            ", parentName='" + getParentName() + "'" +
            "}";
    }
}
