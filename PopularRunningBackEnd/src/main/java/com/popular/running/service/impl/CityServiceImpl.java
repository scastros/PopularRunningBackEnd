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
public class CityServiceImpl extends BaseServiceImpl implements BaseService
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

    @Override
    public void save( Object city )
    {
    	cityDao.save( (City)city );
    }

    @Override
    public void merge( Object city )
    {
    	cityDao.merge( (City)city );
    }

    @Override
    public void remove( Object city )
    {
    	cityDao.remove( (City)city );
    }

    @Override
	public void flush() {
    	cityDao.flush();
	}

}
