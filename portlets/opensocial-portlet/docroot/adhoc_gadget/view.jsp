<%--
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
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
Gadget gadget = (Gadget)renderRequest.getAttribute(WebKeys.GADGET);
%>

<c:choose>
	<c:when test="<%= gadget == null %>">
		<div class="alert alert-info portlet-configuration">
			<a href="<%= portletDisplay.getURLConfiguration() %>" onClick="<%= portletDisplay.getURLConfigurationJS() %>">
				<liferay-ui:message key="configure-a-gadget-to-be-displayed-in-this-portlet" />
			</a>
		</div>

		<liferay-ui:icon
			cssClass="portlet-configuration"
			iconCssClass="icon-cog"
			message="configure-gadget"
			method="get"
			onClick="<%= portletDisplay.getURLConfigurationJS() %>"
			url="<%= portletDisplay.getURLConfiguration() %>"
		/>
	</c:when>
	<c:otherwise>
		<liferay-util:include page="/gadget/view.jsp" servletContext="<%= application %>" />
	</c:otherwise>
</c:choose>