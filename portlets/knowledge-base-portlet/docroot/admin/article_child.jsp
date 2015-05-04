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

int status = (Integer)request.getAttribute(WebKeys.KNOWLEDGE_BASE_STATUS);

List<KBArticle> childKBArticles = KBArticleServiceUtil.getKBArticles(scopeGroupId, kbArticle.getResourcePrimKey(), status, QueryUtil.ALL_POS, QueryUtil.ALL_POS, new KBArticlePriorityComparator(true));

KBArticleURLHelper kbArticleURLHelper = new KBArticleURLHelper(renderRequest, renderResponse, templatePath);
%>

<c:if test="<%= !childKBArticles.isEmpty() %>">
	<div class="kb-article-child">
		<div class="kb-elements">

			<%
			for (KBArticle childrenKBArticle : childKBArticles) {
			%>

				<section class="kb-element">
					<h2 class="kb-element-header">

						<%
						PortletURL viewKBArticleURL = null;

						if (rootPortletId.equals(PortletKeys.KNOWLEDGE_BASE_SECTION)) {
							viewKBArticleURL = kbArticleURLHelper.createViewWithRedirectURL(childrenKBArticle, currentURL);
						}
						else {
							viewKBArticleURL = kbArticleURLHelper.createViewURL(childrenKBArticle);
						}
						%>

						<aui:a href="<%= viewKBArticleURL.toString() %>"><%= childrenKBArticle.getTitle() %></aui:a>
					</h2>

					<div class="kb-element-body">
						<c:choose>
							<c:when test="<%= Validator.isNotNull(childrenKBArticle.getDescription()) %>">
								<%= childrenKBArticle.getDescription() %>
							</c:when>
							<c:otherwise>
								<p><%= StringUtil.shorten(HtmlUtil.extractText(childrenKBArticle.getContent()), 200) %></p>

								<aui:a href="<%= viewKBArticleURL.toString() %>"><liferay-ui:message key="read-more" /></aui:a>
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