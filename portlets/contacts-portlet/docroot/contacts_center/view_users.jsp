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
List<User> users = (List<User>)request.getAttribute(WebKeys.CONTACTS_USERS);

PortletURL portletURL = (PortletURL)request.getAttribute(WebKeys.CONTACTS_URL);
%>

<c:if test="<%= users != null %>">
	<div class="lfr-user-grid-container">
		<ul class="lfr-user-grid">

			<%
			for (User user2 : users) {
				portletURL.setParameter("userId", String.valueOf(user2.getUserId()));
			%>

				<li class="lfr-user-grid-item">
					<div class="lfr-user-thumb">
						<a href="<%= portletURL.toString() %>"><img alt="<%= HtmlUtil.escape(user2.getFullName()) %>" src="<%= user2.getPortraitURL(themeDisplay) %>" /></a>
					</div>

					<div class="lfr-user-info">
						<div class="lfr-user-data-name">
							<%= HtmlUtil.escape(user2.getFullName()) %>
						</div>

						<div class="lfr-user-data-job-title">
							<%= HtmlUtil.escape(user2.getJobTitle()) %>
						</div>

						<div class="lfr-user-data-extra">
							<span class="lfr-user-data-email">
								<%= HtmlUtil.escape(user2.getEmailAddress()) %>
							</span>
						</div>
					</div>
				</li>

			<%
			}
			%>

		</ul>
	</div>
</c:if>