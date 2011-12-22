<%--
/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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
	<portlet:param name="jspPage" value="/search/search.jsp" />
</liferay-portlet:renderURL>

<aui:form action="<%= searchURL %>" method="get" name="searchFm">
	<liferay-portlet:renderURLParams varImpl="searchURL" />

	<aui:fieldset>
		<table class="lfr-table">
		<tr>
			<td width="99%">
				<aui:input cssClass="kb-search-keywords" label="" name="keywords" title="search-articles" type="text" />
			</td>
			<td>
				<aui:button type="submit" value="search" />
			</td>
		</tr>
		</table>
	</aui:fieldset>
</aui:form>