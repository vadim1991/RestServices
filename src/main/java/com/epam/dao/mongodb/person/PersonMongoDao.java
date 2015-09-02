package com.epam.dao.mongodb.person;

import com.epam.dao.mongodb.GenericMongoDBDaoImpl;
import com.epam.dao.person.IPersonDao;
import com.epam.entity.Person;
import org.springframework.context.annotation.Scope;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

/**
 * Created by Vadym_Vlasenko on 8/28/2015.
 */
@Repository
public class PersonMongoDao extends GenericMongoDBDaoImpl<Person> implements IPersonDao<Person> {

    public PersonMongoDao() {
        setClazz(Person.class);
    }

    @Override
    public Person getPersonByUserName(String userName) {
        Query query = new Query(Criteria.where("email").is(userName));
        return getMongoOperations().findOne(query, getClazz());
    }

}

