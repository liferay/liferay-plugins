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
String redirect = ParamUtil.getString(request, "redirect");

ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

WebExSite webExSite = null;

if (row != null) {
	webExSite = (WebExSite)row.getObject();
}
else {
	webExSite = (WebExSite)request.getAttribute("view_site.jsp-webExSite");
}
%>

<liferay-ui:icon-menu icon="<%= StringPool.BLANK %>" message="<%= StringPool.BLANK %>" showExpanded="<%= row == null %>">
	<c:if test="<%= WebExSitePermission.contains(permissionChecker, webExSite, ActionKeys.UPDATE) %>">
		<portlet:renderURL var="editURL">
			<portlet:param name="mvcPath" value="/meetings/edit_site.jsp" />
			<portlet:param name="redirect" value="<%= currentURL %>" />
			<portlet:param name="backURL" value="<%= currentURL %>" />
			<portlet:param name="webExSiteId" value="<%= String.valueOf(webExSite.getWebExSiteId()) %>" />
		</portlet:renderURL>

		<liferay-ui:icon
			iconCssClass="icon-edit"
			message="edit"
			url="<%= editURL %>"
		/>
	</c:if>

	<c:if test="<%= WebExSitePermission.contains(permissionChecker, webExSite, ActionKeys.PERMISSIONS) %>">
		<liferay-security:permissionsURL
			modelResource="<%= WebExSite.class.getName() %>"
			modelResourceDescription="<%= webExSite.getName() %>"
			resourcePrimKey="<%= String.valueOf(webExSite.getWebExSiteId()) %>"
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

	<c:if test="<%= WebExSitePermission.contains(permissionChecker, webExSite, ActionKeys.ADD_ACCOUNT) %>">
		<portlet:renderURL var="addAccountURL">
			<portlet:param name="mvcPath" value="/meetings/edit_account.jsp" />
			<portlet:param name="redirect" value="<%= currentURL %>" />
			<portlet:param name="webExSiteId" value="<%= String.valueOf(webExSite.getWebExSiteId()) %>" />
		</portlet:renderURL>

		<liferay-ui:icon
			iconCssClass="icon-cogs"
			message="add-account"
			url="<%= addAccountURL %>"
		/>
	</c:if>

	<c:if test="<%= WebExSitePermission.contains(permissionChecker, webExSite, ActionKeys.DELETE) %>">
		<portlet:actionURL name="deleteWebExSite" var="deleteURL">
			<portlet:param name="redirect" value="<%= (row != null) ? currentURL : redirect %>" />
			<portlet:param name="webExSiteId" value="<%= String.valueOf(webExSite.getWebExSiteId()) %>" />
		</portlet:actionURL>

		<liferay-ui:icon-delete
			url="<%= deleteURL %>"
		/>
	</c:if>
</liferay-ui:icon-menu>