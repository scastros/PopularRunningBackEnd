package com.popular.running;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.popular.running.model.RunningEvent;
import com.popular.running.service.impl.RunningEventServiceImpl;

/**
 * Sample application that demonstrates how to build an application context from
 * an XML file in the CLASSPATH and then access its beans.
 *
 * @author scastros
 */
public class RunningEventTest
{
    private static ApplicationContext applicationContext;
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
        applicationContext = new ClassPathXmlApplicationContext( "classpath:applicationContext.xml" );

        // Load our customer service bean
        runningEventService = ( RunningEventServiceImpl )applicationContext.getBean( "runningEventService" );

        // Test code
        showRunningEvents();
        //addRunningEvent( "Michael", "Haines", "michael@javasrc.com", "funkey" );
        showRunningEvents();
        shutdown();
    }

}
