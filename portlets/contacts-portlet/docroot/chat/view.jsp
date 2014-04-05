<%--
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This file is part of Liferay Social Office. Liferay Social Office is free
 * software: you can redistribute it and/or modify it under the terms of the GNU
 * Affero General Public License as published by the Free Software Foundation,
 * either version 3 of the License, or (at your option) any later version.
 *
 * Liferay Social Office is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License
 * for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * Liferay Social Office. If not, see http://www.gnu.org/licenses/agpl-3.0.html.
 */
--%>

<%@ include file="/init.jsp" %>

<%
Portlet portlet = PortletLocalServiceUtil.getPortletById(company.getCompanyId(), PortletKeys.CONTACTS_CENTER);
%>

<liferay-portlet:renderURL portletName="<%= PortletKeys.CONTACTS_CENTER %>" varImpl="profileURL" windowState="<%= WindowState.MAXIMIZED.toString() %>">
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