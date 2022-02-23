package com.events.database.form;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.validation.constraints.*;

public class RegisterConnectionBean extends FormBean {

    // this is a hidden data value and is only needed to distinguish an edit from a create
    private Integer id;   

    //@NotEmpty(message = "Category name is required")
    private Integer category_id;
    
    @NotEmpty(message = "Event name is required")
    private String name;
    
    @NotEmpty(message = "Details is required")
    private String details;
    
    @NotEmpty(message = "Location is required")
    private String location;
    
    
    @NotEmpty(message = "Date is required")
    private String date;
    
    
    @NotEmpty(message = "Start time is required")
    private String start_time;
    
    @NotEmpty(message = "End time is required")
    private String end_time;
    
    private String image_url;
    
    //@NotEmpty(message = "Host is required")
    private Integer host_id;


    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCategory_id() {
		return category_id;
	}

	public void setCategory_id(Integer category_id) {
		this.category_id = category_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getStart_time() {
		return start_time;
	}

	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}

	public String getEnd_time() {
		return end_time;
	}

	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}

	public String getImage_url() {
		return image_url;
	}

	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}

	public Integer getHost_id() {
		return host_id;
	}

	public void setHost_id(Integer host_id) {
		this.host_id = host_id;
	}


	@Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}
