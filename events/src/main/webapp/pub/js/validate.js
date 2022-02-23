/**
 * Validate fields on the SignUp and Login page
 */

/**
	Validate fields on the login page
 */
function validate_form_login() {
	if (!validate_email(document.login.email)) {
		return false;
	}

	if (!validate_password(document.login.password)) {
		return false;
	}
	
	return true;
	

}

/**
	Validate fields on the sign up page
 */

function validate_form_sign_up() {
	if (!validate_email(document.sign_up.email)) {
		return false;
	}

	if (!validate_password(document.sign_up.password)) {
		return false;
	}

	if (!match_pass()) {
		return false;
	}
	
	return true;
	

}

/**
	Go to User Profile page
 */
function goProfile() {
	document.location.href = "users_profile.html";
}
/**
Validate Email
*/
function validate_email(fld) {
	with (fld) {
		if (fld.value == "") {
			fld.style.background = 'Yellow';
			error = "You didn't enter a email.\n";
			alert(error);
			return false;

		} else {
			apos = value.indexOf("@");
			dotpos = value.lastIndexOf(".");
			if (apos < 1 || dotpos - apos < 2) {
				fld.style.background = 'Yellow';
				error = "You didn't enter a valid email.\n";
				alert(error);
				return false;
			} else {
				return true;
			}
		}

	}
}

/*
Validate Password
*/

function validate_password(fld) {
	var error = "";
	var illegalChars = /[\W_]/; // allow only letters and numbers

	if (fld.value == "") {
		fld.style.background = 'Yellow';
		error = "You didn't enter a password.\n";
		alert(error);
		return false;

	} else if ((fld.value.length < 1)) {
		error = "The password is at least three letter. \n";
		fld.style.background = 'Yellow';
		alert(error);
		return false;

	} else if (illegalChars.test(fld.value)) {
		error = "The password contains illegal characters.\n";
		fld.style.background = 'Yellow';
		alert(error);
		return false;

	} else if ((fld.value.search(/[a-zA-Z]+/) == -1) || (fld.value.search(/[0-9]+/) == -1)) {
		error = "The password must contain at least one numeral.\n";
		fld.style.background = 'Yellow';
		alert(error);
		return false;

	} else {
		fld.style.background = 'White';
	}

	return true;
}

/**
Check two password and repeat password match or not.
*/
function match_pass() {
	var firstpassword = document.sign_up.password.value;
	var secondpassword = document.sign_up.password_repeat.value;

	if (firstpassword == secondpassword) {
		return true;
	} else {
		alert("password must be same!");
		return false;
	}
}