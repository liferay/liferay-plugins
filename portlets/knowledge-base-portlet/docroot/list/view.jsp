<%
/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
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
%>

<%@ include file="/list/init.jsp" %>

<c:choose>
	<c:when test="<%= Validator.isNotNull(articlesTitle) %>">
		<liferay-ui:panel-container extended="<%= false %>" id='<%= renderResponse.getNamespace() + "ArticlesPanelContainer" %>' persistState="<%= true %>">
			<liferay-ui:panel collapsible="<%= true %>" extended="<%= true %>" id='<%= renderResponse.getNamespace() + "ArticlesPanel" %>' persistState="<%= true %>" title='<%= LanguageUtil.get(pageContext, articlesTitle) %>'>
				<liferay-util:include page="/list/view_articles.jsp" servletContext="<%= application %>" />
			</liferay-ui:panel>
		</liferay-ui:panel-container>
	</c:when>
	<c:otherwise>
		<liferay-util:include page="/list/view_articles.jsp" servletContext="<%= application %>" />
	</c:otherwise>
</c:choose>