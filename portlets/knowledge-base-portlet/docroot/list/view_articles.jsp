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
		List<AssetEntry> assetEntries = KnowledgeBaseUtil.getAssetEntries(plid, portletDisplay.getId(), categoryId, tag);

		if (assetEntries != null) {
			long[] classPKs = StringUtil.split(ListUtil.toString(assetEntries, "classPK"), 0L);
			long[] viewableParentResourcePrimKeys = ArticleServiceUtil.getViewableParentResourcePrimKeys(scopeGroupId, WorkflowConstants.STATUS_APPROVED);

			results = ArticleServiceUtil.getArticles(scopeGroupId, classPKs, WorkflowConstants.STATUS_APPROVED, viewableParentResourcePrimKeys, searchContainer.getStart(), searchContainer.getEnd(), orderByComparator);
			total = ArticleServiceUtil.getArticlesCount(scopeGroupId, classPKs, WorkflowConstants.STATUS_APPROVED, viewableParentResourcePrimKeys);
		}
		else if (!allArticles) {
			results = ArticleServiceUtil.getSiblingArticles(scopeGroupId, ArticleConstants.DEFAULT_PARENT_RESOURCE_PRIM_KEY, WorkflowConstants.STATUS_APPROVED, searchContainer.getStart(), searchContainer.getEnd(), orderByComparator);
			total = ArticleServiceUtil.getSiblingArticlesCount(scopeGroupId, ArticleConstants.DEFAULT_PARENT_RESOURCE_PRIM_KEY, WorkflowConstants.STATUS_APPROVED);
		}
		else {
			long[] viewableParentResourcePrimKeys = ArticleServiceUtil.getViewableParentResourcePrimKeys(scopeGroupId, WorkflowConstants.STATUS_APPROVED);

			results = ArticleServiceUtil.getGroupArticles(scopeGroupId, WorkflowConstants.STATUS_APPROVED, viewableParentResourcePrimKeys, searchContainer.getStart(), searchContainer.getEnd(), orderByComparator);
			total = ArticleServiceUtil.getGroupArticlesCount(scopeGroupId, WorkflowConstants.STATUS_APPROVED, viewableParentResourcePrimKeys);
		}

		pageContext.setAttribute("results", results);
		pageContext.setAttribute("total", total);
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
			<portlet:resourceURL id="rss" var="rssURL">
				<portlet:param name="resourcePrimKey" value="<%= String.valueOf(article.getResourcePrimKey()) %>" />
				<portlet:param name="max" value="<%= String.valueOf(rssDelta) %>" />
				<portlet:param name="type" value="<%= rssFormatType %>" />
				<portlet:param name="version" value="<%= String.valueOf(rssFormatVersion) %>" />
				<portlet:param name="displayStyle" value="<%= rssDisplayStyle %>" />
			</portlet:resourceURL>

			<liferay-ui:icon
				image="rss"
				label="<%= true %>"
				method="get"
				target="_blank"
				url="<%= rssURL %>"
			/>

			<c:if test="<%= themeDisplay.isSignedIn() %>">

				<%
				boolean subscribed = false;

				if (SubscriptionLocalServiceUtil.isSubscribed(user.getCompanyId(), user.getUserId(), Article.class.getName(), article.getResourcePrimKey())) {
					Subscription subscription = SubscriptionLocalServiceUtil.getSubscription(user.getCompanyId(), user.getUserId(), Article.class.getName(), article.getResourcePrimKey());

					String[] portletPrimKeys = ExpandoValueLocalServiceUtil.getData(user.getCompanyId(), Subscription.class.getName(), "KB", "portletPrimKeys", subscription.getSubscriptionId(), new String[0]);

					for (String portletPrimKey : portletPrimKeys) {
						long curPlid = ArticleConstants.getPlid(portletPrimKey);
						String curPortletId = ArticleConstants.getPortletId(portletPrimKey);

						if ((curPlid == plid) && curPortletId.equals(portletDisplay.getId())) {
							subscribed = true;
						}
					}
				}
				%>

				<c:choose>
					<c:when test="<%= subscribed %>">
						<portlet:actionURL name="unsubscribe" var="unsubscribeURL">
							<portlet:param name="redirect" value="<%= currentURL %>" />
							<portlet:param name="resourcePrimKey" value="<%= String.valueOf(article.getResourcePrimKey()) %>" />
						</portlet:actionURL>

						<liferay-ui:icon
							image="unsubscribe"
							label="<%= true %>"
							url="<%= unsubscribeURL %>"
						/>
					</c:when>
					<c:otherwise>
						<portlet:actionURL name="subscribe" var="subscribeURL">
							<portlet:param name="redirect" value="<%= currentURL %>" />
							<portlet:param name="resourcePrimKey" value="<%= String.valueOf(article.getResourcePrimKey()) %>" />
						</portlet:actionURL>

						<liferay-ui:icon
							image="subscribe"
							label="<%= true %>"
							url="<%= subscribeURL %>"
						/>
					</c:otherwise>
				</c:choose>
			</c:if>
		</liferay-ui:search-container-column-text>
	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator />

	<c:if test="<%= results.isEmpty() && (total == 0) %>">
		<liferay-ui:search-paginator searchContainer="<%= searchContainer %>" />
	</c:if>
</liferay-ui:search-container>