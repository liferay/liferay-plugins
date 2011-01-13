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
			<portlet:param name="type" value="<%= String.valueOf(SocialRelationConstants.TYPE_BI_FRIEND) %>" />
		</portlet:renderURL>

		<liferay-ui:icon
			image="view"
			message="view-profile"
			url="<%= viewURL %>"
		/>
	</c:if>

	<c:choose>
		<c:when test="<%= SocialRelationLocalServiceUtil.hasRelation(themeDisplay.getUserId(), user2.getUserId(), SocialRelationConstants.TYPE_BI_FRIEND) %>">
			<portlet:actionURL name="deleteSocialRelation" var="friendURL">
				<portlet:param name="redirect" value="<%= currentURL %>" />
				<portlet:param name="userId" value="<%= String.valueOf(user2.getUserId()) %>" />
				<portlet:param name="type" value="<%= String.valueOf(SocialRelationConstants.TYPE_BI_FRIEND) %>" />
			</portlet:actionURL>

			<liferay-ui:icon
				image="leave"
				message="remove-as-friend"
				url="<%= friendURL %>"
			/>
		</c:when>
		<c:when test="<%= SocialRequestLocalServiceUtil.hasRequest(themeDisplay.getUserId(), User.class.getName(), themeDisplay.getUserId(), SocialRelationConstants.TYPE_BI_FRIEND, user2.getUserId(), SocialRequestConstants.STATUS_PENDING) %>">
			<liferay-ui:icon
				cssClass="disabled"
				image="join"
				message="friend-requested"
			/>
		</c:when>
		<c:when test="<%= SocialRelationLocalServiceUtil.isRelatable(themeDisplay.getUserId(), user2.getUserId(), SocialRelationConstants.TYPE_BI_FRIEND) %>">
			<portlet:actionURL name="requestSocialRelation" var="friendURL">
				<portlet:param name="redirect" value="<%= currentURL %>" />
				<portlet:param name="userId" value="<%= String.valueOf(user2.getUserId()) %>" />
				<portlet:param name="type" value="<%= String.valueOf(SocialRelationConstants.TYPE_BI_FRIEND) %>" />
			</portlet:actionURL>

			<liferay-ui:icon
				image="join"
				message="add-as-friend"
				url="<%= friendURL %>"
			/>
		</c:when>
	</c:choose>

	<c:choose>
		<c:when test="<%= SocialRelationLocalServiceUtil.hasRelation(themeDisplay.getUserId(), user2.getUserId(), SocialRelationConstants.TYPE_BI_COWORKER) %>">
			<portlet:actionURL name="deleteSocialRelation" var="friendURL">
				<portlet:param name="redirect" value="<%= currentURL %>" />
				<portlet:param name="userId" value="<%= String.valueOf(user2.getUserId()) %>" />
				<portlet:param name="type" value="<%= String.valueOf(SocialRelationConstants.TYPE_BI_COWORKER) %>" />
			</portlet:actionURL>

			<liferay-ui:icon
				image="leave"
				message="remove-as-coworker"
				url="<%= friendURL %>"
			/>
		</c:when>
		<c:when test="<%= SocialRequestLocalServiceUtil.hasRequest(themeDisplay.getUserId(), User.class.getName(), themeDisplay.getUserId(), SocialRelationConstants.TYPE_BI_COWORKER, user2.getUserId(), SocialRequestConstants.STATUS_PENDING) %>">
			<liferay-ui:icon
				cssClass="disabled"
				image="join"
				message="coworker-requested"
			/>
		</c:when>
		<c:when test="<%= SocialRelationLocalServiceUtil.isRelatable(themeDisplay.getUserId(), user2.getUserId(), SocialRelationConstants.TYPE_BI_COWORKER) %>">
			<portlet:actionURL name="requestSocialRelation" var="friendURL">
				<portlet:param name="redirect" value="<%= currentURL %>" />
				<portlet:param name="userId" value="<%= String.valueOf(user2.getUserId()) %>" />
				<portlet:param name="type" value="<%= String.valueOf(SocialRelationConstants.TYPE_BI_COWORKER) %>" />
			</portlet:actionURL>

			<liferay-ui:icon
				image="join"
				message="add-as-coworker"
				url="<%= friendURL %>"
			/>
		</c:when>
	</c:choose>

	<c:choose>
		<c:when test="<%= SocialRelationLocalServiceUtil.hasRelation(themeDisplay.getUserId(), user2.getUserId(), SocialRelationConstants.TYPE_UNI_FOLLOWER) %>">
			<portlet:actionURL name="deleteSocialRelation" var="friendURL">
				<portlet:param name="redirect" value="<%= currentURL %>" />
				<portlet:param name="userId" value="<%= String.valueOf(user2.getUserId()) %>" />
				<portlet:param name="type" value="<%= String.valueOf(SocialRelationConstants.TYPE_UNI_FOLLOWER) %>" />
			</portlet:actionURL>

			<liferay-ui:icon
				image="leave"
				message="unfollow"
				url="<%= friendURL %>"
			/>
		</c:when>
		<c:when test="<%= SocialRelationLocalServiceUtil.isRelatable(themeDisplay.getUserId(), user2.getUserId(), SocialRelationConstants.TYPE_UNI_FOLLOWER) %>">
			<portlet:actionURL name="addSocialRelation" var="followURL">
				<portlet:param name="redirect" value="<%= currentURL %>" />
				<portlet:param name="userId" value="<%= String.valueOf(user2.getUserId()) %>" />
				<portlet:param name="type" value="<%= String.valueOf(SocialRelationConstants.TYPE_UNI_FOLLOWER) %>" />
			</portlet:actionURL>

			<liferay-ui:icon
				image="join"
				message="follow"
				url="<%= followURL %>"
			/>
		</c:when>
	</c:choose>
</liferay-ui:icon-menu>