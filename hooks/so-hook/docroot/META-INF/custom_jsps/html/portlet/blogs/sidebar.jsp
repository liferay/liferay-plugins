<%
/**
 * Copyright (c) 2008-2010 Liferay, Inc. All rights reserved.
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

<%@ include file="/html/portlet/blogs/init.jsp" %>

<div class="sidebar">
	<div class="search">
		<input class="search-input" id="<portlet:namespace />keywords" name="<portlet:namespace />keywords" type="text" />

		<input class="search-button" type="submit" value="<liferay-ui:message key="search" />" />
	</div>

	<%
	List<BlogsEntry> results = BlogsEntryLocalServiceUtil.getGroupEntries(scopeGroupId, WorkflowConstants.STATUS_APPROVED, 0, 5);
	%>

	<c:choose>
		<c:when test="<%= !results.isEmpty() %>">
			<h2><liferay-ui:message key="recent-entries" /></h2>

			<div class="recent-entries-list">
				<ul class="disc">

					<%
					for (BlogsEntry entry : results) {
						String title = entry.getTitle();
						String author = PortalUtil.getUserName(entry.getUserId(), entry.getUserName());
					%>

						<portlet:renderURL var="viewEntryURL">
							<portlet:param name="struts_action" value="/blogs/view_entry" />
							<portlet:param name="redirect" value="<%= currentURL %>" />
							<portlet:param name="urlTitle" value="<%= entry.getUrlTitle() %>" />
						</portlet:renderURL>

						<li>
							<a href="<%= viewEntryURL %>"><%= title %></a> <span class="author"><%= author %></span>
						</li>

					<%
					}
					%>

				</ul>
			</div>
		</c:when>
		<c:otherwise>
			<br />
		</c:otherwise>
	</c:choose>

	<c:if test="<%= GroupPermissionUtil.contains(permissionChecker, scopeGroupId, ActionKeys.PERMISSIONS) %>">
		<h2><liferay-ui:message key="quick-links" /></h2>

		<ul class="disc">
			<liferay-security:permissionsURL
				modelResource="com.liferay.portlet.blogs"
				modelResourceDescription="<%= portletDisplay.getTitle() %>"
				resourcePrimKey="<%= String.valueOf(scopeGroupId) %>"
				var="permissionsURL"
			/>

			<li>
				<a href="<%= permissionsURL %>"><liferay-ui:message key="permissions" /></a>
			</li>
		</ul>
	</c:if>
</div>