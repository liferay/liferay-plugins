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
String redirect = ParamUtil.getString(request, "redirect");

long gadgetId = ParamUtil.getLong(request, "gadgetId");

String gadgetKey = StringPool.BLANK;

Gadget gadget = null;

if (gadgetId > 0) {
	gadget = GadgetLocalServiceUtil.fetchGadget(gadgetId);

	gadgetKey = GadgetConstants.toPublishedGadgetKey(gadgetId);
}
else {
	redirect = StringPool.BLANK;

	gadget = ShindigUtil.getGadget(portletPreferences);

	String namespace = ShindigUtil.getPortletResourceNamespace(renderRequest, themeDisplay);

	long moduleId = ShindigUtil.getModuleId(namespace);

	gadgetKey = GadgetConstants.toAdhocGadgetKey(moduleId);
}

Map<String, OAuthService> oAuthServices = null;

try {
	oAuthServices = ShindigUtil.getOAuthServices(gadget.getUrl());
}
catch (Exception e) {
}

int oAuthServiceCount = 0;
%>

<c:if test="<%= gadgetId > 0 %>">
	<liferay-ui:header
		backURL="<%= redirect %>"
		title="<%= gadget.getName() %>"
	/>
</c:if>

<portlet:actionURL name="updateOAuthConsumers" var="updateOAuthConsumersURL" />

<aui:form action="<%= updateOAuthConsumersURL %>" method="post" name="fm" onSubmit='<%= "event.preventDefault(); " + renderResponse.getNamespace() + "saveOAuthConsumers();" %>'>
	<aui:input name="mvcPath" type="hidden" value="/admin/edit_oauth_consumers.jsp" />
	<aui:input name="redirect" type="hidden" value="<%= redirect %>" />
	<aui:input name="gadgetKey" type="hidden" value="<%= gadgetKey %>" />

	<%
	for (Map.Entry<String, OAuthService> entry : oAuthServices.entrySet()) {
		OAuthService oAuthService = entry.getValue();

		String serviceName = oAuthService.getName();

		long oAuthConsumerId = 0;

		OAuthConsumer oAuthConsumer = OAuthConsumerLocalServiceUtil.fetchOAuthConsumer(gadgetKey, serviceName);

		if (oAuthConsumer != null) {
			oAuthConsumerId = oAuthConsumer.getOAuthConsumerId();
		}
	%>

		<h3><%= serviceName %></h3>

		<aui:input name="gadgetId" type="hidden" value="<%= gadgetId %>" />
		<aui:input name="oAuthConsumerId" type="hidden" value="<%= oAuthConsumerId %>" />
		<aui:input name="serviceName" type="hidden" value="<%= serviceName %>" />

		<aui:model-context bean="<%= oAuthConsumer %>" model="<%= OAuthConsumer.class %>" />

		<aui:fieldset>
			<aui:select id='<%= "keyType" + oAuthServiceCount %>' name="keyType">
				<aui:option label="<%= OAuthConsumerConstants.KEY_TYPE_HMAC_SYMMETRIC.toString() %>" value="<%= OAuthConsumerConstants.KEY_TYPE_HMAC_SYMMETRIC %>" />
				<aui:option label="<%= OAuthConsumerConstants.KEY_TYPE_PLAINTEXT.toString() %>" value="<%= OAuthConsumerConstants.KEY_TYPE_PLAINTEXT %>" />
				<aui:option label="<%= OAuthConsumerConstants.KEY_TYPE_RSA_PRIVATE.toString() %>" value="<%= OAuthConsumerConstants.KEY_TYPE_RSA_PRIVATE %>" />
			</aui:select>

			<aui:input name="consumerKey" />

			<div id='<%= "consumerSecretField" + oAuthServiceCount %>' >
				<aui:input name="consumerSecret" />
			</div>
		</aui:fieldset>

	<%
	oAuthServiceCount++;
	}
	%>

	<aui:button-row>
		<aui:button type="submit" />

		<c:if test="<%= gadgetId > 0 %>">
			<aui:button href="<%= redirect %>" type="cancel" />
		</c:if>
	</aui:button-row>
</aui:form>

<aui:script>
	function <portlet:namespace />saveOAuthConsumers() {
		submitForm(document.<portlet:namespace />fm);
	}

	Liferay.Util.focusFormField(document.<portlet:namespace />fm.<portlet:namespace />name);
</aui:script>

<aui:script use="aui-base">
	function <portlet:namespace />renderConsumerSecretRow(rowCount) {
		var consumerSecretField = A.one('#consumerSecretField' + rowCount);

		A.one('#<portlet:namespace />keyType' + rowCount).get('options').each(
			function() {
				if (this.get('selected') && (this.get('value') == '<%= OAuthConsumerConstants.KEY_TYPE_RSA_PRIVATE %>')) {
					consumerSecretField.hide();
				}
				else {
					consumerSecretField.show();
				}
			}
		)
	};

	<%
	for (int rowCount = 0; rowCount < oAuthServiceCount; rowCount++) {
	%>

		A.one('#<portlet:namespace />keyType<%= rowCount %>').on(
			'change',
			function() {
				<portlet:namespace />renderConsumerSecretRow(<%= rowCount %>);
			}
		);

		<portlet:namespace />renderConsumerSecretRow(<%= rowCount %>);

	<%
	}
	%>

</aui:script>

<%
PortalUtil.addPortletBreadcrumbEntry(request, LanguageUtil.get(pageContext, "manage-oauth"), currentURL);
%>