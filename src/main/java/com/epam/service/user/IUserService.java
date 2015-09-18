package com.epam.service.user;

import com.epam.entity.GoogleProfileBean;
import com.epam.entity.User;
import com.epam.service.generic.GenericManager;
import org.springframework.data.mongodb.core.query.Query;

import java.io.Serializable;

/**
 * Created by Vadym_Vlasenko on 9/8/2015.
 */
public interface IUserService<T> extends GenericManager<T> {

    User getUserByUserName(String userName);

    User getUserByQuery(Query query);

    User getUserByGoogleID(String id);

    User getUserByVKID(String id);

    User createNewGoogleUser(GoogleProfileBean googleProfileBean);

}
