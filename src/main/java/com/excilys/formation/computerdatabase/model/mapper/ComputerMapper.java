package com.excilys.formation.computerdatabase.model.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.jdbc.core.RowMapper;

import com.excilys.formation.computerdatabase.model.Company;
import com.excilys.formation.computerdatabase.model.Computer;
import com.excilys.formation.computerdatabase.model.dto.ComputerDTO;

public class ComputerMapper implements RowMapper<Computer> {

    public static final String ATT_NAME = "computerName";
    public static final String ATT_COMPANY = "companyId";
    public static final String ATT_INTRODUCED = "introduced";
    public static final String ATT_DISCONTINUED = "discontinued";
    public static final String ATT_ID = "id";
    public static final String regex = "^[0-9]{4}-[0-1][0-9]-[0-3][0-9]$|^$";

    public static Computer toComputer(ResultSet rs) throws SQLException {
	Computer computer = new Computer();
	computer.setId(rs.getInt("computer.id"));
	computer.setName(rs.getString("computer.name"));
	if (rs.getTimestamp("introduced") != null) {
	    computer.setIntroduced(rs.getTimestamp("introduced").toLocalDateTime().toLocalDate());
	}
	if (rs.getTimestamp("discontinued") != null) {
	    computer.setDiscontinued(rs.getTimestamp("discontinued").toLocalDateTime().toLocalDate());
	}
	Company company = new Company();
	if (rs.getLong("company.id") != 0) {
	    company.setId(rs.getLong("company.id"));
	    company.setName(rs.getString("company.name"));
	}
	computer.setCompany(company);
	return computer;
    }

    public static List<Computer> toComputers(ResultSet rs) throws SQLException {

	List<Computer> list = new ArrayList<>();
	Computer computer;
	while (rs.next()) {
	    computer = new Computer();
	    computer.setId(rs.getInt("computer.id"));
	    computer.setName(rs.getString("computer.name"));
	    if (rs.getTimestamp("introduced") != null) {
		computer.setIntroduced(rs.getTimestamp("introduced").toLocalDateTime().toLocalDate());
	    }
	    if (rs.getTimestamp("discontinued") != null) {
		computer.setDiscontinued(rs.getTimestamp("discontinued").toLocalDateTime().toLocalDate());
	    }
	    Company company = new Company();
	    company.setId(rs.getLong("company.id"));
	    company.setName(rs.getString("company.name"));
	    computer.setCompany(company);
	    list.add(computer);
	}
	return list;
    }

    public static Computer toComputer(ComputerDTO dto) {

	Computer computer = new Computer();

	if (dto.getId() != 0) {
	    computer.setId(dto.getId());
	}
	computer.setName(dto.getName());

	if (dto.getIntroduced() == null || "".equals(dto.getIntroduced())) {
	    computer.setIntroduced(null);
	} else {
	    dto.setIntroduced(dto.getIntroduced().replace('/', '-'));
	    computer.setIntroduced(LocalDate.parse(dto.getIntroduced(), DateTimeFormatter.ISO_LOCAL_DATE));
	}

	if (dto.getDiscontinued() == null || "".equals(dto.getDiscontinued())) {
	    computer.setDiscontinued(null);
	} else {
	    dto.setDiscontinued(dto.getDiscontinued().replace('/', '-'));
	    computer.setDiscontinued(LocalDate.parse(dto.getDiscontinued(), DateTimeFormatter.ISO_LOCAL_DATE));
	}

	if (dto.getCompanyId() != 0) {
	    computer.setCompany(new Company(dto.getCompanyName(), dto.getCompanyId()));
	} else {
	    computer.setCompany(null);
	}
	return computer;
    }

    static List<Computer> toComputer(List<ComputerDTO> dtoList) {
	List<Computer> computerList = new ArrayList<>();

	if (dtoList != null) {
	    for (ComputerDTO dto : dtoList) {
		computerList.add(toComputer(dto));
	    }
	}
	return computerList;
    }

    public static ComputerDTO toDTO(Computer computer) {

	ComputerDTO dto = new ComputerDTO();
	dto.setId(computer.getId());
	dto.setName(computer.getName());
	if (computer.getIntroduced() == null) {
	    dto.setIntroduced("");
	} else {
	    dto.setIntroduced(computer.getIntroduced().toString());
	}

	if (computer.getDiscontinued() == null) {
	    dto.setDiscontinued("");
	} else {
	    dto.setDiscontinued(computer.getDiscontinued().toString());
	}

	if (computer.getCompany() == null) {
	    dto.setCompanyId(0L);
	    dto.setCompanyName("");
	} else {
	    dto.setCompanyId(computer.getCompany().getId());

	    if (computer.getCompany().getName() == null || "".equals(computer.getCompany().getName())) {
		dto.setCompanyName("");
	    } else {
		dto.setCompanyName(computer.getCompany().getName());
	    }
	}
	return dto;
    }

    public static List<ComputerDTO> toDTOs(List<Computer> listcompu) {
	ComputerDTO dto;
	List<ComputerDTO> list = new ArrayList<>();
	for (Computer computer : listcompu) {
	    dto = new ComputerDTO();

	    dto.setId(computer.getId());

	    dto.setName(computer.getName());
	    if (computer.getIntroduced() == null) {
		dto.setIntroduced("");
	    } else {
		dto.setIntroduced(computer.getIntroduced().toString());
	    }

	    if (computer.getDiscontinued() == null) {
		dto.setDiscontinued("");
	    } else {
		dto.setDiscontinued(computer.getDiscontinued().toString());
	    }

	    if (computer.getCompany() == null) {
		dto.setCompanyId(0L);
		dto.setCompanyName("");
	    } else {
		dto.setCompanyId(computer.getCompany().getId());

		if (computer.getCompany().getName() == null || "".equals(computer.getCompany().getName())) {
		    dto.setCompanyName("");
		} else {
		    dto.setCompanyName(computer.getCompany().getName());
		}
	    }
	    list.add(dto);
	}
	return list;
    }

    public static Computer toComputer(HttpServletRequest request) {
	Map<String, String> erreur = new HashMap<>();
	String name = request.getParameter(ATT_NAME);
	String introduced = request.getParameter(ATT_INTRODUCED);
	String discontinued = request.getParameter(ATT_DISCONTINUED);
	String companyId = request.getParameter(ATT_COMPANY);
	String id = request.getParameter(ATT_ID);
	long id2 = 0;
	if (id != null) {
	    id2 = Long.valueOf(id);
	}
	if (name == null) {
	    return null;
	}
	if (!Pattern.matches(regex, introduced) && (introduced != "")) {
	    erreur.put("introduced", "Erreur de format, renseigner YYYY-MM-JJ");
	}
	if (!Pattern.matches(regex, discontinued) && discontinued != "") {
	    erreur.put("discontinued", "Erreur de format, renseigner YYYY-MM-JJ");
	}
	Company company = null;
	if (companyId != null && !companyId.isEmpty() && Integer.valueOf(companyId) != 0) {
	    company = new Company();
	    company.setId(Long.valueOf(companyId));
	}
	Computer computer = null;
	if (erreur.isEmpty()) {
	    LocalDate intro = null;
	    LocalDate disco = null;
	    if (introduced != null && introduced != "") {
		intro = LocalDate.parse(introduced);
	    }
	    if (discontinued != null && discontinued != "") {
		disco = LocalDate.parse(discontinued);
	    }
	    computer = new Computer(id2, name, intro, disco, company);
	}
	return computer;
    }

    static List<ComputerDTO> toDTO(List<Computer> computerList) {
	List<ComputerDTO> computerDTOList = new ArrayList<>();
	for (Computer computer : computerList) {
	    computerDTOList.add(toDTO(computer));
	}
	return computerDTOList;
    }

    @Override
    public Computer mapRow(ResultSet rs, int arg1) throws SQLException {
	Computer computer = new Computer();
	computer.setId(rs.getInt("computer.id"));
	computer.setName(rs.getString("computer.name"));
	if (rs.getTimestamp("introduced") != null) {
	    computer.setIntroduced(rs.getTimestamp("introduced").toLocalDateTime().toLocalDate());
	}
	if (rs.getTimestamp("discontinued") != null) {
	    computer.setDiscontinued(rs.getTimestamp("discontinued").toLocalDateTime().toLocalDate());
	}
	Company company = new Company();
	if (rs.getLong("company.id") != 0) {
	    company.setId(rs.getLong("company.id"));
	    company.setName(rs.getString("company.name"));
	}
	computer.setCompany(company);
	return computer;
    }

    public static Computer toComputer(Map<String, String> param) {
	String name = param.get(ATT_NAME);
	String introduced = param.get(ATT_INTRODUCED);
	String discontinued = param.get(ATT_DISCONTINUED);
	String companyId = param.get(ATT_COMPANY);
	String id = param.get(ATT_ID);
	long id2 = 0;
	if (id != null) {
	    id2 = Long.valueOf(id);
	}
	if (name == null) {
	    return null;
	}
	Company company = null;
	if (companyId != null && !companyId.isEmpty() && Integer.valueOf(companyId) != 0) {
	    company = new Company();
	    company.setId(Long.valueOf(companyId));
	}
	Computer computer = null;
	if (!Pattern.matches(regex, discontinued) && discontinued != "" && !Pattern.matches(regex, introduced)
		&& (introduced != "")) {
	    LocalDate intro = null;
	    LocalDate disco = null;
	    if (introduced != null && introduced != "") {
		intro = LocalDate.parse(introduced);
	    }
	    if (discontinued != null && discontinued != "") {
		disco = LocalDate.parse(discontinued);
	    }
	    computer = new Computer(id2, name, intro, disco, company);
	}
	return computer;
    }

    public static ComputerDTO toEnDto(ComputerDTO dto) {
	dto.setIntroduced(DateMapper.toEnFormat(dto.getIntroduced()));
	dto.setDiscontinued(DateMapper.toEnFormat(dto.getDiscontinued()));
	return dto;
    }

    public static ComputerDTO toFrDto(ComputerDTO dto) {
	dto.setIntroduced(DateMapper.toFrFormat(dto.getIntroduced()));
	dto.setDiscontinued(DateMapper.toFrFormat(dto.getDiscontinued()));
	return dto;
    }

    public static ComputerDTO frToInDto(ComputerDTO dto) {
	dto.setIntroduced(DateMapper.frToInternFormat(dto.getIntroduced()));
	dto.setDiscontinued(DateMapper.frToInternFormat(dto.getDiscontinued()));
	return dto;
    }

    public static ComputerDTO enToInDto(ComputerDTO dto) {
	dto.setIntroduced(DateMapper.enToInternFormat(dto.getIntroduced()));
	dto.setDiscontinued(DateMapper.enToInternFormat(dto.getDiscontinued()));
	return dto;
    }

    public static ComputerDTO ToInternational(ComputerDTO dto, String lang) {
	if ("fr".equals(lang)) {
	    return frToInDto(dto);
	} else {
	    return enToInDto(dto);
	}
    }

    public static ComputerDTO ToLocale(ComputerDTO dto, String lang) {
	if ("fr".equals(lang)) {
	    return toFrDto(dto);
	} else {
	    return toEnDto(dto);
	}
    }

    public static Page<ComputerDTO> toDTO(Page<Computer> findAll, PageRequest pr) {
	List<Computer> list = findAll.getContent();
	PageImpl<ComputerDTO> pager = new PageImpl(toDTO(list), pr, findAll.getTotalElements());
	return pager;
    }
}