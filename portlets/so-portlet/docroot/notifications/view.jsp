<%--
/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
 *
 * This file is part of Liferay Social Office. Liferay Social Office is free
 * software: you can redistribute it and/or modify it under the terms of the GNU
 * Affero General Public License as published by the Free Software Foundation,
 * either version 3 of the License, or (at your option) any later version.
 *
 * Liferay Social Office is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License
 * for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * Liferay Social Office. If not, see http://www.gnu.org/licenses/agpl-3.0.html.
 */
--%>

<%@ include file="/init.jsp" %>

<liferay-portlet:actionURL name="deleteUserNotificationEvents" portletName="6_WAR_soportlet" var="deleteURL" windowState="<%= LiferayWindowState.NORMAL.toString() %>">
	<portlet:param name="redirect" value="<%= currentURL %>" />
</liferay-portlet:actionURL>

<aui:form action="<%= deleteURL %>" method="get" name="fm" onSubmit='<%= "event.preventDefault(); " + renderResponse.getNamespace() + "deleteNotifications();" %>'>
	<aui:input name="userNotificationEventUuids" type="hidden" />

	<aui:fieldset>
		<div class="view-all-user-notifications">
			<div class="user-notification-events">

				<liferay-portlet:renderURL varImpl="iteratorURL" />

				<liferay-ui:search-container
					deltaConfigurable="<%= true %>"
					emptyResultsMessage="you-have-no-notification"
					iteratorURL="<%= iteratorURL %>"
					rowChecker="<%= new RowChecker(renderResponse) %>"
				>

					<%
					List<UserNotificationEvent> userNotificationEvents = UserNotificationEventLocalServiceUtil.getUserNotificationEvents(themeDisplay.getUserId(), true, searchContainer.getStart(), searchContainer.getEnd());
					int notificationCount = userNotificationEvents.size();
					%>

					<liferay-ui:search-container-results
						results="<%= userNotificationEvents %>"
						total="<%= notificationCount %>"
					/>

					<liferay-ui:search-container-row
						className="com.liferay.portal.model.UserNotificationEvent"
						escapedModel="<%= false %>"
						keyProperty="uuid"
						modelVar="notificationEvent"
					>

						<%
						JSONObject notificationEventJSON = JSONFactoryUtil.createJSONObject(notificationEvent.getPayload());

						long senderUserId = notificationEventJSON.getLong("senderUserId", 0);

						String userDisplayURL = StringPool.BLANK;
						String userFullName = PortalUtil.getUserName(senderUserId, StringPool.BLANK);
						String userPortaitURL = StringPool.BLANK;

						try {
							User curUser = UserLocalServiceUtil.getUserById(senderUserId);

							userDisplayURL = curUser.getDisplayURL(themeDisplay);
							userPortaitURL = curUser.getPortraitURL(themeDisplay);
						}
						catch (NoSuchUserException nsue) {
						}
						%>

						<liferay-ui:search-container-column-text name="notifications" valign="top">
							<c:choose>
								<c:when test='<%= notificationEventJSON.getString("portletId", StringPool.BLANK).equals("1_WAR_privatemessagingportlet") %>'>
									<%@ include file="/notifications/view_private_message.jspf" %>
								</c:when>
								<c:when test='<%= notificationEventJSON.getString("portletId", StringPool.BLANK).equals("2_WAR_soportlet") %>'>
									<%@ include file="/notifications/view_member_request.jspf" %>
								</c:when>
								<c:when test='<%= notificationEventJSON.getString("portletId", StringPool.BLANK).equals("1_WAR_contactsportlet") %>'>
									<%@ include file="/notifications/view_social_request.jspf" %>
								</c:when>
								<c:when test='<%= notificationEventJSON.getString("portletId", StringPool.BLANK).equals("1_WAR_tasksportlet") %>'>
									<%@ include file="/notifications/view_task.jspf" %>
								</c:when>
								<c:when test='<%= notificationEventJSON.getString("portletId", StringPool.BLANK).equals("1_WAR_microblogsportlet") %>'>
									<%@ include file="/notifications/view_microblogs.jspf" %>
								</c:when>
							</c:choose>
						</liferay-ui:search-container-column-text>
					</liferay-ui:search-container-row>

					<liferay-ui:search-iterator />

					<c:if test="<%= !results.isEmpty() %>">
						<aui:button-row cssName="delete-button-row">
							<aui:button type="submit" value="delete" />
						</aui:button-row>
					</c:if>
				</liferay-ui:search-container>
			</div>
		</div>
	</aui:fieldset>
</aui:form>

<aui:script use="aui-base">
	var userNotificationEvents = A.one('.view-all-user-notifications .user-notification-events');

	userNotificationEvents.delegate(
		'click',
		function(event) {
			var portletURL = event.currentTarget.getAttribute('data-portletUrl');

			if (portletURL) {
				window.location = portletURL;
			}
		},
		'.user-notification-event-content'
	);

	Liferay.provide(
		window,
		'<portlet:namespace />deleteNotifications',
		function() {
			document.<portlet:namespace />fm.method = "post";
			document.<portlet:namespace />fm.<portlet:namespace />userNotificationEventUuids.value = Liferay.Util.listCheckedExcept(document.<portlet:namespace />fm, "<portlet:namespace />allRowIds");

			submitForm(document.<portlet:namespace />fm);
		},
		['liferay-util-list-fields']
	);
</aui:script>