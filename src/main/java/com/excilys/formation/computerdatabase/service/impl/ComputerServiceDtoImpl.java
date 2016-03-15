package com.excilys.formation.computerdatabase.service.impl;

import java.util.List;

import com.excilys.formation.computerdatabase.model.dto.ComputerDTO;
import com.excilys.formation.computerdatabase.persist.dao.impl.ComputerDaoDtoImpl;
import com.excilys.formation.computerdatabase.persist.dao.impl.ComputerDaoImpl;
import com.excilys.formation.computerdatabase.service.GenericService;

public class ComputerServiceDtoImpl implements GenericService<ComputerDTO> {

	private static ComputerServiceDtoImpl instance;
	private ComputerDaoImpl computerDao = ComputerDaoImpl.INSTANCE;
	private ComputerDaoDtoImpl computerDtoDao = ComputerDaoDtoImpl.getComputerDTOImpl();
	
	private ComputerServiceDtoImpl() {
	}

	public static GenericService<ComputerDTO> getInstance() {
		if (instance == null) {
			instance = new ComputerServiceDtoImpl();
		}
		return instance;
	}

	@Override
	public int count() {
		return computerDao.count();
	}

	@Override
	public List<ComputerDTO> findAll(Long index, int nbrElement) {
		if (index < 0 || nbrElement <= 0) {
			return null;
		}
		return computerDtoDao.findAll(index, nbrElement);
	}

}
