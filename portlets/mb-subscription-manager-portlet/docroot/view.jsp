<%--
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
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
String keywords = ParamUtil.getString(request, "keywords");
%>

<liferay-ui:header
	title="categories"
/>

<aui:form action="<%= portletURL %>" cssClass="form-search" method="post" name="fm1">
	<liferay-ui:input-search placeholder='<%= LanguageUtil.get(request, "keywords") %>' title='<%= LanguageUtil.get(request, "keywords") %>' />
</aui:form>

<liferay-ui:search-container
	emptyResultsMessage="no-categories-were-found"
	iteratorURL="<%= portletURL %>"
	total="<%= SubscriptionManagerUtil.getMBCategoriesCount(scopeGroupId, keywords) %>"
>
	<liferay-ui:search-container-results
		results="<%= SubscriptionManagerUtil.getMBCategories(scopeGroupId, keywords, searchContainer.getStart(), searchContainer.getEnd()) %>"
	/>

	<liferay-ui:search-container-row
		className="com.liferay.message.boards.kernel.model.MBCategory"
		escapedModel="<%= true %>"
		keyProperty="categoryId"
		modelVar="mbCategory"
	>
		<liferay-portlet:renderURL var="editSubscriptionsURL">
			<portlet:param name="mvcPath" value="/manage_subscriptions.jsp" />
			<portlet:param name="redirect" value="<%= currentURL %>" />
			<portlet:param name="mbCategoryId" value="<%= String.valueOf(mbCategory.getCategoryId()) %>" />
		</liferay-portlet:renderURL>

		<liferay-ui:search-container-column-text
			href="<%= editSubscriptionsURL %>"
			name="category"
			value="<%= mbCategory.getName() %>"
		/>

		<liferay-ui:search-container-column-text
			name="path"
			value="<%= _getCategoryBreadcrumb(mbCategory) %>"
		/>

		<liferay-ui:search-container-column-text
			name="subscribers"
			value="<%= _getSubscribers(pageContext, mbCategory) %>"
		/>

		<liferay-ui:search-container-column-jsp
			align="right"
			cssClass="entry-action"
			path="/category_action.jsp"
		/>
	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator />
</liferay-ui:search-container>

<%!
private String _getCategoryBreadcrumb(MBCategory mbCategory) throws Exception {
	StringBundler sb = new StringBundler();

	List<MBCategory> ancestorMBCategories = mbCategory.getAncestors();

	Collections.reverse(ancestorMBCategories);

	Iterator itr = ancestorMBCategories.iterator();

	while (itr.hasNext()) {
		MBCategory ancestorMBCategory = (MBCategory)itr.next();

		sb.append(ancestorMBCategory.getName());

		if (itr.hasNext()) {
			sb.append(" &raquo; ");
		}
	}

	return sb.toString();
}

private String _getSubscribers(PageContext pageContext, MBCategory mbCategory) throws Exception {
	StringBundler sb = new StringBundler();

	List<Subscription> subscriptions = SubscriptionLocalServiceUtil.getSubscriptions(mbCategory.getCompanyId(), MBCategory.class.getName(), mbCategory.getCategoryId());

	int count = subscriptions.size() - 3;

	subscriptions = ListUtil.subList(subscriptions, 0, 3);

	Iterator itr = subscriptions.iterator();

	while (itr.hasNext()) {
		Subscription subscription = (Subscription)itr.next();

		sb.append(subscription.getUserName());

		if (itr.hasNext()) {
			sb.append(StringPool.COMMA_AND_SPACE);
		}
	}

	if (count <= 0) {
		return sb.toString();
	}

	HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();

	ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(WebKeys.THEME_DISPLAY);

	PortletURL portletURL = PortletURLFactoryUtil.create(request, PortletKeys.MB_SUBSCRIPTION_MANAGER, themeDisplay.getPlid(), PortletRequest.RENDER_PHASE);

	portletURL.setParameter("mvcPath", "/manage_subscriptions.jsp");
	portletURL.setParameter("redirect", PortalUtil.getCurrentURL(request));
	portletURL.setParameter("mbCategoryId", String.valueOf(mbCategory.getCategoryId()));

	sb.append(StringPool.SPACE);
	sb.append("<a href=\"");
	sb.append(HtmlUtil.escape(portletURL.toString()));
	sb.append("\">");
	sb.append(LanguageUtil.format(request, "and-x-more", String.valueOf(count), false));
	sb.append("</a>");

	return sb.toString();
}
%>