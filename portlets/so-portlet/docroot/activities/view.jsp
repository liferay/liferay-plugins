<%--
/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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

<%@ include file="/activities/init.jsp" %>

<%
Group group = themeDisplay.getScopeGroup();

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("tabs1", tabs1);

SearchContainer searchContainer = new SearchContainer(renderRequest, null, null, SearchContainer.DEFAULT_CUR_PARAM, 10, portletURL, null, null);
%>

<c:choose>
	<c:when test="<%= GetterUtil.getBoolean(PropsUtil.get(PropsKeys.SOCIAL_ACTIVITY_SETS_ENABLED)) %>">
		<%@ include file="/activities/view_activity_sets.jspf" %>
	</c:when>
	<c:otherwise>
		<%@ include file="/activities/view_activities.jspf" %>
	</c:otherwise>
</c:choose>

<aui:script use="aui-base">
	var activities = A.one('#p_p_id<portlet:namespace />');

	activities.delegate(
		'click',
		function(event) {
			var node = event.currentTarget;

			var ancestor = node.ancestor();

			var commentsContainer = ancestor.siblings('.comments-container');

			var commentsList = commentsContainer.one('.comments-list');

			commentsList.toggleClass('aui-helper-hidden');
		},
		'.view-comments a'
	);

	activities.delegate(
		'click',
		function(event) {
			if (confirm(Liferay.Language.get('are-you-sure-you-want-to-delete-the-selected-entry'))) {
				var node = event.currentTarget;

				var messageId = node.getAttribute('data-messageId');
				var commentsContainer = node.ancestor('.comments-container');

				var form = commentsContainer.one('form');

				var cmdNode = form.one('#<portlet:namespace /><%= Constants.CMD %>');

				cmdNode.val('<%= Constants.DELETE %>');

				var messageIdNode = form.one('#<portlet:namespace />messageId');

				messageIdNode.val(messageId);

				A.io.request(
					form.attr('action'),
					{
						after: {
							failure: function(event, id, obj) {
							},
							success: function(event, id, obj) {
								var responseData = this.get('responseData');

								if (responseData.success) {
									var activityFooterToolbar = node.ancestor('.activity-footer-toolbar');

									var viewCommentsNode = activityFooterToolbar.one('.view-comments a');

									var messagesCountString = viewCommentsNode.getContent();

									var messagesCount = parseInt(messagesCountString) - 1;

									viewCommentsNode.html((messagesCount > 0 ? messagesCount : '') + ' ' + Liferay.Language.get(messagesCount > 1 ? 'comments' : 'comment'));

									node.ancestor('.comment-entry').remove();
								}
							}
						},
						dataType: 'json',
						form: {
							id: form
						}
					}
				);
			}
		},
		'.comment-entry .delete-comment a'
	);

	activities.delegate(
		'click',
		function(event) {
			var node = event.currentTarget;

			var messageId = node.getAttribute('data-messageId');
			var ancestor = node.ancestor('.comments-container');

			var editForm = A.one('#<portlet:namespace />fm1' + messageId);

			var commentEntryNode = node.ancestor('.comment-entry')

			var commentBodyNode = commentEntryNode.one('.comment-body');

			var messageNode = commentBodyNode.one('.message');

			if (editForm) {
				editForm.toggleClass('aui-helper-hidden');
				messageNode.toggleClass('aui-helper-hidden');

				var message = messageNode.get('innerHTML');

				editForm.one('#<portlet:namespace />body').html(message);
			}
			else {
				editForm = ancestor.one('form').cloneNode(true);

				editForm.removeClass('aui-helper-hidden');
				editForm.set('id','<portlet:namespace />fm1' + messageId);
				editForm.set('name','<portlet:namespace />fm1' + messageId);

				var cmdNode = editForm.one('#<portlet:namespace /><%= Constants.CMD %>');

				cmdNode.val('<%= Constants.EDIT %>');

				var messageIdNode = editForm.one('#<portlet:namespace />messageId');

				messageIdNode.val(messageId);

				var message = messageNode.get('innerHTML');

				var body = editForm.one('#<portlet:namespace />body');

				body.val(message);

				messageNode.toggleClass('aui-helper-hidden');

				commentBodyNode.append(editForm);

				editForm.on(
					'submit',
					function(event) {
						event.halt();

						A.io.request(
							editForm.attr('action'),
							{
								after: {
									failure: function(event, id, obj) {
									},
									success: function(event, id, obj) {
										var responseData = this.get('responseData');

										if (responseData.success) {
											var message = commentEntryNode.one('.comment-body .message');

											message.html(responseData.body);

											var postDate = commentEntryNode.one('.comment-info .post-date');

											postDate.html(responseData.modifiedDate);

											editForm.toggleClass('aui-helper-hidden');
											messageNode.toggleClass('aui-helper-hidden');
										}
									}
								},
								dataType: 'json',
								form: {
									id: editForm
								}
							}
						);
					}
				);
			}
		},
		'.comment-entry .edit-comment a'
	);

	var announcementEntries = A.one('#p_p_id<portlet:namespace />');

	announcementEntries.delegate(
		'click',
		function(event) {
			Liferay.SO.Activities.toggleEntry(event,'<portlet:namespace />');
		},
		'.toggle-entry'
	);
</aui:script>