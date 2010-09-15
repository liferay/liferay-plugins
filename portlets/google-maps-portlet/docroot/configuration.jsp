<%--
/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
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

<form action="<liferay-portlet:actionURL portletConfiguration="true" />" method="post" name="<portlet:namespace />fm">
<input name="<portlet:namespace /><%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />

<div class="portlet-msg-info">
	<a href="http://www.google.com/apis/maps/signup.html" target="_blank"><liferay-ui:message key="you-can-get-a-license-directly-from-google" /></a>
</div>

<table class="lfr-table">
<tr>
	<td>
		<liferay-ui:message key="google-license" />
	</td>
	<td>
		<input class="lfr-input-text" name="<portlet:namespace />license" type="text" value="<%= license %>" />
	</td>
</tr>
<tr>
	<td colspan="2">
		<br />
	</td>
</tr>
<tr>
	<td>
		<liferay-ui:message key="map-address" />
	</td>
	<td>
		<input class="lfr-input-text" name="<portlet:namespace />mapAddress" type="text" value="<%= mapAddress %>" />
	</td>
</tr>
<tr>
	<td>
		<liferay-ui:message key="map-input-enabled" />
	</td>
	<td>
		<liferay-ui:input-checkbox param="mapInputEnabled" defaultValue="<%= mapInputEnabled %>" />
	</td>
</tr>
<tr>
	<td colspan="2">
		<br />
	</td>
</tr>
<tr>
	<td>
		<liferay-ui:message key="directions-address" />
	</td>
	<td>
		<input class="lfr-input-text" name="<portlet:namespace />directionsAddress" type="text" value="<%= directionsAddress %>" />
	</td>
</tr>
<tr>
	<td>
		<liferay-ui:message key="directions-input-enabled" />
	</td>
	<td>
		<liferay-ui:input-checkbox param="directionsInputEnabled" defaultValue="<%= directionsInputEnabled %>" />
	</td>
</tr>
<tr>
	<td colspan="2">
		<br />
	</td>
</tr>
<tr>
	<td>
		<liferay-ui:message key="height" />
	</td>
	<td>
		<input name="<portlet:namespace />height" size="4" type="text" value="<%= height %>" /> px
	</td>
</tr>
</table>

<br />

<input type="button" value="<liferay-ui:message key="save" />" onClick="submitForm(document.<portlet:namespace />fm);" />

</form>

<c:if test="<%= renderRequest.getWindowState().equals(WindowState.MAXIMIZED) %>">
	<script type="text/javascript">
		Liferay.Util.focusFormField(document.<portlet:namespace />fm.<portlet:namespace />license);
	</script>
</c:if>