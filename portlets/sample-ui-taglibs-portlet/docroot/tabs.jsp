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

<pre>&lt;liferay-ui:tabs
&nbsp;&nbsp;&nbsp;&nbsp;names="One,Two,Three"
&nbsp;&nbsp;&nbsp;&nbsp;refresh="&lt;%= false %&gt;"
&gt;
&nbsp;&nbsp;&nbsp;&nbsp;&lt;liferay-ui:section&gt;
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;One body
&nbsp;&nbsp;&nbsp;&nbsp;&lt;/liferay-ui:section&gt;
&nbsp;&nbsp;&nbsp;&nbsp;&lt;liferay-ui:section&gt;
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Two body
&nbsp;&nbsp;&nbsp;&nbsp;&lt;/liferay-ui:section&gt;
&nbsp;&nbsp;&nbsp;&nbsp;&lt;liferay-ui:section&gt;
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Three body
&nbsp;&nbsp;&nbsp;&nbsp;&lt;/liferay-ui:section&gt;
&lt;/liferay-ui:tabs&gt;</pre>

<div class="separator"></div>

Result:

<br /><br />

<liferay-ui:tabs
	names="One,Two,Three"
	refresh="<%= false %>"
>
	<liferay-ui:section>
		One body
	</liferay-ui:section>
	<liferay-ui:section>
		Two body
	</liferay-ui:section>
	<liferay-ui:section>
		Three body
	</liferay-ui:section>
</liferay-ui:tabs>

<div class="separator"></div>

&laquo; <a href="<portlet:renderURL />">Back</a>