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
nodeId = ParamUtil.getLong(request, "nodeId", nodeId);

List<WikiNode> nodes = WikiNodeLocalServiceUtil.getNodes(scopeGroupId);

%>


<form action="<liferay-portlet:actionURL portletConfiguration="true" />" method="post" name="<portlet:namespace />fm">
<input name="<portlet:namespace /><%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />
<input id="<portlet:namespace/>nodeId" name="<portlet:namespace/>nodeId" type="hidden" value="<%= nodeId %>" />

<liferay-ui:error exception="<%= NoSuchNodeException.class %>" message="the-node-could-not-be-found" />

<table class="lfr-table">
<tr>
	<td class="lfr-label">
		<liferay-ui:message key="visualization" />
	</td>
	<td>
		<table class="lfr-table">
		<tr>
			<td>
				<input <%= (type.equals("tree")) ? "checked" : "" %> type="radio" name="type" value="tree"><liferay-ui:message key="tree" /></input>
			</td>
			<td>
				<liferay-ui:message key="depth" />
			</td>
			<td>
				<select name="<portlet:namespace />displayDepth">
					<option <%= (displayDepth == -1) ? "selected" : "" %> value="-1"><liferay-ui:message key="all" /></option>
					<%
					for (int i = 1; i <= 5; i++) {
					%>

						<option <%= (displayDepth == i) ? "selected" : "" %> value="<%= i %>"><%= i %></option>

					<%
					}
					%>

				</select>
			</td>
		</tr>
		<tr>
			<td>
				<input <%= (type.equals("menu")) ? "checked" : "" %> type="radio" name="type" value="menu"><liferay-ui:message key="menu" /></input>
			</td>
		</tr>
		</table>
	</td>
</tr>
<tr>
	<td colspan="2">
		<br />

		<liferay-ui:tabs names="node-based,page-based" refresh="<%= false %>">
			<liferay-ui:section>
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
								WikiNode node = (WikiNode)nodes.get(i);
							%>

								<option <%= (nodeId == node.getNodeId()) ? "selected" : "" %> value="<%= node.getNodeId() %>"><%= node.getName() %></option>

							<%
							}
							%>

						</select>
					</td>
				</tr>
				</table>
			</liferay-ui:section>
			<liferay-ui:section>
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
								WikiNode node = (WikiNode)nodes.get(i);
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
						<td>
							<liferay-ui:message key="page" />
						</td>
						<td>
							<select name="<portlet:namespace />title">
								<option value=""></option>

								<%

								for (int i = 0; i < wikiPages.size(); i++) {
									WikiPage wikiPage = (WikiPage)wikiPages.get(i);
								%>

									<option <%= (wikiPage.getTitle().equals(title) || (Validator.isNull(title) && wikiPage.getTitle().equals("FRONT_PAGE"))) ? "selected" : "" %> value="<%= wikiPage.getTitle()%>"><%= wikiPage.getTitle() %></option>

								<%
								}
								%>

							</select>
						</td>
					</tr>
				</c:if>

				</table>
			</liferay-ui:section>
		</liferay-ui:tabs>
	</td>
</tr>
</table>

<br />

<input type="button" value="<liferay-ui:message key="save" />" onClick="submitForm(document.<portlet:namespace />fm);" />

</form>