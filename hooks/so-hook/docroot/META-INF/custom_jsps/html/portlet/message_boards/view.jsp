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

<%@ include file="/html/portlet/message_boards/init.jsp" %>

<%
String topLink = ParamUtil.getString(request, "topLink", "message-boards-home");

String redirect = ParamUtil.getString(request, "redirect");

MBCategory category = (MBCategory)request.getAttribute(WebKeys.MESSAGE_BOARDS_CATEGORY);

long categoryId = MBUtil.getCategoryId(request, category);

boolean defaultShowCategories = false;

if (categoryId == MBCategoryConstants.DEFAULT_PARENT_CATEGORY_ID) {
	defaultShowCategories = true;
}

boolean showCategories = ParamUtil.getBoolean(request, "showCategories", defaultShowCategories);

MBCategoryDisplay categoryDisplay = new MBCategoryDisplayImpl(scopeGroupId, categoryId);

Set<Long> categorySubscriptionClassPKs = null;
Set<Long> threadSubscriptionClassPKs = null;

if (themeDisplay.isSignedIn()) {
	List<Subscription> categorySubscriptions = SubscriptionLocalServiceUtil.getUserSubscriptions(user.getUserId(), MBCategory.class.getName());

	categorySubscriptionClassPKs = new HashSet<Long>(categorySubscriptions.size());

	for (Subscription subscription : categorySubscriptions) {
		categorySubscriptionClassPKs.add(subscription.getClassPK());
	}

	threadSubscriptionClassPKs = new HashSet<Long>();

	List<Subscription> threadSubscriptions = SubscriptionLocalServiceUtil.getUserSubscriptions(user.getUserId(), MBThread.class.getName());

	threadSubscriptionClassPKs = new HashSet<Long>(threadSubscriptions.size());

	for (Subscription subscription : threadSubscriptions) {
		threadSubscriptionClassPKs.add(subscription.getClassPK());
	}
}

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("struts_action", "/message_boards/view");
portletURL.setParameter("topLink", topLink);
portletURL.setParameter("mbCategoryId", String.valueOf(categoryId));
%>

<liferay-util:include page="/html/portlet/message_boards/sidebar.jsp" />

<liferay-portlet:renderURL varImpl="searchURL"><portlet:param name="struts_action" value="/message_boards/search" /></liferay-portlet:renderURL>

<c:choose>
	<c:when test='<%= topLink.equals("message-boards-home") %>'>
		<liferay-ui:header title='<%= (category != null) ? category.getName() : "forums" %>' />

		<c:if test="<%= showCategories || categoryId == MBCategoryConstants.DEFAULT_PARENT_CATEGORY_ID %>">
			<form action="<%= searchURL %>" method="get" name="<portlet:namespace />fm1" onSubmit="submitForm(this); return false;">
			<liferay-portlet:renderURLParams varImpl="searchURL" />
			<input name="<portlet:namespace />redirect" type="hidden" value="<%= HtmlUtil.escapeAttribute(currentURL) %>" />
			<input name="<portlet:namespace />breadcrumbsCategoryId" type="hidden" value="<%= categoryId %>" />
			<input name="<portlet:namespace />searchCategoryIds" type="hidden" value="<%= categoryId %>" />

			<%
			List<String> headerNames = new ArrayList<String>();

			headerNames.add("category");
			headerNames.add(StringPool.BLANK);

			SearchContainer searchContainer = new SearchContainer(renderRequest, null, null, "cur1", SearchContainer.DEFAULT_DELTA, portletURL, headerNames, "there-are-no-categories");

			List results = categoryDisplay.getCategories();

			int total = results.size();

			searchContainer.setTotal(total);

			results = ListUtil.subList(results, searchContainer.getStart(), searchContainer.getEnd());

			searchContainer.setResults(results);

			List resultRows = searchContainer.getResultRows();

			for (int i = 0; i < results.size(); i++) {
				MBCategory curCategory = (MBCategory)results.get(i);

				curCategory = curCategory.toEscapedModel();

				ResultRow row = new ResultRow(curCategory, curCategory.getCategoryId(), i);

				boolean restricted = !MBCategoryPermission.contains(permissionChecker, curCategory, ActionKeys.VIEW);

				row.setRestricted(restricted);

				PortletURL rowURL = renderResponse.createRenderURL();

				rowURL.setParameter("struts_action", "/message_boards/view");
				rowURL.setParameter("mbCategoryId", String.valueOf(curCategory.getCategoryId()));

				// Name and description

				StringBuilder sb = new StringBuilder();

				sb.append("<div class=\"result-title\">");

				if (!restricted) {
					sb.append("<a href=\"");
					sb.append(rowURL);
					sb.append("\">");
				}

				sb.append(curCategory.getName());

				if (!restricted) {
					sb.append("</a>");
				}

				sb.append("</div>");

				if (Validator.isNotNull(curCategory.getDescription())) {
					sb.append("<div class=\"result-data\">");
					sb.append(curCategory.getDescription());
					sb.append("</div>");
				}

				int threadsCount = categoryDisplay.getSubcategoriesThreadsCount(curCategory);
				int messagesCount = categoryDisplay.getSubcategoriesMessagesCount(curCategory);

				sb.append("<div class=\"result-data\"><span class=\"threads\">");
				sb.append(LanguageUtil.get(pageContext, "threads"));
				sb.append(": ");
				sb.append(String.valueOf(threadsCount));
				sb.append("</span><span class=\"messages\">");
				sb.append(LanguageUtil.get(pageContext, "messages"));
				sb.append(": ");
				sb.append(String.valueOf(messagesCount));
				sb.append("</span>");

				if (!restricted) {
					List subcategories = categoryDisplay.getCategories(curCategory);

					int subcategoriesCount = subcategories.size();

					subcategories = ListUtil.subList(subcategories, 0, 3);

					if (subcategoriesCount > 0) {
						sb.append("<span class=\"result-data\">");
						sb.append(LanguageUtil.get(pageContext, "subcategories"));
						sb.append(": ");

						for (int j = 0; j < subcategories.size(); j++) {
							MBCategory subcategory = (MBCategory)subcategories.get(j);

							rowURL.setParameter("mbCategoryId", String.valueOf(subcategory.getCategoryId()));

							sb.append("<a href=\"");
							sb.append(rowURL);
							sb.append("\">");
							sb.append(subcategory.getName());
							sb.append("</a>");

							if ((j + 1) < subcategories.size()) {
								sb.append(", ");
							}
						}

						if (subcategoriesCount > subcategories.size()) {
							rowURL.setParameter("mbCategoryId", String.valueOf(curCategory.getCategoryId()));

							sb.append(", <a href=\"");
							sb.append(rowURL);
							sb.append("\">");
							sb.append(String.valueOf(subcategoriesCount - 3));
							sb.append(StringPool.SPACE);
							sb.append(LanguageUtil.get(pageContext, "more"));
							sb.append(" &raquo;");
							sb.append("</a>");
						}

						sb.append("</span>");

						rowURL.setParameter("mbCategoryId", String.valueOf(curCategory.getCategoryId()));
					}
				}

				sb.append("</div>");

				row.addText(sb.toString());

				// Action

				if (restricted) {
					row.addText(StringPool.BLANK);
				}
				else {
					row.addJSP("right", SearchEntry.DEFAULT_VALIGN, "/html/portlet/message_boards/category_action.jsp");
				}

				// Add result row

				resultRows.add(row);
			}

			boolean showAddCategoryButton = MBCategoryPermission.contains(permissionChecker, scopeGroupId, categoryId, ActionKeys.ADD_CATEGORY);
			boolean showPermissionsButton = GroupPermissionUtil.contains(permissionChecker, scopeGroupId, ActionKeys.PERMISSIONS);
			%>

			<c:if test="<%= showAddCategoryButton || showPermissionsButton %>">
				<div>
					<c:if test="<%= showAddCategoryButton %>">
						<input type="button" value="<liferay-ui:message key='<%= (category == null) ? "add-category" : "add-subcategory" %>' />" onClick="<portlet:namespace />addCategory();" />
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

						<input type="button" value="<liferay-ui:message key="permissions" />" onClick="location.href = '<%= permissionsURL %>';" />
					</c:if>
				</div>

				<br />
			</c:if>

			<liferay-ui:search-iterator searchContainer="<%= searchContainer %>" />

			</form>

			<br />
		</c:if>

		<script type="text/javascript">
			function <portlet:namespace />addCategory() {
				var url = '<portlet:renderURL><portlet:param name="struts_action" value="/message_boards/edit_category" /><portlet:param name="redirect" value="<%= currentURL %>" /><portlet:param name="parentCategoryId" value="<%= String.valueOf(categoryId) %>" /></portlet:renderURL>';

				submitForm(document.hrefFm, url);
			}

			<c:if test="<%= windowState.equals(WindowState.MAXIMIZED) && !themeDisplay.isFacebook() %>">
				Liferay.Util.focusFormField(document.<portlet:namespace />fm1.<portlet:namespace />keywords);
			</c:if>
		</script>

		<c:if test="<%= category != null %>">
			<form action="<%= searchURL %>" method="get" name="<portlet:namespace />fm2" onSubmit="submitForm(this); return false;">
			<liferay-portlet:renderURLParams varImpl="searchURL" />
			<input name="<portlet:namespace />redirect" type="hidden" value="<%= currentURL %>" />
			<input name="<portlet:namespace />breadcrumbsCategoryId" type="hidden" value="<%= categoryId %>" />
			<input name="<portlet:namespace />searchCategoryId" type="hidden" value="<%= categoryId %>" />

			<%
			List<String> headerNames = new ArrayList<String>();

			headerNames.add("thread");
			headerNames.add("last-post");
			headerNames.add(StringPool.BLANK);

			SearchContainer searchContainer = new SearchContainer(renderRequest, null, null, "cur2", SearchContainer.DEFAULT_DELTA, portletURL, headerNames, "there-are-no-threads");

			int total = MBThreadLocalServiceUtil.getThreadsCount(scopeGroupId, categoryId, WorkflowConstants.STATUS_APPROVED);

			searchContainer.setTotal(total);

			List results = MBThreadLocalServiceUtil.getThreads(scopeGroupId, categoryId, WorkflowConstants.STATUS_APPROVED, searchContainer.getStart(), searchContainer.getEnd());

			searchContainer.setResults(results);

			List resultRows = searchContainer.getResultRows();

			for (int i = 0; i < results.size(); i++) {
				MBThread thread = (MBThread)results.get(i);

				MBMessage message = MBMessageLocalServiceUtil.getMessage(thread.getRootMessageId());

				message = message.toEscapedModel();

				boolean readThread = MBMessageFlagLocalServiceUtil.hasReadFlag(themeDisplay.getUserId(), thread);

				ResultRow row = new ResultRow(new Object[] {message, threadSubscriptionClassPKs}, thread.getThreadId(), i, !readThread);

				row.setRestricted(!MBMessagePermission.contains(permissionChecker, message, ActionKeys.VIEW));

				boolean restricted = !MBMessagePermission.contains(permissionChecker, message, ActionKeys.VIEW);

				PortletURL rowURL = renderResponse.createRenderURL();

				rowURL.setParameter("struts_action", "/message_boards/view_message");
				rowURL.setParameter("messageId", String.valueOf(message.getMessageId()));

				// Thread

				StringBuilder sb = new StringBuilder();

				String[] threadPriority = MBUtil.getThreadPriority(preferences, themeDisplay.getLanguageId(), thread.getPriority(), themeDisplay);

				sb.append("<div class=\"result-title\">");

				if (!restricted) {
					sb.append("<a href=\"");
					sb.append(rowURL);
					sb.append("\">");
				}

				if ((threadPriority != null) && (thread.getPriority() > 0)) {
					sb.append("<img align=\"left\" alt=\"");
					sb.append(threadPriority[0]);
					sb.append("\" border=\"0\" src=\"");
					sb.append(threadPriority[1]);
					sb.append("\" title=\"");
					sb.append(threadPriority[0]);
					sb.append("\" />");
				}

				sb.append(message.getSubject());

				if (!restricted) {
					sb.append("</a>");
				}

				sb.append("</div><div class=\"result-data\"><span class=\"started-by\">");
				sb.append(LanguageUtil.get(pageContext, "started-by"));
				sb.append(": ");

				if (message.isAnonymous()) {
					sb.append(LanguageUtil.get(pageContext, "anonymous"));
				}
				else {
					String userName = PortalUtil.getUserName(message.getUserId(), message.getUserName());

					sb.append(HtmlUtil.escape(userName));
				}

				sb.append("</span><span class=\"posts\">");
				sb.append(LanguageUtil.get(pageContext, "posts"));
				sb.append(": ");
				sb.append(String.valueOf(thread.getMessageCount()));
				sb.append("</span><span class=\"views\">");
				sb.append(LanguageUtil.get(pageContext, "views"));
				sb.append(": ");
				sb.append(String.valueOf(thread.getViewCount()));
				sb.append("</span></div>");
				sb.append("<div class=\"result-data\">");

				if (MBMessageFlagLocalServiceUtil.hasQuestionFlag(message.getMessageId())) {
					sb.append(LanguageUtil.get(pageContext, "waiting-for-an-answer"));
				}
				if (MBMessageFlagLocalServiceUtil.hasAnswerFlag(message.getMessageId())) {
					sb.append(LanguageUtil.get(pageContext, "resolved"));
				}

				sb.append("</div>");

				row.addText(sb.toString());

				// Last post

				if (thread.getLastPostDate() == null) {
					row.addText(LanguageUtil.get(pageContext, "none"), rowURL);
				}
				else {
					sb = new StringBuilder();

					String lastPostByUserName = PortalUtil.getUserName(thread.getLastPostByUserId(), StringPool.BLANK);

					sb.append("<div class=\"result-title\">");

					if (Validator.isNotNull(lastPostByUserName)) {
						sb.append(HtmlUtil.escape(lastPostByUserName));
					}
					else {
						sb.append(LanguageUtil.get(pageContext, "anonymous"));
					}

					sb.append("</div><div class=\"result-data\">");
					sb.append(dateFormatDateTime.format(thread.getLastPostDate()));
					sb.append("</div>");

					row.addText(sb.toString());
				}

				// Action

				row.addJSP("right", SearchEntry.DEFAULT_VALIGN, "/html/portlet/message_boards/message_action.jsp");

				// Add result row

				resultRows.add(row);
			}

			boolean showAddMessageButton = MBCategoryPermission.contains(permissionChecker, category, ActionKeys.ADD_MESSAGE);

			if (showAddMessageButton && !themeDisplay.isSignedIn()) {
				if (!allowAnonymousPosting) {
					showAddMessageButton = false;
				}
			}
			%>

			<c:if test="<%= showAddMessageButton %>">
				<div>
					<input type="button" value="<liferay-ui:message key="post-new-thread" />" onClick="<portlet:namespace />addMessage();" />
				</div>

				<br />
			</c:if>

			<liferay-ui:search-iterator searchContainer="<%= searchContainer %>" />

			</form>

			<script type="text/javascript">
				function <portlet:namespace />addMessage() {
					var url = '<portlet:renderURL><portlet:param name="struts_action" value="/message_boards/edit_message" /><portlet:param name="redirect" value="<%= currentURL %>" /><portlet:param name="mbCategoryId" value="<%= String.valueOf(categoryId) %>" /></portlet:renderURL>';

					if (document.<portlet:namespace />fm2.<portlet:namespace />keywords) {
						url += '&<portlet:namespace />subject=' + document.<portlet:namespace />fm2.<portlet:namespace />keywords.value;
					}

					submitForm(document.hrefFm, url);
				}

				<c:if test="<%= windowState.equals(WindowState.MAXIMIZED) && !themeDisplay.isFacebook() %>">
					Liferay.Util.focusFormField(document.<portlet:namespace />fm2.<portlet:namespace />keywords);
					Liferay.Util.focusFormField(document.<portlet:namespace />fm1.<portlet:namespace />keywords);
				</c:if>
			</script>

			<%
			PortalUtil.setPageSubtitle(category.getName(), request);
			PortalUtil.setPageDescription(category.getDescription(), request);

			MBUtil.addPortletBreadcrumbEntries(category, request, renderResponse);
			%>

		</c:if>
	</c:when>
	<c:when test='<%= topLink.equals("my-posts") || topLink.equals("my-subscriptions") || topLink.equals("recent-posts") %>'>
		<liferay-ui:header title="<%= topLink %>" />

		<%
		long groupThreadsUserId = ParamUtil.getLong(request, "groupThreadsUserId");

		if ((topLink.equals("my-posts") || topLink.equals("my-subscriptions")) && themeDisplay.isSignedIn()) {
			groupThreadsUserId = user.getUserId();
		}

		if (groupThreadsUserId > 0) {
			portletURL.setParameter("groupThreadsUserId", String.valueOf(groupThreadsUserId));
		}
		%>

		<c:if test='<%= topLink.equals("recent-posts") && (groupThreadsUserId > 0) %>'>
			<div class="portlet-msg-info">
				<liferay-ui:message key="filter-by-user" />: <%= HtmlUtil.escape(PortalUtil.getUserName(groupThreadsUserId, StringPool.BLANK)) %>
			</div>
		</c:if>

		<c:if test='<%= topLink.equals("my-subscriptions") %>'>
			<liferay-ui:search-container
				curParam="cur1"
				deltaConfigurable="<%= false %>"
				emptyResultsMessage="you-are-not-subscribed-to-any-categories"
				headerNames="category,categories,threads,posts"
				iteratorURL="<%= portletURL %>"
			>
				<liferay-ui:search-container-results
					results="<%= MBCategoryServiceUtil.getSubscribedCategories(scopeGroupId, user.getUserId(), searchContainer.getStart(), searchContainer.getEnd()) %>"
					total="<%= MBCategoryServiceUtil.getSubscribedCategoriesCount(scopeGroupId, user.getUserId()) %>"
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

					<%@ include file="/html/portlet/message_boards/subscribed_category_columns.jspf" %>
				</liferay-ui:search-container-row>

				<liferay-ui:search-iterator type="more" />
			</liferay-ui:search-container>
		</c:if>

		<liferay-ui:search-container
			headerNames="thread,started-by,posts,views,last-post"
			iteratorURL="<%= portletURL %>"
		>

			<%
			String emptyResultsMessage = null;

			if (topLink.equals("my-posts")) {
				emptyResultsMessage = "you-do-not-have-any-posts";
			}
			else if (topLink.equals("my-subscriptions")) {
				emptyResultsMessage = "you-are-not-subscribed-to-any-threads";
			}
			else if (topLink.equals("recent-posts")) {
				emptyResultsMessage = "there-are-no-recent-posts";
			}

			searchContainer.setEmptyResultsMessage(emptyResultsMessage);
			%>

			<liferay-ui:search-container-results>

				<%
				if (topLink.equals("my-posts")) {
					results = MBThreadServiceUtil.getGroupThreads(scopeGroupId, groupThreadsUserId, WorkflowConstants.STATUS_ANY, searchContainer.getStart(), searchContainer.getEnd());
					total = MBThreadServiceUtil.getGroupThreadsCount(scopeGroupId, groupThreadsUserId, WorkflowConstants.STATUS_ANY);
				}
				else if (topLink.equals("my-subscriptions")) {
					results = MBThreadServiceUtil.getGroupThreads(scopeGroupId, groupThreadsUserId, WorkflowConstants.STATUS_APPROVED, true, searchContainer.getStart(), searchContainer.getEnd());
					total = MBThreadServiceUtil.getGroupThreadsCount(scopeGroupId, groupThreadsUserId, WorkflowConstants.STATUS_APPROVED, true);
				}
				else if (topLink.equals("recent-posts")) {
					results = MBThreadServiceUtil.getGroupThreads(scopeGroupId, groupThreadsUserId, WorkflowConstants.STATUS_APPROVED, false, false, searchContainer.getStart(), searchContainer.getEnd());
					total = MBThreadServiceUtil.getGroupThreadsCount(scopeGroupId, groupThreadsUserId, WorkflowConstants.STATUS_APPROVED, false, false);
				}

				pageContext.setAttribute("results", results);
				pageContext.setAttribute("total", total);
				%>

			</liferay-ui:search-container-results>

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

				boolean readThread = MBMessageFlagLocalServiceUtil.hasReadFlag(themeDisplay.getUserId(), thread);

				row.setBold(!readThread);
				row.setObject(new Object[] {message, threadSubscriptionClassPKs});
				row.setRestricted(!MBMessagePermission.contains(permissionChecker, message, ActionKeys.VIEW));
				%>

				<liferay-portlet:renderURL varImpl="rowURL">
					<portlet:param name="struts_action" value="/message_boards/view_message" />
					<portlet:param name="messageId" value="<%= String.valueOf(message.getMessageId()) %>" />
				</liferay-portlet:renderURL>

				<liferay-ui:search-container-column-text
					buffer="buffer"
					href="<%= rowURL %>"
					name="thread"
				>

					<%
					String[] threadPriority = MBUtil.getThreadPriority(preferences, themeDisplay.getLanguageId(), thread.getPriority(), themeDisplay);

					if ((threadPriority != null) && (thread.getPriority() > 0)) {
						buffer.append("<img class=\"thread-priority\" alt=\"");
						buffer.append(threadPriority[0]);
						buffer.append("\" src=\"");
						buffer.append(threadPriority[1]);
						buffer.append("\" title=\"");
						buffer.append(threadPriority[0]);
						buffer.append("\" />");
					}

					buffer.append(message.getSubject());
					%>

				</liferay-ui:search-container-column-text>

				<liferay-ui:search-container-column-text
					href="<%= rowURL %>"
					name="started-by"
					value='<%= message.isAnonymous() ? LanguageUtil.get(pageContext, "anonymous") : HtmlUtil.escape(PortalUtil.getUserName(message.getUserId(), message.getUserName())) %>'
				/>

				<liferay-ui:search-container-column-text
					href="<%= rowURL %>"
					name="posts"
					value="<%= String.valueOf(thread.getMessageCount()) %>"
				/>

				<liferay-ui:search-container-column-text
					href="<%= rowURL %>"
					name="views"
					value="<%= String.valueOf(thread.getViewCount()) %>"
				/>

				<liferay-ui:search-container-column-text
					buffer="buffer"
					href="<%= rowURL %>"
					name="last-post"
				>

					<%
					if (thread.getLastPostDate() == null) {
						buffer.append(LanguageUtil.get(pageContext, "none"));
					}
					else {
						buffer.append(LanguageUtil.get(pageContext, "date"));
						buffer.append(": ");
						buffer.append(dateFormatDateTime.format(thread.getLastPostDate()));

						String lastPostByUserName = HtmlUtil.escape(PortalUtil.getUserName(thread.getLastPostByUserId(), StringPool.BLANK));

						if (Validator.isNotNull(lastPostByUserName)) {
							buffer.append("<br />");
							buffer.append(LanguageUtil.get(pageContext, "by"));
							buffer.append(": ");
							buffer.append(lastPostByUserName);
						}
					}
					%>

				</liferay-ui:search-container-column-text>

				<c:if test='<%= topLink.equals("my-posts") %>'>
					<liferay-ui:search-container-column-text
						href="<%= rowURL %>"
						name="status"
						value="<%= LanguageUtil.get(pageContext, WorkflowConstants.toLabel(message.getStatus())) %>"
					/>
				</c:if>

				<liferay-ui:search-container-column-jsp
					align="right"
					path="/html/portlet/message_boards/message_action.jsp"
				/>
			</liferay-ui:search-container-row>

			<liferay-ui:search-iterator />
		</liferay-ui:search-container>

		<c:if test='<%= topLink.equals("recent-posts") %>'>

			<%
			String rssURL = themeDisplay.getPortalURL() + themeDisplay.getPathMain() + "/message_boards/rss?p_l_id=" + plid + "&groupId=" + scopeGroupId;

			if (groupThreadsUserId > 0) {
				rssURL += "&userId=" + groupThreadsUserId;
			}

			rssURL += rssURLParams;
			%>

			<br />

			<table class="lfr-table">
			<tr>
				<td>
					<liferay-ui:icon
						image="rss"
						label="<%= true %>"
						message="subscribe-to-recent-posts"
						method="get"
						target="_blank"
						url="<%= rssURL %>"
					/>
				</td>
			</tr>
			</table>
		</c:if>

		<%
		PortalUtil.setPageSubtitle(LanguageUtil.get(pageContext, StringUtil.replace(topLink, StringPool.UNDERLINE, StringPool.DASH)), request);
		PortalUtil.addPortletBreadcrumbEntry(request, LanguageUtil.get(pageContext, TextFormatter.format(topLink, TextFormatter.O)), portletURL.toString());
		%>

	</c:when>
	<c:when test='<%= topLink.equals("statistics") %>'>
		<liferay-ui:header title="statistics" />

		<liferay-ui:panel-container cssClass="statistics-panel" extended="<%= false %>" id="messageBoardsStatisticsPanelContainer" persistState="<%= true %>">
			<liferay-ui:panel collapsible="<%= true %>" cssClass="statistics-panel-content" extended="<%= true %>" id="messageBoardsGeneralStatisticsPanel" persistState="<%= true %>" title="general">
				<dl>
					<dt>
						<liferay-ui:message key="num-of-categories" />:
					</dt>
					<dd>
						<%= numberFormat.format(categoryDisplay.getAllCategoriesCount()) %>
					</dd>
					<dt>
						<liferay-ui:message key="num-of-posts" />:
					</dt>
					<dd>
						<%= numberFormat.format(MBMessageLocalServiceUtil.getGroupMessagesCount(scopeGroupId, WorkflowConstants.STATUS_APPROVED)) %>
					</dd>
					<dt>
						<liferay-ui:message key="num-of-participants" />:
					</dt>
					<dd>
						<%= numberFormat.format(MBStatsUserLocalServiceUtil.getStatsUsersByGroupIdCount(scopeGroupId)) %>
					</dd>
				</dl>
			</liferay-ui:panel>

			<liferay-ui:panel collapsible="<%= true %>" cssClass="statistics-panel-content" extended="<%= true %>" id="messageBoardsTopPostersPanel" persistState="<%= true %>" title="top-posters">
				<liferay-ui:search-container
					emptyResultsMessage="there-are-no-top-posters"
					iteratorURL="<%= portletURL %>"
				>
					<liferay-ui:search-container-results
						results="<%= MBStatsUserLocalServiceUtil.getStatsUsersByGroupId(scopeGroupId, searchContainer.getStart(), searchContainer.getEnd()) %>"
						total="<%= MBStatsUserLocalServiceUtil.getStatsUsersByGroupIdCount(scopeGroupId) %>"
					/>

					<liferay-ui:search-container-row
						className="com.liferay.portlet.messageboards.model.MBStatsUser"
						keyProperty="statsUserId"
						modelVar="statsUser"
					>
						<liferay-ui:search-container-column-jsp
							path="/html/portlet/message_boards/top_posters_user_display.jsp"
						/>
					</liferay-ui:search-container-row>

					<liferay-ui:search-iterator />
				</liferay-ui:search-container>
			</liferay-ui:panel>
		</liferay-ui:panel-container>

		<%
		PortalUtil.setPageSubtitle(LanguageUtil.get(pageContext, StringUtil.replace(topLink, StringPool.UNDERLINE, StringPool.DASH)), request);
		PortalUtil.addPortletBreadcrumbEntry(request, LanguageUtil.get(pageContext, TextFormatter.format(topLink, TextFormatter.O)), portletURL.toString());
		%>

	</c:when>
	<c:when test='<%= topLink.equals("banned-users") %>'>
		<liferay-ui:header title="banned-users" />

		<liferay-ui:search-container
			emptyResultsMessage="there-are-no-banned-users"
			headerNames="banned-user,banned-by,ban-date"
			iteratorURL="<%= portletURL %>"
		>
			<liferay-ui:search-container-results
				results="<%= MBBanLocalServiceUtil.getBans(scopeGroupId, searchContainer.getStart(), searchContainer.getEnd()) %>"
				total="<%= MBBanLocalServiceUtil.getBansCount(scopeGroupId) %>"
			/>

			<liferay-ui:search-container-row
				className="com.liferay.portlet.messageboards.model.MBBan"
				keyProperty="banId"
				modelVar="ban"
			>
				<liferay-ui:search-container-column-text
					name="banned-user"
					value="<%= HtmlUtil.escape(PortalUtil.getUserName(ban.getBanUserId(), StringPool.BLANK)) %>"
				/>

				<liferay-ui:search-container-column-text
					name="banned-by"
					value="<%= HtmlUtil.escape(PortalUtil.getUserName(ban.getUserId(), StringPool.BLANK)) %>"
				/>

				<liferay-ui:search-container-column-text
					name="ban-date"
					value="<%= dateFormatDateTime.format(ban.getCreateDate()) %>"
				/>

				<c:if test="<%= PropsValues.MESSAGE_BOARDS_EXPIRE_BAN_INTERVAL > 0 %>">
					<liferay-ui:search-container-column-text
						name="unban-date"
						value="<%= dateFormatDateTime.format(MBUtil.getUnbanDate(ban, PropsValues.MESSAGE_BOARDS_EXPIRE_BAN_INTERVAL)) %>"
					/>
				</c:if>

				<liferay-ui:search-container-column-jsp
					align="right"
					path="/html/portlet/message_boards/ban_user_action.jsp"
				/>
			</liferay-ui:search-container-row>

			<liferay-ui:search-iterator />
		</liferay-ui:search-container>

		<%
		PortalUtil.setPageSubtitle(LanguageUtil.get(pageContext, StringUtil.replace(topLink, StringPool.UNDERLINE, StringPool.DASH)), request);
		PortalUtil.addPortletBreadcrumbEntry(request, LanguageUtil.get(pageContext, TextFormatter.format(topLink, TextFormatter.O)), portletURL.toString());
		%>

	</c:when>
</c:choose>

<%!
private static Log _log = LogFactoryUtil.getLog("portal-web.docroot.html.portlet.message_boards.view_jsp");
%>