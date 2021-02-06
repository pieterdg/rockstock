package org.piedere.rockstock.service.tools;

import java.io.IOException;

import org.piedere.rockstock.service.dto.tools.ImportDTO;

/**
 * Service Interface for managing {@link org.piedere.rockstock.domain.Picture}.
 */
public interface ImportService {

	/**
	 * File to import. Can contain both locations and/or specimen.
	 * 
	 * @param importDTO The file to import into the DB.
	 * @return Returns a text with a summary.
	 */
	String importFile(ImportDTO importDTO) throws IOException;
}
