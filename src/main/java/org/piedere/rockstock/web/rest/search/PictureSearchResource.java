package org.piedere.rockstock.web.rest.search;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.piedere.rockstock.service.dto.PictureDTO;
import org.piedere.rockstock.service.search.PictureSearchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller used for searching for pictures.
 */
@RestController
@RequestMapping("/api")
public class PictureSearchResource {

	private final Logger log = LoggerFactory.getLogger(PictureSearchResource.class);

	private final PictureSearchService pictureSearchService;

	public PictureSearchResource(PictureSearchService pictureSearchService) {
		this.pictureSearchService = pictureSearchService;
	}

	// ----------------------------------------------------------------------------------------------------------------
	// Picture retrieval methods:
	// ----------------------------------------------------------------------------------------------------------------

	/**
	 * Retrieve the list of all pictures for to a specimen.
	 * 
	 * @param specimenId The specimen to retrieve pictures for.
	 * @return The list of picture DTO's.
	 * @throws IOException 
	 */
	@GetMapping("/picturesearch/specimen/{specimenId}")
	public ResponseEntity<List<PictureDTO>> getPicturesForSpecimen(@PathVariable long specimenId,
			@RequestParam("maxHeight") Optional<Integer> maxHeight,
			@RequestParam("maxCount") Optional<Integer> maxCount) throws IOException {
		log.debug("REST getPicturesForSpecimen request");
		List<PictureDTO> pictures = pictureSearchService.getPicturesForSpecimen(specimenId, optionalToInt(maxHeight),
				optionalToInt(maxCount));
		return ResponseEntity.ok().body(pictures);
	}

	/**
	 * Retrieve the list of all pictures for to a location.
	 * 
	 * @param locationId The location to retrieve pictures for.
	 * @return The list of picture DTO's.
	 * @throws IOException 
	 */
	@GetMapping("/picturesearch/location/{locationId}")
	public ResponseEntity<List<PictureDTO>> getPicturesForLocation(@PathVariable long locationId,
			@RequestParam("maxHeight") Optional<Integer> maxHeight,
			@RequestParam("maxCount") Optional<Integer> maxCount) throws IOException {
		log.debug("REST getPicturesForLocation request");
		List<PictureDTO> pictures = pictureSearchService.getPicturesForLocation(locationId, optionalToInt(maxHeight),
				optionalToInt(maxCount));
		return ResponseEntity.ok().body(pictures);
	}

	/**
	 * Retrieve the list of all pictures for to a mineral. This is retrieved through
	 * the specimen pictures. A specimen might contain multiple minerals, so this
	 * list is not guaranteed to be correct.
	 * 
	 * @param mineralId The specimen to retrieve mineral for.
	 * @return The list of picture DTO's.
	 * @throws IOException
	 */
	@GetMapping("/picturesearch/mineral/{mineralId}")
	public ResponseEntity<List<PictureDTO>> getPicturesForMineral(@PathVariable long mineralId,
			@RequestParam("maxHeight") Optional<Integer> maxHeight,
			@RequestParam("maxCount") Optional<Integer> maxCount) throws IOException {
		log.debug("REST getPicturesForMineral request");
		List<PictureDTO> pictures = pictureSearchService.getPicturesForMineral(mineralId, optionalToInt(maxHeight),
				optionalToInt(maxCount));
		return ResponseEntity.ok().body(pictures);
	}

	/**
	 * Retrieve a single random picture.
	 * 
	 * @return A random picture.
	 */
	@GetMapping("/picturesearch/random")
	public ResponseEntity<PictureDTO> getRandomPicture() {
		log.debug("REST getRandomPicture request");
		PictureDTO picture = pictureSearchService.getRandomPicture();
		return ResponseEntity.ok().body(picture);
	}

	// ----------------------------------------------------------------------------------------------------------------
	// Private methods:
	// ----------------------------------------------------------------------------------------------------------------

	private int optionalToInt(Optional<Integer> optional) {
		if (optional.isPresent()) {
			return optional.get().intValue();
		}
		return 0;
	}
}