<%
/**
 * Copyright (c) 2008-2010 Liferay, Inc. All rights reserved.
 *
 * This file is part of Liferay Social Office. Liferay Social Office is free
 * software: you can redistribute it and/or modify it under the terms of the GNU
 * Affero General Public License as published by the Free Software Foundation,
 * either version 3 of the License, or (at your option) any later version.
 *
 * Liferay Social Office is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Affero General Public License
 * for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * Liferay Social Office. If not, see http://www.gnu.org/licenses/agpl-3.0.html.
 */
%>

<%@ include file="/html/portlet/document_library/init.jsp" %>

<%
ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

Object result = row.getObject();

DLFileEntry fileEntry = null;
DLFileShortcut fileShortcut = null;

if (result instanceof DLFileEntry) {
	fileEntry = (DLFileEntry)result;
}
else {
	fileShortcut = (DLFileShortcut)result;
}
%>

<liferay-ui:icon-menu>
	<c:choose>
		<c:when test="<%= fileEntry != null %>">
			<c:if test="<%= DLFileEntryPermission.contains(permissionChecker, fileEntry, ActionKeys.VIEW) %>">
				<portlet:renderURL windowState="<%= WindowState.MAXIMIZED.toString() %>" var="viewURL">
					<portlet:param name="struts_action" value="/document_library/view_file_entry" />
					<portlet:param name="redirect" value="<%= currentURL %>" />
					<portlet:param name="folderId" value="<%= String.valueOf(fileEntry.getFolderId()) %>" />
					<portlet:param name="name" value="<%= HtmlUtil.unescape(fileEntry.getName()) %>" />
				</portlet:renderURL>

				<liferay-ui:icon image="view" url="<%= viewURL %>" />

				<%
				String downloadURL = themeDisplay.getPathMain() + "/document_library/get_file?p_l_id=" + themeDisplay.getPlid() + "&folderId=" + fileEntry.getFolderId() + "&name=" + HttpUtil.encodeURL(fileEntry.getName());
				%>

				<liferay-ui:icon image="download" url="<%= downloadURL %>" />
			</c:if>

			<c:if test="<%= DLFileEntryPermission.contains(permissionChecker, fileEntry, ActionKeys.PERMISSIONS) %>">
				<liferay-security:permissionsURL
					modelResource="<%= DLFileEntry.class.getName() %>"
					modelResourceDescription="<%= HtmlUtil.unescape(fileEntry.getTitleWithExtension()) %>"
					resourcePrimKey="<%= String.valueOf(fileEntry.getFileEntryId()) %>"
					var="permissionsURL"
				/>

				<liferay-ui:icon image="permissions" url="<%= permissionsURL %>" />
			</c:if>

			<c:if test="<%= DLFileEntryPermission.contains(permissionChecker, fileEntry, ActionKeys.DELETE) %>">
				<portlet:actionURL windowState="<%= WindowState.MAXIMIZED.toString() %>" var="deleteURL">
					<portlet:param name="struts_action" value="/document_library/edit_file_entry" />
					<portlet:param name="<%= Constants.CMD %>" value="<%= Constants.DELETE %>" />
					<portlet:param name="redirect" value="<%= currentURL %>" />
					<portlet:param name="folderId" value="<%= String.valueOf(fileEntry.getFolderId()) %>" />
					<portlet:param name="name" value="<%= HtmlUtil.unescape(fileEntry.getName()) %>" />
				</portlet:actionURL>

				<liferay-ui:icon-delete url="<%= deleteURL %>" />
			</c:if>
		</c:when>
		<c:otherwise>

			<%
			fileEntry = DLFileEntryLocalServiceUtil.getFileEntry(fileShortcut.getToFolderId(), HtmlUtil.unescape(fileShortcut.getToName()));
			%>

			<c:if test="<%= DLFileShortcutPermission.contains(permissionChecker, fileShortcut, ActionKeys.VIEW) %>">
				<portlet:renderURL windowState="<%= WindowState.MAXIMIZED.toString() %>" var="viewShortcutURL">
					<portlet:param name="struts_action" value="/document_library/view_file_shortcut" />
					<portlet:param name="redirect" value="<%= currentURL %>" />
					<portlet:param name="fileShortcutId" value="<%= String.valueOf(fileShortcut.getFileShortcutId()) %>" />
				</portlet:renderURL>

				<liferay-ui:icon image="view" url="<%= viewShortcutURL %>" />
			</c:if>

			<c:if test="<%= DLFileShortcutPermission.contains(permissionChecker, fileShortcut, ActionKeys.UPDATE) %>">
				<portlet:renderURL windowState="<%= WindowState.MAXIMIZED.toString() %>" var="editShortcutURL">
					<portlet:param name="struts_action" value="/document_library/edit_file_shortcut" />
					<portlet:param name="redirect" value="<%= currentURL %>" />
					<portlet:param name="fileShortcutId" value="<%= String.valueOf(fileShortcut.getFileShortcutId()) %>" />
				</portlet:renderURL>

				<liferay-ui:icon image="edit" url="<%= editShortcutURL %>" />
			</c:if>

			<c:if test="<%= DLFileShortcutPermission.contains(permissionChecker, fileShortcut, ActionKeys.PERMISSIONS) %>">
				<liferay-security:permissionsURL
					modelResource="<%= DLFileShortcut.class.getName() %>"
					modelResourceDescription="<%= fileEntry.getTitleWithExtension() %>"
					resourcePrimKey="<%= String.valueOf(fileShortcut.getFileShortcutId()) %>"
					var="shortcutPermissionsURL"
				/>

				<liferay-ui:icon image="permissions" url="<%= shortcutPermissionsURL %>" />
			</c:if>

			<c:if test="<%= DLFileShortcutPermission.contains(permissionChecker, fileShortcut, ActionKeys.DELETE) %>">
				<portlet:actionURL windowState="<%= WindowState.MAXIMIZED.toString() %>" var="deleteShortcutURL">
					<portlet:param name="struts_action" value="/document_library/edit_file_shortcut" />
					<portlet:param name="<%= Constants.CMD %>" value="<%= Constants.DELETE %>" />
					<portlet:param name="redirect" value="<%= currentURL %>" />
					<portlet:param name="fileShortcutId" value="<%= String.valueOf(fileShortcut.getFileShortcutId()) %>" />
				</portlet:actionURL>

				<liferay-ui:icon-delete url="<%= deleteShortcutURL %>" />
			</c:if>
		</c:otherwise>
	</c:choose>
</liferay-ui:icon-menu>