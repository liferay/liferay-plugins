<%--
/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
 *
 * This file is part of Liferay Social Office. Liferay Social Office is free
 * software: you can redistribute it and/or modify it under the terms of the GNU
 * Affero General Public License as published by the Free Software Foundation,
 * either version 3 of the License, or (at your option) any later version.
 *
 * Liferay Social Office is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License
 * for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * Liferay Social Office. If not, see http://www.gnu.org/licenses/agpl-3.0.html.
 */
--%>

<%@ include file="/init.jsp" %>

<%
String redirect = ParamUtil.getString(request, "redirect");

String tabs1 = ParamUtil.getString(request, "tabs1", "sites");

List<Group> groups = GroupLocalServiceUtil.getUserGroups(user.getUserId(), true);
List<Organization> organizations = OrganizationLocalServiceUtil.getUserOrganizations(user.getUserId());
List<Role> roles = RoleLocalServiceUtil.getRoles(PortalUtil.getCompanyId(renderRequest));
List<UserGroup> userGroups = UserGroupLocalServiceUtil.getUserGroups(themeDisplay.getCompanyId());

String tabs1Names = "sites";

if (!organizations.isEmpty()) {
	tabs1Names = tabs1Names.concat(",organizations");
}

if (!userGroups.isEmpty()) {
	tabs1Names = tabs1Names.concat(",user-groups");
}

if (!roles.isEmpty()) {
	tabs1Names = tabs1Names.concat(",roles");
}
%>

<liferay-portlet:renderURL portletConfiguration="true" var="portletURL">
	<portlet:param name="tabs1" value="<%= tabs1 %>" />
	<portlet:param name="redirect" value="<%= redirect %>" />
</liferay-portlet:renderURL>

<liferay-portlet:actionURL portletConfiguration="true" var="configurationURL" />

<aui:form action="<%= configurationURL %>" method="post" name="fm" onSubmit='<%= "event.preventDefault(); " + renderResponse.getNamespace() + "saveConfiguration();" %>'>
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />
	<aui:input name="redirect" type="hidden" value="<%= redirect %>" />

	<liferay-ui:panel-container extended="<%= true %>" id="soAnnouncementsConfigurationsPanelContainer" persistState="<%= true %>">
		<liferay-ui:panel collapsible="<%= true %>" extended="<%= true %>" id="soAnnouncementsDisplaySettingsPanel" persistState="<%= true %>" title="display-settings">
			<aui:fieldset>

				<%
				String modifiedPageDelta = renderResponse.getNamespace() + "modifiedPageDelta();";
				%>

				<aui:select label="entries-to-display-per-page" name="preferences--pageDelta--" onChange="<%= modifiedPageDelta %>">

					<%
					for (int pageDeltaValue : GetterUtil.getIntegerValues(PropsUtil.getArray(PropsKeys.SEARCH_CONTAINER_PAGE_DELTA_VALUES))) {
					%>

						<aui:option label="<%= pageDeltaValue %>" selected="<%= pageDelta == pageDeltaValue %>" />

					<%
					}
					%>

				</aui:select>
			</aui:fieldset>
		</liferay-ui:panel>

		<%
		Boolean customizeAnnouncementsDisplayed = PrefsParamUtil.getBoolean(preferences, request, "customizeAnnouncementsDisplayed", layout.getGroup().isUser() ? false : true);

		String toggleCustomizeAnnouncementsDisplayed = renderResponse.getNamespace() + "toggleCustomizeAnnouncementsDisplayed();";
		%>

		<liferay-ui:panel collapsible="<%= true %>" extended="<%= true %>" id="soAnnouncementsDisplayPanel" persistState="<%= true %>" title="announcements-displayed">
			<aui:input cssClass="customize-announcements-displayed" id="customizeAnnouncementsDisplayedInput" name="preferences--customizeAnnouncementsDisplayed--" onChange="<%= toggleCustomizeAnnouncementsDisplayed %>" title="customize-announcements-displayed" type="checkbox" value="<%= customizeAnnouncementsDisplayed %>" />

			<div class="<%= customizeAnnouncementsDisplayed ? "" : "aui-helper-hidden" %>" id="<portlet:namespace />customizeAnnouncementsDisplayed">
				<div class="portlet-msg-info">
					<liferay-ui:message key="general-annnouncements-will-always-be-shown-select-any-other-distribution-scopes-you-would-like-to-display" />
				</div>

				<liferay-ui:tabs
					names="<%= tabs1Names %>"
					param="tabs1"
					refresh="<%= false %>"
				>

				<c:if test="<%= !groups.isEmpty() %>">
					<liferay-ui:section>
						<aui:fieldset cssClass="scope-section-holder">

							<%
							String selectedScopeGroups;
							try {
								selectedScopeGroups = PrefsParamUtil.getString(preferences, request, "selectedScopeGroups", String.valueOf(layout.getGroupId()));
							} catch (Exception e) {
								selectedScopeGroups = "";
							}

							// Left list
							List<KeyValuePair> leftList = new ArrayList<KeyValuePair>();
							for (Group group : groups) {
								if (selectedScopeGroups.contains(String.valueOf(group.getGroupId()))) {
									leftList.add(new KeyValuePair(String.valueOf(group.getGroupId()), group.getDescriptiveName(locale)));
								}
							}

							// Right list
							List<KeyValuePair> rightList = new ArrayList<KeyValuePair>();

							for (Group group : groups) {

								KeyValuePair tempKeyValuePair = new KeyValuePair(String.valueOf(group.getGroupId()), group.getDescriptiveName(locale));
								if (!leftList.contains(tempKeyValuePair)) {
									rightList.add(tempKeyValuePair);
								}
							}
							%>

							<aui:input name="preferences--selectedScopeGroups--" type="hidden" />

							<div id="<portlet:namespace />scopeGroupsBoxes">
								<liferay-ui:input-move-boxes
									leftBoxName="currentScopeGroups"
									leftList="<%= leftList %>"
									leftReorder="true"
									leftTitle="displaying"
									rightBoxName="availableScopeGroups"
									rightList="<%= rightList %>"
									rightTitle="available"
								/>
							</div>
						</aui:fieldset>
					</liferay-ui:section>
				</c:if>

				<c:if test="<%= !organizations.isEmpty() %>">
					<liferay-ui:section>
						<aui:fieldset cssClass="scope-section-holder">

							<%
							String selectedScopeOrganizations;
							try {
								selectedScopeOrganizations = GetterUtil.getString(PrefsParamUtil.getString(preferences, request, "selectedScopeOrganizations", ""));
							} catch (Exception e) {
								selectedScopeOrganizations = "";
							}

							// Left list
							List<KeyValuePair> leftList = new ArrayList<KeyValuePair>();
							for (Organization organization : organizations) {
								if (selectedScopeOrganizations.contains(String.valueOf(organization.getOrganizationId()))) {
									leftList.add(new KeyValuePair(String.valueOf(organization.getOrganizationId()), organization.getName()));
								}
							}

							// Right list
							List<KeyValuePair> rightList = new ArrayList<KeyValuePair>();

							for (Organization organization : organizations) {

								KeyValuePair tempKeyValuePair = new KeyValuePair(String.valueOf(organization.getOrganizationId()), organization.getName());
								if (!leftList.contains(tempKeyValuePair)) {
									rightList.add(tempKeyValuePair);
								}
							}
							%>

							<aui:input name="preferences--selectedScopeOrganizations--" type="hidden" />

							<div id="<portlet:namespace />scopeOrganizationsBoxes">
								<liferay-ui:input-move-boxes
									leftBoxName="currentScopeOrganizations"
									leftList="<%= leftList %>"
									leftReorder="true"
									leftTitle="displaying"
									rightBoxName="availableScopeOrganizations"
									rightList="<%= rightList %>"
									rightTitle="available"
								/>
							</div>
						</aui:fieldset>
					</liferay-ui:section>
				</c:if>

				<c:if test="<%= !userGroups.isEmpty() %>">
					<liferay-ui:section>
						<aui:fieldset cssClass="scope-section-holder">

							<%
							String selectedScopeUserGroups;
							try {
								selectedScopeUserGroups = GetterUtil.getString(PrefsParamUtil.getString(preferences, request, "selectedScopeUserGroups", ""));
							} catch (Exception e) {
								selectedScopeUserGroups = "";
							}

							// Left list
							List<KeyValuePair> leftList = new ArrayList<KeyValuePair>();
							for (UserGroup userGroup : userGroups) {
								if (selectedScopeUserGroups.contains(String.valueOf(userGroup.getUserGroupId()))) {
									leftList.add(new KeyValuePair(String.valueOf(userGroup.getUserGroupId()), userGroup.getName()));
								}
							}

							// Right list
							List<KeyValuePair> rightList = new ArrayList<KeyValuePair>();

							for (UserGroup userGroup : userGroups) {

								KeyValuePair tempKeyValuePair = new KeyValuePair(String.valueOf(userGroup.getUserGroupId()), userGroup.getName());
								if (!leftList.contains(tempKeyValuePair)) {
									rightList.add(tempKeyValuePair);
								}
							}
							%>

							<aui:input name="preferences--selectedScopeUserGroups--" type="hidden" />

							<div id="<portlet:namespace />scopeUserGroupsBoxes">
								<liferay-ui:input-move-boxes
									leftBoxName="currentScopeUserGroups"
									leftList="<%= leftList %>"
									leftReorder="true"
									leftTitle="displaying"
									rightBoxName="availableScopeUserGroups"
									rightList="<%= rightList %>"
									rightTitle="available"
								/>
							</div>
						</aui:fieldset>
					</liferay-ui:section>
				</c:if>

				<c:if test="<%= !roles.isEmpty() %>">
					<liferay-ui:section>
						<aui:fieldset cssClass="scope-section-holder">

							<%
							String selectedScopeRoles;
							try {
								selectedScopeRoles = GetterUtil.getString(PrefsParamUtil.getString(preferences, request, "selectedScopeRoles", ""));
							} catch (Exception e) {
								selectedScopeRoles = "";
							}

							// Left list
							List<KeyValuePair> leftList = new ArrayList<KeyValuePair>();
							for (Role role : roles) {
								if (selectedScopeRoles.contains(String.valueOf(role.getRoleId()))) {
									leftList.add(new KeyValuePair(String.valueOf(role.getRoleId()), role.getTitle(locale)));
								}
							}

							// Right list
							List<KeyValuePair> rightList = new ArrayList<KeyValuePair>();

							for (Role role : roles) {

								KeyValuePair tempKeyValuePair = new KeyValuePair(String.valueOf(role.getRoleId()), role.getTitle(locale));
								if (!leftList.contains(tempKeyValuePair)) {
									rightList.add(tempKeyValuePair);
								}
							}
							%>

							<aui:input name="preferences--selectedScopeRoles--" type="hidden" />

							<div id="<portlet:namespace />scopeRolesBoxes">
								<liferay-ui:input-move-boxes
									leftBoxName="currentScopeRoles"
									leftList="<%= leftList %>"
									leftReorder="true"
									leftTitle="displaying"
									rightBoxName="availableScopeRoles"
									rightList="<%= rightList %>"
									rightTitle="available"
								/>
							</div>
						</aui:fieldset>
					</liferay-ui:section>
				</c:if>
			</liferay-ui:tabs>
			</div>
		</liferay-ui:panel>
	</liferay-ui:panel-container>

	<aui:button-row>
		<aui:button type="submit" />
	</aui:button-row>
</aui:form>

<aui:script use="aui-base, event">
	Liferay.provide(
		window,
		'<portlet:namespace />saveConfiguration',
		function() {
			if (document.<portlet:namespace />fm.<portlet:namespace />selectedScopeGroups) {
				document.<portlet:namespace />fm.<portlet:namespace />selectedScopeGroups.value = Liferay.Util.listSelect(document.<portlet:namespace />fm.<portlet:namespace />currentScopeGroups);
			}

			if (document.<portlet:namespace />fm.<portlet:namespace />selectedScopeOrganizations) {
				document.<portlet:namespace />fm.<portlet:namespace />selectedScopeOrganizations.value = Liferay.Util.listSelect(document.<portlet:namespace />fm.<portlet:namespace />currentScopeOrganizations);
			}

			if (document.<portlet:namespace />fm.<portlet:namespace />selectedScopeRoles) {
				document.<portlet:namespace />fm.<portlet:namespace />selectedScopeRoles.value = Liferay.Util.listSelect(document.<portlet:namespace />fm.<portlet:namespace />currentScopeRoles);
			}

			if (document.<portlet:namespace />fm.<portlet:namespace />selectedScopeUserGroups) {
				document.<portlet:namespace />fm.<portlet:namespace />selectedScopeUserGroups.value = Liferay.Util.listSelect(document.<portlet:namespace />fm.<portlet:namespace />currentScopeUserGroups);
			}

			submitForm(document.<portlet:namespace />fm);
		},
		['liferay-util-list-fields']
	);

	Liferay.provide(
		window,
		'<portlet:namespace />modifiedPageDelta',
		function() {
			var settingsPanel = A.one('#soAnnouncementsDisplaySettingsPanel');

			<portlet:namespace />modified(settingsPanel);
		}
	);

	var displayPanel = A.one('#soAnnouncementsDisplayPanel');

	Liferay.provide(
		window,
		'<portlet:namespace />toggleCustomizeAnnouncementsDisplayed',
		function() {
			<portlet:namespace />modified(displayPanel);

			var customizeAnnouncementsDisplayed = A.one('#<portlet:namespace />customizeAnnouncementsDisplayed');

			if (customizeAnnouncementsDisplayed) {
				customizeAnnouncementsDisplayed.toggleClass('aui-helper-hidden');
			}
		}
	);

	var selects = displayPanel.all('.left-selector-column-content select');
	var initSelectsHTML = "";

	for (var i = selects._nodes.length - 1; i >= 0; --i) {
		initSelectsHTML = initSelectsHTML.concat(selects._nodes[i].innerHTML);
	}

	Liferay.on('inputmoveboxes:moveItem', function(event) {
		<portlet:namespace />afterMoveClick();
	})

	function <portlet:namespace />afterMoveClick() {
		var currSelectsHTML = "";

		for (var i = selects._nodes.length - 1; i >= 0; --i) {
			currSelectsHTML = currSelectsHTML.concat(selects._nodes[i].innerHTML);
		}

		if (initSelectsHTML != currSelectsHTML) {
			<portlet:namespace />modified(displayPanel);
		}
	}

	function <portlet:namespace />modified(panel) {
		var modifiedNotice = panel.one('.lfr-panel-title .modified-notice');

		if (modifiedNotice == null) {
			var displayTitle = panel.one('.lfr-panel-title');

			displayTitle.append('<span class="modified-notice"> (<liferay-ui:message key="modified" />) </span>');
		}
	}
</aui:script>