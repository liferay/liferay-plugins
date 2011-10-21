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

<%@ include file="/html/portlet/message_boards/init.jsp" %>

<%
String topLink = ParamUtil.getString(request, "topLink", "message-boards-home");

MBCategory category = (MBCategory)request.getAttribute(WebKeys.MESSAGE_BOARDS_CATEGORY);

long categoryId = MBUtil.getCategoryId(request, category);

MBCategoryDisplay categoryDisplay = (MBCategoryDisplay)request.getAttribute("view.jsp-categoryDisplay");

Set<Long> categorySubscriptionClassPKs = (Set<Long>)request.getAttribute("view.jsp-threadSubscriptionClassPKs");
Set<Long> threadSubscriptionClassPKs = (Set<Long>)request.getAttribute("view.jsp-threadSubscriptionClassPKs");

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("struts_action", "/message_boards/view");
portletURL.setParameter("topLink", topLink);
portletURL.setParameter("mbCategoryId", String.valueOf(categoryId));
%>

<liferay-ui:panel-container cssClass="message-boards-panels" extended="<%= false %>" id="messageBoardsPanelContainer" persistState="<%= true %>">

	<%
	int categoriesCount = MBCategoryServiceUtil.getCategoriesCount(scopeGroupId, categoryId);
	%>

	<c:if test="<%= categoriesCount > 0 %>">
		<liferay-ui:panel collapsible="<%= categoriesPanelCollapsible %>" extended="<%= categoriesPanelExtended %>" id="messageBoardsCategoriesPanel" persistState="<%= true %>" title='<%= LanguageUtil.get(pageContext, (category != null) ? "subcategories" : "categories") %>'>
			<liferay-ui:search-container
				curParam="cur1"
				deltaConfigurable="<%= false %>"
				headerNames="category,categories,threads,posts"
				iteratorURL="<%= portletURL %>"
			>
				<liferay-ui:search-container-results
					results="<%= MBCategoryServiceUtil.getCategories(scopeGroupId, categoryId, searchContainer.getStart(), searchContainer.getEnd()) %>"
					total="<%= categoriesCount %>"
				/>

				<liferay-ui:search-container-row
					className="com.liferay.portlet.messageboards.model.MBCategory"
					escapedModel="<%= true %>"
					keyProperty="categoryId"
					modelVar="curCategory"
				>
					<liferay-ui:search-container-row-parameter name="categorySubscriptionClassPKs" value="<%= categorySubscriptionClassPKs %>" />

					<liferay-portlet:renderURL varImpl="rowURL">
						<portlet:param name="struts_action" value="/message_boards/view" />
						<portlet:param name="mbCategoryId" value="<%= String.valueOf(curCategory.getCategoryId()) %>" />
					</liferay-portlet:renderURL>

					<%@ include file="/html/portlet/message_boards/category_columns.jspf" %>
				</liferay-ui:search-container-row>

				<liferay-ui:search-iterator />
			</liferay-ui:search-container>
		</liferay-ui:panel>
	</c:if>
</liferay-ui:panel-container>

<liferay-ui:search-container
	curParam="cur2"
	emptyResultsMessage="there-are-no-questions-in-this-category"
	headerNames="stats,question,last-post"
	iteratorURL="<%= portletURL %>"
>
	<liferay-ui:search-container-results
		results="<%= MBThreadServiceUtil.getThreads(scopeGroupId, categoryId, WorkflowConstants.STATUS_APPROVED, searchContainer.getStart(), searchContainer.getEnd()) %>"
		total="<%= MBThreadServiceUtil.getThreadsCount(scopeGroupId, categoryId, WorkflowConstants.STATUS_APPROVED) %>"
	/>

	<liferay-ui:search-container-row
		className="com.liferay.portlet.messageboards.model.MBThread"
		keyProperty="threadId"
		modelVar="thread"
	>

		<%
		MBMessage message = null;

		try {
			message = MBMessageLocalServiceUtil.getMessage(thread.getRootMessageId());
		}
		catch (NoSuchMessageException nsme) {
			_log.error("Thread requires missing root message id " + thread.getRootMessageId());

			message = new MBMessageImpl();

			row.setSkip(true);
		}

		message = message.toEscapedModel();

		row.setBold(!MBThreadFlagLocalServiceUtil.hasThreadFlag(themeDisplay.getUserId(), thread));
		row.setObject(new Object[] {message, threadSubscriptionClassPKs});
		row.setRestricted(!MBMessagePermission.contains(permissionChecker, message, ActionKeys.VIEW));
		%>

		<liferay-portlet:renderURL varImpl="rowURL">
			<portlet:param name="struts_action" value="/message_boards/view_message" />
			<portlet:param name="messageId" value="<%= String.valueOf(message.getMessageId()) %>" />
		</liferay-portlet:renderURL>

		<liferay-ui:search-container-column-text
			cssClass="stats"
			name="statistics"
			href="<%= rowURL %>"
		>

			<%
			int answers = MBMessageServiceUtil.getThreadAnswersCount(scopeGroupId, categoryId, thread.getThreadId());

			RatingsStats ratingsStats = RatingsStatsLocalServiceUtil.getStats(MBMessage.class.getName(), message.getMessageId());

			int ratingScore = (int)ratingsStats.getTotalScore();
			%>

			<span class="question-details">
				<span class= "votes">
					<span class="count"><%= String.valueOf(ratingScore) %></span> <span><%= LanguageUtil.get(pageContext, "votes") %></span>
				</span>

				<span class="status <%= (answers != 0) ? "answered" : " unanswered" %> ">
					<span class="count"><%= answers %></span> <span><%= LanguageUtil.get(pageContext, (answers != 1) ? "answers" : "answer") %></span>
				</span>

				<span class="views">
					<span class="count"><%= thread.getViewCount() %></span> <span><%= LanguageUtil.get(pageContext, "views") %></span>
				</span>
			</span>
		</liferay-ui:search-container-column-text>

		<liferay-ui:search-container-column-jsp
			cssClass="question"
			name="question"
			path="/html/portlet/message_boards/message_summary_question.jsp"
		/>

		<liferay-ui:search-container-column-jsp
			cssClass="last-post"
			name="last-post"
			path="/html/portlet/message_boards/last_post_question.jsp"
		/>

		<c:if test="<%= MBMessagePermission.contains(permissionChecker, message, ActionKeys.UPDATE) %>">
			<liferay-ui:search-container-column-jsp
				align="right"
				cssClass="action-question"
				path="/html/portlet/message_boards/message_action.jsp"
			/>
		</c:if>
	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator />
</liferay-ui:search-container>

<%!
private static Log _log = LogFactoryUtil.getLog("portal-web.docroot.html.portlet.message_boards.view_category_question_jsp");
%>