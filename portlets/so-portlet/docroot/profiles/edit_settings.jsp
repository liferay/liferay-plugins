<%--
/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
 *
 * This file is part of Liferay Social Office. Liferay Social Office is free
 * software: you can redistribute it and/or modify it under the terms of the GNU
 * Affero General Public License as published by the Free Software Foundation,
 * either version 3 of the License, or (at your option) any later version.
 *
 * Liferay Social Office is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Affero General Public License
 * for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * Liferay Social Office. If not, see http://www.gnu.org/licenses/agpl-3.0.html.
 */
--%>

<%@ include file="/init.jsp" %>

<%
PasswordPolicy passwordPolicy = null;

if (user == null) {
	passwordPolicy = PasswordPolicyLocalServiceUtil.getDefaultPasswordPolicy(company.getCompanyId());
}
else {
	passwordPolicy = user.getPasswordPolicy();
}

String languageId = BeanParamUtil.getString(user, request, "languageId", user.getLanguageId());
String timeZoneId = BeanParamUtil.getString(user, request, "timeZoneId", user.getTimeZoneId());
%>

<h1><%= user.getFullName() %> : <liferay-ui:message key="edit-settings" /></h1>

<form action="<portlet:actionURL name="updateUserSettings"></portlet:actionURL>" name="<portlet:namespace />fm">
<input name="<portlet:namespace />redirect" type="hidden" value="<%= PortalUtil.getLayoutURL(layout, themeDisplay) %>/-/profiles/user_profile" />
<input name="<portlet:namespace />redirectOnError" type="hidden" value="<%= PortalUtil.getLayoutURL(layout, themeDisplay) %>/-/profiles/edit_settings" />
<input name="<portlet:namespace />userId" type="hidden" value="<%= user.getUserId() %>" />

<liferay-ui:error />

<table width="100%">
<tr>
	<td>
		<img alt="<%= user.getFullName() %>" src="<%= user.getPortraitURL(themeDisplay) %>" />

		<div class="profile-controls">
			<a class="so-display-profile" href="javascript:;"><liferay-ui:message key="cancel-edit" /></a>
		</div>
	</td>
	<td class="profile-area edit-profile">
		<table width="100%">
		<tr>
			<td colspan="2">
				<h3 class="first"><liferay-ui:message key="password" /></h3>
			</td>
		</tr>
		<tr>
			<td class="lfr-label">
				<liferay-ui:message key="password" />
			</td>
			<td>
				<liferay-ui:error exception="<%= UserPasswordException.class %>">

					<%
					UserPasswordException upe = (UserPasswordException)errorException;
					%>

					<c:if test="<%= upe.getType() == UserPasswordException.PASSWORD_ALREADY_USED %>">
						<liferay-ui:message key="that-password-has-already-been-used-please-enter-in-a-different-password" />
					</c:if>

					<c:if test="<%= upe.getType() == UserPasswordException.PASSWORD_CONTAINS_TRIVIAL_WORDS %>">
						<liferay-ui:message key="that-password-uses-common-words-please-enter-in-a-password-that-is-harder-to-guess-i-e-contains-a-mix-of-numbers-and-letters" />
					</c:if>

					<c:if test="<%= upe.getType() == UserPasswordException.PASSWORD_INVALID %>">
						<liferay-ui:message key="that-password-is-invalid-please-enter-in-a-different-password" />
					</c:if>

					<c:if test="<%= upe.getType() == UserPasswordException.PASSWORD_LENGTH %>">
						<%= LanguageUtil.format(pageContext, "that-password-is-too-short-or-too-long-please-make-sure-your-password-is-between-x-and-512-characters", String.valueOf(passwordPolicy.getMinLength()), false) %>
					</c:if>

					<c:if test="<%= upe.getType() == UserPasswordException.PASSWORD_NOT_CHANGEABLE %>">
						<liferay-ui:message key="your-password-cannot-be-changed" />
					</c:if>

					<c:if test="<%= upe.getType() == UserPasswordException.PASSWORD_SAME_AS_CURRENT %>">
						<liferay-ui:message key="your-new-password-cannot-be-the-same-as-your-old-password-please-enter-in-a-different-password" />
					</c:if>

					<c:if test="<%= upe.getType() == UserPasswordException.PASSWORD_TOO_YOUNG %>">
						<%= LanguageUtil.format(pageContext, "you-cannot-change-your-password-yet-please-wait-at-least-x-before-changing-your-password-again", LanguageUtil.getTimeDescription(pageContext, passwordPolicy.getMinAge() * 1000), false) %>
					</c:if>

					<c:if test="<%= upe.getType() == UserPasswordException.PASSWORDS_DO_NOT_MATCH %>">
						<liferay-ui:message key="the-passwords-you-entered-do-not-match-each-other-please-re-enter-your-password" />
					</c:if>
				</liferay-ui:error>

				<input name="<portlet:namespace />password1" size="30" type="password" value="" />
			</td>
		</tr>
		<tr>
			<td class="lfr-label">
				<liferay-ui:message key="enter-again" />
			</td>
			<td>
				<input name="<portlet:namespace />password2" size="30" type="password" value="" />
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<h3><liferay-ui:message key="display-settings" /></h3>
			</td>
		</tr>
		<tr>
			<td class="lfr-label">
				<liferay-ui:message key="language" />
			</td>
			<td>
				<select name="<portlet:namespace />languageId">

					<%
					Locale selLocale = LocaleUtil.fromLanguageId(languageId);

					Locale[] locales = LanguageUtil.getAvailableLocales();

					for (Locale curLocale : locales) {
					%>

						<option <%= (selLocale.getLanguage().equals(curLocale.getLanguage()) && selLocale.getCountry().equals(curLocale.getCountry())) ? "selected" : "" %> value="<%= curLocale.getLanguage() + "_" + curLocale.getCountry() %>"><%= curLocale.getDisplayName(curLocale) %></option>

					<%
					}
					%>

				</select>
			</td>
		</tr>
		<tr>
			<td class="lfr-label">
				<liferay-ui:message key="time-zone" />
			</td>
			<td>
				<liferay-ui:input-time-zone name="timeZoneId" value="<%= timeZoneId %>" />
			</td>
		</tr>
		</table>

		<br />

		<div>
			<input id="<portlet:namespace />submit" type="submit" value="<liferay-ui:message key="save" />" />

			<input class="so-display-profile" type="button" value="<liferay-ui:message key="cancel" />" />
		</div>
	</td>
</tr>
</table>

</form>