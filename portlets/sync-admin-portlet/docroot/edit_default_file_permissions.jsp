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
long groupId = ParamUtil.getLong(request, "groupId");

Group group = GroupLocalServiceUtil.fetchGroup(groupId);

int currentPermissions = GetterUtil.getInteger(group.getTypeSettingsProperty("syncSiteMemberFilePermissions"));
%>

<liferay-ui:header
	localizeTitle="<%= false %>"
	title="<%= group.getDescriptiveName() %>"
/>

<table class="table table-bordered table-hover table-striped">
	<thead class="table-columns">
		<tr>
			<th>
				<liferay-ui:message key="name" />
			</th>
			<th />
		</tr>
	</thead>

	<tbody>

		<%
		List<Integer> permissionsOptions = new ArrayList<Integer>(3);

		permissionsOptions.add(SyncPermissionsConstants.PERMISSIONS_VIEW_ONLY);
		permissionsOptions.add(SyncPermissionsConstants.PERMISSIONS_VIEW_AND_ADD_DISCUSSION);
		permissionsOptions.add(SyncPermissionsConstants.PERMISSIONS_FULL_ACCESS);

		for (Integer permissions : permissionsOptions) {
		%>

			<tr class="record-row">
				<td>

					<%
					if (permissions == SyncPermissionsConstants.PERMISSIONS_VIEW_ONLY) {
					%>

						<liferay-ui:message key="view-only" />

						<liferay-ui:icon-help message="view-only-help" />

					<%
					}
					else if (permissions == SyncPermissionsConstants.PERMISSIONS_VIEW_AND_ADD_DISCUSSION) {
					%>

						<liferay-ui:message key="view-and-add-discussion" />

						<liferay-ui:icon-help message="view-and-add-discussion-help" />

					<%
					}
					else if (permissions == SyncPermissionsConstants.PERMISSIONS_FULL_ACCESS) {
						List<String> resourceActions = ListUtil.toList(SyncPermissionsConstants.getFileResourceActions(permissions));

						List<String> localizedResourceActions = new ArrayList<String>(resourceActions.size());

						for (String resourceAction : resourceActions) {
							localizedResourceActions.add(LanguageUtil.get(locale, ResourceActionsUtil.getActionNamePrefix() + resourceAction));
						}
					%>

						<liferay-ui:message arguments="<%= StringUtil.merge(localizedResourceActions, StringPool.COMMA_AND_SPACE) %>" key="full-access-x" />

						<liferay-ui:icon-help message="full-access-help" />

					<%
					}
					%>

				</td>
				<td>
					<portlet:actionURL name="updateSites" var="setPermissionsURL">
						<portlet:param name="groupIds" value="<%= String.valueOf(groupId) %>" />
						<portlet:param name="permissions" value="<%= String.valueOf(permissions) %>" />
					</portlet:actionURL>

					<%
					String setPermissions = renderResponse.getNamespace() + "setPermissions('" + setPermissionsURL + "');";
					%>

					<aui:button disabled="<%= currentPermissions == permissions %>" onClick="<%= setPermissions %>" value="choose" />
				</td>
			</tr>

		<%
		}
		%>

	</tbody>
</table>

<aui:script use="aui-base,aui-io-request">
	Liferay.provide(
		window,
		'<portlet:namespace />setPermissions',
		function(uri) {
			A.io.request(
				uri,
				{
					method: 'post',
					on: {
						success: function() {
							Liferay.Util.getWindow('<portlet:namespace />editDefaultFilePermissionsDialog').destroy();
						}
					}
				}
			);
		},
		['liferay-util']
	);
</aui:script>