package com.popular.running.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.popular.running.dao.DistanceDAO;
import com.popular.running.model.Distance;
import com.popular.running.service.BaseService;

/**
 * Implements the business methods for the Distance service
 * @author scastros
 */
@SuppressWarnings("rawtypes")
@Transactional
@Service( "distanceService" )
public abstract class DistanceServiceImpl extends BaseServiceImpl implements BaseService
{
    @Autowired
    private DistanceDAO distanceDao;

    @Override
    public Distance findById( long id )
    {
        return distanceDao.find( id );
    }

    @Override
    public List<Distance> findAll()
    {
        return distanceDao.findAll();
    }

    public void save( Distance distance )
    {
    	distanceDao.save( distance );
    }

    public void merge( Distance distance )
    {
    	distanceDao.merge( distance );
    }

    public void remove( Distance distance )
    {
    	distanceDao.remove( distance );
    }

    @Override
	public void flush() {
    	distanceDao.flush();
	}

}
