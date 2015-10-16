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
String tabs1 = ParamUtil.getString(request, "tabs1", "sites");

String keywords = ParamUtil.getString(request, "keywords");

int delta = ParamUtil.getInteger(request, "delta", SearchContainer.DEFAULT_DELTA);

PortletURL currentURLObj = PortletURLUtil.getCurrent(liferayPortletRequest, liferayPortletResponse);

String currentURL = currentURLObj.toString();

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("tabs1", tabs1);
portletURL.setParameter("delta", String.valueOf(delta));
%>

<aui:nav-bar>
	<aui:nav cssClass="hide navbar-nav" id="toolbarContainer">
		<aui:nav-item href='<%= "javascript:" + renderResponse.getNamespace() + "enableSites();" %>' iconCssClass="icon-ok" label="enable-sync-sites" />

		<aui:nav-item href='<%= "javascript:" + renderResponse.getNamespace() + "disableSites();" %>' iconCssClass="icon-remove" label="disable-sync-sites" />

		<aui:nav-item dropdown="<%= true %>" iconCssClass="icon-lock" label="default-file-permissions">
			<aui:nav-item href='<%= "javascript:" + renderResponse.getNamespace() + "setPermissionsViewOnly();" %>' label="view-only" />

			<aui:nav-item href='<%= "javascript:" + renderResponse.getNamespace() + "setPermissionsViewAndAddDiscussion();" %>' label="view-and-add-discussion" />

			<aui:nav-item href='<%= "javascript:" + renderResponse.getNamespace() + "setPermissionsViewUpdateAndAddDiscussion();" %>' label="view-update-and-add-discussion" />

			<aui:nav-item href='<%= "javascript:" + renderResponse.getNamespace() + "setPermissionsFullAccess();" %>' label="full-access" />
		</aui:nav-item>
	</aui:nav>

	<aui:nav-bar-search cssClass="pull-right">
		<aui:form action="<%= portletURL %>" cssClass="form-search" method="post" name="fm1">
			<liferay-ui:input-search placeholder='<%= LanguageUtil.get(request, "keywords") %>' title='<%= LanguageUtil.get(request, "keywords") %>' />
		</aui:form>
	</aui:nav-bar-search>
</aui:nav-bar>

<aui:form method="post" name="fm">
	<aui:input name="redirect" type="hidden" value="<%= currentURL %>" />
	<aui:input name="enabled" type="hidden" />
	<aui:input name="groupIds" type="hidden" />
	<aui:input name="permissions" type="hidden" />

	<%
	LinkedHashMap<String, Object> groupParams = new LinkedHashMap<String, Object>();

	groupParams.put("active", true);

	List<Group> groups = GroupLocalServiceUtil.search(themeDisplay.getCompanyId(), keywords, groupParams, QueryUtil.ALL_POS, QueryUtil.ALL_POS);

	List<String> resourceActions = ListUtil.toList(SyncPermissionsConstants.getFileResourceActions(SyncPermissionsConstants.PERMISSIONS_FULL_ACCESS));

	List<String> localizedResourceActions = new ArrayList<String>(resourceActions.size());

	for (String resourceAction : resourceActions) {
		localizedResourceActions.add(LanguageUtil.get(locale, ResourceActionsUtil.getActionNamePrefix() + resourceAction));
	}

	String fullAccessPermissionsDescription = LanguageUtil.format(request, "full-access-x", StringUtil.merge(localizedResourceActions, StringPool.COMMA_AND_SPACE));

	resourceActions = ResourceActionsUtil.getModelResourceGroupDefaultActions(DLFileEntry.class.getName());

	String defaultPermissionsDescription = null;

	if (resourceActions != null) {
		localizedResourceActions = new ArrayList<String>(resourceActions.size());

		for (String resourceAction : resourceActions) {
			localizedResourceActions.add(LanguageUtil.get(locale, ResourceActionsUtil.getActionNamePrefix() + resourceAction));
		}

		defaultPermissionsDescription = StringUtil.merge(localizedResourceActions, StringPool.COMMA_AND_SPACE);
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
				property="descriptiveName"
			/>

			<liferay-ui:search-container-column-text
				name="description"
				property="description"
			/>

			<%
			boolean syncSiteEnabled = GetterUtil.getBoolean(group.getTypeSettingsProperty("syncEnabled"), true);

			String permissionsDescription = StringPool.BLANK;

			if (syncSiteEnabled) {
				int currentPermissions = GetterUtil.getInteger(group.getTypeSettingsProperty("syncSiteMemberFilePermissions"));

				if (currentPermissions == SyncPermissionsConstants.PERMISSIONS_VIEW_ONLY) {
					permissionsDescription = LanguageUtil.get(request, "view-only");
				}
				else if (currentPermissions == SyncPermissionsConstants.PERMISSIONS_VIEW_AND_ADD_DISCUSSION) {
					permissionsDescription = LanguageUtil.get(request, "view-and-add-discussion");
				}
				else if (currentPermissions == SyncPermissionsConstants.PERMISSIONS_VIEW_UPDATE_AND_ADD_DISCUSSION) {
					permissionsDescription = LanguageUtil.get(request, "view-update-and-add-discussion");
				}
				else if (currentPermissions == SyncPermissionsConstants.PERMISSIONS_FULL_ACCESS) {
					permissionsDescription = fullAccessPermissionsDescription;
				}
				else if (Validator.isNotNull(defaultPermissionsDescription)) {
					permissionsDescription = defaultPermissionsDescription;
				}
			}
			%>

			<liferay-ui:search-container-column-text
				name="default-file-permissions"
				value="<%= permissionsDescription %>"
			/>

			<liferay-ui:search-container-column-text
				name="enabled"
				translate="true"
				value='<%= syncSiteEnabled ? "yes" : "no" %>'
			/>

			<liferay-ui:search-container-column-jsp
				align="right"
				cssClass="entry-action"
				path="/sites_action.jsp"
			/>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator />
	</liferay-ui:search-container>
</aui:form>

<aui:script use="aui-base,liferay-util-list-fields">
	A.one('#<portlet:namespace /><%= searchContainerReference.getId() %>SearchContainer').delegate(
		'click',
		function() {
			var hide = (Liferay.Util.listCheckedExcept(document.<portlet:namespace />fm, '<portlet:namespace /><%= RowChecker.ALL_ROW_IDS %>').length == 0);

			A.one('#<portlet:namespace />toolbarContainer').toggle(!hide);
		},
		'input[type=checkbox]'
	);
</aui:script>

<aui:script>
	Liferay.provide(
		window,
		'<portlet:namespace />disableSites',
		function() {
			var groupIds = Liferay.Util.listCheckedExcept(document.<portlet:namespace />fm, '<portlet:namespace />allRowIds');

			if (groupIds && confirm('<%= UnicodeLanguageUtil.get(request, "disabling-a-sync-site-will-delete-all-associated-files-from-all-clients") %>')) {
				document.<portlet:namespace />fm.<portlet:namespace />groupIds.value = groupIds;
				document.<portlet:namespace />fm.<portlet:namespace />enabled.value = false;

				submitForm(document.<portlet:namespace />fm, '<liferay-portlet:actionURL name="updateSites" />');
			}
		},
		['liferay-util-list-fields']
	);

	Liferay.provide(
		window,
		'<portlet:namespace />enableSites',
		function() {
			var groupIds = Liferay.Util.listCheckedExcept(document.<portlet:namespace />fm, '<portlet:namespace />allRowIds');

			if (groupIds) {
				document.<portlet:namespace />fm.<portlet:namespace />groupIds.value = groupIds;
				document.<portlet:namespace />fm.<portlet:namespace />enabled.value = true;

				submitForm(document.<portlet:namespace />fm, '<liferay-portlet:actionURL name="updateSites" />');
			}
		},
		['liferay-util-list-fields']
	);

	Liferay.provide(
		window,
		'<portlet:namespace />setPermissionsFullAccess',
		function() {
			var groupIds = Liferay.Util.listCheckedExcept(document.<portlet:namespace />fm, '<portlet:namespace />allRowIds');

			if (groupIds) {
				document.<portlet:namespace />fm.<portlet:namespace />groupIds.value = groupIds;
				document.<portlet:namespace />fm.<portlet:namespace />permissions.value = <%= SyncPermissionsConstants.PERMISSIONS_FULL_ACCESS %>;

				submitForm(document.<portlet:namespace />fm, '<liferay-portlet:actionURL name="updateSites" />');
			}
		},
		['liferay-util-list-fields']
	);

	Liferay.provide(
		window,
		'<portlet:namespace />setPermissionsViewAndAddDiscussion',
		function() {
			var groupIds = Liferay.Util.listCheckedExcept(document.<portlet:namespace />fm, '<portlet:namespace />allRowIds');

			if (groupIds) {
				document.<portlet:namespace />fm.<portlet:namespace />groupIds.value = groupIds;
				document.<portlet:namespace />fm.<portlet:namespace />permissions.value = <%= SyncPermissionsConstants.PERMISSIONS_VIEW_AND_ADD_DISCUSSION %>;

				submitForm(document.<portlet:namespace />fm, '<liferay-portlet:actionURL name="updateSites" />');
			}
		},
		['liferay-util-list-fields']
	);

	Liferay.provide(
		window,
		'<portlet:namespace />setPermissionsViewOnly',
		function() {
			var groupIds = Liferay.Util.listCheckedExcept(document.<portlet:namespace />fm, '<portlet:namespace />allRowIds');

			if (groupIds) {
				document.<portlet:namespace />fm.<portlet:namespace />groupIds.value = groupIds;
				document.<portlet:namespace />fm.<portlet:namespace />permissions.value = <%= SyncPermissionsConstants.PERMISSIONS_VIEW_ONLY %>;

				submitForm(document.<portlet:namespace />fm, '<liferay-portlet:actionURL name="updateSites" />');
			}
		},
		['liferay-util-list-fields']
	);

	Liferay.provide(
		window,
		'<portlet:namespace />setPermissionsViewUpdateAndAddDiscussion',
		function() {
			var groupIds = Liferay.Util.listCheckedExcept(document.<portlet:namespace />fm, '<portlet:namespace />allRowIds');

			if (groupIds) {
				document.<portlet:namespace />fm.<portlet:namespace />groupIds.value = groupIds;
				document.<portlet:namespace />fm.<portlet:namespace />permissions.value = <%= SyncPermissionsConstants.PERMISSIONS_VIEW_UPDATE_AND_ADD_DISCUSSION %>;

				submitForm(document.<portlet:namespace />fm, '<liferay-portlet:actionURL name="updateSites" />');
			}
		},
		['liferay-util-list-fields']
	);
</aui:script>