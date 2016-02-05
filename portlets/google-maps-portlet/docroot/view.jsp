<%--
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
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
		<aui:form name="fm">
			<aui:fieldset>
				<c:choose>
					<c:when test="<%= mapInputEnabled %>">
						<aui:field-wrapper inlineField="<%= true %>" label='<%= (directionsInputEnabled || Validator.isNotNull(directionsAddress)) ? "from" : "map-address" %>'>
							<div class="input-append">
								<aui:input label="" name="mapAddress" type="text" value="<%= mapAddress %>" />

								<c:if test="<%= !directionsInputEnabled && Validator.isNull(directionsAddress) %>">
									<aui:button name="getMapButton" value="get-map" />
								</c:if>
							</div>
						</aui:field-wrapper>
					</c:when>
					<c:otherwise>
						<c:if test="<%= Validator.isNotNull(mapAddress) && (Validator.isNotNull(directionsAddress) || directionsInputEnabled) %>">
							<aui:input inlineField="<%= true %>" name="from" type="resource" value="<%= mapAddress %>" />
						</c:if>

						<aui:input name="mapAddress" type="hidden" value="<%= mapAddress %>" />
					</c:otherwise>
				</c:choose>

				<c:choose>
					<c:when test="<%= directionsInputEnabled %>">
						<aui:input inlineField="<%= true %>" label="to" name="directionsAddress" type="text" value="<%= directionsAddress %>" />
					</c:when>
					<c:otherwise>
						<c:if test="<%= Validator.isNotNull(directionsAddress) %>">
							<aui:input inlineField="<%= true %>" name="to" type="resource" value="<%= directionsAddress %>" />
						</c:if>

						<aui:input name="directionsAddress" type="hidden" value="<%= directionsAddress %>" />
					</c:otherwise>
				</c:choose>

				<c:choose>
					<c:when test="<%= enableChangingTravelingMode %>">
						<aui:select inlineField="<%= true %>" label="traveling-mode" name="travelingMode">
							<aui:option label="driving" value="<%= GoogleMapsConstants.DRIVING %>" />
							<aui:option label="walking" value="<%= GoogleMapsConstants.WALKING %>" />
							<aui:option label="bicycling" value="<%= GoogleMapsConstants.BICYCLING %>" />
						</aui:select>
					</c:when>
					<c:otherwise>
						<aui:input name="travelingMode" type="hidden" value="<%= GoogleMapsConstants.DRIVING %>" />
					</c:otherwise>
				</c:choose>

				<c:if test="<%= directionsInputEnabled || (mapInputEnabled && Validator.isNotNull(directionsAddress)) %>">
					<aui:button cssClass="get-directions" name="getDirectionsButton" value="get-directions" />
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

		<aui:script use="liferay-google-maps">
			new Liferay.Portlet.GoogleMaps(
				{
					directionsAddress: '<%= directionsAddress %>',

					<c:if test="<%= PortalUtil.isSecure(request) %>">
						googleMapsURL: 'https://maps-api-ssl.google.com/maps/api/js',
					</c:if>

					languageId: '<%= themeDisplay.getLanguageId() %>',
					mapAddress: '<%= mapAddress %>',
					mapInputEnabled: <%= mapInputEnabled %>,
					namespace: '<portlet:namespace />',
					portletId: '<%= portletDisplay.getId() %>',
					showDirectionSteps: <%= showDirectionSteps %>
				}
			).render();
		</aui:script>
	</c:when>
	<c:otherwise>
		<liferay-util:include page="/html/portal/portlet_not_setup.jsp" />
	</c:otherwise>
</c:choose>