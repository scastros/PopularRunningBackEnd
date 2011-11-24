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

import com.popular.running.model.Distance;
import com.popular.running.operations.OperationsHolder;
import com.popular.running.service.impl.DistanceServiceImpl;
import com.popular.running.utils.PopularRunningURLs;
import com.popular.running.utils.SwissArmyKnife;


/**
 * A resource that provides access to 
 * Distances stored in Database
 * <p>
 * Handler for requests for collection of Distances of PopularRunning application
 * <p>
 * The Resource is invoked for HTTP requests with URI:
 * <code>http://[server]:[port]/PopularRunningBackEnd/jaxrs/PopularRunning/distances</code>
 */
@Path(value=PopularRunningURLs.DISTANCE_DISTANCES)
@Workspace(workspaceTitle = SwissArmyKnife.APP_NAME, collectionTitle = "Distances")
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
    
    @Path(PopularRunningURLs.GENERIC_ID)
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
    
    @Path(PopularRunningURLs.GENERIC_NAME)
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

    @Path(PopularRunningURLs.DISTANCE_LESS)
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
    
    @Path(PopularRunningURLs.DISTANCE_GREATER)
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