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

<div class="manage-notifications-content">
	<div class="manage-notifications" id="<portlet:namespace />manageNotifications">
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
			Portlet portlet = PortletLocalServiceUtil.getPortletById(entry.getKey());
		%>

			<table class="notification-deliveries table table-condensed">
				<caption><%= PortalUtil.getPortletTitle(portlet, application, locale) %></caption>
				<tbody>

				<%
				List<UserNotificationDefinition> userNotificationDefinitions = entry.getValue();

				for (UserNotificationDefinition userNotificationDefinition : userNotificationDefinitions) {
				%>

					<tr>
						<td class="span8">
							<liferay-ui:message key="<%= userNotificationDefinition.getDescription(locale) %>" />
						</td>
						<td class="span1">

							<%
							Map<Integer, UserNotificationDeliveryType> userNotificationDeliveryTypesMap = userNotificationDefinition.getUserNotificationDeliveryTypes();

							for (Map.Entry<Integer, UserNotificationDeliveryType> userNotificationDeliveryTypeEntry : userNotificationDeliveryTypesMap.entrySet()) {
								UserNotificationDeliveryType userNotificationDeliveryType = userNotificationDeliveryTypeEntry.getValue();

								UserNotificationDelivery userNotificationDelivery = UserNotificationDeliveryLocalServiceUtil.getUserNotificationDelivery(themeDisplay.getUserId(), entry.getKey(), userNotificationDefinition.getClassNameId(), userNotificationDefinition.getNotificationType(), userNotificationDeliveryType.getType(), userNotificationDeliveryType.isDefault());
							%>

								<div class="checkbox-container">
									<aui:input cssClass="notification-delivery" data-userNotificationDeliveryId="<%= String.valueOf(userNotificationDelivery.getUserNotificationDeliveryId()) %>" disabled="<%= !userNotificationDeliveryType.isModifiable() %>" inlineLabel="true" label="<%= userNotificationDeliveryType.getName() %>" name="<%= String.valueOf(userNotificationDelivery.getUserNotificationDeliveryId()) %>" type="checkbox" value="<%= userNotificationDelivery.isDeliver() %>" />
								</div>

							<%
							}
							%>

						</td>
					</tr>

				<%
				}
				%>

				</tbody>
			</table>

		<%
		}
		%>

		<aui:script use="aui-base,aui-io-request">
			var notificationDelivery = A.one('#<portlet:namespace />manageNotifications');

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
								},
								dataType: 'json',
								on: {
									success: function() {
										var responseData = this.get('responseData');

										if (responseData.success) {
											var checkboxContainer = currentTarget.ancestor('.checkbox-container');

											var saved = checkboxContainer.one('.saved');

											if (saved) {
												saved.remove();
											}

											var input = checkboxContainer.one('input');

											checkboxContainer.insertBefore('<span class="saved" style="background: #0A85E4; color: #FFF;"><liferay-ui:message key="saved" /></span>', input);

											setInterval(
												function() {
													var saved = checkboxContainer.one('.saved');

													if (saved) {
														saved.setStyle('background', 'transparent');
														saved.setStyle('color', 'transparent');
													}

													setInterval(
														function() {
															if (saved) {
																saved.remove();
															}
														},
														3000
													);
												},
												500
											);
										}
									}
								}
							}
						);
					},
					'.notification-deliveries .notification-delivery'
				);
			}
		</aui:script>
	</div>
</div>