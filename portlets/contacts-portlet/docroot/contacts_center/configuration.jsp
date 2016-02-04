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

<div class="contacts-portlet">
	<liferay-portlet:actionURL portletConfiguration="true" var="configurationActionURL" />

	<aui:form action="<%= configurationActionURL %>" method="post" name="fm">
		<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />

		<liferay-ui:panel collapsible="<%= true %>" extended="<%= true %>" persistState="<%= true %>" title="user-profile">
			<aui:select label="display-style" name="preferences--displayStyle--" value="<%= displayStyle %>">
				<aui:option label="<%= ContactsConstants.DISPLAY_STYLE_FULL_LABEL %>" value="<%= ContactsConstants.DISPLAY_STYLE_FULL %>" />
				<aui:option label="<%= ContactsConstants.DISPLAY_STYLE_BASIC_LABEL %>" value="<%= ContactsConstants.DISPLAY_STYLE_BASIC %>" />
				<aui:option label="<%= ContactsConstants.DISPLAY_STYLE_DETAIL_LABEL %>" value="<%= ContactsConstants.DISPLAY_STYLE_DETAIL %>" />
			</aui:select>

			<aui:field-wrapper cssClass="lfr-user-profile-preferences">
				<aui:col>
					<aui:input name="preferences--showAdditionalEmailAddresses--" type="checkbox" value="<%= showAdditionalEmailAddresses %>" />

					<aui:input name="preferences--showAddresses--" type="checkbox" value="<%= showAddresses %>" />

					<aui:input name="preferences--showComments--" type="checkbox" value="<%= showComments %>" />

					<aui:input name="preferences--showCompleteYourProfile--" type="checkbox" value="<%= showCompleteYourProfile %>" />

					<aui:input name="preferences--showInstantMessenger--" type="checkbox" value="<%= showInstantMessenger %>" />

					<aui:input name="preferences--showPhones--" type="checkbox" value="<%= showPhones %>" />

					<aui:input label="show-sms" name="preferences--showSMS--" type="checkbox" value="<%= showSMS %>" />
				</aui:col>

				<aui:col>
					<aui:input name="preferences--showSocialNetwork--" type="checkbox" value="<%= showSocialNetwork %>" />

					<aui:input label="show-icon" name="preferences--showIcon--" type="checkbox" value="<%= showIcon %>" />

					<aui:input name="preferences--showRecentActivity--" type="checkbox" value="<%= showRecentActivity %>" />

					<aui:input label="show-sites" name="preferences--showSites--" type="checkbox" value="<%= showSites %>" />

					<aui:input label="show-tags" name="preferences--showTags--" type="checkbox" value="<%= showTags %>" />

					<aui:input name="preferences--showUsersInformation--" type="checkbox" value="<%= showUsersInformation %>" />

					<aui:input name="preferences--showWebsites--" type="checkbox" value="<%= showWebsites %>" />
				</aui:col>
			</aui:field-wrapper>
		</liferay-ui:panel>

		<aui:button type="submit" />
	</aui:form>
</div>