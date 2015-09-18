package com.epam.service.user;

import com.epam.dao.GenericDao;
import com.epam.dao.user.UserDAO;
import com.epam.entity.GoogleProfileBean;
import com.epam.entity.Role;
import com.epam.entity.User;
import com.epam.service.generic.GenericManagerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.HashSet;

/**
 * Created by Vadym_Vlasenko on 9/8/2015.
 */
@Service
public class UserService extends GenericManagerImpl<User, UserDAO> implements IUserService<User> {

    private final Role role = new Role("ROLE_USER");

    @Autowired
    @Qualifier("userDAO")
    @Override
    public void setDao(GenericDao dao) {
        super.setDao(dao);
    }

    @Override
    public User getUserByUserName(String userName) {
        return dao.getUserByUserName(userName);
    }

    @Override
    public User getUserByQuery(Query query) {
        return dao.getUserByQuery(query);
    }

    @Override
    public User getUserByGoogleID(String id) {
        return dao.getUserByGoogleID(id);
    }

    @Override
    public User getUserByVKID(String id) {
        return null;
    }

    @Override
    public User createNewGoogleUser(GoogleProfileBean googleProfileBean) {
        User user = new User();
        HashSet<Role> roles = new HashSet<>();
        roles.add(role);
        user.setGoogleProfileBean(googleProfileBean);
        user.setRoles(roles);
        dao.save(user);
        return user;
    }

}
