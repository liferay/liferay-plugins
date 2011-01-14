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
String topLink = ParamUtil.getString(request, "topLink", "contacts-home");

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("jspPage", "/contacts_center/view.jsp");
portletURL.setParameter("topLink", topLink);

String keywords = ParamUtil.getString(request, "keywords");
%>

<liferay-portlet:renderURL varImpl="searchURL">
	<portlet:param name="jspPage" value="/contacts_center/view.jsp" />
	<portlet:param name="topLink" value="<%= topLink %>" />
</liferay-portlet:renderURL>

<aui:form action="<%= searchURL %>" method="get" name="fm">
	<liferay-portlet:renderURLParams varImpl="searchURL" />
	<aui:input name="topLink" type="hidden" value="<%= topLink %>" />

	<liferay-ui:search-container
		emptyResultsMessage="no-users-were-found"
		iteratorURL="<%= portletURL %>"
	>
		<div>
			<aui:input inlineField="<%= true %>" id="keywords" label="" name="keywords" size="30" title="search-users" type="text" />

			<aui:button type="submit" value="search" />
		</div>

		<br />

		<liferay-ui:search-container-results
			results="<%= UserLocalServiceUtil.search(company.getCompanyId(), keywords, true, null, searchContainer.getStart(), searchContainer.getEnd(), new UserLastNameComparator(true)) %>"
			total="<%= UserLocalServiceUtil.searchCount(company.getCompanyId(), keywords, true, null) %>"
		/>

		<liferay-ui:search-container-row
			className="com.liferay.portal.model.User"
			escapedModel="<%= true %>"
			keyProperty="userId"
			modelVar="user2"
		>
			<liferay-portlet:renderURL varImpl="rowURL">
				<portlet:param name="jspPage" value="/contacts_center/view_user.jsp" />
				<portlet:param name="backURL" value="<%= currentURL %>" />
				<portlet:param name="userId" value="<%= String.valueOf(user2.getUserId()) %>" />
			</liferay-portlet:renderURL>

			<liferay-ui:search-container-column-text
				name="people"
			>
				<div class="lfr-user-portrait">
					<a href="<%= rowURL %>"><img alt="<liferay-ui:message key="avatar" />" class="avatar" src="<%= user2.getPortraitURL(themeDisplay) %>" /></a>
				</div>

				<div class="lfr-user-data">
					<div class="lfr-user-data-name">
						<a href="<%= rowURL %>"><%= HtmlUtil.escape(user2.getFullName()) %></a>
					</div>
					<div class="lfr-user-data-job-title">
						<%= HtmlUtil.escape(user2.getJobTitle()) %>
					</div>
					<div class="lfr-user-data-extra">
						<span class="lfr-user-data-email"><%= HtmlUtil.escape(user2.getEmailAddress()) %></span>
					</div>
				</div>
			</liferay-ui:search-container-column-text>

			<liferay-ui:search-container-column-jsp
				align="right"
				path="/contacts_center/user_action.jsp"
			/>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator />
	</liferay-ui:search-container>
</aui:form>