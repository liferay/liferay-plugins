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
Group group = themeDisplay.getScopeGroup();
%>

<c:choose>
	<c:when test="<%= group.isUser() %>">

		<%
		List<User> users = UserLocalServiceUtil.getSocialUsers(group.getClassPK(), 0, 30, new UserLoginDateComparator());

		PortletURL portletURL = null;

		try {
			long contactsPlid = PortalUtil.getPlidFromPortletId(group.getGroupId(), false, "1_WAR_contactsportlet");

			portletURL = PortletURLFactoryUtil.create(request, "1_WAR_contactsportlet", contactsPlid, PortletRequest.RENDER_PHASE);
		}
		catch (Exception e){
			portletURL = renderResponse.createRenderURL();

			portletURL.setWindowState(WindowState.MAXIMIZED);

			portletURL.setParameter("jspPage", "/contacts_center/view.jsp");
		}
		%>

		<c:choose>
			<c:when test="<%= users.isEmpty() %>">
				<div class="portlet-msg-info">
					<liferay-ui:message arguments="<%= group.getDescriptiveName(locale) %>" key="x-has-no-contacts" />
				</div>
			</c:when>
			<c:otherwise>
				<aui:layout cssClass="my-contacts">

					<%
					for (User user2 : users) {
					%>

						<aui:layout cssClass="lfr-contact-grid-item">
							<div class="lfr-contact-thumb">
								<a href="<%= user2.getDisplayURL(themeDisplay) %>"><img alt="<%= HtmlUtil.escape(user2.getFullName()) %>" src="<%= user2.getPortraitURL(themeDisplay) %>" /></a>
							</div>

							<div class="lfr-contact-info">
								<div class="lfr-contact-name">
									<a href="<%= user2.getDisplayURL(themeDisplay) %>"><%= HtmlUtil.escape(user2.getFullName()) %></a>
								</div>

								<div class="lfr-contact-job-title">
									<%= HtmlUtil.escape(user2.getJobTitle()) %>
								</div>
							</div>

							<div class="clear"><!-- --></div>
						</aui:layout>

					<%
					}
					%>

				</aui:layout>

				<c:if test="<%= portletURL != null %>">
					<a href="<%= portletURL %>"><liferay-ui:message arguments="<%= group.getDescriptiveName(locale) %>" key="view-all-x-connections" /></a>
				</c:if>
			</c:otherwise>
		</c:choose>
	</c:when>
	<c:otherwise>
		<div class="portlet-msg-error">
			<liferay-ui:message key="this-application-will-only-function-when-placed-on-a-user-page" />
		</div>
	</c:otherwise>
</c:choose>