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

<%@ include file="/html/portlet/asset_publisher/init.jsp" %>

<liferay-util:include page="/html/portlet/asset_publisher/view.portal.jsp" />

<script type="text/javascript">
	var titleLinks = jQuery('li.title-list > a');

	titleLinks.each(
		function(i) {
			var titleLink = jQuery(this);

			var titleURL = this.href;

			titleLink.attr(
				{
					'href': 'javascript:;'
				}
			);

			titleLink.click(
				function() {
					<portlet:namespace />loadRelatedContent(titleURL);
				}
			);
		}
	);

	function <portlet:namespace />loadRelatedContent(titleURL) {
		var portletContent = jQuery('.portlet-asset-publisher .portlet-content');

		portletContent.html('<div class="loading-animation" />');

		portletContent.load(
			titleURL + ' .portlet-asset-publisher .portlet-content-container',
			{
				uuid: Math.random() * 9999
			}
		);
	}
</script>