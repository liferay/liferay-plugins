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

<%@ include file="/admin/init.jsp" %>

<%
ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

KBFolder kbFolder = (KBFolder)row.getObject();
%>

<liferay-ui:icon-menu>
	<c:if test="<%= KBFolderPermission.contains(permissionChecker, kbFolder, ActionKeys.UPDATE) %>">
		<liferay-portlet:renderURL var="editURL">
			<portlet:param name="mvcPath" value='<%= templatePath + "common/edit_folder.jsp" %>' />
			<portlet:param name="redirect" value="<%= redirect %>" />
			<portlet:param name="kbFolderId" value="<%= String.valueOf(kbFolder.getKbFolderId()) %>" />
		</liferay-portlet:renderURL>

		<liferay-ui:icon
			image="edit"
			method="get"
			url="<%= editURL %>"
		/>
	</c:if>
</liferay-ui:icon-menu>