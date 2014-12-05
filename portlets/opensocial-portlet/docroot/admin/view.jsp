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

<%@ include file="/init.jsp" %>

<%
PortletURL portletURL = renderResponse.createRenderURL();
%>

<liferay-ui:search-container
	emptyResultsMessage="there-are-no-gadgets"
	headerNames="name"
	iteratorURL="<%= portletURL %>"
	total="<%= GadgetLocalServiceUtil.getGadgetsCount(company.getCompanyId()) %>"
>
	<liferay-ui:search-container-results
		results="<%= GadgetLocalServiceUtil.getGadgets(company.getCompanyId(), searchContainer.getStart(), searchContainer.getEnd()) %>"
	/>

	<liferay-ui:search-container-row
		className="com.liferay.opensocial.model.Gadget"
		keyProperty="gadgetId"
		modelVar="gadget"
	>
		<liferay-ui:search-container-column-text
			name="gadget"
			property="name"
		/>

		<%
		String gadgetURL = gadget.getUrl();
		%>

		<liferay-ui:search-container-column-text
			href="<%= gadgetURL %>"
			name="url"
			value="<%= gadgetURL %>"
		/>

		<liferay-ui:search-container-column-jsp
			align="right"
			cssClass="entry-action"
			path="/admin/gadget_action.jsp"
			valign="top"
		/>
	</liferay-ui:search-container-row>

	<div>
		<c:if test="<%= GadgetPermission.contains(permissionChecker, themeDisplay.getScopeGroupId(), ActionKeys.PUBLISH_GADGET) %>">
			<span>
				<portlet:renderURL var="publishGadgetURL">
					<portlet:param name="mvcPath" value="/admin/edit_gadget.jsp" />
					<portlet:param name="redirect" value="<%= currentURL %>" />
				</portlet:renderURL>

				<aui:button onClick="<%= publishGadgetURL %>" value="publish-gadget" />
			</span>
		</c:if>

		<span>
			<portlet:actionURL name="refreshGadgets" var="refreshGadgetsURL">
				<portlet:param name="redirect" value="<%= currentURL %>" />
			</portlet:actionURL>

			<aui:button onClick="<%= refreshGadgetsURL %>" value="refresh-gadgets" />
		</span>
	</div>

	<br />

	<liferay-ui:search-iterator />
</liferay-ui:search-container>