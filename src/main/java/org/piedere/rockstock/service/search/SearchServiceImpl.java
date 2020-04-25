package org.piedere.rockstock.service.search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.piedere.rockstock.domain.Location;
import org.piedere.rockstock.domain.Mineral;
import org.piedere.rockstock.domain.NickelStrunzLevelOne;
import org.piedere.rockstock.domain.NickelStrunzLevelThree;
import org.piedere.rockstock.domain.NickelStrunzLevelTwo;
import org.piedere.rockstock.domain.Specimen;
import org.piedere.rockstock.repository.LocationRepository;
import org.piedere.rockstock.repository.MineralRepository;
import org.piedere.rockstock.repository.NickelStrunzLevelOneRepository;
import org.piedere.rockstock.repository.NickelStrunzLevelThreeRepository;
import org.piedere.rockstock.repository.NickelStrunzLevelTwoRepository;
import org.piedere.rockstock.repository.SpecimenRepository;
import org.piedere.rockstock.service.dto.NickelStrunzLevelThreeDTO;
import org.piedere.rockstock.service.dto.NickelStrunzLevelTwoDTO;
import org.piedere.rockstock.service.dto.search.SearchResultDTO;
import org.piedere.rockstock.service.mapper.LocationMapper;
import org.piedere.rockstock.service.mapper.MineralMapper;
import org.piedere.rockstock.service.mapper.NickelStrunzLevelOneMapper;
import org.piedere.rockstock.service.mapper.NickelStrunzLevelThreeMapper;
import org.piedere.rockstock.service.mapper.NickelStrunzLevelTwoMapper;
import org.piedere.rockstock.service.mapper.SpecimenMapper;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SearchServiceImpl implements SearchService {

	@PersistenceContext
	private EntityManager entityManager;

	private final SpecimenRepository specimenRepository;
	private final MineralRepository mineralRepository;
	private final LocationRepository locationRepository;

	private final SpecimenMapper specimenMapper;
	private final MineralMapper mineralMapper;
	private final LocationMapper locationMapper;

	// Nickel-strunz services:

	private final NickelStrunzLevelOneRepository levelOneRepository;

	private final NickelStrunzLevelTwoRepository levelTwoRepository;

	private final NickelStrunzLevelThreeRepository levelThreeRepository;

	private final NickelStrunzLevelOneMapper nickelStrunzLevelOneMapper;

	private final NickelStrunzLevelTwoMapper nickelStrunzLevelTwoMapper;

	private final NickelStrunzLevelThreeMapper nickelStrunzLevelThreeMapper;

	public SearchServiceImpl(SpecimenRepository specimenRepository, MineralRepository mineralRepository,
			LocationRepository locationRepository, SpecimenMapper specimenMapper, MineralMapper mineralMapper,
			LocationMapper locationMapper, NickelStrunzLevelOneRepository levelOneRepository,
			NickelStrunzLevelTwoRepository levelTwoRepository, NickelStrunzLevelThreeRepository levelThreeRepository,
			NickelStrunzLevelOneMapper nickelStrunzLevelOneMapper,
			NickelStrunzLevelTwoMapper nickelStrunzLevelTwoMapper,
			NickelStrunzLevelThreeMapper nickelStrunzLevelThreeMapper) {
		this.specimenRepository = specimenRepository;
		this.mineralRepository = mineralRepository;
		this.locationRepository = locationRepository;
		this.specimenMapper = specimenMapper;
		this.mineralMapper = mineralMapper;
		this.locationMapper = locationMapper;

		this.levelOneRepository = levelOneRepository;
		this.levelTwoRepository = levelTwoRepository;
		this.levelThreeRepository = levelThreeRepository;
		this.nickelStrunzLevelOneMapper = nickelStrunzLevelOneMapper;
		this.nickelStrunzLevelTwoMapper = nickelStrunzLevelTwoMapper;
		this.nickelStrunzLevelThreeMapper = nickelStrunzLevelThreeMapper;
	}

	// ----------------------------------------------------------------------------------------------------------------
	// Generic search methods:
	// ----------------------------------------------------------------------------------------------------------------

	@Override
	public SearchResultDTO search(String searchText) {
		SearchResultDTO searchResult = new SearchResultDTO();
		List<Specimen> specimen = new ArrayList<Specimen>();

		// First we check if the searchText equals a specimen code:
		specimen = findSpecimen(searchText);

		// If we did not find exactly 1 specimen, look further:
		if (specimen.size() != 1) {
			// Search for minerals and their specimen:
			List<Mineral> minerals = findMinerals(searchText);
			for (int i = 0; i < minerals.size(); i++) {
				findSpecimenForMineral(minerals.get(i), specimen);
			}
			searchResult.setMinerals(mineralMapper.toDto(minerals));

			// Search for locations and their specimen:
			List<Location> locations = findLocations(searchText);
			for (int i = 0; i < locations.size(); i++) {
				findSpecimenForLocation(locations.get(i), specimen);
			}
			searchResult.setLocations(locationMapper.toDto(locations));
		}

		// Now return the SearchResultDTO:
		searchResult.setSpecimen(specimenMapper.toDto(specimen));
		return searchResult;
	}

	private List<Specimen> findSpecimen(String searchText) {
		Specimen specimen = new Specimen();
		specimen.setCode(searchText);
		ExampleMatcher matcher = ExampleMatcher.matching().withMatcher("code",
				ExampleMatcher.GenericPropertyMatcher.of(ExampleMatcher.StringMatcher.EXACT).ignoreCase());
		return specimenRepository.findAll(Example.of(specimen, matcher));
	}

	private List<Mineral> findMinerals(String searchText) {
		Mineral mineral = new Mineral();
		mineral.setDutchName(searchText);
		ExampleMatcher matcher = ExampleMatcher.matching().withMatcher("dutchName",
				ExampleMatcher.GenericPropertyMatcher.of(ExampleMatcher.StringMatcher.CONTAINING).ignoreCase());
		return mineralRepository.findAll(Example.of(mineral, matcher));
	}

	private void findSpecimenForMineral(Mineral mineral, List<Specimen> specimenList) {
		specimenList.addAll(mineral.getSpecimens());
	}

	private List<Location> findLocations(String searchText) {
		Location location = new Location();
		location.setShortName(searchText);
		ExampleMatcher matcher = ExampleMatcher.matching().withMatcher("shortName",
				ExampleMatcher.GenericPropertyMatcher.of(ExampleMatcher.StringMatcher.CONTAINING).ignoreCase());
		return locationRepository.findAll(Example.of(location, matcher));
	}

	private void findSpecimenForLocation(Location location, List<Specimen> specimenList) {
		Specimen specimen = new Specimen();
		specimen.setLocation(location);
		specimenList.addAll(specimenRepository.findAll(Example.of(specimen)));
	}

	// ----------------------------------------------------------------------------------------------------------------
	// Methods regarding Nickel-Strunz:
	// ----------------------------------------------------------------------------------------------------------------

	@Override
	public List<NickelStrunzLevelTwoDTO> getNickelStrunzLevelTwos(long levelOneId) {
		NickelStrunzLevelOne one = new NickelStrunzLevelOne();
		one.setId(levelOneId);
		NickelStrunzLevelTwo two = new NickelStrunzLevelTwo();
		two.setParent(one);
		List<NickelStrunzLevelTwo> twos = levelTwoRepository.findAll(Example.of(two));

		List<NickelStrunzLevelTwoDTO> dtos = new ArrayList<NickelStrunzLevelTwoDTO>(twos.size());
		for (NickelStrunzLevelTwo pojo : twos) {
			dtos.add(nickelStrunzLevelTwoMapper.toDto(pojo));
		}
		return dtos;
	}

	@Override
	public List<NickelStrunzLevelThreeDTO> getNickelStrunzLevelThrees(long levelTwoId) {
		NickelStrunzLevelTwo two = new NickelStrunzLevelTwo();
		two.setId(levelTwoId);
		NickelStrunzLevelThree three = new NickelStrunzLevelThree();
		three.setParent(two);
		List<NickelStrunzLevelThree> threes = levelThreeRepository.findAll(Example.of(three));

		List<NickelStrunzLevelThreeDTO> dtos = new ArrayList<NickelStrunzLevelThreeDTO>(threes.size());
		for (NickelStrunzLevelThree pojo : threes) {
			dtos.add(nickelStrunzLevelThreeMapper.toDto(pojo));
		}
		return dtos;
	}

	@Override
	public Map<String, Object> getHierarchy(Long nickelstrunzLevelThreeId) {
		Optional<NickelStrunzLevelThree> three = levelThreeRepository.findById(nickelstrunzLevelThreeId);
		if (!three.isEmpty()) {
			Optional<NickelStrunzLevelTwo> two = levelTwoRepository.findById(three.get().getParent().getId());
			Optional<NickelStrunzLevelOne> one = levelOneRepository.findById(two.get().getParent().getId());
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("levelOne", nickelStrunzLevelOneMapper.toDto(one.get()));
			map.put("levelTwo", nickelStrunzLevelTwoMapper.toDto(two.get()));
			map.put("levelThree", nickelStrunzLevelThreeMapper.toDto(three.get()));
			return map;
		}
		return null;
	}
}
