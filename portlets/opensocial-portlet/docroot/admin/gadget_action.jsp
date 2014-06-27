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

<%
ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

Gadget gadget = (Gadget)row.getObject();

Map<String, OAuthService> oAuthServices = null;

try {
	oAuthServices = ShindigUtil.getOAuthServices(gadget.getUrl());
}
catch (Exception e) {
	row.setRestricted(true);
}
%>

<liferay-ui:icon-menu icon="<%= StringPool.BLANK %>" message="<%= StringPool.BLANK %>">
	<c:if test="<%= GadgetPermission.contains(permissionChecker, themeDisplay.getScopeGroupId(), gadget.getGadgetId(), ActionKeys.UPDATE) %>">
		<portlet:renderURL var="updateGadgetURL">
			<portlet:param name="mvcPath" value="/admin/edit_gadget.jsp" />
			<portlet:param name="redirect" value="<%= currentURL %>" />
			<portlet:param name="gadgetId" value="<%= String.valueOf(gadget.getGadgetId()) %>" />
		</portlet:renderURL>

		<liferay-ui:icon iconCssClass="icon-edit" message="edit" url="<%= updateGadgetURL %>" />

		<c:if test="<%= (oAuthServices != null) && (oAuthServices.size() > 0) %>">
			<portlet:renderURL var="configureOAuthURL">
				<portlet:param name="mvcPath" value="/admin/edit_oauth_consumers.jsp" />
				<portlet:param name="redirect" value="<%= currentURL %>" />
				<portlet:param name="gadgetId" value="<%= String.valueOf(gadget.getGadgetId()) %>" />
			</portlet:renderURL>

			<liferay-ui:icon iconCssClass="icon-list-alt" message="manage-oauth" url="<%= configureOAuthURL %>" />
		</c:if>

		<portlet:actionURL name="refreshGadgets" var="refreshGadgetsURL">
			<portlet:param name="redirect" value="<%= currentURL %>" />
			<portlet:param name="gadgetId" value="<%= String.valueOf(gadget.getGadgetId()) %>" />
		</portlet:actionURL>

		<liferay-ui:icon iconCssClass="icon-refresh" message="refresh" url="<%= refreshGadgetsURL %>" />
	</c:if>

	<c:if test="<%= GadgetPermission.contains(permissionChecker, themeDisplay.getScopeGroupId(), gadget.getGadgetId(), ActionKeys.PERMISSIONS) %>">
		<liferay-security:permissionsURL
			modelResource="<%= Gadget.class.getName() %>"
			modelResourceDescription="<%= gadget.getName() %>"
			resourcePrimKey="<%= String.valueOf(gadget.getGadgetId()) %>"
			var="permissionsURL"
			windowState="<%= LiferayWindowState.POP_UP.toString() %>"
		/>

		<liferay-ui:icon
			iconCssClass="icon-lock"
			message="permissions"
			method="get"
			url="<%= permissionsURL %>"
			useDialog="<%= true %>"
		/>
	</c:if>

	<c:if test="<%= GadgetPermission.contains(permissionChecker, themeDisplay.getScopeGroupId(), gadget.getGadgetId(), ActionKeys.DELETE) %>">
		<portlet:actionURL name="deleteGadget" var="deleteURL">
			<portlet:param name="redirect" value="<%= currentURL %>" />
			<portlet:param name="gadgetId" value="<%= String.valueOf(gadget.getGadgetId()) %>" />
		</portlet:actionURL>

		<liferay-ui:icon-delete url="<%= deleteURL %>" />
	</c:if>
</liferay-ui:icon-menu>