<%--
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
--%>

<%@ include file="/html/portlet/document_library/init.jsp" %>

<%
String strutsAction = ParamUtil.getString(request, "struts_action");

String tabs2 = ParamUtil.getString(request, "tabs2", "version-history");

String redirect = ParamUtil.getString(request, "redirect");

String referringPortletResource = ParamUtil.getString(request, "referringPortletResource");

String uploadProgressId = "dlFileEntryUploadProgress";

DLFileEntry fileEntry = (DLFileEntry)request.getAttribute(WebKeys.DOCUMENT_LIBRARY_FILE_ENTRY);

fileEntry = fileEntry.toEscapedModel();

long fileEntryId = fileEntry.getFileEntryId();
long folderId = fileEntry.getFolderId();
String name = fileEntry.getName();
String title = fileEntry.getTitle();

String extension = StringPool.BLANK;

if (Validator.isNotNull(title)) {
	extension = FileUtil.getExtension(title);
}

String[] conversions = new String[0];

if (PrefsPropsUtil.getBoolean(PropsKeys.OPENOFFICE_SERVER_ENABLED, PropsValues.OPENOFFICE_SERVER_ENABLED)) {
	conversions = (String[])DocumentConversionUtil.getConversions(extension);
}

DLFolder folder = fileEntry.getFolder();

Lock lock = null;
Boolean isLocked = Boolean.FALSE;
Boolean hasLock = Boolean.FALSE;

try {
	lock = LockLocalServiceUtil.getLock(DLFileEntry.class.getName(), DLUtil.getLockId(fileEntry.getGroupId(), fileEntry.getFolderId(), fileEntry.getName()));

	isLocked = Boolean.TRUE;

	if (lock.getUserId() == user.getUserId()) {
		hasLock = Boolean.TRUE;
	}
}
catch (Exception e) {
}

String fileUrl = themeDisplay.getPortalURL() + themeDisplay.getPathContext() + "/documents/" + themeDisplay.getScopeGroupId() + StringPool.SLASH + folderId + StringPool.SLASH + HttpUtil.encodeURL(HtmlUtil.unescape(title));
String webDavUrl = StringPool.BLANK;

if (portletDisplay.isWebDAVEnabled()) {
	StringBuilder sb = new StringBuilder();

	if (folderId != DLFolderConstants.DEFAULT_PARENT_FOLDER_ID) {
		DLFolder curFolder = DLFolderLocalServiceUtil.getFolder(folderId);

		while (true) {
			sb.insert(0, HttpUtil.encodeURL(curFolder.getName(), true));
			sb.insert(0, StringPool.SLASH);

			if (curFolder.getParentFolderId() == DLFolderConstants.DEFAULT_PARENT_FOLDER_ID) {
				break;
			}
			else {
				curFolder = DLFolderLocalServiceUtil.getFolder(curFolder.getParentFolderId());
			}
		}
	}

	sb.append(StringPool.SLASH);
	sb.append(HttpUtil.encodeURL(HtmlUtil.unescape(title), true));

	Group group = themeDisplay.getScopeGroup();

	webDavUrl = themeDisplay.getPortalURL() + "/tunnel-web/secure/webdav" + group.getFriendlyURL() + "/document_library" + sb.toString();
}

UnicodeProperties extraSettingsProperties = fileEntry.getExtraSettingsProperties();

String versionText = LanguageUtil.format(pageContext, "version-x", fileEntry.getVersion());

if (Validator.isNull(fileEntry.getVersion())) {
	versionText = LanguageUtil.get(pageContext, "not-approved");
}

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("struts_action", strutsAction);
portletURL.setParameter("tabs2", tabs2);
portletURL.setParameter("redirect", redirect);
portletURL.setParameter("folderId", String.valueOf(folderId));
portletURL.setParameter("name", name);

request.setAttribute("view_file_entry.jsp-fileEntry", fileEntry);
%>

<liferay-util:include page="/html/portlet/document_library/sidebar.jsp" />

<div class="breadcrumbs">
	<%= getFolderBreadcrumbs(folderId, pageContext, renderResponse) %>

	<h6 class="file-entry-title"><%= fileEntry.getTitle() + " <span>" + versionText + "</span>" %></h6>
</div>

<c:if test="<%= isLocked %>">
	<c:choose>
		<c:when test="<%= hasLock %>">

			<%
			String lockExpirationTime = LanguageUtil.getTimeDescription(pageContext, DLFileEntryImpl.LOCK_EXPIRATION_TIME).toLowerCase();
			%>

			<div class="portlet-msg-success">
				<%= LanguageUtil.format(pageContext, "you-now-have-a-lock-on-this-document", lockExpirationTime, false) %>
			</div>
		</c:when>
		<c:otherwise>
			<div class="portlet-msg-error">
				<%= LanguageUtil.format(pageContext, "you-cannot-modify-this-document-because-it-was-locked-by-x-on-x", new Object[] {PortalUtil.getUserName(lock.getUserId(), String.valueOf(lock.getUserId())), dateFormatDateTime.format(lock.getCreateDate())}, false) %>
			</div>
		</c:otherwise>
	</c:choose>
</c:if>

<aui:layout cssClass="layout-container">
	<aui:column columnWidth="<%= 70 %>" cssClass="layout-column-content" first="<%= true %>">
		<h2><liferay-ui:message key="document-properties" /></h2>

		<table class="lfr-table">
		<tr>
			<td class="lfr-label">
				<liferay-ui:message key="name" />
			</td>
			<td>
				<a href="<%= fileUrl %>"><%= fileEntry.getTitle() %></a>
			</td>
		</tr>
		<tr>
			<td class="lfr-label">
				<liferay-ui:message key="title" />
			</td>
			<td>
				<%= GetterUtil.getString(extraSettingsProperties.getProperty("name")) %>
			</td>
		</tr>
		<tr>
			<td class="lfr-label">
				<liferay-ui:message key="description" />
			</td>
			<td>
				<%= GetterUtil.getString(extraSettingsProperties.getProperty("description")) %>
			</td>
		</tr>
		<tr>
			<td class="lfr-label">
				<liferay-ui:message key="current-revision" />
			</td>
			<td>
				<%= fileEntry.getVersion() %>
			</td>
		</tr>
		<tr>
			<td class="lfr-label">
				<liferay-ui:message key="size" />
			</td>
			<td>
				<%= TextFormatter.formatKB(fileEntry.getSize(), locale) %>k
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<br />
			</td>
		</tr>
		<tr>
			<td class="lfr-label">
				<liferay-ui:message key="author" />
			</td>
			<td>
				<%= GetterUtil.getString(extraSettingsProperties.getProperty("author")) %>
			</td>
		</tr>
		<tr>
			<td class="lfr-label">
				<liferay-ui:message key="creator" />
			</td>
			<td>
				<%= fileEntry.getUserName() %>
			</td>
		</tr>
		<tr>
			<td class="lfr-label">
				<liferay-ui:message key="create-date" />
			</td>
			<td>
				<%= longDateFormatDateTime.format(fileEntry.getCreateDate())  %>
			</td>
		</tr>
		<tr>
			<td class="lfr-label">
				<liferay-ui:message key="last-modified-by" />
			</td>
			<td>
				<%= fileEntry.getVersionUserName() %>
			</td>
		</tr>
		<tr>
			<td class="lfr-label">
				<liferay-ui:message key="modified-date" />
			</td>
			<td>
				<%= longDateFormatDateTime.format(fileEntry.getModifiedDate())  %>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<br />
			</td>
		</tr>
		<tr>
			<td class="lfr-label">
				<liferay-ui:message key="num-of-downloads" />
			</td>
			<td>
				<%= fileEntry.getReadCount() %>
			</td>
		</tr>
		<tr>
			<td class="lfr-label">
				<liferay-ui:message key="url" />
			</td>
			<td>
				<liferay-ui:input-resource url='<%= themeDisplay.getPortalURL() + themeDisplay.getPathContext() + "/documents/" + themeDisplay.getScopeGroupId() + StringPool.SLASH + fileEntry.getUuid() %>' />
			</td>
		</tr>

		<c:if test="<%= portletDisplay.isWebDAVEnabled() %>">
			<tr>
				<td class="lfr-label">
					<liferay-ui:message key="webdav-url" />
				</td>
				<td>
					<liferay-ui:input-resource url="<%= webDavUrl %>" />
				</td>
			</tr>
		</c:if>

		</table>

		<br />

		<h2><liferay-ui:message key="social-data-and-revision-history" /></h2>

		<liferay-ui:asset-tags-summary
			className="<%= DLFileEntry.class.getName() %>"
			classPK="<%= fileEntryId %>"
		/>

		<liferay-ui:ratings
			className="<%= DLFileEntry.class.getName() %>"
			classPK="<%= fileEntryId %>"
		/>

		<br />

		<liferay-ui:tabs
			names="version-history,comments"
			param="tabs2"
			url="<%= portletURL.toString() %>"
		/>

		<c:choose>
			<c:when test='<%= tabs2.equals("version-history") %>'>

				<%
				boolean comparableFileEntry = false;

				String[] comparableFileExtensions = PropsValues.DL_COMPARABLE_FILE_EXTENSIONS;

				for (int i = 0; i < comparableFileExtensions.length; i++) {
					if (StringPool.STAR.equals(comparableFileExtensions[i]) ||
						StringUtil.endsWith(name, comparableFileExtensions[i])) {

						comparableFileEntry = true;

						break;
					}
				}

				SearchContainer searchContainer = new SearchContainer();

				List<String> headerNames = new ArrayList<String>();

				headerNames.add(StringPool.BLANK);

				searchContainer.setHeaderNames(headerNames);

				if (comparableFileEntry) {
					searchContainer.setRowChecker(new RowChecker(renderResponse, RowChecker.ALIGN, RowChecker.VALIGN, RowChecker.FORM_NAME, null, RowChecker.ROW_IDS));
				}

				List results = DLFileVersionLocalServiceUtil.getFileVersions(scopeGroupId, folderId, name, WorkflowConstants.STATUS_ANY);
				List resultRows = searchContainer.getResultRows();

				for (int i = 0; i < results.size(); i++) {
					DLFileVersion fileVersion = (DLFileVersion)results.get(i);

					ResultRow row = new ResultRow(new Object[] {fileEntry, fileVersion, conversions, portletURL, isLocked, hasLock}, String.valueOf(fileVersion.getVersion()), i);

					// File Version

					row.addJSP("/html/portlet/document_library/file_version_info.jsp");

					resultRows.add(row);
				}
				%>

				<liferay-ui:search-iterator searchContainer="<%= searchContainer %>" paginate="<%= false %>" />
			</c:when>
			<c:when test='<%= tabs2.equals("comments") %>'>
				<c:if test="<%= DLFileEntryPermission.contains(permissionChecker, fileEntry, ActionKeys.ADD_DISCUSSION) %>">
					<portlet:actionURL var="discussionURL">
						<portlet:param name="struts_action" value="/document_library/edit_file_entry_discussion" />
					</portlet:actionURL>

					<%
					List<DLFileVersion> fileVersions = DLFileVersionLocalServiceUtil.getFileVersions(scopeGroupId, folderId, name, WorkflowConstants.STATUS_APPROVED);

					for (DLFileVersion fileVersion : fileVersions) {
					%>

						<h3><liferay-ui:message key="revision" /> <%= fileVersion.getVersion() %></h3>

						<liferay-ui:discussion
							formName='<%= "fm2" + fileVersion.getFileVersionId() %>'
							formAction="<%= discussionURL %>"
							className="<%= DLFileVersion.class.getName() %>"
							classPK="<%= fileVersion.getFileVersionId() %>"
							permissionClassName="<%= DLFileEntry.class.getName() %>"
							permissionClassPK="<%= fileEntry.getFileEntryId() %>"
							userId="<%= fileVersion.getUserId() %>"
							subject="<%= fileEntry.getTitle() %>"
							redirect="<%= currentURL %>"
							ratingsEnabled="<%= enableCommentRatings %>"
						/>

					<%
					}
					%>

				</c:if>
			</c:when>
		</c:choose>
	</aui:column>

	<aui:column columnWidth="<%= 30 %>" cssClass="layout-column-side" last="<%= true %>">
		<h2><liferay-ui:message key="document-actions" /></h2>

		<ul class="document-action-list">
			<li class="action-download">
				<span>
					<liferay-ui:icon
						image='<%= "../file_system/small/" + extension %>'
						message="<%= extension.toUpperCase() %>"
						url='<%= fileUrl %>'
						label="<%= true %>"
					/>

					<%
					for (int i = 0; i < conversions.length; i++) {
						String conversion = conversions[i];
					%>

						<span>
							<liferay-ui:icon
								image='<%= "../file_system/small/" + conversion %>'
								message="<%= conversion.toUpperCase() %>"
								url='<%= fileUrl + "?version=" + fileEntry.getVersion() + "&targetExtension=" + conversion %>'
								label="<%= true %>"
							/>
						</span>

					<%
					}
					%>

				</span>
			</li>
		</ul>

		<c:if test="<%= DLFileEntryPermission.contains(permissionChecker, fileEntry, ActionKeys.UPDATE) %>">
			<ul class="document-action-list">
				<c:choose>
					<c:when test="<%= (isLocked && hasLock) %>">
						<li class="action-unlock"><a href="javascript:;" onClick="<portlet:namespace />unlock();"><liferay-ui:message key="unlock-document" /></a></li>

						<portlet:renderURL windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>" var="editURL">
							<portlet:param name="struts_action" value="/document_library/edit_file_entry" />
							<portlet:param name="display_section" value="upload" />
							<portlet:param name="redirect" value="<%= currentURL %>" />
							<portlet:param name="folderId" value="<%= String.valueOf(fileEntry.getFolderId()) %>" />
							<portlet:param name="name" value="<%= HtmlUtil.unescape(fileEntry.getName()) %>" />
						</portlet:renderURL>

						<li class="action-upload"><a href="javascript:;" onClick="Liferay.SO.DocumentLibrary.displayPopup('<%= editURL %>', '<liferay-ui:message key="upload-new-revision" />');"><liferay-ui:message key="upload-new-revision" /></a></li>
					</c:when>
					<c:when test="<%= (isLocked.booleanValue() && permissionChecker.isCommunityAdmin(fileEntry.getGroupId())) %>">
						<li class="action-unlock"><a href="javascript:;" onClick="<portlet:namespace />unlock();"><liferay-ui:message key="unlock-document" /></a></li>
						<li class="action-upload"><liferay-ui:message key="upload-new-revision" /></li>
					</c:when>
					<c:when test="<%= isLocked %>">
						<li class="action-unlock"><liferay-ui:message key="unlock-document" /></li>
						<li class="action-upload"><liferay-ui:message key="upload-new-revision" /></li>
					</c:when>
					<c:otherwise>
						<li class="action-lock"><a href="javascript:;" onClick="<portlet:namespace />lock();"><liferay-ui:message key="lock-document" /></a></li>
						<li class="action-upload"><liferay-ui:message key="upload-new-revision" /></li>
					</c:otherwise>
				</c:choose>
			</ul>

			<%
			String officeDoc = null;

			if (portletDisplay.isWebDAVEnabled() && BrowserSnifferUtil.isIe(request)) {
				if (extension.equalsIgnoreCase("doc") ||
					extension.equalsIgnoreCase("dot") ||
					extension.equalsIgnoreCase("ppt") ||
					extension.equalsIgnoreCase("xls")) {

					officeDoc = "SharePoint.OpenDocuments.1";
				}
				else if (extension.equalsIgnoreCase("docx") ||
						 extension.equalsIgnoreCase("pptx") ||
						 extension.equalsIgnoreCase("xlsx")) {

					officeDoc = "SharePoint.OpenDocuments.2";
				}
			}
			%>

			<c:if test="<%= Validator.isNotNull(officeDoc) %>">
				<aui:script>
					function <portlet:namespace />openDocument() {
						var fileUrl = "<%= fileUrl %>";
						var webDavUrl = "<%= webDavUrl %>";

						var officeDoc = new ActiveXObject("<%= officeDoc %>");

						if (officeDoc) {
							officeDoc.EditDocument(webDavUrl);

							<c:if test="<%= !isLocked %>">
								<portlet:namespace />lock();
							</c:if>
						}
						else {
							window.location.href = fileUrl;
						}
					}
				</aui:script>

				<ul class="document-action-list">
					<c:choose>
						<c:when test="<%= (isLocked && hasLock) %>">
							<li class="action-edit-office"><a href="javascript:;" onClick="<portlet:namespace />openDocument();"><liferay-ui:message key="edit-document-online" /></a></li>

							<portlet:renderURL windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>" var="onlineEditURL">
								<portlet:param name="struts_action" value="/document_library/edit_file_entry" />
								<portlet:param name="display_section" value="online" />
								<portlet:param name="redirect" value="<%= currentURL %>" />
								<portlet:param name="folderId" value="<%= String.valueOf(fileEntry.getFolderId()) %>" />
								<portlet:param name="name" value="<%= HtmlUtil.unescape(fileEntry.getName()) %>" />
							</portlet:renderURL>

							<li class="action-save-office"><a href="javascript:;" onClick="Liferay.SO.DocumentLibrary.displayPopup('<%= onlineEditURL %>', '<liferay-ui:message key="finish-editing-online" />');"><liferay-ui:message key="finish-editing-online" /></a></li>
						</c:when>
						<c:when test="<%= isLocked %>">
							<li class="action-edit-office"><liferay-ui:message key="edit-document-online" /></li>
							<li class="action-save-office"><liferay-ui:message key="finish-editing-online" /></li>
						</c:when>
						<c:otherwise>
							<li class="action-edit-office"><a href="javascript:;" onClick="<portlet:namespace />openDocument();"><liferay-ui:message key="edit-document-online" /></a></li>
							<li class="action-save-office"><liferay-ui:message key="finish-editing-online" /></li>
						</c:otherwise>
					</c:choose>
				</ul>
			</c:if>
		</c:if>

		<ul class="document-action-list">
			<c:choose>
				<c:when test="<%= !isLocked || (isLocked && hasLock) %>">
					<c:if test="<%= DLFileEntryPermission.contains(permissionChecker, fileEntry, ActionKeys.UPDATE) %>">
						<li class="action-move"><a href="javascript:;" onClick="var folderWindow = window.open('<portlet:renderURL windowState="<%= LiferayWindowState.POP_UP.toString() %>"><portlet:param name="struts_action" value="/document_library/select_folder" /><portlet:param name="folderId" value="<%= String.valueOf(folderId) %>" /></portlet:renderURL>', 'folder', 'directories=no,height=640,location=no,menubar=no,resizable=yes,scrollbars=yes,status=no,toolbar=no,width=680'); void(''); folderWindow.focus();"><liferay-ui:message key="move-to" /></a></li>

						<portlet:renderURL windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>" var="editURL">
							<portlet:param name="struts_action" value="/document_library/edit_file_entry" />
							<portlet:param name="<%= Constants.CMD %>" value="<%= Constants.DELETE %>" />
							<portlet:param name="display_section" value="properties" />
							<portlet:param name="redirect" value="<%= currentURL %>" />
							<portlet:param name="folderId" value="<%= String.valueOf(fileEntry.getFolderId()) %>" />
							<portlet:param name="name" value="<%= HtmlUtil.unescape(fileEntry.getName()) %>" />
						</portlet:renderURL>

						<li class="action-edit"><a href="javascript:;" onClick="Liferay.SO.DocumentLibrary.displayPopup('<%= editURL %>', '<liferay-ui:message key="edit-properties" />');"><liferay-ui:message key="edit-properties" /></a></li>
					</c:if>

					<c:if test="<%= DLFileEntryPermission.contains(permissionChecker, fileEntry, ActionKeys.PERMISSIONS) %>">
						<liferay-security:permissionsURL
							modelResource="<%= DLFileEntry.class.getName() %>"
							modelResourceDescription="<%= HtmlUtil.unescape(fileEntry.getTitle()) %>"
							resourcePrimKey="<%= String.valueOf(fileEntry.getFileEntryId()) %>"
							var="permissionsURL"
						/>

						<li class="action-permissions"><a href="<%= permissionsURL %>"><liferay-ui:message key="edit-permissions" /></a></li>
					</c:if>

					<c:if test="<%= DLFileEntryPermission.contains(permissionChecker, fileEntry, ActionKeys.DELETE) %>">
						<portlet:actionURL windowState="<%= WindowState.MAXIMIZED.toString() %>" var="deleteURL">
							<portlet:param name="struts_action" value="/document_library/edit_file_entry" />
							<portlet:param name="<%= Constants.CMD %>" value="<%= Constants.DELETE %>" />
							<portlet:param name="redirect" value="<%= redirect %>" />
							<portlet:param name="folderId" value="<%= String.valueOf(fileEntry.getFolderId()) %>" />
							<portlet:param name="name" value="<%= HtmlUtil.unescape(fileEntry.getName()) %>" />
						</portlet:actionURL>

						<li class="action-delete"><a href="javascript:;" onClick="return (confirm('<liferay-ui:message key="are-you-sure-you-want-to-delete-this" />') && submitForm(document.hrefFm, '<%= deleteURL %>'));"><liferay-ui:message key="delete-document-and-all-revisions" /></a></li>
					</c:if>
				</c:when>
				<c:otherwise>
					<li class="action-move"><liferay-ui:message key="move-to" /></li>
					<li class="action-permissions"><liferay-ui:message key="edit-permissions" /></li>
					<li class="action-delete"><liferay-ui:message key="delete-document-and-all-revisions" /></li>
				</c:otherwise>
			</c:choose>
		</ul>
	</aui:column>
</aui:layout>

<aui:script>
	function <portlet:namespace />lock() {
		submitForm(document.hrefFm, "<portlet:actionURL><portlet:param name="struts_action" value="/document_library/edit_file_entry" /><portlet:param name="<%= Constants.CMD %>" value="<%= Constants.LOCK %>" /><portlet:param name="redirect" value="<%= currentURL %>" /><portlet:param name="folderId" value="<%= String.valueOf(folderId) %>" /><portlet:param name="name" value="<%= name %>" /></portlet:actionURL>");
	}

	function <portlet:namespace />selectFolder(folderId, folderName) {
		submitForm(document.hrefFm, "<portlet:actionURL><portlet:param name="struts_action" value="/document_library/edit_file_entry" /><portlet:param name="<%= Constants.CMD %>" value="<%= Constants.MOVE %>" /><portlet:param name="redirect" value="<%= redirect %>" /><portlet:param name="folderId" value="<%= String.valueOf(folderId) %>" /><portlet:param name="name" value="<%= HtmlUtil.unescape(fileEntry.getName()) %>" /></portlet:actionURL>&<portlet:namespace />newFolderId=" + folderId);
	}

	function <portlet:namespace />unlock() {
		submitForm(document.hrefFm, "<portlet:actionURL><portlet:param name="struts_action" value="/document_library/edit_file_entry" /><portlet:param name="<%= Constants.CMD %>" value="<%= Constants.UNLOCK %>" /><portlet:param name="redirect" value="<%= currentURL %>" /><portlet:param name="folderId" value="<%= String.valueOf(folderId) %>" /><portlet:param name="name" value="<%= name %>" /></portlet:actionURL>");
	}

	Liferay.provide(
		window,
		'<portlet:namespace />compare',
		function() {
			var A = AUI();

			var rowIds = A.all('input[name=<portlet:namespace />rowIds]:checked');
			var sourceVersion = A.one('input[name="<portlet:namespace />sourceVersion"]');
			var targetVersion = A.one('input[name="<portlet:namespace />targetVersion"]');

			var rowIdsSize = rowIds.size();

			if (rowIdsSize == 1) {
				if (sourceVersion) {
					sourceVersion.val(rowIds.item(0).val());
				}
			}
			else if (rowIdsSize == 2) {
				if (sourceVersion) {
					sourceVersion.val(rowIds.item(1).val());
				}

				if (targetVersion) {
					targetVersion.val(rowIds.item(0).val());
				}
			}

			submitForm(document.<portlet:namespace />fm1);
		},
		['selector-css3']
	);

	Liferay.provide(
		window,
		'<portlet:namespace />initRowsChecked',
		function() {
			var A = AUI();

			var rowIds = A.all('input[name=<portlet:namespace />rowIds]');

			rowIds.each(
				function(item, index, collection) {
					if (index >= 2) {
						item.set('checked', false);
					}
				}
			);
		},
		['aui-base']
	);

	Liferay.provide(
		window,
		'<portlet:namespace />updateRowsChecked',
		function(element) {
			var A = AUI();

			var rowsChecked = A.all('input[name=<portlet:namespace />rowIds]:checked');

			if (rowsChecked.size() > 2) {
				var index = 2;

				if (rowsChecked.item(2).compareTo(element)) {
					index = 1;
				}

				rowsChecked.item(index).set('checked', false);
			}
		},
		['selector-css3']
	);
</aui:script>

<aui:script use="aui-base">
	<portlet:namespace />initRowsChecked();

	A.all('input[name=<portlet:namespace />rowIds]').on(
		'click',
		function(event) {
			<portlet:namespace />updateRowsChecked(event.currentTarget);
		}
	);
</aui:script>

<aui:script use="aui-dialog">
	Liferay.namespace('SO');

	Liferay.SO.DocumentLibrary = {
		closePopup: function() {
			var instance = this;

			var popup = instance._getPopup();

			popup.hide();
		},

		displayPopup: function(url, title) {
			var instance = this;

			var popup = instance._getPopup();

			popup.show();

			popup.set('title', title);

			popup.io.set('uri', url);
			popup.io.start();
		},

		_getPopup: function() {
			var instance = this;

			if (!instance._popup) {
				instance._popup = new A.Dialog(
					{
						cssClass: 'document-library-dialog',
						resizable: false,
						width: 600,
						xy: [15,15]
					}
				).plug(
					A.Plugin.IO,
					{autoLoad: false}
				).render();
			}

			return instance._popup;
		}
	}

	A.all('.portlet-document-library .result-data .comments').on(
		'click',
		function(event) {
			event = event.currentTarget;

			var container = event.ancestor('.result-wrapper');
			var comments = container.one('.result-comments');

			if (comments.hasClass('aui-helper-hidden')) {
				comments.show();
				event.text(Liferay.Language.get('hide-comments'));
			}
			else {
				comments.hide();
				event.text(Liferay.Language.get('show-comments'));
			}
		}
	);
</aui:script>

<%
DLUtil.addPortletBreadcrumbEntries(fileEntry, request, renderResponse);
%>