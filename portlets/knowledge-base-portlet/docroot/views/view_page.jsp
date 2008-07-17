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
WikiPage wikiPage = (WikiPage)request.getAttribute(KnowledgeBaseKeys.WIKI_PAGE);

String title = wikiPage.getTitle();

boolean print = ParamUtil.getBoolean(request, Constants.PRINT);

TagsAssetLocalServiceUtil.incrementViewCounter(WikiPage.class.getName(), wikiPage.getResourcePrimKey());

PortletURL viewAllURL = renderResponse.createRenderURL();

PortletURL viewPageURL = renderResponse.createRenderURL();

viewPageURL.setParameter(Constants.CMD, "view_page");
viewPageURL.setParameter("title", title);

PortletURL addPageURL = renderResponse.createRenderURL();

addPageURL.setParameter(Constants.CMD, "edit_page");
addPageURL.setParameter("redirect", currentURL);
addPageURL.setParameter("editTitle", "1");

if (wikiPage != null) {
	addPageURL.setParameter("parentTitle", wikiPage.getTitle());
}

List childPages = wikiPage.getChildPages();

PortletURL editPageURL = renderResponse.createRenderURL();

editPageURL.setParameter(Constants.CMD, "edit_page");
editPageURL.setParameter("redirect", currentURL);
editPageURL.setParameter("title", title);

PortletURL printPageURL = renderResponse.createRenderURL();

printPageURL.setParameter(Constants.CMD, "view_page");
printPageURL.setParameter("title", title);
printPageURL.setWindowState(LiferayWindowState.POP_UP);

printPageURL.setParameter("print", "true");

PortletURL taggedPagesURL = renderResponse.createRenderURL();

taggedPagesURL.setParameter(Constants.CMD, "view_tagged_pages");
%>

<c:if test="<%= !print %>">
	<div style="text-align: right; margin-bottom: 1em">
		<a href="<%= viewAllURL %>">&laquo; <liferay-ui:message key="back" /></a>
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
	<%= title %>
</h1>

<div>
	<c:if test="<%= !print %>">
		<div class="side-boxes">
			<div class="side-box">
				<div class="side-box-title"><liferay-ui:message key="details" /></div>
				<div class="side-box-content">
					<ul class="lfr-component">
						<li>
							<b><liferay-ui:message key="version" /></b>: <%= wikiPage.getVersion() %>
						</li>
						<li>
							<b><liferay-ui:message key="updated" /></b>: <%= dateFormatDateTime.format(wikiPage.getModifiedDate()) %>
						</li>
						<li>
							<b><liferay-ui:message key="by" /></b>: <%= wikiPage.getUserName() %>
						</li>
						<li>
							<b><liferay-ui:message key="views" /></b>:
							<%
							TagsAsset asset = TagsAssetLocalServiceUtil.getAsset(WikiPage.class.getName(), wikiPage.getResourcePrimKey());
							%>
							<%= asset.getViewCount() %>
						</li>
						<li>
							<liferay-ui:tags-summary
								className="<%= WikiPage.class.getName() %>"
								classPK="<%= wikiPage.getResourcePrimKey() %>"
								portletURL="<%= taggedPagesURL %>"
							/>
						</li>

				</div>
			</div>
			<div class="side-box">
				<div class="side-box-title"><liferay-ui:message key="attachments" /></div>
				<div class="side-box-content">
					0 <liferay-ui:message key="attachments" />
				</div>
			</div>
			<div class="side-box">
				<div class="side-box-title"><liferay-ui:message key="actions" /></div>
				<div class="side-box-content">
					<ul class="lfr-component">
						<li><liferay-ui:icon image="edit" label="<%= true %>" url="<%= editPageURL.toString() %>" /></li>
						<li><liferay-ui:icon image="print" label="<%= true %>" message="print" url='<%= "javascript: " + renderResponse.getNamespace() + "printPage();" %>' /></li>
					</ul>
				</div>
			</div>
		</div>
	</c:if>

	<div class="knowledge-base-body">
		<%= wikiPage.getContent() %>
	</div>
</div>

<c:if test="<%= (childPages.size() > 0) %>">
	<div class="separator"><!-- --></div>
</c:if>

<c:if test="<%= childPages.size() > 0 %>">
	<div class="child-pages">
		<h3><liferay-ui:message key="children" /></h3>

		<ul class="child-pages">

			<%
			PortletURL curPageURL = renderResponse.createRenderURL();
			curPageURL.setParameter(Constants.CMD, "view_page");

			for (int i = 0; i < childPages.size(); i++) {
				WikiPage curPage = (WikiPage)childPages.get(i);

				curPageURL.setParameter("title", curPage.getTitle());
			%>

				<li>
					<a href="<%= curPageURL %>"><%= curPage.getTitle() %></a>
				</li>

			<%
			}
			%>

		</ul>
	</div>
</c:if>

<c:if test="<%= !print %>">
	<div class="page-actions">
		<liferay-ui:icon image="add_article" message="add-child-article" url="<%= addPageURL.toString() %>" label="<%= true %>" />
	</div>
</c:if>