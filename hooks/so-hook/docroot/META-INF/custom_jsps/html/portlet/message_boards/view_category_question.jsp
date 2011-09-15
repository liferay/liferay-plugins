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
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License
 * for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * Liferay Social Office. If not, see http://www.gnu.org/licenses/agpl-3.0.html.
 */
--%>

<%@ include file="/html/portlet/message_boards/init.jsp" %>

<%
MBCategory category = (MBCategory)request.getAttribute(WebKeys.MESSAGE_BOARDS_CATEGORY);

long categoryId = MBUtil.getCategoryId(request, category);

MBCategoryDisplay categoryDisplay = (MBCategoryDisplay)request.getAttribute("view.jsp-categoryDisplay");

Set<Long> categorySubscriptionClassPKs = (Set<Long>)request.getAttribute("view.jsp-categorySubscriptionClassPKs");
Set<Long> threadSubscriptionClassPKs = (Set<Long>)request.getAttribute("view.jsp-threadSubscriptionClassPKs");

Boolean showCategories = GetterUtil.getBoolean((String)request.getAttribute("view.jsp-viewCategory"), false);

PortletURL portletURL = (PortletURL)request.getAttribute("view.jsp-portletURL");
%>

<c:if test="<%= showCategories %>">
	<liferay-ui:search-container
		curParam="cur1"
		deltaConfigurable="<%= false %>"
		emptyResultsMessage="there-are-no-categories"
		headerNames="category"
		iteratorURL="<%= portletURL %>"
	>
		<liferay-ui:search-container-results
			results="<%= MBCategoryServiceUtil.getCategories(scopeGroupId, categoryId, searchContainer.getStart(), searchContainer.getEnd()) %>"
			total="<%= MBCategoryServiceUtil.getCategoriesCount(scopeGroupId, categoryId) %>"
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

			<%@ include file="/html/portlet/message_boards/category_columns.so-hook.jspf" %>
		</liferay-ui:search-container-row>

		<%
		boolean showAddCategoryButton = MBCategoryPermission.contains(permissionChecker, scopeGroupId, categoryId, ActionKeys.ADD_CATEGORY);
		boolean showPermissionsButton = GroupPermissionUtil.contains(permissionChecker, scopeGroupId, ActionKeys.PERMISSIONS);
		%>

		<c:if test="<%= showAddCategoryButton || showPermissionsButton %>">
			<aui:button-row>
				<c:if test="<%= showAddCategoryButton %>">
					<portlet:renderURL var="editCategoryURL">
						<portlet:param name="struts_action" value="/message_boards/edit_category" />
						<portlet:param name="redirect" value="<%= currentURL %>" />
						<portlet:param name="parentCategoryId" value="<%= String.valueOf(categoryId) %>" />
					</portlet:renderURL>

					<aui:button href='<%= editCategoryURL %>' value='<%= (category == null) ? "add-category" : "add-subcategory" %>' />
				</c:if>

				<c:if test="<%= showPermissionsButton %>">

					<%
					String modelResource = "com.liferay.portlet.messageboards";
					String modelResourceDescription = themeDisplay.getScopeGroupName();
					String resourcePrimKey = String.valueOf(scopeGroupId);

					if (category != null) {
						modelResource = MBCategory.class.getName();
						modelResourceDescription = category.getName();
						resourcePrimKey = String.valueOf(category.getCategoryId());
					}
					%>

					<liferay-security:permissionsURL
						modelResource="<%= modelResource %>"
						modelResourceDescription="<%= HtmlUtil.escape(modelResourceDescription) %>"
						resourcePrimKey="<%= resourcePrimKey %>"
						var="permissionsURL"
					/>

					<aui:button href="<%= permissionsURL %>" value="permissions" />
				</c:if>
			</aui:button-row>
		</c:if>

		<liferay-ui:search-iterator />
	</liferay-ui:search-container>
</c:if>

<liferay-ui:search-container
	curParam="cur2"
	deltaConfigurable="<%= false %>"
	emptyResultsMessage="there-are-no-threads"
	headerNames="statistics,thread,last-post"
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

			continue;
		}

		message = message.toEscapedModel();

		row.setBold(!MBMessageFlagLocalServiceUtil.hasReadFlag(themeDisplay.getUserId(), thread));
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
		>

			<%
			int replies = MBMessageServiceUtil.getThreadMessagesCount(scopeGroupId, categoryId, thread.getThreadId(), WorkflowConstants.STATUS_ANY) - 1;

			RatingsStats ratingsStats = RatingsStatsLocalServiceUtil.getStats(MBMessage.class.getName(), message.getMessageId());

			int ratingScore = (int)ratingsStats.getTotalScore();
			%>

			<div class="votes">
				<span class="count">
					<%= String.valueOf(ratingScore) %>
				</span>

				<span>
					<%= LanguageUtil.get(pageContext, "votes") %>
				</span>
			</div>

			<div class="status <%= (replies != 0) ? "answered" : " unanswered" %> ">
				<span class="count">
					<%= replies %>
				</span>

				<span>
					<%= LanguageUtil.get(pageContext, (replies != 1) ? "answers" : "answer") %>
				</span>
			</div>
		</liferay-ui:search-container-column-text>

		<liferay-ui:search-container-column-text
			name="thread"
		>

			<div class="result-title">
				<c:if test="<%= !row.isRestricted() %>">
					<a href="<%= rowURL %>">
				</c:if>

				<%
				String[] threadPriority = MBUtil.getThreadPriority(preferences, themeDisplay.getLanguageId(), thread.getPriority(), themeDisplay);
				%>

				<c:choose>
					<c:when test="<%= (threadPriority != null) && (thread.getPriority() > 0) %>">
						<img alt="<%= threadPriority[0] %>" class="thread-priority" src="<%= threadPriority[1] %>" title="<%= threadPriority[0] %>" />
					</c:when>
					<c:when test="<%= thread.isLocked() %>">
						<img alt="<liferay-ui:message key="thread-locked" />" class="thread-priority" src="<%= themeDisplay.getPathThemeImages() + "/common/lock.png" %>" title="<liferay-ui:message key="thread-locked" />" />
					</c:when>
				</c:choose>

				<%= message.getSubject() %>

				<c:if test="<%= !row.isRestricted() %>">
					</a>
				</c:if>
			</div>

			<div class="result-data">
				<span class="started-by">
					<liferay-ui:message key="started-by" />:

					<c:choose>
						<c:when test="<%= message.isAnonymous() %>">
							<liferay-ui:message key="anonymous" />
						</c:when>
						<c:otherwise>
							<%= HtmlUtil.escape(PortalUtil.getUserName(message.getUserId(), message.getUserName())) %>
						</c:otherwise>
					</c:choose>
				</span>

				<span class="posts">
					<liferay-ui:message key="posts" />:

					<%= thread.getMessageCount() %>
				</span>

				<span class="views">
					<liferay-ui:message key="views" />:

					<%= thread.getViewCount() %>
				</span>

				<c:if test="<%= category == null %>">

					<%
					MBCategory curCategory = null;

					try {
						curCategory = MBCategoryLocalServiceUtil.getCategory(thread.getCategoryId());
					}
					catch (NoSuchCategoryException nsce) {
					}
					%>

					<c:if test="<%= curCategory != null %>">
						<liferay-portlet:renderURL varImpl="categoryURL">
							<portlet:param name="struts_action" value="/message_boards/view" />
							<portlet:param name="mbCategoryId" value="<%= String.valueOf(curCategory.getCategoryId()) %>" />
						</liferay-portlet:renderURL>

						<span class="category">
							<liferay-ui:message key="category" />:

							<a href="<%= categoryURL %>"><%= curCategory.getName() %></a>
						</span>
					</c:if>
				</c:if>
			</div>

			<c:choose>
				<c:when test="<%= MBMessageFlagLocalServiceUtil.hasQuestionFlag(message.getMessageId()) %>">
					<div class="result-data">
						<liferay-ui:message key="not-resolved" />
					</div>
				</c:when>
				<c:when test="<%= MBMessageFlagLocalServiceUtil.hasAnswerFlag(message.getMessageId()) %>">
					<div class="result-data">
						<liferay-ui:message key="resolved" />
					</div>
				</c:when>
			</c:choose>
		</liferay-ui:search-container-column-text>

		<liferay-ui:search-container-column-text
			name="last-post"
		>
			<c:choose>
				<c:when test="<%= thread.getLastPostDate() == null %>">
					<liferay-ui:message key="none" />
				</c:when>
				<c:otherwise>
					<div class="result-title">

						<%
						String lastPostByUserName = HtmlUtil.escape(PortalUtil.getUserName(thread.getLastPostByUserId(), StringPool.BLANK));

						if (Validator.isNull(lastPostByUserName)) {
							lastPostByUserName = LanguageUtil.get(pageContext, "anonymous");
						}
						%>

						<%= lastPostByUserName %>
					</div>
					<div class="result-data">
						<%= dateFormatDateTime.format(thread.getLastPostDate()) %>
					</div>
				</c:otherwise>
			</c:choose>
		</liferay-ui:search-container-column-text>

		<liferay-ui:search-container-column-jsp
			align="right"
			path="/html/portlet/message_boards/message_action.jsp"
		/>
	</liferay-ui:search-container-row>

	<c:if test="<%= category == null %>">
		<liferay-ui:header title="recent-posts" />
	</c:if>

	<%
	boolean showAddMessageButton = false;

	if (category != null) {
		showAddMessageButton = MBCategoryPermission.contains(permissionChecker, category, ActionKeys.ADD_MESSAGE);

		if (showAddMessageButton && !themeDisplay.isSignedIn() && !allowAnonymousPosting) {
			showAddMessageButton = false;
		}
	}
	%>

	<c:if test="<%= showAddMessageButton %>">
		<aui:button-row>
			<portlet:renderURL var="editMessageURL">
				<portlet:param name="struts_action" value="/message_boards/edit_message" />
				<portlet:param name="redirect" value="<%= currentURL %>" />
				<portlet:param name="mbCategoryId" value="<%= String.valueOf(categoryId) %>" />
			</portlet:renderURL>

			<aui:button href='<%= editMessageURL %>' value="post-new-thread" />
		</aui:button-row>
	</c:if>

	<liferay-ui:search-iterator />
</liferay-ui:search-container>

<%!
private static Log _log = LogFactoryUtil.getLog("portal-web.docroot.html.portlet.message_boards.view_category_default_jsp");
%>