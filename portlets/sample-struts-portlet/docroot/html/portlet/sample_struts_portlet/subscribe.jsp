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

<%@ include file="/html/portlet/sample_struts_portlet/init.jsp" %>

<bean:define id="firstName" name="subscribeForm" property="firstName" type="java.lang.String" />
<bean:define id="lastName" name="subscribeForm" property="lastName" type="java.lang.String" />
<bean:define id="emailAddress" name="subscribeForm" property="emailAddress" type="java.lang.String" />

<logic:messagesPresent>
	<span class="alert alert-danger">
	<html:errors />
	</span>
</logic:messagesPresent>

<html:form action="/sample_struts_portlet/subscribe/action" focus="firstName" method="post">

<table border="0" cellpadding="0" cellspacing="0">
<tr>
	<td>
		First Name
	</td>
	<td style="padding-left: 10px;"></td>
	<td>
		<html:text name="subscribeForm" property="firstName" size="23" />
	</td>
</tr>
<tr>
	<td>
		Last Name
	</td>
	<td style="padding-left: 10px;"></td>
	<td>
		<html:text name="subscribeForm" property="lastName" size="23" />
	</td>
</tr>
<tr>
	<td>
		Email Address
	</td>
	<td style="padding-left: 10px;"></td>
	<td>
		<html:text name="subscribeForm" property="emailAddress" size="23" />
	</td>
</tr>
</table>

<br />

<html:submit>Subscribe</html:submit>

</html:form>