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

<%@ include file="/display/init.jsp" %>

<%
KBNavigationDisplayContext kbNavigationDisplayContext = (KBNavigationDisplayContext)request.getAttribute(WebKeys.KNOWLEDGE_BASE_KB_NAVIGATION_DISPLAY_CONTEXT);

KBArticle kbArticle = (KBArticle)request.getAttribute(WebKeys.KNOWLEDGE_BASE_KB_ARTICLE);

long parentResourcePrimKey = (Long)request.getAttribute("view_navigation_articles.jsp-parentResourcePrimKey");
int level = GetterUtil.getInteger(request.getAttribute("view_navigation_articles.jsp-level"));

KBArticleURLHelper kbArticleURLHelper = (KBArticleURLHelper)request.getAttribute("view_navigation_articles.jsp-kbArticleURLHelper");

List<KBArticle> childKBArticles = kbNavigationDisplayContext.getChildKBArticles(themeDisplay.getScopeGroupId(), parentResourcePrimKey, level);

for (KBArticle childKBArticle : childKBArticles) {
	PortletURL viewChildURL = kbArticleURLHelper.createViewURL(childKBArticle);
%>

	<ul>
		<li>

			<%
			boolean childKBArticleExpanded = kbNavigationDisplayContext.isChildKBArticleExpanded(childKBArticle, level);

			String childKBArticleClass = StringPool.BLANK;

			if (childKBArticle.getResourcePrimKey() == kbArticle.getResourcePrimKey()) {
				childKBArticleClass = "kbarticle-selected";
			}
			else if (childKBArticleExpanded && !kbNavigationDisplayContext.isMaxNestingLevelReached(level)) {
				childKBArticleClass = "kbarticle-expanded";
			}
			%>

			<a class="<%= childKBArticleClass %>" href="<%= viewChildURL %>"><%= HtmlUtil.escape(childKBArticle.getTitle()) %></a>

			<c:if test="<%= kbNavigationDisplayContext.isFurtherExpansionRequired(parentResourcePrimKey, childKBArticle, level) %>">

				<%
				request.setAttribute("view_navigation_articles.jsp-level", level + 1);
				request.setAttribute("view_navigation_articles.jsp-parentResourcePrimKey", childKBArticle.getResourcePrimKey());
				%>

				<liferay-util:include page="/display/view_navigation_articles.jsp" servletContext="<%= application %>" />
			</c:if>
		</li>
	</ul>

<%
}
%>