package com.popular.running.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.popular.running.dao.StateDAO;
import com.popular.running.model.State;
import com.popular.running.service.BaseService;

/**
 * Implements the business methods for the RunningEvent service
 * @author scastros
 */
@SuppressWarnings("rawtypes")
@Transactional
@Service( "stateService" )
public abstract class StateServiceImpl extends BaseServiceImpl implements BaseService
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

    public void save( State state )
    {
    	stateDao.save( state );
    }

    public void merge( State state )
    {
    	stateDao.merge( state );
    }

    public void remove( State state )
    {
    	stateDao.remove( state );
    }

    @Override
	public void flush() {
    	stateDao.flush();
	}

}
