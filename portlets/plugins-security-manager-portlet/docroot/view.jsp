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

<div class="portlet-msg-alert">
	<c:choose>
		<c:when test="<%= !PluginsSecurityManagerUtil.isAllowed() %>">
			<liferay-ui:message key="the-plugins-security-manager-is-not-active" />
		</c:when>
		<c:when test="<%= PluginsSecurityManagerUtil.isPACLActive() %>">
			<liferay-ui:message key="the-plugins-security-manager-is-active-because-one-or-more-of-the-following-plugins-requests-security-management" />
		</c:when>
		<c:otherwise>
			<liferay-ui:message key="the-plugins-security-manager-is-not-active-because-there-are-no-plugins-that-request-security-management" />
		</c:otherwise>
	</c:choose>
</div>

<liferay-portlet:actionURL name="updateConfiguration" var="configurationURL" />

<aui:form action="<%= configurationURL %>" method="post" name="fm">
	<aui:input name="redirect" type="hidden" value="<%= currentURL %>" />

	<aui:fieldset>
		<aui:input helpMessage="enable-the-plugins-security-manager-help" label="enable-the-plugins-security-manager" name="pluginsSecurityManagerAllowed" type="checkbox" value="<%= PluginsSecurityManagerUtil.isAllowed() %>" />

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

List<JSONObject> paclPoliciesJSONObjects = PluginsSecurityManagerUtil.getPACLPoliciesJSONObjects();

int total = paclPoliciesJSONObjects.size();

searchContainer.setTotal(total);

List<JSONObject> results = ListUtil.subList(paclPoliciesJSONObjects, searchContainer.getStart(), searchContainer.getEnd());

searchContainer.setResults(results);

for (int i = 0; i < results.size(); i++) {
	JSONObject paclPolicyJSONObject = results.get(i);

	String servletContextName = paclPolicyJSONObject.getString("servletContextName");

	ResultRow row = new ResultRow(servletContextName, servletContextName, i);

	row.addText(servletContextName);
	row.addText(LanguageUtil.get(pageContext, paclPolicyJSONObject.getBoolean("active") ? "yes" : "no"));

	resultRows.add(row);
}
%>

<liferay-ui:search-iterator searchContainer="<%= searchContainer %>" />