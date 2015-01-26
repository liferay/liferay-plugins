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

KBArticle[] previousAndNextKBArticles = KBArticleLocalServiceUtil.getPreviousAndNextKBArticles(kbArticle.getKbArticleId());

KBArticle previousKBArticle = previousAndNextKBArticles[0];
KBArticle nextKBArticle = previousAndNextKBArticles[2];

KBArticleURLHelper kbArticleURLHelper = new KBArticleURLHelper(renderRequest, renderResponse, templatePath);
%>

<div class="kb-article-siblings">
	<span class="kb-article-previous">
		<c:if test="<%= previousKBArticle != null %>">

			<%
			PortletURL previousKBArticleURL = kbArticleURLHelper.createViewURL(previousKBArticle);
			%>

			<aui:a cssClass="hidden-phone" href="<%= previousKBArticleURL.toString() %>">
				<i class="icon icon-circle-arrow-left"></i>

				<span class="title"><%= HtmlUtil.escape(previousKBArticle.getTitle()) %></span>
			</aui:a>

			<aui:a cssClass="visible-phone" href="<%= previousKBArticleURL.toString() %>">
				<i class="icon icon-circle-arrow-left"></i>

				<span class="title"><liferay-ui:message key="previous" /></span>
			</aui:a>
		</c:if>
	</span>

	<span class="kb-article-next">
		<c:if test="<%= nextKBArticle != null %>">

			<%
			PortletURL nextKBArticleURL = kbArticleURLHelper.createViewURL(nextKBArticle);
			%>

			<aui:a cssClass="hidden-phone next" href="<%= nextKBArticleURL.toString() %>">
				<span class="title"><%= HtmlUtil.escape(nextKBArticle.getTitle()) %></span>

				<i class="icon icon-circle-arrow-right"></i>
			</aui:a>

			<aui:a cssClass="next visible-phone" href="<%= nextKBArticleURL.toString() %>">
				<span class="title"><liferay-ui:message key="next" /></span>

				<i class="icon icon-circle-arrow-right"></i>
			</aui:a>
		</c:if>
	</span>
</div>