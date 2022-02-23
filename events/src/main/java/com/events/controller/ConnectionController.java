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
import com.events.database.entity.UserRole;
import com.events.database.form.RegisterConnectionBean;
import com.events.service.CategoryService;
import com.events.service.ConnectionService;
import com.events.service.RsvpService;
import com.events.service.UserService;

/**
 *
 *
 */

@Controller
@RequestMapping("/connection")
public class ConnectionController {

    // make sure are import the slf4j object imports for this line of code
    public static final Logger LOG = LoggerFactory.getLogger(ConnectionController.class);

    @Autowired
    private ConnectionService con_service;
    
    @Autowired
    private CategoryService cate_service;
    
    @Autowired
    private UserService user_service;
    
    @Autowired
    private RsvpService rsvp_service;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView connectionList(Model model, @Param("keyword") String keyword) throws Exception {
        ModelAndView response = new ModelAndView();
        response.setViewName("connection/pub_list");

        List<Connection> listConnections = con_service.listAll(keyword);
        model.addAttribute("listConnections", listConnections);
        List<Category> listCategories = cate_service.listAll("");
        model.addAttribute("listCategories", listCategories);

        model.addAttribute("keyword", keyword);
        return response;
    }
    
    @RequestMapping(value = "/adminlist", method = RequestMethod.GET)
    public ModelAndView adminConnectionList(Model model, @Param("keyword") String keyword) throws Exception {
        ModelAndView response = new ModelAndView();
        response.setViewName("connection/list");

        List<Connection> listConnections = con_service.listAll(keyword);
        model.addAttribute("listConnections", listConnections);

        model.addAttribute("keyword", keyword);
        return response;
    }
    
    @RequestMapping(value = "/show", method = RequestMethod.GET)
    public ModelAndView show(@RequestParam(required = false) Integer id ) throws Exception {
        ModelAndView response = new ModelAndView();
        response.setViewName("connection/show");

        if ( id != null ) {
            // id has been passed to this form so it is an edit
        	Connection connection = con_service.findById(id);
            // populate the form bean with the data loaded from the database
            RegisterConnectionBean form = new RegisterConnectionBean();
            // since we loaded this from the database we know the id field
            form.setId(connection.getId());            
            form.setName(connection.getName());
            form.setCategory_id(connection.getCategory_id());//get from the hidden variable
            form.setDetails(connection.getDetails());
            form.setLocation(connection.getLocation());
            form.setDate(connection.getDate());
            form.setStart_time(connection.getStart_time());
            form.setEnd_time(connection.getEnd_time());
            form.setImage_url(connection.getImage_url());
            form.setHost_id(connection.getHost_id());//get from the session or hidden variable

            User host_user = user_service.findById(connection.getHost_id());
            response.addObject("connection", form);
            response.addObject("host_user", host_user);
            System.out.println("host_user" + host_user);
            
            //use rsvp_service to get the list of rsvp to show on this connection page
            List<Rsvp> listRsvp = rsvp_service.findByConnectionAndAttending(id, "yes");
            System.out.println("listRsvp" + listRsvp);
            response.addObject("listRsvp", listRsvp);

        }

        return response;
    }


    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public ModelAndView register(@RequestParam(required = false) Integer id ) throws Exception {
        ModelAndView response = new ModelAndView();
        response.setViewName("connection/new");

        if ( id != null ) {
            // id has been passed to this form so it is an edit
            Connection connection = con_service.findById(id);

            // populate the form bean with the data loaded from the database
            RegisterConnectionBean form = new RegisterConnectionBean();
            form.setName(connection.getName());

            // since we loaded this from the database we know the id field
            form.setId(connection.getId());
            
            //get category list from database
            List<Category> listCategories = cate_service.listAll("");

            response.addObject("formBeanKey", form);
            response.addObject("listCategories", listCategories);

        } else {
            // an id has not been passed so it is a create
            // there is no data from the database so give an empty form bean
            RegisterConnectionBean form = new RegisterConnectionBean();
            response.addObject("formBeanKey", form);
            
            //get category list from database
            List<Category> listCategories = cate_service.listAll("");

            response.addObject("formBeanKey", form);
            response.addObject("listCategories", listCategories);
            
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

			String currentPrincipalName = authentication.getName();//get the email from the authentication
			
			User current_user = user_service.findByEmail(currentPrincipalName);
            response.addObject("host_id", current_user.getId());


            System.out.println("<connectionController> The user email is " + currentPrincipalName + " has logged in.");
        }

        return response;
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public ModelAndView edit(@RequestParam(required = false) Integer id ) throws Exception {
        ModelAndView response = new ModelAndView();
        response.setViewName("connection/edit");

        System.out.println("edit GET " + id);
        if ( id != null ) {
            // id has been passed to this form so it is an edit
        	Connection connection = con_service.findById(id);
            // populate the form bean with the data loaded from the database
            RegisterConnectionBean form = new RegisterConnectionBean();
            // since we loaded this from the database we know the id field
            form.setId(connection.getId());            
            form.setName(connection.getName());
            form.setCategory_id(connection.getCategory_id());//get from the hidden variable
            form.setDetails(connection.getDetails());
            form.setLocation(connection.getLocation());
            form.setDate(connection.getDate());
            form.setStart_time(connection.getStart_time());
            form.setEnd_time(connection.getEnd_time());
            form.setImage_url(connection.getImage_url());
            form.setHost_id(connection.getHost_id());//get from the session or hidden variable


            response.addObject("formBeanKey", form);
            //get category list from database
            List<Category> listCategories = cate_service.listAll("");

            response.addObject("formBeanKey", form);
            response.addObject("listCategories", listCategories);
        } else {
            // an id has not been passed so it is a create
            // there is no data from the database so give an empty form bean
        	RegisterConnectionBean form = new RegisterConnectionBean();
            response.addObject("formBeanKey", form);
            
            //get category list from database
            List<Category> listCategories = cate_service.listAll("");

            response.addObject("formBeanKey", form);
            response.addObject("listCategories", listCategories);
            
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

			String currentPrincipalName = authentication.getName();//get the email from the authentication

            System.out.println("<connectionController> The user email is " + currentPrincipalName + " has logged in.");
        }

        return response;
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    @RequestMapping(value = "/edit", method = { RequestMethod.POST, RequestMethod.POST })
    public ModelAndView editSubmit(@Valid RegisterConnectionBean form, BindingResult errors) throws Exception {
        ModelAndView response = new ModelAndView();

        System.out.println(form);

        if (errors.hasErrors()) {
            for ( FieldError error : errors.getFieldErrors() ) {
                // add the error message to the errorMessages list in the form bean
                form.getErrorMessages().add(error.getDefaultMessage());
                LOG.debug("error field = " + error.getField() + " message = " + error.getDefaultMessage());
            }

            response.addObject("formBeanKey", form);
            //get category list from database
            List<Category> listCategories = cate_service.listAll("");

            response.addObject("formBeanKey", form);
            response.addObject("listCategories", listCategories);
            response.setViewName("connection/edit");

        } else {
            // there are no errors on the form submission so this is either a create or an update.

            // no matter what we need to create a new user object
            Connection connection;

            if ( form.getId() == null ) {
                // the id is not present in the form bean so we know this is a create
            	connection  = new Connection();
            } else {
                // this is an update so we need to load the connection from the database first
            	connection = con_service.findById(form.getId());
            }

            connection.setName(form.getName());
            connection.setCategory_id(form.getCategory_id());//get from the hidden variable
            connection.setDetails(form.getDetails());
            connection.setLocation(form.getLocation());
            connection.setDate(form.getDate());
            connection.setStart_time(form.getStart_time());
            connection.setEnd_time(form.getEnd_time());
            connection.setImage_url(form.getImage_url());
            connection.setHost_id(form.getHost_id());//get from the session or hidden variable

            con_service.save(connection);

            form.getSuccessMessages().add("Connection Updated Successfully!");
            response.addObject("formBeanKey", form);
            //if user is admin then go to connection/adminlist
            //otherwise go to connection/show
            
			/*
			 * Authentication authentication =
			 * SecurityContextHolder.getContext().getAuthentication();
			 * 
			 * String currentPrincipalName = authentication.getName();//get the email from
			 * the authentication
			 * 
			 * User current_user = user_service.findByEmail(currentPrincipalName);
			 * List<UserRole> userRoles = user_service.getUserRoles(current_user.getId());
			 * boolean admin = false; for(UserRole role:userRoles) { if (role.getUserRole()
			 * == "ADMIN") { admin = true; break; } } if (admin) {
			 * response.setViewName("redirect:/connection/adminlist");
			 * 
			 * } else { response.setViewName("redirect:/connection/show?id=" +form.getId());
			 * }
			 */

            
            response.setViewName("connection/show");
           
           
            form.setId(connection.getId());            
            form.setName(connection.getName());
            form.setCategory_id(connection.getCategory_id());//get from the hidden variable
            form.setDetails(connection.getDetails());
            form.setLocation(connection.getLocation());
            form.setDate(connection.getDate());
            form.setStart_time(connection.getStart_time());
            form.setEnd_time(connection.getEnd_time());
            form.setImage_url(connection.getImage_url());
            form.setHost_id(connection.getHost_id());//get from the session or hidden variable

            response.addObject("connection", form);
            User host_user = user_service.findById(connection.getHost_id());
            response.addObject("connection", form);
            response.addObject("host_user", host_user);
            System.out.println("host_user" + host_user);
            
            //use rsvp_service to get the list of rsvp to show on this connection page
            List<Rsvp> listRsvp = rsvp_service.findByConnectionAndAttending(form.getId(), "yes");
            System.out.println("listRsvp" + listRsvp);
            response.addObject("listRsvp", listRsvp);

        }

        return response;
    }
    
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    @RequestMapping(value = "/new", method = { RequestMethod.POST, RequestMethod.POST })
    public ModelAndView newSubmit(@Valid RegisterConnectionBean form, BindingResult errors) throws Exception {
        ModelAndView response = new ModelAndView();

        System.out.println(form);

        if (errors.hasErrors()) {
            for ( FieldError error : errors.getFieldErrors() ) {
                // add the error message to the errorMessages list in the form bean
                form.getErrorMessages().add(error.getDefaultMessage());
                LOG.debug("error field = " + error.getField() + " message = " + error.getDefaultMessage());
            }

            response.addObject("formBeanKey", form);
            //get category list from database
            List<Category> listCategories = cate_service.listAll("");

            response.addObject("formBeanKey", form);
            response.addObject("listCategories", listCategories);
            response.setViewName("connection/new");

        } else {
            // there are no errors on the form submission so this is either a create or an update.

            // no matter what we need to create a new connection object
            Connection connection;

            if ( form.getId() == null ) {
                // the id is not present in the form bean so we know this is a create
                connection  = new Connection();
            } else {
                // this is an update so we need to load the connection from the database first
            	connection = con_service.findById(form.getId());
            }

            connection.setName(form.getName());
            connection.setCategory_id(form.getCategory_id());//get from the hidden variable
            connection.setDetails(form.getDetails());
            connection.setLocation(form.getLocation());
            connection.setDate(form.getDate());
            connection.setStart_time(form.getStart_time());
            connection.setEnd_time(form.getEnd_time());
            connection.setImage_url(form.getImage_url());
            connection.setHost_id(form.getHost_id());//get from the session or hidden variable

            con_service.save(connection);
            form.getSuccessMessages().add("Connection Created Successfully!");
            response.addObject("formBeanKey", form);

            response.setViewName("redirect:/connection/list");

        }

        return response;
    }


    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public ModelAndView delete(@RequestParam Integer id ) throws Exception {
        ModelAndView response = new ModelAndView();

        //if user is admin then go to connection/list
        //otherwise to go to the user/profile
        response.setViewName("redirect:/connection/list");
        response.setViewName("redirect:/user/profile");

        
        Connection delete = con_service.findById(id);
        
        
        if ( delete != null ) {
        	
        	//search rsvp that are relationship with this connection
            List<Rsvp> listDelete = rsvp_service.findByConnection(id);
            if (listDelete != null ) {
            	for(Rsvp rsvp:listDelete) {
                	rsvp_service.delete(rsvp);
            	}
            }
            //after we deleted successfully all the records rsvp of this connection
            //we delete this connection.
        	con_service.delete(delete);
        }
        RegisterConnectionBean form = new RegisterConnectionBean();
        form.getSuccessMessages().add("Connection Deleted Successfully!");
        response.addObject("formBeanKey", form);
        return response;

    }
}
