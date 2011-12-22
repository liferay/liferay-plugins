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
long userId = ParamUtil.getLong(request, "userId");

User user2 = UserLocalServiceUtil.getUserById(userId);

List<User> users = UserLocalServiceUtil.getSocialUsers(userId, 0, 30, new UserLoginDateComparator());
%>

<aui:layout>
	<aui:column columnWidth="<%= 25 %>" cssClass="group-members" first="<%= true %>">
		<c:choose>
			<c:when test="<%= users.isEmpty() %>">
				<div class="portlet-msg-info">
					<liferay-ui:message arguments="<%= user2.getFullName() %>" key="x-has-no-contacts" />
				</div>
			</c:when>
			<c:otherwise>
				<div class="filter-input">
					<aui:input id="filter" label='<%= LanguageUtil.format(pageContext, "filter-x-contacts" ,user2.getFullName()) %>' name="filter" type="text" />
				</div>

				<%
				String backURL = ParamUtil.getString(request, "backURL");

				PortletURL viewUserURL = renderResponse.createRenderURL();

				viewUserURL.setParameter("jspPage", "/members/view_user.jsp");
				viewUserURL.setParameter("backURL", backURL);

				request.setAttribute(WebKeys.CONTACTS_URL, viewUserURL);
				request.setAttribute(WebKeys.CONTACTS_USERS, users);
				%>

				<div class="group-user-container">
					<liferay-util:include page="/contacts_center/view_users.jsp" servletContext="<%= application %>" />
				</div>
			</c:otherwise>
		</c:choose>
	</aui:column>

	<aui:column columnWidth="<%= 75 %>" last="<%= true %>">

		<%
		request.setAttribute(WebKeys.CONTACTS_USER, user2);
		%>

		<liferay-util:include page="/contacts_center/view_user.jsp" servletContext="<%= application %>" />
	</aui:column>
</aui:layout>

<aui:script use="aui-base,aui-live-search">
	var container = A.one('.lfr-user-grid');

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

	var groupMembers = A.one('.contacts-portlet .group-members');

	var liveSearch = new A.LiveSearch(
		{
			data: function(node) {
				var userInfo = node.one('.lfr-user-info');

				return userInfo.one('.lfr-user-data-name').html() + " " + userInfo.one('.lfr-user-data-email').html();
			},
			input: groupMembers.one('#<portlet:namespace />filter'),
			nodes: groupMembers.all('.lfr-user-grid-item')
		}
	);
</aui:script>