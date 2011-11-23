package com.popular.running.operations;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.popular.running.service.impl.CityServiceImpl;
import com.popular.running.service.impl.DistanceServiceImpl;
import com.popular.running.service.impl.RunningEventServiceImpl;
import com.popular.running.service.impl.StateServiceImpl;

/**
 * Operations Holder. 
 * Holds instances for every entity service
 * 
 * @author scastros
 *
 */
public class OperationsHolder {

    private static ApplicationContext applicationContext;
    private static RunningEventServiceImpl runningEventService;
    private static CityServiceImpl cityService;
    private static DistanceServiceImpl distanceService;
    private static StateServiceImpl stateService;
    
    public OperationsHolder() {
        // Load the application context
        applicationContext = new ClassPathXmlApplicationContext( "classpath:applicationContext.xml" );

        // Load runningEvent service bean
        runningEventService = ( RunningEventServiceImpl )applicationContext.getBean( "runningEventService" );

        // Load city service bean
        cityService = ( CityServiceImpl )applicationContext.getBean( "cityService" );

        // Load distance service bean
        distanceService = ( DistanceServiceImpl )applicationContext.getBean( "distanceService" );
        
        // Load state service bean
        stateService = ( StateServiceImpl )applicationContext.getBean( "stateService" );
    }

    /**
     * Get Instance
     * @return the Instance
     */
	public static OperationsHolder getInstance() {
		return _instance;
	}
	
	public RunningEventServiceImpl getRunningEventService() {
		return runningEventService;
	}

	public CityServiceImpl getCityService() {
		return cityService;
	}

	public DistanceServiceImpl getDistanceService() {
		return distanceService;
	}

	public StateServiceImpl getStateService() {
		return stateService;
	}
	
	public ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	private static OperationsHolder _instance = new OperationsHolder();
}
