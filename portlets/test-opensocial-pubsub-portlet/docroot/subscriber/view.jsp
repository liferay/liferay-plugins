<%--
/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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

<div>
	<input id="<portlet:namespace />subscribe" type="button" value="Subscribe" />

	<input id="<portlet:namespace />unsubscribe" type="button" value="Unsubscribe" />
</div>

<div id="<portlet:namespace />message"></div>

<aui:script use="aui-base">
	var topic = null;

	function <portlet:namespace />callback(topic, data, subscriberData) {
		A.one('#<portlet:namespace />message').set('innerHTML', 'Message: ' + data + '<br />Received: ' + new Date().toString());
	}

	A.one('#<portlet:namespace />subscribe').on(
		'click',
		function () {
			topic = Liferay.on('gadget:org.apache.shindig.random-number', <portlet:namespace />callback);
		}
	);

	A.one('#<portlet:namespace />unsubscribe').on(
		'click',
		function () {
			Liferay.detach(topic);

			A.one('#<portlet:namespace />message').set('innerHTML', '');
		}
	);
</aui:script>