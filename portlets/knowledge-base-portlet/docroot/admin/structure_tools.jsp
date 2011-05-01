<%--
/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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
KBStructure kbStructure = (KBStructure)request.getAttribute(WebKeys.KNOWLEDGE_BASE_KB_STRUCTURE);
%>

<div class="kb-structure-tools">
	<table class="lfr-table">
	<tr>
		<td>
			<liferay-portlet:renderURL var="printURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
				<portlet:param name="jspPage" value='<%= jspPath + "print_structure.jsp" %>' />
				<portlet:param name="kbStructureId" value="<%= String.valueOf(kbStructure.getKbStructureId()) %>" />
			</liferay-portlet:renderURL>

			<%
			String taglibURL = "javascript:var printKBStructureWindow = window.open('" + printURL + "', 'printKBStructure', 'directories=no,height=640,location=no,menubar=no,resizable=yes,scrollbars=yes,status=no,toolbar=no,width=680'); void(''); printKBStructureWindow.focus();";
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