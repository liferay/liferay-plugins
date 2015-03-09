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

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

<%@ page import="com.liferay.ip.geocoder.IPInfo" %>

<%
IPInfo ipInfo = (IPInfo)request.getAttribute(IPInfo.class.getName());
%>

<c:choose>
	<c:when test="<%= ipInfo != null %>">
		City: <%= ipInfo.getCity() %><br />
		Country Code: <%= ipInfo.getCountryCode() %><br />
		Country Name: <%= ipInfo.getCountryName() %><br />
		IP: <%= ipInfo.getIpAddress() %><br />
		Latitude: <%= ipInfo.getLatitude() %><br />
		Longitude: <%= ipInfo.getLongitude() %><br />
		Postal Code: <%= ipInfo.getPostalCode() %><br />
		Region: <%= ipInfo.getRegion() %>
	</c:when>
	<c:otherwise>
		<div class="alert alert-danger">
			<liferay-ui:message key="an-unexpected-error-occurred" />
		</div>
	</c:otherwise>
</c:choose>