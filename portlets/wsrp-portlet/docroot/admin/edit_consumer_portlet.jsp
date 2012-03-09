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

WSRPConsumerManager wsrpConsumerManager = WSRPConsumerManagerFactory.getWSRPConsumerManager(wsrpConsumer, userToken);

ServiceDescription serviceDescription = wsrpConsumerManager.getServiceDescription();

PortletDescription[] portletDescriptions = serviceDescription.getOfferedPortlets();
%>

<liferay-ui:header
	backURL="<%= redirect %>"
	title='<%= (wsrpConsumerPortlet != null) ? wsrpConsumerPortlet.getName() : "new-portlet" %>'
/>

<form action="<portlet:actionURL name="updateWSRPConsumerPortlet"><portlet:param name="mvcPath" value="/admin/edit_consumer_portlet.jsp" /><portlet:param name="redirect" value="<%= redirect %>" /></portlet:actionURL>" method="post" name="<portlet:namespace />fm" onSubmit="<portlet:namespace />saveConsumerPortlet(); return false;">
<input name="<portlet:namespace />wsrpConsumerPortletId" type="hidden" value="<%= wsrpConsumerPortletId %>" />
<input name="<portlet:namespace />wsrpConsumerId" type="hidden" value="<%= wsrpConsumerId %>" />

<liferay-ui:error exception="<%= WSRPConsumerPortletHandleException.class %>" message="please-enter-a-valid-remote-portlet" />
<liferay-ui:error exception="<%= WSRPConsumerPortletNameException.class %>" message="please-enter-a-valid-name" />

<table class="lfr-table">
<tr>
	<td>
		<liferay-ui:message key="name" />
	</td>
	<td>
		<liferay-ui:input-field bean="<%= wsrpConsumerPortlet %>" field="name" model="<%= WSRPConsumerPortlet.class %>" />
	</td>
</tr>
<tr>
	<td>
		<liferay-ui:message key="remote-portlet" />
	</td>
	<td>
		<select name="<portlet:namespace />portletHandle">
			<option value=""></option>

			<c:if test="<%= portletDescriptions != null %>">

				<%
				for (PortletDescription portletDescription : portletDescriptions) {
					try {
						WSRPConsumerPortletLocalServiceUtil.getWSRPConsumerPortlet(wsrpConsumer.getWsrpConsumerId(), portletDescription.getPortletHandle());
					}
					catch (NoSuchConsumerPortletException nscpe) {
				%>

						<option <%= portletHandle.equals(portletDescription.getPortletHandle()) ? "selected" : "" %> value="<%= portletDescription.getPortletHandle() %>"><%= wsrpConsumerManager.getDisplayName(portletDescription) %></option>

				<%
					}
				}
				%>

			</c:if>
		</select>
	</td>
</tr>
</table>

<br />

<input type="submit" value="<liferay-ui:message key="save" />" />

<input type="button" value="<liferay-ui:message key="cancel" />" onClick="location.href = '<%= HtmlUtil.escape(PortalUtil.escapeRedirect(redirect)) %>';" />

</form>

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

PortalUtil.addPortletBreadcrumbEntry(request, LanguageUtil.get(pageContext, "manage-portlets"), viewConsumerPortletsURL.toString());

if (wsrpConsumerPortlet != null) {
	PortalUtil.addPortletBreadcrumbEntry(request, LanguageUtil.get(pageContext, "edit"), currentURL);
}
else {
	PortalUtil.addPortletBreadcrumbEntry(request, LanguageUtil.get(pageContext, "add-portlet"), currentURL);
}
%>