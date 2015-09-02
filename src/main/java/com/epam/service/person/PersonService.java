package com.epam.service.person;

import com.epam.dao.GenericDao;
import com.epam.dao.mongodb.person.PersonMongoDao;
import com.epam.entity.Person;
import com.epam.service.generic.GenericManagerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Created by Vadym_Vlasenko on 8/20/2015.
 */
@Service
public class PersonService extends GenericManagerImpl<Person, PersonMongoDao> implements IPersonService<Person> {

    @Autowired
    @Qualifier("personMongoDao")
    @Override
    public void setDao(GenericDao dao) {
        super.setDao(dao);
    }

    @Override
    public Person getPersonByUserName(String userName) {
        return dao.getPersonByUserName(userName);
    }

}
