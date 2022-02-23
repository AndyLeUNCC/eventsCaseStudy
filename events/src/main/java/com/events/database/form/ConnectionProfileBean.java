package com.events.database.form;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


public class ConnectionProfileBean extends FormBean {

   
	private Integer id;   
    
    private String name;
    
    private String categoryName;

   

    public ConnectionProfileBean(Integer id, String name, String categoryName) {
		super();
		this.id = id;
		this.name = name;
		this.categoryName = categoryName;
	}



	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getCategoryName() {
		return categoryName;
	}



	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}



	@Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}
