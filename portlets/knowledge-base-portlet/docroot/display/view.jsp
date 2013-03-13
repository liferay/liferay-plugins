<%--
/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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

<liferay-util:include page="/display/top_links.jsp" servletContext="<%= application %>" />

<liferay-portlet:renderURL varImpl="iteratorURL">
	<portlet:param name="mvcPath" value="/display/view.jsp" />
</liferay-portlet:renderURL>

<liferay-ui:search-container
	searchContainer="<%= new KBArticleSearch(renderRequest, iteratorURL) %>"
>
	<liferay-ui:search-container-results
		results="<%= KBArticleServiceUtil.getSiblingKBArticles(scopeGroupId, KBArticleConstants.DEFAULT_PARENT_RESOURCE_PRIM_KEY, WorkflowConstants.STATUS_APPROVED, searchContainer.getStart(), searchContainer.getEnd(), searchContainer.getOrderByComparator()) %>"
		total="<%= KBArticleServiceUtil.getSiblingKBArticlesCount(scopeGroupId, KBArticleConstants.DEFAULT_PARENT_RESOURCE_PRIM_KEY, WorkflowConstants.STATUS_APPROVED) %>"
	/>

	<liferay-ui:search-container-row
		className="com.liferay.knowledgebase.model.KBArticle"
		keyProperty="resourcePrimKey"
		modelVar="kbArticle"
	>
		<liferay-portlet:renderURL varImpl="rowURL">
			<portlet:param name="mvcPath" value="/display/view_article.jsp" />
			<portlet:param name="resourcePrimKey" value="<%= String.valueOf(kbArticle.getResourcePrimKey()) %>" />
		</liferay-portlet:renderURL>

		<c:if test="<%= showKBArticlePriorityColumn %>">
			<liferay-ui:search-container-column-text
				cssClass="kb-column-no-wrap"
				href="<%= rowURL %>"
				name="priority"
				orderable="<%= true %>"
				value="<%= BigDecimal.valueOf(kbArticle.getPriority()).toPlainString() %>"
			/>
		</c:if>

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

	<liferay-util:include page="/display/display_tools.jsp" servletContext="<%= application %>" />

	<aui:button-row cssClass="float-container">
		<c:if test="<%= DisplayPermission.contains(permissionChecker, scopeGroupId, ActionKeys.ADD_KB_ARTICLE) && DisplayPermission.contains(permissionChecker, scopeGroupId, ActionKeys.ADMINISTRATOR) %>">
			<liferay-portlet:renderURL var="addKBArticleURL">
				<portlet:param name="mvcPath" value="/display/edit_article.jsp" />
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
	</aui:button-row>

	<liferay-ui:search-iterator />
</liferay-ui:search-container>