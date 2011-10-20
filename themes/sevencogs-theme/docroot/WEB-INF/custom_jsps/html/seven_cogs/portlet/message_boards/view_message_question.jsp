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
themeDisplay.setIncludeServiceJs(true);

MBMessageDisplay messageDisplay = (MBMessageDisplay)request.getAttribute(WebKeys.MESSAGE_BOARDS_MESSAGE);

MBMessage message = messageDisplay.getMessage();

MBCategory category = messageDisplay.getCategory();

MBThread thread = messageDisplay.getThread();
%>

<c:choose>
	<c:when test="<%= portletName.equals(PortletKeys.MESSAGE_BOARDS_ADMIN) %>">

		<%
		PortletURL portletURL = renderResponse.createRenderURL();

		portletURL.setParameter("tabs1", "message-boards-home");
		%>

		<liferay-ui:tabs
			names="message-boards-home,recent-posts,statistics,banned-users"
			url="<%= portletURL.toString() %>"
		/>
	</c:when>
	<c:otherwise>
		<liferay-util:include page="/html/portlet/message_boards/top_links.jsp" />
	</c:otherwise>
</c:choose>

<div id="<portlet:namespace />addAnswerFlagDiv" style="display: none;">
	<liferay-ui:icon
		image="checked"
		label="<%= true %>"
		message="answer"
	/>

	<c:if test="<%= !MBMessagePermission.contains(permissionChecker, message.getRootMessageId(), ActionKeys.UPDATE) %>">

		<%
		String taglibHREF = "javascript:" + renderResponse.getNamespace() + "deleteAnswerFlag('@MESSAGE_ID@');";
		%>

		(<aui:a href="<%= taglibHREF %>"><liferay-ui:message key="unmark" /></aui:a>)
	</c:if>
</div>

<div id="<portlet:namespace />deleteAnswerFlagDiv" style="display: none;">

	<%
	String taglibMarkAsAnAnswerURL = "javascript:" + renderResponse.getNamespace() + "addAnswerFlag('@MESSAGE_ID@');";
	%>

	<liferay-ui:icon
		image="checked"
		label="<%= true %>"
		message="mark-as-an-answer"
		url="<%= taglibMarkAsAnAnswerURL %>"
	/>
</div>

<c:choose>
	<c:when test="<%= includeFormTag %>">
		<aui:form>
			<aui:input name="breadcrumbsCategoryId" type="hidden" value="<%= category.getCategoryId() %>" />
			<aui:input name="breadcrumbsMessageId" type="hidden" value="<%= message.getMessageId() %>" />
			<aui:input name="threadId" type="hidden" value="<%= message.getThreadId() %>" />

			<liferay-util:include page="/html/seven_cogs/portlet/message_boards/view_message_content.jsp" />
		</aui:form>
	</c:when>
	<c:otherwise>
		<liferay-util:include page="/html/seven_cogs/portlet/message_boards/view_message_content.jsp" />
	</c:otherwise>
</c:choose>

<c:if test="<%= MBCategoryPermission.contains(permissionChecker, scopeGroupId, message.getCategoryId(), ActionKeys.REPLY_TO_MESSAGE) && !thread.isLocked() %>">
	<div class="aui-helper-hidden" id="<portlet:namespace />addQuickReplyDiv">
		<%@ include file="/html/portlet/message_boards/edit_message_quick.jspf" %>
	</div>
</c:if>

<aui:script>
	Liferay.provide(
		window,
		'<portlet:namespace />addAnswerFlag',
		function(messageId) {
			var A = AUI();

			Liferay.Service.MB.MBMessage.updateAnswer(
				{
					messageId: messageId,
					answer: true,
					cascade: false
				}
			);

			var addAnswerFlagDiv = A.one('#<portlet:namespace />addAnswerFlagDiv').clone();

			var html = addAnswerFlagDiv.html();

			html = '<div class="answer" id="<portlet:namespace />deleteAnswerFlag_' + messageId + '">' + html + '</div>';
			html = html.replace(/@MESSAGE_ID@/g, messageId);

			var tags = A.one('#<portlet:namespace />message_' + messageId).one('div.tags');

			if (tags) {
				tags.html(html);
			}

			A.one('#<portlet:namespace />addAnswerFlag_' + messageId).hide();
			A.one('#<portlet:namespace />deleteAnswerFlag_' + messageId).show();
		},
		['aui-base']
	);

	Liferay.provide(
		window,
		'<portlet:namespace />addQuickReply',
		function(cmd, messageId) {
			var A = AUI();

			var addQuickReplyDiv = A.one('#<portlet:namespace />addQuickReplyDiv');

			if (cmd == 'reply') {
				addQuickReplyDiv.show();
				addQuickReplyDiv.one('#<portlet:namespace />parentMessageId').val(messageId);
				addQuickReplyDiv.one('textarea').focus();
			}
			else {
				addQuickReplyDiv.hide();
			}
		},
		['aui-base']
	);

	Liferay.provide(
		window,
		'<portlet:namespace />deleteAnswerFlag',
		function(messageId) {
			var A = AUI();

			Liferay.Service.MB.MBMessage.updateAnswer(
				{
					messageId: messageId,
					answer: false,
					cascade: false
				}
			);

			var deleteAnswerFlagDiv = A.one('#<portlet:namespace />deleteAnswerFlagDiv').clone();

			var html = deleteAnswerFlagDiv.html();

			html = '<li id="<portlet:namespace />addAnswerFlag_' + messageId + '">' + html + '</li>';
			html = html.replace(/@MESSAGE_ID@/g, messageId);

			var editControls = A.one('#<portlet:namespace />message_' + messageId).one('ul.edit-controls');

			if (editControls) {
				editControls.prepend(html);
			}

			A.one('#<portlet:namespace />deleteAnswerFlag_' + messageId).hide();

			A.one('#<portlet:namespace />addAnswerFlag_' + messageId).show();
		},
		['aui-base']
	);

	<c:if test="<%= thread.getRootMessageId() != message.getMessageId() %>">
		document.getElementById("<portlet:namespace />message_" + <%= message.getMessageId() %>).scrollIntoView(true);
	</c:if>
</aui:script>

<%
MBThreadFlagLocalServiceUtil.addThreadFlag(themeDisplay.getUserId(), thread);

message = messageDisplay.getMessage();

PortalUtil.setPageSubtitle(message.getSubject(), request);
PortalUtil.setPageDescription(message.getSubject(), request);

List<AssetTag> assetTags = AssetTagLocalServiceUtil.getTags(MBMessage.class.getName(), message.getMessageId());

PortalUtil.setPageKeywords(ListUtil.toString(assetTags, AssetTag.NAME_ACCESSOR), request);

MBUtil.addPortletBreadcrumbEntries(message, request, renderResponse);
%>