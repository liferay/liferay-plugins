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

<logic:messagesPresent>
	<span class="alert alert-danger">
	<html:errors />
	</span>
</logic:messagesPresent>

<html:form action="/sample_struts_portlet/nested/action" method="post">

<table border="0" cellpadding="0" cellspacing="0">
<tr>
	<td>
		Book Title
	</td>
	<td style="padding-left: 10px;"></td>
	<td>
		Book Cover
	</td>
</tr>

<nested:iterate property="books">
	<tr>
		<td>
			<nested:text property="title" />
		</td>
		<td style="padding-left: 10px;"></td>
		<td>
			<nested:file property="cover" />
		</td>
	</tr>
</nested:iterate>

</table>

<br />

<html:submit>Nested Submit</html:submit>

</html:form>