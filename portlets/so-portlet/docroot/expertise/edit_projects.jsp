<%--
/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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
User selUser = PortalUtil.getSelectedUser(request);

if (selUser == null) {
	selUser = user;
}

List<ProjectsEntry> projectsEntries = ProjectsEntryLocalServiceUtil.getUserProjectsEntries(selUser.getUserId());

Calendar curCal = CalendarFactoryUtil.getCalendar(timeZone, locale);

int[] monthIds = CalendarUtil.getMonthIds();
String[] months = CalendarUtil.getMonths(locale);

int yearRangeStart = curCal.get(Calendar.YEAR) - 70;
int yearRangeEnd = curCal.get(Calendar.YEAR);

int[] projectsEntriesIndexes = new int[projectsEntries.size()];

for (int i = 0; i < projectsEntries.size() ; i++) {
	projectsEntriesIndexes[i] = i;
}

if (projectsEntries.isEmpty()) {
	projectsEntries = new ArrayList<ProjectsEntry>();

	projectsEntries.add(new ProjectsEntryImpl());

	projectsEntriesIndexes = new int[] {0};
}
%>

<liferay-ui:error-marker key="errorSection" value="expertise" />

<h3><liferay-ui:message key="projects" /></h3>

<aui:fieldset>

	<%
	for (int i = 0; i < projectsEntriesIndexes.length; i++) {
		int projectsEntriesIndex = projectsEntriesIndexes[i];

		ProjectsEntry projectsEntry = projectsEntries.get(i);

		Calendar startDate = CalendarFactoryUtil.getCalendar();
		Calendar endDate = CalendarFactoryUtil.getCalendar();

		boolean current = true;

		if (projectsEntry.getStartDate() != null) {
			startDate.setTime(projectsEntry.getStartDate());
		}

		if (projectsEntry.getEndDate() != null) {
			endDate.setTime(projectsEntry.getEndDate());

			current = false;
		}
	%>

		<aui:model-context bean="<%= projectsEntry %>" model="<%= ProjectsEntry.class %>" />

		<div class="lfr-form-row">
			<div class="row-fields">
				<aui:input name='<%= "projectsEntryId" + projectsEntriesIndex %>' type="hidden" value="<%= projectsEntry.getProjectsEntryId() %>" />

				<aui:input fieldParam='<%= "projectsEntryTitle" + projectsEntriesIndex %>' name="title" />

				<div style="clear: both;"><!-- --></div>

				<aui:layout cssClass="lfr-form-row-inline">
					<aui:column>

						<%
						String fieldParam = "projectsEntryStartDate";

						Calendar selDate = startDate;
						%>

						<div class="aui-field">
							<label class="aui-field-label" for="<portlet:namespace /><%= fieldParam %>"><liferay-ui:message key="start-date" /></label>

							<%@ include file="/expertise/select_date.jspf" %>
						</div>
					</aui:column>
					<aui:column>

						<%
						String fieldParam = "projectsEntryEndDate";

						Calendar selDate = endDate;
						%>

						<div class="aui-field">
							<label class="aui-field-label" for="<portlet:namespace /><%= fieldParam %>"><liferay-ui:message key="end-date" /></label>

							<%@ include file="/expertise/select_date.jspf" %>
						</div>
					</aui:column>
					<aui:column>

						<%
						String fieldParam = "projectsEntryCurrent" + projectsEntriesIndex;
						%>

						<div class="aui-field">
							<label class="aui-field-label" for="<portlet:namespace /><%= fieldParam %>"><liferay-ui:message key="current" /></label>

							<liferay-ui:input-checkbox param="<%= fieldParam %>" defaultValue="<%= current %>" />
						</div>
					</aui:column>
				</aui:layout>

				<aui:input fieldParam='<%= "projectsEntryDescription" + projectsEntriesIndex %>' name="description" />
			</div>
		</div>

	<%
	}
	%>

	<aui:input name="projectsEntriesIndexes" type="hidden" value="<%= StringUtil.merge(projectsEntriesIndexes) %>" />
</aui:fieldset>

<aui:script use="liferay-auto-fields">
	Liferay.once(
		'formNavigator:reveal<portlet:namespace />expertise',
		function() {
			new Liferay.AutoFields(
				{
					contentBox: '#<portlet:namespace />expertise > fieldset',
					fieldIndexes: '<portlet:namespace />projectsEntriesIndexes'
				}
			).render();
		}
	);
</aui:script>