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

<%@ include file="/admin/init.jsp" %>

<liferay-util:include page="/admin/top_tabs.jsp" servletContext="<%= application %>" />

<liferay-portlet:renderURL varImpl="searchURL">
	<portlet:param name="jspPage" value="/admin/view_templates.jsp" />
</liferay-portlet:renderURL>

<aui:form action="<%= searchURL %>" method="get" name="fm">
	<liferay-portlet:renderURLParams varImpl="searchURL" />
	<aui:input name="kbTemplateIds" type="hidden" />

	<aui:fieldset>
		<liferay-portlet:renderURL varImpl="iteratorURL">
			<portlet:param name="jspPage" value="/admin/view_templates.jsp" />
		</liferay-portlet:renderURL>

		<liferay-ui:search-container
			rowChecker="<%= AdminPermission.contains(permissionChecker, scopeGroupId, ActionKeys.DELETE_KB_TEMPLATES) ? new RowChecker(renderResponse) : null %>"
			searchContainer="<%= new KBTemplateSearch(renderRequest, iteratorURL) %>"
		>
			<liferay-ui:search-form
				page="/admin/template_search.jsp"
				servletContext="<%= application %>"
			/>

			<%
			KBTemplateSearchTerms searchTerms = (KBTemplateSearchTerms)searchContainer.getSearchTerms();
			%>

			<liferay-ui:search-container-results>
				<%@ include file="/admin/template_search_results.jspf" %>
			</liferay-ui:search-container-results>

			<liferay-ui:search-container-row
				className="com.liferay.knowledgebase.model.KBTemplate"
				keyProperty="kbTemplateId"
				modelVar="kbTemplate"
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
					cssClass="kb-column-no-wrap"
					name="create-date"
					orderable="<%= true %>"
					value='<%= dateFormatDate.format(kbTemplate.getCreateDate()) + "<br />" + dateFormatTime.format(kbTemplate.getCreateDate()) %>'
				/>

				<liferay-ui:search-container-column-text
					cssClass="kb-column-no-wrap"
					name="modified-date"
					orderable="<%= true %>"
					value='<%= dateFormatDate.format(kbTemplate.getModifiedDate()) + "<br />" + dateFormatTime.format(kbTemplate.getModifiedDate()) %>'
				/>

				<liferay-ui:search-container-column-jsp
					align="right"
					path="/admin/template_action.jsp"
				/>
			</liferay-ui:search-container-row>

			<c:if test="<%= AdminPermission.contains(permissionChecker, scopeGroupId, ActionKeys.ADD_KB_TEMPLATE) || (AdminPermission.contains(permissionChecker, scopeGroupId, ActionKeys.PERMISSIONS) && GroupPermissionUtil.contains(permissionChecker, scopeGroupId, ActionKeys.PERMISSIONS)) %>">
				<aui:button-row>
					<c:if test="<%= AdminPermission.contains(permissionChecker, scopeGroupId, ActionKeys.ADD_KB_TEMPLATE) %>">
						<liferay-portlet:renderURL var="addKBTemplateURL">
							<portlet:param name="jspPage" value='<%= jspPath + "edit_template.jsp" %>' />
							<portlet:param name="redirect" value="<%= redirect %>" />
						</liferay-portlet:renderURL>

						<aui:button href="<%= addKBTemplateURL %>" value="add-template" />
					</c:if>

					<c:if test="<%= AdminPermission.contains(permissionChecker, scopeGroupId, ActionKeys.PERMISSIONS) && GroupPermissionUtil.contains(permissionChecker, scopeGroupId, ActionKeys.PERMISSIONS) %>">
						<liferay-security:permissionsURL
							modelResource="com.liferay.knowledgebase.admin"
							modelResourceDescription="<%= HtmlUtil.escape(themeDisplay.getScopeGroupName()) %>"
							resourcePrimKey="<%= String.valueOf(scopeGroupId) %>"
							var="permissionsURL"
						/>

						<aui:button href="<%= permissionsURL %>" value="permissions" />
					</c:if>
				</aui:button-row>

				<div class="separator"><!-- --></div>
			</c:if>

			<c:if test="<%= (total > 0) && AdminPermission.contains(permissionChecker, scopeGroupId, ActionKeys.DELETE_KB_TEMPLATES) %>">
				<aui:button-row>
					<aui:button onClick='<%= renderResponse.getNamespace() + "deleteKBTemplates();" %>' value="delete" />
				</aui:button-row>
			</c:if>

			<liferay-ui:search-iterator type='<%= searchTerms.hasSearchTerms() ? "more" : "regular" %>' />
		</liferay-ui:search-container>
	</aui:fieldset>
</aui:form>

<aui:script>
	Liferay.provide(
		window,
		'<portlet:namespace />deleteKBTemplates',
		function() {
			if (confirm('<%= UnicodeLanguageUtil.get(pageContext, "are-you-sure-you-want-to-delete-the-selected-templates") %>')) {
				document.<portlet:namespace />fm.method = "post";
				document.<portlet:namespace />fm.<portlet:namespace />kbTemplateIds.value = Liferay.Util.listCheckedExcept(document.<portlet:namespace />fm, "<portlet:namespace />allRowIds");
				submitForm(document.<portlet:namespace />fm, "<liferay-portlet:actionURL name="deleteKBTemplates"><portlet:param name="jspPage" value="/admin/view_templates.jsp" /><portlet:param name="redirect" value="<%= redirect %>" /></liferay-portlet:actionURL>");
			}
		},
		['liferay-util-list-fields']
	);
</aui:script>