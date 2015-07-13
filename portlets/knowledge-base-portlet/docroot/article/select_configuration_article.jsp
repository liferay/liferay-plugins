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

<%@ include file="/article/init.jsp" %>

<%
long selResourcePrimKey = ParamUtil.getLong(request, "selResourcePrimKey");

String orderByCol = ParamUtil.getString(request, "orderByCol", "modified-date");
String orderByType = ParamUtil.getString(request, "orderByType", "desc");

String portletId = PortletProviderUtil.getPortletId(PortletConfigurationApplicationType.PortletConfiguration.CLASS_NAME, PortletProvider.Action.VIEW);
%>

<liferay-ui:header
	title="article"
/>

<liferay-portlet:renderURL varImpl="iteratorURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
	<portlet:param name="mvcPath" value="/article/select_configuration_article.jsp" />
</liferay-portlet:renderURL>

<liferay-ui:search-container
	emptyResultsMessage="there-are-no-articles"
	iteratorURL="<%= iteratorURL %>"
	orderByCol="<%= orderByCol %>"
	orderByComparator="<%= KnowledgeBaseUtil.getKBArticleOrderByComparator(orderByCol, orderByType) %>"
	orderByType="<%= orderByType %>"
	total="<%= KBArticleServiceUtil.getGroupKBArticlesCount(scopeGroupId, WorkflowConstants.STATUS_APPROVED) %>"
>
	<liferay-ui:search-container-results
		results="<%= KBArticleServiceUtil.getGroupKBArticles(scopeGroupId, WorkflowConstants.STATUS_APPROVED, searchContainer.getStart(), searchContainer.getEnd(), searchContainer.getOrderByComparator()) %>"
	/>

	<liferay-ui:search-container-row
		className="com.liferay.knowledgebase.model.KBArticle"
		escapedModel="<%= true %>"
		keyProperty="resourcePrimKey"
		modelVar="kbArticle"
	>
		<liferay-portlet:renderURL var="rowURL">
			<portlet:param name="mvcPath" value="/article/print_article.jsp" />
			<portlet:param name="resourcePrimKey" value="<%= String.valueOf(kbArticle.getResourcePrimKey()) %>" />
		</liferay-portlet:renderURL>

		<%
		rowURL = "javascript:var printKBArticleWindow = window.open('" + rowURL + "', 'printKBArticle', 'directories=no,height=640,location=no,menubar=no,resizable=yes,scrollbars=yes,status=no,toolbar=no,width=680'); void(''); printKBArticleWindow.focus();";
		%>

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

		<liferay-ui:search-container-column-date
			cssClass="kb-column-no-wrap"
			href="<%= rowURL %>"
			name="create-date"
			orderable="<%= true %>"
			value="<%= kbArticle.getCreateDate() %>"
		/>

		<liferay-ui:search-container-column-date
			cssClass="kb-column-no-wrap"
			href="<%= rowURL %>"
			name="modified-date"
			orderable="<%= true %>"
			value="<%= kbArticle.getModifiedDate() %>"
		/>

		<liferay-ui:search-container-column-text
			cssClass="kb-column-no-wrap"
			href="<%= rowURL %>"
			name="status"
			orderable="<%= true %>"
			value='<%= kbArticle.getStatus() + " (" + LanguageUtil.get(request, WorkflowConstants.getStatusLabel(kbArticle.getStatus())) + ")" %>'
		/>

		<liferay-ui:search-container-column-text
			cssClass="kb-column-no-wrap"
			href="<%= rowURL %>"
			name="views"
			orderable="<%= true %>"
			orderableProperty="view-count"
			property="viewCount"
		/>

		<liferay-ui:search-container-column-text
			align="right"
		>

			<%
			String taglibOnClick = "opener." + PortalUtil.getPortletNamespace(portletId) + "selectConfigurationKBArticle('" + kbArticle.getResourcePrimKey() + "', '" + UnicodeFormatter.toString(kbArticle.getTitle()) + "'); window.close();";
			%>

			<aui:button onClick="<%= taglibOnClick %>" value="choose" />
		</liferay-ui:search-container-column-text>
	</liferay-ui:search-container-row>

	<c:if test="<%= selResourcePrimKey > 0 %>">

		<%
		KBArticle selKBArticle = KBArticleLocalServiceUtil.fetchLatestKBArticle(selResourcePrimKey, WorkflowConstants.STATUS_APPROVED);
		%>

		<c:if test="<%= selKBArticle != null %>">
			<aui:button-row>
				<%= selKBArticle.getTitle() %>

				<%
				String taglibOnClick = "opener." + PortalUtil.getPortletNamespace(portletId) + "selectConfigurationKBArticle('0', ''); window.close();";
				%>

				<aui:button onClick="<%= taglibOnClick %>" value="remove" />
			</aui:button-row>

			<div class="separator"><!-- --></div>
		</c:if>
	</c:if>

	<liferay-ui:search-iterator />
</liferay-ui:search-container>