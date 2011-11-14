package com.popular.running.service;

import java.util.List;

/**
 * Base Interface for Services
 * 
 * @author scastros
 *
 * @param <T>
 */
public interface BaseService <T extends Object>{
    public T findById( long id );
    public List<T> findAll();
    public void save( T object );
    public void merge( T object );
    public void remove( T object );
    public void shutdown();
	public void flush();
}
