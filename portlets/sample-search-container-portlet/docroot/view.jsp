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
String tabs = ParamUtil.getString(request, "tabs", "Test_1");
String cur = ParamUtil.getString(request, "cur");
String cur2 = ParamUtil.getString(request, "cur2");

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setWindowState(WindowState.MAXIMIZED);
portletURL.setParameter("cur", cur);
portletURL.setParameter("cur2", cur2);
portletURL.setParameter("tabs", tabs);

List results = null;
%>

This is the <b>Sample Search Container Portlet</b> for demonstrating and testing
the output, and functionality of the &lt;liferay-ui:search-container&gt; JSP
tags.

<div class="separator"><!--//--></div>

<liferay-ui:tabs
	names="Test_1,Test_2,Test_3,Test_4"
	param="tabs"
	url="<%= portletURL.toString() %>"
/>

<c:choose>
	<c:when test='<%= tabs.equals("Test_1") %>'>

		This is a generic test showing property based data access, pagination, custom
		value using the "value" attribute, and alignments.

		<br /><br />

		<liferay-ui:search-container delta="5" iteratorURL="<%= portletURL %>">
			<liferay-ui:search-container-results
				total="<%= books.size() %>"
				results="<%= ListUtil.subList(books, searchContainer.getStart(), searchContainer.getEnd()) %>"
			/>
			<liferay-ui:search-container-row keyProperty="bookId" modelVar="book" className="com.liferay.searchcontainer.bean.Book">
				<liferay-ui:search-container-column-text name="Book (Left Align)" property="bookId" align="left" />
				<liferay-ui:search-container-column-text name="Title (Right Align)" property="title" align="right" />
				<liferay-ui:search-container-column-text name="Author (Center Align)" property="author" align="center" />
				<liferay-ui:search-container-column-text name="Publish Date" value="<%= dateFormatDate.format(book.getPublicationDate()) %>" />
				<liferay-ui:search-container-column-text name="Publisher" property="publisher" />
			</liferay-ui:search-container-row>

			<liferay-ui:search-iterator searchContainer="<%= searchContainer %>" />
		</liferay-ui:search-container>

	</c:when>
	<c:when test='<%= tabs.equals("Test_2") %>'>

		This section tests three things; attaching row urls, JSP column tag (output of a
		JSP page as the column data), and whether the pagination behaves properly when
		more than one paginated list are one the portlet view.

		<br /><br />

		<liferay-ui:search-container delta="5" iteratorURL="<%= portletURL %>">
			<liferay-ui:search-container-results
				total="<%= books.size() %>"
				results="<%= ListUtil.subList(books, searchContainer.getStart(), searchContainer.getEnd()) %>"
			/>
			<liferay-ui:search-container-row keyProperty="bookId" modelVar="book" className="com.liferay.searchcontainer.bean.Book">
				<liferay-ui:search-container-column-text name="Book (Left Align)" property="bookId" align="left" />
				<liferay-ui:search-container-column-text name="Title (Right Align)" property="title" align="right" />
				<liferay-ui:search-container-column-text name="Author (Center Align)" property="author" align="center" />
				<liferay-ui:search-container-column-text name="Publish Date" value="<%= dateFormatDate.format(book.getPublicationDate()) %>" />
				<liferay-ui:search-container-column-text name="Publisher" property="publisher" />
			</liferay-ui:search-container-row>

			<liferay-ui:search-iterator searchContainer="<%= searchContainer %>" />
		</liferay-ui:search-container>

		<br /><br />

		<liferay-ui:search-container delta="5" curParam="cur2" iteratorURL="<%= portletURL %>">
			<liferay-ui:search-container-results
				total="<%= books.size() %>"
				results="<%= ListUtil.subList(books, searchContainer.getStart(), searchContainer.getEnd()) %>"
			/>
			<liferay-ui:search-container-row keyProperty="bookId" modelVar="book" className="com.liferay.searchcontainer.bean.Book">
				<portlet:renderURL windowState="<%= WindowState.MAXIMIZED.toString() %>" var="rowURL">
					<portlet:param name="redirect" value="<%= currentURL %>" />
				</portlet:renderURL>
				<liferay-ui:search-container-column-text name="Book" property="bookId" href="<%= rowURL %>" />
				<liferay-ui:search-container-column-text name="Title" property="title" href="<%= rowURL %>" />
				<liferay-ui:search-container-column-text name="Author" property="author" href="<%= rowURL %>" />
				<liferay-ui:search-container-column-text name="Publish Date" value="<%= dateFormatDate.format(book.getPublicationDate()) %>" href="<%= rowURL %>" />
				<liferay-ui:search-container-column-text name="Publisher" property="publisher" href="<%= rowURL %>" />
				<liferay-ui:search-container-column-jsp path="/book_action.jsp" />
			</liferay-ui:search-container-row>

			<liferay-ui:search-iterator searchContainer="<%= searchContainer %>" />
		</liferay-ui:search-container>

	</c:when>
	<c:when test='<%= tabs.equals("Test_3") %>'>

		Here we will do some valign tests.

		<br /><br />

		<liferay-ui:search-container delta="5" curParam="cur3" iteratorURL="<%= portletURL %>">
			<liferay-ui:search-container-results
				total="<%= books.size() %>"
				results="<%= ListUtil.subList(books, searchContainer.getStart(), searchContainer.getEnd()) %>"
			/>
			<liferay-ui:search-container-row keyProperty="bookId" modelVar="book" className="com.liferay.searchcontainer.bean.Book">
				<liferay-ui:search-container-column-text name="Book (VAlign Top)" property="bookId" valign="top" />
				<liferay-ui:search-container-column-text name="Title (VAlign Middle)" property="title" valign="middle" />
				<liferay-ui:search-container-column-text name="Author (VAlign Bottom)" property="author" valign="bottom" />
				<liferay-ui:search-container-column-text name="Publish Date" value="<%= dateFormatDate.format(book.getPublicationDate()) %>" />
				<liferay-ui:search-container-column-text name="Publisher" property="publisher" />
				<liferay-ui:search-container-column-jsp path="/book_action2.jsp" />
			</liferay-ui:search-container-row>

			<liferay-ui:search-iterator searchContainer="<%= searchContainer %>" />
		</liferay-ui:search-container>

	</c:when>
	<c:when test='<%= tabs.equals("Test_4") %>'>

		Various methods of adding column data:
		<ul>
			<li>a bean property using the "property" attribute</li>
			<li>an arbitrary value supplied via the "value" attribute</li>
			<li>using a StringBuilder by specifying the buffer's name using the "buffer" attribute</li>
			<li>the tag body (this is the default if no "property", "buffer" or "value" attribute is specified)</li>
		</ul>

		<br /><br />

		<liferay-ui:search-container delta="5" curParam="cur3" iteratorURL="<%= portletURL %>">
			<liferay-ui:search-container-results
				total="<%= books.size() %>"
				results="<%= ListUtil.subList(books, searchContainer.getStart(), searchContainer.getEnd()) %>"
			/>
			<liferay-ui:search-container-row keyProperty="bookId" modelVar="book" className="com.liferay.searchcontainer.bean.Book">
				<portlet:renderURL windowState="<%= WindowState.MAXIMIZED.toString() %>" var="rowURL">
					<portlet:param name="redirect" value="<%= currentURL %>" />
				</portlet:renderURL>
				<c:if test="<%= index%2 == 1 %>">
					<liferay-ui:param name="restricted" value="true" />
				</c:if>
				<liferay-ui:search-container-column-text name="Book" property="bookId" />
				<liferay-ui:search-container-column-text name="Title (Buffer)" buffer="sb">
					<%
					sb.append("<a href=\"");
					sb.append(rowURL);
					sb.append("\"><b>");
					sb.append(book.getTitle());
					sb.append("</b></a>");
					%>
				</liferay-ui:search-container-column-text>
				<liferay-ui:search-container-column-text name="Author (Tag Body)">
					<a href="<%= rowURL %>">
						<b><%= book.getAuthor() %></b>
					</a>
				</liferay-ui:search-container-column-text>
				<liferay-ui:search-container-column-text name="Publish Date" value="<%= dateFormatDate.format(book.getPublicationDate()) %>" />
				<liferay-ui:search-container-column-text name="Publisher" property="publisher" />
				<liferay-ui:search-container-column-button name="Action" href="javascript: alert('Test');" />
			</liferay-ui:search-container-row>

			<liferay-ui:search-iterator searchContainer="<%= searchContainer %>" />
		</liferay-ui:search-container>

	</c:when>
</c:choose>