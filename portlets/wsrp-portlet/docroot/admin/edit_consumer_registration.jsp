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
String redirect = ParamUtil.getString(request, "redirect");

long wsrpConsumerId = ParamUtil.getLong(request, "wsrpConsumerId");

WSRPConsumer wsrpConsumer = WSRPConsumerLocalServiceUtil.getWSRPConsumer(wsrpConsumerId);

UnicodeProperties registrationProperties = wsrpConsumer.getRegistrationProperties();

WSRPConsumerManager wsrpConsumerManager = WSRPConsumerManagerFactory.getWSRPConsumerManager(wsrpConsumer, userToken);

ServiceDescription serviceDescription = wsrpConsumerManager.getServiceDescription();

boolean supportsInbandRegistration = false;

if (serviceDescription.isRequiresRegistration() && (wsrpConsumerManager.getRegistrationService() != null)) {
	supportsInbandRegistration = true;
}

PropertyDescription[] propertyDescriptions = new PropertyDescription[0];

if (supportsInbandRegistration) {
	propertyDescriptions = wsrpConsumerManager.getPropertyDescriptions();
}
%>

<liferay-ui:header
	backURL="<%= redirect %>"
	title='<%= (wsrpConsumer != null) ? wsrpConsumer.getName() : "new-consumer-registration" %>'
/>

<form action="<portlet:actionURL name="updateWSRPConsumerRegistration"><portlet:param name="jspPage" value="/admin/edit_consumer_registration.jsp" /><portlet:param name="redirect" value="<%= redirect %>" /></portlet:actionURL>" method="post" name="<portlet:namespace />fm" onSubmit="<portlet:namespace />saveConsumerRegistration(); return false;">
<input name="<portlet:namespace />wsrpConsumerId" type="hidden" value="<%= wsrpConsumerId %>" />

<table class="lfr-table">
<tr>
	<td>
		<liferay-ui:message key="name" />
	</td>
	<td>
		<%= wsrpConsumer.getName() %>
	</td>
</tr>
<tr>
	<td>
		<liferay-ui:message key="url" />
	</td>
	<td>
		<a href="<%= wsrpConsumer.getUrl() %>" target="_blank"><%= wsrpConsumer.getUrl() %></a>
	</td>
</tr>
<tr>
	<td colspan="2">
		<br />
	</td>
</tr>
<tr>
	<td>
		<liferay-ui:message key="registration-type" />
	</td>
	<td>
		<select id="<portlet:namespace />inbandRegistration" name="<portlet:namespace />inbandRegistration">
			<c:if test="<%= supportsInbandRegistration %>">
				<option value="true"><liferay-ui:message key="inband" /></option>
			</c:if>

			<option value="false"><liferay-ui:message key="outband" /></option>
		</select>
	</td>
</tr>
<tbody <%= supportsInbandRegistration ? "class=\"aui-helper-hidden\"" : "" %> id="<portlet:namespace />registrationHandleSettings">
	<tr>
		<td>
			<liferay-ui:message key="registration-handle" />
		</td>
		<td>
			<input class="lfr-input-text" name="<portlet:namespace />registrationHandle" type="text" />
		</td>
	</tr>
</tbody>
<tbody <%= !supportsInbandRegistration ? "class=\"aui-helper-hidden\"" : "" %> id="<portlet:namespace />registrationPropertiesSettings">
	<tr>
		<td>
			<liferay-ui:message key="registration-properties" />
		</td>
		<td>

			<%
			SearchContainer searchContainer = new SearchContainer();

			List<String> headerNames = new ArrayList<String>();

			headerNames.add("name");
			headerNames.add("value");
			headerNames.add("description");

			searchContainer.setHeaderNames(headerNames);
			searchContainer.setEmptyResultsMessage("there-are-no-registration-properties");

			List resultRows = searchContainer.getResultRows();

			for (int i = 0; i < propertyDescriptions.length; i++) {
				PropertyDescription propertyDescription = propertyDescriptions[i];

				String fullyQualifiedName = propertyDescription.getName().toString();

				String name = propertyDescription.getName().getLocalPart();

				String description = LocalizedStringUtil.getLocalizedStringValue(propertyDescription.getDescription(), StringPool.BLANK);

				description += LocalizedStringUtil.getLocalizedStringValue(propertyDescription.getHint(), StringPool.BLANK);

				ResultRow row = new ResultRow(name, name, i);

				// Name

				row.addText(name);

				// Value

				StringBuilder sb = new StringBuilder();

				sb.append("<input name=\"");
				sb.append(renderResponse.getNamespace());
				sb.append("registrationPropertyName");
				sb.append(i);
				sb.append("\" type=\"hidden\" value=\"");
				sb.append(fullyQualifiedName);
				sb.append("\" />");

				String registrationPropertyValue = GetterUtil.getString(registrationProperties.get(fullyQualifiedName));

				sb.append("<input name=\"");
				sb.append(renderResponse.getNamespace());
				sb.append("registrationPropertyValue");
				sb.append(i);
				sb.append("\" type=\"text\" value=\"");
				sb.append(registrationPropertyValue);
				sb.append("\" />");

				row.addText(sb.toString());

				// Description

				row.addText(description);

				// Add result row

				resultRows.add(row);
			}
			%>

			<liferay-ui:search-iterator searchContainer="<%= searchContainer %>" paginate="<%= false %>" />
		</td>
	</tr>
</tbody>
</table>

<br />

<input type="submit" value="<liferay-ui:message key="save" />" />

<input type="button" value="<liferay-ui:message key="cancel" />" onClick="location.href = '<%= HtmlUtil.escape(PortalUtil.escapeRedirect(redirect)) %>';" />

</form>

<aui:script>
	function <portlet:namespace />saveConsumerRegsitration() {
		submitForm(document.<portlet:namespace />fm);
	}
</aui:script>

<aui:script use="aui-base">
	A.one('#<portlet:namespace />inbandRegistration').on(
		'change',
		function(event) {
			A.one('#<portlet:namespace />registrationHandleSettings').toggle();
			A.one('#<portlet:namespace />registrationPropertiesSettings').toggle();
		}
	);
</aui:script>

<%
if (wsrpConsumer != null) {
	PortalUtil.addPortletBreadcrumbEntry(request, LanguageUtil.get(pageContext, "edit"), currentURL);
}
else {
	PortalUtil.addPortletBreadcrumbEntry(request, LanguageUtil.get(pageContext, "add-consumer-registration"), currentURL);
}
%>