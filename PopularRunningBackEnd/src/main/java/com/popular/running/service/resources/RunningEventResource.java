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

import com.popular.running.model.RunningEvent;
import com.popular.running.operations.OperationsHolder;
import com.popular.running.service.impl.RunningEventServiceImpl;

/**
 * A resource that provides access to 
 * RunningEvents stored in Database
 */
@Path(value="/PopularRunning/runningevent")
public class RunningEventResource {
	
	@Context UriInfo uriInfo;
	
	private static OperationsHolder _operations = OperationsHolder.getInstance();
	private static RunningEventServiceImpl _runningEventService = _operations.getRunningEventService();
	
    public RunningEventResource() {
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    /**
     * Returns all available RunningEvents
     * 
     * @return The RunningEvents
     */
    public List<RunningEvent> getRunningEvents() {
        return _runningEventService.findAll();
    }
    
    @Path("{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    /**
     * Returns given id RunningEvent
     * 
     * @return The RunningEvent
     */
    public RunningEvent getRunningEventById(@PathParam("id") String id) {
    	if (StringUtils.isNumeric(id))
    		return _runningEventService.findById(Long.parseLong(id));
        return new RunningEvent();
    }
    
    @Path("name/{name}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    /**
     * Returns given name RunningEvent
     * 
     * @return The RunningEvent
     */
    public List<RunningEvent> getRunningEventByName(@PathParam("name") String name) {
    	return _runningEventService.findByName(name);
    }
    
    @Path("city/{city}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    /**
     * Returns given city RunningEvents
     * 
     * @return The RunningEvents
     */
    public List<RunningEvent> getRunningEventByCity(@PathParam("city") long cityId) {
    	return _runningEventService.findByCity(cityId);
    }

    @Path("state/{state}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    /**
     * Returns given state RunningEvents
     * 
     * @return The RunningEvents
     */
    public List<RunningEvent> getRunningEventByState(@PathParam("state") long stateId) {
    	return _runningEventService.findByState(stateId);
    }
    
    @Path("distance/{distance}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    /**
     * Returns given distance RunningEvents
     * 
     * @return The RunningEvents
     */
    public List<RunningEvent> getRunningEventByDistance(@PathParam("distance") long distanceId) {
    	return _runningEventService.findByDistance(distanceId);
    }
    
    @Path("date/{date}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    /**
     * Returns given date RunningEvents
     * 
     * @return The RunningEvents
     */
    public List<RunningEvent> getRunningEventByDate(@PathParam("date") String date) {
    	return _runningEventService.findByDate(date);
    }    
    
    @Path("month/{month}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    /**
     * Returns given month RunningEvents
     * 
     * @return The RunningEvents
     */
    public List<RunningEvent> getRunningEventByMonth(@PathParam("month") int month) {
    	return _runningEventService.findByMonth(month);
    }    
}