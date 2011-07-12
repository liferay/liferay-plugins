<%--
/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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
User user2 = (User)request.getAttribute("view_user.jsp-user");

List<ProjectsEntry> projectsEntries = null;

if (user2 != null) {
	projectsEntries = ProjectsEntryLocalServiceUtil.getUserProjectsEntries(user2.getUserId());
}
%>

<c:if test="<%= (projectsEntries != null) && !projectsEntries.isEmpty() %>">

	<%
	for (ProjectsEntry projectsEntry : projectsEntries) {
		String startDate = dateFormatDate.format(projectsEntry.getStartDate());

		String endDate = null;

		if (projectsEntry.getEndDate() != null) {
			endDate = dateFormatDate.format(projectsEntry.getEndDate());
		}
		else {
			endDate = LanguageUtil.get(pageContext, "current");
		}
	%>

		<div class="project">
			<h3 class="project-title"><%= projectsEntry.getTitle() %></h3>

			<div class="project-date">
				<%= startDate %> - <%= endDate %>
			</div>

			<div class="project-description">
				<%= projectsEntry.getDescription() %>
			</div>
		</div>

	<%
	}
	%>

</c:if>