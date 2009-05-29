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

<liferay-util:buffer var="html">
	<liferay-util:include page="/html/portlet/asset_publisher/view.portal.jsp" />
</liferay-util:buffer>

<%
String[] htmlFragments = StringUtil.split(html, "<a class=\"title-link\" ");
%>

<%
for (int i = 0; i < htmlFragments.length; i++) {
	String htmlFragment = htmlFragments[i];
%>

	<c:choose>
		<c:when test="<%= (i == 0) || htmlFragments.length == 1 %>">
			<%= htmlFragment %>
		</c:when>
		<c:otherwise>

			<%
			int x = htmlFragment.indexOf("href=\"");
			int y = htmlFragment.indexOf("\"", x + 6);
			int z = htmlFragment.indexOf(">", y);
			%>

			<a class="title-link" href="javascript: ;" onClick="<portlet:namespace />loadRelatedContent('<%= htmlFragment.substring(x + 6, y) %>');">

			<%= htmlFragment.substring(z + 1) %>
		</c:otherwise>
	</c:choose>

<%
}
%>

<c:if test="<%= htmlFragments.length > 1 %>">
	<script type="text/javascript">
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
</c:if>