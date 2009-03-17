<%
/**
 * Copyright (c) 2008-2009 Liferay, Inc. All rights reserved.
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
%>

<%@ include file="/html/portlet/message_boards/init.jsp" %>

<%
MBCategory category = (MBCategory)request.getAttribute(WebKeys.MESSAGE_BOARDS_CATEGORY);

long categoryId = BeanParamUtil.getLong(category, request, "categoryId", MBCategoryImpl.DEFAULT_PARENT_CATEGORY_ID);

PortletURL tabs1URL = renderResponse.createRenderURL();
%>

<div class="sidebar">
	<liferay-portlet:renderURL windowState="<%= WindowState.MAXIMIZED.toString() %>" varImpl="searchURL"><portlet:param name="struts_action" value="/message_boards/search" /></liferay-portlet:renderURL>

	<form action="<%= searchURL %>" method="get" name="<portlet:namespace />sidebarFm" onSubmit="submitForm(this); return false;">
	<liferay-portlet:renderURLParams varImpl="searchURL" />
	<input name="<portlet:namespace />redirect" type="hidden" value="<%= currentURL %>" />
	<input name="<portlet:namespace />breadcrumbsCategoryId" type="hidden" value="<%= categoryId %>" />
	<input name="<portlet:namespace />searchCategoryIds" type="hidden" value="<%= categoryId %>" />

	<div class="search">
		<input class="search-input" id="<portlet:namespace />keywords1" name="<portlet:namespace />keywords" type="text" />

		<input class="search-button" type="submit" value="<liferay-ui:message key="search" />" />
	</div>

	</form>

	<h2><liferay-ui:message key="categories" /></h2>

	<ul class="disc">

		<%
		long parentCategoryId = MBCategoryImpl.DEFAULT_PARENT_CATEGORY_ID;

		boolean showMoreLink = false;

		if (MBCategoryLocalServiceUtil.getCategoriesCount(scopeGroupId, parentCategoryId) > 10) {
			showMoreLink = true;
		}

		List<MBCategory> categories = MBCategoryLocalServiceUtil.getCategories(scopeGroupId, parentCategoryId, 0, 10);

		for(MBCategory curCategory : categories) {
			curCategory = curCategory.toEscapedModel();

			PortletURL viewCategoryURL = renderResponse.createRenderURL();

			viewCategoryURL.setParameter("struts_action", "/message_boards/view");
			viewCategoryURL.setParameter("categoryId", String.valueOf(curCategory.getCategoryId()));
		%>

			<li>
				<a <%= (curCategory.getCategoryId() == categoryId) ? "class=\"category-current\"" : "" %> href="<%= viewCategoryURL %>"><%= curCategory.getName() %></a>
			</li>

		<%
		}
		%>

	</ul>

	<c:if test="<%= showMoreLink %>">

		<%
		tabs1URL.setParameter("tabs1", "categories");
		%>

		<a href="<%= tabs1URL %>"><liferay-ui:message key="view-more" /></a>
	</c:if>

	<h2><liferay-ui:message key="quick-links" /></h2>

	<ul class="disc">
		<li>

			<%
			tabs1URL.setParameter("tabs1", "categories");
			%>

			<a href="<%= tabs1URL %>"><liferay-ui:message key="categories" /></a>
		</li>

		<c:if test="<%= themeDisplay.isSignedIn() %>">
			<li>

				<%
				tabs1URL.setParameter("tabs1", "my_posts");
				%>

				<a href="<%= tabs1URL %>"><liferay-ui:message key="my-posts" /></a>
			</li>
			<li>

				<%
				tabs1URL.setParameter("tabs1", "my_subscriptions");
				%>

				<a href="<%= tabs1URL %>"><liferay-ui:message key="my-subscriptions" /></a>
			</li>
		</c:if>

		<li>

			<%
			tabs1URL.setParameter("tabs1", "recent_posts");
			%>

			<a href="<%= tabs1URL %>"><liferay-ui:message key="recent-posts" /></a>
		</li>
		<li>

			<%
			tabs1URL.setParameter("tabs1", "statistics");
			%>

			<a href="<%= tabs1URL %>"><liferay-ui:message key="statistics" /></a>
		</li>

		<c:if test="<%= PortletPermissionUtil.contains(permissionChecker, plid, PortletKeys.MESSAGE_BOARDS, ActionKeys.BAN_USER) %>">
			<li>

				<%
				tabs1URL.setParameter("tabs1", "banned_users");
				%>

				<a href="<%= tabs1URL %>"><liferay-ui:message key="banned-users" /></a>
			</li>
		</c:if>
	</ul>
</div>