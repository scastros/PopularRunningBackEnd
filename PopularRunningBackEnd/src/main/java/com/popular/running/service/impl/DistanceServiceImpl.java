package com.popular.running.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.googlecode.genericdao.search.Search;
import com.popular.running.dao.DistanceDAO;
import com.popular.running.model.Distance;
import com.popular.running.service.DistanceService;

/**
 * Implements the business methods for the Distance service
 * @author scastros
 */
@SuppressWarnings("rawtypes")
@Transactional
@TransactionConfiguration(defaultRollback=false)
@Service( "distanceService" )
public class DistanceServiceImpl extends BaseServiceImpl implements DistanceService
{
    @Autowired
    private DistanceDAO distanceDao;

    public Distance findById( long id )
    {
        return distanceDao.find( id );
    }

    public List<Distance> findAll()
    {
        return distanceDao.findAll();
    }

    public void save( Object distance )
    {
    	distanceDao.save( (Distance)distance );
    }

    public void merge( Object distance )
    {
    	distanceDao.merge( (Distance)distance );
    }

    public void remove( Object distance )
    {
    	distanceDao.remove( (Distance)distance );
    }

	public void flush() {
    	distanceDao.flush();
	}

    /**
     * This is a DAO method that returns all the distances with the given description.
     * If any parameter is null, that parameter is ignored. So only non-null parameters are used in defining the search criteria.
     */
	public List<Distance> findByName(String name) {
        return distanceDao.search(new Search(Distance.class).addFilterILike("description", name));
	}
    
    /**
     * Returns distances greater than given distance
     * @param distance
     * @return Distances that meet the criteria
     */
	public List<Distance> findDistancesGreatherThan(long distance) {
        return distanceDao.search(new Search(Distance.class).addFilterGreaterOrEqual("meters", distance));
	} 
	
    /**
     * Returns distances less than given distance
     * @param distance
     * @return Distances that meet the criteria
     */
	public List<Distance> findDistancesLessThan(long distance) {
        return distanceDao.search(new Search(Distance.class).addFilterLessOrEqual("meters", distance));
	} 	
    
}