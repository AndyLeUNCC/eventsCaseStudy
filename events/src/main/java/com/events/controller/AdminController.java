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
import org.springframework.data.repository.query.Param;
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

import com.events.database.entity.User;
import com.events.database.form.ConnectionProfileBean;
import com.events.database.form.EditUserBean;
import com.events.database.form.RegisterFormBean;
import com.events.service.ConnectionService;
import com.events.service.UserService;

import lombok.extern.slf4j.Slf4j;


@Controller
@RequestMapping("/admin")
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminController {

	// make sure are import the slf4j object imports for this line of code
    public static final Logger LOG = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService service;
    
    @Autowired
    private ConnectionService conservice;

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public ModelAndView index(HttpServletRequest request, HttpSession session) throws Exception {
        ModelAndView response = new ModelAndView();
        response.setViewName("admin/home");
//        log.debug("");
        return response;
    }
    
    @RequestMapping(value = "/uploadFile", method = RequestMethod.GET)
    public ModelAndView upload(HttpServletRequest request, HttpSession session) throws Exception {
        ModelAndView response = new ModelAndView();
        response.setViewName("user/upload");
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
    public ModelAndView profile(Model model, HttpServletRequest request, HttpSession session) throws Exception {
        ModelAndView response = new ModelAndView();
        response.setViewName("user/profile");
        System.out.println("<AdminController> profile");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
        
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//
//		String currentPrincipalName = authentication.getName();//get the email from the authentication
//		
//		User current_user = service.findByEmail(currentPrincipalName);
//		System.out.println("current user is "+ current_user);
//        response.addObject("user", current_user);
        return response;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView userList(Model model, @Param("keyword") String keyword) throws Exception {
        ModelAndView response = new ModelAndView();
        response.setViewName("admin/list");

        List<User> listUsers = service.listAll(keyword);
        model.addAttribute("listUsers", listUsers);
        model.addAttribute("keyword", keyword);
        return response;
    }

    // 1) use the existing request mapping to do a firstname OR lastname search case insensitve

    // 2) implement the ability to search by first name AND last name case insensitive - this is a new form on the jsp page
    // I want you to make a new controller request mapping to handle the first name and last name search

    // 3) in both cases I want you to pass the incoming search parameter back to the jsp page using the model
    // I want to populate the search input with the incoming search parameter

    // 4) get your logback config setup and log out stuff to debug

//    @RequestMapping(value = "/list", method = RequestMethod.GET)
//    public ModelAndView userList(@RequestParam(required = false) String search, @RequestParam(required = false) String firstName,
//                                 @RequestParam(required = false) String lastName) throws Exception {
//        ModelAndView response = new ModelAndView();
//        response.setViewName("user/userList");
//
//        if ( ! StringUtils.isEmpty(search)) {
//            List<User> users = userDao.findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(search,search);
//            response.addObject("userListKey", users);
//            response.addObject("searchInput", search);
//        }
//
//        if ( !StringUtils.isEmpty(firstName) && ! StringUtils.isEmpty(lastName)) {
//            List<User> users = userDao.findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCase(firstName,lastName);
//            response.addObject("userListKey", users);
//        }
//        return response;
//    }

    // this method is responsible for populating the jsp page with the correct data so the page can render
    // if this method is called without the id parameter it will be a create and no database will be loaded
    // if it is called with an id it will be an edit and we need to load the user from the databse and
    // populate the form bean.
    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public ModelAndView register(@RequestParam(required = false) Integer id ) throws Exception {
        ModelAndView response = new ModelAndView();
        response.setViewName("admin/new");

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

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public ModelAndView edit(@RequestParam(required = false) Integer id ) throws Exception {
        ModelAndView response = new ModelAndView();
        response.setViewName("admin/edit");

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
            response.setViewName("redirect:/admin/list");
        }

        return response;
    }


    // this method describes what happens when a user submits the form to the back end
    // it handles both the create and update logic for saving the user input to the database
    @RequestMapping(value = "/new", method = { RequestMethod.POST, RequestMethod.POST })
    public ModelAndView newSubmit(@Valid RegisterFormBean form, BindingResult errors) throws Exception {
        ModelAndView response = new ModelAndView();

        System.out.println(form);

        if (errors.hasErrors()) {
            for ( FieldError error : errors.getFieldErrors() ) {
                // add the error message to the errorMessages list in the form bean
                form.getErrorMessages().add(error.getDefaultMessage());
                LOG.debug("error field = " + error.getField() + " message = " + error.getDefaultMessage());
            }

            response.addObject("formBeanKey", form);
            response.setViewName("admin/new");

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
            user.setPassword(form.getPassword());
            user.setUsername(form.getUsername());
            user.setPhone(form.getPhone());

            String encryptedPassword = passwordEncoder.encode(form.getPassword());
            user.setPassword(encryptedPassword);

            service.save(user);

            form.getSuccessMessages().add("User Account Created Successfully!");
            response.addObject("formBeanKey", form);
            response.setViewName("redirect:/admin/list");

        }

        return response;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public ModelAndView delete(@RequestParam Integer id ) throws Exception {
        ModelAndView response = new ModelAndView();

        response.setViewName("redirect:/admin/list");

        User delete = service.findById(id);
        if ( delete != null ) {
            service.delete(delete);
            System.out.println("<Admin Controller> delete user id  " + id);
        }
        RegisterFormBean form = new RegisterFormBean();
        form.getSuccessMessages().add("User Account Delete Successfully!");
        response.addObject("formBeanKey", form);
        return response;

    }
}
