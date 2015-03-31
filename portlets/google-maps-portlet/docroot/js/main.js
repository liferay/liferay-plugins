/* global google */

AUI.add(
	'liferay-google-maps',
	function(A) {
		var Lang = A.Lang;

		var KEY_DOWN_ENTER = 'down:13';

		var MAP_TYPE_HYBRID = 2;

		var MAP_TYPE_ROADMAP = 0;

		var MAP_TYPE_SATELLITE = 1;

		var MAP_TYPE_TERRAIN = 3;

		var STR_CLICK = 'click';

		var STR_DIRECTION_ADDRESS = 'directionsAddress';

		var STR_KEY = 'key';

		var STR_MAP_ADDRESS = 'mapAddress';

		var STR_TRAVELING_MODE = 'travelingMode';

		var WIN = A.config.win;

		var GoogleMaps = A.Component.create(
			{
				ATTRS: {
					directionsAddress: {
						validator: Lang.isString
					},

					googleMapsURL: {
						validator: Lang.isString,
						value: 'http://maps.google.com/maps/api/js'
					},

					languageId: {
						validator: Lang.isString
					},

					mapAddress: {
						getter: '_getMapAddress',
						validator: Lang.isString
					},

					mapInputEnabled: {
						validator: Lang.isBoolean,
						value: false
					},

					mapParams: {
						validator: Lang.isObject,
						value: {
							mapTypeId: MAP_TYPE_ROADMAP,
							zoom: 8
						}
					},

					namespace: {
						validator: Lang.isString
					},

					portletId: {
						validator: Lang.isNumber
					},

					showDirectionSteps: {
						validator: Lang.isBoolean,
						value: false
					}
				},

				AUGMENTS: [Liferay.PortletBase],

				EXTENDS: A.Widget,

				NAME: 'googlemaps',

				prototype: {
					initializer: function(config) {
						var instance = this;

						instance._markersArray = [];
					},

					renderUI: function() {
						var instance = this;

						if (instance._isGoogleMapLoaded()) {
							instance._renderMap();
						}
						else {
							instance._initGoogleMaps();
						}
					},

					bindUI: function() {
						var instance = this;

						var eventHandles = [];

						var getDirectionsButtonNode = instance.byId('getDirectionsButton');

						if (getDirectionsButtonNode) {
							eventHandles.push(
								getDirectionsButtonNode.on(STR_CLICK, instance._getDirections, instance)
							);
						}

						var travelingModeNode = instance.byId(STR_TRAVELING_MODE);

						if (travelingModeNode) {
							eventHandles.push(
								travelingModeNode.on('change', instance._getDirections, instance)
							);
						}

						var getMapButtonNode = instance.byId('getMapButton');

						if (getMapButtonNode) {
							eventHandles.push(
								getMapButtonNode.on(STR_CLICK, instance._getMap, instance)
							);
						}

						var directionsAddressNode = instance.byId(STR_DIRECTION_ADDRESS);

						if (directionsAddressNode) {
							eventHandles.push(
								directionsAddressNode.on(STR_KEY, A.bind(instance._onDirectionsAddressKeyDown, instance), KEY_DOWN_ENTER)
							);
						}

						var mapAddressNode = instance.byId(STR_MAP_ADDRESS);

						if (mapAddressNode) {
							eventHandles.push(
								mapAddressNode.on(STR_KEY, A.bind(instance._onMapAddressKeyDown, instance), KEY_DOWN_ENTER)
							);
						}

						var openLinkNode = instance.byId('openInGoogleMapsLink');

						if (openLinkNode) {
							eventHandles.push(
								openLinkNode.on(STR_CLICK, instance._onOpenInGoogleMapsClick, instance)
							);
						}

						eventHandles.push(Liferay.on(instance.get('portletId') + ':portletRefreshed', A.bind(instance.destructor, instance)));

						instance._eventHandles = eventHandles;
					},

					destructor: function() {
						var instance = this;

						A.Array.invoke(instance._eventHandles, 'detach');
					},

					_attachInstructionText: function(marker, text) {
						var instance = this;

						google.maps.event.addListener(marker, STR_CLICK, A.rbind(instance._onMarkerClick, instance, marker, text));
					},

					_getAddress: function(address) {
						var instance = this;

						instance._geocoder.geocode(
							{
								address: address
							},
							A.rbind(instance._onAddressGeocoded, instance, address)
						);
					},

					_getDirections: function() {
						var instance = this;

						var mapAddress = instance.get(STR_MAP_ADDRESS);

						var directionsAddress = instance.byId(STR_DIRECTION_ADDRESS).val();

						var travelingMode = instance.byId(STR_TRAVELING_MODE).val();

						var request = {
							destination: directionsAddress,
							origin: mapAddress,
							travelMode: google.maps.TravelMode[travelingMode.toUpperCase()]
						};

						instance._removeMarkers();

						instance._directionsService.route(request, A.rbind(instance._onRoute, instance, directionsAddress));
					},

					_getGoogleMapType: function(type) {
						var mapType = google.maps.MapTypeId;

						var googleMapsType = mapType.ROADMAP;

						if (type == MAP_TYPE_SATELLITE) {
							googleMapsType = mapType.SATELLITE;
						}
						else if (type == MAP_TYPE_HYBRID) {
							googleMapsType = mapType.HYBRID;
						}
						else if (type == MAP_TYPE_TERRAIN) {
							googleMapsType = mapType.TERRAIN;
						}

						return googleMapsType;
					},

					_getMap: function() {
						var instance = this;

						var mapAddress = instance.get(STR_MAP_ADDRESS);

						instance._removeMarkers();

						instance._getAddress(mapAddress);

						return mapAddress;
					},

					_getMapAddress: function(value) {
						var instance = this;

						if (instance.get('mapInputEnabled')) {
							var mapAddressNode = instance.byId(STR_MAP_ADDRESS);

							var mapAddress = mapAddressNode.val();

							if (mapAddress) {
								value = mapAddress;
							}
						}

						return value;
					},

					_initGoogleMaps: function() {
						var instance = this;

						Liferay.namespace('GOOGLE_MAPS').onGoogleMapsLoaded = A.bind(instance._renderMap, instance);

						var googleMapsURL = instance.get('googleMapsURL');

						googleMapsURL = googleMapsURL + '?sensor=true&language=' + instance.get('languageId') + '&callback=Liferay.GOOGLE_MAPS.onGoogleMapsLoaded';

						A.Get.script(googleMapsURL);
					},

					_isDirectionFilled: function() {
						var instance = this;

						return instance.get(STR_DIRECTION_ADDRESS) && instance.byId(STR_DIRECTION_ADDRESS).val();
					},

					_isGoogleMapLoaded: function() {
						return (typeof google !== 'undefined' && A.Lang.isObject(google.maps));
					},

					_onAddressGeocoded: function(results, status, address) {
						var instance = this;

						var googleMaps = google.maps;

						if (status == googleMaps.GeocoderStatus.OK) {
							var location = results[0].geometry.location;

							instance._map.setCenter(location);

							if (!instance._marker) {
								instance._marker = new googleMaps.Marker(
									{
										map: instance._map,
										position: location
									}
								);
							}
							else {
								instance._marker.setMap(instance._map);

								instance._marker.setPosition(location);
							}

							if (!instance._infoWindow) {
								instance._infoWindow = new googleMaps.InfoWindow(
									{
										content: address
									}
								);
							}
							else {
								instance._infoWindow.setContent(address);
							}

							instance._infoWindow.setPosition(location);

							instance._infoWindow.open(instance._map, instance._marker);
						}
					},

					_onDirectionsAddressKeyDown: function(event) {
						var instance = this;

						event.preventDefault();

						instance._getDirections();
					},

					_onMapAddressKeyDown: function(event) {
						var instance = this;

						event.preventDefault();

						if (instance._isDirectionFilled()) {
							instance._getDirections();
						}
						else {
							instance._getMap();
						}
					},

					_onMarkerClick: function(event, marker, text) {
						var instance = this;

						var stepDisplay = instance._stepDisplay;

						stepDisplay.setContent(text);

						stepDisplay.open(instance._map, marker);
					},

					_onOpenInGoogleMapsClick: function(event) {
						var instance = this;

						event.preventDefault();

						var mapAddress = instance.get(STR_MAP_ADDRESS);

						var directionsAddress = instance.byId(STR_DIRECTION_ADDRESS).val();

						var encodedMapAddress = encodeURIComponent(mapAddress);

						var url = 'http://maps.google.com/maps?q=' + encodedMapAddress;

						if (directionsAddress) {
							url = 'http://maps.google.com/maps?f=q&hl=en&q=' + encodedMapAddress + '+to+' + encodeURIComponent(directionsAddress);
						}

						WIN.open(url);
					},

					_onRoute: function(response, status, directionsAddress) {
						var instance = this;

						if (status == google.maps.DirectionsStatus.OK) {
							var warnings = instance.byId('warningsPanel');

							warnings.html(response.routes[0].warnings);

							instance._directionsDisplay.setDirections(response);

							if (instance.get('showDirectionSteps')) {
								instance._showSteps(response);
							}
						}
					},

					_removeMarkers: function() {
						var instance = this;

						var markersArray = instance._markersArray;

						var length = markersArray.length;

						for (var i = 0; i < length; i++) {
							markersArray[i].setMap(null);
						}

						if (instance._marker) {
							instance._marker.setMap(null);
						}
					},

					_renderMap: function() {
						var instance = this;

						var mapParams = instance.get('mapParams');

						mapParams = A.merge(
							mapParams,
							{
								mapTypeId: instance._getGoogleMapType(mapParams.mapTypeId)
							}
						);

						var googleMaps = google.maps;

						instance._map = new googleMaps.Map(instance.byId('map').getDOMNode(), mapParams);

						instance._geocoder = new googleMaps.Geocoder();

						instance._directionsService = new googleMaps.DirectionsService();

						instance._directionsDisplay = new googleMaps.DirectionsRenderer(
							{
								map: instance._map
							}
						);

						instance._stepDisplay = new googleMaps.InfoWindow();

						if (instance._isDirectionFilled()) {
							instance._getDirections();
						}
						else {
							instance._getAddress(instance.get(STR_MAP_ADDRESS));
						}
					},

					_showSteps: function(directionResult) {
						var instance = this;

						var markersArray = instance._markersArray;

						// For each step, place a marker, and add the text to the marker's info window.

						var myRoute = directionResult.routes[0].legs[0];

						var stepsCount = myRoute.steps.length;

						var googleMaps = google.maps;

						for (var i = 0; i < stepsCount; i++) {
							var marker = new googleMaps.Marker(
								{
									map: instance._map,
									position: myRoute.steps[i].start_point
								}
							);

							instance._attachInstructionText(marker, myRoute.steps[i].instructions);

							markersArray.push(marker);
						}
					}
				}
			}
		);

		GoogleMaps.MAP_TYPE_HYBRID = MAP_TYPE_HYBRID;
		GoogleMaps.MAP_TYPE_ROADMAP = MAP_TYPE_ROADMAP;
		GoogleMaps.MAP_TYPE_SATELLITE = MAP_TYPE_SATELLITE;
		GoogleMaps.MAP_TYPE_TERRAIN = MAP_TYPE_TERRAIN;

		Liferay.Portlet.GoogleMaps = GoogleMaps;
	},
	'',
	{
		requires: ['get', 'liferay-portlet-base']
	}
);