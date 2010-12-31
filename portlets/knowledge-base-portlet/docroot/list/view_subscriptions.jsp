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

<liferay-util:include page="/list/top_links.jsp" servletContext="<%= application %>" />

<liferay-portlet:renderURL varImpl="iteratorURL" />

<liferay-ui:search-container
	delta="<%= articlesDelta %>"
	headerNames="title,portlets,version"
	iteratorURL="<%= iteratorURL %>"
>
	<liferay-ui:search-container-results>

		<%
		List<Subscription> subscriptions = SubscriptionLocalServiceUtil.getUserSubscriptions(user.getUserId(), Article.class.getName());

		long[] classPKs = StringUtil.split(ListUtil.toString(subscriptions, "classPK"), 0L);
		long[] viewableParentResourcePrimKeys = ArticleServiceUtil.getViewableParentResourcePrimKeys(scopeGroupId, WorkflowConstants.STATUS_APPROVED);

		pageContext.setAttribute("results", ArticleServiceUtil.getArticles(scopeGroupId, classPKs, WorkflowConstants.STATUS_APPROVED, viewableParentResourcePrimKeys, searchContainer.getStart(), searchContainer.getEnd(), orderByComparator));
		pageContext.setAttribute("total", ArticleServiceUtil.getArticlesCount(scopeGroupId, classPKs, WorkflowConstants.STATUS_APPROVED, viewableParentResourcePrimKeys));
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

		<%
		Subscription subscription = SubscriptionLocalServiceUtil.getSubscription(user.getCompanyId(), user.getUserId(), Article.class.getName(), article.getResourcePrimKey());

		String[] portletIds = ExpandoValueLocalServiceUtil.getData(user.getCompanyId(), Subscription.class.getName(), "KB", "portletIds", subscription.getSubscriptionId(), new String[0]);

		List<String> portletTitles = new ArrayList<String>();

		for (String portletId : portletIds) {
			PortletPreferences jxPreferences = PortletPreferencesFactoryUtil.getPortletSetup(themeDisplay.getLayout(), portletId, StringPool.BLANK);

			String portletTitle = PortletConfigurationUtil.getPortletTitle(jxPreferences, themeDisplay.getLanguageId());

			if (Validator.isNull(portletTitle)) {
				portletTitle = PortalUtil.getPortletTitle(PortletConstants.getRootPortletId(portletId), locale);
			}

			if (!portletTitles.contains(portletTitle)) {
				portletTitles.add(portletTitle);
			}
		}

		Collections.sort(portletTitles);
		%>

		<liferay-ui:search-container-column-text
			href="<%= rowURL %>"
			name="portlets"
			value="<%= StringUtil.merge(portletTitles, StringPool.COMMA_AND_SPACE) %>"
		/>

		<c:if test="<%= themeDisplay.isSignedIn() %>">
			<liferay-ui:search-container-column-text
				align="right"
			>
				<portlet:actionURL name="unsubscribeAllPortlets" var="unsubscribeAllPortletsURL">
					<portlet:param name="redirect" value="<%= currentURL %>" />
					<portlet:param name="subscriptionId" value="<%= String.valueOf(subscription.getSubscriptionId()) %>" />
				</portlet:actionURL>

				<liferay-ui:icon
					image="unsubscribe"
					label="<%= true %>"
					url="<%= unsubscribeAllPortletsURL %>"
				/>
			</liferay-ui:search-container-column-text>
		</c:if>
	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator />

	<c:if test="<%= results.isEmpty() && (total == 0) %>">
		<liferay-ui:search-paginator searchContainer="<%= searchContainer %>" />
	</c:if>
</liferay-ui:search-container>