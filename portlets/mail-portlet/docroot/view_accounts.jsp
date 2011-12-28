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
long accountId = ParamUtil.getLong(request, "accountId");

MailManager mailManager = MailManager.getInstance(request);
%>

<c:if test="<%= mailManager != null %>">
	<aui:layout>

		<%
		List<Account> mailAccounts = mailManager.getAccounts();
		%>

		<c:if test="<%= !mailAccounts.isEmpty() %>">
			<aui:column>

				<ul class="aui-tabview-list">

					<%
					for (Account mailAccount : mailAccounts) {
					%>

						<li class="aui-tab <%= (mailAccount.getAccountId() == accountId) ? "aui-tab-active" : "" %>">
							<span class="aui-tab-content">
								<span class="aui-tab-label">
									<aui:a cssClass="folders-link" data-accountId="<%= mailAccount.getAccountId() %>" data-inboxFolderId="<%= mailAccount.getInboxFolderId() %>" href="javascript:;" label="<%= mailAccount.getAddress() %>" />
								</span>
							</span>
						</li>

					<%
					}
					%>

				</ul>
			</aui:column>
		</c:if>

		<aui:column>
			<aui:button onClick="Liferay.Mail.addAccount();" value="add-mail-account" />
		</aui:column>
	</aui:layout>
</c:if>