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

<div class="sidebar">
	<div class="search">

		<%
		String redirect = ParamUtil.getString(request, "redirect");

		long breadcrumbsFolderId = ParamUtil.getLong(request, "breadcrumbsFolderId");

		long searchFolderId = ParamUtil.getLong(request, "searchFolderId");
		long searchFolderIds = ParamUtil.getLong(request, "searchFolderIds");

		long[] folderIdsArray = null;

		if (searchFolderId > 0) {
			folderIdsArray = new long[] {searchFolderId};
		}
		else {
			List folderIds = new ArrayList();

			folderIds.add(new Long(searchFolderIds));

			DLFolderLocalServiceUtil.getSubfolderIds(folderIds, scopeGroupId, searchFolderIds);

			folderIdsArray = StringUtil.split(StringUtil.merge(folderIds), 0L);
		}

		String keywords = ParamUtil.getString(request, "keywords");
		%>

		<liferay-portlet:renderURL windowState="<%= WindowState.MAXIMIZED.toString() %>" varImpl="sideSearchURL"><portlet:param name="struts_action" value="/document_library/search" /></liferay-portlet:renderURL>

		<form action="<%= sideSearchURL %>" method="get" name="<portlet:namespace />sidebarFm" onSubmit="submitForm(this); return false;">
		<liferay-portlet:renderURLParams varImpl="sideSearchURL" />
		<input name="<portlet:namespace />redirect" type="hidden" value="<%= HtmlUtil.escape(redirect) %>" />
		<input name="<portlet:namespace />breadcrumbsFolderId" type="hidden" value="<%= breadcrumbsFolderId %>" />
		<input name="<portlet:namespace />searchFolderId" type="hidden" value="<%= searchFolderId %>" />
		<input name="<portlet:namespace />searchFolderIds" type="hidden" value="<%= searchFolderIds %>" />

		<div class="search">
			<input class="search-input" id="<portlet:namespace />keywords1" name="<portlet:namespace />keywords" type="text" />

			<input class="search-button" type="submit" value="<liferay-ui:message key="search" />" />
		</div>

		</form>
	</div>

	<h2><liferay-ui:message key="quick-links" /></h2>

	<%
	String tabs1 = ParamUtil.getString(request, "tabs1", "document-home");

	PortletURL tabs1URL = renderResponse.createRenderURL();

	tabs1URL.setParameter("tabs1", "document-home");
	%>

	<ul class="disc">
		<li>
			<a href="<%= tabs1URL %>"><liferay-ui:message key="documents-home" /></a>
		</li>

		<%
		tabs1URL.setParameter("tabs1", "recent-documents");
		%>

		<li>
			<a href="<%= tabs1URL %>"><liferay-ui:message key="recent-documents" /></a>
		</li>

		<c:if test="<%= themeDisplay.isSignedIn() %>">

			<%
			tabs1URL.setParameter("tabs1", "my-documents");
			%>

			<li>
				<a href="<%= tabs1URL %>"><liferay-ui:message key="my-documents" /></a>
			</li>
		</c:if>

		<c:if test="<%= GroupPermissionUtil.contains(permissionChecker, scopeGroupId, ActionKeys.PERMISSIONS) %>">
			<liferay-security:permissionsURL
				modelResource="com.liferay.portlet.documentlibrary"
				modelResourceDescription="<%= portletDisplay.getTitle() %>"
				resourcePrimKey="<%= String.valueOf(scopeGroupId) %>"
				var="permissionsURL"
			/>

			<li>
				<a href="<%= permissionsURL %>"><liferay-ui:message key="permissions" /></a>
			</li>
		</c:if>

	</ul>

	<%
	int total = DLFileEntryLocalServiceUtil.getGroupFileEntriesCount(portletGroupId, 0);

	if (total > 0) {
		List<DLFileEntry> results = DLFileEntryLocalServiceUtil.getGroupFileEntries(portletGroupId, 0, 0, 5);
	%>

		<h2>
			<liferay-ui:message key="recent-documents" />
		</h2>

		<%@ include file="/html/portlet/document_library/sidebar_file_entries.jspf" %>

	<%
	}

	total = DLFileEntryLocalServiceUtil.getGroupFileEntriesCount(portletGroupId, user.getUserId());

	if (total > 0) {
		List<DLFileEntry> results = DLFileEntryLocalServiceUtil.getGroupFileEntries(portletGroupId, user.getUserId(), 0, 5);
	%>

		<h2>
			<liferay-ui:message key="my-documents" />
		</h2>

		<%@ include file="/html/portlet/document_library/sidebar_file_entries.jspf" %>

	<%
	}
	%>

</div>