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
%>

<liferay-portlet:renderURL var="viewKBArticleURL">
	<c:choose>
		<c:when test="<%= Validator.isNotNull(kbArticle.getUrlTitle()) %>">
			<portlet:param name="urlTitle" value="<%= kbArticle.getUrlTitle() %>" />

			<c:if test="<%= kbArticle.getKbFolderId() != KBFolderConstants.DEFAULT_PARENT_FOLDER_ID %>">

				<%
				KBFolder kbFolder = KBFolderServiceUtil.getKBFolder(kbArticle.getKbFolderId());
				%>

				<portlet:param name="kbFolderUrlTitle" value="<%= kbFolder.getUrlTitle() %>" />
			</c:if>
		</c:when>
		<c:otherwise>
			<portlet:param name="resourcePrimKey" value="<%= String.valueOf(kbArticle.getResourcePrimKey()) %>" />
		</c:otherwise>
	</c:choose>
</liferay-portlet:renderURL>

<liferay-ui:social-bookmarks
	contentId="<%= String.valueOf(kbArticle.getResourcePrimKey()) %>"
	displayStyle="<%= socialBookmarksDisplayStyle %>"
	target="_blank"
	title="<%= kbArticle.getTitle() %>"
	types="<%= socialBookmarksTypes %>"
	url="<%= PortalUtil.getCanonicalURL(viewKBArticleURL.toString(), themeDisplay, layout) %>"
/>