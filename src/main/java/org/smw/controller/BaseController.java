package org.smw.controller;

import org.smw.view.View;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class BaseController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String defaultHome() {
		return View.HOME;
	}

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home() {
		return View.HOME;
	}

	@RequestMapping(value = "layout/header", method = RequestMethod.GET)
	public String header() {
		return View.HEADER;
	}

	@RequestMapping(value = "/404", method = RequestMethod.GET)
	public String error404() {
		return View.PAGE_NOT_FOUND;
	}

	@RequestMapping(value = "/400", method = RequestMethod.GET)
	public String error400() {
		return View.BAD_REQUEST;
	}

}