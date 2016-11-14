$(function() {
	google.maps.event.addDomListener(window, 'load', initialize);

	function initialize() {
		var mapOptions = {
			center : new google.maps.LatLng(-25.363, 131.044),
			zoom : 8
		};
		var map = new google.maps.Map(document.getElementById("map-canvas"),
				mapOptions);
		map.addListener('center_changed', function() {
			console.log(map.getCenter());
		});
	}
});