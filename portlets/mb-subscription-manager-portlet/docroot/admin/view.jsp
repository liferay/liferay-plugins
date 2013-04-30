<%--
/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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

<aui:form action="<%= portletURL %>" method="post" name="fm1">
	<aui:input id="keywords" inlineField="<%= true %>" label="" name="keywords" size="30" type="text" />

	<aui:button type="submit" value="search" />
</aui:form>

<liferay-ui:search-container
	emptyResultsMessage="no-categories-found"
	iteratorURL="<%= portletURL %>"
>
	<liferay-ui:search-container-results
		results="<%= SubscriptionManagerUtil.getCategories(themeDisplay.getCompanyId(), keywords, searchContainer.getStart(), searchContainer.getEnd()) %>"
		total="<%= SubscriptionManagerUtil.getCategoriesCount(themeDisplay.getCompanyId(), keywords) %>"
	/>

	<liferay-ui:search-container-row
		className="com.liferay.portlet.messageboards.model.MBCategory"
		escapedModel="<%= true %>"
		keyProperty="categoryId"
		modelVar="curCategory"
	>
		<liferay-portlet:renderURL var="editSubscriptionsURL">
			<portlet:param name="mvcPath" value="/admin/edit_subscriptions.jsp" />
			<portlet:param name="redirect" value="<%= currentURL %>" />
			<portlet:param name="mbCategoryId" value="<%= String.valueOf(curCategory.getCategoryId()) %>" />
		</liferay-portlet:renderURL>

		<liferay-ui:search-container-column-text
			href="<%= editSubscriptionsURL %>"
			name="category"
			value="<%= curCategory.getName() %>"
		/>

		<liferay-ui:search-container-column-text
			name="context"
			value="<%= StringUtil.shorten(_getCategoryBreadcrumb(curCategory), 512) %>"
		/>

		<liferay-ui:search-container-column-text
			name="subscribers"
			value="<%= StringUtil.shorten(_getSubscribers(curCategory), 512) %>"
		/>

		<liferay-ui:search-container-column-jsp
			align="right"
			path="/admin/category_action.jsp"
		/>
	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator />
</liferay-ui:search-container>

<%!
private String _getCategoryBreadcrumb(MBCategory category) throws Exception {
	StringBundler sb = new StringBundler();

	List<MBCategory> ancestorCategories = category.getAncestors();

	Collections.reverse(ancestorCategories);

	Iterator itr = ancestorCategories.iterator();

	while (itr.hasNext()) {
		MBCategory mbCategory = (MBCategory)itr.next();

		sb.append(mbCategory.getName());

		if (itr.hasNext()) {
			sb.append(" &raquo; ");
		}
	}

	return sb.toString();
}

private String _getSubscribers(MBCategory category) throws Exception {
	StringBundler sb = new StringBundler();

	List<Subscription> subscriptions = SubscriptionLocalServiceUtil.getSubscriptions(category.getCompanyId(), MBCategory.class.getName(), category.getCategoryId());

	Iterator itr = subscriptions.iterator();

	while (itr.hasNext()) {
		Subscription subscription = (Subscription)itr.next();

		sb.append(subscription.getUserName());

		if (itr.hasNext()) {
			sb.append(StringPool.COMMA_AND_SPACE);
		}
	}

	return sb.toString();
}
%>