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

<%
folderColumns = new String[] {"name", "lock", "action"};
fileEntryColumns = folderColumns;

mergedView = true;

Format longDateFormatDateTime = FastDateFormatFactoryUtil.getSimpleDateFormat("EEE, MMM d yyyy h:mm aaa");
%>

<%!
public String getFolderBreadcrumbs(long folderId, PageContext pageContext, RenderResponse renderResponse) throws Exception {
	PortletURL portletURL = renderResponse.createRenderURL();

	portletURL.setWindowState(WindowState.NORMAL);

	portletURL.setParameter("struts_action", "/document_library/view");
	portletURL.setParameter("folderId", String.valueOf(DLFolderConstants.DEFAULT_PARENT_FOLDER_ID));

	String head = "<span><a href=\"" + portletURL.toString() + "\">" + LanguageUtil.get(pageContext, "documents-home") + "</a></span> &raquo; ";

	if (folderId == DLFolderConstants.DEFAULT_PARENT_FOLDER_ID) {
		return head;
	}

	String breadcrumb = StringPool.BLANK;

	DLFolder folder = DLFolderLocalServiceUtil.getFolder(folderId);

	while (true) {
		portletURL.setParameter("folderId", String.valueOf(folder.getFolderId()));

		breadcrumb = "<span><a href=\"" + portletURL.toString() + "\">" + folder.getName() + "</a></span> &raquo; " + breadcrumb;

		if (folder.isRoot()) {
			break;
		}

		folder = DLFolderLocalServiceUtil.getFolder(folder.getParentFolderId());
	};

	breadcrumb = head + breadcrumb;

	return breadcrumb;
}
%>