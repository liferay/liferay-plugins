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

<%@ include file="/display/init.jsp" %>

<liferay-util:include page="/aggregator/configuration.jsp" servletContext="<%= application %>">
	<liferay-util:param name="rootPortletId" value="<%= PortletKeys.KNOWLEDGE_BASE_DISPLAY %>" />
	<liferay-util:param name="jspPath" value="/display/" />
</liferay-util:include>