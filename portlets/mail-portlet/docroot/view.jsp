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
MailManager mailManager = MailManager.getInstance(request);
%>

<c:choose>
	<c:when test="<%= mailManager != null %>">
		<div id="accountsContainer"></div>

		<aui:layout cssClass="mail-status">
		</aui:layout>

		<div id="mailContainer">
			<aui:layout>
				<aui:column columnWidth="20">
					<div class="aui-helper-hidden" id="controlContainer">
						<div id="foldersContainer"></div>
					</div>
				</aui:column>

				<aui:column columnWidth="80">
					<div id="contentContainer">
						<div class="aui-helper-hidden" id="manageFoldersContainer"></div>

						<div id="messagesContainer"></div>

						<div class="aui-helper-hidden" id="messageContainer"></div>

						<div class="aui-helper-hidden" id="composeContainer"></div>
					</div>
				</aui:column>
			</aui:layout>
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