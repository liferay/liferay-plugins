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

<%@ include file="/html/portlet/message_boards/init.jsp" %>

<liferay-util:include page="/html/portlet/message_boards/sidebar.jsp" />

<%
String tabs1 = ParamUtil.getString(request, "tabs1", "categories");

MBCategory category = (MBCategory)request.getAttribute(WebKeys.MESSAGE_BOARDS_CATEGORY);

long categoryId = BeanParamUtil.getLong(category, request, "categoryId", MBCategoryImpl.DEFAULT_PARENT_CATEGORY_ID);
%>

<c:choose>
	<c:when test='<%= (tabs1.equals("categories")) && (categoryId == MBCategoryImpl.DEFAULT_PARENT_CATEGORY_ID) %>'>
		<h6><liferay-ui:message key="forums" /></h6>
	</c:when>
	<c:when test='<%= tabs1.equals("my_posts") %>'>
		<h6><liferay-ui:message key="my-posts" /></h6>
	</c:when>
	<c:when test='<%= tabs1.equals("my_subscriptions") %>'>
		<h6><liferay-ui:message key="my-subscriptions" /></h6>
	</c:when>
	<c:when test='<%= tabs1.equals("recent_posts") %>'>
		<h6><liferay-ui:message key="recent-posts" /></h6>
	</c:when>
	<c:when test='<%= tabs1.equals("statistics") %>'>
		<h6><liferay-ui:message key="statistics" /></h6>
	</c:when>
	<c:when test='<%= tabs1.equals("banned_users") %>'>
		<h6><liferay-ui:message key="banned-users" /></h6>
	</c:when>
</c:choose>

<liferay-util:include page="/html/portlet/message_boards/view.portal.jsp" />