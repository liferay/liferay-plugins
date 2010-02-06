<%
/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
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