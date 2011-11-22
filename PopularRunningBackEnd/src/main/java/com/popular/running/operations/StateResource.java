package com.popular.running.operations;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import org.apache.commons.lang.StringUtils;

import com.popular.running.model.State;
import com.popular.running.service.impl.StateServiceImpl;

/**
 * A resource that provides access to 
 * States stored in Database
 */
@Path(value="/PopularRunning/states")
public class StateResource {
	
	@Context UriInfo uriInfo;
	
	private static OperationsHolder _operations = OperationsHolder.getInstance();
	private static StateServiceImpl _stateService = _operations.getStateService();
	
    public StateResource() {
    }
    
    /** Returns array with the States */
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<State> getStates() {
        return _stateService.findAll();
    }
    
    /** Returns given id State */
    @Path("{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public State getStateById(@PathParam("id") String id) {
    	if (StringUtils.isNumeric(id))
    		return _stateService.findById(Long.parseLong(id));
        return new State();
    }
}