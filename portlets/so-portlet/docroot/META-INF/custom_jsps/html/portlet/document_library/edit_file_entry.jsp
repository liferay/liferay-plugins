<%
/**
 * Copyright (c) 2008-2009 Liferay, Inc. All rights reserved.
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
String strutsAction = ParamUtil.getString(request, "struts_action");

String tabs2 = ParamUtil.getString(request, "tabs2", "version-history");

String redirect = ParamUtil.getString(request, "redirect");

String referringPortletResource = ParamUtil.getString(request, "referringPortletResource");

String uploadProgressId = "dlFileEntryUploadProgress";

DLFileEntry fileEntry = (DLFileEntry)request.getAttribute(WebKeys.DOCUMENT_LIBRARY_FILE_ENTRY);

long folderId = BeanParamUtil.getLong(fileEntry, request, "folderId");
String name = BeanParamUtil.getString(fileEntry, request, "name");

String extension = StringPool.BLANK;

if (Validator.isNotNull(name)) {
	extension = FileUtil.getExtension(name);
}

String titleWithExtension = BeanParamUtil.getString(fileEntry, request, "titleWithExtension");

String tagsEntries = ParamUtil.getString(request, "tagsEntries");

String[] conversions = new String[0];

if (PrefsPropsUtil.getBoolean(PropsKeys.OPENOFFICE_SERVER_ENABLED, PropsValues.OPENOFFICE_SERVER_ENABLED)) {
	conversions = (String[])DocumentConversionUtil.getConversions(extension);
}

DLFolder folder = null;

Lock lock = null;
Boolean isLocked = Boolean.FALSE;
Boolean hasLock = Boolean.FALSE;

if (fileEntry != null) {
	folder = fileEntry.getFolder();

	try {
		lock = LockLocalServiceUtil.getLock(DLFileEntry.class.getName(), DLUtil.getLockId(fileEntry.getFolderId(), fileEntry.getName()));

		isLocked = Boolean.TRUE;

		if (lock.getUserId() == user.getUserId()) {
			hasLock = Boolean.TRUE;
		}
	}
	catch (Exception e) {
	}
}

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setWindowState(WindowState.NORMAL);

portletURL.setParameter("struts_action", strutsAction);
portletURL.setParameter("tabs2", tabs2);
portletURL.setParameter("redirect", redirect);
portletURL.setParameter("folderId", String.valueOf(folderId));
portletURL.setParameter("name", name);
%>

<script type="text/javascript">
	function <portlet:namespace />compare() {
		var rowIds = jQuery('input[name=<portlet:namespace />rowIds]:checked');
		var sourceVersion = jQuery('input[name="<portlet:namespace />sourceVersion"]');
		var targetVersion = jQuery('input[name="<portlet:namespace />targetVersion"]');

		if (rowIds.length == 1) {
			sourceVersion.val(rowIds[0].value);
		}
		else if (rowIds.length == 2) {
			sourceVersion.val(rowIds[1].value);
			targetVersion.val(rowIds[0].value);
		}

		submitForm(document.<portlet:namespace />fm1);
	}

	function <portlet:namespace />initRowsChecked() {
		var rowIds = jQuery('input[name=<portlet:namespace />rowIds]');

		var found = 0;

		for (i = 0; i < rowIds.length; i++) {
			if (rowIds[i].checked && (found < 2)) {
				found++;
			}
			else {
				rowIds[i].checked = false;
			}
		}
	}

	function <portlet:namespace />lock() {
		submitForm(document.hrefFm, "<portlet:actionURL windowState="<%= WindowState.NORMAL.toString() %>"><portlet:param name="struts_action" value="/document_library/edit_file_entry" /><portlet:param name="<%= Constants.CMD %>" value="<%= Constants.LOCK %>" /><portlet:param name="redirect" value="<%= redirect %>" /><portlet:param name="folderId" value="<%= String.valueOf(folderId) %>" /><portlet:param name="name" value="<%= name %>" /></portlet:actionURL>");
	}

	function <portlet:namespace />saveFileEntry() {
		<%= HtmlUtil.escape(uploadProgressId) %>.startProgress();

		document.<portlet:namespace />fm.<portlet:namespace /><%= Constants.CMD %>.value = "<%= fileEntry == null ? Constants.ADD : Constants.UPDATE %>";
		submitForm(document.<portlet:namespace />fm);
	}

	function <portlet:namespace />selectFolder(folderId, folderName) {
		if (document.<portlet:namespace />fm.<portlet:namespace />folderId.value <= 0) {
			document.<portlet:namespace />fm.<portlet:namespace />folderId.value = folderId;
		}

		document.<portlet:namespace />fm.<portlet:namespace />newFolderId.value = folderId;

		var nameEl = document.getElementById("<portlet:namespace />folderName");

		nameEl.href = "javascript:location = '<portlet:renderURL windowState="<%= WindowState.NORMAL.toString() %>"><portlet:param name="struts_action" value="/document_library/view" /></portlet:renderURL>&<portlet:namespace />folderId=" + folderId + "'; void('');";
		nameEl.innerHTML = folderName + "&nbsp;";
	}

	function <portlet:namespace />unlock() {
		submitForm(document.hrefFm, "<portlet:actionURL windowState="<%= WindowState.NORMAL.toString() %>"><portlet:param name="struts_action" value="/document_library/edit_file_entry" /><portlet:param name="<%= Constants.CMD %>" value="<%= Constants.UNLOCK %>" /><portlet:param name="redirect" value="<%= redirect %>" /><portlet:param name="folderId" value="<%= String.valueOf(folderId) %>" /><portlet:param name="name" value="<%= name %>" /></portlet:actionURL>");
	}

	function <portlet:namespace />updateRowsChecked(element) {
		var rowsChecked = jQuery('input[name=<portlet:namespace />rowIds]:checked');

		if (rowsChecked.length > 2) {
			if (rowsChecked[2] == element) {
				rowsChecked[1].checked = false;
			}
			else {
				rowsChecked[2].checked = false;
			}
		}
	}

	jQuery(
		function() {
			<portlet:namespace />initRowsChecked();

			jQuery('input[name=<portlet:namespace />rowIds]').click(
				function() {
					<portlet:namespace />updateRowsChecked(this);
				}
			);
		}
	);
</script>

<liferay-util:include page="/html/portlet/document_library/sidebar.jsp" />

<c:if test="<%= isLocked.booleanValue() %>">
	<c:choose>
		<c:when test="<%= hasLock.booleanValue() %>">

			<%
			String lockExpirationTime = LanguageUtil.getTimeDescription(pageContext, DLFileEntryImpl.LOCK_EXPIRATION_TIME).toLowerCase();
			%>

			<span class="portlet-msg-success">
				<%= LanguageUtil.format(pageContext, "you-now-have-a-lock-on-this-document", lockExpirationTime, false) %>
			</span>
		</c:when>
		<c:otherwise>
			<span class="portlet-msg-error">
				<%= LanguageUtil.format(pageContext, "you-cannot-modify-this-document-because-it-was-locked-by-x-on-x", new Object[] {PortalUtil.getUserName(lock.getUserId(), String.valueOf(lock.getUserId())), dateFormatDateTime.format(lock.getCreateDate())}, false) %>
			</span>
		</c:otherwise>
	</c:choose>
</c:if>

<div class="breadcrumbs">
	<%= BreadcrumbsUtil.removeLastClass(DLUtil.getBreadcrumbs(folderId, null, rootFolderId, pageContext, renderRequest, renderResponse)) %> &raquo;

	<span class="last"><liferay-ui:message key="view-file-entry" /></span>
</div>

<c:if test="<%= fileEntry != null %>">
	<table class="lfr-table">
	<tr>
		<td class="lfr-label">
			<liferay-ui:message key="name" />
		</td>
		<td>
			<a href="<%= themeDisplay.getPathMain() %>/document_library/get_file?p_l_id=<%= themeDisplay.getPlid() %>&folderId=<%= folderId %>&name=<%= HttpUtil.encodeURL(name) %>">
			<%= HtmlUtil.escape(fileEntry.getTitleWithExtension()) %>
			</a>
		</td>
	</tr>
	<tr>
		<td class="lfr-label">
			<liferay-ui:message key="version" />
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
		<td class="lfr-label">
			<liferay-ui:message key="num-of-downloads" />
		</td>
		<td>
			<%= fileEntry.getReadCount() %>
		</td>
	</tr>
	<tr>
		<td colspan="2">
			<br />
		</td>
	</tr>
	<tr>
		<td class="lfr-label">
			<liferay-ui:message key="download" />
		</td>
		<td>
			<liferay-ui:icon
				image='<%= "../document_library/" + extension %>'
				message="<%= extension.toUpperCase() %>"
				url='<%= themeDisplay.getPathMain() + "/document_library/get_file?p_l_id=" + themeDisplay.getPlid() + "&folderId=" + folderId + "&name=" + HttpUtil.encodeURL(name) %>'
				label="<%= true %>"
			/>
		</td>
	</tr>

	<c:if test="<%= conversions.length > 0 %>">
		<tr>
			<td class="lfr-label">
				<liferay-ui:message key="convert-to" />
			</td>
			<td>
				<table class="lfr-table">
				<tr>

				<%
				for (int i = 0; i < conversions.length; i++) {
					String conversion = conversions[i];

				%>

					<td>
						<liferay-ui:icon
							image='<%= "../document_library/" + conversion %>'
							message="<%= conversion.toUpperCase() %>"
							url='<%= themeDisplay.getPathMain() + "/document_library/get_file?p_l_id=" + themeDisplay.getPlid() + "&folderId=" + folderId + "&name=" + HttpUtil.encodeURL(name) + "&targetExtension=" + conversion %>'
							label="<%= true %>"
						/>
					</td>

				<%
				}
				%>

				</tr>
				</table>
			</td>
		</tr>
	</c:if>

	<tr>
		<td colspan="2">
			<br />
		</td>
	</tr>
	<tr>
		<td class="lfr-label">
			<liferay-ui:message key="url" />
		</td>
		<td>
			<liferay-ui:input-resource
				url='<%= themeDisplay.getPortalURL() + themeDisplay.getPathMain() + "/document_library/get_file?uuid=" + fileEntry.getUuid() + "&groupId=" + folder.getGroupId() %>'
			/>
		</td>
	</tr>

	<c:if test="<%= portletDisplay.isWebDAVEnabled() %>">
		<tr>
			<td class="lfr-label">
				<liferay-ui:message key="webdav-url" />
			</td>
			<td>

				<%
				StringBuilder sb = new StringBuilder();

				DLFolder curFolder = DLFolderLocalServiceUtil.getFolder(folderId);

				while (true) {
					sb.insert(0, WebDAVUtil.encodeURL(curFolder.getName()));
					sb.insert(0, StringPool.SLASH);

					if (curFolder.getParentFolderId() == DLFolderConstants.DEFAULT_PARENT_FOLDER_ID) {
						break;
					}
					else {
						curFolder = DLFolderLocalServiceUtil.getFolder(curFolder.getParentFolderId());
					}
				}

				sb.append(StringPool.SLASH);
				sb.append(WebDAVUtil.encodeURL(titleWithExtension));

				Group group = themeDisplay.getScopeGroup();
				%>

				<liferay-ui:input-resource
					url='<%= themeDisplay.getPortalURL() + "/tunnel-web/secure/webdav/" + company.getWebId() + group.getFriendlyURL() + "/document_library" + sb.toString() %>'
				/>
			</td>
		</tr>
	</c:if>

	</table>
</c:if>

<c:if test='<%= strutsAction.equals("/document_library/edit_file_entry") && ((fileEntry == null) || DLFileEntryPermission.contains(permissionChecker, fileEntry, ActionKeys.UPDATE)) %>'>
	<c:if test="<%= fileEntry != null %>">
		<br />
	</c:if>

	<c:if test="<%= fileEntry == null %>">
		<script type="text/javascript">
			jQuery(
				function() {
					new Liferay.Upload(
						{
							allowedFileTypes: '<%= StringUtil.merge(PrefsPropsUtil.getStringArray(PropsKeys.DL_FILE_EXTENSIONS, StringPool.COMMA)) %>',
							container: '#<portlet:namespace />fileUpload',
							fileDescription: '<%= StringUtil.merge(PrefsPropsUtil.getStringArray(PropsKeys.DL_FILE_EXTENSIONS, StringPool.COMMA)) %>',
							fallbackContainer: '#<portlet:namespace />fallback',
							maxFileSize: <%= PrefsPropsUtil.getLong(PropsKeys.DL_FILE_MAX_SIZE) %> / 1024,
							namespace: '<portlet:namespace />',
							uploadFile: '<liferay-portlet:actionURL windowState="<%= LiferayWindowState.POP_UP.toString() %>" doAsUserId="<%= user.getUserId() %>"><portlet:param name="struts_action" value="/document_library/edit_file_entry" /><portlet:param name="<%= Constants.CMD %>" value="<%= Constants.ADD %>" /><portlet:param name="folderId" value="<%= String.valueOf(folderId) %>" /></liferay-portlet:actionURL><liferay-ui:input-permissions-params modelName="<%= DLFileEntry.class.getName() %>" />'
						}
					);
				}
			);
		</script>

		<div class="lfr-dynamic-uploader">
			<div class="lfr-upload-container" id="<portlet:namespace />fileUpload"></div>
		</div>

		<div class="lfr-fallback" id="<portlet:namespace />fallback">
	</c:if>

	<form action="<portlet:actionURL><portlet:param name="struts_action" value="/document_library/edit_file_entry" /></portlet:actionURL>" enctype="multipart/form-data" method="post" name="<portlet:namespace />fm" onSubmit="<portlet:namespace />saveFileEntry(); return false;">
	<input name="<portlet:namespace /><%= Constants.CMD %>" type="hidden" value="" />
	<input name="<portlet:namespace />redirect" type="hidden" value="<%= HtmlUtil.escapeAttribute(redirect) %>" />
	<input name="<portlet:namespace />referringPortletResource" type="hidden" value="<%= HtmlUtil.escapeAttribute(referringPortletResource) %>" />
	<input name="<portlet:namespace />uploadProgressId" type="hidden" value="<%= HtmlUtil.escapeAttribute(uploadProgressId) %>" />
	<input name="<portlet:namespace />folderId" type="hidden" value="<%= folderId %>" />
	<input name="<portlet:namespace />newFolderId" type="hidden" value="" />
	<input name="<portlet:namespace />name" type="hidden" value="<%= HtmlUtil.escapeAttribute(name) %>" />

	<liferay-ui:error exception="<%= DuplicateFileException.class %>" message="please-enter-a-unique-document-name" />
	<liferay-ui:error exception="<%= DuplicateFolderNameException.class %>" message="please-enter-a-unique-document-name" />

	<liferay-ui:error exception="<%= FileNameException.class %>">
		<liferay-ui:message key="document-names-must-end-with-one-of-the-following-extensions" /> <%= StringUtil.merge(PrefsPropsUtil.getStringArray(PropsKeys.DL_FILE_EXTENSIONS, StringPool.COMMA), StringPool.COMMA_AND_SPACE) %>.
	</liferay-ui:error>

	<liferay-ui:error exception="<%= NoSuchFolderException.class %>" message="please-enter-a-valid-folder" />

	<liferay-ui:error exception="<%= SourceFileNameException.class %>">
		<liferay-ui:message key="document-extensions-does-not-match" />
	</liferay-ui:error>

	<liferay-ui:error exception="<%= FileSizeException.class %>" message="please-enter-a-file-with-a-valid-file-size" />

	<liferay-ui:tags-error />

	<%
	String fileMaxSize = String.valueOf(PrefsPropsUtil.getLong(PropsKeys.DL_FILE_MAX_SIZE) / 1024);
	%>

	<c:if test='<%= !fileMaxSize.equals("0") %>'>
		<%= LanguageUtil.format(pageContext, "upload-documents-no-larger-than-x-k", fileMaxSize, false) %>

		<br /><br />
	</c:if>

	<table class="lfr-table">

	<c:if test="<%= ((fileEntry != null) || (folderId <= 0)) %>">
		<tr>
			<td class="lfr-label">
				<liferay-ui:message key="folder" />
			</td>
			<td>

				<%
				String folderName = StringPool.BLANK;

				if (folderId > 0) {
					folder = DLFolderLocalServiceUtil.getFolder(folderId);

					folder = folder.toEscapedModel();

					folderId = folder.getFolderId();
					folderName = folder.getName();
				}

				%>

				<a href="javascript:window.location = '<portlet:renderURL windowState="<%= WindowState.NORMAL.toString() %>"><portlet:param name="struts_action" value="/document_library/view" /><portlet:param name="folderId" value="<%= String.valueOf(folderId) %>" /></portlet:renderURL>'; void('');" id="<portlet:namespace />folderName">
				<%= folderName %></a>

				<input type="button" value="<liferay-ui:message key="select" />" onClick="var folderWindow = window.open('<portlet:renderURL windowState="<%= LiferayWindowState.POP_UP.toString() %>"><portlet:param name="struts_action" value="/document_library/select_folder" /><portlet:param name="folderId" value="<%= String.valueOf(folderId) %>" /></portlet:renderURL>', 'folder', 'directories=no,height=640,location=no,menubar=no,resizable=yes,scrollbars=yes,status=no,toolbar=no,width=680'); void(''); folderWindow.focus();" />
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<br />
			</td>
		</tr>
	</c:if>

	<tr>
		<td class="lfr-label">
			<liferay-ui:message key="file" />
		</td>
		<td>
			<input class="lfr-input-text" id="<portlet:namespace />file" name="<portlet:namespace />file" type="file" />
		</td>
	</tr>
	<tr>
		<td class="lfr-label">
			<liferay-ui:message key="title" />
		</td>
		<td>
			<liferay-ui:input-field model="<%= DLFileEntry.class %>" bean="<%= fileEntry %>" field="title" /><%= extension %>
		</td>
	</tr>
	<tr>
		<td class="lfr-label">
			<liferay-ui:message key="description" />
		</td>
		<td>
			<liferay-ui:input-field model="<%= DLFileEntry.class %>" bean="<%= fileEntry %>" field="description" />
		</td>
	</tr>
	<tr>
		<td colspan="2">
			<br />
		</td>
	</tr>
	<tr>
		<td class="lfr-label">
			<liferay-ui:message key="tags" />
		</td>
		<td>

			<%
			long classPK = 0;

			if (fileEntry != null) {
				classPK = fileEntry.getFileEntryId();
			}
			%>

			<liferay-ui:tags-selector
				className="<%= DLFileEntry.class.getName() %>"
				classPK="<%= classPK %>"
				hiddenInput="tagsEntries"
			/>
		</td>
	</tr>

	<%
	if (fileEntry == null) {
		request.setAttribute(WebKeys.DOCUMENT_LIBRARY_FILE_ENTRY, new DLFileEntryImpl());
	}
	%>

	<%@ include file="/html/portlet/document_library/edit_file_entry_form_extra_fields.jsp" %>

	<%
	if (fileEntry == null) {
		request.removeAttribute(WebKeys.DOCUMENT_LIBRARY_FILE_ENTRY);
	}
	%>

	<c:if test="<%= fileEntry == null %>">
		<tr>
			<td colspan="2">
				<br />
			</td>
		</tr>
		<tr>
			<td class="lfr-label">
				<liferay-ui:message key="permissions" />
			</td>
			<td>
				<liferay-ui:input-permissions
					modelName="<%= DLFileEntry.class.getName() %>"
				/>
			</td>
		</tr>
	</c:if>

	</table>

	<br />

	<c:if test='<%= strutsAction.equals("/document_library/view_file_entry") && DLFileEntryPermission.contains(permissionChecker, fileEntry, ActionKeys.UPDATE) %>'>
		<br />

		<portlet:renderURL windowState="<%= WindowState.NORMAL.toString() %>" var="editURL">
			<portlet:param name="struts_action" value="/document_library/edit_file_entry" />
			<portlet:param name="redirect" value="<%= redirect %>" />
			<portlet:param name="folderId" value="<%= String.valueOf(fileEntry.getFolderId()) %>" />
			<portlet:param name="name" value="<%= HtmlUtil.unescape(fileEntry.getName()) %>" />
		</portlet:renderURL>

		<input type="button" value="<liferay-ui:message key="edit" />" onClick="location = '<%= editURL %>';" />
	</c:if>

	<c:if test="<%= !(isLocked.booleanValue() && !hasLock.booleanValue()) %>">
		<input type="submit" value="<liferay-ui:message key="save" />">
	</c:if>

	<c:if test="<%= (fileEntry != null) && ((isLocked.booleanValue() && hasLock.booleanValue()) || !isLocked.booleanValue()) %>">
		<c:choose>
			<c:when test="<%= !hasLock.booleanValue() %>">
				<input type="button" value="<liferay-ui:message key="lock" />" onClick="<portlet:namespace />lock();" />
			</c:when>
			<c:otherwise>
				<input type="button" value="<liferay-ui:message key="unlock" />" onClick="<portlet:namespace />unlock();" />
			</c:otherwise>
		</c:choose>
	</c:if>

	<input type="button" value="<liferay-ui:message key="cancel" />" onClick="location = '<%= HtmlUtil.escape(PortalUtil.escapeRedirect(redirect)) %>';" />

	</form>

	<c:if test="<%= fileEntry == null %>">
		<br />
	</c:if>

	<script type="text/javascript">
		<c:if test="<%= windowState.equals(WindowState.NORMAL) %>">
			Liferay.Util.focusFormField(document.<portlet:namespace />fm.<portlet:namespace />file);
		</c:if>

		jQuery(
			function() {
				jQuery("#<portlet:namespace />file").change(
					function() {
						var value = jQuery(this).val();

						if ((value != null) && (value != "")) {
							var extension = value.substring(value.lastIndexOf(".")).toLowerCase();

							var validExtensions = new Array('<%= StringUtil.merge(PrefsPropsUtil.getStringArray(PropsKeys.DL_FILE_EXTENSIONS, StringPool.COMMA), "', '") %>');

							if ((jQuery.inArray("*", validExtensions) == -1) && (jQuery.inArray(extension, validExtensions) == -1)) {
								alert('<%= UnicodeLanguageUtil.get(pageContext, "document-names-must-end-with-one-of-the-following-extensions") %> <%= StringUtil.merge(PrefsPropsUtil.getStringArray(PropsKeys.DL_FILE_EXTENSIONS, StringPool.COMMA), StringPool.COMMA_AND_SPACE) %>');

								jQuery(this).val("");
							}
						}
					}
				).change();
			}
		);
	</script>

	<liferay-ui:upload-progress
		id="<%= uploadProgressId %>"
		message="uploading"
		redirect="<%= PortalUtil.escapeRedirect(redirect) %>"
	/>

	<c:if test="<%= fileEntry == null %>">
		</div>
	</c:if>
</c:if>

<c:if test="<%= fileEntry != null %>">
	<br />

	<liferay-ui:ratings
		className="<%= DLFileEntry.class.getName() %>"
		classPK="<%= fileEntry.getFileEntryId() %>"
	/>

	<br />

	<%
	String tabs2Names = "version-history,comments";

	if (!PropsValues.DL_FILE_ENTRY_COMMENTS_ENABLED || !DLFileEntryPermission.contains(permissionChecker, fileEntry, ActionKeys.ADD_DISCUSSION)) {
		tabs2Names = "version-history";
	}
	%>

	<liferay-ui:tabs
		names="<%= tabs2Names %>"
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

			List results = DLFileVersionLocalServiceUtil.getFileVersions(folderId, name);
			List resultRows = searchContainer.getResultRows();

			for (int i = 0; i < results.size(); i++) {
				DLFileVersion fileVersion = (DLFileVersion)results.get(i);

				ResultRow row = new ResultRow(new Object[] {fileEntry, fileVersion, conversions, portletURL, isLocked, hasLock}, String.valueOf(fileVersion.getVersion()), i);

				// File Version

				row.addJSP("/html/portlet/document_library/file_version_info.jsp");

				resultRows.add(row);
			}

			if (comparableFileEntry && (results.size() > 0)) {
				DLFileVersion fileVersion = (DLFileVersion)results.get(0);
			%>

				<form action="<portlet:renderURL windowState="<%= WindowState.NORMAL.toString() %>"><portlet:param name="struts_action" value="/document_library/compare_versions" /></portlet:renderURL>" method="post" name="<portlet:namespace />fm1" onSubmit="<portlet:namespace />compare(); return false;">
				<input name="<portlet:namespace />backURL" type="hidden" value="<%= HtmlUtil.escapeAttribute(currentURL) %>" />
				<input name="<portlet:namespace />fileEntryId" type="hidden" value="<%= fileEntry.getFileEntryId() %>" />
				<input name="<portlet:namespace />folderId" type="hidden" value="<%= folderId %>" />
				<input name="<portlet:namespace />name" type="hidden" value="<%= HtmlUtil.escapeAttribute(name) %>" />
				<input name="<portlet:namespace />titleWithExtension" type="hidden" value="<%= HtmlUtil.escapeAttribute(titleWithExtension) %>" />
				<input name="<portlet:namespace />sourceVersion" type="hidden" value="<%= fileVersion.getVersion() %>" />
				<input name="<portlet:namespace />targetVersion" type="hidden" value="<%= fileEntry.getVersion() %>" />

				<input type="submit" value="<liferay-ui:message key="compare-versions" />" />

				</form>

				<br />

			<%
			}
			%>

			<liferay-ui:search-iterator searchContainer="<%= searchContainer %>" paginate="<%= false %>" />
		</c:when>
		<c:when test='<%= tabs2.equals("comments") %>'>
			<c:if test="<%= DLFileEntryPermission.contains(permissionChecker, fileEntry, ActionKeys.ADD_DISCUSSION) %>">
				<portlet:actionURL var="discussionURL">
					<portlet:param name="struts_action" value="/document_library/edit_file_entry_discussion" />
				</portlet:actionURL>

				<liferay-ui:discussion
					formName="fm2"
					formAction="<%= discussionURL %>"
					className="<%= DLFileEntry.class.getName() %>"
					classPK="<%= fileEntry.getFileEntryId() %>"
					userId="<%= fileEntry.getUserId() %>"
					subject="<%= fileEntry.getTitle() %>"
					redirect="<%= currentURL %>"
					ratingsEnabled="<%= enableCommentRatings %>"
				/>
			</c:if>
		</c:when>
	</c:choose>
</c:if>