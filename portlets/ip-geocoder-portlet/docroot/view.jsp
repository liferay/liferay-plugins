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

<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

<%@ page import="com.liferay.ipgeocoder.model.IPInfo" %>
<%@ page import="com.liferay.ipgeocoder.util.IPGeocoderUtil" %>
<%@ page import="com.liferay.portal.kernel.util.ParamUtil" %>
<%@ page import="com.liferay.portal.util.PortalUtil" %>

<%
boolean init = ParamUtil.getBoolean(request, "init");

if (init) {
	IPGeocoderUtil.init();
}
%>

<c:choose>
	<c:when test="<%= !IPGeocoderUtil.isInitialized() %>">
		<div class="portlet-msg-info">
			<a href="http://www.maxmind.com/app/geolitecity"><liferay-ui:message key="install-and-configure-maxmind-geopip-city-or-geolite-city-in-order-to-use-this-portlet" /></a>
		</div>

		<form action="<portlet:renderURL><portlet:param name='init' value='true' /></portlet:renderURL>" method="post">
			<input type="submit" value="<liferay-ui:message key="refresh" />" />
		</form>
	</c:when>
	<c:otherwise>

		<%
		HttpServletRequest originalRequest = PortalUtil.getOriginalServletRequest(request);

		IPInfo ipInfo = IPGeocoderUtil.getIPInfo(originalRequest.getRemoteAddr());
		%>

		IP: <%= ipInfo.getIpAddress() %><br />
		Latitude: <%= ipInfo.getLatitude() %><br />
		Longitude: <%= ipInfo.getLongitude() %>
	</c:otherwise>
</c:choose>


