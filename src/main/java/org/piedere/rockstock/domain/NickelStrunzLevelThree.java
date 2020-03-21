package org.piedere.rockstock.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A NickelStrunzLevelThree.
 */
@Entity
@Table(name = "nickel_strunz_level_three")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class NickelStrunzLevelThree implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "code", nullable = false)
    private String code;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "short_name")
    private String shortName;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("nickelStrunzLevelThrees")
    private NickelStrunzLevelTwo parent;

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

    public NickelStrunzLevelThree code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public NickelStrunzLevelThree name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public NickelStrunzLevelThree shortName(String shortName) {
        this.shortName = shortName;
        return this;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public NickelStrunzLevelTwo getParent() {
        return parent;
    }

    public NickelStrunzLevelThree parent(NickelStrunzLevelTwo nickelStrunzLevelTwo) {
        this.parent = nickelStrunzLevelTwo;
        return this;
    }

    public void setParent(NickelStrunzLevelTwo nickelStrunzLevelTwo) {
        this.parent = nickelStrunzLevelTwo;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof NickelStrunzLevelThree)) {
            return false;
        }
        return id != null && id.equals(((NickelStrunzLevelThree) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "NickelStrunzLevelThree{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", name='" + getName() + "'" +
            ", shortName='" + getShortName() + "'" +
            "}";
    }
}
