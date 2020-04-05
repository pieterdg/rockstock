package org.piedere.rockstock.service.search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.piedere.rockstock.domain.NickelStrunzLevelOne;
import org.piedere.rockstock.domain.NickelStrunzLevelThree;
import org.piedere.rockstock.domain.NickelStrunzLevelTwo;
import org.piedere.rockstock.repository.NickelStrunzLevelOneRepository;
import org.piedere.rockstock.repository.NickelStrunzLevelThreeRepository;
import org.piedere.rockstock.repository.NickelStrunzLevelTwoRepository;
import org.piedere.rockstock.service.dto.NickelStrunzLevelThreeDTO;
import org.piedere.rockstock.service.dto.NickelStrunzLevelTwoDTO;
import org.piedere.rockstock.service.mapper.NickelStrunzLevelOneMapper;
import org.piedere.rockstock.service.mapper.NickelStrunzLevelThreeMapper;
import org.piedere.rockstock.service.mapper.NickelStrunzLevelTwoMapper;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SearchServiceImpl implements SearchService {

	private final NickelStrunzLevelOneRepository levelOneRepository;

	private final NickelStrunzLevelTwoRepository levelTwoRepository;

	private final NickelStrunzLevelThreeRepository levelThreeRepository;

	private final NickelStrunzLevelOneMapper nickelStrunzLevelOneMapper;

	private final NickelStrunzLevelTwoMapper nickelStrunzLevelTwoMapper;

	private final NickelStrunzLevelThreeMapper nickelStrunzLevelThreeMapper;

	public SearchServiceImpl(NickelStrunzLevelOneRepository levelOneRepository,
			NickelStrunzLevelTwoRepository levelTwoRepository, NickelStrunzLevelThreeRepository levelThreeRepository,
			NickelStrunzLevelOneMapper nickelStrunzLevelOneMapper,
			NickelStrunzLevelTwoMapper nickelStrunzLevelTwoMapper,
			NickelStrunzLevelThreeMapper nickelStrunzLevelThreeMapper) {
		this.levelOneRepository = levelOneRepository;
		this.levelTwoRepository = levelTwoRepository;
		this.levelThreeRepository = levelThreeRepository;
		this.nickelStrunzLevelOneMapper = nickelStrunzLevelOneMapper;
		this.nickelStrunzLevelTwoMapper = nickelStrunzLevelTwoMapper;
		this.nickelStrunzLevelThreeMapper = nickelStrunzLevelThreeMapper;
	}

	@Override
	public List<NickelStrunzLevelTwoDTO> getNickelStrunzLevelTwos(long levelOneId) {
		NickelStrunzLevelOne one = new NickelStrunzLevelOne();
		one.setId(levelOneId);
		NickelStrunzLevelTwo two = new NickelStrunzLevelTwo();
		two.setParent(one);
		List<NickelStrunzLevelTwo> twos = levelTwoRepository.findAll(Example.of(two));

		List<NickelStrunzLevelTwoDTO> dtos = new ArrayList<NickelStrunzLevelTwoDTO>(twos.size());
		for (NickelStrunzLevelTwo pojo : twos) {
			dtos.add(nickelStrunzLevelTwoMapper.toDto(pojo));
		}
		return dtos;
	}

	@Override
	public List<NickelStrunzLevelThreeDTO> getNickelStrunzLevelThrees(long levelTwoId) {
		NickelStrunzLevelTwo two = new NickelStrunzLevelTwo();
		two.setId(levelTwoId);
		NickelStrunzLevelThree three = new NickelStrunzLevelThree();
		three.setParent(two);
		List<NickelStrunzLevelThree> threes = levelThreeRepository.findAll(Example.of(three));

		List<NickelStrunzLevelThreeDTO> dtos = new ArrayList<NickelStrunzLevelThreeDTO>(threes.size());
		for (NickelStrunzLevelThree pojo : threes) {
			dtos.add(nickelStrunzLevelThreeMapper.toDto(pojo));
		}
		return dtos;
	}

	@Override
	public Map<String, Object> getHierarchy(Long nickelstrunzLevelThreeId) {
		Optional<NickelStrunzLevelThree> three = levelThreeRepository.findById(nickelstrunzLevelThreeId);
		if (!three.isEmpty()) {
			Optional<NickelStrunzLevelTwo> two = levelTwoRepository.findById(three.get().getParent().getId());
			Optional<NickelStrunzLevelOne> one = levelOneRepository.findById(two.get().getParent().getId());
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("levelOne", nickelStrunzLevelOneMapper.toDto(one.get()));
			map.put("levelTwo", nickelStrunzLevelTwoMapper.toDto(two.get()));
			map.put("levelThree", nickelStrunzLevelThreeMapper.toDto(three.get()));
			return map;
		}
		return null;
	}
}
