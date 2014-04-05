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

<%
MailManager mailManager = MailManager.getInstance(request);
%>

<c:choose>
	<c:when test="<%= mailManager != null %>">
		<div id="accountsContainer"></div>

		<div class="mail-status"></div>

		<div class="row-fluid" id="mailContainer">
			<div class="hide span3" id="controlContainer">
				<div id="foldersContainer"></div>
			</div>

			<div class="span9" id="contentContainer">
				<div class="hide" id="manageFoldersContainer"></div>

				<div id="messagesContainer"></div>

				<div class="hide" id="messageContainer"></div>

				<div class="hide" id="composeContainer"></div>
			</div>
		</div>

		<%
		Account initialAccount = mailManager.getInitialAccount();

		long initialAccountId = 0;
		long initialFolderId = 0;

		if (initialAccount != null) {
			initialAccountId = initialAccount.getAccountId();
			initialFolderId = initialAccount.getInboxFolderId();
		}
		%>

		<aui:script use="liferay-plugin-mail">
			Liferay.Mail.init(
				{
					initialAccountId: <%= initialAccountId %>,
					initialFolderId: <%= initialFolderId %>,
					namespace: '<portlet:namespace />'
				}
			);
		</aui:script>
	</c:when>
	<c:otherwise>
		<liferay-ui:message key="please-log-in-to-use-the-mail-portlet" />
	</c:otherwise>
</c:choose>