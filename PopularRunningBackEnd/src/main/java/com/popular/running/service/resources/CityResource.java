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

import com.popular.running.model.City;
import com.popular.running.operations.OperationsHolder;
import com.popular.running.service.impl.CityServiceImpl;

/**
 * A resource that provides access to 
 * Cities stored in Database
 */
@Path(value="/PopularRunning/cities")
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
    
    @Path("{id}")
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
    
    @Path("state/{id}")
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
    
    @Path("name/{name}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    /**
     * Returns Cities which name contains given name 
     *
     * @param id of the state
     * @return The Cities
     */
    public List<City> getCitiesNameContains(@PathParam("name") String name) {
   		return _cityService.findByName("%"+name+"%");
    }    
    
}