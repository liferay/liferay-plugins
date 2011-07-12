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

<%@ include file="/html/portlet/message_boards/init.jsp" %>

<%
String topLink = ParamUtil.getString(request, "topLink", "message-boards-home");
%>

<c:choose>
	<c:when test='<%= topLink.equals("message-boards-home") %>'>

		<%
		String redirect = ParamUtil.getString(request, "redirect");

		MBCategory category = (MBCategory)request.getAttribute(WebKeys.MESSAGE_BOARDS_CATEGORY);

		long categoryId = MBUtil.getCategoryId(request, category);

		boolean showCategories = ParamUtil.getBoolean(request, "showCategories", false);

		String displayStyle = BeanPropertiesUtil.getString(category, "displayStyle", MBCategoryConstants.DEFAULT_DISPLAY_STYLE);

		MBCategoryDisplay categoryDisplay = new MBCategoryDisplayImpl(scopeGroupId, categoryId);

		Set<Long> categorySubscriptionClassPKs = null;
		Set<Long> threadSubscriptionClassPKs = null;

		if (themeDisplay.isSignedIn()) {
			List<Subscription> categorySubscriptions = SubscriptionLocalServiceUtil.getUserSubscriptions(user.getUserId(), MBCategory.class.getName());

			categorySubscriptionClassPKs = new HashSet<Long>(categorySubscriptions.size());

			for (Subscription subscription : categorySubscriptions) {
				categorySubscriptionClassPKs.add(subscription.getClassPK());
			}

			threadSubscriptionClassPKs = new HashSet<Long>();

			List<Subscription> threadSubscriptions = SubscriptionLocalServiceUtil.getUserSubscriptions(user.getUserId(), MBThread.class.getName());

			threadSubscriptionClassPKs = new HashSet<Long>(threadSubscriptions.size());

			for (Subscription subscription : threadSubscriptions) {
				threadSubscriptionClassPKs.add(subscription.getClassPK());
			}
		}

		PortletURL portletURL = renderResponse.createRenderURL();

		portletURL.setParameter("struts_action", "/message_boards/view");
		portletURL.setParameter("topLink", topLink);
		portletURL.setParameter("mbCategoryId", String.valueOf(categoryId));

		request.setAttribute("view.jsp-categoryDisplay", categoryDisplay);

		request.setAttribute("view.jsp-categorySubscriptionClassPKs", categorySubscriptionClassPKs);
		request.setAttribute("view.jsp-threadSubscriptionClassPKs", threadSubscriptionClassPKs);

		request.setAttribute("view.jsp-viewCategory", String.valueOf(showCategories));

		request.setAttribute("view.jsp-portletURL", portletURL);
		%>

		<liferay-util:include page="/html/portlet/message_boards/sidebar.jsp" />

		<c:if test="<%= category != null %>">

			<%
			long parentCategoryId = category.getParentCategoryId();
			String parentCategoryName = LanguageUtil.get(pageContext, "message-boards-home");

			if (!category.isRoot()) {
				MBCategory parentCategory = MBCategoryLocalServiceUtil.getCategory(parentCategoryId);

				parentCategoryId = parentCategory.getCategoryId();
				parentCategoryName = parentCategory.getName();
			}
			%>

			<portlet:renderURL var="backURL">
				<portlet:param name="struts_action" value="/message_boards/view" />
				<portlet:param name="mbCategoryId" value="<%= String.valueOf(parentCategoryId) %>" />
			</portlet:renderURL>

			<liferay-ui:header
				backLabel="<%= parentCategoryName %>"
				backURL="<%= backURL.toString() %>"
				title="<%= category.getName() %>"
			/>
		</c:if>

		<div class="displayStyle-<%= displayStyle %>">
			<liferay-util:include page='<%= "/html/portlet/message_boards/view_category_" + displayStyle + ".jsp" %>' />
		</div>

		<%
		if (category != null) {
			PortalUtil.setPageSubtitle(category.getName(), request);
			PortalUtil.setPageDescription(category.getDescription(), request);

			MBUtil.addPortletBreadcrumbEntries(category, request, renderResponse);
		}
		%>

	</c:when>
	<c:otherwise>
		<liferay-ui:header title="<%= topLink %>" />

		<liferay-util:include page="/html/portlet/message_boards/sidebar.jsp" />

		<liferay-util:include page="/html/portlet/message_boards/view.jsp" useCustomPage="<%= false %>" />
	</c:otherwise>
</c:choose>