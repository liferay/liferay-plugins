<%
/**
 * Copyright (c) 2000-2009 Liferay, Inc. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
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
				boolean displayPrivateGroup = false;

				if ((GroupPermissionUtil.contains(permissionChecker, group.getGroupId(), ActionKeys.UPDATE)) ||
					(GroupPermissionUtil.contains(permissionChecker, group.getGroupId(), ActionKeys.DELETE)) ||
					(group.getType() == GroupConstants.TYPE_COMMUNITY_OPEN)) {

					displayPrivateGroup = true;
				}

				if (!myGroups.contains(group) && displayPrivateGroup && !group.getName().equals(GroupConstants.GUEST)) {
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