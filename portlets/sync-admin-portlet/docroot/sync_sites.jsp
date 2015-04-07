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
String tabs1 = ParamUtil.getString(request, "tabs1", "sync-sites");

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

			<aui:nav-item href='<%= "javascript:" + renderResponse.getNamespace() + "setPermissionsFullAccess();" %>' label="full-access" />
		</aui:nav-item>
	</aui:nav>

	<aui:nav-bar-search cssClass="pull-right">
		<aui:form action="<%= portletURL %>" cssClass="form-search" method="post" name="fm1">
			<liferay-ui:input-search placeholder='<%= LanguageUtil.get(locale, "keywords") %>' title='<%= LanguageUtil.get(locale, "keywords") %>' />
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

	List<String> defaultResourceActions = ResourceActionsUtil.getModelResourceGroupDefaultActions(DLFileEntry.class.getName());
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

			<%
			boolean syncSiteEnabled = GetterUtil.getBoolean(group.getTypeSettingsProperty("syncEnabled"), true);
			%>

			<liferay-ui:search-container-column-text
				name="enabled"
				translate="true"
				value='<%= syncSiteEnabled ? "yes" : "no" %>'
			/>

			<%
			List<String> localizedResourceActions = null;

			if (syncSiteEnabled) {
				int permissions = GetterUtil.getInteger(group.getTypeSettingsProperty("syncSiteMemberFilePermissions"));

				List<String> resourceActions = null;

				if (permissions > 0) {
					resourceActions = ListUtil.toList(SyncPermissionsConstants.getFileResourceActions(permissions));
				}
				else {
					resourceActions = defaultResourceActions;
				}

				localizedResourceActions = new ArrayList<String>(resourceActions.size());

				for (String resourceAction : resourceActions) {
					localizedResourceActions.add(LanguageUtil.get(locale, ResourceActionsUtil.getActionNamePrefix() + resourceAction));
				}
			}
			%>

			<liferay-ui:search-container-column-text
				name="default-file-permissions"
				value="<%= ListUtil.isNotEmpty(localizedResourceActions) ? StringUtil.merge(localizedResourceActions, StringPool.COMMA_AND_SPACE) : StringPool.BLANK %>"
			/>

			<liferay-ui:search-container-column-jsp
				align="right"
				cssClass="entry-action"
				path="/sync_sites_action.jsp"
			/>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator />
	</liferay-ui:search-container>
</aui:form>

<aui:script use="aui-base">
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

			if (groupIds && confirm('<%= UnicodeLanguageUtil.get(locale, "disabling-a-sync-site-will-delete-all-associated-files-from-all-clients") %>')) {
				document.<portlet:namespace />fm.<portlet:namespace />groupIds.value = groupIds;
				document.<portlet:namespace />fm.<portlet:namespace />enabled.value = false;

				submitForm(document.<portlet:namespace />fm, '<liferay-portlet:actionURL name="updateSites" />');
			}
		}
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
		}
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
		}
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
		}
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
		}
	);
</aui:script>