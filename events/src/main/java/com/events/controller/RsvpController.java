package com.events.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.events.database.entity.Category;
import com.events.database.entity.Connection;
import com.events.database.entity.Rsvp;
import com.events.database.entity.User;
import com.events.database.form.RegisterConnectionBean;
import com.events.database.form.RsvpBean;
import com.events.service.CategoryService;
import com.events.service.ConnectionService;
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
    private ConnectionService con_service;
    
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

        response.setViewName("redirect:/connection/show?id="+id);

        return response;
    }

    
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public ModelAndView delete(@RequestParam Integer id ) throws Exception {
        ModelAndView response = new ModelAndView();

        //response.setViewName("redirect:/connection/list");
        response.setViewName("redirect:/connection/show?id="+id);

        //search rsvps that are relationship with this connection
        List<Rsvp> listDelete = rsvp_service.findByConnection(id);
        if (listDelete != null ) {
        	for(Rsvp delete:listDelete) {
            	rsvp_service.delete(delete);
        	}
        }
        
        return response;

    }
   


   
}
