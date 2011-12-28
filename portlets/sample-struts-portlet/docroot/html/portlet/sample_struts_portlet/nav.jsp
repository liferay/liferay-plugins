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

<%@ include file="/html/portlet/sample_struts_portlet/init.jsp" %>

View Page:

<html:link page="/portlet_action/sample_struts_portlet/view">Default</html:link>

|

<html:link page="/portlet_action/sample_struts_portlet/x/render">X</html:link>

|

<html:link page="/portlet_action/sample_struts_portlet/y/render?hello=Hello+World%21">Y</html:link>

|

<%
Map zParams = new HashMap();

zParams.put("hello", "Hello World!");
zParams.put("hi", "Hi Mom!");

pageContext.setAttribute("zParams", zParams);
%>

<html:link name="zParams" page="/portlet_action/sample_struts_portlet/z">Z</html:link>

<br /><br />

Portlet URL:

<html:link actionURL="true" page="/portlet_action/sample_struts_portlet/x/action?x_param=bad_x_value">Action</html:link>

|

<html:link page="/portlet_action/sample_struts_portlet/x/render">Render</html:link>

<br /><br />

Chart:

<a href="javascript:var viewChartWindow = window.open('<%= request.getContextPath() %>/portlet_action/sample_struts_portlet/view_chart?chart_type=area', 'viewChart', 'directories=no,height=430,location=no,menubar=no,resizable=no,scrollbars=no,status=no,toolbar=no,width=420'); void(''); viewChartWindow.focus();">Area</a>

|

<a href="javascript:var viewChartWindow = window.open('<%= request.getContextPath() %>/portlet_action/sample_struts_portlet/view_chart?chart_type=horizontal_bar', 'viewChart', 'directories=no,height=430,location=no,menubar=no,resizable=no,scrollbars=no,status=no,toolbar=no,width=420'); void(''); viewChartWindow.focus();">Horizontal Bar</a>

|

<a href="javascript:var viewChartWindow = window.open('<%= request.getContextPath() %>/portlet_action/sample_struts_portlet/view_chart?chart_type=line', 'viewChart', 'directories=no,height=430,location=no,menubar=no,resizable=no,scrollbars=no,status=no,toolbar=no,width=420'); void(''); viewChartWindow.focus();">Line</a>

|

<a href="javascript:var viewChartWindow = window.open('<%= request.getContextPath() %>/portlet_action/sample_struts_portlet/view_chart?chart_type=pie', 'viewChart', 'directories=no,height=430,location=no,menubar=no,resizable=no,scrollbars=no,status=no,toolbar=no,width=420'); void(''); viewChartWindow.focus();">Pie</a>

|

<a href="javascript:var viewChartWindow = window.open('<%= request.getContextPath() %>/portlet_action/sample_struts_portlet/view_chart?chart_type=vertical_bar', 'viewChart', 'directories=no,height=430,location=no,menubar=no,resizable=no,scrollbars=no,status=no,toolbar=no,width=420'); void(''); viewChartWindow.focus();">Vertical Bar</a>

<br /><br />

Struts Form:

<html:link page="/portlet_action/sample_struts_portlet/nested/render">Nested</html:link>

|

<html:link page="/portlet_action/sample_struts_portlet/subscribe/render?firstName=John&lastName=Wayne&emailAddress=test@liferay.com">Subscribe</html:link>

|

<html:link page="/portlet_action/sample_struts_portlet/unsubscribe/render?firstName=John&lastName=Wayne&emailAddress=test@liferay.com">Unsubscribe</html:link>

|

<html:link page="/portlet_action/sample_struts_portlet/upload/render">Upload</html:link>

<br /><br />

Struts Exception:

<html:link actionURL="true" page="/portlet_action/sample_struts_portlet/x/action?action_exception=true">Action</html:link>

|

<html:link page="/portlet_action/sample_struts_portlet/x/render?render_exception=true">Render</html:link>