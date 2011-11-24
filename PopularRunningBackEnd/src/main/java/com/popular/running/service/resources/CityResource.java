package com.popular.running.service.resources;

import java.util.ArrayList;
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

import com.popular.running.model.City;
import com.popular.running.operations.OperationsHolder;
import com.popular.running.service.impl.CityServiceImpl;
import com.popular.running.utils.PopularRunningURLs;
import com.popular.running.utils.SwissArmyKnife;

/**
 * A resource that provides access to 
 * Cities stored in Database
 * <p>
 * Handler for requests for collection of Cities of PopularRunning application
 * <p>
 * The Resource is invoked for HTTP requests with URI:
 * <code>http://[server]:[port]/PopularRunningBackEnd/jaxrs/PopularRunning/cities</code>
 */
@Path(value=PopularRunningURLs.CITY_CITIES)
@Workspace(workspaceTitle = SwissArmyKnife.APP_NAME, collectionTitle = "Cities")
public class CityResource {
	
	@Context UriInfo uriInfo;
	
	private static OperationsHolder _operations = OperationsHolder.getInstance();
	private static CityServiceImpl _cityService = _operations.getCityService();
	
    public CityResource() {
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    /**
     * Returns array with the Cities
     * 
     * @return cities
     */
    public List<City> getCities() {
        return _cityService.findAll();
    }
    
    @Path(PopularRunningURLs.GENERIC_ID)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    /**
     * Returns given id City
     * 
     * @param City id
     * @return The City
     */
    public City getCityById(@PathParam("id") String id) {
    	if (StringUtils.isNumeric(id))
    		return _cityService.findById(Long.parseLong(id));
        return new City();
    }
    
    @Path(PopularRunningURLs.CITY_ID_STATE)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    /**
     * Returns Cities in given State id 
     *
     * @param id of the state
     * @return The Cities
     */
    public List<City> getCitiesInState(@PathParam("id") String id) {
    	if (StringUtils.isNumeric(id))
    		return _cityService.findCitiesInState(Long.parseLong(id));
        return new ArrayList<City>();
    }
    
    @Path(PopularRunningURLs.GENERIC_NAME)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    /**
     * Returns Cities which name contains given name 
     *
     * @param id of the state
     * @return The Cities
     */
    public List<City> getCitiesNameContains(@PathParam("name") String name) {
   		return _cityService.findByName(name);
    }    
    
}