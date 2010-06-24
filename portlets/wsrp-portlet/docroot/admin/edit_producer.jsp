<%
/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
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
%>

<%@ include file="/init.jsp" %>

<%
String redirect = ParamUtil.getString(request, "redirect");

long wsrpProducerId = ParamUtil.getLong(request, "wsrpProducerId");

WSRPProducer wsrpProducer = null;

try {
	wsrpProducer = WSRPProducerLocalServiceUtil.getWSRPProducer(wsrpProducerId);
}
catch (NoSuchProducerException nsce) {
}

String[] portletIds = StringUtil.split(BeanParamUtil.getString(wsrpProducer, request, "portletIds"));
%>

<aui:script>
	Liferay.provide(
		window,
		'<portlet:namespace />saveProducer',
		function() {
			document.<portlet:namespace />fm.<portlet:namespace />portletIds.value = Liferay.Util.listSelect(document.<portlet:namespace />fm.<portlet:namespace />currentPortletIds);
			submitForm(document.<portlet:namespace />fm);
		},
		['liferay-util-list-fields']
	);
</aui:script>

<form action="<portlet:actionURL name="updateWSRPProducer"><portlet:param name="jspPage" value="/admin/edit_producer.jsp" /><portlet:param name="redirect" value="<%= redirect %>" /></portlet:actionURL>" method="post" name="<portlet:namespace />fm" onSubmit="<portlet:namespace />saveProducer(); return false;">
<input name="<portlet:namespace />wsrpProducerId" type="hidden" value="<%= wsrpProducerId %>" />
<input name="<portlet:namespace />portletIds" type="hidden" value="" />

<liferay-ui:error exception="<%= WSRPProducerNameException.class %>" message="please-enter-a-valid-name" />

<div class="breadcrumbs">
	<span class="first"><a href="<portlet:renderURL />"><liferay-ui:message key="producers" /></a></span> &raquo;

	<span class="last"><liferay-ui:message key='<%= ((wsrpProducer == null) ? Constants.ADD : Constants.UPDATE) + "-producer" %>' /></span>
</div>

<table class="lfr-table">
<tr>
	<td>
		<liferay-ui:message key="name" />
	</td>
	<td>
		<liferay-ui:input-field model="<%= WSRPProducer.class %>" bean="<%= wsrpProducer %>" field="name" />
	</td>
</tr>

<c:if test="<%= wsrpProducer != null %>">
	<tr>
		<td>
			<liferay-ui:message key="url" />
		</td>
		<td>
			<a href="<%= themeDisplay.getPortalURL() %>/wsrp-portlet/wsdl/<%= wsrpProducer.getWsrpProducerId() %>" target="_blank"><%= themeDisplay.getPortalURL() %>/wsrp-portlet/wsdl/<%= wsrpProducer.getWsrpProducerId() %></a>
		</td>
	</tr>
</c:if>

<tr>
	<td colspan="3">
		<br />
	</td>
</tr>
<tr>
	<td>
		<liferay-ui:message key="portlets" />
	</td>
	<td>

		<%

		// Left list

		List<KeyValuePair> leftList = new ArrayList<KeyValuePair>();

		for (String portletId : portletIds) {
			Portlet portlet = PortletLocalServiceUtil.getPortletById(company.getCompanyId(), portletId);

			if ((portlet == null) || portlet.isUndeployedPortlet()) {
				continue;
			}

			leftList.add(new KeyValuePair(portletId, PortalUtil.getPortletTitle(portlet, application, locale)));
		}

		leftList = ListUtil.sort(leftList, new KeyValuePairComparator(false, true));

		// Right list

		List<KeyValuePair> rightList = new ArrayList<KeyValuePair>();

		Arrays.sort(portletIds);

		Iterator<Portlet> itr = PortletLocalServiceUtil.getPortlets(company.getCompanyId(), false, false).iterator();

		while (itr.hasNext()) {
			Portlet portlet = (Portlet)itr.next();

			if (portlet.isUndeployedPortlet()) {
				continue;
			}

			String portletId = portlet.getPortletId();

			if (Arrays.binarySearch(portletIds, portletId) < 0) {
				rightList.add(new KeyValuePair(portletId, PortalUtil.getPortletTitle(portlet, application, locale)));
			}
		}

		rightList = ListUtil.sort(rightList, new KeyValuePairComparator(false, true));
		%>

		<liferay-ui:input-move-boxes
			leftTitle="current"
			rightTitle="available"
			leftBoxName="currentPortletIds"
			rightBoxName="availablePortletIds"
			leftList="<%= leftList %>"
			rightList="<%= rightList %>"
		/>
	</td>
</tr>
</table>

<br />

<input type="submit" value="<liferay-ui:message key="save" />" />

<input type="button" value="<liferay-ui:message key="cancel" />" onClick="location.href = '<%= HtmlUtil.escape(PortalUtil.escapeRedirect(redirect)) %>';" />

</form>

<aui:script>
	Liferay.Util.focusFormField(document.<portlet:namespace />fm.<portlet:namespace />name);
</aui:script>