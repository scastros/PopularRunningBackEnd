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
public class RunningEventServiceImpl extends BaseServiceImpl implements BaseService
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

    @Override
    public void save( Object runningEvent )
    {
        runningEventDao.save( (RunningEvent)runningEvent );
    }

    @Override
    public void merge( Object runningEvent )
    {
        runningEventDao.merge( (RunningEvent)runningEvent );
    }

    @Override
    public void remove( Object runningEvent )
    {
        runningEventDao.remove( (RunningEvent)runningEvent );
    }

    @Override
	public void flush() {
		runningEventDao.flush();
	}

}
