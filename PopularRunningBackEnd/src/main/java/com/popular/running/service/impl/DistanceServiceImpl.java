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
public class DistanceServiceImpl extends BaseServiceImpl implements BaseService
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

    @Override
    public void save( Object distance )
    {
    	distanceDao.save( (Distance)distance );
    }

    @Override
    public void merge( Object distance )
    {
    	distanceDao.merge( (Distance)distance );
    }

    @Override
    public void remove( Object distance )
    {
    	distanceDao.remove( (Distance)distance );
    }

    @Override
	public void flush() {
    	distanceDao.flush();
	}

}
