<%--
/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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
ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

User user2 = null;

boolean view = false;

if (row != null) {
	user2 = (User)row.getObject();
}
else {
	user2 = (User)request.getAttribute("view_user.jsp-user");

	view = true;
}
%>

<liferay-ui:icon-menu showExpanded="<%= view %>" showWhenSingleIcon="<%= view %>">
	<c:if test="<%= !view && (user.getUserId() != user2.getUserId()) %>">
		<portlet:renderURL var="viewURL">
			<portlet:param name="jspPage" value="/contacts_center/view_user.jsp" />
			<portlet:param name="redirect" value="<%= currentURL %>" />
			<portlet:param name="userId" value="<%= String.valueOf(user2.getUserId()) %>" />
		</portlet:renderURL>

		<liferay-ui:icon
			image="view"
			message="view-profile"
			url="<%= viewURL %>"
		/>
	</c:if>

	<%
	boolean viewRelationActions = true;

	if (SocialRelationLocalServiceUtil.hasRelation(user2.getUserId(), themeDisplay.getUserId(), SocialRelationConstants.TYPE_UNI_ENEMY)) {
		viewRelationActions = false;
	}
	else if (SocialRelationLocalServiceUtil.hasRelation(themeDisplay.getUserId(), user2.getUserId(), SocialRelationConstants.TYPE_UNI_ENEMY)) {
		viewRelationActions = false;
	}
	%>

	<c:if test="<%= viewRelationActions %>">
		<c:choose>
			<c:when test="<%= SocialRelationLocalServiceUtil.hasRelation(themeDisplay.getUserId(), user2.getUserId(), SocialRelationConstants.TYPE_BI_FRIEND) %>">
				<portlet:actionURL name="deleteSocialRelation" var="removeFriendURL">
					<portlet:param name="redirect" value="<%= currentURL %>" />
					<portlet:param name="userId" value="<%= String.valueOf(user2.getUserId()) %>" />
					<portlet:param name="type" value="<%= String.valueOf(SocialRelationConstants.TYPE_BI_FRIEND) %>" />
				</portlet:actionURL>

				<liferay-ui:icon
					image="../social/remove_friend"
					message="remove-as-friend"
					url="<%= removeFriendURL %>"
				/>
			</c:when>
			<c:when test="<%= SocialRequestLocalServiceUtil.hasRequest(themeDisplay.getUserId(), User.class.getName(), themeDisplay.getUserId(), SocialRelationConstants.TYPE_BI_FRIEND, user2.getUserId(), SocialRequestConstants.STATUS_PENDING) %>">
				<liferay-ui:icon
					cssClass="disabled"
					image="../social/friend"
					message="friend-requested"
				/>
			</c:when>
			<c:when test="<%= SocialRelationLocalServiceUtil.isRelatable(themeDisplay.getUserId(), user2.getUserId(), SocialRelationConstants.TYPE_BI_FRIEND) %>">
				<portlet:actionURL name="requestSocialRelation" var="addFriendURL">
					<portlet:param name="redirect" value="<%= currentURL %>" />
					<portlet:param name="userId" value="<%= String.valueOf(user2.getUserId()) %>" />
					<portlet:param name="type" value="<%= String.valueOf(SocialRelationConstants.TYPE_BI_FRIEND) %>" />
				</portlet:actionURL>

				<liferay-ui:icon
					image="../social/add_friend"
					message="add-as-friend"
					url="<%= addFriendURL %>"
				/>
			</c:when>
		</c:choose>

		<c:choose>
			<c:when test="<%= SocialRelationLocalServiceUtil.hasRelation(themeDisplay.getUserId(), user2.getUserId(), SocialRelationConstants.TYPE_BI_COWORKER) %>">
				<portlet:actionURL name="deleteSocialRelation" var="removeCoworkerURL">
					<portlet:param name="redirect" value="<%= currentURL %>" />
					<portlet:param name="userId" value="<%= String.valueOf(user2.getUserId()) %>" />
					<portlet:param name="type" value="<%= String.valueOf(SocialRelationConstants.TYPE_BI_COWORKER) %>" />
				</portlet:actionURL>

				<liferay-ui:icon
					image="../social/remove_coworker"
					message="remove-as-coworker"
					url="<%= removeCoworkerURL %>"
				/>
			</c:when>
			<c:when test="<%= SocialRequestLocalServiceUtil.hasRequest(themeDisplay.getUserId(), User.class.getName(), themeDisplay.getUserId(), SocialRelationConstants.TYPE_BI_COWORKER, user2.getUserId(), SocialRequestConstants.STATUS_PENDING) %>">
				<liferay-ui:icon
					cssClass="disabled"
					image="../social/coworker"
					message="coworker-requested"
				/>
			</c:when>
			<c:when test="<%= SocialRelationLocalServiceUtil.isRelatable(themeDisplay.getUserId(), user2.getUserId(), SocialRelationConstants.TYPE_BI_COWORKER) %>">
				<portlet:actionURL name="requestSocialRelation" var="addCoworkerURL">
					<portlet:param name="redirect" value="<%= currentURL %>" />
					<portlet:param name="userId" value="<%= String.valueOf(user2.getUserId()) %>" />
					<portlet:param name="type" value="<%= String.valueOf(SocialRelationConstants.TYPE_BI_COWORKER) %>" />
				</portlet:actionURL>

				<liferay-ui:icon
					image="../social/add_coworker"
					message="add-as-coworker"
					url="<%= addCoworkerURL %>"
				/>
			</c:when>
		</c:choose>

		<c:choose>
			<c:when test="<%= SocialRelationLocalServiceUtil.hasRelation(themeDisplay.getUserId(), user2.getUserId(), SocialRelationConstants.TYPE_UNI_FOLLOWER) %>">
				<portlet:actionURL name="deleteSocialRelation" var="unfollowURL">
					<portlet:param name="redirect" value="<%= currentURL %>" />
					<portlet:param name="userId" value="<%= String.valueOf(user2.getUserId()) %>" />
					<portlet:param name="type" value="<%= String.valueOf(SocialRelationConstants.TYPE_UNI_FOLLOWER) %>" />
				</portlet:actionURL>

				<liferay-ui:icon
					image="../social/unfollow"
					message="unfollow"
					url="<%= unfollowURL %>"
				/>
			</c:when>
			<c:when test="<%= SocialRelationLocalServiceUtil.isRelatable(themeDisplay.getUserId(), user2.getUserId(), SocialRelationConstants.TYPE_UNI_FOLLOWER) %>">
				<portlet:actionURL name="addSocialRelation" var="followURL">
					<portlet:param name="redirect" value="<%= currentURL %>" />
					<portlet:param name="userId" value="<%= String.valueOf(user2.getUserId()) %>" />
					<portlet:param name="type" value="<%= String.valueOf(SocialRelationConstants.TYPE_UNI_FOLLOWER) %>" />
				</portlet:actionURL>

				<liferay-ui:icon
					image="../social/follow"
					message="follow"
					url="<%= followURL %>"
				/>
			</c:when>
		</c:choose>
	</c:if>

	<c:choose>
		<c:when test="<%= SocialRelationLocalServiceUtil.hasRelation(themeDisplay.getUserId(), user2.getUserId(), SocialRelationConstants.TYPE_UNI_ENEMY) %>">
			<portlet:actionURL name="deleteSocialRelation" var="unblockURL">
				<portlet:param name="redirect" value="<%= currentURL %>" />
				<portlet:param name="userId" value="<%= String.valueOf(user2.getUserId()) %>" />
				<portlet:param name="type" value="<%= String.valueOf(SocialRelationConstants.TYPE_UNI_ENEMY) %>" />
			</portlet:actionURL>

			<liferay-ui:icon
				image="../social/unblock"
				message="unblock"
				url="<%= unblockURL %>"
			/>
		</c:when>
		<c:when test="<%= SocialRelationLocalServiceUtil.isRelatable(themeDisplay.getUserId(), user2.getUserId(), SocialRelationConstants.TYPE_UNI_ENEMY) %>">
			<portlet:actionURL name="addSocialRelation" var="blockURL">
				<portlet:param name="redirect" value="<%= currentURL %>" />
				<portlet:param name="userId" value="<%= String.valueOf(user2.getUserId()) %>" />
				<portlet:param name="type" value="<%= String.valueOf(SocialRelationConstants.TYPE_UNI_ENEMY) %>" />
			</portlet:actionURL>

			<liferay-ui:icon
				image="../social/block"
				message="block"
				url="<%= blockURL %>"
			/>
		</c:when>
	</c:choose>

	<portlet:resourceURL id="exportVCard" var="exportURL">
		<portlet:param name="userId" value="<%= String.valueOf(user2.getUserId()) %>" />
	</portlet:resourceURL>

	<liferay-ui:icon
		image="export"
		message="export-vcard"
		url="<%= exportURL %>"
	/>
</liferay-ui:icon-menu>