var BaiduMaps = {
	Map : {
		createMap : function(mapCanvasId, latitude, longitude, zoomLevel) {
			var map = new BMap.Map(mapCanvasId);

			var point = new BMap.Point(latitude, longitude);

			map.centerAndZoom(point, zoomLevel);

			var options = {
				type : BMAP_NAVIGATION_CONTROL_LARGE
			};

			map.addControl(new BMap.NavigationControl(options));
			map.addControl(new BMap.MapTypeControl());

			map.enableScrollWheelZoom();
			map.enableContinuousZoom();

			return map;
		},

		getDirections : function(map, directionStepsPanel, fromAddress, toAddress) {
			var driving = new BMap.DrivingRoute(map, {
				renderOptions : {
					map : map,
					autoViewport : true,
					panel : directionStepsPanel
				},
			});

			map.clearOverlays();

			driving.search(document.getElementById(fromAddress).value, document.getElementById(toAddress).value);
		},

		getLocations : function(map, locationsPanel, mapAddress) {
			var localSearch = new BMap.LocalSearch(map, {
				renderOptions : {
					map : map,
					panel : locationsPanel
				},
				pageCapacity : 8
			});

			map.clearOverlays();

			localSearch.search(document.getElementById(mapAddress).value);
		}
	}
};