package com.popular.running.service.impl;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.time.DateUtils;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.googlecode.genericdao.search.Search;
import com.popular.running.dao.CityDAO;
import com.popular.running.dao.RunningEventDAO;
import com.popular.running.model.City;
import com.popular.running.model.RunningEvent;
import com.popular.running.service.RunningEventService;
import com.popular.running.utils.SwissArmyKnife;

/**
 * Implements the business methods for the RunningEvent service
 * @author scastros
 */
@SuppressWarnings("rawtypes")
@Transactional(readOnly = false, propagation=Propagation.REQUIRES_NEW)
@TransactionConfiguration(defaultRollback=false)
@Service( "runningEventService" )
public class RunningEventServiceImpl extends BaseServiceImpl implements RunningEventService
{
    @Autowired
    private RunningEventDAO runningEventDao;
    
    @Autowired
    private CityDAO cityDao;
    
    Transaction transaction;

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
    @Transactional
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

    /**
     * This is a DAO method that returns all the runningEvents with the given description.
     * If any parameter is null, that parameter is ignored. So only non-null parameters are used in defining the search criteria.
     */
    @Override
	public List<RunningEvent> findByName(String name) {
        return runningEventDao.search(new Search(RunningEvent.class).addFilterILike("description", name));
	}

    /**
     * Returns RunningEvent filtered by City
     * 
     * @param cityId
     * @return The RunningEvents
     */
    @Override
	public List<RunningEvent> findByCity(long cityId) {
        return runningEventDao.search(new Search(RunningEvent.class).addFilterEqual("location", cityId));
	}
    
    /**
     * Returns RunningEvent filtered by State
     * 
     * @param stateId
     * @return The RunningEvents
     */
    @Override
    public List<RunningEvent> findByState(long stateId) {
    	List<City> citiesInState = cityDao.search(new Search(City.class).addFilterEqual("state", stateId));
    	return runningEventDao.search(new Search(RunningEvent.class).addFilterIn("location", citiesInState));
    }
    
    /**
     * Returns RunningEvent filtered by Distance
     * 
     * @param distanceId
     * @return The RunningEvents
     */
    @Override
    public List<RunningEvent> findByDistance(long distanceId) {
    	return runningEventDao.search(new Search(RunningEvent.class).addFilterEqual("distance", distanceId));
    }

    /**
     * Returns RunningEvent filtered by Date
     * 
     * @param date format DD/MM/YYYY
     * @return The RunningEvents
     */
    @Override
    public List<RunningEvent> findByDate(String date) {
    	Date givenDate = new Date();
    	try {
			givenDate = DateUtils.parseDate(date, SwissArmyKnife.parsers);
		} catch (ParseException e) {
			e.printStackTrace();
		}
    	return runningEventDao.search(new Search(RunningEvent.class).addFilterEqual("date", givenDate.getTime()));
    }
    
    /**
     * Returns RunningEvent filtered by given month
     * 
     * @param month 
     * @return The RunningEvents
     */
    @Override
    public List<RunningEvent> findByMonth(int month) {
    	Calendar calendar = Calendar.getInstance();
    	calendar.set(Calendar.MONTH, month);
    	calendar.set(Calendar.DAY_OF_MONTH, 1);
    	Date beginMonth = calendar.getTime();
    	calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DATE));
    	Date endMonth = calendar.getTime();
    	Search currentSearch = new Search(RunningEvent.class);
    	currentSearch.addFilterGreaterOrEqual("date", beginMonth.getTime());
    	currentSearch.addFilterLessOrEqual("date", endMonth.getTime());
    	return runningEventDao.search(currentSearch);
    }    
}