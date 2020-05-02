package org.piedere.rockstock.service.search;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.piedere.rockstock.domain.Location;
import org.piedere.rockstock.domain.Mineral;
import org.piedere.rockstock.domain.Picture;
import org.piedere.rockstock.domain.Specimen;
import org.piedere.rockstock.repository.MineralRepository;
import org.piedere.rockstock.repository.PictureRepository;
import org.piedere.rockstock.service.dto.PictureDTO;
import org.piedere.rockstock.service.mapper.PictureMapper;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Default implementation of the PictureSearchService interface.
 * 
 * @author Pieter De Graef
 */
@Service
@Transactional
public class PictureSearchServiceImpl implements PictureSearchService {

	private final PictureRepository pictureRepository;

	private final MineralRepository mineralRepository;

	private final PictureMapper pictureMapper;

	@PersistenceContext
	EntityManager entityManager;

	public PictureSearchServiceImpl(PictureRepository pictureRepository, MineralRepository mineralRepository,
			PictureMapper pictureMapper) {
		this.pictureRepository = pictureRepository;
		this.pictureMapper = pictureMapper;
		this.mineralRepository = mineralRepository;
	}

	// ----------------------------------------------------------------------------------------------------------------
	// Picture retrieval methods:
	// ----------------------------------------------------------------------------------------------------------------

	@Override
	public List<PictureDTO> getPicturesForSpecimen(long specimenId, int maxHeight, int maxCount) throws IOException {
		Picture example = new Picture();
		Specimen specimen = new Specimen();
		specimen.setId(specimenId);
		example.setSpecimen(specimen);
		List<Picture> pictures = pictureRepository.findAll(Example.of(example));
		if (maxCount > 0 && maxCount < pictures.size()) {
			pictures = pictures.subList(0, maxCount);
		}

		return getScaledDtos(pictures, maxHeight);
	}

	@Override
	public List<PictureDTO> getPicturesForLocation(long locationId, int maxHeight, int maxCount) throws IOException {
		Picture example = new Picture();
		Location location = new Location();
		location.setId(locationId);
		example.setLocation(location);
		List<Picture> pictures = pictureRepository.findAll(Example.of(example));
		if (maxCount > 0 && maxCount < pictures.size()) {
			pictures = pictures.subList(0, maxCount);
		}

		return getScaledDtos(pictures, maxHeight);
	}

	@Override
	public List<PictureDTO> getPicturesForMineral(long mineralId, int maxHeight, int maxCount) throws IOException {
		Mineral example = new Mineral();
		example.setId(mineralId);
		List<Mineral> minerals = mineralRepository.findAll(Example.of(example));

		// Make a "list" of all specimen (getting rid of doubles):
		Map<Long, Specimen> specimens = new HashMap<Long, Specimen>();
		for (Mineral mineral : minerals) {
			for (Specimen specimen : mineral.getSpecimens()) {
				specimens.put(specimen.getId(), specimen);
			}
		}

		// Now get the pictures:
		List<PictureDTO> pictures = new ArrayList<PictureDTO>();
		for (Specimen specimen : specimens.values()) {
			pictures.addAll(getPicturesForSpecimen(specimen.getId(), maxHeight, 0));
		}
		if (maxCount > 0 && maxCount < pictures.size()) {
			getRandomSubList(pictures, maxCount);
		}
		if (maxHeight > 0) {
			for (PictureDTO picture : pictures) {
				scalePicture(picture, maxHeight);
			}
		}
		return pictures;
	}

	private void getRandomSubList(List<PictureDTO> pictures, int maxCount) {
		Random random = new Random();
		while (pictures.size() > maxCount) {
			int n = random.nextInt(pictures.size());
			pictures.remove(n);
		}
	}

	@Override
	public PictureDTO getRandomPicture() {
		// Session session = sessionFactory.openSession();
		Session session = getCurrentSession();
		Criteria criteria = session.createCriteria(Picture.class);
		criteria.add(Restrictions.eq("id", Long.valueOf(1)));
		criteria.add(Restrictions.sqlRestriction("1=1 order by rand()"));
		criteria.setMaxResults(1);
		List<Picture> pictures = criteria.list();
		if (pictures != null && pictures.size() > 0) {
			return pictureMapper.toDto(pictures.get(0));
		}
		return null;

//		Criterion restriction = yourRestrictions;
//		Object result = null;  // will later contain a random entity
//		Criteria crit = session.createCriteria(Picture.class);
//		crit.add(restriction);
//		crit.setProjection(Projections.rowCount());
//		int count = ((Number) crit.uniqueResult()).intValue();
//		if (0 != count) {
//		  int index = new Random().nextInt(count);
//		  crit = session.createCriteria(Picture.class);
//		  crit.add(restriction);
//		  result = crit.setFirstResult(index).setMaxResults(1).uniqueResult();
//		}
	}

	private Session getCurrentSession() {
		return entityManager.unwrap(Session.class);
	}

	private List<PictureDTO> getScaledDtos(List<Picture> pictures, int maxHeight) throws IOException {
		List<PictureDTO> dtos = pictureMapper.toDto(pictures);
		if (maxHeight > 0) {
			for (PictureDTO picture : dtos) {
				scalePicture(picture, maxHeight);
			}
		}
		return dtos;
	}

	private void scalePicture(PictureDTO picture, int targetHeight) throws IOException {
		if (picture != null && picture.getData() != null) {
			// Read image from byte array:
			byte[] content = picture.getData();
			BufferedImage sourceImg = ImageIO.read(new ByteArrayInputStream(content));
			if (sourceImg == null) {
				return;
			}

			// Scale the mofo:
			BufferedImage scaledImg = scaleImage(sourceImg, targetHeight);

			// Write out to byte array:
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ImageIO.write(scaledImg, "jpg", bos);
			picture.setData(bos.toByteArray());
			picture.setDataContentType("image/jpeg");
		}
	}

	private BufferedImage scaleImage(BufferedImage source, int targetHeight) {
		BufferedImage target = null;
		if (source != null) {
			double ratio = targetHeight / (double) source.getHeight();
			int width = (int) Math.round(source.getWidth() * ratio);
			target = new BufferedImage(width, targetHeight, source.getType());
			Graphics2D g = target.createGraphics();
			AffineTransform at = AffineTransform.getScaleInstance(ratio, ratio);
			g.drawRenderedImage(source, at);
		}
		return target;
	}
}
