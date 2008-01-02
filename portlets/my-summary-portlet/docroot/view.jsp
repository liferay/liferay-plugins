<%
/**
 * Copyright (c) 2000-2008 Liferay, Inc. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
%>

<%@ taglib uri="http://java.sun.com/portlet" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>
<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>

<%@ page import="com.liferay.portal.kernel.servlet.ImageServletTokenUtil" %>
<%@ page import="com.liferay.portal.model.Group" %>
<%@ page import="com.liferay.portal.model.User" %>
<%@ page import="com.liferay.portal.service.GroupLocalServiceUtil" %>
<%@ page import="com.liferay.portal.service.UserLocalServiceUtil" %>

<portlet:defineObjects />

<liferay-theme:defineObjects />

<%
Group group = GroupLocalServiceUtil.getGroup(themeDisplay.getPortletGroupId());

User user2 = user;

if (group.isUser()) {
	user2 = UserLocalServiceUtil.getUserById(group.getClassPK());
}
%>

<style type="text/css">
	.ie .<portlet:namespace />container {
		height: 1%;
	}

	.<portlet:namespace />container:after {
		clear: both;
		content: ".";
		display: block;
		height: 0;
		visibility: hidden;
	}

	.<portlet:namespace />container h2 {
		color: #83B4E1;
		font-size: 16px;
		margin-bottom: 10px;
	}

	.<portlet:namespace />container img {
		margin: 5px;
		float: right;
	}

	.<portlet:namespace />container p {
		margin-bottom: 10px;
	}

	.<portlet:namespace />container span {
		color: #3D536C;
		font-size: 10px;
		text-transform: uppercase;
	}
</style>

<div class="<portlet:namespace />container">
	<img src="<%= themeDisplay.getPathImage() %>/user_portrait?img_id=<%= user2.getPortraitId() %>&t=<%= ImageServletTokenUtil.getToken(user2.getPortraitId()) %>" />

	<h2>
		<%= user2.getFullName() %>
	</h2>

	<p>
		<span><liferay-ui:message key="job-title" /></span><br />

		<%= user2.getContact().getJobTitle() %>
	</p>

	<p>
		<span><liferay-ui:message key="about-me" /></span><br />

		<%= user2.getComments() %>
	</p>
</div>