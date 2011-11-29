package com.popular.running;

import java.util.Date;
import java.util.List;

import com.popular.running.model.RunningEvent;
import com.popular.running.operations.OperationsHolder;
import com.popular.running.service.impl.RunningEventServiceImpl;

/**
 * Sample application that demonstrates how to build an application context from
 * an XML file in the CLASSPATH and then access its beans.
 *
 * @author scastros
 */
public class RunningEventTest
{
    //private static ApplicationContext applicationContext;
    private static RunningEventServiceImpl runningEventService;

    public static void showRunningEvents()
    {
        List<RunningEvent> runningEvents = runningEventService.findAll();
        System.out.println( "Running Events:" );
        for( RunningEvent runningEvent : runningEvents )
        {
            System.out.println( "\t" + runningEvent.getDate() );
            System.out.println( "\t" + runningEvent.getShortName() );
            System.out.println( "\t" + runningEvent.getPicture() );
            System.out.println( "\t" + runningEvent.getDistance() );
            System.out.println( "\t" + runningEvent.getLocation() );
            System.out.println( "\t" + runningEvent.getDescription() );
            System.out.println( "\t" + runningEvent.getEnrollment() );
            System.out.println( "\t" + runningEvent.getMap() );
            System.out.println( "\t" + runningEvent.getElevation() );
        }
    }

    public static void addRunningEvent( long date, String shortName, String picture,
										long distance, long location, String description,
										String enrollment, String map, String elevation )
    {
        runningEventService.save( new RunningEvent(date, shortName, picture, distance, location, description, enrollment, map, elevation) );
    }

    public static void shutdown()
    {
        runningEventService.shutdown();
    }


    public static void main( String[] args )
    {
        // Load the application context
    	//applicationContext = OperationsHolder.getInstance().getApplicationContext();
        //applicationContext = new ClassPathXmlApplicationContext( "classpath:applicationContext.xml" );

        // Load our customer service bean
        //runningEventService = OperationsHolder.getInstance().getRunningEventService();
        //runningEventService = ( RunningEventServiceImpl )applicationContext.getBean( "runningEventService" );

        // Test code
        showRunningEvents();

        // {"date":"1355245920000", "shortName":"Carrera Trofeo Akiles", "picture":"http://www.google.com/image.jpg", "distance":"3", "location":"345", "description":"Edición XII del Trofeo Akiles", "enrollment":"http://www.enrollment.com", "map":"http://www.map.com", "elevation":"http://www.elevation.com"}
        
        addRunningEvent( new Date().getTime(), "Carrera Trofeo Akiles", "http://www.google.com/image.jpg", 3, 345, "Edición XII del Trofeo Akiles", "http://www.enrollment.com", "http://www.map.com", "http://www.elevation.com");
        showRunningEvents();
        shutdown();
    }

}
