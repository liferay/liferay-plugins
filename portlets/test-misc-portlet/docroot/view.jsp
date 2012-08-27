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
String defaultDescription = "Test Misc Localized Description";
String defaultTitle = "Test Misc Localized Title";
String title = ParamUtil.getString(renderRequest, "title", defaultTitle);
%>

<h3>Portlet Localized Title and Description</h3>

<p>
	TestLocalizationUtil.getLocalizedPortletTitle=<%= _assertEquals(TestLocalizationUtil.getLocalizedPortletTitle(request, application), defaultTitle) %><br />
	TestLocalizationUtil.getLocalizedPortletDescription=<%= _assertEquals(TestLocalizationUtil.getLocalizedPortletDescription(request, application), defaultDescription) %><br />
</p>

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

<h3>Portlet Response (Title)</h3>

<p>
	TestLocalizationUtil.getPortletDisplayTitle=<%= _assertEquals(TestLocalizationUtil.getPortletDisplayTitle(request), title) %><br /><br />
	<a href="<portlet:renderURL><portlet:param name="title" value="New Title" /></portlet:renderURL>">Change Portlet Title</a><br />
	<a href="<portlet:renderURL />">Restore Portlet Title</a>
</p>

<h3>Portlet Session</h3>

<p>
	<a href="<portlet:renderURL><portlet:param name="mvcPath" value="/portlet_session/attribute_sharing.jsp" /></portlet:renderURL>">Attribute Sharing</a>
</p>

<h3>Scheduler</h3>

<p>
	TestSchedulerMessageListener.isReceived=<%= _assertTrue(TestSchedulerMessageListener.isReceived()) %><br />
	TestSchedulerUtil.isReceivedBeforeSpringInitialzed=<%= _assertTrue(!TestSchedulerUtil.isReceivedBeforeSpringInitialzed()) %>
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
%>