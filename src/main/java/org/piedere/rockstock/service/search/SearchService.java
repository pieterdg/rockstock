package org.piedere.rockstock.service.search;

import java.util.List;
import java.util.Map;

import org.piedere.rockstock.service.dto.NickelStrunzLevelThreeDTO;
import org.piedere.rockstock.service.dto.NickelStrunzLevelTwoDTO;
import org.piedere.rockstock.service.dto.search.SearchResultDTO;

/**
 * Service Interface used to search for entities.
 */
public interface SearchService {

	// ----------------------------------------------------------------------------------------------------------------
	// Generic search methods:
	// ----------------------------------------------------------------------------------------------------------------

	/**
	 * Search for specimen/minerals/locations based on a search text.
	 * 
	 * @param searchText The search parameter.
	 * @return The result. Can contain specimen/minerals/locations.
	 */
	SearchResultDTO search(String searchText);

	// ----------------------------------------------------------------------------------------------------------------
	// Methods regarding Nickel-Strunz:
	// ----------------------------------------------------------------------------------------------------------------

	/**
	 * Get a list of nickel-strunz level twos for a given nickel-strunz level one.
	 * 
	 * @param levelOneId The nickel-strunz level one for which to get the
	 *                   nickel-strunz level twos.
	 * @return The list of level twos for the given nickel-strunz level one.
	 */
	List<NickelStrunzLevelTwoDTO> getNickelStrunzLevelTwos(long levelOneId);

	/**
	 * Get a list of nickel-strunz level threes for a given nickel-strunz level two.
	 * 
	 * @param levelTwoId nickel-strunz level two for which to get the nickel-strunz
	 *                   level threes.
	 * @return The list of nickel-strunz level threes for the given nickel-strunz
	 *         level two.
	 */
	List<NickelStrunzLevelThreeDTO> getNickelStrunzLevelThrees(long levelTwoId);

	/**
	 * Get the nickel-strunz hierarchy of the given nickel-strunz level three.
	 * three.
	 * 
	 * @param nickelstrunzLevelThreeId The ID of the nickel-strunz level three
	 * @return A map containing the nickel-strunz hierarchy.
	 */
	Map<String, Object> getHierarchy(Long nickelstrunzLevelThreeId);
}