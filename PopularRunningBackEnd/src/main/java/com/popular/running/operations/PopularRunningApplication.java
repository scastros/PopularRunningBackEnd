package com.popular.running.operations;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

public class PopularRunningApplication extends Application {

	@Override
	public Set<Class<?>> getClasses() {
		Set<Class<?>> classes = new HashSet<Class<?>>();
		classes.add(PopularRunning.class);
		classes.add(StateResource.class);
		return classes;
	}

}