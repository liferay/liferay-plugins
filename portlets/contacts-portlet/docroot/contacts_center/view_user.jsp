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
String backURL = ParamUtil.getString(request, "backURL");

long userId = ParamUtil.getLong(request, "userId");

User user2 = UserLocalServiceUtil.getUser(userId);

user2 = user2.toEscapedModel();

request.setAttribute("view_user.jsp-viewUser", Boolean.TRUE.toString());
%>

<liferay-util:include page="/contacts_center/top_links.jsp" portletId="<%= portletDisplay.getId() %>" />

<liferay-ui:header
	backURL="<%= backURL.toString() %>"
	title="<%= user2.getFullName() %>"
/>

<aui:layout>
	<aui:column columnWidth="<%= 75 %>" cssClass="lfr-asset-column lfr-asset-column-details" first="<%= true %>">

		<%
		boolean follower = SocialRelationLocalServiceUtil.hasRelation(user2.getUserId(), themeDisplay.getUserId(), SocialRelationConstants.TYPE_UNI_FOLLOWER);
		boolean following = SocialRelationLocalServiceUtil.hasRelation(themeDisplay.getUserId(), user2.getUserId(), SocialRelationConstants.TYPE_UNI_FOLLOWER);
		boolean friend = SocialRelationLocalServiceUtil.hasRelation(themeDisplay.getUserId(), user2.getUserId(), SocialRelationConstants.TYPE_BI_FRIEND);
		boolean coworker = SocialRelationLocalServiceUtil.hasRelation(themeDisplay.getUserId(), user2.getUserId(), SocialRelationConstants.TYPE_BI_COWORKER);
		%>

		<div class="lfr-asset-metadata">
			<c:if test="<%= friend %>">
				<div class="lfr-asset-icon lfr-asset-friend<%= (coworker || following || follower) ? StringPool.BLANK : " last" %>">
					<liferay-ui:message key="friend" />
				</div>
			</c:if>

			<c:if test="<%= coworker %>">
				<div class="lfr-asset-icon lfr-asset-friend<%= (following || follower) ? StringPool.BLANK : " last" %>">
					<liferay-ui:message key="co-worker" />
				</div>
			</c:if>

			<c:if test="<%= following %>">
				<div class="lfr-asset-icon lfr-asset-friend<%= follower ? StringPool.BLANK : " last" %>">
					<liferay-ui:message key="following" />
				</div>
			</c:if>

			<c:if test="<%= follower %>">
				<div class="lfr-asset-icon lfr-asset-friend last">
					<liferay-ui:message key="follower" />
				</div>
			</c:if>
		</div>

		<liferay-ui:panel-container extended="<%= false %>" persistState="<%= true %>">
			<liferay-ui:panel collapsible="<%= true %>" extended="<%= true %>" persistState="<%= true %>" title="information">
			</liferay-ui:panel>
			<liferay-ui:panel collapsible="<%= true %>" extended="<%= true %>" persistState="<%= true %>" title="recent-activity">
				<liferay-ui:social-activities
					activities="<%= SocialActivityLocalServiceUtil.getUserActivities(user2.getUserId(), 0, delta) %>"
					feedEnabled="<%= false %>"
				/>
			</liferay-ui:panel>
		</liferay-ui:panel-container>
	</aui:column>

	<aui:column columnWidth="<%= 25 %>" cssClass="lfr-asset-column lfr-asset-column-actions" last="<%= true %>">
		<div class="lfr-asset-summary">
			<img alt="<%= user2.getFullName() %>" class="avatar" src="<%= user2.getPortraitURL(themeDisplay) %>" />

			<div class="lfr-asset-name">
				<h4><%= user2.getFullName() %></h4>
			</div>
		</div>

		<%
		request.setAttribute("view_user.jsp-user", user2);

		request.removeAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);
		%>

		<liferay-util:include page="/contacts_center/user_action.jsp" portletId="<%= portletDisplay.getId() %>" />
	</aui:column>
</aui:layout>