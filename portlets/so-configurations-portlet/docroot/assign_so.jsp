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
String tabs2 = ParamUtil.getString(request, "tabs2", "users");

String keywords = ParamUtil.getString(request, "keywords");
String searchFilter = ParamUtil.getString(request, "searchFilter", "available");

Role role = RoleLocalServiceUtil.getRole(themeDisplay.getCompanyId(), RoleConstants.SOCIAL_OFFICE_USER);

List<Group> roleGroups = GroupLocalServiceUtil.getRoleGroups(role.getRoleId());

int roleGroupsCuont = roleGroups.size();

int roleUsersCount = UserLocalServiceUtil.getRoleUsersCount(role.getRoleId());
%>

<c:choose>
	<c:when test="<%= (roleGroupsCuont == 0) && (roleUsersCount == 0) %>">
		<div class="portlet-msg-success">
			<liferay-ui:message key="thank-you-for-installing-social-office" />
		</div>
	</c:when>
	<c:otherwise>
		<div class="portlet-msg-info">
			<liferay-ui:message key="give-users-social-office-access" />
		</div>
	</c:otherwise>
</c:choose>

<liferay-portlet:renderURL var="portletURL">
	<portlet:param name="tabs2" value="<%= tabs2 %>" />
</liferay-portlet:renderURL>

<liferay-ui:tabs
	names="users,organizations,user-groups"
	param="tabs2"
	url="<%= portletURL.toString() %>"
/>

<aui:form action="<%= portletURL %>" method="post" name="fm">
	<aui:input name="addIds" type="hidden" />
	<aui:input name="removeIds" type="hidden" />

	<aui:input label="" name="keywords" size="30" value="<%= HtmlUtil.escape(keywords) %>" />

	<aui:button type="submit" value="search" />

	<div id="filterRadioButtons">
		<span class="filter-title"><liferay-ui:message key="view" />&#58;</span>

		<aui:input checked='<%= searchFilter.equals("available") %>' label="available" name="searchFilter" type="radio" value="available" />

		<aui:input checked='<%= searchFilter.equals("current") %>' label="current" name="searchFilter" type="radio" value="current" />
	</div>

	<c:choose>
		<c:when test='<%= tabs2.equals("organizations") %>'>
			<liferay-util:include page="/assign_so_organizations.jsp" servletContext="<%= application %>" />
		</c:when>
		<c:when test='<%= tabs2.equals("user-groups") %>'>
			<liferay-util:include page="/assign_so_user_groups.jsp" servletContext="<%= application %>" />
		</c:when>
		<c:when test='<%= tabs2.equals("users") %>'>
			<liferay-util:include page="/assign_so_users.jsp" servletContext="<%= application %>" />
		</c:when>
	</c:choose>
</aui:form>

<div class="so-welcome-setup">

	<%
	Group layoutGroup = layout.getGroup();
	%>

	<c:if test='<%= !layoutGroup.isControlPanel() && tabs2.equals("users") %>'>
		<div id="addAllUsers">
			<aui:input label="give-every-liferay-portal-user-access-to-social-office-can-be-configured-later" name="" type="checkbox" value="" />
		</div>
	</c:if>

	<aui:button-row>
		<aui:button onClick='<%= renderResponse.getNamespace() + "updateRole(false)" %>' value="save" />

		<c:if test="<%= !layoutGroup.isControlPanel() %>">
			<aui:button onClick='<%= renderResponse.getNamespace() + "updateRole(true)" %>' value="finish" />
		</c:if>
	</aui:button-row>
</div>

<aui:script>
	Liferay.provide(
		window,
		'<portlet:namespace />updateRole',
		function(finished) {
			var A = AUI();

			var addAllUsers = A.one('#addAllUsers input[type=checkbox]');

			if (addAllUsers && addAllUsers.get('checked')) {

				<%
				Group userGroup = user.getGroup();

				String dashboardURL = userGroup.getDisplayURL(themeDisplay, true);
				%>

				if (finished) {
					uri = '<portlet:actionURL name="addRoleAllUsers"><portlet:param name="redirect" value="<%= dashboardURL %>" /></portlet:actionURL>';
				}
				else {
					uri = '<portlet:actionURL name="addRoleAllUsers"><portlet:param name="redirect" value="<%= currentURL %>" /></portlet:actionURL>';
				}
			}
			else {
				document.<portlet:namespace />fm.<portlet:namespace />addIds.value = Liferay.Util.listCheckedExcept(document.<portlet:namespace />fm, '<portlet:namespace />allRowIds');
				document.<portlet:namespace />fm.<portlet:namespace />removeIds.value = Liferay.Util.listUncheckedExcept(document.<portlet:namespace />fm, '<portlet:namespace />allRowIds');

				if (<%= tabs2.equals("users") %>) {
					if (finished) {
						uri = '<portlet:actionURL name="updateUsersRole"><portlet:param name="redirect" value="<%= dashboardURL %>" /></portlet:actionURL>';
					}
					else {
						uri = '<portlet:actionURL name="updateUsersRole"><portlet:param name="redirect" value="<%= currentURL %>" /></portlet:actionURL>';
					}
				}
				else {
					if (finished) {
						uri = '<portlet:actionURL name="updateGroupsRole"><portlet:param name="redirect" value="<%= dashboardURL %>" /></portlet:actionURL>';
					}
					else {
						uri = '<portlet:actionURL name="updateGroupsRole"><portlet:param name="redirect" value="<%= currentURL %>" /></portlet:actionURL>';
					}
				}
			}

			submitForm(document.<portlet:namespace />fm, uri);
		},
		['liferay-util-list-fields']
	);
</aui:script>

<aui:script use="aui-base">
	var radioButtons = A.all('#filterRadioButtons input[type=radio]');

	radioButtons.on(
		'change',
		function(event) {
			submitForm(document.<portlet:namespace />fm);
		}
	);
</aui:script>