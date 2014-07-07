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
String tabs2 = ParamUtil.getString(request, "tabs2", "gadget");

String url = PrefsParamUtil.getString(portletPreferences, request, "url", StringPool.BLANK);

Map<String, UserPref> userPrefs = (Map<String, UserPref>)renderRequest.getAttribute(WebKeys.USER_PREFS);

Map<String, OAuthService> oAuthServices = (Map<String, OAuthService>)renderRequest.getAttribute(WebKeys.OAUTH_SERVICES);
%>

<liferay-portlet:actionURL portletConfiguration="true" var="configurationActionURL" />

<aui:form action="<%= configurationActionURL %>" method="post" name="fm">
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />
	<aui:input name="tabs2" type="hidden" value="<%= tabs2 %>" />

	<liferay-portlet:renderURL portletConfiguration="true" var="configurationRenderURL">
		<portlet:param name="tabs2" value="<%= tabs2 %>" />
	</liferay-portlet:renderURL>

	<aui:input name="redirect" type="hidden" value="<%= configurationRenderURL %>" />

	<%
	String tabs2Names = "gadget";
	%>

	<c:if test="<%= oAuthServices != null %>">

		<%
		tabs2Names = tabs2Names.concat(",manage-oauth");
		%>

	</c:if>

	<c:if test="<%= userPrefs != null %>">

		<%
		tabs2Names = tabs2Names.concat(",preferences");
		%>

	</c:if>

	<c:if test='<%= !tabs2Names.equals("gadget") %>'>
		<liferay-ui:tabs
			names="<%= tabs2Names %>"
			param="tabs2"
			url="<%= configurationRenderURL %>"
		/>
	</c:if>

	<liferay-ui:error exception="<%= GadgetURLException.class %>" message="url-does-not-point-to-a-valid-gadget" />

	<c:choose>
		<c:when test='<%= tabs2.equals("gadget") || tabs2Names.equals("gadget") %>'>
			<aui:fieldset>
				<aui:input label="url" name="preferences--url--" type="text" value="<%= url %>" wrapperCssClass="lfr-input-text-container" />
			</aui:fieldset>

			<aui:button-row>
				<aui:button type="submit" />
			</aui:button-row>
		</c:when>
		<c:when test='<%= tabs2.equals("manage-oauth") %>'>
			<liferay-util:include page="/admin/edit_oauth_consumers.jsp" servletContext="<%= application %>" />
		</c:when>
		<c:when test='<%= tabs2.equals("preferences") %>'>
			<liferay-util:include page="/gadget/configuration.jsp" servletContext="<%= application %>" />
		</c:when>
	</c:choose>
</aui:form>