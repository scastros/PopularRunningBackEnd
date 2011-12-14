package com.popular.running.front.converters;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.popular.running.front.forms.RunningEventForm;
import com.popular.running.model.City;
import com.popular.running.model.Distance;
import com.popular.running.model.RunningEvent;
import com.popular.running.operations.OperationsHolder;
import com.popular.running.service.CityService;
import com.popular.running.service.DistanceService;

@SuppressWarnings("unchecked")
public class RunningEventConverter {
	
    private static DistanceService<Distance> _distanceService = 
    	(DistanceService<Distance>)OperationsHolder.getInstance().getDistanceService();

    private static CityService<City> _cityService = 
    	(CityService<City>)OperationsHolder.getInstance().getCityService();

	
	/**
	 * Converts RunningEvent to RunningEventForm
	 * 
	 * @param runningEvent
	 * @return
	 */
	public static RunningEventForm toRunningEventForm(RunningEvent runningEvent) throws RunningConverterException {
		if (runningEvent == null) return null;
		
		try {
			Date runningEventDate = new Date(runningEvent.getDate());
			Distance runningEventDistance = (Distance)_distanceService.findById(runningEvent.getDistance());
			City runningEventLocation = (City) _cityService.findById(runningEvent.getLocation());
			
			RunningEventForm formRunningEvent = new RunningEventForm();
			formRunningEvent.setDate(runningEventDate);
			formRunningEvent.setDescription(runningEvent.getDescription());
			formRunningEvent.setDistance(runningEventDistance);
			formRunningEvent.setElevation(runningEvent.getElevation());
			formRunningEvent.setEnrollment(runningEvent.getEnrollment());
			formRunningEvent.setLocation(runningEventLocation);
			formRunningEvent.setMap(runningEvent.getMap());
			formRunningEvent.setPicture(runningEvent.getPicture());
			formRunningEvent.setShortName(runningEvent.getShortName());
			
			return formRunningEvent;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RunningConverterException(e);
		}
	}
	
	/**
	 * Converts RunningEventForm to RunningEvent
	 * 
	 * @param runningEventForm
	 * @return
	 */
	public static RunningEvent toRunningEvent(RunningEventForm runningEventForm) throws RunningConverterException {
		if (runningEventForm == null) return null;
		
		try {
			RunningEvent dbRunningEvent = new RunningEvent();
			dbRunningEvent.setDate(runningEventForm.getDate().getTime());
			dbRunningEvent.setDescription(runningEventForm.getDescription());
			dbRunningEvent.setDistance(runningEventForm.getDistance().getId());
			dbRunningEvent.setElevation(runningEventForm.getElevation());
			dbRunningEvent.setEnrollment(runningEventForm.getEnrollment());
			dbRunningEvent.setLocation(runningEventForm.getLocation().getId());
			dbRunningEvent.setMap(runningEventForm.getMap());
			dbRunningEvent.setPicture(runningEventForm.getPicture());
			dbRunningEvent.setShortName(runningEventForm.getShortName());
			
			return dbRunningEvent;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RunningConverterException(e);
		}
	}
	
	/**
	 * Converts RunningEventFormList to RunningEventList
	 * 
	 * @param runningEventFormList
	 * @return
	 * @throws RunningConverterException
	 */
	public static List<RunningEvent> toRunningEventList(List<Object> runningEventFormList) throws RunningConverterException {
		if (runningEventFormList == null) return null;

		try {
			List<RunningEvent> runningEventList = new ArrayList<RunningEvent>();
			
			for (Object runningEventForm : runningEventFormList) {
				runningEventList.add(toRunningEvent((RunningEventForm)runningEventForm));
			}
			
			return runningEventList;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RunningConverterException(e);
		}
	}
	
	/**
	 * Converts RunningEventList to RunningEventFormList
	 * 
	 * @param runningEventList
	 * @return
	 * @throws RunningConverterException
	 */
	public static List<RunningEventForm> toRunningEventFormList(List<Object> runningEventList) throws RunningConverterException {
		if (runningEventList == null) return null;

		try {
			List<RunningEventForm> runningEventFormList = new ArrayList<RunningEventForm>();
			
			for (Object runningEvent : runningEventList) {
				runningEventFormList.add(toRunningEventForm((RunningEvent)runningEvent));
			}
			
			return runningEventFormList;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RunningConverterException(e);
		}
	}	
}
