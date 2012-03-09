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

<c:choose>
	<c:when test='<%= SessionMessages.contains(renderRequest, "request_processed") %>'>
		<div class="portlet-msg-success">
			<liferay-ui:message key="your-request-processed-successfully" />
		</div>

		<aui:script use="aui-dialog">
			var closeDialog = function() {
				var container = A.one('.so-portlet-invite-members-content');

				if (container) {
					A.DialogManager.closeByChild(container);
				}
			}

			setTimeout(closeDialog, 3000);
		</aui:script>
	</c:when>
	<c:otherwise>

		<%
		Group group = GroupLocalServiceUtil.getGroup(scopeGroupId);
		%>

		<div id="so-invitemembers-container">
			<div class="user-search-wrapper">
				<h2>
					<liferay-ui:message key="find-members" />
				</h2>

				<input id="invite-user-search" name="<portlet:namespace />userName" type="text" />

				<div class="search">
					<div class="list">

						<%
						LinkedHashMap usersParams = new LinkedHashMap();

						List<User> users = UserLocalServiceUtil.search(layout.getCompanyId(), StringPool.BLANK, WorkflowConstants.STATUS_APPROVED, usersParams, QueryUtil.ALL_POS, QueryUtil.ALL_POS, new ContactFirstNameComparator(true));

						User defaultUser = UserLocalServiceUtil.getDefaultUser(layout.getCompanyId());

						List<User> inviteUsers = new ArrayList<User>();

						for (User curUser : users) {
							if (!UserLocalServiceUtil.hasGroupUser(layout.getGroupId(), curUser.getUserId()) && !curUser.equals(defaultUser)) {
								inviteUsers.add(curUser);
							}
						}

						if (!inviteUsers.isEmpty()) {
							for (User curUser : inviteUsers) {
						%>

								<div class="user" data-userId="<%= curUser.getUserId() %>">
									<span class="name">
										<%= HtmlUtil.escape(curUser.getFullName()) %>
									</span>

									<span class="email">
										<%= curUser.getEmailAddress() %>
									</span>
								</div>

						<%
							}
						}
						else {
						%>

							<div>
								<liferay-ui:message key="there-are-no-users-to-invite" />
							</div>

						<%
						}
						%>

					</div>
				</div>
			</div>

			<div class="invited-users-wrapper">
				<div class="user-invited">
					<h2>
						<liferay-ui:message key="members-to-invite" />

						<span>
							<liferay-ui:message key="to-add,-click-members-on-the-left" />
						</span>
					</h2>

					<div class="list">
					</div>
				</div>

				<div class="email-invited">
					<h2>
						<liferay-ui:message key="invite-by-email" />
					</h2>

					<div class="list">
					</div>

					<div class="controls">
						<input id="new-member-email-address" name="<portlet:namespace />emailAddress" size="50" type="text" />

						<input id="so-add-email-address" type="button" value="<liferay-ui:message key="add-email-address" />" />
					</div>
				</div>

				<%
				List<Role> roles = RoleLocalServiceUtil.search(layout.getCompanyId(), null, null, new Integer[] {RoleConstants.TYPE_SITE}, QueryUtil.ALL_POS, QueryUtil.ALL_POS, new RoleNameComparator(false));

				roles = UsersAdminUtil.filterGroupRoles(permissionChecker, group.getGroupId(), roles);
				%>

				<c:if test="<%= !roles.isEmpty() && GroupPermissionUtil.contains(permissionChecker, group.getGroupId(), ActionKeys.ASSIGN_USER_ROLES) %>">
					<div class="invite-to">
						<h2>
							<liferay-ui:message key="invite-to-role" />
						</h2>

						<select name="<portlet:namespace />roleId">
							<option selected value="0"></option>

							<%
							for (Role role : roles) {
							%>

								<option value="<%= role.getRoleId() %>"><%= HtmlUtil.escape(role.getName()) %></option>

							<%
							}
							%>

						</select>
					</div>
				</c:if>

				<%
				List<Team> teams = TeamLocalServiceUtil.getGroupTeams(group.getGroupId());
				%>

				<c:if test="<%= !teams.isEmpty() && GroupPermissionUtil.contains(permissionChecker, group.getGroupId(), ActionKeys.MANAGE_TEAMS) %>">
					<div class="invite-to">
						<h2>
							<liferay-ui:message key="invite-to-team" />
						</h2>

						<select name="<portlet:namespace />teamId">
							<option selected value="0"></option>

							<%
							for (Team team : teams) {
							%>

								<option value="<%= team.getTeamId() %>"><%= HtmlUtil.escape(team.getName()) %></option>

							<%
							}
							%>

						</select>
					</div>
				</c:if>

				<div class="invite-actions">
					<form action="<portlet:actionURL name=" id="<portlet:namespace />fm"sendInvites" />" method="post" name="<portlet:namespace />fm">
					<input name="<portlet:namespace />redirect" type="hidden" value="<portlet:renderURL windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>"><portlet:param name="mvcPath" value="/invite_members/view_invite.jsp" /></portlet:renderURL>" />
					<input name="<portlet:namespace />groupId" type="hidden" value="<%= themeDisplay.getScopeGroupId() %>" />
					<input name="<portlet:namespace />receiverUserIds" type="hidden" value="" />
					<input name="<portlet:namespace />receiverEmailAddresses" type="hidden" value="" />
					<input name="<portlet:namespace />invitedRoleId" type="hidden" value="" />
					<input name="<portlet:namespace />invitedTeamId" type="hidden" value="" />

					<input id="<portlet:namespace />submit" type="submit" value="<liferay-ui:message key="send-invitations" />" />

					</form>
				</div>
			</div>
		</div>
	</c:otherwise>
</c:choose>