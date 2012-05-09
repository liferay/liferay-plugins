<%--
/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */
--%>

<%@ include file="/init.jsp" %>

<%
mapAddress = GetterUtil.getString((String)portletSession.getAttribute("mapAddress"), mapAddress);
directionsAddress = GetterUtil.getString((String)portletSession.getAttribute("directionsAddress"), directionsAddress);
%>

<c:choose>
	<c:when test="<%= Validator.isNotNull(mapAddress) %>">
		<c:choose>
			<c:when test="<%= PortalUtil.isSecure(request) %>">
				<script src="https://maps-api-ssl.google.com/maps/api/js?v=3&sensor=true" type="text/javascript"></script>
			</c:when>
			<c:otherwise>
				<script src="http://maps.google.com/maps/api/js?sensor=true" type="text/javascript"></script>
			</c:otherwise>
		</c:choose>

		<aui:form name="fm">
			<aui:fieldset>
				<c:choose>
					<c:when test="<%= directionsInputEnabled %>">
						<aui:input cssClass="address-field" inlineField="<%= true %>" label='<%= mapInputEnabled ? "from" : "directions-from" %>' name="directionsAddress" type="text" value="<%= directionsAddress %>" />
					</c:when>
					<c:otherwise>
						<aui:input name="directionsAddress" type="hidden" value="<%= directionsAddress %>" />
					</c:otherwise>
				</c:choose>

				<c:choose>
					<c:when test="<%= mapInputEnabled %>">
						<aui:input cssClass="address-field" inlineField="<%= true %>" label='<%= directionsInputEnabled ? "to" : StringPool.BLANK %>' name="mapAddress" type="text" value="<%= mapAddress %>" />
					</c:when>
					<c:otherwise>
						<aui:input name="mapAddress" type="hidden" value="<%= mapAddress %>" />
					</c:otherwise>
				</c:choose>

				<c:if test="<%= directionsInputEnabled %>">
					<aui:button name="getDirectionsButton" value="get-directions" />
				</c:if>

				<c:choose>
					<c:when test="<%= enableChangingTravellingMode %>">
						<aui:select inlineField="<%= true %>" label="" name="travellingMode">
							<aui:option label="<%= GoogleMapsConstants.DRIVING %>" />
							<aui:option label="<%= GoogleMapsConstants.WALKING %>" />
							<aui:option label="<%= GoogleMapsConstants.BICYCLING %>" />
						</aui:select>
					</c:when>
					<c:otherwise>
						<aui:input name="travellingMode" type="hidden" value="<%= GoogleMapsConstants.DRIVING %>" />
					</c:otherwise>
				</c:choose>

				<c:if test="<%= mapInputEnabled %>">
					<aui:button name="getMapButton" value="get-map" />
				</c:if>

				<div style="padding-top: 5px;"></div>

				<div class="maps-content" id="<portlet:namespace />map" style="height: <%= height %>px; width: 100%;"></div>

				<div id="<portlet:namespace />warningsPanel"></div>

				<c:if test="<%= showGoogleMapsLink %>">
					<div class="google-maps-link">
						<aui:a href="javascript:;" id="openInGoogleMapsLink" label="open-in-google-maps" target="_blank" />
					</div>
				</c:if>
			</aui:fieldset>
		</aui:form>

		<aui:script use="aui-base,aui-io-request">
			var <portlet:namespace />directionDisplay;
			var <portlet:namespace />directionsService;
			var <portlet:namespace />geocoder;
			var <portlet:namespace />map;
			var <portlet:namespace />markersArray = [];
			var <portlet:namespace />stepDisplay;
			var <portlet:namespace />marker;
			var <portlet:namespace />infoWindow;

			function <portlet:namespace />load() {
				var myOptions = {
					mapTypeId: google.maps.MapTypeId.ROADMAP,
					zoom: 8
		  		}

				<portlet:namespace />map = new google.maps.Map(document.getElementById("<portlet:namespace />map"), myOptions);
				<portlet:namespace />geocoder = new google.maps.Geocoder();
				<portlet:namespace />directionsService = new google.maps.DirectionsService();

				var rendererOptions = {
					map: <portlet:namespace />map
				}

				<portlet:namespace />directionsDisplay = new google.maps.DirectionsRenderer(rendererOptions);
				<portlet:namespace />stepDisplay = new google.maps.InfoWindow();

				var getDirectionsButton = A.one('#<portlet:namespace />getDirectionsButton');

				if (getDirectionsButton) {
					getDirectionsButton.on(
						'click',
						function(event) {
							<portlet:namespace />getDirections();
						}
					);
				}

				var travellingMode = A.one('#<portlet:namespace />travellingMode');

				if (travellingMode) {
					travellingMode.on(
						'change',
						function(event) {
							<portlet:namespace />getDirections();
						}
					);
				}

				var getMapButton = A.one('#<portlet:namespace />getMapButton');

				if (getMapButton) {
					getMapButton.on(
						'click',
						function(event) {
							<portlet:namespace />getMap();
						}
					);
				}

				var directionsAddress = A.one('#<portlet:namespace />directionsAddress');

				if (directionsAddress) {
					directionsAddress.on(
						'key',
						function(event) {
							event.preventDefault();

							<portlet:namespace />getDirections();
						},
						'down:13'
					);
				}

				var mapAddress = A.one('#<portlet:namespace />mapAddress');

				if (mapAddress) {
					mapAddress.on(
						'key',
						function(event) {
							event.preventDefault();

							<portlet:namespace />getMap();
						},
						'down:13'
					);
				}

				var openInGoogleMapsLink = A.one('#<portlet:namespace />openInGoogleMapsLink');

				if (openInGoogleMapsLink) {
					openInGoogleMapsLink.on(
						'click',
						function(event) {
							event.preventDefault();

							var mapAddress = <portlet:namespace />getMapAddress();
							var directionsAddress = A.one('#<portlet:namespace />directionsAddress').val();

			                if (directionsAddress) {
								window.open("http://maps.google.com/maps?f=q&hl=en&q=" + encodeURIComponent(mapAddress) + "+to+" + encodeURIComponent(directionsAddress));
							}
							else {
								window.open("http://maps.google.com/maps?q=" + encodeURIComponent(mapAddress));
							}
						}
					);
				}

				<c:choose>
					<c:when test="<%= Validator.isNotNull(directionsAddress) %>">
						<portlet:namespace />getDirections();
					</c:when>
					<c:otherwise>
						<portlet:namespace />getAddress("<%= mapAddress %>");
					</c:otherwise>
				</c:choose>
			}

			<portlet:namespace />load();

			function <portlet:namespace />getAddress(address) {
				<portlet:namespace />geocoder.geocode(
					{
						'address': address
					},
					function(results, status) {
						if (status == google.maps.GeocoderStatus.OK) {
							var location = results[0].geometry.location;

							<portlet:namespace />map.setCenter(location);

							if (!<portlet:namespace />marker) {
								<portlet:namespace />marker = new google.maps.Marker(
									{
										map: <portlet:namespace />map,
										position: location
									}
								);
							}
							else {
								<portlet:namespace />marker.setMap(<portlet:namespace />map);
								<portlet:namespace />marker.setPosition(location);
							}

							if (!<portlet:namespace />infoWindow) {
								<portlet:namespace />infoWindow = new google.maps.InfoWindow(
									{
										content: address
									}
								);
							}
							else {
								<portlet:namespace />infoWindow.setContent(address);
							}

							<portlet:namespace />infoWindow.setPosition(location);

							<portlet:namespace />infoWindow.open(<portlet:namespace />map, <portlet:namespace />marker);
						}
						else {
							//alert(status);
						}
					}
				);
			}

			function <portlet:namespace />getDirections() {
				var mapAddress = <portlet:namespace />getMapAddress();
				var directionsAddress = A.one('#<portlet:namespace />directionsAddress').val();

				var travellingMode = A.one('#<portlet:namespace />travellingMode').val();

				var request = {
					destination: directionsAddress,
					origin: mapAddress,
					travelMode: google.maps.TravelMode[travellingMode.toUpperCase()]
				};

				<portlet:namespace />removeMarkers();

				<portlet:namespace />directionsService.route(
					request,
					function(response, status) {
						if (status == google.maps.DirectionsStatus.OK) {
							var warnings = document.getElementById("<portlet:namespace />warningsPanel");
							warnings.innerHTML = "" + response.routes[0].warnings + "";

							<portlet:namespace />directionsDisplay.setDirections(response);

							<c:if test="<%= showDirectionSteps%>">
								<portlet:namespace />showSteps(response);
							</c:if>

							<portlet:namespace />saveDirectionsAddress(directionsAddress);
						}
						else {
							//alert(status);
						}
					}
				);
			}

			function <portlet:namespace />showSteps(directionResult) {

				// For each step, place a marker, and add the text to the marker's info window.

				var myRoute = directionResult.routes[0].legs[0];

				for (var i = 0; i < myRoute.steps.length; i++) {
					var marker = new google.maps.Marker(
						{
							position: myRoute.steps[i].start_point,
							map: <portlet:namespace />map
						}
					);

					<portlet:namespace />attachInstructionText(marker, myRoute.steps[i].instructions);

					<portlet:namespace />markersArray.push(marker);
				}
			}

			function <portlet:namespace />attachInstructionText(marker, text) {
				google.maps.event.addListener(
					marker,
					'click',
					function() {
						<portlet:namespace />stepDisplay.setContent(text);

						<portlet:namespace />stepDisplay.open(<portlet:namespace />map, marker);
					}
				);
			}

			function <portlet:namespace />removeMarkers() {
				if (<portlet:namespace />markersArray){
					for (i in <portlet:namespace />markersArray) {
						<portlet:namespace />markersArray[i].setMap(null);
					}
				}

				if (<portlet:namespace />marker) {
					<portlet:namespace />marker.setMap(null);
				}
			}

			function <portlet:namespace />getMap() {
				var mapAddress = <portlet:namespace />getMapAddress();

				<portlet:namespace />removeMarkers();
				<portlet:namespace />getAddress(mapAddress);
				<portlet:namespace />saveMapAddress(mapAddress);

				return mapAddress;
			}

			function <portlet:namespace />getMapAddress() {
				var mapAddress = "<%= mapAddress %>";

				<c:if test="<%= mapInputEnabled %>">
					mapAddress = document.<portlet:namespace />fm.<portlet:namespace />mapAddress.value;
				</c:if>

				return mapAddress;
			}

			function <portlet:namespace />saveDirectionsAddress(address) {
				A.io.request(
					'<portlet:actionURL><portlet:param name="<%= Constants.CMD %>" value="saveDirectionsAddress" /></portlet:actionURL>',
					{
						data: {
							directionsAddress: address
						}
					}
				);
			}

			function <portlet:namespace />saveMapAddress(address) {
				A.io.request(
					'<portlet:actionURL><portlet:param name="<%= Constants.CMD %>" value="saveMapAddress" /></portlet:actionURL>',
					{
						data: {
							mapAddress: address
						}
					}
				);
			}
		</aui:script>
	</c:when>
	<c:otherwise>
		<liferay-util:include page="/html/portal/portlet_not_setup.jsp" />
	</c:otherwise>
</c:choose>