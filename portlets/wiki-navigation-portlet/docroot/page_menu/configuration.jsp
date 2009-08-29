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