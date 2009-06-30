<%
/**
 * Copyright (c) 2000-2009 Liferay, Inc. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
%>

<%@ include file="/init.jsp" %>

<c:choose>
	<c:when test="<%= Validator.isNotNull(license) %>">

		<%
		mapAddress = GetterUtil.getString((String)portletSession.getAttribute("mapAddress"), mapAddress);
		directionsAddress = GetterUtil.getString((String)portletSession.getAttribute("directionsAddress"), directionsAddress);
		%>

		<script src="http://maps.google.com/maps?file=api&amp;v=2&amp;key=<%= license %>&amp;sensor=false" type="text/javascript"></script>

		<script type="text/javascript">
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

			jQuery(document).ready(<portlet:namespace />load);
			jQuery(window).unload(GUnload);

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

			function <portlet:namespace />saveDirectionsAddress(address) {
				jQuery.ajax(
					{
						url: '<portlet:actionURL><portlet:param name="<%= Constants.CMD %>" value="saveDirectionsAddress" /></portlet:actionURL>',
						data: {
							directionsAddress: encodeURIComponent(address)
						}
					}
				);
			}

			function <portlet:namespace />saveMapAddress(address) {
				jQuery.ajax(
					{
						url: '<portlet:actionURL><portlet:param name="<%= Constants.CMD %>" value="saveMapAddress" /></portlet:actionURL>',
						data: {
							mapAddress: encodeURIComponent(address)
						}
					}
				);
			}
		</script>

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

		<div id="<portlet:namespace />map" style="height: <%= height %>px; width: 100%;"></div>

		</form>
	</c:when>
	<c:otherwise>
		<liferay-ui:message key="please-contact-the-administrator-to-setup-this-portlet" />
	</c:otherwise>
</c:choose>