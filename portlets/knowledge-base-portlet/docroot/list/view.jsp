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

<c:choose>
	<c:when test="<%= Validator.isNotNull(articlesTitle) %>">
		<liferay-ui:panel-container extended="<%= false %>" id='<%= renderResponse.getNamespace() + "ArticlesPanelContainer" %>' persistState="<%= true %>">
			<liferay-ui:panel collapsible="<%= true %>" extended="<%= true %>" id='<%= renderResponse.getNamespace() + "ArticlesPanel" %>' cssClass="kb-articles-panel" persistState="<%= true %>" title="<%= articlesTitle %>">
				<liferay-util:include page="/list/view_articles.jsp" servletContext="<%= application %>" />
			</liferay-ui:panel>
		</liferay-ui:panel-container>
	</c:when>
	<c:otherwise>
		<liferay-util:include page="/list/view_articles.jsp" servletContext="<%= application %>" />
	</c:otherwise>
</c:choose>

<div class="separator"><!-- --></div>

<div>
	<portlet:resourceURL id="rss" var="rssURL">
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

		if (SubscriptionLocalServiceUtil.isSubscribed(user.getCompanyId(), user.getUserId(), Article.class.getName(), scopeGroupId)) {
			Subscription subscription = SubscriptionLocalServiceUtil.getSubscription(user.getCompanyId(), user.getUserId(), Article.class.getName(), scopeGroupId);

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
				</portlet:actionURL>

				<liferay-ui:icon
					cssClass="top-link last"
					image="subscribe"
					label="<%= true %>"
					url="<%= subscribeURL %>"
				/>
			</c:otherwise>
		</c:choose>
	</c:if>
</div>