<%--
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
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
User selUser = (User)request.getAttribute("user.selUser");
%>

<aui:model-context bean="<%= selUser %>" model="<%= User.class %>" />

<liferay-ui:asset-categories-error />

<liferay-ui:asset-tags-error />

<h3><liferay-ui:message key="categorization" /></h3>

<aui:fieldset>
	<aui:input name="categories" type="assetCategories" />

	<aui:input name="tags" type="assetTags"  />
</aui:fieldset>

<aui:script>
	function <portlet:namespace />getSuggestionsContent() {

		<%
		StringBundler sb = new StringBundler();

		if (selUser.getComments() != null) {
			sb.append(selUser.getComments());
		}

		if (selUser.getJobTitle() != null) {
			sb.append(StringPool.SPACE);
			sb.append(selUser.getJobTitle());
		}
		%>

		return '<%= HtmlUtil.escape(HtmlUtil.replaceNewLine(sb.toString())) %>'
	}
</aui:script>