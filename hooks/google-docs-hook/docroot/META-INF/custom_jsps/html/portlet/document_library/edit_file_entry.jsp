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
FileEntry fileEntry = (FileEntry)request.getAttribute(WebKeys.DOCUMENT_LIBRARY_FILE_ENTRY);

long fileEntryTypeId = ParamUtil.getLong(request, "fileEntryTypeId", -1);

if (fileEntry != null) {
	FileVersion fileVersion = fileEntry.getLatestFileVersion();

	if ((fileEntryTypeId == -1) && (fileVersion.getModel() instanceof DLFileVersion)) {
		DLFileVersion dlFileVersion = (DLFileVersion)fileVersion.getModel();

		fileEntryTypeId = dlFileVersion.getFileEntryTypeId();
	}
}

boolean googleDocs = false;

if (fileEntryTypeId != -1) {
	DLFileEntryType dlfileEntryType = DLFileEntryTypeLocalServiceUtil.getFileEntryType(fileEntryTypeId);

	String fileEntryTypeKey = dlfileEntryType.getFileEntryTypeKey();

	if (fileEntryTypeKey.equals("GOOGLE-DOCS")) {
		googleDocs = true;
	}
}
%>

<c:choose>
	<c:when test="<%= googleDocs %>">
		<style>
			#<portlet:namespace />file, label[for=<portlet:namespace />file],
			#<portlet:namespace />title, label[for=<portlet:namespace />title],
			#<portlet:namespace />description, label[for=<portlet:namespace />description],
			#<portlet:namespace />fileEntryTypeId, label[for=<portlet:namespace />fileEntryTypeId],
			.lfr-ddm-container {
				display: none;
			}
		</style>

		<liferay-util:include page="/html/portlet/document_library/edit_file_entry_google_docs.jsp" />

		<liferay-util:include page="/html/portlet/document_library/google_picker_js.jsp" />
	</c:when>

	<c:otherwise>
		<liferay-util:include page="/html/portlet/document_library/edit_file_entry.portal.jsp" />
	</c:otherwise>
</c:choose>