<%--
/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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
%>

<c:choose>
	<c:when test="<%= group.isUser() %>">
		<liferay-ui:message key="this-application-will-only-function-when-placed-on-a-site-page" />
	</c:when>
	<c:when test="<%= GroupPermissionUtil.contains(permissionChecker, group.getGroupId(), ActionKeys.UPDATE) %>">
		<portlet:renderURL var="inviteURL" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
			<portlet:param name="mvcPath" value="/invite_members/view_invite.jsp" />
		</portlet:renderURL>

		<a class="invite-members" href="javascript:;" onClick="<portlet:namespace />openInviteMembers('<%= inviteURL %>');"><liferay-ui:message key="invite-members-to-this-site" /></a>

		<aui:script use="aui-base,aui-dialog,aui-io-plugin,liferay-soffice-invitemembers">
			Liferay.provide(
				window,
				'<portlet:namespace />openInviteMembers',
				function(url) {
					if (A.one('#so-invitemembers-container')) {
						return;
					}

					var title = '';
					var titleNode = A.one('.so-portlet-invite-members .portlet-title-default');

					if (titleNode) {
						title = titleNode.get('innerHTML');
					}

					var viewportRegion = A.getBody().get('viewportRegion');

					var dialog = new A.Dialog(
						{
							cssClass: 'so-portlet-invite-members',
							destroyOnClose: true,
							resizable: false,
							title: title,
							width: 700,
							xy: [viewportRegion.left + 20, viewportRegion.top + 20]
						}
					).plug(
						A.Plugin.IO,
						{
							after: {
								success: function() {
									new Liferay.SO.InviteMembers(
										{
											dialog: dialog,
											portletNamespace: '<portlet:namespace />'
										}
									);
								}
							},
							method: 'GET',
							uri: url
						}
					).render();
				},
				['aui-base', 'aui-dialog', 'aui-io-plugin', 'liferay-soffice-invitemembers']
			);
		</aui:script>
	</c:when>
</c:choose>