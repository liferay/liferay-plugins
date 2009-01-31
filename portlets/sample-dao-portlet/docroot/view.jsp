<%
/**
 * Copyright (c) 2000-2009 Liferay, Inc. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
%>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ page import="com.liferay.portal.kernel.util.Constants" %>
<%@ page import="com.liferay.portal.kernel.util.HtmlUtil" %>
<%@ page import="com.liferay.portal.kernel.util.ParamUtil" %>
<%@ page import="com.liferay.sampledao.model.FoodItem" %>
<%@ page import="com.liferay.sampledao.model.FoodItemDAO" %>
<%@ page import="com.liferay.sampledao.util.ConnectionPool" %>

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

		<script type="text/javascript">
			document.<portlet:namespace />fm.name.focus();
		</script>
	<%
	}
	%>

<%
}
else {
%>

	<input name="<%= Constants.CMD %>" type="hidden" value="" />
	<input name="foodItemId" type="hidden" value="" />

	<input type="button" value="Add" onClick="self.location = '<portlet:renderURL><portlet:param name="<%= Constants.CMD %>" value="<%= Constants.ADD %>" /></portlet:renderURL>';" />

	<br /><br />

	<table border="1" cellpadding="4" cellspacing="0" width="100%">
	<tr>
		<td>
			<b>Food Item ID</b>
		</td>
		<td>
			<b>Name</b>
		</td>
		<td>
			<b>Points</b>
		</td>
		<td>
			<b>Action</b>
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
				<input type="button" value="Edit" onClick="self.location = '<portlet:renderURL><portlet:param name="<%= Constants.CMD %>" value="<%= Constants.EDIT %>" /><portlet:param name="foodItemId" value="<%= String.valueOf(foodItem.getFoodItemId()) %>" /></portlet:renderURL>';" />

				<input type="button" value="Delete" onClick="document.<portlet:namespace />fm.<%= Constants.CMD %>.value = '<%= Constants.DELETE %>'; document.<portlet:namespace />fm.foodItemId.value = '<%= foodItem.getFoodItemId() %>'; document.<portlet:namespace />fm.submit();" />
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