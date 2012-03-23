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

<%@ include file="/html/portlet/document_library/init.jsp" %>

<%
ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

Object[] objArray = (Object[])row.getObject();

FileEntry fileEntry = (FileEntry)objArray[0];
FileVersion fileVersion = (FileVersion)objArray[1];
int fileVersionsCount = (Integer)objArray[2];
String[] conversions = (String[])objArray[3];
Boolean isLocked = (Boolean)objArray[4];
Boolean hasLock = (Boolean)objArray[5];
%>

<liferay-ui:icon-menu>
	<c:if test="<%= showActions && (row.getPos() != 0) && (fileVersion.getStatus() == WorkflowConstants.STATUS_APPROVED) && DLFileEntryPermission.contains(permissionChecker, fileEntry, ActionKeys.UPDATE) && (!isLocked || hasLock) %>">
		<portlet:renderURL var="redirectURL">
			<portlet:param name="struts_action" value="/document_library/view" />
			<portlet:param name="folderId" value="<%= String.valueOf(fileEntry.getFolderId()) %>" />
		</portlet:renderURL>

		<portlet:actionURL var="revertURL">
			<portlet:param name="struts_action" value="/document_library/edit_file_entry" />
			<portlet:param name="<%= Constants.CMD %>" value="<%= Constants.REVERT %>" />
			<portlet:param name="redirect" value="<%= (fileVersionsCount == 1) ? redirectURL : currentURL %>" />
			<portlet:param name="fileEntryId" value="<%= String.valueOf(fileEntry.getFileEntryId()) %>" />
			<portlet:param name="version" value="<%= String.valueOf(fileVersion.getVersion()) %>" />
		</portlet:actionURL>

		<liferay-ui:icon
			image="undo"
			label="<%= false %>"
			message="revert"
			url="<%= revertURL %>"
		/>
	</c:if>
</liferay-ui:icon-menu>