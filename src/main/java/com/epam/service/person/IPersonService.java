package com.epam.service.person;

import com.epam.entity.Person;
import com.epam.service.generic.GenericManager;

/**
 * Created by Vadym_Vlasenko on 8/28/2015.
 */
public interface IPersonService<T> extends GenericManager<T> {

    Person getPersonByUserName(String userName);

}
