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

<%@ include file="/init.jsp" %>

<%
List<MicroblogsEntry> microblogsEntries = (List<MicroblogsEntry>)request.getAttribute(WebKeys.MICROBLOGS_ENTRIES);

PortletURL microblogsEntriesURL = (PortletURL)request.getAttribute(WebKeys.MICROBLOGS_ENTRIES_URL);
%>

<c:if test="<%= microblogsEntries.isEmpty() %>">
	<div class="portlet-msg-info">
		<liferay-ui:message key="you-have-no-microblogs-entry" />
	</div>
</c:if>

<%
for (MicroblogsEntry microblogsEntry : microblogsEntries) {
	String userDisplayURL = StringPool.BLANK;
	String userFullName = PortalUtil.getUserName(microblogsEntry.getUserId(), microblogsEntry.getUserName());
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

	<div class="microblogs-entry" id="microblogsEntry<%= microblogsEntry.getMicroblogsEntryId() %>">
		<span class="thumbnail">
			<a href="<%= userDisplayURL %>"><img alt="<%= userFullName %>" src="<%= userPortaitURL %>" /></a>
		</span>

		<div class="entry-bubble">
			<div class="user-name">
				<span><a href="<%= userDisplayURL %>"><%= userFullName %></a></span>

				<c:choose>
					<c:when test="<%= microblogsEntry.getType() == MicroblogsEntryConstants.TYPE_REPOST %>">
						<span class="small"><liferay-ui:message key="reposted-from" /></span> <span><%= PortalUtil.getUserName(microblogsEntry.getReceiverUserId(), StringPool.BLANK) %></span>
					</c:when>
					<c:otherwise>
						<span class="small"><liferay-ui:message key="says" /></span>
					</c:otherwise>
				</c:choose>
			</div>

			<div class="content">

				<%
				String content = microblogsEntry.getContent();

				Pattern pattern = Pattern.compile("\\#\\S*");

				Matcher matcher = pattern.matcher(content);

				while (matcher.find()) {
					String result = matcher.group();

					String assetTagName = result.substring(1);

					PortletURL viewURL = renderResponse.createRenderURL();

					viewURL.setWindowState(LiferayWindowState.NORMAL);

					viewURL.setParameter("jspPage", "/microblogs/view.jsp");
					viewURL.setParameter("tabs1", assetTagName);
					viewURL.setParameter("assetTagName", assetTagName);

					content = StringUtil.replace(content, result, "<a href=\"" + viewURL + "\">" + assetTagName + "</a>");
				}

				pattern = Pattern.compile("\\[\\@\\S*\\]");

				matcher = pattern.matcher(content);

				while (matcher.find()) {
					String result = matcher.group();

					String assetTagName = result.replace("[@", StringPool.BLANK);

					assetTagName = assetTagName.replace("]", StringPool.BLANK);

					try {
						User taggedUser = UserLocalServiceUtil.getUserByScreenName(microblogsEntry.getCompanyId(), assetTagName);

						assetTagName = PortalUtil.getUserName(taggedUser.getUserId(), assetTagName);

						content = StringUtil.replace(content, result, "<a href=\"" + taggedUser.getDisplayURL(themeDisplay) + "\">" + assetTagName + "</a>");
					}
					catch (NoSuchUserException nsue) {
					}
				}
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
						int replyCount = MicroblogsEntryLocalServiceUtil.getReceiverMicroblogsEntryMicroblogsEntriesCount(MicroblogsEntryConstants.TYPE_REPLY, microblogsEntry.getMicroblogsEntryId());
						%>

						<portlet:renderURL var="commentsURL" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
							<portlet:param name="jspPage" value="/microblogs/view_comments.jsp" />
							<portlet:param name="receiverMicroblogsEntryId" value="<%= String.valueOf(microblogsEntry.getMicroblogsEntryId()) %>" />
						</portlet:renderURL>

						<span class="action comment">
							<a data-microblogsEntryId="<%= microblogsEntry.getMicroblogsEntryId() %>" href="<%= commentsURL %>"><%= replyCount > 0 ? replyCount : StringPool.BLANK %> <liferay-ui:message key='<%= replyCount > 1 ? "comments" : "comment" %>' /></a>
						</span>
					</c:if>

					<c:if test="<%= themeDisplay.getUserId() != microblogsEntry.getUserId() && MicroblogsPermission.contains(permissionChecker, scopeGroupId, ActionKeys.ADD_ENTRY) && (microblogsEntry.getSocialRelationType() == MicroblogsEntryConstants.TYPE_EVERYONE) && (microblogsEntry.getType() != MicroblogsEntryConstants.TYPE_REPLY) %>">
						<portlet:renderURL var="repostMicroblogsEntryURL" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
							<portlet:param name="jspPage" value="/microblogs/edit_microblogs_entry.jsp" />
							<portlet:param name="microblogsEntryId" value="<%= String.valueOf(microblogsEntry.getMicroblogsEntryId()) %>" />
							<portlet:param name="repost" value="<%= Boolean.TRUE.toString() %>" />
						</portlet:renderURL>

						<%
						String repostURL = "javascript:Liferay.Microblogs.displayPopup('" + repostMicroblogsEntryURL + "','" + LanguageUtil.get(pageContext, "repost") + "');";
						%>

						<span class="action repost">
							<a data-microblogsEntryId="<%= microblogsEntry.getMicroblogsEntryId() %>" href="<%= repostURL %>"><liferay-ui:message key="repost" /></a>
						</span>
					</c:if>

					<c:if test="<%= (microblogsEntry.getType() != MicroblogsEntryConstants.TYPE_REPOST) && (microblogsEntry.getType() != MicroblogsEntryConstants.TYPE_REPLY) && (MicroblogsEntryPermission.contains(permissionChecker, microblogsEntry.getMicroblogsEntryId(), ActionKeys.UPDATE)) %>">
						<portlet:renderURL var="updateMicroblogsEntryURL" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
							<portlet:param name="jspPage" value="/microblogs/edit_microblogs_entry.jsp" />
							<portlet:param name="redirect" value="<%= microblogsEntriesURL.toString() %>" />
							<portlet:param name="microblogsEntryId" value="<%= String.valueOf(microblogsEntry.getMicroblogsEntryId()) %>" />
							<portlet:param name="edit" value="<%= Boolean.TRUE.toString() %>" />
						</portlet:renderURL>

						<span class="action edit">
							<a data-microblogsEntryId="<%= microblogsEntry.getMicroblogsEntryId() %>" href="<%= updateMicroblogsEntryURL %>"><liferay-ui:message key="edit" /></a>
						</span>
					</c:if>

					<c:if test="<%= MicroblogsEntryPermission.contains(permissionChecker, microblogsEntry.getMicroblogsEntryId(), ActionKeys.DELETE) %>">
						<portlet:actionURL name="deleteMicroblogsEntry" var="deleteURL" windowState="<%= LiferayWindowState.NORMAL.toString() %>">
							<portlet:param name="microblogsEntryId" value="<%= String.valueOf(microblogsEntry.getMicroblogsEntryId()) %>" />
						</portlet:actionURL>

						<span class="action delete"><a href="<%= deleteURL %>"><liferay-ui:message key="delete" /></a></span>
					</c:if>
				</span>
			</div>
		</div>

		<div class="comments-container reply" id="commentsContainer<%= microblogsEntry.getMicroblogsEntryId() %>"><!-- --></div>
	</div>

<%
}
%>