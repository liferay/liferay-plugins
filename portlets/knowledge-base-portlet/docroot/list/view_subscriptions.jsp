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

<liferay-util:include page="/list/top_links.jsp" servletContext="<%= application %>" />

<liferay-portlet:renderURL varImpl="iteratorURL" />

<liferay-ui:search-container
	delta="<%= articlesDelta %>"
	headerNames="title,author,date,version"
	iteratorURL="<%= iteratorURL %>"
>
	<liferay-ui:search-container-results>

		<%
		List<Subscription> subscriptions = ArticleLocalServiceUtil.getSubscriptions(user.getUserId(), scopeGroupId);

		long[] classPKs = StringUtil.split(ListUtil.toString(subscriptions, "classPK"), 0L);

		Map<String, Object> params = new HashMap<String, Object>();

		params.put("parentGroupId", themeDisplay.getParentGroupId());
		params.put("resourcePrimKey", ArrayUtil.toArray(classPKs));
		params.put("status", WorkflowConstants.STATUS_APPROVED);

		pageContext.setAttribute("results", ArticleServiceUtil.getArticles(params, false, searchContainer.getStart(), searchContainer.getEnd(), orderByComparator));
		pageContext.setAttribute("total", ArticleServiceUtil.getArticlesCount(params, false));
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

		<c:if test="<%= themeDisplay.isSignedIn() %>">
			<liferay-ui:search-container-column-text
				align="right"
			>
				<portlet:actionURL name="unsubscribe" var="unsubscribeURL">
					<portlet:param name="redirect" value="<%= currentURL %>" />
					<portlet:param name="resourcePrimKey" value="<%= String.valueOf(article.getResourcePrimKey()) %>" />
				</portlet:actionURL>

				<liferay-ui:icon
					image="unsubscribe"
					label="<%= true %>"
					url="<%= unsubscribeURL %>"
				/>
			</liferay-ui:search-container-column-text>
		</c:if>
	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator />

	<c:if test="<%= results.isEmpty() && (total == 0) %>">
		<liferay-ui:search-paginator searchContainer="<%= searchContainer %>" />
	</c:if>
</liferay-ui:search-container>