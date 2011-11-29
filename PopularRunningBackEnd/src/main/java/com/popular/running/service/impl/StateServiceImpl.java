package com.popular.running.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.googlecode.genericdao.search.Search;
import com.popular.running.dao.StateDAO;
import com.popular.running.model.State;
import com.popular.running.service.StateService;

/**
 * Implements the business methods for the RunningEvent service
 * @author scastros
 */
@SuppressWarnings("rawtypes")
@Transactional
@TransactionConfiguration(defaultRollback=false)
@Service( "stateService" )
public class StateServiceImpl extends BaseServiceImpl implements StateService
{
    @Autowired
    private StateDAO stateDao;

    @Override
    public State findById( long id )
    {
        return stateDao.find( id );
    }

    @Override
    public List<State> findAll()
    {
        return stateDao.findAll();
    }

    @Override
    public void save( Object state )
    {
    	stateDao.save( (State)state );
    }

    @Override
    public void merge( Object state )
    {
    	stateDao.merge( (State)state );
    }

    @Override
    public void remove( Object state )
    {
    	stateDao.remove( (State)state );
    }

    @Override
	public void flush() {
    	stateDao.flush();
	}

    /**
     * This is a DAO method that returns all the states with the given description.
     * If any parameter is null, that parameter is ignored. So only non-null parameters are used in defining the search criteria.
     */
    @Override
	public List<State> findByName(String name) {
        return stateDao.search(new Search(State.class).addFilterILike("description", name));
	}
}
