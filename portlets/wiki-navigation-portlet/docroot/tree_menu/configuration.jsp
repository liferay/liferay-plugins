<%--
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
--%>

<%@ include file="/tree_menu/init.jsp" %>

<%
List<WikiNode> wikiNodes = WikiNodeLocalServiceUtil.getNodes(scopeGroupId);
%>

<form action="<liferay-portlet:actionURL portletConfiguration="true" />" method="post" name="<portlet:namespace />fm">
<input name="<portlet:namespace /><%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />

<liferay-ui:error exception="<%= NoSuchNodeException.class %>" message="the-node-could-not-be-found" />

<table class="lfr-table">
<tr>
	<td>
		<liferay-ui:message key="node" />
	</td>
	<td>
		<select name="<portlet:namespace />selNodeId">
			<option value=""></option>

			<%
			for (WikiNode wikiNode : wikiNodes) {
			%>

				<option <%= (selNodeId == wikiNode.getNodeId()) ? "selected" : "" %> value="<%= wikiNode.getNodeId() %>"><%= wikiNode.getName() %></option>

			<%
			}
			%>

		</select>
	</td>
</tr>
<tr>
	<td>
		<liferay-ui:message key="depth" />
	</td>
	<td>
		<select name="<portlet:namespace />depth">
			<option <%= (depth == WikiNavigationConstants.DEPTH_ALL) ? "selected" : "" %> value="<%= WikiNavigationConstants.DEPTH_ALL %>"><liferay-ui:message key="all" /></option>

			<%
			for (int i = 1; i < 6; i++) {
			%>

				<option <%= (depth == i) ? "selected" : "" %> value="<%= i %>"><%= i %></option>

			<%
			}
			%>

		</select>
	</td>
</tr>
</table>

<br />

<input type="button" value="<liferay-ui:message key="save" />" onClick="submitForm(document.<portlet:namespace />fm);" />

</form>