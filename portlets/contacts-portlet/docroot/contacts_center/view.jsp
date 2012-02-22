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
int socialRelationType = ParamUtil.getInteger(request, "socialRelationType");

String name = ParamUtil.getString(request, "name");

boolean userPublicPage = false;

Group group = themeDisplay.getScopeGroup();

if (group.isUser() && layout.isPublicLayout()) {
	userPublicPage = true;
}

LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();

if (userPublicPage) {
	params.put("socialRelation", new Long[] {group.getClassPK()});
}
else if (socialRelationType != 0) {
	params.put("socialRelationType", new Long[] {themeDisplay.getUserId(), new Long(socialRelationType)});
}

List<User> users = UserLocalServiceUtil.search(company.getCompanyId(), name, WorkflowConstants.STATUS_APPROVED, params, 0, maxResultCount, new UserLastNameComparator(true));
int usersCount = UserLocalServiceUtil.searchCount(themeDisplay.getCompanyId(), name, WorkflowConstants.STATUS_APPROVED, params);

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setWindowState(WindowState.NORMAL);
%>

<c:choose>
	<c:when test="<%= userPublicPage && (usersCount <= 0) %>">
		<aui:layout cssClass="contacts-center-home">
			<h3 class="header-title">
				<liferay-ui:message arguments="<%= new Object[] {group.getDescriptiveName(locale), String.valueOf(usersCount)} %>" key="x-has-no-contacts" />
			</h3>
		</aui:layout>
	</c:when>
	<c:otherwise>
		<div id="<portlet:namespace/>saveMessages"><!-- --></div>

		<aui:form action="<%= portletURL.toString() %>" method="post" name="fm">
			<aui:input name="<%= Constants.CMD %>" type="hidden" value="" />
			<aui:input name="redirect" type="hidden" value="" />
			<aui:input name="userIds" type="hidden" value="" />
			<aui:input name="type" type="hidden" value="" />

			<aui:layout cssClass="toolbar">
				<aui:column cssClass="search-column" columnWidth="30">
					<div class="lfr-search-column contacts-search aui-search-bar">
						<aui:input cssClass="search-input" label="" id="name" name="name" size="30" type="text" value="<%= HtmlUtil.escape(name) %>" />
					</div>
				</aui:column>

				<aui:column cssClass="button-column" columnWidth="70">
					<div id="<portlet:namespace/>userToolbarButtons"><!-- --></div>

					<div id="<portlet:namespace/>contactCenterToolbarButtons" class="aui-helper-hidden">
						<liferay-util:include page="/contacts_center/contacts_center_toolbar.jsp" servletContext="<%= application %>" />
					</div>
				</aui:column>
			</aui:layout>

			<aui:layout cssClass="contacts-result-container lfr-app-column-view">
				<aui:column columnWidth="30" first="<%= true %>" cssClass="contacts-list">
					<c:if test="<%= !userPublicPage %>">
						<aui:layout cssClass="contact-group-filter">
							<aui:select inlineField="true" label="" name="socialRelationType">
								<aui:option label="all" selected='<%= socialRelationType == 0 %>' value="all" />
								<aui:option label="connections" selected='<%= socialRelationType == SocialRelationConstants.TYPE_BI_CONNECTION %>' value="<%= SocialRelationConstants.TYPE_BI_CONNECTION %>" />
								<aui:option label="following" selected='<%= socialRelationType == SocialRelationConstants.TYPE_UNI_FOLLOWER %>' value="<%= SocialRelationConstants.TYPE_UNI_FOLLOWER %>" />
							</aui:select>
						</aui:layout>
					</c:if>

					<aui:layout cssClass='<%= userPublicPage ? "contacts-result personal-contact-list" : "contacts-result" %>'>

						<%
						String lastNameAnchor = StringPool.SPACE;

						for (User user2 : users) {
							String lastName = user2.getLastName();

							String curLastNameAnchor = LanguageUtil.get(pageContext, "no-last-name");

							if (Validator.isNotNull(lastName)) {
								curLastNameAnchor = StringUtil.upperCase(lastName.substring(0, 1));
							}
						%>

							<c:if test="<%= !curLastNameAnchor.equals(lastNameAnchor) %>">

								<%
								lastNameAnchor = curLastNameAnchor;
								%>

								<div class="lastNameAnchor">
									<a><liferay-ui:message key="<%= lastNameAnchor %>" /></a>
								</div>
							</c:if>

							<liferay-portlet:renderURL windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>" var="viewSummaryURL">
								<portlet:param name="mvcPath" value="/contacts_center/view_resources.jsp" />
								<portlet:param name="userId" value="<%= String.valueOf(user2.getUserId()) %>" />
							</liferay-portlet:renderURL>

							<div class="lfr-contact">
								<div class="lfr-contact-checkbox">
									<input class="contact-ids" <%= themeDisplay.getUserId() == user2.getUserId() ? "disabled=\"true\"" : StringPool.BLANK %> name="contact-ids-<%= user2.getUserId() %>" type="checkbox" value="<%= user2.getUserId() %>" />
								</div>

								<div class="lfr-contact-grid-item" data-viewSummaryURL="<%= viewSummaryURL %>" data-userId="<%= user2.getUserId() %>">
									<div class="lfr-contact-thumb">
										<img alt="<%= HtmlUtil.escape(user2.getFullName()) %>" src="<%= user2.getPortraitURL(themeDisplay) %>" />
									</div>

									<div class="lfr-contact-info">
										<div class="lfr-contact-name">
											<a>
												<c:if test="<%= Validator.isNotNull(user2.getLastName()) %>">
													<%= HtmlUtil.escape(user2.getLastName()) %>,
												</c:if>

												<%= HtmlUtil.escape(user2.getFirstName()) %>
											</a>
										</div>

										<div class="lfr-contact-extra">
											<%= HtmlUtil.escape(user2.getEmailAddress()) %>
										</div>
									</div>

									<div class="clear"><!-- --></div>
								</div>
							</div>

						<%
						}
						%>

						<c:if test="<%= usersCount > maxResultCount %>">
							<div class="more-results">
								<a href="javascript:;" data-end="<%= maxResultCount %>" data-lastNameAnchor="<%= lastNameAnchor %>"><liferay-ui:message key="view-more" /> (<%= usersCount - maxResultCount %>)</a>
							</div>
						</c:if>
					</aui:layout>
				</aui:column>

				<aui:column columnWidth="70" cssClass="contacts-container">
					<div id="<portlet:namespace/>detailUserView">
						<c:choose>
							<c:when test="<%= userPublicPage %>">

								<%
								request.setAttribute(WebKeys.CONTACTS_USER, users.get(0));
								%>

								<liferay-util:include page="/contacts_center/view_user.jsp" servletContext="<%= application %>" />
							</c:when>
							<c:otherwise>
								<aui:layout cssClass="contacts-center-home">
									<liferay-ui:header
										title="contacts-center"
									/>

									<%
									int allUsersCount = UserLocalServiceUtil.searchCount(themeDisplay.getCompanyId(), StringPool.BLANK, WorkflowConstants.STATUS_APPROVED, null);
									int connectionUsersCount = UserLocalServiceUtil.getSocialUsersCount(themeDisplay.getUserId(), SocialRelationConstants.TYPE_BI_CONNECTION);
									int followingUsersCount = UserLocalServiceUtil.getSocialUsersCount(themeDisplay.getUserId(), SocialRelationConstants.TYPE_UNI_FOLLOWER);
									%>

									<aui:layout cssClass="contacts-count connections">
										<a href="javascript:;"><liferay-ui:message arguments="<%= String.valueOf(connectionUsersCount) %>" key="you-have-x-connections" /></a>
									</aui:layout>

									<aui:layout cssClass="contacts-count followings">
										<a href="javascript:;"><liferay-ui:message arguments="<%= String.valueOf(followingUsersCount) %>" key="you-are-following-x-people" /></a>
									</aui:layout>

									<aui:layout cssClass="contacts-count all">
										<a href="javascript:;"><liferay-ui:message arguments="<%= String.valueOf(allUsersCount) %>" key="view-all-x-users" /></a>
									</aui:layout>

									<c:if test="<%= (connectionUsersCount <= 0) && (followingUsersCount <= 0) %>">
										<aui:layout cssClass="contacts-center-introduction">
											<liferay-ui:message key="contacts-center-allows-you-to-search-view-and-establish-social-relations-with-other-users" />
										</aui:layout>
									</c:if>
								</aui:layout>
							</c:otherwise>
						</c:choose>
					</div>

					<div id="<portlet:namespace/>selectedUsersView"><!-- --></div>
				</aui:column>
			</aui:layout>
		</aui:form>

		<aui:script use="aui-io,datatype-number,liferay-contacts-center">
			var searchInput = A.one('.contacts-portlet #<portlet:namespace />name');

			var contactsCenter = new Liferay.ContactsCenter(
				{
					contactsResult: '.contacts-portlet .contacts-result-content',
					contactsResultContainer: '.contacts-portlet .contacts-result',
					contactsResultURL: '<portlet:resourceURL id="getContacts"><portlet:param name="portletResource" value="<%= portletResource %>" /></portlet:resourceURL>',
					contactsSearchInput: '#<portlet:namespace />name',
					namespace: '<portlet:namespace />'
				}
			);

			Liferay.ContactsCenter = contactsCenter;

			<c:if test="<%= !userPublicPage %>">
				var contactFilterSelect = A.one('.contacts-portlet .contact-group-filter select[name=<portlet:namespace />socialRelationType]');

				contactFilterSelect.on(
					'change',
					function(event) {
						searchInput.set('value', '');

						contactsCenter.updateContacts(searchInput.get('value'), contactFilterSelect.get('value'));
					}
				);
			</c:if>

			var contactsResult = A.one('.contacts-portlet .contacts-result');

			contactsResult.delegate(
				'click',
				function(event) {
					var node = event.currentTarget;

					A.io.request(
						node.getAttribute('data-viewSummaryURL'),
						{
							after: {
								failure: function(event, id, obj) {
									var saveMessages = A.one('#<portlet:namespace/>saveMessages');

									if (saveMessages) {
										saveMessages.html('<span class="portlet-msg-error">' + Liferay.Language.get('an-error-occurred-while-retrieving-the-users-information') + '</span>');
									}
								},
								success: function(event, id, obj) {
									contactsCenter.renderContent(this.get('responseData'), true);
								}
							},
							data: {
								userId: node.getAttribute('data-userId')
							}
						}
					);
				},
				'.lfr-contact-grid-item'
			);

			contactsResult.delegate(
				'click',
				function(event) {
					var node = event.currentTarget;

					var start = A.DataType.Number.parse(node.getAttribute('data-end'));
					var end = start + <%= maxResultCount %>;

					var lastNameAnchor = node.getAttribute('data-lastNameAnchor');

					A.io.request(
						'<portlet:resourceURL id="getContacts"><portlet:param name="portletResource" value="<%= portletResource %>" /></portlet:resourceURL>',
						{
							after: {
								success: function(event, id, obj) {
									var responseData = this.get('responseData');

									contactsCenter.showMoreResult(responseData, lastNameAnchor);
								}
							},
							data: {
								end: end,
								keywords: searchInput.get('value'),
								socialRelationType: contactFilterSelect.get('value') || 'all',
								start: start
							},
							type: 'json'
						}
					);
				},
				'.more-results a'
			);

			contactsResult.delegate(
				'click',
				function(event) {
					var checkBox = event.target;

					var userId = checkBox.val();

					if (checkBox.get('checked')) {
						A.io.request(
							'<portlet:resourceURL id="getContact"><portlet:param name="portletResource" value="<%= portletResource %>" /></portlet:resourceURL>',
							{
								after: {
									failure: function(event, id, obj) {
										var saveMessages = A.one('#<portlet:namespace/>saveMessages');

										if (saveMessages) {
											saveMessages.html('<span class="portlet-msg-error">' + responseData.message + '</span>');
										}
									},
									success: function(event, id, obj) {
										var responseData = this.get('responseData');

										if (responseData.success) {
											contactsCenter.addContactResult(responseData);
										}
									}
								},
								data: {
									userId: userId
								},
								dataType: 'json'
							}
						);
					}
					else {
						contactsCenter.deleteContactResult(userId);
					}
				},
				'.contact-ids'
			);

			A.one('.contacts-container-content').delegate(
				'click',
				function(event) {
					var instance = this;

					var node = event.currentTarget;

					var userId = instance.one('input').val();

					var ioRequest = A.io.request(
						node.getAttribute('data-viewSummaryURL'),
						{
							after: {
								failure: function(event, id, obj) {
									var saveMessages = A.one('#<portlet:namespace/>saveMessages');

									if (saveMessages) {
										saveMessages.html('<span class="portlet-msg-error">' + Liferay.Language.get('an-error-occurred-while-retrieving-the-users-information') + '</span>');
									}
								},
								success: function(event, id, obj) {
									contactsCenter.renderContent(this.get('responseData'));
								}
							},
							data: {
								showDetailView: true,
								userId: userId
							}
						}
					);
				},
				'.lfr-contact-grid-item'
			);

			<c:if test="<%= !userPublicPage %>">
				A.one('.contacts-portlet .contacts-center-home .connections').on(
					'click',
					function(event) {
						contactFilterSelect.set('value', '<%= SocialRelationConstants.TYPE_BI_CONNECTION %>');

						contactsCenter.updateContacts(searchInput.get('value'), contactFilterSelect.get('value'));
					},
					'a'
				);

				A.one('.contacts-portlet .contacts-center-home .followings').on(
					'click',
					function(event) {
						contactFilterSelect.set('value', '<%= SocialRelationConstants.TYPE_UNI_FOLLOWER %>');

						contactsCenter.updateContacts(searchInput.get('value'), contactFilterSelect.get('value'));
					},
					'a'
				);

				A.one('.contacts-portlet .contacts-center-home .all').on(
					'click',
					function(event) {
						contactFilterSelect.set('value', 'all');

						searchInput.set('value', '');

						contactsCenter.updateContacts('', contactFilterSelect.get('value'));
					},
					'a'
				);
			</c:if>
		</aui:script>
	</c:otherwise>
</c:choose>