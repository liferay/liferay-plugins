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

if (enableKBArticleViewCountIncrement && !kbArticle.isDraft() && !kbArticle.isPending()) {
	KBArticle latestKBArticle = KBArticleLocalServiceUtil.getLatestKBArticle(kbArticle.getResourcePrimKey(), WorkflowConstants.STATUS_APPROVED);

	KBArticleLocalServiceUtil.updateViewCount(themeDisplay.getUserId(), kbArticle.getResourcePrimKey(), latestKBArticle.getViewCount() + 1);

	AssetEntryServiceUtil.incrementViewCounter(KBArticle.class.getName(), latestKBArticle.getClassPK());
}
%>

<c:if test='<%= enableSocialBookmarks && socialBookmarksDisplayPosition.equals("top") %>'>
	<liferay-util:include page="/admin/article_social_bookmarks.jsp" servletContext="<%= application %>" />
</c:if>

<c:choose>
	<c:when test="<%= !redirect.equals(currentURL) %>">
		<div class="kb-tools">
			<liferay-util:include page="/admin/article_tools.jsp" servletContext="<%= application %>" />
		</div>

		<liferay-ui:header title="<%= kbArticle.getTitle() %>" />
	</c:when>
	<c:otherwise>
		<div class="float-container kb-entity-header">
			<div class="kb-tools">
				<liferay-util:include page="/admin/article_tools.jsp" servletContext="<%= application %>" />
			</div>

			<h1 class="kb-title">
				<%= HtmlUtil.escape(kbArticle.getTitle()) %>
			</h1>

			<c:if test="<%= !kbArticle.isApproved() %>">
				<div class="kb-article-status">
					<aui:model-context bean="<%= kbArticle %>" model="<%= KBArticle.class %>" />

					<aui:workflow-status status="<%= kbArticle.getStatus() %>" />
				</div>
			</c:if>
		</div>
	</c:otherwise>
</c:choose>

<%
request.setAttribute("article_icons.jsp-kb_article", kbArticle);
%>

<liferay-util:include page="/admin/article_icons.jsp" servletContext="<%= application %>" />

<div class="kb-entity-body">
	<div class="kb-article-body" id="<portlet:namespace /><%= kbArticle.getResourcePrimKey() %>">
		<%= kbArticle.getContent() %>
	</div>

	<liferay-util:include page="/admin/article_child.jsp" servletContext="<%= application %>" />

	<c:if test='<%= enableSocialBookmarks && socialBookmarksDisplayPosition.equals("bottom") %>'>
		<liferay-util:include page="/admin/article_social_bookmarks.jsp" servletContext="<%= application %>" />
	</c:if>

	<liferay-util:include page="/admin/article_ratings.jsp" servletContext="<%= application %>" />

	<c:if test="<%= !rootPortletId.equals(PortletKeys.KNOWLEDGE_BASE_ARTICLE) %>">
		<liferay-util:include page="/admin/article_siblings.jsp" servletContext="<%= application %>" />
	</c:if>

	<liferay-util:include page="/admin/article_assets.jsp" servletContext="<%= application %>" />

	<liferay-util:include page="/admin/article_asset_entries.jsp" servletContext="<%= application %>" />

	<liferay-util:include page="/admin/article_asset_links.jsp" servletContext="<%= application %>" />
</div>