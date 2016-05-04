package com.excilys.computerdatabase.mapper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import com.excilys.computerdatabase.dto.ComputerDTO;
import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.model.Computer;

public class ComputerMapper {

    public static final String ATT_NAME = "computerName";
    public static final String ATT_COMPANY = "companyId";
    public static final String ATT_INTRODUCED = "introduced";
    public static final String ATT_DISCONTINUED = "discontinued";
    public static final String ATT_ID = "id";
    public static final String regex = "^[0-9]{4}-[0-1][0-9]-[0-3][0-9]$|^$";

    /**
     * Static method use to convert a DTO into the normal Computer POJO.
     * 
     * @param dto
     * @return Computer
     */
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

    /**
     * method use to convert a list of DTO into a list of Computer.
     * 
     * @param dtoList
     * @return
     */
    static List<Computer> toComputer(List<ComputerDTO> dtoList) {
        List<Computer> computerList = new ArrayList<>();

        if (dtoList != null) {
            for (ComputerDTO dto : dtoList) {
                computerList.add(toComputer(dto));
            }
        }
        return computerList;
    }

    /**
     * method which convert a POJO computer into a DTO.
     * 
     * @param computer
     * @return
     */
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

    /**
     * method which convert a list of POJO computer into a list of DTO.
     * 
     * @param listcompu
     * @return
     */

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

    /**
     * Method which transform date field into english format mm-dd-yyyy.
     * 
     * @param dto
     *            with international format for date in String
     * @return dto
     */
    public static ComputerDTO toEnDto(ComputerDTO dto) {
        dto.setIntroduced(DateMapper.toEnFormat(dto.getIntroduced()));
        dto.setDiscontinued(DateMapper.toEnFormat(dto.getDiscontinued()));
        return dto;
    }

    /**
     * Method which transform date field into format mm-dd-yyyy.
     * 
     * @param dto
     *            with international format for date in String
     * @return
     */
    public static ComputerDTO toFrDto(ComputerDTO dto) {
        dto.setIntroduced(DateMapper.toFrFormat(dto.getIntroduced()));
        dto.setDiscontinued(DateMapper.toFrFormat(dto.getDiscontinued()));
        return dto;
    }

    /**
     * Method which transform date field into format yyyy-mm-dd.
     * 
     * @param dto
     *            with french format for date in String
     * @return
     */
    public static ComputerDTO frToInDto(ComputerDTO dto) {
        dto.setIntroduced(DateMapper.frToInternFormat(dto.getIntroduced()));
        dto.setDiscontinued(DateMapper.frToInternFormat(dto.getDiscontinued()));
        return dto;
    }

    /**
     * Method which transform date field into format yyyy-mm-dd.
     * 
     * @param dto
     *            with english format for date in String
     * @return
     */
    public static ComputerDTO enToInDto(ComputerDTO dto) {
        dto.setIntroduced(DateMapper.enToInternFormat(dto.getIntroduced()));
        dto.setDiscontinued(DateMapper.enToInternFormat(dto.getDiscontinued()));
        return dto;
    }

    /**
     * which transform date field into format yyyy-mm-dd.
     * 
     * @param dto
     *            with string date field
     * @param lang
     * @return
     */
    public static ComputerDTO ToInternational(ComputerDTO dto, String lang) {
        if ("fr".equals(lang)) {
            return frToInDto(dto);
        } else {
            return enToInDto(dto);
        }
    }

    /**
     * which transform date field into specified format.
     * 
     * @param dto
     *            in international string format.
     * @param lang
     * @return
     */
    public static ComputerDTO ToLocale(ComputerDTO dto, String lang) {
        if ("fr".equals(lang)) {
            return toFrDto(dto);
        } else {
            return toEnDto(dto);
        }
    }

    public static Page<ComputerDTO> toDTO(Page<Computer> findAll, PageRequest pr) {
        List<Computer> list = findAll.getContent();
        PageImpl<ComputerDTO> pager = new PageImpl(toDTOs(list), pr, findAll.getTotalElements());
        return pager;
    }
}