package org.piedere.rockstock.web.rest.home;

import org.piedere.rockstock.service.home.HomeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller used on the home page.
 */
@RestController
@RequestMapping("/api")
public class HomeResource {

	private final Logger log = LoggerFactory.getLogger(HomeResource.class);

	private final HomeService homeService;

	public HomeResource(HomeService homeService) {
		this.homeService = homeService;
	}

	/**
	 * Get the total number of countries within the collection.
	 * 
	 * @return the ResponseEntity with status 200 (OK) containing the number.
	 */
	@GetMapping("/countries/count")
	public ResponseEntity<Long> countCountries() {
		log.debug("REST request to get the total number of countries");
		return ResponseEntity.ok(Long.valueOf(homeService.countCountries()));
	}

	/**
	 * Get the total number of minerals within the collection.
	 * 
	 * @return the ResponseEntity with status 200 (OK) containing the number.
	 */
	@GetMapping("/minerals/count")
	public ResponseEntity<Long> countMinerals() {
		log.debug("REST request to get the total number of minerals");
		return ResponseEntity.ok(Long.valueOf(homeService.countMinerals()));
	}

	/**
	 * Get the total number of countries within the collection.
	 * 
	 * @return the ResponseEntity with status 200 (OK) containing the number.
	 */
	@GetMapping("/specimen/count")
	public ResponseEntity<Long> countSpecimen() {
		log.debug("REST request to get the total number of specimen");
		return ResponseEntity.ok(Long.valueOf(homeService.countSpecimen()));
	}
}