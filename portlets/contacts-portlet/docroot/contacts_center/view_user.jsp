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
%>

<c:if test="<%= user2 != null %>">
	<div class="contacts-profile">

		<c:if test="<%= showSimpleUserInformation %>">
			<aui:layout cssClass="lfr-contact-grid-item">
				<c:if test="<%= showUsersIcon %>">
					<div class="lfr-contact-thumb">
						<a href="<%= user2.getDisplayURL(themeDisplay) %>"><img alt="<%= HtmlUtil.escape(user2.getFullName()) %>" src="<%= user2.getPortraitURL(themeDisplay) %>" /></a>
					</div>
				</c:if>


				<div class="<%= showUsersIcon ? StringPool.BLANK : "no-icon" %> lfr-contact-info">
					<div class="lfr-contact-name">
						<a href="<%= user2.getDisplayURL(themeDisplay) %>"><%= HtmlUtil.escape(user2.getFullName()) %></a>
					</div>

					<div class="lfr-contact-job-title">
						<%= HtmlUtil.escape(user2.getJobTitle()) %>
					</div>

					<div class="lfr-contact-extra">
						<%= HtmlUtil.escape(user2.getEmailAddress()) %>
					</div>
				</div>

				<div class="clear"></div>
			</aui:layout>
		</c:if>

		<c:if test="<%= showSocialActions %>">
			<aui:layout cssClass="social-relations">

				<%
				boolean connection = SocialRelationLocalServiceUtil.hasRelation(themeDisplay.getUserId(), user2.getUserId(), SocialRelationConstants.TYPE_BI_CONNECTION);
				boolean follower = SocialRelationLocalServiceUtil.hasRelation(user2.getUserId(), themeDisplay.getUserId(), SocialRelationConstants.TYPE_UNI_FOLLOWER);
				boolean following = SocialRelationLocalServiceUtil.hasRelation(themeDisplay.getUserId(), user2.getUserId(), SocialRelationConstants.TYPE_UNI_FOLLOWER);
				%>

				<c:if test="<%= connection || follower || following %>">
					<div class="lfr-asset-metadata">
						<c:if test="<%= connection %>">
							<span class="lfr-asset-icon lfr-asset-connection<%= (following || follower) ? StringPool.BLANK : " last" %>">
								<liferay-ui:message key="connection" />
							</span>
						</c:if>

						<c:if test="<%= following %>">
							<span class="lfr-asset-icon lfr-asset-following<%= follower ? StringPool.BLANK : " last" %>">
								<liferay-ui:message key="following" />
							</span>
						</c:if>

						<c:if test="<%= follower %>">
							<span class="lfr-asset-icon lfr-asset-follower last">
								<liferay-ui:message key="follower" />
							</span>
						</c:if>
					</div>
				</c:if>

				<aui:layout cssClass="contacts-action">
					<liferay-util:include page="/contacts_center/user_action.jsp" servletContext="<%= application %>" />
				</aui:layout>
			</aui:layout>
		</c:if>

		<c:if test="<%= showUsersInformation && UserPermissionUtil.contains(permissionChecker, user2.getUserId(), ActionKeys.VIEW) %>">
			<aui:layout cssClass="user-information">
				<div class="user-information-title">
					<liferay-ui:message key="about" />
				</div>

				<div class="lfr-user-info-container">
					<liferay-util:include page="/contacts_center/view_user_information.jsp" servletContext="<%= application %>" />
				</div>

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

					<div class="user-information-title">
						<liferay-ui:message key="<%= title %>" />
					</div>

					<div class="section">
						<div class="<%= cssClass %>">
							<liferay-util:include page="<%= extensionPath %>" servletContext="<%= extensionServletContext %>" />
						</div>
					</div>

				<%
				}
				%>
			</aui:layout>
		</c:if>

		<c:if test="<%= showUsersRecentActivity %>">
			<div class="user-information-title">
				<liferay-ui:message key="recent-activity" />
			</div>

			<liferay-ui:social-activities
				activities="<%= SocialActivityLocalServiceUtil.getUserActivities(user2.getUserId(), 0, 10) %>"
				feedEnabled="<%= false %>"
			/>
		</c:if>
	</div>
</c:if>