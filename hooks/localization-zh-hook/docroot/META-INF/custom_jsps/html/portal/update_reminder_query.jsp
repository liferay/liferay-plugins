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

<%@ include file="/html/portal/init.jsp" %>

<%
String currentURL = PortalUtil.getCurrentURL(request);

String referer = ParamUtil.getString(request, WebKeys.REFERER, currentURL);

if (referer.equals(themeDisplay.getPathMain() + "/portal/update_reminder_query")) {
	referer = themeDisplay.getPathMain() + "?doAsUserId=" + themeDisplay.getDoAsUserId();
}
%>

<aui:form action='<%= themeDisplay.getPathMain() + "/portal/update_reminder_query" %>' method="post" name="fm">
	<aui:input name="p_auth" type="hidden" value="<%= AuthTokenUtil.getToken(request) %>" />
	<aui:input name="doAsUserId" type="hidden" value="<%= themeDisplay.getDoAsUserId() %>" />
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />
	<aui:input name="<%= WebKeys.REFERER %>" type="hidden" value="<%= referer %>" />

	<div class="portlet-msg-info">
		<liferay-ui:message key="please-choose-a-reminder-query" />
	</div>

	<c:if test="<%= SessionErrors.contains(request, UserReminderQueryException.class.getName()) %>">
		<div class="portlet-msg-error">
			<liferay-ui:message key="reminder-query-and-answer-cannot-be-empty" />
		</div>
	</c:if>

	<aui:fieldset label="password-reminder">
		<%@ include file="/html/portal/update_reminder_query_question.jspf" %>

		<c:if test="<%= PropsValues.USERS_REMINDER_QUERIES_CUSTOM_QUESTION_ENABLED %>">
			<div class="aui-helper-hidden" id="customQuestionContainer">
				<aui:input bean="<%= user %>" fieldParam="reminderQueryCustomQuestion" label="" model="<%= User.class %>" name="reminderQueryQuestion" />
			</div>
		</c:if>

		<aui:input label="answer" name="reminderQueryAnswer" size="50" type="text" value="<%= user.getReminderQueryAnswer() %>" />
	</aui:fieldset>

	<aui:button-row>
		<aui:button type="submit" />
	</aui:button-row>
</aui:form>

<aui:script use="aui-base">
	var reminderQueryQuestion = A.one('#reminderQueryQuestion');
	var customQuestionContainer = A.one('#customQuestionContainer');

	if (reminderQueryQuestion && customQuestionContainer) {
		if (reminderQueryQuestion.val() != '<%= UsersAdminUtil.CUSTOM_QUESTION %>') {
			customQuestionContainer.hide();
		}
		else {
			customQuestionContainer.show();
		}

		reminderQueryQuestion.on(
			'change',
			function(event) {
				if (this.val() == '<%= UsersAdminUtil.CUSTOM_QUESTION %>') {
					<c:if test="<%= PropsValues.USERS_REMINDER_QUERIES_CUSTOM_QUESTION_ENABLED %>">
						customQuestionContainer.show();

						Liferay.Util.focusFormField('#reminderQueryCustomQuestion');
					</c:if>
				}
				else {
					customQuestionContainer.hide();

					Liferay.Util.focusFormField('#reminderQueryAnswer');
				}
			}
		);

		Liferay.Util.focusFormField(reminderQueryQuestion);
	}
</aui:script>