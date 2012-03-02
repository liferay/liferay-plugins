<%--
/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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
String redirect = ParamUtil.getString(request, "redirect");

String curSectionId = ParamUtil.getString(request, "curSectionId");

User selUser = themeDisplay.getUser();

Contact selContact = null;

if (selUser != null) {
	selContact = selUser.getContact();
}

PasswordPolicy passwordPolicy = null;

if (selUser == null) {
	passwordPolicy = PasswordPolicyLocalServiceUtil.getDefaultPasswordPolicy(company.getCompanyId());
}
else {
	passwordPolicy = selUser.getPasswordPolicy();
}

String groupIds = ParamUtil.getString(request, "groupsSearchContainerPrimaryKeys");

List<Group> groups = Collections.emptyList();

if (Validator.isNotNull(groupIds)) {
	long[] groupIdsArray = StringUtil.split(groupIds, 0L);

	groups = GroupLocalServiceUtil.getGroups(groupIdsArray);
}
else if (selUser != null) {
	groups = selUser.getGroups();
}

String organizationIds = ParamUtil.getString(request, "organizationsSearchContainerPrimaryKeys");

List<Organization> organizations = Collections.emptyList();

if (Validator.isNotNull(organizationIds)) {
	long[] organizationIdsArray = StringUtil.split(organizationIds, 0L);

	organizations = OrganizationLocalServiceUtil.getOrganizations(organizationIdsArray);
}
else {
	if (selUser != null) {
		organizations = selUser.getOrganizations();
	}
}

String roleIds = ParamUtil.getString(request, "rolesSearchContainerPrimaryKeys");

List<Role> roles = Collections.emptyList();

if (Validator.isNotNull(roleIds)) {
	long[] roleIdsArray = StringUtil.split(roleIds, 0L);

	roles = RoleLocalServiceUtil.getRoles(roleIdsArray);
}
else if (selUser != null) {
	roles = selUser.getRoles();
}

List<UserGroupRole> userGroupRoles = UsersAdminUtil.getUserGroupRoles(renderRequest);

List<UserGroupRole> communityRoles = new ArrayList<UserGroupRole>();
List<UserGroupRole> organizationRoles = new ArrayList<UserGroupRole>();

if (userGroupRoles.isEmpty() && (selUser != null)) {
	userGroupRoles = UserGroupRoleLocalServiceUtil.getUserGroupRoles(selUser.getUserId());
}

for (UserGroupRole userGroupRole : userGroupRoles) {
	int roleType = userGroupRole.getRole().getType();

	if (roleType == RoleConstants.TYPE_ORGANIZATION) {
		organizationRoles.add(userGroupRole);
	}
	else if (roleType == RoleConstants.TYPE_SITE) {
		communityRoles.add(userGroupRole);
	}
}

String userGroupIds = ParamUtil.getString(request, "userGroupsSearchContainerPrimaryKeys");

List<UserGroup> userGroups = Collections.emptyList();

if (Validator.isNotNull(userGroupIds)) {
	long[] userGroupIdsArray = StringUtil.split(userGroupIds, 0L);

	userGroups = UserGroupLocalServiceUtil.getUserGroups(userGroupIdsArray);
}
else if (selUser != null) {
	userGroups = selUser.getUserGroups();
}

List<Group> allGroups = new ArrayList<Group>();

allGroups.addAll(groups);
allGroups.addAll(GroupLocalServiceUtil.getOrganizationsGroups(organizations));
allGroups.addAll(GroupLocalServiceUtil.getOrganizationsRelatedGroups(organizations));
allGroups.addAll(GroupLocalServiceUtil.getUserGroupsGroups(userGroups));
allGroups.addAll(GroupLocalServiceUtil.getUserGroupsRelatedGroups(userGroups));

String[] mainSections = ContactsUtil.getPortalPropsValue("USERS_FORM_MY_ACCOUNT_MAIN");
String[] identificationSections = ContactsUtil.getPortalPropsValue("USERS_FORM_MY_ACCOUNT_IDENTIFICATION");
String[] miscellaneousSections = ContactsUtil.getPortalPropsValue("USERS_FORM_MY_ACCOUNT_MISCELLANEOUS");

String[][] categorySections = {mainSections, identificationSections, miscellaneousSections};

String[] allSections = new String[0];

for (String[] categorySection : categorySections) {
	allSections = ArrayUtil.append(allSections, categorySection);
}
%>

<liferay-util:buffer var="html">
	<liferay-portlet:actionURL portletName="<%= PortletKeys.USERS_ADMIN %>" var="editUserURL" windowState="<%= LiferayWindowState.NORMAL.toString() %>">
		<portlet:param name="struts_action" value="/users_admin/edit_user" />
	</liferay-portlet:actionURL>

	<%
	String taglibOnSubmit = "event.preventDefault(); " + renderResponse.getNamespace() + "saveForm();";
	%>

	<div id="<portlet:namespace />updateUserDialog">
		<aui:form action="<%= editUserURL %>" method="post" name="fm" onSubmit="<%= taglibOnSubmit %>">
			<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />
			<aui:input name="redirect" type="hidden"  value="<%= redirect %>" />
			<aui:input name="backURL" type="hidden" value="<%= redirect %>" />
			<aui:input name="p_u_i_d" type="hidden" value="<%= (selUser != null) ? selUser.getUserId() : 0 %>" />

			<%
			request.setAttribute("user.selContact", selContact);
			request.setAttribute("user.selUser", selUser);
			request.setAttribute("user.passwordPolicy", passwordPolicy);
			request.setAttribute("user.groups", groups);
			request.setAttribute("user.organizations", organizations);
			request.setAttribute("user.roles", roles);
			request.setAttribute("user.communityRoles", communityRoles);
			request.setAttribute("user.organizationRoles", organizationRoles);
			request.setAttribute("user.userGroups", userGroups);
			request.setAttribute("user.allGroups", allGroups);

			request.setAttribute("addresses.className", Contact.class.getName());
			request.setAttribute("emailAddresses.className", Contact.class.getName());
			request.setAttribute("phones.className", Contact.class.getName());
			request.setAttribute("websites.className", Contact.class.getName());

			if (selContact != null) {
				request.setAttribute("addresses.classPK", selContact.getContactId());
				request.setAttribute("emailAddresses.classPK", selContact.getContactId());
				request.setAttribute("phones.classPK", selContact.getContactId());
				request.setAttribute("websites.classPK", selContact.getContactId());
			}
			else {
				request.setAttribute("addresses.classPK", 0L);
				request.setAttribute("emailAddresses.classPK", 0L);
				request.setAttribute("phones.classPK", 0L);
				request.setAttribute("websites.classPK", 0L);
			}

			String[] categoryNames = {"user-information", "identification", "miscellaneous"};

			String jspPath = "/html/portlet/users_admin/user/";

			for (String section : allSections) {
				String sectionId = _getSectionId(section);
				String sectionJsp = jspPath + _getSectionJsp(section) + ".jsp";
			%>

				<div class="form-section <%= !curSectionId.equals(sectionId) ? "aui-helper-hidden" : StringPool.BLANK %>" id="<portlet:namespace /><%= sectionId %>">
					<liferay-util:include page="<%= sectionJsp %>" portletId="<%= PortletKeys.USERS_ADMIN %>" />
				</div>

			<%
			}
			%>

			<aui:button-row>
				<aui:button type="submit" />
			</aui:button-row>
		</aui:form>
	</div>

	<aui:script>
		Liferay.fire('formNavigator:reveal<portlet:namespace /><%= curSectionId %>');

		var <portlet:namespace />saveForm = function() {
			var A = AUI();

			var form = A.one('#<portlet:namespace />fm');

			Liferay.fire(
				'saveAutoFields',
				{
					form: form
				}
			);

			A.io.request(
				form.get('action'),
				{
					form: {
						id: form
					},
					after: {
						success: function(event, id, obj) {
							var redirect = document.<portlet:namespace />fm.<portlet:namespace />redirect.value;

							redirect = redirect.replace("p_p_state=exclusive", "p_p_state=normal");

							location.href = redirect;
						}
					}
				}
			);
		}
	</aui:script>
</liferay-util:buffer>

<%= StringUtil.replace(html, renderResponse.getNamespace(), "_" + PortletKeys.USERS_ADMIN + "_") %>

<%!
private String _getSectionId(String name) {
	return TextFormatter.format(name, TextFormatter.M);
}

private String _getSectionJsp(String name) {
	return TextFormatter.format(name, TextFormatter.N);
}
%>