package com.epam.service.generic;

import java.util.List;

/**
 * Created by Vadym_Vlasenko on 8/20/2015.
 */
public interface GenericManager<T> {

    public T findById(int id);

    public List<T> findAll();

    public void save(T entity);

    public void update(T entity);

    public void delete(T entity);

}
