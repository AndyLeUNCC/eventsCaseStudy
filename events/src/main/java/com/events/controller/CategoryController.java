package com.events.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.events.database.entity.Category;
import com.events.database.form.RegisterCategoryBean;
import com.events.service.CategoryService;



@Controller
@RequestMapping("/category")
@PreAuthorize("hasAuthority('ADMIN')")
public class CategoryController {

    // make sure are import the slf4j object imports for this line of code
    public static final Logger LOG = LoggerFactory.getLogger(CategoryController.class);

    @Autowired
    private CategoryService service;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView categoryList(Model model, @Param("keyword") String keyword) throws Exception {
        ModelAndView response = new ModelAndView();
        response.setViewName("category/list");

        List<Category> listCategories = service.listAll(keyword);
        model.addAttribute("listCategories", listCategories);
        model.addAttribute("keyword", keyword);
        return response;
    }


    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public ModelAndView register(@RequestParam(required = false) Integer id ) throws Exception {
        ModelAndView response = new ModelAndView();
        response.setViewName("category/new");

        if ( id != null ) {
            // id has been passed to this form so it is an edit
            Category category = service.findById(id);

            // populate the form bean with the data loaded from the database
            RegisterCategoryBean form = new RegisterCategoryBean();
            form.setName(category.getName());

            // since we loaded this from the database we know the id field
            form.setId(category.getId());

            response.addObject("formBeanKey", form);
        } else {
            // an id has not been passed so it is a create
            // there is no data from the database so give an empty form bean
        	RegisterCategoryBean form = new RegisterCategoryBean();
            response.addObject("formBeanKey", form);
        }

        return response;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public ModelAndView edit(@RequestParam(required = false) Integer id ) throws Exception {
        ModelAndView response = new ModelAndView();
        response.setViewName("category/edit");

        if ( id != null ) {
            // id has been passed to this form so it is an edit
        	Category category = service.findById(id);
            // populate the form bean with the data loaded from the database
            RegisterCategoryBean form = new RegisterCategoryBean();
            form.setName(category.getName());
            // since we loaded this from the database we know the id field
            form.setId(category.getId());

            response.addObject("formBeanKey", form);
        } else {
            // an id has not been passed so it is a create
            // there is no data from the database so give an empty form bean
        	RegisterCategoryBean form = new RegisterCategoryBean();
            response.addObject("formBeanKey", form);
        }

        return response;
    }

    @RequestMapping(value = "/edit", method = { RequestMethod.POST, RequestMethod.POST })
    public ModelAndView editSubmit(@Valid RegisterCategoryBean form, BindingResult errors) throws Exception {
        ModelAndView response = new ModelAndView();

        System.out.println(form);

        if (errors.hasErrors()) {
            for ( FieldError error : errors.getFieldErrors() ) {
                // add the error message to the errorMessages list in the form bean
                form.getErrorMessages().add(error.getDefaultMessage());
                LOG.debug("error field = " + error.getField() + " message = " + error.getDefaultMessage());
            }

            response.addObject("formBeanKey", form);
            response.setViewName("category/edit");

        } else {
            // there are no errors on the form submission so this is either a create or an update.

            // no matter what we need to create a new user object
            Category category;

            if ( form.getId() == null ) {
                // the id is not present in the form bean so we know this is a create
            	category  = new Category();
            } else {
                // this is an update so we need to load the category from the database first
            	category = service.findById(form.getId());
            }

            category.setName(form.getName());

            service.save(category);
            form.getSuccessMessages().add("Category Updated Successfully!");
            response.addObject("formBeanKey", form);
            response.setViewName("redirect:/category/list");
        }

        return response;
    }

    @RequestMapping(value = "/new", method = { RequestMethod.POST, RequestMethod.POST })
    public ModelAndView newSubmit(@Valid RegisterCategoryBean form, BindingResult errors) throws Exception {
        ModelAndView response = new ModelAndView();

        System.out.println(form);

        if (errors.hasErrors()) {
            for ( FieldError error : errors.getFieldErrors() ) {
                // add the error message to the errorMessages list in the form bean
                form.getErrorMessages().add(error.getDefaultMessage());
                LOG.debug("error field = " + error.getField() + " message = " + error.getDefaultMessage());
            }

            response.addObject("formBeanKey", form);
            response.setViewName("category/new");

        } else {
            // there are no errors on the form submission so this is either a create or an update.

            // no matter what we need to create a new category object
            Category category;

            if ( form.getId() == null ) {
                // the id is not present in the form bean so we know this is a create
                category  = new Category();
            } else {
                // this is an update so we need to load the category from the database first
            	category = service.findById(form.getId());
            }

            category.setName(form.getName());

            service.save(category);

            form.getSuccessMessages().add("Category Created Successfully!");
            response.addObject("formBeanKey", form);
            response.setViewName("redirect:/category/list");

        }

        return response;
    }



    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public ModelAndView delete(@RequestParam Integer id ) throws Exception {
        ModelAndView response = new ModelAndView();

        response.setViewName("redirect:/category/list");

        Category delete = service.findById(id);
        if ( delete != null ) {
            service.delete(delete);
        }
        RegisterCategoryBean form = new RegisterCategoryBean();
        form.getSuccessMessages().add("Category Deleted Successfully!");
        response.addObject("formBeanKey", form);
        return response;

    }
}
