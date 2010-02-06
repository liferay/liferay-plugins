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

<%@ include file="/page_menu/init.jsp" %>

<%
PortletURL portletURL = renderResponse.createRenderURL();
%>

<c:choose>
	<c:when test="<%= wikiPage != null %>">
		<liferay-ui:panel-container id='pageMenu' extended="<%= Boolean.TRUE %>" persistState="<%= true %>">

			<%
			List<MenuItem> menuItems = MenuItem.fromWikiPage(wikiPage, portletURL);

			for (MenuItem menuItem : menuItems) {
				String url = menuItem.getURL();
				String label = menuItem.getLabel();
			%>

				<c:choose>
					<c:when test="<%= Validator.isNotNull(label) %>">
						<liferay-ui:panel id='pageMenu_<%= label %>' title='<%= label %>' collapsible="<%= true %>" persistState="<%= true %>" extended="<%= true %>">
							<%= _buildPageMenuLinksHTML(menuItem.getChildren()) %>
						</liferay-ui:panel>
					</c:when>
					<c:otherwise>
						<%= _buildPageMenuLinksHTML(menuItem.getChildren()) %>
					</c:otherwise>
				</c:choose>

			<%
			}
			%>

		</liferay-ui:panel-container>

		<c:if test="<%= PortletPermissionUtil.contains(permissionChecker, plid, portletDisplay.getId(), ActionKeys.CONFIGURATION) && WikiPagePermission.contains(permissionChecker, wikiPage, ActionKeys.UPDATE) %>">
			<br />

			<liferay-portlet:renderURL var="editURL" portletName="<%= PortletKeys.WIKI %>">
				<portlet:param name="struts_action" value="/wiki/edit_page" />
				<portlet:param name="nodeId" value="<%= String.valueOf(wikiPage.getNodeId()) %>" />
				<portlet:param name="title" value="<%= HtmlUtil.unescape(wikiPage.getTitle()) %>" />
			</liferay-portlet:renderURL>

			<liferay-ui:icon image="edit" label="true" url="<%= editURL %>" />
		</c:if>
	</c:when>
	<c:otherwise>

		<%
		renderRequest.setAttribute("PORTLET_CONFIGURATOR_VISIBILITY", Boolean.TRUE);
		%>

		<div class="portlet-msg-info">
			<a href="<%= portletDisplay.getURLConfiguration() %>"><liferay-ui:message key="please-select-a-wiki-page-that-will-act-as-a-navigation-menu" /></a>
		</div>
	</c:otherwise>
</c:choose>

<%!
private String _buildPageMenuLinksHTML(List<MenuItem> menuItems) {
	StringBuilder sb = new StringBuilder();

	sb.append("<ul class=\"page-menu\">");

	for (MenuItem menuItem : menuItems) {
		String label = menuItem.getLabel();
		String url = menuItem.getURL();

		sb.append("<li>");
		sb.append("<a href=\"");
		sb.append(url);
		sb.append("\"");

		if (menuItem.getExternalURL()) {
			sb.append(" target=\"_blank\"");
		}

		sb.append(">");
		sb.append(label);
		sb.append("</a>");
		sb.append("</li>");
	}

	sb.append("</ul>");

	return sb.toString();
}
%>