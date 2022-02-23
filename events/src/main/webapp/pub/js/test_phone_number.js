/**
 * 
 */
function validate_phone_number() {
	if (document.getElementById("PHN").value == "") {
		alert("Enter your Phone Number");
		document.getElementById("PHN").focus();
		return false;
	} else { 
	
		var PHN = document.getElementById("PHN").value; 
		alert(PHN);
		//let re = new RegExp('/^[+]*[(]{0,1}[0-9]{1,3}[)]{0,1}[-\s\./0-9]*$/g');
		var phoneRe = /^[(]{0,1}[0-9]{3}[)]{0,1}[-\s\.]{0,1}[0-9]{3}[-\s\.]{0,1}[0-9]{4}$/;
		
		if (phoneRe.test(PHN) === false) {
			alert("Enter your Phone number in (###)-###-#### format");
			document.getElementById("PHN").focus();
			console.log("invalid number = " + document.getElementById("PHN").value)
			return false;
		}
	
	}
	return true;

}
