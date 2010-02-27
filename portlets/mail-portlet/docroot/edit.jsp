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

<script type="text/javascript">
	AUI().ready(
		function() {
			Liferay.MailConfiguration.init(
				{
					preconfiguredMailAccounts : <%= PortletProps.get("preconfigured.mail.accounts") %>
				}
			);
		}
	);
</script>

<div class="mail-portlet">
	<div class="accounts-configuration">
		<div class="add-account-container">
			<liferay-ui:message key="add-a-new-email-account" />

			<br /><br />

			<div class="preconfigured-mail-accounts"></div>
		</div>

		<br />

		<div class="current-accounts-container">
			<liferay-ui:message key="your-email-accounts" />

			<br /><br />

			<div class="current-mail-accounts"></div>
		</div>
	</div>
</div>