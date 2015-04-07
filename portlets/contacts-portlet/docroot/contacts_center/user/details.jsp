<%--
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
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
User selUser = (User)request.getAttribute("user.selUser");
%>

<aui:model-context bean="<%= selUser %>" model="<%= User.class %>" />

<h3><liferay-ui:message key="details" /></h3>

<aui:fieldset column="<%= true %>" cssClass="w50">
	<aui:input autocapitalize="off" autocorrect="off" disabled='<%= !UsersAdminUtil.hasUpdateFieldPermission(permissionChecker, user, selUser, "screenName") %>' name="screenName" type="text" />

	<aui:input bean="<%= user %>" disabled='<%= !UsersAdminUtil.hasUpdateFieldPermission(permissionChecker, user, selUser, "emailAddress") %>' model="<%= User.class %>" name="emailAddress" type="email">
		<c:if test="<%= PrefsPropsUtil.getBoolean(company.getCompanyId(), PropsKeys.USERS_EMAIL_ADDRESS_REQUIRED) %>">
			<aui:validator name="required" />
		</c:if>
	</aui:input>

	<aui:input disabled='<%= !UsersAdminUtil.hasUpdateFieldPermission(permissionChecker, user, selUser, "firstName") %>' name="firstName" />

	<aui:input disabled='<%= !UsersAdminUtil.hasUpdateFieldPermission(permissionChecker, user, selUser, "middleName") %>' name="middleName" />

	<aui:input disabled='<%= !UsersAdminUtil.hasUpdateFieldPermission(permissionChecker, user, selUser, "lastName") %>' name="lastName" />

	<aui:input disabled='<%= !UsersAdminUtil.hasUpdateFieldPermission(permissionChecker, user, selUser, "jobTitle") %>' name="jobTitle" />
</aui:fieldset>

<aui:fieldset column="<%= true %>" cssClass="w50">
	<div class="user-profile-image" id="<portlet:namespace />userProfileImage">
		<c:if test="<%= selUser != null %>">
			<c:choose>
				<c:when test='<%= UsersAdminUtil.hasUpdateFieldPermission(permissionChecker, user, selUser, "portrait") %>'>

					<%
					Group controlPanelGroup = GroupLocalServiceUtil.getGroup(themeDisplay.getCompanyId(), GroupConstants.CONTROL_PANEL);

					long controlPanelPlid = LayoutLocalServiceUtil.getDefaultPlid(controlPanelGroup.getGroupId(), true);
					%>

					<liferay-portlet:renderURL plid="<%= controlPanelPlid %>" portletName="<%= PortletKeys.MY_ACCOUNT %>" refererPlid="<%= plid %>" var="editUserPortraitURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
						<portlet:param name="struts_action" value="/my_account/edit_user_portrait" />
						<portlet:param name="redirect" value="<%= currentURL %>" />
						<portlet:param name="p_u_i_d" value="<%= String.valueOf(selUser.getUserId()) %>" />
						<portlet:param name="portrait_id" value="<%= String.valueOf(selUser.getPortraitId()) %>" />
					</liferay-portlet:renderURL>

					<liferay-ui:logo-selector
						currentLogoURL="<%= selUser.getPortraitURL(themeDisplay) %>"
						defaultLogoURL="<%= UserConstants.getPortraitURL(themeDisplay.getPathImage(), selUser.isMale(), 0) %>"
						defaultLogo="<%= selUser.getPortraitId() == 0 %>"
						logoDisplaySelector=".user-logo"
						showBackground="<%= false %>"
					/>
				</c:when>
				<c:otherwise>
					<img alt="<liferay-ui:message escapeAttribute="<%= true %>" key="portrait" />" src="<%= selUser.getPortraitURL(themeDisplay) %>" />
				</c:otherwise>
			</c:choose>
		</c:if>
	</div>
</aui:fieldset>

<aui:script use="aui-base">
	window['<%= PortalUtil.getPortletNamespace(PortletKeys.MY_ACCOUNT) %>changeLogo'] = function(logoURL) {
		var avatarDialog = A.one('#<portlet:namespace />userProfileImage .avatar');

		if (avatarDialog) {
			avatarDialog.attr('src', logoURL);
		}

		var avatarSidebar = A.one('#so-sidebar .profile-image img');

		if (avatarSidebar) {
			avatarSidebar.attr('src', logoURL);
		}

		var avatarDockbar = A.one('.user-fullname.user-portrait img');

		if (avatarDockbar) {
			avatarDockbar.attr('src', logoURL);
		}
	}
</aui:script>