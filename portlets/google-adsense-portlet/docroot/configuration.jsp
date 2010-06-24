<%
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
%>

<%@ include file="/init.jsp" %>

<form action="<liferay-portlet:actionURL portletConfiguration="true" />" method="post" name="<portlet:namespace />fm">
<input name="<portlet:namespace /><%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />

<table class="lfr-table">
<tr>
	<td>
		<liferay-ui:message key="ad-client" />
	</td>
	<td>
		<input class="lfr-input-text" name="<portlet:namespace />adClient" type="text" value="<%= adClient %>" />
	</td>
</tr>
<tr>
	<td>
		<liferay-ui:message key="ad-channel" />
	</td>
	<td>
		<input class="lfr-input-text" name="<portlet:namespace />adChannel" type="text" value="<%= adChannel %>" />
	</td>
</tr>
<tr>
	<td>
		<liferay-ui:message key="ad-type" />
	</td>
	<td>
		<select name="<portlet:namespace />adType">

			<%
			for (int i = 1; i < adTypes.length; i++) {
			%>

				<option <%= (adType == GetterUtil.getInteger(adTypes[i][0])) ? "selected" : "" %> value="<%= adTypes[i][0] %>"><%= adTypes[i][1] %></option>

			<%
			}
			%>

		</select>
	</td>
</tr>
<tr>
	<td>
		<liferay-ui:message key="ad-format" />
	</td>
	<td>
		<select name="<portlet:namespace />adFormat">

			<%
			for (int i = 1; i < adFormats.length; i++) {
			%>

				<option <%= (adFormat == GetterUtil.getInteger(adFormats[i][0])) ? "selected" : "" %> value="<%= adFormats[i][0] %>"><%= adFormats[i][3] %></option>

			<%
			}
			%>

		</select>
	</td>
</tr>
<tr>
	<td colspan="2">
		<br />
	</td>
</tr>
<tr>
	<td>
		<liferay-ui:message key="color-border" />
	</td>
	<td>
		<input class="lfr-input-text" name="<portlet:namespace />colorBorder" type="text"  value="<%= colorBorder %>" />
	</td>
</tr>
<tr>
	<td>
		<liferay-ui:message key="color-background" />
	</td>
	<td>
		<input class="lfr-input-text" name="<portlet:namespace />colorBg" type="text" value="<%= colorBg %>" />
	</td>
</tr>
<tr>
	<td>
		<liferay-ui:message key="color-link" />
	</td>
	<td>
		<input class="lfr-input-text" name="<portlet:namespace />colorLink" type="text" value="<%= colorLink %>" />
	</td>
</tr>
<tr>
	<td>
		<liferay-ui:message key="color-text" />
	</td>
	<td>
		<input class="lfr-input-text" name="<portlet:namespace />colorText" type="text" value="<%= colorText %>" />
	</td>
</tr>
<tr>
	<td>
		<liferay-ui:message key="color-url" />
	</td>
	<td>
		<input class="lfr-input-text" name="<portlet:namespace />colorUrl" type="text" value="<%= colorUrl %>" />
	</td>
</tr>
</table>

<br />

<input type="button" value="<liferay-ui:message key="save" />" onClick="submitForm(document.<portlet:namespace />fm);" />

</form>

<c:if test="<%= renderRequest.getWindowState().equals(WindowState.MAXIMIZED) %>">
	<aui:script>
		Liferay.Util.focusFormField(document.<portlet:namespace />fm.<portlet:namespace />adClient);
	</aui:script>
</c:if>