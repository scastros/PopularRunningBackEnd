package com.popular.running.front.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.popular.running.model.RunningEvent;
import com.popular.running.operations.OperationsHolder;
import com.popular.running.service.RunningEventService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class RunningEventController {

	private static final Logger logger = LoggerFactory.getLogger(RunningEventController.class);

	@SuppressWarnings("unchecked")
    private static RunningEventService<RunningEvent> _runningEventService = 
    	(RunningEventService<RunningEvent>)OperationsHolder.getInstance().getRunningEventService();
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String homeForm(Model model) {
		logger.info("Popular Running BackEnd Application");

		model.addAttribute("runningEvent", new RunningEvent());

		return "index";
	}

	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public String create(@Valid @ModelAttribute("runningEvent") RunningEvent runningEvent, BindingResult result) {

		if (result.hasErrors()) {
			return "index";
		}

		addNewRunningEvent(runningEvent);
		return "redirect:/list";
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView showResults() {

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("list");
		modelAndView.addObject("runningEvents", _runningEventService.findAll());

		return modelAndView;
	}

	/**
	 * Adds new Running Event to Database
	 * @param runningEventForm
	 */
	private void addNewRunningEvent(RunningEvent runningEvent) {
		_runningEventService.save(runningEvent);
	}
}