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

<%@ include file="/init.jsp" %>

<%
long repositoryId = themeDisplay.getScopeGroupId();

Folder rootFolder = ShindigUtil.getGadgetEditorRootFolder(repositoryId);
%>

<div id="<portlet:namespace />editor"></div>

<aui:script use="opensocial-editor">
	new Liferay.OpenSocial.Editor(
		{
			baseRenderURL: '<%= PortletURLFactoryUtil.create(request, portletDisplay.getId(), themeDisplay.getPlid(), PortletRequest.RENDER_PHASE) %>',

			<portlet:renderURL var="editorGadgetURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
				<portlet:param name="mvcPath" value="/admin/edit_gadget.jsp" />
				<portlet:param name="editorGadgetURL" value="editorGadgetURLPlaceholder" />
			</portlet:renderURL>

			editorGadgetURL: '<%= editorGadgetURL %>',
			gadgetPortletId: '<%= portletDisplay.getId() %>',
			gadgetServerBase: '<%= PortalUtil.getPathContext(renderRequest) %>/gadgets/',
			namespace: '<portlet:namespace />',
			publishGadgetPermission: <%= GadgetPermission.contains(themeDisplay.getPermissionChecker(), themeDisplay.getScopeGroupId(), ActionKeys.PUBLISH_GADGET) %>,
			repositoryId: '<%= repositoryId %>',
			resourceURL: '<portlet:resourceURL />',
			rootFolderId: '<%= rootFolder.getFolderId() %>'
		}
	).render('#<portlet:namespace />editor');
</aui:script>