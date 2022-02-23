package com.events.database.form;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginFormBean extends FormBean {

    private String usernameFromForm;

    private String passwordFromForm;

	public String getUsernameFromForm() {
		return usernameFromForm;
	}

	public void setUsernameFromForm(String usernameFromForm) {
		this.usernameFromForm = usernameFromForm;
	}

	public String getPasswordFromForm() {
		return passwordFromForm;
	}

	public void setPasswordFromForm(String passwordFromForm) {
		this.passwordFromForm = passwordFromForm;
	}


    
}
