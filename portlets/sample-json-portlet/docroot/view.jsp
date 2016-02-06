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

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>
<%@ taglib uri="http://liferay.com/tld/util" prefix="liferay-util" %>

<%@ page import="com.liferay.portal.kernel.util.PortalUtil" %>

<portlet:defineObjects />

<div id="<portlet:namespace />json"></div>

<liferay-util:html-bottom>
	<script src="<%= PortalUtil.getPathContext(request) %>/servlet/do?id=<portlet:namespace />json&callback=<portlet:namespace />printJSON" type="text/javascript"></script>
</liferay-util:html-bottom>

<aui:script position="inline">
	Liferay.provide(
		window,
		'<portlet:namespace />printJSON',
		function(obj) {
			var A = AUI();

			A.one('#<portlet:namespace />json').html(obj.toString());
		},
		['aui-base']
	);
</aui:script>