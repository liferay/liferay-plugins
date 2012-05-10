AUI.add(
	'liferay-google-maps',
	function(A) {
		var KEY_DONW_ENTER = 'down:13';

		var STR_CLICK = 'click';

		var STR_DIRECTION_ADDRESS = 'directionsAddress';

		var STR_KEY = 'key';

		var STR_MAP_ADDRESS = 'mapAddress';

		var STR_TRAVELLING_MODE = 'travellingMode';

		var GoogleMaps = A.Component.create(
			{
				AUGMENTS: [Liferay.PortletBase],

				EXTENDS: A.Base,

				NAME: 'googlemaps',

				prototype: {
					initializer: function(config) {
						var instance = this;

						instance._markersArray = [];

						instance._map = new google.maps.Map(instance.byId('map').getDOMNode(), config.mapParams);

						instance._geocoder = new google.maps.Geocoder();

						instance._directionsService = new google.maps.DirectionsService();

						instance._directionsDisplay = new google.maps.DirectionsRenderer(
							{
								map: instance._map
							}
						);

						instance._stepDisplay = new google.maps.InfoWindow();

						var eventHandles = [];

						var getDirectionsButtonNode = instance.byId('getDirectionsButton');

						if (getDirectionsButtonNode) {
							eventHandles.push(
								getDirectionsButtonNode.on(STR_CLICK, instance._getDirections, instance)
							);
						}

						var travellingModeNode = instance.byId(STR_TRAVELLING_MODE);

						if (travellingModeNode) {
							eventHandles.push(
								travellingModeNode.on('change', instance._getDirections, instance)
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
								directionsAddressNode.on(STR_KEY, A.bind(instance._onDirectionsAddressKeyDown, instance), KEY_DONW_ENTER)
							);
						}

						var mapAddressNode = instance.byId(STR_MAP_ADDRESS);

						if (mapAddressNode) {
							eventHandles.push(
								mapAddressNode.on(STR_KEY, A.bind(instance._onMapAddressKeyDown, instance), KEY_DONW_ENTER)
							);
						}

						var openLinkNode = instance.byId('openInGoogleMapsLink');

						if (openLinkNode) {
							eventHandles.push(
								openLinkNode.on(STR_CLICK, instance._onOpenInGoogleMapsClick, instance)
							);
						}

						eventHandles.push(Liferay.on(config.portletId + ':portletRefreshed', A.bind(instance.destructor, instance)));

						instance._config = config;

						if (config.directionsAddress) {
							instance._getDirections();
						}
						else {
							instance._getAddress(config.mapAddress);
						}

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

						var mapAddress = instance._getMapAddress();

						var directionsAddress = instance.byId(STR_DIRECTION_ADDRESS).val();

						var travellingMode = instance.byId(STR_TRAVELLING_MODE).val();

						var request = {
							destination: directionsAddress,
							origin: mapAddress,
							travelMode: google.maps.TravelMode[travellingMode.toUpperCase()]
						};

						instance._removeMarkers();

						instance._directionsService.route(request, A.rbind(instance._onRoute, instance, directionsAddress));
					},

					_getMap: function() {
						var instance = this;

						var mapAddress = instance._getMapAddress();

						instance._removeMarkers();

						instance._getAddress(mapAddress);

						instance._saveMapAddress(mapAddress);

						return mapAddress;
					},

					_getMapAddress: function() {
						var instance = this;

						var mapAddress = instance._config.mapAddress;

						if (instance._config.mapInputEnabled) {
							var mapAddressNode = instance.byId(STR_MAP_ADDRESS);

							mapAddress = mapAddressNode.val();
						}
							
						return mapAddress;
					},

					_onAddressGeocoded: function(results, status, address) {
						var instance = this;

						if (status == google.maps.GeocoderStatus.OK) {
							var location = results[0].geometry.location;

							instance._map.setCenter(location);

							if (!instance._marker) {
								instance._marker = new google.maps.Marker(
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
								instance._infoWindow = new google.maps.InfoWindow(
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

						instance._getMap();
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

						var mapAddress = instance._getMapAddress();

						var directionsAddress = instance.byId(STR_DIRECTION_ADDRESS).val();

						if (directionsAddress) {
							window.open('http://maps.google.com/maps?f=q&hl=en&q=' + encodeURIComponent(mapAddress) + '+to+' + encodeURIComponent(directionsAddress));
						}
						else {
							window.open('http://maps.google.com/maps?q=' + encodeURIComponent(mapAddress));
						}
					},

					_onRoute: function(response, status, directionsAddress) {
						var instance = this;

						if (status == google.maps.DirectionsStatus.OK) {
							var warnings = instance.byId('warningsPanel');

							warnings.html(response.routes[0].warnings);

							instance._directionsDisplay.setDirections(response);

							if (instance._config.showDirectionSteps) {
								instance._showSteps(response);
							}

							instance._saveDirectionsAddress(directionsAddress);
						}
					},

					_removeMarkers: function() {
						var instance = this;

						var length = instance._markersArray.length;

						for (var i = 0; i < length; i++) {
							instance._markersArray[i].setMap(null);
						}

						if (instance._marker) {
							instance._marker.setMap(null);
						}
					},

					_saveDirectionsAddress: function(address) {
						var instance = this;

						A.io.request(
							instance._config.saveDirectionsAddressURL,
							{
								data: {
									directionsAddress: address
								}
							}
						);
					},

					_saveMapAddress: function(address) {
						var instance = this;

						A.io.request(
							instance._config.saveMapAddressURL,
							{
								data: {
									mapAddress: address
								}
							}
						);
					},

					_showSteps: function(directionResult) {
						var instance = this;

						// For each step, place a marker, and add the text to the marker's info window.

						var myRoute = directionResult.routes[0].legs[0];

						var stepsCount = myRoute.steps.length;

						for (var i = 0; i < stepsCount; i++) {
							var marker = new google.maps.Marker(
								{
									position: myRoute.steps[i].start_point,
									map: instance._map
								}
							);

							instance._attachInstructionText(marker, myRoute.steps[i].instructions);

							instance._markersArray.push(marker);
						}
					}
				}
			}
		);

		Liferay.Portlet.GoogleMaps = GoogleMaps;
	},
	'',
	{
		requires: ['aui-dialog','aui-io-request','liferay-portlet-base']
	}
);