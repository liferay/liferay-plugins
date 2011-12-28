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

<c:choose>
	<c:when test="<%= Validator.isNotNull(license) %>">

		<%
		mapAddress = GetterUtil.getString((String)portletSession.getAttribute("mapAddress"), mapAddress);
		directionsAddress = GetterUtil.getString((String)portletSession.getAttribute("directionsAddress"), directionsAddress);
		%>

		<style>
			.ie6 .maps-content img {
 				behavior: expression(this.pngSet=true);
			}
		</style>

		<script src="https://www.google.com/jsapi?key=<%= license %>" type="text/javascript"></script>

		<form name="<portlet:namespace />fm">

		<c:if test="<%= mapInputEnabled %>">
			<input class="lfr-input-text" name="<portlet:namespace />mapAddress" type="text" onKeyPress="if (event.keyCode == 13) { <portlet:namespace />getMap(); return false; }" value="<%= mapAddress %>" />

			<input type="button" value="<liferay-ui:message key="get-map" />" onClick="<portlet:namespace />getMap();" />
		</c:if>

		<c:if test="<%= directionsInputEnabled %>">
			<input class="lfr-input-text" name="<portlet:namespace />directionsAddress" type="text" onKeyPress="if (event.keyCode == 13) { <portlet:namespace />getDirections(); return false; }" value="<%= directionsAddress %>" />

			<input type="button" value="<liferay-ui:message key="get-directions" />" onClick="<portlet:namespace />getDirections();" />
		</c:if>

		<c:if test="<%= mapInputEnabled || directionsInputEnabled %>">
			<div style="padding-top: 5px;"></div>
		</c:if>

		<div class="maps-content" id="<portlet:namespace />map" style="height: <%= height %>px; width: 100%;"></div>

		</form>

		<aui:script>
			var <portlet:namespace />map;
			var <portlet:namespace />geocoder;

			function <portlet:namespace />load() {
				if (GBrowserIsCompatible()) {
					<portlet:namespace />map = new GMap2(document.getElementById("<portlet:namespace />map"));

					<portlet:namespace />map.addControl(new GSmallMapControl());
					<portlet:namespace />map.addControl(new GMapTypeControl());

					<portlet:namespace />geocoder = new GClientGeocoder();

					<portlet:namespace />getAddress("<%= mapAddress %>");
				}
			}

			google.load("maps", "2", {"callback" : <portlet:namespace />load});

			function <portlet:namespace />getAddress(address) {
				<portlet:namespace />geocoder.getLatLng(
					address,
					function(point) {
						if (!point) {
							//alert(address + " not found");
						}
						else {
							<portlet:namespace />map.setCenter(point, 13);

							var marker = new GMarker(point);

							<portlet:namespace />map.addOverlay(marker);

							marker.openInfoWindowHtml(address);
						}
					}
				);
			}

			function <portlet:namespace />getDirections() {
				var mapAddress = <portlet:namespace />getMapAddress();
				var directionsAddress = document.<portlet:namespace />fm.<portlet:namespace />directionsAddress.value;

				<portlet:namespace />saveDirectionsAddress(directionsAddress);

				window.open("http://maps.google.com/maps?f=q&hl=en&q=" + encodeURIComponent(document.<portlet:namespace />fm.<portlet:namespace />directionsAddress.value) + "+to+" + encodeURIComponent(mapAddress));
			}

			function <portlet:namespace />getMap() {
				var mapAddress = <portlet:namespace />getMapAddress();

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

			Liferay.provide(
				window,
				'<portlet:namespace />saveDirectionsAddress',
				function(address) {
					var A = AUI();

					A.io.request(
						'<portlet:actionURL><portlet:param name="<%= Constants.CMD %>" value="saveDirectionsAddress" /></portlet:actionURL>',
						{
							data: {
								directionsAddress: address
							}
						}
					);
				},
				['aui-io-request']
			);

			Liferay.provide(
				window,
				'<portlet:namespace />saveMapAddress',
				function(address) {
					var A = AUI();

					A.io.request(
						'<portlet:actionURL><portlet:param name="<%= Constants.CMD %>" value="saveMapAddress" /></portlet:actionURL>',
						{
							data: {
								mapAddress: address
							}
						}
					);
				},
				['aui-io-request']
			);
		</aui:script>

		<aui:script use="aui-base">
			A.getWin().on('unload', GUnload);
		</aui:script>
	</c:when>
	<c:otherwise>
		<liferay-util:include page="/html/portal/portlet_not_setup.jsp" />
	</c:otherwise>
</c:choose>