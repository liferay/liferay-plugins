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

<liferay-portlet:actionURL portletConfiguration="true" var="configurationURL" />

<div class="contacts-portlet">
	<aui:form action="<%= configurationURL %>" method="post" name="fm">
		<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />

		<liferay-ui:panel collapsible="<%= true %>" extended="<%= true %>" persistState="<%= true %>" title="contacts-home">
			<aui:input name="preferences--usersPerSection--" size="2" type="text" value="<%= usersPerSection %>" />
		</liferay-ui:panel>

		<liferay-ui:panel collapsible="<%= true %>" extended="<%= true %>" persistState="<%= true %>" title="user-profile">
			<aui:input name="preferences--showUsersInformation--" type="checkbox" value="<%= showUsersInformation %>" />

			<aui:field-wrapper cssClass="lfr-user-profile-preferences">
				<aui:column>
					<aui:input name="preferences--showAdditionalEmailAddresses--" type="checkbox" value="<%= showAdditionalEmailAddresses %>" />

					<aui:input name="preferences--showAddresses--" type="checkbox" value="<%= showAddresses %>" />

					<aui:input name="preferences--showComments--" type="checkbox" value="<%= showComments %>" />

					<aui:input name="preferences--showInstantMessenger--" type="checkbox" value="<%= showInstantMessenger %>" />
				</aui:column>

				<aui:column>
					<aui:input name="preferences--showPhones--" type="checkbox" value="<%= showPhones %>" />

					<aui:input label="show-sms" name="preferences--showSMS--" type="checkbox" value="<%= showSMS %>" />

					<aui:input name="preferences--showSocialNetwork--" type="checkbox" value="<%= showSocialNetwork %>" />

					<aui:input name="preferences--showWebsites--" type="checkbox" value="<%= showWebsites %>" />
				</aui:column>
			</aui:field-wrapper>

			<aui:input name="preferences--showUsersRecentActivity--" type="checkbox" value="<%= showUsersRecentActivity %>" />
		</liferay-ui:panel>

		<aui:button type="submit" />
	</aui:form>
</div>

<aui:script use="aui-base">
	var showUserInfoCheckbox = A.one('#<portlet:namespace />showUsersInformationCheckbox');

	var extraFields = A.one('.lfr-user-profile-preferences');

	var toggleExtraFields = function() {
		if (showUserInfoCheckbox.attr('checked')) {
			extraFields.show();
		}
		else {
			extraFields.hide();
		}
	}

	showUserInfoCheckbox.on('change', toggleExtraFields)

	toggleExtraFields();
</aui:script>