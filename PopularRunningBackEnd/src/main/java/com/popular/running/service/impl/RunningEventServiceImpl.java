package com.popular.running.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.popular.running.dao.RunningEventDAO;
import com.popular.running.model.RunningEvent;
import com.popular.running.service.BaseService;

/**
 * Implements the business methods for the RunningEvent service
 * @author scastros
 */
@SuppressWarnings("rawtypes")
@Transactional
@Service( "runningEventService" )
public abstract class RunningEventServiceImpl extends BaseServiceImpl implements BaseService
{
    @Autowired
    private RunningEventDAO runningEventDao;

    @Override
    public RunningEvent findById( long id )
    {
        return runningEventDao.find( id );
    }

    @Override
    public List<RunningEvent> findAll()
    {
        return runningEventDao.findAll();
    }

    public void save( RunningEvent runningEvent )
    {
        runningEventDao.save( runningEvent );
    }

    public void merge( RunningEvent runningEvent )
    {
        runningEventDao.merge( runningEvent );
    }

    public void remove( RunningEvent runningEvent )
    {
        runningEventDao.remove( runningEvent );
    }

    @Override
	public void flush() {
		runningEventDao.flush();
	}

}
