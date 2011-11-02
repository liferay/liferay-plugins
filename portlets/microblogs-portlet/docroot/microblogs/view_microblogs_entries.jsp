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

<%@ include file="/init.jsp" %>

<%
String redirect = ParamUtil.getString(request, "redirect");

List<MicroblogsEntry> microblogsEntries = (List<MicroblogsEntry>)request.getAttribute(WebKeys.MICROBLOGS_ENTRIES);

PortletURL portletURL = (PortletURL)request.getAttribute(WebKeys.MICROBLOGS_ENTRIES_URL);
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

	<div class="microblogs-entry">
		<span class="thumbnail">
			<a href="<%= userDisplayURL %>"><img alt="<%= userFullName %>" src="<%= userPortaitURL %>" /></a>
		</span>

		<div class="<%= (themeDisplay.getUserId() == microblogsEntry.getUserId()) ? "my-entry-bubble" : "entry-bubble" %> ">
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

				<%
				int replyCount = MicroblogsEntryLocalServiceUtil.getReceiverMicroblogsEntryMicroblogsEntriesCount(MicroblogsEntryConstants.TYPE_REPLY, microblogsEntry.getMicroblogsEntryId());
				%>

				<c:if test="<%= replyCount > 0 %>">
					<portlet:renderURL var="conversationURL" windowState="<%= LiferayWindowState.NORMAL.toString() %>">
						<portlet:param name="jspPage" value="/microblogs/view.jsp" />
						<portlet:param name="tabs1" value="conversation" />
						<portlet:param name="redirect" value="<%= redirect %>" />
						<portlet:param name="receiverMicroblogsEntryId" value="<%= String.valueOf(microblogsEntry.getMicroblogsEntryId()) %>" />
					</portlet:renderURL>

					<liferay-ui:icon
						cssClass="microblogs-reply-icon"
						image="conversation"
						url="<%= conversationURL %>"
					/>
				</c:if>

				<c:if test="<%= microblogsEntry.getType() == MicroblogsEntryConstants.TYPE_REPLY %>">
					<portlet:renderURL var="conversationURL" windowState="<%= LiferayWindowState.NORMAL.toString() %>">
						<portlet:param name="jspPage" value="/microblogs/view.jsp" />
						<portlet:param name="tabs1" value="conversation" />
						<portlet:param name="redirect" value="<%= redirect %>" />
						<portlet:param name="receiverMicroblogsEntryId" value="<%= String.valueOf(microblogsEntry.getReceiverMicroblogsEntryId()) %>" />
					</portlet:renderURL>

					<liferay-ui:icon
						cssClass="microblogs-reply-icon"
						image="conversation"
						url="<%= conversationURL %>"
					/>
				</c:if>
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
					viewURL.setParameter("assetTagName", assetTagName);
					viewURL.setParameter("tabs1", assetTagName);

					content = StringUtil.replace(content, result, "<a href=\"" + viewURL + "\">" + assetTagName + "</a>");
				}

				pattern = Pattern.compile("\\[\\@\\S*\\]");

				matcher = pattern.matcher(content);

				while (matcher.find()) {
					String result = matcher.group();

					String assetTagName = result.replace("[@", StringPool.BLANK);

					assetTagName = assetTagName.replace("]", StringPool.BLANK);

					PortletURL viewURL = renderResponse.createRenderURL();

					viewURL.setWindowState(LiferayWindowState.NORMAL);

					viewURL.setParameter("jspPage", "/microblogs/view.jsp");
					viewURL.setParameter("assetTagName", assetTagName);
					viewURL.setParameter("tabs1", assetTagName);

					try {
						User taggedUser = UserLocalServiceUtil.getUserByScreenName(microblogsEntry.getCompanyId(), assetTagName);

						assetTagName = PortalUtil.getUserName(taggedUser.getUserId(), assetTagName);

						viewURL.setParameter("receiverUserId", String.valueOf(taggedUser.getUserId()));
					}
					catch (NoSuchUserException nsue) {
						viewURL.setParameter("receiverUserId", String.valueOf(0));
					}

					content = StringUtil.replace(content, result, "<a href=\"" + viewURL + "\">" + assetTagName + "</a>");
				}
				%>

				<span>
					<%= content %>
				<span>
			</div>

			<div class="footer">
				<span class="modified-date">
					<%= dateFormatDateTime.format(microblogsEntry.getModifiedDate()) %>
				</span>

				<span class="action-container">
					<c:if test="<%= themeDisplay.getUserId() != microblogsEntry.getUserId() && MicroblogsPermission.contains(permissionChecker, scopeGroupId, ActionKeys.ADD_ENTRY) && (microblogsEntry.getSocialRelationType() == MicroblogsEntryConstants.TYPE_EVERYONE) && (microblogsEntry.getType() != MicroblogsEntryConstants.TYPE_REPLY) %>">
						<portlet:renderURL var="repostMicroblogsEntryURL" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
							<portlet:param name="jspPage" value="/microblogs/edit_microblogs_entry.jsp" />
							<portlet:param name="redirect" value="<%= redirect %>" />
							<portlet:param name="microblogsEntryId" value="<%= String.valueOf(microblogsEntry.getMicroblogsEntryId()) %>" />
							<portlet:param name="repost" value="<%= Boolean.TRUE.toString() %>" />
						</portlet:renderURL>

						<%
						String taglibRepostURL = "javascript:Liferay.Microblogs.displayPopup('" + repostMicroblogsEntryURL + "','" + LanguageUtil.get(pageContext, "repost") + "');";
						%>

						<liferay-ui:icon
							image="copy"
							label="<%= true %>"
							message="repost"
							url="<%= taglibRepostURL %>"
						/>
					</c:if>

					<c:if test="<%= MicroblogsPermission.contains(permissionChecker, scopeGroupId, ActionKeys.ADD_ENTRY) && (microblogsEntry.getSocialRelationType() == MicroblogsEntryConstants.TYPE_EVERYONE) && (microblogsEntry.getType() != MicroblogsEntryConstants.TYPE_REPLY) %>">
						<portlet:renderURL var="replyMicroblogsEntryURL" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
							<portlet:param name="jspPage" value="/microblogs/edit_microblogs_entry.jsp" />
							<portlet:param name="redirect" value="<%= redirect %>" />
							<portlet:param name="microblogsEntryId" value="<%= String.valueOf(microblogsEntry.getMicroblogsEntryId()) %>" />
							<portlet:param name="reply" value="<%= Boolean.TRUE.toString() %>" />
						</portlet:renderURL>

						<%
						String taglibReplyURL = "javascript:Liferay.Microblogs.displayPopup('" + replyMicroblogsEntryURL + "','" + LanguageUtil.get(pageContext, "reply") + "');";
						%>

						<liferay-ui:icon
							image="conversation"
							label="<%= true %>"
							message="reply"
							url="<%= taglibReplyURL %>"
						/>
					</c:if>

					<c:if test="<%= microblogsEntry.getType() != MicroblogsEntryConstants.TYPE_REPOST && MicroblogsEntryPermission.contains(permissionChecker, microblogsEntry.getMicroblogsEntryId(), ActionKeys.UPDATE) %>">
						<portlet:renderURL var="updateMicroblogsEntryURL" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
							<portlet:param name="jspPage" value="/microblogs/edit_microblogs_entry.jsp" />
							<portlet:param name="redirect" value="<%= redirect %>" />
							<portlet:param name="microblogsEntryId" value="<%= String.valueOf(microblogsEntry.getMicroblogsEntryId()) %>" />
							<portlet:param name="edit" value="<%= Boolean.TRUE.toString() %>" />
						</portlet:renderURL>

						<%
						String taglibEditURL = "javascript:Liferay.Microblogs.displayPopup('" + updateMicroblogsEntryURL + "','" + LanguageUtil.get(pageContext, "edit") + "');";
						%>

						<liferay-ui:icon
							image="edit"
							label="<%= true %>"
							url="<%= taglibEditURL %>"
						/>
					</c:if>

					<c:if test="<%= MicroblogsEntryPermission.contains(permissionChecker, microblogsEntry.getMicroblogsEntryId(), ActionKeys.DELETE) %>">
						<portlet:actionURL name="deleteMicroblogsEntry" var="deleteURL" windowState="<%= LiferayWindowState.NORMAL.toString() %>">
							<portlet:param name="redirect" value="<%= portletURL.toString() %>" />
							<portlet:param name="microblogsEntryId" value="<%= String.valueOf(microblogsEntry.getMicroblogsEntryId()) %>" />
						</portlet:actionURL>

						<liferay-ui:icon-delete
							label="<%= true %>"
							url="<%= deleteURL %>"
						/>
					</c:if>
				</span>
			</div>
		</div>
	</div>

<%
}
%>