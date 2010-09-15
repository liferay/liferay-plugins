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

<%@ include file="/page_menu/init.jsp" %>

<%
List<WikiNode> wikiNodes = WikiNodeLocalServiceUtil.getNodes(scopeGroupId);

if ((selNodeId == 0) && (wikiNodes.size() == 1)) {
	WikiNode wikiNode = wikiNodes.get(0);

	selNodeId = wikiNode.getNodeId();
}
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

<c:if test="<%= selNodeId > 0 %>">
	<tr>
		<td>
			<liferay-ui:message key="page" />
		</td>
		<td>

			<%
			List<WikiPage> wikiPages = WikiPageServiceUtil.getNodePages(selNodeId, WikiNavigationConstants.MAX_PAGES);
			%>

			<select name="<portlet:namespace />selTitle">
				<option value=""></option>

				<%
				for (WikiPage curWikiPage : wikiPages) {
				%>

					<option <%= (curWikiPage.getTitle().equals(selTitle)) ? "selected" : "" %> value="<%= curWikiPage.getTitle() %>"><%= curWikiPage.getTitle() %></option>

				<%
				}
				%>

			</select>
		</td>
	</tr>
</c:if>

</table>

<br />

<input type="button" value="<liferay-ui:message key="save" />" onClick="submitForm(document.<portlet:namespace />fm);" />

</form>