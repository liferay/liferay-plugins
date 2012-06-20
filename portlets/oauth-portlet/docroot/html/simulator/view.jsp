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

<%@ page import="com.liferay.portal.oauth.service.OAuthApplicationLocalServiceUtil" %>
<%@ page import="com.liferay.portlet.oauth.search.OAuthApplicationSearch" %>
<%@ page import="com.liferay.portlet.oauth.search.OAuthApplicationSearchTerms" %>

<%@ page import="java.util.List" %>

<%@ include file="/html/init.jsp" %>

<%
String token = (String)request.getAttribute("oauth-simulator-token");
String secret = (String)request.getAttribute("oauth-simulator-secret");
String oauthURL = (String)request.getAttribute("oauth-simulator-url");
String applicationId = (String)request.getAttribute("applicationId");

boolean verifyStep = null != oauthURL && !"".equals(oauthURL);
%>

<liferay-ui:error-marker key="errorSection" value="details" />


	<c:choose>
	<c:when test="<%= verifyStep %>">
		<liferay-ui:panel title="Authorize Application?">
			<span>You decided to authorize this application to access your resources? To complete process click the
			<aui:a href="<%= oauthURL %>">LINK</aui:a>.</span>
		</liferay-ui:panel>

		<liferay-portlet:actionURL name="verifyOAuthorization" var="verifyOAuthorizationURL">
				<portlet:param name="jspPage" value="/html/simulator/view.jsp" />
		</liferay-portlet:actionURL>

		<aui:form action="<%= verifyOAuthorizationURL %>" method="post">
			<aui:fieldset>
				<aui:input id="applicationId" name="applicationId" type="hidden" value="<%= applicationId %>" />
				<aui:input id="oauth-simulator-token" name="oauth-simulator-token" type="hidden" value="<%= token %>" />
				<aui:input id="oauth-simulator-secret" name="oauth-simulator-secret" type="hidden" value="<%= secret %>" />
				<aui:input id="oauth-simulator-verifier" label="Verifier" name="oauth-simulator-verifier" />
				<aui:button-row>
				<aui:button type="submit" value="verify" />
				</aui:button-row>
			</aui:fieldset>
		</aui:form>
	</c:when>
	<c:otherwise>
		<liferay-ui:search-container
			delta="5"
			searchContainer="<%= new OAuthApplicationSearch(renderRequest, currentURLObj) %>" >

	<%
		String name = ((OAuthApplicationSearchTerms)searchContainer.getSearchTerms()).getName();

		List<OAuthApplication> oAuthApps = null;
		int oAuthAppsCnt = 0;

		oAuthApps = OAuthApplicationLocalServiceUtil.getApplicationsByCN(themeDisplay.getCompanyId(), "%");;
		oAuthAppsCnt = OAuthApplicationLocalServiceUtil.getApplicationsCountByCN(themeDisplay.getCompanyId(), "%");
	%>

	<liferay-ui:search-container-results
	results="<%= oAuthApps %>"
	total="<%= oAuthAppsCnt %>"
	 />

	<liferay-ui:search-container-row
		className="com.liferay.portal.oauth.model.OAuthApplication"
		keyProperty="applicationId"
		modelVar="app">

		<liferay-ui:search-container-column-text
					name="name"
					orderable="<%= true %>"
					value="<%= app.getName() %>"
				/>
		<liferay-ui:search-container-column-text
					name="website"
					orderable="<%= false %>"
				/>
		<liferay-ui:search-container-column-text
					name="access-level"
					orderable="<%= false %>"
				>
				<liferay-ui:message key='<%= OAuthConstants.WEB_APP_LANG_KEY_ACCESS_TYPE_SHORT.replace("{0}", Integer.toString(app.getAccessLevel())) %>' />
		</liferay-ui:search-container-column-text>

		<liferay-portlet:actionURL name="addOAuthorization" var="addAuthorizationURL">
			<portlet:param name="jspPage" value="/html/simulator/view.jsp" />
			<portlet:param name="applicationId" value="<%= String.valueOf(app.getApplicationId()) %>" />
		</liferay-portlet:actionURL>

		<liferay-ui:search-container-column-text href="<%= addAuthorizationURL %>" value="Authorize" />
	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator />

</liferay-ui:search-container>

	</c:otherwise>
</c:choose>