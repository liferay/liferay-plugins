<%--
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
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
String tabs1 = ParamUtil.getString(request, "tabs1", "message-boards");

portletURL.setParameter("tabs1", tabs1);
portletURL.setWindowState(WindowState.MAXIMIZED);

request.setAttribute("view.jsp-portletURL", portletURL);
%>

<liferay-ui:tabs
	names="message-boards,discussions,wikis"
	param="tabs1"
	url="<%= portletURL.toString() %>"
/>

<liferay-ui:error exception="<%= NoSuchMessageException.class %>" message="the-message-could-not-be-found" />
<liferay-ui:error exception="<%= NoSuchPageException.class %>" message="the-page-could-not-be-found" />
<liferay-ui:error exception="<%= RequiredMessageException.class %>" message="you-cannot-delete-a-root-message-that-has-more-than-one-immediate-reply" />

<liferay-ui:error-principal />

<c:if test='<%= SessionMessages.contains(renderRequest, "anotherUserHasMadeChangesToThesePages") %>'>

	<%
	String wikiPageLinks = (String)SessionMessages.get(renderRequest, "anotherUserHasMadeChangesToThesePages");
	%>

	<div class="portlet-msg-info">
		<%= LanguageUtil.format(request, "another-user-made-changes-to-the-following-pages-and-the-approved-changes-were-not-merged-into-the-latest-version-x", wikiPageLinks, false) %>
	</div>
</c:if>

<c:choose>
	<c:when test='<%= tabs1.equals("message-boards") %>'>
		<%@ include file="/moderation/message_boards.jspf" %>
	</c:when>
	<c:when test='<%= tabs1.equals("discussions") %>'>
		<%@ include file="/moderation/discussions.jspf" %>
	</c:when>
	<c:otherwise>
		<%@ include file="/moderation/wiki_pages.jspf" %>
	</c:otherwise>
</c:choose>

<aui:script>
	Liferay.provide(
		window,
		'<portlet:namespace />deleteMBMessages',
		function(dicussion) {
			var deleteMBMessageIds = Liferay.Util.listCheckedExcept(document.<portlet:namespace />fm, '<portlet:namespace />allRowIds');

			if (deleteMBMessageIds && confirm('<%= UnicodeLanguageUtil.get(request, "are-you-sure-you-want-to-delete-the-selected-messages") %>')) {
				document.<portlet:namespace />fm.<portlet:namespace />deleteMBMessageIds.value = deleteMBMessageIds;

				if (dicussion) {
					submitForm(document.<portlet:namespace />fm, '<portlet:actionURL name="deleteDiscussionMBMessages"><portlet:param name="redirect" value="<%= portletURL.toString() %>" /></portlet:actionURL>');
				}
				else {
					submitForm(document.<portlet:namespace />fm, '<portlet:actionURL name="deleteMBMessages"><portlet:param name="redirect" value="<%= portletURL.toString() %>" /></portlet:actionURL>');
				}
			}
		},
		['liferay-util-list-fields']
	);

	Liferay.provide(
		window,
		'<portlet:namespace />notSpamMBMessages',
		function() {
			var notSpamMBMessageIds = Liferay.Util.listCheckedExcept(document.<portlet:namespace />fm, '<portlet:namespace />allRowIds');

			if (notSpamMBMessageIds && confirm('<%= UnicodeLanguageUtil.get(request, "are-you-sure-you-want-to-mark-the-selected-messages-as-not-spam") %>')) {
				document.<portlet:namespace />fm.<portlet:namespace />notSpamMBMessageIds.value = notSpamMBMessageIds;
				submitForm(document.<portlet:namespace />fm, '<portlet:actionURL name="markNotSpamMBMessages"><portlet:param name="redirect" value="<%= portletURL.toString() %>" /></portlet:actionURL>');
			}
		},
		['liferay-util-list-fields']
	);

	Liferay.provide(
		window,
		'<portlet:namespace />notSpamWikiPages',
		function() {
			var notSpamWikiPageIds = Liferay.Util.listCheckedExcept(document.<portlet:namespace />fm, '<portlet:namespace />allRowIds');

			if (notSpamWikiPageIds && confirm('<%= UnicodeLanguageUtil.get(request, "are-you-sure-you-want-to-mark-the-selected-pages-as-not-spam") %>')) {
				document.<portlet:namespace />fm.<portlet:namespace />notSpamWikiPageIds.value = notSpamWikiPageIds;
				submitForm(document.<portlet:namespace />fm, '<portlet:actionURL name="markNotSpamWikiPages"><portlet:param name="redirect" value="<%= portletURL.toString() %>" /></portlet:actionURL>');
			}
		},
		['liferay-util-list-fields']
	);

	Liferay.provide(
		window,
		'<portlet:namespace />spamWikiPages',
		function() {
			var spamWikiPageIds = Liferay.Util.listCheckedExcept(document.<portlet:namespace />fm, '<portlet:namespace />allRowIds');

			if (spamWikiPageIds && confirm('<%= UnicodeLanguageUtil.get(request, "are-you-sure-you-want-to-mark-the-selected-pages-as-spam") %>')) {
				document.<portlet:namespace />fm.<portlet:namespace />spamWikiPageIds.value = spamWikiPageIds;
				submitForm(document.<portlet:namespace />fm, '<portlet:actionURL name="spamWikiPages"><portlet:param name="redirect" value="<%= portletURL.toString() %>" /></portlet:actionURL>');
			}
		},
		['liferay-util-list-fields']
	);
</aui:script>