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
		<liferay-portlet:renderURL var="rowURL">
			<portlet:param name="jspPage" value="/article/view_article.jsp" />
			<portlet:param name="resourcePrimKey" value="<%= String.valueOf(kbArticle.getResourcePrimKey()) %>" />
		</liferay-portlet:renderURL>

		<%
		rowURL = "var kbArticleWindow = window.open('" + rowURL + "', 'kbArticle', 'directories=no,height=640,location=no,menubar=no,resizable=yes,scrollbars=yes,status=no,toolbar=no,width=680'); void(''); kbArticleWindow.focus();";
		%>

		<liferay-ui:search-container-column-text
			orderable="<%= true %>"
			name="title"
		>
			<aui:a href="javascript:;" onClick="<%= rowURL %>"><%= kbArticle.getTitle() %></aui:a>
		</liferay-ui:search-container-column-text>

		<liferay-ui:search-container-column-text
			name="author"
			orderable="<%= true %>"
			orderableProperty="user-name"
		>
			<aui:a href="javascript:;" onClick="<%= rowURL %>"><%= kbArticle.getUserName() %></aui:a>
		</liferay-ui:search-container-column-text>

		<liferay-ui:search-container-column-text
			cssClass="kb-column-no-wrap"
			name="create-date"
			orderable="<%= true %>"
		>
			<aui:a href="javascript:;" onClick="<%= rowURL %>"><%= dateFormatDate.format(kbArticle.getCreateDate()) + "<br />" + dateFormatTime.format(kbArticle.getCreateDate()) %></aui:a>
		</liferay-ui:search-container-column-text>

		<liferay-ui:search-container-column-text
			cssClass="kb-column-no-wrap"
			name="modified-date"
			orderable="<%= true %>"
		>
			<aui:a href="javascript:;" onClick="<%= rowURL %>"><%= dateFormatDate.format(kbArticle.getModifiedDate()) + "<br />" + dateFormatTime.format(kbArticle.getModifiedDate()) %></aui:a>
		</liferay-ui:search-container-column-text>

		<liferay-ui:search-container-column-text
			cssClass="kb-column-no-wrap"
			name="status"
			orderable="<%= true %>"
		>
			<aui:a href="javascript:;" onClick="<%= rowURL %>"><%= kbArticle.getStatus() + " (" + LanguageUtil.get(pageContext, WorkflowConstants.toLabel(kbArticle.getStatus())) + ")" %></aui:a>
		</liferay-ui:search-container-column-text>

		<liferay-ui:search-container-column-text
			cssClass="kb-column-no-wrap"
			name="views"
			orderable="<%= true %>"
			orderableProperty="view-count"
		>
			<aui:a href="javascript:;" onClick="<%= rowURL %>"><%= kbArticle.getViewCount() %></aui:a>
		</liferay-ui:search-container-column-text>

		<liferay-ui:search-container-column-text
			align="right"
		>

			<%
			String taglibOnClick = "opener." + PortalUtil.getPortletNamespace(PortletKeys.PORTLET_CONFIGURATION) + "selectConfigurationKBArticle('" + kbArticle.getResourcePrimKey() + "', '" + UnicodeFormatter.toString(kbArticle.getTitle()) + "'); window.close();";
			%>

			<aui:button onClick="<%= taglibOnClick %>" value="choose" />
		</liferay-ui:search-container-column-text>
	</liferay-ui:search-container-row>

	<c:if test="<%= selResourcePrimKey > 0 %>">

		<%
		KBArticle selKBArticle = null;

		try {
			selKBArticle = KBArticleLocalServiceUtil.getLatestKBArticle(selResourcePrimKey, WorkflowConstants.STATUS_APPROVED);
		}
		catch (NoSuchArticleException nsae) {
		}
		%>

		<c:if test="<%= selKBArticle != null %>">
			<aui:button-row>
				<%= selKBArticle.getTitle() %>

				<%
				String taglibOnClick = "opener." + PortalUtil.getPortletNamespace(PortletKeys.PORTLET_CONFIGURATION) + "selectConfigurationKBArticle('0', ''); window.close();";
				%>

				<aui:button onClick="<%= taglibOnClick %>" value="remove" />
			</aui:button-row>

			<div class="separator"><!-- --></div>
		</c:if>
	</c:if>

	<liferay-ui:search-iterator />
</liferay-ui:search-container>