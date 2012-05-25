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
long userId = ParamUtil.getLong(request, "userId");

User user2 = null;

if (userId > 0) {
	user2 = UserLocalServiceUtil.getUser(userId);
}

boolean showAddAsConnectionButton = false;
boolean showBlockButton = false;
boolean showFollowButton = false;
boolean showRemoveAsConnectionButton = false;
boolean showUnBlockButton = false;
boolean showUnFollowButton = false;
boolean viewRelationActions = true;

if (user2 != null) {
	if (SocialRelationLocalServiceUtil.hasRelation(user2.getUserId(), themeDisplay.getUserId(), SocialRelationConstants.TYPE_UNI_ENEMY)) {
		viewRelationActions = false;
	}
	else if (SocialRelationLocalServiceUtil.hasRelation(themeDisplay.getUserId(), user2.getUserId(), SocialRelationConstants.TYPE_UNI_ENEMY)) {
		viewRelationActions = false;
	}

	if (viewRelationActions) {
		showAddAsConnectionButton = !SocialRequestLocalServiceUtil.hasRequest(themeDisplay.getUserId(), User.class.getName(), themeDisplay.getUserId(), SocialRelationConstants.TYPE_BI_CONNECTION, user2.getUserId(), SocialRequestConstants.STATUS_PENDING) && SocialRelationLocalServiceUtil.isRelatable(themeDisplay.getUserId(), user2.getUserId(), SocialRelationConstants.TYPE_BI_CONNECTION);
		showRemoveAsConnectionButton = SocialRelationLocalServiceUtil.hasRelation(themeDisplay.getUserId(), user2.getUserId(), SocialRelationConstants.TYPE_BI_CONNECTION);
		showFollowButton = SocialRelationLocalServiceUtil.isRelatable(themeDisplay.getUserId(), user2.getUserId(), SocialRelationConstants.TYPE_UNI_FOLLOWER);
		showUnFollowButton = SocialRelationLocalServiceUtil.hasRelation(themeDisplay.getUserId(), user2.getUserId(), SocialRelationConstants.TYPE_UNI_FOLLOWER);
	}

	showBlockButton = SocialRelationLocalServiceUtil.isRelatable(themeDisplay.getUserId(), user2.getUserId(), SocialRelationConstants.TYPE_UNI_ENEMY);
	showUnBlockButton = SocialRelationLocalServiceUtil.hasRelation(themeDisplay.getUserId(), user2.getUserId(), SocialRelationConstants.TYPE_UNI_ENEMY);
}

String userDisplayURL = StringPool.BLANK;

if (user2 != null) {
	userDisplayURL = user2.getDisplayURL(themeDisplay);
}
%>

<div class="lfr-button-column">
	<div class="lfr-button-column-content">
		<aui:button-row cssClass="edit-toolbar" id='<%= renderResponse.getNamespace() + "userToolbar" %>' />
	</div>
</div>

<aui:script use="aui-dialog,aui-dialog-iframe">
	var buttonRow = A.one('#<portlet:namespace />userToolbar');

	var contactsToolbarChildren = [];

	contactsToolbarChildren.push(
		{
			handler: function(event) {
				<portlet:namespace />relationAction(event, '<portlet:actionURL name="requestSocialRelation"><portlet:param name="type" value="<%= String.valueOf(SocialRelationConstants.TYPE_BI_CONNECTION) %>" /></portlet:actionURL>');
			},
			icon: 'add-coworker',
			id: '<portlet:namespace />addConnectionButton',
			label: '<%= UnicodeLanguageUtil.get(pageContext, "add-connection") %>',
			visible: <%= showAddAsConnectionButton %>
		}
	);

	contactsToolbarChildren.push(
		{
			handler: function(event) {
				<portlet:namespace />relationAction(event, '<portlet:actionURL name="deleteSocialRelation"><portlet:param name="type" value="<%= String.valueOf(SocialRelationConstants.TYPE_BI_CONNECTION) %>" /></portlet:actionURL>');
			},
			icon: 'remove-coworker',
			id: '<portlet:namespace />removeConnectionButton',
			label: '<%= UnicodeLanguageUtil.get(pageContext, "remove-connection") %>',
			visible: <%= showRemoveAsConnectionButton %>
		}
	);

	contactsToolbarChildren.push(
		{
			handler: function(event) {
				<portlet:namespace />relationAction(event, '<portlet:actionURL name="addSocialRelation"><portlet:param name="type" value="<%= String.valueOf(SocialRelationConstants.TYPE_UNI_FOLLOWER) %>" /></portlet:actionURL>');
			},
			icon: 'follow',
			id: '<portlet:namespace />followButton',
			label: '<%= UnicodeLanguageUtil.get(pageContext, "follow") %>',
			visible: <%= showFollowButton %>
		}
	);

	contactsToolbarChildren.push(
		{
			handler: function(event) {
				<portlet:namespace />relationAction(event, '<portlet:actionURL name="deleteSocialRelation"><portlet:param name="type" value="<%= String.valueOf(SocialRelationConstants.TYPE_UNI_FOLLOWER) %>" /></portlet:actionURL>');
			},
			icon: 'unfollow',
			id: '<portlet:namespace />unfollowButton',
			label: '<%= UnicodeLanguageUtil.get(pageContext, "unfollow") %>',
			visible: <%= showUnFollowButton %>,
		}
	);

	contactsToolbarChildren.push(
		{
			handler: function(event) {
				<portlet:namespace />relationAction(event, '<portlet:actionURL name="addSocialRelation"><portlet:param name="type" value="<%= String.valueOf(SocialRelationConstants.TYPE_UNI_ENEMY) %>" /></portlet:actionURL>');
			},
			icon: 'block',
			id: '<portlet:namespace />blockButton',
			label: '<%= UnicodeLanguageUtil.get(pageContext, "block") %>',
			visible: <%= showBlockButton %>
		}
	);

	contactsToolbarChildren.push(
		{
			handler: function(event) {
				<portlet:namespace />relationAction(event, '<portlet:actionURL name="deleteSocialRelation"><portlet:param name="type" value="<%= String.valueOf(SocialRelationConstants.TYPE_UNI_ENEMY) %>" /></portlet:actionURL>');
			},
			icon: 'unblock',
			id: '<portlet:namespace />unblockButton',
			label: '<%= UnicodeLanguageUtil.get(pageContext, "unblock") %>',
			visible: <%= showUnBlockButton %>
		}
	);

	contactsToolbarChildren.push(
		{
			handler: function(event) {
				<c:choose>
					<c:when test="<%= (user2 == null) %>">
						<portlet:namespace />relationAction(event, '<liferay-portlet:resourceURL id="exportVCards" />');
					</c:when>
					<c:otherwise>
						location.href = '<liferay-portlet:resourceURL id="exportVCard"><portlet:param name="userId" value="<%= String.valueOf(user2.getUserId()) %>" /></liferay-portlet:resourceURL>';
					</c:otherwise>
				</c:choose>
			},
			icon: 'export',
			id: '<portlet:namespace />exportButton',
			label: '<%= UnicodeLanguageUtil.get(pageContext, "vcard") %>'
		}
	);

	<c:if test="<%= Validator.isNotNull(userDisplayURL) %>">
		contactsToolbarChildren.push(
			{
				handler: function(event) {
					location.href= '<%= userDisplayURL %>';
				},
				icon: 'user',
				id: '<portlet:namespace />gotoProfileButton',
				label: '<%= UnicodeLanguageUtil.get(pageContext, "profile") %>'
			}
		);
	</c:if>

	<%
	ServletContext servletContext = ServletContextPool.get("private-messaging-portlet");
	%>

	<c:if test="<%= Validator.isNotNull(servletContext) && (user2 == null || (user2.getUserId() != themeDisplay.getUserId())) %>">
		contactsToolbarChildren.push(
			{
				handler: function(event) {
					var uri = '<liferay-portlet:renderURL portletName="1_WAR_privatemessagingportlet" windowState="<%= LiferayWindowState.POP_UP.toString() %>"><portlet:param name="mvcPath" value="/new_message.jsp" /></liferay-portlet:renderURL>';

					<c:choose>
						<c:when test="<%= user2 != null %>">
							var userIds = [<%= user2.getUserId() %>];
						</c:when>
						<c:otherwise>
							var userIds = A.all('.lfr-contact-grid-item input').val();
						</c:otherwise>
					</c:choose>

					new A.Dialog(
						{
							align: {
								node: null,
								points: ['tc', 'tc']
							},
							cssClass: 'private-messaging-portlet',
							destroyOnClose: true,
							modal: true,
							title: '<%= UnicodeLanguageUtil.get(pageContext, "new-message") %>',
							width: 600
						}
					).plug(
						A.Plugin.IO,
						{
							data: {
								userIds: userIds.join()
							},
							uri: uri
						}
					).render();
				},
				icon: 'send-email',
				id: '<portlet:namespace />sendEmailButton',
				label: '<%= UnicodeLanguageUtil.get(pageContext, "send-email") %>'
			}
		);
	</c:if>

	var contactsToolbar = new A.Toolbar(
		{
			activeState: false,
			boundingBox: buttonRow,
			children: contactsToolbarChildren
		}
	).render();

	function <portlet:namespace />relationAction(event, uri) {
		var end = <%= ContactsConstants.MAX_RESULT_COUNT %>;

		var lastNameAnchor = '';

		var node = A.one('.more-results a');

		if (node) {
			end = A.DataType.Number.parse(node.getAttribute('data-end'));

			lastNameAnchor = node.getAttribute('data-lastNameAnchor');
		}

		<c:choose>
			<c:when test="<%= user2 != null %>">
				var userIds = [<%= user2.getUserId() %>];
			</c:when>
			<c:otherwise>
				var userIds = A.all('.lfr-contact-grid-item input').val();
			</c:otherwise>
		</c:choose>

		var contactFilterSelect = A.one('#<portlet:namespace />filterBy');

		var searchInput = A.one('.contacts-portlet #<portlet:namespace />name');

		A.io.request(
			uri,
			{
				after: {
					failure: function(event, id, obj) {
						Liferay.ContactsCenter.showMessage(false);
					},
					success: function(event, id, obj) {
						Liferay.ContactsCenter.renderSelectedContacts(this.get('responseData'), lastNameAnchor);
					}
				},
				data: {
					end: end,
					filterBy: contactFilterSelect.get('value') || '<%= ContactsConstants.FILTER_BY_DEFAULT %>',
					keywords: searchInput.get('value'),
					start: 0,
					jsonFormat: true,
					userIds: userIds.join()
				},
				dataType: 'json'
			}
		);
	}
</aui:script>