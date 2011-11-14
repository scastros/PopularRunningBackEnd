package com.popular.running.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.popular.running.dao.CityDAO;
import com.popular.running.model.City;
import com.popular.running.service.BaseService;

/**
 * Implements the business methods for the City service
 * @author scastros
 */
@SuppressWarnings("rawtypes")
@Transactional
@Service( "cityService" )
public abstract class CityServiceImpl extends BaseServiceImpl implements BaseService
{
    @Autowired
    private CityDAO cityDao;

    @Override
    public City findById( long id )
    {
        return cityDao.find( id );
    }

    @Override
    public List<City> findAll()
    {
        return cityDao.findAll();
    }

    public void save( City city )
    {
    	cityDao.save( city );
    }

    public void merge( City city )
    {
    	cityDao.merge( city );
    }

    public void remove( City city )
    {
    	cityDao.remove( city );
    }

    @Override
	public void flush() {
    	cityDao.flush();
	}

}
