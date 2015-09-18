package com.epam.dao.user;

import com.epam.dao.mongodb.GenericMongoDBDaoImpl;
import com.epam.datamodel.CommonConstants;
import com.epam.entity.User;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

/**
 * Created by Vadym_Vlasenko on 9/8/2015.
 */
@Repository("userDAO")
public class UserDAO extends GenericMongoDBDaoImpl<User> implements IUserDAO<User> {

    public UserDAO() {
        setClazz(User.class);
    }

    @Override
    public User getUserByUserName(String userName) {
        Query query = new Query(Criteria.where(CommonConstants.USER_NAME).is(userName));
        return getMongoOperations().findOne(query, getClazz());
    }

    @Override
    public User getUserByQuery(Query query) {
        return getMongoOperations().findOne(query, getClazz());
    }

    @Override
    public User getUserByGoogleID(String id) {
        Query query = new Query(Criteria.where("googleProfileBean.id").is(id));
        return getMongoOperations().findOne(query, User.class);
    }

    @Override
    public User getUserByVKID(String id) {
        return null;
    }

}
