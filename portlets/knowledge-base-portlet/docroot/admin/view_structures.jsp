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

<liferay-portlet:renderURL varImpl="searchURL">
	<portlet:param name="jspPage" value="/admin/view_structures.jsp" />
</liferay-portlet:renderURL>

<aui:form action="<%= searchURL %>" method="get" name="fm">
	<liferay-portlet:renderURLParams varImpl="searchURL" />
	<aui:input name="kbStructureIds" type="hidden" />

	<aui:fieldset>
		<liferay-portlet:renderURL varImpl="iteratorURL">
			<portlet:param name="jspPage" value="/admin/view_structures.jsp" />
		</liferay-portlet:renderURL>

		<liferay-ui:search-container
			rowChecker="<%= AdminPermission.contains(permissionChecker, scopeGroupId, ActionKeys.DELETE_KB_STRUCTURES) ? new RowChecker(renderResponse) : null %>"
			searchContainer="<%= new KBStructureSearch(renderRequest, iteratorURL) %>"
		>
			<liferay-ui:search-form
				page="/admin/structure_search.jsp"
				servletContext="<%= application %>"
			/>

			<%
			KBStructureSearchTerms searchTerms = (KBStructureSearchTerms)searchContainer.getSearchTerms();
			%>

			<liferay-ui:search-container-results>
				<%@ include file="/admin/structure_search_results.jspf" %>
			</liferay-ui:search-container-results>

			<liferay-ui:search-container-row
				className="com.liferay.knowledgebase.model.KBStructure"
				keyProperty="kbStructureId"
				modelVar="kbStructure"
			>
				<liferay-ui:search-container-column-text
					value="<%= kbStructure.getTitle(locale) %>"
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
					value='<%= dateFormatDate.format(kbStructure.getCreateDate()) + "<br />" + dateFormatTime.format(kbStructure.getCreateDate()) %>'
				/>

				<liferay-ui:search-container-column-text
					cssClass="kb-column-no-wrap"
					name="modified-date"
					orderable="<%= true %>"
					value='<%= dateFormatDate.format(kbStructure.getModifiedDate()) + "<br />" + dateFormatTime.format(kbStructure.getModifiedDate()) %>'
				/>

				<liferay-ui:search-container-column-jsp
					align="right"
					path="/admin/structure_action.jsp"
				/>
			</liferay-ui:search-container-row>

			<c:if test="<%= AdminPermission.contains(permissionChecker, scopeGroupId, ActionKeys.ADD_KB_STRUCTURE) || (AdminPermission.contains(permissionChecker, scopeGroupId, ActionKeys.PERMISSIONS) && GroupPermissionUtil.contains(permissionChecker, scopeGroupId, ActionKeys.PERMISSIONS)) %>">
				<aui:button-row>
					<c:if test="<%= AdminPermission.contains(permissionChecker, scopeGroupId, ActionKeys.ADD_KB_STRUCTURE) %>">
						<liferay-portlet:renderURL var="addKBStructureURL">
							<portlet:param name="jspPage" value='<%= jspPath + "edit_structure.jsp" %>' />
							<portlet:param name="redirect" value="<%= redirect %>" />
						</liferay-portlet:renderURL>

						<aui:button onClick="<%= addKBStructureURL %>" value="add-structure" />
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

			<c:if test="<%= (total > 0) && AdminPermission.contains(permissionChecker, scopeGroupId, ActionKeys.DELETE_KB_STRUCTURES) %>">
				<aui:button-row>
					<aui:button onClick='<%= renderResponse.getNamespace() + "deleteKBStructures();" %>' value="delete" />
				</aui:button-row>
			</c:if>

			<liferay-ui:search-iterator type='<%= searchTerms.hasSearchTerms() ? "more" : "regular" %>' />
		</liferay-ui:search-container>
	</aui:fieldset>
</aui:form>

<aui:script>
	Liferay.provide(
		window,
		'<portlet:namespace />deleteKBStructures',
		function() {
			if (confirm('<%= UnicodeLanguageUtil.get(pageContext, "are-you-sure-you-want-to-delete-the-selected-structures") %>')) {
				document.<portlet:namespace />fm.method = "post";
				document.<portlet:namespace />fm.<portlet:namespace />kbStructureIds.value = Liferay.Util.listCheckedExcept(document.<portlet:namespace />fm, "<portlet:namespace />allRowIds");
				submitForm(document.<portlet:namespace />fm, "<liferay-portlet:actionURL name="deleteKBStructures"><portlet:param name="jspPage" value="/admin/view_structures.jsp" /><portlet:param name="redirect" value="<%= redirect %>" /></liferay-portlet:actionURL>");
			}
		},
		['liferay-util-list-fields']
	);
</aui:script>