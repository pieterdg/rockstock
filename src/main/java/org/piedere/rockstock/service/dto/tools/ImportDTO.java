package org.piedere.rockstock.service.dto.tools;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Lob;

/**
 * A DTO importing specimen into the DB.
 */
public class ImportDTO implements Serializable {

	@Lob
	private byte[] data;

	private String dataContentType;

	private boolean importLocations;

	private boolean importSpecimen;

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

	public boolean isImportLocations() {
		return importLocations;
	}

	public void setImportLocations(boolean importLocations) {
		this.importLocations = importLocations;
	}

	public boolean isImportSpecimen() {
		return importSpecimen;
	}

	public void setImportSpecimen(boolean importSpecimen) {
		this.importSpecimen = importSpecimen;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		ImportDTO importDTO = (ImportDTO) o;
		if (importDTO.getData() == null || getData() == null) {
			return false;
		}
		return Objects.equals(getData(), importDTO.getData());
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(getData());
	}

	@Override
	public String toString() {
		return "ImportDTO{data='" + getData() + ", importLocations='" + isImportLocations() + ", importSpecimen='"
				+ isImportSpecimen() + "'}";
	}

	public String toStringWithoutData() {
		return "ImportDTO{importLocations='" + isImportLocations() + ", importSpecimen='" + isImportSpecimen() + "'}";
	}
}
