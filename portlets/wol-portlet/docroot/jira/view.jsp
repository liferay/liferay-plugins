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
String jiraUserId = ExpandoValueLocalServiceUtil.getData(User.class.getName(), "WOL", "jiraUserId", user2.getUserId(), StringPool.BLANK);
%>

<c:choose>
	<c:when test="<%= Validator.isNotNull(jiraUserId) %>">

		<%
		int assignedIssuesTotalCount = JIRAIssueLocalServiceUtil.getAssigneeJIRAIssuesCount(JIRAConstants.PROJECT_LEP, jiraUserId);
		int assignedIssuesClosedCount = JIRAIssueLocalServiceUtil.getAssigneeJIRAIssuesCount(JIRAConstants.PROJECT_LEP, jiraUserId, JIRAConstants.STATUS_CLOSED);

		Date firstAssignedIssueDate = null;

		if (assignedIssuesTotalCount > 0) {
			firstAssignedIssueDate = JIRAIssueLocalServiceUtil.getFirstAssigneeJIRAIssue(JIRAConstants.PROJECT_LEP, jiraUserId).getCreateDate();
		}
		else {
			firstAssignedIssueDate = new Date();
		}
		%>

		<%= user2.getFullName() %> is assigned to <b><%= numberFormat.format(assignedIssuesTotalCount - assignedIssuesClosedCount) %></b> unresolved issues. He has resolved over <%= numberFormat.format(assignedIssuesClosedCount) %> issues since <%= dateFormatDate.format(firstAssignedIssueDate) %>.

		<br /><br />

		See unresolved <a href="http://support.liferay.com/secure/IssueNavigator.jspa?assigneeSelect=specificuser&sorter/field=priority&mode=hide&reset=true&resolution=-1&assignee=<%= jiraUserId %>&pid=<%= JIRAConstants.PROJECT_LEP %>&sorter/order=DESC" target="_blank">LEP</a> issues assigned to him.
	</c:when>
	<c:otherwise>
		<c:choose>
			<c:when test="<%= UserPermissionUtil.contains(permissionChecker, user2.getUserId(), ActionKeys.UPDATE) %>">
				<a href="<liferay-portlet:renderURL windowState="<%= WindowState.MAXIMIZED.toString() %>" portletName="1_WAR_wolportlet" />">Set your JIRA login.</a>
			</c:when>
			<c:otherwise>
				<%= user2.getFullName() %> has not configured his JIRA login.
			</c:otherwise>
		</c:choose>
	</c:otherwise>
</c:choose>