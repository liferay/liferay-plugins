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

portletPreferences = SyncDLObjectServiceUtil.getPortletPreferences();

boolean enabled = PrefsPropsUtil.getBoolean(portletPreferences, themeDisplay.getCompanyId(), PortletPropsKeys.SYNC_SERVICES_ENABLED);
int maxConnections = PrefsPropsUtil.getInteger(portletPreferences, themeDisplay.getCompanyId(), PortletPropsKeys.SYNC_CLIENT_MAX_CONNECTIONS);
int pollInterval = PrefsPropsUtil.getInteger(portletPreferences, themeDisplay.getCompanyId(), PortletPropsKeys.SYNC_CLIENT_POLL_INTERVAL);
%>

<liferay-portlet:actionURL var="configurationActionURL" />

<aui:form action="<%= configurationActionURL %>" method="post" name="fm" onSubmit='<%= "event.preventDefault(); " + renderResponse.getNamespace() + "submit();" %>'>
	<aui:input name="redirect" type="hidden" value="<%= redirect %>" />
	<aui:input name="disabledGroupIds" type="hidden" />
	<aui:input name="enabledGroupIds" type="hidden" />

	<aui:fieldset>
		<aui:input name="enabled" type="checkbox" value="<%= enabled %>" />
	</aui:fieldset>

	<aui:fieldset label="sync-sites">

		<%
		List<Group> groups = GroupLocalServiceUtil.getGroups(themeDisplay.getCompanyId(), GroupConstants.ANY_PARENT_GROUP_ID, true);

		List<KeyValuePair> leftList = new ArrayList<KeyValuePair>();
		List<KeyValuePair> rightList = new ArrayList<KeyValuePair>();

		for (Group group : groups) {
			if (group.isCompany()) {
				continue;
			}

			KeyValuePair keyValuePair = new KeyValuePair(String.valueOf(group.getGroupId()), group.getDescriptiveName());

			boolean syncEnabled = GetterUtil.getBoolean(group.getTypeSettingsProperty("syncEnabled"), true);

			if (syncEnabled) {
				leftList.add(keyValuePair);
			}
			else {
				rightList.add(keyValuePair);
			}
		}
		%>

		<liferay-ui:input-move-boxes
			leftBoxName="currentGroupIds"
			leftList="<%= ListUtil.sort(leftList, new KeyValuePairComparator(false, true)) %>"
			leftTitle="current"
			rightBoxName="availableGroupIds"
			rightList="<%= ListUtil.sort(rightList, new KeyValuePairComparator(false, true)) %>"
			rightTitle="available"
		/>
	</aui:fieldset>

	<aui:fieldset label="sync-client">
		<aui:input helpMessage="max-connections-help" label="max-connections" name="maxConnections" type="text" value="<%= maxConnections %>" wrapperCssClass="lfr-input-text-container">
			<aui:validator name="digits" />
			<aui:validator name="min">1</aui:validator>
		</aui:input>

		<aui:input helpMessage="poll-interval-help" label="poll-interval" name="pollInterval" type="text" value="<%= pollInterval %>" wrapperCssClass="lfr-input-text-container">
			<aui:validator name="digits" />
			<aui:validator name="min">1</aui:validator>
		</aui:input>
	</aui:fieldset>

	<aui:button-row>
		<aui:button type="submit" />
	</aui:button-row>
</aui:form>

<aui:script>
	Liferay.provide(
		window,
		'<portlet:namespace />submit',
		function() {
			document.<portlet:namespace />fm.<portlet:namespace />disabledGroupIds.value = Liferay.Util.listSelect(document.<portlet:namespace />fm.<portlet:namespace />availableGroupIds);
			document.<portlet:namespace />fm.<portlet:namespace />enabledGroupIds.value = Liferay.Util.listSelect(document.<portlet:namespace />fm.<portlet:namespace />currentGroupIds);

			submitForm(document.<portlet:namespace />fm);
		},
		['liferay-util-list-fields']
	);
</aui:script>