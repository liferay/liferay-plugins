<%--
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
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

<%@ include file="/search/init.jsp" %>

<liferay-portlet:renderURL varImpl="searchURL" windowState="<%= WindowState.MAXIMIZED.toString() %>">
	<portlet:param name="mvcPath" value="/search/search.jsp" />
</liferay-portlet:renderURL>

<aui:nav-bar>
	<aui:nav-bar-search cssClass="pull-right">
		<div class="form-search">
			<aui:form action="<%= searchURL %>" method="get" name="searchFm">
				<liferay-portlet:renderURLParams varImpl="searchURL" />

				<liferay-ui:input-search />
			</aui:form>
		</div>
	</aui:nav-bar-search>
</aui:nav-bar>