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

long wsrpConsumerPortletId = ParamUtil.getLong(request, "wsrpConsumerPortletId");

WSRPConsumerPortlet wsrpConsumerPortlet = null;

try {
	wsrpConsumerPortlet = WSRPConsumerPortletLocalServiceUtil.getWSRPConsumerPortlet(wsrpConsumerPortletId);
}
catch (NoSuchConsumerPortletException nscpe) {
}

long wsrpConsumerId = BeanParamUtil.getLong(wsrpConsumerPortlet, request, "wsrpConsumerId");
String portletHandle = BeanParamUtil.getString(wsrpConsumerPortlet, request, "portletHandle");

WSRPConsumer wsrpConsumer = WSRPConsumerLocalServiceUtil.getWSRPConsumer(wsrpConsumerId);

WSRPConsumerManager wsrpConsumerManager = WSRPConsumerManagerFactory.getWSRPConsumerManager(wsrpConsumer);

ServiceDescription serviceDescription = wsrpConsumerManager.getServiceDescription();

PortletDescription[] portletDescriptions = serviceDescription.getOfferedPortlets();
%>

<liferay-ui:header
	backURL="<%= redirect %>"
	title='<%= (wsrpConsumerPortlet != null) ? wsrpConsumerPortlet.getName() : "new-portlet" %>'
/>

<portlet:actionURL name="updateWSRPConsumerPortlet" var="updateWSRPConsumerPortletURL" />

<aui:form action="<%= updateWSRPConsumerPortletURL %>" method="post" name="fm" onSubmit='<%= "event.preventDefault(); " + renderResponse.getNamespace() + "saveConsumerPortlet();" %>'>
	<aui:input name="mvcPath" type="hidden" value="/admin/edit_consumer_portlet.jsp" />
	<aui:input name="redirect" type="hidden" value="<%= redirect %>" />
	<aui:input name="wsrpConsumerPortletId" type="hidden" value="<%= wsrpConsumerPortletId %>" />
	<aui:input name="wsrpConsumerId" type="hidden" value="<%= wsrpConsumerId %>" />

	<liferay-ui:error exception="<%= WSRPConsumerPortletHandleException.class %>" message="please-enter-a-valid-remote-portlet" />
	<liferay-ui:error exception="<%= WSRPConsumerPortletNameException.class %>" message="please-enter-a-valid-name" />

	<aui:model-context bean="<%= wsrpConsumerPortlet %>" model="<%= WSRPConsumerPortlet.class %>" />

	<aui:fieldset>
		<aui:input name="name" />

		<aui:select label="remote-portlet" name="portletHandle">
			<aui:option value="" />

			<c:if test="<%= portletDescriptions != null %>">

				<%
				for (PortletDescription portletDescription : portletDescriptions) {
					try {
						WSRPConsumerPortletLocalServiceUtil.getWSRPConsumerPortlet(wsrpConsumer.getWsrpConsumerId(), portletDescription.getPortletHandle());
					}
					catch (NoSuchConsumerPortletException nscpe) {
				%>

					<aui:option label="<%= wsrpConsumerManager.getDisplayName(portletDescription) %>" selected="<%= portletHandle.equals(portletDescription.getPortletHandle()) %>" value="<%= portletDescription.getPortletHandle() %>" />

				<%
					}
				}
				%>

			</c:if>
		</aui:select>
	</aui:fieldset>

	<aui:button-row>
		<aui:button type="submit" />

		<aui:button href="<%= redirect %>" type="cancel" />
	</aui:button-row>
</aui:form>

<aui:script>
	function <portlet:namespace />saveConsumerPortlet() {
		submitForm(document.<portlet:namespace />fm);
	}

	Liferay.Util.focusFormField(document.<portlet:namespace />fm.<portlet:namespace />name);
</aui:script>

<%
PortletURL viewConsumerPortletsURL = renderResponse.createRenderURL();

viewConsumerPortletsURL.setParameter("mvcPath", "/admin/view_consumer_portlets.jsp");
viewConsumerPortletsURL.setParameter("wsrpConsumerId", String.valueOf(wsrpConsumer.getWsrpConsumerId()));

PortalUtil.addPortletBreadcrumbEntry(request, LanguageUtil.get(request, "manage-portlets"), viewConsumerPortletsURL.toString());

if (wsrpConsumerPortlet != null) {
	PortalUtil.addPortletBreadcrumbEntry(request, LanguageUtil.get(request, "edit"), currentURL);
}
else {
	PortalUtil.addPortletBreadcrumbEntry(request, LanguageUtil.get(request, "add-portlet"), currentURL);
}
%>