<%--
/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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
int status = (Integer)request.getAttribute(WebKeys.KNOWLEDGE_BASE_STATUS);

KBArticle kbArticle = (KBArticle)request.getAttribute(WebKeys.KNOWLEDGE_BASE_KB_ARTICLE);
%>

<div class="kb-article-tools">
	<table class="lfr-table">
	<tr>
		<c:if test="<%= (kbArticle.isApproved() || !kbArticle.isFirstVersion()) && !Validator.equals(portletDisplay.getRootPortletId(), PortletKeys.KNOWLEDGE_BASE_ADMIN) %>">
			<td>
				<liferay-portlet:resourceURL id="kbArticleRSS" var="kbArticleRSSURL">
					<portlet:param name="resourcePrimKey" value="<%= String.valueOf(kbArticle.getResourcePrimKey()) %>" />
					<portlet:param name="rssDelta" value="<%= String.valueOf(rssDelta) %>" />
					<portlet:param name="rssDisplayStyle" value="<%= rssDisplayStyle %>" />
					<portlet:param name="rssFormat" value="<%= rssFormat %>" />
				</liferay-portlet:resourceURL>

				<liferay-ui:icon
					image="rss"
					label="<%= true %>"
					method="get"
					target="_blank"
					url="<%= kbArticleRSSURL %>"
				/>
			</td>
		</c:if>

		<c:if test="<%= (kbArticle.isApproved() || !kbArticle.isFirstVersion()) && KBArticlePermission.contains(permissionChecker, kbArticle, ActionKeys.SUBSCRIBE) %>">
			<td>
				<c:choose>
					<c:when test="<%= SubscriptionLocalServiceUtil.isSubscribed(user.getCompanyId(), user.getUserId(), KBArticle.class.getName(), kbArticle.getResourcePrimKey()) %>">
						<liferay-portlet:actionURL name="unsubscribeKBArticle" var="unsubscribeKBArticleURL">
							<portlet:param name="redirect" value="<%= redirect %>" />
							<portlet:param name="resourcePrimKey" value="<%= String.valueOf(kbArticle.getResourcePrimKey()) %>" />
						</liferay-portlet:actionURL>

						<liferay-ui:icon
							image="unsubscribe"
							label="<%= true %>"
							url="<%= unsubscribeKBArticleURL %>"
						/>
					</c:when>
					<c:otherwise>
						<liferay-portlet:actionURL name="subscribeKBArticle" var="subscribeKBArticleURL">
							<portlet:param name="redirect" value="<%= redirect %>" />
							<portlet:param name="resourcePrimKey" value="<%= String.valueOf(kbArticle.getResourcePrimKey()) %>" />
						</liferay-portlet:actionURL>

						<liferay-ui:icon
							image="subscribe"
							label="<%= true %>"
							url="<%= subscribeKBArticleURL %>"
						/>
					</c:otherwise>
				</c:choose>
			</td>
		</c:if>

		<c:if test="<%= kbArticle.isApproved() || !kbArticle.isFirstVersion() %>">
			<td>
				<liferay-portlet:renderURL var="historyURL">
					<portlet:param name="jspPage" value='<%= jspPath + "history.jsp" %>' />
					<portlet:param name="resourcePrimKey" value="<%= String.valueOf(kbArticle.getResourcePrimKey()) %>" />
					<portlet:param name="status" value="<%= String.valueOf(status) %>" />
				</liferay-portlet:renderURL>

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
			<liferay-portlet:renderURL var="printURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
				<portlet:param name="jspPage" value='<%= jspPath + "print_article.jsp" %>' />
				<portlet:param name="resourcePrimKey" value="<%= String.valueOf(kbArticle.getResourcePrimKey()) %>" />
				<portlet:param name="status" value="<%= String.valueOf(status) %>" />
			</liferay-portlet:renderURL>

			<%
			String taglibURL = "javascript:var printKBArticleWindow = window.open('" + printURL + "', 'printKBArticle', 'directories=no,height=640,location=no,menubar=no,resizable=yes,scrollbars=yes,status=no,toolbar=no,width=680'); void(''); printKBArticleWindow.focus();";
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