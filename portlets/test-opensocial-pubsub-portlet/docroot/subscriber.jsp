<%
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
%>

<%@ include file="/init.jsp" %>

<p>Subscriber portlet - test</p>

<div>
	<input id="<portlet:namespace />subscribe" type="button" value="Subscribe" />
	<input id="<portlet:namespace />unsubscribe" type="button" value="Unsubscribe" />
</div>

<div id="<portlet:namespace />message"></div>

<aui:script use="aui-base">
	var topic;

	function callback(topic, data, subscriberData) {
		A.one('#<portlet:namespace />message').set('innerHTML', 'liferay message: ' + data + '<br/> liferay received at: ' + new Date().toString());
	}

	A.one('#<portlet:namespace />subscribe').on(
		'click',
		function () {
			topic = Liferay.on('gadget:org.apache.shindig.random-number', callback);
		}
	);

	A.one('#<portlet:namespace />unsubscribe').on(
		'click',
		function () {
			Liferay.detach(topic, <portlet:namespace />callback);

			A.one('#<portlet:namespace />message').set('innerHTML', '');
		}
	);
</aui:script>
