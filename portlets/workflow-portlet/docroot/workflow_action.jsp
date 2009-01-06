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
String tabs1 = ParamUtil.getString(request, "tabs1");

ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

String className = row.getClassName();
String classHoverName = row.getClassHoverName();

WorkflowInstance instance = (WorkflowInstance)request.getAttribute("WORKFLOW_INSTANCE");

String instanceId = String.valueOf(instance.getInstanceId());

WorkflowDefinition definition = instance.getDefinition();

WorkflowToken token = (WorkflowToken)request.getAttribute("WORKFLOW_TOKEN");

List tasks = (List)request.getAttribute("WORKFLOW_TASKS");
%>

<c:if test="<%= !instance.isEnded() %>">
	<c:choose>
		<c:when test="<%= tasks.size() > 0 %>">
			<c:if test='<%= tabs1.equals("instances") || tabs1.equals("my-workflows") %>'>
					</td>
				</tr>
			</c:if>

			<%
			for (int i = 0; i < tasks.size(); i++) {
				WorkflowTask task = (WorkflowTask)tasks.get(i);

				String taskId = String.valueOf(task.getTaskId());

				String displayName = null;

				if (token != null) {
					displayName = token.getName();
				}
				else {
					displayName = task.getName();
				}
			%>

				<portlet:renderURL windowState="<%= WindowState.MAXIMIZED.toString() %>" var="manageTaskURL">
					<portlet:param name="jspPage" value="/edit_task.jsp" />
					<portlet:param name="redirect" value="<%= currentURL %>" />
					<portlet:param name="instanceId" value="<%= instanceId %>" />
					<portlet:param name="taskId" value="<%= taskId %>" />
				</portlet:renderURL>

				<c:if test='<%= tabs1.equals("instances") || tabs1.equals("my-workflows") %>'>
					<tr class="<%= className %>" onMouseEnter="this.className = '<%= classHoverName %>';" onMouseLeave="this.className = '<%= className %>';">
						<td colspan="<%= viewType.equals("user") ? "3" : "5" %>"></td>
						<td>
							<b><%= LanguageUtil.get(pageContext, "task") %> <%= i + 1 %>:</b> <a href="<%= manageTaskURL %>"><%= LanguageUtil.get(pageContext, displayName) %></a>
						</td>
						<td align="right" valign="middle">
				</c:if>

				<c:if test="<%= WorkflowTaskPermission.contains(permissionChecker, task, ActionKeys.PERMISSIONS) %>">
					<liferay-security:permissionsURL
						modelResource="<%= WorkflowTask.class.getName() %>"
						modelResourceDescription='<%= definition.getName() + " " + definition.getVersion() + ", " + LanguageUtil.get(pageContext, "instance") + " " + instanceId + ", " + task.getName() %>'
						resourcePrimKey="<%= taskId %>"
						var="permissionsURL"
					/>

					<liferay-ui:icon image="permissions" url="<%= permissionsURL %>" />
				</c:if>

				<c:if test="<%= task.getEndDate() == null %>">
					<c:if test="<%= WorkflowTaskPermission.contains(permissionChecker, task, ActionKeys.MANAGE) %>">
						<liferay-ui:icon image="manage_task" message="manage" url="<%= manageTaskURL %>" />
					</c:if>
				</c:if>

			<%
			}
			%>

			<c:if test='<%= tabs1.equals("instances") || tabs1.equals("my-workflows") %>'>
					</td>
				</tr>
			</c:if>
		</c:when>
		<c:otherwise>
			<c:if test='<%= tabs1.equals("instances") || tabs1.equals("my-workflows") %>'>
					</td>
				</tr>
				<tr class="<%= className %>" onMouseEnter="this.className = '<%= classHoverName %>';" onMouseLeave="this.className = '<%= className %>';">
					<td colspan="<%= viewType.equals("user") ? "3" : "5" %>"></td>
					<td>
						<%= LanguageUtil.get(pageContext, token.getName()) %>
					</td>
					<td align="right" valign="middle">
			</c:if>

			<c:choose>
				<c:when test='<%= token.getType().equals("join") %>'>
					<liferay-ui:message key="waiting-on-sibling-tokens-to-complete" />
				</c:when>
				<c:otherwise>
					<c:if test="<%= WorkflowInstancePermission.contains(permissionChecker, instance, ActionKeys.SIGNAL) %>">

						<%
						PortletURL signalTokenURL = renderResponse.createActionURL();

						signalTokenURL.setWindowState(WindowState.MAXIMIZED);

						signalTokenURL.setParameter(ActionRequest.ACTION_NAME, "signalInstance");
						signalTokenURL.setParameter("redirect", currentURL);
						signalTokenURL.setParameter("instanceId", instanceId);
						signalTokenURL.setParameter("tokenId", String.valueOf(token.getTokenId()));
						%>

						<liferay-ui:icon image="signal_instance" message="signal" url="<%= signalTokenURL.toString() %>" />
					</c:if>
				</c:otherwise>
			</c:choose>

			<c:if test='<%= tabs1.equals("instances") || tabs1.equals("my-workflows") %>'>
					</td>
				</tr>
			</c:if>
		</c:otherwise>
	</c:choose>
</c:if>