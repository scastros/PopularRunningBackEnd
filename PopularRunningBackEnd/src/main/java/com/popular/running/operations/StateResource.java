package com.popular.running.operations;

import java.net.URI;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import org.apache.wink.json4j.JSONArray;
import org.apache.wink.json4j.JSONException;

import com.popular.running.model.State;

/**
 * A resource that provides access to 
 * States stored in Database
 */
@Path(value="/PopularRunning/states")
public class StateResource {
	
	@Context UriInfo uriInfo;
	
    public StateResource() {
    }
    
    /** Returns JSONArray with the States */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public JSONArray getStates() {
    	
    	JSONArray array = new JSONArray();
        List<State> states = OperationsHolder.getInstance().getStateService().findAll();
        
        for (State state : states) {
            UriBuilder ub = uriInfo.getAbsolutePathBuilder();
            URI userUri = ub.path(state.getDescription()).build();
            try {
				array.put(userUri.toASCIIString());
			} catch (JSONException e) {
				e.printStackTrace();
			}
        }
        return array;
    }
}