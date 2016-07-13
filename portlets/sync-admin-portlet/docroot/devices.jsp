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
String tabs1 = ParamUtil.getString(request, "tabs1", "devices");

String keywords = ParamUtil.getString(request, "keywords");

int delta = ParamUtil.getInteger(request, "delta", SearchContainer.DEFAULT_DELTA);
String orderByCol = ParamUtil.getString(request, "orderByCol", "userName");
String orderByType = ParamUtil.getString(request, "orderByType", "asc");

OrderByComparator obc = OrderByComparatorFactoryUtil.create("SyncDevice", orderByCol, orderByType.equals("asc"));

PortletURL currentURLObj = PortletURLUtil.getCurrent(liferayPortletRequest, liferayPortletResponse);

String currentURL = currentURLObj.toString();

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("tabs1", tabs1);
portletURL.setParameter("delta", String.valueOf(delta));
%>

<aui:nav-bar>
	<aui:nav-bar-search cssClass="pull-right">
		<aui:form action="<%= portletURL %>" cssClass="form-search" method="post" name="fm1">
			<liferay-ui:input-search placeholder='<%= LanguageUtil.get(locale, "keywords") %>' title='<%= LanguageUtil.get(locale, "keywords") %>' />
		</aui:form>
	</aui:nav-bar-search>
</aui:nav-bar>

<aui:form method="post" name="fm">
	<aui:input name="redirect" type="hidden" value="<%= currentURL %>" />

	<liferay-ui:search-container
		emptyResultsMessage="no-devices-were-found"
		iteratorURL="<%= portletURL %>"
	>

	<%
	searchContainer.setOrderByType(orderByType);
	searchContainer.setOrderByCol(orderByCol);

	List<SyncDevice> syncDevices = new ArrayList<SyncDevice>();

	String portletId = (String)request.getAttribute(WebKeys.PORTLET_ID);

	if (portletId.equals(PortletKeys.SYNC_ADMIN)) {
		syncDevices = SyncDeviceLocalServiceUtil.search(themeDisplay.getCompanyId(), keywords, searchContainer.getStart(), searchContainer.getEnd(), obc);
	}
	else {
		syncDevices = SyncDeviceLocalServiceUtil.getSyncDevices(themeDisplay.getUserId(), searchContainer.getStart(), searchContainer.getEnd(), obc);
	}
	%>

		<liferay-ui:search-container-results
			results="<%= syncDevices %>"
		/>

		<liferay-ui:search-container-row
			className="com.liferay.sync.model.SyncDevice"
			escapedModel="<%= true %>"
			keyProperty="syncDeviceId"
			modelVar="syncDevice"
		>
			<liferay-ui:search-container-column-text
				name="name"
				orderable="<%= true %>"
				orderableProperty="userName"
				property="userName"
			/>

			<%
			String location = syncDevice.getHostname();

			IPInfo ipInfo = IPGeocoderUtil.getIPInfo(location);

			if (ipInfo != null) {
				String city = ipInfo.getCity();

				if (city != null) {
					location = city + " " + location;
				}
			}
			%>

			<liferay-ui:search-container-column-text
				name="location"
				value="<%= location %>"
			/>

			<liferay-ui:search-container-column-text
				name="type"
				orderable="<%= true %>"
			/>

			<liferay-ui:search-container-column-text
				name="build"
				orderable="<%= true %>"
				orderableProperty="buildNumber"
				property="buildNumber"
			/>

			<liferay-ui:search-container-column-date
				name="last-seen"
				orderable="<%= true %>"
				orderableProperty="modifiedDate"
				property="modifiedDate"
			/>

			<liferay-ui:search-container-column-text
				name="status"
				translate="true"
				value="<%= SyncDeviceConstants.getStatusLabel(syncDevice.getStatus()) %>"
			/>

			<liferay-ui:search-container-column-jsp
				align="right"
				cssClass="entry-action"
				path="/devices_action.jsp"
			/>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator />
	</liferay-ui:search-container>
</aui:form>