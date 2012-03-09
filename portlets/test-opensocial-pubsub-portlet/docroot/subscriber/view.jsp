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

<h3>Subscribe</h3>

<div>
	<aui:select label="" name="subscribeTopic">
		<aui:option label="String" />
		<aui:option label="Array" />
	</aui:select>

	<aui:button name="subscribe" value="Subscribe" />
</div>

<h3>Unsubscribe</h3>

<div>
	<aui:select label="" name="unsubscribeTopic">
		<aui:option label="String with Callback Function" value="string_callback_fn" />
		<aui:option label="String without Callback Function" value="string" />
		<aui:option label="Topic Handle" value="handle" />
		<aui:option label="Handle.detach();" value="handle_detach" />
	</aui:select>

	<aui:button name="unsubscribe" value="Unsubscribe" />
</div>

<div id="<portlet:namespace />message"></div>

<aui:script use="aui-base">
	var handle = null;

	function callback(topic, data, subscriberData) {
		A.one('#<portlet:namespace />message').set('innerHTML', 'Message: ' + data + '<br />Received: ' + new Date().toString());
	}

	A.one('#<portlet:namespace />subscribe').on(
		'click',
		function () {
			var subscribeTopic = 'gadget:org.apache.shindig.random-number';

			var topicType = A.one('#<portlet:namespace />subscribeTopic').val();

			if (topicType == 'Array') {
				subscribeTopic = [subscribeTopic];
			}

			handle = Liferay.on(subscribeTopic, callback);
		}
	);

	A.one('#<portlet:namespace />unsubscribe').on(
		'click',
		function () {
			var lfrDetach = false;

			var unsubscribeFn;
			var unsubscribeTopic;

			var topicType = A.one('#<portlet:namespace />unsubscribeTopic').val();

			if (topicType == 'string_callback_fn') {
				lfrDetach = true;

				unsubscribeFn = callback;
				unsubscribeTopic= 'gadget:org.apache.shindig.random-number';
			}
			else if (topicType == 'string') {
				lfrDetach = true;

				unsubscribeTopic= 'gadget:org.apache.shindig.random-number';
			}
			else if (topicType == 'handle') {
				lfrDetach = true;

				unsubscribeTopic = handle;
			}

			if (lfrDetach) {
				Liferay.detach(unsubscribeTopic, unsubscribeFn);
			}
			else {
				handle.detach();
			}

			A.one('#<portlet:namespace />message').empty();
		}
	);
</aui:script>