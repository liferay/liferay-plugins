<%--
/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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
List<MemberRequest> memberRequests = MemberRequestLocalServiceUtil.getReceiverStatusMemberRequest(themeDisplay.getUserId(), InviteMembersConstants.STATUS_PENDING, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
%>

<%
for (MemberRequest memberRequest : memberRequests) {
	Group curGroup = null;

	try {
		curGroup = GroupLocalServiceUtil.getGroup(memberRequest.getGroupId());
	}
	catch (Exception e) {
		MemberRequestLocalServiceUtil.deleteMemberRequest(memberRequest);

		continue;
	}

	String userFullName = PortalUtil.getUserName(memberRequest.getUserId(), memberRequest.getUserName());
	String userDisplayURL = StringPool.BLANK;
	String userPortaitURL = StringPool.BLANK;

	try {
		User user2 = UserLocalServiceUtil.getUser(memberRequest.getUserId());

		userDisplayURL = user2.getDisplayURL(themeDisplay);
		userPortaitURL = user2.getPortraitURL(themeDisplay);
	}
	catch (NoSuchUserException nsue) {
	}
%>

	<div class="notification-entry">
		<div class="thumbnail">
			<a href="<%= userDisplayURL %>">
				<img alt="<%= userFullName %>" src="<%= userPortaitURL %>" />
			</a>
		</div>

		<%
		if (Validator.isNotNull(userDisplayURL)) {
			userFullName = "<a href=\"" + userDisplayURL + "\">" + userFullName + "</a>";
		}
		%>

		<div class="notification-content">
			<div>
				<%= themeDisplay.translate("x-has-invited-you-to-join-x", new Object[] {userFullName, HtmlUtil.escape(curGroup.getDescriptiveName())}) %>
			</div>

			<div class="lfr-user-action">
				<portlet:actionURL name="updateMemberRequest" var="confirmURL">
					<portlet:param name="redirect" value="<%= currentURL %>" />
					<portlet:param name="memberRequestId" value="<%= String.valueOf(memberRequest.getMemberRequestId()) %>" />
					<portlet:param name="status" value="<%= String.valueOf(InviteMembersConstants.STATUS_ACCEPTED) %>" />
				</portlet:actionURL>

				<span class="lfr-user-action-item lfr-user-action-confirm">
					<a href="<%= confirmURL %>"><liferay-ui:message key="confirm" /></a>
				</span>

				<portlet:actionURL name="updateMemberRequest" var="ignoreURL">
					<portlet:param name="redirect" value="<%= currentURL %>" />
					<portlet:param name="memberRequestId" value="<%= String.valueOf(memberRequest.getMemberRequestId()) %>" />
					<portlet:param name="status" value="<%= String.valueOf(InviteMembersConstants.STATUS_DECLINED) %>" />
				</portlet:actionURL>

				<span class="lfr-user-action-item lfr-user-action-ignore">
					<a href="<%= ignoreURL %>"><liferay-ui:message key="ignore" /></a>
				</span>
			</div>
		</div>
	</div>

<%
}
%>