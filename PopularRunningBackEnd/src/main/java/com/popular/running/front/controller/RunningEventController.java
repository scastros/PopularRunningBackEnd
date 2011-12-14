package com.popular.running.front.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.popular.running.front.converters.RunningConverterException;
import com.popular.running.front.converters.RunningEventConverter;
import com.popular.running.front.editors.CityFormEditor;
import com.popular.running.front.editors.DistanceFormEditor;
import com.popular.running.front.forms.RunningEventForm;
import com.popular.running.model.City;
import com.popular.running.model.Distance;
import com.popular.running.model.RunningEvent;
import com.popular.running.model.State;
import com.popular.running.operations.OperationsHolder;
import com.popular.running.service.DistanceService;
import com.popular.running.service.RunningEventService;
import com.popular.running.service.StateService;

/**
 * Handles requests for the application home page.
 */
@Controller
@SuppressWarnings("unchecked")
public class RunningEventController {

	private static final Logger logger = LoggerFactory.getLogger(RunningEventController.class);

    private static RunningEventService<RunningEvent> _runningEventService = 
    	(RunningEventService<RunningEvent>)OperationsHolder.getInstance().getRunningEventService();

    private static DistanceService<Distance> _distanceService = 
    	(DistanceService<Distance>)OperationsHolder.getInstance().getDistanceService();

    private static StateService<State> _stateService = 
    	(StateService<State>)OperationsHolder.getInstance().getStateService();

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String homeForm(Model model) {
		logger.info("Popular Running BackEnd Application");

		model.addAttribute("runningEvent", new RunningEventForm());

		return "index";
	}

	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public String create(@Valid @ModelAttribute("runningEvent") RunningEventForm runningEvent, BindingResult result) {

		if (result.hasErrors()) {
			return "index";
		}

		try {
			addNewRunningEvent(runningEvent);
		} catch (Exception e) {
			e.printStackTrace();
			return "index";
		}
		return "redirect:/list";
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView showResults() {

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("list");
		
		try {
			modelAndView.addObject("runningEvents", RunningEventConverter.toRunningEventFormList(_runningEventService.findAll()));
		} catch (RunningConverterException e) {
			e.printStackTrace();
			modelAndView.setViewName("error");
		}

		return modelAndView;
	}
	
	@ModelAttribute("allDistances")
	public List<Object> populateDistances() {
		return _distanceService.findAll();
	}
	
	@ModelAttribute("allStates")
	public List<Object> populateStates() {
		return _stateService.findAll();
	}

	/**
	 * Adds new Running Event to Database
	 * @param runningEventForm
	 */
	private void addNewRunningEvent(RunningEventForm runningEventForm) throws Exception {
		_runningEventService.save(RunningEventConverter.toRunningEvent(runningEventForm));
	}
	
	@InitBinder
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception
	{       
	      binder.registerCustomEditor(Distance.class, new DistanceFormEditor());
	      binder.registerCustomEditor(City.class, new CityFormEditor());
	}  
}