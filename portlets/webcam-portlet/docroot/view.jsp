<%
/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
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
%>

<%@ include file="/init.jsp" %>

<c:choose>
	<c:when test="<%= Validator.isNotNull(url) %>">
		<table>
		<tr>
			<td>
				<applet archive="cambozola.jar" code="com.charliemouse.cambozola.Viewer" codebase="<%= request.getContextPath() %>" height="<%= height %>" width="<%= width %>">
					<param name="url" value="<%= url %>">
					<param name="accessories" value="none">
				</applet>
			</td>
		</tr>

		<c:if test="<%= Validator.isNotNull(message) %>">
			<tr>
				<td>
					<%= message %>
				</td>
			</tr>
		</c:if>

		</table>
	</c:when>
	<c:otherwise>
		<liferay-ui:message key="please-contact-the-administrator-to-setup-this-portlet" />
	</c:otherwise>
</c:choose>