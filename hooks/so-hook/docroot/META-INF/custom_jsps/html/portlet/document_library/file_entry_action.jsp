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

DLFileEntry fileEntry = null;
DLFileShortcut fileShortcut = null;

boolean view = false;

if (row != null) {
	Object result = row.getObject();

	if (result instanceof AssetEntry) {
		AssetEntry assetEntry = (AssetEntry)result;

		if (assetEntry.getClassName().equals(DLFileEntry.class.getName())) {
			fileEntry = DLFileEntryLocalServiceUtil.getFileEntry(assetEntry.getClassPK());
		}
		else {
			fileShortcut = DLFileShortcutLocalServiceUtil.getFileShortcut(assetEntry.getClassPK());
		}
	}
	else if (result instanceof DLFileEntry) {
		fileEntry = (DLFileEntry)result;
	}
	else {
		fileShortcut = (DLFileShortcut)result;
	}
}
else {
	if (request.getAttribute("view_file_entry.jsp-fileEntry") != null) {
		fileEntry = (DLFileEntry)request.getAttribute("view_file_entry.jsp-fileEntry");
	}
	else {
		fileShortcut = (DLFileShortcut)request.getAttribute("view_file_shortcut.jsp-fileShortcut");
	}

	view = true;
}

long folderId = 0;

if (fileEntry != null) {
	folderId = fileEntry.getFolderId();
}
else if (fileShortcut != null) {
	folderId = fileShortcut.getFolderId();
}

String extension = StringPool.BLANK;

if (Validator.isNotNull(fileEntry.getTitle())) {
	extension = FileUtil.getExtension(fileEntry.getTitle());
}

PortletURL viewFolderURL = renderResponse.createRenderURL();

viewFolderURL.setParameter("struts_action", "/document_library/view");
viewFolderURL.setParameter("folderId", String.valueOf(folderId));
%>

<liferay-ui:icon-menu showExpanded="<%= view %>">
	<c:choose>
		<c:when test="<%= fileEntry != null %>">
			<c:if test="<%= DLFileEntryPermission.contains(permissionChecker, fileEntry, ActionKeys.VIEW) %>">
				<liferay-ui:icon
					image="download"
					message='<%= LanguageUtil.get(pageContext, "download") + " (" + TextFormatter.formatKB(fileEntry.getSize(), locale) + "k)" %>'
					url='<%= themeDisplay.getPortalURL() + themeDisplay.getPathContext() + "/documents/" + themeDisplay.getScopeGroupId() + StringPool.SLASH + fileEntry.getFolderId() + StringPool.SLASH + HttpUtil.encodeURL(HtmlUtil.unescape(fileEntry.getTitle())) %>'
				/>
			</c:if>

			<%
			String officeDoc = getOfficeDocumentType(fileEntry.getTitle());

			boolean viewOnlineEdit = Validator.isNotNull(officeDoc) && (!fileEntry.isLocked() || fileEntry.hasLock(user.getUserId())) && BrowserSnifferUtil.isIe(request);
			%>

			<c:if test="<%= !view && viewOnlineEdit && DLFileEntryPermission.contains(permissionChecker, fileEntry, ActionKeys.UPDATE) %>">

				<%
				String webDavUrl = getWebDavUrl(fileEntry, portletDisplay, themeDisplay);

				PortletURL lockURL = null;

				if (!fileEntry.hasLock(user.getUserId())) {
					lockURL = renderResponse.createActionURL();

					lockURL.setParameter("struts_action", "/document_library/edit_file_entry");
					lockURL.setParameter(Constants.CMD, Constants.LOCK);
					lockURL.setParameter("redirect", currentURL);
					lockURL.setParameter("folderId", String.valueOf(fileEntry.getFolderId()));
					lockURL.setParameter("name", fileEntry.getName());
				}

				String taglibOnlineEditUrl = "javascript:" + renderResponse.getNamespace() + "openDocument('" + officeDoc + "','" + webDavUrl+ "','" + (lockURL == null ? StringPool.BLANK : lockURL.toString()) + "');";
				%>

				<liferay-ui:icon
					image="../custom/online_edit"
					message="edit-document-online"
					url="<%= taglibOnlineEditUrl %>"
				/>
			</c:if>

			<c:if test="<%= !view %>">
				<%@ include file="/html/portlet/document_library/file_entry_action_lock.jspf" %>
			</c:if>

			<c:if test="<%= !view && DLFileEntryPermission.contains(permissionChecker, fileEntry, ActionKeys.PERMISSIONS) %>">
				<liferay-security:permissionsURL
					modelResource="<%= DLFileEntry.class.getName() %>"
					modelResourceDescription="<%= HtmlUtil.unescape(fileEntry.getTitle()) %>"
					resourcePrimKey="<%= String.valueOf(fileEntry.getFileEntryId()) %>"
					var="permissionsURL"
				/>

				<liferay-ui:icon
					image="permissions"
					url="<%= permissionsURL %>"
				/>
			</c:if>

			<c:if test="<%= !view && DLFileEntryPermission.contains(permissionChecker, fileEntry, ActionKeys.DELETE) %>">
				<portlet:actionURL var="deleteURL">
					<portlet:param name="struts_action" value="/document_library/edit_file_entry" />
					<portlet:param name="<%= Constants.CMD %>" value="<%= Constants.DELETE %>" />
					<portlet:param name="redirect" value="<%= viewFolderURL.toString() %>" />
					<portlet:param name="folderId" value="<%= String.valueOf(fileEntry.getFolderId()) %>" />
					<portlet:param name="name" value="<%= HtmlUtil.unescape(fileEntry.getName()) %>" />
				</portlet:actionURL>

				<liferay-ui:icon-delete url="<%= deleteURL %>" />
			</c:if>
		</c:when>
		<c:otherwise>

			<%
			fileEntry = DLFileEntryLocalServiceUtil.getFileEntry(fileShortcut.getGroupId(), fileShortcut.getToFolderId(), HtmlUtil.unescape(fileShortcut.getToName()));
			%>

			<c:if test="<%= DLFileShortcutPermission.contains(permissionChecker, fileShortcut, ActionKeys.VIEW) %>">
				<liferay-ui:icon
					image="download"
					message='<%= LanguageUtil.get(pageContext, "download") + " (" + TextFormatter.formatKB(fileEntry.getSize(), locale) + "k)" %>'
					url='<%= themeDisplay.getPortalURL() + themeDisplay.getPathContext() + "/documents/" + themeDisplay.getScopeGroupId() + StringPool.SLASH + fileEntry.getFolderId() + StringPool.SLASH + HttpUtil.encodeURL(HtmlUtil.unescape(fileEntry.getTitle())) %>'
				/>
			</c:if>

			<c:if test="<%= view && DLFileEntryPermission.contains(permissionChecker, fileEntry, ActionKeys.UPDATE) %>">
				<portlet:renderURL var="viewOriginalFileURL">
					<portlet:param name="struts_action" value="/document_library/view_file_entry" />
					<portlet:param name="redirect" value="<%= currentURL %>" />
					<portlet:param name="folderId" value="<%= String.valueOf(fileShortcut.getToFolderId()) %>" />
					<portlet:param name="name" value="<%= HtmlUtil.unescape(fileShortcut.getToName()) %>" />
				</portlet:renderURL>

				<liferay-ui:icon
					image="view"
					message="view-original-file"
					url="<%= viewOriginalFileURL %>"
				/>
			</c:if>

			<c:if test="<%= !view %>">
				<%@ include file="/html/portlet/document_library/file_entry_action_lock.jspf" %>
			</c:if>

			<c:if test="<%= !view && DLFileShortcutPermission.contains(permissionChecker, fileShortcut, ActionKeys.PERMISSIONS) %>">
				<liferay-security:permissionsURL
					modelResource="<%= DLFileShortcut.class.getName() %>"
					modelResourceDescription="<%= fileEntry.getTitle() %>"
					resourcePrimKey="<%= String.valueOf(fileShortcut.getFileShortcutId()) %>"
					var="shortcutPermissionsURL"
				/>

				<liferay-ui:icon
					image="permissions"
					url="<%= shortcutPermissionsURL %>"
				/>
			</c:if>

			<c:if test="<%= !view && DLFileShortcutPermission.contains(permissionChecker, fileShortcut, ActionKeys.DELETE) %>">
				<portlet:actionURL var="deleteShortcutURL">
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