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

import com.popular.running.model.Distance;
import com.popular.running.operations.OperationsHolder;
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
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    /**
     *  Returns array with the Distances 
     *  
     *  @return The distances
     */
    public List<Distance> getDistances() {
        return _distanceService.findAll();
    }
    
    @Path("{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    /**
     * Returns given id Distance
     * @param id
     * @return Distance
     */
    public Distance getDistanceById(@PathParam("id") String id) {
    	if (StringUtils.isNumeric(id))
    		return _distanceService.findById(Long.parseLong(id));
        return new Distance();
    }
    
    @Path("name/{name}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    /**
     * Returns given name Distance
     * @param name
     * @return Distance
     */
    public List<Distance> getDistanceByName(@PathParam("name") String name) {
    	return _distanceService.findByName(name);
    }

    @Path("less/{distance}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    /**
     * Returns Distance less than given distance in meters
     * @param distance
     * @return Distances that meet the criteria
     */
    public List<Distance> getDistancesLessThan(@PathParam("distance") long distance) {
    	return _distanceService.findDistancesLessThan(distance);
    }
    
    @Path("greater/{distance}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    /**
     * Returns Distance greater than given distance in meters
     * @param distance
     * @return Distances that meet the criteria
     */
    public List<Distance> getDistancesGreaterThan(@PathParam("distance") long distance) {
    	return _distanceService.findDistancesGreatherThan(distance);
    }
    
}