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

<%@ include file="/article/init.jsp" %>

<%
long selResourcePrimKey = ParamUtil.getLong(request, "selResourcePrimKey");

String orderByCol = ParamUtil.getString(request, "orderByCol", "modified-date");
String orderByType = ParamUtil.getString(request, "orderByType", "desc");
%>

<liferay-ui:header
	title="article"
/>

<liferay-portlet:renderURL varImpl="iteratorURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
	<portlet:param name="jspPage" value="/article/select_configuration_article.jsp" />
</liferay-portlet:renderURL>

<liferay-ui:search-container
	emptyResultsMessage="there-are-no-articles"
	iteratorURL="<%= iteratorURL %>"
	orderByCol="<%= orderByCol %>"
	orderByComparator="<%= KnowledgeBaseUtil.getArticleOrderByComparator(orderByCol, orderByType) %>"
	orderByType="<%= orderByType %>"
>
	<liferay-ui:search-container-results
		results="<%= ArticleServiceUtil.getGroupArticles(scopeGroupId, WorkflowConstants.STATUS_APPROVED, searchContainer.getStart(), searchContainer.getEnd(), searchContainer.getOrderByComparator()) %>"
		total="<%= ArticleServiceUtil.getGroupArticlesCount(scopeGroupId, WorkflowConstants.STATUS_APPROVED) %>"
	/>

	<liferay-ui:search-container-row
		className="com.liferay.knowledgebase.model.Article"
		keyProperty="resourcePrimKey"
		modelVar="article"
	>
		<liferay-portlet:renderURL var="rowURL">
			<portlet:param name="jspPage" value="/article/view_article.jsp" />
			<portlet:param name="resourcePrimKey" value="<%= String.valueOf(article.getResourcePrimKey()) %>" />
		</liferay-portlet:renderURL>

		<%
		rowURL = "var articleWindow = window.open('" + rowURL + "', 'article', 'directories=no,height=640,location=no,menubar=no,resizable=yes,scrollbars=yes,status=no,toolbar=no,width=680'); void(''); articleWindow.focus();";
		%>

		<liferay-ui:search-container-column-text
			orderable="<%= true %>"
			name="title"
		>
			<aui:a href="javascript:;" onClick="<%= rowURL %>"><%= article.getTitle() %></aui:a>
		</liferay-ui:search-container-column-text>

		<liferay-ui:search-container-column-text
			name="author"
			orderable="<%= true %>"
			orderableProperty="user-name"
		>
			<aui:a href="javascript:;" onClick="<%= rowURL %>"><%= article.getUserName() %></aui:a>
		</liferay-ui:search-container-column-text>

		<liferay-ui:search-container-column-text
			cssClass="kb-column-no-wrap"
			name="create-date"
			orderable="<%= true %>"
		>
			<aui:a href="javascript:;" onClick="<%= rowURL %>"><%= dateFormatDate.format(article.getCreateDate()) + "<br />" + dateFormatTime.format(article.getCreateDate()) %></aui:a>
		</liferay-ui:search-container-column-text>

		<liferay-ui:search-container-column-text
			cssClass="kb-column-no-wrap"
			name="modified-date"
			orderable="<%= true %>"
		>
			<aui:a href="javascript:;" onClick="<%= rowURL %>"><%= dateFormatDate.format(article.getModifiedDate()) + "<br />" + dateFormatTime.format(article.getModifiedDate()) %></aui:a>
		</liferay-ui:search-container-column-text>

		<liferay-ui:search-container-column-text
			cssClass="kb-column-no-wrap"
			name="status"
			orderable="<%= true %>"
		>
			<aui:a href="javascript:;" onClick="<%= rowURL %>"><%= article.getStatus() + " (" + LanguageUtil.get(pageContext, WorkflowConstants.toLabel(article.getStatus())) + ")" %></aui:a>
		</liferay-ui:search-container-column-text>

		<liferay-ui:search-container-column-text
			cssClass="kb-column-no-wrap"
			name="views"
			orderable="<%= true %>"
			orderableProperty="view-count"
		>
			<aui:a href="javascript:;" onClick="<%= rowURL %>"><%= article.getViewCount() %></aui:a>
		</liferay-ui:search-container-column-text>

		<liferay-ui:search-container-column-text
			align="right"
		>

			<%
			String taglibOnClick = "opener." + PortalUtil.getPortletNamespace(PortletKeys.PORTLET_CONFIGURATION) + "selectConfigurationArticle('" + article.getResourcePrimKey() + "', '" + UnicodeFormatter.toString(article.getTitle()) + "'); window.close();";
			%>

			<aui:button onClick="<%= taglibOnClick %>" value="choose" />
		</liferay-ui:search-container-column-text>
	</liferay-ui:search-container-row>

	<c:if test="<%= selResourcePrimKey > 0 %>">

		<%
		Article selArticle = null;

		try {
			selArticle = ArticleLocalServiceUtil.getLatestArticle(selResourcePrimKey, WorkflowConstants.STATUS_APPROVED);
		}
		catch (NoSuchArticleException nsae) {
		}
		%>

		<c:if test="<%= selArticle != null %>">
			<aui:button-row>
				<%= selArticle.getTitle() %>

				<%
				String taglibOnClick = "opener." + PortalUtil.getPortletNamespace(PortletKeys.PORTLET_CONFIGURATION) + "selectConfigurationArticle('0', ''); window.close();";
				%>

				<aui:button onClick="<%= taglibOnClick %>" value="remove" />
			</aui:button-row>

			<div class="separator"><!-- --></div>
		</c:if>
	</c:if>

	<liferay-ui:search-iterator />
</liferay-ui:search-container>