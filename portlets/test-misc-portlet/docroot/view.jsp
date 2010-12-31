<%--
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
--%>

<%@ include file="/init.jsp" %>

This is the <strong>Sample Test Portlet</strong>. This was made to test some portlet features.

<br /><br />

<strong>Portlet Request:</strong> <a href="<portlet:renderURL><portlet:param name="jspPage" value="/portlet_request/attribute_sharing.jsp" /></portlet:renderURL>">Attribute Sharing</a> | <a href="<portlet:renderURL><portlet:param name="jspPage" value="/portlet_request/remote_user.jsp" /></portlet:renderURL>">Remote User</a>

<br /><br />

<strong>Portlet Response:</strong> <a href="<portlet:renderURL><portlet:param name="jspPage" value="/portlet_response/buffer_size.jsp" /></portlet:renderURL>">Buffer Size</a> | <a href="<portlet:resourceURL id="logo.png" />">Download File</a>

<br /><br />

<strong>Portlet Session:</strong> <a href="<portlet:renderURL><portlet:param name="jspPage" value="/portlet_session/attribute_sharing.jsp" /></portlet:renderURL>">Attribute Sharing</a>

<br /><br />

<strong>Servlet Request:</strong> <a href="<%= request.getContextPath() %>/servlet_request/remote_user.jsp">Remote User</a>

<br /><br />

<strong>Upload:</strong> <a href="<portlet:renderURL><portlet:param name="<%= ActionRequest.ACTION_NAME %>" value="uploadForm1" /><portlet:param name="jspPage" value="/upload/form.jsp" /></portlet:renderURL>">Form 1</a> | <a href="<portlet:renderURL><portlet:param name="<%= ActionRequest.ACTION_NAME %>" value="uploadForm2" /><portlet:param name="jspPage" value="/upload/form.jsp" /></portlet:renderURL>">Form 2</a> | <a href="<portlet:renderURL><portlet:param name="<%= ActionRequest.ACTION_NAME %>" value="uploadForm3" /><portlet:param name="jspPage" value="/upload/form.jsp" /></portlet:renderURL>">Form 3</a>