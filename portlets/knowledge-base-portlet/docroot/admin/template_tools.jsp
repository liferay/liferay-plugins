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

<%@ include file="/admin/init.jsp" %>

<%
KBTemplate kbTemplate = (KBTemplate)request.getAttribute(WebKeys.KNOWLEDGE_BASE_KB_TEMPLATE);
%>

<div class="kb-template-tools">
	<table class="lfr-table">
	<tr>
		<td>
			<liferay-portlet:renderURL var="printURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
				<portlet:param name="mvcPath" value='<%= templatePath + "print_template.jsp" %>' />
				<portlet:param name="kbTemplateId" value="<%= String.valueOf(kbTemplate.getKbTemplateId()) %>" />
			</liferay-portlet:renderURL>

			<%
			String taglibURL = "javascript:var printKBTemplateWindow = window.open('" + printURL + "', 'printKBTemplate', 'directories=no,height=640,location=no,menubar=no,resizable=yes,scrollbars=yes,status=no,toolbar=no,width=680'); void(''); printKBTemplateWindow.focus();";
			%>

			<liferay-ui:icon
				image="print"
				label="<%= true %>"
				method="get"
				url="<%= taglibURL %>"
			/>
		</td>
	</tr>
	</table>
</div>