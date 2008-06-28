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

Producer producer = (Producer)row.getObject();
%>

<liferay-ui:icon-menu>
	<c:if test="<%= ProducerPermission.contains(permissionChecker, producer, ActionKeys.UPDATE) %>">
		<portlet:renderURL windowState="<%= WindowState.MAXIMIZED.toString() %>" var="editURL">
			<portlet:param name="<%= Constants.CMD %>" value="<%= Constants.UPDATE %>" />
			<portlet:param name="redirect" value='<%= "" %>' />
			<portlet:param name="producerId" value="<%= String.valueOf(producer.getProducerId()) %>" />
		</portlet:renderURL>

		<liferay-ui:icon image="edit" url="<%= editURL %>" />
	</c:if>

	<c:if test="<%= ProducerPermission.contains(permissionChecker, producer, ActionKeys.PERMISSIONS) %>">
		<liferay-security:permissionsURL
			modelResource="<%= Producer.class.getName() %>"
			modelResourceDescription="<%= producer.getName() %>"
			resourcePrimKey="<%= String.valueOf(producer.getProducerId()) %>"
			var="permissionsURL"
		/>

		<liferay-ui:icon image="permissions" url="<%= permissionsURL %>" />
	</c:if>

	<c:if test="<%= ProducerPermission.contains(permissionChecker, producer, ActionKeys.DELETE) %>">
		<portlet:actionURL windowState="<%= WindowState.MAXIMIZED.toString() %>" var="deleteURL">
			<portlet:param name="<%= Constants.CMD %>" value="<%= Constants.DELETE %>" />
			<portlet:param name="redirect" value='<%= "" %>' />
			<portlet:param name="producerId" value="<%= String.valueOf(producer.getProducerId()) %>" />
		</portlet:actionURL>

		<liferay-ui:icon-delete url="<%= deleteURL %>" />
	</c:if>
</liferay-ui:icon-menu>