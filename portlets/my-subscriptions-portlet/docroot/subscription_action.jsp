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
ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

Subscription subscription = (Subscription)row.getObject();

AssetRenderer assetRenderer = MySubscriptionsUtil.getAssetRenderer(subscription.getClassName(), subscription.getClassPK());
%>

<liferay-ui:icon-menu icon="<%= StringPool.BLANK %>" message="<%= StringPool.BLANK %>">

	<%
	String viewURL = null;

	if (assetRenderer != null) {
		viewURL = assetRenderer.getURLViewInContext(liferayPortletRequest, liferayPortletResponse, currentURL);
	}
	else {
		viewURL = MySubscriptionsUtil.getAssetURLViewInContext(themeDisplay, subscription.getClassName(), subscription.getClassPK());
	}
	%>

	<c:if test="<%= viewURL != null %>">
		<liferay-ui:icon
			iconCssClass="icon-search"
			message="view"
			url="<%= viewURL %>"
		/>
	</c:if>

	<%
	String displayPopupHREF = null;

	if (assetRenderer != null) {
		String displayPopupURL = assetRenderer.getURLView(liferayPortletResponse, LiferayWindowState.POP_UP);

		if (Validator.isNotNull(displayPopupURL)) {
			StringBundler sb = new StringBundler(7);

			sb.append("javascript:");
			sb.append(liferayPortletResponse.getNamespace());
			sb.append("displayPopup('");
			sb.append(displayPopupURL);
			sb.append("', '");
			sb.append(UnicodeLanguageUtil.get(request, "my-subscription"));
			sb.append("');");

			displayPopupHREF = sb.toString();
		}
	}
	%>

	<c:if test="<%= displayPopupHREF != null %>">
		<liferay-ui:icon
			iconCssClass="icon-list-alt"
			message="view-in-popup"
			url="<%= displayPopupHREF %>"
		/>
	</c:if>

	<portlet:actionURL name="unsubscribe" var="unsubscribeURL">
		<portlet:param name="redirect" value="<%= currentURL %>" />
		<portlet:param name="subscriptionIds" value="<%= String.valueOf(subscription.getSubscriptionId()) %>" />
	</portlet:actionURL>

	<liferay-ui:icon
		iconCssClass="icon-remove-sign"
		label="<%= true %>"
		message="unsubscribe"
		url="<%= unsubscribeURL %>"
	/>
</liferay-ui:icon-menu>