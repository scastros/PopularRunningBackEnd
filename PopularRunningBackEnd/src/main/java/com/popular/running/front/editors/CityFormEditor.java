package com.popular.running.front.editors;

import java.beans.PropertyEditorSupport;

import com.popular.running.model.City;
import com.popular.running.operations.OperationsHolder;
import com.popular.running.service.CityService;

@SuppressWarnings("unchecked")
public class CityFormEditor extends PropertyEditorSupport {
    
	private static CityService<City> _cityService = 
    	(CityService<City>)OperationsHolder.getInstance().getCityService();
    
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		long id = Long.valueOf(text);
		setValue(_cityService.findById(id));
	}

	/**
	 * This implementation returns <code>null</code> to indicate that
	 * there is no appropriate text representation.
	 */
	@Override
	public String getAsText() {
		City value = (City) getValue();
		return (value != null ? String.valueOf(value.getId()) : "");
	}
}