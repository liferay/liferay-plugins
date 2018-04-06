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

WebExAccount webExAccount = null;

if (row != null) {
	webExAccount = (WebExAccount)row.getObject();
}
else {
	webExAccount = (WebExAccount)request.getAttribute("view_account.jsp-webExAccount");
}
%>

<liferay-ui:icon-menu icon="<%= StringPool.BLANK %>" message="<%= StringPool.BLANK %>" showExpanded="<%= row == null %>">
	<c:if test="<%= WebExAccountPermission.contains(permissionChecker, webExAccount, ActionKeys.UPDATE) %>">
		<portlet:renderURL var="editURL">
			<portlet:param name="mvcPath" value="/meetings/edit_account.jsp" />
			<portlet:param name="redirect" value="<%= currentURL %>" />
			<portlet:param name="backURL" value="<%= currentURL %>" />
			<portlet:param name="webExAccountId" value="<%= String.valueOf(webExAccount.getWebExAccountId()) %>" />
		</portlet:renderURL>

		<liferay-ui:icon
			iconCssClass="icon-edit"
			message="edit"
			url="<%= editURL.toString() %>"
		/>
	</c:if>

	<c:if test="<%= WebExAccountPermission.contains(permissionChecker, webExAccount, ActionKeys.PERMISSIONS) %>">
		<liferay-security:permissionsURL
			modelResource="<%= WebExAccount.class.getName() %>"
			modelResourceDescription="<%= webExAccount.getLogin() %>"
			resourcePrimKey="<%= String.valueOf(webExAccount.getWebExAccountId()) %>"
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

	<c:if test="<%= WebExAccountPermission.contains(permissionChecker, webExAccount, ActionKeys.ADD_MEETING) %>">
		<portlet:renderURL var="addMeetingURL">
			<portlet:param name="mvcPath" value="/meetings/edit_meeting.jsp" />
			<portlet:param name="redirect" value="<%= currentURL %>" />
			<portlet:param name="webExAccountId" value="<%= String.valueOf(webExAccount.getWebExAccountId()) %>" />
		</portlet:renderURL>

		<liferay-ui:icon
			iconCssClass="icon-cogs"
			message="add-meeting"
			url="<%= addMeetingURL %>"
		/>
	</c:if>

	<c:if test="<%= WebExAccountPermission.contains(permissionChecker, webExAccount, ActionKeys.DELETE) %>">
		<portlet:actionURL name="deleteWebExAccount" var="deleteURL">
			<portlet:param name="redirect" value="<%= (row != null) ? currentURL : redirect %>" />
			<portlet:param name="webExAccountId" value="<%= String.valueOf(webExAccount.getWebExAccountId()) %>" />
		</portlet:actionURL>

		<liferay-ui:icon-delete
			url="<%= deleteURL %>"
		/>
	</c:if>
</liferay-ui:icon-menu>