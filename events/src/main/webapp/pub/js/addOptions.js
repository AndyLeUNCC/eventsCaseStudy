/**
 * Demo for using the jquery to add option into select tag 
   Using a set as one form of collections
 */

function addOption() {
	// Create a Set
	const options = new Set(["Entertainment", "Sport", "Music"]);

	// List all entries
	const iterator = options.entries();
	for (let entry of iterator) {
		optionText = entry[0].toUpperCase();
		optionValue = entry[0].toLowerCase();

		$('#category').append(`<option value="${optionValue}">
                                            ${optionText}
                                       </option>`);
	}

}