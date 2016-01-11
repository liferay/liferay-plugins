<%--
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
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

<%@ include file="/init.jsp" %>

<%
List<MicroblogsEntry> microblogsEntries = (List<MicroblogsEntry>)request.getAttribute(WebKeys.MICROBLOGS_ENTRIES);

PortletURL microblogsEntriesURL = (PortletURL)request.getAttribute(WebKeys.MICROBLOGS_ENTRIES_URL);
%>

<c:if test="<%= microblogsEntries.isEmpty() %>">

	<%
	String message = LanguageUtil.get(request, "there-are-no-microblog-entries");

	Group group = themeDisplay.getScopeGroup();

	if (group.isUser()) {
		if (group.getGroupId() == user.getGroupId()) {
			message = LanguageUtil.get(request, "you-do-not-have-any-microblog-entries");
		}
		else {
			User user2 = UserLocalServiceUtil.getUser(group.getClassPK());

			message = LanguageUtil.format(request, "x-does-not-have-any-microblog-entries", HtmlUtil.escape(user2.getFullName()), false);
		}
	}
	%>

	<div class="alert alert-info">
		<%= message %>
	</div>
</c:if>

<%
if (microblogsEntries != null) {
	for (MicroblogsEntry microblogsEntry : microblogsEntries) {
		String userDisplayURL = StringPool.BLANK;
		String userFullName = HtmlUtil.escape(PortalUtil.getUserName(microblogsEntry));
		String userPortaitURL = StringPool.BLANK;
		String userScreenName = StringPool.BLANK;

		try {
			User curUser = UserLocalServiceUtil.getUserById(microblogsEntry.getUserId());

			userDisplayURL = curUser.getDisplayURL(themeDisplay);
			userPortaitURL = curUser.getPortraitURL(themeDisplay);
			userScreenName = curUser.getScreenName();
		}
		catch (NoSuchUserException nsue) {
		}
%>

		<div class="microblogs-entry" id="<portlet:namespace />microblogsEntry<%= microblogsEntry.getMicroblogsEntryId() %>">
			<span class="thumbnail">
				<a href="<%= userDisplayURL %>"><img alt="<%= HtmlUtil.escapeAttribute(userFullName) %>" src="<%= userPortaitURL %>" /></a>
			</span>

			<div class="entry-bubble">
				<div class="user-name">
					<span><a href="<%= userDisplayURL %>"><%= userFullName %></a></span>

					<c:if test="<%= microblogsEntry.getType() == MicroblogsEntryConstants.TYPE_REPOST %>">
						<span class="small"><liferay-ui:message key="reposted-from" /></span> <span><%= HtmlUtil.escape(PortalUtil.getUserName(microblogsEntry.getParentMicroblogsEntryUserId(), StringPool.BLANK)) %></span>
					</c:if>
				</div>

				<div class="content">

					<%
					String content = MicroblogsUtil.getProcessedContent(microblogsEntry, ServiceContextFactory.getInstance(request));
					%>

					<span>
						<%= content %>
					</span>
				</div>

				<div class="edit-container"><!-- --></div>

				<div class="footer">
					<span class="modified-date">
						<%= dateFormatDateTime.format(microblogsEntry.getModifiedDate()) %>
					</span>

					<span class="action-container">
						<c:if test="<%= microblogsEntry.getType() != MicroblogsEntryConstants.TYPE_REPLY %>">

							<%
							int replyCount = MicroblogsEntryLocalServiceUtil.getParentMicroblogsEntryMicroblogsEntriesCount(MicroblogsEntryConstants.TYPE_REPLY, microblogsEntry.getMicroblogsEntryId());
							%>

							<span class="action comment">
								<portlet:renderURL var="commentsURL" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
									<portlet:param name="mvcPath" value="/microblogs/view_comments.jsp" />
									<portlet:param name="parentMicroblogsEntryId" value="<%= String.valueOf(microblogsEntry.getMicroblogsEntryId()) %>" />
								</portlet:renderURL>

								<a data-microblogsEntryId="<%= microblogsEntry.getMicroblogsEntryId() %>" href="<%= commentsURL %>"><%= replyCount > 0 ? replyCount : StringPool.BLANK %> <liferay-ui:message key='<%= replyCount > 1 ? "comments" : "comment" %>' /></a>
							</span>
						</c:if>

						<c:if test="<%= (themeDisplay.getUserId() != microblogsEntry.getUserId()) && MicroblogsPermission.contains(permissionChecker, scopeGroupId, ActionKeys.ADD_ENTRY) && (microblogsEntry.getSocialRelationType() == MicroblogsEntryConstants.TYPE_EVERYONE) && (microblogsEntry.getType() != MicroblogsEntryConstants.TYPE_REPLY) %>">
							<portlet:renderURL var="repostMicroblogsEntryURL" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
								<portlet:param name="mvcPath" value="/microblogs/edit_microblogs_entry.jsp" />
								<portlet:param name="microblogsEntryId" value="<%= String.valueOf(microblogsEntry.getMicroblogsEntryId()) %>" />
								<portlet:param name="repost" value="<%= Boolean.TRUE.toString() %>" />
							</portlet:renderURL>

							<%
							String repostURL = "javascript:Liferay.Microblogs.displayPopup('" + repostMicroblogsEntryURL + "','" + LanguageUtil.get(request, "repost") + "');";
							%>

							<span class="action repost">
								<a data-microblogsEntryId="<%= microblogsEntry.getMicroblogsEntryId() %>" href="<%= repostURL %>"><liferay-ui:message key="repost" /></a>
							</span>
						</c:if>

						<c:if test="<%= (microblogsEntry.getType() != MicroblogsEntryConstants.TYPE_REPOST) && (microblogsEntry.getType() != MicroblogsEntryConstants.TYPE_REPLY) && MicroblogsEntryPermission.contains(permissionChecker, microblogsEntry.getMicroblogsEntryId(), ActionKeys.UPDATE) %>">
							<span class="action edit">
								<portlet:renderURL var="updateMicroblogsEntryURL" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
									<portlet:param name="mvcPath" value="/microblogs/edit_microblogs_entry.jsp" />
									<portlet:param name="redirect" value="<%= microblogsEntriesURL.toString() %>" />
									<portlet:param name="microblogsEntryId" value="<%= String.valueOf(microblogsEntry.getMicroblogsEntryId()) %>" />
									<portlet:param name="edit" value="<%= Boolean.TRUE.toString() %>" />
								</portlet:renderURL>

								<a data-microblogsEntryId="<%= microblogsEntry.getMicroblogsEntryId() %>" href="<%= updateMicroblogsEntryURL %>"><liferay-ui:message key="edit" /></a>
							</span>
						</c:if>

						<c:if test="<%= MicroblogsEntryPermission.contains(permissionChecker, microblogsEntry.getMicroblogsEntryId(), ActionKeys.DELETE) %>">
							<span class="action delete">
								<portlet:actionURL name="deleteMicroblogsEntry" var="deleteURL" windowState="<%= LiferayWindowState.NORMAL.toString() %>">
									<portlet:param name="microblogsEntryId" value="<%= String.valueOf(microblogsEntry.getMicroblogsEntryId()) %>" />
								</portlet:actionURL>

								<a href="<%= deleteURL %>"><liferay-ui:message key="delete" /></a>
							</span>
						</c:if>
					</span>
				</div>
			</div>

			<div class="comments-container reply" id="<portlet:namespace />commentsContainer<%= microblogsEntry.getMicroblogsEntryId() %>"><!-- --></div>
		</div>

<%
	}
}
%>