package com.popular.running.service.resources;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import org.apache.commons.lang.StringUtils;
import org.apache.wink.common.annotations.Workspace;

import com.popular.running.model.State;
import com.popular.running.operations.OperationsHolder;
import com.popular.running.service.impl.StateServiceImpl;
import com.popular.running.utils.PopularRunningURLs;
import com.popular.running.utils.SwissArmyKnife;

/**
 * A resource that provides access to 
 * States stored in Database
 * <p>
 * Handler for requests for collection of States of PopularRunning application
 * <p>
 * The Resource is invoked for HTTP requests with URI:
 * <code>http://[server]:[port]/PopularRunningBackEnd/jaxrs/PopularRunning/states</code>
 */
@Path(value=PopularRunningURLs.STATE_STATES)
@Workspace(workspaceTitle = SwissArmyKnife.APP_NAME, collectionTitle = "States")
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
    @Path(PopularRunningURLs.GENERIC_ID)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public State getStateById(@PathParam("id") String id) {
    	if (StringUtils.isNumeric(id))
    		return _stateService.findById(Long.parseLong(id));
        return new State();
    }
    
    @Path(PopularRunningURLs.GENERIC_NAME)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    /**
     * Returns given name State
     * @param name
     * @return State
     */
    public List<State> getStateByName(@PathParam("name") String name) {
    	return _stateService.findByName(name);
    }
}