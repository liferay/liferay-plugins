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
			<td>
				<font class="portlet-font" style="font-size: x-small;">
				<h:outputText value="#{msgs.please_enter_your_name}" />
				</font>
			</td>
			<td>
				<h:inputText id="name" required="true" value="#{user.name}" />
			</td>
		</tr>
		<tr>
			<td align="center" colspan="2">
				<font class="portlet-font" style="font-size: x-small;">
				<h:message for="name" />
				</font>
			</td>
		</tr>
		<tr>
			<td align="center" colspan="2">
				<h:commandButton action="submit" value="Submit" />
			</td>
		</tr>
		</table>
	</h:form>
</f:view>