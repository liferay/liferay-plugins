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

<liferay-portlet:actionURL portletConfiguration="true" var="actionURL" />

<aui:form action="<%= actionURL %>" method="post" name="fm">
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />

	<aui:fieldset>
	
		<aui:input cssClass="lfr-input-text-container" name="preferences--mapKey--" type="text" value="<%= mapKey %>" />
		<aui:a href="https://developers.google.com/maps/documentation/javascript/tutorial#api_key" label="api-key" target="_blank" />
		
		<hr />

		<aui:input cssClass="lfr-input-text-container" name="preferences--mapAddress--" type="text" value="<%= mapAddress %>" />

		<aui:input cssClass="lfr-input-text-container" name="preferences--mapZoom--" type="text" value="<%= mapZoom %>" />
		
		<aui:input label="allow-map-address-to-be-edited" name="preferences--mapInputEnabled--" type="checkbox" value="<%= mapInputEnabled %>" />

		<aui:input cssClass="lfr-input-text-container" name="preferences--directionsAddress--" type="text" value="<%= directionsAddress %>" />

		<aui:input label="allow-directions-address-to-be-edited" name="preferences--directionsInputEnabled--" type="checkbox" value="<%= directionsInputEnabled %>" />

		<aui:input name="preferences--showDirectionSteps--" type="checkbox" value="<%= showDirectionSteps %>" />

		<aui:input name="preferences--enableChangingTravelingMode--" type="checkbox" value="<%= enableChangingTravelingMode %>" />

		<aui:input name="preferences--height--" size="4" suffix="px" type="text" value="<%= height %>" />

		<aui:input name="preferences--showGoogleMapsLink--" type="checkbox" value="<%= showGoogleMapsLink %>" />
	</aui:fieldset>

	<aui:button-row>
		<aui:button type="submit" />
	</aui:button-row>
</aui:form>