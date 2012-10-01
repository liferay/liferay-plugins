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

<div class="portlet-msg-alert">
	<c:choose>
		<c:when test="<%= PluginSecurityManagerUtil.isPACLActive() %>">
			<liferay-ui:message key="the-plugin-security-manager-is-active-because-one-or-more-of-the-following-plugins-requests-security-management" />
		</c:when>
		<c:otherwise>
			<liferay-ui:message key="the-plugin-security-manager-is-not-active-because-there-are-no-plugins-that-request-security-management" />
		</c:otherwise>
	</c:choose>
</div>

<liferay-portlet:actionURL name="updateConfiguration" var="configurationURL" />

<aui:form action="<%= configurationURL %>" method="post" name="fm">
	<aui:input name="redirect" type="hidden" value="<%= currentURL %>" />

	<aui:fieldset>
		<aui:input helpMessage="allow-the-plugin-security-manager-help" label="allow-the-plugin-security-manager" name="pluginSecurityManagerAllowed" type="checkbox" value="<%= PluginSecurityManagerUtil.isAllowed() %>" />

		<aui:button-row>
			<aui:button type="submit" />
		</aui:button-row>
	</aui:fieldset>
</aui:form>

<%
List<String> headerNames = new ArrayList<String>();

headerNames.add("context-path");
headerNames.add("requests-security-management");

SearchContainer searchContainer = new SearchContainer(renderRequest, null, null, SearchContainer.DEFAULT_CUR_PARAM, SearchContainer.DEFAULT_DELTA, renderResponse.createRenderURL(), headerNames, null);

List<ResultRow> resultRows = searchContainer.getResultRows();

JSONArray paclPoliciesJSONArray = PluginSecurityManagerUtil.getPACLPoliciesJSONArray();

searchContainer.setTotal(paclPoliciesJSONArray.length());

for (int i = 0; i < paclPoliciesJSONArray.length(); i++) {
	JSONObject paclPolicyJSONObject = paclPoliciesJSONArray.getJSONObject(i);

	String servletContextName = paclPolicyJSONObject.getString("servletContextName");

	ResultRow row = new ResultRow(servletContextName, servletContextName, i);

	row.addText(servletContextName);
	row.addText(LanguageUtil.get(pageContext, paclPolicyJSONObject.getBoolean("active") ? "yes" : "no"));

	resultRows.add(row);
}
%>

<liferay-ui:search-iterator searchContainer="<%= searchContainer %>" />