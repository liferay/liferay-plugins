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

<%@ include file="/init.jsp" %>

<%
String query = ParamUtil.getString(request, "query");
String sort = ParamUtil.getString(request, "sort", "recent");
int category = ParamUtil.getInteger(request, "category");
String region = ParamUtil.getString(request, "region", "all");
%>

<table class="lfr-table">
<tr>
	<td>
		<label for="<portlet:namespace/>sort"><strong>Sort</strong></label>

		<select name="<portlet:namespace/>sort">
			<option <%= sort.equals("popular") ? "selected" : "" %> value="popular">Most Popular</option>
			<option <%= sort.equals("recent") ? "selected" : "" %> value="recent">Most Recent</option>
		</select>
	</td>
	<td>
		<label for="<portlet:namespace/>category"><strong>Category</strong></label>

		<select name="<portlet:namespace/>category">
			<option <%= category == 0 ? "selected" : "" %> value="0"><liferay-ui:message key="all" /></option>

			<%
			for (String[] categoryValues : NetvibesWidgetUtil.getCategories()) {
			%>

				<option <%= GetterUtil.getInteger(categoryValues[0]) == category ? "selected" : "" %> value="<%= GetterUtil.getInteger(categoryValues[0]) %>"><%= categoryValues[1] %></option>

			<%
			}
			%>

		</select>
	</td>
	<td>
		<label for="<portlet:namespace/>region"><strong>Region</strong></label>

		<select name="<portlet:namespace/>region">

			<%
			for (String[] regionValues : _REGIONS) {
			%>

				<option <%= region.equals(regionValues[0]) ? "selected" : "" %> value="<%= regionValues[0] %>"><%= regionValues[1] %></option>

			<%
			}
			%>

		</select>
	</td>
</tr>
<tr>
	<td colspan="3">
		<br />
	</td>
</tr>
<tr>
	<td colspan="3">
		<input name="<portlet:namespace/>query" size="30" type="text" value="<%= query %>" />

		<input type="button" value="<liferay-ui:message key="search" />" onClick="<portlet:namespace />search();" />
	</td>
</tr>
</table>

<div class="separator"><!-- --></div>

<%!
private static final String[][] _REGIONS = {
	{"all", "All"},
	{"al", "Albania"},
	{"dz", "Algeria"},
	{"ar", "Argentina"},
	{"au", "Australia"},
	{"at", "Austria"},
	{"bh", "Bahrain"},
	{"pv", "Basque Country"},
	{"by", "Belarus"},
	{"be", "Belgium (Dutch)"},
	{"bef", "Belgium (French)"},
	{"bz", "Belize"},
	{"bo", "Bolivia"},
	{"ba", "Bosnia and Herzegovina"},
	{"br", "Brazil"},
	{"bg", "Bulgaria"},
	{"cm", "Cameroon"},
	{"ca", "Canada"},
	{"qc", "Canada (Quebec)"},
	{"cl", "Chile"},
	{"cn", "China"},
	{"hk", "China (Hong Kong)"},
	{"tw", "China (Taiwan)"},
	{"co", "Colombia"},
	{"cd", "Congo"},
	{"cr", "Costa Rica"},
	{"hr", "Croatia"},
	{"cy", "Cyprus"},
	{"cz", "Czech Republic"},
	{"dk", "Denmark"},
	{"ec", "Ecuador"},
	{"eg", "Egypt"},
	{"sv", "El Salvador"},
	{"ee", "Estonia"},
	{"et", "Ethiopia"},
	{"fk", "Falkland Islands"},
	{"fi", "Finland"},
	{"fr", "France"},
	{"de", "Germany"},
	{"gr", "Greece"},
	{"gra", "Greece (Attica)"},
	{"gt", "Guatemala"},
	{"gy", "Guyana"},
	{"hn", "Honduras"},
	{"hu", "Hungary"},
	{"is", "Iceland"},
	{"in", "India"},
	{"id", "Indonesia"},
	{"ir", "Iran"},
	{"iq", "Iraq"},
	{"ie", "Ireland"},
	{"il", "Israel"},
	{"it", "Italy"},
	{"ci", "Ivory Coast"},
	{"jp", "Japan"},
	{"jo", "Jordan"},
	{"ke", "Kenya"},
	{"ko", "Kosovo"},
	{"kw", "Kuwait"},
	{"lv", "Latvia"},
	{"lb", "Lebanon"},
	{"lt", "Lithuania"},
	{"lu", "Luxembourg"},
	{"ly", "Lybia"},
	{"my", "Malaysia"},
	{"mr", "Mauritania"},
	{"mx", "Mexico"},
	{"mn", "Mongolia"},
	{"ma", "Morocco"},
	{"nl", "Netherlands"},
	{"nlf", "Netherlands (Friesland)"},
	{"nz", "New Zealand"},
	{"ni", "Nicaragua"},
	{"ng", "Nigeria"},
	{"no", "Norway"},
	{"pk", "Pakistan"},
	{"ps", "Palestine"},
	{"pa", "Panama"},
	{"py", "Paraguay"},
	{"pe", "Peru"},
	{"ph", "Philippines"},
	{"pl", "Poland"},
	{"pt", "Portugal"},
	{"ro", "Romania"},
	{"ru", "Russia"},
	{"sa", "Saudi Arabia"},
	{"sn", "Senegal"},
	{"rs", "Serbia"},
	{"sg", "Singapore"},
	{"sk", "Slovakia"},
	{"si", "Slovenia"},
	{"so", "Somalia"},
	{"za", "South Africa"},
	{"kr", "South Korea"},
	{"es", "Spain"},
	{"ct", "Spain (Catalonia)"},
	{"ga", "Spain (Galicia)"},
	{"sr", "Suriname"},
	{"se", "Sweden"},
	{"ch", "Switzerland"},
	{"chf", "Switzerland (French)"},
	{"sy", "Syria"},
	{"th", "Thailand"},
	{"tn", "Tunisia"},
	{"tr", "Turkey"},
	{"ua", "Ukraina"},
	{"ae", "United Arab Emirates"},
	{"gb", "United Kingdom"},
	{"us", "United States"},
	{"uy", "Uruguay"},
	{"ve", "Venezuela"},
	{"vn", "Vietnam"}
};
%>