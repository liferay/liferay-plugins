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

<portlet:actionURL name="unsubscribe" var="unsubscribeURL">
	<portlet:param name="redirect" value="<%= currentURL %>" />
</portlet:actionURL>

<aui:form action="<%= unsubscribeURL %>" method="get" name="fm" onSubmit='<%= "event.preventDefault(); " + renderResponse.getNamespace() + "unsubscribe();" %>'>
	<liferay-portlet:renderURLParams varImpl="portletURL" />
	<aui:input name="subscriptionIds" type="hidden" />

	<liferay-ui:error exception="<%= NoSuchSubscriptionException.class %>" message="the-subscription-could-not-be-found" />

	<aui:fieldset>
		<liferay-portlet:renderURL varImpl="iteratorURL" />

		<liferay-ui:search-container
			rowChecker="<%= new RowChecker(renderResponse) %>"
			searchContainer="<%= new SubscriptionSearch(renderRequest, iteratorURL) %>"
		>
			<liferay-ui:search-container-results
				results="<%= SubscriptionTransactionUtil.getSubscriptions(user.getUserId(), searchContainer.getStart(), searchContainer.getEnd(), searchContainer.getOrderByComparator()) %>"
				total="<%= SubscriptionTransactionUtil.getSubscriptionsCount(user.getUserId()) %>"
			/>

			<liferay-ui:search-container-row
				className="com.liferay.portal.model.Subscription"
				escapedModel="<%= true %>"
				keyProperty="subscriptionId"
				modelVar="subscription"
			>

				<%
				AssetRenderer assetRenderer = MySubscriptionsUtil.getAssetRenderer(subscription.getClassName(), subscription.getClassPK());

				String rowURL = null;

				if (assetRenderer != null) {
					rowURL = assetRenderer.getURLViewInContext((LiferayPortletRequest)renderRequest, (LiferayPortletResponse)renderResponse, currentURL);
				}
				%>

				<liferay-ui:search-container-column-text
					href="<%= rowURL %>"
					name="class-name-id"
					orderable="<%= true %>"
					value='<%= MySubscriptionsUtil.getClassNameIdText(subscription.getClassNameId(), locale) %>'
				/>

				<liferay-ui:search-container-column-text
					href="<%= rowURL %>"
					name="class-primary-key"
					orderable="<%= true %>"
					value ="<%= MySubscriptionsUtil.getTitleText(subscription.getClassName(), subscription.getClassPK(), ((assetRenderer != null) ? assetRenderer.getTitle(locale) : null), locale) %>"
				/>

				<liferay-ui:search-container-column-text
					href="<%= rowURL %>"
					name="create-date"
					orderable="<%= true %>"
					value="<%= dateFormatDateTime.format(subscription.getCreateDate()) %>"
				/>

				<liferay-ui:search-container-column-text
					align="right"
				>
					<portlet:actionURL name="unsubscribe" var="unsubscribeURL">
						<portlet:param name="redirect" value="<%= currentURL %>" />
						<portlet:param name="subscriptionIds" value="<%= String.valueOf(subscription.getSubscriptionId()) %>" />
					</portlet:actionURL>

					<liferay-ui:icon
						image="unsubscribe"
						label="<%= true %>"
						url="<%= unsubscribeURL %>"
					/>
				</liferay-ui:search-container-column-text>
			</liferay-ui:search-container-row>

			<c:if test="<%= !results.isEmpty() %>">
				<aui:button-row>
					<aui:button type="submit" value="unsubscribe" />
				</aui:button-row>
			</c:if>

			<liferay-ui:search-iterator />
		</liferay-ui:search-container>
	</aui:fieldset>
</aui:form>

<aui:script>
	Liferay.provide(
		window,
		'<portlet:namespace />unsubscribe',
		function() {
			document.<portlet:namespace />fm.method = "post";
			document.<portlet:namespace />fm.<portlet:namespace />subscriptionIds.value = Liferay.Util.listCheckedExcept(document.<portlet:namespace />fm, "<portlet:namespace />allRowIds");
			submitForm(document.<portlet:namespace />fm);
		},
		['liferay-util-list-fields']
	);
</aui:script>