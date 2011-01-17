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

<%@ include file="/aggregator/init.jsp" %>

<c:choose>
	<c:when test="<%= Validator.isNotNull(articlesTitle) %>">
		<liferay-ui:panel-container extended="<%= false %>" id='<%= renderResponse.getNamespace() + "ArticlesPanelContainer" %>' persistState="<%= true %>">
			<liferay-ui:panel collapsible="<%= true %>" extended="<%= true %>" id='<%= renderResponse.getNamespace() + "ArticlesPanel" %>' persistState="<%= true %>" title="<%= articlesTitle %>">
				<liferay-util:include page="/aggregator/view_articles.jsp" servletContext="<%= application %>" />
			</liferay-ui:panel>
		</liferay-ui:panel-container>
	</c:when>
	<c:otherwise>
		<liferay-util:include page="/aggregator/view_articles.jsp" servletContext="<%= application %>" />
	</c:otherwise>
</c:choose>

<c:if test='<%= selectionMethod.equals("group") && (((assetCategoryIds.length == 0) && assetEntryQueryName.equals("asset-categories")) || ((assetTagNames.length == 0) && assetEntryQueryName.equals("asset-tags"))) %>'>
	<div class="separator"><!-- --></div>

	<div>
		<portlet:resourceURL id="groupArticlesRSS" var="groupArticlesRSSURL">
			<portlet:param name="rssDelta" value="<%= String.valueOf(rssDelta) %>" />
			<portlet:param name="rssDisplayStyle" value="<%= rssDisplayStyle %>" />
			<portlet:param name="rssFormat" value="<%= rssFormat %>" />
		</portlet:resourceURL>

		<liferay-ui:icon
			image="rss"
			label="<%= true %>"
			method="get"
			target="_blank"
			url="<%= groupArticlesRSSURL %>"
		/>

		<c:if test="<%= themeDisplay.isSignedIn() %>">
			<c:choose>
				<c:when test="<%= SubscriptionLocalServiceUtil.isSubscribed(user.getCompanyId(), user.getUserId(), Article.class.getName(), scopeGroupId) %>">
					<portlet:actionURL name="unsubscribeGroupArticles" var="unsubscribeGroupArticlesURL">
						<portlet:param name="redirect" value="<%= currentURL %>" />
					</portlet:actionURL>

					<liferay-ui:icon
						image="unsubscribe"
						label="<%= true %>"
						url="<%= unsubscribeGroupArticlesURL %>"
					/>
				</c:when>
				<c:otherwise>
					<portlet:actionURL name="subscribeGroupArticles" var="subscribeGroupArticlesURL">
						<portlet:param name="redirect" value="<%= currentURL %>" />
					</portlet:actionURL>

					<liferay-ui:icon
						image="subscribe"
						label="<%= true %>"
						url="<%= subscribeGroupArticlesURL %>"
					/>
				</c:otherwise>
			</c:choose>
		</c:if>
	</div>
</c:if>