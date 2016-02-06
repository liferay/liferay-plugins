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
String redirect = ParamUtil.getString(request, "redirect");

long mbCategoryId = ParamUtil.getLong(request, "mbCategoryId");

portletURL.setParameter("mvcPath", "/manage_subscriptions.jsp");
portletURL.setParameter("redirect", redirect);
portletURL.setParameter("mbCategoryId", String.valueOf(mbCategoryId));

request.setAttribute("manage_subscriptions.jsp-portletURL", portletURL);

MBCategory mbCategory = MBCategoryLocalServiceUtil.getMBCategory(mbCategoryId);
%>

<liferay-ui:header
	title="<%= mbCategory.getName() %>"
/>

<form action="<%= portletURL.toString() %>" method="post" name="<portlet:namespace />fm" onSubmit="submitForm(this); return false;">
	<aui:input name="mbCategoryId" type="hidden" value="<%= mbCategoryId %>" />
	<aui:input name="userIds" type="hidden" />

	<h3 class="lfr-panel-title"><span><liferay-ui:message key="users" /></span></h3>

	<liferay-ui:user-search
		portletURL="<%= portletURL %>"
		userParams="<%= new LinkedHashMap() %>"
	>

		<%
		SearchContainer userSearchContainer = (SearchContainer)request.getAttribute(WebKeys.SEARCH_CONTAINER);

		userSearchContainer.setRowChecker(new RowChecker(renderResponse));
		%>

		<liferay-ui:search-container
			headerNames="name,screen-name"
			searchContainer="<%= userSearchContainer %>"
		>
			<liferay-ui:search-container-results
				results="<%= userSearchContainer.getResults() %>"
			/>

			<c:if test="<%= !results.isEmpty() %>">
				<aui:button-row>
					<aui:button onClick='<%= renderResponse.getNamespace() + "subscribeUsers();" %>' value="subscribe" />

					<aui:button onClick='<%= renderResponse.getNamespace() + "unsubscribeUsers();" %>' value="unsubscribe" />
				</aui:button-row>
			</c:if>

			<liferay-ui:search-container-row
				className="com.liferay.portal.kernel.model.User"
				escapedModel="<%= true %>"
				keyProperty="userId"
				modelVar="user2"
				rowIdProperty="screenName"
			>
				<liferay-ui:search-container-column-text
					name="first-name"
					orderable="<%= true %>"
					property="firstName"
				/>

				<liferay-ui:search-container-column-text
					name="last-name"
					orderable="<%= true %>"
					property="lastName"
				/>

				<liferay-ui:search-container-column-text
					name="screen-name"
					orderable="<%= true %>"
					property="screenName"
				/>

				<liferay-ui:search-container-column-text
					name="job-title"
					orderable="<%= true %>"
					value="<%= user2.getJobTitle() %>"
				/>

				<liferay-ui:search-container-column-text
					name="organizations"
				>
					<liferay-ui:write bean="<%= user2 %>" property="organizations" />
				</liferay-ui:search-container-column-text>

				<liferay-ui:search-container-column-text
					name="user-groups"
				>
					<liferay-ui:write bean="<%= user2 %>" property="user-groups" />
				</liferay-ui:search-container-column-text>

				<liferay-ui:search-container-column-jsp
					align="right"
					cssClass="entry-action"
					path="/subscription_action.jsp"
				/>
			</liferay-ui:search-container-row>

			<liferay-ui:search-iterator />
		</liferay-ui:search-container>
	</liferay-ui:user-search>
</form>

<aui:script>
	Liferay.provide(
		window,
		'<portlet:namespace />subscribeUsers',
		function() {
			var userIds = Liferay.Util.listCheckedExcept(document.<portlet:namespace />fm, '<portlet:namespace />allRowIds');

			if (userIds) {
				document.<portlet:namespace />fm.<portlet:namespace />userIds.value = userIds;

				submitForm(document.<portlet:namespace />fm, '<portlet:actionURL name="subscribeUsers"><portlet:param name="redirect" value="<%= portletURL.toString() %>" /></portlet:actionURL>');
			}
		},
		['liferay-util-list-fields']
	);

	Liferay.provide(
		window,
		'<portlet:namespace />unsubscribeUsers',
		function() {
			var userIds = Liferay.Util.listCheckedExcept(document.<portlet:namespace />fm, '<portlet:namespace />allRowIds');

			if (userIds) {
				document.<portlet:namespace />fm.<portlet:namespace />userIds.value = userIds;

				submitForm(document.<portlet:namespace />fm, '<portlet:actionURL name="unsubscribeUsers"><portlet:param name="redirect" value="<%= portletURL.toString() %>" /></portlet:actionURL>');
			}
		},
		['liferay-util-list-fields']
	);
</aui:script>