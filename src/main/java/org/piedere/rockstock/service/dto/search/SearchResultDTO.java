package org.piedere.rockstock.service.dto.search;

import java.io.Serializable;
import java.util.List;

import org.piedere.rockstock.service.dto.LocationDTO;
import org.piedere.rockstock.service.dto.MineralDTO;
import org.piedere.rockstock.service.dto.SpecimenDTO;

/**
 * A DTO for the {@link org.piedere.rockstock.domain.Country} entity.
 */
public class SearchResultDTO implements Serializable {

	private List<SpecimenDTO> specimen;

	private List<LocationDTO> locations;

	private List<MineralDTO> minerals;

	public List<SpecimenDTO> getSpecimen() {
		return specimen;
	}

	public void setSpecimen(List<SpecimenDTO> specimen) {
		this.specimen = specimen;
	}

	public List<LocationDTO> getLocations() {
		return locations;
	}

	public void setLocations(List<LocationDTO> locations) {
		this.locations = locations;
	}

	public List<MineralDTO> getMinerals() {
		return minerals;
	}

	public void setMinerals(List<MineralDTO> minerals) {
		this.minerals = minerals;
	}
}
