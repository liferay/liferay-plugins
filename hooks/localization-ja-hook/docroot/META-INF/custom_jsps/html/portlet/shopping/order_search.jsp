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

<%@ include file="/html/portlet/shopping/init.jsp" %>

<%
OrderSearch searchContainer = (OrderSearch)request.getAttribute("liferay-ui:search:searchContainer");

OrderDisplayTerms displayTerms = (OrderDisplayTerms)searchContainer.getDisplayTerms();
%>

<aui:fieldset>
	<aui:column>
		<aui:input name="<%= displayTerms.NUMBER %>" size="20" type="text" value="<%= displayTerms.getNumber() %>" />

		<aui:select label="" name="<%= displayTerms.AND_OPERATOR %>">
			<aui:option label="and" selected="<%= displayTerms.isAndOperator() %>" value="1" />
			<aui:option label="or" selected="<%= !displayTerms.isAndOperator() %>" value="0" />
		</aui:select>
	</aui:column>

	<aui:column>
		<aui:select name="<%= displayTerms.STATUS %>" showEmptyOption="<%= true %>">

			<%
			for (int i = 0; i < ShoppingOrderConstants.STATUSES.length; i++) {
			%>

				<aui:option label="<%= ShoppingOrderConstants.STATUSES[i] %>" selected="<%= displayTerms.getStatus().equals(ShoppingOrderConstants.STATUSES[i]) %>" />

			<%
			}
			%>

		</aui:select>
	</aui:column>

	<%@ include file="/html/portlet/shopping/order_search_user_name.jspf" %>

	<aui:column>
		<aui:input name="<%= displayTerms.EMAIL_ADDRESS %>" size="20" type="text" value="<%= displayTerms.getEmailAddress() %>" />
	</aui:column>
</aui:fieldset>

<aui:button-row>
	<aui:button type="submit" value="search" />
</aui:button-row>

<c:if test="<%= windowState.equals(WindowState.MAXIMIZED) %>">
	<aui:script>
		Liferay.Util.focusFormField(document.<portlet:namespace />fm.<portlet:namespace /><%= displayTerms.NUMBER %>);
	</aui:script>
</c:if>