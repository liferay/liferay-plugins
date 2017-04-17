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

long parentResourcePrimKey = kbNavigationDisplayContext.getRootResourcePrimKey();

String pageTitle = kbNavigationDisplayContext.getPageTitle();

if (Validator.isNotNull(pageTitle)) {
	PortalUtil.setPageTitle(pageTitle, request);
}

KBArticleURLHelper kbArticleURLHelper = new KBArticleURLHelper(renderRequest, renderResponse, templatePath);
%>

<div class="kbarticle-navigation">
	<c:if test="<%= resourceClassNameId == kbFolderClassNameId %>">
		<liferay-util:include page="/display/content_root_selector.jsp" servletContext="<%= application %>" />
	</c:if>

	<%
	request.setAttribute("view_navigation_articles.jsp-kbArticleURLHelper", kbArticleURLHelper);
	request.setAttribute("view_navigation_articles.jsp-level", 0);
	request.setAttribute("view_navigation_articles.jsp-parentResourcePrimKey", parentResourcePrimKey);
	%>

	<liferay-util:include page="/display/view_navigation_articles.jsp" servletContext="<%= application %>" />
</div>