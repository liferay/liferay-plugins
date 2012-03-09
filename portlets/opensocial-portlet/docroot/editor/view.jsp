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

<%@ include file="/init.jsp" %>

<%
long repositoryId = themeDisplay.getScopeGroupId();

Folder rootFolder = ShindigUtil.getGadgetEditorRootFolder(repositoryId);
%>

<div id="<portlet:namespace />editor"></div>

<portlet:renderURL var="editorGadgetURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
	<portlet:param name="mvcPath" value="/admin/edit_gadget.jsp" />
	<portlet:param name="editorGadgetURL" value="editorGadgetURLPlaceholder" />
</portlet:renderURL>

<aui:script use="opensocial-editor">
	new Liferay.OpenSocial.Editor(
		{
			editorGadgetURL: '<%= editorGadgetURL %>',
			gadgetPortletId: '<%= portletDisplay.getId() %>',
			gadgetServerBase: '<%= renderRequest.getContextPath() %>/gadgets/',
			publishGadgetPermission: <%= GadgetPermission.contains(themeDisplay.getPermissionChecker(), themeDisplay.getScopeGroupId(), ActionKeys.PUBLISH_GADGET) %>,
			repositoryId: '<%= repositoryId %>',
			resourceURL: '<portlet:resourceURL />',
			rootFolderId: '<%= rootFolder.getFolderId() %>'
		}
	).render('#<portlet:namespace />editor');
</aui:script>