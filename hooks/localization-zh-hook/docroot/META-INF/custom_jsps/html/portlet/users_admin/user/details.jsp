<%--
/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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

<%@ include file="/html/portlet/users_admin/init.jsp" %>

<%
User selUser = (User)request.getAttribute("user.selUser");
Contact selContact = (Contact)request.getAttribute("user.selContact");

Calendar birthday = CalendarFactoryUtil.getCalendar();

birthday.set(Calendar.MONTH, Calendar.JANUARY);
birthday.set(Calendar.DATE, 1);
birthday.set(Calendar.YEAR, 1970);

if (selContact != null) {
	birthday.setTime(selContact.getBirthday());
}

boolean deletePortrait = ParamUtil.getBoolean(request, "deletePortrait");
%>

<liferay-ui:error-marker key="errorSection" value="details" />

<aui:model-context bean="<%= selUser %>" model="<%= User.class %>" />

<h3><liferay-ui:message key="details" /></h3>

<aui:fieldset column="<%= true %>" cssClass="aui-w50">
	<liferay-ui:success key="verificationEmailSent" message="your-email-verification-code-has-been-sent-and-the-new-email-address-will-be-applied-to-your-account-once-it-has-been-verified" />

	<liferay-ui:error exception="<%= DuplicateUserScreenNameException.class %>" message="the-screen-name-you-requested-is-already-taken" />

	<liferay-ui:error exception="<%= GroupFriendlyURLException.class %>">

		<%
		GroupFriendlyURLException gfurle = (GroupFriendlyURLException)errorException;
		%>

		<c:if test="<%= gfurle.getType() == GroupFriendlyURLException.DUPLICATE %>">
			<liferay-ui:message key="the-screen-name-you-requested-is-associated-with-an-existing-friendly-url" />
		</c:if>
	</liferay-ui:error>

	<liferay-ui:error exception="<%= ReservedUserScreenNameException.class %>" message="the-screen-name-you-requested-is-reserved" />
	<liferay-ui:error exception="<%= UserScreenNameException.class %>" message="please-enter-a-valid-screen-name" />

	<c:if test="<%= !PrefsPropsUtil.getBoolean(company.getCompanyId(), PropsKeys.USERS_SCREEN_NAME_ALWAYS_AUTOGENERATE) || (selUser != null) %>">
		<c:choose>
			<c:when test="<%= PrefsPropsUtil.getBoolean(company.getCompanyId(), PropsKeys.USERS_SCREEN_NAME_ALWAYS_AUTOGENERATE) || ((selUser != null) && !UsersAdminUtil.hasUpdateScreenName(permissionChecker, selUser)) %>">
				<aui:field-wrapper name="screenName">
					<%= selUser.getScreenName() %>

					<aui:input name="screenName" type="hidden" value="<%= selUser.getScreenName() %>" />
				</aui:field-wrapper>
			</c:when>
			<c:otherwise>
				<aui:input name="screenName" />
			</c:otherwise>
		</c:choose>
	</c:if>

	<liferay-ui:error exception="<%= DuplicateUserEmailAddressException.class %>" message="the-email-address-you-requested-is-already-taken" />
	<liferay-ui:error exception="<%= ReservedUserEmailAddressException.class %>" message="the-email-address-you-requested-is-reserved" />
	<liferay-ui:error exception="<%= UserEmailAddressException.class %>" message="please-enter-a-valid-email-address" />

	<c:choose>
		<c:when test="<%= (selUser != null) && !UsersAdminUtil.hasUpdateEmailAddress(permissionChecker, selUser) %>">
			<aui:field-wrapper name="emailAddress">
				<%= selUser.getDisplayEmailAddress() %>

				<aui:input name="emailAddress" type="hidden" value="<%= selUser.getEmailAddress() %>" />
			</aui:field-wrapper>
		</c:when>
		<c:otherwise>

			<%
			User displayEmailAddressUser = null;

			if (selUser != null) {
				displayEmailAddressUser = (User)selUser.clone();

				displayEmailAddressUser.setEmailAddress(displayEmailAddressUser.getDisplayEmailAddress());
			}
			%>

			<aui:input bean="<%= displayEmailAddressUser %>" model="<%= User.class %>" name="emailAddress">
				<c:if test="<%= PrefsPropsUtil.getBoolean(company.getCompanyId(), PropsKeys.USERS_EMAIL_ADDRESS_REQUIRED) %>">
					<aui:validator name="required" />
				</c:if>
			</aui:input>
		</c:otherwise>
	</c:choose>

	<%@ include file="/html/portlet/users_admin/user/details_user_name.jspf" %>
</aui:fieldset>

<aui:fieldset column="<%= true %>" cssClass="aui-w50">
	<div>
		<c:if test="<%= selUser != null %>">
			<portlet:renderURL var="editUserPortraitURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
				<portlet:param name="struts_action" value="/users_admin/edit_user_portrait" />
				<portlet:param name="redirect" value="<%= currentURL %>" />
				<portlet:param name="p_u_i_d" value="<%= String.valueOf(selUser.getUserId()) %>" />
				<portlet:param name="portrait_id" value="<%= String.valueOf(selUser.getPortraitId()) %>" />
			</portlet:renderURL>

			<liferay-ui:logo-selector
				defaultLogoURL="<%= UserConstants.getPortraitURL(themeDisplay.getPathImage(), selUser.isMale(), 0) %>"
				editLogoURL="<%= editUserPortraitURL %>"
				imageId="<%= selUser.getPortraitId() %>"
				logoDisplaySelector=".user-logo"
				showBackground="<%= false %>"
			/>
		</c:if>
	</div>

	<c:if test="<%= selUser != null %>">
		<liferay-ui:error exception="<%= DuplicateUserIdException.class %>" message="the-user-id-you-requested-is-already-taken" />
		<liferay-ui:error exception="<%= ReservedUserIdException.class %>" message="the-user-id-you-requested-is-reserved" />
		<liferay-ui:error exception="<%= UserIdException.class %>" message="please-enter-a-valid-user-id" />

		<aui:field-wrapper name="userId">
			<%= selUser.getUserId() %>

			<aui:input name="userId" type="hidden" value="<%= selUser.getUserId() %>" />
		</aui:field-wrapper>
	</c:if>

	<c:choose>
		<c:when test="<%= PrefsPropsUtil.getBoolean(company.getCompanyId(), PropsKeys.FIELD_ENABLE_COM_LIFERAY_PORTAL_MODEL_CONTACT_BIRTHDAY) %>">
			<liferay-ui:error exception="<%= ContactBirthdayException.class %>" message="please-enter-a-valid-date" />

			<aui:input bean="<%= selContact %>" model="<%= Contact.class %>" name="birthday" value="<%= birthday %>" />
		</c:when>
		<c:otherwise>
			<aui:input name="birthdayMonth" type="hidden" value="<%= Calendar.JANUARY %>" />
			<aui:input name="birthdayDay" type="hidden" value="1" />
			<aui:input name="birthdayYear" type="hidden" value="1970" />
		</c:otherwise>
	</c:choose>

	<c:if test="<%= PrefsPropsUtil.getBoolean(company.getCompanyId(), PropsKeys.FIELD_ENABLE_COM_LIFERAY_PORTAL_MODEL_CONTACT_MALE) %>">
		<aui:select bean="<%= selContact %>" label="gender" model="<%= Contact.class %>" name="male">
			<aui:option label="male" value="true" />
			<aui:option label="female" value="false" />
		</aui:select>
	</c:if>

	<aui:input name="jobTitle" />
</aui:fieldset>