<%
/**
 * Copyright (c) 2008-2009 Liferay, Inc. All rights reserved.
 *
 * This file is part of Liferay Social Office. Liferay Social Office is free
 * software: you can redistribute it and/or modify it under the terms of the GNU
 * Affero General Public License as published by the Free Software Foundation,
 * either version 3 of the License, or (at your option) any later version.
 *
 * Liferay Social Office is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Affero General Public License
 * for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * Liferay Social Office. If not, see http://www.gnu.org/licenses/agpl-3.0.html.
 */
%>

<%@ include file="/init.jsp" %>

<script type="text/javascript">
	jQuery(
		function() {
			var form = jQuery(document.<portlet:namespace />fm);

			jQuery('#<portlet:namespace />submit').click(
				function() {
					Liferay.trigger('submitForm', {form: form});
				}
			);

			form.ajaxForm(
				{
					type: "POST",
					beforeSubmit: function() {
						document.getElementById('<portlet:namespace />submit').disabled = true;
					},
					success: function() {
						Liferay.SO.Profiles.displayUserProfile(<%= user.getUserId() %>);
					}
				}
			);
		}
	);
</script>

<h1><%= user.getFullName() %> : <liferay-ui:message key="edit-projects" /></h1>

<form action="<portlet:actionURL name="updateUserProjects"></portlet:actionURL>" name="<portlet:namespace />fm">
<input name="<portlet:namespace />userId" type="hidden" value="<%= user.getUserId() %>" />

<table width="100%">
<tr>
	<td>
		<img alt="<%= user.getFullName() %>" src="<%= themeDisplay.getPathImage() %>/user_<%= (user.isFemale() ? "female" : "male") %>_portrait?img_id=<%= user.getPortraitId() %>&t=<%= ImageServletTokenUtil.getToken(user.getPortraitId()) %>" />

		<div class="profile-controls">
			<a href="javascript: ;" onClick="Liferay.SO.Profiles.displayUserProfile(<%= user.getUserId() %>);"><liferay-ui:message key="cancel-edit" /></a>
		</div>
	</td>
	<td class="profile-area edit-profile">
		<table width="100%">
		<tr>
			<td colspan="2">
				<h3 class="first"><liferay-ui:message key="projects" /></h3>
			</td>
		</tr>
		<tr>
			<td class="lfr-label">
				<liferay-ui:message key="projects" />
			</td>
			<td>

				<%
				List<ProjectsEntry> projectsEntries = ProjectsEntryLocalServiceUtil.getUserProjectsEntries(user.getUserId());

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

					projectsEntries.add(null);

					projectsEntriesIndexes = new int[]{0};
				}
				%>

				<div id="projectsEntries">
					<fieldset class="block-labels">

						<%
						for (int i = 0; i < projectsEntriesIndexes.length; i++) {
							int projectsEntriesIndex = projectsEntriesIndexes[i];

							ProjectsEntry projectsEntry = projectsEntries.get(i);

							long projectsEntryId = 0;

							Calendar startDate = CalendarFactoryUtil.getCalendar();
							Calendar endDate = CalendarFactoryUtil.getCalendar();

							boolean current = true;

							if (projectsEntry != null) {
								projectsEntryId = projectsEntry.getProjectsEntryId();

								startDate.setTime(projectsEntry.getStartDate());

								if (projectsEntry.getEndDate() != null) {
									endDate.setTime(projectsEntry.getEndDate());

									current = false;
								}
							}
						%>

							<div class="lfr-form-row">
								<div class="row-fields">

									<%
									String fieldParam = "projectsEntryId" + projectsEntriesIndex;
									%>

									<input id="<portlet:namespace /><%= fieldParam %>" name="<portlet:namespace /><%= fieldParam %>" type="hidden" value="<%= projectsEntryId %>" />

									<%
									fieldParam = "projectsEntryTitle" + projectsEntriesIndex;
									%>

									<div class="ctrl-holder">
										<label for="<portlet:namespace /><%= fieldParam %>"><liferay-ui:message key="title" /></label>

										<liferay-ui:input-field model="<%= ProjectsEntry.class %>" bean="<%= projectsEntry %>" field="title" fieldParam="<%= fieldParam %>" />
									</div>

									<div class="date-selectors">

										<%
										fieldParam = "projectsEntryStartDate";
										%>

										<div class="ctrl-holder">
											<label for="<portlet:namespace /><%= fieldParam %>"><liferay-ui:message key="start-date" /></label>

											<%
											int selMonthValue = startDate.get(Calendar.MONTH);
											int selYearValue = startDate.get(Calendar.YEAR);

											String inputId = StringPool.BLANK;
											%>

											<%@ include file="/profiles/select_date.jsp" %>

										</div>

										<%
										fieldParam = "projectsEntryEndDate";
										%>

										<div class="ctrl-holder">
											<label for="<portlet:namespace /><%= fieldParam %>"><liferay-ui:message key="end-date" /></label>

											<%
											selMonthValue = endDate.get(Calendar.MONTH);
											selYearValue = endDate.get(Calendar.YEAR);
											%>

											<%@ include file="/profiles/select_date.jsp" %>

										</div>

										<%
										fieldParam = "projectsEntryCurrent" + projectsEntriesIndex;
										%>

										<div class="ctrl-holder">
											<label for="<portlet:namespace /><%= fieldParam %>"><liferay-ui:message key="current" /></label>

											<liferay-ui:input-checkbox param="<%= fieldParam %>" defaultValue="<%= current %>" />
										</div>
									</div>

									<%
									fieldParam = "projectsEntryDescription" + projectsEntriesIndex;
									%>

									<div class="ctrl-holder">
										<label for="<portlet:namespace /><%= fieldParam %>"><liferay-ui:message key="description" /></label>

										<liferay-ui:input-field model="<%= ProjectsEntry.class %>" bean="<%= projectsEntry %>" field="description" fieldParam="<%= fieldParam %>" />
									</div>

									<%
									fieldParam = "projectsEntryOtherMembers" + projectsEntriesIndex;
									%>

									<div class="ctrl-holder last">
										<label for="<portlet:namespace /><%= fieldParam %>"><liferay-ui:message key="other-members" /></label>

										<liferay-ui:input-field model="<%= ProjectsEntry.class %>" bean="<%= projectsEntry %>" field="data" fieldParam="<%= fieldParam %>" />
									</div>
								</div>
							</div>

						<%
						}
						%>

					</fieldset>

					<script type="text/javascript">
						jQuery(
							function () {
								new Liferay.AutoFields(
									{
										container: '#projectsEntries > fieldset',
										baseRows: '#projectsEntries > fieldset .lfr-form-row',
										fieldIndexes: '<portlet:namespace />projectsEntriesIndexes'
									}
								);
							}
						);
					</script>
				</div>

			</td>
		</tr>
		</table>

		<br />

		<div>
			<input id="<portlet:namespace />submit" type="submit" value="<liferay-ui:message key="save" />" />

			<input type="button" value="<liferay-ui:message key="cancel" />" onClick="Liferay.SO.Profiles.displayUserProfile(<%= user.getUserId() %>);" />
		</div>
	</td>
</tr>
</table>

</form>