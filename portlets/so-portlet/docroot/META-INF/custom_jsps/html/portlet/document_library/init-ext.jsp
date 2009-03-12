<%
/**
 * Copyright (c) 2000-2009 Liferay, Inc. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
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
		dynamicRootFolder = DLFolderLocalServiceUtil.addFolder(themeDisplay.getUserId(), scopeGroupId, DLFolderImpl.DEFAULT_PARENT_FOLDER_ID, "Documents Home", "", true, true);
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