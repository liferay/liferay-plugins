<%
/**
 * Copyright (c) 2000-2007 Liferay, Inc. All rights reserved.
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

<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<%@ taglib uri="http://java.sun.com/portlet" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/security" prefix="liferay-security" %>
<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>

<portlet:defineObjects />

<liferay-theme:defineObjects />

<%
long groupId = themeDisplay.getPortletGroupId();
String name = portletDisplay.getRootPortletId();
String primKey = portletDisplay.getResourcePK();
String actionId = "VIEW";
%>

Do you have the VIEW permission for this portlet?

<b>

<c:choose>
	<c:when test="<%= permissionChecker.hasPermission(groupId, name, primKey, actionId) %>">
		Yes
	</c:when>
	<c:otherwise>
		No
	</c:otherwise>
</c:choose>

</b>

<br><br>

<%
name = "com.sample.permissions.model.Something";
primKey = "1";
actionId = "DELETE";
%>

Does you have the DELETE permission for the model <%= name %> with the primary key <%= primKey %>?

<b>

<c:choose>
	<c:when test="<%= permissionChecker.hasPermission(groupId, name, primKey, actionId) %>">
		Yes
	</c:when>
	<c:otherwise>
		No
	</c:otherwise>
</c:choose>

</b>

<br><br>

<portlet:renderURL var="redirectURL" />

<liferay-security:permissionsURL
	redirect="<%= redirectURL %>"
	modelResource="<%= name %>"
	modelResourceDescription='<%= "Hello World" %>'
	resourcePrimKey="<%= primKey %>"
	var="permissionsURL"
/>

Click <a href="<%= permissionsURL %>">here</a> to edit the permissions for the <%= name %> model.