package com.popular.running.service;


/**
 * Base Interface for Services
 * 
 * @author scastros
 *
 * @param <T>
 */
public interface StateService <State extends Object> extends BaseService<Object>{
	public final static String SERVICE_NAME = "stateService";
}