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

<%
if (rootFolderId <= 0) {
	List<DLFolder> folders = DLFolderLocalServiceUtil.getFolders(scopeGroupId, DLFolderImpl.DEFAULT_PARENT_FOLDER_ID, 0, 1);

	DLFolder dynamicRootFolder = null;

	if (folders.size() > 0) {
		dynamicRootFolder = folders.get(0);
	}
	else {
		ServiceContext serviceContext = ServiceContextFactory.getInstance(DLFolder.class.getName(), renderRequest);
		dynamicRootFolder = DLFolderLocalServiceUtil.addFolder(themeDisplay.getUserId(), scopeGroupId, DLFolderImpl.DEFAULT_PARENT_FOLDER_ID, "Documents Home", "", serviceContext);
	}

	rootFolderId = dynamicRootFolder.getFolderId();

	rootFolder = DLFolderLocalServiceUtil.getFolder(rootFolderId);

	rootFolderName = rootFolder.getName();

	preferences.setValue("rootFolderId", String.valueOf(rootFolderId));

	preferences.store();
}

folderColumns = new String[] {"name", "size", "tags", "downloads", "locked", "action"};
fileEntryColumns = folderColumns;

showSubfolders = false;

showFileEntriesSearch = false;

mergedView = true;

showAddFileShortcutButton = false;
%>