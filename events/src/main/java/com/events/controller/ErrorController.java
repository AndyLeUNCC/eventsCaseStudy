package com.events.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@ControllerAdvice
public class ErrorController {

	//@Autowired
	//private AuthenticatedUserService authenticatedUserService;

	@RequestMapping(value = "/error/404")
	public String error404(HttpServletRequest request) {

		return "error/404";
	}

	@ExceptionHandler(AccessDeniedException.class)
	public ModelAndView accessDenied(HttpServletRequest request, Exception ex) {
		ModelAndView model = new ModelAndView("error/404");

		//if (authenticatedUserService.isAuthenticated()) {
		//	log.error("User : " + authenticatedUserService.getCurrentUsername() + " requested url that they do not have permission to " + request.getRequestURL() + " from IP address " + WebUtils.getIpAddress(request));
		//} else {
		//	log.error("Unauthenticated user requested url that they do not have permission to " + request.getRequestURL() + " from IP address " + WebUtils.getIpAddress(request));
		//}


		return model;
	}

	@ExceptionHandler(Exception.class)
	public ModelAndView handleAllException(HttpServletRequest request, Exception ex) {

		ModelAndView model = new ModelAndView("/error/500");

		String stackTrace = getHTMLStackTrace(ex);

		//if (authenticatedUserService.isUserInRole(UserRoleEnum.ADMIN.toString())) {
			model.addObject("message", ex.getMessage());
			model.addObject("stackTrace", stackTrace);
		//}

		return model;
	}

	private String getHTMLStackTrace(Exception ex) {
		String stackTrace = ExceptionUtils.getStackTrace(ex);

		stackTrace = stackTrace.replaceAll("[\\r\\f\\n]+", "<br/>");
		stackTrace = stackTrace.replaceAll("\\t", " &nbsp; &nbsp; &nbsp;");

		return stackTrace;
	}

	private String getRequestURL(HttpServletRequest request) {
		String result = request.getRequestURL().toString();
		if (request.getQueryString() != null) {
			result = result + "?" + request.getQueryString();
		}

		return result;
	}


}
