AUI.add(
	'liferay-google-maps',
	function(A) {
		var DOC                   = A.config.doc;
		var Lang                  = A.Lang;
		var WIN                   = A.config.win;
		
		var KEY_DOWN_ENTER        = 'down:13';

		var MAP_TYPE_HYBRID       = 2;

		var MAP_TYPE_ROADMAP      = 0;

		var MAP_TYPE_SATELLITE    = 1;

		var MAP_TYPE_TERRAIN      = 3;

		var STR_CLICK             = 'click';

		var STR_DIRECTION_ADDRESS = 'directionsAddress';

		var STR_KEY               = 'key';

		var STR_MAP_ADDRESS       = 'mapAddress';
		
		var STR_TRAVELLING_MODE   = 'travellingMode';

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

					bindUI: function() {
						var instance = this;

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

					renderUI: function() {
						var instance = this;

						if (instance._isGoogleMapLoaded()) {
							instance._renderMap();
						}
						else {
							instance._initGoogleMaps();
						}
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

					_getGoogleMapType: function(type) {
						var mapType = google.maps.MapTypeId;

						var googleMapsType = mapType.ROADMAP;

						if (type == MAP_TYPE_SATELLITE) {
							googleMapsType = mapType.SATELLITE;
						}
						else if (MAP_TYPE_HYBRID) {
							googleMapsType = mapType.HYBRID;
						}
						else if (MAP_TYPE_TERRAIN) {
							googleMapsType = mapType.TERRAIN;
						}

						return googleMapsType;
					},

					_getMap: function() {
						var instance = this;

						var mapAddress = instance._getMapAddress();

						instance._removeMarkers();

						instance._getAddress(mapAddress);

						return mapAddress;
					},

					_getMapAddress: function() {
						var instance = this;

						var mapAddress = instance.get(STR_MAP_ADDRESS);

						if (instance.get('mapInputEnabled')) {
							var mapAddressNode = instance.byId(STR_MAP_ADDRESS);

							mapAddress = mapAddressNode.val();
						}
							
						return mapAddress;
					},

					_initGoogleMaps: function() {
						var instance = this;

						Liferay.namespace('GOOGLE_MAPS')['onGoogleMapsLoaded'] = A.bind(instance._renderMap, instance);

						var script = document.createElement('script');

						script.type = 'text/javascript';

						var googleMapsURL = instance.get('googleMapsURL');

						script.src = googleMapsURL + '?sensor=true&language=' + instance.get('languageId') + '&callback=Liferay.GOOGLE_MAPS.onGoogleMapsLoaded';

						DOC.body.appendChild(script);
					},

					_isGoogleMapLoaded: function() {
						return (typeof google !== 'undefined' && A.Lang.isObject(google.maps));
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

						instance._map = new google.maps.Map(instance.byId('map').getDOMNode(), mapParams);

						instance._geocoder = new google.maps.Geocoder();

						instance._directionsService = new google.maps.DirectionsService();

						instance._directionsDisplay = new google.maps.DirectionsRenderer(
							{
								map: instance._map
							}
						);

						instance._stepDisplay = new google.maps.InfoWindow();

						if (instance.get(STR_DIRECTION_ADDRESS)) {
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

						for (var i = 0; i < stepsCount; i++) {
							var marker = new google.maps.Marker(
								{
									position: myRoute.steps[i].start_point,
									map: instance._map
								}
							);

							instance._attachInstructionText(marker, myRoute.steps[i].instructions);

							markersArray.push(marker);
						}
					}
				}
			}
		);

		GoogleMaps.MAP_TYPE_HYBRID    = MAP_TYPE_HYBRID;
		GoogleMaps.MAP_TYPE_ROADMAP   = MAP_TYPE_ROADMAP;
		GoogleMaps.MAP_TYPE_SATELLITE = MAP_TYPE_SATELLITE;
		GoogleMaps.MAP_TYPE_TERRAIN   = MAP_TYPE_TERRAIN;

		Liferay.Portlet.GoogleMaps = GoogleMaps;
	},
	'',
	{
		requires: ['aui-dialog','aui-io-request','liferay-portlet-base']
	}
);