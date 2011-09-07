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

<%@ include file="/html/taglib/init.jsp" %>

<ul class="taglib-my-places">
	<liferay-util:include page="/sites/view_starred_sites.jsp" portletId="5_WAR_soportlet" />

	<liferay-portlet:renderURL portletName="5_WAR_soportlet" varImpl="viewSitesURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
		<portlet:param name="jspPage" value="/sites/view_sites.jsp" />
	</liferay-portlet:renderURL>

	<li>
		<a class="open-sites-directory" href="javascript:;" onClick="<portlet:namespace />displayDirectoryPopup('<%= viewSitesURL %>', '<liferay-ui:message key="more-sites" />');">
			<span class="site-name">
				<liferay-ui:icon
					message="more-sites"
					src='<%= themeDisplay.getPathContext() + "/html/icons/more_sites.png" %>'
				/>

				<liferay-ui:message key="more-sites" />
			</span>
		</a>
	</li>
</ul>

<aui:script>
	Liferay.provide(
		window,
		'<portlet:namespace />displayDirectoryPopup',
		function(url, title) {
			var A = AUI();

			var dialog = new A.Dialog(
				{
					align: {
						node: null,
						points: ['tc', 'tc']
					},
					constrain2view: true,
					cssClass: 'so-portlet-sites-dialog',
					resizable: true,
					title: title,
					width: 650
				}
			).plug(
				A.Plugin.IO,
				{
					uri: url
				}
			).render();
		},
		['aui-base', 'aui-dialog', 'aui-io']
	);
</aui:script>