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
String tabs1 = ParamUtil.getString(request, "tabs1", "document-home");

DLFolder folder = (DLFolder)request.getAttribute(WebKeys.DOCUMENT_LIBRARY_FOLDER);

long defaultFolderId = GetterUtil.getLong(preferences.getValue("rootFolderId", StringPool.BLANK), DLFolderConstants.DEFAULT_PARENT_FOLDER_ID);

long folderId = BeanParamUtil.getLong(folder, request, "folderId", defaultFolderId);

if ((folder == null) && (defaultFolderId != DLFolderConstants.DEFAULT_PARENT_FOLDER_ID)) {
	try {
		folder = DLFolderLocalServiceUtil.getFolder(folderId);
	}
	catch (NoSuchFolderException nsfe) {
		folderId = DLFolderConstants.DEFAULT_PARENT_FOLDER_ID;
	}
}

int status = WorkflowConstants.STATUS_APPROVED;

if (permissionChecker.isCompanyAdmin() || permissionChecker.isCommunityAdmin(scopeGroupId)) {
	status = WorkflowConstants.STATUS_ANY;
}

int foldersCount = DLFolderLocalServiceUtil.getFoldersCount(scopeGroupId, folderId);
int fileEntriesCount = DLFolderLocalServiceUtil.getFileEntriesAndFileShortcutsCount(scopeGroupId, folderId, status);

long categoryId = ParamUtil.getLong(request, "categoryId");
String tagName = ParamUtil.getString(request, "tag");

String categoryName = null;
String vocabularyName = null;

if (categoryId != 0) {
	AssetCategory assetCategory = AssetCategoryLocalServiceUtil.getAssetCategory(categoryId);

	categoryName = assetCategory.getName();

	AssetVocabulary assetVocabulary = AssetVocabularyLocalServiceUtil.getAssetVocabulary(assetCategory.getVocabularyId());

	vocabularyName = assetVocabulary.getName();
}

boolean useAssetEntryQuery = (categoryId > 0) || Validator.isNotNull(tagName);

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("struts_action", "/document_library/view");
portletURL.setParameter("tabs1", tabs1);
portletURL.setParameter("folderId", String.valueOf(folderId));

request.setAttribute("view.jsp-folder", folder);

request.setAttribute("view.jsp-folderId", String.valueOf(folderId));

request.setAttribute("view.jsp-viewFolder", Boolean.TRUE.toString());

request.setAttribute("view.jsp-useAssetEntryQuery", String.valueOf(useAssetEntryQuery));
%>

<liferay-util:include page="/html/portlet/document_library/sidebar.jsp" />

<c:choose>
	<c:when test='<%= tabs1.equals("document-home") %>'>
		<aui:layout>
			<div class="breadcrumb">
				<c:choose>
					<c:when test="<%= folder != null %>">
						<%= getFolderBreadcrumbs(folder.getParentFolderId(), pageContext, renderResponse) %>

						<h6 class="folder-title"><%= folder.getName() %></h6>
					</c:when>
					<c:otherwise>
						<h6 class="no-folder-title"><liferay-ui:message key="documents-home" /></h6>
					</c:otherwise>
				</c:choose>
			</div>

			<aui:column columnWidth="100" cssClass="lfr-asset-column lfr-asset-column-details" first="<%= true %>">
				<liferay-ui:panel-container extended="<%= false %>" id="documentLibraryPanelContainer" persistState="<%= true %>">
					<c:if test="<%= showSubfolders %>">
						<c:if test="<%= folder != null %>">
							<c:if test="<%= Validator.isNotNull(folder.getDescription()) %>">
								<div class="lfr-asset-description">
									<%= folder.getDescription() %>
								</div>
							</c:if>

							<div class="lfr-asset-metadata">
								<div class="lfr-asset-icon lfr-asset-date">
									<%= LanguageUtil.format(pageContext, "last-updated-x", dateFormatDateTime.format(folder.getModifiedDate())) %>
								</div>

								<div class="lfr-asset-icon lfr-asset-subfolders">
									<%= foldersCount %> <liferay-ui:message key='<%= (foldersCount == 1) ? "subfolder" : "subfolders" %>' />
								</div>

								<div class="lfr-asset-icon lfr-asset-items last">
									<%= fileEntriesCount %> <liferay-ui:message key='<%= (fileEntriesCount == 1) ? "document" : "documents" %>' />
								</div>
							</div>

							<div class="custom-attributes">
								<liferay-ui:custom-attributes-available className="<%= DLFolder.class.getName() %>">
									<liferay-ui:custom-attribute-list
										className="<%= DLFolder.class.getName() %>"
										classPK="<%= (folder != null) ? folder.getFolderId() : 0 %>"
										editable="<%= false %>"
										label="<%= true %>"
									/>
								</liferay-ui:custom-attributes-available>
							</div>
						</c:if>

						<%
						request.removeAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);
						%>

						<liferay-util:include page="/html/portlet/document_library/folder_action.jsp" />

						<%@ include file="/html/portlet/document_library/view_file_entries.jspf" %>
					</c:if>
				</liferay-ui:panel-container>
			</aui:column>
		</aui:layout>

		<%
		if (folder != null) {
			DLUtil.addPortletBreadcrumbEntries(folder, request, renderResponse);

			if (portletName.equals(PortletKeys.DOCUMENT_LIBRARY)) {
				PortalUtil.setPageSubtitle(folder.getName(), request);
				PortalUtil.setPageDescription(folder.getDescription(), request);
			}
		}
		%>

	</c:when>
	<c:when test='<%= tabs1.equals("my-documents") || tabs1.equals("recent-documents") %>'>
		<aui:layout>
			<h6><liferay-ui:message key="<%= tabs1 %>" /></h6>

			<%
			long groupFileEntriesUserId = 0;

			if (tabs1.equals("my-documents") && themeDisplay.isSignedIn()) {
				groupFileEntriesUserId = user.getUserId();
			}
			%>

			<liferay-ui:search-container
				delta="<%= fileEntriesPerPage %>"
				emptyResultsMessage="there-are-no-documents"
				iteratorURL="<%= portletURL %>"
			>
				<liferay-ui:search-container-results
					results="<%= DLFileEntryLocalServiceUtil.getGroupFileEntries(scopeGroupId, groupFileEntriesUserId, searchContainer.getStart(), searchContainer.getEnd()) %>"
					total="<%= DLFileEntryLocalServiceUtil.getGroupFileEntriesCount(scopeGroupId, groupFileEntriesUserId) %>"
				/>

				<liferay-ui:search-container-row
					className="com.liferay.portlet.documentlibrary.model.DLFileEntry"
					escapedModel="<%= true %>"
					keyProperty="fileEntryId"
					modelVar="fileEntry"
				>

					<%
					String rowHREF = null;

					if (DLFileEntryPermission.contains(permissionChecker, fileEntry, ActionKeys.VIEW)) {
						rowHREF = themeDisplay.getPortalURL() + themeDisplay.getPathContext() + "/documents/" + themeDisplay.getScopeGroupId() + StringPool.SLASH + fileEntry.getFolderId() + StringPool.SLASH + HttpUtil.encodeURL(HtmlUtil.unescape(fileEntry.getTitle()));
					}
					%>

					<%@ include file="/html/portlet/document_library/file_entry_columns.jspf" %>
				</liferay-ui:search-container-row>

				<liferay-ui:search-iterator />
			</liferay-ui:search-container>
		</aui:layout>

		<%
		PortalUtil.addPortletBreadcrumbEntry(request, LanguageUtil.get(pageContext, tabs1), currentURL);

		PortalUtil.setPageSubtitle(LanguageUtil.get(pageContext, tabs1), request);
		%>

	</c:when>
</c:choose>