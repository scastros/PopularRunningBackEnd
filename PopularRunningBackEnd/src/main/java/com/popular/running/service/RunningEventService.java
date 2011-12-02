package com.popular.running.service;

import java.util.List;

/**
 * Base Interface for Services
 * 
 * @author scastros
 *
 * @param <T>
 */
public interface RunningEventService <RunningEvent extends Object> extends BaseService<Object>{
	public final static String SERVICE_NAME = "runningEventService";
	public List<RunningEvent> findByCity(long cityId);
    public List<RunningEvent> findByState(long stateId);
    public List<RunningEvent> findByDistance(long distanceId);
    public List<RunningEvent> findByDate(String date);
    public List<RunningEvent> findByMonth(int month);
}