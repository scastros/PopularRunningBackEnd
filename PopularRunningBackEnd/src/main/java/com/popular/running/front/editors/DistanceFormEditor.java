package com.popular.running.front.editors;

import java.beans.PropertyEditorSupport;

import com.popular.running.model.Distance;
import com.popular.running.operations.OperationsHolder;
import com.popular.running.service.DistanceService;

@SuppressWarnings("unchecked")
public class DistanceFormEditor extends PropertyEditorSupport {

    private static DistanceService<Distance> _distanceService = 
    	(DistanceService<Distance>)OperationsHolder.getInstance().getDistanceService();

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		long id = Long.valueOf(text);
		setValue(_distanceService.findById(id));
	}

	/**
	 * This implementation returns <code>null</code> to indicate that
	 * there is no appropriate text representation.
	 */
	@Override
	public String getAsText() {
		Distance value = (Distance) getValue();
		return (value != null ? String.valueOf(value.getId()) : "");
	}
}