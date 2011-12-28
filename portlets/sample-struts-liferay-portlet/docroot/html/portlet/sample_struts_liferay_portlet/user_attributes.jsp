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

<%@ include file="/html/portlet/sample_struts_liferay_portlet/init.jsp" %>

<font class="portlet-font" style="font-size: x-small;">

<%
Map userInfo = (Map)renderRequest.getAttribute(PortletRequest.USER_INFO);

if (userInfo == null) {
%>

	The user information map is null.

<%
}
else if (userInfo.size() == 0) {
%>

	The user information map is not null but has 0 entries.

<%
}
else {
	Iterator itr = userInfo.entrySet().iterator();

	while (itr.hasNext()) {
		Map.Entry entry = (Map.Entry)itr.next();

		String key = (String)entry.getKey();
		String value = (String)entry.getValue();
%>

		<%= key %>=<%= value %><br />

<%
	}
}
%>

</font>