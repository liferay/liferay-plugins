<%--
/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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

<%@ include file="/html/portlet/bookmarks/init.jsp" %>

<%
String topLink = ParamUtil.getString(request, "topLink", "bookmarks-home");

BookmarksFolder folder = (BookmarksFolder)request.getAttribute(WebKeys.BOOKMARKS_FOLDER);

long defaultFolderId = GetterUtil.getLong(preferences.getValue("rootFolderId", StringPool.BLANK), BookmarksFolderConstants.DEFAULT_PARENT_FOLDER_ID);

long folderId = BeanParamUtil.getLong(folder, request, "folderId", defaultFolderId);

if ((folder == null) && (defaultFolderId != BookmarksFolderConstants.DEFAULT_PARENT_FOLDER_ID)) {
	try {
		folder = BookmarksFolderServiceUtil.getFolder(folderId);
	}
	catch (NoSuchFolderException nsfe) {
		folderId = BookmarksFolderConstants.DEFAULT_PARENT_FOLDER_ID;
	}
}

int foldersCount = BookmarksFolderServiceUtil.getFoldersCount(scopeGroupId, folderId);
int entriesCount = BookmarksEntryServiceUtil.getEntriesCount(scopeGroupId, folderId);

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setWindowState(WindowState.MAXIMIZED);

portletURL.setParameter("struts_action", "/bookmarks/view");
portletURL.setParameter("topLink", topLink);
portletURL.setParameter("folderId", String.valueOf(folderId));

request.setAttribute("view.jsp-folder", folder);

request.setAttribute("view.jsp-folderId", String.valueOf(folderId));

request.setAttribute("view.jsp-viewFolder", Boolean.TRUE.toString());
%>

<liferay-portlet:renderURL varImpl="searchURL" windowState="<%= WindowState.MAXIMIZED.toString() %>">
	<portlet:param name="struts_action" value="/bookmarks/search" />
</liferay-portlet:renderURL>

<liferay-portlet:renderURLParams varImpl="searchURL" />

<form action="<%= searchURL %>" method="get" name="<portlet:namespace />fm2" onSubmit="submitForm(this); return false;">
<input name="<portlet:namespace />redirect" type="hidden" value="<%= HtmlUtil.escapeAttribute(currentURL) %>" />
<input name="<portlet:namespace />breadcrumbsFolderId" type="hidden" value="<%= folderId %>" />
<input name="<portlet:namespace />searchFolderId" type="hidden" value="<%= folderId %>" />

<liferay-ui:search-container
	curParam="cur1"
	iteratorURL="<%= portletURL %>"
>

	<%
	OrderByComparator orderByComparator = BookmarksUtil.getEntriesOrderByComparator("name", "asc");
	%>

	<liferay-ui:search-container-results
		results="<%= BookmarksEntryServiceUtil.getEntries(scopeGroupId, folderId, searchContainer.getStart(), searchContainer.getEnd(), orderByComparator) %>"
		total="<%= BookmarksEntryServiceUtil.getEntriesCount(scopeGroupId, folderId) %>"
	/>

	<liferay-ui:search-container-row
		className="com.liferay.portlet.bookmarks.model.BookmarksEntry"
		escapedModel="<%= true %>"
		keyProperty="entryId"
		modelVar="entry"
	>

		<%
		String rowHREF = null;

		if (BookmarksEntryPermission.contains(permissionChecker, entry, ActionKeys.VIEW)) {
			StringBuilder sb = new StringBuilder();

			sb.append(themeDisplay.getPathMain());
			sb.append("/bookmarks/open_entry?entryId=");
			sb.append(entry.getEntryId());

			rowHREF = sb.toString();
		}
		%>

		<liferay-ui:search-container-column-text
			href="<%= rowHREF %>"
			orderable="<%= true %>"
			orderableProperty="name"
			property="name"
			target="_blank"
			title="<%= entry.getDescription() %>"
		/>

		<liferay-ui:search-container-column-jsp
			align="right"
			path="/html/portlet/bookmarks/entry_action.jsp"
		/>
	</liferay-ui:search-container-row>

	<c:if test="<%= BookmarksFolderPermission.contains(permissionChecker, scopeGroupId, folderId, ActionKeys.ADD_ENTRY) %>">
		<div class="control-wrapper">
			<a href="javascript:;" onClick="<portlet:namespace />addEntry();"><liferay-ui:message key="add-entry" /></a>
		</div>
	</c:if>

	<c:if test="<%= BookmarksEntryServiceUtil.getEntriesCount(scopeGroupId, folderId) <= 0 %>">
		<liferay-ui:message key="there-are-no-entries" />
	</c:if>

	<liferay-ui:search-iterator />
</liferay-ui:search-container>

</form>

<script type="text/javascript">
	function <portlet:namespace />addEntry() {
		var url = '<portlet:renderURL windowState="<%= WindowState.MAXIMIZED.toString() %>"><portlet:param name="struts_action" value="/bookmarks/edit_entry" /><portlet:param name="redirect" value="<%= currentURL %>" /><portlet:param name="folderId" value="<%= String.valueOf(folderId) %>" /></portlet:renderURL>';

		if (document.<portlet:namespace />fm2.<portlet:namespace />keywords) {
			url += '&<portlet:namespace />name=' + document.<portlet:namespace />fm2.<portlet:namespace />keywords.value;
		}

		submitForm(document.hrefFm, url);
	}
</script>