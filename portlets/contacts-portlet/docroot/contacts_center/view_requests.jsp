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

<liferay-ui:header title="requests" />

<liferay-ui:search-container
	emptyResultsMessage="you-have-no-pending-requests"
>
	<liferay-ui:search-container-results
		results="<%= SocialRequestLocalServiceUtil.getReceiverUserRequests(themeDisplay.getUserId(), SocialRequestConstants.STATUS_PENDING, searchContainer.getStart(), searchContainer.getEnd()) %>"
		total="<%= SocialRequestLocalServiceUtil.getReceiverUserRequestsCount(themeDisplay.getUserId(), SocialRequestConstants.STATUS_PENDING) %>"
	/>

	<liferay-ui:search-container-row
		className="com.liferay.portlet.social.model.SocialRequest"
		escapedModel="<%= true %>"
		keyProperty="requestId"
		modelVar="socialRequest"
	>

		<%
		User user2 = UserLocalServiceUtil.getUser(socialRequest.getUserId());
		%>

		<liferay-portlet:renderURL varImpl="rowURL">
			<portlet:param name="jspPage" value="/contacts_center/view_user.jsp" />
			<portlet:param name="backURL" value="<%= currentURL %>" />
			<portlet:param name="userId" value="<%= String.valueOf(user2.getUserId()) %>" />
		</liferay-portlet:renderURL>

		<liferay-ui:search-container-column-text
			name="requests"
		>

			<%
			String creatorUserName = "<a href=\"" + rowURL.toString() +"\">" + user2.getFullName() + "</a>";
			%>

			<div class="lfr-user-portrait">
				<a href="<%= rowURL %>"><img alt="<liferay-ui:message key="avatar" />" class="avatar" src="<%= user2.getPortraitURL(themeDisplay) %>" /></a>
			</div>

			<div class="lfr-user-data">
				<div class="lfr-user-data-title">
					<liferay-ui:message arguments="<%= creatorUserName %>" key="request-social-networking-summary-add-connection" />
				</div>
			</div>

			<div class="lfr-user-action">
				<portlet:actionURL name="updateSocialRequest" var="confirmURL">
					<portlet:param name="redirect" value="<%= currentURL %>" />
					<portlet:param name="requestId" value="<%= String.valueOf(socialRequest.getRequestId()) %>" />
					<portlet:param name="status" value="<%= String.valueOf(SocialRequestConstants.STATUS_CONFIRM) %>" />
				</portlet:actionURL >

				<span class="lfr-user-action-item lfr-user-action-confirm">
					<a href="<%= confirmURL %>"><liferay-ui:message key="confirm" /></a>
				</span>

				<portlet:actionURL name="updateSocialRequest" var="ignoreURL">
					<portlet:param name="redirect" value="<%= currentURL %>" />
					<portlet:param name="requestId" value="<%= String.valueOf(socialRequest.getRequestId()) %>" />
					<portlet:param name="status" value="<%= String.valueOf(SocialRequestConstants.STATUS_IGNORE) %>" />
				</portlet:actionURL >

				<span class="lfr-user-action-item lfr-user-action-ignore">
					<a href="<%= ignoreURL %>"><liferay-ui:message key="ignore" /></a>
				</span>
			</div>
		</liferay-ui:search-container-column-text>
	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator />
</liferay-ui:search-container>