package org.piedere.rockstock.service.search;

import java.io.IOException;
import java.util.List;

import org.piedere.rockstock.service.dto.PictureDTO;

/**
 * Service Interface used to retrieve pictures.
 */
public interface PictureSearchService {

	// ----------------------------------------------------------------------------------------------------------------
	// Picture retrieval methods:
	// ----------------------------------------------------------------------------------------------------------------

	/**
	 * Retrieve the list of all pictures for to a specimen.
	 * 
	 * @param specimenId The specimen to retrieve pictures for.
	 * @param maxHeight  Set the maximum height for the pictures to return. This way
	 *                   thumbnails can be used client-side. Use 0 to ignore this
	 *                   option.
	 * @param maxCount   Set the maximum number of pictures to return. Use 0 to
	 *                   ignore this option.
	 * @return The list of picture DTO's.
	 */
	List<PictureDTO> getPicturesForSpecimen(long specimenId, int maxHeight, int maxCount) throws IOException;

	/**
	 * Retrieve the list of all pictures for to a location.
	 * 
	 * @param locationId The location to retrieve pictures for.
	 * @param maxHeight  Set the maximum height for the pictures to return. This way
	 *                   thumbnails can be used client-side. Use 0 to ignore this
	 *                   option.
	 * @param maxCount   Set the maximum number of pictures to return. Use 0 to
	 *                   ignore this option.
	 * @return The list of picture DTO's.
	 */
	List<PictureDTO> getPicturesForLocation(long locationId, int maxHeight, int maxCount) throws IOException;

	/**
	 * Retrieve the list of all pictures for to a mineral. This is retrieved through
	 * the specimen pictures. A specimen might contain multiple minerals, so this
	 * list is not guaranteed to be correct.
	 * 
	 * @param mineralId The specimen to retrieve mineral for.
	 * @param maxHeight  Set the maximum height for the pictures to return. This way
	 *                   thumbnails can be used client-side. Use 0 to ignore this
	 *                   option.
	 * @param maxCount   Set the maximum number of pictures to return. Use 0 to
	 *                   ignore this option.
	 * @return The list of picture DTO's.
	 */
	List<PictureDTO> getPicturesForMineral(long mineralId, int maxHeight, int maxCount) throws IOException;

	/**
	 * Retrieve a single random picture.
	 * 
	 * @return A random picture.
	 */
	PictureDTO getRandomPicture();
}