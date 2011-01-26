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

<%@ include file="/list/init.jsp" %>

<%
long categoryId = ParamUtil.getLong(request, "categoryId");
String tag = ParamUtil.getString(request, "tag");
%>

<liferay-portlet:renderURL varImpl="iteratorURL" />

<liferay-ui:search-container
	delta="<%= articlesDelta %>"
	headerNames="title,author,date,version"
	iteratorURL="<%= iteratorURL %>"
>
	<liferay-ui:search-container-results>

		<%
		Map<String, Object> preferencesArticlesMap = KnowledgeBaseUtil.getPortletPreferencesArticlesMap(scopeGroupId, portletDisplay.getId(), categoryId, tag, searchContainer.getStart(), searchContainer.getEnd(), preferences);

		pageContext.setAttribute("results", preferencesArticlesMap.get("articles"));
		pageContext.setAttribute("total", preferencesArticlesMap.get("count"));
		%>

	</liferay-ui:search-container-results>

	<liferay-ui:search-container-row
		className="com.liferay.knowledgebase.model.Article"
		keyProperty="resourcePrimKey"
		modelVar="article"
	>
		<portlet:renderURL var="rowURL" windowState="<%= articleWindowState %>">
			<portlet:param name="jspPage" value="/list/view_article.jsp" />
			<portlet:param name="resourcePrimKey" value="<%= String.valueOf(article.getResourcePrimKey()) %>" />
		</portlet:renderURL>

		<liferay-ui:search-container-column-text
			href="<%= rowURL %>"
			property="title"
		/>

		<liferay-ui:search-container-column-text
			href="<%= rowURL %>"
			name="author"
			property="userName"
		/>

		<liferay-ui:search-container-column-text
			href="<%= rowURL %>"
			name="date"
			value="<%= dateFormatDateTime.format(article.getModifiedDate()) %>"
		/>

		<liferay-ui:search-container-column-text
			align="right"
		>
			<portlet:resourceURL id="articleRSS" var="articleRSSURL">
				<portlet:param name="resourcePrimKey" value="<%= String.valueOf(article.getResourcePrimKey()) %>" />
				<portlet:param name="rssDelta" value="<%= String.valueOf(rssDelta) %>" />
				<portlet:param name="rssDisplayStyle" value="<%= rssDisplayStyle %>" />
				<portlet:param name="rssFormat" value="<%= rssFormat %>" />
			</portlet:resourceURL>

			<liferay-ui:icon
				image="rss"
				label="<%= true %>"
				method="get"
				target="_blank"
				url="<%= articleRSSURL %>"
			/>

			<c:if test="<%= ArticlePermission.contains(permissionChecker, article, ActionKeys.SUBSCRIBE) %>">
				<c:choose>
					<c:when test="<%= SubscriptionLocalServiceUtil.isSubscribed(user.getCompanyId(), user.getUserId(), Article.class.getName(), article.getResourcePrimKey()) %>">
						<portlet:actionURL name="unsubscribeArticle" var="unsubscribeArticleURL">
							<portlet:param name="redirect" value="<%= currentURL %>" />
							<portlet:param name="resourcePrimKey" value="<%= String.valueOf(article.getResourcePrimKey()) %>" />
						</portlet:actionURL>

						<liferay-ui:icon
							image="unsubscribe"
							label="<%= true %>"
							url="<%= unsubscribeArticleURL %>"
						/>
					</c:when>
					<c:otherwise>
						<portlet:actionURL name="subscribeArticle" var="subscribeArticleURL">
							<portlet:param name="redirect" value="<%= currentURL %>" />
							<portlet:param name="resourcePrimKey" value="<%= String.valueOf(article.getResourcePrimKey()) %>" />
						</portlet:actionURL>

						<liferay-ui:icon
							image="subscribe"
							label="<%= true %>"
							url="<%= subscribeArticleURL %>"
						/>
					</c:otherwise>
				</c:choose>
			</c:if>
		</liferay-ui:search-container-column-text>
	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator />

	<c:if test="<%= total == 0 %>">
		<liferay-ui:search-paginator searchContainer="<%= searchContainer %>" />
	</c:if>
</liferay-ui:search-container>