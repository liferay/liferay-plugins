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
Integer status = (Integer)request.getAttribute(WebKeys.KNOWLEDGE_BASE_STATUS);

Article article = (Article)request.getAttribute(WebKeys.KNOWLEDGE_BASE_ARTICLE);
%>

<div class="kb-article-tools">
	<table class="lfr-table">
	<tr>
		<c:if test="<%= (article.isApproved() || !article.isFirstVersion()) && !Validator.equals(portletDisplay.getRootPortletId(), PortletKeys.KNOWLEDGE_BASE_ADMIN) %>">
			<td>
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
			</td>
		</c:if>

		<c:if test="<%= (article.isApproved() || !article.isFirstVersion()) && ArticlePermission.contains(permissionChecker, article, ActionKeys.SUBSCRIBE) %>">
			<td>
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
			</td>
		</c:if>

		<c:if test="<%= article.isApproved() || !article.isFirstVersion() %>">
			<td>
				<portlet:renderURL var="historyURL">
					<portlet:param name="jspPage" value='<%= portletConfig.getInitParameter("jsp-path") + "history.jsp" %>' />
					<portlet:param name="resourcePrimKey" value="<%= String.valueOf(article.getResourcePrimKey()) %>" />
					<portlet:param name="status" value="<%= String.valueOf(status.intValue()) %>" />
				</portlet:renderURL>

				<liferay-ui:icon
					image="recent_changes"
					label="<%= true %>"
					message="history"
					method="get"
					url="<%= historyURL %>"
				/>
			</td>
		</c:if>

		<td>
			<portlet:renderURL var="printURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
				<portlet:param name="jspPage" value='<%= portletConfig.getInitParameter("jsp-path") + "print_article.jsp" %>' />
				<portlet:param name="resourcePrimKey" value="<%= String.valueOf(article.getResourcePrimKey()) %>" />
				<portlet:param name="status" value="<%= String.valueOf(status.intValue()) %>" />
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