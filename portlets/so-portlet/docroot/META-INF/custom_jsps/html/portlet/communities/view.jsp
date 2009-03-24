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

<%@ include file="/html/portlet/communities/init.jsp" %>

<%
String tabs1 = ParamUtil.getString(request, "tabs1", "my-sites");

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setWindowState(WindowState.NORMAL);

portletURL.setParameter("struts_action", "/communities/view");
portletURL.setParameter("tabs1", tabs1);

pageContext.setAttribute("portletURL", portletURL);
%>

<form action="<%= portletURL.toString() %>" method="get" name="<portlet:namespace />fm">
<liferay-portlet:renderURLParams varImpl="portletURL" />

<h1><liferay-ui:message key="sites" /></h1>

<div class="site-list">
	<liferay-ui:search-container
		delta="<%= 1000 %>"
		headerNames=""
		searchContainer="<%= new GroupSearch(renderRequest, portletURL) %>"
	>
		<liferay-ui:search-form
			page="/html/portlet/communities/group_search.jsp"
		/>

		<div class="control-wrapper">
			<liferay-ui:tabs
				names="my-sites,open-sites"
				url="<%= portletURL.toString() %>"
			/>

			<c:if test="<%= PortalPermissionUtil.contains(permissionChecker, ActionKeys.ADD_COMMUNITY) %>">
				<a class="add-site" href="javascript: ;"><liferay-ui:message key="add-site" /></a>
			</c:if>
		</div>

		<%
		GroupSearchTerms searchTerms = (GroupSearchTerms)searchContainer.getSearchTerms();

		LinkedHashMap groupParams = new LinkedHashMap();

		groupParams.put("usersGroups", new Long(user.getUserId()));

		List<Group> displayGroups = null;

		List<Group> myGroups = GroupLocalServiceUtil.search(company.getCompanyId(), searchTerms.getName(), searchTerms.getDescription(), groupParams, searchContainer.getStart(), searchContainer.getEnd(), searchContainer.getOrderByComparator());

		if (tabs1.equals("my-sites")) {
			displayGroups = myGroups;
		}
		else {
			groupParams = new LinkedHashMap();

			List<Group> allGroups = GroupLocalServiceUtil.search(company.getCompanyId(), searchTerms.getName(), searchTerms.getDescription(), groupParams, searchContainer.getStart(), searchContainer.getEnd(), searchContainer.getOrderByComparator());

			List<Group> openGroups = new ArrayList<Group>(allGroups.size());

			for (Group group : allGroups) {
				boolean displayGroup = true;

				if (group.getType() == GroupConstants.TYPE_COMMUNITY_PRIVATE) {
					displayGroup = false;
				}

				if (!myGroups.contains(group) && displayGroup && !group.getName().equals(GroupConstants.GUEST)) {
					 openGroups.add(group);
				}
			}

			displayGroups = openGroups;
		}
		%>

		<%@ include file="/html/portlet/communities/community_iterator.jspf" %>
	</liferay-ui:search-container>
</div>

</form>

<br />

<script type="text/javascript">
	function <portlet:namespace />displayPopup(popup, popupUrl, popupTitle) {
		if ((popup == null) || !(popup[0].parentNode && popup[0].parentNode.tagName)) {
			popup = new Liferay.Popup(
				{
					title: popupTitle,
					className: 'site-dialog',
					resizable: false,
					height: 'auto',
					position: [15,15],
					width: '600px',
					close: function() {
						popup = null;
					}
				}
			);
		}
		else {
			var popupContainer = popup.parents('.ui-dialog');
			popupContainer.find('.ui-dialog-title').text(popupTitle);
			var popupHelper = popupContainer.data('ui-helper-drag');

			if (popupHelper) {
				popupHelper.find('.ui-dialog-title').text(popupTitle);
			}
		}

		popup.html('<div class="loading-animation" />');

		jQuery.ajax(
			{
				url: popupUrl,
				success: function(message) {
					popup.html(message);
				}
			}
		);

		return popup;
	}

	jQuery(
		function() {
			var popup;

			var addSite = jQuery('.add-site');

			addSite.livequery(
				"click",
				function(event) {

					<%
					PortletURL ajaxURL = renderResponse.createRenderURL();

					ajaxURL.setWindowState(LiferayWindowState.EXCLUSIVE);

					ajaxURL.setParameter("struts_action", "/communities/edit_community");
					ajaxURL.setParameter("redirect", ajaxURL.toString());
					%>

					var href = '<portlet:renderURL windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>"><portlet:param name="struts_action" value="/communities/edit_community" /><portlet:param name="redirect" value="<%= ajaxURL.toString() %>" /><portlet:param name="callingPageURL" value="<%= currentURL %>" /></portlet:renderURL>';

					popup = <portlet:namespace />displayPopup(popup, href, "Add Site");

					return false;
				}
			);

			var editSite = jQuery('.edit-site');

			editSite.livequery(
				"click",
				function(event) {
					var href = jQuery(this).attr('href');

					href = href.replace(/p_p_state=<%= LiferayWindowState.MAXIMIZED %>/gim, 'p_p_state=<%= LiferayWindowState.EXCLUSIVE %>');
					href = href.replace(/redirect=([^&]+)/gim, escape('redirect=<%= UnicodeFormatter.toString(ajaxURL.toString()) %>'));
					href += "&<portlet:namespace />callingPageURL=<%= currentURL %>";

					popup = <portlet:namespace />displayPopup(popup, href, "Edit Site");

					return false;
				}

			);
		}
	)
</script>

<input id="<portlet:namespace />pageURL" type="hidden" value="<%= currentURL %>" />
<input id="<portlet:namespace />popupURL" type="hidden" value="<%= ajaxURL.toString() %>" />