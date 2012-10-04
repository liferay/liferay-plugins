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

<%@ include file="/html/init.jsp" %>

<c:if test="<%= SessionMessages.contains(request, OAuthConstants.REQUEST_PROCESSED) %>">
	<liferay-ui:success key="<%= OAuthConstants.REQUEST_PROCESSED %>" message="your-request-completed-successfully"></liferay-ui:success>
</c:if>

<portlet:actionURL var="searchActionURL">
</portlet:actionURL>

<aui:form action="<%= searchActionURL %>" name="fm">

<%
String toolbarItem = ParamUtil.getString(request, "toolbarItem", "view-all");
String replaceParm0 = "{0}";
%>

<liferay-util:include page="/html/admin/toolbar.jsp" servletContext="<%= application %>">
		<liferay-util:param name="toolbarItem" value="view-all" />
</liferay-util:include>

<liferay-ui:search-container delta="5" searchContainer="<%= new OAuthApplicationSearch(renderRequest, currentURLObj) %>">

	<liferay-ui:search-form page="/html/admin/search.jsp" servletContext="<%= application %>"
			/>

	<%
		String name = ((OAuthApplicationSearchTerms)searchContainer.getSearchTerms()).getName();

		List<Application> oAuthApps = null;
		int oAuthAppsCnt = 0;

		if (adminUser) {
			oAuthApps = ApplicationLocalServiceUtil.getApplications(themeDisplay.getCompanyId(), name, searchContainer.getStart(), searchContainer.getEnd(), searchContainer.getOrderByComparator());
			oAuthAppsCnt = ApplicationLocalServiceUtil.getApplicationsCountByCN(themeDisplay.getCompanyId(), name);
		} else {
			oAuthApps = ApplicationLocalServiceUtil.getApplicationsByON(themeDisplay.getUserId(), name, searchContainer.getStart(), searchContainer.getEnd(), searchContainer.getOrderByComparator());
			oAuthAppsCnt = ApplicationLocalServiceUtil.getApplicationsCountByON(themeDisplay.getUserId(), name);
		}
	%>

	<liferay-ui:search-container-results
	results="<%= oAuthApps %>"
	total="<%= oAuthAppsCnt %>"
	 />

	<liferay-ui:search-container-row
		className="com.liferay.portal.oauth.model.Application"
		keyProperty="applicationId"
		modelVar="app"
		>

		<%
		int authorizationsCount = ApplicationUserLocalServiceUtil.getApplicationUsersCount(app.getApplicationId());
		%>

		<liferay-ui:search-container-column-text
					name="id"
					orderable="<%= true %>"
					value="<%= String.valueOf(app.getApplicationId()) %>"
				/>
		<liferay-ui:search-container-column-text
					name="name"
					orderable="<%= true %>"
					value="<%= app.getName() %>"
				/>
		<liferay-ui:search-container-column-text
					name="website"
				/>
		<liferay-ui:search-container-column-text
					name="access-type"
				>
				<liferay-ui:message key="<%= OAuthConstants.ACCESS_TYPE_SHORT.replace(replaceParm0, String.valueOf(app.getAccessLevel())) %>" />
		</liferay-ui:search-container-column-text>
		<liferay-ui:search-container-column-text
					name="authorizations-count-short"
					value="<%= String.valueOf(authorizationsCount) %>"
				/>
		<liferay-ui:search-container-column-jsp
					align="right"
					path="/html/admin/actions.jsp"
				/>
	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator />

</liferay-ui:search-container>
</aui:form>