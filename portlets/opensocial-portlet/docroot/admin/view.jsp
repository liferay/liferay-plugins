<%--
/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
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

<%
PortletURL portletURL = renderResponse.createRenderURL();
%>

<liferay-ui:search-container
	emptyResultsMessage="there-are-no-gadgets"
	headerNames="name"
	iteratorURL="<%= portletURL %>"
>
	<liferay-ui:search-container-results
		results="<%= GadgetLocalServiceUtil.getGadgets(company.getCompanyId(), searchContainer.getStart(), searchContainer.getEnd()) %>"
		total="<%= GadgetLocalServiceUtil.getGadgetsCount(company.getCompanyId()) %>"
	/>

	<liferay-ui:search-container-row
		className="com.liferay.opensocial.model.Gadget"
		escapedModel="<%= false %>"
		keyProperty="gadgetId"
		modelVar="gadget"
	>
		<portlet:renderURL var="rowURL">
			<portlet:param name="jspPage" value="/admin/edit_gadget.jsp" />
			<portlet:param name="redirect" value="<%= currentURL %>" />
			<portlet:param name="gadgetId" value="<%= String.valueOf(gadget.getGadgetId()) %>" />
		</portlet:renderURL>

		<liferay-ui:search-container-column-text
			href="<%= rowURL %>"
			name="gadget"
			property="name"
		/>

		<liferay-ui:search-container-column-jsp
			align="right"
			path="/admin/gadget_action.jsp"
			valign="top"
		/>
	</liferay-ui:search-container-row>

	<div>
		<input type="button" value="<liferay-ui:message key="add-gadget" />" onClick="location.href = '<portlet:renderURL><portlet:param name="jspPage" value="/admin/edit_gadget.jsp" /><portlet:param name="redirect" value="<%= currentURL %>" /></portlet:renderURL>';" />
	</div>

	<br />

	<liferay-ui:search-iterator />
</liferay-ui:search-container>