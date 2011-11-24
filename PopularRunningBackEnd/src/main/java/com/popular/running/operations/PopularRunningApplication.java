package com.popular.running.operations;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

import org.apache.wink.providers.jettison.JettisonJAXBProvider;
import org.codehaus.jackson.jaxrs.JacksonJaxbJsonProvider;
import org.codehaus.jackson.map.AnnotationIntrospector;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.codehaus.jackson.map.introspect.JacksonAnnotationIntrospector;
import org.codehaus.jackson.xc.JaxbAnnotationIntrospector;

import com.popular.running.service.resources.CityResource;
import com.popular.running.service.resources.DistanceResource;
import com.popular.running.service.resources.RunningEventResource;
import com.popular.running.service.resources.StateResource;

/**
 * Main PopularRunning Wink Application class
 * Publish JSON machinery
 * 
 * @author scastros
 *
 */
public class PopularRunningApplication extends Application {

	@Override
	public Set<Class<?>> getClasses() {
		Set<Class<?>> classes = new HashSet<Class<?>>();
		classes.add(StateResource.class);
		classes.add(CityResource.class);
		classes.add(DistanceResource.class);
		classes.add(RunningEventResource.class);
		return classes;
	}

    @SuppressWarnings({ "rawtypes", "unchecked" })
	public Set<Object> getSingletons() {
        Set s = new HashSet();
        // Register the Jackson provider for JSON
        
        // Make (de)serializer use a subset of JAXB and (afterwards) Jackson annotations
        // See http://wiki.fasterxml.com/JacksonJAXBAnnotations for more information
        ObjectMapper mapper = new ObjectMapper();
        AnnotationIntrospector primary = new JaxbAnnotationIntrospector();
        AnnotationIntrospector secondary = new JacksonAnnotationIntrospector();
        AnnotationIntrospector pair = new AnnotationIntrospector.Pair(primary, secondary);
        mapper.getDeserializationConfig().withAnnotationIntrospector(pair);
        mapper.getSerializationConfig().withAnnotationIntrospector(pair);
        mapper.getSerializationConfig().withSerializationInclusion(Inclusion.NON_NULL);

        JettisonJAXBProvider jettisonProvider = new JettisonJAXBProvider();
        JacksonJaxbJsonProvider jacksonProvider = new JacksonJaxbJsonProvider();
        jacksonProvider.setMapper(mapper);
        jettisonProvider.setUseAsReader(true);
        jettisonProvider.setUseAsWriter(true);
        s.add(jacksonProvider);
        s.add(jettisonProvider);

        return s;
    }
}