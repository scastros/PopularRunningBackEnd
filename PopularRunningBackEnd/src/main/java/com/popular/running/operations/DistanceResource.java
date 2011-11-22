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

import com.popular.running.model.Distance;
import com.popular.running.service.impl.DistanceServiceImpl;

/**
 * A resource that provides access to 
 * Distances stored in Database
 */
@Path(value="/PopularRunning/distances")
public class DistanceResource {
	
	@Context UriInfo uriInfo;
	
	private static OperationsHolder _operations = OperationsHolder.getInstance();
	private static DistanceServiceImpl _distanceService = _operations.getDistanceService();
	
    public DistanceResource() {
    }
    
    /** Returns array with the Distances */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Distance> getDistances() {
        return _distanceService.findAll();
    }
    
    /** Returns given id Distance */
    @Path("{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Distance getDistanceById(@PathParam("id") String id) {
    	if (StringUtils.isNumeric(id))
    		return _distanceService.findById(Long.parseLong(id));
        return new Distance();
    }
}