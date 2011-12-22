<%--
/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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
int status = (Integer)request.getAttribute(WebKeys.KNOWLEDGE_BASE_STATUS);

KBArticle kbArticle = (KBArticle)request.getAttribute(WebKeys.KNOWLEDGE_BASE_KB_ARTICLE);

List<KBArticle> siblingKBArticles = KBArticleServiceUtil.getSiblingKBArticles(scopeGroupId, kbArticle.getResourcePrimKey(), status, QueryUtil.ALL_POS, QueryUtil.ALL_POS, new KBArticlePriorityComparator(true));
%>

<c:if test="<%= !siblingKBArticles.isEmpty() %>">
	<div class="kb-article-siblings">
		<div class="kb-elements">

			<%
			for (KBArticle siblingKBArticle : siblingKBArticles) {
			%>

				<div class="kb-element-header">
					<liferay-portlet:renderURL var="viewKBArticleURL">
						<portlet:param name="jspPage" value='<%= jspPath + "view_article.jsp" %>' />
						<portlet:param name="resourcePrimKey" value="<%= String.valueOf(siblingKBArticle.getResourcePrimKey()) %>" />
						<portlet:param name="status" value="<%= String.valueOf(status) %>" />
					</liferay-portlet:renderURL>

					<liferay-ui:icon
						image="../trees/page"
						label="<%= true %>"
						message="<%= siblingKBArticle.getTitle() %>"
						method="get"
						url="<%= viewKBArticleURL %>"
					/>
				</div>
				<div class="kb-element-body">

					<%
					request.setAttribute("article_icons.jsp-kb_article", siblingKBArticle);
					%>

					<liferay-util:include page="/admin/article_icons.jsp" servletContext="<%= application %>" />

					<c:choose>
						<c:when test="<%= Validator.isNotNull(siblingKBArticle.getDescription()) %>">
							<%= siblingKBArticle.getDescription() %>
						</c:when>
						<c:otherwise>
							<%= StringUtil.shorten(HtmlUtil.extractText(siblingKBArticle.getContent()), 500) %>
						</c:otherwise>
					</c:choose>
				</div>

			<%
			}
			%>

		</div>
	</div>
</c:if>