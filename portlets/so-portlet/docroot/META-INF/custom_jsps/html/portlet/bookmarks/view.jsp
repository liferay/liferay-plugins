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

<%@ include file="/html/portlet/bookmarks/init.jsp" %>

<%
String tabs1 = ParamUtil.getString(request, "tabs1", "folders");

PortletPreferences preferences = renderRequest.getPreferences();

String portletResource = ParamUtil.getString(request, "portletResource");

if (Validator.isNotNull(portletResource)) {
	preferences = PortletPreferencesFactoryUtil.getPortletSetup(request, portletResource);
}

BookmarksFolder rootFolder = null;

long rootFolderId = PrefsParamUtil.getLong(preferences, request, "rootFolderId", BookmarksFolderImpl.DEFAULT_PARENT_FOLDER_ID);

if (rootFolderId == BookmarksFolderImpl.DEFAULT_PARENT_FOLDER_ID) {
	BookmarksFolder dynamicRootFolder = null;

	int count = BookmarksFolderLocalServiceUtil.getFoldersCount(scopeGroupId, BookmarksFolderImpl.DEFAULT_PARENT_FOLDER_ID);

	if (count > 1) {
		List<BookmarksFolder> folders = BookmarksFolderLocalServiceUtil.getFolders(scopeGroupId, BookmarksFolderImpl.DEFAULT_PARENT_FOLDER_ID, -1, -1);

		ServiceContext serviceContext = ServiceContextFactory.getInstance(BookmarksFolder.class.getName(), renderRequest);

		dynamicRootFolder = BookmarksFolderLocalServiceUtil.addFolder(themeDisplay.getUserId(), BookmarksFolderImpl.DEFAULT_PARENT_FOLDER_ID, LanguageUtil.get(pageContext, "bookmarks"), "", serviceContext);

		long dynamicRootFolderId = dynamicRootFolder.getFolderId();

		for (BookmarksFolder folder : folders) {
			BookmarksFolderLocalServiceUtil.updateFolder(folder.getFolderId(), dynamicRootFolderId, folder.getName(), folder.getDescription(), false, serviceContext);
		}
	}
	else if (count == 1) {
		List<BookmarksFolder> folders = BookmarksFolderLocalServiceUtil.getFolders(scopeGroupId, BookmarksFolderImpl.DEFAULT_PARENT_FOLDER_ID, 0, 1);

		dynamicRootFolder = folders.get(0);
	}
	else {
		ServiceContext serviceContext = ServiceContextFactory.getInstance(BookmarksFolder.class.getName(), renderRequest);

		dynamicRootFolder = BookmarksFolderLocalServiceUtil.addFolder(themeDisplay.getUserId(), BookmarksFolderImpl.DEFAULT_PARENT_FOLDER_ID, LanguageUtil.get(pageContext, "bookmarks"), "", serviceContext);
	}

	rootFolderId = dynamicRootFolder.getFolderId();

	rootFolder = BookmarksFolderLocalServiceUtil.getFolder(rootFolderId);

	preferences.setValue("rootFolderId", String.valueOf(rootFolderId));

	preferences.store();
}

BookmarksFolder folder = (BookmarksFolder)request.getAttribute(WebKeys.BOOKMARKS_FOLDER);

long defaultFolderId = GetterUtil.getLong(preferences.getValue("rootFolderId", StringPool.BLANK), BookmarksFolderImpl.DEFAULT_PARENT_FOLDER_ID);

long folderId = BeanParamUtil.getLong(folder, request, "folderId", defaultFolderId);

if ((folder == null) && (defaultFolderId != BookmarksFolderImpl.DEFAULT_PARENT_FOLDER_ID)) {
	try {
		folder = BookmarksFolderLocalServiceUtil.getFolder(folderId);
	}
	catch (NoSuchFolderException nsfe) {
		folderId = BookmarksFolderImpl.DEFAULT_PARENT_FOLDER_ID;
	}
}

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setWindowState(WindowState.MAXIMIZED);

portletURL.setParameter("struts_action", "/bookmarks/view");
portletURL.setParameter("tabs1", tabs1);
portletURL.setParameter("folderId", String.valueOf(folderId));
%>

<%
String orderByCol = ParamUtil.getString(request, "orderByCol");
String orderByType = ParamUtil.getString(request, "orderByType");

if (Validator.isNotNull(orderByCol) && Validator.isNotNull(orderByType)) {
	portalPrefs.setValue(PortletKeys.BOOKMARKS, "entries-order-by-col", orderByCol);
	portalPrefs.setValue(PortletKeys.BOOKMARKS, "entries-order-by-type", orderByType);
}
else {
	orderByCol = portalPrefs.getValue(PortletKeys.BOOKMARKS, "entries-order-by-col", "name");
	orderByType = portalPrefs.getValue(PortletKeys.BOOKMARKS, "entries-order-by-type", "asc");
}

OrderByComparator orderByComparator = BookmarksUtil.getEntriesOrderByComparator(orderByCol, orderByType);
%>

<liferay-portlet:renderURL windowState="<%= WindowState.MAXIMIZED.toString() %>" varImpl="searchURL">
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
	<liferay-ui:search-container-results
		results="<%= BookmarksEntryLocalServiceUtil.getEntries(folder.getFolderId(), searchContainer.getStart(), searchContainer.getEnd(), orderByComparator) %>"
		total="<%= BookmarksEntryLocalServiceUtil.getEntriesCount(folder.getFolderId()) %>"
	/>

	<liferay-ui:search-container-row
		className="com.liferay.portlet.bookmarks.model.BookmarksEntry"
		escapedModel="<%= true %>"
		keyProperty="entryId"
		modelVar="entry"
	>

		<%
		StringBuilder sb = new StringBuilder();

		sb.append(themeDisplay.getPathMain());
		sb.append("/bookmarks/open_entry?entryId=");
		sb.append(entry.getEntryId());

		String rowHREF = sb.toString();
		%>

		<liferay-ui:search-container-column-text
			href="<%= rowHREF %>"
			orderable="<%= true %>"
			orderableProperty="name"
			property="name"
			target="_blank"
			title="<%= entry.getComments() %>"
		/>

		<liferay-ui:search-container-column-jsp
			align="right"
			path="/html/portlet/bookmarks/entry_action.jsp"
		/>
	</liferay-ui:search-container-row>

	<c:if test="<%= BookmarksFolderPermission.contains(permissionChecker, folder, ActionKeys.ADD_ENTRY) %>">
		<div class="control-wrapper">
			<a href="javascript:;" onClick="<portlet:namespace />addEntry();"><liferay-ui:message key="add-entry" /></a>
		</div>
	</c:if>

	<c:if test="<%= BookmarksEntryLocalServiceUtil.getEntriesCount(folder.getFolderId()) <= 0 %>">
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