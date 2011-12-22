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
			deltaConfigurable="<%= true %>"
			emptyResultsMessage="no-subscriptions-were-found"
			iteratorURL="<%= iteratorURL %>"
			rowChecker="<%= new RowChecker(renderResponse) %>"
		>
			<liferay-ui:search-container-results
				results="<%= SubscriptionLocalServiceUtil.getUserSubscriptions(user.getUserId(), searchContainer.getStart(), searchContainer.getEnd(), new SubscriptionClassNameIdComparator(true)) %>"
				total="<%= SubscriptionLocalServiceUtil.getUserSubscriptionsCount(user.getUserId()) %>"
			/>

			<%
			String subscriptionClassName = StringPool.BLANK;
			%>

			<liferay-ui:search-container-row
				className="com.liferay.portal.model.Subscription"
				escapedModel="<%= true %>"
				keyProperty="subscriptionId"
				modelVar="subscription"
			>

				<%
				if (!subscriptionClassName.equals(subscription.getClassName())) {
					subscriptionClassName = subscription.getClassName();

					ResultRow resultRow = new ResultRow(null, 0, searchContainer.getCur());

					resultRow.addText("left", "top", 4, ResourceActionsUtil.getModelResource(locale, subscription.getClassName()));
					resultRow.setClassHoverName("asset-name");
					resultRow.setClassName("asset-name");

					List<ResultRow> resultRows = searchContainer.getResultRows();

					resultRows.add(resultRow);
				}

				AssetRenderer assetRenderer = MySubscriptionsUtil.getAssetRenderer(subscription.getClassName(), subscription.getClassPK());

				String rowURL = null;

				if (assetRenderer != null) {
					rowURL = assetRenderer.getURLViewInContext((LiferayPortletRequest)renderRequest, (LiferayPortletResponse)renderResponse, currentURL);
				}
				else {
					rowURL = MySubscriptionsUtil.getAssetURLViewInContext(subscription.getClassName(), subscription.getClassPK());
				}
				%>

				<liferay-ui:search-container-column-text
					href="<%= rowURL %>"
					name="title"
					value="<%= MySubscriptionsUtil.getTitleText(locale, subscription.getClassName(), subscription.getClassPK(), ((assetRenderer != null) ? assetRenderer.getTitle(locale) : null)) %>"
				/>

				<liferay-ui:search-container-column-text
					href="<%= rowURL %>"
					name="asset-type"
					value="<%= ResourceActionsUtil.getModelResource(locale, subscription.getClassName()) %>"
				/>

				<liferay-ui:search-container-column-text
					href="<%= rowURL %>"
					name="create-date"
					value="<%= dateFormatDateTime.format(subscription.getCreateDate()) %>"
				/>

				<liferay-ui:search-container-column-jsp
					align="right"
					path="/subscription_action.jsp"
				/>
			</liferay-ui:search-container-row>

			<liferay-ui:search-iterator />

			<c:if test="<%= !results.isEmpty() %>">
				<aui:button-row cssName="unsubscribe-button-row">
					<aui:button type="submit" value="unsubscribe" />
				</aui:button-row>
			</c:if>
		</liferay-ui:search-container>
	</aui:fieldset>
</aui:form>

<aui:script>
	Liferay.provide(
		window,
		'<portlet:namespace />displayPopup',
		function(url, title) {
			var dialog = new A.Dialog(
				{
					align: {
						node: null,
						points: ['tc', 'tc']
					},
					constrain2view: true,
					cssClass: 'portlet-my-subscription',
					modal: true,
					resizable: false,
					title: title,
					width: 950
				}
			).plug(
				A.Plugin.DialogIframe,
				{
					uri: url
				}
			).render();
		},
		['aui-dialog', 'aui-dialog-iframe']
	);

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