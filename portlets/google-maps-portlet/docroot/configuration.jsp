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

<liferay-portlet:actionURL portletConfiguration="true" var="configurationActionURL" />

<aui:form action="<%= configurationActionURL %>" method="post" name="fm">
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />

	<aui:fieldset>
		<aui:input name="preferences--mapAddress--" type="text" value="<%= mapAddress %>" wrapperCssClass="lfr-input-text-container" />

		<aui:input label="allow-map-address-to-be-edited" name="preferences--mapInputEnabled--" type="checkbox" value="<%= mapInputEnabled %>" />

		<aui:input name="preferences--directionsAddress--" type="text" value="<%= directionsAddress %>" wrapperCssClass="lfr-input-text-container" />

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