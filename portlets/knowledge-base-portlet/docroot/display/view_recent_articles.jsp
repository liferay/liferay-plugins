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

<%@ include file="/display/init.jsp" %>

<%
String orderByCol = ParamUtil.getString(request, "orderByCol", "modified-date");
String orderByType = ParamUtil.getString(request, "orderByType", "desc");
%>

<liferay-util:include page="/display/top_links.jsp" servletContext="<%= application %>" />

<liferay-portlet:renderURL varImpl="iteratorURL">
	<portlet:param name="jspPage" value="/display/view_recent_articles.jsp" />
</liferay-portlet:renderURL>

<liferay-ui:search-container
	emptyResultsMessage="no-articles-were-found"
	iteratorURL="<%= iteratorURL %>"
	orderByCol="<%= orderByCol %>"
	orderByComparator="<%= KnowledgeBaseUtil.getArticleOrderByComparator(orderByCol, orderByType) %>"
	orderByType="<%= orderByType %>"
>
	<liferay-ui:search-container-results
		results="<%= ArticleServiceUtil.getGroupArticles(scopeGroupId, WorkflowConstants.STATUS_APPROVED, searchContainer.getStart(), searchContainer.getEnd(), searchContainer.getOrderByComparator()) %>"
		total="<%= ArticleServiceUtil.getGroupArticlesCount(scopeGroupId, WorkflowConstants.STATUS_APPROVED) %>"
	/>

	<%
	boolean administrator = DisplayPermission.contains(permissionChecker, scopeGroupId, ActionKeys.ADMINISTRATOR);
	%>

	<liferay-ui:search-container-row
		className="com.liferay.knowledgebase.model.Article"
		keyProperty="resourcePrimKey"
		modelVar="article"
	>
		<liferay-portlet:renderURL varImpl="rowURL">
			<portlet:param name="jspPage" value="/display/view_article.jsp" />
			<portlet:param name="resourcePrimKey" value="<%= String.valueOf(article.getResourcePrimKey()) %>" />
		</liferay-portlet:renderURL>

		<liferay-ui:search-container-column-text
			href="<%= rowURL %>"
			orderable="<%= true %>"
			property="title"
		/>

		<liferay-ui:search-container-column-text
			href="<%= rowURL %>"
			name="author"
			orderable="<%= true %>"
			orderableProperty="user-name"
			property="userName"
		/>

		<liferay-ui:search-container-column-text
			cssClass="kb-column-no-wrap"
			href="<%= rowURL %>"
			name="create-date"
			orderable="<%= true %>"
			value='<%= dateFormatDate.format(article.getCreateDate()) + "<br />" + dateFormatTime.format(article.getCreateDate()) %>'
		/>

		<liferay-ui:search-container-column-text
			cssClass="kb-column-no-wrap"
			href="<%= rowURL %>"
			name="modified-date"
			orderable="<%= true %>"
			value='<%= dateFormatDate.format(article.getModifiedDate()) + "<br />" + dateFormatTime.format(article.getModifiedDate()) %>'
		/>

		<c:if test="<%= administrator %>">
			<liferay-ui:search-container-column-text
				cssClass="kb-column-no-wrap"
				href="<%= rowURL %>"
				name="status"
				orderable="<%= true %>"
				value='<%= article.getStatus() + " (" + LanguageUtil.get(pageContext, WorkflowConstants.toLabel(article.getStatus())) + ")" %>'
			/>
		</c:if>

		<liferay-ui:search-container-column-text
			cssClass="kb-column-no-wrap"
			href="<%= rowURL %>"
			name="views"
			orderable="<%= true %>"
			orderableProperty="view-count"
			property="viewCount"
		/>

		<liferay-ui:search-container-column-jsp
			align="right"
			path="/display/article_action.jsp"
		/>
	</liferay-ui:search-container-row>

	<aui:button-row cssClass="float-container">
		<c:if test="<%= DisplayPermission.contains(permissionChecker, scopeGroupId, ActionKeys.ADD_ARTICLE) && DisplayPermission.contains(permissionChecker, scopeGroupId, ActionKeys.ADMINISTRATOR) %>">
			<liferay-portlet:renderURL var="addArticleURL">
				<portlet:param name="jspPage" value="/display/edit_article.jsp" />
				<portlet:param name="redirect" value="<%= currentURL %>" />
			</liferay-portlet:renderURL>

			<aui:button onClick="<%= addArticleURL %>" value="add-article" />
		</c:if>

		<c:if test="<%= DisplayPermission.contains(permissionChecker, scopeGroupId, ActionKeys.PERMISSIONS) && GroupPermissionUtil.contains(permissionChecker, scopeGroupId, ActionKeys.PERMISSIONS) %>">
			<liferay-security:permissionsURL
				modelResource="com.liferay.knowledgebase.display"
				modelResourceDescription="<%= HtmlUtil.escape(themeDisplay.getScopeGroupName()) %>"
				resourcePrimKey="<%= String.valueOf(scopeGroupId) %>"
				var="permissionsURL"
			/>

			<aui:button onClick="<%= permissionsURL %>" value="permissions" />
		</c:if>

		<div class="kb-display-tools">
			<table class="lfr-table">
			<tr>
				<td>
					<liferay-portlet:resourceURL id="groupArticlesRSS" var="groupArticlesRSSURL">
						<portlet:param name="rssDelta" value="<%= String.valueOf(rssDelta) %>" />
						<portlet:param name="rssDisplayStyle" value="<%= rssDisplayStyle %>" />
						<portlet:param name="rssFormat" value="<%= rssFormat %>" />
					</liferay-portlet:resourceURL>

					<liferay-ui:icon
						image="rss"
						label="<%= true %>"
						method="get"
						target="_blank"
						url="<%= groupArticlesRSSURL %>"
					/>
				</td>

				<c:if test="<%= DisplayPermission.contains(permissionChecker, scopeGroupId, ActionKeys.SUBSCRIBE) %>">
					<td>
						<c:choose>
							<c:when test="<%= SubscriptionLocalServiceUtil.isSubscribed(user.getCompanyId(), user.getUserId(), Article.class.getName(), scopeGroupId) %>">
								<liferay-portlet:actionURL name="unsubscribeGroupArticles" var="unsubscribeGroupArticlesURL">
									<portlet:param name="redirect" value="<%= currentURL %>" />
								</liferay-portlet:actionURL>

								<liferay-ui:icon
									image="unsubscribe"
									label="<%= true %>"
									url="<%= unsubscribeGroupArticlesURL %>"
								/>
							</c:when>
							<c:otherwise>
								<liferay-portlet:actionURL name="subscribeGroupArticles" var="subscribeGroupArticlesURL">
									<portlet:param name="redirect" value="<%= currentURL %>" />
								</liferay-portlet:actionURL>

								<liferay-ui:icon
									image="subscribe"
									label="<%= true %>"
									url="<%= subscribeGroupArticlesURL %>"
								/>
							</c:otherwise>
						</c:choose>
					</td>
				</c:if>
			</tr>
			</table>
		</div>
	</aui:button-row>

	<liferay-ui:search-iterator />
</liferay-ui:search-container>