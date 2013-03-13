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

<h3>Portlet Request</h3>

<p>
	<a href="<portlet:renderURL><portlet:param name="mvcPath" value="/portlet_request/attribute_sharing.jsp" /></portlet:renderURL>">Attribute Sharing</a><br />
	<a href="<portlet:renderURL><portlet:param name="mvcPath" value="/portlet_request/remote_user.jsp" /></portlet:renderURL>">Remote User</a>
</p>

<h3>Portlet Response (ActionResponse, Normal State)</h3>

<p>
	<a href="<portlet:actionURL />">Download File</a>
</p>

<h3>Portlet Response (ActionResponse, Exclusive State)</h3>

<p>
	<a href="<portlet:actionURL windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>" />">Download File</a>
</p>

<h3>Portlet Response (ResourceResponse)</h3>

<p>
	<a href="<portlet:renderURL><portlet:param name="mvcPath" value="/portlet_response/buffer_size.jsp" /></portlet:renderURL>">Buffer Size</a><br />
	<a href="<portlet:resourceURL id="logo.png" />">Download File</a>
</p>

<h3>Portlet Session</h3>

<p>
	<a href="<portlet:renderURL><portlet:param name="mvcPath" value="/portlet_session/attribute_sharing.jsp" /></portlet:renderURL>">Attribute Sharing</a>
</p>

<h3>Portlet Title (Dynamic)</h3>

<p>

	<%
	String title = ParamUtil.getString(renderRequest, "title", _PORTLET_TITLE_DEFAULT);
	%>

	portletDisplay.getTitle=<%= _assertEquals(portletDisplay.getTitle(), title) %><br /><br />

	<a href="<portlet:renderURL><portlet:param name="title" value="New Title" /></portlet:renderURL>">Change</a><br />
	<a href="<portlet:renderURL />">Restore</a>
</p>

<h3>Portlet Title (Static)</h3>

<p>
	PortalUtil.getPortletTitle=<%= _assertEquals(_getPortletTitle(request, application), _PORTLET_TITLE_DEFAULT) %><br />
	PortalUtil.getPortletDescription=<%= _assertEquals(_getPortletDescription(request, application), "Test Misc Localized Description") %><br />
</p>

<h3>Scheduler</h3>

<p>
	TestPortletConfigMessageListener.isReceived=<%= _assertTrue(TestPortletConfigMessageListener.isReceived()) %>
	TestSpringConfigMessageListener.isReceived=<%= _assertTrue(TestSpringConfigMessageListener.isReceived()) %><br />
</p>

<h3>Servlet Request</h3>

<p>
	<a href="<%= request.getContextPath() %>/servlet_request/remote_user.jsp">Remote User</a>
</p>

<h3>Upload</h3>

<p>
	<a href="<portlet:renderURL><portlet:param name="<%= ActionRequest.ACTION_NAME %>" value="uploadForm1" /><portlet:param name="mvcPath" value="/upload/form.jsp" /></portlet:renderURL>">Form 1</a><br />
	<a href="<portlet:renderURL><portlet:param name="<%= ActionRequest.ACTION_NAME %>" value="uploadForm2" /><portlet:param name="mvcPath" value="/upload/form.jsp" /></portlet:renderURL>">Form 2</a><br />
	<a href="<portlet:renderURL><portlet:param name="<%= ActionRequest.ACTION_NAME %>" value="uploadForm3" /><portlet:param name="mvcPath" value="/upload/form.jsp" /></portlet:renderURL>">Form 3</a>
</p>

<%!
private static String _assertEquals(Object expected, Object actual) {
	return _assertTrue(Validator.equals(expected, actual));
}

private static String _assertTrue(boolean value) {
	if (value) {
		return "PASSED";
	}
	else {
		return "FAILED";
	}
}

private static String _getPortletDescription(
	HttpServletRequest request, ServletContext servletContext) {

	Portlet portlet = (Portlet)request.getAttribute(WebKeys.RENDER_PORTLET);

	return PortalUtil.getPortletDescription(portlet, servletContext, Locale.US);
}

private static String _getPortletTitle(
	HttpServletRequest request, ServletContext servletContext) {

	Portlet portlet = (Portlet)request.getAttribute(WebKeys.RENDER_PORTLET);

	return PortalUtil.getPortletTitle(portlet, servletContext, Locale.US);
}

private static final String _PORTLET_TITLE_DEFAULT = "Test Misc Localized Title";
%>