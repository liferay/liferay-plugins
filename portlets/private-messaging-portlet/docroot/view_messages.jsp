<%--
/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
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

<aui:layout cssClass="controls">
	<aui:column columnWidth="15" cssClass="col-1">
		<liferay-ui:message key="select" />:

		<span class="select-all"><a href="javascript:;"><liferay-ui:message key="all" /></a></span>

		<span class="select-none"><a href="javascript:;"><liferay-ui:message key="none" /></a></span>
	</aui:column>

	<aui:column columnWidth="35">
		<aui:button cssClass="mark-messages-as-unread" name="markAsUnread" value="mark-as-unread" />

		<aui:button cssClass="delete-messages" name="deleteMessage" value="delete" />
	</aui:column>

	<aui:column columnWidth="50" cssClass="col-2">
		<aui:button cssClass="new-message" name="newMessage" value="new-message" />
	</aui:column>
</aui:layout>

<liferay-ui:search-container delta="25" emptyResultsMessage="no-messages-found">
	<liferay-ui:search-container-results>

		<%
		results = UserThreadLocalServiceUtil.getUserUserThreads(themeDisplay.getUserId(), false, searchContainer.getStart(), searchContainer.getEnd());
		total = UserThreadLocalServiceUtil.getUserUserThreadCount(themeDisplay.getUserId(), false);

		pageContext.setAttribute("results", results);
		pageContext.setAttribute("total", total);
		%>

	</liferay-ui:search-container-results>

	<liferay-ui:search-container-row
		className="com.liferay.privatemessaging.model.UserThread"
		modelVar="userThread"
	>
		<liferay-ui:search-container-column-text align="center">
			<aui:input label="" name="mbThread" type="checkbox" data-mbThreadId="<%= userThread.getMbThreadId() %>" />
		</liferay-ui:search-container-column-text>

		<liferay-ui:search-container-column-text align="center">
			<aui:layout cssClass='<%= (!userThread.isRead()) ? "unread" : "" %>'>

				<%
				long userId = PrivateMessagingUtil.getThreadRepresentativeUserId(user.getUserId(), userThread.getMbThreadId());

				User curUser = UserLocalServiceUtil.getUser(userId);
				%>

				<liferay-ui:user-display
					userId="<%= curUser.getUserId() %>"
					userName="<%= curUser.getFullName() %>"
					displayStyle="<%= 2 %>"
				/>
			</aui:layout>
		</liferay-ui:search-container-column-text>

		<liferay-ui:search-container-column-text>
			<aui:layout cssClass='<%= (!userThread.isRead()) ? "unread" : "" %>'>

				<%
				List<User> users = PrivateMessagingUtil.getThreadUsers(user.getUserId(), userThread.getMbThreadId(), false);

				if (users.isEmpty()) {
					users.add(user);
				}

				for (int i = 0; i < users.size(); i++) {
					User curUser = users.get(i);
				%>

					<liferay-portlet:actionURL var="publicPagesURL" portletName="<%= PortletKeys.MY_PLACES %>">
						<portlet:param name="struts_action" value="/my_places/view" />
						<portlet:param name="groupId" value="<%= String.valueOf(curUser.getGroup().getGroupId()) %>" />
						<portlet:param name="privateLayout" value="<%= Boolean.FALSE.toString() %>" />
					</liferay-portlet:actionURL>

					<a class="profile-link" href="<%= publicPagesURL %>"><%= HtmlUtil.escape(curUser.getFullName()) %></a>

					<c:if test="<%= i != users.size() - 1 %>">
						,
					</c:if>

					<%
				}
				%>

			</aui:layout>

			<aui:layout cssClass='<%= (!userThread.isRead()) ? "unread" : "" %>'>

				<%
				MBMessage lastMBMessage = PrivateMessagingUtil.getLastThreadMessage(user.getUserId(), userThread.getMbThreadId());
				%>

				<%= dateFormatDateTime.format(lastMBMessage.getCreateDate()) %>

			</aui:layout>
		</liferay-ui:search-container-column-text>

		<liferay-ui:search-container-column-text>
			<aui:layout cssClass='<%= (!userThread.isRead()) ? "unread" : "" %>'>

				<%
				MBMessage lastMBMessage = PrivateMessagingUtil.getLastThreadMessage(user.getUserId(), userThread.getMbThreadId());

				PortletURL viewThreadURL = renderResponse.createRenderURL();

				viewThreadURL.setParameter("mbThreadId", String.valueOf(userThread.getMbThreadId()));
				%>

				<a href="<%= viewThreadURL.toString() %>">
					<aui:layout cssClass="subject">
						<%= HtmlUtil.escape(StringUtil.shorten(lastMBMessage.getSubject(), 50)) %>
					</aui:layout>

					<aui:layout cssClass="body">
						<%= HtmlUtil.escape(StringUtil.shorten(lastMBMessage.getBody(), 100)) %>
					</aui:layout>
				</a>
			</aui:layout>
		</liferay-ui:search-container-column-text>
	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator />
</liferay-ui:search-container>