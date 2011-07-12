<%--
/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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
List<SocialRequest> socialRequests = SocialRequestLocalServiceUtil.getReceiverUserRequests(themeDisplay.getUserId(), SocialRequestConstants.STATUS_PENDING, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
%>

<%
for (SocialRequest socialRequest : socialRequests) {
	SocialRequestFeedEntry requestFeedEntry = SocialRequestInterpreterLocalServiceUtil.interpret(socialRequest, themeDisplay);

	String userFullName = StringPool.BLANK;
	String userDisplayURL = StringPool.BLANK;
	String userPortaitURL = StringPool.BLANK;

	try {
		User user2 = UserLocalServiceUtil.getUser(socialRequest.getUserId());
		userFullName = user2.getFullName();
		userDisplayURL = user2.getDisplayURL(themeDisplay);
		userPortaitURL = user2.getPortraitURL(themeDisplay);
	}
	catch (NoSuchUserException nsue) {
	}
%>

	<c:choose>
		<c:when test="<%= requestFeedEntry == null %>">
			<div class="portlet-msg-error">
				<liferay-ui:message key="request-cannot-be-interpreted-because-it-does-not-have-an-associated-interpreter" />
			</div>
		</c:when>
		<c:otherwise>
			<div class="notification-entry">
				<div class="thumbnail">
					<a href="<%= userDisplayURL %>">
						<img alt="<%= userFullName %>" src="<%= userPortaitURL %>" />
					</a>
				</div>

				<div class="notification-content">
					<div>
						<%= requestFeedEntry.getTitle() %>
					</div>

					<c:if test="<%= Validator.isNotNull(requestFeedEntry.getBody()) %>">
						<div>
							<%= requestFeedEntry.getBody() %>
						</div>
					</c:if>

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
				</div>
			</div>
		</c:otherwise>
	</c:choose>

<%
}
%>