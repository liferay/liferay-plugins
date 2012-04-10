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
boolean portalUser = ParamUtil.getBoolean(request, "portalUser");
%>

<div>
	<c:choose>
		<c:when test="<%= !portalUser %>">

			<%
			long entryId = ParamUtil.getLong(request, "entryId");
			%>

			<c:if test="<%= entryId > 0 %>">

				<%
				Entry entry = EntryLocalServiceUtil.getEntry(entryId);

				String redirect = ParamUtil.getString(request, "redirect");
				%>

				<div id="<portlet:namespace />contactSummary">
					<liferay-util:include page="/contacts_center/view_entry.jsp" servletContext="<%= application %>" />
				</div>

				<span id="<portlet:namespace />contactsToolbar">
					<div class="lfr-button-column">
						<div class="lfr-button-column-content">
							<aui:button-row cssClass="edit-toolbar" id='<%= renderResponse.getNamespace() + "entryToolbar" %>' />
						</div>
					</div>

					<aui:script use="aui-dialog,aui-io-plugin,aui-toolbar">
						var buttonRow = A.one('#<portlet:namespace />entryToolbar');

						var contactsToolbarChildren = [];

						<portlet:renderURL var="viewEntryURL" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
							<portlet:param name="mvcPath" value="/contacts_center/edit_entry.jsp" />
							<portlet:param name="redirect" value="<%= redirect %>" />
							<portlet:param name="entryId" value="<%= String.valueOf(entryId) %>" />
						</portlet:renderURL>

						contactsToolbarChildren.push(
							{
								handler: function(event) {
									var uri = '<%= viewEntryURL %>';

									var dialog = new A.Dialog(
										{
											centered: true,
											constrain2view: true,
											cssClass: 'contact-dialog',
											destroyOnClose: true,
											modal: true,
											resizable: false,
											title: '<%= LanguageUtil.get(pageContext, "update-contact") %>',
											width: 500
										}
									).plug(
										A.Plugin.IO,
										{
											uri: uri
										}
									).render();

									var fm = A.one('#<portlet:namespace />fm');

									fm.setData('dialogInstance', dialog);
								},
								icon: 'edit',
								id: '<portlet:namespace />edit',
								label: '<%= UnicodeLanguageUtil.get(pageContext, "edit") %>'
							}
						);

						contactsToolbarChildren.push(
							{
								handler: function(event) {
									var confirmMessage = '<%= LanguageUtil.format(pageContext, "are-you-sure-you-want-to-delete-x-from-your-contacts", entry.getFullName()) %>';

									if (confirm(confirmMessage)) {
										A.io.request(
											'<portlet:actionURL name="deleteEntry" />',
											{
												after: {
													failure: function(event, id, obj) {
														var saveMessages = A.one('#<portlet:namespace/>saveMessages');

														if (saveMessages) {
															saveMessages.html('<span class="portlet-msg-error">' + Liferay.Language.get('an-error-occurred-while-retrieving-the-users-information') + '</span>');
														}
													},
													success: function(event, id, obj) {
														location.href = '<%= redirect %>';
													}
												},
												data: {
													entryId: <%= entryId %>
												}
											}
										);
									}
								},
								icon: 'delete',
								id: '<portlet:namespace />delete',
								label: '<%= UnicodeLanguageUtil.get(pageContext, "delete") %>'
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
				</span>
			</c:if>
		</c:when>
		<c:otherwise>

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
		</c:otherwise>
	</c:choose>
</div>