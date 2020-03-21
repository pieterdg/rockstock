package org.piedere.rockstock.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link org.piedere.rockstock.domain.Mineral} entity.
 */
public class MineralDTO implements Serializable {
    
    private Long id;

    @NotNull
    private String dutchName;

    @NotNull
    private String formula;

    @NotNull
    private Float hardnessMin;

    @NotNull
    private Float hardnessMax;

    @NotNull
    private String crystalSystem;

    @NotNull
    private String nickelStruntzLevelFour;

    @NotNull
    private String mindatUrl;


    private Long nickelStrunzLevelThreeId;

    private String nickelStrunzLevelThreeName;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDutchName() {
        return dutchName;
    }

    public void setDutchName(String dutchName) {
        this.dutchName = dutchName;
    }

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

    public Float getHardnessMin() {
        return hardnessMin;
    }

    public void setHardnessMin(Float hardnessMin) {
        this.hardnessMin = hardnessMin;
    }

    public Float getHardnessMax() {
        return hardnessMax;
    }

    public void setHardnessMax(Float hardnessMax) {
        this.hardnessMax = hardnessMax;
    }

    public String getCrystalSystem() {
        return crystalSystem;
    }

    public void setCrystalSystem(String crystalSystem) {
        this.crystalSystem = crystalSystem;
    }

    public String getNickelStruntzLevelFour() {
        return nickelStruntzLevelFour;
    }

    public void setNickelStruntzLevelFour(String nickelStruntzLevelFour) {
        this.nickelStruntzLevelFour = nickelStruntzLevelFour;
    }

    public String getMindatUrl() {
        return mindatUrl;
    }

    public void setMindatUrl(String mindatUrl) {
        this.mindatUrl = mindatUrl;
    }

    public Long getNickelStrunzLevelThreeId() {
        return nickelStrunzLevelThreeId;
    }

    public void setNickelStrunzLevelThreeId(Long nickelStrunzLevelThreeId) {
        this.nickelStrunzLevelThreeId = nickelStrunzLevelThreeId;
    }

    public String getNickelStrunzLevelThreeName() {
        return nickelStrunzLevelThreeName;
    }

    public void setNickelStrunzLevelThreeName(String nickelStrunzLevelThreeName) {
        this.nickelStrunzLevelThreeName = nickelStrunzLevelThreeName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MineralDTO mineralDTO = (MineralDTO) o;
        if (mineralDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mineralDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MineralDTO{" +
            "id=" + getId() +
            ", dutchName='" + getDutchName() + "'" +
            ", formula='" + getFormula() + "'" +
            ", hardnessMin=" + getHardnessMin() +
            ", hardnessMax=" + getHardnessMax() +
            ", crystalSystem='" + getCrystalSystem() + "'" +
            ", nickelStruntzLevelFour='" + getNickelStruntzLevelFour() + "'" +
            ", mindatUrl='" + getMindatUrl() + "'" +
            ", nickelStrunzLevelThreeId=" + getNickelStrunzLevelThreeId() +
            ", nickelStrunzLevelThreeName='" + getNickelStrunzLevelThreeName() + "'" +
            "}";
    }
}
