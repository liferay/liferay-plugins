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
int status = (Integer)request.getAttribute(WebKeys.KNOWLEDGE_BASE_STATUS);

KBArticle kbArticle = (KBArticle)request.getAttribute(WebKeys.KNOWLEDGE_BASE_KB_ARTICLE);

List<KBArticle> childKBArticles = KBArticleServiceUtil.getKBArticles(scopeGroupId, kbArticle.getResourcePrimKey(), status, QueryUtil.ALL_POS, QueryUtil.ALL_POS, new KBArticlePriorityComparator(true));
%>

<c:if test="<%= !childKBArticles.isEmpty() %>">
	<div class="kb-article-child">
		<div class="kb-elements">

			<%
			for (KBArticle childrenKBArticle : childKBArticles) {
			%>

				<section class="kb-element">
					<h2 class="kb-element-header">
						<liferay-portlet:renderURL var="viewKBArticleURL">
							<c:choose>
								<c:when test="<%= Validator.isNotNull(childrenKBArticle.getUrlTitle()) %>">
									<portlet:param name="urlTitle" value="<%= childrenKBArticle.getUrlTitle() %>" />
								</c:when>
								<c:otherwise>
									<portlet:param name="resourcePrimKey" value="<%= String.valueOf(childrenKBArticle.getResourcePrimKey()) %>" />
								</c:otherwise>
							</c:choose>
						</liferay-portlet:renderURL>

						<aui:a href="<%= viewKBArticleURL %>"><%= childrenKBArticle.getTitle() %></aui:a>
					</h2>

					<div class="kb-element-body">
						<c:choose>
							<c:when test="<%= Validator.isNotNull(childrenKBArticle.getDescription()) %>">
								<%= childrenKBArticle.getDescription() %>
							</c:when>
							<c:otherwise>
								<%= StringUtil.shorten(HtmlUtil.extractText(childrenKBArticle.getContent()), 200) %>

								<aui:a href="<%= viewKBArticleURL %>"><liferay-ui:message key="read-more" /></aui:a>
							</c:otherwise>
						</c:choose>
					</div>
				</section>

			<%
			}
			%>

		</div>
	</div>
</c:if>