package com.popular.running.service.resources;

import java.net.URI;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.wink.common.annotations.Workspace;

import com.popular.running.model.RunningEvent;
import com.popular.running.operations.OperationsHolder;
import com.popular.running.service.impl.RunningEventServiceImpl;
import com.popular.running.utils.PopularRunningURLs;
import com.popular.running.utils.SwissArmyKnife;

/**
 * A resource that provides access to 
 * RunningEvents stored in Database
 * <p>
 * Handler for requests for collection of RunningEvents of PopularRunning application
 * <p>
 * The Resource is invoked for HTTP requests with URI:
 * <code>http://[server]:[port]/PopularRunningBackEnd/jaxrs/PopularRunning/runningevents</code>
 */
@Path(value=PopularRunningURLs.RUNNINGEVENT_RUNNINGEVENTS)
@Workspace(workspaceTitle = SwissArmyKnife.APP_NAME, collectionTitle = "RunningEvents")
public class RunningEventResource {
	
	@Context UriInfo uriInfo;
	
	private static OperationsHolder _operations = OperationsHolder.getInstance();
	private static RunningEventServiceImpl _runningEventService = _operations.getRunningEventService();
	private Log log = LogFactory.getLog(RunningEventResource.class);

    /**
     * <p>
     * Method is handling GET requests for collection of RunningEvents.
     * <ul>
     * <em>Examples of handled URIs:</em>
     * <li><code>/runningevents</code> - returns collection of RunningEvents in JSON Format
     * </ul>
     */
	@GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<RunningEvent> getRunningEvents() {
        return _runningEventService.findAll();
    }
    
    /**
     * Returns given id RunningEvent
     * 
     * @return The RunningEvent
     */
    @Path(PopularRunningURLs.RUNNINGEVENT_ID)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public RunningEvent getRunningEventById(@PathParam("id") String id) {
    	if (StringUtils.isNumeric(id))
    		return _runningEventService.findById(Long.parseLong(id));
        return new RunningEvent();
    }
    
    /**
     * Returns given name RunningEvent
     * 
     * @return The RunningEvent
     */
    @Path(PopularRunningURLs.GENERIC_NAME)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<RunningEvent> getRunningEventByName(@PathParam("name") String name) {
    	return _runningEventService.findByName(name);
    }
    
    /**
     * Returns given city RunningEvents
     * 
     * @return The RunningEvents
     */
    @Path(PopularRunningURLs.RUNNINGEVENT_CITY)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<RunningEvent> getRunningEventByCity(@PathParam("city") long cityId) {
    	return _runningEventService.findByCity(cityId);
    }

    /**
     * Returns given state RunningEvents
     * 
     * @return The RunningEvents
     */
    @Path(PopularRunningURLs.RUNNINGEVENT_STATE)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<RunningEvent> getRunningEventByState(@PathParam("state") long stateId) {
    	return _runningEventService.findByState(stateId);
    }
    
    /**
     * Returns given distance RunningEvents
     * 
     * @return The RunningEvents
     */
    @Path(PopularRunningURLs.RUNNINGEVENT_DISTANCE)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<RunningEvent> getRunningEventByDistance(@PathParam("distance") long distanceId) {
    	return _runningEventService.findByDistance(distanceId);
    }
    
    /**
     * Returns given date RunningEvents
     * 
     * @return The RunningEvents
     */
    @Path(PopularRunningURLs.RUNNINGEVENT_DATE)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<RunningEvent> getRunningEventByDate(@PathParam("date") String date) {
    	return _runningEventService.findByDate(date);
    }    
    
    /**
     * Returns given month RunningEvents
     * 
     * @return The RunningEvents
     */
    @Path(PopularRunningURLs.RUNNINGEVENT_MONTH)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<RunningEvent> getRunningEventByMonth(@PathParam("month") int month) {
    	return _runningEventService.findByMonth(month);
    }
    
    /**
     * Creates RunningEvent
     * 
     * @return Response with status code and object created
     */
    @Path(PopularRunningURLs.RUNNINGEVENT_CREATE)
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createRunningEvent(RunningEvent runningevent) {
    	if (runningevent == null) {
            log.error("The content of the RunningEvent is missing");
            throw new WebApplicationException(Response.Status.BAD_REQUEST);    		
    	}
    	// RunningEvent creation
    	_runningEventService.save(runningevent);
        // return the RunningEvent and set the status code to created (201)
        URI location = uriInfo.getAbsolutePathBuilder().segment(String.valueOf(runningevent.getId())).build();
        return Response.created(location).entity(runningevent).build();    	
    }       
    
}