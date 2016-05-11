package com.excilys.computerdatabase.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.excilys.computerdatabase.model.User;
import com.excilys.computerdatabase.model.roles.UserRole;
import com.excilys.computerdatabase.persist.dao.UserDao;

@Service("userDetailsService")
public class myUserDetailsService implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Override
    @Cacheable("user")
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userDao.findOne(login);
        return buildUserForAuthentication(user, buildUserAuthority(user.getUserRole()));
    }

    private org.springframework.security.core.userdetails.User buildUserForAuthentication(User user,
            List<GrantedAuthority> authorities) {
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                authorities);
    }

    private List<GrantedAuthority> buildUserAuthority(UserRole userRole) {
        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
        grantedAuthorityList.add(new SimpleGrantedAuthority(userRole.toString()));
        return grantedAuthorityList;
    }
}