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

	<%
	ServletContext servletContext = ServletContextPool.get("private-messaging-portlet");
	%>

	contactsToolbarChildren.push(
		new A.Button(
			{
				cssClass: 'more',
				icon: 'icon-save',
				id: '<portlet:namespace />exportButton',
				label: '<%= UnicodeLanguageUtil.get(pageContext, "vcard") %>',
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
				dataType: 'json'
			}
		);
	}
</aui:script>