
package com.mycompany.ProjectManagementSystem.model.dao;

import java.util.List;

/**
 *
 * @author YARUS
 * @param <T>
 */
public interface AbstractDAO<T> {
    public abstract List<T> findAll();
    public abstract T find(int id);
    public abstract boolean delete(int id);
    public abstract T create(T entity);
    public abstract T update(T entity);
}
