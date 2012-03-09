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

long wsrpProducerId = ParamUtil.getLong(request, "wsrpProducerId");

WSRPProducer wsrpProducer = null;

String version = Constants.WSRP_V2;

try {
	wsrpProducer = WSRPProducerLocalServiceUtil.getWSRPProducer(wsrpProducerId);

	version = GetterUtil.getString(wsrpProducer.getVersion(), Constants.WSRP_V2);
}
catch (NoSuchProducerException nspe) {
}

String[] portletIds = StringUtil.split(BeanParamUtil.getString(wsrpProducer, request, "portletIds"));

String portalServletContextName = PortalUtil.getPathContext();

ServletContext portalServletContext = ServletContextPool.get(portalServletContextName);
%>

<liferay-ui:header
	backURL="<%= redirect %>"
	title='<%= (wsrpProducer != null) ? wsrpProducer.getName() : "new-producer" %>'
/>

<form action="<portlet:actionURL name="updateWSRPProducer"><portlet:param name="mvcPath" value="/admin/edit_producer.jsp" /><portlet:param name="redirect" value="<%= redirect %>" /></portlet:actionURL>" method="post" name="<portlet:namespace />fm" onSubmit="<portlet:namespace />saveProducer(); return false;">
<input name="<portlet:namespace />wsrpProducerId" type="hidden" value="<%= wsrpProducerId %>" />
<input name="<portlet:namespace />portletIds" type="hidden" value="" />

<liferay-ui:error exception="<%= WSRPProducerNameException.class %>" message="please-enter-a-valid-name" />

<table class="lfr-table">
<tr>
	<td>
		<liferay-ui:message key="name" />
	</td>
	<td>
		<liferay-ui:input-field bean="<%= wsrpProducer %>" field="name" model="<%= WSRPProducer.class %>" />
	</td>
</tr>
<tr>
	<td>
		<liferay-ui:message key="version" />
	</td>
	<td>
		<select name="<portlet:namespace />version">
			<option <%= version.equals(Constants.WSRP_V2) ? "selected" : "" %> value="<%= Constants.WSRP_V2 %>"><%= Constants.WSRP_V2 %></option>
			<option <%= version.equals(Constants.WSRP_V1) ? "selected" : "" %> value="<%= Constants.WSRP_V1 %>"><%= Constants.WSRP_V1 %></option>
		</select>
	</td>
</tr>

<c:if test="<%= wsrpProducer != null %>">
	<tr>
		<td>
			<liferay-ui:message key="url" />
		</td>
		<td>
			<a href="<%= wsrpProducer.getURL(themeDisplay.getPortalURL()) %>" target="_blank"><%= wsrpProducer.getURL(themeDisplay.getPortalURL()) %></a>
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

			leftList.add(new KeyValuePair(portletId, PortalUtil.getPortletTitle(portlet, portalServletContext, locale)));
		}

		leftList = ListUtil.sort(leftList, new KeyValuePairComparator(false, true));

		// Right list

		List<KeyValuePair> rightList = new ArrayList<KeyValuePair>();

		for (int i = 0; i < portletIds.length; i++) {
			String portletId = portletIds[i];

			int index = portletId.indexOf(PortletConstants.INSTANCE_SEPARATOR);

			if (index != -1) {
				portletIds[i] = portletId.substring(0, index);
			}
		}

		Arrays.sort(portletIds);

		Iterator<Portlet> itr = PortletLocalServiceUtil.getPortlets(company.getCompanyId(), false, false).iterator();

		while (itr.hasNext()) {
			Portlet portlet = (Portlet)itr.next();

			if (portlet.isUndeployedPortlet()) {
				continue;
			}

			if (!portlet.isRemoteable()) {
				continue;
			}

			String portletId = portlet.getPortletId();

			if (Arrays.binarySearch(portletIds, portletId) < 0) {
				rightList.add(new KeyValuePair(portletId, PortalUtil.getPortletTitle(portlet, portalServletContext, locale)));
			}
		}

		rightList = ListUtil.sort(rightList, new KeyValuePairComparator(false, true));
		%>

		<liferay-ui:input-move-boxes
			leftBoxName="currentPortletIds"
			leftList="<%= leftList %>"
			leftTitle="current"
			rightBoxName="availablePortletIds"
			rightList="<%= rightList %>"
			rightTitle="available"
		/>
	</td>
</tr>
</table>

<br />

<input type="submit" value="<liferay-ui:message key="save" />" />

<input type="button" value="<liferay-ui:message key="cancel" />" onClick="location.href = '<%= HtmlUtil.escape(PortalUtil.escapeRedirect(redirect)) %>';" />

</form>

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

	Liferay.Util.focusFormField(document.<portlet:namespace />fm.<portlet:namespace />name);
</aui:script>

<%
PortletURL producersURL = renderResponse.createRenderURL();

producersURL.setParameter("tabs1", "producers");

PortalUtil.addPortletBreadcrumbEntry(request, LanguageUtil.get(pageContext, "producers"), producersURL.toString());

if (wsrpProducer != null) {
	PortalUtil.addPortletBreadcrumbEntry(request, LanguageUtil.get(pageContext, "edit"), currentURL);
}
else {
	PortalUtil.addPortletBreadcrumbEntry(request, LanguageUtil.get(pageContext, "add-producer"), currentURL);
}
%>