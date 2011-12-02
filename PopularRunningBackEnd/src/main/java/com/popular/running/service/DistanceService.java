package com.popular.running.service;

import java.util.List;

/**
 * Base Interface for DistanceServices
 * 
 * @author scastros
 *
 * @param <T>
 */
public interface DistanceService <Distance extends Object> extends BaseService<Object>{
	public final static String SERVICE_NAME = "distanceService";
	public List<Distance> findDistancesGreatherThan(long distance);
	public List<Distance> findDistancesLessThan(long distance);
}