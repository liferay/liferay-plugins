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

<%
MBMessageDisplay messageDisplay = MBMessageLocalServiceUtil.getDiscussionMessageDisplay(user.getUserId(), themeDisplay.getScopeGroupId(), TasksEntry.class.getName(), tasksEntry.getTasksEntryId(), WorkflowConstants.STATUS_APPROVED);

MBThread thread = messageDisplay.getThread();
MBTreeWalker treeWalker = messageDisplay.getTreeWalker();
MBMessage rootMessage = treeWalker.getRoot();
List<MBMessage> messages = treeWalker.getMessages();
%>

<div>

	<%
	messages = ListUtil.sort(messages, new MessageCreateDateComparator(true));

	for (int i = 1; i < messages.size(); i++) {
		MBMessage message = (MBMessage)messages.get(i);

		String className = StringPool.BLANK;

		if ((i % 2) == 0) {
			className += "alt";
		}
	%>

		<div class="comment-wrapper <%= className %>">
			<div class="comment-body">
				<span class="message"><%= HtmlUtil.escape(message.getBody()) %></span>
			</div>

			<div class="comment-info">

				<%
				User messageUser = UserLocalServiceUtil.getUser(message.getUserId());
				%>

				<c:choose>
					<c:when test="<%= Validator.isNotNull(messageUser.getDisplayURL(themeDisplay)) %>">
						<span class="user-name"><a href="<%= messageUser.getDisplayURL(themeDisplay) %>"><%= message.getUserName() %></a></span>
					</c:when>
					<c:otherwise>
						<span class="user-name"><%= message.getUserName() %></span>
					</c:otherwise>
				</c:choose>

				<span class="post-date"><%= dateFormatDateTime.format(message.getModifiedDate()) %></span>

				<c:if test="<%= DateUtil.compareTo(message.getCreateDate(), message.getModifiedDate()) != 0 %>">
					<span class="edit-notice"><liferay-ui:message key="modified" /></span>
				</c:if>

				<c:if test="<%= message.getUserId() == user.getUserId() %>">
					<span class="edit-message"><a href="javascript:;" onClick="<portlet:namespace />editPost(this, <%= message.getMessageId() %>);"><liferay-ui:message key="edit" /></a></span>
					<span class="delete-message"><a href="javascript:;" onClick="<portlet:namespace />deletePost(<%= message.getMessageId() %>);"><liferay-ui:message key="delete" /></a></span>
				</c:if>
			</div>
		</div>

	<%
	}
	%>

</div>

<form action="<portlet:actionURL name="updateMessage"><portlet:param name="mvcPath" value="/tasks/view_task.jsp" /></portlet:actionURL>" method="post" name="<portlet:namespace />fm2">
<input name="<portlet:namespace />redirect" type="hidden" value="<%= HtmlUtil.escape(currentURL) %>" />
<input name="<portlet:namespace /><%= Constants.CMD %>" type="hidden" value="" />
<input name="<portlet:namespace />className" type="hidden" value="<%= TasksEntry.class.getName() %>" />
<input name="<portlet:namespace />classPK" type="hidden" value="<%= tasksEntry.getTasksEntryId() %>" />
<input name="<portlet:namespace />messageId" type="hidden" value="" />
<input name="<portlet:namespace />threadId" type="hidden" value="<%= thread.getThreadId() %>" />
<input name="<portlet:namespace />parentMessageId" type="hidden" value="<%= rootMessage.getMessageId() %>" />

<div class="add-comment">
	<div class="control">
		<liferay-ui:icon image="reply" label="<%= true %>" message="add-comment" url="javascript:Liferay.Tasks.toggleCommentForm();" />
	</div>

	<div class="form aui-helper-hidden">
		<div>
			<textarea class="comment-form" name="<portlet:namespace />body" onKeyUp="document.getElementById('<portlet:namespace />postButton').disabled = (this.value == '');" wrap="soft"></textarea>
		</div>

		<input disabled id="<portlet:namespace />postButton" type="button" value="<liferay-ui:message key="post" />" onClick="<portlet:namespace />postReply();" />

		<input type="button" value="<liferay-ui:message key="cancel" />" onClick="document.<portlet:namespace />fm2.<portlet:namespace />messageId.value = '';document.<portlet:namespace />fm2.<portlet:namespace />body.value = '';Liferay.Tasks.toggleCommentForm();" />
	</div>
</div>

</form>

<aui:script>
	function <portlet:namespace />deletePost(messageId) {
		document.<portlet:namespace />fm2.<portlet:namespace /><%= Constants.CMD %>.value = "<%= Constants.DELETE %>";
		document.<portlet:namespace />fm2.<portlet:namespace />messageId.value = messageId;

		<portlet:namespace />submitCommentForm();
	}

	function <portlet:namespace />editPost(post, messageId) {
		var wrapper = AUI().one('.tasks-dialog .add-comment');

		var control = wrapper.one('.control');
		var form = wrapper.one('.form');

		var comment = AUI().one(post).ancestor('.comment-wrapper').one('.comment-body .message').text();

		document.<portlet:namespace />fm2.<portlet:namespace />messageId.value = messageId;
		document.<portlet:namespace />fm2.<portlet:namespace />body.value = comment;

		form.show();
		control.hide();
	}

	function <portlet:namespace />postReply() {
		document.<portlet:namespace />fm2.<portlet:namespace /><%= Constants.CMD %>.value = "<%= Constants.ADD %>";

		<portlet:namespace />submitCommentForm();
	}

	function <portlet:namespace />submitCommentForm() {
		var form = AUI().one(document.<portlet:namespace />fm2);

		var popup = Liferay.Tasks.getPopup();

		popup.io.set('form', {id: form.getDOM()});
		popup.io.set('uri', form.getAttribute('action'));

		popup.io.start();
	}
</aui:script>