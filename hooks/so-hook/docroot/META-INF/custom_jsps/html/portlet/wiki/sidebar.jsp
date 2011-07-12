<%--
/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
 *
 * This file is part of Liferay Social Office. Liferay Social Office is free
 * software: you can redistribute it and/or modify it under the terms of the GNU
 * Affero General Public License as published by the Free Software Foundation,
 * either version 3 of the License, or (at your option) any later version.
 *
 * Liferay Social Office is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License
 * for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * Liferay Social Office. If not, see http://www.gnu.org/licenses/agpl-3.0.html.
 */
--%>

<%@ include file="/html/portlet/wiki/init.jsp" %>

<div class="sidebar">

	<%
	boolean print = ParamUtil.getBoolean(request, Constants.PRINT);
	%>

	<c:if test="<%= !print && portletName.equals(PortletKeys.WIKI) %>">

		<%
		WikiNode node = (WikiNode)request.getAttribute(WebKeys.WIKI_NODE);

		String keywords = ParamUtil.getString(request, "keywords");

		List<WikiNode> nodes = WikiUtil.getNodes(allNodes, hiddenNodes, permissionChecker);

		PortletURL portletURL = renderResponse.createRenderURL();
		%>

		<c:if test="<%= node != null %>">
			<liferay-portlet:renderURL varImpl="searchURL"><portlet:param name="struts_action" value="/wiki/search" /></liferay-portlet:renderURL>

			<%
			portletURL.setParameter("nodeName", node.getName());
			%>

			<form action="<%= searchURL %>" method="get" name="<portlet:namespace />sidebarFm" onSubmit="submitForm(this); return false;">
			<liferay-portlet:renderURLParams varImpl="searchURL" />
			<input name="<portlet:namespace />redirect" type="hidden" value="<%= HtmlUtil.escape(currentURL) %>" />
			<input name="<portlet:namespace />nodeId" type="hidden" value="<%= node.getNodeId() %>" />

			<div class="search">
				<input class="search-input" name="<portlet:namespace />keywords" type="text" value="<%= HtmlUtil.escape(keywords) %>" />

				<input class="search-button" type="submit" value="<liferay-ui:message key="search" />" />
			</div>

			</form>
		</c:if>

		<c:if test="<%= nodes.size() > 1 %>">
			<div class="wiki-navigation">
				<h2><liferay-ui:message key="wiki-navigation" /></h2>

				<ul class="disc">

					<%
					for (WikiNode curNode : nodes) {
					%>

						<li>
							<a <%= (node != null) && (node.getNodeId() == curNode.getNodeId()) ? "class=\"node-current\"" : "" %> href="<portlet:renderURL><portlet:param name="struts_action" value="/wiki/view" /><portlet:param name="nodeName" value="<%= curNode.getName() %>" /><portlet:param name="title" value="<%= WikiPageConstants.FRONT_PAGE %>" /></portlet:renderURL>"><nobr><%= curNode.getName() %></nobr></a>
						</li>

					<%
					}
					%>

				</ul>
			</div>
		</c:if>

		<div class="quick-links">
			<h2><liferay-ui:message key="quick-links" /></h2>

			<ul class="disc">
				<c:if test="<%= WikiPermission.contains(permissionChecker, scopeGroupId, ActionKeys.ADD_NODE) %>">
					<li>

						<%
						portletURL.setParameter("struts_action", "/wiki/view_nodes");
						%>

						<a href="<%= portletURL.toString() %>"><nobr><liferay-ui:message key="manage-wikis" /></nobr></a>
					</li>
					<li>

						<%
						PortletURL frontPageURL = PortletURLUtil.clone(portletURL, renderResponse);

						frontPageURL.setParameter("struts_action", "/wiki/view");
						frontPageURL.setParameter("title", WikiPageConstants.FRONT_PAGE);
						%>

						<a href="<%= frontPageURL.toString() %>"><nobr><%= WikiPageConstants.FRONT_PAGE %></nobr></a>
					</li>
					<li>

						<%
						portletURL.setParameter("struts_action", "/wiki/view_recent_changes");
						%>

						<a href="<%= portletURL.toString() %>"><nobr><liferay-ui:message key="recent-changes" /></nobr></a>
					</li>
					<li>

						<%
						portletURL.setParameter("struts_action", "/wiki/view_all_pages");
						%>

						<a href="<%= portletURL.toString() %>"><nobr><liferay-ui:message key="all-pages" /></nobr></a>
					</li>
					<li>

						<%
						portletURL.setParameter("struts_action", "/wiki/view_orphan_pages");
						%>

						<a href="<%= portletURL.toString() %>"><nobr><liferay-ui:message key="orphan-pages" /></nobr></a>
					</li>
				</c:if>

				<c:if test="<%= PortletPermissionUtil.contains(permissionChecker, layout.getPlid(), portletDisplay.getResourcePK(), ActionKeys.CONFIGURATION) %>">

					<%
					String modelResource = "com.liferay.portlet.wiki";
					String modelResourceDescription = portletDisplay.getTitle();
					String resourcePrimKey = String.valueOf(scopeGroupId);

					if (node != null) {
						modelResource = WikiNode.class.getName();
						modelResourceDescription = node.getName();
						resourcePrimKey = String.valueOf(node.getNodeId());
					}
					%>

					<liferay-security:permissionsURL
						modelResource="<%= modelResource %>"
						modelResourceDescription="<%= HtmlUtil.escape(modelResourceDescription) %>"
						resourcePrimKey="<%= resourcePrimKey %>"
						var="permissionsURL"
					/>

					<li>
						<a href="<%= permissionsURL %>"><liferay-ui:message key="permissions" /></a>
					</li>
				</c:if>
			</ul>
		</div>
	</c:if>
</div>