/**
 * 
 */
$(document).ready(function() {
	let images = [];//hold the all images
	let titles = [];//hold the all caption
	let imagesMap = new Map();

	let duration = 3000;
	let captionFontSize = "2.0em";

	// get the image URL and id for each image
	$("li >img").each(function(index) {
		// get the image URL and title for each image
		//console.log(index + ":" + $(this).attr("href"));
		images.push($(this).attr("src"));
		titles.push($(this).attr("title"));

	});


	// preload the image for each link
	function preloadImages(images) {
		var i;
		for (i = 0; i < images.length; i++) {
			var tempImage = new Image();
			tempImage.src = images[i];
			tempImage.id = "image";
			tempImage.alt = "Image Gallery area";
			imagesMap.set(titles[i], tempImage);

		}

	}
	preloadImages(images);

	function logMapElements(value, key, map) {
		console.log(`map.get('${key}') = ${value.src}`)
	}
	imagesMap.forEach(logMapElements);

	//set up some parameter for the object slider
	$("#slider").bxSlider({
		auto: true,
		//minSlides: 2,
		//maxSlides: 2,
		randomStart: true, //1. start sliding from a random position every time
		captions: true, // 5. show the caption as text which will fetch the title
		speed: 3000,   // 6. the time between the automatic transitions to 3 seconds
		pagerType: "short", // 3. showing the pager as 1 image per time
		pagerSelector: "#id_pager", // 7. displayed in the paragraph with the id of "id_pager" below the list of images
		slideWidth: 500, // 4. the width of each slide is 500
		slideMargin: 20,
		autoDelay: 3000

	});
});