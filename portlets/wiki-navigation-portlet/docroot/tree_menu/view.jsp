<%
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
%>

<%@ include file="/tree_menu/init.jsp" %>

<%
String title = ParamUtil.getString(request, "title", PropsUtil.get("wiki.front.page.name"));

PortletURL portletURL = renderResponse.createRenderURL();

List<MenuItem> menuItems = MenuItem.fromWikiNode(selNodeId, depth, portletURL);
%>

<c:choose>
	<c:when test="<%= !menuItems.isEmpty() %>">
		<script type="text/javascript">
			jQuery(
				function() {
					var treeMenu = jQuery('.wiki-navigation-portlet-tree-menu .tree-menu');

					treeMenu.treeview(
						{
							animated: 'fast',
							cookieId: 'tree-menu',
							persist: 'cookie'
						}
					);
				}
			);
		</script>

		<%= _buildTreeMenuHTML(menuItems , title, true) %>
	</c:when>
	<c:otherwise>
		<liferay-ui:message key="no-wiki-pages-were-found" />
	</c:otherwise>
</c:choose>

<%!
private String _buildTreeMenuHTML(List<MenuItem> menuItems, String curTitle, boolean isRoot) {
	StringBuilder sb = new StringBuilder();

	if (isRoot) {
		sb.append("<ul class=\"tree-menu\">");
	}

	for (MenuItem menuItem : menuItems) {
		String label = menuItem.getLabel();
		String url = menuItem.getURL();

		sb.append("<li>");

		if (Validator.isNotNull(url)) {
			if (label.equals(curTitle)) {
				sb.append("<strong>");
				sb.append(label);
				sb.append("</strong>");
			}
			else {
				sb.append("<a ");
				sb.append("href=\"");
				sb.append(url);
				sb.append("\">");
				sb.append(label);
				sb.append("</a>");
			}
		}
		else {
			sb.append(label);
		}

		if (!menuItem.getChildren().isEmpty()) {
			sb.append("<ul>");
			sb.append(_buildTreeMenuHTML(menuItem.getChildren(), curTitle, false));
			sb.append("</ul>");
		}

		sb.append("</li>");
	}

	if (isRoot) {
		sb.append("</ul>");
	}

	return sb.toString();
}
%>