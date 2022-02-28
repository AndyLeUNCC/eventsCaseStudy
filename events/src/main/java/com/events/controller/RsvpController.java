package com.events.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.events.database.entity.Rsvp;
import com.events.database.entity.User;
import com.events.database.form.RsvpBean;
import com.events.service.RsvpService;
import com.events.service.UserService;

/**
 *
 *
 */

@Controller
@RequestMapping("/rsvp")
@PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
public class RsvpController {

    // make sure are import the slf4j object imports for this line of code
    public static final Logger LOG = LoggerFactory.getLogger(RsvpController.class);

    @Autowired
    private UserService user_service;

    @Autowired
    private RsvpService rsvp_service;


    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public ModelAndView register(@RequestParam(required = false) Integer id
    		, @RequestParam(required = false) String attend) throws Exception {
        ModelAndView response = new ModelAndView();

        //search the rsvp table by connection id and user id.
        //if we get the result from rsvp then update
        //otherwise then create new rsvp record

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		String currentPrincipalName = authentication.getName();//get the email from the authentication

		User current_user = user_service.findByEmail(currentPrincipalName);

		int userId = current_user.getId();
		int connectionId = id;
		RsvpBean form = new RsvpBean();

        Rsvp rsvp = rsvp_service.findByUserAndConnection(userId, connectionId);
        if (rsvp != null) {
        	//update the RSVP with new value from attending
        	rsvp.setUser(userId);
        	rsvp.setConnection(connectionId);
        	rsvp.setAttending(attend);
        	rsvp_service.save(rsvp);
        	form.getSuccessMessages().add("Rsvp Updated Successfully!");
            response.addObject("formBeanKey", form);

        } else {
        	//create a new RSVP with new value from userId, connectionId and attending
        	rsvp = new Rsvp();

        	rsvp.setUser(userId);
        	rsvp.setConnection(connectionId);
        	rsvp.setAttending(attend);
        	rsvp_service.save(rsvp);

        	form.getSuccessMessages().add("Rsvp Created Successfully!");
            response.addObject("formBeanKey", form);

        }

        LOG.info("new Rsvp record " + rsvp);
        response.setViewName("redirect:/connection/show?id="+id);

        return response;
    }


    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public ModelAndView delete(@RequestParam Integer id ) throws Exception {
        ModelAndView response = new ModelAndView();

        //response.setViewName("redirect:/connection/list");
        response.setViewName("redirect:/user/profile");

        //search rsvps that are relationship with this connection
        Rsvp delete = rsvp_service.findById(id);
        if (delete != null ) {
            rsvp_service.delete(delete);
            LOG.info("delete Rsvp record " + delete);

        }

        return response;

    }




}
