package com.popular.running.operations;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.popular.running.service.BaseService;

/**
 * Operations Holder. 
 * Holds instances for every entity service
 * 
 * @author scastros
 *
 */
public class OperationsHolder {

    private static ApplicationContext applicationContext;
    private static BaseService<?> runningEventService;
    private static BaseService<?> cityService;
    private static BaseService<?> distanceService;
    private static BaseService<?> stateService;
    
    public OperationsHolder() {
        // Load the application context
        applicationContext = new ClassPathXmlApplicationContext( "classpath:applicationContext.xml" );

        // Load runningEvent service bean
        runningEventService = ( BaseService<?> )applicationContext.getBean( "runningEventService" );

        // Load city service bean
        cityService = ( BaseService<?> )applicationContext.getBean( "cityService" );

        // Load distance service bean
        distanceService = ( BaseService<?> )applicationContext.getBean( "distanceService" );
        
        // Load state service bean
        stateService = ( BaseService<?> )applicationContext.getBean( "stateService" );
    }

    /**
     * Get Instance
     * @return the Instance
     */
	public static OperationsHolder getInstance() {
		return _instance;
	}
	
	public BaseService<?> getRunningEventService() {
		return runningEventService;
	}

	public BaseService<?> getCityService() {
		return cityService;
	}

	public BaseService<?> getDistanceService() {
		return distanceService;
	}

	public BaseService<?> getStateService() {
		return stateService;
	}
	
	public ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	private static OperationsHolder _instance = new OperationsHolder();
}
