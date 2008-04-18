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

<script type="text/javascript">
	function <portlet:namespace />deleteWallEntry(wallEntryId) {
		document.<portlet:namespace />fm.<portlet:namespace /><%= Constants.CMD %>.value = "<%= Constants.DELETE %>";
		document.<portlet:namespace />fm.<portlet:namespace />wallEntryId.value = wallEntryId;
		submitForm(document.<portlet:namespace />fm);
	}
</script>

<form action="<portlet:actionURL />" method="post" name="<portlet:namespace />fm">
<input name="<portlet:namespace /><%= Constants.CMD %>" type="hidden" value="<%= Constants.ADD %>" />
<input name="<portlet:namespace />redirect" type="hidden" value="<%= PortalUtil.getCurrentURL(request) %>" />
<input name="<portlet:namespace />wallEntryId" type="hidden" value="" />

<liferay-ui:input-field model="<%= WallEntry.class %>" bean="<%= null %>" field="comments" />

<br /><br />

<input type="submit" value="<liferay-ui:message key="post" />" />

</form>

<%
PortletURL portletURL = renderResponse.createRenderURL();

SearchContainer searchContainer = new SearchContainer(renderRequest, null, null, SearchContainer.DEFAULT_CUR_PARAM, 5, portletURL, null, null);

int total = WallEntryLocalServiceUtil.getWallEntriesCount(group.getGroupId());

searchContainer.setTotal(total);

List<WallEntry> results = WallEntryLocalServiceUtil.getWallEntries(group.getGroupId(), searchContainer.getStart(), searchContainer.getEnd());
%>

<table class="lfr-table" width="100%">

<%
for (WallEntry wallEntry : results) {
%>

	<tr>
		<td colspan="2">
			<div class="separator"><!-- --></div>
		</td>
	</tr>
	<tr>
		<td align="center" valign="top">
			<liferay-ui:user-display
				userId="<%= wallEntry.getUserId() %>"
				userName="<%= wallEntry.getUserName() %>"
				displayStyle="<%= 2 %>"
			/>
		</td>
		<td valign="top" width="99%">
			<div>
				<%= wallEntry.getComments() %>
			</div>

			<br />

			<div>
				<%= LanguageUtil.format(pageContext, "posted-on-x", dateFormatDateTime.format(wallEntry.getCreateDate())) %>
			</div>

			<c:if test="<%= UserPermissionUtil.contains(permissionChecker, user2.getUserId(), ActionKeys.UPDATE) %>">
				<br />

				<%
				String deleteHREF = "javascript: " + namespace + "deleteWallEntry(" + wallEntry.getWallEntryId() + ");";
				%>

				<div>
					<liferay-ui:icon-delete url="<%= deleteHREF %>" label="<%= true %>" />
				</div>
			</c:if>
		</td>
	</tr>

<%
}
%>

</table>

<c:if test="<%= results.size() > 0 %>">
	<div class="separator"><!-- --></div>

	<div class="taglib-search-iterator-page-iterator-bottom">
		<liferay-ui:search-paginator searchContainer="<%= searchContainer %>" />
	</div>
</c:if>