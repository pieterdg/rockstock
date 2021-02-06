package org.piedere.rockstock.web.rest.tools;

import java.io.IOException;
import java.io.Serializable;
import java.net.URISyntaxException;

import javax.validation.Valid;

import org.piedere.rockstock.service.dto.tools.ImportDTO;
import org.piedere.rockstock.service.tools.ImportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.jhipster.web.util.HeaderUtil;

/**
 * REST controller for managing {@link org.piedere.rockstock.domain.Picture}.
 */
@RestController
@RequestMapping("/api")
public class ImportResource {

	private final Logger log = LoggerFactory.getLogger(ImportResource.class);

	private static final String ENTITY_NAME = "import";

	@Value("${jhipster.clientApp.name}")
	private String applicationName;

	private final ImportService importService;

	public ImportResource(ImportService importService) {
		this.importService = importService;
	}

	/**
	 * {@code POST  /pictures} : Create a new picture.
	 *
	 * @param importDTO the information to import. Can be both locations and
	 *                  specimen.
	 * @return the {@link ResponseEntity} with status {@code 201 (Created)} or with
	 *         status {@code 400 (Bad Request)} if the import somehow failed.
	 * @throws URISyntaxException if the Location URI syntax is incorrect.
	 */
	@PostMapping("/import")
	public ResponseEntity<ImportMessage> importFile(@Valid @RequestBody ImportDTO importDTO) throws URISyntaxException {
		log.debug("REST request to import file : {}", importDTO.toStringWithoutData());

		try {
			String result = importService.importFile(importDTO);
			return ResponseEntity.ok(new ImportMessage(result));
		} catch (IOException e) {
			return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(applicationName, false,
					"Location or Specimen", "Import error", e.getMessage())).build();
		}
	}

	/**
	 * Message to be returned to the client.
	 */
	private class ImportMessage implements Serializable {

		private static final long serialVersionUID = 1L;

		private String message;

		private ImportMessage(String message) {
			this.message = message;
		}

		public String getMessage() {
			return message;
		}
	}
}
