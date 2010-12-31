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

<%@ include file="/admin/init.jsp" %>

<%
Article article = (Article)request.getAttribute(WebKeys.KNOWLEDGE_BASE_ARTICLE);
%>

<div class="kb-article-tools">
	<table class="lfr-table">
	<tr>
		<td>
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
		</td>

		<c:if test="<%= ArticlePermission.contains(permissionChecker, article, ActionKeys.SUBSCRIBE) %>">
			<td>

				<%
				boolean subscribed = false;

				if (SubscriptionLocalServiceUtil.isSubscribed(user.getCompanyId(), user.getUserId(), Article.class.getName(), article.getResourcePrimKey())) {
					Subscription subscription = SubscriptionLocalServiceUtil.getSubscription(user.getCompanyId(), user.getUserId(), Article.class.getName(), article.getResourcePrimKey());

					String[] portletIds = ExpandoValueLocalServiceUtil.getData(user.getCompanyId(), Subscription.class.getName(), "KB", "portletIds", subscription.getSubscriptionId(), new String[0]);

					if (ArrayUtil.contains(portletIds, portletDisplay.getId())) {
						subscribed = true;
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
			</td>
		</c:if>

		<td>
			<portlet:renderURL var="historyURL">
				<portlet:param name="jspPage" value='<%= jspPath + "history.jsp" %>' />
				<portlet:param name="resourcePrimKey" value="<%= String.valueOf(article.getResourcePrimKey()) %>" />
			</portlet:renderURL>

			<liferay-ui:icon
				image="recent_changes"
				label="<%= true %>"
				message="history"
				method="get"
				url="<%= historyURL %>"
			/>
		</td>
		<td>
			<portlet:renderURL var="printURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
				<portlet:param name="jspPage" value='<%= jspPath + "print_article.jsp" %>' />
				<portlet:param name="resourcePrimKey" value="<%= String.valueOf(article.getResourcePrimKey()) %>" />
			</portlet:renderURL>

			<%
			String taglibURL = "javascript:var printArticleWindow = window.open('" + printURL + "', 'printArticle', 'directories=no,height=640,location=no,menubar=no,resizable=yes,scrollbars=yes,status=no,toolbar=no,width=680'); void(''); printArticleWindow.focus();";
			%>

			<liferay-ui:icon
				image="print"
				label="<%= true %>"
				method="get"
				url="<%= taglibURL %>"
			/>
		</td>
	</tr>
	</table>
</div>