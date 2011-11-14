package com.popular.running.operations;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.wink.json4j.JSONArray;

import com.popular.running.model.State;

/**
 * A resource that provides access to 
 * States stored in Database
 */
@Path(value="/PopularRunning/states")
public class StateResource {
    public StateResource() {
    }
    
    /** Returns JSONArray with the States */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public JSONArray getStates() {
    	
    	JSONArray array = new JSONArray();
        List<State> states = OperationsHolder.getInstance().getStateService().findAll();
        array.addAll(states);
        
        return array;
    }
}