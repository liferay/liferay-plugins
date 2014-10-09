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

<%@ include file="/admin/init.jsp" %>

<%
KBArticle kbArticle = (KBArticle)request.getAttribute(WebKeys.KNOWLEDGE_BASE_KB_ARTICLE);

int status = (Integer)request.getAttribute(WebKeys.KNOWLEDGE_BASE_STATUS);
%>

<div class="kb-article-tools">
	<c:if test="<%= PortletPropsValues.KNOWLEDGE_BASE_SOURCE_URL_ENABLED && Validator.isUrl(kbArticle.getSourceURL()) %>">
		<a href="<%= kbArticle.getSourceURL() %>" target="_blank">
			<span class="kb-article-source-url label label-success">
				<liferay-ui:message key="<%= PortletPropsValues.KNOWLEDGE_BASE_SOURCE_URL_EDIT_MESSAGE_KEY %>" />
			</span>
		</a>
	</c:if>

	<c:if test="<%= enableRSS && (kbArticle.isApproved() || !kbArticle.isFirstVersion()) && !Validator.equals(portletDisplay.getRootPortletId(), PortletKeys.KNOWLEDGE_BASE_ADMIN) %>">
		<liferay-portlet:resourceURL id="kbArticleRSS" varImpl="kbArticleRSSURL">
			<portlet:param name="resourceClassNameId" value="<%= String.valueOf(kbArticle.getClassNameId()) %>" />
			<portlet:param name="resourcePrimKey" value="<%= String.valueOf(kbArticle.getResourcePrimKey()) %>" />
		</liferay-portlet:resourceURL>

		<liferay-ui:rss
			delta="<%= rssDelta %>"
			displayStyle="<%= rssDisplayStyle %>"
			feedType="<%= rssFeedType %>"
			resourceURL="<%= kbArticleRSSURL %>"
		/>
	</c:if>

	<c:if test="<%= enableKBArticleSubscriptions && (kbArticle.isApproved() || !kbArticle.isFirstVersion()) && KBArticlePermission.contains(permissionChecker, kbArticle, ActionKeys.SUBSCRIBE) %>">
		<c:choose>
			<c:when test="<%= SubscriptionLocalServiceUtil.isSubscribed(user.getCompanyId(), user.getUserId(), KBArticle.class.getName(), kbArticle.getResourcePrimKey()) %>">
				<liferay-portlet:actionURL name="unsubscribeKBArticle" var="unsubscribeKBArticleURL">
					<portlet:param name="redirect" value="<%= redirect %>" />
					<portlet:param name="resourceClassNameId" value="<%= String.valueOf(kbArticle.getClassNameId()) %>" />
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
					<portlet:param name="resourceClassNameId" value="<%= String.valueOf(kbArticle.getClassNameId()) %>" />
					<portlet:param name="resourcePrimKey" value="<%= String.valueOf(kbArticle.getResourcePrimKey()) %>" />
				</liferay-portlet:actionURL>

				<liferay-ui:icon
					image="subscribe"
					label="<%= true %>"
					url="<%= subscribeKBArticleURL %>"
				/>
			</c:otherwise>
		</c:choose>
	</c:if>

	<c:if test="<%= enableKBArticleHistory && (kbArticle.isApproved() || !kbArticle.isFirstVersion()) %>">
		<liferay-portlet:renderURL var="historyURL">
			<portlet:param name="mvcPath" value='<%= templatePath + "history.jsp" %>' />
			<portlet:param name="resourceClassNameId" value="<%= String.valueOf(kbArticle.getClassNameId()) %>" />
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
	</c:if>

	<c:if test="<%= enableKBArticlePrint %>">
		<liferay-portlet:renderURL var="printURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
			<portlet:param name="mvcPath" value='<%= templatePath + "print_article.jsp" %>' />
			<portlet:param name="resourceClassNameId" value="<%= String.valueOf(kbArticle.getClassNameId()) %>" />
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
	</c:if>
</div>