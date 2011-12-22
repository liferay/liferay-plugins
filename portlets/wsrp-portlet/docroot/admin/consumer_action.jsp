<%--
/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */
--%>

<%@ include file="/init.jsp" %>

<%
ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

WSRPConsumer wsrpConsumer = (WSRPConsumer)row.getObject();

ServiceDescription serviceDescription = null;

try {
	WSRPConsumerManager wsrpConsumerManager = WSRPConsumerManagerFactory.getWSRPConsumerManager(wsrpConsumer, userToken);

	serviceDescription = wsrpConsumerManager.getServiceDescription();
}
catch (Exception e) {
}
%>

<c:choose>
	<c:when test="<%= serviceDescription == null %>">
		<liferay-ui:icon-menu>
			<portlet:actionURL name="deleteWSRPConsumer" var="deleteURL">
				<portlet:param name="redirect" value="<%= currentURL %>" />
				<portlet:param name="wsrpConsumerId" value="<%= String.valueOf(wsrpConsumer.getWsrpConsumerId()) %>" />
			</portlet:actionURL>

			<liferay-ui:icon-delete url="<%= deleteURL %>" />

			<portlet:actionURL name="restartConsumer" var="restartConsumerURL">
				<portlet:param name="redirect" value="<%= currentURL %>" />
				<portlet:param name="wsrpConsumerId" value="<%= String.valueOf(wsrpConsumer.getWsrpConsumerId()) %>" />
			</portlet:actionURL>

			<liferay-ui:icon image="portlet" message="restart-consumer" url="<%= restartConsumerURL %>" />
		</liferay-ui:icon-menu>
	</c:when>
	<c:otherwise>
		<liferay-ui:icon-menu>
			<portlet:renderURL var="editURL">
				<portlet:param name="jspPage" value="/admin/edit_consumer.jsp" />
				<portlet:param name="redirect" value="<%= currentURL %>" />
				<portlet:param name="wsrpConsumerId" value="<%= String.valueOf(wsrpConsumer.getWsrpConsumerId()) %>" />
			</portlet:renderURL>

			<liferay-ui:icon
				image="edit"
				url="<%= editURL %>"
			/>

			<c:if test="<%= serviceDescription.isRequiresRegistration() %>">
				<portlet:renderURL var="editRegistrationURL">
					<portlet:param name="jspPage" value="/admin/edit_consumer_registration.jsp" />
					<portlet:param name="redirect" value="<%= currentURL %>" />
					<portlet:param name="wsrpConsumerId" value="<%= String.valueOf(wsrpConsumer.getWsrpConsumerId()) %>" />
				</portlet:renderURL>

				<liferay-ui:icon
					image="edit"
					message="edit-registration"
					url="<%= editRegistrationURL %>"
				/>
			</c:if>

			<c:if test="<%= !serviceDescription.isRequiresRegistration() || (wsrpConsumer.getRegistrationContext() != null) %>">
				<portlet:renderURL var="managePortletsURL">
					<portlet:param name="jspPage" value="/admin/view_consumer_portlets.jsp" />
					<portlet:param name="wsrpConsumerId" value="<%= String.valueOf(wsrpConsumer.getWsrpConsumerId()) %>" />
				</portlet:renderURL>

				<liferay-ui:icon
					image="portlet"
					message="manage-portlets"
					url="<%= managePortletsURL %>"
				/>
			</c:if>

			<portlet:actionURL name="updateServiceDescription" var="updateServiceDescriptionURL">
				<portlet:param name="redirect" value="<%= currentURL %>" />
				<portlet:param name="wsrpConsumerId" value="<%= String.valueOf(wsrpConsumer.getWsrpConsumerId()) %>" />
			</portlet:actionURL>

			<liferay-ui:icon
				image="portlet"
				message="update-service-description"
				url="<%= updateServiceDescriptionURL %>"
			/>

			<portlet:actionURL name="restartConsumer" var="restartConsumerURL">
				<portlet:param name="redirect" value="<%= currentURL %>" />
				<portlet:param name="wsrpConsumerId" value="<%= String.valueOf(wsrpConsumer.getWsrpConsumerId()) %>" />
			</portlet:actionURL>

			<liferay-ui:icon
				image="portlet"
				message="restart-consumer"
				url="<%= restartConsumerURL %>"
			/>

			<portlet:actionURL name="deleteWSRPConsumer" var="deleteURL">
				<portlet:param name="redirect" value="<%= currentURL %>" />
				<portlet:param name="wsrpConsumerId" value="<%= String.valueOf(wsrpConsumer.getWsrpConsumerId()) %>" />
			</portlet:actionURL>

			<liferay-ui:icon-delete url="<%= deleteURL %>" />
		</liferay-ui:icon-menu>
	</c:otherwise>
</c:choose>