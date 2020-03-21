package org.piedere.rockstock.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link org.piedere.rockstock.domain.NickelStrunzLevelTwo} entity.
 */
public class NickelStrunzLevelTwoDTO implements Serializable {

    private Long id;

    @NotNull
    private String code;

    @NotNull
    private String name;


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

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long nickelStrunzLevelOneId) {
        this.parentId = nickelStrunzLevelOneId;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String nickelStrunzLevelOneName) {
        this.parentName = nickelStrunzLevelOneName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        NickelStrunzLevelTwoDTO nickelStrunzLevelTwoDTO = (NickelStrunzLevelTwoDTO) o;
        if (nickelStrunzLevelTwoDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), nickelStrunzLevelTwoDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "NickelStrunzLevelTwoDTO{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", name='" + getName() + "'" +
            ", parentId=" + getParentId() +
            ", parentName='" + getParentName() + "'" +
            "}";
    }
}
