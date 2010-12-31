<%--
/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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
--%>

<%@ include file="/html/portlet/document_library/init.jsp" %>

<%
ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);
%>

<c:choose>
	<c:when test="<%= row == null %>">

		<%
		String randomNamespace = PortalUtil.generateRandomKey(request, "portlet_document_library_folder_action") + StringPool.UNDERLINE;

		DLFolder folder = (DLFolder)request.getAttribute("view.jsp-folder");

		long folderId = GetterUtil.getLong((String)request.getAttribute("view.jsp-folderId"));
		%>

		<div class="folder-actions">
			<c:if test="<%= DLFolderPermission.contains(permissionChecker, scopeGroupId, folderId, ActionKeys.ADD_FOLDER) %>">
				<portlet:renderURL var="addFolderURL">
					<portlet:param name="struts_action" value="/document_library/edit_folder" />
					<portlet:param name="redirect" value="<%= currentURL %>" />
					<portlet:param name="parentFolderId" value="<%= String.valueOf(folderId) %>" />
				</portlet:renderURL>

				<input type="button" value="<liferay-ui:message key="add-folder" />" onClick="location.href='<%= addFolderURL %>'" />
			</c:if>

			<c:if test="<%= DLFolderPermission.contains(permissionChecker, scopeGroupId, folderId, ActionKeys.ADD_DOCUMENT) %>">
				<portlet:renderURL var="editFileEntryURL">
					<portlet:param name="struts_action" value="/document_library/edit_file_entry" />
					<portlet:param name="redirect" value="<%= currentURL %>" />
					<portlet:param name="folderId" value="<%= String.valueOf(folderId) %>" />
				</portlet:renderURL>

				<input type="button" value="<liferay-ui:message key="add-document" />" onClick="location.href='<%= editFileEntryURL %>'" />
			</c:if>

			<c:if test="<%= portletDisplay.isWebDAVEnabled() && DLFolderPermission.contains(permissionChecker, scopeGroupId, folderId, ActionKeys.VIEW) %>">
				<input type="button" class='<%= randomNamespace + "-webdav-action" %>' value="<liferay-ui:message key="access-from-desktop" />" />
			</c:if>
		</div>
		<div id="<%= randomNamespace %>webDav" style="display: none;">
			<div class="portlet-document-library">

				<%
				String webDavHelpMessage = null;

				if (BrowserSnifferUtil.isWindows(request)) {
					webDavHelpMessage = LanguageUtil.format(pageContext, "webdav-windows-help", new Object[] {"http://www.microsoft.com/downloads/details.aspx?FamilyId=17C36612-632E-4C04-9382-987622ED1D64", "http://www.liferay.com/web/guest/community/wiki/-/wiki/Main/WebDAV"});
				}
				else {
					webDavHelpMessage = LanguageUtil.format(pageContext, "webdav-help", "http://www.liferay.com/web/guest/community/wiki/-/wiki/Main/WebDAV");
				}
				%>

				<liferay-ui:message key="<%= webDavHelpMessage %>" />

				<br /><br />

				<div class="file-entry-field">
					<label><liferay-ui:message key="webdav-url" /></label>

					<%
					StringBuilder sb = new StringBuilder();

					if (folder != null) {
						DLFolder curFolder = folder;

						while (true) {
							sb.insert(0, HttpUtil.encodeURL(curFolder.getName(), true));
							sb.insert(0, StringPool.SLASH);

							if (curFolder.getParentFolderId() == DLFolderConstants.DEFAULT_PARENT_FOLDER_ID) {
								break;
							}
							else {
								curFolder = DLAppLocalServiceUtil.getFolder(curFolder.getParentFolderId());
							}
						}
					}

					Group group = themeDisplay.getScopeGroup();
					%>

					<liferay-ui:input-resource
						url='<%= themeDisplay.getPortalURL() + "/tunnel-web/secure/webdav" + group.getFriendlyURL() + "/document_library" + sb.toString() %>'
					/>
				</div>
			</div>
		</div>

		<aui:script use="aui-dialog">
			A.on(
				'click',
				function(event) {
					var popup = new A.Dialog(
						{
							bodyContent: A.get('#<%= randomNamespace %>webDav').html(),
							centered: true,
							destroyOnClose: true,
							modal: true,
							title: '<liferay-ui:message key="access-from-desktop" />',
							width: 500
						}
					)
					.render();

					event.preventDefault();
				},
				'.<%= randomNamespace %>-webdav-action'
			);
		</aui:script>
	</c:when>
	<c:otherwise>
		<liferay-util:include page="/html/portlet/document_library/folder_action.portal.jsp" />
	</c:otherwise>
</c:choose>