package com.popular.running.utils;

/**
 * Collected constants of general utility.
 * 
 * <P>
 * All members of this class are immutable.
 */

public final class PopularRunningURLs {

	/** CITY URLs **/
	public static final String CITY_CITIES = "/PopularRunning/cities";
	public static final String CITY_ID_STATE = "state/{id}";

	/** STATE URLs **/
	public static final String STATE_STATES = "/PopularRunning/states";
	
	/** DISTANCE URLs **/
	public static final String DISTANCE_DISTANCES = "/PopularRunning/distances";
	public static final String DISTANCE_LESS = "less/{distance}";
	public static final String DISTANCE_GREATER = "greater/{distance}";

	/** RUNNINGEVENT URLs **/
	public static final String RUNNINGEVENT_RUNNINGEVENTS = "/PopularRunning/runningevents";
	public static final String RUNNINGEVENT_ID = "id/{id}";
	public static final String RUNNINGEVENT_CITY = "city/{city}";
	public static final String RUNNINGEVENT_STATE = "state/{state}";
	public static final String RUNNINGEVENT_DISTANCE = "distance/{distance}";
	public static final String RUNNINGEVENT_DATE = "date/{date}";
	public static final String RUNNINGEVENT_MONTH = "month/{month}";
	public static final String RUNNINGEVENT_CREATE = "create";
	
	/** GENERICS URLs **/
	public static final String GENERIC_ID = "{id}";
	public static final String GENERIC_NAME = "name/{name}";
	
	
	// PRIVATE //

	/**
	 * The caller references the constants using <tt>PopularRunningURLs.CITY_CITIES</tt>,
	 * and so on. Thus, the caller should be prevented from constructing objects
	 * of this class, by declaring this private constructor.
	 */
	private PopularRunningURLs() {
		// this prevents even the native class from
		// calling this actor as well :
		throw new AssertionError();
	}

}