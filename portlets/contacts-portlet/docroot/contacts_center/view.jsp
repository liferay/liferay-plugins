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
String filterBy = ParamUtil.getString(request, "filterBy", ContactsConstants.FILTER_BY_DEFAULT);

String name = ParamUtil.getString(request, "name");

boolean userPublicPage = false;

Group group = themeDisplay.getScopeGroup();

if (group.isUser() && layout.isPublicLayout()) {
	userPublicPage = true;
}

LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();

params.put("inherit", Boolean.TRUE);

if (userPublicPage) {
	params.put("socialRelation", new Long[] {group.getClassPK()});
}
else if (filterBy.startsWith(ContactsConstants.FILTER_BY_TYPE)) {
	params.put("socialRelationType", new Long[] {themeDisplay.getUserId(), ContactsUtil.getSocialRelationType(filterBy)});
}

if (showOnlySiteMembers) {
	params.put("usersGroups", Long.valueOf(group.getGroupId()));
}
else if (filterBy.startsWith(ContactsConstants.FILTER_BY_GROUP)) {
	params.put("usersGroups", ContactsUtil.getGroupId(filterBy));
}

List<BaseModel<?>> contacts = null;
int contactsCount = 0;

if (userPublicPage) {
	List<User> users = UserLocalServiceUtil.getSocialUsers(group.getClassPK(), SocialRelationConstants.TYPE_BI_CONNECTION, StringPool.EQUAL, 0, ContactsConstants.MAX_RESULT_COUNT, new UserLastNameComparator(true));

	contacts = new ArrayList<BaseModel<?>>(users);
	contactsCount = UserLocalServiceUtil.getSocialUsersCount(group.getClassPK(), SocialRelationConstants.TYPE_BI_CONNECTION, StringPool.EQUAL);
}
else if (showOnlySiteMembers || !filterBy.equals(ContactsConstants.FILTER_BY_DEFAULT)) {
	List<User> users = UserLocalServiceUtil.search(company.getCompanyId(), name, WorkflowConstants.STATUS_APPROVED, params, 0, ContactsConstants.MAX_RESULT_COUNT, new UserLastNameComparator(true));

	contacts = new ArrayList<BaseModel<?>>(users);
	contactsCount = UserLocalServiceUtil.searchCount(themeDisplay.getCompanyId(), name, WorkflowConstants.STATUS_APPROVED, params);
}
else {
	contacts = EntryLocalServiceUtil.searchUsersAndContacts(themeDisplay.getCompanyId(), user.getUserId(), name, 0, ContactsConstants.MAX_RESULT_COUNT);
	contactsCount = EntryLocalServiceUtil.searchUsersAndContactsCount(themeDisplay.getCompanyId(), user.getUserId(), name);
}

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setWindowState(WindowState.NORMAL);
%>

<c:choose>
	<c:when test="<%= userPublicPage && (contactsCount <= 0) %>">
		<aui:layout cssClass="contacts-center-home">
			<h3 class="header-title">
				<liferay-ui:message arguments="<%= new Object[] {HtmlUtil.escape(group.getDescriptiveName(locale)), String.valueOf(contactsCount)} %>" key='<%= userPublicPage ? "x-has-no-connections" : "x-has-no-contacts" %>' translateArguments="<%= false %>" />
			</h3>
		</aui:layout>
	</c:when>
	<c:otherwise>
		<aui:form action="<%= portletURL.toString() %>" method="post" name="fm">
			<aui:input name="<%= Constants.CMD %>" type="hidden" value="" />
			<aui:input name="redirect" type="hidden" value="" />
			<aui:input name="userIds" type="hidden" value="" />
			<aui:input name="type" type="hidden" value="" />

			<aui:layout cssClass="toolbar">
				<div class="filter-container">
					<aui:layout cssClass="contact-group-filter">
						<aui:input label="" name="checkAll" type="checkbox" />

						<c:if test="<%= !userPublicPage %>">
							<aui:select cssClass="contact-group-filter-select" inlineField="true" label="" name="filterBy" value="<%= filterBy %>">
								<aui:option label="all" value="<%= ContactsConstants.FILTER_BY_DEFAULT %>" />

								<c:if test="<%= showOnlySiteMembers %>">
									<aui:option label="administrators" value="<%= ContactsConstants.FILTER_BY_ADMINS %>" />
								</c:if>

								<aui:option label="connections" value="<%= ContactsConstants.FILTER_BY_TYPE_BI_CONNECTION %>" />
								<aui:option label="following" value="<%= ContactsConstants.FILTER_BY_TYPE_UNI_FOLLOWER %>" />

								<c:if test="<%= !showOnlySiteMembers %>">
									<aui:option label="followers" value="<%= ContactsConstants.FILTER_BY_FOLLOWERS %>" />
									<aui:option label="my-contacts" value="<%= ContactsConstants.FILTER_BY_TYPE_MY_CONTACTS %>" />

									<%
									List<Group> groups = user.getGroups();
									%>

									<optgroup label="<liferay-ui:message key="members-of" />">

										<%
										for (Group curGroup : groups) {

											String filterByGroupId = ContactsConstants.FILTER_BY_GROUP + curGroup.getGroupId();
										%>

											<aui:option label="<%= HtmlUtil.escape(curGroup.getDescriptiveName(locale)) %>" value="<%= filterByGroupId %>" />

										<%
										}
										%>

									</optgroup>
								</c:if>
							</aui:select>
						</c:if>
					</aui:layout>
				</div>

				<c:if test="<%= !showOnlySiteMembers && !userPublicPage %>">
					<aui:button cssClass="add-contact" icon="icon-plus-sign" id='<%= renderResponse.getNamespace() + "addContact" %>' value="add-contact" />
				</c:if>
			</aui:layout>
		</aui:form>

		<aui:layout cssClass="contacts-result-container lfr-app-column-view">
			<aui:column columnWidth="30" cssClass="contacts-list" first="<%= true %>">
				<div class="toggle-user">
					<i class="icon-chevron-left"></i>
				</div>

				<div class="contacts-search lfr-search-column search-bar">
					<aui:input cssClass="search-input" id="name" label="" name="name" size="30" type="text" value="<%= HtmlUtil.escape(name) %>" />

					<i class="icon-search"></i>
				</div>

				<aui:layout cssClass='<%= userPublicPage ? "contacts-result personal-contact-list" : "contacts-result" %>'>

					<%
					String lastNameAnchor = StringPool.SPACE;

					for (BaseModel<?> curContact : contacts) {
					%>

						<c:choose>
							<c:when test="<%= curContact instanceof User %>">

								<%
								User user2 = (User)curContact;

								String lastName = user2.getLastName();

								String curLastNameAnchor = LanguageUtil.get(request, "no-last-name");

								if (Validator.isNotNull(lastName)) {
									curLastNameAnchor = StringUtil.upperCase(lastName.substring(0, 1));
								}
								%>

								<c:if test="<%= !curLastNameAnchor.equals(lastNameAnchor) %>">

									<%
									lastNameAnchor = curLastNameAnchor;
									%>

									<div class="last-name-anchor">
										<a><liferay-ui:message key="<%= lastNameAnchor %>" /></a>
									</div>
								</c:if>

								<div class="lfr-contact">
									<div class="lfr-contact-checkbox">
										<input class="contact-ids" <%= themeDisplay.getUserId() == user2.getUserId() ? "disabled=\"true\"" : StringPool.BLANK %> name="contact-ids-<%= user2.getUserId() %>" type="checkbox" value="<%= user2.getUserId() %>" />
									</div>

									<liferay-portlet:renderURL var="viewUserSummaryURL" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
										<portlet:param name="mvcPath" value="/contacts_center/view_resources.jsp" />
										<portlet:param name="userId" value="<%= String.valueOf(user2.getUserId()) %>" />
										<portlet:param name="portalUser" value="<%= Boolean.TRUE.toString() %>" />
									</liferay-portlet:renderURL>

									<div class="lfr-contact-grid-item" data-userId="<%= user2.getUserId() %>" data-viewSummaryURL="<%= viewUserSummaryURL %>">
										<div class="lfr-contact-thumb">
											<img alt="<%= HtmlUtil.escapeAttribute(user2.getFullName()) %>" src="<%= user2.getPortraitURL(themeDisplay) %>" />
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
								Entry entry = (Entry)curContact;

								String fullName = entry.getFullName();

								String curLastNameAnchor = LanguageUtil.get(request, "no-last-name");

								if (Validator.isNotNull(fullName)) {
									curLastNameAnchor = StringUtil.upperCase(fullName.substring(0, 1));
								}
								%>

								<c:if test="<%= !curLastNameAnchor.equals(lastNameAnchor) %>">

									<%
									lastNameAnchor = curLastNameAnchor;
									%>

									<div class="last-name-anchor">
										<a><liferay-ui:message key="<%= lastNameAnchor %>" /></a>
									</div>
								</c:if>

								<div class="lfr-contact">
									<div class="lfr-contact-checkbox">
										<input class="contact-ids" disabled="true" label="" name="contact-ids-<%= entry.getEntryId() %>" type="checkbox" value="<%= entry.getEntryId() %>" />
									</div>

									<liferay-portlet:renderURL var="viewContactSummaryURL" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
										<portlet:param name="mvcPath" value="/contacts_center/view_resources.jsp" />
										<portlet:param name="redirect" value="<%= currentURL %>" />
										<portlet:param name="entryId" value="<%= String.valueOf(entry.getEntryId()) %>" />
										<portlet:param name="portalUser" value="<%= Boolean.FALSE.toString() %>" />
									</liferay-portlet:renderURL>

									<div class="lfr-contact-grid-item" data-userId="" data-viewSummaryURL="<%= viewContactSummaryURL %>">
										<div class="lfr-contact-thumb">
											<img alt="<%= HtmlUtil.escapeAttribute(fullName) %>" src='<%= themeDisplay.getPathImage() + "/user_male_portrait?img_id=0&t=" %>' />
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

					<c:if test="<%= contactsCount > ContactsConstants.MAX_RESULT_COUNT %>">
						<div class="more-results">
							<a data-end="<%= ContactsConstants.MAX_RESULT_COUNT %>" data-lastNameAnchor="<%= lastNameAnchor %>" href="javascript:;"><liferay-ui:message key="view-more" /> (<%= contactsCount - ContactsConstants.MAX_RESULT_COUNT %>)</a>
						</div>
					</c:if>
				</aui:layout>
			</aui:column>

			<aui:column columnWidth="70" cssClass="contacts-container">
				<div id="<portlet:namespace />userToolbarButtons"><!-- --></div>

				<div class="hide" id="<portlet:namespace />contactCenterToolbarButtons">
					<liferay-util:include page="/contacts_center/contacts_center_toolbar.jsp" servletContext="<%= application %>" />
				</div>

				<div id="<portlet:namespace />messageContainer"></div>

				<div id="<portlet:namespace />detailUserView">
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

								if (userPublicPage || showOnlySiteMembers || !filterBy.equals(ContactsConstants.FILTER_BY_DEFAULT)) {
									allUsersCount = UserLocalServiceUtil.searchCount(themeDisplay.getCompanyId(), StringPool.BLANK, WorkflowConstants.STATUS_APPROVED, params);
								}
								else {
									allUsersCount = EntryLocalServiceUtil.searchUsersAndContactsCount(themeDisplay.getCompanyId(), themeDisplay.getUserId(), StringPool.BLANK);
								}

								params.put("socialRelationType", new Long[] {themeDisplay.getUserId(), Long.valueOf(SocialRelationConstants.TYPE_BI_CONNECTION)});

								int connectionUsersCount = UserLocalServiceUtil.searchCount(themeDisplay.getCompanyId(), null, WorkflowConstants.STATUS_APPROVED, params);

								params.put("socialRelationType", new Long[] {themeDisplay.getUserId(), Long.valueOf(SocialRelationConstants.TYPE_UNI_FOLLOWER)});

								int followingUsersCount = UserLocalServiceUtil.searchCount(themeDisplay.getCompanyId(), null, WorkflowConstants.STATUS_APPROVED, params);

								int followerUsersCount = SocialRelationLocalServiceUtil.getInverseRelationsCount(themeDisplay.getUserId(), SocialRelationConstants.TYPE_UNI_FOLLOWER);
								%>

								<aui:layout cssClass="connections contacts-count">
									<a href="javascript:;"><liferay-ui:message arguments="<%= String.valueOf(connectionUsersCount) %>" key='<%= showOnlySiteMembers ? "you-have-x-connections-in-this-site" : "you-have-x-connections" %>' translateArguments="<%= false %>" /></a>
								</aui:layout>

								<aui:layout cssClass="contacts-count followings">
									<a href="javascript:;"><liferay-ui:message arguments="<%= String.valueOf(followingUsersCount) %>" key='<%= showOnlySiteMembers ? "you-are-following-x-people-in-this-site" : "you-are-following-x-people" %>' translateArguments="<%= false %>" /></a>
								</aui:layout>

								<c:if test="<%= !showOnlySiteMembers %>">
									<aui:layout cssClass="contacts-count followers">
										<a href="javascript:;"><liferay-ui:message arguments="<%= String.valueOf(followerUsersCount) %>" key="you-have-x-followers" translateArguments="<%= false %>" /></a>
									</aui:layout>

									<%
									int myContactsCount = EntryLocalServiceUtil.getEntriesCount(user.getUserId());
									%>

									<aui:layout cssClass="contacts contacts-count">
										<a href="javascript:;"><liferay-ui:message arguments="<%= String.valueOf(myContactsCount) %>" key="view-my-x-contacts" translateArguments="<%= false %>" /></a>
									</aui:layout>
								</c:if>

								<aui:layout cssClass="all contacts-count">
									<a href="javascript:;"><liferay-ui:message arguments="<%= String.valueOf(allUsersCount) %>" key="view-all-x-users" translateArguments="<%= false %>" /></a>
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

				<div id="<portlet:namespace />selectedUsersView"><!-- --></div>
			</aui:column>
		</aui:layout>

		<aui:script use="aui-io-deprecated,aui-loading-mask-deprecated,datatype-number,liferay-contacts-center">
			var searchInput = A.one('.contacts-portlet #<portlet:namespace />name');

			Liferay.component(
				'contactsCenter',
				function() {
					return new Liferay.ContactsCenter(
						{
							baseActionURL: '<%= PortletURLFactoryUtil.create(request, portletDisplay.getId(), themeDisplay.getPlid(), PortletRequest.ACTION_PHASE) %>',
							baseRenderURL: '<%= PortletURLFactoryUtil.create(request, portletDisplay.getId(), themeDisplay.getPlid(), PortletRequest.RENDER_PHASE) %>',
							contactsResult: '.contacts-portlet .contacts-result-content',
							contactsResultContainer: '.contacts-portlet .contacts-result',
							contactsResultURL: '<portlet:resourceURL id="getContacts"><portlet:param name="portletResource" value="<%= portletResource %>" /><portlet:param name="redirect" value="<%= currentURL %>" /></portlet:resourceURL>',
							contactsSearchInput: '#<portlet:namespace />name',
							defaultMessageError: '<liferay-ui:message key="an-error-occurred-while-retrieving-the-users-information" unicode="<%= true %>" />',
							defaultMessageSuccess: '<liferay-ui:message key="your-request-completed-successfully" unicode="<%= true %>" />',
							getSelectedContactsURL: '<portlet:resourceURL id="getSelectedContacts" />',
							maxResultCount: <%= ContactsConstants.MAX_RESULT_COUNT %>,
							namespace: '<portlet:namespace />',
							showIcon: '<%= showIcon %>'
						}
					);
				}
			);

			var contactsCenter = Liferay.component('contactsCenter');

			<c:if test="<%= !userPublicPage %>">
				var contactFilterSelect = A.one('#<portlet:namespace />filterBy');

				contactFilterSelect.on(
					'change',
					function(event) {
						searchInput.set('value', '');

						contactsCenter.updateContacts(searchInput.get('value'), contactFilterSelect.get('value'));
					}
				);
			</c:if>

			var contactsCenterNode = A.one('#p_p_id<portlet:namespace />');

			var toggleUser = A.one('.contacts-portlet .toggle-user');

			if (toggleUser) {
				toggleUser.on(
				'click',
					function(event) {
						contactsCenterNode.toggleClass('show-user', false);
					}
				);
			}

			var contactsResult = A.one('.contacts-portlet .contacts-result');

			contactsResult.delegate(
				'click',
				function(event) {
					contactsCenterNode.toggleClass('show-user', true);

					var contactsContainer = A.one('.contacts-portlet .contacts-container');

					contactsContainer.plug(A.LoadingMask);

					contactsContainer.loadingmask.show();

					var node = event.currentTarget;

					A.io.request(
						node.getAttribute('data-viewSummaryURL'),
						{
							after: {
								failure: function(event, id, obj) {
									contactsContainer.loadingmask.hide();

									contactsCenter.showMessage(false);
								},
								success: function(event, id, obj) {
									contactsCenter.renderContent(this.get('responseData'), true);

									window.scrollTo(0,0);

									contactsContainer.loadingmask.hide();
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
					var end = start + <%= ContactsConstants.MAX_RESULT_COUNT %>;

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
								<portlet:namespace />end: end,
								<portlet:namespace />filterBy: contactFilterSelect.get('value') || '<%= ContactsConstants.FILTER_BY_DEFAULT %>',
								<portlet:namespace />keywords: searchInput.get('value'),
								<portlet:namespace />start: start
							},
							dataType: 'JSON'
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
										contactsCenter.showMessage(false, responseData.message);
									},
									success: function(event, id, obj) {
										var responseData = this.get('responseData');

										if (responseData.success) {
											contactsCenter.addContactResult(responseData);
										}
									}
								},
								data: {
									<portlet:namespace />userId: userId
								},
								dataType: 'JSON'
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
									contactsCenter.showMessage(false);
								},
								success: function(event, id, obj) {
									contactsCenter.renderContent(this.get('responseData'));
								}
							},
							data: {
								<portlet:namespace />showDetailView: true,
								<portlet:namespace />userId: userId
							}
						}
					);
				},
				'.lfr-contact-grid-item'
			);

			<c:if test="<%= !userPublicPage %>">
				var contactsCenterHome = A.one('.contacts-portlet .contacts-center-home');

				<c:if test="<%= !showOnlySiteMembers %>">
					var addContact = A.one('#<portlet:namespace />addContact');

					if (addContact) {
						addContact.on(
							'click',
							function(event) {
								contactsCenter.showPopup('<%= LanguageUtil.get(request, "add-contact") %>', '<portlet:renderURL windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>"><portlet:param name="mvcPath" value="/contacts_center/edit_entry.jsp" /><portlet:param name="redirect" value="<%= currentURL %>" /></portlet:renderURL>');
							}
						);
					}

					var contacts = contactsCenterHome.one('.contacts');

					if (contacts) {
						contacts.on(
							'click',
							function(event) {
								contactFilterSelect.set('value', '<%= ContactsConstants.FILTER_BY_TYPE_MY_CONTACTS %>');

								contactsCenter.updateContacts(searchInput.get('value'), contactFilterSelect.get('value'));
							},
							'a'
						);
					}
				</c:if>

				var connections = contactsCenterHome.one('.connections');

				if (connections) {
					connections.on(
						'click',
						function(event) {
							contactFilterSelect.set('value', '<%= ContactsConstants.FILTER_BY_TYPE_BI_CONNECTION %>');

							contactsCenter.updateContacts(searchInput.get('value'), contactFilterSelect.get('value'));
						},
						'a'
					);
				}

				var following = contactsCenterHome.one('.followings');

				if (following) {
					following.on(
						'click',
						function(event) {
							contactFilterSelect.set('value', '<%= ContactsConstants.FILTER_BY_TYPE_UNI_FOLLOWER %>');

							contactsCenter.updateContacts(searchInput.get('value'), contactFilterSelect.get('value'));
						},
						'a'
					);
				}

				var followers = contactsCenterHome.one('.followers');

				if (followers) {
					followers.on(
						'click',
						function(event) {
							contactFilterSelect.set('value', '<%= ContactsConstants.FILTER_BY_FOLLOWERS %>');

							contactsCenter.updateContacts(searchInput.get('value'), contactFilterSelect.get('value'));
						},
						'a'
					);
				}

				var all = contactsCenterHome.one('.all');

				if (all) {
					all.on(
						'click',
						function(event) {
							contactFilterSelect.set('value', '<%= ContactsConstants.FILTER_BY_DEFAULT %>');

							searchInput.set('value', '');

							contactsCenter.updateContacts(searchInput.get('value'), contactFilterSelect.get('value'));
						},
						'a'
					);
				}
			</c:if>
		</aui:script>
	</c:otherwise>
</c:choose>