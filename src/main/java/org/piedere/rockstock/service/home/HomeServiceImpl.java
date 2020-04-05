package org.piedere.rockstock.service.home;

import org.piedere.rockstock.repository.CountryRepository;
import org.piedere.rockstock.repository.LocationRepository;
import org.piedere.rockstock.repository.MineralRepository;
import org.piedere.rockstock.repository.SpecimenRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing Country.
 */
@Service
@Transactional
public class HomeServiceImpl implements HomeService {

	private final CountryRepository countryRepository;

	private final LocationRepository locationRepository;

	private final MineralRepository mineralRespository;

	private final SpecimenRepository specimenRepository;

	public HomeServiceImpl(MineralRepository mineralRespository, SpecimenRepository specimenRepository,
			CountryRepository countryRepository, LocationRepository locationRepository) {
		this.countryRepository = countryRepository;
		this.mineralRespository = mineralRespository;
		this.specimenRepository = specimenRepository;
		this.locationRepository = locationRepository;
	}

	@Override
	public long countCountries() {
		return countryRepository.count();
	}

	@Override
	public long countLocations() {
		return locationRepository.count();
	}

	@Override
	public long countMinerals() {
		return mineralRespository.count();
	}

	@Override
	public long countSpecimen() {
		return specimenRepository.count();
	}
}