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
private static String _assertTrue(boolean value) {
	if (value) {
		return "PASSED";
	}
	else {
		return "FAILED";
	}
}
%>