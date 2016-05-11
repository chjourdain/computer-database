package com.excilys.computerdatabase.service.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.persist.dao.CompanyDao;
import com.excilys.computerdatabase.persist.dao.ComputerDao;
import com.excilys.computerdatabase.service.CompanyService;

@Service
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    CompanyDao companyDao;
    @Autowired
    ComputerDao computerDao;
    private static Map<Long, String> map;

    public Company findByName(String companyName) {
        if (companyName == null || companyName.isEmpty()) {
            return null;
        }
        return companyDao.findByName(companyName);
    }

    @Override
    public long count() {
        return companyDao.count();
    }

    @Override
    @Cacheable("findCompanyCache")
    public Company find(long id) {
        if (id == 0) {
            return null;
        }
        return companyDao.findOne(id);
    }

    /**
     * Method which return a hashmap with <company id, company name>
     * 
     * @return
     */
    @Cacheable("companyCache")
    public Map<Long, String> getMap() {
        if (map == null) {
            map = new HashMap<>();
            Iterator<Company> iter = companyDao.findAll().iterator();
            while (iter.hasNext()) {
                Company company = iter.next();
                map.put((Long) company.getId(), company.getName());
            }
        }
        return map;
    }

    @Override
    @CacheEvict(value = { "companyCache", "findCompanyCache" }, allEntries = true)
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void delete(Company c) {
        computerDao.deleteByCompany_id(c.getId());
        companyDao.delete(c);
    }
}
