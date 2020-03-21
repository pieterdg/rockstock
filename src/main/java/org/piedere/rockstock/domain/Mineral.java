package org.piedere.rockstock.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;
import java.util.HashSet;
import java.util.Set;

/**
 * A Mineral.
 */
@Entity
@Table(name = "mineral")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Mineral implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "dutch_name", nullable = false)
    private String dutchName;

    @NotNull
    @Column(name = "formula", nullable = false)
    private String formula;

    @NotNull
    @Column(name = "hardness_min", nullable = false)
    private Float hardnessMin;

    @NotNull
    @Column(name = "hardness_max", nullable = false)
    private Float hardnessMax;

    @NotNull
    @Column(name = "crystal_system", nullable = false)
    private String crystalSystem;

    @NotNull
    @Column(name = "nickel_struntz_level_four", nullable = false)
    private String nickelStruntzLevelFour;

    @NotNull
    @Column(name = "mindat_url", nullable = false)
    private String mindatUrl;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("minerals")
    private NickelStrunzLevelThree nickelStrunzLevelThree;

    @ManyToMany(mappedBy = "minerals")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JsonIgnore
    private Set<Specimen> specimens = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDutchName() {
        return dutchName;
    }

    public Mineral dutchName(String dutchName) {
        this.dutchName = dutchName;
        return this;
    }

    public void setDutchName(String dutchName) {
        this.dutchName = dutchName;
    }

    public String getFormula() {
        return formula;
    }

    public Mineral formula(String formula) {
        this.formula = formula;
        return this;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

    public Float getHardnessMin() {
        return hardnessMin;
    }

    public Mineral hardnessMin(Float hardnessMin) {
        this.hardnessMin = hardnessMin;
        return this;
    }

    public void setHardnessMin(Float hardnessMin) {
        this.hardnessMin = hardnessMin;
    }

    public Float getHardnessMax() {
        return hardnessMax;
    }

    public Mineral hardnessMax(Float hardnessMax) {
        this.hardnessMax = hardnessMax;
        return this;
    }

    public void setHardnessMax(Float hardnessMax) {
        this.hardnessMax = hardnessMax;
    }

    public String getCrystalSystem() {
        return crystalSystem;
    }

    public Mineral crystalSystem(String crystalSystem) {
        this.crystalSystem = crystalSystem;
        return this;
    }

    public void setCrystalSystem(String crystalSystem) {
        this.crystalSystem = crystalSystem;
    }

    public String getNickelStruntzLevelFour() {
        return nickelStruntzLevelFour;
    }

    public Mineral nickelStruntzLevelFour(String nickelStruntzLevelFour) {
        this.nickelStruntzLevelFour = nickelStruntzLevelFour;
        return this;
    }

    public void setNickelStruntzLevelFour(String nickelStruntzLevelFour) {
        this.nickelStruntzLevelFour = nickelStruntzLevelFour;
    }

    public String getMindatUrl() {
        return mindatUrl;
    }

    public Mineral mindatUrl(String mindatUrl) {
        this.mindatUrl = mindatUrl;
        return this;
    }

    public void setMindatUrl(String mindatUrl) {
        this.mindatUrl = mindatUrl;
    }

    public NickelStrunzLevelThree getNickelStrunzLevelThree() {
        return nickelStrunzLevelThree;
    }

    public Mineral nickelStrunzLevelThree(NickelStrunzLevelThree nickelStrunzLevelThree) {
        this.nickelStrunzLevelThree = nickelStrunzLevelThree;
        return this;
    }

    public void setNickelStrunzLevelThree(NickelStrunzLevelThree nickelStrunzLevelThree) {
        this.nickelStrunzLevelThree = nickelStrunzLevelThree;
    }

    public Set<Specimen> getSpecimens() {
        return specimens;
    }

    public Mineral specimens(Set<Specimen> specimen) {
        this.specimens = specimen;
        return this;
    }

    public Mineral addSpecimens(Specimen specimen) {
        this.specimens.add(specimen);
        specimen.getMinerals().add(this);
        return this;
    }

    public Mineral removeSpecimens(Specimen specimen) {
        this.specimens.remove(specimen);
        specimen.getMinerals().remove(this);
        return this;
    }

    public void setSpecimens(Set<Specimen> specimen) {
        this.specimens = specimen;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Mineral)) {
            return false;
        }
        return id != null && id.equals(((Mineral) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Mineral{" +
            "id=" + getId() +
            ", dutchName='" + getDutchName() + "'" +
            ", formula='" + getFormula() + "'" +
            ", hardnessMin=" + getHardnessMin() +
            ", hardnessMax=" + getHardnessMax() +
            ", crystalSystem='" + getCrystalSystem() + "'" +
            ", nickelStruntzLevelFour='" + getNickelStruntzLevelFour() + "'" +
            ", mindatUrl='" + getMindatUrl() + "'" +
            "}";
    }
}
