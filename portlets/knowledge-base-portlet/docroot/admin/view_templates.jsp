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

<%@ include file="/admin/init.jsp" %>

<liferay-util:include page="/admin/top_tabs.jsp" servletContext="<%= application %>" />

<liferay-portlet:renderURL varImpl="searchURL">
	<portlet:param name="mvcPath" value="/admin/view_templates.jsp" />
</liferay-portlet:renderURL>

<aui:form action="<%= searchURL %>" method="get" name="fm">
	<liferay-portlet:renderURLParams varImpl="searchURL" />
	<aui:input name="kbTemplateIds" type="hidden" />

	<aui:fieldset>
		<liferay-portlet:renderURL varImpl="iteratorURL">
			<portlet:param name="mvcPath" value="/admin/view_templates.jsp" />
		</liferay-portlet:renderURL>

		<liferay-ui:search-container
			id="kbTemplateAdminSearchContainer"
			rowChecker="<%= AdminPermission.contains(permissionChecker, scopeGroupId, ActionKeys.DELETE_KB_TEMPLATES) ? new RowChecker(renderResponse) : null %>"
			searchContainer="<%= new KBTemplateSearch(renderRequest, iteratorURL) %>"
		>

			<%
			KBTemplateSearchTerms searchTerms = (KBTemplateSearchTerms)searchContainer.getSearchTerms();
			%>

			<%@ include file="/admin/template_search_results.jspf" %>

			<liferay-ui:search-container-row
				className="com.liferay.knowledgebase.model.KBTemplate"
				escapedModel="<%= true %>"
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

				<liferay-ui:search-container-column-date
					cssClass="kb-column-no-wrap"
					name="create-date"
					orderable="<%= true %>"
					value="<%= kbTemplate.getCreateDate() %>"
				/>

				<liferay-ui:search-container-column-date
					cssClass="kb-column-no-wrap"
					name="modified-date"
					orderable="<%= true %>"
					value="<%= kbTemplate.getModifiedDate() %>"
				/>

				<liferay-ui:search-container-column-jsp
					align="right"
					path="/admin/template_action.jsp"
				/>
			</liferay-ui:search-container-row>

			<aui:nav-bar>
				<aui:nav cssClass="navbar-nav">
					<c:if test="<%= (total > 0) && AdminPermission.contains(permissionChecker, scopeGroupId, ActionKeys.DELETE_KB_TEMPLATES) %>">
						<aui:nav-item cssClass="hide" id="deleteKBTemplates" label="delete" />
					</c:if>

					<c:if test="<%= AdminPermission.contains(permissionChecker, scopeGroupId, ActionKeys.ADD_KB_TEMPLATE) %>">
						<liferay-portlet:renderURL var="addKBTemplateURL">
							<portlet:param name="mvcPath" value='<%= templatePath + "edit_template.jsp" %>' />
							<portlet:param name="redirect" value="<%= redirect %>" />
						</liferay-portlet:renderURL>

						<aui:nav-item href="<%= addKBTemplateURL %>" label="add-template" />
					</c:if>

					<c:if test="<%= AdminPermission.contains(permissionChecker, scopeGroupId, ActionKeys.PERMISSIONS) && GroupPermissionUtil.contains(permissionChecker, scopeGroupId, ActionKeys.PERMISSIONS) %>">
						<liferay-security:permissionsURL
							modelResource="com.liferay.knowledgebase.admin"
							modelResourceDescription="<%= HtmlUtil.escape(themeDisplay.getScopeGroupName()) %>"
							resourcePrimKey="<%= String.valueOf(scopeGroupId) %>"
							var="permissionsURL"
							windowState="<%= LiferayWindowState.POP_UP.toString() %>"
						/>

						<aui:nav-item href="<%= permissionsURL %>" label="permissions" useDialog="<%= true %>" />
					</c:if>
				</aui:nav>

				<aui:nav-bar-search
					cssClass="navbar-search-advanced"
				>
					<liferay-ui:search-form
						page="/admin/template_search.jsp"
						servletContext="<%= application %>"
					/>
				</aui:nav-bar-search>
			</aui:nav-bar>

			<liferay-ui:search-iterator type='<%= searchTerms.hasSearchTerms() ? "more" : "regular" %>' />
		</liferay-ui:search-container>
	</aui:fieldset>
</aui:form>

<aui:script use="aui-base,liferay-util-list-fields">
	var deleteKBTemplates = A.one('#<portlet:namespace />deleteKBTemplates');

	if (deleteKBTemplates) {
		deleteKBTemplates.on(
			'click',
			function() {
				if (confirm('<%= UnicodeLanguageUtil.get(pageContext, "are-you-sure-you-want-to-delete-the-selected-templates") %>')) {
					document.<portlet:namespace />fm.method = 'post';
					document.<portlet:namespace />fm.<portlet:namespace />kbTemplateIds.value = Liferay.Util.listCheckedExcept(document.<portlet:namespace />fm, '<portlet:namespace />allRowIds');

					submitForm(document.<portlet:namespace />fm, '<liferay-portlet:actionURL name="deleteKBTemplates"><portlet:param name="mvcPath" value="/admin/view_templates.jsp" /><portlet:param name="redirect" value="<%= redirect %>" /></liferay-portlet:actionURL>');
				}
			}
		);
	}

	A.one('#<portlet:namespace />kbTemplateAdminSearchContainer').delegate(
		'click',
		function() {
			var hide = (Liferay.Util.listCheckedExcept(document.<portlet:namespace />fm, '<portlet:namespace /><%= RowChecker.ALL_ROW_IDS %>').length == 0);

			var deleteKBTemplates = A.one('#<portlet:namespace />deleteKBTemplates');

			if (deleteKBTemplates) {
				deleteKBTemplates.toggle(!hide);
			}
		},
		'input[type=checkbox]'
	);
</aui:script>