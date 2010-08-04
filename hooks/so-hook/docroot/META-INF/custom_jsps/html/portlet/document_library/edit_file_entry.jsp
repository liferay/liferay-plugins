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
String strutsAction = ParamUtil.getString(request, "struts_action");

String displaySection = ParamUtil.getString(request, "display_section", StringPool.BLANK);

String tabs2 = ParamUtil.getString(request, "tabs2", "version-history");

String redirect = ParamUtil.getString(request, "redirect");

String referringPortletResource = ParamUtil.getString(request, "referringPortletResource");

String uploadProgressId = "dlFileEntryUploadProgress";

DLFileEntry fileEntry = (DLFileEntry)request.getAttribute(WebKeys.DOCUMENT_LIBRARY_FILE_ENTRY);

long fileEntryId = BeanParamUtil.getLong(fileEntry, request, "fileEntryId");

long folderId = BeanParamUtil.getLong(fileEntry, request, "folderId");
String name = BeanParamUtil.getString(fileEntry, request, "name");
String title = BeanParamUtil.getString(fileEntry, request, "title");

String extension = StringPool.BLANK;

if (Validator.isNotNull(title)) {
	extension = FileUtil.getExtension(title);
}


String assetTagNames = ParamUtil.getString(request, "assetTagNames");

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
		lock = LockLocalServiceUtil.getLock(DLFileEntry.class.getName(), DLUtil.getLockId(fileEntry.getGroupId(), fileEntry.getFolderId(), fileEntry.getName()));

		isLocked = Boolean.TRUE;

		if (lock.getUserId() == user.getUserId()) {
			hasLock = Boolean.TRUE;
		}
	}
	catch (Exception e) {
	}
}

int status = WorkflowConstants.STATUS_APPROVED;

if (fileEntry == null) {
	if (WorkflowDefinitionLinkLocalServiceUtil.hasWorkflowDefinitionLink(company.getCompanyId(), scopeGroupId, DLFileEntry.class.getName())) {
		status = WorkflowConstants.STATUS_PENDING;
	}
}
else if (WorkflowInstanceLinkLocalServiceUtil.hasWorkflowInstanceLink(company.getCompanyId(), fileEntry.getGroupId(), DLFileEntry.class.getName(), fileEntry.getFileEntryId())) {
	status = WorkflowConstants.STATUS_PENDING;
}

UnicodeProperties extraSettingsProperties = new UnicodeProperties();

if (fileEntry != null) {
	extraSettingsProperties = fileEntry.getExtraSettingsProperties();
}

boolean hideEdit = false;

if (displaySection.equals("online") && (fileEntry != null)) {
	DLFileVersion fileVersion = DLFileVersionLocalServiceUtil.getLatestFileVersion(fileEntry.getGroupId(), fileEntry.getFolderId(), fileEntry.getName());

	if (fileVersion.getStatus() == WorkflowConstants.STATUS_APPROVED) {
		hideEdit = true;
	}
}

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("struts_action", strutsAction);
portletURL.setParameter("tabs2", tabs2);
portletURL.setParameter("redirect", redirect);
portletURL.setParameter("folderId", String.valueOf(folderId));
portletURL.setParameter("name", name);
%>

<c:if test="<%= !windowState.equals(LiferayWindowState.EXCLUSIVE) %>">
	<liferay-util:include page="/html/portlet/document_library/sidebar.jsp" />

	<div class="breadcrumbs">
		<%= getFolderBreadcrumbs(folderId, pageContext, renderResponse) %>

		<c:choose>
			<c:when test="<%= fileEntry != null %>">
				<h6><%= HtmlUtil.escape(fileEntry.getTitle()) %></h6>
			</c:when>
			<c:otherwise>
				<h6><liferay-ui:message key="add-file-entry" /></h6>
			</c:otherwise>
		</c:choose>
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
</c:if>

<c:choose>
	<c:when test="<%= hideEdit %>">
		<div class="portlet-msg-info no-document-changes">
			<liferay-ui:message key="there-are-no-changes-to-the-document" />
		</div>
	</c:when>
	<c:otherwise>
		<c:if test="<%= fileEntry == null %>">
			<div class="lfr-dynamic-uploader">
				<div class="lfr-upload-container" id="<portlet:namespace />fileUpload"></div>
			</div>

			<div class="lfr-fallback aui-helper-hidden" id="<portlet:namespace />fallback">

			<aui:script use="liferay-upload">
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
			</aui:script>
		</c:if>

		<portlet:actionURL var="editFileEntryURL">
			<portlet:param name="struts_action" value="/document_library/edit_file_entry" />
			<portlet:param name="uploader" value="classic" />
		</portlet:actionURL>

		<aui:form action="<%= editFileEntryURL %>" enctype="multipart/form-data" method="post" name="fm" onSubmit='<%= "event.preventDefault(); " + renderResponse.getNamespace() + "saveFileEntry(false);" %>'>
			<aui:input name="<%= Constants.CMD %>" type="hidden" />
			<aui:input name="redirect" type="hidden" value="<%= redirect %>" />
			<aui:input name="referringPortletResource" type="hidden" value="<%= referringPortletResource %>" />
			<aui:input name="uploadProgressId" type="hidden" value="<%= uploadProgressId %>" />
			<aui:input name="folderId" type="hidden" value="<%= folderId %>" />
			<aui:input name="name" type="hidden" value="<%= name %>" />

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

			<liferay-ui:asset-tags-error />

			<aui:model-context bean="<%= fileEntry %>" model="<%= DLFileEntry.class %>" />

			<aui:fieldset cssClass="<%= displaySection %>">
				<div class="section-upload">
					<aui:field-wrapper>

						<%
						long fileMaxSize = PrefsPropsUtil.getLong(PropsKeys.DL_FILE_MAX_SIZE) / 1024;
						%>

						<c:if test="<%= fileMaxSize != 0 %>">
							<div class="portlet-msg-info">
								<%= LanguageUtil.format(pageContext, "upload-documents-no-larger-than-x-k", String.valueOf(fileMaxSize), false) %>
							</div>
						</c:if>
					</aui:field-wrapper>

					<c:if test="<%= fileEntry != null %>">
						<aui:field-wrapper>
							<aui:input name="majorVersion" type="hidden" />

							<aui:input label="major-revision" name="version" type="radio" value="major" />
							<aui:input label="minor-revision" checked="true" name="version" type="radio" value="minor" />

							<c:if test='<%= displaySection.equals("online") %>'>
								<aui:input label="revert-revision" name="version" type="radio" value="revert" />
							</c:if>
						</aui:field-wrapper>
					</c:if>

					<aui:input name="file" type="file" />

					<c:if test="<%= fileEntry != null %>">
						<aui:input label="version-description" name="versionDescription" style="width: 350px;" type="textarea" />
					</c:if>
				</div>
				<div class="section-properties">
					<aui:input label="title" name="extraSettingsProperties(name)" style="width: 350px;" type="text" value='<%= GetterUtil.getString(extraSettingsProperties.getProperty("name")) %>' />

					<aui:input label="name" name="title" />

					<aui:input label="author" name="extraSettingsProperties(author)" style="width: 350px;" type="text" value='<%= GetterUtil.getString(extraSettingsProperties.getProperty("author")) %>' />

					<aui:input name="description" />

					<liferay-ui:custom-attributes-available className="<%= DLFileEntry.class.getName() %>">
						<liferay-ui:custom-attribute-list
							className="<%= DLFileEntry.class.getName() %>"
							classPK="<%= (fileEntry != null) ? fileEntry.getFileEntryId() : 0 %>"
							editable="<%= true %>"
							label="<%= true %>"
						/>
					</liferay-ui:custom-attributes-available>

					<aui:input name="tags" type="assetTags" />
				</div>

				<c:if test="<%= fileEntry == null %>">
					<aui:field-wrapper label="permissions">
						<liferay-ui:input-permissions
							modelName="<%= DLFileEntry.class.getName() %>"
						/>
					</aui:field-wrapper>
				</c:if>

				<aui:button-row>
					<c:choose>
						<c:when test="<%= windowState.equals(LiferayWindowState.EXCLUSIVE) %>">
							<aui:button onClick='<%= renderResponse.getNamespace() + "saveFileEntry();" %>' type="button" value="save" />

							<aui:button onClick="Liferay.SO.DocumentLibrary.closePopup();" type="cancel" />
						</c:when>
						<c:otherwise>
							<aui:button disabled="<%= isLocked && !hasLock %>" type="submit" value="save" />

							<aui:button onClick="<%= redirect %>" type="cancel" />
						</c:otherwise>
					</c:choose>
				</aui:button-row>
			</aui:fieldset>
		</aui:form>

		<liferay-ui:upload-progress
			id="<%= uploadProgressId %>"
			message="uploading"
			redirect="<%= redirect %>"
		/>

		<c:if test="<%= fileEntry == null %>">
			</div>
		</c:if>

		<aui:script>
			<c:choose>
				<c:when test='<%= displaySection.equals("online") || displaySection.equals("upload") %>'>
					function <portlet:namespace />saveFileEntry() {
						<%= HtmlUtil.escape(uploadProgressId) %>.startProgress();

						document.<portlet:namespace />fm.<portlet:namespace /><%= Constants.CMD %>.value = "<%= Constants.UPDATE %>";
						document.<portlet:namespace />fm.<portlet:namespace />majorVersion.value = document.<portlet:namespace />fm.<portlet:namespace />version[0].checked;

						<c:if test='<%= displaySection.equals("online") %>'>
							if (document.<portlet:namespace />fm.<portlet:namespace />version[2].checked) {
								document.<portlet:namespace />fm.<portlet:namespace /><%= Constants.CMD %>.value = "<%= Constants.REVERT %>";
							}
						</c:if>

						AUI().use(
							'aui-io-request',
							function(A) {
								var form = A.one(document.<portlet:namespace />fm);

								A.io.request(
									form.getAttribute('action'),
									{
										form: {
											id: form.get('id'),
											upload: true
										},
										on: {
											complete: function(event) {
												submitForm(document.hrefFm, '<portlet:actionURL><portlet:param name="struts_action" value="/document_library/edit_file_entry" /><portlet:param name="<%= Constants.CMD %>" value="<%= Constants.UNLOCK %>" /><portlet:param name="redirect" value="<%= redirect %>" /><portlet:param name="folderId" value="<%= String.valueOf(folderId) %>" /><portlet:param name="name" value="<%= name %>" /></portlet:actionURL>');
											}
										}
									}
								);
							}
						);
					}
				</c:when>
				<c:otherwise>
					function <portlet:namespace />saveFileEntry() {
						<%= HtmlUtil.escape(uploadProgressId) %>.startProgress();

						document.<portlet:namespace />fm.<portlet:namespace /><%= Constants.CMD %>.value = "<%= (fileEntry == null) ? Constants.ADD : Constants.UPDATE %>";

						<c:if test="<%= fileEntry != null %>">
							document.<portlet:namespace />fm.<portlet:namespace />majorVersion.value = false;
						</c:if>

						submitForm(document.<portlet:namespace />fm);
					}
				</c:otherwise>
			</c:choose>

			<c:if test="<%= windowState.equals(WindowState.MAXIMIZED) || windowState.equals(LiferayWindowState.EXCLUSIVE) %>">
				Liferay.Util.focusFormField(document.<portlet:namespace />fm.<portlet:namespace />title);
			</c:if>
		</aui:script>

		<aui:script use="event,node">
			var validateFile = function(fileField) {
				var value = fileField.val();

				if (value) {
					var extension = value.substring(value.lastIndexOf('.')).toLowerCase();
					var validExtensions = ['<%= StringUtil.merge(PrefsPropsUtil.getStringArray(PropsKeys.DL_FILE_EXTENSIONS, StringPool.COMMA), "', '") %>'];

					if ((A.Array.indexOf(validExtensions, '*') == -1) &&
						(A.Array.indexOf(validExtensions, extension) == -1)) {

						alert('<%= UnicodeLanguageUtil.get(pageContext, "document-names-must-end-with-one-of-the-following-extensions") %> <%= StringUtil.merge(PrefsPropsUtil.getStringArray(PropsKeys.DL_FILE_EXTENSIONS, StringPool.COMMA), StringPool.COMMA_AND_SPACE) %>');

						fileField.val('');
					}
				}
			};

			var onFileChange = function(event) {
				validateFile(event.currentTarget);
			};

			var fileField = A.one('#<portlet:namespace />file')

			if (fileField) {
				fileField.on('change', onFileChange);

				validateFile(fileField);
			}
		</aui:script>

		<%
		if (fileEntry != null) {
			DLUtil.addPortletBreadcrumbEntries(fileEntry, request, renderResponse);

			PortalUtil.addPortletBreadcrumbEntry(request, LanguageUtil.get(pageContext, "edit"), currentURL);
		}
		else {
			DLUtil.addPortletBreadcrumbEntries(folderId, request, renderResponse);

			PortalUtil.addPortletBreadcrumbEntry(request, LanguageUtil.get(pageContext, "add-file-entry"), currentURL);
		}
		%>
	</c:otherwise>
</c:choose>