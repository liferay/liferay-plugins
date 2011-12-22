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

<html:form action="/sample_struts_portlet/upload/action" enctype="multipart/form-data" method="post" focus="file">

<table border="0" cellpadding="0" cellspacing="0">
<tr>
	<td>
		File Location
	</td>
	<td style="padding-left: 10px;"></td>
	<td>
		<html:file property="file" />
	</td>
</tr>
</table>

<br />

<html:submit>Upload File</html:submit>

</html:form>