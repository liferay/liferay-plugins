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

boolean viewRelationActions = true;

if (user2 != null) {
	if (SocialRelationLocalServiceUtil.hasRelation(user2.getUserId(), themeDisplay.getUserId(), SocialRelationConstants.TYPE_UNI_ENEMY)) {
		viewRelationActions = false;
	}
	else if (SocialRelationLocalServiceUtil.hasRelation(themeDisplay.getUserId(), user2.getUserId(), SocialRelationConstants.TYPE_UNI_ENEMY)) {
		viewRelationActions = false;
	}
}

boolean removeConnectionButton = (user2 == null) || (viewRelationActions && SocialRelationLocalServiceUtil.hasRelation(themeDisplay.getUserId(), user2.getUserId(), SocialRelationConstants.TYPE_BI_CONNECTION));
boolean connectionRequestedButton = (user2 != null) && SocialRequestLocalServiceUtil.hasRequest(themeDisplay.getUserId(), User.class.getName(), themeDisplay.getUserId(), SocialRelationConstants.TYPE_BI_CONNECTION, user2.getUserId(), SocialRequestConstants.STATUS_PENDING);
boolean addConnectionButton = (user2 == null) || (viewRelationActions && !connectionRequestedButton && SocialRelationLocalServiceUtil.isRelatable(themeDisplay.getUserId(), user2.getUserId(), SocialRelationConstants.TYPE_BI_CONNECTION));
boolean unFollowButton = (user2 == null) || (viewRelationActions && SocialRelationLocalServiceUtil.hasRelation(themeDisplay.getUserId(), user2.getUserId(), SocialRelationConstants.TYPE_UNI_FOLLOWER));
boolean followButton = (user2 == null) || (viewRelationActions && SocialRelationLocalServiceUtil.isRelatable(themeDisplay.getUserId(), user2.getUserId(), SocialRelationConstants.TYPE_UNI_FOLLOWER));
boolean unBlockButton = (user2 == null) || SocialRelationLocalServiceUtil.hasRelation(themeDisplay.getUserId(), user2.getUserId(), SocialRelationConstants.TYPE_UNI_ENEMY);
boolean blockButton = (user2 == null) || SocialRelationLocalServiceUtil.isRelatable(themeDisplay.getUserId(), user2.getUserId(), SocialRelationConstants.TYPE_UNI_ENEMY);
%>

<div class="lfr-button-column">
	<div class="lfr-button-column-content">
		<aui:button-row cssClass="edit-toolbar" id='<%= renderResponse.getNamespace() + "userToolbar" %>' />
	</div>
</div>

<aui:script use="aui-dialog,aui-dialog-iframe">
	var buttonRow = A.one('#<portlet:namespace />userToolbar');

	var contactsToolbarChildren = [];

	<c:if test="<%= addConnectionButton %>">
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

	<c:if test="<%= removeConnectionButton %>">
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

	<c:if test="<%= followButton %>">
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

	<c:if test="<%= unFollowButton %>">
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

	<c:if test="<%= blockButton %>">
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

	<c:if test="<%= unBlockButton %>">
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

	<c:if test="<%= user2 != null && user.getUserId() == user2.getUserId() %>">
		contactsToolbarChildren.push(
			{
				handler: function(event) {
					event.preventDefault();

					var url = Liferay.Util.addParams('controlPanelCategory=<%= PortletCategoryKeys.MY %>', '<%= themeDisplay.getURLMyAccount().toString() %>');

					var dialog = new A.Dialog(
						{
							align: {
								node: null,
								points: ['tc', 'tc']
							},
							constrain2view: true,
							modal: true,
							resizable: false,
							title: '<liferay-ui:message key="edit-profile" />',
							width: 950
						}
					).plug(
						A.Plugin.DialogIframe,
						{
							uri: url
						}
					).render();
				},
				icon: 'edit',
				label: '<%= UnicodeLanguageUtil.get(pageContext, "edit-profile") %>'
			}
		);
	</c:if>

	<c:if test="<%= (user2 != null) %>">
		<portlet:resourceURL id="exportVCard" var="exportURL">
			<portlet:param name="userId" value="<%= String.valueOf(user2.getUserId()) %>" />
		</portlet:resourceURL>

		contactsToolbarChildren.push(
			{
				handler: function(event) {
					location.href = '<%= exportURL %>'
				},
				icon: 'export',
				label: '<%= UnicodeLanguageUtil.get(pageContext, "export-vcard") %>'
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
		var url = '<portlet:actionURL />';

		<c:choose>
			<c:when test="<%= (user2 == null) %>">
				var selectedUsersNodes = A.all('.lfr-contact-grid-item input');

				if (selectedUsersNodes.size() > 0) {
					var selectedUsersIds = selectedUsersNodes.val();

					if (selectedUsersIds.length > 0) {
						document.<portlet:namespace />fm.<portlet:namespace />userIds.value = selectedUsersIds.join();
						document.<portlet:namespace />fm.<portlet:namespace />type.value = type;
						document.<portlet:namespace />fm.<portlet:namespace /><%= Constants.CMD %>.value = action;
						document.<portlet:namespace />fm.<portlet:namespace />redirect.value = location.href;

						submitForm(document.<portlet:namespace />fm, url);
					}
				}
			</c:when>
			<c:otherwise>
				<liferay-portlet:renderURL windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>" var="viewSummaryURL">
					<portlet:param name="mvcPath" value="/contacts_center/view_resources.jsp" />
					<portlet:param name="userId" value="<%= String.valueOf(user2.getUserId()) %>" />
				</liferay-portlet:renderURL>

				event.preventDefault();

				A.io.request(
					url,
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
									saveMessages.html('<span class="portlet-msg-error">' + Liferay.Language.get('an-error-occurred-while-retrieving-the-user-information') + '</span>');
								}
							},
							success: function(event, id, obj) {
								Liferay.ContactsCenter.renderContent(this.get('responseData'));
							}
						}
					}
				);
			</c:otherwise>
		</c:choose>
	}
</aui:script>