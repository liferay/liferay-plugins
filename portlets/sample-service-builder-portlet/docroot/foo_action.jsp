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
ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

Foo foo = null;

boolean view = false;

if (row != null) {
	foo = (Foo)row.getObject();
}
else {
	foo = (Foo)request.getAttribute("edit_foo.jsp-foo");

	view = true;
}
%>

<liferay-ui:icon-menu showExpanded="<%= view %>" showWhenSingleIcon="<%= view %>">
	<c:if test="<%= !view %>">
		<portlet:renderURL var="editURL">
			<portlet:param name="jspPage" value="/edit_foo.jsp" />
			<portlet:param name="redirect" value="<%= currentURL %>" />
			<portlet:param name="fooId" value="<%= String.valueOf(foo.getFooId()) %>" />
		</portlet:renderURL>

		<liferay-ui:icon
			image="edit"
			url="<%= editURL %>"
		/>
	</c:if>

	<portlet:renderURL var="redirectURL">
		<portlet:param name="jspPage" value="/view.jsp" />
	</portlet:renderURL>

	<portlet:actionURL var="deleteURL">
		<portlet:param name="<%= Constants.CMD %>" value="<%= Constants.DELETE %>" />
		<portlet:param name="redirect" value="<%= view ? redirectURL : currentURL %>" />
		<portlet:param name="fooId" value="<%= String.valueOf(foo.getFooId()) %>" />
	</portlet:actionURL>

	<liferay-ui:icon-delete url="<%= deleteURL %>" />
</liferay-ui:icon-menu>