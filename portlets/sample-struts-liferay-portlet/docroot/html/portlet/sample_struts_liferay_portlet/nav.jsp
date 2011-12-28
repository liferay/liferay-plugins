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

Window State:

<html:link page="/sample_struts_liferay_portlet/view?windowState=maximized">Maximize</html:link>

|

<html:link page="/sample_struts_liferay_portlet/view?windowState=normal">Normal</html:link>

<br /><br />

Portlet Mode:

<html:link page="/sample_struts_liferay_portlet/edit?portletMode=edit">Edit</html:link>

|

<html:link page="/sample_struts_liferay_portlet/help?portletMode=help">Help</html:link>

|

<html:link page="/sample_struts_liferay_portlet/print?portletMode=print">Print</html:link>

|

<html:link page="/sample_struts_liferay_portlet/view?portletMode=view">View</html:link>

<br /><br />

View Page:

<html:link page="/sample_struts_liferay_portlet/view">Default</html:link>

|

<html:link page="/sample_struts_liferay_portlet/x">X</html:link>

|

<html:link page="/sample_struts_liferay_portlet/y?hello=Hello+World%21">Y</html:link>

|

<%
Map zParams = new HashMap();

zParams.put("hello", "Hello World!");
zParams.put("hi", "Hi Mom!");

pageContext.setAttribute("zParams", zParams);
%>

<html:link name="zParams" page="/sample_struts_liferay_portlet/z">Z</html:link>

<br /><br />

Portlet URL:

<a href="<portlet:actionURL><portlet:param name="struts_action" value="/sample_struts_liferay_portlet/x" /><portlet:param name="x_param" value="bad_x_value" /></portlet:actionURL>">Action</a>

|

<a href="<portlet:renderURL><portlet:param name="struts_action" value="/sample_struts_liferay_portlet/x" /></portlet:renderURL>">Render</a>

<br /><br />

User Information:

<html:link page="/sample_struts_liferay_portlet/user_attributes">Portlet and Custom User Attributes</html:link>

<br /><br />

Shared Sessions:

<html:link page="/sample_struts_liferay_portlet/portlet_session_attributes">Portlet Session Attributes</html:link>

|

<a href="<%= request.getContextPath() %>/test_session/servlet_session_attributes">Servlet Session Attributes</a>

<br /><br />

Portlet Display:

<html:link page="/sample_struts_liferay_portlet/portlet_display_attributes">Portlet Display Attributes</html:link>

<br /><br />

Chart:

<a href="javascript:var viewChartWindow = window.open('<%= request.getContextPath() %>/portlet_action/sample_struts_liferay_portlet/view_chart?chart_type=area', 'viewChart', 'directories=no,height=430,location=no,menubar=no,resizable=no,scrollbars=no,status=no,toolbar=no,width=420'); void(''); viewChartWindow.focus();">Area</a>

|

<a href="javascript:var viewChartWindow = window.open('<%= request.getContextPath() %>/portlet_action/sample_struts_liferay_portlet/view_chart?chart_type=horizontal_bar', 'viewChart', 'directories=no,height=430,location=no,menubar=no,resizable=no,scrollbars=no,status=no,toolbar=no,width=420'); void(''); viewChartWindow.focus();">Horizontal Bar</a>

|

<a href="javascript:var viewChartWindow = window.open('<%= request.getContextPath() %>/portlet_action/sample_struts_liferay_portlet/view_chart?chart_type=line', 'viewChart', 'directories=no,height=430,location=no,menubar=no,resizable=no,scrollbars=no,status=no,toolbar=no,width=420'); void(''); viewChartWindow.focus();">Line</a>

|

<a href="javascript:var viewChartWindow = window.open('<%= request.getContextPath() %>/portlet_action/sample_struts_liferay_portlet/view_chart?chart_type=pie', 'viewChart', 'directories=no,height=430,location=no,menubar=no,resizable=no,scrollbars=no,status=no,toolbar=no,width=420'); void(''); viewChartWindow.focus();">Pie</a>

|

<a href="javascript:var viewChartWindow = window.open('<%= request.getContextPath() %>/portlet_action/sample_struts_liferay_portlet/view_chart?chart_type=vertical_bar', 'viewChart', 'directories=no,height=430,location=no,menubar=no,resizable=no,scrollbars=no,status=no,toolbar=no,width=420'); void(''); viewChartWindow.focus();">Vertical Bar</a>

<br /><br />

Struts Form:

<html:link page="/sample_struts_liferay_portlet/subscribe?firstName=John&lastName=Wayne&emailAddress=test@liferay.com">Subscribe</html:link>

|

<html:link page="/sample_struts_liferay_portlet/unsubscribe?firstName=John&lastName=Wayne&emailAddress=test@liferay.com">Unsubscribe</html:link>

|

<html:link page="/sample_struts_liferay_portlet/upload">Upload</html:link>

<br /><br />

Struts Exception:

<a href="<portlet:actionURL><portlet:param name="struts_action" value="/sample_struts_liferay_portlet/x" /><portlet:param name="action_exception" value="true" /></portlet:actionURL>">Action</a>

|

<a href="<portlet:renderURL><portlet:param name="struts_action" value="/sample_struts_liferay_portlet/x" /><portlet:param name="render_exception" value="true" /></portlet:renderURL>">Render</a>