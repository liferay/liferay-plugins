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
String strutsAction = ParamUtil.getString(request, "struts_action");

String displaySection = ParamUtil.getString(request, "displaySection", StringPool.BLANK);

String cmd = ParamUtil.getString(request, Constants.CMD, Constants.EDIT);

String tabs2 = ParamUtil.getString(request, "tabs2", "version-history");

String redirect = ParamUtil.getString(request, "redirect");
String backURL = ParamUtil.getString(request, "backURL");

String referringPortletResource = ParamUtil.getString(request, "referringPortletResource");

String referringPortletResourceRootPortletId = PortletConstants.getRootPortletId(referringPortletResource);

String uploadProgressId = "dlFileEntryUploadProgress";

FileEntry fileEntry = (FileEntry)request.getAttribute(WebKeys.DOCUMENT_LIBRARY_FILE_ENTRY);

long fileEntryId = BeanParamUtil.getLong(fileEntry, request, "fileEntryId");

long repositoryId = BeanParamUtil.getLong(fileEntry, request, "repositoryId");

if (repositoryId <= 0) {

	// add_asset.jspf only passes in groupId

	repositoryId = BeanParamUtil.getLong(fileEntry, request, "groupId");
}

long folderId = BeanParamUtil.getLong(fileEntry, request, "folderId");
String extension = BeanParamUtil.getString(fileEntry, request, "extension");

Folder folder = null;

if (fileEntry != null) {
	folder = fileEntry.getFolder();
}

if ((folder == null) && (folderId != DLFolderConstants.DEFAULT_PARENT_FOLDER_ID)) {
	try {
		folder = DLAppServiceUtil.getFolder(folderId);
	}
	catch (NoSuchFolderException nsfe) {
		folderId = DLFolderConstants.DEFAULT_PARENT_FOLDER_ID;
	}
}

FileVersion fileVersion = null;

long fileVersionId = 0;

long fileEntryTypeId = ParamUtil.getLong(request, "fileEntryTypeId", -1);

if (fileEntry != null) {
	fileVersion = fileEntry.getLatestFileVersion();

	fileVersionId = fileVersion.getFileVersionId();

	if ((fileEntryTypeId == -1) && (fileVersion.getModel() instanceof DLFileVersion)) {
		DLFileVersion dlFileVersion = (DLFileVersion)fileVersion.getModel();

		fileEntryTypeId = dlFileVersion.getFileEntryTypeId();
	}
}

DLFileEntryType dlFileEntryType = null;

if (fileEntryTypeId > 0) {
	dlFileEntryType = DLFileEntryTypeLocalServiceUtil.getFileEntryType(fileEntryTypeId);
}

long assetClassPK = 0;

if ((fileVersion != null) && !fileVersion.isApproved() && Validator.isNotNull(fileVersion.getVersion()) && !fileVersion.getVersion().equals(DLFileEntryConstants.VERSION_DEFAULT)) {
	assetClassPK = fileVersion.getFileVersionId();
}
else if (fileEntry != null) {
	assetClassPK = fileEntry.getFileEntryId();
}

boolean checkedOut = false;
boolean hasLock = false;
Lock lock = null;

if (fileEntry != null) {
	checkedOut = fileEntry.isCheckedOut();
	hasLock = fileEntry.hasLock();
	lock = fileEntry.getLock();
}

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("struts_action", strutsAction);
portletURL.setParameter("tabs2", tabs2);
portletURL.setParameter("redirect", redirect);
portletURL.setParameter("fileEntryId", String.valueOf(fileEntryId));
%>

<c:if test="<%= Validator.isNull(referringPortletResource) %>">
	<liferay-util:include page="/html/portlet/document_library/top_links.jsp" />
</c:if>

<c:if test="<%= checkedOut %>">
	<c:choose>
		<c:when test="<%= hasLock %>">
			<div class="portlet-msg-success">
				<c:choose>
					<c:when test="<%= lock.isNeverExpires() %>">
						<liferay-ui:message key="you-now-have-an-indefinite-lock-on-this-document" />
					</c:when>
					<c:otherwise>

						<%
						String lockExpirationTime = LanguageUtil.getTimeDescription(pageContext, DLFileEntryConstants.LOCK_EXPIRATION_TIME).toLowerCase();
						%>

						<%= LanguageUtil.format(pageContext, "you-now-have-a-lock-on-this-document", lockExpirationTime, false) %>
					</c:otherwise>
				</c:choose>
			</div>
		</c:when>
		<c:otherwise>
			<div class="portlet-msg-error">
				<%= LanguageUtil.format(pageContext, "you-cannot-modify-this-document-because-it-was-checked-out-by-x-on-x", new Object[] {HtmlUtil.escape(PortalUtil.getUserName(lock.getUserId(), String.valueOf(lock.getUserId()))), dateFormatDateTime.format(lock.getCreateDate())}, false) %>
			</div>
		</c:otherwise>
	</c:choose>
</c:if>

<%
boolean localizeTitle = true;
String headerTitle = LanguageUtil.get(pageContext, "new-document");

if (fileVersion != null) {
	headerTitle = fileVersion.getTitle();
	localizeTitle= false;
}
else if (dlFileEntryType != null) {
	headerTitle = LanguageUtil.format(pageContext, "new-x", new Object[] {dlFileEntryType.getName()});
}
%>

<liferay-ui:header
	backURL="<%= backURL %>"
	localizeTitle="<%= localizeTitle %>"
	title="<%= headerTitle %>"
/>

<portlet:actionURL var="editFileEntryURL">
	<portlet:param name="struts_action" value="/document_library/edit_file_entry" />
	<portlet:param name="uploader" value="classic" />
</portlet:actionURL>

<aui:form action="<%= editFileEntryURL %>" cssClass="lfr-dynamic-form" enctype="multipart/form-data" method="post" name="dialogFm" onSubmit='<%= "event.preventDefault(); " + renderResponse.getNamespace() + "saveFileEntry(false);" %>'>
	<aui:input name="<%= Constants.CMD %>" type="hidden" />
	<aui:input name="redirect" type="hidden" value="<%= redirect %>" />
	<aui:input name="backURL" type="hidden" value="<%= backURL %>" />
	<aui:input name="referringPortletResource" type="hidden" value="<%= referringPortletResource %>" />
	<aui:input name="uploadProgressId" type="hidden" value="<%= uploadProgressId %>" />
	<aui:input name="repositoryId" type="hidden" value="<%= repositoryId %>" />
	<aui:input name="folderId" type="hidden" value="<%= folderId %>" />
	<aui:input name="fileEntryId" type="hidden" value="<%= fileEntryId %>" />
	<aui:input name="workflowAction" type="hidden" value="<%= WorkflowConstants.ACTION_PUBLISH %>" />

	<liferay-ui:error exception="<%= DuplicateFileException.class %>" message="please-enter-a-unique-document-name" />
	<liferay-ui:error exception="<%= DuplicateFolderNameException.class %>" message="please-enter-a-unique-document-name" />

	<liferay-ui:error exception="<%= FileExtensionException.class %>">
		<liferay-ui:message key="document-names-must-end-with-one-of-the-following-extensions" /> <%= StringUtil.merge(PrefsPropsUtil.getStringArray(PropsKeys.DL_FILE_EXTENSIONS, StringPool.COMMA), StringPool.COMMA_AND_SPACE) %>.
	</liferay-ui:error>

	<liferay-ui:error exception="<%= FileMimeTypeException.class %>">
		<liferay-ui:message key="media-files-must-be-one-of-the-following-formats" /> <%= StringUtil.merge(DLUtil.getMediaGalleryMimeTypes(preferences, renderRequest), StringPool.COMMA_AND_SPACE) %>.
	</liferay-ui:error>

	<liferay-ui:error exception="<%= FileNameException.class %>" message="please-enter-a-file-with-a-valid-file-name" />
	<liferay-ui:error exception="<%= NoSuchFolderException.class %>" message="please-enter-a-valid-folder" />

	<liferay-ui:error exception="<%= SourceFileNameException.class %>">
		<liferay-ui:message key="the-source-file-does-not-have-the-same-extension-as-the-original-file" />
	</liferay-ui:error>

	<%
	long fileMaxSize = PrefsPropsUtil.getLong(PropsKeys.DL_FILE_MAX_SIZE);

	if (fileMaxSize == 0) {
		fileMaxSize = PrefsPropsUtil.getLong(PropsKeys.UPLOAD_SERVLET_REQUEST_IMPL_MAX_SIZE);
	}

	fileMaxSize /= 1024;
	%>

	<liferay-ui:error exception="<%= FileSizeException.class %>">
		<liferay-ui:message arguments="<%= fileMaxSize %>" key="please-enter-a-file-with-a-valid-file-size-no-larger-than-x" />
	</liferay-ui:error>

	<liferay-ui:asset-categories-error />

	<liferay-ui:asset-tags-error />

	<aui:model-context bean="<%= fileVersion %>" model="<%= DLFileVersion.class %>" />

	<c:if test="<%= fileVersion != null %>">
		<aui:workflow-status model="<%= DLFileEntry.class %>" status="<%= fileVersion.getStatus() %>" version="<%= fileVersion.getVersion() %>" />
	</c:if>

	<aui:fieldset>
		<aui:field-wrapper cssClass='<%= (displaySection.equals(StringPool.BLANK) || displaySection.equals("newRevision")) ? StringPool.BLANK : "aui-helper-hidden" %>'>
			<c:if test="<%= fileMaxSize != 0 %>">
				<div class="portlet-msg-info">
					<%= LanguageUtil.format(pageContext, "upload-documents-no-larger-than-x-k", String.valueOf(fileMaxSize), false) %>
				</div>
			</c:if>
		</aui:field-wrapper>

		<%
		String folderName = StringPool.BLANK;

		if (folderId > 0) {
			folder = DLAppLocalServiceUtil.getFolder(folderId);

			folder = folder.toEscapedModel();

			folderId = folder.getFolderId();
			folderName = folder.getName();
		}
		else {
			folderName = LanguageUtil.get(pageContext, "home");
		}
		%>

		<aui:field-wrapper cssClass='<%= (displaySection.equals("checkin") || displaySection.equals("newRevision")) ? StringPool.BLANK : "aui-helper-hidden" %>'>
			<aui:input label="major-revision" name="majorVersion" type="radio" value="true" />
			<aui:input label="minor-revision" checked="true" name="majorVersion" type="radio" value="false" />
		</aui:field-wrapper>


		<aui:input cssClass='<%= (displaySection.equals(StringPool.BLANK) || displaySection.equals("newRevision")) ? StringPool.BLANK : "aui-helper-hidden" %>' name="file" type="file" style="width: auto;">
			<aui:validator name="acceptFiles">
				'<%= StringUtil.merge(PrefsPropsUtil.getStringArray(PropsKeys.DL_FILE_EXTENSIONS, StringPool.COMMA)) %>'
			</aui:validator>
		</aui:input>

		<aui:input cssClass='<%= !displaySection.equals("checkin") ? StringPool.BLANK : "aui-helper-hidden" %>' name="title">
			<aui:validator errorMessage="you-must-specify-a-file-or-a-title" name="custom">
				function(val, fieldNode, ruleValue) {
					return ((val != '') || A.one('#<portlet:namespace />file').val() != '');
				}
			</aui:validator>
		</aui:input>

		<c:if test='<%= ((folder == null) || folder.isSupportsMetadata()) %>'>
			<aui:input name="description" />

			<c:if test="<%= (folder == null) || (folder.getModel() instanceof DLFolder) && displaySection.equals(StringPool.BLANK) %>">

				<%
				boolean inherited = false;

				if (folder != null) {
					DLFolder dlFolder = (DLFolder)folder.getModel();

					inherited = !dlFolder.isOverrideFileEntryTypes();
				}

				List<DLFileEntryType> dlFileEntryTypes = DLFileEntryTypeLocalServiceUtil.getFolderFileEntryTypes(DLUtil.getGroupIds(themeDisplay), folderId, inherited);
				%>

				<c:choose>
					<c:when test='<%= !cmd.equals(Constants.ADD) && (dlFileEntryTypes.size() > 1) %>'>
						<aui:select changesContext="<%= true %>" label="document-type" name="fileEntryTypeId" onChange='<%= renderResponse.getNamespace() + "changeFileEntryType();" %>'>

							<%
							for (DLFileEntryType curDLFileEntryType : dlFileEntryTypes) {
							%>

								<aui:option label="<%= curDLFileEntryType.getName() %>" selected="<%= (fileEntryTypeId == curDLFileEntryType.getPrimaryKey()) %>" value="<%= curDLFileEntryType.getPrimaryKey() %>" />

							<%
							}
							%>

						</aui:select>
					</c:when>
					<c:otherwise>
						<aui:input name="fileEntryTypeId" type="hidden" value="<%= fileEntryTypeId %>" />
					</c:otherwise>
				</c:choose>

				<%
				if (fileEntryTypeId > 0) {
					try {
						List<DDMStructure> ddmStructures = dlFileEntryType.getDDMStructures();

						for (DDMStructure ddmStructure : ddmStructures) {
							Fields fields = null;

							try {
								DLFileEntryMetadata fileEntryMetadata = DLFileEntryMetadataLocalServiceUtil.getFileEntryMetadata(ddmStructure.getStructureId(), fileVersionId);

								fields = StorageEngineUtil.getFields(fileEntryMetadata.getDDMStorageId());
							}
							catch (Exception e) {
							}
				%>

							<%= DDMXSDUtil.getHTML(pageContext, ddmStructure.getXsd(), fields, String.valueOf(ddmStructure.getPrimaryKey()), locale) %>

				<%
						}
					}
					catch (Exception e) {
						_log.error(e, e);
					}
				}
				%>
			</c:if>

			<liferay-ui:custom-attributes-available className="<%= DLFileEntryConstants.getClassName() %>">
				<liferay-ui:custom-attribute-list
					className="<%= DLFileEntryConstants.getClassName() %>"
					classPK="<%= fileVersionId %>"
					editable="<%= true %>"
					label="<%= true %>"
				/>
			</liferay-ui:custom-attributes-available>
		</c:if>

		<c:if test='<%= ((folder == null) || folder.isSupportsSocial()) && displaySection.equals("editProperties") %>'>
			<liferay-ui:panel defaultState="closed" extended="<%= false %>" id="dlFileEntryCategorizationPanel" persistState="<%= true %>" title="categorization">
				<aui:fieldset>
					<aui:input classPK="<%= assetClassPK %>" model="<%= DLFileEntry.class %>" name="categories" type="assetCategories" />

					<aui:input classPK="<%= assetClassPK %>" model="<%= DLFileEntry.class %>" name="tags" type="assetTags" />
				</aui:fieldset>
			</liferay-ui:panel>

			<liferay-ui:panel defaultState="closed" extended="<%= false %>" id="dlFileEntryAssetLinksPanel" persistState="<%= true %>" title="related-assets">
				<aui:fieldset>
					<liferay-ui:input-asset-links
						className="<%= DLFileEntry.class.getName() %>"
						classPK="<%= assetClassPK %>"
					/>
				</aui:fieldset>
			</liferay-ui:panel>
		</c:if>

		<%
		boolean approved = false;
		boolean draft = false;
		boolean pending = false;

		if (fileVersion != null) {
			approved = fileVersion.isApproved();
			draft = fileVersion.isDraft();
			pending = fileVersion.isPending();
		}
		%>

		<c:if test="<%= approved %>">
			<div class="portlet-msg-info">
				<liferay-ui:message key="a-new-version-will-be-created-automatically-if-this-content-is-modified" />
			</div>
		</c:if>

		<c:if test="<%= pending %>">
			<div class="portlet-msg-info">
				<liferay-ui:message key="there-is-a-publication-workflow-in-process" />
			</div>
		</c:if>

		<aui:button-row>

			<%
			String saveButtonLabel = "save";

			if ((fileVersion == null) || draft || approved) {
				saveButtonLabel = "save-as-draft";
			}

			String publishButtonLabel = "publish";

			if (WorkflowDefinitionLinkLocalServiceUtil.hasWorkflowDefinitionLink(themeDisplay.getCompanyId(), scopeGroupId, DLFileEntryConstants.getClassName())) {
				publishButtonLabel = "submit-for-publication";
			}

			if ((checkedOut || pending) && !PropsValues.DL_FILE_ENTRY_DRAFTS_ENABLED) {
				publishButtonLabel = "save";
			}
			%>

			<c:if test='<%= PropsValues.DL_FILE_ENTRY_DRAFTS_ENABLED && !displaySection.equals("checkin") %>'>
				<aui:button disabled="<%= checkedOut && !hasLock %>" name="saveButton" onClick='<%= renderResponse.getNamespace() + "saveFileEntry(true);" %>' value="<%= saveButtonLabel %>" />
			</c:if>

			<aui:button disabled="<%= checkedOut && !hasLock || (pending && PropsValues.DL_FILE_ENTRY_DRAFTS_ENABLED) %>" name="publishButton" type="submit" value="<%= publishButtonLabel %>" />

			<c:if test="<%= (fileEntry != null) && ((checkedOut && hasLock) || !checkedOut) %>">
				<c:if test="<%= hasLock %>">
					<aui:button onClick='<%= renderResponse.getNamespace() + "checkIn();" %>' value="save-and-checkin" />
				</c:if>
			</c:if>

			<aui:button href="<%= redirect %>" type="cancel" />
		</aui:button-row>
	</aui:fieldset>
</aui:form>

<liferay-ui:upload-progress
	id="<%= uploadProgressId %>"
	message="uploading"
	redirect="<%= redirect %>"
/>

<aui:script>
	function <portlet:namespace />changeFileEntryType() {
		document.<portlet:namespace />dialogFm.<portlet:namespace /><%= Constants.CMD %>.value = "<%= Constants.PREVIEW %>";
		submitForm(document.<portlet:namespace />dialogFm);
	}

	function <portlet:namespace />cancelCheckOut() {
		submitForm(document.hrefFm, "<portlet:actionURL><portlet:param name="struts_action" value="/document_library/edit_file_entry" /><portlet:param name="<%= Constants.CMD %>" value="<%= Constants.CANCEL_CHECKOUT %>" /><portlet:param name="redirect" value="<%= redirect %>" /><portlet:param name="fileEntryId" value="<%= String.valueOf(fileEntryId) %>" /></portlet:actionURL>");
	}

	function <portlet:namespace />checkIn() {
		document.<portlet:namespace />dialogFm.<portlet:namespace /><%= Constants.CMD %>.value = "<%= Constants.UPDATE_AND_CHECKIN %>";
		submitForm(document.<portlet:namespace />dialogFm);
	}

	function <portlet:namespace />checkOut() {
		submitForm(document.hrefFm, "<portlet:actionURL><portlet:param name="struts_action" value="/document_library/edit_file_entry" /><portlet:param name="<%= Constants.CMD %>" value="<%= Constants.CHECKOUT %>" /><portlet:param name="redirect" value="<%= redirect %>" /><portlet:param name="fileEntryId" value="<%= String.valueOf(fileEntryId) %>" /></portlet:actionURL>");
	}

	function <portlet:namespace />openFolderSelector() {
		var folderWindow = window.open('<liferay-portlet:renderURL windowState="<%= LiferayWindowState.POP_UP.toString() %>"><portlet:param name="struts_action" value='<%= "/document_library/select_folder" %>' /></liferay-portlet:renderURL>', 'folder', 'directories=no,height=640,location=no,menubar=no,resizable=yes,scrollbars=yes,status=no,toolbar=no,width=830');

		folderWindow.focus();
	}

	function <portlet:namespace />revertFileEntry() {
		document.<portlet:namespace />dialogFm.<portlet:namespace /><%= Constants.CMD %>.value = "<%= Constants.REVERT %>";
		submitForm(document.<portlet:namespace />dialogFm);
	}

	function <portlet:namespace />saveFileEntry(draft) {
		<%= HtmlUtil.escape(uploadProgressId) %>.startProgress();

		if (draft) {
			document.<portlet:namespace />dialogFm.<portlet:namespace />workflowAction.value = "<%= WorkflowConstants.ACTION_SAVE_DRAFT %>";
		}

		document.<portlet:namespace />dialogFm.<portlet:namespace /><%= Constants.CMD %>.value = "<%= (fileEntry == null) ? Constants.ADD : Constants.UPDATE %>";
		submitForm(document.<portlet:namespace />dialogFm);
	}

	function <portlet:namespace />selectFolder(folderId, folderName) {
		var folderData = {
			idString: 'folderId',
			idValue: folderId,
			nameString: 'folderName',
			nameValue: folderName
		};

		Liferay.Util.selectFolder(folderData, '<liferay-portlet:renderURL portletName="<%= portletResource %>"><portlet:param name="struts_action" value='<%= "/document_library/view" %>' /></liferay-portlet:renderURL>', '<portlet:namespace />');
	}

	<c:if test="<%= windowState.equals(WindowState.MAXIMIZED) %>">
		Liferay.Util.focusFormField(document.<portlet:namespace />dialogFm.<portlet:namespace />file);
	</c:if>
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

<%!
private static Log _log = LogFactoryUtil.getLog("portal-web.docroot.html.portlet.document_library.edit_file_entry_jsp");
%>