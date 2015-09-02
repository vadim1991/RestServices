package com.epam.service.generic;

import com.epam.dao.GenericDao;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Vadym_Vlasenko on 8/20/2015.
 */
public class GenericManagerImpl<T, D extends GenericDao<T>> implements
        GenericManager<T> {

    protected D dao;

    public void setDao(GenericDao dao) {
        this.dao = (D) dao;
    }

    public T findById(int id) {
        return dao.findById(id);
    }

    public List<T> findAll() {
        return dao.findAll();
    }

    public void save(T entity) {
        dao.save(entity);
    }

    public void update(T entity) {
        dao.update(entity);
    }

    public void delete(T entity) {
        dao.delete(entity);
    }

}
