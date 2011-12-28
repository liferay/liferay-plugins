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

<aui:form action="<%= actionURL %>" method="post" name="<portlet:namespace />fm">
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />

	<aui:fieldset>
		<aui:input cssClass="lfr-input-text-container" name="preferences--adClient--" type="text" value="<%= adClient %>" />

		<aui:input cssClass="lfr-input-text-container" name="preferences--adChannel--" type="text" value="<%= adChannel %>" />

		<aui:select name="preferences--adType--">

			<%
			for (int i = 1; i < adTypes.length; i++) {
			%>

				<aui:option label="<%= adTypes[i][1] %>" selected="<%= adType == GetterUtil.getInteger(adTypes[i][0]) %>" value="<%= adTypes[i][0] %>" />

			<%
			}
			%>

		</aui:select>

		<aui:select name="preferences--adFormat--">

			<%
			for (int i = 1; i < adFormats.length; i++) {
			%>

				<aui:option label="<%= adFormats[i][3] %>" selected="<%= adFormat == GetterUtil.getInteger(adFormats[i][0]) %>" value="<%= adFormats[i][0] %>" />

			<%
			}
			%>

		</aui:select>

		<aui:input cssClass="lfr-input-text-container" name="preferences--colorBorder--" type="text" value="<%= colorBorder %>" />

		<aui:input cssClass="lfr-input-text-container" label="color-background" name="preferences--colorBg--" type="text" value="<%= colorBg %>" />

		<aui:input cssClass="lfr-input-text-container" name="preferences--colorLink--" type="text" value="<%= colorLink %>" />

		<aui:input cssClass="lfr-input-text-container" name="preferences--colorText--" type="text" value="<%= colorText %>" />

		<aui:input cssClass="lfr-input-text-container" name="preferences--colorUrl--" type="text" value="<%= colorUrl %>" />
	</aui:fieldset>

	<aui:button-row>
		<aui:button type="submit" />
	</aui:button-row>
</aui:form>