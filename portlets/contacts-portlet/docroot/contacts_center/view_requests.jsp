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

<liferay-ui:header title="requests" />

<liferay-ui:search-container
	emptyResultsMessage="you-have-no-pending-requests"
	total="<%= SocialRequestLocalServiceUtil.getReceiverUserRequestsCount(themeDisplay.getUserId(), SocialRequestConstants.STATUS_PENDING) %>"
>
	<liferay-ui:search-container-results
		results="<%= SocialRequestLocalServiceUtil.getReceiverUserRequests(themeDisplay.getUserId(), SocialRequestConstants.STATUS_PENDING, searchContainer.getStart(), searchContainer.getEnd()) %>"
	/>

	<liferay-ui:search-container-row
		className="com.liferay.social.kernel.model.SocialRequest"
		escapedModel="<%= true %>"
		keyProperty="requestId"
		modelVar="socialRequest"
	>

		<%
		User user2 = UserLocalServiceUtil.getUser(socialRequest.getUserId());
		%>

		<liferay-ui:search-container-column-text
			name="requests"
		>
			<liferay-portlet:renderURL varImpl="rowURL">
				<portlet:param name="mvcPath" value="/contacts_center/view_user.jsp" />
				<portlet:param name="backURL" value="<%= currentURL %>" />
				<portlet:param name="userId" value="<%= String.valueOf(user2.getUserId()) %>" />
			</liferay-portlet:renderURL>

			<%
			String creatorUserName = "<a href=\"" + rowURL.toString() +"\">" + user2.getFullName() + "</a>";
			%>

			<div class="lfr-user-portrait">
				<a href="<%= rowURL %>"><img alt="<liferay-ui:message escapeAttribute="<%= true %>" key="avatar" />" class="avatar" src="<%= user2.getPortraitURL(themeDisplay) %>" /></a>
			</div>

			<div class="lfr-user-data">
				<div class="lfr-user-data-title">
					<liferay-ui:message arguments="<%= creatorUserName %>" key="request-social-networking-summary-add-connection" translateArguments="<%= false %>" />
				</div>
			</div>

			<div class="lfr-user-action">
				<portlet:actionURL name="updateSocialRequest" var="confirmURL">
					<portlet:param name="redirect" value="<%= currentURL %>" />
					<portlet:param name="requestId" value="<%= String.valueOf(socialRequest.getRequestId()) %>" />
					<portlet:param name="status" value="<%= String.valueOf(SocialRequestConstants.STATUS_CONFIRM) %>" />
				</portlet:actionURL>

				<span class="lfr-user-action-confirm lfr-user-action-item">
					<a href="<%= confirmURL %>"><liferay-ui:message key="confirm" /></a>
				</span>

				<portlet:actionURL name="updateSocialRequest" var="ignoreURL">
					<portlet:param name="redirect" value="<%= currentURL %>" />
					<portlet:param name="requestId" value="<%= String.valueOf(socialRequest.getRequestId()) %>" />
					<portlet:param name="status" value="<%= String.valueOf(SocialRequestConstants.STATUS_IGNORE) %>" />
				</portlet:actionURL>

				<span class="lfr-user-action-ignore lfr-user-action-item">
					<a href="<%= ignoreURL %>"><liferay-ui:message key="ignore" /></a>
				</span>
			</div>
		</liferay-ui:search-container-column-text>
	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator />
</liferay-ui:search-container>