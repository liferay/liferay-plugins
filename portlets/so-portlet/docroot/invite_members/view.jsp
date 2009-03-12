<%
/**
 * Copyright (c) 2000-2009 Liferay, Inc. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
%>

<%@ include file="/init.jsp" %>

<%
List<User> groupUsers = UserLocalServiceUtil.getGroupUsers(layout.getGroupId());
%>

<c:if test="<%= groupUsers.contains(user) %>">
	<div class="invite-members-wrapper" style="display: none;">
		<h1>
			<liferay-ui:message key="invite-members" />
		</h1>

		<div class="user-search-wrapper">
			<h2>
				<liferay-ui:message key="find-members" />
			</h2>

			<input id="invite-user-search" name="<portlet:namespace />userName" type="text" />

			<div class="uninvited">
				<div class="list">

					<%
					LinkedHashMap usersParams = new LinkedHashMap();

					List<User> users = UserLocalServiceUtil.search(layout.getCompanyId(), StringPool.BLANK, true, usersParams, QueryUtil.ALL_POS, QueryUtil.ALL_POS, new ContactFirstNameComparator(true));

					User defaultUser = UserLocalServiceUtil.getDefaultUser(layout.getCompanyId());

					List<User> inviteUsers = new ArrayList<User>();

					for (User curUser : users) {
						if(!groupUsers.contains(curUser) && !curUser.equals(defaultUser)) {
							inviteUsers.add(curUser);
						}
					}

					if (inviteUsers.size() > 0) {
						for (User curUser : inviteUsers) {
					%>

							<div class="user" data-userId="<%= curUser.getUserId() %>">
								<span class="name">
									<%= curUser.getFullName() %>
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
			<div class="invited">
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

					<span>
						<a href="<liferay-portlet:renderURL portletMode="<%= PortletMode.EDIT.toString() %>" />"><liferay-ui:message key="edit" /></a>
					</span>
				</h2>

				<div class="list">
				</div>

				<input id="new-member-email-address" name="<portlet:namespace />emailAddress" size="50" type="text" />

				<input id="add-email-address" type="button" value="<liferay-ui:message key="add-email-address" />" />
			</div>

			<form action="<portlet:actionURL name="sendEmail" />" method="post" name="<portlet:namespace />fm">

			<div class="invite-actions">
				<input id="invitedMembersCount" name="invitedMembersCount" type="hidden" value="0" />

				<input type="submit" value="<liferay-ui:message key="send-invitations" />" />
			</div>

			</form>
		</div>
	</div>

	<script type="text/javascript">
		jQuery(
			function() {
				Liferay.SO.InviteMembers.init();
			}
		);
	</script>
</c:if>