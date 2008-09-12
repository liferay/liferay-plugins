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
ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

WorkflowInstance instance = (WorkflowInstance)row.getObject();

String instanceId = String.valueOf(instance.getInstanceId());

WorkflowDefinition definition = instance.getDefinition();
WorkflowToken token = instance.getToken();

List tasks = token.getTasks();
List children = token.getChildren();

request.setAttribute("WORKFLOW_INSTANCE", instance);
%>

<c:if test="<%= WorkflowInstancePermission.contains(permissionChecker, instance, ActionKeys.PERMISSIONS) %>">
	<liferay-security:permissionsURL
		modelResource="<%= WorkflowInstance.class.getName() %>"
		modelResourceDescription='<%= definition.getName() + " " + definition.getVersion() + ", " + LanguageUtil.get(pageContext, "instance") + " " + instance.getInstanceId() %>'
		resourcePrimKey="<%= instanceId %>"
		var="permissionsURL"
	/>

	<liferay-ui:icon image="permissions" url="<%= permissionsURL %>" />
</c:if>

<c:if test="<%= !instance.isEnded() %>">
	<c:choose>
		<c:when test="<%= tasks.size() > 0 %>">

			<%
			request.setAttribute("WORKFLOW_TOKEN", token);
			request.setAttribute("WORKFLOW_TASKS", tasks);

			RequestDispatcher requestDispatcher = application.getRequestDispatcher("/workflow_action.jsp");

			requestDispatcher.include(request, response);
			%>

		</c:when>
		<c:when test="<%= children.size() == 0 %>">
			<c:if test="<%= WorkflowInstancePermission.contains(permissionChecker, instance, ActionKeys.SIGNAL) %>">
				<portlet:actionURL windowState="<%= WindowState.MAXIMIZED.toString() %>" var="signalInstanceURL" name="signalInstance">
					<portlet:param name="redirect" value="<%= currentURL %>" />
					<portlet:param name="instanceId" value="<%= instanceId %>" />
				</portlet:actionURL>

				<liferay-ui:icon image="signal_instance" message="signal" url="<%= signalInstanceURL %>" />
			</c:if>
		</c:when>
		<c:when test="<%= children.size() > 0 %>">

			<%
			for (int i = 0; i < children.size(); i++) {
				WorkflowToken childToken = (WorkflowToken)children.get(i);

				request.setAttribute("WORKFLOW_TOKEN", childToken);
				request.setAttribute("WORKFLOW_TASKS", childToken.getTasks());

				RequestDispatcher requestDispatcher = application.getRequestDispatcher("/workflow_action.jsp");

				requestDispatcher.include(request, response);
			}
			%>

		</c:when>
	</c:choose>
</c:if>