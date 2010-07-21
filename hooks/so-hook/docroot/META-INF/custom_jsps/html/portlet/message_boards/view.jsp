<%
/**
 * Copyright (c) 2008-2010 Liferay, Inc. All rights reserved.
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
%>

<%@ include file="/html/portlet/message_boards/init.jsp" %>

<%
String tabs1 = ParamUtil.getString(request, "tabs1", "categories");
String tabs2 = ParamUtil.getString(request, "tabs2", "general");

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
portletURL.setParameter("tabs1", tabs1);
portletURL.setParameter("tabs2", tabs2);
portletURL.setParameter("mbCategoryId", String.valueOf(categoryId));
%>

<liferay-util:include page="/html/portlet/message_boards/tabs1.jsp" />

<liferay-portlet:renderURL varImpl="searchURL"><portlet:param name="struts_action" value="/message_boards/search" /></liferay-portlet:renderURL>

<c:choose>
	<c:when test='<%= tabs1.equals("categories") %>'>
		<c:if test="<%= category != null %>">
			<div class="breadcrumbs">
				<%= getCategoryBreadcrumbs(category.getParentCategoryId(), pageContext, renderResponse) %>

				<h6><%= category.getName() %></h6>
			</div>
		</c:if>

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
					sb.append(PortalUtil.getUserName(message.getUserId(), message.getUserName()));
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
						sb.append(lastPostByUserName);
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
			%>

		</c:if>
	</c:when>
	<c:when test='<%= tabs1.equals("my_posts") || tabs1.equals("my_subscriptions") || tabs1.equals("recent_posts") %>'>

		<%
		long groupThreadsUserId = ParamUtil.getLong(request, "groupThreadsUserId");

		if ((tabs1.equals("my_posts") || tabs1.equals("my_subscriptions")) && themeDisplay.isSignedIn()) {
			groupThreadsUserId = user.getUserId();
		}

		if (groupThreadsUserId > 0) {
			portletURL.setParameter("groupThreadsUserId", String.valueOf(groupThreadsUserId));
		}
		%>

		<c:if test='<%= tabs1.equals("recent_posts") && (groupThreadsUserId > 0) %>'>
			<div class="portlet-msg-info">
				<liferay-ui:message key="filter-by-user" />: <%= PortalUtil.getUserName(groupThreadsUserId, StringPool.BLANK) %>
			</div>
		</c:if>

		<%
		int totalCategories = 0;
		%>

		<c:if test='<%= tabs1.equals("my_subscriptions") %>'>

			<%
			List<String> headerNames = new ArrayList<String>();

			headerNames.add("category");
			headerNames.add("categories");
			headerNames.add("threads");
			headerNames.add("posts");
			headerNames.add(StringPool.BLANK);

			SearchContainer searchContainer = new SearchContainer(renderRequest, null, null, "cur1", SearchContainer.DEFAULT_DELTA, portletURL, headerNames, "you-are-not-subscribed-to-any-categories");

			int total = MBCategoryLocalServiceUtil.getSubscribedCategoriesCount(scopeGroupId, user.getUserId());

			searchContainer.setTotal(total);

			totalCategories = total;

			List results = MBCategoryLocalServiceUtil.getSubscribedCategories(scopeGroupId, user.getUserId(), searchContainer.getStart(), searchContainer.getEnd());

			searchContainer.setResults(results);

			List resultRows = searchContainer.getResultRows();

			for (int i = 0; i < results.size(); i++) {
				MBCategory curCategory = (MBCategory)results.get(i);

				curCategory = curCategory.toEscapedModel();

				ResultRow row = new ResultRow(new Object[] {curCategory, categorySubscriptionClassPKs}, curCategory.getCategoryId(), i);

				boolean restricted = !MBCategoryPermission.contains(permissionChecker, curCategory, ActionKeys.VIEW);

				row.setRestricted(restricted);

				PortletURL rowURL = renderResponse.createRenderURL();

				rowURL.setParameter("struts_action", "/message_boards/view");
				rowURL.setParameter("mbCategoryId", String.valueOf(curCategory.getCategoryId()));

				// Name and description

				StringBuilder sb = new StringBuilder();

				if (!restricted) {
					sb.append("<a href=\"");
					sb.append(rowURL);
					sb.append("\">");
				}

				sb.append("<b>");
				sb.append(curCategory.getName());
				sb.append("</b>");

				if (Validator.isNotNull(curCategory.getDescription())) {
					sb.append("<br />");
					sb.append(curCategory.getDescription());
				}

				row.addText(sb.toString());

				// Statistics

				int categoriesCount = categoryDisplay.getSubcategoriesCount(curCategory);
				int threadsCount = categoryDisplay.getSubcategoriesThreadsCount(curCategory);
				int messagesCount = categoryDisplay.getSubcategoriesMessagesCount(curCategory);

				row.addText(String.valueOf(categoriesCount), rowURL);
				row.addText(String.valueOf(threadsCount), rowURL);
				row.addText(String.valueOf(messagesCount), rowURL);

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
			%>

			<liferay-ui:search-iterator searchContainer="<%= searchContainer %>" />
		</c:if>

		<%
		List<String> headerNames = new ArrayList<String>();

		headerNames.add("thread");
		headerNames.add("started-by");
		headerNames.add("posts");
		headerNames.add("views");
		headerNames.add("last-post");
		headerNames.add(StringPool.BLANK);

		String emptyResultsMessage = null;

		if (tabs1.equals("my_posts")) {
			emptyResultsMessage = "you-do-not-have-any-posts";
		}
		else if (tabs1.equals("my_subscriptions")) {
			emptyResultsMessage = "you-are-not-subscribed-to-any-threads";
		}
		else if (tabs1.equals("recent_posts")) {
			emptyResultsMessage = "there-are-no-recent-posts";
		}

		SearchContainer searchContainer = new SearchContainer(renderRequest, null, null, SearchContainer.DEFAULT_CUR_PARAM, SearchContainer.DEFAULT_DELTA, portletURL, headerNames, emptyResultsMessage);

		List results = null;

		if (tabs1.equals("my_posts")) {
			int total = MBThreadLocalServiceUtil.getGroupThreadsCount(scopeGroupId, groupThreadsUserId, WorkflowConstants.STATUS_APPROVED);

			searchContainer.setTotal(total);

			results = MBThreadLocalServiceUtil.getGroupThreads(scopeGroupId, groupThreadsUserId, WorkflowConstants.STATUS_APPROVED, searchContainer.getStart(), searchContainer.getEnd());

			searchContainer.setResults(results);
		}
		else if (tabs1.equals("my_subscriptions")) {
			int total = MBThreadLocalServiceUtil.getGroupThreadsCount(scopeGroupId, groupThreadsUserId, WorkflowConstants.STATUS_APPROVED, true);

			searchContainer.setTotal(total);

			results = MBThreadLocalServiceUtil.getGroupThreads(scopeGroupId, groupThreadsUserId, WorkflowConstants.STATUS_APPROVED, true, searchContainer.getStart(), searchContainer.getEnd());

			searchContainer.setResults(results);
		}
		else if (tabs1.equals("recent_posts")) {
			int total = MBThreadLocalServiceUtil.getGroupThreadsCount(scopeGroupId, groupThreadsUserId, WorkflowConstants.STATUS_APPROVED, false, false);

			searchContainer.setTotal(total);

			results = MBThreadLocalServiceUtil.getGroupThreads(scopeGroupId, groupThreadsUserId, WorkflowConstants.STATUS_APPROVED, false, false, searchContainer.getStart(), searchContainer.getEnd());

			searchContainer.setResults(results);
		}

		List resultRows = searchContainer.getResultRows();

		for (int i = 0; i < results.size(); i++) {
			MBThread thread = (MBThread)results.get(i);

			MBMessage message = MBMessageLocalServiceUtil.getMessage(thread.getRootMessageId());

			message = message.toEscapedModel();

			boolean readThread = MBMessageFlagLocalServiceUtil.hasReadFlag(themeDisplay.getUserId(), thread);

			ResultRow row = new ResultRow(new Object[] {message, threadSubscriptionClassPKs}, thread.getThreadId(), i, !readThread);

			row.setRestricted(!MBMessagePermission.contains(permissionChecker, message, ActionKeys.VIEW));

			PortletURL rowURL = renderResponse.createRenderURL();

			rowURL.setParameter("struts_action", "/message_boards/view_message");
			rowURL.setParameter("messageId", String.valueOf(message.getMessageId()));

			// Thread

			StringBuilder sb = new StringBuilder();

			String[] threadPriority = MBUtil.getThreadPriority(preferences, themeDisplay.getLanguageId(), thread.getPriority(), themeDisplay);

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

			row.addText(sb.toString(), rowURL);

			// Started by

			if (message.isAnonymous()) {
				row.addText(LanguageUtil.get(pageContext, "anonymous"), rowURL);
			}
			else {
				row.addText(PortalUtil.getUserName(message.getUserId(), message.getUserName()), rowURL);
			}

			// Number of posts

			row.addText(String.valueOf(thread.getMessageCount()), rowURL);

			// Number of views

			row.addText(String.valueOf(thread.getViewCount()), rowURL);

			// Last post

			if (thread.getLastPostDate() == null) {
				row.addText(LanguageUtil.get(pageContext, "none"), rowURL);
			}
			else {
				sb = new StringBuilder();

				sb.append(LanguageUtil.get(pageContext, "date"));
				sb.append(": ");
				sb.append(dateFormatDateTime.format(thread.getLastPostDate()));

				String lastPostByUserName = PortalUtil.getUserName(thread.getLastPostByUserId(), StringPool.BLANK);

				if (Validator.isNotNull(lastPostByUserName)) {
					sb.append("<br />");
					sb.append(LanguageUtil.get(pageContext, "by"));
					sb.append(": ");
					sb.append(lastPostByUserName);
				}

				row.addText(sb.toString(), rowURL);
			}

			// Action

			row.addJSP("right", SearchEntry.DEFAULT_VALIGN, "/html/portlet/message_boards/message_action.jsp");

			// Add result row

			resultRows.add(row);
		}
		%>

		<c:if test='<%= tabs1.equals("my_subscriptions") %>'>
			<br />
		</c:if>

		<liferay-ui:search-iterator searchContainer="<%= searchContainer %>" />

		<c:if test='<%= tabs1.equals("recent_posts") %>'>

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
						message="subscribe-to-recent-posts"
						url="<%= rssURL %>"
						method="get"
						target="_blank"
						label="<%= true %>"
					/>
				</td>
			</tr>
			</table>
		</c:if>

		<%
		PortalUtil.setPageSubtitle(LanguageUtil.get(pageContext, StringUtil.replace(tabs1, StringPool.UNDERLINE, StringPool.DASH)), request);
		%>

	</c:when>
	<c:when test='<%= tabs1.equals("statistics") %>'>
		<liferay-ui:tabs
			names="general,top-posters"
			param="tabs2"
			url="<%= portletURL.toString() %>"
		/>

		<c:choose>
			<c:when test='<%= tabs2.equals("general") %>'>
				<liferay-ui:message key="num-of-categories" />: <%= numberFormat.format(categoryDisplay.getAllCategoriesCount()) %><br />
				<liferay-ui:message key="num-of-posts" />: <%= numberFormat.format(MBMessageLocalServiceUtil.getGroupMessagesCount(scopeGroupId, WorkflowConstants.STATUS_APPROVED)) %><br />
				<liferay-ui:message key="num-of-participants" />: <%= numberFormat.format(MBStatsUserLocalServiceUtil.getStatsUsersByGroupIdCount(scopeGroupId)) %>
			</c:when>
			<c:when test='<%= tabs2.equals("top-posters") %>'>

				<%
				SearchContainer searchContainer = new SearchContainer(renderRequest, null, null, SearchContainer.DEFAULT_CUR_PARAM, SearchContainer.DEFAULT_DELTA, portletURL, null, "there-are-no-top-posters");

				int total = MBStatsUserLocalServiceUtil.getStatsUsersByGroupIdCount(scopeGroupId);

				searchContainer.setTotal(total);

				List results = MBStatsUserLocalServiceUtil.getStatsUsersByGroupId(scopeGroupId, searchContainer.getStart(), searchContainer.getEnd());

				searchContainer.setResults(results);

				List resultRows = searchContainer.getResultRows();

				for (int i = 0; i < results.size(); i++) {
					MBStatsUser statsUser = (MBStatsUser)results.get(i);

					ResultRow row = new ResultRow(statsUser, statsUser.getStatsUserId(), i);

					// User display

					row.addJSP("/html/portlet/message_boards/top_posters_user_display.jsp");

					// Add result row

					resultRows.add(row);
				}
				%>

				<liferay-ui:search-iterator searchContainer="<%= searchContainer %>" />
			</c:when>
		</c:choose>

		<%
		PortalUtil.setPageSubtitle(LanguageUtil.get(pageContext, StringUtil.replace(tabs1, StringPool.UNDERLINE, StringPool.DASH)), request);
		%>

	</c:when>
	<c:when test='<%= tabs1.equals("banned_users") %>'>

		<%
		List<String> headerNames = new ArrayList<String>();

		headerNames.add("banned-user");
		headerNames.add("banned-by");
		headerNames.add("ban-date");

		if (PropsValues.MESSAGE_BOARDS_EXPIRE_BAN_INTERVAL > 0) {
			headerNames.add("unban-date");
		}

		headerNames.add(StringPool.BLANK);

		SearchContainer searchContainer = new SearchContainer(renderRequest, null, null, SearchContainer.DEFAULT_CUR_PARAM, SearchContainer.DEFAULT_DELTA, portletURL, headerNames, "there-are-no-banned-users");

		int total = MBBanLocalServiceUtil.getBansCount(scopeGroupId);

		searchContainer.setTotal(total);

		List results = MBBanLocalServiceUtil.getBans(scopeGroupId, searchContainer.getStart(), searchContainer.getEnd());

		searchContainer.setResults(results);

		List resultRows = searchContainer.getResultRows();

		for (int i = 0; i < results.size(); i++) {
			MBBan ban = (MBBan)results.get(i);

			ResultRow row = new ResultRow(ban, ban.getBanId(), i);

			// Banned user

			row.addText(PortalUtil.getUserName(ban.getBanUserId(), StringPool.BLANK));

			// Banned by

			row.addText(PortalUtil.getUserName(ban.getUserId(), StringPool.BLANK));

			// Ban date

			row.addText(dateFormatDateTime.format(ban.getCreateDate()));

			// Unban date

			if (PropsValues.MESSAGE_BOARDS_EXPIRE_BAN_INTERVAL > 0) {
				row.addText(dateFormatDateTime.format(MBUtil.getUnbanDate(ban, PropsValues.MESSAGE_BOARDS_EXPIRE_BAN_INTERVAL)));
			}

			// Action

			row.addJSP("right", SearchEntry.DEFAULT_VALIGN, "/html/portlet/message_boards/ban_user_action.jsp");

			// Add result row

			resultRows.add(row);
		}
		%>

		<liferay-ui:search-iterator searchContainer="<%= searchContainer %>" />

		<%
		PortalUtil.setPageSubtitle(LanguageUtil.get(pageContext, StringUtil.replace(tabs1, StringPool.UNDERLINE, StringPool.DASH)), request);
		%>

	</c:when>
</c:choose>