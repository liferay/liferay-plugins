<%--
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
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

<liferay-portlet:actionURL portletConfiguration="true" var="configurationActionURL" />

<aui:form action="<%= configurationActionURL %>" method="post" name="fm" onSubmit='<%= "event.preventDefault(); " + renderResponse.getNamespace() + "saveConfigurations();" %>'>
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />

	<liferay-portlet:renderURL portletConfiguration="true" var="configurationRenderURL">
		<portlet:param name="tabs1" value="<%= tabs1 %>" />
	</liferay-portlet:renderURL>

	<aui:input name="redirect" type="hidden" value="<%= configurationRenderURL %>" />

	<liferay-ui:panel-container extended="<%= true %>" id="soAnnouncementsConfigurationsPanelContainer" persistState="<%= true %>">
		<liferay-ui:panel collapsible="<%= true %>" extended="<%= true %>" id="displaySettingsPanel" persistState="<%= true %>" title="display-settings">
			<aui:fieldset>
				<aui:select label="entries-to-display-per-page" name="preferences--pageDelta--">

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

		<liferay-ui:panel collapsible="<%= true %>" extended="<%= true %>" id="announcementsDisplayedPanel" persistState="<%= true %>" title="announcements-displayed">
			<aui:input cssClass="customize-announcements-displayed" id="customizeAnnouncementsDisplayed" name="preferences--customizeAnnouncementsDisplayed--" title="customize-announcements-displayed" type="checkbox" value="<%= customizeAnnouncementsDisplayed %>" />

			<div class="<%= customizeAnnouncementsDisplayed ? "" : "hide" %>" id="<portlet:namespace />announcementsDisplayed">
				<div class="alert alert-info">
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
								List<KeyValuePair> leftList = new ArrayList<KeyValuePair>();

								for (Group curGroup : groups) {
									if (selectedScopeGroupIds.contains(String.valueOf(curGroup.getGroupId()))) {
										leftList.add(new KeyValuePair(String.valueOf(curGroup.getGroupId()), curGroup.getDescriptiveName(locale)));
									}
								}

								List<KeyValuePair> rightList = new ArrayList<KeyValuePair>();

								for (Group curGroup : groups) {
									KeyValuePair tempKeyValuePair = new KeyValuePair(String.valueOf(curGroup.getGroupId()), curGroup.getDescriptiveName(locale));

									if (!leftList.contains(tempKeyValuePair)) {
										rightList.add(tempKeyValuePair);
									}
								}
								%>

								<aui:input name="preferences--selectedScopeGroupIds--" type="hidden" />

								<div id="<portlet:namespace />scopeGroupIdsBoxes">
									<liferay-ui:input-move-boxes
										leftBoxName="currentScopeGroupIds"
										leftList="<%= leftList %>"
										leftReorder="true"
										leftTitle="displaying"
										rightBoxName="availableScopeGroupIds"
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
								List<KeyValuePair> leftList = new ArrayList<KeyValuePair>();

								for (Organization organization : organizations) {
									if (selectedScopeOrganizationIds.contains(String.valueOf(organization.getOrganizationId()))) {
										leftList.add(new KeyValuePair(String.valueOf(organization.getOrganizationId()), organization.getName()));
									}
								}

								List<KeyValuePair> rightList = new ArrayList<KeyValuePair>();

								for (Organization organization : organizations) {
									KeyValuePair tempKeyValuePair = new KeyValuePair(String.valueOf(organization.getOrganizationId()), organization.getName());

									if (!leftList.contains(tempKeyValuePair)) {
										rightList.add(tempKeyValuePair);
									}
								}
								%>

								<aui:input name="preferences--selectedScopeOrganizationIds--" type="hidden" />

								<div id="<portlet:namespace />scopeOrganizationIdsBoxes">
									<liferay-ui:input-move-boxes
										leftBoxName="currentScopeOrganizationIds"
										leftList="<%= leftList %>"
										leftReorder="true"
										leftTitle="displaying"
										rightBoxName="availableScopeOrganizationIds"
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
								List<KeyValuePair> leftList = new ArrayList<KeyValuePair>();

								for (UserGroup userGroup : userGroups) {
									if (selectedScopeUserGroupIds.contains(String.valueOf(userGroup.getUserGroupId()))) {
										leftList.add(new KeyValuePair(String.valueOf(userGroup.getUserGroupId()), userGroup.getName()));
									}
								}

								List<KeyValuePair> rightList = new ArrayList<KeyValuePair>();

								for (UserGroup userGroup : userGroups) {
									KeyValuePair tempKeyValuePair = new KeyValuePair(String.valueOf(userGroup.getUserGroupId()), userGroup.getName());

									if (!leftList.contains(tempKeyValuePair)) {
										rightList.add(tempKeyValuePair);
									}
								}
								%>

								<aui:input name="preferences--selectedScopeUserGroupIds--" type="hidden" />

								<div id="<portlet:namespace />scopeUserGroupIdsBoxes">
									<liferay-ui:input-move-boxes
										leftBoxName="currentScopeUserGroupIds"
										leftList="<%= leftList %>"
										leftReorder="true"
										leftTitle="displaying"
										rightBoxName="availableScopeUserGroupIds"
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
								List<KeyValuePair> leftList = new ArrayList<KeyValuePair>();

								for (Role role : roles) {
									if (selectedScopeRoleIds.contains(String.valueOf(role.getRoleId()))) {
										leftList.add(new KeyValuePair(String.valueOf(role.getRoleId()), role.getTitle(locale)));
									}
								}

								List<KeyValuePair> rightList = new ArrayList<KeyValuePair>();

								for (Role role : roles) {
									KeyValuePair tempKeyValuePair = new KeyValuePair(String.valueOf(role.getRoleId()), role.getTitle(locale));

									if (!leftList.contains(tempKeyValuePair)) {
										rightList.add(tempKeyValuePair);
									}
								}
								%>

								<aui:input name="preferences--selectedScopeRoleIds--" type="hidden" />

								<div id="<portlet:namespace />scopeRoleIdsBoxes">
									<liferay-ui:input-move-boxes
										leftBoxName="currentScopeRoleIds"
										leftList="<%= leftList %>"
										leftReorder="true"
										leftTitle="displaying"
										rightBoxName="availableScopeRoleIds"
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

		<aui:button type="cancel" />
	</aui:button-row>
</aui:form>

<aui:script use="aui-base">
	var form = A.one('#<portlet:namespace />fm');

	var modified = function(panel) {
		var modifiedNotice = panel.one('.accordion-heading .modified-notice');

		if (modifiedNotice == null) {
			var displayTitle = panel.one('.accordion-heading');

			displayTitle.append('<span class="accordion-toggle modified-notice"> (<liferay-ui:message key="modified" />) </span>');
		}
	}

	var customizeAnnouncementsDisplayedCheckbox = form.one('#<portlet:namespace />customizeAnnouncementsDisplayed');

	customizeAnnouncementsDisplayedCheckbox.on(
		'change',
		function() {
			var announcementsdisplayedPanel = A.one('#announcementsDisplayedPanel');

			modified(A.one('#announcementsDisplayedPanel'));

			var announcementsDisplayed = form.one('#<portlet:namespace />announcementsDisplayed');

			if (announcementsDisplayed) {
				announcementsDisplayed.toggleClass('hide');
			}
		}
	);

	var selected = form.all('.left-selector');

	var selectedHTML = '';

	for (var i = selected._nodes.length - 1; i >= 0; --i) {
		selectedHTML = selectedHTML.concat(selected._nodes[i].innerHTML);
	}

	Liferay.on(
		'inputmoveboxes:moveItem',
		function(event) {
			var currSelectedHTML = '';

			for (var i = selected._nodes.length - 1; i >= 0; --i) {
				currSelectedHTML = currSelectedHTML.concat(selected._nodes[i].innerHTML);
			}

			if (selectedHTML != currSelectedHTML) {
				modified(A.one('#announcementsDisplayedPanel'));
			}
		}
	);

	var pageDeltaInput = form.one('select[name=<portlet:namespace />preferences--pageDelta--]');

	pageDeltaInput.on(
		'change',
		function(event) {
			modified(A.one('#displaySettingsPanel'));
		}
	);
</aui:script>

<aui:script>
	Liferay.provide(
		window,
		'<portlet:namespace />saveConfigurations',
		function() {
			if (document.<portlet:namespace />fm.<portlet:namespace />selectedScopeGroupIds) {
				document.<portlet:namespace />fm.<portlet:namespace />selectedScopeGroupIds.value = Liferay.Util.listSelect(document.<portlet:namespace />fm.<portlet:namespace />currentScopeGroupIds);
			}

			if (document.<portlet:namespace />fm.<portlet:namespace />selectedScopeOrganizationIds) {
				document.<portlet:namespace />fm.<portlet:namespace />selectedScopeOrganizationIds.value = Liferay.Util.listSelect(document.<portlet:namespace />fm.<portlet:namespace />currentScopeOrganizationIds);
			}

			if (document.<portlet:namespace />fm.<portlet:namespace />selectedScopeRoleIds) {
				document.<portlet:namespace />fm.<portlet:namespace />selectedScopeRoleIds.value = Liferay.Util.listSelect(document.<portlet:namespace />fm.<portlet:namespace />currentScopeRoleIds);
			}

			if (document.<portlet:namespace />fm.<portlet:namespace />selectedScopeUserGroupIds) {
				document.<portlet:namespace />fm.<portlet:namespace />selectedScopeUserGroupIds.value = Liferay.Util.listSelect(document.<portlet:namespace />fm.<portlet:namespace />currentScopeUserGroupIds);
			}

			submitForm(document.<portlet:namespace />fm);
		},
		['liferay-util-list-fields']
	);
</aui:script>