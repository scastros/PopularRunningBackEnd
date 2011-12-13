package com.popular.running.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.googlecode.genericdao.search.Search;
import com.popular.running.dao.CityDAO;
import com.popular.running.model.City;
import com.popular.running.model.State;
import com.popular.running.service.CityService;

/**
 * Implements the business methods for the City service
 * @author scastros
 */
@SuppressWarnings("rawtypes")
@Transactional
@TransactionConfiguration(defaultRollback=false)
@Service( "cityService" )
public class CityServiceImpl extends BaseServiceImpl implements CityService
{
    @Autowired
    private CityDAO cityDao;

    public City findById( long id )
    {
        return cityDao.find( id );
    }

    public List<City> findAll()
    {
        return cityDao.findAll();
    }

    public void save( Object city )
    {
    	cityDao.save( (City)city );
    }

    public void merge( Object city )
    {
    	cityDao.merge( (City)city );
    }

    public void remove( Object city )
    {
    	cityDao.remove( (City)city );
    }

	public void flush() {
    	cityDao.flush();
	}
    
    /**
     * This is a DAO method that returns all the cities with the given description.
     * If any parameter is null, that parameter is ignored. 
     * So only non-null parameters are used in defining the search criteria.
     */
	public List<City> findByName(String name) {
    	if (name != null) name = "%"+name+"%"; // For like DB searches
        return cityDao.search(new Search(City.class).addFilterILike("description", name));
	}
    
    /**
     * Returns Cities in given state
     * @param stateId
     * @return
     */
	public List<City> findCitiesInState(long stateId) {
		State theState = new State();
		theState.setId(stateId);
        return cityDao.search(new Search(City.class).addFilterEqual("state", theState));
	}    
}