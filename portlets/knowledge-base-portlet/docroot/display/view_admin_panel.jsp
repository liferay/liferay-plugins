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
String orderByCol1 = ParamUtil.getString(request, "orderByCol1", "modified-date");
String orderByType1 = ParamUtil.getString(request, "orderByType1", "desc");

String orderByCol2 = ParamUtil.getString(request, "orderByCol2", "modified-date");
String orderByType2 = ParamUtil.getString(request, "orderByType2", "desc");
%>

<liferay-util:include page="/display/top_links.jsp" servletContext="<%= application %>" />

<c:if test="<%= DisplayPermission.contains(permissionChecker, scopeGroupId, ActionKeys.ADD_KB_ARTICLE) || DisplayPermission.contains(permissionChecker, scopeGroupId, ActionKeys.ADD_KB_TEMPLATE) || (DisplayPermission.contains(permissionChecker, scopeGroupId, ActionKeys.PERMISSIONS) && GroupPermissionUtil.contains(permissionChecker, scopeGroupId, ActionKeys.PERMISSIONS)) %>">
	<aui:button-row>
		<c:if test="<%= DisplayPermission.contains(permissionChecker, scopeGroupId, ActionKeys.ADD_KB_ARTICLE) %>">
			<liferay-portlet:renderURL var="addKBArticleURL">
				<portlet:param name="jspPage" value="/display/edit_article.jsp" />
				<portlet:param name="redirect" value="<%= redirect %>" />
			</liferay-portlet:renderURL>

			<aui:button href="<%= addKBArticleURL %>" value="add-article" />
		</c:if>

		<c:if test="<%= DisplayPermission.contains(permissionChecker, scopeGroupId, ActionKeys.ADD_KB_TEMPLATE) %>">
			<liferay-portlet:renderURL var="addKBTemplateURL">
				<portlet:param name="jspPage" value="/display/edit_template.jsp" />
				<portlet:param name="redirect" value="<%= redirect %>" />
			</liferay-portlet:renderURL>

			<aui:button href="<%= addKBTemplateURL %>" value="add-template" />
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
</c:if>

<liferay-portlet:renderURL varImpl="iteratorURL">
	<portlet:param name="jspPage" value="/display/view_admin_panel.jsp" />
</liferay-portlet:renderURL>

<liferay-ui:panel-container extended="<%= false %>" id='<%= renderResponse.getNamespace() + "AdministratorPanelContainer" %>' persistState="<%= true %>">
	<liferay-ui:panel collapsible="<%= true %>" cssClass='<%= (DisplayPermission.contains(permissionChecker, scopeGroupId, ActionKeys.VIEW_KB_TEMPLATES) && (KBTemplateServiceUtil.getGroupKBTemplatesCount(scopeGroupId) > 0)) ? "kb-panel-separator" : StringPool.BLANK %>' extended="<%= true %>" id='<%= renderResponse.getNamespace() + "AdministratorArticlesPanel" %>' persistState="<%= true %>" title="articles">
		<liferay-ui:search-container
			curParam="cur1"
			deltaParam="delta1"
			emptyResultsMessage="there-are-no-articles"
			iteratorURL="<%= iteratorURL %>"
			orderByCol="<%= orderByCol1 %>"
			orderByColParam="orderByCol1"
			orderByComparator="<%= KnowledgeBaseUtil.getKBArticleOrderByComparator(orderByCol1, orderByType1) %>"
			orderByType="<%= orderByType1 %>"
			orderByTypeParam="orderByType1"
		>
			<liferay-ui:search-container-results
				results="<%= KBArticleServiceUtil.getGroupKBArticles(scopeGroupId, WorkflowConstants.STATUS_ANY, searchContainer.getStart(), searchContainer.getEnd(), searchContainer.getOrderByComparator()) %>"
				total="<%= KBArticleServiceUtil.getGroupKBArticlesCount(scopeGroupId, WorkflowConstants.STATUS_ANY) %>"
			/>

			<liferay-ui:search-container-row
				className="com.liferay.knowledgebase.model.KBArticle"
				keyProperty="resourcePrimKey"
				modelVar="kbArticle"
			>
				<liferay-portlet:renderURL varImpl="rowURL">
					<portlet:param name="jspPage" value="/display/view_article.jsp" />
					<portlet:param name="resourcePrimKey" value="<%= String.valueOf(kbArticle.getResourcePrimKey()) %>" />
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
					value='<%= dateFormatDate.format(kbArticle.getCreateDate()) + "<br />" + dateFormatTime.format(kbArticle.getCreateDate()) %>'
				/>

				<liferay-ui:search-container-column-text
					cssClass="kb-column-no-wrap"
					href="<%= rowURL %>"
					name="modified-date"
					orderable="<%= true %>"
					value='<%= dateFormatDate.format(kbArticle.getModifiedDate()) + "<br />" + dateFormatTime.format(kbArticle.getModifiedDate()) %>'
				/>

				<liferay-ui:search-container-column-text
					cssClass="kb-column-no-wrap"
					href="<%= rowURL %>"
					name="status"
					orderable="<%= true %>"
					value='<%= kbArticle.getStatus() + " (" + LanguageUtil.get(pageContext, WorkflowConstants.toLabel(kbArticle.getStatus())) + ")" %>'
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

	<c:if test="<%= DisplayPermission.contains(permissionChecker, scopeGroupId, ActionKeys.VIEW_KB_TEMPLATES) && (KBTemplateServiceUtil.getGroupKBTemplatesCount(scopeGroupId) > 0) %>">
		<liferay-ui:panel collapsible="<%= true %>" extended="<%= true %>" id='<%= renderResponse.getNamespace() + "AdministratorTemplatesPanel" %>' persistState="<%= true %>" title="templates">
			<liferay-ui:search-container
				curParam="cur2"
				deltaParam="delta2"
				emptyResultsMessage="there-are-no-templates"
				iteratorURL="<%= iteratorURL %>"
				orderByCol="<%= orderByCol2 %>"
				orderByColParam="orderByCol2"
				orderByComparator="<%= KnowledgeBaseUtil.getKBTemplateOrderByComparator(orderByCol2, orderByType2) %>"
				orderByType="<%= orderByType2 %>"
				orderByTypeParam="orderByType2"
			>
				<liferay-ui:search-container-results
					results="<%= KBTemplateServiceUtil.getGroupKBTemplates(scopeGroupId, searchContainer.getStart(), searchContainer.getEnd(), searchContainer.getOrderByComparator()) %>"
					total="<%= KBTemplateServiceUtil.getGroupKBTemplatesCount(scopeGroupId) %>"
				/>

				<liferay-ui:search-container-row
					className="com.liferay.knowledgebase.model.KBTemplate"
					keyProperty="kbTemplateId"
					modelVar="kbTemplate"
				>
					<liferay-portlet:renderURL varImpl="rowURL">
						<portlet:param name="jspPage" value="/display/view_template.jsp" />
						<portlet:param name="kbTemplateId" value="<%= String.valueOf(kbTemplate.getKbTemplateId()) %>" />
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
						value='<%= dateFormatDate.format(kbTemplate.getCreateDate()) + "<br />" + dateFormatTime.format(kbTemplate.getCreateDate()) %>'
					/>

					<liferay-ui:search-container-column-text
						cssClass="kb-column-no-wrap"
						href="<%= rowURL %>"
						name="modified-date"
						orderable="<%= true %>"
						value='<%= dateFormatDate.format(kbTemplate.getModifiedDate()) + "<br />" + dateFormatTime.format(kbTemplate.getModifiedDate()) %>'
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