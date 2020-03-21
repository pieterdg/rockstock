package org.piedere.rockstock.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A Picture.
 */
@Entity
@Table(name = "picture")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Picture implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    
    @Lob
    @Column(name = "data", nullable = false)
    private byte[] data;

    @Column(name = "data_content_type", nullable = false)
    private String dataContentType;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JsonIgnoreProperties("pictures")
    private Specimen specimen;

    @ManyToOne
    @JsonIgnoreProperties("pictures")
    private Location location;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getData() {
        return data;
    }

    public Picture data(byte[] data) {
        this.data = data;
        return this;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public String getDataContentType() {
        return dataContentType;
    }

    public Picture dataContentType(String dataContentType) {
        this.dataContentType = dataContentType;
        return this;
    }

    public void setDataContentType(String dataContentType) {
        this.dataContentType = dataContentType;
    }

    public String getDescription() {
        return description;
    }

    public Picture description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Specimen getSpecimen() {
        return specimen;
    }

    public Picture specimen(Specimen specimen) {
        this.specimen = specimen;
        return this;
    }

    public void setSpecimen(Specimen specimen) {
        this.specimen = specimen;
    }

    public Location getLocation() {
        return location;
    }

    public Picture location(Location location) {
        this.location = location;
        return this;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Picture)) {
            return false;
        }
        return id != null && id.equals(((Picture) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Picture{" +
            "id=" + getId() +
            ", data='" + getData() + "'" +
            ", dataContentType='" + getDataContentType() + "'" +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
