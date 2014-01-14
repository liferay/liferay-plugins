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

<portlet:renderURL copyCurrentRenderParameters="<%= true %>" var="portletURL1" />

<portlet:renderURL copyCurrentRenderParameters="<%= true %>" var="portletURL2">
	<portlet:param name="name1" value="value1" />
	<portlet:param name="name2" value="value2" />
	<portlet:param name="name1" value="" />
</portlet:renderURL>

<portlet:renderURL copyCurrentRenderParameters="<%= true %>" var="portletURL3">
	<portlet:param name="testRenderParamName" value="" />
</portlet:renderURL>

<%@ include file="/portlet_url.jspf" %>