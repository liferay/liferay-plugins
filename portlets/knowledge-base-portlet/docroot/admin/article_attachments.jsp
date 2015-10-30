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

<%@ include file="/admin/init.jsp" %>

<%
KBArticle kbArticle = (KBArticle)request.getAttribute(WebKeys.KNOWLEDGE_BASE_KB_ARTICLE);

List<FileEntry> attachmentsFileEntries = new ArrayList<FileEntry>();

if (kbArticle != null) {
	attachmentsFileEntries = kbArticle.getAttachmentsFileEntries();
}
%>

<div class="kb-attachments">
	<c:if test="<%= !attachmentsFileEntries.isEmpty() %>">
		<div id="<portlet:namespace />existingAttachmentsContainer">

			<%
			for (FileEntry fileEntry : attachmentsFileEntries) {
			%>

				<div id="<portlet:namespace />fileEntryIdWrapper<%= fileEntry.getFileEntryId() %>">

					<%
					String clipURL = DLUtil.getPreviewURL(fileEntry, fileEntry.getFileVersion(), themeDisplay, StringPool.BLANK);
					%>

					<liferay-ui:icon
						iconCssClass="icon-paper-clip"
						label="<%= true %>"
						message='<%= HtmlUtil.escape(fileEntry.getTitle() + " (" + TextFormatter.formatStorageSize(fileEntry.getSize(), locale) + ")") %>'
						method="get"
						url="<%= clipURL %>"
					/>
				</div>

			<%
			}
			%>

		</div>
	</c:if>
</div>