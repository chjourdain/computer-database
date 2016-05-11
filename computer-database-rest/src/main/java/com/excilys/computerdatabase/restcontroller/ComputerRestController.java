package com.excilys.computerdatabase.restcontroller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.excilys.computerdatabase.dto.ComputerDTO;
import com.excilys.computerdatabase.mapper.ComputerMapper;
import com.excilys.computerdatabase.service.impl.ComputerServiceImpl;

@RestController
@RequestMapping("/rest/computer")
public class ComputerRestController {
    @Autowired
    ComputerServiceImpl service;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<ComputerDTO>> get() {
        PageRequest page = new PageRequest(1, 10);
        Page<ComputerDTO> pager = ComputerMapper.toDTO(service.findAll(page, null), page);
        return new ResponseEntity<List<ComputerDTO>>(pager.getContent(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{pageNumber}/{number}", method = RequestMethod.GET)
    public ResponseEntity<List<ComputerDTO>> getPage(@PathVariable int pageNumber, @PathVariable int number) {
        PageRequest page = new PageRequest(pageNumber, number);
        Page<ComputerDTO> pager = ComputerMapper.toDTO(service.findAll(page, null), page);
        return new ResponseEntity<List<ComputerDTO>>(pager.getContent(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<ComputerDTO> get(@PathVariable Long id) {
        return new ResponseEntity<ComputerDTO>(ComputerMapper.toDTO(service.find(id)), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<ComputerDTO> create(@RequestBody ComputerDTO dto) {
        service.create(ComputerMapper.toComputer(dto));
        return new ResponseEntity<ComputerDTO>(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<ComputerDTO> update(@RequestBody ComputerDTO dto) {
        service.update(ComputerMapper.toComputer(dto));
        return new ResponseEntity<ComputerDTO>(HttpStatus.OK);
    }
}