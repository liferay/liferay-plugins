<%--
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
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

<%@ include file="/html/portlet/portal_settings/init.jsp" %>

<%
String redirect = ParamUtil.getString(request, "redirect");
String backURL = ParamUtil.getString(request, "backURL", redirect);

long ldapServerId = ParamUtil.getLong(request, "ldapServerId", 0);

String postfix = LDAPSettingsUtil.getPropertyPostfix(ldapServerId);

String ldapServerName = PrefsPropsUtil.getString(company.getCompanyId(), "ldap.server.name" + postfix, StringPool.BLANK);
String ldapBaseProviderUrl = PrefsPropsUtil.getString(company.getCompanyId(), PropsKeys.LDAP_BASE_PROVIDER_URL + postfix);
String ldapBaseDN = PrefsPropsUtil.getString(company.getCompanyId(), PropsKeys.LDAP_BASE_DN + postfix);
String ldapSecurityPrincipal = PrefsPropsUtil.getString(company.getCompanyId(), PropsKeys.LDAP_SECURITY_PRINCIPAL + postfix);

String ldapSecurityCredentials = PrefsPropsUtil.getString(company.getCompanyId(), PropsKeys.LDAP_SECURITY_CREDENTIALS + postfix);

if (Validator.isNotNull(ldapSecurityCredentials)) {
	ldapSecurityCredentials = Portal.TEMP_OBFUSCATION_VALUE;
}

String ldapAuthSearchFilter = PrefsPropsUtil.getString(company.getCompanyId(), PropsKeys.LDAP_AUTH_SEARCH_FILTER + postfix);
String ldapImportUserSearchFilter = PrefsPropsUtil.getString(company.getCompanyId(), PropsKeys.LDAP_IMPORT_USER_SEARCH_FILTER + postfix);
String ldapImportGroupSearchFilter = PrefsPropsUtil.getString(company.getCompanyId(), PropsKeys.LDAP_IMPORT_GROUP_SEARCH_FILTER + postfix);
String ldapUsersDN = PrefsPropsUtil.getString(company.getCompanyId(), PropsKeys.LDAP_USERS_DN + postfix);
String ldapUserDefaultObjectClasses = PrefsPropsUtil.getString(company.getCompanyId(), PropsKeys.LDAP_USER_DEFAULT_OBJECT_CLASSES + postfix);
String ldapGroupsDN = PrefsPropsUtil.getString(company.getCompanyId(), PropsKeys.LDAP_GROUPS_DN + postfix);
String ldapGroupDefaultObjectClasses = PrefsPropsUtil.getString(company.getCompanyId(), PropsKeys.LDAP_GROUP_DEFAULT_OBJECT_CLASSES + postfix);

String ldapUserMappings = PrefsPropsUtil.getString(company.getCompanyId(), PropsKeys.LDAP_USER_MAPPINGS + postfix);

String[] userMappingArray = new String[0];

if (ldapUserMappings != null) {
	userMappingArray = ldapUserMappings.split("\n");
}

String userMappingEmailAddress = StringPool.BLANK;
String userMappingFirstName = StringPool.BLANK;
String userMappingFullName = StringPool.BLANK;
String userMappingGroup = StringPool.BLANK;
String userMappingJobTitle = StringPool.BLANK;
String userMappingLastName = StringPool.BLANK;
String userMappingMiddleName = StringPool.BLANK;
String userMappingPassword = StringPool.BLANK;
String userMappingPortrait= StringPool.BLANK;
String userMappingScreenName = StringPool.BLANK;
String userMappingStatus = StringPool.BLANK;
String userMappingUuid = StringPool.BLANK;

for (int i = 0 ; i < userMappingArray.length ; i++) {
	if (!userMappingArray[i].contains("=")) {
		continue;
	}

	String mapping[] = userMappingArray[i].split("=");

	if (mapping.length != 2) {
		continue;
	}

	if (mapping[0].equals("emailAddress")) {
		userMappingEmailAddress = mapping[1];
	}
	else if (mapping[0].equals("firstName")) {
		userMappingFirstName = mapping[1];
	}
	else if (mapping[0].equals("fullName")) {
		userMappingFullName = mapping[1];
	}
	else if (mapping[0].equals("group")) {
		userMappingGroup = mapping[1];
	}
	else if (mapping[0].equals("jobTitle")) {
		userMappingJobTitle = mapping[1];
	}
	else if (mapping[0].equals("lastName")) {
		userMappingLastName = mapping[1];
	}
	else if (mapping[0].equals("middleName")) {
		userMappingMiddleName = mapping[1];
	}
	else if (mapping[0].equals("password")) {
		userMappingPassword = mapping[1];
	}
	else if (mapping[0].equals("portrait")) {
		userMappingPortrait = mapping[1];
	}
	else if (mapping[0].equals("screenName")) {
		userMappingScreenName = mapping[1];
	}
	else if (mapping[0].equals("status")) {
		userMappingStatus = mapping[1];
	}
	else if (mapping[0].equals("uuid")) {
		userMappingUuid = mapping[1];
	}

	mapping[1] = "";
}

String ldapGroupMappings = PrefsPropsUtil.getString(company.getCompanyId(), PropsKeys.LDAP_GROUP_MAPPINGS + postfix);

String[] groupMappingArray = new String[0];

if (ldapGroupMappings != null) {
	groupMappingArray = ldapGroupMappings.split("\n");
}

String groupMappingDescription = StringPool.BLANK;
String groupMappingGroupName = StringPool.BLANK;
String groupMappingUser = StringPool.BLANK;

for (int i = 0 ; i < groupMappingArray.length ; i++) {
	if (!groupMappingArray[i].contains("=")) {
		continue;
	}

	String mapping[] = groupMappingArray[i].split("=");

	if (mapping.length != 2) {
		continue;
	}

	if (mapping[0].equals("description")) {
		groupMappingDescription = mapping[1];
	}
	else if (mapping[0].equals("groupName")) {
		groupMappingGroupName = mapping[1];
	}
	else if (mapping[0].equals("user")) {
		groupMappingUser = mapping[1];
	}
}
%>

<liferay-ui:header
	backURL="<%= backURL %>"
	title='<%= (ldapServerId == 0) ? "add-ldap-server" : "edit-ldap-server" %>'
/>

<portlet:actionURL var="editLDAPServerURL">
	<portlet:param name="struts_action" value="/portal_settings/edit_ldap_server" />
</portlet:actionURL>

<aui:form action="<%= editLDAPServerURL %>" method="post" name="fm" onSubmit='<%= "event.preventDefault(); " + renderResponse.getNamespace() + "saveEntry(false);" %>'>
	<liferay-ui:error exception="<%= DuplicateLDAPServerNameException.class %>" message="please-enter-a-unique-ldap-server-name" />
	<liferay-ui:error exception="<%= LDAPFilterException.class %>" message="please-enter-a-valid-ldap-search-filter" />
	<liferay-ui:error exception="<%= LDAPServerNameException.class %>" message="please-enter-a-valid-ldap-server-name" />

	<aui:input name="<%= Constants.CMD %>" type="hidden" />
	<aui:input name="redirect" type="hidden" value="<%= redirect %>" />
	<aui:input name="ldapServerId" type="hidden" value="<%= ldapServerId %>" />

	<liferay-ui:error key="ldapAuthentication" message="failed-to-bind-to-the-ldap-server-with-given-values" />

	<aui:fieldset>
		<aui:input cssClass="lfr-input-text-container" label="server-name" name='<%= "settings--ldap.server.name" + postfix + "--" %>' type="text" value="<%= ldapServerName %>" />
	</aui:fieldset>

	<h3><liferay-ui:message key="default-values" /></h3>

	<aui:fieldset>
		<aui:field-wrapper>
			<aui:input label="Apache Directory Server" name="defaultLdap" type="radio" value="apache" />
			<aui:input label="Fedora Directory Server" name="defaultLdap" type="radio" value="fedora" />
			<aui:input label="Microsoft Active Directory Server" name="defaultLdap" type="radio" value="microsoft" />
			<aui:input label="Novell eDirectory" name="defaultLdap" type="radio" value="novell" />
			<aui:input label="OpenLDAP" name="defaultLdap" type="radio" value="open" />
			<aui:input label="other-directory-server" name="defaultLdap" type="radio" value="other" />
		</aui:field-wrapper>

		<aui:button-row>
			<aui:button onClick='<%= renderResponse.getNamespace() + "updateDefaultLdap();" %>' value="reset-values" />
		</aui:button-row>
	</aui:fieldset>

	<h3><liferay-ui:message key="connection" /></h3>

	<aui:fieldset>
		<aui:input cssClass="lfr-input-text-container" helpMessage="the-ldap-url-format-is" label="base-provider-url" name='<%= "settings--" + PropsKeys.LDAP_BASE_PROVIDER_URL + postfix + "--" %>' type="text" value="<%= ldapBaseProviderUrl %>" />

		<aui:input cssClass="lfr-input-text-container" helpMessage="the-ldap-url-format-is" label="base-dn" name='<%= "settings--" + PropsKeys.LDAP_BASE_DN + postfix + "--" %>' type="text" value="<%= ldapBaseDN %>" />

		<aui:input cssClass="lfr-input-text-container" label="principal" name='<%= "settings--" + PropsKeys.LDAP_SECURITY_PRINCIPAL + postfix + "--" %>' type="text" value="<%= ldapSecurityPrincipal %>" />

		<aui:input cssClass="lfr-input-text-container" label="credentials" name='<%= "settings--" + PropsKeys.LDAP_SECURITY_CREDENTIALS + postfix + "--" %>' type="password" value="<%= ldapSecurityCredentials %>" />

		<aui:button-row>

			<%
			String taglibOnClick = renderResponse.getNamespace() + "testSettings('ldapConnection');";
			%>

			<aui:button onClick="<%= taglibOnClick %>" value="test-ldap-connection" />
		</aui:button-row>
	</aui:fieldset>

	<h3><liferay-ui:message key="users" /></h3>

	<aui:fieldset>
		<aui:input cssClass="lfr-input-text-container" helpMessage="enter-the-search-filter-that-will-be-used-to-test-the-validity-of-a-user" label="authentication-search-filter" name='<%= "settings--" + PropsKeys.LDAP_AUTH_SEARCH_FILTER + postfix + "--" %>' type="text" value="<%= ldapAuthSearchFilter %>" />

		<aui:input cssClass="lfr-input-text-container" label="import-search-filter" name='<%= "settings--" + PropsKeys.LDAP_IMPORT_USER_SEARCH_FILTER + postfix + "--" %>' type="text" value="<%= ldapImportUserSearchFilter %>" />

		<h4><liferay-ui:message key="user-mapping" /></h4>

		<aui:input cssClass="lfr-input-text-container" label="uuid" name="userMappingUuid" type="text" value="<%= userMappingUuid %>" />

		<aui:input cssClass="lfr-input-text-container" label="screen-name" name="userMappingScreenName" type="text" value="<%= userMappingScreenName %>" />

		<aui:input cssClass="lfr-input-text-container" label="email-address" name="userMappingEmailAddress" type="text" value="<%= userMappingEmailAddress %>" />

		<aui:input cssClass="lfr-input-text-container" label="password" name="userMappingPassword" type="text" value="<%= userMappingPassword %>" />

		<%@ include file="/html/portlet/portal_settings/edit_ldap_server_user_name.jspf" %>

		<aui:input cssClass="lfr-input-text-container" label="job-title" name="userMappingJobTitle" type="text" value="<%= userMappingJobTitle %>" />

		<aui:input cssClass="lfr-input-text-container" label="status" name="userMappingStatus" type="text" value="<%= userMappingStatus %>" />

		<aui:input cssClass="lfr-input-text-container" label="group" name="userMappingGroup" type="text" value="<%= userMappingGroup %>" />

		<aui:input cssClass="lfr-input-text-container" label="portrait" name="userMappingPortrait" type="text" value="<%= userMappingPortrait %>" />

		<aui:input name='<%= "settings--" + PropsKeys.LDAP_USER_MAPPINGS + postfix + "--" %>' type="hidden" />

		<aui:input name='<%= "settings--" + PropsKeys.LDAP_USER_CUSTOM_MAPPINGS + postfix + "--" %>' type="hidden" value="<%= PrefsPropsUtil.getString(company.getCompanyId(), PropsKeys.LDAP_USER_CUSTOM_MAPPINGS + postfix, PropsUtil.get(PropsKeys.LDAP_USER_CUSTOM_MAPPINGS)) %>" />

		<aui:input name='<%= "settings--" + PropsKeys.LDAP_CONTACT_MAPPINGS + postfix + "--" %>' type="hidden" value="<%= PrefsPropsUtil.getString(company.getCompanyId(), PropsKeys.LDAP_CONTACT_MAPPINGS + postfix, PropsUtil.get(PropsKeys.LDAP_CONTACT_MAPPINGS)) %>" />

		<aui:input name='<%= "settings--" + PropsKeys.LDAP_CONTACT_CUSTOM_MAPPINGS + postfix + "--" %>' type="hidden" value="<%= PrefsPropsUtil.getString(company.getCompanyId(), PropsKeys.LDAP_CONTACT_CUSTOM_MAPPINGS + postfix, PropsUtil.get(PropsKeys.LDAP_CONTACT_CUSTOM_MAPPINGS)) %>" />

		<aui:button-row>

			<%
			String taglibOnClick = renderResponse.getNamespace() + "testSettings('ldapUsers');";
			%>

			<aui:button onClick="<%= taglibOnClick %>" value="test-ldap-users" />
		</aui:button-row>
	</aui:fieldset>

	<h3><liferay-ui:message key="groups" /></h3>

	<aui:fieldset>
		<aui:input cssClass="lfr-input-text-container" label="import-search-filter" name='<%= "settings--" + PropsKeys.LDAP_IMPORT_GROUP_SEARCH_FILTER + postfix + "--" %>' type="text" value="<%= ldapImportGroupSearchFilter %>" />

		<h4><liferay-ui:message key="group-mapping" /></h4>

		<aui:input cssClass="lfr-input-text-container" label="group-name" name="groupMappingGroupName" type="text" value="<%= groupMappingGroupName %>" />

		<aui:input cssClass="lfr-input-text-container" label="description" name="groupMappingDescription" type="text" value="<%= groupMappingDescription %>" />

		<aui:input cssClass="lfr-input-text-container" label="user" name="groupMappingUser" type="text" value="<%= groupMappingUser %>" />

		<aui:input name='<%= "settings--" + PropsKeys.LDAP_GROUP_MAPPINGS + postfix + "--" %>' type="hidden" />

		<aui:button-row>

			<%
			String taglibOnClick = renderResponse.getNamespace() + "testSettings('ldapGroups');";
			%>

			<aui:button onClick="<%= taglibOnClick %>" value="test-ldap-groups" />
		</aui:button-row>
	</aui:fieldset>

	<h3><liferay-ui:message key="export" /></h3>

	<aui:fieldset>
		<aui:input cssClass="lfr-input-text-container" label="users-dn" name='<%= "settings--" + PropsKeys.LDAP_USERS_DN + postfix + "--" %>' type="text" value="<%= ldapUsersDN %>" />

		<aui:input cssClass="lfr-input-text-container" label="user-default-object-classes" name='<%= "settings--" + PropsKeys.LDAP_USER_DEFAULT_OBJECT_CLASSES + postfix + "--" %>' type="text" value="<%= ldapUserDefaultObjectClasses %>" />

		<aui:input cssClass="lfr-input-text-container" label="groups-dn" name='<%= "settings--" + PropsKeys.LDAP_GROUPS_DN + postfix + "--" %>' type="text" value="<%= ldapGroupsDN %>" />

		<aui:input cssClass="lfr-input-text-container" label="group-default-object-classes" name='<%= "settings--" + PropsKeys.LDAP_GROUP_DEFAULT_OBJECT_CLASSES + postfix + "--" %>' type="text" value="<%= ldapGroupDefaultObjectClasses %>" />
	</aui:fieldset>

	<aui:button-row>

		<%
		String taglibOnClick = renderResponse.getNamespace() + "saveLdap();";
		%>

		<aui:button name="saveButton" onClick="<%= taglibOnClick %>" value="save" />

		<aui:button href="<%= redirect %>" name="cancelButton" type="cancel" />
	</aui:button-row>
</aui:form>

<aui:script>
	function <portlet:namespace />saveLdap() {
		var userMappingFields = ['emailAddress', 'firstName', 'fullName', 'group', 'jobTitle', 'lastName', 'middleName', 'password', 'portrait', 'screenName', 'status', 'uuid'];
		var userMappingFieldValues = ['userMappingEmailAddress', 'userMappingFirstName', 'userMappingFullName', 'userMappingGroup', 'userMappingJobTitle', 'userMappingLastName', 'userMappingMiddleName', 'userMappingPassword', 'userMappingPortrait', 'userMappingScreenName', 'userMappingStatus', 'userMappingUuid'];
		var userMappingInput = document.<portlet:namespace />fm['<portlet:namespace />settings--<%= PropsKeys.LDAP_USER_MAPPINGS + postfix %>--'];

		userMappingInput.value = '';

		for (var i = 0; i < userMappingFields.length; i++) {
			var userMappingField = userMappingFields[i];
			var userMappingValue = document.<portlet:namespace />fm['<portlet:namespace />' + userMappingFieldValues[i]].value;

			if (userMappingValue) {
				userMappingInput.value += userMappingFields[i] + '=' + userMappingValue + '\n';
			}
		}

		var groupMappingFields = ['description', 'groupName', 'user'];
		var groupMappingFieldValues = ['groupMappingDescription', 'groupMappingGroupName', 'groupMappingUser'];
		var groupMappingInput = document.<portlet:namespace />fm['<portlet:namespace />settings--<%= PropsKeys.LDAP_GROUP_MAPPINGS + postfix %>--'];

		groupMappingInput.value = '';

		for (var i = 0; i<groupMappingFields.length; i++) {
			var groupMappingField = groupMappingFields[i];
			var groupMappingValue = document.<portlet:namespace />fm['<portlet:namespace />' + groupMappingFieldValues[i]].value;

			if (groupMappingValue) {
				groupMappingInput.value += groupMappingFields[i] + '=' + groupMappingValue + '\n';
			}
		}

		document.<portlet:namespace />fm.<portlet:namespace /><%= Constants.CMD %>.value = "<%= ldapServerId <= 0 ? Constants.ADD : Constants.UPDATE %>";

		submitForm(document.<portlet:namespace />fm);
	}

	Liferay.provide(
		window,
		'<portlet:namespace />testSettings',
		function(type) {
			var A = AUI();

			var url = null;

			var data = {};

			if (type == "ldapConnection") {
				url = "<portlet:renderURL windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>"><portlet:param name="struts_action" value="/portal_settings/test_ldap_connection" /></portlet:renderURL>";
			}
			else if (type == "ldapGroups") {
				url = "<portlet:renderURL windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>"><portlet:param name="struts_action" value="/portal_settings/test_ldap_groups" /></portlet:renderURL>";

				data.<portlet:namespace />importGroupSearchFilter = document.<portlet:namespace />fm['<portlet:namespace />settings--<%= PropsKeys.LDAP_IMPORT_GROUP_SEARCH_FILTER + postfix %>--'].value;
				data.<portlet:namespace />groupMappingDescription = document.<portlet:namespace />fm['<portlet:namespace />groupMappingDescription'].value;
				data.<portlet:namespace />groupMappingGroupName = document.<portlet:namespace />fm['<portlet:namespace />groupMappingGroupName'].value;
				data.<portlet:namespace />groupMappingUser = document.<portlet:namespace />fm['<portlet:namespace />groupMappingUser'].value;
			}
			else if (type == "ldapUsers") {
				url = "<portlet:renderURL windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>"><portlet:param name="struts_action" value="/portal_settings/test_ldap_users" /></portlet:renderURL>";

				data.<portlet:namespace />importUserSearchFilter = document.<portlet:namespace />fm['<portlet:namespace />settings--<%= PropsKeys.LDAP_IMPORT_USER_SEARCH_FILTER + postfix %>--'].value;
				data.<portlet:namespace />userMappingEmailAddress = document.<portlet:namespace />fm['<portlet:namespace />userMappingEmailAddress'].value;
				data.<portlet:namespace />userMappingFirstName = document.<portlet:namespace />fm['<portlet:namespace />userMappingFirstName'].value;
				data.<portlet:namespace />userMappingFullName = document.<portlet:namespace />fm['<portlet:namespace />userMappingFullName'].value;
				data.<portlet:namespace />userMappingGroup = document.<portlet:namespace />fm['<portlet:namespace />userMappingGroup'].value;
				data.<portlet:namespace />userMappingJobTitle = document.<portlet:namespace />fm['<portlet:namespace />userMappingJobTitle'].value;
				data.<portlet:namespace />userMappingLastName = document.<portlet:namespace />fm['<portlet:namespace />userMappingLastName'].value;
				data.<portlet:namespace />userMappingMiddleName = document.<portlet:namespace />fm['<portlet:namespace />userMappingMiddleName'].value;
				data.<portlet:namespace />userMappingPassword = document.<portlet:namespace />fm['<portlet:namespace />userMappingPassword'].value;
				data.<portlet:namespace />userMappingPortrait = document.<portlet:namespace />fm['<portlet:namespace />userMappingPortrait'].value;
				data.<portlet:namespace />userMappingScreenName = document.<portlet:namespace />fm['<portlet:namespace />userMappingScreenName'].value;
				data.<portlet:namespace />userMappingStatus = document.<portlet:namespace />fm['<portlet:namespace />userMappingStatus'].value;
				data.<portlet:namespace />userMappingUuid = document.<portlet:namespace />fm['<portlet:namespace />userMappingUuid'].value;
			}

			if (url != null) {
				data.<portlet:namespace />ldapServerId = document.<portlet:namespace />fm['<portlet:namespace />ldapServerId'].value;
				data.<portlet:namespace />baseProviderURL = document.<portlet:namespace />fm['<portlet:namespace />settings--<%= PropsKeys.LDAP_BASE_PROVIDER_URL + postfix %>--'].value;
				data.<portlet:namespace />baseDN = document.<portlet:namespace />fm['<portlet:namespace />settings--<%= PropsKeys.LDAP_BASE_DN + postfix %>--'].value;
				data.<portlet:namespace />principal = document.<portlet:namespace />fm['<portlet:namespace />settings--<%= PropsKeys.LDAP_SECURITY_PRINCIPAL + postfix %>--'].value;
				data.<portlet:namespace />credentials = document.<portlet:namespace />fm['<portlet:namespace />settings--<%= PropsKeys.LDAP_SECURITY_CREDENTIALS + postfix %>--'].value;

				var dialog = new A.Dialog(
					{
						align: Liferay.Util.Window.ALIGN_CENTER,
						destroyOnClose: true,
						modal: true,
						title: '<%= UnicodeLanguageUtil.get(pageContext, "ldap") %>',
						width: 600
					}
				).render();

				dialog.plug(
					A.Plugin.IO,
					{
						data: data,
						uri: url
					}
				);
			}
		},
		['aui-dialog', 'aui-io']
	);

	Liferay.provide(
		window,
		'<portlet:namespace />updateDefaultLdap',
		function(ldapType) {
			var A = AUI();

			var baseProviderURL = "";
			var baseDN = "";
			var principal = "";
			var credentials = "";
			var searchFilter = "";
			var importUserSearchFilter = "";
			var userMappingEmailAddress = "";
			var userMappingFirstName = "";
			var userMappingFullName = "";
			var userMappingGroup = "";
			var userMappingJobTitle = "";
			var userMappingLastName = "";
			var userMappingMiddleName = "";
			var userMappingPassword = "";
			var userMappingPortrait = "";
			var userMappingScreenName = "";
			var userMappingStatus = "";
			var userMappingUuid = "";
			var importGroupSearchFilter = "";
			var groupMappingDescription = "";
			var groupMappingGroupName = "";
			var groupMappingUser = "";
			var exportMappingUserDefaultObjectClass = "";
			var exportMappingGroupDefaultObjectClass = "";

			if (!ldapType) {
				A.all(document.<portlet:namespace />fm.<portlet:namespace />defaultLdap).some(
					function(item, index, collection) {
						var checked = item.get('checked');

						if (checked) {
							ldapType = item.val();
						}

						return checked;
					}
				);
			}

			if (ldapType == "apache") {
				baseProviderURL = "ldap://localhost:10389";
				baseDN = "dc=example,dc=com";
				principal = "uid=admin,ou=system";
				credentials = "secret";
				searchFilter = "(mail=@email_address@)";
				importUserSearchFilter = "(objectClass=person)";
				userMappingEmailAddress = "mail";
				userMappingFirstName = "givenName";
				userMappingFullName = "";
				userMappingGroup = "";
				userMappingJobTitle = "title";
				userMappingLastName = "sn";
				userMappingMiddleName = "";
				userMappingPassword = "userPassword";
				userMappingPortrait = "";
				userMappingScreenName = "cn";
				userMappingStatus = "";
				userMappingUuid = "";
				importGroupSearchFilter = "(objectClass=groupOfUniqueNames)";
				groupMappingDescription = "description";
				groupMappingGroupName = "cn";
				groupMappingUser = "uniqueMember";
				exportMappingUserDefaultObjectClass = "top,person,inetOrgPerson,organizationalPerson";
				exportMappingGroupDefaultObjectClass = "top,groupOfUniqueNames";
			}
			else if (ldapType == "fedora") {
				baseProviderURL = "ldap://localhost:19389";
				baseDN = "dc=localdomain";
				principal = "cn=Directory Manager";
				credentials = "";
				searchFilter = "(mail=@email_address@)";
				importUserSearchFilter = "(objectClass=inetOrgPerson)";
				userMappingEmailAddress = "mail";
				userMappingFirstName = "givenName";
				userMappingFullName = "cn";
				userMappingGroup = "";
				userMappingJobTitle = "title";
				userMappingLastName = "sn";
				userMappingMiddleName = "";
				userMappingPassword = "userPassword";
				userMappingPortrait = "";
				userMappingScreenName = "uid";
				userMappingStatus = "";
				userMappingUuid = "";
				importGroupSearchFilter = "";
				groupMappingDescription = "";
				groupMappingGroupName = "";
				groupMappingUser = "";
				exportMappingUserDefaultObjectClass = "";
				exportMappingGroupDefaultObjectClass = "";
			}
			else if (ldapType == "microsoft") {
				baseProviderURL = "ldap://localhost:389";
				baseDN = "dc=example,dc=com";
				principal = "admin";
				credentials = "secret";
				searchFilter = "(&(objectCategory=person)(sAMAccountName=@user_id@))";
				importUserSearchFilter = "(objectClass=person)";
				userMappingEmailAddress = "userprincipalname";
				userMappingFirstName = "givenName";
				userMappingFullName = "cn";
				userMappingGroup = "memberOf";
				userMappingJobTitle = "";
				userMappingLastName = "sn";
				userMappingMiddleName = "middleName";
				userMappingPassword = "userPassword";
				userMappingPortrait = "";
				userMappingScreenName = "sAMAccountName";
				userMappingStatus = "";
				userMappingUuid = "";
				importGroupSearchFilter = "(objectClass=group)";
				groupMappingDescription = "sAMAccountName";
				groupMappingGroupName = "cn";
				groupMappingUser = "member";
				exportMappingUserDefaultObjectClass = "";
				exportMappingGroupDefaultObjectClass = "";
			}
			else if (ldapType == "novell") {
				url = "ldap://localhost:389";
				baseDN = "";
				principal = "cn=admin,ou=test";
				credentials = "secret";
				searchFilter = "(mail=@email_address@)";
				importUserSearchFilter = "";
				userMappingEmailAddress = "mail";
				userMappingFirstName = "givenName";
				userMappingFullName = "";
				userMappingGroup = "";
				userMappingJobTitle = "title";
				userMappingLastName = "sn";
				userMappingMiddleName = "";
				userMappingPassword = "userPassword";
				userMappingPortrait = "";
				userMappingScreenName = "cn";
				userMappingStatus = "";
				userMappingUuid = "";
				importGroupSearchFilter = "";
				groupMappingDescription = "";
				groupMappingGroupName = "";
				groupMappingUser = "";
				exportMappingUserDefaultObjectClass = "";
				exportMappingGroupDefaultObjectClass = "";
			}
			else if (ldapType == "open") {
				url = "ldap://localhost:389";
				baseDN = "dc=example,dc=com";
				principal = "cn=admin,ou=test";
				credentials = "secret";
				searchFilter = "(mail=@email_address@)";
				importUserSearchFilter = "(objectClass=inetOrgPerson)";
				userMappingEmailAddress = "mail";
				userMappingFirstName = "givenName";
				userMappingFullName = "";
				userMappingGroup = "";
				userMappingJobTitle = "title";
				userMappingLastName = "sn";
				userMappingMiddleName = "";
				userMappingPassword = "userPassword";
				userMappingPortrait = "";
				userMappingScreenName = "cn";
				userMappingStatus = "";
				userMappingUuid = "";
				importGroupSearchFilter = "(objectClass=groupOfUniqueNames)";
				groupMappingGroupName = "cn";
				groupMappingDescription = "description";
				groupMappingUser = "uniqueMember";
				exportMappingUserDefaultObjectClass = "";
				exportMappingGroupDefaultObjectClass = "";
			}

			document.<portlet:namespace />fm['<portlet:namespace />settings--<%= PropsKeys.LDAP_BASE_PROVIDER_URL + postfix %>--'].value = baseProviderURL;
			document.<portlet:namespace />fm['<portlet:namespace />settings--<%= PropsKeys.LDAP_BASE_DN + postfix %>--'].value = baseDN;
			document.<portlet:namespace />fm['<portlet:namespace />settings--<%= PropsKeys.LDAP_SECURITY_PRINCIPAL + postfix %>--'].value = principal;
			document.<portlet:namespace />fm['<portlet:namespace />settings--<%= PropsKeys.LDAP_SECURITY_CREDENTIALS + postfix %>--'].value = credentials;
			document.<portlet:namespace />fm['<portlet:namespace />settings--<%= PropsKeys.LDAP_AUTH_SEARCH_FILTER + postfix %>--'].value = searchFilter;
			document.<portlet:namespace />fm['<portlet:namespace />settings--<%= PropsKeys.LDAP_IMPORT_USER_SEARCH_FILTER + postfix %>--'].value = importUserSearchFilter;
			document.<portlet:namespace />fm['<portlet:namespace />userMappingEmailAddress'].value = userMappingEmailAddress;
			document.<portlet:namespace />fm['<portlet:namespace />userMappingFirstName'].value = userMappingFirstName;
			document.<portlet:namespace />fm['<portlet:namespace />userMappingFullName'].value = userMappingFullName;
			document.<portlet:namespace />fm['<portlet:namespace />userMappingGroup'].value = userMappingGroup;
			document.<portlet:namespace />fm['<portlet:namespace />userMappingJobTitle'].value = userMappingJobTitle;
			document.<portlet:namespace />fm['<portlet:namespace />userMappingLastName'].value = userMappingLastName;
			document.<portlet:namespace />fm['<portlet:namespace />userMappingMiddleName'].value = userMappingMiddleName;
			document.<portlet:namespace />fm['<portlet:namespace />userMappingPassword'].value = userMappingPassword;
			document.<portlet:namespace />fm['<portlet:namespace />userMappingPortrait'].value = userMappingPortrait;
			document.<portlet:namespace />fm['<portlet:namespace />userMappingScreenName'].value = userMappingScreenName;
			document.<portlet:namespace />fm['<portlet:namespace />userMappingStatus'].value = userMappingStatus;
			document.<portlet:namespace />fm['<portlet:namespace />userMappingUuid'].value = userMappingUuid;
			document.<portlet:namespace />fm['<portlet:namespace />settings--<%= PropsKeys.LDAP_IMPORT_GROUP_SEARCH_FILTER + postfix %>--'].value = importGroupSearchFilter;
			document.<portlet:namespace />fm['<portlet:namespace />groupMappingDescription'].value = groupMappingDescription;
			document.<portlet:namespace />fm['<portlet:namespace />groupMappingGroupName'].value = groupMappingGroupName;
			document.<portlet:namespace />fm['<portlet:namespace />groupMappingUser'].value = groupMappingUser;
			document.<portlet:namespace />fm['<portlet:namespace />settings--<%= PropsKeys.LDAP_USERS_DN + postfix %>--'].value = baseDN;
			document.<portlet:namespace />fm['<portlet:namespace />settings--<%= PropsKeys.LDAP_USER_DEFAULT_OBJECT_CLASSES + postfix %>--'].value = exportMappingUserDefaultObjectClass;
			document.<portlet:namespace />fm['<portlet:namespace />settings--<%= PropsKeys.LDAP_GROUPS_DN + postfix %>--'].value = baseDN;
			document.<portlet:namespace />fm['<portlet:namespace />settings--<%= PropsKeys.LDAP_GROUP_DEFAULT_OBJECT_CLASSES + postfix %>--'].value = exportMappingGroupDefaultObjectClass;
		},
		['aui-base']
	);

</aui:script>

<%
PortalUtil.addPortletBreadcrumbEntry(request, (ldapServerId == 0) ? LanguageUtil.get(pageContext, "add-ldap-server") : ldapServerName, currentURL);
%>