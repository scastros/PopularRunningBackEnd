package com.popular.running.service;

import java.util.List;

/**
 * Base Interface for City Services
 * 
 * @author scastros
 *
 * @param <T>
 */
public interface CityService <City extends Object> extends BaseService<Object>{
	public final static String SERVICE_NAME = "cityService";
	public List<City> findCitiesInState(long stateId);
}
