package org.piedere.rockstock.service.tools;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.piedere.rockstock.domain.Country;
import org.piedere.rockstock.domain.Location;
import org.piedere.rockstock.domain.Mineral;
import org.piedere.rockstock.domain.Specimen;
import org.piedere.rockstock.repository.CountryRepository;
import org.piedere.rockstock.repository.LocationRepository;
import org.piedere.rockstock.repository.MineralRepository;
import org.piedere.rockstock.repository.NickelStrunzLevelThreeRepository;
import org.piedere.rockstock.repository.SpecimenRepository;
import org.piedere.rockstock.repository.SpecimenStatusRepository;
import org.piedere.rockstock.service.dto.tools.ImportDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for importing files into the DB.
 */
@Service
@Transactional
public class ImportServiceImpl implements ImportService {

	private final Logger log = LoggerFactory.getLogger(ImportServiceImpl.class);

	private final CountryRepository countryRepository;

	private final LocationRepository locationRepository;

	private final SpecimenRepository specimenRepository;

	private final SpecimenStatusRepository statusRepository;

	private final MineralRepository mineralRepository;

	private final NickelStrunzLevelThreeRepository nickelStrunzRepository;

	public ImportServiceImpl(CountryRepository countryRepository, LocationRepository locationRepository,
			SpecimenRepository specimenRepository, SpecimenStatusRepository statusRepository,
			MineralRepository mineralRepository, NickelStrunzLevelThreeRepository nickelStrunzRepository) {
		this.countryRepository = countryRepository;
		this.locationRepository = locationRepository;
		this.specimenRepository = specimenRepository;
		this.statusRepository = statusRepository;
		this.mineralRepository = mineralRepository;
		this.nickelStrunzRepository = nickelStrunzRepository;
	}

	@Override
	public String importFile(ImportDTO importDTO) throws IOException {
		if (importDTO.getData() == null) {
			return "No CSV file was received.";
		}

		InputStream is = null;
		BufferedReader bfReader = null;
		int count = 0;
		try {
			is = new ByteArrayInputStream(importDTO.getData());
			bfReader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
			bfReader.readLine(); // Read the header and forget about it.

			String temp = null;
			while ((temp = bfReader.readLine()) != null) {
				String[] values = temp.split(",", -30);
				Location location = null;
				if (importDTO.isImportLocations()) {
					location = saveOrUpdateLocation(cleanupCsvLine(values));
				}
				if (importDTO.isImportSpecimen()) {
					saveOrUpdateSpecimen(cleanupCsvLine(values), location);
				}
				count++;
			}
		} catch (IOException e) {
			throw e;
		} finally {
			try {
				if (is != null)
					is.close();
			} catch (Exception ex) {
			}
		}

		String message = "File imported succesfully: " + count + " ";
		if (importDTO.isImportLocations()) {
			message += "locations ";
		}
		if (importDTO.isImportSpecimen()) {
			if (importDTO.isImportLocations()) {
				message += "and ";
			}
			message += "specimen ";
		}
		return message + "imported.";
	}

	private Location saveOrUpdateLocation(List<String> values) throws IOException {
		Location tempLocation = new Location();
		tempLocation.setCity(values.get(4));
		tempLocation.setMine(values.get(5));
		tempLocation.setStateProvince(values.get(3));

		Location location = null;
		Optional<Location> optional = locationRepository.findOne(Example.of(tempLocation));
		if (optional.isEmpty()) {
			location = tempLocation;

			Country tempCountry = new Country();
			tempCountry.setCountryName(values.get(2));
			Optional<Country> maybeCountry = countryRepository.findOne(Example.of(tempCountry));
			if (maybeCountry.isEmpty()) {
				throw new IOException("Country (" + values.get(2) + ") not recognized.");
			}
			location.setCountry(maybeCountry.get());
		} else {
			location = optional.get();
		}

		location.setShortName(values.get(6));
		return locationRepository.save(location);
	}

	private void saveOrUpdateSpecimen(List<String> values, Location location) throws IOException {
		log.error("Line (" + values.size() + "): " + values.toString());
		if (location == null) {
			throw new IOException("Location not found while saving specimen.");
		}
		Specimen tempSpecimen = new Specimen();
		tempSpecimen.setCode(values.get(0));

		Specimen specimen = null;
		Optional<Specimen> optional = specimenRepository.findOne(Example.of(tempSpecimen));
		if (optional.isEmpty()) {
			specimen = tempSpecimen;
			specimen.setStatus(statusRepository.getOne(Long.valueOf(1)));
		} else {
			specimen = optional.get();
		}
		specimen.setAcquiredAt(values.get(11));
		specimen.setAcquiredBy(values.get(8));
		specimen.setAcquiredFrom(values.get(12));
		String price = values.get(7);
		log.error("PRICE=" + price);
		if (price.startsWith("\"")) {
			price = price.substring(1, price.length() - 2);
			log.error("PRICE=" + price);
		}
		specimen.setAcquiredPrice(Float.valueOf(price.replace(",", ".")));
		specimen.setRemark(values.get(13));
		specimen.setLocation(location);

		String[] dateString = values.get(9).split("-");
		if (dateString.length > 0) {
			specimen.setAcquiredDate(LocalDate.of(Integer.valueOf(dateString[2]), Integer.valueOf(dateString[1]),
					Integer.valueOf(dateString[0])));
		} else {
			specimen.setAcquiredDate(LocalDate.now());
		}

		Set<Mineral> minerals = new HashSet<Mineral>();
		String[] mins;
		if (values.get(1).startsWith("\"")) {
			mins = values.get(1).substring(1, price.length() - 2).split(",");
		} else {
			mins = values.get(1).split(",");
		}
		for (int i = 0; i < mins.length; i++) {
			minerals.add(findOrCreateMineral(mins[i]));
		}
		specimen.setMinerals(minerals);
		specimenRepository.save(specimen);
	}

	private Mineral findOrCreateMineral(String mineralName) {
		Mineral mineral = new Mineral();
		mineral.setDutchName(mineralName);
		Optional<Mineral> optional = mineralRepository.findOne(Example.of(mineral));
		if (optional.isEmpty()) {
			mineral.setCrystalSystem("TODO");
			mineral.setFormula("TODO");
			mineral.setHardnessMax(Float.valueOf(0));
			mineral.setHardnessMin(Float.valueOf(0));
			mineral.setMindatUrl("TODO");
			mineral.setNickelStruntzLevelFour("TODO");
			mineral.setNickelStrunzLevelThree(nickelStrunzRepository.getOne(Long.valueOf(1)));
			return mineralRepository.save(mineral);
		}
		return optional.get();
	}

	private List<String> cleanupCsvLine(String[] values) {
		List<String> newValues = new ArrayList<String>();
		String temp = "";
		for (int i = 0; i < values.length; i++) {
			if (values[i].startsWith("\"")) {
				temp += values[i++].substring(1);
				while (!values[i].endsWith("\"")) {
					temp += "," + values[i++];
				}
				temp += "," + values[i].substring(0, values[i].length() - 1);
				newValues.add(temp);
				temp = "";
			} else {
				newValues.add(values[i]);
			}
		}
		return newValues;
	}
}
