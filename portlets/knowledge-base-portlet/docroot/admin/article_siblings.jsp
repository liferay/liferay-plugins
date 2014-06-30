<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

KBArticle previousKBArticle = kbArticle.getPreviousKBArticle();

KBArticle nextKBArticle = kbArticle.getNextKBArticle();
%>

<div class="kb-article-siblings">
	<span class="kb-article-prev">
		<c:choose>
			<c:when test="<%= previousKBArticle != null %>">
				<liferay-portlet:renderURL var="previousKBArticleURL">
					<c:choose>
						<c:when test="<%= Validator.isNotNull(previousKBArticle.getUrlTitle()) %>">
							<portlet:param name="urlTitle" value="<%= previousKBArticle.getUrlTitle() %>" />
						</c:when>
						<c:otherwise>
							<portlet:param name="resourcePrimKey" value="<%= String.valueOf(previousKBArticle.getResourcePrimKey()) %>" />
						</c:otherwise>
					</c:choose>
				</liferay-portlet:renderURL>

				<a href="<%= previousKBArticleURL %>">
					&laquo; <liferay-ui:message key="previous" />
				</a>

				<span class="kb-article-sibling-title"><%= previousKBArticle.getTitle() %></span>
			</c:when>
			<c:otherwise>
				<span>
					&laquo; <liferay-ui:message key="previous" />
				</span>
			</c:otherwise>
		</c:choose>
	</span>

	<span class="kb-article-next">
		<c:choose>
			<c:when test="<%= nextKBArticle != null %>">
				<liferay-portlet:renderURL var="nextKBArticleURL">
					<c:choose>
						<c:when test="<%= Validator.isNotNull(nextKBArticle.getUrlTitle()) %>">
							<portlet:param name="urlTitle" value="<%= nextKBArticle.getUrlTitle() %>" />
						</c:when>
						<c:otherwise>
							<portlet:param name="resourcePrimKey" value="<%= String.valueOf(nextKBArticle.getResourcePrimKey()) %>" />
						</c:otherwise>
					</c:choose>
				</liferay-portlet:renderURL>

				<a href="<%= nextKBArticleURL %>">
					<liferay-ui:message key="next" /> &raquo;
				</a>

				<span class="kb-article-sibling-title"><%= nextKBArticle.getTitle() %></span>

			</c:when>
			<c:otherwise>
				<span>
					<liferay-ui:message key="next" /> &raquo;
				</span>
			</c:otherwise>
		</c:choose>
	</span>
</div>