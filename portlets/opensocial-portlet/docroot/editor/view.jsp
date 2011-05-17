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

<%@ include file="/init.jsp" %>

<%
long repositoryId = themeDisplay.getScopeGroupId();

Folder rootFolder = ShindigUtil.getGadgetEditorRootFolder(repositoryId);
%>

<div id="<portlet:namespace />editor"></div>

<aui:script use="opensocial-editor">
	new Liferay.OpenSocial.Editor(
		{
			gadgetDebug: '<%= PortletPropsValues.SHINDIG_JS_DEBUG %>',
			gadgetNocache: '<%= PortletPropsValues.SHINDIG_NO_CACHE %>',
			gadgetPortletId: '<%= portletDisplay.getId() %>',
			gadgetServerBase: '<%= renderRequest.getContextPath() %>/gadgets/',
			gadgetStore: new Liferay.OpenSocial.Store.Expando(
				{
					userPrefsKey: '<%= ShindigUtil.getColumnUserPrefs(renderResponse.getNamespace()) %>'
				}
			),
			gadgetView: '<%= renderRequest.getAttribute(WebKeys.VIEW) %>',
			gadgetViewParams: '<%= ParamUtil.getString(renderRequest, "viewParams") %>',
			repositoryId: '<%= repositoryId %>',
			resourceURL: '<portlet:resourceURL />',
			rootFolderId: '<%= rootFolder.getFolderId() %>'
		}
	).render('#<portlet:namespace />editor');
</aui:script>