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
long wsrpConsumerId = ParamUtil.getLong(request, "wsrpConsumerId");

WSRPConsumer wsrpConsumer = WSRPConsumerLocalServiceUtil.getWSRPConsumer(wsrpConsumerId);

WSRPConsumerManager wsrpConsumerManager = WSRPConsumerManagerFactory.getWSRPConsumerManager(wsrpConsumer);

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("mvcPath", "/admin/view_consumer_portlets.jsp");
portletURL.setParameter("wsrpConsumerId", String.valueOf(wsrpConsumerId));
%>

<form>

<liferay-ui:search-container
	emptyResultsMessage="there-are-no-portlets"
	headerNames="name,remote-portlet"
	iteratorURL="<%= portletURL %>"
>
	<liferay-ui:search-container-results
		results="<%= WSRPConsumerPortletLocalServiceUtil.getWSRPConsumerPortlets(wsrpConsumerId, searchContainer.getStart(), searchContainer.getEnd()) %>"
		total="<%= WSRPConsumerPortletLocalServiceUtil.getWSRPConsumerPortletsCount(wsrpConsumerId) %>"
	/>

	<liferay-ui:search-container-row
		className="com.liferay.wsrp.model.WSRPConsumerPortlet"
		keyProperty="wsrpConsumerPortletId"
		modelVar="wsrpConsumerPortlet"
	>
		<liferay-ui:search-container-column-text
			property="name"
		/>

		<liferay-ui:search-container-column-text
			buffer="buffer"
			name="remote-portlet"
		>

			<%
			PortletDescription portletDescription = wsrpConsumerManager.getPortletDescription(wsrpConsumerPortlet.getPortletHandle());

			if (portletDescription != null) {
				buffer.append(wsrpConsumerManager.getDisplayName(portletDescription));
			}
			else {
				buffer.append(LanguageUtil.format(locale, "is-temporarily-unavailable", "remote-portlet"));
			}
			%>

		</liferay-ui:search-container-column-text>

		<liferay-ui:search-container-column-jsp
			align="right"
			path="/admin/consumer_portlet_action.jsp"
			valign="top"
		/>
	</liferay-ui:search-container-row>

	<div>
		<input type="button" value="<liferay-ui:message key="add-portlet" />" onClick="location.href = '<portlet:renderURL><portlet:param name="mvcPath" value="/admin/edit_consumer_portlet.jsp" /><portlet:param name="redirect" value="<%= currentURL %>" /><portlet:param name="wsrpConsumerId" value="<%= String.valueOf(wsrpConsumer.getWsrpConsumerId()) %>" /></portlet:renderURL>';" />
	</div>

	<br />

	<liferay-ui:search-iterator />
</liferay-ui:search-container>

</form>

<%
PortalUtil.addPortletBreadcrumbEntry(request, LanguageUtil.get(pageContext, "manage-portlets"), currentURL);
%>