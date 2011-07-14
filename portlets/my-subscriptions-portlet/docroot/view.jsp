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
			deltaConfigurable="<%= true %>"
			emptyResultsMessage="no-subscriptions-were-found"
			iteratorURL="<%= iteratorURL %>"
			rowChecker="<%= new RowChecker(renderResponse) %>"
		>
			<liferay-ui:search-container-results
				results="<%= SubscriptionTransactionUtil.getSubscriptions(user.getUserId(), searchContainer.getStart(), searchContainer.getEnd(),new SubscriptionClassNameIdComparator(true)) %>"
				total="<%= SubscriptionTransactionUtil.getSubscriptionsCount(user.getUserId()) %>"
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
				if (!subscription.getClassName().equals(subscriptionClassName)) {
					subscriptionClassName = subscription.getClassName();

					ResultRow insertRow = new ResultRow(null, 0, searchContainer.getCur());

					insertRow.addText("left", "top", 4, MySubscriptionsUtil.getClassNameIdText(subscription.getClassNameId(), locale));
					insertRow.setClassHoverName("asset-name");
					insertRow.setClassName("asset-name");

					List resultRows = searchContainer.getResultRows();

					resultRows.add(insertRow);
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
					value="<%= MySubscriptionsUtil.getTitleText(subscription.getClassName(), subscription.getClassPK(), ((assetRenderer != null) ? assetRenderer.getTitle(locale) : null)) %>"
				/>

				<liferay-ui:search-container-column-text
					href="<%= rowURL %>"
					name="asset-type"
					value='<%= MySubscriptionsUtil.getClassNameIdText(subscription.getClassNameId(), locale) %>'
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

<aui:script use="aui-base,aui-dialog,aui-dialog-iframe,aui-io">
	displayPopup = function(url, title) {
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
	};

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