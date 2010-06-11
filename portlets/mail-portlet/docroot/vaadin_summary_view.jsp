<%
/**
 * Copyright (c) 2000-2010 Liferay, Inc. and IT Mill, Ltd All rights reserved.
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
%>

<!-- Adapted from view_accounts_summary.jsp -->

<%@ page import="javax.portlet.WindowState" %>

<%@ include file="/init.jsp" %>

<%
MailManager mailManager = MailManager.getInstance(request);
%>

<c:choose>
	<c:when test="<%= mailManager != null %>">

		<div class="v-main-view">
			<div class="v-compose-link">
				<portlet:renderURL windowState="<%= WindowState.MAXIMIZED.toString() %>" var="composeMailURL">
					<portlet:param name="mail_mode" value="compose" />
				</portlet:renderURL>
				<a href="<%= composeMailURL %>">
					<liferay-ui:message key="compose-message" />
				</a>
			</div>

			<!-- Unread messages URL -->
			<div class="v-unread-link">
				<portlet:renderURL windowState="<%= WindowState.MAXIMIZED.toString() %>" var="viewUnreadURL">
					<portlet:param name="mail_mode" value="unread" />
				</portlet:renderURL>
				<a href="<%= viewUnreadURL %>">
					<liferay-ui:message key="unread-messages" />
					<!-- TODO Total unread messages count -->
				</a>
			</div>

			<portlet:renderURL windowState="<%= WindowState.MAXIMIZED.toString() %>" var="viewAccountInitialURL">
				<portlet:param name="mail_mode" value="account" />
				<portlet:param name="initialAccountEntryId" value="" />
			</portlet:renderURL>

			<div class="v-message-summary">
				<c:if test="<%= mailManager != null %>">
					<%
					List<Account> accounts = mailManager.getAccounts();
					%>

					<a href="<%= portletDisplay.getURLEdit() %>"><liferay-ui:message key="configure-email-accounts" /></a>

					<c:if test="<%= !accounts.isEmpty() %>">
						<liferay-ui:message key="mail-messages" />

						<%
						for (Account messageAccount : accounts) {
						%>

							<div class="v-message-summary-listing">
								<a href="<%= viewAccountInitialURL %><%= messageAccount.getAccountId() %>">
									<%= messageAccount.getAddress() %> (<%= mailManager.getAccountUnreadMessagesCount(messageAccount.getAccountId()) %> <liferay-ui:message key="new-messages" />)
								</a>
							</div>

							<!-- TODO explicit synchronization: mailManager.synchronizeAccount(messageAccount.getAccountId()); -->
						<%
						}
						%>
					</c:if>
				</c:if>
			</div>
		</div>
	</c:when>
	<c:otherwise>
		<liferay-ui:message key="please-log-in-to-use-the-mail-portlet" />
	</c:otherwise>
</c:choose>