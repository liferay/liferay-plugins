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

<%@ include file="/init.jsp" %>

Code:

<br /><br />

<pre>&lt;liferay-ui:calendar
&nbsp;&nbsp;&nbsp;&nbsp;month="&lt;%= Calendar.FEBRUARY %&gt;"
&nbsp;&nbsp;&nbsp;&nbsp;day="&lt;%= 14 %&gt;"
&nbsp;&nbsp;&nbsp;&nbsp;year="&lt;%= 2007 %&gt;"
&nbsp;&nbsp;&nbsp;&nbsp;headerPattern="MMMM"
/&gt;</pre>

<div class="separator"></div>

Result:

<br /><br />

<liferay-ui:calendar
	day="<%= 14 %>"
	headerPattern="MMMM"
	month="<%= Calendar.FEBRUARY %>"
	year="<%= 2007 %>"
/>

<div class="separator"></div>

&laquo; <a href="<portlet:renderURL />">Back</a>