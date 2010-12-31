<%--
/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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
Article article = (Article)portletSession.getAttribute(WebKeys.KNOWLEDGE_BASE_ARTICLE);

long categoryId = ParamUtil.getLong(request, "categoryId");
String tag = ParamUtil.getString(request, "tag");

if ((categoryId <= 0) && Validator.isNull(tag)) {
	article = KnowledgeBaseUtil.getDisplayArticle(plid, portletDisplay.getId(), categoryId, tag, permissionChecker);
}
%>

<c:choose>
	<c:when test="<%= article == null %>">
		<liferay-ui:message key="there-are-no-articles" />
	</c:when>
	<c:otherwise>

		<%
		request.setAttribute(WebKeys.KNOWLEDGE_BASE_ARTICLE, article);
		%>

		<liferay-util:include page="/display/view_article.jsp" servletContext="<%= application %>" />
	</c:otherwise>
</c:choose>