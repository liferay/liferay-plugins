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

<%@ include file="/init.jsp" %>

<%
PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("jspPage", "/advanced.jsp");
%>

<liferay-ui:search-container
	delta="<%= 5 %>"
	headerNames="email-address,screen-name,user-id"
	iteratorURL="<%= portletURL %>"
>
	<liferay-ui:search-container-results>

		<%
		results = UserLocalServiceUtil.getUsers(searchContainer.getStart(), searchContainer.getEnd());
		total = UserLocalServiceUtil.getUsersCount();

		pageContext.setAttribute("results", results);
		pageContext.setAttribute("total", total);
		%>

	</liferay-ui:search-container-results>

	<liferay-ui:search-container-row
		className="com.liferay.portal.model.User"
		escapedModel="<%= true %>"
		keyProperty="userId"
		modelVar="curUser"
	>
		<liferay-portlet:renderURL windowState="<%= WindowState.MAXIMIZED.toString() %>" varImpl="rowURL">
			<portlet:param name="jspPage" value="/advanced_user_display.jsp" />
			<portlet:param name="redirect" value="<%= currentURL %>" />
			<portlet:param name="userId" value="<%= String.valueOf(curUser.getUserId()) %>" />
		</liferay-portlet:renderURL>

		<liferay-ui:search-container-row-parameter
			name="rowURL"
			value="<%= rowURL.toString() %>"
		/>

		<liferay-ui:search-container-column-jsp
			align="left"
			path="/advanced_column.jsp"
		/>

		<liferay-ui:search-container-column-text
			buffer="buffer"
			name="email-address"
		>

			<%
			buffer.append("<a href=\"");
			buffer.append(rowURL.toString());
			buffer.append("\">");
			buffer.append(curUser.getEmailAddress());
			buffer.append("</a>");
			%>

		</liferay-ui:search-container-column-text>

		<liferay-ui:search-container-column-text
			href="<%= rowURL %>"
			name="screen-name"
			property="screenName"
		/>

		<liferay-ui:search-container-column-text
			href="<%= rowURL %>"
			name="user-id"
			value="<%= String.valueOf(curUser.getUserId()) %>"
		/>
	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator />
</liferay-ui:search-container>

<div class="separator"></div>

<a href="<portlet:renderURL />">&laquo; Back</a>