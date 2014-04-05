<%--
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This file is part of Liferay Social Office. Liferay Social Office is free
 * software: you can redistribute it and/or modify it under the terms of the GNU
 * Affero General Public License as published by the Free Software Foundation,
 * either version 3 of the License, or (at your option) any later version.
 *
 * Liferay Social Office is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License
 * for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * Liferay Social Office. If not, see http://www.gnu.org/licenses/agpl-3.0.html.
 */
--%>

<%@ include file="/html/portlet/document_library/init.jsp" %>

<%@ page import="com.liferay.portlet.messageboards.service.MBMessageLocalServiceUtil" %>

<%
String tabs2 = ParamUtil.getString(request, "tabs2", "version-history");

String redirect = ParamUtil.getString(request, "redirect");

String referringPortletResource = ParamUtil.getString(request, "referringPortletResource");

String uploadProgressId = "dlFileEntryUploadProgress";

FileEntry fileEntry = (FileEntry)request.getAttribute(WebKeys.DOCUMENT_LIBRARY_FILE_ENTRY);

long fileEntryId = fileEntry.getFileEntryId();
long folderId = fileEntry.getFolderId();
String extension = fileEntry.getExtension();
String title = fileEntry.getTitle();
boolean checkedOut = fileEntry.isCheckedOut();
boolean hasLock = fileEntry.hasLock();

Folder folder = fileEntry.getFolder();
FileVersion fileVersion = (FileVersion)request.getAttribute(WebKeys.DOCUMENT_LIBRARY_FILE_VERSION);

boolean versionSpecific = false;

if (fileVersion != null) {
	versionSpecific = true;
}
else if ((user.getUserId() == fileEntry.getUserId()) || permissionChecker.isCompanyAdmin() || permissionChecker.isGroupAdmin(scopeGroupId) || DLFileEntryPermission.contains(permissionChecker, fileEntry, ActionKeys.UPDATE)) {
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

if (PrefsPropsUtil.getBoolean(PropsKeys.OPENOFFICE_SERVER_ENABLED, PropsValues.OPENOFFICE_SERVER_ENABLED)) {
	conversions = (String[])DocumentConversionUtil.getConversions(extension);
}

long assetClassPK = 0;

if (!fileVersion.isApproved() && !fileVersion.getVersion().equals(DLFileEntryConstants.VERSION_DEFAULT)) {
	assetClassPK = fileVersion.getFileVersionId();
	title = fileVersion.getTitle();
	extension = fileVersion.getExtension();
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

<c:if test="<%= layout != null %>">

	<%
	Group group = layout.getGroup();
	%>

	<c:if test="<%= !group.isControlPanel() %>">

		<%
		FileEntry fileEntryBreadcrumb = (FileEntry)request.getAttribute(WebKeys.DOCUMENT_LIBRARY_FILE_ENTRY);

		DLUtil.addPortletBreadcrumbEntries(fileEntryBreadcrumb, request, renderResponse);
		%>

		<div class="so-breadcrumbs">
			<liferay-ui:breadcrumb showCurrentGroup="<%= false %>" showCurrentPortlet="<%= false %>" showGuestGroup="<%= false %>" showLayout="<%= true %>" showParentGroups="<%= false %>" />
		</div>
	</c:if>
</c:if>

<portlet:actionURL var="editFileEntry">
	<portlet:param name="struts_action" value="/document_library/edit_file_entry" />
	<portlet:param name="fileEntryId" value="<%= String.valueOf(fileEntry.getFileEntryId()) %>" />
</portlet:actionURL>

<aui:form action="<%= editFileEntry %>" method="post" name="fm">
	<aui:input name="<%= Constants.CMD %>" type="hidden" />
	<aui:input name="redirect" type="hidden" value="<%= currentURL %>" />
</aui:form>

<c:if test="<%= folder != null %>">

	<%
	String versionText = null;

	if (Validator.isNotNull(fileVersion.getVersion())) {
		versionText = LanguageUtil.format(pageContext, "version-x", fileVersion.getVersion());
	}
	else {
		versionText = LanguageUtil.get(pageContext, "not-approved");
	}
	%>

</c:if>

<div class="view">
	<aui:layout>
		<div class="lfr-header-row">
			<div class="lfr-header-row-content">
				<aui:button-row cssClass="edit-toolbar" id='<%= renderResponse.getNamespace() + "fileEntryToolbar" %>' />
			</div>
		</div>

		<aui:column columnWidth="<%= 65 %>" cssClass="lfr-asset-column-details" first="<%= true %>">
			<c:if test="<%= (fileEntry.getLock() != null) && DLFileEntryPermission.contains(permissionChecker, fileEntry, ActionKeys.UPDATE) %>">
				<c:choose>
					<c:when test="<%= hasLock %>">
						<div class="portlet-msg-lock portlet-msg-success">
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
							<%= LanguageUtil.format(pageContext, "you-cannot-modify-this-document-because-it-was-locked-by-x-on-x", new Object[] {HtmlUtil.escape(PortalUtil.getUserName(lock.getUserId(), String.valueOf(lock.getUserId()))), dateFormatDateTime.format(lock.getCreateDate())}, false) %>
						</div>
					</c:otherwise>
				</c:choose>
			</c:if>

			<div class="body-row">
				<div class="document-info">
					<span class="document-thumbnail">

						<%
						DLFileShortcut dlFileShortcut = null;

						String thumbnailSrc = DLUtil.getThumbnailSrc(fileEntry, fileVersion, dlFileShortcut, themeDisplay);

						if (layoutAssetEntry != null) {
							AssetEntry incrementAssetEntry = AssetEntryServiceUtil.incrementViewCounter(layoutAssetEntry.getClassName(), fileEntry.getFileEntryId());

							if (incrementAssetEntry != null) {
								layoutAssetEntry = incrementAssetEntry;
							}
						}
						%>

						<img alt="" border="no" class="thumbnail" src="<%= thumbnailSrc %>" />
					</span>

					<div class="document-header">
						<span class="document-title">
							<c:choose>
								<c:when test="<%= versionSpecific %>">
									<%= fileVersion.getTitle() %>

									(<liferay-ui:message key="version" /> <%= HtmlUtil.escape(fileVersion.getVersion()) %>)
								</c:when>
								<c:otherwise>
									<%= title %>
								</c:otherwise>
							</c:choose>
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

						<c:if test="<%= fileEntry.isSupportsSocial() %>">
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

						<c:if test="<%= Validator.isNotNull(fileVersion.getDescription()) %>">
							<span class="document-description">
								<%= HtmlUtil.escape(fileVersion.getDescription()) %>
							</span>
						</c:if>

						<c:if test="<%= fileEntry.isSupportsSocial() %>">
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
				</div>

				<aui:model-context bean="<%= fileVersion %>" model="<%= DLFileVersion.class %>" />

				<c:if test="<%= PropsValues.DL_FILE_ENTRY_PREVIEW_ENABLED %>">
					<div>

						<%
						int previewFileCount = 0;
						String previewFileURL = null;
						String[] previewFileURLs = null;
						String videoThumbnailURL = null;

						String previewQueryString = null;

						if (hasAudio) {
							previewQueryString = "&audioPreview=1";
						}
						else if (hasImages) {
							previewQueryString = "&imagePreview=1";
						}
						else if (hasPDFImages) {
							previewFileCount = PDFProcessorUtil.getPreviewFileCount(fileVersion);

							previewQueryString = "&previewFileIndex=";

							previewFileURL = DLUtil.getPreviewURL(fileEntry, fileVersion, themeDisplay, previewQueryString);
						}
						else if (hasVideo) {
							previewQueryString = "&videoPreview=1";

							videoThumbnailURL = DLUtil.getPreviewURL(fileEntry, fileVersion, themeDisplay, "&videoThumbnail=1");
						}

						if (Validator.isNotNull(previewQueryString)) {
							if (hasAudio) {
								previewFileURLs = new String[PropsValues.DL_FILE_ENTRY_PREVIEW_AUDIO_CONTAINERS.length];

								for (int i = 0; i < PropsValues.DL_FILE_ENTRY_PREVIEW_AUDIO_CONTAINERS.length; i++) {
									previewFileURLs[i] = DLUtil.getPreviewURL(fileEntry, fileVersion, themeDisplay, previewQueryString + "&type=" + PropsValues.DL_FILE_ENTRY_PREVIEW_AUDIO_CONTAINERS[i]);
								}
							}
							else if (hasVideo) {
								if (PropsValues.DL_FILE_ENTRY_PREVIEW_VIDEO_CONTAINERS.length > 0) {
									previewFileURLs = new String[PropsValues.DL_FILE_ENTRY_PREVIEW_VIDEO_CONTAINERS.length];

									for (int i = 0; i < PropsValues.DL_FILE_ENTRY_PREVIEW_VIDEO_CONTAINERS.length; i++) {
										previewFileURLs[i] = DLUtil.getPreviewURL(fileEntry, fileVersion, themeDisplay, previewQueryString + "&type=" + PropsValues.DL_FILE_ENTRY_PREVIEW_VIDEO_CONTAINERS[i]);
									}
								}
								else {
									previewFileURLs = new String[1];

									previewFileURLs[0] = videoThumbnailURL;
								}
							}
							else {
								previewFileURLs = new String[1];

								previewFileURLs[0] = DLUtil.getPreviewURL(fileEntry, fileVersion, themeDisplay, previewQueryString);
							}

							previewFileURL = previewFileURLs[0];

							if (!hasPDFImages) {
								previewFileCount = 1;
							}
						}

						request.setAttribute("view_file_entry.jsp-supportedAudio", String.valueOf(hasAudio));
						request.setAttribute("view_file_entry.jsp-supportedVideo", String.valueOf(hasVideo));

						request.setAttribute("view_file_entry.jsp-previewFileURLs", previewFileURLs);
						request.setAttribute("view_file_entry.jsp-videoThumbnailURL", videoThumbnailURL);
						%>

						<c:choose>
							<c:when test="<%= previewFileCount == 0 %>">
								<c:if test="<%= AudioProcessorUtil.isAudioSupported(fileVersion) || ImageProcessorUtil.isImageSupported(fileVersion) || PDFProcessorUtil.isDocumentSupported(fileVersion) || VideoProcessorUtil.isVideoSupported(fileVersion) %>">
									<div class="portlet-msg-info">
										<liferay-ui:message key="generating-preview-will-take-a-few-minutes" />
									</div>
								</c:if>
							</c:when>
							<c:otherwise>
								<c:choose>
									<c:when test="<%= hasAudio %>">
										<div class="lfr-preview-audio" id="<portlet:namespace />previewFile">
											<div class="lfr-preview-audio-content" id="<portlet:namespace />previewFileContent"></div>
										</div>

										<liferay-util:include page="/html/portlet/document_library/player.jsp" />
									</c:when>
									<c:when test="<%= hasImages %>">
										<div class="lfr-preview-file lfr-preview-image" id="<portlet:namespace />previewFile">
											<div class="lfr-preview-file-content lfr-preview-image-content" id="<portlet:namespace />previewFileContent">
												<div class="lfr-preview-file-image-current-column">
													<div class="lfr-preview-file-image-container">
														<img class="lfr-preview-file-image-current" src="<%= previewFileURL %>" />
													</div>
												</div>
											</div>
										</div>
									</c:when>
									<c:when test="<%= hasVideo %>">
										<div class="lfr-preview-file lfr-preview-video" id="<portlet:namespace />previewFile">
											<div class="lfr-preview-file-content lfr-preview-video-content">
												<div class="lfr-preview-file-video-current-column">
													<div id="<portlet:namespace />previewFileContent"></div>
												</div>
											</div>
										</div>

										<liferay-util:include page="/html/portlet/document_library/player.jsp" />
									</c:when>
									<c:otherwise>
										<div class="lfr-preview-file" id="<portlet:namespace />previewFile">
											<div class="lfr-preview-file-content" id="<portlet:namespace />previewFileContent">
												<div class="lfr-preview-file-image-current-column">
													<div class="lfr-preview-file-image-container">
														<img class="lfr-preview-file-image-current" id="<portlet:namespace />previewFileImage" src="<%= previewFileURL + "1" %>" />
													</div>
													<span class="lfr-preview-file-actions aui-helper-hidden" id="<portlet:namespace />previewFileActions">
														<span class="lfr-preview-file-toolbar" id="<portlet:namespace />previewToolbar"></span>

														<span class="lfr-preview-file-info">
															<span class="lfr-preview-file-index" id="<portlet:namespace />previewFileIndex">1</span> of <span class="lfr-preview-file-count"><%= previewFileCount %></span>
														</span>
													</span>
												</div>

												<div class="lfr-preview-file-images" id="<portlet:namespace />previewImagesContent">
													<div class="lfr-preview-file-images-content"></div>
												</div>
											</div>
										</div>

										<aui:script use="aui-base,liferay-preview">
											new Liferay.Preview(
												{
													actionContent: '#<portlet:namespace />previewFileActions',
													baseImageURL: '<%= previewFileURL %>',
													boundingBox: '#<portlet:namespace />previewFile',
													contentBox: '#<portlet:namespace />previewFileContent',
													currentPreviewImage: '#<portlet:namespace />previewFileImage',
													imageListContent: '#<portlet:namespace />previewImagesContent',
													maxIndex: <%= previewFileCount %>,
													previewFileIndexNode: '#<portlet:namespace />previewFileIndex',
													toolbar: '#<portlet:namespace />previewToolbar'
												}
											).render();
										</aui:script>
									</c:otherwise>
								</c:choose>
							</c:otherwise>
						</c:choose>
					</div>
				</c:if>

				<c:if test="<%= PropsValues.DL_FILE_ENTRY_COMMENTS_ENABLED %>">
					<div class="lfr-document-library-versions">
						<div class="lfr-panel lfr-extended lfr-collapsed">
							<div class="lfr-panel-titlebar aui-helper-unselectable">
								<div class="lfr-panel-title">
									<liferay-ui:message key="version-history" />
								</div>
							</div>
						</div>

						<portlet:actionURL var="discussionURL">
							<portlet:param name="struts_action" value="/document_library/edit_file_entry_discussion" />
						</portlet:actionURL>

						<%
						FileVersion fileEntryFileVersion = fileEntry.getFileVersion();
						%>

						<c:choose>
							<c:when test="<%= fileVersionId == fileEntryFileVersion.getFileVersionId() %>">
								<div class="lfr-search-container">
									<liferay-portlet:renderURL varImpl="viewFileEntryURL">
										<portlet:param name="struts_action" value="/document_library/view_file_entry" />
										<portlet:param name="redirect" value="<%= redirect %>" />
										<portlet:param name="fileEntryId" value="<%= String.valueOf(fileEntry.getFileEntryId()) %>" />
									</liferay-portlet:renderURL>

									<liferay-ui:search-container
										delta="<%= 5 %>"
										deltaConfigurable="<%= false %>"
										iteratorURL="<%= viewFileEntryURL %>"
									>

										<%
										List<FileVersion> fileVersions = fileEntry.getFileVersions(WorkflowConstants.STATUS_APPROVED);
										%>

										<liferay-ui:search-container-results
											results="<%= ListUtil.subList(fileVersions, searchContainer.getStart(), searchContainer.getEnd()) %>"
											total="<%= fileVersions.size() %>"
										/>

										<c:if test="<%= fileVersions.size() > searchContainer.getDelta() %>">
											<div class="taglib-search-iterator-page-iterator-top">
												<liferay-ui:search-paginator searchContainer="<%= searchContainer %>" type="article" />
											</div>
										</c:if>

										<liferay-ui:search-container-row
											className="com.liferay.portal.kernel.repository.model.FileVersion"
											modelVar="curFileVersion"
										>
											<c:if test="<%= curFileVersion.isApproved() %>">
												<div class="version">
													<span class="version-number"><liferay-ui:message key="version" /> <%= curFileVersion.getVersion() %></span>
													<span class="user-name"><liferay-ui:message key="by" />: <%= HtmlUtil.escape(curFileVersion.getStatusByUserName()) %></span>

													<div class="extra">
														<span class="modified-date"><liferay-ui:message key="on" />: <%= dateFormatDateTime.format(curFileVersion.getCreateDate()) %></span>
														<span class="size"><liferay-ui:message key="size" />: <%= TextFormatter.formatKB(curFileVersion.getSize(), locale) + "k" %></span>
													</div>

													<c:if test="<%= Validator.isNotNull(curFileVersion.getChangeLog()) %>">
														<div class="changelog">
															<%= HtmlUtil.escape(curFileVersion.getChangeLog()) %>
														</div>
													</c:if>

													<div class="actions">
														<liferay-ui:icon
															image="download"
															label="<%= true %>"
															url="<%= DLUtil.getPreviewURL(fileEntry, curFileVersion, themeDisplay, StringPool.BLANK) %>"
														/>

														<portlet:renderURL var="viewFileVersionURL">
															<portlet:param name="struts_action" value="/document_library/view_file_entry" />
															<portlet:param name="redirect" value="<%= currentURL %>" />
															<portlet:param name="fileEntryId" value="<%= String.valueOf(fileEntry.getFileEntryId()) %>" />
															<portlet:param name="version" value="<%= curFileVersion.getVersion() %>" />
														</portlet:renderURL>

														<liferay-ui:icon
															image="view"
															label="<%= true %>"
															url="<%= viewFileVersionURL %>"
														/>

														<c:if test="<%= (!checkedOut || hasLock) && showActions && (index != 0) && (curFileVersion.getStatus() == WorkflowConstants.STATUS_APPROVED) && DLFileEntryPermission.contains(permissionChecker, fileEntry, ActionKeys.UPDATE) %>">
															<portlet:actionURL var="revertURL">
																<portlet:param name="struts_action" value="/document_library/edit_file_entry" />
																<portlet:param name="<%= Constants.CMD %>" value="<%= Constants.REVERT %>" />
																<portlet:param name="redirect" value="<%= currentURL %>" />
																<portlet:param name="fileEntryId" value="<%= String.valueOf(fileEntry.getFileEntryId()) %>" />
																<portlet:param name="version" value="<%= String.valueOf(curFileVersion.getVersion()) %>" />
															</portlet:actionURL>

															<liferay-ui:icon
																image="undo"
																label="<%= true %>"
																message="revert"
																url="<%= revertURL %>"
															/>
														</c:if>

														<c:if test="<%= (!checkedOut || hasLock) && DLFileEntryPermission.contains(permissionChecker, fileEntry, ActionKeys.DELETE) && (curFileVersion.getStatus() == WorkflowConstants.STATUS_APPROVED) && (fileEntry.getModel() instanceof DLFileEntry) && (((DLFileEntry)fileEntry.getModel()).getFileVersionsCount(WorkflowConstants.STATUS_APPROVED) > 1) %>">
															<portlet:actionURL var="deleteURL">
																<portlet:param name="struts_action" value="/document_library/edit_file_entry" />
																<portlet:param name="<%= Constants.CMD %>" value="<%= Constants.DELETE %>" />
																<portlet:param name="redirect" value="<%= currentURL %>" />
																<portlet:param name="fileEntryId" value="<%= String.valueOf(fileEntry.getFileEntryId()) %>" />
																<portlet:param name="version" value="<%= String.valueOf(curFileVersion.getVersion()) %>" />
															</portlet:actionURL>

															<liferay-ui:icon-delete
																label="<%= true %>"
																message="delete-version"
																url="<%= deleteURL %>"
															/>
														</c:if>
													</div>
												</div>

												<liferay-ui:panel collapsible="<%= true %>" cssClass="lfr-document-library-comments" extended="<%= true %>" id='<%= "documentLibraryCommentsPanel" + curFileVersion.getFileVersionId() %>' persistState="<%= false %>" title="comments">
													<liferay-ui:discussion
														className="<%= DLFileVersion.class.getName() %>"
														classPK="<%= curFileVersion.getFileVersionId() %>"
														formAction="<%= discussionURL %>"
														formName='<%= "fm2" + curFileVersion.getFileVersionId() %>'
														ratingsEnabled="<%= enableCommentRatings %>"
														redirect="<%= currentURL %>"
														subject="<%= title %>"
														userId="<%= curFileVersion.getUserId() %>"
													/>
												</liferay-ui:panel>
											</c:if>
										</liferay-ui:search-container-row>

										<c:if test="<%= fileVersions.size() > searchContainer.getDelta() %>">
											<div class="taglib-search-iterator-page-iterator-bottom">
												<liferay-ui:search-paginator searchContainer="<%= searchContainer %>" type="article" />
											</div>
										</c:if>
									</liferay-ui:search-container>
								</div>

								<c:if test="<%= MBMessageLocalServiceUtil.getDiscussionMessagesCount(DLFileEntryConstants.getClassName(), fileEntryId, WorkflowConstants.STATUS_ANY) != 0 %>">
									<liferay-ui:panel collapsible="<%= true %>" cssClass="lfr-document-library-general-comments" extended="<%= true %>" id='<%= "documentLibraryCommentsPanel" + fileEntryId %>' persistState="<%= false %>" title="previous-comments">
										<div class="portlet-msg-info">
											<liferay-ui:message key="the-following-comments-were-added-prior-to-upgrading-to-social-office-ee" />
										</div>

										<liferay-ui:discussion
											className="<%= DLFileEntryConstants.getClassName() %>"
											classPK="<%= fileEntryId %>"
											formAction="<%= discussionURL %>"
											formName='<%= "fm2" + fileEntryId %>'
											hideControls="<%= true %>"
											ratingsEnabled="<%= enableCommentRatings %>"
											redirect="<%= currentURL %>"
											subject="<%= title %>"
											userId="<%= fileEntry.getUserId() %>"
										/>
									</liferay-ui:panel>
								</c:if>
							</c:when>
							<c:otherwise>
								<div class="version">
									<span class="version-number"><liferay-ui:message key="version" /> <%= fileVersion.getVersion() %></span>
									<span class="user-name"><liferay-ui:message key="by" />: <%= HtmlUtil.escape(fileVersion.getStatusByUserName()) %></span>

									<div class="extra">
										<span class="modified-date"><liferay-ui:message key="on" />: <%= dateFormatDateTime.format(fileVersion.getCreateDate()) %></span>
										<span class="size"><liferay-ui:message key="size" />: <%= TextFormatter.formatKB(fileVersion.getSize(), locale) + "k" %></span>
									</div>

									<div class="changelog">
										<span><%= fileVersion.getChangeLog() %></span>
									</div>

									<liferay-util:buffer var="html">
										<portlet:renderURL var="viewFileEntryURL">
											<portlet:param name="struts_action" value="/document_library/view_file_entry" />
											<portlet:param name="redirect" value="<%= redirect %>" />
											<portlet:param name="fileEntryId" value="<%= String.valueOf(fileEntry.getFileEntryId()) %>" />
										</portlet:renderURL>

										<c:if test="<%= (!checkedOut || hasLock) && (fileVersion.getStatus() == WorkflowConstants.STATUS_APPROVED) && DLFileEntryPermission.contains(permissionChecker, fileEntry, ActionKeys.UPDATE) %>">
											<portlet:actionURL var="revertURL">
												<portlet:param name="struts_action" value="/document_library/edit_file_entry" />
												<portlet:param name="<%= Constants.CMD %>" value="<%= Constants.REVERT %>" />
												<portlet:param name="redirect" value="<%= viewFileEntryURL %>" />
												<portlet:param name="fileEntryId" value="<%= String.valueOf(fileEntry.getFileEntryId()) %>" />
												<portlet:param name="version" value="<%= String.valueOf(fileVersion.getVersion()) %>" />
											</portlet:actionURL>

											<liferay-ui:icon
												image="undo"
												label="<%= true %>"
												message="revert"
												url="<%= revertURL %>"
											/>
										</c:if>

										<c:if test="<%= (!checkedOut || hasLock) && DLFileEntryPermission.contains(permissionChecker, fileEntry, ActionKeys.DELETE) && (fileVersion.getStatus() == WorkflowConstants.STATUS_APPROVED) && (fileEntry.getModel() instanceof DLFileEntry) && (((DLFileEntry)fileEntry.getModel()).getFileVersionsCount(WorkflowConstants.STATUS_APPROVED) > 1) %>">
											<portlet:actionURL var="deleteURL">
												<portlet:param name="struts_action" value="/document_library/edit_file_entry" />
												<portlet:param name="<%= Constants.CMD %>" value="<%= Constants.DELETE %>" />
												<portlet:param name="redirect" value="<%= viewFileEntryURL %>" />
												<portlet:param name="fileEntryId" value="<%= String.valueOf(fileEntry.getFileEntryId()) %>" />
												<portlet:param name="version" value="<%= String.valueOf(fileVersion.getVersion()) %>" />
											</portlet:actionURL>

											<liferay-ui:icon-delete
												label="<%= true %>"
												message="delete-version"
												url="<%= deleteURL %>"
											/>
										</c:if>
									</liferay-util:buffer>

									<c:if test="<%= Validator.isNotNull(html.trim()) %>">
										<div class="actions">
											<%= html %>
										</div>
									</c:if>
								</div>

								<liferay-ui:discussion
									className="<%= DLFileVersion.class.getName() %>"
									classPK="<%= fileVersion.getFileVersionId() %>"
									formAction="<%= discussionURL %>"
									formName='<%= "fm2" + fileVersion.getFileVersionId() %>'
									ratingsEnabled="<%= enableCommentRatings %>"
									redirect="<%= currentURL %>"
									subject="<%= title %>"
									userId="<%= fileVersion.getUserId() %>"
								/>
							</c:otherwise>
						</c:choose>
					</div>
				</c:if>
			</div>
		</aui:column>

		<aui:column columnWidth="<%= 35 %>" cssClass="lfr-asset-column-details context-pane" last="<%= true %>">
			<div class="body-row asset-details">
				<div class="asset-details-content">
					<liferay-ui:header
						backURL="<%= redirect %>"
						localizeTitle="<%= false %>"
						title="<%= fileEntry.getTitle() %>"
					/>

					<h3 class="version <%= checkedOut ? "document-locked" : StringPool.BLANK %>">
						<liferay-ui:message key="version" /> <%= HtmlUtil.escape(fileVersion.getVersion()) %>
					</h3>

					<div class="lfr-asset-icon lfr-asset-author">
						<liferay-ui:message arguments="<%= HtmlUtil.escape(fileVersion.getStatusByUserName()) %>" key="last-updated-by-x" />
					</div>

					<div class="lfr-asset-icon lfr-asset-date">
						<%= dateFormatDateTime.format(fileVersion.getModifiedDate()) %>
					</div>

					<div class="lfr-asset-summary">
						<aui:workflow-status model="<%= DLFileEntry.class %>" status="<%= fileVersion.getStatus() %>" />
					</div>

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
								message='<%= LanguageUtil.get(pageContext, "download") + " (" + TextFormatter.formatKB(fileVersion.getSize(), locale) + "k)" %>'
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
								message="<%= conversion.toUpperCase() %>"
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

					<div class="lfr-asset-field url-file-container aui-helper-hidden">
						<label><liferay-ui:message key="url" /></label>

						<liferay-ui:input-resource
							url="<%= DLUtil.getPreviewURL(fileEntry, fileEntry.getFileVersion(), themeDisplay, StringPool.BLANK, false, true) %>"
						/>
					</div>

					<c:if test="<%= portletDisplay.isWebDAVEnabled() && fileEntry.isSupportsSocial() %>">
						<div class="lfr-asset-field webdav-url-file-container aui-helper-hidden">

							<%
							String webDavHelpMessage = null;

							if (BrowserSnifferUtil.isWindows(request)) {
								webDavHelpMessage = LanguageUtil.format(pageContext, "webdav-windows-help", new Object[] {"http://www.microsoft.com/downloads/details.aspx?FamilyId=17C36612-632E-4C04-9382-987622ED1D64", "http://www.liferay.com/web/guest/community/wiki/-/wiki/Main/WebDAV"});
							}
							else {
								webDavHelpMessage = LanguageUtil.format(pageContext, "webdav-help", "http://www.liferay.com/web/guest/community/wiki/-/wiki/Main/WebDAV");
							}
							%>

							<aui:field-wrapper helpMessage="<%= webDavHelpMessage %>" label="webdav-url">
								<liferay-ui:input-resource url="<%= webDavUrl %>" />
							</aui:field-wrapper>
						</div>
					</c:if>
				</div>

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

									<liferay-ui:panel collapsible="<%= true %>" cssClass="metadata" extended="<%= true %>" id="documentLibraryMetadataPanel" persistState="<%= true %>" title="<%= HtmlUtil.escape(ddmStructure.getName(LocaleUtil.getDefault())) %>">

										<%= DDMXSDUtil.getHTML(pageContext, ddmStructure.getXsd(), fields, String.valueOf(ddmStructure.getPrimaryKey()), true, locale) %>

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
							List<DDMStructure> ddmStructures = DDMStructureLocalServiceUtil.getClassStructures(PortalUtil.getClassNameId(DLFileEntry.class), new StructureStructureKeyComparator(true));

							for (DDMStructure ddmStructure : ddmStructures) {
								Fields fields = null;

								try {
									DLFileEntryMetadata fileEntryMetadata = DLFileEntryMetadataLocalServiceUtil.getFileEntryMetadata(ddmStructure.getStructureId(), fileVersionId);

									fields = StorageEngineUtil.getFields(fileEntryMetadata.getDDMStorageId());
								}
								catch (Exception e) {
								}

								if (fields != null) {
									String name = "metadata." + ddmStructure.getName(LocaleUtil.getDefault(), true);
						%>

									<liferay-ui:panel collapsible="<%= true %>" cssClass="lfr-asset-metadata" id="documentLibraryAssetMetadataPanel" persistState="<%= true %>" title="<%= name %>">

										<%= DDMXSDUtil.getHTML(pageContext, ddmStructure.getXsd(), fields, String.valueOf(ddmStructure.getPrimaryKey()), true, locale) %>

									</liferay-ui:panel>

						<%
								}
							}
						}
						catch (Exception e) {
						}
						%>

					</liferay-ui:panel-container>
				</div>
			</div>
		</aui:column>
	</aui:layout>
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

<aui:script use="aui-base,aui-dialog,aui-io-request,aui-toolbar">
	var showURLFile = A.one('.show-url-file');
	var showWebDavFile = A.one('.show-webdav-url-file');

	if (showURLFile) {
		showURLFile.on(
			'click',
			function(event) {
				var URLFileContainer = A.one('.url-file-container');

				URLFileContainer.toggleClass('aui-helper-hidden');
			}
		);
	}

	if (showWebDavFile) {
		showWebDavFile.on(
			'click',
			function(event) {
				var WebDavFileContainer = A.one('.webdav-url-file-container');

				WebDavFileContainer.toggleClass('aui-helper-hidden');
			}
		);
	}

	var buttonRow = A.one('#<portlet:namespace />fileEntryToolbar');

	var fileEntryToolbarChildren = [];

	<c:if test="<%= DLFileEntryPermission.contains(permissionChecker, fileEntry, ActionKeys.VIEW) %>">
		fileEntryToolbarChildren.push(
			{
				handler: function(event) {
					location.href = '<%= DLUtil.getPreviewURL(fileEntry, fileVersion, themeDisplay, StringPool.BLANK) %>';
				},
				icon: 'download',
				label: '<%= UnicodeLanguageUtil.get(pageContext, "download") %>'
			}
		);
	</c:if>

	<c:if test="<%= DLFileEntryPermission.contains(permissionChecker, fileEntry, ActionKeys.UPDATE) %>">
		<c:if test="<%= !checkedOut %>">
			fileEntryToolbarChildren.push(
				{

					handler: function(event) {
						document.<portlet:namespace />fm.<portlet:namespace /><%= Constants.CMD %>.value = '<%= Constants.CHECKOUT %>';
						submitForm(document.<portlet:namespace />fm);
					},
					icon: 'lock',
					label: '<%= UnicodeLanguageUtil.get(pageContext, "checkout[document]") %>'
				}
			);
		</c:if>

		<portlet:actionURL var="checkOutURL">
			<portlet:param name="struts_action" value="/document_library/edit_file_entry" />
			<portlet:param name="<%= Constants.CMD %>" value="<%= Constants.CHECKOUT %>" />
			<portlet:param name="redirect" value="<%= currentURL %>" />
			<portlet:param name="fileEntryId" value="<%= String.valueOf(fileEntry.getFileEntryId()) %>" />
		</portlet:actionURL>

		<c:if test="<%= !checkedOut || hasLock %>">
			fileEntryToolbarChildren.push(
				{
					<portlet:renderURL var="editURL">
						<portlet:param name="struts_action" value="/document_library/edit_file_entry" />
						<portlet:param name="redirect" value="<%= currentURL %>" />
						<portlet:param name="fileEntryId" value="<%= String.valueOf(fileEntry.getFileEntryId()) %>" />
					</portlet:renderURL>

					handler: function(event) {
						location.href = '<%= editURL %>';
					},
					icon: 'edit',
					label: '<%= UnicodeLanguageUtil.get(pageContext, "edit") %>'
				}
			);
		</c:if>

		<%
		String curExtension = fileEntry.getExtension();
		%>

		<c:if test='<%= (!checkedOut || hasLock) && portletDisplay.isWebDAVEnabled() && BrowserSnifferUtil.isIe(request) && (curExtension.equalsIgnoreCase("doc") || curExtension.equalsIgnoreCase("docx") || curExtension.equalsIgnoreCase("dot") || curExtension.equalsIgnoreCase("ppt") || curExtension.equalsIgnoreCase("pptx") || curExtension.equalsIgnoreCase("xls") || curExtension.equalsIgnoreCase("xlsx")) %>'>
			fileEntryToolbarChildren.push(
				{
					handler: function(event) {
						var UA = A.UA;
						var WIN = A.config.win;

						<%

						// The following logic is copied from DLUtil#getWebDavURL(ThemeDisplay, Folder, FileEntry, boolean)

						StringBuilder sb = new StringBuilder();

						if (folder != null) {
							Folder curFolder = folder;

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

						if (fileEntry != null) {
							sb.append(StringPool.SLASH);
							sb.append(HttpUtil.encodeURL(fileEntry.getTitle(), true));
						}

						Group group = themeDisplay.getScopeGroup();

						StringBundler manualCheckInRequiredWebDavURL = new StringBundler(7);

						manualCheckInRequiredWebDavURL.append(themeDisplay.getPortalURL());
						manualCheckInRequiredWebDavURL.append(themeDisplay.getPathContext());
						manualCheckInRequiredWebDavURL.append("/api/secure/webdav");
						manualCheckInRequiredWebDavURL.append("/manualCheckInRequired");
						manualCheckInRequiredWebDavURL.append(group.getFriendlyURL());
						manualCheckInRequiredWebDavURL.append("/document_library");
						manualCheckInRequiredWebDavURL.append(sb.toString());
						%>

						var webDavUrl = '<%= manualCheckInRequiredWebDavURL.toString() %>';

						var checkOutURL = '<%= checkOutURL.toString() %>';

						if (webDavUrl && UA.ie) {
							try {
								var executor = new WIN.ActiveXObject('SharePoint.OpenDocuments');

								executor.EditDocument(webDavUrl);

								A.io.request(
									checkOutURL,
									{
										after: {
											success: function(event, id, obj) {
												location.href = "<%= currentURL %>";
											}
										},
										method: 'POST'
									}
								);
							}
							catch (exception) {
								var errorMessage = A.Lang.sub(
									Liferay.Language.get('cannot-open-the-requested-document-due-to-the-following-reason'),
									[exception.message]
								);

								var documentContainer = A.one('.lfr-asset-column-details');

								var existingMessage = documentContainer.one('.portlet-msg-error');

								if (existingMessage) {
									existingMessage.setContent(errorMessage);
								}
								else {
									var output = A.Node.create('<div class="lfr-message-response portlet-msg-error" />');

									output.html(errorMessage);

									documentContainer.prepend(output);
								}
							}
						}
					},
					icon: 'edit',
					label: '<%= UnicodeLanguageUtil.get(pageContext, "edit-document-online") %>'
				}
			);
		</c:if>

		<c:if test="<%= hasLock || (permissionChecker.isGroupAdmin(fileEntry.getRepositoryId()) && checkedOut) %>">
			fileEntryToolbarChildren.push(
				{
					<portlet:renderURL var="checkinURL">
						<portlet:param name="struts_action" value="/document_library/edit_file_entry" />
						<portlet:param name="redirect" value="<%= currentURL %>" />
						<portlet:param name="displaySection" value="checkin" />
						<portlet:param name="fileEntryId" value="<%= String.valueOf(fileEntry.getFileEntryId()) %>" />
					</portlet:renderURL>

					handler: function(event) {
						location.href = '<%= checkinURL %>';
					},
					icon: 'unlock',
					label: '<%= UnicodeLanguageUtil.get(pageContext, "checkin") %>'
				},
				{

					handler: function(event) {
						document.<portlet:namespace />fm.<portlet:namespace /><%= Constants.CMD %>.value = '<%= Constants.CANCEL_CHECKOUT %>';
						submitForm(document.<portlet:namespace />fm);
					},
					icon: 'undo',
					label: '<%= UnicodeLanguageUtil.get(pageContext, "cancel-checkout[document]") %>'
				}
			);
		</c:if>

		<c:if test="<%= !checkedOut || hasLock %>">
			fileEntryToolbarChildren.push(
				{
					<portlet:renderURL var="moveURL">
						<portlet:param name="struts_action" value="/document_library/move_file_entry" />
						<portlet:param name="redirect" value="<%= currentURL %>" />
						<portlet:param name="fileEntryId" value="<%= String.valueOf(fileEntry.getFileEntryId()) %>" />
					</portlet:renderURL>

					handler: function(event) {
						location.href = '<%= moveURL %>';
					},
					icon: 'move',
					label: '<%= UnicodeLanguageUtil.get(pageContext, "move") %>'
				}
			);
		</c:if>
	</c:if>

	<c:if test="<%= DLFileEntryPermission.contains(permissionChecker, fileEntry, ActionKeys.PERMISSIONS) %>">
		fileEntryToolbarChildren.push(
			{
				<liferay-security:permissionsURL
					modelResource="<%= DLFileEntryConstants.getClassName() %>"
					modelResourceDescription="<%= fileEntry.getTitle() %>"
					resourcePrimKey="<%= String.valueOf(fileEntry.getFileEntryId()) %>"
					var="permissionsURL"
					windowState="<%= LiferayWindowState.POP_UP.toString() %>"
				/>

				handler: function(event) {
					Liferay.Util.openWindow(
						{
							dialog: {
								align: {
									node: null,
									points: ['tc', 'tc']
								},
								constrain2view: true,
								modal: true,
								resizable: false,
								width: 960
							},
							id: '<portlet:namespace />permissions',
							title: '<%= UnicodeLanguageUtil.get(pageContext, "permissions") %>',
							uri:'<%= permissionsURL.toString() %>'
						}
					);
				},
				icon: 'permissions',
				label: '<%= UnicodeLanguageUtil.get(pageContext, "permissions") %>'
			}
		);
	</c:if>

	<c:if test="<%= (!checkedOut || hasLock) && DLFileEntryPermission.contains(permissionChecker, fileEntry, ActionKeys.DELETE) %>">
		fileEntryToolbarChildren.push(
			{
				<portlet:renderURL var="viewFolderURL">
					<portlet:param name="struts_action" value="/document_library/view" />
					<portlet:param name="folderId" value="<%= String.valueOf(fileEntry.getFolderId()) %>" />
				</portlet:renderURL>

				handler: function(event) {
					if (confirm('<%= UnicodeLanguageUtil.get(pageContext, "are-you-sure-you-want-to-delete-this-entry") %>')) {
						document.<portlet:namespace />fm.<portlet:namespace /><%= Constants.CMD %>.value = '<%= Constants.DELETE %>';
						document.<portlet:namespace />fm.<portlet:namespace />redirect.value = '<%= viewFolderURL.toString() %>';
						submitForm(document.<portlet:namespace />fm);
					}
				},
				icon: 'delete',
				label: '<%= UnicodeLanguageUtil.get(pageContext, "delete") %>'
			}
		);
	</c:if>

	var fileEntryToolbar = new A.Toolbar(
		{
			activeState: false,
			boundingBox: buttonRow,
			children: fileEntryToolbarChildren
		}
	).render();

	buttonRow.setData('fileEntryToolbar', fileEntryToolbar);

	<portlet:namespace />initRowsChecked();

	A.all('input[name=<portlet:namespace />rowIds]').on(
		'click',
		function(event) {
			<portlet:namespace />updateRowsChecked(event.currentTarget);
		}
	);
</aui:script>

<%
DLUtil.addPortletBreadcrumbEntries(fileEntry, request, renderResponse);
%>