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

<%@ include file="/tree_menu/init.jsp" %>

<%
List<WikiNode> wikiNodes = WikiNodeLocalServiceUtil.getNodes(scopeGroupId);
%>

<liferay-portlet:actionURL portletConfiguration="true" var="configurationActionURL" />

<aui:form action="<%= configurationActionURL %>" method="post" name="fm">
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />

	<liferay-ui:error exception="<%= NoSuchNodeException.class %>" message="the-node-could-not-be-found" />

	<aui:fieldset>
		<aui:select label="node" name="preferences--selNodeId--" showEmptyOption="<%= true %>">

			<%
			for (WikiNode wikiNode : wikiNodes) {
			%>

				<aui:option label="<%= wikiNode.getName() %>" selected="<%= selNodeId == wikiNode.getNodeId() %>" value="<%= wikiNode.getNodeId() %>" />

			<%
			}
			%>

		</aui:select>

		<aui:select name="preferences--depth--">
			<aui:option label="all" selected="<%= depth == WikiNavigationConstants.DEPTH_ALL %>" value="<%= WikiNavigationConstants.DEPTH_ALL %>" />

			<%
			for (int i = 1; i < 6; i++) {
			%>

				<aui:option label="<%= i %>" selected="<%= depth == i %>" />

			<%
			}
			%>

		</aui:select>
	</aui:fieldset>

	<aui:button-row>
		<aui:button type="submit" />
	</aui:button-row>
</aui:form>