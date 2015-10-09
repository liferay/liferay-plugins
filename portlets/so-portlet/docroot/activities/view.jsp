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

<%@ include file="/activities/init.jsp" %>

<%
Group group = themeDisplay.getScopeGroup();

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("tabs1", tabs1);
%>

<c:if test="<%= group.isUser() && layout.isPrivateLayout() %>">
	<liferay-ui:tabs
		names="all,connections,following,my-sites,me"
		url="<%= portletURL.toString() %>"
		value="<%= tabs1 %>"
	/>
</c:if>

<div class="social-activities"></div>

<div class="loading-bar"></div>

<aui:script use="aui-base,aui-io-request-deprecated,aui-parse-content,liferay-so-scroll">
	var activities = A.one('#p_p_id<portlet:namespace />');
	var body = A.getBody();

	var loadingBar = activities.one('.loading-bar');
	var socialActivities = activities.one('.social-activities');

	socialActivities.plug(A.Plugin.ParseContent);

	var win = A.getWin();

	win.plug(
		Liferay.SO.Scroll,
		{
			edgeProximity: 0.4
		}
	);

	var loading = false;

	<portlet:namespace />start = 0;

	var loadNewContent = function() {
		loadingBar.removeClass('loaded');

		loading = true;

		setTimeout(
			function() {
				<portlet:renderURL var="viewActivitySetsURL" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
					<c:choose>
						<c:when test="<%= GetterUtil.getBoolean(PropsUtil.get(PropsKeys.SOCIAL_ACTIVITY_SETS_ENABLED)) %>">
							<portlet:param name="mvcPath" value="/activities/view_activity_sets.jsp" />
						</c:when>
						<c:otherwise>
							<portlet:param name="mvcPath" value="/activities/view_activities.jsp" />
						</c:otherwise>
					</c:choose>

					<portlet:param name="tabs1" value="<%= tabs1 %>" />
				</portlet:renderURL>

				var uri = '<%= viewActivitySetsURL %>';

				uri = Liferay.Util.addParams('<portlet:namespace />start=' + <portlet:namespace />start, uri) || uri;

				A.io.request(
					uri,
					{
						after: {
							success: function(event, id, obj) {
								var responseData = this.get('responseData');

								socialActivities.append(responseData);

								loadingBar.addClass('loaded');

								loading = false;

								if (!activities.one('.no-activities')) {
									if (body.height() < win.height()) {
										loadNewContent();
									}
									else if (win.width() < 768) {
										loading = true;

										var manualLoaderTemplate =
											'<div class="manual-loader">' +
												'<button href="javascript:;"><liferay-ui:message key="load-more-activities" /></button>' +
											'</div>';

										socialActivities.append(manualLoaderTemplate);
									}
								}
							}
						}
					}
				);
			},
			1000
		);
	}

	if (socialActivities && !loading) {
		loadNewContent();
	}

	win.scroll.on(
		'bottom-edge',
		function(event) {
			if (activities.one('.no-activities')) {
				loading = true;
			}

			if (!loading) {
				loadNewContent();
			}
		}
	);

	socialActivities.delegate(
		'click',
		function(event) {
			var manualLoader = socialActivities.one('.manual-loader');

			manualLoader.remove(true);

			loadNewContent();
		},
		'.manual-loader button'
	)

	socialActivities.delegate(
		'click',
		function(event) {
			var currentTarget = event.currentTarget;

			var activityFooterToolbar = currentTarget.ancestor('.activity-footer-toolbar');

			var commentsContainer = activityFooterToolbar.siblings('.comments-container');

			var commentsList = commentsContainer.one('.comments-list');

			if (commentsList.attr('loaded')) {
				commentsList.toggle();
			}
			else {
				var uri = '<liferay-portlet:resourceURL id="getComments"></liferay-portlet:resourceURL>';

				uri = Liferay.Util.addParams('<portlet:namespace />activitySetId=' + currentTarget.getAttribute('data-activitySetId'), uri) || uri;

				A.io.request(
					uri,
					{
						after: {
							success: function(event, id, obj) {
								var responseData = this.get('responseData');

								if (responseData) {
									commentsList.empty();

									responseData.comments.forEach(
										function(item, index) {
											Liferay.SO.Activities.addNewComment(commentsList, item);
										}
									);

									commentsList.attr('loaded', 'true');
								}
							}
						},
						dataType: 'JSON'
					}
				);
			}

			commentsContainer.one('.comment-form').focus();
		},
		'.view-comments a'
	);

	socialActivities.delegate(
		'click',
		function(event) {
			if (confirm('<%= UnicodeLanguageUtil.get(request, "are-you-sure-you-want-to-delete-the-selected-entry") %>')) {
				var currentTarget = event.currentTarget;

				var activityFooter = currentTarget.ancestor('.activity-footer');
				var commentEntry = currentTarget.ancestor('.comment-entry')
				var commentsContainer = currentTarget.ancestor('.comments-container');

				var form = commentsContainer.one('form');

				var cmdInput = form.one('#<portlet:namespace /><%= Constants.CMD %>');

				cmdInput.val('<%= Constants.DELETE %>');

				var mbMessageIdOrMicroblogsEntryId = currentTarget.attr('data-mbMessageIdOrMicroblogsEntryId');

				var mbMessageIdOrMicroblogsEntryIdInput = form.one('#<portlet:namespace />mbMessageIdOrMicroblogsEntryId');

				mbMessageIdOrMicroblogsEntryIdInput.val(mbMessageIdOrMicroblogsEntryId);

				A.io.request(
					form.attr('action'),
					{
						after: {
							success: function(event, id, obj) {
								var responseData = this.get('responseData');

								if (responseData.success) {
									commentEntry.remove();

									var viewComments = activityFooter.one('.view-comments a');

									var viewCommentsHtml = viewComments.html();

									var messagesCount = A.Lang.toInt(viewCommentsHtml) - 1;

									var commentText = '';

									if (messagesCount > 0) {
										commentText += messagesCount;
									}

									if (messagesCount > 1) {
										commentText += ' <%= UnicodeLanguageUtil.get(request, "comments") %>';
									}
									else {
										commentText += ' <%= UnicodeLanguageUtil.get(request, "comment") %>';
									}

									viewComments.html(commentText);
								}
							}
						},
						dataType: 'JSON',
						form: {
							id: form
						}
					}
				);
			}
		},
		'.comment-entry .delete-comment a'
	);

	socialActivities.delegate(
		'click',
		function(event) {
			var currentTarget = event.currentTarget;

			var mbMessageIdOrMicroblogsEntryId = currentTarget.getAttribute('data-mbMessageIdOrMicroblogsEntryId');

			var commentsContainer = currentTarget.ancestor('.comments-container');

			var editForm = commentsContainer.one('#<portlet:namespace />fm1' + mbMessageIdOrMicroblogsEntryId);

			var commentEntry = currentTarget.ancestor('.comment-entry');

			var message = commentEntry.one('.comment-body .message');

			message.toggle();

			if (editForm) {
				editForm.toggle();
			}
			else {
				editForm = commentsContainer.one('form').cloneNode(true);

				editForm.show();

				editForm.attr(
					{
						id: '<portlet:namespace />fm1' + mbMessageIdOrMicroblogsEntryId,
						name: '<portlet:namespace />fm1' + mbMessageIdOrMicroblogsEntryId
					}
				);

				var userPortrait = editForm.one('.user-portrait');

				if (userPortrait) {
					userPortrait.remove();
				}

				var cmdInput = editForm.one('#<portlet:namespace /><%= Constants.CMD %>');

				cmdInput.val('<%= Constants.EDIT %>');

				var mbMessageIdOrMicroblogsEntryIdInput = editForm.one('#<portlet:namespace />mbMessageIdOrMicroblogsEntryId');

				mbMessageIdOrMicroblogsEntryIdInput.val(mbMessageIdOrMicroblogsEntryId);

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

											editForm.toggle();
											message.toggle();
										}
									}
								},
								dataType: 'JSON',
								form: {
									id: editForm
								}
							}
						);
					}
				);
			}

			var messageHtml = message.html();

			var bodyInput = editForm.one('#<portlet:namespace />body');

			bodyInput.val(messageHtml);
		},
		'.comment-entry .edit-comment a'
	);

	socialActivities.delegate(
		'click',
		function(event) {
			var currentTarget = event.currentTarget;

			var uri = '<portlet:renderURL windowState="<%= LiferayWindowState.POP_UP.toString() %>"><portlet:param name="mvcPath" value="/activities/repost_microblogs_entry.jsp" /><portlet:param name="mvcPath" value="/activities/repost_microblogs_entry.jsp" /><portlet:param name="redirect" value="<%= currentURL %>" /></portlet:renderURL>';

			uri = Liferay.Util.addParams('<portlet:namespace />microblogsEntryId=' + currentTarget.getAttribute('data-microblogsEntryId'), uri) || uri;

			Liferay.Util.openWindow(
				{
					cache: false,
					dialog: {
						align: Liferay.Util.Window.ALIGN_CENTER,
						modal: true,
						width: 400
					},
					id: '<portlet:namespace />Dialog',
					title: '<%= UnicodeLanguageUtil.get(request, "repost") %>',
					uri: uri
				}
			);
		},
		'.repost a'
	);

	socialActivities.delegate(
		'click',
		function(event) {
			Liferay.SO.Activities.toggleEntry(event, '<portlet:namespace />');
		},
		'.toggle-entry'
	);

	Liferay.on(
		'microblogPosted',
		function(event) {
			Liferay.Portlet.refresh('#p_p_id<portlet:namespace />');
		}
	);

	Liferay.on(
		'sessionExpired',
		function(event) {
			var reload = function() {
				window.location.reload();
			};

			loadNewContent = reload;
		}
	);
</aui:script>