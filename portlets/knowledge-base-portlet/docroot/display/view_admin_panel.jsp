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
String orderByCol1 = ParamUtil.getString(request, "orderByCol1", "modified-date");
String orderByType1 = ParamUtil.getString(request, "orderByType1", "desc");

String orderByCol2 = ParamUtil.getString(request, "orderByCol2", "modified-date");
String orderByType2 = ParamUtil.getString(request, "orderByType2", "desc");
%>

<liferay-util:include page="/display/top_links.jsp" servletContext="<%= application %>" />

<c:if test="<%= DisplayPermission.contains(permissionChecker, scopeGroupId, ActionKeys.ADD_ARTICLE) || DisplayPermission.contains(permissionChecker, scopeGroupId, ActionKeys.ADD_TEMPLATE) || (DisplayPermission.contains(permissionChecker, scopeGroupId, ActionKeys.PERMISSIONS) && GroupPermissionUtil.contains(permissionChecker, scopeGroupId, ActionKeys.PERMISSIONS)) %>">
	<aui:button-row>
		<c:if test="<%= DisplayPermission.contains(permissionChecker, scopeGroupId, ActionKeys.ADD_ARTICLE) %>">
			<liferay-portlet:renderURL var="addArticleURL">
				<portlet:param name="jspPage" value="/display/edit_article.jsp" />
				<portlet:param name="redirect" value="<%= currentURL %>" />
			</liferay-portlet:renderURL>

			<aui:button onClick="<%= addArticleURL %>" value="add-article" />
		</c:if>

		<c:if test="<%= DisplayPermission.contains(permissionChecker, scopeGroupId, ActionKeys.ADD_TEMPLATE) %>">
			<liferay-portlet:renderURL var="addTemplateURL">
				<portlet:param name="jspPage" value="/display/edit_template.jsp" />
				<portlet:param name="redirect" value="<%= currentURL %>" />
			</liferay-portlet:renderURL>

			<aui:button onClick="<%= addTemplateURL %>" value="add-template" />
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
	</aui:button-row>
</c:if>

<%
int templatesCount = TemplateServiceUtil.getGroupTemplatesCount(scopeGroupId);
%>

<liferay-portlet:renderURL varImpl="iteratorURL">
	<portlet:param name="jspPage" value="/display/view_admin_panel.jsp" />
</liferay-portlet:renderURL>

<liferay-ui:panel-container extended="<%= false %>" id='<%= renderResponse.getNamespace() + "AdministratorPanelContainer" %>' persistState="<%= true %>">
	<liferay-ui:panel collapsible="<%= true %>" cssClass='<%= (DisplayPermission.contains(permissionChecker, scopeGroupId, ActionKeys.VIEW_TEMPLATES) && (templatesCount > 0)) ? "kb-panel-separator" : StringPool.BLANK %>' extended="<%= true %>" id='<%= renderResponse.getNamespace() + "AdministratorArticlesPanel" %>' persistState="<%= true %>" title="articles">
		<liferay-ui:search-container
			curParam="cur1"
			deltaParam="delta1"
			emptyResultsMessage="there-are-no-articles"
			iteratorURL="<%= iteratorURL %>"
			orderByCol="<%= orderByCol1 %>"
			orderByColParam="orderByCol1"
			orderByComparator="<%= KnowledgeBaseUtil.getArticleOrderByComparator(orderByCol1, orderByType1) %>"
			orderByType="<%= orderByType1 %>"
			orderByTypeParam="orderByType1"
		>
			<liferay-ui:search-container-results
				results="<%= ArticleServiceUtil.getGroupArticles(scopeGroupId, WorkflowConstants.STATUS_ANY, searchContainer.getStart(), searchContainer.getEnd(), searchContainer.getOrderByComparator()) %>"
				total="<%= ArticleServiceUtil.getGroupArticlesCount(scopeGroupId, WorkflowConstants.STATUS_ANY) %>"
			/>

			<liferay-ui:search-container-row
				className="com.liferay.knowledgebase.model.Article"
				keyProperty="resourcePrimKey"
				modelVar="article"
			>
				<liferay-portlet:renderURL varImpl="rowURL">
					<portlet:param name="jspPage" value="/display/view_article.jsp" />
					<portlet:param name="resourcePrimKey" value="<%= String.valueOf(article.getResourcePrimKey()) %>" />
					<portlet:param name="status" value="<%= String.valueOf(WorkflowConstants.STATUS_ANY) %>" />
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

				<liferay-ui:search-container-column-text
					cssClass="kb-column-no-wrap"
					href="<%= rowURL %>"
					name="status"
					orderable="<%= true %>"
					value='<%= article.getStatus() + " (" + LanguageUtil.get(pageContext, WorkflowConstants.toLabel(article.getStatus())) + ")" %>'
				/>

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

			<liferay-ui:search-iterator />
		</liferay-ui:search-container>
	</liferay-ui:panel>

	<c:if test="<%= DisplayPermission.contains(permissionChecker, scopeGroupId, ActionKeys.VIEW_TEMPLATES) && (templatesCount > 0) %>">
		<liferay-ui:panel collapsible="<%= true %>" extended="<%= true %>" id='<%= renderResponse.getNamespace() + "AdministratorTemplatesPanel" %>' persistState="<%= true %>" title="templates">
			<liferay-ui:search-container
				curParam="cur2"
				deltaParam="delta2"
				emptyResultsMessage="there-are-no-templates"
				iteratorURL="<%= iteratorURL %>"
				orderByCol="<%= orderByCol2 %>"
				orderByColParam="orderByCol2"
				orderByComparator="<%= KnowledgeBaseUtil.getTemplateOrderByComparator(orderByCol2, orderByType2) %>"
				orderByType="<%= orderByType2 %>"
				orderByTypeParam="orderByType2"
			>
				<liferay-ui:search-container-results
					results="<%= TemplateServiceUtil.getGroupTemplates(scopeGroupId, searchContainer.getStart(), searchContainer.getEnd(), searchContainer.getOrderByComparator()) %>"
					total="<%= TemplateServiceUtil.getGroupTemplatesCount(scopeGroupId) %>"
				/>

				<liferay-ui:search-container-row
					className="com.liferay.knowledgebase.model.Template"
					keyProperty="templateId"
					modelVar="template"
				>
					<liferay-portlet:renderURL varImpl="rowURL">
						<portlet:param name="jspPage" value="/display/view_template.jsp" />
						<portlet:param name="templateId" value="<%= String.valueOf(template.getTemplateId()) %>" />
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
						value='<%= dateFormatDate.format(template.getCreateDate()) + "<br />" + dateFormatTime.format(template.getCreateDate()) %>'
					/>

					<liferay-ui:search-container-column-text
						cssClass="kb-column-no-wrap"
						href="<%= rowURL %>"
						name="modified-date"
						orderable="<%= true %>"
						value='<%= dateFormatDate.format(template.getModifiedDate()) + "<br />" + dateFormatTime.format(template.getModifiedDate()) %>'
					/>

					<liferay-ui:search-container-column-jsp
						align="right"
						path="/display/template_action.jsp"
					/>
				</liferay-ui:search-container-row>

				<liferay-ui:search-iterator />
			</liferay-ui:search-container>
		</liferay-ui:panel>
	</c:if>
</liferay-ui:panel-container>