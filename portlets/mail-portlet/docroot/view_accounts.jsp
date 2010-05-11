<%
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
%>

<%@ include file="/init.jsp" %>

<%
MailManager mailManager = MailManager.getInstance(request);
%>

<c:if test="<%= mailManager != null %>">

	<%
	List<Account> accounts = mailManager.getAccounts();

	String tabNames = "";

	for (int i = 0; i < accounts.size(); i++) {
		Account account = accounts.get(i);

		String accountLabel = account.getAddress() + " (" + mailManager.getAccountUnreadMessagesCount(account.getAccountId()) + ")";

		tabNames += accountLabel;

		if (i != (accounts.size() - 1)) {
			tabNames += ",";
		}
	}
	%>

	<liferay-ui:tabs onClick="javascript:;"
		names="<%= tabNames %>"  />

	<aui:layout>
		<aui:column columnWidth="80">

		<%
		for (Account account : accounts) {
			String accountLabel = account.getAddress() + " (" + mailManager.getAccountUnreadMessagesCount(account.getAccountId()) + ")";
		%>

			<aui:a accountId="<%= account.getAccountId() %>" cssClass="folders-link" href="javascript:;" inboxFolderId="<%= account.getInboxFolderId() %>" label="<%= accountLabel %>" />

		<%
		}
		%>

		</aui:column>

		<aui:column columnWidth="20">
			<aui:button onClick="Liferay.Mail.addAccount();" value="add-mail-account" />
		</aui:column>
	</aui:layout>
</c:if>