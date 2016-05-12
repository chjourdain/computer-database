package com.excilys.computerdatabase.restcontroller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.service.impl.CompanyServiceImpl;

@RestController
@RequestMapping("/rest/company")
public class CompanyRestController {

    @Autowired
    CompanyServiceImpl service;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Company>> get() {
        PageRequest page = new PageRequest(1, 10);
        Page<Company> pager = service.findAll(page);
        return new ResponseEntity<List<Company>>(pager.getContent(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{pageNumber}/{number}", method = RequestMethod.GET)
    public ResponseEntity<List<Company>> getPage(@PathVariable int pageNumber, @PathVariable int number) {
        PageRequest page = new PageRequest(pageNumber, number);
        Page<Company> pager = service.findAll(page);
        return new ResponseEntity<List<Company>>(pager.getContent(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Company> delete(@PathVariable Long id) {
        Company company = service.find(id);
        service.delete(company);
        return new ResponseEntity<Company>(HttpStatus.OK);
    }
}
