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
		<aui:form action="<%= portletURL.toString() %>" method="post" name="fm">
			<aui:layout cssClass="contacts-search aui-search-bar">
				<aui:input cssClass="search-input" id="name" name="name" size="30" type="text" value="<%= HtmlUtil.escape(name) %>" />
			</aui:layout>

			<c:if test="<%= !userPublicPage %>">
				<aui:layout cssClass="contact-group-filter">
					<aui:select inlineField="true" label="" name="socialRelationType">
						<aui:option label="all" selected='<%= socialRelationType == 0 %>' value="all" />
						<aui:option label="connections" selected='<%= socialRelationType == SocialRelationConstants.TYPE_BI_CONNECTION %>' value="<%= SocialRelationConstants.TYPE_BI_CONNECTION %>" />
						<aui:option label="following" selected='<%= socialRelationType == SocialRelationConstants.TYPE_UNI_FOLLOWER %>' value="<%= SocialRelationConstants.TYPE_UNI_FOLLOWER %>" />
					</aui:select>
				</aui:layout>
			</c:if>

			<div class="clear"><!-- --></div>

			<aui:layout cssClass="contacts-result-container lfr-app-column-view">
				<aui:column columnWidth="30" first="<%= true %>">
					<aui:layout cssClass="contacts-result">

						<%
						String lastNameAnchor = StringPool.SPACE;

						for (User user2 : users) {
						%>

							<c:if test="<%= !StringUtil.startsWith(user2.getLastName(), lastNameAnchor) %>">

								<%
								lastNameAnchor = user2.getLastName().substring(0, 1);
								%>

								<div class="lastNameAnchor">
									<a><%= StringUtil.upperCase(lastNameAnchor) %></a>
								</div>
							</c:if>

							<liferay-portlet:renderURL windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>" var="viewSummaryURL">
								<portlet:param name="jspPage" value="/contacts_center/view_user.jsp" />
								<portlet:param name="userId" value="<%= String.valueOf(user2.getUserId()) %>" />
							</liferay-portlet:renderURL>

							<div class="lfr-contact-grid-item" data-viewSummaryURL="<%= viewSummaryURL %>">
								<div class="lfr-contact-thumb">
									<img alt="<%= HtmlUtil.escape(user2.getFullName()) %>" src="<%= user2.getPortraitURL(themeDisplay) %>" />
								</div>

								<div class="lfr-contact-info">
									<div class="lfr-contact-name">
										<a><%= HtmlUtil.escape(user2.getLastName()) %>, <%= HtmlUtil.escape(user2.getFirstName()) %></a>
									</div>

									<div class="lfr-contact-extra">
										<%= HtmlUtil.escape(user2.getEmailAddress()) %>
									</div>
								</div>

								<div class="clear"><!-- --></div>
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
								int allUsersCount = UserLocalServiceUtil.getUsersCount();
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
				</aui:column>
			</aui:layout>
		</aui:form>

		<aui:script use="aui-base,aui-io,datatype-number">
			Liferay.Contacts.init(
				{
					contactsResult: '.contacts-portlet .contacts-result-content',
					contactsResultContainer: '.contacts-portlet .contacts-result',
					contactsResultURL: '<portlet:resourceURL id="getContacts"><portlet:param name="portletResource" value="<%= portletResource %>" /></portlet:resourceURL>',
					contactsSearchInput: '#<portlet:namespace />name'
				}
			);

			var searchInput = A.one('.contacts-portlet #<portlet:namespace />name');

			<c:if test="<%= !userPublicPage %>">
				var contactFilterContainer = A.one('.contacts-portlet .contact-group-filter');
				var contactFilterSelect = contactFilterContainer.one('select[name=<portlet:namespace />socialRelationType]');

				contactFilterSelect.on(
					'change',
					function(event) {
						searchInput.set('value', '');

						Liferay.Contacts.updateContacts(searchInput.get('value'), contactFilterSelect.get('value'));
					}
				);
			</c:if>

			var contactsResult = A.one('.contacts-portlet .contacts-result');

			contactsResult.delegate(
				'click',
				function(event) {
					var uri = event.currentTarget.getAttribute('data-viewSummaryURL');
					var contactSummary = A.one('.contacts-portlet .contacts-result-container .contacts-container-content');

					if (!contactSummary.io) {
						contactSummary.plug(
							A.Plugin.IO,
							{
								autoLoad: false
							}
						);
					}

					contactSummary.io.set('uri', uri);

					contactSummary.io.start();
				},
				'.lfr-contact-grid-item'
			);

			contactsResult.delegate(
				'click',
				function(event) {
					var start = A.DataType.Number.parse(event.currentTarget.getAttribute('data-end'));
					var end = start + <%= maxResultCount %>;

					var lastNameAnchor = event.currentTarget.getAttribute('data-lastNameAnchor');

					A.io.request(
						'<portlet:resourceURL id="getContacts"><portlet:param name="portletResource" value="<%= portletResource %>" /></portlet:resourceURL>',
						{
							after: {
								success: function(event, id, obj) {
									Liferay.Contacts.showMoreResult(this.get('responseData'), lastNameAnchor);
								}
							},
							data: {
								end: end,
								keywords: searchInput.get('value'),
								socialRelationType: contactFilterSelect.get('value') || 'all',
								start: start
							}
						}
					);
				},
				'.more-results a'
			);

			<c:if test="<%= !userPublicPage %>">
				var viewConnections = A.one('.contacts-portlet .contacts-center-home .connections');

				viewConnections.on(
					'click',
					function(event) {
						contactFilterSelect.set('value', '<%= SocialRelationConstants.TYPE_BI_CONNECTION %>');

						Liferay.Contacts.updateContacts(searchInput.get('value'), contactFilterSelect.get('value'));
					},
					'a'
				);

				var viewFollowings = A.one('.contacts-portlet .contacts-center-home .followings');

				viewFollowings.on(
					'click',
					function(event) {
						contactFilterSelect.set('value', '<%= SocialRelationConstants.TYPE_UNI_FOLLOWER %>');

						Liferay.Contacts.updateContacts(searchInput.get('value'), contactFilterSelect.get('value'));
					},
					'a'
				);

				var viewAll = A.one('.contacts-portlet .contacts-center-home .all');

				viewAll.on(
					'click',
					function(event) {
						contactFilterSelect.set('value', 'all');

						Liferay.Contacts.updateContacts(searchInput.get('value'), contactFilterSelect.get('value'));
					},
					'a'
				);
			</c:if>
		</aui:script>
	</c:otherwise>
</c:choose>