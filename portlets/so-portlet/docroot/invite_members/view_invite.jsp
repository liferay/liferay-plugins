<%--
/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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
	<c:when test='<%= SessionMessages.contains(renderRequest, "requestProcessed") %>'>
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

		<div id="<portlet:namespace />inviteMembersContainer">
			<div class="user-search-wrapper">
				<h2>
					<liferay-ui:message key="find-members" />
				</h2>

				<input class="invite-user-search" id="<portlet:namespace />inviteUserSearch" name="<portlet:namespace />userName" type="text" />

				<div class="search">
					<div class="list"></div>
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
					<form action='<portlet:actionURL name="sendInvites" />' id="<portlet:namespace />fm" method="post" name="<portlet:namespace />fm">
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

<aui:script use="aui-base,datasource-io,datatype-number,liferay-so-invite-members-list">
	var inviteMembersContainer = A.one('#<portlet:namespace />inviteMembersContainer');

	var invitedMembersList = inviteMembersContainer.one('.user-invited .list');
	var searchList = inviteMembersContainer.one('.search .list');

	var pageDelta = 50;

	var createDataSource = function(url) {
		return new A.DataSource.IO(
			{
				ioConfig: {
					method: "post"
				},
				on: {
					request: function(event) {
						var data = event.request;

						event.cfg.data = {
							end: data.end || pageDelta,
							keywords: data.keywords || '',
							start: data.start || 0
						}
					}
				},
				source: url
			}
		);
	}

	var inviteMembersList = new Liferay.SO.InviteMembersList(
		{
			inputNode: '#<portlet:namespace />inviteMembersContainer #<portlet:namespace />inviteUserSearch',
			listNode: '#<portlet:namespace />inviteMembersContainer .search .list',
			minQueryLength: 0,
			requestTemplate: function(query) {
				return {
					end: pageDelta,
					keywords: query,
					start: 0
				}
			},
			resultTextLocator: function(response) {
				var result = '';

				if (typeof response.toString != 'undefined') {
					result = response.toString();
				}
				else if (typeof response.responseText != 'undefined') {
					result = response.responseText;
				}

				return result;
			},
			source: createDataSource('<portlet:resourceURL id="getAvailableUsers" />')
		}
	);

	var renderResults = function(responseData) {
		var count = responseData.count;
		var options = responseData.options;
		var results = responseData.users;

		var buffer = [];

		if (results.length == 0) {
			if (options.start == 0) {
				buffer.push(
					'<div class="empty"><liferay-ui:message key="there-are-no-users-to-invite" unicode="<%= true %>" /></div>'
				);
			}
		}
		else {
			buffer.push(
				A.Array.map(
					results,
					function(result) {
						var userTemplate =
							'<div class="{cssClass}" data-userId="{userId}">' +
								'<span class="name">{userFullName}</span>'+
								'<span class="email">{userEmailAddress}</span>' +
							'</div>';

						var invited = invitedMembersList.one('[data-userId="' + result.userId + '"]');

						return A.Lang.sub(
							userTemplate,
							{
								cssClass: invited ? "invited user" : "user",
								userEmailAddress: result.userEmailAddress,
								userFullName: result.userFullName,
								userId: result.userId
							}
						);
					}
				).join('')
			);

			if (count > results.length) {
				buffer.push(
					'<div class="more-results">' +
						'<a href="javascript:;" data-end="' + options.end + '"><liferay-ui:message key="view-more" unicode="<%= true %>" /></a>' +
					'</div>'
				);
			}
		}

		return buffer;
	}

	var showMoreResults = function(responseData) {
		var moreResults = searchList.one('.more-results');

		moreResults.remove();

		searchList.append(renderResults(responseData).join(''));
	}

	var updateInviteMembersList = function(event) {
		var responseData = A.JSON.parse(event.data.responseText);

		searchList.html(renderResults(responseData).join(''));
	}

	inviteMembersList.on('results', updateInviteMembersList);

	searchList.delegate(
		'click',
		function(event) {
			var node = event.currentTarget;

			var start = A.DataType.Number.parse(node.getAttribute('data-end'));

			var end = start + pageDelta;

			var inviteUserSearch = inviteMembersContainer.one('#<portlet:namespace />inviteUserSearch');

			A.io.request(
				'<portlet:resourceURL id="getAvailableUsers" />',
				{
					after: {
						success: function(event, id, obj) {
							var responseData = this.get('responseData');

							showMoreResults(responseData);
						}
					},
					data: {
						end: end,
						keywords: inviteUserSearch.get('value'),
						start: start
					},
					dataType: 'json'
				}
			);
		},
		'.more-results a'
	);

	inviteMembersList.sendRequest();
</aui:script>