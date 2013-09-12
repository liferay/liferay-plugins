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

<aui:script position="inline" use="liferay-util-window,aui-dialog-iframe-deprecated">
	var buttonRow = A.one('#<portlet:namespace />userToolbar');

	var contactsToolbarChildren = [];

	contactsToolbarChildren.push(
		{
			on: {
				click: function(event) {
					<portlet:namespace />relationAction(event, '<portlet:actionURL name="requestSocialRelation" windowState="<%= LiferayWindowState.NORMAL.toString() %>"><portlet:param name="type" value="<%= String.valueOf(SocialRelationConstants.TYPE_BI_CONNECTION) %>" /></portlet:actionURL>');
				}
			},
			icon: 'add-coworker',
			id: '<portlet:namespace />addConnectionButton',
			label: '<%= UnicodeLanguageUtil.get(pageContext, "connect") %>',
			visible: <%= showAddAsConnectionButton %>
		}
	);

	contactsToolbarChildren.push(
		{
			on: {
				click: function(event) {
					<portlet:namespace />relationAction(event, '<portlet:actionURL name="deleteSocialRelation" windowState="<%= LiferayWindowState.NORMAL.toString() %>"><portlet:param name="type" value="<%= String.valueOf(SocialRelationConstants.TYPE_BI_CONNECTION) %>" /></portlet:actionURL>');
				}
			},
			icon: 'remove-coworker',
			id: '<portlet:namespace />removeConnectionButton',
			label: '<%= UnicodeLanguageUtil.get(pageContext, "disconnect") %>',
			visible: <%= showRemoveAsConnectionButton %>
		}
	);

	contactsToolbarChildren.push(
		{
			on: {
				click: function(event) {
					<portlet:namespace />relationAction(event, '<portlet:actionURL name="addSocialRelation" windowState="<%= LiferayWindowState.NORMAL.toString() %>"><portlet:param name="type" value="<%= String.valueOf(SocialRelationConstants.TYPE_UNI_FOLLOWER) %>" /></portlet:actionURL>');
				}
			},
			icon: 'follow',
			id: '<portlet:namespace />followButton',
			label: '<%= UnicodeLanguageUtil.get(pageContext, "follow") %>',
			visible: <%= showFollowButton %>
		}
	);

	contactsToolbarChildren.push(
		{
			on: {
				click: function(event) {
					<portlet:namespace />relationAction(event, '<portlet:actionURL name="deleteSocialRelation" windowState="<%= LiferayWindowState.NORMAL.toString() %>"><portlet:param name="type" value="<%= String.valueOf(SocialRelationConstants.TYPE_UNI_FOLLOWER) %>" /></portlet:actionURL>');
				}
			},
			icon: 'unfollow',
			id: '<portlet:namespace />unfollowButton',
			label: '<%= UnicodeLanguageUtil.get(pageContext, "unfollow") %>',
			visible: <%= showUnFollowButton %>
		}
	);

	contactsToolbarChildren.push(
		{
			on: {
				click: function(event) {
					<portlet:namespace />relationAction(event, '<portlet:actionURL name="addSocialRelation" windowState="<%= LiferayWindowState.NORMAL.toString() %>"><portlet:param name="type" value="<%= String.valueOf(SocialRelationConstants.TYPE_UNI_ENEMY) %>" /></portlet:actionURL>');
				}
			},
			icon: 'block',
			id: '<portlet:namespace />blockButton',
			label: '<%= UnicodeLanguageUtil.get(pageContext, "block") %>',
			visible: <%= showBlockButton %>
		}
	);

	contactsToolbarChildren.push(
		{
			on: {
				click: function(event) {
					<portlet:namespace />relationAction(event, '<portlet:actionURL name="deleteSocialRelation" windowState="<%= LiferayWindowState.NORMAL.toString() %>"><portlet:param name="type" value="<%= String.valueOf(SocialRelationConstants.TYPE_UNI_ENEMY) %>" /></portlet:actionURL>');
				}
			},
			icon: 'unblock',
			id: '<portlet:namespace />unblockButton',
			label: '<%= UnicodeLanguageUtil.get(pageContext, "unblock") %>',
			visible: <%= showUnBlockButton %>
		}
	);

	<%
	ServletContext servletContext = ServletContextPool.get("private-messaging-portlet");
	%>

	<c:if test="<%= Validator.isNotNull(servletContext) && (user2 == null || (user2.getUserId() != themeDisplay.getUserId())) %>">
		contactsToolbarChildren.push(
			{
				on: {
					click: function(event) {
						<portlet:renderURL var="redirectURL" windowState="<%= LiferayWindowState.NORMAL.toString() %>" />

						var uri = '<liferay-portlet:renderURL portletName="1_WAR_privatemessagingportlet" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>"><portlet:param name="mvcPath" value="/new_message.jsp" /><portlet:param name="redirect" value="<%= redirectURL %>" /></liferay-portlet:renderURL>';

						<c:choose>
							<c:when test="<%= user2 != null %>">
								var userIds = [<%= user2.getUserId() %>];
							</c:when>
							<c:otherwise>
								var userIds = A.all('.lfr-contact-grid-item input').val();
							</c:otherwise>
						</c:choose>

						Liferay.Util.Window.getWindow(
							{
								dialog: {
									align: Liferay.Util.Window.ALIGN_CENTER,
									cssClass: 'private-messaging-portlet',
									destroyOnClose: true,
									modal: true,
									width: 600
								},
								title: '<%= UnicodeLanguageUtil.get(pageContext, "new-message") %>'
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
					}
				},
				icon: 'send-message',
				id: '<portlet:namespace />sendMessageButton',
				label: '<%= UnicodeLanguageUtil.get(pageContext, "message") %>'
			}
		);
	</c:if>

	contactsToolbarChildren.push(
		{
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
			},
			icon: 'export',
			id: '<portlet:namespace />exportButton',
			label: '<%= UnicodeLanguageUtil.get(pageContext, "vcard") %>'
		}
	);

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
						Liferay.ContactsCenter.showMessage(false);
					},
					success: function(event, id, obj) {
						Liferay.ContactsCenter.renderSelectedContacts(this.get('responseData'), lastNameAnchor);
					}
				},
				data: {
					end: end,
					filterBy: contactFilerSelectValue,
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