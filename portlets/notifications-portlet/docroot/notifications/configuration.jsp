<%--
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
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

<div class="manage-notifications">
	<div class="title">
		<div class="receive-notification">
			<c:choose>
				<c:when test="<%= UserNotificationDeliveryLocalServiceUtil.getUserNotificationDeliveriesCount() > 0 %>">
					<span><liferay-ui:message key="receive-a-notification-when-someone" /></span>
				</c:when>
				<c:otherwise>
					<span><liferay-ui:message key="there-are-no-available-options-to-configure" /></span>
				</c:otherwise>
			</c:choose>
		</div>
	</div>

	<%
	Map<String, List<UserNotificationDefinition>> userNotificationDefinitionsMap = new TreeMap<String, List<UserNotificationDefinition>>(new PortletIdComparator(locale));

	userNotificationDefinitionsMap.putAll(UserNotificationManagerUtil.getUserNotificationDefinitions());

	for (Map.Entry<String, List<UserNotificationDefinition>> entry : userNotificationDefinitionsMap.entrySet()) {
	%>

		<table class="notification-deliveries table table-condensed">
			<caption><%= PortalUtil.getPortletTitle(entry.getKey(), locale) %></caption>
			<tbody>

			<%
			List<UserNotificationDefinition> userNotificationDefinitions = entry.getValue();

			for (UserNotificationDefinition userNotificationDefinition : userNotificationDefinitions) {
			%>

				<tr>
					<td class="span8">
						<liferay-ui:message key="<%= userNotificationDefinition.getDescription() %>" />
					</td>

					<%
					Map<Integer, UserNotificationDeliveryType> userNotificationDeliveryTypesMap = userNotificationDefinition.getUserNotificationDeliveryTypes();

					for (Map.Entry<Integer, UserNotificationDeliveryType> userNotificationDeliveryTypeEntry : userNotificationDeliveryTypesMap.entrySet()) {
						UserNotificationDeliveryType userNotificationDeliveryType = userNotificationDeliveryTypeEntry.getValue();

						UserNotificationDelivery userNotificationDelivery = UserNotificationDeliveryLocalServiceUtil.getUserNotificationDelivery(themeDisplay.getUserId(), entry.getKey(), userNotificationDefinition.getClassNameId(), userNotificationDefinition.getNotificationType(), userNotificationDeliveryType.getType(), userNotificationDeliveryType.isDefault());
					%>

						<td class="span1">
							<aui:input cssClass="notification-delivery" data-userNotificationDeliveryId="<%= String.valueOf(userNotificationDelivery.getUserNotificationDeliveryId()) %>" disabled="<%= !userNotificationDeliveryType.isModifiable() %>" inlineLabel="true" label="<%= userNotificationDeliveryType.getName() %>" name="<%= String.valueOf(userNotificationDelivery.getUserNotificationDeliveryId()) %>" type="checkbox" value="<%= userNotificationDelivery.isDeliver() %>" />
						</td>

					<%
					}
					%>

				</tr>

			<%
			}
			%>

			</tbody>
		</table>

	<%
	}
	%>

</div>

<aui:script use="aui-base,aui-io-request">
	var userNotifications = A.one('#portlet_<%= PortletKeys.NOTIFICATIONS %>');

	var notificationDelivery = userNotifications.one('.manage-notifications');

	if (notificationDelivery) {
		notificationDelivery.delegate(
			'change',
			function(event) {
				event.preventDefault();

				var currentTarget = event.currentTarget;

				A.io.request(
					'<portlet:actionURL name="updateUserNotificationDelivery" />',
					{
						data: {
							<portlet:namespace />deliver: currentTarget.attr('checked'),
							<portlet:namespace />userNotificationDeliveryId: currentTarget.attr('data-userNotificationDeliveryId')
						}
					}
				);
			},
			'.notification-deliveries .notification-delivery'
		);
	}
</aui:script>