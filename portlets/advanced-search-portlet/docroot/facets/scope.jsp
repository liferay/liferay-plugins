<%--
/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */
--%>

<%@ include file="/init.jsp" %>

<%
Facet facet = (Facet)request.getAttribute("search-search.jsp-facet");

long groupId = ParamUtil.getLong(request, "groupId");

Group group = themeDisplay.getScopeGroup();
%>

<aui:field-wrapper cssClass="scope" label="" name="scope">
	<aui:select inlineField="<%= true %>" label="" name="groupId">
		<aui:option label="everything" selected="<%= groupId == 0 %>" value="0" />
		<aui:option label='<%= "this-" + (group.isOrganization() ? "organization" : "site") %>' selected="<%= groupId != 0 %>" value="<%= group.getGroupId() %>" />
	</aui:select>
</aui:field-wrapper>