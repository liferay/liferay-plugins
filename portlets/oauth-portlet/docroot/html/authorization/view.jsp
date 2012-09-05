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

<%@ page import="com.liferay.portal.kernel.servlet.SessionMessages" %><%@
page import="com.liferay.portal.oauth.model.ApplicationUser" %><%@
page import="com.liferay.portlet.oauth.search.OAuthApplicationDisplayTerms" %><%@
page import="com.liferay.portlet.oauth.search.OAuthApplicationSearchTerms" %><%@
page import="com.liferay.portlet.oauth.search.OAuthApplicationUserSearch" %>

<%@ include file="/html/init.jsp" %>

<%
int myAppsCount = 0;
long userId = themeDisplay.getUserId();

String noResultsMsgKey = "no-authorized-apps-were-found";
String toolbarItem = ParamUtil.getString(request, OAuthConstants.TOOLBAR_ITEM, OAuthConstants.TOOLBAR_ITEM_VIEW_ALL);

if (!adminUser) {
	myAppsCount = ApplicationLocalServiceUtil.getApplicationsByUserIdCount(userId);

	if (OAuthConstants.TOOLBAR_ITEM_MY_APPS.equals(toolbarItem)) {
		noResultsMsgKey = "no-authorized-my-apps-were-found";
	}
}
%>

<c:if test="<%= SessionMessages.contains(request, OAuthConstants.WEB_APP_REQ_PROCESSED) %>">
	<liferay-ui:success key="<%= OAuthConstants.WEB_APP_REQ_PROCESSED %>" message="your-request-completed-successfully"></liferay-ui:success>
</c:if>

<portlet:actionURL var="searchActionURL">
</portlet:actionURL>

<aui:form action="<%= searchActionURL %>" name="fm">

<liferay-util:include page="/html/authorization/toolbar.jsp" servletContext="<%= application %>">
		<liferay-util:param name="myAppsCount" value="<%= Integer.toString(myAppsCount) %>" />
</liferay-util:include>

<liferay-ui:search-container
	delta="5"
	emptyResultsMessage="<%= noResultsMsgKey %>"
	searchContainer="<%= new OAuthApplicationUserSearch(renderRequest, currentURLObj) %>"
	>

	<liferay-ui:search-form
				page="/html/authorization/search.jsp"
				servletContext="<%= application %>"
			/>

	<%
		String name = ((OAuthApplicationSearchTerms)searchContainer.getSearchTerms()).getName();

		List<ApplicationUser> oAuthApps = null;
		int oAuthAppsCnt = 0;

		boolean renderRevokeAction = false;

		if (adminUser) {
			renderRevokeAction = true;

			oAuthApps = ApplicationUserLocalServiceUtil.getApplicationUsers(searchContainer.getStart(), searchContainer.getEnd());
			oAuthAppsCnt = ApplicationUserLocalServiceUtil.getApplicationUsersCount();
		} else {
			if (OAuthConstants.TOOLBAR_ITEM_MY_APPS.equals(toolbarItem)) {
				oAuthApps = ApplicationUserLocalServiceUtil.getAuthorizedApplicationUsersByOwnerId(userId, true, searchContainer.getStart(), searchContainer.getEnd(), searchContainer.getOrderByComparator());
				oAuthAppsCnt = ApplicationUserLocalServiceUtil.getAuthorizedApplicationUsersByOwnerIdCount(userId, true);
			}
			else {
				renderRevokeAction = true;

				oAuthApps = ApplicationUserLocalServiceUtil.getAuthorizedApplicationUsersByUserId(userId, true, searchContainer.getStart(), searchContainer.getEnd(), null);
				oAuthAppsCnt = ApplicationUserLocalServiceUtil.getAuthorizedApplicationUsersByUserIdCount(userId, true);
			}
		}
	%>

	<liferay-ui:search-container-results
	results="<%= oAuthApps %>"
	total="<%= oAuthAppsCnt %>"
	 />

	<liferay-ui:search-container-row
		className="com.liferay.portal.oauth.model.ApplicationUser"
		keyProperty="oaauId"
		modelVar="appAuth">

		<%
		Application app = ApplicationLocalServiceUtil.getApplication(appAuth.getApplicationId());
		%>

		<liferay-ui:search-container-column-text
			name="oaauid"
			orderable="<%= true %>"
			value="<%= String.valueOf(appAuth.getOaauId()) %>"
		/>
		<liferay-ui:search-container-column-text
			name="application-id-short"
			orderable="<%= true %>"
			value="<%= String.valueOf(appAuth.getApplicationId()) %>"
		/>
		<liferay-ui:search-container-column-text
			name="name"
			value="<%= app.getName() %>"
		/>
		<liferay-ui:search-container-column-text
			name="access-token"
			value="<%= appAuth.getAccessToken() %>"
		/>
		<liferay-ui:search-container-column-text
			name="authorized"
			value="<%= String.valueOf(appAuth.getAuthorized()) %>"
		/>
		<liferay-ui:search-container-column-text
			name="access-level"
		>
			<liferay-ui:message key='<%= OAuthConstants.WEB_APP_LANG_KEY_ACCESS_TYPE_SHORT.replace("{0}", Integer.toString(app.getAccessLevel())) %>' />
		</liferay-ui:search-container-column-text>

		<c:if test="<%= renderRevokeAction %>">
			<liferay-portlet:actionURL name="deleteOAuthAppUsr" var="revokeURL">
				<portlet:param name="<%=OAuthConstants.APPLICATION_ID%>" value="<%= String.valueOf(appAuth.getApplicationId()) %>" />
			</liferay-portlet:actionURL>

			<liferay-ui:search-container-column-text href="<%= revokeURL %>">
				<liferay-ui:message key="revoke" />
			</liferay-ui:search-container-column-text>
		</c:if>
	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator />

</liferay-ui:search-container>
</aui:form>