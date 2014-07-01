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

KBArticle[] prevAndNext = KBArticleLocalServiceUtil.getKBArticlesPrevAndNext(kbArticle.getKbArticleId());

KBArticle previousKBArticle = prevAndNext[0];
KBArticle nextKBArticle = prevAndNext[2];
%>

<div class="kb-article-siblings">
	<span class="kb-article-prev">
		<c:if test="<%= previousKBArticle != null %>">
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

			<aui:a cssClass="icon-circle-arrow-left" href="<%= previousKBArticleURL %>" label="previous" />

			<span class="kb-article-sibling-title"><%= previousKBArticle.getTitle() %></span>
		</c:if>
	</span>

	<span class="kb-article-next">
		<c:if test="<%= nextKBArticle != null %>">
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

			<aui:a cssClass="next" href="<%= nextKBArticleURL %>" label="next">
				<i class="icon-circle-arrow-right"></i>
			</aui:a>

			<span class="kb-article-sibling-title"><%= nextKBArticle.getTitle() %></span>
		</c:if>
	</span>
</div>