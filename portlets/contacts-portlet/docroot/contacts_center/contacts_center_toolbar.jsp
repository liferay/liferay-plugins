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

<div class="lfr-button-column">
	<div class="lfr-button-column-content">
		<aui:button-row cssClass="edit-toolbar" id='<%= renderResponse.getNamespace() + "userToolbar" %>' />
	</div>
</div>

<aui:script use="aui-dialog,aui-dialog-iframe">
	var buttonRow = A.one('#<portlet:namespace />userToolbar');

	var contactsToolbarChildren = [];

	<%
	long userId = ParamUtil.getLong(request, "userId");

	User user2 = null;

	if (userId > 0) {
		user2 = UserLocalServiceUtil.getUser(userId);
	}

	boolean viewRelationActions = true;

	if (user2 != null) {
		if (SocialRelationLocalServiceUtil.hasRelation(user2.getUserId(), themeDisplay.getUserId(), SocialRelationConstants.TYPE_UNI_ENEMY)) {
			viewRelationActions = false;
		}
		else if (SocialRelationLocalServiceUtil.hasRelation(themeDisplay.getUserId(), user2.getUserId(), SocialRelationConstants.TYPE_UNI_ENEMY)) {
			viewRelationActions = false;
		}
	}
	%>

	<c:if test="<%= (user2 == null) || (viewRelationActions && !((user2 != null) && SocialRequestLocalServiceUtil.hasRequest(themeDisplay.getUserId(), User.class.getName(), themeDisplay.getUserId(), SocialRelationConstants.TYPE_BI_CONNECTION, user2.getUserId(), SocialRequestConstants.STATUS_PENDING)) && SocialRelationLocalServiceUtil.isRelatable(themeDisplay.getUserId(), user2.getUserId(), SocialRelationConstants.TYPE_BI_CONNECTION)) %>">
		contactsToolbarChildren.push(
			{
				cssClass: '<%= user2 == null ? "aui-helper-hidden" : "" %>',
				handler: function(event) {
					<portlet:namespace />relationAction(event, 'requestSocialRelation', '<%= String.valueOf(SocialRelationConstants.TYPE_BI_CONNECTION) %>');
				},
				icon: 'add-coworker',
				id: '<portlet:namespace />addConnectionButton',
				label: '<%= UnicodeLanguageUtil.get(pageContext, "add-as-connection") %>'
			}
		);
	</c:if>

	<c:if test="<%= (user2 == null) || (viewRelationActions && SocialRelationLocalServiceUtil.hasRelation(themeDisplay.getUserId(), user2.getUserId(), SocialRelationConstants.TYPE_BI_CONNECTION)) %>">
		contactsToolbarChildren.push(
			{
				cssClass: '<%= user2 == null ? "aui-helper-hidden" : "" %>',
				handler: function(event) {
					<portlet:namespace />relationAction(event, 'deleteSocialRelation', '<%= String.valueOf(SocialRelationConstants.TYPE_BI_CONNECTION) %>');
				},
				icon: 'remove-coworker',
				id: '<portlet:namespace />removeConnectionButton',
				label: '<%= UnicodeLanguageUtil.get(pageContext, "remove-as-connection") %>'
			}
		);
	</c:if>

	<c:if test="<%= (user2 == null) || (viewRelationActions && SocialRelationLocalServiceUtil.isRelatable(themeDisplay.getUserId(), user2.getUserId(), SocialRelationConstants.TYPE_UNI_FOLLOWER)) %>">
		contactsToolbarChildren.push(
			{
				cssClass: '<%= user2 == null ? "aui-helper-hidden" : "" %>',
				handler: function(event) {
					<portlet:namespace />relationAction(event, 'addSocialRelation', '<%= String.valueOf(SocialRelationConstants.TYPE_UNI_FOLLOWER) %>');
				},
				icon: 'follow',
				id: '<portlet:namespace />followButton',
				label: '<%= UnicodeLanguageUtil.get(pageContext, "follow") %>'
			}
		);
	</c:if>

	<c:if test="<%= (user2 == null) || (viewRelationActions && SocialRelationLocalServiceUtil.hasRelation(themeDisplay.getUserId(), user2.getUserId(), SocialRelationConstants.TYPE_UNI_FOLLOWER)) %>">
		contactsToolbarChildren.push(
			{
				cssClass: '<%= user2 == null ? "aui-helper-hidden" : "" %>',
				handler: function(event) {
					<portlet:namespace />relationAction(event, 'deleteSocialRelation', '<%= String.valueOf(SocialRelationConstants.TYPE_UNI_FOLLOWER) %>');
				},
				icon: 'unfollow',
				id: '<portlet:namespace />unfollowButton',
				label: '<%= UnicodeLanguageUtil.get(pageContext, "unfollow") %>'
			}
		);
	</c:if>

	<c:if test="<%= (user2 == null) || SocialRelationLocalServiceUtil.isRelatable(themeDisplay.getUserId(), user2.getUserId(), SocialRelationConstants.TYPE_UNI_ENEMY) %>">
		contactsToolbarChildren.push(
			{
				cssClass: '<%= user2 == null ? "aui-helper-hidden" : "" %>',
				handler: function(event) {
					<portlet:namespace />relationAction(event, 'addSocialRelation', '<%= String.valueOf(SocialRelationConstants.TYPE_UNI_ENEMY) %>');
				},
				icon: 'block',
				id: '<portlet:namespace />blockButton',
				label: '<%= UnicodeLanguageUtil.get(pageContext, "block") %>'
			}
		);
	</c:if>

	<c:if test="<%= (user2 == null) || SocialRelationLocalServiceUtil.hasRelation(themeDisplay.getUserId(), user2.getUserId(), SocialRelationConstants.TYPE_UNI_ENEMY) %>">
		contactsToolbarChildren.push(
			{
				cssClass: '<%= user2 == null ? "aui-helper-hidden" : "" %>',
				handler: function(event) {
					<portlet:namespace />relationAction(event, 'deleteSocialRelation', '<%= String.valueOf(SocialRelationConstants.TYPE_UNI_ENEMY) %>');
				},
				icon: 'unblock',
				id: '<portlet:namespace />unblockButton',
				label: '<%= UnicodeLanguageUtil.get(pageContext, "unblock") %>'
			}
		);
	</c:if>

	contactsToolbarChildren.push(
		{
			cssClass: '<%= user2 == null ? "aui-helper-hidden" : "" %>',
			handler: function(event) {
				<portlet:namespace />relationAction(event, 'exportVCard');
			},
			icon: 'export',
			id: '<portlet:namespace />exportButton',
			label: '<%= UnicodeLanguageUtil.get(pageContext, "export-vcard") %>'
		}
	);

	<%
	String userDisplayURL = null;

	if (user2 != null) {
		userDisplayURL = user2.getDisplayURL(themeDisplay);
	}
	%>

	<c:if test="<%= Validator.isNotNull(userDisplayURL) %>">
		contactsToolbarChildren.push(
			{
				handler: function(event) {
					location.href= '<%= userDisplayURL %>';
				},
				icon: 'user',
				id: '<portlet:namespace />gotoProfileButton',
				label: '<%= UnicodeLanguageUtil.get(pageContext, "go-to-profile") %>'
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

	function <portlet:namespace />relationAction(event, action, type) {
		<c:choose>
			<c:when test="<%= (user2 == null) %>">
				var selectedUsersNodes = A.all('.lfr-contact-grid-item input');

				if (selectedUsersNodes.size() > 0) {
					var selectedUsersIds = selectedUsersNodes.val();

					if (selectedUsersIds.length > 0) {
						document.<portlet:namespace />fm.<portlet:namespace /><%= Constants.CMD %>.value = action;
						document.<portlet:namespace />fm.<portlet:namespace />redirect.value = location.href;
						document.<portlet:namespace />fm.<portlet:namespace />userIds.value = selectedUsersIds.join();
						document.<portlet:namespace />fm.<portlet:namespace />type.value = type;

						if (action == 'exportVCard') {
							submitForm(document.<portlet:namespace />fm, '<liferay-portlet:resourceURL id="exportVCards" />');
						}
						else {
							submitForm(document.<portlet:namespace />fm, '<portlet:actionURL />');
						}
					}
				}
			</c:when>
			<c:otherwise>
				event.preventDefault();

				if (action == 'exportVCard') {
					location.href = '<liferay-portlet:resourceURL id="exportVCard"><portlet:param name="userId" value="<%= String.valueOf(user2.getUserId()) %>" /></liferay-portlet:resourceURL>'
				}
				else {
					A.io.request(
						'<portlet:actionURL />',
						{
							data: {
								cmd: action,
								redirect: '<%= currentURL %>',
								type: type,
								userIds: '<%= user2.getUserId() %>'
							},
							after: {
								failure: function(event, id, obj) {
									var saveMessages = A.one('#<portlet:namespace/>saveMessages');

									if (saveMessages) {
										saveMessages.html('<span class="portlet-msg-error">' + Liferay.Language.get('an-error-occurred-while-retrieving-the-users-information') + '</span>');
									}
								},
								success: function(event, id, obj) {
									Liferay.ContactsCenter.renderContent(this.get('responseData'));
								}
							}
						}
					);
				}
			</c:otherwise>
		</c:choose>
	}
</aui:script>