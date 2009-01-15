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

<%@ include file="/init.jsp" %>

<%
List<WikiNode> nodes = WikiNodeLocalServiceUtil.getNodes(scopeGroupId);

if ((nodeId == 0) && (nodes.size() == 1)) {
	nodeId = nodes.get(0).getNodeId();
}
%>

<div class="portlet-msg-info">
	<liferay-ui:message key="navigation-page-help" />
</div>

<form action="<liferay-portlet:actionURL portletConfiguration="true" />" method="post" name="<portlet:namespace />fm">
<input name="<portlet:namespace /><%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />
<input id="<portlet:namespace/>nodeId" name="<portlet:namespace/>nodeId" type="hidden" value="<%= nodeId %>" />

<liferay-ui:error exception="<%= NoSuchNodeException.class %>" message="the-node-could-not-be-found" />
<liferay-ui:error exception="<%= NoSuchPageException.class %>" message="the-page-could-not-be-found" />

<table class="lfr-table">
<tr>
	<td>
		<liferay-ui:message key="node" />
	</td>
	<td>
		<select onchange="javascript: jQuery('#<portlet:namespace />nodeId').val(this.value);">
			<option value=""></option>

			<%
			for (int i = 0; i < nodes.size(); i++) {
				WikiNode node = nodes.get(i);
			%>

				<option <%= (nodeId == node.getNodeId()) ? "selected" : "" %> value="<%= node.getNodeId() %>"><%= node.getName() %></option>

			<%
			}
			%>

		</select>
	</td>
</tr>

<c:if test="<%= nodeId > 0 %>">
	<tr>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td>
			<liferay-ui:message key="page" />
		</td>
		<td>
			<select name="<portlet:namespace />title">
				<option value=""></option>

				<%
				List<WikiPage> wikiPages = WikiPageServiceUtil.getNodePages(nodeId, WikiNavigationConstants.MAX_PAGES);

				for (int i = 0; i < wikiPages.size(); i++) {
					WikiPage wikiPage = wikiPages.get(i);
				%>

					<option <%= (wikiPage.getTitle().equals(title)) ? "selected" : "" %> value="<%= wikiPage.getTitle() %>"><%= wikiPage.getTitle() %></option>

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