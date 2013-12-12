<%--
/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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
String randomNamespace = null;

if (portletName.equals(PortletKeys.DOCUMENT_LIBRARY)) {
	randomNamespace = PortalUtil.generateRandomKey(request, "portlet_document_library_folder_action") + StringPool.UNDERLINE;
}
else if (portletName.equals(PortletKeys.DOCUMENT_LIBRARY_DISPLAY)) {
	randomNamespace = PortalUtil.generateRandomKey(request, "portlet_document_library_display_folder_action") + StringPool.UNDERLINE;
}
else {
	randomNamespace = PortalUtil.generateRandomKey(request, "portlet_image_gallery_display_folder_action") + StringPool.UNDERLINE;
}

String redirect = currentURL;

ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

Folder folder = null;

long folderId = 0;

long repositoryId = 0;

if (row != null) {
	Object result = row.getObject();

	if (result instanceof Folder) {
		folder = (Folder)result;

		folderId = folder.getFolderId();

		repositoryId = folder.getRepositoryId();
	}
}
else {
	if (portletName.equals(PortletKeys.DOCUMENT_LIBRARY_DISPLAY) || portletName.equals(PortletKeys.MEDIA_GALLERY_DISPLAY)) {
		folder = (Folder)request.getAttribute("view.jsp-folder");

		folderId = GetterUtil.getLong((String)request.getAttribute("view.jsp-folderId"));

		repositoryId = GetterUtil.getLong((String)request.getAttribute("view.jsp-repositoryId"));
	}
	else {
		folder = (Folder)request.getAttribute("view_entries.jsp-folder");

		folderId = GetterUtil.getLong((String)request.getAttribute("view_entries.jsp-folderId"));

		repositoryId = GetterUtil.getLong((String)request.getAttribute("view_entries.jsp-repositoryId"));
	}
}

int status = WorkflowConstants.STATUS_APPROVED;

if (permissionChecker.isCompanyAdmin() || permissionChecker.isGroupAdmin(scopeGroupId)) {
	status = WorkflowConstants.STATUS_ANY;
}

boolean folderSelected = GetterUtil.getBoolean((String)request.getAttribute("view_entries.jsp-folderSelected"));

String modelResource = null;
String modelResourceDescription = null;
String resourcePrimKey = null;

boolean showPermissionsURL = false;

if (folder != null) {
	modelResource = DLFolderConstants.getClassName();
	modelResourceDescription = folder.getName();
	resourcePrimKey = String.valueOf(folderId);

	showPermissionsURL = DLFolderPermission.contains(permissionChecker, folder, ActionKeys.PERMISSIONS);
}
else {
	modelResource = "com.liferay.portlet.documentlibrary";
	modelResourceDescription = themeDisplay.getScopeGroupName();
	resourcePrimKey = String.valueOf(scopeGroupId);

	showPermissionsURL = DLPermission.contains(permissionChecker, scopeGroupId, ActionKeys.PERMISSIONS);
}

boolean showWhenSingleIcon = false;

if ((row == null) || portletId.equals(PortletKeys.DOCUMENT_LIBRARY)) {
	showWhenSingleIcon = true;
}

boolean view = false;

if ((row == null) && ((portletName.equals(PortletKeys.DOCUMENT_LIBRARY_DISPLAY) && !showMinimalActionButtons) || portletName.equals(PortletKeys.MEDIA_GALLERY_DISPLAY))) {
	view = true;
}

String iconMenuId = null;
%>

<liferay-util:buffer var="iconMenu">
	<liferay-ui:icon-menu direction='<%= showMinimalActionButtons ? "down" : "left" %>' extended="<%= showMinimalActionButtons ? false : true %>" icon="<%= showMinimalActionButtons ? StringPool.BLANK : null %>" message='<%= showMinimalActionButtons ? StringPool.BLANK : "actions" %>' showExpanded="<%= view %>" showWhenSingleIcon="<%= showWhenSingleIcon %>" triggerCssClass="btn">
		<c:if test="<%= showActions %>">
			<c:choose>
				<c:when test="<%= folder != null %>">

					<%
					boolean hasDeletePermission = DLFolderPermission.contains(permissionChecker, scopeGroupId, folderId, ActionKeys.DELETE);
					boolean hasUpdatePermission = DLFolderPermission.contains(permissionChecker, scopeGroupId, folderId, ActionKeys.UPDATE);
					%>

					<c:if test="<%= hasUpdatePermission && !folder.isMountPoint() %>">
						<portlet:renderURL var="editURL">
							<portlet:param name="struts_action" value="/document_library/edit_folder" />
							<portlet:param name="redirect" value="<%= redirect %>" />
							<portlet:param name="folderId" value="<%= String.valueOf(folderId) %>" />
							<portlet:param name="repositoryId" value="<%= String.valueOf(repositoryId) %>" />
						</portlet:renderURL>

						<liferay-ui:icon
							image="edit"
							url="<%= editURL %>"
						/>
					</c:if>

					<c:if test="<%= hasUpdatePermission && folder.isMountPoint() %>">
						<portlet:renderURL var="editURL">
							<portlet:param name="struts_action" value="/document_library/edit_repository" />
							<portlet:param name="redirect" value="<%= redirect %>" />
							<portlet:param name="folderId" value="<%= String.valueOf(folderId) %>" />
							<portlet:param name="repositoryId" value="<%= String.valueOf(repositoryId) %>" />
						</portlet:renderURL>

						<liferay-ui:icon
							image="edit"
							url="<%= editURL %>"
						/>
					</c:if>

					<c:if test="<%= hasUpdatePermission && !folder.isMountPoint() %>">
						<portlet:renderURL var="moveURL">
							<portlet:param name="struts_action" value="/document_library/move_folder" />
							<portlet:param name="redirect" value="<%= redirect %>" />
							<portlet:param name="folderId" value="<%= String.valueOf(folderId) %>" />
							<portlet:param name="repositoryId" value="<%= String.valueOf(repositoryId) %>" />
						</portlet:renderURL>

						<liferay-ui:icon
							image="submit"
							message="move"
							url="<%= moveURL %>"
						/>
					</c:if>

					<c:if test="<%= showPermissionsURL %>">
						<liferay-security:permissionsURL
							modelResource="<%= modelResource %>"
							modelResourceDescription="<%= HtmlUtil.escape(modelResourceDescription) %>"
							redirect="<%= currentURL %>"
							resourcePrimKey="<%= resourcePrimKey %>"
							var="permissionsURL"
							windowState="<%= LiferayWindowState.POP_UP.toString() %>"
						/>

						<liferay-ui:icon
							image="permissions"
							method="get"
							url="<%= permissionsURL %>"
							useDialog="<%= true %>"
						/>
					</c:if>

					<c:if test="<%= hasDeletePermission && !folder.isMountPoint() %>">
						<portlet:renderURL var="redirectURL">
							<portlet:param name="struts_action" value="/document_library/view" />
							<portlet:param name="folderId" value="<%= String.valueOf(folder.getParentFolderId()) %>" />
						</portlet:renderURL>

						<portlet:actionURL var="deleteURL">
							<portlet:param name="struts_action" value="/document_library/edit_folder" />
							<portlet:param name="<%= Constants.CMD %>" value="<%= ((folder.getModel() instanceof DLFolder) && TrashUtil.isTrashEnabled(scopeGroupId)) ? Constants.MOVE_TO_TRASH : Constants.DELETE %>" />
							<portlet:param name="redirect" value="<%= (view || folderSelected) ? redirectURL : redirect %>" />
							<portlet:param name="folderId" value="<%= String.valueOf(folderId) %>" />
						</portlet:actionURL>

						<liferay-ui:icon-delete trash="<%= ((folder.getModel() instanceof DLFolder) && TrashUtil.isTrashEnabled(scopeGroupId)) %>" url="<%= deleteURL %>" />
					</c:if>

					<c:if test="<%= hasDeletePermission && folder.isMountPoint() %>">
						<portlet:renderURL var="redirectURL">
							<portlet:param name="struts_action" value="/document_library/view" />
							<portlet:param name="folderId" value="<%= String.valueOf(folder.getParentFolderId()) %>" />
						</portlet:renderURL>

						<portlet:actionURL var="deleteURL">
							<portlet:param name="struts_action" value="/document_library/edit_repository" />
							<portlet:param name="<%= Constants.CMD %>" value="<%= Constants.DELETE %>" />
							<portlet:param name="redirect" value="<%= (view || folderSelected) ? redirectURL : redirect %>" />
							<portlet:param name="repositoryId" value="<%= String.valueOf(repositoryId) %>" />
						</portlet:actionURL>

						<liferay-ui:icon-delete url="<%= deleteURL %>" />
					</c:if>

					<c:if test="<%= DLFolderPermission.contains(permissionChecker, scopeGroupId, folderId, ActionKeys.ADD_FOLDER) && !folder.isMountPoint() %>">
						<portlet:renderURL var="addFolderURL">
							<portlet:param name="struts_action" value="/document_library/edit_folder" />
							<portlet:param name="redirect" value="<%= currentURL %>" />
							<portlet:param name="parentFolderId" value="<%= String.valueOf(folderId) %>" />
							<portlet:param name="repositoryId" value="<%= String.valueOf(repositoryId) %>" />
						</portlet:renderURL>

						<liferay-ui:icon
							image="add_folder"
							message="add-subfolder"
							url="<%= addFolderURL %>"
						/>
					</c:if>
				</c:when>
				<c:otherwise>

					<%
					boolean workflowEnabled = WorkflowEngineManagerUtil.isDeployed() && (WorkflowHandlerRegistryUtil.getWorkflowHandler(DLFileEntry.class.getName()) != null);
					%>

					<c:if test="<%= workflowEnabled && DLFolderPermission.contains(permissionChecker, scopeGroupId, folderId, ActionKeys.UPDATE) %>">
						<portlet:renderURL var="editURL">
							<portlet:param name="struts_action" value="/document_library/edit_folder" />
							<portlet:param name="redirect" value="<%= redirect %>" />
							<portlet:param name="folderId" value="<%= String.valueOf(folderId) %>" />
							<portlet:param name="repositoryId" value="<%= String.valueOf(repositoryId) %>" />
							<portlet:param name="rootFolder" value="true" />
						</portlet:renderURL>

						<liferay-ui:icon
							image="edit"
							url="<%= editURL %>"
						/>
					</c:if>

					<c:if test="<%= showPermissionsURL %>">
						<liferay-security:permissionsURL
							modelResource="<%= modelResource %>"
							modelResourceDescription="<%= HtmlUtil.escape(modelResourceDescription) %>"
							redirect="<%= currentURL %>"
							resourcePrimKey="<%= resourcePrimKey %>"
							var="permissionsURL"
							windowState="<%= LiferayWindowState.POP_UP.toString() %>"
						/>

						<liferay-ui:icon
							image="permissions"
							method="get"
							url="<%= permissionsURL %>"
							useDialog="<%= true %>"
						/>
					</c:if>

					<c:if test="<%= DLFolderPermission.contains(permissionChecker, scopeGroupId, folderId, ActionKeys.ADD_FOLDER) %>">
						<portlet:renderURL var="addFolderURL">
							<portlet:param name="struts_action" value="/document_library/edit_folder" />
							<portlet:param name="redirect" value="<%= currentURL %>" />
							<portlet:param name="parentFolderId" value="<%= String.valueOf(folderId) %>" />
							<portlet:param name="repositoryId" value="<%= String.valueOf(repositoryId) %>" />
						</portlet:renderURL>

						<liferay-ui:icon
							image="add_folder"
							message='<%= (folder != null) ? "add-subfolder" : "add-folder" %>'
							url="<%= addFolderURL %>"
						/>
					</c:if>

					<c:if test="<%= DLFolderPermission.contains(permissionChecker, scopeGroupId, folderId, ActionKeys.ADD_REPOSITORY) %>">
						<portlet:renderURL var="addRepositoryURL">
							<portlet:param name="struts_action" value="/document_library/edit_repository" />
							<portlet:param name="redirect" value="<%= currentURL %>" />
							<portlet:param name="folderId" value="<%= String.valueOf(folderId) %>" />
						</portlet:renderURL>

						<liferay-ui:icon
							image="add_drive"
							message="add-repository"
							url="<%= addRepositoryURL %>"
						/>
					</c:if>
				</c:otherwise>
			</c:choose>
		</c:if>

		<%
		boolean hasViewPermission = DLFolderPermission.contains(permissionChecker, scopeGroupId, folderId, ActionKeys.VIEW);
		%>

		<c:choose>
			<c:when test="<%= portletName.equals(PortletKeys.DOCUMENT_LIBRARY_DISPLAY) || portletName.equals(PortletKeys.MEDIA_GALLERY_DISPLAY) %>">
				<c:if test="<%= showActions && DLFolderPermission.contains(permissionChecker, scopeGroupId, folderId, ActionKeys.ADD_DOCUMENT) && ((folder == null) || !folder.isMountPoint()) %>">
					<c:if test="<%= ((folder == null) || folder.isSupportsMultipleUpload()) %>">
						<portlet:renderURL var="editFileEntryURL">
							<portlet:param name="struts_action" value="/document_library/upload_multiple_file_entries" />
							<portlet:param name="redirect" value="<%= currentURL %>" />
							<portlet:param name="backURL" value="<%= currentURL %>" />
							<portlet:param name="repositoryId" value="<%= String.valueOf(repositoryId) %>" />
							<portlet:param name="folderId" value="<%= String.valueOf(folderId) %>" />
						</portlet:renderURL>

						<liferay-ui:icon
							cssClass="hide upload-multiple-documents"
							image="../document_library/add_multiple_documents"
							message='<%= portletName.equals(PortletKeys.MEDIA_GALLERY_DISPLAY) ? "multiple-media" : "multiple-documents" %>'
							url="<%= editFileEntryURL %>"
						/>
					</c:if>

					<%
					int fileEntryTypesCount = DLFileEntryTypeServiceUtil.getFileEntryTypesCount(PortalUtil.getSiteAndCompanyGroupIds(themeDisplay));
					%>

					<liferay-portlet:renderURL var="editFileEntryURL" windowState="<%= (((folder == null) || folder.isSupportsMetadata()) && (fileEntryTypesCount > 0)) ? LiferayWindowState.POP_UP.toString() : WindowState.NORMAL.toString() %>">
						<portlet:param name="struts_action" value='<%= (((folder == null) || folder.isSupportsMetadata()) && (fileEntryTypesCount > 0)) ? "/document_library_display/select_add_file_entry_type" : "/document_library_display/edit_file_entry" %>' />
						<portlet:param name="redirect" value="<%= currentURL %>" />
						<portlet:param name="backURL" value="<%= currentURL %>" />
						<portlet:param name="repositoryId" value="<%= String.valueOf(repositoryId) %>" />
						<portlet:param name="folderId" value="<%= String.valueOf(folderId) %>" />
					</liferay-portlet:renderURL>

					<%
					String taglibEditURL = "javascript:Liferay.Util.openWindow({id: '" + renderResponse.getNamespace() + "selectFileEntryType', title: '" + UnicodeLanguageUtil.get(pageContext, portletName.equals(PortletKeys.MEDIA_GALLERY_DISPLAY) ? "select-media-type" : "select-document-type") + "', uri:'" + HtmlUtil.escapeURL(editFileEntryURL.toString()) + "'});";
					%>

					<liferay-ui:icon
						image="../document_library/add_document"
						message='<%= portletName.equals(PortletKeys.MEDIA_GALLERY_DISPLAY) ? "add-media" : "add-document" %>'
						url="<%= (((folder == null) || folder.isSupportsMetadata()) && (fileEntryTypesCount > 0)) ? taglibEditURL : editFileEntryURL %>"
					/>
				</c:if>

				<c:if test="<%= hasViewPermission && portletName.equals(PortletKeys.MEDIA_GALLERY_DISPLAY) && (DLAppServiceUtil.getFileEntriesAndFileShortcutsCount(repositoryId, folderId, status) > 0) %>">
					<liferay-ui:icon
						cssClass='<%= randomNamespace + "-slide-show" %>'
						image="../image_gallery_display/slide_show"
						message="view-slide-show"
						url="javascript:;"
					/>
				</c:if>

				<c:if test="<%= showActions && ((folder == null) || (!folder.isMountPoint() && folder.isSupportsShortcuts())) && DLFolderPermission.contains(permissionChecker, scopeGroupId, folderId, ActionKeys.ADD_SHORTCUT) %>">
					<portlet:renderURL var="editFileShortcutURL">
						<portlet:param name="struts_action" value="/document_library_display/edit_file_shortcut" />
						<portlet:param name="redirect" value="<%= currentURL %>" />
						<portlet:param name="repositoryId" value="<%= String.valueOf(repositoryId) %>" />
						<portlet:param name="folderId" value="<%= String.valueOf(folderId) %>" />
					</portlet:renderURL>

					<liferay-ui:icon
						image="add_instance"
						message="add-shortcut"
						url="<%= editFileShortcutURL %>"
					/>
				</c:if>
			</c:when>
			<c:when test="<%= portletName.equals(PortletKeys.TRASH) %>">

				<%
				boolean hasUpdatePermission = DLFolderPermission.contains(permissionChecker, scopeGroupId, folderId, ActionKeys.UPDATE);
				%>

				<c:if test="<%= hasUpdatePermission && ((folder == null) || !folder.isMountPoint()) %>">
					<liferay-portlet:renderURL portletName="<%= PortletKeys.DOCUMENT_LIBRARY %>" var="moveURL">
						<portlet:param name="struts_action" value="/document_library/move_folder" />
						<portlet:param name="<%= Constants.CMD %>" value="<%= Constants.MOVE_FROM_TRASH %>" />
						<portlet:param name="redirect" value="<%= redirect %>" />
						<portlet:param name="folderId" value="<%= String.valueOf(folderId) %>" />
						<portlet:param name="parentFolderId" value="<%= String.valueOf(DLFolderConstants.DEFAULT_PARENT_FOLDER_ID) %>" />
						<portlet:param name="repositoryId" value="<%= String.valueOf(repositoryId) %>" />
					</liferay-portlet:renderURL>

					<liferay-ui:icon
						image="submit"
						message="move"
						url="<%= moveURL %>"
					/>
				</c:if>
			</c:when>
		</c:choose>

		<c:if test="<%= hasViewPermission && portletDisplay.isWebDAVEnabled() && ((folder == null) || (folder.getRepositoryId() == scopeGroupId)) %>">

			<%
			iconMenuId = GetterUtil.getString((String)request.getAttribute("liferay-ui:icon-menu:id"));
			%>

			<liferay-ui:icon
				cssClass='<%= randomNamespace + "-webdav-action" %>'
				image="desktop"
				message="access-from-desktop"
				url="javascript:;"
			/>
		</c:if>
	</liferay-ui:icon-menu>
</liferay-util:buffer>

<c:choose>
	<c:when test="<%= (portletName.equals(PortletKeys.DOCUMENT_LIBRARY_DISPLAY) || portletName.equals(PortletKeys.MEDIA_GALLERY_DISPLAY)) && !showMinimalActionButtons %>">

		<%= iconMenu %>

	</c:when>
	<c:otherwise>
		<span class="entry-action overlay">

			<%= iconMenu %>

		</span>
	</c:otherwise>
</c:choose>

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

			<liferay-ui:input-resource
				cssClass="webdav-url-resource"
				url="<%= DLUtil.getWebDavURL(themeDisplay, folder, null) %>"
			/>
		</div>
	</div>
</div>

<portlet:renderURL var="viewSlideShowURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
	<portlet:param name="struts_action" value="/image_gallery_display/view_slide_show" />
	<portlet:param name="folderId" value="<%= String.valueOf(folderId) %>" />
</portlet:renderURL>

<aui:script use="uploader,liferay-util-window">
	if (!A.UA.ios && (A.Uploader.TYPE != 'none')) {
		var uploadMultipleDocumentsIcon = A.all('.upload-multiple-documents:hidden');

		uploadMultipleDocumentsIcon.show();
	}

	var slideShow = A.one('.<%= randomNamespace %>-slide-show');

	if (slideShow) {
		slideShow.on(
			'click',
			function(event) {
				var slideShowWindow = window.open('<%= viewSlideShowURL %>', 'slideShow', 'directories=no,location=no,menubar=no,resizable=yes,scrollbars=yes,status=no,toolbar=no');

				slideShowWindow.focus();
			}
		);
	}

	var webdavAction = A.one('.<%= randomNamespace %>-webdav-action');

	if (webdavAction) {
		webdavAction.on(
			'click',
			function(event) {
				event.preventDefault();

				var webdavDialog = Liferay.Util.Window.getWindow(
					{
						dialog: {
							bodyContent: A.one('#<%= randomNamespace %>webDav').html(),
							destroyOnHide: true
						},
						title: '<%= UnicodeLanguageUtil.get(pageContext, "access-from-desktop") %>'
					}
				);

				webdavDialog.after(
					'render',
					function(event) {
						var webdavURLInput = webdavDialog.get('boundingBox').one('.webdav-url-resource');

						webdavURLInput.focus();
					}
				);

				webdavDialog.on(
					'close',
					function(event) {
						var trigger = A.one('#<portlet:namespace /><%= iconMenuId %>Button');

						if (trigger) {
							trigger.focus();
						}
					}
				);

				webdavDialog.render();
			}
		);
	}
</aui:script>