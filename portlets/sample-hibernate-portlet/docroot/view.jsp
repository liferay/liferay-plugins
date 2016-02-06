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

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>
<%@ taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %>

<%@ page import="com.liferay.portal.kernel.util.Constants" %>
<%@ page import="com.liferay.portal.kernel.util.HtmlUtil" %>
<%@ page import="com.liferay.portal.kernel.util.HttpUtil" %>
<%@ page import="com.liferay.portal.kernel.util.ParamUtil" %>
<%@ page import="com.liferay.portal.kernel.util.PortalUtil" %>
<%@ page import="com.liferay.samplehibernate.model.FoodItem" %>
<%@ page import="com.liferay.samplehibernate.util.FoodItemUtil" %>

<%@ page import="java.util.List" %>

<%@ page import="javax.portlet.WindowState" %>

<portlet:defineObjects />

<%
WindowState windowState = renderRequest.getWindowState();
%>

<liferay-portlet:actionURL var="actionURL" />

<aui:form action="<%= actionURL %>" method="post" name="fm">

	<%
	String cmd = ParamUtil.getString(request, Constants.CMD);

	if (cmd.equals(Constants.ADD) || cmd.equals(Constants.EDIT)) {
		long foodItemId = 0;

		if (cmd.equals(Constants.EDIT)) {
			foodItemId = ParamUtil.getLong(request, "foodItemId");
		}
	%>

		<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= HtmlUtil.escape(cmd) %>" />
		<aui:input name="foodItemId" type="hidden" value="<%= foodItemId %>" />

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

		FoodItem foodItem = FoodItemUtil.getFoodItem(foodItemId);
		%>

		<aui:model-context bean="<%= foodItem %>" model="<%= FoodItem.class %>" />

		<aui:fieldset>
			<aui:input autoFocus="<%= windowState.equals(WindowState.MAXIMIZED) %>" name="name" type="text" />

			<aui:input label="Points" name="points" type="text" />
		</aui:fieldset>
		</table>

		<br />

		<aui:button name="saveButton" type="submit" value="Save" />

	<%
	}
	else {
	%>

		<aui:input name="<%= Constants.CMD %>" type="hidden" value="" />
		<aui:input name="foodItemId" type="hidden" value="" />

		<input onClick="self.location = '<portlet:renderURL><portlet:param name="<%= Constants.CMD %>" value="<%= Constants.ADD %>" /></portlet:renderURL>';"  type="button" value="Add" />

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
		List foodItems = FoodItemUtil.getFoodItems();

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
					<input onClick="self.location = '<portlet:renderURL><portlet:param name="<%= Constants.CMD %>" value="<%= Constants.EDIT %>" /><portlet:param name="foodItemId" value="<%= String.valueOf(foodItem.getFoodItemId()) %>" /></portlet:renderURL>';" type="button" value="Edit" />

					<input onClick="document.<portlet:namespace />fm.<portlet:namespace /><%= Constants.CMD %>.value = '<%= Constants.DELETE %>'; document.<portlet:namespace />fm.<portlet:namespace />foodItemId.value = '<%= foodItem.getFoodItemId() %>'; document.<portlet:namespace />fm.submit();" type="button" value="Delete" />
				</td>
			</tr>

		<%
		}
		%>

		</table>

		<br />

		You can also access the list of food items via the following URLs in the XSL Content portlet.

		<br /><br />

		<%= HttpUtil.getProtocol(request) %>://<%= request.getServerName() %>:<%= request.getServerPort() %><%= PortalUtil.getPathContext(request) %>/servlet/test?<%= Constants.CMD %>=getFoodItemXml<br />
		<%= HttpUtil.getProtocol(request) %>://<%= request.getServerName() %>:<%= request.getServerPort() %><%= PortalUtil.getPathContext(request) %>/view.xsl

	<%
	}
	%>

</aui:form>