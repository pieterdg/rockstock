package org.piedere.rockstock.service.home;

import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.piedere.rockstock.domain.Picture;
import org.piedere.rockstock.repository.PictureRepository;
import org.piedere.rockstock.service.dto.PictureDTO;
import org.piedere.rockstock.service.mapper.PictureMapper;

public class HomePictureServiceImpl implements HomePictureService {

	private final PictureRepository pictureRepository;

	private final PictureMapper pictureMapper;

	private final EntityManager em;

	public HomePictureServiceImpl(PictureRepository pictureRepository, EntityManager em, PictureMapper pictureMapper) {
		this.pictureRepository = pictureRepository;
		this.em = em;
		this.pictureMapper = pictureMapper;
	}

	@Override
	public PictureDTO getRandomPicture() {
		long count = pictureRepository.count();

		Random random = new Random();
		int number = random.nextInt((int) count);

		Query selectQuery = em.createQuery("select p from Picture p");
		selectQuery.setFirstResult(number);
		selectQuery.setMaxResults(1);
		Picture picture = (Picture) selectQuery.getSingleResult();
		return pictureMapper.toDto(picture);
	}
}
