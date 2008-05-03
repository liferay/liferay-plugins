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
		<style type="text/css">
			.jira-summary .issue-count {
				padding-right: 4px;
				text-align: right;
				width: 5%;
			}

			.jira-summary .activity-details {
				width: 65%;
				padding-bottom: 5px;
			}

			.jira-summary .activity-details .progress-bar {
				background: #c2c8cb;
				height: 15px;
				margin-bottom: 2px;
			}

			.jira-summary .activity-details .progress-text {
				padding-left: 4px;
				font-weight: bold;
			}
		</style>

		<%
		String jiraURL = "http://support.liferay.com/secure/IssueNavigator.jspa?reset=true&pid=" + JIRAConstants.PROJECT_LEP;

		Date lastWeek = JIRAUtil.getJIRADate(-1);

		int assignedIssuesTotalCount = JIRAIssueLocalServiceUtil.getAssigneeJIRAIssuesCount(JIRAConstants.PROJECT_LEP, jiraUserId);
		int assignedIssuesClosedCount = JIRAIssueLocalServiceUtil.getAssigneeJIRAIssuesCount(JIRAConstants.PROJECT_LEP, jiraUserId, JIRAConstants.STATUS_CLOSED);
		int assignedIssuesLastWeekCount = JIRAIssueLocalServiceUtil.getAssigneeJIRAIssuesCount(lastWeek, JIRAConstants.PROJECT_LEP, jiraUserId);
		int assignedIssuesOpenCount = assignedIssuesTotalCount - assignedIssuesClosedCount;

		int reporterIssuesTotalCount = JIRAIssueLocalServiceUtil.getReporterJIRAIssuesCount(JIRAConstants.PROJECT_LEP, jiraUserId);
		int reporterIssuesClosedCount = JIRAIssueLocalServiceUtil.getReporterJIRAIssuesCount(JIRAConstants.PROJECT_LEP, jiraUserId, JIRAConstants.STATUS_CLOSED);
		int reporterIssuesLastWeekCount = JIRAIssueLocalServiceUtil.getReporterJIRAIssuesCount(lastWeek, JIRAConstants.PROJECT_LEP, jiraUserId);
		int reporterIssuesOpenCount = reporterIssuesTotalCount - reporterIssuesClosedCount;

		Object[][] jiraValuesArray = new Object[][] {
			new Object[] {
				"assigned-issues",
				jiraURL + "&assigneeSelect=specificuser&assignee=" + jiraUserId
			},
			new Object[] {
				"open", request.getContextPath() + "/jira/images/status_open.png",
				jiraURL + "&assigneeSelect=specificuser&assignee=" + jiraUserId + "&resolution=-1",
				new Integer(assignedIssuesOpenCount), new Integer(assignedIssuesTotalCount)
			},
			new Object[] {
				"closed", request.getContextPath() + "/jira/images/status_closed.png",
				jiraURL + "&assigneeSelect=specificuser&assignee=" + jiraUserId + "&status=" + JIRAConstants.STATUS_CLOSED,
				new Integer(assignedIssuesClosedCount), new Integer(assignedIssuesTotalCount)
			},
			new Object[] {
				"last-week", request.getContextPath() + "/jira/images/calendar.png",
				jiraURL + "&assigneeSelect=specificuser&assignee=" + jiraUserId + "&updated:previous=-1w",
				new Integer(assignedIssuesLastWeekCount), new Integer(assignedIssuesTotalCount)
			},
			new Object[] {
				"reported-issues",
				jiraURL + "&reporterSelect=specificuser&reporter=" + jiraUserId
			},
			new Object[] {
				"open", request.getContextPath() + "/jira/images/status_open.png",
				jiraURL + "&reporterSelect=specificuser&reporter=" + jiraUserId + "&resolution=-1",
				new Integer(reporterIssuesOpenCount), new Integer(reporterIssuesTotalCount)
			},
			new Object[] {
				"closed", request.getContextPath() + "/jira/images/status_closed.png",
				jiraURL + "&reporterSelect=specificuser&reporter=" + jiraUserId + "&status=" + JIRAConstants.STATUS_CLOSED,
				new Integer(reporterIssuesClosedCount), new Integer(reporterIssuesTotalCount)
			},
			new Object[] {
				"last-week", request.getContextPath() + "/jira/images/calendar.png",
				jiraURL + "&reporterSelect=specificuser&reporter=" + jiraUserId + "&updated:previous=-1w",
				new Integer(reporterIssuesLastWeekCount), new Integer(reporterIssuesTotalCount)
			}
		};
		%>

		<table class="jira-summary lfr-table">

		<%
		for (int i = 0; i < jiraValuesArray.length; i++) {
			Object[] jiraValues = jiraValuesArray[i];

			if (jiraValues.length == 2) {
				String message = (String)jiraValues[0];
				String url = (String)jiraValues[1];
		%>

				<tr>
					<td colspan="3">
						<c:if test="<%= i != 0 %>">
							<br />
						</c:if>

						<a href="<%= url %>" target="_blank"><b><liferay-ui:message key="<%= message %>" /></b></a>
					</td>
				</tr>

		<%
			}
			else {
				String message = (String)jiraValues[0];
				String icon = (String)jiraValues[1];
				String url = "javascript: location.href = '" + (String)jiraValues[2] + "';";
				int curCount = ((Integer)jiraValues[3]).intValue();
				int totalCount = ((Integer)jiraValues[4]).intValue();

				double ratio = (double)curCount / (double)totalCount;

				if (totalCount == 0) {
					ratio = 0;
				}
		%>

				<tr>
					<td>
						<liferay-ui:icon
							message="<%= message %>"
							src="<%= icon %>"
							url="<%= url %>"
							target="_blank"
							label="<%= true %>"
						/>
					</td>
					<td>
						<%= numberFormat.format(curCount) %>
					</td>
					<td class="activity-details">
						<div class="progress-bar" style="width: <%= percentFormat.format(ratio) %>;">
							<span class="progress-text"><%= percentFormat.format(ratio) %></span>
						</div>
					</td>
				</tr>

		<%
			}
		}
		%>

		</table>
	</c:when>
	<c:otherwise>
		<c:choose>
			<c:when test="<%= UserPermissionUtil.contains(permissionChecker, user2.getUserId(), ActionKeys.UPDATE) %>">
				<a href="<liferay-portlet:renderURL windowState="<%= WindowState.MAXIMIZED.toString() %>" portletName="1_WAR_wolportlet" />">Set your JIRA login.</a>
			</c:when>
			<c:otherwise>
				<%= LanguageUtil.format(pageContext, (user2.isMale() ? "x-has-not-configured-his-jira-login" : "x-has-not-configured-her-jira-login"), user2.getFullName()) %>
			</c:otherwise>
		</c:choose>
	</c:otherwise>
</c:choose>