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

WSRPConsumer wsrpConsumer = null;

try {
	wsrpConsumer = WSRPConsumerLocalServiceUtil.getWSRPConsumer(wsrpConsumerId);
}
catch (NoSuchConsumerException nsce) {
}
%>

<liferay-ui:header
	backURL="<%= redirect %>"
	title='<%= (wsrpConsumer != null) ? wsrpConsumer.getName() : "new-consumer" %>'
/>

<form action="<portlet:actionURL name="updateWSRPConsumer"><portlet:param name="mvcPath" value="/admin/edit_consumer.jsp" /><portlet:param name="redirect" value="<%= redirect %>" /></portlet:actionURL>" method="post" name="<portlet:namespace />fm" onSubmit="<portlet:namespace />saveConsumer(); return false;">
<input name="<portlet:namespace />wsrpConsumerId" type="hidden" value="<%= wsrpConsumerId %>" />

<liferay-ui:error exception="<%= WSRPConsumerNameException.class %>" message="please-enter-a-valid-name" />
<liferay-ui:error exception="<%= WSRPConsumerWSDLException.class %>" message="url-does-not-point-to-a-valid-wsrp-producer" />

<table class="lfr-table">
<tr>
	<td>
		<liferay-ui:message key="name" />
	</td>
	<td>
		<liferay-ui:input-field bean="<%= wsrpConsumer %>" field="name" model="<%= WSRPConsumer.class %>" />
	</td>
</tr>
<tr>
	<td>
		<liferay-ui:message key="url" />
	</td>
	<td>
		<liferay-ui:input-field bean="<%= wsrpConsumer %>" field="url" model="<%= WSRPConsumer.class %>" />
	</td>
</tr>
<tr>
	<td>
		<liferay-ui:message key="forward-cookies" />
	</td>
	<td>
		<liferay-ui:input-field bean="<%= wsrpConsumer %>" field="forwardCookies" model="<%= WSRPConsumer.class %>" />
	</td>
</tr>
<tr>
	<td>
		<liferay-ui:message key="forward-headers" />
	</td>
	<td>
		<liferay-ui:input-field bean="<%= wsrpConsumer %>" field="forwardHeaders" model="<%= WSRPConsumer.class %>" />
	</td>
</tr>
<tr>
	<td>
		<liferay-ui:message key="markup-character-sets" /><liferay-ui:icon-help message="markup-character-sets-help" />

	</td>
	<td>
		<liferay-ui:input-field bean="<%= wsrpConsumer %>" field="markupCharacterSets" model="<%= WSRPConsumer.class %>" />
	</td>
</tr>
</table>

<br />

<input type="submit" value="<liferay-ui:message key="save" />" />

<input type="button" value="<liferay-ui:message key="cancel" />" onClick="location.href = '<%= HtmlUtil.escape(PortalUtil.escapeRedirect(redirect)) %>';" />

</form>

<aui:script>
	function <portlet:namespace />saveConsumer() {
		submitForm(document.<portlet:namespace />fm);
	}

	Liferay.Util.focusFormField(document.<portlet:namespace />fm.<portlet:namespace />name);
</aui:script>

<%
if (wsrpConsumer != null) {
	PortalUtil.addPortletBreadcrumbEntry(request, LanguageUtil.get(pageContext, "edit"), currentURL);
}
else {
	PortalUtil.addPortletBreadcrumbEntry(request, LanguageUtil.get(pageContext, "add-consumer"), currentURL);
}
%>