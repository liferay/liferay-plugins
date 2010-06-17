<%
/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
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
%>

<%@ include file="/admin/init.jsp" %>

<liferay-util:include page="/admin/top_links.jsp" servletContext="<%= application %>" />

<c:if test="<%= AdminPermission.contains(permissionChecker, scopeGroupId, ActionKeys.ADD_TEMPLATE) || (AdminPermission.contains(permissionChecker, scopeGroupId, ActionKeys.PERMISSIONS) && GroupPermissionUtil.contains(permissionChecker, scopeGroupId, ActionKeys.PERMISSIONS)) %>">
	<div class="float-container kb-results-header">
		<div class="kb-buttons">
			<c:if test="<%= AdminPermission.contains(permissionChecker, scopeGroupId, ActionKeys.ADD_TEMPLATE) %>">
				<portlet:renderURL var="addTemplateURL">
					<portlet:param name="jspPage" value="/admin/edit_template.jsp" />
					<portlet:param name="redirect" value="<%= currentURL %>" />
				</portlet:renderURL>

				<aui:button onClick="<%= addTemplateURL %>" value="add-template" />
			</c:if>

			<c:if test="<%= AdminPermission.contains(permissionChecker, scopeGroupId, ActionKeys.PERMISSIONS) && GroupPermissionUtil.contains(permissionChecker, scopeGroupId, ActionKeys.PERMISSIONS) %>">
				<liferay-security:permissionsURL
					modelResource="com.liferay.knowledgebase.admin"
					modelResourceDescription="<%= HtmlUtil.escape(themeDisplay.getScopeGroupName()) %>"
					resourcePrimKey="<%= String.valueOf(scopeGroupId) %>"
					var="permissionsURL"
				/>

				<aui:button onClick="<%= permissionsURL %>" value="permissions" />
			</c:if>
		</div>
	</div>
</c:if>

<liferay-ui:panel-container extended="<%= false %>" id='<%= renderResponse.getNamespace() + "TemplatesPanelContainer" %>' persistState="<%= true %>">
	<liferay-ui:panel collapsible="<%= true %>" extended="<%= true %>" id='<%= renderResponse.getNamespace() + "TemplatesPanel" %>' persistState="<%= true %>" title='<%= LanguageUtil.get(pageContext, "templates") %>'>
		<liferay-portlet:renderURL varImpl="iteratorURL">
			<portlet:param name="jspPage" value="/admin/view_templates.jsp" />
			<portlet:param name="topLink" value="templates" />
		</liferay-portlet:renderURL>

		<liferay-ui:search-container
			delta="<%= templatesDelta %>"
			iteratorURL="<%= iteratorURL %>"
		>
			<liferay-ui:search-container-results
				results="<%= TemplateServiceUtil.getGroupTemplates(scopeGroupId, searchContainer.getStart(), searchContainer.getEnd(), null) %>"
				total="<%= TemplateServiceUtil.getGroupTemplatesCount(scopeGroupId) %>"
			/>

			<div class="kb-results-body">

				<%
				for (Template template : (List<Template>)results) {
				%>

					<portlet:renderURL var="viewTemplateURL">
						<portlet:param name="jspPage" value="/admin/view_template.jsp" />
						<portlet:param name="templateId" value="<%= String.valueOf(template.getTemplateId()) %>" />
					</portlet:renderURL>

					<liferay-ui:icon
						cssClass="kb-title"
						image="../trees/page"
						label="<%= true %>"
						message="<%= template.getTitle() %>"
						method="get"
						url="<%= viewTemplateURL %>"
					/>

					<%
					request.setAttribute(WebKeys.KNOWLEDGE_BASE_TEMPLATE, template);
					%>

					<liferay-util:include page="/admin/template_icons.jsp" servletContext="<%= application %>" />

					<c:choose>
						<c:when test='<%= templatesDisplayStyle.equals("full-content") %>'>
							<%= template.getContent() %>
						</c:when>
						<c:when test='<%= (templatesDisplayStyle.equals("abstract") && Validator.isNotNull(template.getDescription())) %>'>
							<%= template.getDescription() %>
						</c:when>
						<c:when test='<%= templatesDisplayStyle.equals("abstract") %>'>
							<%= StringUtil.shorten(HtmlUtil.extractText(template.getContent()), 500) %>
						</c:when>
					</c:choose>

				<%
				}
				%>

			</div>

			<div class="kb-results-footer">
				<liferay-ui:search-paginator searchContainer="<%= searchContainer %>" />
			</div>
		</liferay-ui:search-container>
	</liferay-ui:panel>
</liferay-ui:panel-container>