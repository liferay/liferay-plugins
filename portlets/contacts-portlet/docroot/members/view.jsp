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
Group group = themeDisplay.getScopeGroup();

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("jspPage", "/members/view.jsp");
%>

<c:choose>
	<c:when test="<%= group.isRegularSite() %>">

		<%
		SearchContainer searchContainer = new SearchContainer(renderRequest, null, null, SearchContainer.DEFAULT_CUR_PARAM, 20, portletURL, null, null);

		searchContainer.setDeltaConfigurable(true);

		String keywords = ParamUtil.getString(request, "keywords");
		%>

		<liferay-portlet:renderURL varImpl="searchURL">
			<portlet:param name="jspPage" value="/members/view.jsp" />
		</liferay-portlet:renderURL>

		<aui:form action="<%= searchURL %>" method="get" name="fm">
			<liferay-portlet:renderURLParams varImpl="searchURL" />
			<aui:input inlineField="<%= true %>" id="keywords" label="" name="keywords" size="30" title="search-users" type="text" />

			<aui:button type="submit" value="search" />
		</aui:form>

		<div class="members-container">

		<%
		LinkedHashMap param = new LinkedHashMap();

		param.put("usersGroups", new Long(group.getGroupId()));

		List<User> users = UserLocalServiceUtil.search(company.getCompanyId(), keywords, WorkflowConstants.STATUS_APPROVED, param, searchContainer.getStart(), searchContainer.getEnd(), new UserLastNameComparator(true));

		searchContainer.setResults(users);

		int total= UserLocalServiceUtil.searchCount(company.getCompanyId(), keywords, WorkflowConstants.STATUS_APPROVED, param);

		searchContainer.setTotal(total);

		for (User user2 : users) {
			PortletURL viewUserURL = renderResponse.createRenderURL();

			viewUserURL.setParameter("jspPage", "/members/view_user.jsp");
			viewUserURL.setParameter("backURL", currentURL);
			viewUserURL.setParameter("userId", String.valueOf(user2.getUserId()));
		%>

			<div class="lfr-members-grid-item">
				<div class="lfr-members-thumb">
					<a href="<%= viewUserURL.toString() %>"><img alt="<%= HtmlUtil.escape(user2.getFullName()) %>" src="<%= user2.getPortraitURL(themeDisplay) %>" style="" /></a>
				</div>

				<div class="lfr-user-data-info">
					<div class="lfr-user-data-name">
						<a href="<%= viewUserURL.toString() %>"><%= HtmlUtil.escape(user2.getFullName()) %></a>
					</div>

					<div class="lfr-user-data-job-title">
						<%= HtmlUtil.escape(user2.getJobTitle()) %>
					</div>

					<div class="lfr-user-data-extra">
						<%= HtmlUtil.escape(user2.getEmailAddress()) %>
					</div>
				</div>
			</div>

		<%
		}
		%>

		</div>

		<liferay-ui:search-paginator searchContainer="<%= searchContainer %>" />
	</c:when>
	<c:otherwise>
		<div class="portlet-msg-info">
			<liferay-ui:message key="this-application-will-only-function-when-placed-on-a-site-page" />
		</div>
	</c:otherwise>
</c:choose>