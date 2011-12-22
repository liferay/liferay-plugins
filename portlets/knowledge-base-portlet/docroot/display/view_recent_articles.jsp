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
	orderByComparator="<%= KnowledgeBaseUtil.getKBArticleOrderByComparator(orderByCol, orderByType) %>"
	orderByType="<%= orderByType %>"
>
	<liferay-ui:search-container-results
		results="<%= KBArticleServiceUtil.getGroupKBArticles(scopeGroupId, WorkflowConstants.STATUS_APPROVED, searchContainer.getStart(), searchContainer.getEnd(), searchContainer.getOrderByComparator()) %>"
		total="<%= KBArticleServiceUtil.getGroupKBArticlesCount(scopeGroupId, WorkflowConstants.STATUS_APPROVED) %>"
	/>

	<liferay-ui:search-container-row
		className="com.liferay.knowledgebase.model.KBArticle"
		keyProperty="resourcePrimKey"
		modelVar="kbArticle"
	>
		<liferay-portlet:renderURL varImpl="rowURL">
			<portlet:param name="jspPage" value="/display/view_article.jsp" />
			<portlet:param name="resourcePrimKey" value="<%= String.valueOf(kbArticle.getResourcePrimKey()) %>" />
		</liferay-portlet:renderURL>

		<liferay-ui:search-container-column-text
			href="<%= rowURL %>"
			orderable="<%= true %>"
			property="title"
		/>

		<c:if test="<%= showKBArticleAuthorColumn %>">
			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="author"
				orderable="<%= true %>"
				orderableProperty="user-name"
				property="userName"
			/>
		</c:if>

		<c:if test="<%= showKBArticleCreateDateColumn %>">
			<liferay-ui:search-container-column-text
				cssClass="kb-column-no-wrap"
				href="<%= rowURL %>"
				name="create-date"
				orderable="<%= true %>"
				value='<%= dateFormatDate.format(kbArticle.getCreateDate()) + "<br />" + dateFormatTime.format(kbArticle.getCreateDate()) %>'
			/>
		</c:if>

		<c:if test="<%= showKBArticleModifiedDateColumn %>">
			<liferay-ui:search-container-column-text
				cssClass="kb-column-no-wrap"
				href="<%= rowURL %>"
				name="modified-date"
				orderable="<%= true %>"
				value='<%= dateFormatDate.format(kbArticle.getModifiedDate()) + "<br />" + dateFormatTime.format(kbArticle.getModifiedDate()) %>'
			/>
		</c:if>

		<c:if test="<%= showKBArticleStatusColumn && DisplayPermission.contains(permissionChecker, scopeGroupId, ActionKeys.ADMINISTRATOR) %>">
			<liferay-ui:search-container-column-text
				cssClass="kb-column-no-wrap"
				href="<%= rowURL %>"
				name="status"
				orderable="<%= true %>"
				value='<%= kbArticle.getStatus() + " (" + LanguageUtil.get(pageContext, WorkflowConstants.toLabel(kbArticle.getStatus())) + ")" %>'
			/>
		</c:if>

		<c:if test="<%= showKBArticleViewsColumn %>">
			<liferay-ui:search-container-column-text
				cssClass="kb-column-no-wrap"
				href="<%= rowURL %>"
				name="views"
				orderable="<%= true %>"
				orderableProperty="view-count"
				property="viewCount"
			/>
		</c:if>

		<liferay-ui:search-container-column-jsp
			align="right"
			path="/display/article_action.jsp"
		/>
	</liferay-ui:search-container-row>

	<aui:button-row cssClass="float-container">
		<c:if test="<%= DisplayPermission.contains(permissionChecker, scopeGroupId, ActionKeys.ADD_KB_ARTICLE) && DisplayPermission.contains(permissionChecker, scopeGroupId, ActionKeys.ADMINISTRATOR) %>">
			<liferay-portlet:renderURL var="addKBArticleURL">
				<portlet:param name="jspPage" value="/display/edit_article.jsp" />
				<portlet:param name="redirect" value="<%= redirect %>" />
			</liferay-portlet:renderURL>

			<aui:button href="<%= addKBArticleURL %>" value="add-article" />
		</c:if>

		<c:if test="<%= DisplayPermission.contains(permissionChecker, scopeGroupId, ActionKeys.PERMISSIONS) && GroupPermissionUtil.contains(permissionChecker, scopeGroupId, ActionKeys.PERMISSIONS) %>">
			<liferay-security:permissionsURL
				modelResource="com.liferay.knowledgebase.display"
				modelResourceDescription="<%= HtmlUtil.escape(themeDisplay.getScopeGroupName()) %>"
				resourcePrimKey="<%= String.valueOf(scopeGroupId) %>"
				var="permissionsURL"
			/>

			<aui:button href="<%= permissionsURL %>" value="permissions" />
		</c:if>

		<div class="kb-display-tools">
			<table class="lfr-table">
			<tr>
				<td>
					<liferay-portlet:resourceURL id="groupKBArticlesRSS" var="groupKBArticlesRSSURL">
						<portlet:param name="rssDelta" value="<%= String.valueOf(rssDelta) %>" />
						<portlet:param name="rssDisplayStyle" value="<%= rssDisplayStyle %>" />
						<portlet:param name="rssFormat" value="<%= rssFormat %>" />
					</liferay-portlet:resourceURL>

					<liferay-ui:icon
						image="rss"
						label="<%= true %>"
						method="get"
						target="_blank"
						url="<%= groupKBArticlesRSSURL %>"
					/>
				</td>

				<c:if test="<%= DisplayPermission.contains(permissionChecker, scopeGroupId, ActionKeys.SUBSCRIBE) %>">
					<td>
						<c:choose>
							<c:when test="<%= SubscriptionLocalServiceUtil.isSubscribed(user.getCompanyId(), user.getUserId(), KBArticle.class.getName(), scopeGroupId) %>">
								<liferay-portlet:actionURL name="unsubscribeGroupKBArticles" var="unsubscribeGroupKBArticlesURL">
									<portlet:param name="redirect" value="<%= redirect %>" />
								</liferay-portlet:actionURL>

								<liferay-ui:icon
									image="unsubscribe"
									label="<%= true %>"
									url="<%= unsubscribeGroupKBArticlesURL %>"
								/>
							</c:when>
							<c:otherwise>
								<liferay-portlet:actionURL name="subscribeGroupKBArticles" var="subscribeGroupKBArticlesURL">
									<portlet:param name="redirect" value="<%= redirect %>" />
								</liferay-portlet:actionURL>

								<liferay-ui:icon
									image="subscribe"
									label="<%= true %>"
									url="<%= subscribeGroupKBArticlesURL %>"
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