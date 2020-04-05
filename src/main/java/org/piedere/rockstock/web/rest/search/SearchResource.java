package org.piedere.rockstock.web.rest.search;

import java.util.List;
import java.util.Map;

import org.piedere.rockstock.service.dto.NickelStrunzLevelThreeDTO;
import org.piedere.rockstock.service.dto.NickelStrunzLevelTwoDTO;
import org.piedere.rockstock.service.search.SearchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller used for searching.
 */
@RestController
@RequestMapping("/api")
public class SearchResource {

	private final Logger log = LoggerFactory.getLogger(SearchResource.class);

	private final SearchService searchService;

	public SearchResource(SearchService searchService) {
		this.searchService = searchService;
	}

	/**
	 * {@code GET  /nickel-strunz-level-twos} : get all the nickelStrunzLevelTwos
	 * for a nickel strunz level one.
	 *
	 * @param nickelStrunzLevelOneId The ID of the nickel-strunz level one.
	 * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list
	 *         of nickelStrunzLevelTwos in body.
	 */
	@GetMapping("/search/nickel-strunz-level-twos/{nickelStrunzLevelOneId}")
	public ResponseEntity<List<NickelStrunzLevelTwoDTO>> getNickelStrunzLevelTwosForLevelOne(
			@PathVariable long nickelStrunzLevelOneId) {
		log.debug("REST request to NickelStrunzLevelTwos for a NickelStrunzLevelOne");
		List<NickelStrunzLevelTwoDTO> list = searchService.getNickelStrunzLevelTwos(nickelStrunzLevelOneId);
		return ResponseEntity.ok().body(list);
	}

	/**
	 * {@code GET  /nickel-strunz-level-threes} : get all the
	 * nickelStrunzLevelThrees for a nickel strunz level two.
	 *
	 * @param nickelStrunzLevelTwoId The ID of the nickel-strunz level two.
	 * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list
	 *         of nickelStrunzLevelThrees in body.
	 */
	@GetMapping("/search/nickel-strunz-level-threes/{nickelStrunzLevelTwoId}")
	public ResponseEntity<List<NickelStrunzLevelThreeDTO>> getNickelStrunzLevelThreesForLevelTwo(
			@PathVariable long nickelStrunzLevelTwoId) {
		log.debug("REST request to NickelStrunzLevelThrees for a NickelStrunzLevelTwo");
		List<NickelStrunzLevelThreeDTO> list = searchService.getNickelStrunzLevelThrees(nickelStrunzLevelTwoId);
		return ResponseEntity.ok().body(list);
	}

	/**
	 * {@code GET  /nickel-strunz-parents} : Get the nickel-strunz hierarchy for a
	 * level three nickel-strunz ID.
	 * 
	 * @param nickelStrunzLevelThreeId The nickel-strunz level three ID.
	 * @return A map containing the nickel-strunz hierarchy DTO's. Access using
	 *         "levelOne", "levelTwo" and "levelThree".
	 */
	@GetMapping("/search/nickel-strunz-hierarchy/{nickelStrunzLevelThreeId}")
	public ResponseEntity<Map<String, Object>> getParents(@PathVariable long nickelStrunzLevelThreeId) {
		log.debug("REST request to find the hierarchy of a nickel-strunz level three");
		Map<String, Object> map = searchService.getHierarchy(nickelStrunzLevelThreeId);
		return ResponseEntity.ok().body(map);
	}
}