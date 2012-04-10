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

if (showOnlySiteMembers) {
	params.put("usersGroups", new Long(group.getGroupId()));
}

List<BaseModel<?>> contacts = null;
int contactsCount = 0;

if (userPublicPage || showOnlySiteMembers || (socialRelationType != 0)) {
	List<User> users = UserLocalServiceUtil.search(company.getCompanyId(), name, WorkflowConstants.STATUS_APPROVED, params, 0, maxResultCount, new UserLastNameComparator(true));

	contacts = new ArrayList<BaseModel<?>>(users);
	contactsCount = UserLocalServiceUtil.searchCount(themeDisplay.getCompanyId(), name, WorkflowConstants.STATUS_APPROVED, params);
}
else {
	contacts = EntryLocalServiceUtil.searchUsersAndContacts(themeDisplay.getCompanyId(), user.getUserId(), name, 0, maxResultCount);
	contactsCount = EntryLocalServiceUtil.searchUsersAndContactsCount(themeDisplay.getCompanyId(), user.getUserId(), name);
}

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setWindowState(WindowState.NORMAL);
%>

<c:choose>
	<c:when test="<%= userPublicPage && (contactsCount <= 0) %>">
		<aui:layout cssClass="contacts-center-home">
			<h3 class="header-title">
				<liferay-ui:message arguments="<%= new Object[] {group.getDescriptiveName(locale), String.valueOf(contactsCount)} %>" key="x-has-no-contacts" />
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
				<aui:column columnWidth="30" cssClass="search-column">
					<div class="lfr-search-column contacts-search aui-search-bar">
						<aui:input cssClass="search-input" id="name" label="" name="name" size="30" type="text" value="<%= HtmlUtil.escape(name) %>" />
					</div>
				</aui:column>

				<aui:column columnWidth="70" cssClass="button-column">
					<div id="<portlet:namespace/>userToolbarButtons"><!-- --></div>

					<div class="aui-helper-hidden" id="<portlet:namespace/>contactCenterToolbarButtons">
						<liferay-util:include page="/contacts_center/contacts_center_toolbar.jsp" servletContext="<%= application %>" />
					</div>
				</aui:column>
			</aui:layout>

			<aui:layout cssClass="contacts-result-container lfr-app-column-view">
				<aui:column columnWidth="30" cssClass="contacts-list" first="<%= true %>">
					<c:if test="<%= !userPublicPage %>">
						<aui:layout cssClass="contact-group-filter">
							<aui:select inlineField="true" label="" name="socialRelationType">
								<aui:option label="all" selected='<%= socialRelationType == 0 %>' value="all" />
								<aui:option label="connections" selected='<%= socialRelationType == SocialRelationConstants.TYPE_BI_CONNECTION %>' value="<%= SocialRelationConstants.TYPE_BI_CONNECTION %>" />
								<aui:option label="following" selected='<%= socialRelationType == SocialRelationConstants.TYPE_UNI_FOLLOWER %>' value="<%= SocialRelationConstants.TYPE_UNI_FOLLOWER %>" />

								<c:if test="<%= !showOnlySiteMembers %>">
									<aui:option label="my-contacts" selected='<%= socialRelationType == SocialRelationConstants.TYPE_MY_CONTACTS %>' value="<%= SocialRelationConstants.TYPE_MY_CONTACTS %>" />
								</c:if>
							</aui:select>

							<c:if test="<%= !showOnlySiteMembers %>">
								<span class="add-contact">
									<aui:a href="javascript:;" label="add" />
								</span>
							</c:if>
						</aui:layout>
					</c:if>

					<aui:layout cssClass='<%= userPublicPage ? "contacts-result personal-contact-list" : "contacts-result" %>'>

						<%
						String lastNameAnchor = StringPool.SPACE;

						for (BaseModel<?> contact : contacts) {
						%>

							<c:choose>
								<c:when test="<%= contact instanceof User %>">

									<%
									User user2 = (User)contact;

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

									<liferay-portlet:renderURL var="viewUserSummaryURL" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
										<portlet:param name="mvcPath" value="/contacts_center/view_resources.jsp" />
										<portlet:param name="userId" value="<%= String.valueOf(user2.getUserId()) %>" />
										<portlet:param name="portalUser" value="<%= Boolean.TRUE.toString() %>" />
									</liferay-portlet:renderURL>

									<div class="lfr-contact">
										<div class="lfr-contact-checkbox">
											<input class="contact-ids" <%= themeDisplay.getUserId() == user2.getUserId() ? "disabled=\"true\"" : StringPool.BLANK %> name="contact-ids-<%= user2.getUserId() %>" type="checkbox" value="<%= user2.getUserId() %>" />
										</div>

										<div class="lfr-contact-grid-item" data-userId="<%= user2.getUserId() %>" data-viewSummaryURL="<%= viewUserSummaryURL %>">
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
								</c:when>
								<c:otherwise>

									<%
									Entry entry = (Entry)contact;

									String fullName = entry.getFullName();

									String curLastNameAnchor = LanguageUtil.get(pageContext, "no-last-name");

									if (Validator.isNotNull(fullName)) {
										curLastNameAnchor = StringUtil.upperCase(fullName.substring(0, 1));
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

									<liferay-portlet:renderURL var="viewContactSummaryURL" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
										<portlet:param name="mvcPath" value="/contacts_center/view_resources.jsp" />
										<portlet:param name="redirect" value="<%= currentURL %>" />
										<portlet:param name="entryId" value="<%= String.valueOf(entry.getEntryId()) %>" />
										<portlet:param name="portalUser" value="<%= Boolean.FALSE.toString() %>" />
									</liferay-portlet:renderURL>

									<div class="lfr-contact">
										<div class="lfr-contact-checkbox">
											<input class="contact-ids" disabled="true" label="" name="contact-ids-<%= entry.getEntryId() %>" type="checkbox" value="<%= entry.getEntryId() %>" />
										</div>

										<div class="lfr-contact-grid-item" data-userId="" data-viewSummaryURL="<%= viewContactSummaryURL %>">
											<div class="lfr-contact-thumb">
												<img alt="<%= HtmlUtil.escape(fullName) %>" src='<%= themeDisplay.getPathImage() + "/user_male_portrait?img_id=0&t=" %>' />
											</div>

											<div class="lfr-contact-info">
												<div class="lfr-contact-name">
													<a><%= HtmlUtil.escape(fullName) %></a>
												</div>

												<div class="lfr-contact-extra">
													<%= HtmlUtil.escape(entry.getEmailAddress()) %>
												</div>
											</div>

											<div class="clear"><!-- --></div>
										</div>
									</div>
								</c:otherwise>
							</c:choose>

						<%
						}
						%>

						<c:if test="<%= contactsCount > maxResultCount %>">
							<div class="more-results">
								<a data-end="<%= maxResultCount %>" data-lastNameAnchor="<%= lastNameAnchor %>" href="javascript:;"><liferay-ui:message key="view-more" /> (<%= contactsCount - maxResultCount %>)</a>
							</div>
						</c:if>
					</aui:layout>
				</aui:column>

				<aui:column columnWidth="70" cssClass="contacts-container">
					<div id="<portlet:namespace/>detailUserView">
						<c:choose>
							<c:when test="<%= userPublicPage %>">

								<%
								request.setAttribute(WebKeys.CONTACTS_USER, contacts.get(0));
								%>

								<liferay-util:include page="/contacts_center/view_user.jsp" servletContext="<%= application %>" />
							</c:when>
							<c:otherwise>
								<aui:layout cssClass="contacts-center-home">
									<c:choose>
										<c:when test="<%= !showOnlySiteMembers %>">
											<liferay-ui:header title="contacts-center" />
										</c:when>
										<c:otherwise>
											<liferay-ui:header title="members" />
										</c:otherwise>
									</c:choose>

									<%
									int allUsersCount = 0;

									if (userPublicPage || showOnlySiteMembers || (socialRelationType != 0)) {
										allUsersCount = UserLocalServiceUtil.searchCount(themeDisplay.getCompanyId(), StringPool.BLANK, WorkflowConstants.STATUS_APPROVED, params);
									}
									else {
										allUsersCount = EntryLocalServiceUtil.searchUsersAndContactsCount(themeDisplay.getCompanyId(), themeDisplay.getUserId(), StringPool.BLANK);
									}

									params.put("socialRelationType", new Long[] {themeDisplay.getUserId(), new Long(SocialRelationConstants.TYPE_BI_CONNECTION)});

									int connectionUsersCount = UserLocalServiceUtil.searchCount(themeDisplay.getCompanyId(), null, WorkflowConstants.STATUS_APPROVED, params);

									params.put("socialRelationType", new Long[] {themeDisplay.getUserId(), new Long(SocialRelationConstants.TYPE_UNI_FOLLOWER)});

									int followingUsersCount = UserLocalServiceUtil.searchCount(themeDisplay.getCompanyId(), null, WorkflowConstants.STATUS_APPROVED, params);
									%>

									<aui:layout cssClass="contacts-count connections">
										<a href="javascript:;"><liferay-ui:message arguments="<%= String.valueOf(connectionUsersCount) %>" key='<%= showOnlySiteMembers ? "you-have-x-connections-in-this-site" : "you-have-x-connections" %>' /></a>
									</aui:layout>

									<aui:layout cssClass="contacts-count followings">
										<a href="javascript:;"><liferay-ui:message arguments="<%= String.valueOf(followingUsersCount) %>" key='<%= showOnlySiteMembers ? "you-are-following-x-people-in-this-site" : "you-are-following-x-people" %>' /></a>
									</aui:layout>

									<c:if test="<%= !showOnlySiteMembers %>">

										<%
										int myContactsCount = EntryLocalServiceUtil.getEntriesCount(user.getUserId());
										%>

										<aui:layout cssClass="contacts-count contacts">
											<a href="javascript:;"><liferay-ui:message arguments="<%= String.valueOf(myContactsCount) %>" key="view-my-x-contacts" /></a>
										</aui:layout>
									</c:if>

									<aui:layout cssClass="contacts-count all">
										<a href="javascript:;"><liferay-ui:message arguments="<%= String.valueOf(allUsersCount) %>" key="view-all-x-users" /></a>
									</aui:layout>

									<c:if test="<%= !showOnlySiteMembers && (connectionUsersCount <= 0) && (followingUsersCount <= 0) %>">
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

		<aui:script use="aui-dialog,aui-io,aui-io-plugin,datatype-number,liferay-contacts-center,liferay-form">
			var searchInput = A.one('.contacts-portlet #<portlet:namespace />name');

			var contactsCenter = new Liferay.ContactsCenter(
				{
					contactsResult: '.contacts-portlet .contacts-result-content',
					contactsResultContainer: '.contacts-portlet .contacts-result',
					contactsResultURL: '<portlet:resourceURL id="getContacts"><portlet:param name="portletResource" value="<%= portletResource %>" /><portlet:param name="redirect" value="<%= currentURL %>" /></portlet:resourceURL>',
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
						'<portlet:resourceURL id="getContacts"><portlet:param name="portletResource" value="<%= portletResource %>" /><portlet:param name="redirect" value="<%= currentURL %>" /></portlet:resourceURL>',
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
				<c:if test="<%= !showOnlySiteMembers %>">
					A.one('.contacts-portlet .add-contact').on(
						'click',
						function(event) {
							var dialog = new A.Dialog(
							{
								centered: true,
								constrain2view: true,
								cssClass: 'contact-dialog',
								destroyOnClose: true,
								modal: true,
								resizable: false,
								title: '<%= LanguageUtil.get(pageContext, "add-contact") %>',
								width: 500
							}
							).plug(
								A.Plugin.IO,
								{
									uri: '<portlet:renderURL windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>"><portlet:param name="mvcPath" value="/contacts_center/edit_entry.jsp" /><portlet:param name="redirect" value="<%= currentURL %>" /></portlet:renderURL>'
								}
							).render();

							A.one('#<portlet:namespace />fm').setData('dialogInstance', dialog);
						}
					);
				</c:if>

				var contactsCenterHome = A.one('.contacts-portlet .contacts-center-home');

				contactsCenterHome.one('.contacts').on(
					'click',
					function(event) {
						contactFilterSelect.set('value', '<%= SocialRelationConstants.TYPE_MY_CONTACTS %>');

						contactsCenter.updateContacts(searchInput.get('value'), contactFilterSelect.get('value'));
					},
					'a'
				);

				contactsCenterHome.one('.connections').on(
					'click',
					function(event) {
						contactFilterSelect.set('value', '<%= SocialRelationConstants.TYPE_BI_CONNECTION %>');

						contactsCenter.updateContacts(searchInput.get('value'), contactFilterSelect.get('value'));
					},
					'a'
				);

				contactsCenterHome.one('.followings').on(
					'click',
					function(event) {
						contactFilterSelect.set('value', '<%= SocialRelationConstants.TYPE_UNI_FOLLOWER %>');

						contactsCenter.updateContacts(searchInput.get('value'), contactFilterSelect.get('value'));
					},
					'a'
				);

				contactsCenterHome.one('.all').on(
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