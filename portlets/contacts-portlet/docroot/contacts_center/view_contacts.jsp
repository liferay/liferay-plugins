<%--
/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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
String topLink = ParamUtil.getString(request, "topLink", "contacts-home");

int socialRelationType = ParamUtil.getInteger(request, "socialRelationType");

String socialRelationTypeDescriptiveKey = StringPool.BLANK;

if (socialRelationType == SocialRelationConstants.TYPE_BI_COWORKER) {
	socialRelationTypeDescriptiveKey = "coworkers";
}
else if (socialRelationType == SocialRelationConstants.TYPE_BI_FRIEND) {
	socialRelationTypeDescriptiveKey = "friends";
}
else if (socialRelationType == SocialRelationConstants.TYPE_UNI_FOLLOWER) {
	socialRelationTypeDescriptiveKey = "following";
}

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("jspPage", "/contacts_center/view.jsp");
portletURL.setParameter("topLink", topLink);
portletURL.setParameter("socialRelationType", String.valueOf(socialRelationType));
%>

<c:if test="<%= socialRelationType > 0 %>">
	<portlet:renderURL var="backURL">
		<portlet:param name="jspPage" value="/contacts_center/view.jsp" />
		<portlet:param name="topLink" value="contacts-home" />
	</portlet:renderURL>

	<liferay-ui:header
		backURL="<%= backURL.toString() %>"
		title="<%= socialRelationTypeDescriptiveKey %>"
	/>
</c:if>

<aui:layout>
	<aui:column columnWidth="<%= 75 %>" cssClass="lfr-asset-column lfr-asset-column-details" first="<%= true %>">
		<c:choose>
			<c:when test="<%= socialRelationType > 0 %>">
				<liferay-portlet:renderURL varImpl="searchURL">
					<portlet:param name="jspPage" value="/contacts_center/view.jsp" />
					<portlet:param name="topLink" value="<%= topLink %>" />
					<portlet:param name="socialRelationType" value="<%= String.valueOf(socialRelationType) %>" />
				</liferay-portlet:renderURL>

				<aui:form action="<%= searchURL %>" method="get" name="fm">
					<liferay-portlet:renderURLParams varImpl="searchURL" />
					<aui:input name="topLink" type="hidden" value="<%= topLink %>" />
					<aui:input name="socialRelationType" type="hidden" value="<%= String.valueOf(socialRelationType) %>" />

					<liferay-ui:search-container
						emptyResultsMessage="no-users-were-found"
						iteratorURL="<%= portletURL %>"
					>

						<%
						String keywords = ParamUtil.getString(request, "keywords");
						%>

						<div>
							<aui:input inlineField="<%= true %>" id="keywords" label="" name="keywords" size="30" title="search-users" type="text" />

							<aui:button type="submit" value="search" />
						</div>

						<%
						LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();

						params.put("socialRelationType", new Long[] {themeDisplay.getUserId(), new Long(socialRelationType)});
						%>

						<liferay-ui:search-container-results
							results="<%= UserLocalServiceUtil.search(company.getCompanyId(), keywords, WorkflowConstants.STATUS_APPROVED, params, searchContainer.getStart(), searchContainer.getEnd(), new UserLastNameComparator(true)) %>"
							total="<%= UserLocalServiceUtil.searchCount(company.getCompanyId(), keywords, WorkflowConstants.STATUS_APPROVED, params) %>"
						/>

						<liferay-ui:search-container-row
							className="com.liferay.portal.model.User"
							escapedModel="<%= true %>"
							keyProperty="userId"
							modelVar="user2"
						>
							<liferay-portlet:renderURL varImpl="rowURL">
								<portlet:param name="jspPage" value="/contacts_center/view_user.jsp" />
								<portlet:param name="backURL" value="<%= currentURL %>" />
								<portlet:param name="userId" value="<%= String.valueOf(user2.getUserId()) %>" />
							</liferay-portlet:renderURL>

							<%@ include file="/contacts_center/user_columns.jspf" %>
						</liferay-ui:search-container-row>

						<liferay-ui:search-iterator />
					</liferay-ui:search-container>
				</aui:form>

				<div class="export-group">
					<portlet:resourceURL id="exportVCards" var="exportURL">
						<portlet:param name="userId" value="<%= String.valueOf(user.getUserId()) %>" />
						<portlet:param name="socialRelationType" value="<%= String.valueOf(socialRelationType) %>" />
					</portlet:resourceURL>

					<%
					String taglibExportMessage = LanguageUtil.format(pageContext, "export-my-as-vcards", socialRelationTypeDescriptiveKey, true);
					%>

					<liferay-ui:icon
						image="export"
						label="<%= true %>"
						message="<%= taglibExportMessage %>"
						url="<%= exportURL %>"
					/>
				</div>
			</c:when>
			<c:otherwise>
				<liferay-ui:panel-container extended="<%= false %>" id="contactsCenterContactsPanelContainer" persistState="<%= true %>">

					<%
					int requestCount = SocialRequestLocalServiceUtil.getReceiverUserRequestsCount(themeDisplay.getUserId(), SocialRequestConstants.STATUS_PENDING);
					%>

					<c:if test="<%= requestCount > 0 %>">
						<div class="lfr-asset-metadata">
							<div class="lfr-asset-icon lfr-asset-requests last">
								<portlet:renderURL var="requestURL">
									<portlet:param name="jspPage" value="/contacts_center/view.jsp" />
									<portlet:param name="topLink" value="requests" />
								</portlet:renderURL>

								<a href="<%= requestURL %>"><liferay-ui:message arguments="<%= String.valueOf(requestCount) %>" key='<%= requestCount > 1 ? "you-have-x-pending-requests" : "you-have-a-pending-request" %>' /></a>
							</div>
						</div>
					</c:if>

					<%
					PortletURL viewUserURL = renderResponse.createRenderURL();

					viewUserURL.setParameter("jspPage", "/contacts_center/view_user.jsp");
					viewUserURL.setParameter("backURL", currentURL);

					request.setAttribute(WebKeys.CONTACTS_URL, viewUserURL);

					int friendUsersCount = UserLocalServiceUtil.getSocialUsersCount(themeDisplay.getUserId(), SocialRelationConstants.TYPE_BI_FRIEND);
					%>

					<c:if test="<%= friendUsersCount > 0 %>">
						<liferay-ui:panel collapsible="<%= true %>" extended="<%= true %>" id="contactsCenterFriendsPanel" persistState="<%= true %>" title="friends">

							<%
							List<User> friendUsers = UserLocalServiceUtil.getSocialUsers(themeDisplay.getUserId(), SocialRelationConstants.TYPE_BI_FRIEND, 0, usersPerSection, new UserLoginDateComparator());

							request.setAttribute(WebKeys.CONTACTS_USERS, friendUsers);
							%>

							<liferay-util:include page="/contacts_center/view_users.jsp" portletId="<%= portletDisplay.getId() %>" />

							<portlet:renderURL var="viewURL">
								<portlet:param name="jspPage" value="/contacts_center/view.jsp" />
								<portlet:param name="topLink" value="contacts-home" />
								<portlet:param name="socialRelationType" value="<%= String.valueOf(SocialRelationConstants.TYPE_BI_FRIEND) %>" />
							</portlet:renderURL>

							<a href="<%= viewURL %>"><liferay-ui:message arguments="<%= String.valueOf(friendUsersCount) %>" key="manage-x-friends" /></a>
						</liferay-ui:panel>
					</c:if>

					<%
					int coworkerUsersCount = UserLocalServiceUtil.getSocialUsersCount(themeDisplay.getUserId(), SocialRelationConstants.TYPE_BI_COWORKER);
					%>

					<c:if test="<%= coworkerUsersCount > 0 %>">
						<liferay-ui:panel collapsible="<%= true %>" extended="<%= true %>" id="contactsCenterCoworkersPanel" persistState="<%= true %>" title="coworkers">

							<%
							List<User> coworkerUsers = UserLocalServiceUtil.getSocialUsers(themeDisplay.getUserId(), SocialRelationConstants.TYPE_BI_COWORKER, 0, usersPerSection, new UserLoginDateComparator());

							request.setAttribute(WebKeys.CONTACTS_USERS, coworkerUsers);
							%>

							<liferay-util:include page="/contacts_center/view_users.jsp" portletId="<%= portletDisplay.getId() %>" />

							<portlet:renderURL var="viewURL">
								<portlet:param name="jspPage" value="/contacts_center/view.jsp" />
								<portlet:param name="topLink" value="contacts-home" />
								<portlet:param name="socialRelationType" value="<%= String.valueOf(SocialRelationConstants.TYPE_BI_COWORKER) %>" />
							</portlet:renderURL>

							<a href="<%= viewURL %>"><liferay-ui:message arguments="<%= String.valueOf(coworkerUsersCount) %>" key="manage-x-coworkers" /></a>
						</liferay-ui:panel>
					</c:if>

					<%
					int followingUsersCount = UserLocalServiceUtil.getSocialUsersCount(themeDisplay.getUserId(), SocialRelationConstants.TYPE_UNI_FOLLOWER);
					%>

					<c:if test="<%= followingUsersCount > 0 %>">
						<liferay-ui:panel collapsible="<%= true %>" extended="<%= true %>"  id="contactsCenterContactsFollowingPanel" persistState="<%= true %>" title="following">

							<%
							List<User> followingUsers = UserLocalServiceUtil.getSocialUsers(themeDisplay.getUserId(), SocialRelationConstants.TYPE_UNI_FOLLOWER, 0, usersPerSection, new UserLoginDateComparator());

							request.setAttribute(WebKeys.CONTACTS_USERS, followingUsers);
							%>

							<liferay-util:include page="/contacts_center/view_users.jsp" portletId="<%= portletDisplay.getId() %>" />

							<portlet:renderURL var="viewURL">
								<portlet:param name="jspPage" value="/contacts_center/view.jsp" />
								<portlet:param name="topLink" value="contacts-home" />
								<portlet:param name="socialRelationType" value="<%= String.valueOf(SocialRelationConstants.TYPE_UNI_FOLLOWER) %>" />
							</portlet:renderURL>

							<a href="<%= viewURL %>"><liferay-ui:message arguments="<%= String.valueOf(followingUsersCount) %>" key="manage-x-people-you-are-following" /></a>
						</liferay-ui:panel>
					</c:if>

					<c:if test="<%= (friendUsersCount <= 0) && (coworkerUsersCount <= 0) && (followingUsersCount <= 0) %>">
						<div class="portlet-msg-info">
							<liferay-ui:message key="you-have-no-contacts" />
						</div>
					</c:if>
				</liferay-ui:panel-container>
			</c:otherwise>
		</c:choose>
	</aui:column>

	<aui:column columnWidth="<%= 25 %>" cssClass="lfr-asset-column lfr-asset-column-actions" last="<%= true %>">
		<div class="lfr-asset-summary">
			<portlet:renderURL var="viewURL">
				<portlet:param name="jspPage" value="/contacts_center/view_user.jsp" />
				<portlet:param name="redirect" value="<%= currentURL %>" />
				<portlet:param name="userId" value="<%= String.valueOf(user.getUserId()) %>" />
			</portlet:renderURL>

			<a href="<%= viewURL %>"><img alt="<liferay-ui:message key="avatar" />" class="avatar" src="<%= user.getPortraitURL(themeDisplay) %>" /></a>

			<div class="lfr-asset-name">
				<h4><%= HtmlUtil.escape(user.getFullName()) %></h4>
			</div>
		</div>
	</aui:column>
</aui:layout>

<aui:script use="aui-base">
	var container = A.one('.lfr-asset-column-details');

	container.delegate(
		'mouseenter',
		function(event) {
			event.currentTarget.ancestor('.lfr-user-grid-item').addClass('hover');
		},
		'.lfr-user-grid-item img'
	);

	container.delegate(
		'mouseleave',
		function(event) {
			event.currentTarget.ancestor('.lfr-user-grid-item').removeClass('hover');
		},
		'.lfr-user-grid-item img'
	);
</aui:script>