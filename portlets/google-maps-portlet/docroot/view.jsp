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
	<c:when test="<%= Validator.isNotNull(mapAddress) %>">
		<c:choose>
			<c:when test="<%= PortalUtil.isSecure(request) %>">
				<script src="https://maps-api-ssl.google.com/maps/api/js?v=3&sensor=true&language=<%= themeDisplay.getLanguageId() %>" type="text/javascript"></script>
			</c:when>
			<c:otherwise>
				<script src="http://maps.google.com/maps/api/js?sensor=true&language=<%= themeDisplay.getLanguageId() %>" type="text/javascript"></script>
			</c:otherwise>
		</c:choose>

		<aui:form name="fm">
			<aui:fieldset>
				<c:choose>
					<c:when test="<%= mapInputEnabled %>">
						<aui:input cssClass="address-field" inlineField="<%= true %>" label="from" name="mapAddress" type="text" value="<%= mapAddress %>" />
					</c:when>
					<c:otherwise>
						<c:if test="<%= Validator.isNotNull(mapAddress) && (Validator.isNotNull(directionsAddress) || directionsInputEnabled) %>">
							<aui:field-wrapper label="from"><%= mapAddress %></aui:field-wrapper>
						</c:if>

						<aui:input name="mapAddress" type="hidden" value="<%= mapAddress %>" />
					</c:otherwise>
				</c:choose>

				<c:choose>
					<c:when test="<%= directionsInputEnabled %>">
						<aui:input cssClass="address-field" inlineField="<%= true %>" label="to" name="directionsAddress" type="text" value="<%= directionsAddress %>" />
					</c:when>
					<c:otherwise>
						<c:if test="<%= Validator.isNotNull(directionsAddress) %>">
							<aui:field-wrapper label="to"><%= directionsAddress %></aui:field-wrapper>
						</c:if>

						<aui:input name="directionsAddress" type="hidden" value="<%= directionsAddress %>" />
					</c:otherwise>
				</c:choose>

				<c:choose>
					<c:when test="<%= Validator.isNotNull(directionsAddress) || directionsInputEnabled %>">
						<aui:button name="getDirectionsButton" value="get-directions" />
					</c:when>
					<c:when test="<%= mapInputEnabled %>">
						<aui:button name="getMapButton" value="get-map" />
					</c:when>
				</c:choose>

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

		<aui:script use="liferay-google-maps">
			new Liferay.Portlet.GoogleMaps(
				{
					directionsAddress: '<%= directionsAddress %>',
					mapAddress: '<%= mapAddress %>',
					mapInputEnabled: <%= mapInputEnabled %>,
					mapParams: {
						mapTypeId: google.maps.MapTypeId.ROADMAP,
						zoom: 8
					},
					namespace: '<portlet:namespace />',
					portletId: '<%= portletDisplay.getId() %>',
					showDirectionSteps: <%= showDirectionSteps %>
				}
			);
		</aui:script>
	</c:when>
	<c:otherwise>
		<liferay-util:include page="/html/portal/portlet_not_setup.jsp" />
	</c:otherwise>
</c:choose>