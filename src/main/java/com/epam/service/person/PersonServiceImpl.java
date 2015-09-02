package com.epam.service.person;

import com.epam.dao.mongodb.person.PersonMongoDao;
import com.epam.entity.Person;
import com.epam.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * Created by Vadym_Vlasenko on 8/30/2015.
 */
@Service("userDetailsService")
public class PersonServiceImpl implements UserDetailsService {

    @Autowired
    private PersonMongoDao personMongoDao;

    @Override
    public UserDetails loadUserByUsername(final String username)
            throws UsernameNotFoundException {

        Person person = personMongoDao.getPersonByUserName(username);
        List<GrantedAuthority> authorities =
                buildUserAuthority(person.getUserRole());

        return buildUserForAuthentication(person, authorities);

    }

    // Converts com.mkyong.users.model.User user to
    // org.springframework.security.core.userdetails.User
    private User buildUserForAuthentication(Person person,
                                            List<GrantedAuthority> authorities) {
        return new User(person.getEmail(), person.getPassword(),
                person.isEnable(), true, true, true, authorities);
    }

    private List<GrantedAuthority> buildUserAuthority(Set<Role> userRoles) {

        Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();
        System.out.println("Method build " + userRoles);
        // Build user's authorities
        for (Role userRole : userRoles) {
            setAuths.add(new SimpleGrantedAuthority(userRole.getRole()));
        }

        List<GrantedAuthority> result = new ArrayList<GrantedAuthority>(setAuths);

        return result;
    }

}
