package com.excilys.computerdatabase.persist.dao;

import org.springframework.data.repository.CrudRepository;
import com.excilys.computerdatabase.model.User;

public interface UserDao extends CrudRepository<User, String>  {

}
