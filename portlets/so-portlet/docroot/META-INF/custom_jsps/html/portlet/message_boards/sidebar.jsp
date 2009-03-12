<%
/**
 * Copyright (c) 2000-2009 Liferay, Inc. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
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