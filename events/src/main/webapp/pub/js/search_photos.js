/**
 * Using the API search photos from photo services of flickr
 */

$(document).ready(function() {
	search_photos("sakura");

	/**
	 * Call this function when the user type something into the input box has id = myInput
	 */
	$("#myInput").on("keyup", function() {
		var keyword = $(this).val().toLowerCase();

		//transfer the search keyword to this function
		// for accessing the public photo service of flickr
		search_photos(keyword)

	});

	/**
	 * This function do the search photos based on the transfer keyword.
	 * By using the API from flickr, it return the list of photos and show to the browser
	 * @param keyword
	 */
	function search_photos(keyword) {
		//set up the URL to access the photo services of flickr
		var url = "https://api.flickr.com/services/feeds/photos_public.gne?" +
			"method=flickr.photos.search&format=json&jsoncallback=?&tags=" + keyword;

		//do the call Ajax to return the data from flickr's API
		// the return data has JSON format
		$.getJSON(url, function(data) {
			var html = "";

			//with each of data element, output to html format and set the all html result to the place for showing
			$.each(data.items, function(i, item) {
				//console.log("item:"+ item.media.m );
				html += "<a href = '" + item.media.m + "'" +
					"title ='" + item.title + "'" +
					"rel = lightbox[vecta]>";
				html += "<img src='" + item.media.m + "'" +
					" width=120 height=100";
				html += "</a>";
			});
			$("#photos").html(html);
		});
	}



});