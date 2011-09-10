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

User user2 = null;

if (userId > 0) {
	user2 = UserLocalServiceUtil.getUser(userId);
}
else {
	user2 = (User)request.getAttribute(WebKeys.CONTACTS_USER);
}

user2 = user2.toEscapedModel();

request.setAttribute("view_user.jsp-user", user2);

request.setAttribute("view_user.jsp-viewUser", Boolean.TRUE.toString());
%>

<c:if test="<%= portletName.equals(PortletKeys.CONTACTS_CENTER) %>">
	<liferay-util:include page="/contacts_center/top_links.jsp" servletContext="<%= application %>" />
</c:if>

<liferay-ui:header
	backURL="<%= backURL.toString() %>"
	title="<%= user2.getFullName() %>"
/>

<aui:layout>
	<aui:column columnWidth="<%= 75 %>" cssClass="lfr-asset-column lfr-asset-column-details" first="<%= true %>">
		<div class="lfr-asset-data">
			<c:if test="<%= Validator.isNotNull(user2.getJobTitle()) %>">
				<div class="lfr-user-data-name">
					<%= user2.getJobTitle() %>
				</div>
			</c:if>

			<div class="lfr-user-data-email">
				<a href="mailto:<%= user2.getEmailAddress() %>"><%= user2.getEmailAddress() %></a>
			</div>
		</div>

		<%
		boolean coworker = SocialRelationLocalServiceUtil.hasRelation(themeDisplay.getUserId(), user2.getUserId(), SocialRelationConstants.TYPE_BI_COWORKER);
		boolean follower = SocialRelationLocalServiceUtil.hasRelation(user2.getUserId(), themeDisplay.getUserId(), SocialRelationConstants.TYPE_UNI_FOLLOWER);
		boolean following = SocialRelationLocalServiceUtil.hasRelation(themeDisplay.getUserId(), user2.getUserId(), SocialRelationConstants.TYPE_UNI_FOLLOWER);
		boolean friend = SocialRelationLocalServiceUtil.hasRelation(themeDisplay.getUserId(), user2.getUserId(), SocialRelationConstants.TYPE_BI_FRIEND);
		%>

		<c:if test="<%= coworker || follower || following || friend %>">
			<div class="lfr-asset-metadata">
				<c:if test="<%= friend %>">
					<div class="lfr-asset-icon lfr-asset-friend<%= (coworker || following || follower) ? StringPool.BLANK : " last" %>">
						<liferay-ui:message key="friend" />
					</div>
				</c:if>

				<c:if test="<%= coworker %>">
					<div class="lfr-asset-icon lfr-asset-coworker<%= (following || follower) ? StringPool.BLANK : " last" %>">
						<liferay-ui:message key="coworker" />
					</div>
				</c:if>

				<c:if test="<%= following %>">
					<div class="lfr-asset-icon lfr-asset-following<%= follower ? StringPool.BLANK : " last" %>">
						<liferay-ui:message key="following" />
					</div>
				</c:if>

				<c:if test="<%= follower %>">
					<div class="lfr-asset-icon lfr-asset-follower last">
						<liferay-ui:message key="follower" />
					</div>
				</c:if>
			</div>
		</c:if>

		<liferay-ui:panel-container extended="<%= false %>" id="contactsCenterUserPanelContainer" persistState="<%= true %>">
			<c:if test="<%= showUsersInformation && UserPermissionUtil.contains(permissionChecker, user2.getUserId(), ActionKeys.VIEW) %>">
				<liferay-ui:panel collapsible="<%= true %>" extended="<%= true %>" id="contactsCenterUserInformationPanel" persistState="<%= true %>" title="information">
					<div class="lfr-user-info-container">
						<liferay-util:include page="/contacts_center/view_user_information.jsp" servletContext="<%= application %>" />
					</div>
				</liferay-ui:panel>

				<%
				Map<String, String> extensions = ContactsExtensionsUtil.getExtensions();

				Set<String> servletContextNames = extensions.keySet();

				for (String servletContextName : servletContextNames) {
					String extensionPath = extensions.get(servletContextName);

					ServletContext extensionServletContext = ServletContextPool.get(servletContextName);

					String title = extensionPath.substring(extensionPath.lastIndexOf(StringPool.SLASH) + 1, extensionPath.lastIndexOf(StringPool.PERIOD));

					title = title.replace(CharPool.UNDERLINE, CharPool.DASH);

					String cssClass = "lfr-" + title + "-container";
				%>

					<liferay-ui:panel collapsible="<%= true %>" extended="<%= true %>" id='<%= "contactsCenter" + title + "Panel" %>' persistState="<%= true %>" title="<%= title %>">
						<div class="<%= cssClass %>">
							<liferay-util:include page="<%= extensionPath %>" servletContext="<%= extensionServletContext %>" />
						</div>
					</liferay-ui:panel>

				<%
				}
				%>

			</c:if>

			<c:if test="<%= showUsersRecentActivity %>">
				<liferay-ui:panel collapsible="<%= true %>" extended="<%= true %>" id="contactsCenterUserRecentActivityPanel" persistState="<%= true %>" title="recent-activity">
					<liferay-ui:social-activities
						activities="<%= SocialActivityLocalServiceUtil.getUserActivities(user2.getUserId(), 0, 10) %>"
						feedEnabled="<%= false %>"
					/>
				</liferay-ui:panel>
			</c:if>
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
		request.removeAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);
		%>

		<liferay-util:include page="/contacts_center/user_action.jsp" servletContext="<%= application %>" />
	</aui:column>
</aui:layout>