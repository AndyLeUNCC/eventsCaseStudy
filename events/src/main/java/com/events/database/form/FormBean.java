package com.events.database.form;


import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import java.util.ArrayList;
import java.util.List;


public class FormBean {

    // this list is populated by the controller with all error messages
    // in the binding result.
    private List<String> errorMessages = new ArrayList<>();

    private List<String> successMessages = new ArrayList<>();


	public List<String> getErrorMessages() {
		return errorMessages;
	}


	public void setErrorMessages(List<String> errorMessages) {
		this.errorMessages = errorMessages;
	}


	public List<String> getSuccessMessages() {
		return successMessages;
	}


	public void setSuccessMessages(List<String> successMessages) {
		this.successMessages = successMessages;
	}


	@Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}
