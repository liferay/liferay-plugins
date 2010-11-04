<%--
/**
 * Copyright (c) 2008-2010 Liferay, Inc. All rights reserved.
 *
 * This file is part of Liferay Social Office. Liferay Social Office is free
 * software: you can redistribute it and/or modify it under the terms of the GNU
 * Affero General Public License as published by the Free Software Foundation,
 * either version 3 of the License, or (at your option) any later version.
 *
 * Liferay Social Office is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Affero General Public License
 * for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * Liferay Social Office. If not, see http://www.gnu.org/licenses/agpl-3.0.html.
 */
--%>

<%@ include file="/html/portlet/requests/init.jsp" %>

<%
List<SocialRequest> requests = (List<SocialRequest>)request.getAttribute(WebKeys.SOCIAL_REQUESTS);
%>

<c:if test="<%= requests != null %>">

	<%
	PortletURL portletURL = renderResponse.createActionURL();

	portletURL.setParameter("struts_action", "/requests/update_request");
	portletURL.setParameter("redirect", currentURL);
	%>

	<table class="lfr-table" width="100%">

	<%
	for (int i = 0; i < requests.size(); i++) {
		SocialRequest socialRequest = requests.get(i);

		SocialRequestFeedEntry requestFeedEntry = SocialRequestInterpreterLocalServiceUtil.interpret(socialRequest, themeDisplay);

		User curUser = UserLocalServiceUtil.getUser(socialRequest.getUserId());

		String src = themeDisplay.getPathImage() + "/user_male_portrait?img_id=0&t=";

		if (curUser != null) {
			src = themeDisplay.getPathImage() + "/user_" + (curUser.isFemale() ? "female" : "male") + "_portrait?img_id=" + curUser.getPortraitId() + "&t=" + ImageServletTokenUtil.getToken(curUser.getPortraitId());
		}
	%>

		<tr class="request">
			<td valign="top">
				<div class="thumbnail">
					<a href="<%= curUser.getDisplayURL(themeDisplay) %>">
						<img alt="<%= curUser.getFullName() %>" src="<%= src %>" />
					</a>
				</div>
			</td>
			<td valign="top" width="99%">
				<c:choose>
					<c:when test="<%= requestFeedEntry == null %>">
						<div class="portlet-msg-error">
							<liferay-ui:message key="request-cannot-be-interpreted-because-it-does-not-have-an-associated-interpreter" />
						</div>
					</c:when>
					<c:otherwise>

						<%
						portletURL.setParameter("requestId", String.valueOf(socialRequest.getRequestId()));
						%>

						<div>
							<%= requestFeedEntry.getTitle() %>
						</div>

						<div class="controls">

							<%
							portletURL.setParameter("status", String.valueOf(SocialRequestConstants.STATUS_CONFIRM));
							%>

							<a href="<%= portletURL.toString() %>" onClick="Liferay.Util.forcePost(this); return false;"><liferay-ui:message key="confirm" /></a>

							<%
							portletURL.setParameter("status", String.valueOf(SocialRequestConstants.STATUS_IGNORE));
							%>

							<a href="<%= portletURL.toString() %>" onClick="Liferay.Util.forcePost(this); return false;"><liferay-ui:message key="ignore" /></a>
						</div>
					</c:otherwise>
				</c:choose>
			</td>
		</tr>

	<%
	}
	%>

	</table>
</c:if>