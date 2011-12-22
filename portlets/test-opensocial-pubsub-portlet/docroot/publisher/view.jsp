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

<div>
	<input id="<portlet:namespace/>publish" type="button" value="Publish a Random Number" />
</div>

<div id="<portlet:namespace />message"></div>

<aui:script use="aui-base">
	A.one('#<portlet:namespace />publish').on(
		'click',
		function () {
			var message = Math.random();

			Liferay.fire('gadget:org.apache.shindig.random-number', message);

			A.one('#<portlet:namespace />message').set('innerHTML', message);
		}
	);
</aui:script>