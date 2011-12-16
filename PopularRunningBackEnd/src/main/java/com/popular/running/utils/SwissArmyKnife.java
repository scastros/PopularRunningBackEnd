package com.popular.running.utils;

/**
 * Provides several utilery mechanisms and whatever stuff
 * 
 * @author scastros
 *
 */
public final class SwissArmyKnife {
	
	public static final String APP_NAME = "PopularRunning";

	public static final String[] parsers = new String [] {"dd/MM/yyyy"};

	public static final String MEDIA_TYPE_JQGRID = "application/json_jqgrid";
	
	
	// PRIVATE //

	/**
	 * The caller references the constants using <tt>SwissArmyKnife.CONSTANT_NAME</tt>,
	 * and so on. Thus, the caller should be prevented from constructing objects
	 * of this class, by declaring this private constructor.
	 */
	private SwissArmyKnife() {
		// this prevents even the native class from
		// calling this actor as well :
		throw new AssertionError();
	}
}
