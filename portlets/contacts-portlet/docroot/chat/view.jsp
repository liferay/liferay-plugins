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

<%
Portlet portlet = PortletLocalServiceUtil.getPortletById(company.getCompanyId(), "1_WAR_contactsportlet");
%>

<liferay-portlet:renderURL portletName="1_WAR_contactsportlet" varImpl="profileURL" windowState="<%= WindowState.MAXIMIZED.toString() %>">
	<portlet:param name="mvcPath" value="/contacts_center/view_user.jsp" />
	<portlet:param name="backURL" value="<%= PortalUtil.getCurrentURL(request) %>" />
</liferay-portlet:renderURL>

<aui:script>
	Liferay.on(
		'chatPortletReady',
		function() {
			Liferay.Chat.Manager.registerBuddyService(
				{
					fn: function(user) {
						var userId = user.getAttribute('userId');

						url = Liferay.Util.addParams("_1_WAR_contactsportlet_userId=" + userId, '<%= profileURL %>');

						window.location = url;
					},
					icon: '<%= portlet.getStaticResourcePath().concat(portlet.getIcon()) %>',
					name: 'contacts-portlet'
				}
			);
		}
	);
</aui:script>