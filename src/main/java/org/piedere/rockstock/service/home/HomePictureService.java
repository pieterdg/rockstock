package org.piedere.rockstock.service.home;

import org.piedere.rockstock.service.dto.PictureDTO;

/**
 * Service Interface used on the home page.
 */
public interface HomePictureService {

    /**
     * Get a random picture from either a location of a specimen.
     * 
     * @return A random picture.
     */
    PictureDTO getRandomPicture();
}