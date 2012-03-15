<%--
/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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

<p>
	<portlet:renderURL var="actionURL">
		<portlet:param name="mvcPath" value="/plt-26-6/action_url.jsp" />
		<portlet:param name="testRenderParamName" value="testRenderParamValue" />
	</portlet:renderURL>

	<a href="<%= actionURL %>">Action URL</a>
</p>

<p>
	<portlet:renderURL var="renderURL">
		<portlet:param name="mvcPath" value="/plt-26-6/render_url.jsp" />
		<portlet:param name="testRenderParamName" value="testRenderParamValue" />
	</portlet:renderURL>

	<a href="<%= renderURL %>">Render URL</a>
</p>

<p>
	<portlet:renderURL var="resourceURL">
		<portlet:param name="mvcPath" value="/plt-26-6/resource_url.jsp" />
		<portlet:param name="testRenderParamName" value="testRenderParamValue" />
	</portlet:renderURL>

	<a href="<%= resourceURL %>">Resource URL</a>
</p>