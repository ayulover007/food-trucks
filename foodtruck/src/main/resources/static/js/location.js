$(function() {
	var map;
	var center_marker;
	var markerArray = new Array();
	google.maps.event.addDomListener(window, 'load', initialize);
	$("#search_btn").on("click", geoLocation);

	/**
	 * initial google map
	 */
	function initialize() {
		var mapOptions = {
			center : new google.maps.LatLng(37.77, -122.41),
			zoom : 14
		};
		map = new google.maps.Map(document.getElementById("map-canvas"),
			mapOptions);
	}

	function geoLocation(e) {
		var address = $("input[name='address']").val();
		var geocoder = new google.maps.Geocoder();
		geocoder.geocode({
			'address' : address
		}, function(results, status) {
			if (status == google.maps.GeocoderStatus.OK) {
				if(center_marker) {
					center_marker.setMap(null);
					center_marker = null;
				}
				if(markerArray.length > 0) {
					for(var i=0; i<markerArray.length; i++) {
						markerArray[i].setMap(null);
						markerArray = new Array();
					}
				}
				map.setCenter(results[0].geometry.location);
				center_marker = new google.maps.Marker({
					map : map,
					animation: google.maps.Animation.DROP,
					position : results[0].geometry.location
				});
				$.ajax({
					url : "/getFoodTrucks",
					type : "get",
					data : {
						"lantitude" : results[0].geometry.location.lat(),
						"longitude" : results[0].geometry.location.lng()
					},
					success : function(data) {
						if (data.errors != null) {
							alert("Search Failed");
						} else {
							var array = data.result;
							for (var i = 0; i < array.length; i++) {
								var marker = new google.maps.Marker({
									position : new google.maps.LatLng(array[i].latitude, array[i].longitude),
									map : map
								});
								markerArray.push(marker);
							}
						}
					},
					error : function(data) {
						console.log(data);
					}
				});
				console.log(results[0].geometry.location.lat());
			} else {
				alert("Geocode was not successful for the following reason: " + status);
			}
		});
		e.preventDefault();
	}

});