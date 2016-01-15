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
	Group group = GroupLocalServiceUtil.getGroup(scopeGroupId);
	String theState = "collapsed";
	if (renderRequest.getWindowState().equals(WindowState.MAXIMIZED)) {
		theState = "open";
	}
%>

<c:choose>
	<c:when test="<%= group.isUser() %>">
		<liferay-ui:message key="this-application-will-only-function-when-placed-on-a-site-page" />
	</c:when>
	<c:when test="<%= GroupPermissionUtil.contains(permissionChecker, group.getGroupId(), ActionKeys.UPDATE) %>">
		<portlet:renderURL var="inviteURL" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
			<portlet:param name="mvcPath" value="/invite_members/view_invite.jsp" />
		</portlet:renderURL>



		<liferay-ui:panel-container accordion="true" extended="false">
		<liferay-ui:panel title="invite-members-to-this-site" defaultState="<%=theState%>">
		<liferay-util:include page="/invite_members/view_invite.jsp" servletContext="<%= application %>" />


		<aui:script position="inline" use="aui-base,aui-io-plugin-deprecated,liferay-so-invite-members,liferay-util-window">
			AUI().ready('aui-base', 'aui-io-plugin-deprecated', 'liferay-so-invite-members', 'liferay-util-window', function(A) {
									new Liferay.SO.InviteMembers(
										{
											portletNamespace: '<portlet:namespace />'
										}
									);
			});
		</aui:script>
		</liferay-ui:panel>
		</liferay-ui:panel-container>
	</c:when>
	<c:otherwise>
		<aui:script use="aui-base">
			var portlet = A.one('#p_p_id<portlet:namespace />');

			if (portlet) {
				portlet.hide();
			}
		</aui:script>
	</c:otherwise>
</c:choose>