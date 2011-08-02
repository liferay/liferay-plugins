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
ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

Subscription subscription = null;

if (row != null) {
	subscription = (Subscription)row.getObject();
}

AssetRenderer assetRenderer = null;

if (subscription != null) {
	assetRenderer = MySubscriptionsUtil.getAssetRenderer(subscription.getClassName(), subscription.getClassPK());
}
%>

<liferay-ui:icon-menu>

	<%
	String viewURL = null;

	if (assetRenderer != null) {
		viewURL = assetRenderer.getURLViewInContext((LiferayPortletRequest)renderRequest, (LiferayPortletResponse)renderResponse, currentURL);
	}
	else {
		viewURL = MySubscriptionsUtil.getAssetURLViewInContext(subscription.getClassName(), subscription.getClassPK());
	}
	%>

	<liferay-ui:icon
		image="view"
		url="<%= viewURL %>"
	/>

	<%
	String viewPopupURL = null;

	if (assetRenderer != null) {

		PortletURL popupPortletURL = assetRenderer.getURLView((LiferayPortletResponse)renderResponse, LiferayWindowState.POP_UP);

		if (popupPortletURL != null) {
			viewPopupURL = "javascript:displayPopup('" + popupPortletURL.toString() + "', 'my-subscription');";
		}
	}
	%>

	<c:if test="<%= viewPopupURL != null %>">
		<liferay-ui:icon
			src="/html/themes/classic/images/portlet/pop_up.png"
			message="view-in-popup"
			url="<%= viewPopupURL %>"
		/>
	</c:if>

	<portlet:actionURL name="unsubscribe" var="unsubscribeURL">
		<portlet:param name="redirect" value="<%= currentURL %>" />
		<portlet:param name="subscriptionIds" value="<%= String.valueOf(subscription.getSubscriptionId()) %>" />
	</portlet:actionURL>

	<liferay-ui:icon
		image="unsubscribe"
		label="<%= true %>"
		url="<%= unsubscribeURL %>"
	/>
</liferay-ui:icon-menu>