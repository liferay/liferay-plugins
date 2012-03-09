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

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>

<%@ page import="com.liferay.portal.kernel.util.Constants" %>
<%@ page import="com.liferay.portal.kernel.util.HtmlUtil" %>
<%@ page import="com.liferay.portal.kernel.util.ParamUtil" %>
<%@ page import="com.liferay.sampledao.model.FoodItem" %>
<%@ page import="com.liferay.sampledao.model.FoodItemDAO" %>

<%@ page import="java.util.List" %>

<%@ page import="javax.portlet.WindowState" %>

<portlet:defineObjects />

<form action="<portlet:actionURL />" method="post" name="<portlet:namespace />fm">

<%
String cmd = ParamUtil.getString(request, Constants.CMD);

if (cmd.equals(Constants.ADD) || cmd.equals(Constants.EDIT)) {
	long foodItemId = 0;
	String name = "";
	int points = 0;

	if (cmd.equals(Constants.EDIT)) {
		foodItemId = ParamUtil.getLong(request, "foodItemId");

		FoodItem foodItem = FoodItemDAO.getFoodItem(foodItemId);

		name = foodItem.getName();
		points = foodItem.getPoints();
	}
%>

	<input name="<%= Constants.CMD %>" type="hidden" value="<%= HtmlUtil.escape(cmd) %>" />
	<input name="foodItemId" type="hidden" value="<%= foodItemId %>" />

	<table class="lfr-table">

	<%
	if (cmd.equals(Constants.EDIT)) {
	%>

		<tr>
			<td>
				Food Item ID
			</td>
			<td>
				<%= foodItemId %>
			</td>
		</tr>

	<%
	}
	%>

	<tr>
		<td>
			Name
		</td>
		<td>
			<input name="name" type="text" value="<%= name %>">
		</td>
	</tr>
	<tr>
		<td>
			Points
		</td>
		<td>
			<input name="points" type="text" value="<%= points %>">
		</td>
	</tr>
	</table>

	<br />

	<input type="submit" value="Save" />

	<%
	if (renderRequest.getWindowState().equals(WindowState.MAXIMIZED)) {
	%>

		<aui:script>
			document.<portlet:namespace />fm.name.focus();
		</aui:script>

	<%
	}
	%>

<%
}
else {
%>

	<input name="<%= Constants.CMD %>" type="hidden" value="" />
	<input name="foodItemId" type="hidden" value="" />

	<input onClick="self.location = '<portlet:renderURL><portlet:param name=" type="button" value="Add"<%= Constants.CMD %>" value="<%= Constants.ADD %>" /></portlet:renderURL>';" />

	<br /><br />

	<table border="1" cellpadding="4" cellspacing="0" width="100%">
	<tr>
		<td>
			<strong>Food Item ID</strong>
		</td>
		<td>
			<strong>Name</strong>
		</td>
		<td>
			<strong>Points</strong>
		</td>
		<td>
			<strong>Action</strong>
		</td>
	</tr>

	<%
	List foodItems = FoodItemDAO.getFoodItems();

	for (int i = 0; i < foodItems.size(); i++) {
		FoodItem foodItem = (FoodItem)foodItems.get(i);
	%>

		<tr>
			<td>
				<%= foodItem.getFoodItemId() %>
			</td>
			<td>
				<%= foodItem.getName() %>
			</td>
			<td>
				<%= foodItem.getPoints() %>
			</td>
			<td>
				<input onClick="self.location = '<portlet:renderURL><portlet:param name=" type="button" value="Edit"<%= Constants.CMD %>" value="<%= Constants.EDIT %>" /><portlet:param name="foodItemId" value="<%= String.valueOf(foodItem.getFoodItemId()) %>" /></portlet:renderURL>';" />

				<input onClick="document.<portlet:namespace />fm.<%= Constants.CMD %>.value = '<%= Constants.DELETE %>'; document.<portlet:namespace />fm.foodItemId.value = '<%= foodItem.getFoodItemId() %>'; document.<portlet:namespace />fm.submit();" type="button" value="Delete" />
			</td>
		</tr>

	<%
	}
	%>

	</table>

<%
}
%>

</form>