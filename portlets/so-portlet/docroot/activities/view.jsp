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
			var currentTarget = event.currentTarget;

			var activityFooterToolbar = currentTarget.ancestor('.activity-footer-toolbar');

			var commentsContainer = activityFooterToolbar.siblings('.comments-container');

			var commentsList = commentsContainer.one('.comments-list');

			var commentEntry = commentsList.one('.comment-entry');

			if (commentEntry) {
				commentsList.toggleClass('aui-helper-hidden');
			}
			else {
				var uri = '<liferay-portlet:resourceURL id="getComments"></liferay-portlet:resourceURL>';

				uri = Liferay.Util.addParams('activitySetId=' + currentTarget.getAttribute('data-activitySetId'), uri) || uri;

				A.io.request(
					uri,
					{
						after: {
							success: function(event, id, obj) {
								var responseData = this.get('responseData');

								if (responseData) {
									var comments = responseData.comments;

									A.Array.map(
										comments,
										function(comment) {
											Liferay.SO.Activities.addNewComment(commentsList, comment);
										}
									)
								}
							}
						},
						dataType: 'json',
					}
				);
			}
		},
		'.view-comments a'
	);

	activities.delegate(
		'click',
		function(event) {
			if (confirm('<%= UnicodeLanguageUtil.get(pageContext,"are-you-sure-you-want-to-delete-the-selected-entry") %>')) {
				var currentTarget = event.currentTarget;

				var activityFooter = currentTarget.ancestor('.activity-footer');
				var commentEntry = currentTarget.ancestor('.comment-entry')
				var commentsContainer = currentTarget.ancestor('.comments-container');

				var form = commentsContainer.one('form');

				var cmdInput = form.one('#<portlet:namespace /><%= Constants.CMD %>');

				cmdInput.val('<%= Constants.DELETE %>');

				var entryId = currentTarget.getAttribute('data-entryId');

				var entryIdInput = form.one('#<portlet:namespace />entryId');

				entryIdInput.val(entryId);

				A.io.request(
					form.attr('action'),
					{
						after: {
							success: function(event, id, obj) {
								var responseData = this.get('responseData');

								if (responseData.success) {
									commentEntry.remove();

									var viewComments = activityFooter.one('.view-comments a');

									var viewCommentsHtml = viewComments.get('innerHTML');

									var messagesCount = parseInt(viewCommentsHtml) - 1;

									viewComments.html(
										(messagesCount > 0 ? messagesCount : '') +
										(messagesCount > 1 ? ' <%= UnicodeLanguageUtil.get(pageContext, "comments") %>' : ' <%= UnicodeLanguageUtil.get(pageContext, "comment") %>')
									);
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
			var currentTarget = event.currentTarget;

			var entryId = currentTarget.getAttribute('data-entryId');

			var editForm = A.one('#<portlet:namespace />fm1' + entryId);

			var commentEntry = currentTarget.ancestor('.comment-entry');

			var message = commentEntry.one('.comment-body .message');

			message.toggleClass('aui-helper-hidden');

			if (editForm) {
				editForm.toggleClass('aui-helper-hidden');
			}
			else {
				var commentsContainer = currentTarget.ancestor('.comments-container');

				editForm = commentsContainer.one('form').cloneNode(true);

				editForm.removeClass('aui-helper-hidden');

				editForm.set('id','<portlet:namespace />fm1' + entryId);
				editForm.set('name','<portlet:namespace />fm1' + entryId);

				var cmdInput = editForm.one('#<portlet:namespace /><%= Constants.CMD %>');

				cmdInput.val('<%= Constants.EDIT %>');

				var entryIdInput = editForm.one('#<portlet:namespace />entryId');

				entryIdInput.val(entryId);

				var commentBody = commentEntry.one('.comment-body');

				commentBody.append(editForm);

				editForm.on(
					'submit',
					function(event) {
						event.halt();

						A.io.request(
							editForm.attr('action'),
							{
								after: {
									success: function(event, id, obj) {
										var responseData = this.get('responseData');

										if (responseData.success) {
											message.html(responseData.body);

											var postDate = commentEntry.one('.comment-info .post-date');

											postDate.html(responseData.modifiedDate);

											editForm.toggleClass('aui-helper-hidden');

											message.toggleClass('aui-helper-hidden');
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

			var messageHtml = message.get('innerHTML');

			var bodyInput = editForm.one('#<portlet:namespace />body');

			bodyInput.val(messageHtml);
		},
		'.comment-entry .edit-comment a'
	);

	activities.delegate(
		'click',
		function(event) {
			Liferay.SO.Activities.toggleEntry(event,'<portlet:namespace />');
		},
		'.toggle-entry'
	);
</aui:script>