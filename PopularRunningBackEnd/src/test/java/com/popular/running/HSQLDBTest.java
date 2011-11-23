package com.popular.running;

import java.sql.*;

/**
 * Easy way to create your database script (testdb.script in this example):
 * java -cp ./lib/hsqldb-2.0.0.jar org.hsqldb.util.DatabaseManager -driver org.hsqldb.jdbcDriver -url jdbc:hsqldb:file:testdb -user sa
 *
 * Be sure to SHUTDOWN the database before quiting so that it saves your work!!
 */
public class HSQLDBTest 
{
	private static Connection c;
	
	public static void addRunningEvent( long date, String shortName, String picture,
										long distance, long location, String description,
										String enrollment, String map, String elevation )
	{
		try
		{
			PreparedStatement ps = c.prepareStatement( "insert into runningevent values (null, ?, ?, ?, ?, ?, ?, ?, ?)" );
			ps.setString( 1, shortName );
			ps.setString( 2, picture );
			ps.setLong( 3, distance );
			ps.setLong( 4, location );
			ps.setString( 5, description );
			ps.setString( 6, enrollment );
			ps.setString( 7, map );
			ps.setString( 8, elevation );
			int count = ps.executeUpdate();
			System.out.println( "Updated rows: " + count );
			ps.close();
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
	}

	@SuppressWarnings({ "unused" })
	public static void shutdown()
	{
		try
		{
			System.out.println( "Shutting down..." );
			Statement s = c.createStatement();
			int count = s.executeUpdate( "SHUTDOWN" );
			s.close();
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
	}


	public static void showRunningEvents()
	{
		try
		{
      		Statement s = c.createStatement();
      		ResultSet rs = s.executeQuery( "SELECT * FROM RUNNINGEVENT" );
      		System.out.println( "RunningEvent list" );
      		while( rs.next() )
      		{
        			System.out.println( "Description: " + rs.getString( "DESCRIPTION" ) );
      		}
      		rs.close();
      		s.close();
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
	}
	
  	public static void main( String[] args )
  	{
  		try
    		{
			// shutdown=true causes it to save the database contents whem the last connection is closed
			// See: http://hsqldb.org/doc/2.0/guide/running-chapt.html#running_inprocess-sect
			// If you don't want to use it then you can explicitly send the database a SHUTDOWN command
      		//c = DriverManager.getConnection( "jdbc:hsqldb:file:testdb;shutdown=true", "SA", "" );
      		c = DriverManager.getConnection( "jdbc:hsqldb:file:popular-running-db", "SA", "" );
      		showRunningEvents();
      		//addRunningEvent( "Michael", "Haines", "michael@javasrc.com", "funkey" );	
      		showRunningEvents();
      		shutdown();
      		c.close();
    		}
 	   	catch( Exception e ) 
    		{
      		e.printStackTrace();
   	 	}
  	}
}
