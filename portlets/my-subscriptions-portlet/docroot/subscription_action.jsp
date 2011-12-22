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
ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

Subscription subscription = (Subscription)row.getObject();

AssetRenderer assetRenderer = MySubscriptionsUtil.getAssetRenderer(subscription.getClassName(), subscription.getClassPK());
%>

<liferay-ui:icon-menu>

	<%
	String viewURL = null;

	if (assetRenderer != null) {
		viewURL = assetRenderer.getURLViewInContext(liferayPortletRequest, liferayPortletResponse, currentURL);
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
	String displayPopupHREF = null;

	if (assetRenderer != null) {
		PortletURL displayPopupURL = assetRenderer.getURLView(liferayPortletResponse, LiferayWindowState.POP_UP);

		if (displayPopupURL != null) {
			displayPopupHREF = "javascript:displayPopup('" + displayPopupURL.toString() + "', 'my-subscription');";
		}
	}
	%>

	<c:if test="<%= displayPopupHREF != null %>">
		<liferay-ui:icon
			message="view-in-popup"
			src="../portlet/pop_up.png"
			url="<%= displayPopupHREF %>"
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