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
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Affero General Public License
 * for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * Liferay Social Office. If not, see http://www.gnu.org/licenses/agpl-3.0.html.
 */
--%>

<%@ include file="/init.jsp" %>

<%
String tabs1 = ParamUtil.getString(request, "tabs1", "my-sites");

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setWindowState(WindowState.NORMAL);

portletURL.setParameter("tabs1", tabs1);

pageContext.setAttribute("portletURL", portletURL);
%>

<form action="<%= portletURL.toString() %>" method="get" name="<portlet:namespace />fm">
<liferay-portlet:renderURLParams varImpl="portletURL" />

<liferay-ui:search-container
	delta="<%= 30 %>"
	emptyResultsMessage="no-communities-were-found"
>

	<%
	String name = ParamUtil.getString(request, "name");
	String searchName = DAOParamUtil.getLike(request, "name");
	%>

	<div>
		<input id="<portlet:namespace />name" name="<portlet:namespace />name" size="30" type="text" value="<%= HtmlUtil.escape(name) %>" />

		<input type="submit" value="<liferay-ui:message key="search" />" />
	</div>

	<div class="control-wrapper">
		<liferay-ui:tabs
			names="my-sites,open-sites"
			url="<%= portletURL.toString() %>"
		/>

		<c:if test="<%= PortalPermissionUtil.contains(permissionChecker, ActionKeys.ADD_COMMUNITY) %>">
			<liferay-portlet:renderURL windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>" var="addSiteURL">
				<liferay-portlet:param name="jspPage" value="/sites/edit_community.jsp" />
			</liferay-portlet:renderURL>

			<a class="add-site" href="javascript:;" onClick="Liferay.SO.Sites.displayPopup('<%= addSiteURL %>','<liferay-ui:message key="add-site" />');"><liferay-ui:message key="add-site" /></a>
		</c:if>
	</div>

	<%
	LinkedHashMap groupParams = new LinkedHashMap();

	groupParams.put("usersGroups", new Long(user.getUserId()));

	List<Group> displayGroups = null;

	List<Group> myGroups = GroupLocalServiceUtil.search(company.getCompanyId(), searchName, null, groupParams, searchContainer.getStart(), searchContainer.getEnd(), searchContainer.getOrderByComparator());

	if (tabs1.equals("my-sites")) {
		displayGroups = myGroups;
	}
	else {
		groupParams = new LinkedHashMap();

		List types = new ArrayList();

		types.add(new Integer(GroupConstants.TYPE_COMMUNITY_OPEN));

		groupParams.put("types", types);

		List<Group> allGroups = GroupLocalServiceUtil.search(company.getCompanyId(), searchName, null, groupParams, searchContainer.getStart(), searchContainer.getEnd(), searchContainer.getOrderByComparator());

		List<Group> openGroups = new ArrayList<Group>(allGroups.size());

		for (Group group : allGroups) {
			if (!myGroups.contains(group) && !group.getName().equals(GroupConstants.GUEST)) {
				openGroups.add(group);
			}
		}

		displayGroups = openGroups;
	}
	%>

	<liferay-ui:search-container-results
		results="<%= displayGroups %>"
		total="<%= displayGroups.size() %>"
	/>

	<liferay-ui:search-container-row
		className="com.liferay.portal.model.Group"
		keyProperty="groupId"
		modelVar="group"
	>
		<liferay-ui:search-container-column-text>
			<liferay-portlet:actionURL windowState="<%= LiferayWindowState.NORMAL.toString() %>" portletName="<%= PortletKeys.MY_PLACES %>" var="rowURL">
				<liferay-portlet:param name="struts_action" value="/my_places/view" />
				<liferay-portlet:param name="groupId" value="<%= String.valueOf(group.getGroupId()) %>" />
			</liferay-portlet:actionURL>

			<%
			boolean socialOfficeEnabled = GetterUtil.getBoolean(group.getExpandoBridge().getAttribute("socialOfficeEnabled"));
			%>

			<div<%= socialOfficeEnabled ? " class=\"social-office-enabled\"" : StringPool.BLANK %>>
				<a href="<%= rowURL %>"><%= HtmlUtil.escape(group.getDescriptiveName()) %></a>

				<c:if test="<%= Validator.isNotNull(group.getDescription()) %>">
					<img alt="arrow" class="description-toggle" src="<%= themeDisplay.getPathThemeImage() %>/custom/arrow_right.png" />

					<div class="aui-helper-hidden description">
						<%= StringUtil.shorten(HtmlUtil.escape(group.getDescription()), 200) %>
					</div>
				</c:if>
			</div>
		</liferay-ui:search-container-column-text>

		<liferay-ui:search-container-column-jsp
			align="right"
			path="/sites/community_action.jsp"
			valign="top"
		/>
	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator />
</liferay-ui:search-container>

</form>

<aui:script>
	AUI().ready(
		function() {
			Liferay.SO.Sites.init();
		}
	)
</aui:script>