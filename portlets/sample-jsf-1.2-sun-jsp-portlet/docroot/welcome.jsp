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

<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>

<f:view>
	<f:loadBundle basename="Language" var="msgs" />

	<h:form>
		<table>
		<tr>
			<td align="center">
				<font class="portlet-font" style="font-size: x-small;">
				<h:outputText value="#{msgs.welcome_to_liferay_portal}" />, <h:outputText value="#{user.name}" />.
				</font>
			</td>
		</tr>
		<tr>
			<td align="center">
				<h:commandButton action="back" value="Back" />
			</td>
		</tr>
		</table>
	</h:form>
</f:view>