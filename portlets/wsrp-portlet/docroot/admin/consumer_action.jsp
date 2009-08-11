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
ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

WSRPConsumer wsrpConsumer = (WSRPConsumer)row.getObject();

WSRPConsumerManager wsrpConsumerManager = WSRPConsumerManagerFactory.getWSRPConsumerManager(wsrpConsumer);

ServiceDescription serviceDescription = wsrpConsumerManager.getServiceDescription();
%>

<liferay-ui:icon-menu>
	<portlet:renderURL var="editURL">
		<portlet:param name="jspPage" value="/admin/edit_consumer.jsp" />
		<portlet:param name="redirect" value="<%= currentURL %>" />
		<portlet:param name="wsrpConsumerId" value="<%= String.valueOf(wsrpConsumer.getWsrpConsumerId()) %>" />
	</portlet:renderURL>

	<liferay-ui:icon image="edit" url="<%= editURL %>" />

	<c:if test="<%= serviceDescription.isRequiresRegistration() %>">
		<portlet:renderURL var="editRegistrationURL">
			<portlet:param name="jspPage" value="/admin/edit_consumer_registration.jsp" />
			<portlet:param name="redirect" value="<%= currentURL %>" />
			<portlet:param name="wsrpConsumerId" value="<%= String.valueOf(wsrpConsumer.getWsrpConsumerId()) %>" />
		</portlet:renderURL>

		<liferay-ui:icon image="edit" message="edit-registration" url="<%= editRegistrationURL %>" />
	</c:if>

	<c:if test="<%= !serviceDescription.isRequiresRegistration() || (wsrpConsumer.getRegistrationContext() != null) %>">
		<portlet:renderURL var="managePortletsURL">
			<portlet:param name="jspPage" value="/admin/view_consumer_portlets.jsp" />
			<portlet:param name="wsrpConsumerId" value="<%= String.valueOf(wsrpConsumer.getWsrpConsumerId()) %>" />
		</portlet:renderURL>

		<liferay-ui:icon image="portlet" message="manage-portlets" url="<%= managePortletsURL %>" />
	</c:if>

	<portlet:actionURL name="deleteWSRPConsumer" var="deleteURL">
		<portlet:param name="redirect" value="<%= currentURL %>" />
		<portlet:param name="wsrpConsumerId" value="<%= String.valueOf(wsrpConsumer.getWsrpConsumerId()) %>" />
	</portlet:actionURL>

	<liferay-ui:icon-delete url="<%= deleteURL %>" />
</liferay-ui:icon-menu>