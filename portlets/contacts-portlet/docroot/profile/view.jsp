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
Group group = themeDisplay.getScopeGroup();
%>

<c:choose>
	<c:when test="<%= group.isUser() %>">

		<%
		User user2 = UserLocalServiceUtil.getUserById(group.getClassPK());

		request.setAttribute(WebKeys.CONTACTS_USER, user2);
		%>

		<liferay-util:include page="/contacts_center/view_user.jsp" portletId="<%= portletDisplay.getId() %>" />
	</c:when>
	<c:otherwise>

		<%
		LinkedHashMap param = new LinkedHashMap();

		param.put("usersGroups", new Long(group.getGroupId()));

		List<User> users = UserLocalServiceUtil.search(company.getCompanyId(), StringPool.BLANK, WorkflowConstants.STATUS_APPROVED, param, QueryUtil.ALL_POS, QueryUtil.ALL_POS, new UserLastNameComparator(true));
		%>

		<c:choose>
			<c:when test="<%= users.size() > 0 %>">
				<aui:layout>
					<aui:column columnWidth="<%= 25 %>" cssClass="group-members" first="<%= true %>">
						<div style="margin-bottom: 1em;">
							<aui:input id="filter" label="filter-members" name="filter" type="text" />
						</div>

						<%
						PortletURL portletURL = renderResponse.createRenderURL();

						portletURL.setParameter("jspPage", "/profile/view.jsp");

						request.setAttribute(WebKeys.CONTACTS_URL, portletURL);
						request.setAttribute(WebKeys.CONTACTS_USERS, users);
						%>

						<div class="group-user-container">
							<liferay-util:include page="/contacts_center/view_users.jsp" portletId="<%= portletDisplay.getId() %>" />
						</div>
					</aui:column>
					<aui:column columnWidth="<%= 75 %>" last="<%= true %>">

						<%
						long userId = ParamUtil.getLong(request, "userId");

						User user2 = null;

						if (userId > 0) {
							user2 = UserLocalServiceUtil.getUserById(userId);
						}
						else {
							user2 = users.get(0);
						}

						request.setAttribute(WebKeys.CONTACTS_USER, user2);
						%>

						<liferay-util:include page="/contacts_center/view_user.jsp" portletId="<%= portletDisplay.getId() %>" />
					</aui:column>
				</aui:layout>
			</c:when>
			<c:otherwise>
				<div class="portlet-msg-error">
					<liferay-ui:message key="this-site-has-no-members" />
				</div>
			</c:otherwise>
		</c:choose>

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
	</c:otherwise>
</c:choose>