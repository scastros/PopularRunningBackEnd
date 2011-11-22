package com.popular.running.operations;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import com.popular.running.model.City;
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
    
    /** Returns array with the Cities */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<City> getCities() {
        return _cityService.findAll();
    }
}