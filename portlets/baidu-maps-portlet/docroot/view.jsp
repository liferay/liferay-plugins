<%--
/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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

<liferay-util:html-top>
	<script src="http://api.map.baidu.com/api?v=1.4" type="text/javascript"></script>
</liferay-util:html-top>

<%
String tabs1 = ParamUtil.getString(request, "tabs1", "search-location");

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("tabs1", tabs1);
%>

<liferay-ui:tabs
	names= "search-location,get-directions"
	param="tabs1"
	url="<%= portletURL.toString() %>"
/>

<c:choose>
	<c:when test='<%= tabs1.equals("search-location") %>'>
		<%@ include file="/search_location.jsp" %>
	</c:when>
	<c:when test='<%= tabs1.equals("get-directions") %>'>
		<%@ include file="/get_directions.jsp" %>
	</c:when>
</c:choose>

<aui:script>
	var <portlet:namespace />map = BaiduMaps.Map.createMap("<portlet:namespace />mapCanvas", <%= longitude %>, <%= latitude %>, 10);

	function <portlet:namespace />getLocations() {
		BaiduMaps.Map.getLocations(<portlet:namespace />map, "<portlet:namespace />locations", "<portlet:namespace />mapAddress");
	}

	function <portlet:namespace />getDirections() {
		BaiduMaps.Map.getDirections(<portlet:namespace />map, "<portlet:namespace />directionSteps", "<portlet:namespace />fromAddress", "<portlet:namespace />toAddress");
	}
</aui:script>