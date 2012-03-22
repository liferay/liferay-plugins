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

FileEntry fileEntry = null;
DLFileShortcut fileShortcut = null;

boolean showWhenSingleIcon = false;

if (portletId.equals(PortletKeys.DOCUMENT_LIBRARY)) {
	showWhenSingleIcon = true;
}

if (row != null) {
	Object result = row.getObject();

	if (result instanceof AssetEntry) {
		AssetEntry assetEntry = (AssetEntry)result;

		if (assetEntry.getClassName().equals(DLFileEntryConstants.getClassName())) {
			fileEntry = DLAppLocalServiceUtil.getFileEntry(assetEntry.getClassPK());
		}
		else {
			fileShortcut = DLAppLocalServiceUtil.getFileShortcut(assetEntry.getClassPK());
		}
	}
	else if (result instanceof FileEntry) {
		fileEntry = (FileEntry)result;
	}
	else {
		fileShortcut = (DLFileShortcut)result;
	}
}
else {
	if (portletName.equals(PortletKeys.DOCUMENT_LIBRARY_DISPLAY)) {
		if (request.getAttribute("view_file_entry.jsp-fileEntry") != null) {
			fileEntry = (FileEntry)request.getAttribute("view_file_entry.jsp-fileEntry");

			if (request.getAttribute("view_file_entry.jsp-fileShortcut") != null) {
				fileShortcut = (DLFileShortcut)request.getAttribute("view_file_entry.jsp-fileShortcut");
			}
		}
		else {
			fileShortcut = (DLFileShortcut)request.getAttribute("view_file_shortcut.jsp-fileShortcut");
		}
	}
	else {
		if (request.getAttribute("view_entries.jsp-fileEntry") != null) {
			fileEntry = (FileEntry)request.getAttribute("view_entries.jsp-fileEntry");

			if (request.getAttribute("view_entries.jsp-fileShortcut") != null) {
				fileShortcut = (DLFileShortcut)request.getAttribute("view_entries.jsp-fileShortcut");
			}

		}
		else {
			fileShortcut = (DLFileShortcut)request.getAttribute("view_file_shortcut.jsp-fileShortcut");
		}
	}
}

long folderId = 0;

if (fileEntry != null) {
	folderId = fileEntry.getFolderId();
}
else if (fileShortcut != null) {
	folderId = fileShortcut.getFolderId();
}

PortletURL viewFolderURL = liferayPortletResponse.createRenderURL();

viewFolderURL.setParameter("struts_action", "/document_library/view");
viewFolderURL.setParameter("folderId", String.valueOf(folderId));
%>

<liferay-util:buffer var="iconMenu">
	<liferay-ui:icon-menu align='<%= portletName.equals(PortletKeys.DOCUMENT_LIBRARY_DISPLAY) ? "right" : "auto" %>' direction='<%= portletName.equals(PortletKeys.DOCUMENT_LIBRARY_DISPLAY) ? null : "down" %>' extended="<%= portletName.equals(PortletKeys.DOCUMENT_LIBRARY_DISPLAY) ? true : false %>" icon="<%= portletName.equals(PortletKeys.DOCUMENT_LIBRARY_DISPLAY) ? null : StringPool.BLANK %>" message='<%= portletName.equals(PortletKeys.DOCUMENT_LIBRARY_DISPLAY) ? "actions" : StringPool.BLANK %>' showExpanded="<%= false %>" showWhenSingleIcon="<%= showWhenSingleIcon %>">
		<c:choose>
			<c:when test="<%= (fileEntry != null) && (fileShortcut == null) %>">
				<c:if test="<%= DLFileEntryPermission.contains(permissionChecker, fileEntry, ActionKeys.VIEW) %>">
					<liferay-ui:icon
						image="download"
						message='<%= LanguageUtil.get(pageContext, "download") + " (" + TextFormatter.formatKB(fileEntry.getSize(), locale) + "k)" %>'
						url="<%= DLUtil.getPreviewURL(fileEntry, fileEntry.getFileVersion(), themeDisplay, StringPool.BLANK, false, true) %>"
					/>
				</c:if>

				<c:if test="<%= fileEntry.isCheckedOut() && fileEntry.hasLock() %>">

					<%@ include file="/html/portlet/document_library/file_entry_action_open_document.jspf" %>

					<portlet:renderURL var="editURL">
						<portlet:param name="struts_action" value="/document_library/edit_file_entry" />
						<portlet:param name="redirect" value="<%= currentURL %>" />
						<portlet:param name="backURL" value="<%= currentURL %>" />
						<portlet:param name="fileEntryId" value="<%= String.valueOf(fileEntry.getFileEntryId()) %>" />
					</portlet:renderURL>

					<liferay-ui:icon
						image="edit"
						url="<%= editURL %>"
					/>
				</c:if>

				<c:if test="<%= showActions && DLFileEntryPermission.contains(permissionChecker, fileEntry, ActionKeys.UPDATE) %>">
					<portlet:renderURL var="moveURL">
						<portlet:param name="struts_action" value="/document_library/move_file_entry" />
						<portlet:param name="redirect" value="<%= viewFolderURL.toString() %>" />
						<portlet:param name="fileEntryId" value="<%= String.valueOf(fileEntry.getFileEntryId()) %>" />
					</portlet:renderURL>

					<liferay-ui:icon
						image="submit"
						message="move"
						url="<%= moveURL %>"
					/>
				</c:if>

				<c:if test="<%= showActions %>">
					<%@ include file="/html/portlet/document_library/file_entry_action_lock.jspf" %>
				</c:if>

				<c:if test="<%= showActions && DLFileEntryPermission.contains(permissionChecker, fileEntry, ActionKeys.PERMISSIONS) %>">
					<liferay-security:permissionsURL
						modelResource="<%= DLFileEntryConstants.getClassName() %>"
						modelResourceDescription="<%= HtmlUtil.unescape(fileEntry.getTitle()) %>"
						redirect="<%= currentURL %>"
						resourcePrimKey="<%= String.valueOf(fileEntry.getFileEntryId()) %>"
						var="permissionsURL"
					/>

					<liferay-ui:icon
						image="permissions"
						url="<%= permissionsURL %>"
					/>
				</c:if>

				<c:if test="<%= showActions && DLFileEntryPermission.contains(permissionChecker, fileEntry, ActionKeys.DELETE) %>">
					<portlet:actionURL var="deleteURL">
						<portlet:param name="struts_action" value="/document_library/edit_file_entry" />
						<portlet:param name="<%= Constants.CMD %>" value="<%= Constants.DELETE %>" />
						<portlet:param name="redirect" value="<%= viewFolderURL.toString() %>" />
						<portlet:param name="fileEntryId" value="<%= String.valueOf(fileEntry.getFileEntryId()) %>" />
					</portlet:actionURL>

					<liferay-ui:icon-delete
						url="<%= deleteURL %>"
					/>
				</c:if>
			</c:when>
			<c:otherwise>

				<%
				fileEntry = DLAppLocalServiceUtil.getFileEntry(fileShortcut.getToFileEntryId());
				%>

				<c:if test="<%= DLFileShortcutPermission.contains(permissionChecker, fileShortcut, ActionKeys.VIEW) %>">
					<liferay-ui:icon
						image="download"
						message='<%= LanguageUtil.get(pageContext, "download") + " (" + TextFormatter.formatKB(fileEntry.getSize(), locale) + "k)" %>'
						url='<%= themeDisplay.getPortalURL() + themeDisplay.getPathContext() + "/documents/" + fileShortcut.getFileShortcutId() %>'
					/>
				</c:if>

				<%@ include file="/html/portlet/document_library/file_entry_action_open_document.jspf" %>

				<c:if test="<%= DLFileEntryPermission.contains(permissionChecker, fileEntry, ActionKeys.UPDATE) %>">
					<portlet:renderURL var="viewOriginalFileURL">
						<portlet:param name="struts_action" value="/document_library/view_file_entry" />
						<portlet:param name="redirect" value="<%= currentURL %>" />
						<portlet:param name="fileEntryId" value="<%= String.valueOf(fileShortcut.getToFileEntryId()) %>" />
					</portlet:renderURL>

					<liferay-ui:icon
						image="view"
						message="view-original-file"
						url="<%= viewOriginalFileURL %>"
					/>
				</c:if>

				<c:if test="<%= showActions %>">
					<%@ include file="/html/portlet/document_library/file_entry_action_lock.jspf" %>
				</c:if>

				<c:if test="<%= showActions && DLFileShortcutPermission.contains(permissionChecker, fileShortcut, ActionKeys.UPDATE) %>">
					<portlet:renderURL var="editShortcutURL">
						<portlet:param name="struts_action" value="/document_library/edit_file_shortcut" />
						<portlet:param name="redirect" value="<%= currentURL %>" />
						<portlet:param name="fileShortcutId" value="<%= String.valueOf(fileShortcut.getFileShortcutId()) %>" />
					</portlet:renderURL>

					<liferay-ui:icon
						image="edit"
						url="<%= editShortcutURL %>"
					/>
				</c:if>

				<c:if test="<%= showActions && DLFileShortcutPermission.contains(permissionChecker, fileShortcut, ActionKeys.PERMISSIONS) %>">
					<liferay-security:permissionsURL
						modelResource="<%= DLFileShortcut.class.getName() %>"
						modelResourceDescription="<%= fileEntry.getTitle() %>"
						redirect="<%= currentURL %>"
						resourcePrimKey="<%= String.valueOf(fileShortcut.getFileShortcutId()) %>"
						var="shortcutPermissionsURL"
					/>

					<liferay-ui:icon
						image="permissions"
						url="<%= shortcutPermissionsURL %>"
					/>
				</c:if>

				<c:if test="<%= showActions && DLFileShortcutPermission.contains(permissionChecker, fileShortcut, ActionKeys.DELETE) %>">
					<portlet:actionURL var="deleteShortcutURL">
						<portlet:param name="struts_action" value="/document_library/edit_file_shortcut" />
						<portlet:param name="<%= Constants.CMD %>" value="<%= Constants.DELETE %>" />
						<portlet:param name="redirect" value="<%= viewFolderURL.toString() %>" />
						<portlet:param name="fileShortcutId" value="<%= String.valueOf(fileShortcut.getFileShortcutId()) %>" />
					</portlet:actionURL>

					<liferay-ui:icon-delete
						url="<%= deleteShortcutURL %>"
					/>
				</c:if>
			</c:otherwise>
		</c:choose>
	</liferay-ui:icon-menu>
</liferay-util:buffer>

<c:choose>
	<c:when test="<%= portletName.equals(PortletKeys.DOCUMENT_LIBRARY_DISPLAY) %>">

		<%= iconMenu %>

	</c:when>
	<c:otherwise>
		<span class="overlay document-action">

			<%= iconMenu %>

		</span>
	</c:otherwise>
</c:choose>