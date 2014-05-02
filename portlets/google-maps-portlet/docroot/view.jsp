<%--
/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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
				<aui:layout>
					<c:choose>
						<c:when test="<%= mapInputEnabled %>">
							<aui:input cssClass="address-field" inlineField="<%= true %>" label='<%= (directionsInputEnabled || Validator.isNotNull(directionsAddress)) ? "from" : StringPool.BLANK %>' name="mapAddress" type="text" value="<%= mapAddress %>" />

							<aui:button name="getMapButton" value="get-map" />
							
						</c:when>
						<c:otherwise>
							<c:if test="<%= Validator.isNotNull(mapAddress) && (Validator.isNotNull(directionsAddress) || directionsInputEnabled) %>">
								<aui:field-wrapper cssClass="address-field" inlineField="<%= true %>" label="from"><%= mapAddress %></aui:field-wrapper>
							</c:if>

							<aui:input name="mapAddress" type="hidden" value="<%= mapAddress %>" />
						</c:otherwise>
					</c:choose>
				</aui:layout>

				<aui:layout>
					<c:choose>
						<c:when test="<%= directionsInputEnabled %>">
							<aui:input cssClass="address-field" inlineField="<%= true %>" label="to" name="directionsAddress" type="text" value="<%= directionsAddress %>" />
						</c:when>
						<c:otherwise>
							<c:if test="<%= Validator.isNotNull(directionsAddress) %>">
								<aui:field-wrapper cssClass="address-field" inlineField="<%= true %>" label="to"><%= directionsAddress %></aui:field-wrapper>
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
				</aui:layout>

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
					languageId: '<%= themeDisplay.getLanguageId() %>',
					mapAddress: '<%= mapAddress %>',
					mapInputEnabled: <%= mapInputEnabled %>,
					namespace: '<portlet:namespace />',
					portletId: '<%= portletDisplay.getId() %>',
					showDirectionSteps: <%= showDirectionSteps %>,
					zoomDisplay: '<%= mapZoom %>',
					mapKey: '<%= mapKey %>'
				}
			).render();
		</aui:script>
	</c:when>
	<c:otherwise>
		<liferay-util:include page="/html/portal/portlet_not_setup.jsp" />
	</c:otherwise>
</c:choose>