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

<div>

	<%
	long userId = ParamUtil.getLong(request, "userId");

	User user2 = null;

	if (userId > 0) {
		user2 = UserLocalServiceUtil.getUser(userId);
	}
	%>

	<c:if test="<%= user2 != null %>">
		<div id="<portlet:namespace />contactSummary">
			<liferay-util:include page="/contacts_center/view_user.jsp" servletContext="<%= application %>" />
		</div>
	</c:if>

	<span id="<portlet:namespace />contactsToolbar">

		<%
		boolean showDetailView = ParamUtil.getBoolean(request, "showDetailView");
		%>

		<c:choose>
			<c:when test="<%= showDetailView %>">
				<div class="lfr-button-column">
					<div class="lfr-button-column-content">
						<aui:button-row cssClass="edit-toolbar" id='<%= renderResponse.getNamespace() + "userToolbar" %>' />
					</div>
				</div>

				<aui:script use="aui-base,liferay-contacts-center">
					var buttonRow = A.one('#<portlet:namespace />userToolbar');

					var contactsToolbarChildren = [];

					contactsToolbarChildren.push(
						{
							cssClass: '',
							handler: function(event) {
								Liferay.ContactsCenter._setVisibleSelectedUsersView();
							},
							icon: 'back',
							id: '<portlet:namespace />backSelection',
							label: '<%= UnicodeLanguageUtil.get(pageContext, "back-to-selection") %>'
						}
					);

					new A.Toolbar(
						{
							activeState: false,
							boundingBox: buttonRow,
							children: contactsToolbarChildren
						}
					).render();
				</aui:script>
			</c:when>
			<c:otherwise>
				<liferay-util:include page="/contacts_center/contacts_center_toolbar.jsp" servletContext="<%= application %>" />
			</c:otherwise>
		</c:choose>
	</span>
</div>