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

<%@ include file="/admin/init.jsp" %>

<liferay-util:include page="/admin/top_tabs.jsp" servletContext="<%= application %>" />

<portlet:actionURL name="deleteTemplates" var="deleteTemplatesURL">
	<portlet:param name="jspPage" value="/admin/view_templates.jsp" />
	<portlet:param name="redirect" value="<%= currentURL %>" />
</portlet:actionURL>

<aui:form action="<%= deleteTemplatesURL %>" method="post" name="fm" onSubmit='<%= "event.preventDefault(); " + renderResponse.getNamespace() + "deleteTemplates();" %>'>
	<liferay-portlet:renderURLParams varImpl="portletURL" />
	<aui:input name="templateIds" type="hidden" />

	<aui:fieldset>
		<liferay-portlet:renderURL varImpl="iteratorURL">
			<portlet:param name="jspPage" value="/admin/view_templates.jsp" />
		</liferay-portlet:renderURL>

		<liferay-ui:search-container
			rowChecker="<%= permissionChecker.isCommunityAdmin(scopeGroupId) ? new RowChecker(renderResponse) : null %>"
			searchContainer="<%= new TemplateSearch(renderRequest, iteratorURL) %>"
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
				<liferay-ui:search-container-column-text
					orderable="<%= true %>"
					property="title"
				/>

				<liferay-ui:search-container-column-text
					name="author"
					orderable="<%= true %>"
					orderableProperty="user-name"
					property="userName"
				/>

				<liferay-ui:search-container-column-text
					name="create-date"
					orderable="<%= true %>"
					value="<%= dateFormatDateTime.format(template.getCreateDate()) %>"
				/>

				<liferay-ui:search-container-column-text
					name="modified-date"
					orderable="<%= true %>"
					value="<%= dateFormatDateTime.format(template.getModifiedDate()) %>"
				/>

				<liferay-ui:search-container-column-jsp
					align="right"
					path="/admin/template_action.jsp"
				/>
			</liferay-ui:search-container-row>

			<c:if test="<%= AdminPermission.contains(permissionChecker, scopeGroupId, ActionKeys.ADD_TEMPLATE) || (AdminPermission.contains(permissionChecker, scopeGroupId, ActionKeys.PERMISSIONS) && GroupPermissionUtil.contains(permissionChecker, scopeGroupId, ActionKeys.PERMISSIONS)) %>">
				<aui:button-row>
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
				</aui:button-row>

				<div class="separator"><!-- --></div>
			</c:if>

			<c:if test="<%= permissionChecker.isCommunityAdmin(scopeGroupId) && !results.isEmpty() %>">
				<aui:button-row>
					<aui:button onClick='<%= renderResponse.getNamespace() + "deleteTemplates();" %>' value="delete" />
				</aui:button-row>
			</c:if>

			<liferay-ui:search-iterator />
		</liferay-ui:search-container>
	</aui:fieldset>
</aui:form>

<aui:script>
	Liferay.provide(
		window,
		'<portlet:namespace />deleteTemplates',
		function() {
			document.<portlet:namespace />fm.<portlet:namespace />templateIds.value = Liferay.Util.listCheckedExcept(document.<portlet:namespace />fm, "<portlet:namespace />allRowIds");
			submitForm(document.<portlet:namespace />fm);
		},
		['liferay-util-list-fields']
	);
</aui:script>