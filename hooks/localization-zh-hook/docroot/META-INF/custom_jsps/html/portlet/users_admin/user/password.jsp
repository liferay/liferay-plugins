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

<%@ include file="/html/portlet/users_admin/init.jsp" %>

<%
User selUser = (User)request.getAttribute("user.selUser");

PasswordPolicy passwordPolicy = (PasswordPolicy)request.getAttribute("user.passwordPolicy");

boolean passwordResetDisabled = false;

if (((selUser == null) || (selUser.getLastLoginDate() == null)) && passwordPolicy.isChangeable() && passwordPolicy.isChangeRequired()) {
	passwordResetDisabled = true;
}

boolean passwordReset = false;

if (passwordResetDisabled) {
	passwordReset = true;
}
else {
	passwordReset = BeanParamUtil.getBoolean(selUser, request, "passwordReset");
}
%>

<liferay-ui:error-marker key="errorSection" value="password" />

<aui:model-context bean="<%= selUser %>" model="<%= User.class %>" />

<h3><liferay-ui:message key="password" /></h3>

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

	<c:if test="<%= upe.getType() == UserPasswordException.PASSWORD_TOO_TRIVIAL %>">
		<liferay-ui:message key="that-password-is-too-trivial" />
	</c:if>

	<c:if test="<%= upe.getType() == UserPasswordException.PASSWORD_TOO_YOUNG %>">
		<%= LanguageUtil.format(pageContext, "you-cannot-change-your-password-yet-please-wait-at-least-x-before-changing-your-password-again", LanguageUtil.getTimeDescription(pageContext, passwordPolicy.getMinAge() * 1000), false) %>
	</c:if>

	<c:if test="<%= upe.getType() == UserPasswordException.PASSWORDS_DO_NOT_MATCH %>">
		<liferay-ui:message key="the-passwords-you-entered-do-not-match-each-other-please-re-enter-your-password" />
	</c:if>
</liferay-ui:error>

<aui:fieldset>
	<c:if test="<%= portletName.equals(PortletKeys.MY_ACCOUNT) %>">
		<aui:input autocomplete="off" label="current-password" name="password0" size="30" type="password" />
	</c:if>

	<aui:input autocomplete="off" label="new-password" name="password1" size="30" type="password" />

	<aui:input autocomplete="off" label="enter-again" name="password2" size="30" type="password">
		<aui:validator name="equalTo">
			'#<portlet:namespace />password1'
		</aui:validator>
	</aui:input>

	<c:if test="<%= (selUser == null) || (user.getUserId() != selUser.getUserId()) %>">
		<aui:input disabled="<%= passwordResetDisabled %>" label="password-reset-required" name="passwordReset" type="checkbox" value="<%= passwordReset %>" />
	</c:if>
</aui:fieldset>

<c:if test="<%= PropsValues.USERS_REMINDER_QUERIES_ENABLED && portletName.equals(PortletKeys.MY_ACCOUNT) %>">
	<h3><liferay-ui:message key="reminder" /></h3>

	<%
	boolean hasCustomQuestion = true;
	%>

	<aui:fieldset>
		<%@ include file="/html/portlet/users_admin/user/password_reminder_query_questions.jspf" %>

		<c:if test="<%= PropsValues.USERS_REMINDER_QUERIES_CUSTOM_QUESTION_ENABLED %>">
			<div id="<portlet:namespace />customQuestionDiv">
				<aui:input fieldParam="reminderQueryCustomQuestion" label="custom-question" name="reminderQueryQuestion" />
			</div>
		</c:if>

		<aui:input label="answer" name="reminderQueryAnswer" size="50" value="<%= selUser.getReminderQueryAnswer() %>" />
	</aui:fieldset>

	<aui:script use="aui-base">
		var reminderQueryQuestion = A.one('#<portlet:namespace />reminderQueryQuestion');
		var customQuestionDiv = A.one('#<portlet:namespace />customQuestionDiv');

		if (<%= !hasCustomQuestion %> && customQuestionDiv) {
			customQuestionDiv.hide();
		}

		if (reminderQueryQuestion) {
			reminderQueryQuestion.on(
				'change',
				function(event) {
					if (event.target.val() == '<%= UsersAdminUtil.CUSTOM_QUESTION %>') {
						var reminderQueryCustomQuestion = A.one('#<portlet:namespace />reminderQueryCustomQuestion');

						if (customQuestionDiv) {
							customQuestionDiv.show();
						}

						<%
						for (String question : PropsValues.USERS_REMINDER_QUERIES_QUESTIONS) {
						%>

							if (reminderQueryCustomQuestion && (reminderQueryCustomQuestion.val() == '<%= UnicodeFormatter.toString(question) %>')) {
								reminderQueryCustomQuestion.val('');
							}

						<%
						}
						%>

						Liferay.Util.focusFormField(reminderQueryCustomQuestion);
					}
					else {
						if (customQuestionDiv) {
							customQuestionDiv.hide();
						}

						Liferay.Util.focusFormField(A.one('#<portlet:namespace />reminderQueryAnswer'));
					}
				}
			);
		}
	</aui:script>
</c:if>