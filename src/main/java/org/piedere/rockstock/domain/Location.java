package org.piedere.rockstock.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A Location.
 */
@Entity
@Table(name = "location")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Location implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "short_name", nullable = false)
    private String shortName;

    @Column(name = "state_province")
    private String stateProvince;

    @Column(name = "city")
    private String city;

    @Column(name = "mine")
    private String mine;

    @Column(name = "x_position")
    private Integer xPosition;

    @Column(name = "y_position")
    private Integer yPosition;

    @ManyToOne
    @JsonIgnoreProperties("locations")
    private Country country;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getShortName() {
        return shortName;
    }

    public Location shortName(String shortName) {
        this.shortName = shortName;
        return this;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getStateProvince() {
        return stateProvince;
    }

    public Location stateProvince(String stateProvince) {
        this.stateProvince = stateProvince;
        return this;
    }

    public void setStateProvince(String stateProvince) {
        this.stateProvince = stateProvince;
    }

    public String getCity() {
        return city;
    }

    public Location city(String city) {
        this.city = city;
        return this;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getMine() {
        return mine;
    }

    public Location mine(String mine) {
        this.mine = mine;
        return this;
    }

    public void setMine(String mine) {
        this.mine = mine;
    }

    public Integer getxPosition() {
        return xPosition;
    }

    public Location xPosition(Integer xPosition) {
        this.xPosition = xPosition;
        return this;
    }

    public void setxPosition(Integer xPosition) {
        this.xPosition = xPosition;
    }

    public Integer getyPosition() {
        return yPosition;
    }

    public Location yPosition(Integer yPosition) {
        this.yPosition = yPosition;
        return this;
    }

    public void setyPosition(Integer yPosition) {
        this.yPosition = yPosition;
    }

    public Country getCountry() {
        return country;
    }

    public Location country(Country country) {
        this.country = country;
        return this;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Location)) {
            return false;
        }
        return id != null && id.equals(((Location) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Location{" +
            "id=" + getId() +
            ", shortName='" + getShortName() + "'" +
            ", stateProvince='" + getStateProvince() + "'" +
            ", city='" + getCity() + "'" +
            ", mine='" + getMine() + "'" +
            ", xPosition=" + getxPosition() +
            ", yPosition=" + getyPosition() +
            "}";
    }
}
