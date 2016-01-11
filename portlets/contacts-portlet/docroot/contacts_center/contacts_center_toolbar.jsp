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

<div class="lfr-button-column" id="<portlet:namespace />buttonColumn">
	<div class="lfr-button-column-content">
		<aui:button-row cssClass="edit-toolbar" id='<%= renderResponse.getNamespace() + "userToolbar" %>' />

		<div class="btn view-more-button">
			<i class="icon-ellipsis-horizontal"></i>

			<liferay-ui:message key="more" />
		</div>
	</div>
</div>

<aui:script position="inline" use="aui-dialog-iframe-deprecated,aui-io-plugin-deprecated,aui-io-request-deprecated,aui-toolbar,liferay-util-window">
	var buttonRow = A.one('#<portlet:namespace />userToolbar');

	var contactsToolbarChildren = [];

	contactsToolbarChildren.push(
		new A.Button(
			{
				on: {
					click: function(event) {
						<portlet:namespace />relationAction(event, '<portlet:actionURL name="requestSocialRelation" windowState="<%= LiferayWindowState.NORMAL.toString() %>"><portlet:param name="type" value="<%= String.valueOf(SocialRelationConstants.TYPE_BI_CONNECTION) %>" /></portlet:actionURL>');
					}
				},
				icon: 'icon-plus-sign',
				id: '<portlet:namespace />addConnectionButton',
				label: '<%= UnicodeLanguageUtil.get(request, "connect") %>',
				render: true,
				visible: <%= showAddAsConnectionButton %>
			}
		)
	);

	contactsToolbarChildren.push(
		new A.Button(
			{
				on: {
					click: function(event) {
						<portlet:namespace />relationAction(event, '<portlet:actionURL name="deleteSocialRelation" windowState="<%= LiferayWindowState.NORMAL.toString() %>"><portlet:param name="type" value="<%= String.valueOf(SocialRelationConstants.TYPE_BI_CONNECTION) %>" /></portlet:actionURL>');
					}
				},
				icon: 'icon-minus-sign',
				id: '<portlet:namespace />removeConnectionButton',
				label: '<%= UnicodeLanguageUtil.get(request, "disconnect") %>',
				render: true,
				visible: <%= showRemoveAsConnectionButton %>
			}
		)
	);

	contactsToolbarChildren.push(
		new A.Button(
			{
				cssClass: 'more',
				on: {
					click: function(event) {
						<portlet:namespace />relationAction(event, '<portlet:actionURL name="addSocialRelation" windowState="<%= LiferayWindowState.NORMAL.toString() %>"><portlet:param name="type" value="<%= String.valueOf(SocialRelationConstants.TYPE_UNI_FOLLOWER) %>" /></portlet:actionURL>');
					}
				},
				icon: 'icon-plus-sign',
				id: '<portlet:namespace />followButton',
				label: '<%= UnicodeLanguageUtil.get(request, "follow") %>',
				render: true,
				visible: <%= showFollowButton %>
			}
		)
	);

	contactsToolbarChildren.push(
		new A.Button(
			{
				cssClass: 'more',
				on: {
					click: function(event) {
						<portlet:namespace />relationAction(event, '<portlet:actionURL name="deleteSocialRelation" windowState="<%= LiferayWindowState.NORMAL.toString() %>"><portlet:param name="type" value="<%= String.valueOf(SocialRelationConstants.TYPE_UNI_FOLLOWER) %>" /></portlet:actionURL>');
					}
				},
				icon: 'icon-minus-sign',
				id: '<portlet:namespace />unfollowButton',
				label: '<%= UnicodeLanguageUtil.get(request, "unfollow") %>',
				render: true,
				visible: <%= showUnFollowButton %>
			}
		)
	);

	contactsToolbarChildren.push(
		new A.Button(
			{
				cssClass: 'more',
				on: {
					click: function(event) {
						<portlet:namespace />relationAction(event, '<portlet:actionURL name="addSocialRelation" windowState="<%= LiferayWindowState.NORMAL.toString() %>"><portlet:param name="type" value="<%= String.valueOf(SocialRelationConstants.TYPE_UNI_ENEMY) %>" /></portlet:actionURL>');
					}
				},
				icon: 'icon-ban-circle',
				id: '<portlet:namespace />blockButton',
				label: '<%= UnicodeLanguageUtil.get(request, "block") %>',
				render: true,
				visible: <%= showBlockButton %>
			}
		)
	);

	contactsToolbarChildren.push(
		new A.Button(
			{
				cssClass: 'more',
				on: {
					click: function(event) {
						<portlet:namespace />relationAction(event, '<portlet:actionURL name="deleteSocialRelation" windowState="<%= LiferayWindowState.NORMAL.toString() %>"><portlet:param name="type" value="<%= String.valueOf(SocialRelationConstants.TYPE_UNI_ENEMY) %>" /></portlet:actionURL>');
					}
				},
				icon: 'icon-ok',
				id: '<portlet:namespace />unblockButton',
				label: '<%= UnicodeLanguageUtil.get(request, "unblock") %>',
				render: true,
				visible: <%= showUnBlockButton %>
			}
		)
	);

	<%
	ServletContext servletContext = ServletContextPool.get("private-messaging-portlet");
	%>

	<c:if test="<%= Validator.isNotNull(servletContext) && ((user2 == null) || (user2.getUserId() != themeDisplay.getUserId())) %>">
		contactsToolbarChildren.push(
			{
				icon: 'icon-envelope',
				id: '<portlet:namespace />sendMessageButton',
				label: '<%= UnicodeLanguageUtil.get(request, "message") %>',
				on: {
					click: function(event) {
						<portlet:renderURL var="redirectURL" windowState="<%= LiferayWindowState.NORMAL.toString() %>" />

						var uri = '<liferay-portlet:renderURL portletName="<%= PortletKeys.PRIVATE_MESSAGING %>" windowState="<%= LiferayWindowState.POP_UP.toString() %>"><portlet:param name="mvcPath" value="/new_message.jsp" /><portlet:param name="redirect" value="<%= redirectURL %>" /></liferay-portlet:renderURL>';

						<c:choose>
							<c:when test="<%= user2 != null %>">
								var userIds = [<%= user2.getUserId() %>];
							</c:when>
							<c:otherwise>
								var userIds = A.all('.lfr-contact-grid-item input').val();
							</c:otherwise>
						</c:choose>

						uri = Liferay.Util.addParams('<%= PortalUtil.getPortletNamespace(PortletKeys.PRIVATE_MESSAGING) %>userIds=' + userIds.join(), uri) || uri;

						Liferay.Util.openWindow(
							{
								dialog: {
									centered: true,
									constrain: true,
									cssClass: 'private-messaging-portlet',
									destroyOnHide: true,
									height: 600,
									modal: true,
									plugins: [Liferay.WidgetZIndex],
									width: 600
								},
								id: '<%= PortalUtil.getPortletNamespace(PortletKeys.PRIVATE_MESSAGING) %>Dialog',
								title: '<%= UnicodeLanguageUtil.get(request, "new-message") %>',
								uri: uri
							}
						);
					}
				}
			}
		);
	</c:if>

	contactsToolbarChildren.push(
		new A.Button(
			{
				cssClass: 'more',
				icon: 'icon-save',
				id: '<portlet:namespace />exportButton',
				label: '<%= UnicodeLanguageUtil.get(request, "vcard") %>',
				on: {
					click: function(event) {
						<c:choose>
							<c:when test="<%= (user2 != null) %>">
								location.href = '<liferay-portlet:resourceURL id="exportVCard"><portlet:param name="userId" value="<%= String.valueOf(user2.getUserId()) %>" /></liferay-portlet:resourceURL>';
							</c:when>
							<c:otherwise>
								location.href = '<liferay-portlet:resourceURL id="exportVCards" />&<portlet:namespace />userIds=' + A.all('.lfr-contact-grid-item input').val();
							</c:otherwise>
						</c:choose>
					}
				}
			}
		)
	);

	var contactsToolbar = new A.Toolbar(
		{
			activeState: false,
			boundingBox: buttonRow,
			children: contactsToolbarChildren
		}
	).render();

	var editToolbar = A.one('.edit-toolbar');

	editToolbar.toggleClass('hide-more-buttons', true);

	var buttonColumn = A.one('#<portlet:namespace />buttonColumn');

	var viewMoreButton = buttonColumn.one('.view-more-button');

	buttonColumn.delegate(
		'click',
		function(event) {
			editToolbar.toggleClass('hide-more-buttons', false);

			viewMoreButton.hide();
		},
		'.view-more-button'
	);

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

		var contactFilerSelectValue = '<%= ContactsConstants.FILTER_BY_DEFAULT %>';

		if (contactFilterSelect) {
			contactFilerSelectValue = contactFilterSelect.get('value');
		}

		var searchInput = A.one('.contacts-portlet #<portlet:namespace />name');

		A.io.request(
			uri,
			{
				after: {
					failure: function(event, id, obj) {
						Liferay.component('contactsCenter').showMessage(false);
					},
					success: function(event, id, obj) {
						Liferay.component('contactsCenter').renderSelectedContacts(this.get('responseData'), lastNameAnchor);
					}
				},
				data: {
					<portlet:namespace />end: end,
					<portlet:namespace />filterBy: contactFilerSelectValue,
					<portlet:namespace />jsonFormat: true,
					<portlet:namespace />keywords: searchInput.get('value'),
					<portlet:namespace />start: 0,
					<portlet:namespace />userIds: userIds.join()
				},
				dataType: 'JSON'
			}
		);
	}
</aui:script>