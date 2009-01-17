<%/**
 * Copyright (c) 2000-2009 Liferay, Inc. All rights reserved.
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
 */%>

<%@ include file="/init.jsp" %>

<%
List<EntryDisplay> entries = (List<EntryDisplay>)renderRequest.getAttribute(WebKeys.NAVIGATION_ENTRIES);
WikiPage wikiPage = (WikiPage)renderRequest.getAttribute(WebKeys.WIKI_PAGE);
%>

<div class="portlet-wiki-navigation-menu">

	<%
	if (entries == null) {
		renderRequest.setAttribute(WebKeys.PORTLET_CONFIGURATOR_VISIBILITY, Boolean.TRUE);
	%>

		<div class="portlet-msg-info">
			<a href="<%= portletDisplay.getURLConfiguration() %>"><liferay-ui:message key="please-select-a-wiki-page-that-will-act-as-a-navigation-menu" /></a>
		</div>

	<%
	}
	else {
		StringBuilder sb = new StringBuilder();

		_buildNavigationMenu(entries, sb);

		out.print(sb.toString());
	}
	%>

	<c:if test="<%= (wikiPage != null) && PortletPermissionUtil.contains(permissionChecker, plid, portletDisplay.getId(), ActionKeys.CONFIGURATION) && WikiPagePermission.contains(permissionChecker, wikiPage, ActionKeys.UPDATE) %>">
		<br />

		<liferay-portlet:renderURL var="editURL" portletName="<%= PortletKeys.WIKI %>">
			<portlet:param name="struts_action" value="/wiki/edit_page" />
			<portlet:param name="nodeId" value="<%= String.valueOf(wikiPage.getNodeId()) %>" />
			<portlet:param name="title" value="<%= HtmlUtil.unescape(wikiPage.getTitle()) %>" />
		</liferay-portlet:renderURL>

		<liferay-ui:icon image="edit" label="true" url="<%= editURL %>" />
	</c:if>
</div>

<%!
private void _buildNavigationMenu(List<EntryDisplay> entries, StringBuilder sb) {
	for (EntryDisplay entry : entries) {
		if (entry.getChildren().isEmpty()) {
			continue;
		}

		String label = entry.getLabel();
		String href = entry.getUrl();

		if (Validator.isNotNull(label)) {
			sb.append("<h1><a name=\"");
			sb.append(label);
			sb.append("\" ");

			if (Validator.isNotNull(href)) {
				sb.append("href=\"");
				sb.append(href);
				sb.append("\"");
			}

			sb.append(">");
			sb.append(label);
			sb.append("</a></h1>");
		}

		sb.append("<ul>");

		for (EntryDisplay child : entry.getChildren()) {
			label = child.getLabel();
			href = child.getUrl();

			sb.append("<li><a href=\"");
			sb.append(href);
			sb.append("\" ");

			if (child.getExternalURL()) {
				sb.append("target=\"_blank\"");
			}
			else {
				sb.append("label=\"");
				sb.append(label);
				sb.append("\"");
			}

			sb.append(">");
			sb.append(label);
			sb.append("</a></li>");
		}

		sb.append("</ul>");
	}
}
%>