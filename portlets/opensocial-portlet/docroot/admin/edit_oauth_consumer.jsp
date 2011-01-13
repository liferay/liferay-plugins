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
String redirect = ParamUtil.getString(request, "redirect");

long gadgetId = ParamUtil.getLong(request, "gadgetId");

Gadget gadget = GadgetLocalServiceUtil.getGadget(gadgetId);

String serviceName = ParamUtil.getString(request, "serviceName");

OAuthConsumer oAuthConsumer = null;

try {
	oAuthConsumer = OAuthConsumerLocalServiceUtil.getOAuthConsumer(gadgetId, serviceName);
}
catch (NoSuchOAuthConsumerException nsce) {
}

long oAuthConsumerId = BeanParamUtil.getLong(oAuthConsumer, request, "oauthConsumerId");

String keyType = BeanParamUtil.getString(oAuthConsumer, request, "keyType");
%>

<liferay-ui:header
	backURL="<%= redirect %>"
	title="<%= gadget.getName() %>"
/>

<form action="<portlet:actionURL name="updateOAuthConsumer"><portlet:param name="jspPage" value="/admin/edit_oauth_consumer.jsp" /><portlet:param name="redirect" value="<%= redirect %>" /></portlet:actionURL>" method="post" name="<portlet:namespace />fm" onSubmit="<portlet:namespace />saveOAuthConsumer(); return false;">
<input name="<portlet:namespace />gadgetId" type="hidden" value="<%= gadgetId %>" />
<input name="<portlet:namespace />oAuthConsumerId" type="hidden" value="<%= oAuthConsumerId %>" />

<table class="lfr-table">
<tr>
	<td>
		<liferay-ui:message key="service-name" />
	</td>
	<td>
		<%= serviceName %>

		<input name="<portlet:namespace />serviceName" type="hidden" value="<%= HtmlUtil.escape(serviceName) %>" />
	</td>
</tr>
<tr>
	<td>
		<liferay-ui:message key="consumer-key" />
	</td>
	<td>
		<liferay-ui:input-field model="<%= OAuthConsumer.class %>" bean="<%= oAuthConsumer %>" field="consumerKey" />
	</td>
</tr>
<tr>
	<td>
		<liferay-ui:message key="consumer-secret" />
	</td>
	<td>
		<liferay-ui:input-field model="<%= OAuthConsumer.class %>" bean="<%= oAuthConsumer %>" field="consumerSecret" />
	</td>
</tr>
<tr>
	<td>
		<liferay-ui:message key="key-type" />
	</td>
	<td>
		<select name="<portlet:namespace />keyType">
			<option <%= keyType.equals(OAuthConsumerConstants.KEY_TYPE_HMAC_SYMMETRIC) ? "selected" : StringPool.BLANK %> value="<%= OAuthConsumerConstants.KEY_TYPE_HMAC_SYMMETRIC %>"><%= OAuthConsumerConstants.KEY_TYPE_HMAC_SYMMETRIC.toString() %></option>
			<option <%= keyType.equals(OAuthConsumerConstants.KEY_TYPE_PLAINTEXT) ? "selected" : StringPool.BLANK %> value="<%= OAuthConsumerConstants.KEY_TYPE_PLAINTEXT %>"><%= OAuthConsumerConstants.KEY_TYPE_PLAINTEXT.toString() %></option>
			<option <%= keyType.equals(OAuthConsumerConstants.KEY_TYPE_RSA_PRIVATE) ? "selected" : StringPool.BLANK %> value="<%= OAuthConsumerConstants.KEY_TYPE_RSA_PRIVATE %>"><%= OAuthConsumerConstants.KEY_TYPE_RSA_PRIVATE.toString() %></option>
		</select>
	</td>
</tr>
</table>

<br />

<input type="submit" value="<liferay-ui:message key="save" />" />

<input type="button" value="<liferay-ui:message key="cancel" />" onClick="location.href = '<%= HtmlUtil.escape(PortalUtil.escapeRedirect(redirect)) %>';" />

</form>

<aui:script>
	function <portlet:namespace />saveOAuthConsumer() {
		submitForm(document.<portlet:namespace />fm);
	}

	Liferay.Util.focusFormField(document.<portlet:namespace />fm.<portlet:namespace />name);
</aui:script>

<%
PortletURL viewOAuthConsumersURL = renderResponse.createRenderURL();

viewOAuthConsumersURL.setParameter("jspPage", "/admin/view_oauth_consumers.jsp");
viewOAuthConsumersURL.setParameter("gadgetId", String.valueOf(gadgetId));

PortalUtil.addPortletBreadcrumbEntry(request, LanguageUtil.get(pageContext, "manage-oauth"), viewOAuthConsumersURL.toString());

if (oAuthConsumer != null) {
	PortalUtil.addPortletBreadcrumbEntry(request, LanguageUtil.get(pageContext, "edit-service"), currentURL);
}
else {
	PortalUtil.addPortletBreadcrumbEntry(request, LanguageUtil.get(pageContext, "add-service"), currentURL);
}
%>