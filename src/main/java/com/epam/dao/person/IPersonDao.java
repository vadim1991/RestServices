package com.epam.dao.person;

import com.epam.dao.GenericDao;
import com.epam.entity.Person;

/**
 * Created by Vadym_Vlasenko on 8/20/2015.
 */
public interface IPersonDao<T> extends GenericDao<T> {

    Person getPersonByUserName(String userName);

}
