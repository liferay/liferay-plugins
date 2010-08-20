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
List<Account> accounts = mailManager.getAccounts();
%>

<c:choose>
	<c:when test="<%= mailManager != null %>">
		<div class="v-main">
			<div class="v-top">
				<div class="v-unread">
					<div class="v-unread-button">
						<portlet:renderURL windowState="<%= WindowState.MAXIMIZED.toString() %>" var="viewUnreadURL">
							<portlet:param name="mail_mode" value="unread" />
						</portlet:renderURL>

						<c:if test="<%= !mailManager.getAccounts().isEmpty() %>">
							<a class="v-unread-button-caption" href="<%= viewUnreadURL %>">
								<%
									int totalUnreadMessages = 0;
									boolean allAccountsUnavailable = true;
									for(Account a: accounts){
										if(a.isSavePassword()){
											allAccountsUnavailable = false;
											totalUnreadMessages += mailManager.getFolderUnreadMessagesCount(a.getInboxFolderId());
										}
									}
								%>

								<c:if test="<%= allAccountsUnavailable %>">
									<liferay-ui:message key="vaadin-mail" />
								</c:if>

								<c:if test="<%= totalUnreadMessages == 0 && !allAccountsUnavailable %>">
									<liferay-ui:message key="no-unread-messages" />
								</c:if>

								<c:if test="<%= totalUnreadMessages > 0 && !allAccountsUnavailable %>">
									<liferay-ui:message key="x-unread-messages" arguments="<%= new Object[]{totalUnreadMessages}%>" />
								</c:if>
							</a>
						</c:if>

						<c:if test="<%= mailManager.getAccounts().isEmpty() %>">
							<span class="v-unread-button-caption">
								<liferay-ui:message key="no-accounts" />
							</span>
						</c:if>

					</div>
				</div>

				<div class="v-compose-button">
					<c:if test="<%= !mailManager.getAccounts().isEmpty() %>">
						<portlet:renderURL windowState="<%= WindowState.MAXIMIZED.toString() %>" var="composeMailURL">
							<portlet:param name="mail_mode" value="compose" />
						</portlet:renderURL>
						<a class="v-compose-button-caption" href="<%= composeMailURL %>">
							<liferay-ui:message key="compose-message" />
						</a>
					</c:if>

					<c:if test="<%= mailManager.getAccounts().isEmpty() %>">
						<a class="v-compose-button-caption" href="<%= portletDisplay.getURLEdit() %>">
							<liferay-ui:message key="add-mail-account" />
						</a>
					</c:if>
				</div>

				<c:if test="<%= !mailManager.getAccounts().isEmpty() %>">
					<a class="v-icon" href="<%= portletDisplay.getURLEdit() %>"></a>

					<portlet:renderURL windowState="<%= WindowState.NORMAL.toString() %>" var="refreshURL">
							<portlet:param name="mail_mode" value="summary" />
					</portlet:renderURL>
					<a class="v-icon v-icon-reload" href="<%= refreshURL %>"></a>
				</c:if>
			</div>


			<c:if test="<%= mailManager != null %>">
				<portlet:renderURL windowState="<%= WindowState.MAXIMIZED.toString() %>" var="viewAccountInitialURL">
					<portlet:param name="mail_mode" value="account" />
					<portlet:param name="initialAccountEntryId" value="" />
				</portlet:renderURL>

				<c:if test="<%= !accounts.isEmpty() %>">
					<%
					for (Account messageAccount : accounts) {
					%>
						<div class="v-account">
							<a class="v-account-caption" href="<%= viewAccountInitialURL %><%= messageAccount.getAccountId() %>">
								<%
									long unreadMessages = mailManager.getFolderUnreadMessagesCount(messageAccount.getInboxFolderId());
								%>
								<c:if test="<%= unreadMessages==0L%>">
									<%= messageAccount.getAddress() %>
									<c:if test="<%= messageAccount.isSavePassword() %>">
										(<liferay-ui:message key="no-new-messages" />)
									</c:if>
								</c:if>

								<c:if test="<%= unreadMessages > 0L %>">
									<%= messageAccount.getAddress() %>
									<c:if test="<%= messageAccount.isSavePassword() %>">
										(<liferay-ui:message key="x-new-messages" arguments="<%= new Object[]{unreadMessages} %>" />)
									</c:if>
								</c:if>
							</a>
						</div>
					<%
					}
					%>
				</c:if>
			</c:if>
		</div>
		<div style="clear:both"></div>
	</c:when>
	<c:otherwise>
		<liferay-ui:message key="please-log-in-to-use-the-mail-portlet" />
	</c:otherwise>
</c:choose>