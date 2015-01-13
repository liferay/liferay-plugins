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

<liferay-ui:tabs
	names="test"
	refresh="<%= false %>"
>
	<liferay-ui:section>
		<aui:form name="fm">
			<aui:input label="message" name="message" rows="6" type="textarea" />

			<aui:button type="submit" value="send" />

			<aui:button type="reset" value="reset" />
		</aui:form>

		<br />

		<div class="alert alert-success hide" id="<portlet:namespace />success">
			<p><liferay-ui:message key="the-push-notification-was-sent-successfully" /></p>
		</div>

		<div class="alert alert-danger hide" id="<portlet:namespace />error">
			<p></p>
		</div>
	</liferay-ui:section>
</liferay-ui:tabs>

<aui:script use="aui-base">
	var form = A.one('#<portlet:namespace />fm');

	form.on(
		'submit',
		function(event) {
			event.halt();

			var message = form.one('textarea[name="<portlet:namespace />message"]').val();

			Liferay.Service(
				'/push-notifications-portlet.pushnotificationsdevice/send-push-notification',
				{
					toUserId: 0,
					payload: A.JSON.stringify(
						{
							message: message
						}
					)
				},
				<portlet:namespace />onSendPushNotification
			);
		}
	);

	function <portlet:namespace />onSendPushNotification(result) {
		var success = A.one('#<portlet:namespace />success');

		success.hide();

		var error = A.one('#<portlet:namespace />error');

		error.hide();

		if (A.Object.hasKey(result, 'exception')) {
			error.one('p').text(result);

			error.show();
		}
		else {
			success.show();
		}
	}
</aui:script>