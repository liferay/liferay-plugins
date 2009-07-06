<%
/**
 * Copyright (c) 2000-2009 Liferay, Inc. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
%>

<%@ include file="/init.jsp" %>

<%
long wsrpConsumerId = ParamUtil.getLong(request, "wsrpConsumerId");

WSRPConsumer wsrpConsumer = WSRPConsumerLocalServiceUtil.getWSRPConsumer(wsrpConsumerId);

WSRPConsumerManager wsrpConsumerManager = WSRPConsumerManagerFactory.getWSRPConsumerManager(wsrpConsumer);

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("jspPage", "/admin/view_consumer_portlets.jsp");
portletURL.setParameter("wsrpConsumerId", String.valueOf(wsrpConsumerId));
%>

<form>

<div class="breadcrumbs">
	<span class="first"><a href="<portlet:renderURL />"><liferay-ui:message key="consumers" /></a></span> &raquo;

	<a href="<portlet:renderURL><portlet:param name="jspPage" value="/admin/edit_consumer.jsp" /><portlet:param name="redirect" value="<%= currentURL %>" /><portlet:param name="wsrpConsumerId" value="<%= String.valueOf(wsrpConsumer.getWsrpConsumerId()) %>" /></portlet:renderURL>"><%= wsrpConsumer.getName() %></a> &raquo;

	<span class="last"><liferay-ui:message key="manage-portlets" /></span>
</div>

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
		escapedModel="<%= true %>"
		keyProperty="wsrpConsumerPortletId"
		modelVar="wsrpConsumerPortlet"
	>
		<liferay-ui:search-container-column-text
			name="name"
			property="name"
		/>

		<liferay-ui:search-container-column-text
			name="remote-portlet"
			value="<%= wsrpConsumerManager.getPortletDescription(wsrpConsumerPortlet.getPortletHandle()).getDisplayName().getValue() %>"
		/>

		<liferay-ui:search-container-column-jsp
			align="right"
			path="/admin/consumer_portlet_action.jsp"
			valign="top"
		/>
	</liferay-ui:search-container-row>

	<div>
		<input type="button" value="<liferay-ui:message key="add-portlet" />" onClick="location.href = '<portlet:renderURL><portlet:param name="jspPage" value="/admin/edit_consumer_portlet.jsp" /><portlet:param name="redirect" value="<%= currentURL %>" /><portlet:param name="wsrpConsumerId" value="<%= String.valueOf(wsrpConsumer.getWsrpConsumerId()) %>" /></portlet:renderURL>';" />
	</div>

	<br />

	<liferay-ui:search-iterator />
</liferay-ui:search-container>

</form>