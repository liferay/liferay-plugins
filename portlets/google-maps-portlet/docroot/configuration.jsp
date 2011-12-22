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

<liferay-portlet:actionURL portletConfiguration="true" var="actionURL" />

<aui:form action="<%= actionURL %>" method="post" name="fm">
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />

	<aui:fieldset>
		<div class="portlet-msg-info">
			<aui:a href="http://www.google.com/apis/maps/signup.html" target="_blank" label="you-can-get-a-license-directly-from-google" />
		</div>

		<aui:input cssClass="lfr-input-text-container" label="google-license" name="preferences--license--" type="text" value="<%= license %>" />

		<aui:input cssClass="lfr-input-text-container" name="preferences--mapAddress--" type="text" value="<%= mapAddress %>" />

		<aui:input name="preferences--mapInputEnabled--" type="checkbox" value="<%= mapInputEnabled %>" />

		<aui:input cssClass="lfr-input-text-container" name="preferences--directionsAddress--" type="text" value="<%= directionsAddress %>" />

		<aui:input name="preferences--directionsInputEnabled--" type="checkbox" value="<%= directionsInputEnabled %>" />

		<aui:input name="preferences--height--" size="4" suffix="px" type="text" value="<%= height %>" />
	</aui:fieldset>

	<aui:button-row>
		<aui:button type="submit" />
	</aui:button-row>
</aui:form>