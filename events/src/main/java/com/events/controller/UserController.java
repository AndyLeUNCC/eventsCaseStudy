package com.events.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.events.database.entity.Category;
import com.events.database.entity.Connection;
import com.events.database.entity.User;
import com.events.database.form.ConnectionProfileBean;
import com.events.database.form.EditUserBean;
import com.events.database.form.IConnectionProfile;
import com.events.database.form.IConnectionProfileRsvp;
import com.events.database.form.RegisterFormBean;
import com.events.service.CategoryService;
import com.events.service.ConnectionService;
import com.events.service.UserService;



@Controller
@RequestMapping("/user")
@PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
public class UserController {

    // make sure are import the slf4j object imports for this line of code
    public static final Logger LOG = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService service;
    
    @Autowired
    private ConnectionService conService;
    
    @Autowired
    private CategoryService cateService;


    @RequestMapping(value = "/uploadFile", method = RequestMethod.GET)
    public ModelAndView upload(HttpServletRequest request, HttpSession session) throws Exception {
        ModelAndView response = new ModelAndView();
        response.setViewName("user/upload");
        return response;
    }
    
    @RequestMapping(value = "/searchFile", method = RequestMethod.GET)
    public ModelAndView searchFile(HttpServletRequest request, HttpSession session) throws Exception {
        ModelAndView response = new ModelAndView();
        response.setViewName("user/searchFile");
        return response;
    }
    //fileUploadSubmit
    /**
     *
     * @param file
     * @param title
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/fileUploadSubmit", method = RequestMethod.POST)
    public ModelAndView uploadSubmit(@RequestParam MultipartFile file, @RequestParam(required = false) String title) throws Exception {
        // figure out what the default OS temp directory is
        String tempdir = System.getProperty("java.io.tmpdir") + File.separator + "perscholas";
    	LOG.debug("Temp directory path : " + tempdir.toString());

        // create a new file object for this directory and see if the directory exists
        File saveFileDirectory = new File(tempdir);
        if (! saveFileDirectory.exists() ) {
            // it does not exist so make the temp directory using mkdirs
            // mkdirs will create any parent folders needed
            //log.debug("Creating temp folder for file upload : " + saveFileDirectory.getAbsolutePath());
        	LOG.debug("Creating temp folder for file upload : " + saveFileDirectory.getAbsolutePath());
            saveFileDirectory.mkdirs();
        }

        // build the full path to file we want to save
        String saveFileName = saveFileDirectory.getAbsolutePath() + File.separator + file.getOriginalFilename();
        File targetFile = new File(saveFileName);

        if ( targetFile.exists() ) {
            throw new Exception("Unable to save uploaded file " + file.getOriginalFilename() + " because a file with that name already exists");
        }
        // save the uploaded file to the hard drive using commons io
        // this will take the uploaded file stream and write it to the target file on the disk
        FileUtils.copyInputStreamToFile(file.getInputStream(), targetFile);

       // log.debug("Uploaded file saved to : " + targetFile.getAbsolutePath());
    	LOG.debug("Uploaded file saved to : " + targetFile.getAbsolutePath());


        ModelAndView response = new ModelAndView();
        
        response.setViewName("user/upload");
        return response;
    }
    
    @ResponseBody
    @RequestMapping(value = "/user/fileList", method = RequestMethod.GET)
    public List<String> fileList() throws Exception {
        String tempdir = System.getProperty("java.io.tmpdir") + File.separator + "perscholas";
        File saveFileDirectory = new File(tempdir);

        List<String> files = new ArrayList<>();
        for ( File file : saveFileDirectory.listFiles() ) {
            LOG.debug("File name : " + file.getAbsolutePath());
            files.add(file.getAbsolutePath());
        }

        return files;
    }

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public ModelAndView index(Model model, HttpServletRequest request, HttpSession session) throws Exception {
        ModelAndView response = new ModelAndView();
        response.setViewName("user/profile");
        
        //get the user information from the spring security here
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		String currentPrincipalName = authentication.getName();//get the email from the authentication
		
		User current_user = service.findByEmail(currentPrincipalName);
		//set the user session
        session.setAttribute("user", current_user);
        //response.addObject("userProfile", user);

        //create ConnectionProfileBean list object to show the list of connection that user has created
        List<IConnectionProfile> listConnectionProfile = new ArrayList<>();
        listConnectionProfile = conService.findConnectionsByHostId(current_user.getId());
        
        List<IConnectionProfileRsvp> listRsvp = conService.findRsvpByUser(current_user.getId());
        //System.out.println(listRsvp);
        
//        List<Connection> listConnections = conService.findByHostId(current_user.getId());
//        for(Connection con : listConnections ) {
//        	Category cate = cateService.findById(con.getCategory_id());
//        	listConnectionProfile.add(new ConnectionProfileBean(con.getId(),con.getName(), cate.getName()));
//        }
    
        //how to mapping the Object into ConnectionProfileBean
        
		/*
		 * for(Object connection : listConnections) { System.out.println(connection);
		 * 
		 * }
		 */
        model.addAttribute("listConnectionProfile", listConnectionProfile);
        model.addAttribute("listRsvp", listRsvp);

        System.out.println(listConnectionProfile);

        return response;
    }
    
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public ModelAndView edit(@RequestParam(required = false) Integer id ) throws Exception {
        ModelAndView response = new ModelAndView();
        response.setViewName("user/edit");

        if ( id != null ) {
            // id has been passed to this form so it is an edit
            User user = service.findById(id);

            // populate the form bean with the data loaded from the database
            RegisterFormBean form = new RegisterFormBean();
            form.setEmail(user.getEmail());
            form.setFirstName(user.getFirstName());
            form.setLastName(user.getLastName());
            form.setUsername(user.getUsername());
            form.setPassword(user.getPassword());
            form.setPhone(user.getPhone());
            // since we loaded this from the database we know the id field
            form.setId(user.getId());

            response.addObject("formBeanKey", form);
        } else {
            // an id has not been passed so it is a create
            // there is no data from the database so give an empty form bean
            RegisterFormBean form = new RegisterFormBean();
            response.addObject("formBeanKey", form);
        }

        return response;
    }

    @RequestMapping(value = "/edit", method = { RequestMethod.POST, RequestMethod.POST })
    public ModelAndView editSubmit(@Valid EditUserBean form, BindingResult errors) throws Exception {
        ModelAndView response = new ModelAndView();

        System.out.println(form);

        if (errors.hasErrors()) {
            for ( FieldError error : errors.getFieldErrors() ) {
                // add the error message to the errorMessages list in the form bean
                form.getErrorMessages().add(error.getDefaultMessage());
                LOG.debug("error field = " + error.getField() + " message = " + error.getDefaultMessage());
            }

            response.addObject("formBeanKey", form);
            response.setViewName("user/edit");

        } else {
            // there are no errors on the form submission so this is either a create or an update.

            // no matter what we need to create a new user object
            User user;

            if ( form.getId() == null ) {
                // the id is not present in the form bean so we know this is a create
                user  = new User();
            } else {
                // this is an update so we need to load the user from the database first
                user = service.findById(form.getId());
            }

            user.setEmail(form.getEmail());
            user.setFirstName(form.getFirstName());
            user.setLastName(form.getLastName());
            user.setPhone(form.getPhone());

            if ( !StringUtils.isEmpty(form.getPassword())) {
            	  String encryptedPassword = passwordEncoder.encode(form.getPassword());
                  user.setPassword(encryptedPassword);

            }
 
            user.setUsername(form.getUsername());

          
            service.save(user);

            form.getSuccessMessages().add("User Account Updated Successfully!");
            response.addObject("formBeanKey", form);
            response.setViewName("/user/profile");
        }

        return response;
    }










    
}
