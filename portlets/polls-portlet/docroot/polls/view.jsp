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

<%@ include file="/polls/init.jsp" %>

<aui:form method="post" name="fm">

	<%
	PortletURL portletURL = renderResponse.createRenderURL();

	portletURL.setParameter("struts_action", "/polls/view");

	List<String> headerNames = new ArrayList<String>();

	headerNames.add("question");
	headerNames.add("num-of-votes");
	headerNames.add("last-vote-date");
	headerNames.add("expiration-date");
	headerNames.add(StringPool.BLANK);

	SearchContainer searchContainer = new SearchContainer(renderRequest, null, null, SearchContainer.DEFAULT_CUR_PARAM, SearchContainer.DEFAULT_DELTA, portletURL, headerNames, null);

	int total = PollsQuestionLocalServiceUtil.getQuestionsCount(scopeGroupId);

	searchContainer.setTotal(total);

	List results = PollsQuestionLocalServiceUtil.getQuestions(scopeGroupId, searchContainer.getStart(), searchContainer.getEnd());

	searchContainer.setResults(results);

	List resultRows = searchContainer.getResultRows();

	for (int i = 0; i < results.size(); i++) {
		PollsQuestion question = (PollsQuestion)results.get(i);

		question = question.toEscapedModel();

		ResultRow row = new ResultRow(question, question.getQuestionId(), i);

		PortletURL rowURL = renderResponse.createRenderURL();

		rowURL.setParameter("struts_action", "/polls/view_question");
		rowURL.setParameter("redirect", currentURL);
		rowURL.setParameter("questionId", String.valueOf(question.getQuestionId()));

		// Title

		row.addText(question.getTitle(locale), rowURL);

		// Number of votes

		int votesCount = PollsVoteLocalServiceUtil.getQuestionVotesCount(question.getQuestionId());

		row.addText(String.valueOf(votesCount), rowURL);

		// Last vote date

		if (question.getLastVoteDate() == null) {
			row.addText(LanguageUtil.get(pageContext, "never"), rowURL);
		}
		else {
			row.addDate(question.getLastVoteDate(), rowURL);
		}

		// Expiration date

		if (question.getExpirationDate() == null) {
			row.addText(LanguageUtil.get(pageContext, "never"), rowURL);
		}
		else {
			row.addDate(question.getExpirationDate(), rowURL);
		}

		// Action

		row.addJSP("right", SearchEntry.DEFAULT_VALIGN, "/html/portlet/polls/question_action.jsp");

		// Add result row

		resultRows.add(row);
	}

	boolean showAddPollButton = PollsPermission.contains(permissionChecker, scopeGroupId, ActionKeys.ADD_QUESTION);
	boolean showPermissionsButton = PollsPermission.contains(permissionChecker, scopeGroupId, ActionKeys.PERMISSIONS);
	%>

	<aui:fieldset>
		<c:if test="<%= showAddPollButton || showPermissionsButton %>">
			<aui:button-row>
				<c:if test="<%= showAddPollButton %>">
					<portlet:renderURL var="editQuestionURL">
						<portlet:param name="struts_action" value="/polls/edit_question" />
						<portlet:param name="redirect" value="<%= currentURL %>" />
					</portlet:renderURL>

					<aui:button href="<%= editQuestionURL %>" value="add-question" />
				</c:if>

				<c:if test="<%= showPermissionsButton %>">
					<liferay-security:permissionsURL
						modelResource="com.liferay.portlet.polls"
						modelResourceDescription="<%= HtmlUtil.escape(themeDisplay.getScopeGroupName()) %>"
						resourcePrimKey="<%= String.valueOf(scopeGroupId) %>"
						var="permissionsURL"
					/>

					<aui:button href="<%= permissionsURL %>" value="permissions" />
				</c:if>
			</aui:button-row>
		</c:if>

		<liferay-ui:search-iterator searchContainer="<%= searchContainer %>" />
	</aui:fieldset>
</aui:form>