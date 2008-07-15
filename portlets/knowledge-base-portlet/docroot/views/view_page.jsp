<%
/**
 * Copyright (c) 2000-2008 Liferay, Inc. All rights reserved.
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
<%@ include file="/init.jsp" %>

<%
WikiNode node = (WikiNode)request.getAttribute(KnowledgeBaseKeys.WIKI_NODE);
WikiPage wikiPage = (WikiPage)request.getAttribute(KnowledgeBaseKeys.WIKI_PAGE);

String title = wikiPage.getTitle();

boolean print = ParamUtil.getBoolean(request, Constants.PRINT);

PortletURL listURL = renderResponse.createRenderURL();

listURL.setParameter(Constants.CMD, "list");

PortletURL viewPageURL = renderResponse.createRenderURL();

viewPageURL.setParameter(Constants.CMD, "view_page");
viewPageURL.setParameter("nodeName", node.getName());
viewPageURL.setParameter("title", title);

PortletURL addPageURL = renderResponse.createRenderURL();

addPageURL.setParameter(Constants.CMD, "edit_page");
addPageURL.setParameter("redirect", currentURL);
addPageURL.setParameter("nodeId", String.valueOf(node.getNodeId()));
addPageURL.setParameter("editTitle", "1");

if (wikiPage != null) {
	addPageURL.setParameter("parentTitle", wikiPage.getTitle());
}

PortletURL editPageURL = renderResponse.createRenderURL();

editPageURL.setParameter(Constants.CMD, "edit_page");
editPageURL.setParameter("redirect", currentURL);
editPageURL.setParameter("nodeId", String.valueOf(node.getNodeId()));
editPageURL.setParameter("title", title);

PortletURL printPageURL = renderResponse.createRenderURL();

printPageURL.setParameter(Constants.CMD, "view_page");
printPageURL.setParameter("nodeId", String.valueOf(node.getNodeId()));
printPageURL.setParameter("title", title);
printPageURL.setWindowState(LiferayWindowState.POP_UP);

printPageURL.setParameter("print", "true");

PortletURL taggedPagesURL = renderResponse.createRenderURL();

taggedPagesURL.setParameter(Constants.CMD, "view_tagged_pages");
taggedPagesURL.setParameter("nodeId", String.valueOf(node.getNodeId()));

%>

<c:if test="<%= !print %>">
	<div style="text-align: right; margin-bottom: 1em">
		<a href="<%= listURL %>">&laquo; <liferay-ui:message key="back" /></a>
	</div>
</c:if>

<c:choose>
	<c:when test="<%= print %>">
		<script type="text/javascript">
			print();
		</script>

		<div class="popup-print">
			<liferay-ui:icon image="print" message="print" url="javascript: print();" />
		</div>
	</c:when>
	<c:otherwise>
		<script type="text/javascript">
			function <portlet:namespace />printPage() {
				window.open('<%= printPageURL %>', '', "directories=0,height=480,left=80,location=1,menubar=1,resizable=1,scrollbars=yes,status=0,toolbar=0,top=180,width=640");
			}
		</script>
	</c:otherwise>
</c:choose>

<c:if test="<%= Validator.isNotNull(wikiPage.getParentTitle()) %>">
	<div class="breadcrumbs">

		<%
		PortletURL viewParentPageURL = renderResponse.createRenderURL();

		viewParentPageURL.setParameter(Constants.CMD, "view_page");
		viewParentPageURL.setParameter("nodeId", String.valueOf(node.getNodeId()));

		List parentPages = wikiPage.getParentPages();

		for (int i = 0; i < parentPages.size(); i++) {
			WikiPage curParentPage = (WikiPage)parentPages.get(i);

			viewParentPageURL.setParameter("title", curParentPage.getTitle());
		%>

			<a href="<%= viewParentPageURL %>"><%= curParentPage.getTitle() %></a>

			<c:if test="<%= (i + 1) < parentPages.size() %>">
				&raquo;
			</c:if>

		<%
		}
		%>

	</div>
</c:if>

<h1 class="page-title">
	<c:if test="<%= !print %>">
		<div class="page-actions">
			<liferay-ui:icon image="edit" url="<%= editPageURL.toString() %>" />

			<liferay-ui:icon image="print" message="print" url='<%= "javascript: " + renderResponse.getNamespace() + "printPage();" %>' />
		</div>
	</c:if>

	<%= title %>
</h1>

<liferay-ui:tags-summary
	className="<%= WikiPage.class.getName() %>"
	classPK="<%= wikiPage.getResourcePrimKey() %>"
	portletURL="<%= taggedPagesURL %>"
/>

<div>
	<div class="knowledge-base-body">
		<%= wikiPage.getContent() %>
	</div>
</div>