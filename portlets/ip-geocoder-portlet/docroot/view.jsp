<%@ page import="com.liferay.ipgeocoder.model.IPInfo" %>
<%@ page import="com.liferay.ipgeocoder.service.IPInfoLocalServiceUtil" %>
<%@ page import="com.liferay.portal.util.PortalUtil" %>

<%
HttpServletRequest originalRequest = PortalUtil.getOriginalServletRequest(request);

IPInfo ipInfo = IPInfoLocalServiceUtil.getIPInfo(originalRequest.getRemoteAddr());
%>

IP: <%= ipInfo.getIpAddress() %><br />
Latitude: <%= ipInfo.getLatitude() %><br />
Longitude: <%= ipInfo.getLongitude() %>