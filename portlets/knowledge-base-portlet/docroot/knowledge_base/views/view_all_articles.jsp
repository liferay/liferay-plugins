<%
/**
 * Copyright (c) 2000-2008 Liferay, Inc. All rights reserved.
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

<%@ include file="/knowledge_base/init.jsp" %>

<%
request.setAttribute("article_iterator.type", "all_articles");
%>

<h1 class="page-title">
	<liferay-ui:message key="all-articles" />
</h1>

<c:if test="<%= KBPermission.contains(permissionChecker, plid, ActionKeys.ADD_ARTICLE) %>">
	<div class="ctrl-holder-add-article">
		<input type="button" value="<liferay-ui:message key="add-article" />" onClick="location.href = '<portlet:renderURL><portlet:param name="view" value="edit_article" /><portlet:param name="template" value="false" /><portlet:param name="redirect" value="<%= currentURL %>"></portlet:param></portlet:renderURL>'" />
	</div>
</c:if>

<jsp:include page="/knowledge_base/views/article_iterator.jsp" />