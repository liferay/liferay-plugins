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
String keywords = ParamUtil.getString(request, "keywords");

String tabs1 = ParamUtil.getString(request, "tabs1", "sync-admin");

int delta = ParamUtil.getInteger(request, "delta", SearchContainer.DEFAULT_DELTA);

PortletURL currentURLObj = PortletURLUtil.getCurrent(liferayPortletRequest, liferayPortletResponse);

String currentURL = currentURLObj.toString();

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("delta", String.valueOf(delta));
portletURL.setParameter("tabs1", tabs1);
%>

<aui:nav-bar>
	<aui:nav cssClass="navbar-nav" id="toolbarContainer">

		<%
		String taglibURL = "javascript:" + renderResponse.getNamespace() + "configureSite('true');";
		%>

		<aui:nav-item href="<%= taglibURL %>" iconCssClass="icon-ok" label="enable-sync-sites" />

		<%
		taglibURL = "javascript:" + renderResponse.getNamespace() + "configureSite('false');";
		%>

		<aui:nav-item href="<%= taglibURL %>" iconCssClass="icon-remove" label="disable-sync-sites" />

		<aui:nav-item dropdown="<%= true %>" iconCssClass="icon-lock" id="defaultPermissionsButton" label="default-permissions">
			<%
			taglibURL = "javascript:" + renderResponse.getNamespace() + "configurePermissions('view-permission');";
			%>

			<aui:nav-item href="<%= taglibURL %>" label="set-view-permission" />

			<%
			taglibURL = "javascript:" + renderResponse.getNamespace() + "configurePermissions('view-and-add-discussion-permission');";
			%>

			<aui:nav-item href="<%= taglibURL %>" label="set-view-and-add-discussion-permission" />

			<%
			taglibURL = "javascript:" + renderResponse.getNamespace() + "configurePermissions('full-access-permission');";
			%>

			<aui:nav-item href="<%= taglibURL %>" label="set-full-access-permission" />
		</aui:nav-item>
	</aui:nav>

	<aui:nav-bar-search cssClass="pull-right">
		<aui:form action="<%= portletURL %>" cssClass="form-search" method="post" name="fm1">
			<liferay-ui:input-search placeholder='<%= LanguageUtil.get(locale, "keywords") %>' title='<%= LanguageUtil.get(locale, "keywords") %>' />
		</aui:form>
	</aui:nav-bar-search>
</aui:nav-bar>

<aui:form method="post" name="fm">
	<aui:input name="enableSyncSites" type="hidden" />
	<aui:input name="groupIds" type="hidden" />
	<aui:input name="permissions" type="hidden" />
	<aui:input name="redirect" type="hidden" value="<%= currentURL %>" />

	<aui:fieldset label="sync-sites">
		<%
		List<Group> groups = GroupLocalServiceUtil.getGroups(themeDisplay.getCompanyId(), GroupConstants.ANY_PARENT_GROUP_ID, true);

		groups = ListUtil.copy(groups);

		Iterator<Group> iterator = groups.iterator();

		while (iterator.hasNext()) {
			Group group = iterator.next();

			if (group.isCompany() ||
				(Validator.isNotNull(keywords) &&
				(!group.getDescriptiveName().contains(keywords)))) {

				iterator.remove();
			}
		}
		%>

		<liferay-ui:search-container
			emptyResultsMessage="no-sites-were-found"
			iteratorURL="<%= portletURL %>"
			rowChecker="<%= new RowChecker(renderResponse) %>"
			total="<%= groups.size() %>"
		>
			<liferay-ui:search-container-results
				results="<%= ListUtil.subList(groups, searchContainer.getStart(), searchContainer.getEnd()) %>"
			/>

			<liferay-ui:search-container-row
				className="com.liferay.portal.model.Group"
				escapedModel="<%= true %>"
				keyProperty="groupId"
				modelVar="group"
			>
				<liferay-ui:search-container-column-text
					name="name"
					value="<%= group.getDescriptiveName() %>"
				/>

				<liferay-ui:search-container-column-text
					name="sync-site-enabled"
					value='<%= LanguageUtil.get(locale, GetterUtil.getString(group.getTypeSettingsProperty("syncEnabled"), "true")) %>'
				/>

				<liferay-ui:search-container-column-text
					name="default-permissions"
					value='<%= LanguageUtil.get(locale, GetterUtil.getString(group.getTypeSettingsProperty("permissions"), "full-access-permission")) %>'
				/>

				<liferay-ui:search-container-column-jsp
					align="right"
					cssClass="entry-action"
					path="/sync_admin_group_action.jsp"
				/>
			</liferay-ui:search-container-row>

			<liferay-ui:search-iterator />
		</liferay-ui:search-container>
	</aui:fieldset>
</aui:form>

<aui:script>
	Liferay.provide(
		window,
		'<portlet:namespace />configurePermissions',
		function(permissions) {
			var groupIds = Liferay.Util.listCheckedExcept(document.<portlet:namespace />fm, '<portlet:namespace />allRowIds');

			if (groupIds) {
				document.<portlet:namespace />fm.<portlet:namespace />groupIds.value = groupIds;

				document.<portlet:namespace />fm.<portlet:namespace />permissions.value = permissions;

				submitForm(document.<portlet:namespace />fm, '<liferay-portlet:actionURL name="configurePermissions" />');
			}
		}
	);

	Liferay.provide(
		window,
		'<portlet:namespace />configureSite',
		function(enableSyncSites) {
			var groupIds = Liferay.Util.listCheckedExcept(document.<portlet:namespace />fm, '<portlet:namespace />allRowIds');

			if (groupIds) {
				if ((enableSyncSites === 'true') || confirm('<%= UnicodeLanguageUtil.get(request, "disabling-a-sync-site-will-delete-all-associated-files-from-all-clients") %>')) {
					document.<portlet:namespace />fm.<portlet:namespace />groupIds.value = groupIds;

					document.<portlet:namespace />fm.<portlet:namespace />enableSyncSites.value = enableSyncSites;

					submitForm(document.<portlet:namespace />fm, '<liferay-portlet:actionURL name="configureSite" />');
				}
			}
		}
	);
</aui:script>