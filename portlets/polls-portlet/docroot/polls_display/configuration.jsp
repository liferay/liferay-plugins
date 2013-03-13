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

<%@ include file="/polls_display/init.jsp" %>

<%
String redirect = ParamUtil.getString(request, "redirect");

questionId = ParamUtil.getLong(request, "questionId", questionId);

List<PollsQuestion> questions = PollsQuestionLocalServiceUtil.getQuestions(scopeGroupId);

if (scopeGroupId != themeDisplay.getCompanyGroupId()) {
	questions = ListUtil.copy(questions);

	questions.addAll(PollsQuestionLocalServiceUtil.getQuestions(themeDisplay.getCompanyGroupId()));
}
%>

<liferay-portlet:actionURL portletConfiguration="true" var="configurationURL" />

<aui:form action="<%= configurationURL %>" method="post" name="fm">
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />
	<aui:input name="redirect" type="hidden" value="<%= redirect %>" />

	<liferay-ui:error exception="<%= NoSuchQuestionException.class %>" message="the-question-could-not-be-found" />

	<c:choose>
		<c:when test="<%= !questions.isEmpty() %>">
			<aui:fieldset>
				<aui:select label="question" name="preferences--questionId--">
					<aui:option value="" />

					<%
					for (PollsQuestion question : questions) {
						question = question.toEscapedModel();
					%>

						<aui:option label="<%= question.getTitle(locale) %>" selected="<%= questionId == question.getQuestionId() %>" value="<%= question.getQuestionId() %>" />

					<%
					}
					%>

				</aui:select>
			</aui:fieldset>
		</c:when>
		<c:otherwise>
			<div class="portlet-msg-info">
				<liferay-ui:message key="there-are-no-available-questions-for-selection" />
			</div>
		</c:otherwise>
	</c:choose>

	<aui:button-row>
		<aui:button disabled="<%= questions.isEmpty() %>" type="submit" />
	</aui:button-row>
</aui:form>