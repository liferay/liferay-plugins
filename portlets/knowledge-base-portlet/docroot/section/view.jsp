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

<%@ include file="/section/init.jsp" %>

<%
KBArticleURLHelper kbArticleURLHelper = new KBArticleURLHelper(renderRequest, renderResponse, templatePath);
%>

<c:choose>
	<c:when test="<%= ArrayUtil.isNotEmpty(PortletPropsValues.ADMIN_KB_ARTICLE_SECTIONS) %>">
		<liferay-portlet:renderURL varImpl="iteratorURL">
			<portlet:param name="mvcPath" value="/section/view.jsp" />
		</liferay-portlet:renderURL>

		<liferay-ui:search-container
			searchContainer="<%= new KBArticleSearch(renderRequest, iteratorURL) %>"
			total="<%= KBArticleServiceUtil.getSectionsKBArticlesCount(scopeGroupId, kbArticlesSections, WorkflowConstants.STATUS_APPROVED) %>"
		>
			<liferay-ui:search-container-results
				results="<%= KBArticleServiceUtil.getSectionsKBArticles(scopeGroupId, kbArticlesSections, WorkflowConstants.STATUS_APPROVED, searchContainer.getStart(), searchContainer.getEnd(), searchContainer.getOrderByComparator()) %>"
			/>

			<c:if test="<%= showKBArticlesSectionsTitle %>">

				<%
				List<String> titles = new ArrayList<String>();

				for (String kbArticlesSection : kbArticlesSections) {
					titles.add(LanguageUtil.get(pageContext, kbArticlesSection));
				}

				Collections.sort(titles);
				%>

				<div class="kb-articles-sections-title">
					<%= StringUtil.merge(titles, StringPool.COMMA_AND_SPACE) %>
				</div>
			</c:if>

			<c:if test="<%= searchContainer.getTotal() == 0 %>">
				<liferay-ui:message key="<%= searchContainer.getEmptyResultsMessage() %>" />
			</c:if>

			<div class="kb-articles">

				<%
				for (int i = 0; i < results.size(); i++) {
					KBArticle kbArticle = (KBArticle)results.get(i);
				%>

					<div class="<%= (i == 0) ? "kb-article-title kb-article-title-first" : "kb-article-title" %>">

						<%
						PortletURL viewKBArticleURL = kbArticleURLHelper.createViewURL(kbArticle);
						%>

						<liferay-ui:icon
							image="../trees/page"
							label="<%= true %>"
							message="<%= kbArticle.getTitle() %>"
							method="get"
							url="<%= viewKBArticleURL.toString() %>"
						/>
					</div>

					<c:if test='<%= !kbArticleDisplayStyle.equals("title") %>'>
						<div class="kb-article-content">
							<c:choose>
								<c:when test='<%= kbArticleDisplayStyle.equals("abstract") && Validator.isNotNull(kbArticle.getDescription()) %>'>
									<%= kbArticle.getDescription() %>
								</c:when>
								<c:when test='<%= kbArticleDisplayStyle.equals("abstract") %>'>
									<%= StringUtil.shorten(HtmlUtil.extractText(kbArticle.getContent()), 500) %>
								</c:when>
							</c:choose>
						</div>
					</c:if>

				<%
				}
				%>

			</div>

			<c:if test="<%= showKBArticlesPagination && (total > searchContainer.getDelta()) %>">
				<div class="taglib-search-iterator-page-iterator-bottom">
					<liferay-ui:search-paginator searchContainer="<%= searchContainer %>" />
				</div>
			</c:if>
		</liferay-ui:search-container>
	</c:when>
	<c:otherwise>

		<%
		renderRequest.setAttribute(WebKeys.PORTLET_CONFIGURATOR_VISIBILITY, Boolean.TRUE);
		%>

		<div class="alert alert-info">
			<%= LanguageUtil.format(pageContext, "please-input-a-list-of-comma-delimited-words-for-portlet-property-x-to-enable-this-portlet", "admin.kb.article.sections", false) %>
		</div>
	</c:otherwise>
</c:choose>