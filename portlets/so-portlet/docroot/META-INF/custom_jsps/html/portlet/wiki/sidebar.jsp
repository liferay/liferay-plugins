<%
/**
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
 */
%>

<%@ include file="/html/portlet/wiki/init.jsp" %>

<div class="sidebar">

	<%
	boolean print = ParamUtil.getBoolean(request, Constants.PRINT);
	%>

	<c:if test="<%= !print && portletName.equals(PortletKeys.WIKI) %>">

		<%
		WikiNode node = (WikiNode)request.getAttribute(WebKeys.WIKI_NODE);

		String keywords = ParamUtil.getString(request, "keywords");

		List<WikiNode> nodes  = WikiUtil.getNodes(scopeGroupId, visibleNodes, hiddenNodes, permissionChecker);

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
							<a <%= (node != null) && (node.getNodeId() == curNode.getNodeId()) ? "class=\"node-current\"" : "" %> href="<portlet:renderURL><portlet:param name="struts_action" value="/wiki/view" /><portlet:param name="nodeName" value="<%= curNode.getName() %>" /><portlet:param name="title" value="<%= WikiPageImpl.FRONT_PAGE %>" /></portlet:renderURL>"><nobr><%= curNode.getName() %></nobr></a>
						</li>

					<%
					}
					%>

				</ul>
			</div>
		</c:if>

		<c:if test="<%= themeDisplay.isSignedIn() && PortletPermissionUtil.contains(permissionChecker, plid, PortletKeys.WIKI, ActionKeys.ADD_NODE) %>">
			<div class="quick-links">
				<h2><liferay-ui:message key="quick-links" /></h2>

				<ul class="disc">
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
						frontPageURL.setParameter("title", WikiPageImpl.FRONT_PAGE);
						%>

						<a href="<%= frontPageURL.toString() %>"><nobr><%= WikiPageImpl.FRONT_PAGE %></nobr></a>
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
				</ul>
			</c:if>
		</div>
	</c:if>
</div>