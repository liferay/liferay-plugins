<%--
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
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
String tabs2 = ParamUtil.getString(request, "tabs2", "version-history");

String redirect = ParamUtil.getString(request, "redirect");

String referringPortletResource = ParamUtil.getString(request, "referringPortletResource");

String uploadProgressId = "dlFileEntryUploadProgress";

FileEntry fileEntry = (FileEntry)request.getAttribute(WebKeys.DOCUMENT_LIBRARY_FILE_ENTRY);

long fileEntryId = fileEntry.getFileEntryId();

long folderId = fileEntry.getFolderId();

if (Validator.isNull(redirect)) {
	PortletURL portletURL = renderResponse.createRenderURL();

	portletURL.setParameter("struts_action", "/document_library/view");
	portletURL.setParameter("folderId", String.valueOf(folderId));

	redirect = portletURL.toString();
}

Folder folder = fileEntry.getFolder();
FileVersion fileVersion = (FileVersion)request.getAttribute(WebKeys.DOCUMENT_LIBRARY_FILE_VERSION);

boolean versionSpecific = false;

if (fileVersion != null) {
	versionSpecific = true;
}
else if ((user.getUserId() == fileEntry.getUserId()) || permissionChecker.isContentReviewer(user.getCompanyId(), scopeGroupId) || DLFileEntryPermission.contains(permissionChecker, fileEntry, ActionKeys.UPDATE)) {
	fileVersion = fileEntry.getLatestFileVersion();
}
else {
	fileVersion = fileEntry.getFileVersion();
}

long fileVersionId = fileVersion.getFileVersionId();

long fileEntryTypeId = 0;

if (fileVersion.getModel() instanceof DLFileVersion) {
	DLFileVersion dlFileVersion = (DLFileVersion)fileVersion.getModel();

	fileEntryTypeId = dlFileVersion.getFileEntryTypeId();
}

Lock lock = fileEntry.getLock();

String[] conversions = new String[0];

if (PropsValues.DL_FILE_ENTRY_CONVERSIONS_ENABLED && PrefsPropsUtil.getBoolean(PropsKeys.OPENOFFICE_SERVER_ENABLED, PropsValues.OPENOFFICE_SERVER_ENABLED)) {
	conversions = (String[])DocumentConversionUtil.getConversions(fileVersion.getExtension());
}

long assetClassPK = 0;

if (!fileVersion.isApproved() && !fileVersion.getVersion().equals(DLFileEntryConstants.VERSION_DEFAULT) && !fileEntry.isInTrash()) {
	assetClassPK = fileVersion.getFileVersionId();
}
else {
	assetClassPK = fileEntry.getFileEntryId();
}

String webDavUrl = StringPool.BLANK;

if (portletDisplay.isWebDAVEnabled()) {
	webDavUrl = DLUtil.getWebDavURL(themeDisplay, folder, fileEntry);
}

boolean hasAudio = AudioProcessorUtil.hasAudio(fileVersion);
boolean hasImages = ImageProcessorUtil.hasImages(fileVersion);
boolean hasPDFImages = PDFProcessorUtil.hasImages(fileVersion);
boolean hasVideo = VideoProcessorUtil.hasVideo(fileVersion);

AssetEntry layoutAssetEntry = AssetEntryLocalServiceUtil.fetchEntry(DLFileEntryConstants.getClassName(), assetClassPK);

request.setAttribute(WebKeys.LAYOUT_ASSET_ENTRY, layoutAssetEntry);

request.setAttribute("view_file_entry.jsp-fileEntry", fileEntry);
%>

<%@ include file="/html/portlet/document_library/google_docs_url.jspf" %>

<portlet:actionURL var="editFileEntry">
	<portlet:param name="struts_action" value="/document_library/edit_file_entry" />
</portlet:actionURL>

<aui:form action="<%= editFileEntry %>" method="post" name="fm">
	<aui:input name="<%= Constants.CMD %>" type="hidden" />
	<aui:input name="redirect" type="hidden" value="<%= currentURL %>" />
	<aui:input name="fileEntryId" type="hidden" value="<%= String.valueOf(fileEntry.getFileEntryId()) %>" />
</aui:form>

<c:if test="<%= showHeader && (folder != null) %>">
	<liferay-ui:header
		backURL="<%= redirect %>"
		localizeTitle="<%= false %>"
		title="<%= fileVersion.getTitle() %>"
	/>
</c:if>

<div class="view">
	<c:if test="<%= showActions %>">
		<liferay-ui:app-view-toolbar>
			<aui:button-row cssClass="edit-toolbar" id='<%= renderResponse.getNamespace() + "fileEntryToolbar" %>' />
		</liferay-ui:app-view-toolbar>
	</c:if>

	<aui:row>
		<aui:col cssClass="lfr-asset-column-details" width="<%= 70 %>">
			<c:if test="<%= showActions %>">
				<liferay-ui:app-view-toolbar>
					<aui:button-row cssClass="edit-toolbar" id='<%= renderResponse.getNamespace() + "fileEntryToolbar" %>' />
				</liferay-ui:app-view-toolbar>
			</c:if>

			<div class="alert alert-error hide" id="<portlet:namespace />openMSOfficeError"></div>

			<c:if test="<%= (fileEntry.getLock() != null) && DLFileEntryPermission.contains(permissionChecker, fileEntry, ActionKeys.UPDATE) %>">
				<c:choose>
					<c:when test="<%= fileEntry.hasLock() %>">
						<div class="alert alert-success">
							<c:choose>
								<c:when test="<%= lock.isNeverExpires() %>">
									<liferay-ui:message key="you-now-have-an-indefinite-lock-on-this-document" />
								</c:when>
								<c:otherwise>

									<%
									String lockExpirationTime = StringUtil.toLowerCase(LanguageUtil.getTimeDescription(pageContext, DLFileEntryConstants.LOCK_EXPIRATION_TIME));
									%>

									<%= LanguageUtil.format(pageContext, "you-now-have-a-lock-on-this-document", lockExpirationTime, false) %>
								</c:otherwise>
							</c:choose>
						</div>
					</c:when>
					<c:otherwise>
						<div class="alert alert-error">
							<%= LanguageUtil.format(pageContext, "you-cannot-modify-this-document-because-it-was-locked-by-x-on-x", new Object[] {HtmlUtil.escape(PortalUtil.getUserName(lock.getUserId(), String.valueOf(lock.getUserId()))), dateFormatDateTime.format(lock.getCreateDate())}, false) %>
						</div>
					</c:otherwise>
				</c:choose>
			</c:if>

			<liferay-util:buffer var="documentTitle">
				<%= fileVersion.getTitle() %>

				<c:if test="<%= versionSpecific %>">
					(<liferay-ui:message key="version" /> <%= fileVersion.getVersion() %>)
				</c:if>
			</liferay-util:buffer>

			<div class="body-row">
				<div class="document-info">
					<c:if test="<%= showAssetMetadata %>">
						<h2 class="document-title" title="<%= HtmlUtil.escapeAttribute(documentTitle) %>">
							<%= HtmlUtil.escape(documentTitle) %>
						</h2>

						<span class="document-thumbnail">

							<%
							String thumbnailSrc = DLUtil.getThumbnailSrc(fileEntry, fileVersion, null, themeDisplay);

							if (layoutAssetEntry != null) {
								AssetEntry incrementAssetEntry = AssetEntryServiceUtil.incrementViewCounter(layoutAssetEntry.getClassName(), fileEntry.getFileEntryId());

								if (incrementAssetEntry != null) {
									layoutAssetEntry = incrementAssetEntry;
								}
							}
							%>

							<img alt="" border="no" class="thumbnail" src="<%= thumbnailSrc %>" style="max-height: <%= PropsValues.DL_FILE_ENTRY_THUMBNAIL_MAX_HEIGHT %>px; max-width: <%= PropsValues.DL_FILE_ENTRY_THUMBNAIL_MAX_WIDTH %>px;" />
						</span>

						<span class="user-date">

							<%
							String displayURL = StringPool.BLANK;

							User userDisplay = UserLocalServiceUtil.fetchUser(fileEntry.getUserId());

							if (userDisplay != null) {
								displayURL = userDisplay.getDisplayURL(themeDisplay);
							}
							%>

							<liferay-ui:icon image="../document_library/add_document" label="<%= true %>" message='<%= LanguageUtil.format(pageContext, "uploaded-by-x-x", new Object[] {displayURL, HtmlUtil.escape(fileEntry.getUserName()), dateFormatDateTime.format(fileEntry.getCreateDate())}) %>' />
						</span>

						<c:if test="<%= enableRatings && fileEntry.isSupportsSocial() %>">
							<span class="lfr-asset-ratings">
								<liferay-ui:ratings
									className="<%= DLFileEntryConstants.getClassName() %>"
									classPK="<%= fileEntryId %>"
								/>
							</span>
						</c:if>

						<c:if test="<%= enableRelatedAssets && fileEntry.isSupportsSocial() %>">
							<div class="entry-links">
								<liferay-ui:asset-links
									assetEntryId="<%= layoutAssetEntry.getEntryId() %>"
								/>
							</div>
						</c:if>
					</c:if>

					<span class="document-description">
						<%= HtmlUtil.escape(fileVersion.getDescription()) %>
					</span>

					<c:if test="<%= showAssetMetadata && fileEntry.isSupportsSocial() %>">
						<div class="lfr-asset-categories">
							<liferay-ui:asset-categories-summary
								className="<%= DLFileEntryConstants.getClassName() %>"
								classPK="<%= assetClassPK %>"
							/>
						</div>

						<div class="lfr-asset-tags">
							<liferay-ui:asset-tags-summary
								className="<%= DLFileEntryConstants.getClassName() %>"
								classPK="<%= assetClassPK %>"
								message="tags"
							/>
						</div>
					</c:if>
				</div>

				<aui:model-context bean="<%= fileVersion %>" model="<%= DLFileVersion.class %>" />

				<c:if test="<%= PropsValues.DL_FILE_ENTRY_PREVIEW_ENABLED && Validator.isNotNull(googleDocsViewURL) %>">
					<div style="height: 300px">
						<iframe src="<%= googleDocsViewURL %>" style="border-width: 0; height: 100%; width: 100%;"></iframe>
					</div>
				</c:if>

				<c:if test="<%= showAssetMetadata && PropsValues.DL_FILE_ENTRY_COMMENTS_ENABLED %>">
					<liferay-ui:panel collapsible="<%= true %>" cssClass="lfr-document-library-comments" extended="<%= true %>" persistState="<%= true %>" title="comments">
						<portlet:actionURL var="discussionURL">
							<portlet:param name="struts_action" value="/document_library/edit_file_entry_discussion" />
						</portlet:actionURL>

						<liferay-ui:discussion
							className="<%= DLFileEntryConstants.getClassName() %>"
							classPK="<%= fileEntryId %>"
							formAction="<%= discussionURL %>"
							formName="fm2"
							ratingsEnabled="<%= enableCommentRatings %>"
							redirect="<%= currentURL %>"
							userId="<%= fileEntry.getUserId() %>"
						/>
					</liferay-ui:panel>
				</c:if>
			</div>
		</aui:col>

		<aui:col cssClass="lfr-asset-column-details context-pane" last="<%= true %>" width="<%= 30 %>">
			<div class="body-row asset-details">
				<c:if test="<%= showAssetMetadata %>">
					<div class="asset-details-content">
						<h3 class="version <%= fileEntry.isCheckedOut() ? "document-locked" : StringPool.BLANK %>">
							<liferay-ui:message key="version" /> <%= HtmlUtil.escape(fileVersion.getVersion()) %>
						</h3>

						<div class="lfr-asset-icon lfr-asset-author">
							<liferay-ui:message arguments="<%= HtmlUtil.escape(fileVersion.getStatusByUserName()) %>" key="last-updated-by-x" />
						</div>

						<div class="lfr-asset-icon lfr-asset-date">
							<%= dateFormatDateTime.format(fileVersion.getModifiedDate()) %>
						</div>

						<c:if test="<%= !portletId.equals(PortletKeys.TRASH) %>">
							<div class="lfr-asset-summary">
								<aui:workflow-status model="<%= DLFileEntry.class %>" status="<%= fileVersion.getStatus() %>" />
							</div>
						</c:if>

						<c:if test="<%= Validator.isNotNull(fileVersion.getDescription()) %>">
							<blockquote class="lfr-asset-description">
								<%= HtmlUtil.escape(fileVersion.getDescription()) %>
							</blockquote>
						</c:if>

						<span class="download-document">
							<c:if test="<%= DLFileEntryPermission.contains(permissionChecker, fileEntry, ActionKeys.VIEW) %>">
								<liferay-ui:icon
									image="download"
									label="<%= true %>"
									message='<%= LanguageUtil.get(pageContext, "download") + " (" + TextFormatter.formatStorageSize(fileVersion.getSize(), locale) + ")" %>'
									url="<%= DLUtil.getPreviewURL(fileEntry, fileVersion, themeDisplay, StringPool.BLANK) %>"
								/>
							</c:if>
						</span>

						<span class="conversions">

							<%
							for (int i = 0; i < conversions.length; i++) {
								String conversion = conversions[i];
							%>

								<liferay-ui:icon
									image='<%= "../file_system/small/" + conversion %>'
									label="<%= true %>"
									message="<%= StringUtil.toUpperCase(conversion) %>"
									url='<%= DLUtil.getPreviewURL(fileEntry, fileVersion, themeDisplay, "&targetExtension=" + conversion) %>'
								/>

							<%
							}
							%>

						</span>

						<span class="webdav-url">
							<c:choose>
								<c:when test="<%= portletDisplay.isWebDAVEnabled() && fileEntry.isSupportsSocial() %>">
									<liferay-ui:message key="get-url-or-webdav-url" />
								</c:when>

								<c:otherwise>
									<liferay-ui:message key="get-url" />
								</c:otherwise>
							</c:choose>
						</span>

						<div class="lfr-asset-field url-file-container hide">
							<aui:field-wrapper name="url">
								<liferay-ui:input-resource
									id="url"
									url="<%= DLUtil.getPreviewURL(fileEntry, fileEntry.getFileVersion(), themeDisplay, StringPool.BLANK, false, true) %>"
								/>
							</aui:field-wrapper>
						</div>

						<c:if test="<%= portletDisplay.isWebDAVEnabled() && fileEntry.isSupportsSocial() %>">
							<div class="lfr-asset-field webdav-url-file-container hide">

								<%
								String webDavHelpMessage = null;

								if (BrowserSnifferUtil.isWindows(request)) {
									webDavHelpMessage = LanguageUtil.format(pageContext, "webdav-windows-help", new Object[] {"http://www.microsoft.com/downloads/details.aspx?FamilyId=17C36612-632E-4C04-9382-987622ED1D64", "http://www.liferay.com/web/guest/community/wiki/-/wiki/Main/WebDAV"});
								}
								else {
									webDavHelpMessage = LanguageUtil.format(pageContext, "webdav-help", "http://www.liferay.com/web/guest/community/wiki/-/wiki/Main/WebDAV");
								}
								%>

								<aui:field-wrapper helpMessage="<%= webDavHelpMessage %>" name="webdavUrl">
									<liferay-ui:input-resource
										id="webdavUrl"
										url="<%= webDavUrl %>"
									/>
								</aui:field-wrapper>
							</div>
						</c:if>
					</div>
				</c:if>

				<%
					request.removeAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);
				%>

				<div class="lfr-asset-panels">
					<liferay-ui:panel-container extended="<%= false %>" id="documentLibraryAssetPanelContainer" persistState="<%= true %>">

						<%
						if (fileEntryTypeId > 0) {
							try {
								DLFileEntryType fileEntryType = DLFileEntryTypeServiceUtil.getFileEntryType(fileEntryTypeId);

								List<DDMStructure> ddmStructures = fileEntryType.getDDMStructures();

								for (DDMStructure ddmStructure : ddmStructures) {
									Fields fields = null;

									try {
										DLFileEntryMetadata fileEntryMetadata = DLFileEntryMetadataLocalServiceUtil.getFileEntryMetadata(ddmStructure.getStructureId(), fileVersionId);

										fields = StorageEngineUtil.getFields(fileEntryMetadata.getDDMStorageId());
									}
									catch (Exception e) {
									}
						%>

									<liferay-ui:panel collapsible="<%= true %>" cssClass="metadata" extended="<%= true %>" id="documentLibraryMetadataPanel" persistState="<%= true %>" title="<%= HtmlUtil.escape(ddmStructure.getName(locale)) %>">

										<liferay-ddm:html
											classNameId="<%= PortalUtil.getClassNameId(DDMStructure.class) %>"
											classPK="<%= ddmStructure.getPrimaryKey() %>"
											fields="<%= fields %>"
											fieldsNamespace="<%= String.valueOf(ddmStructure.getPrimaryKey()) %>"
											readOnly="<%= true %>"
											requestedLocale="<%= locale %>"
										/>

									</liferay-ui:panel>

						<%
								}
							}
							catch (Exception e) {
							}
						}
						%>

						<liferay-ui:custom-attributes-available className="<%= DLFileEntryConstants.getClassName() %>" classPK="<%= fileVersionId %>" editable="<%= false %>">
							<liferay-ui:panel collapsible="<%= true %>" cssClass="custom-fields" id="documentLibraryCustomAttributesPanel" persistState="<%= true %>" title="custom-fields">
								<liferay-ui:custom-attribute-list
									className="<%= DLFileEntryConstants.getClassName() %>"
									classPK="<%= fileVersionId %>"
									editable="<%= false %>"
									label="<%= true %>"
								/>
							</liferay-ui:panel>
						</liferay-ui:custom-attributes-available>

						<%
						try {
							List<DDMStructure> ddmStructures = DDMStructureLocalServiceUtil.getClassStructures(company.getCompanyId(), PortalUtil.getClassNameId(RawMetadataProcessor.class), new StructureStructureKeyComparator(true));

							for (DDMStructure ddmStructure : ddmStructures) {
								Fields fields = null;

								try {
									DLFileEntryMetadata fileEntryMetadata = DLFileEntryMetadataLocalServiceUtil.getFileEntryMetadata(ddmStructure.getStructureId(), fileVersionId);

									fields = StorageEngineUtil.getFields(fileEntryMetadata.getDDMStorageId());
								}
								catch (Exception e) {
								}

								if (fields != null) {
									String name = "metadata." + ddmStructure.getName(locale, true);
						%>

									<liferay-ui:panel collapsible="<%= true %>" cssClass="lfr-asset-metadata" id="documentLibraryAssetMetadataPanel" persistState="<%= true %>" title="<%= name %>">

										<liferay-ddm:html
											classNameId="<%= PortalUtil.getClassNameId(DDMStructure.class) %>"
											classPK="<%= ddmStructure.getPrimaryKey() %>"
											fields="<%= fields %>"
											fieldsNamespace="<%= String.valueOf(ddmStructure.getPrimaryKey()) %>"
											readOnly="<%= true %>"
											requestedLocale="<%= locale %>"
										/>

									</liferay-ui:panel>

						<%
								}
							}
						}
						catch (Exception e) {
						}
						%>

						<c:if test="<%= showAssetMetadata %>">
							<liferay-ui:panel collapsible="<%= true %>" cssClass="version-history" id="documentLibraryVersionHistoryPanel" persistState="<%= true %>" title="version-history">

								<%
								boolean comparableFileEntry = DocumentConversionUtil.isComparableVersion(fileVersion.getExtension());
								boolean showNonApprovedDocuments = false;

								if ((user.getUserId() == fileEntry.getUserId()) || permissionChecker.isContentReviewer(user.getCompanyId(), scopeGroupId)) {
									showNonApprovedDocuments = true;
								}

								SearchContainer searchContainer = new SearchContainer();

								List<String> headerNames = new ArrayList<String>();

								headerNames.add("version");
								headerNames.add("date");
								headerNames.add("size");

								if (showNonApprovedDocuments && !portletId.equals(PortletKeys.TRASH)) {
									headerNames.add("status");
								}

								headerNames.add(StringPool.BLANK);

								searchContainer.setHeaderNames(headerNames);

								PortletURL viewFileEntryURL = renderResponse.createRenderURL();

								viewFileEntryURL.setParameter("struts_action", "/document_library/view_file_entry");
								viewFileEntryURL.setParameter("redirect", currentURL);
								viewFileEntryURL.setParameter("fileEntryId", String.valueOf(fileEntry.getFileEntryId()));

								searchContainer.setIteratorURL(viewFileEntryURL);

								if (comparableFileEntry) {
									RowChecker rowChecker = new RowChecker(renderResponse);

									rowChecker.setAllRowIds(null);

									searchContainer.setRowChecker(rowChecker);
								}

								int status = WorkflowConstants.STATUS_APPROVED;

								if (showNonApprovedDocuments) {
									status = WorkflowConstants.STATUS_ANY;
								}

								List results = fileEntry.getFileVersions(status);
								List resultRows = searchContainer.getResultRows();

								for (int i = 0; i < results.size(); i++) {
									FileVersion curFileVersion = (FileVersion)results.get(i);

									ResultRow row = new ResultRow(new Object[] {fileEntry, curFileVersion, results.size(), conversions, fileEntry.isCheckedOut(), fileEntry.hasLock()}, String.valueOf(curFileVersion.getVersion()), i);

									// Statistics

									row.addText(String.valueOf(curFileVersion.getVersion()));
									row.addDate(curFileVersion.getCreateDate());
									row.addText(TextFormatter.formatStorageSize(curFileVersion.getSize(), locale));

									// Status

									if (showNonApprovedDocuments && !portletId.equals(PortletKeys.TRASH)) {
										row.addStatus(curFileVersion.getStatus(), curFileVersion.getStatusByUserId(), curFileVersion.getStatusDate());
									}

									// Action

									row.addJSP("right", SearchEntry.DEFAULT_VALIGN, "/html/portlet/document_library/file_entry_history_action.jsp");

									// Add result row

									resultRows.add(row);
								}

								if (comparableFileEntry && !results.isEmpty()) {
									FileVersion curFileVersion = (FileVersion)results.get(0);
								%>

									<portlet:actionURL var="compareVersionsURL">
										<portlet:param name="struts_action" value="/document_library/compare_versions" />
									</portlet:actionURL>

									<aui:form action="<%= compareVersionsURL %>" method="post" name="fm1" onSubmit='<%= "event.preventDefault(); " + renderResponse.getNamespace() + "compare();" %>'>
										<aui:input name="backURL" type="hidden" value="<%= currentURL %>" />
										<aui:input name="fileEntryId" type="hidden" value="<%= fileEntryId %>" />
										<aui:input name="sourceVersion" type="hidden" value="<%= curFileVersion.getVersion() %>" />
										<aui:input name="targetVersion" type="hidden" value="<%= fileEntry.getVersion() %>" />

										<aui:button-row>
											<aui:button type="submit" value="compare-versions" />
										</aui:button-row>
									</aui:form>

								<%
								}
								%>

								<liferay-ui:search-iterator paginate="<%= false %>" searchContainer="<%= searchContainer %>" />
							</liferay-ui:panel>
						</c:if>
					</liferay-ui:panel-container>
				</div>
			</div>
		</aui:col>
	</aui:row>
</div>

<aui:script>
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
		['aui-base', 'selector-css3']
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
		['aui-base', 'selector-css3']
	);
</aui:script>

<c:if test="<%= DLFileEntryPermission.contains(permissionChecker, fileEntry, ActionKeys.VIEW) && DLUtil.isOfficeExtension(fileVersion.getExtension()) && portletDisplay.isWebDAVEnabled() && BrowserSnifferUtil.isIeOnWin32(request) %>">
	<%@ include file="/html/portlet/document_library/action/open_document_js.jspf" %>
</c:if>

<aui:script use="aui-base,aui-toolbar">
	var showURLFile = A.one('.show-url-file');
	var showWebDavFile = A.one('.show-webdav-url-file');

	if (showURLFile) {
		showURLFile.on(
			'click',
			function(event) {
				var URLFileContainer = A.one('.url-file-container');

				URLFileContainer.toggleClass('hide');
			}
		);
	}

	if (showWebDavFile) {
		showWebDavFile.on(
			'click',
			function(event) {
				var WebDavFileContainer = A.one('.webdav-url-file-container');

				WebDavFileContainer.toggleClass('hide');
			}
		);
	}

	<c:if test="<%= showActions %>">
		var buttonRow = A.one('#<portlet:namespace />fileEntryToolbar');

		var fileEntryButtonGroup = [];

		<c:if test="<%= DLFileEntryPermission.contains(permissionChecker, fileEntry, ActionKeys.UPDATE) && (!fileEntry.isCheckedOut() || fileEntry.hasLock()) %>">
			fileEntryButtonGroup.push(
				{

					<portlet:renderURL var="editURL">
						<portlet:param name="struts_action" value="/document_library/edit_file_entry" />
						<portlet:param name="redirect" value="<%= currentURL %>" />
						<portlet:param name="fileEntryId" value="<%= String.valueOf(fileEntry.getFileEntryId()) %>" />
					</portlet:renderURL>

					icon: 'icon-pencil',
					label: '<%= UnicodeLanguageUtil.get(pageContext, "edit") %>',
					on: {
						click: function(event) {
							location.href = '<%= editURL.toString() %>';
						}
					}
				},
				{
					label: '<%= UnicodeLanguageUtil.get(pageContext, "edit-in-google-docs") %>',
					on: {
						click: function(event) {
							window.open('<%= googleDocsEditURL %>');
						}
					}
				},
				{

					<portlet:renderURL var="moveURL">
						<portlet:param name="struts_action" value="/document_library/move_file_entry" />
						<portlet:param name="redirect" value="<%= redirect %>" />
						<portlet:param name="fileEntryId" value="<%= String.valueOf(fileEntry.getFileEntryId()) %>" />
					</portlet:renderURL>

					icon: 'icon-move',
					label: '<%= UnicodeLanguageUtil.get(pageContext, "move") %>',
					on: {
						click: function(event) {
							location.href = '<%= moveURL.toString() %>';
						}
					}
				}
			);
		</c:if>

		<c:if test="<%= DLFileEntryPermission.contains(permissionChecker, fileEntry, ActionKeys.PERMISSIONS) %>">
			fileEntryButtonGroup.push(
				{
					<liferay-security:permissionsURL
						modelResource="<%= DLFileEntryConstants.getClassName() %>"
						modelResourceDescription="<%= fileEntry.getTitle() %>"
						resourcePrimKey="<%= String.valueOf(fileEntry.getFileEntryId()) %>"
						var="permissionsURL"
						windowState="<%= LiferayWindowState.POP_UP.toString() %>"
					/>

					icon: 'icon-permissions',
					label: '<%= UnicodeLanguageUtil.get(pageContext, "permissions") %>',
					on: {
						click: function(event) {
							Liferay.Util.openWindow(
								{
									title: '<%= UnicodeLanguageUtil.get(pageContext, "permissions") %>',
									uri: '<%= permissionsURL.toString() %>'
								}
							);
						}
					}
				}
			);
		</c:if>

		<c:if test="<%= DLFileEntryPermission.contains(permissionChecker, fileEntry, ActionKeys.DELETE) && (fileEntry.getModel() instanceof DLFileEntry) && TrashUtil.isTrashEnabled(scopeGroupId) %>">
			fileEntryButtonGroup.push(
				{
					<portlet:renderURL var="viewFolderURL">
						<portlet:param name="struts_action" value="/document_library/view" />
						<portlet:param name="folderId" value="<%= String.valueOf(fileEntry.getFolderId()) %>" />
					</portlet:renderURL>

					icon: 'icon-trash',
					label: '<%= UnicodeLanguageUtil.get(pageContext, "move-to-the-recycle-bin") %>',
					on: {
						click: function(event) {
							document.<portlet:namespace />fm.<portlet:namespace /><%= Constants.CMD %>.value = '<%= Constants.MOVE_TO_TRASH %>';
							document.<portlet:namespace />fm.<portlet:namespace />redirect.value = '<%= viewFolderURL.toString() %>';
							submitForm(document.<portlet:namespace />fm);
						}
					}
				}
			);
		</c:if>

		<c:if test="<%= DLFileEntryPermission.contains(permissionChecker, fileEntry, ActionKeys.DELETE) && (!(fileEntry.getModel() instanceof DLFileEntry) || !TrashUtil.isTrashEnabled(scopeGroupId)) %>">
			fileEntryButtonGroup.push(
				{
					<portlet:renderURL var="viewFolderURL">
						<portlet:param name="struts_action" value="/document_library/view" />
						<portlet:param name="folderId" value="<%= String.valueOf(fileEntry.getFolderId()) %>" />
					</portlet:renderURL>

					icon: 'icon-delete',
					label: '<%= UnicodeLanguageUtil.get(pageContext, "delete") %>',
					on: {
						click: function(event) {
							if (confirm('<%= UnicodeLanguageUtil.get(pageContext, "are-you-sure-you-want-to-delete-this") %>')) {
								document.<portlet:namespace />fm.<portlet:namespace /><%= Constants.CMD %>.value = '<%= Constants.DELETE %>';
								document.<portlet:namespace />fm.<portlet:namespace />redirect.value = '<%= viewFolderURL.toString() %>';
								submitForm(document.<portlet:namespace />fm);
							}
						}
					}
				}
			);
		</c:if>

		var fileEntryToolbar = new A.Toolbar(
			{
				boundingBox: buttonRow,
				children: [fileEntryButtonGroup]
			}
		).render();

		buttonRow.setData('fileEntryToolbar', fileEntryToolbar);
	</c:if>

	<portlet:namespace />initRowsChecked();

	A.all('input[name=<portlet:namespace />rowIds]').on(
		'click',
		function(event) {
			<portlet:namespace />updateRowsChecked(event.currentTarget);
		}
	);
</aui:script>

<%
if (!portletId.equals(PortletKeys.TRASH)) {
	DLUtil.addPortletBreadcrumbEntries(fileEntry, request, renderResponse);
}
%>