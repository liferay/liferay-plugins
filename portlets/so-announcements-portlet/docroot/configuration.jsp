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

List<Group> groups = GroupLocalServiceUtil.getUserGroups(user.getUserId(), true);
%>

<liferay-portlet:renderURL portletConfiguration="true" var="portletURL">
	<portlet:param name="redirect" value="<%= redirect %>" />
</liferay-portlet:renderURL>

<liferay-portlet:actionURL portletConfiguration="true" var="configurationURL" />

<aui:form action="<%= configurationURL %>" method="post" name="fm" onSubmit='<%= "event.preventDefault(); " + renderResponse.getNamespace() + "saveConfiguration();" %>'>
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />
	<aui:input name="redirect" type="hidden" value="<%= redirect %>" />

	<liferay-ui:panel-container extended="<%= true %>" id="soAnnouncementsConfigurationsPanelContainer" persistState="<%= true %>">
		<liferay-ui:panel collapsible="<%= true %>" extended="<%= true %>" id="soAnnouncementsDisplaySettingsPanel" persistState="<%= true %>" title="display-settings">
			<aui:fieldset>
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

		<liferay-ui:panel collapsible="<%= true %>" extended="<%= true %>" id="soAnnouncementsDisplayPanel" persistState="<%= true %>" title="announcements-displayed">
			<div id="<portlet:namespace />customizeAnnouncementsDisplayed">
				<div class="portlet-msg-info">
					<liferay-ui:message key="general-annnouncements-will-always-be-shown-select-any-other-distribution-scopes-you-would-like-to-display" />
				</div>

				<c:if test="<%= !groups.isEmpty() %>">
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
				</c:if>
			</div>
		</liferay-ui:panel>
	</liferay-ui:panel-container>

	<aui:button-row>
		<aui:button type="submit" />
	</aui:button-row>
</aui:form>

<aui:script>
	Liferay.provide(
		window,
		'<portlet:namespace />saveConfiguration',
		function() {
			if (document.<portlet:namespace />fm.<portlet:namespace />selectedScopeGroups) {
				document.<portlet:namespace />fm.<portlet:namespace />selectedScopeGroups.value = Liferay.Util.listSelect(document.<portlet:namespace />fm.<portlet:namespace />currentScopeGroups);
			}

			submitForm(document.<portlet:namespace />fm);
		},
		['liferay-util-list-fields']
	);
</aui:script>