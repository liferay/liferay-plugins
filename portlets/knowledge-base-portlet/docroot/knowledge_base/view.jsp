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
String view = ParamUtil.getString(request, "view", "view_all_articles");
String tag = ParamUtil.getString(request, "tag");
boolean hasError = GetterUtil.getBoolean((String) request.getAttribute("hasError"));

String[] supportedViews = {"compare_versions", "edit_article", "edit_article_attachment", "view_all_articles", "view_article", "view_article_attachments", "view_article_history", "view_subscriptions", "view_tagged_articles", "view_templates", "search", };

if (!ArrayUtil.contains(supportedViews, view)) {
	view = supportedViews[0];
}

if (Validator.isNotNull(tag)) {
	view = "view_tagged_articles";
}

if (hasError) {
	view = "error";
}

if (_log.isInfoEnabled()) {
	_log.info("Including view: " + "/knowledge_base/" + view + ".jsp");
}
%>

<jsp:include page="/knowledge_base/top.jsp" />

<jsp:include page='<%= "/knowledge_base/views/" + view + ".jsp" %>' flush="true" />

<%!
private static Log _log = LogFactoryUtil.getLog("knowledge-base-portlet.docroot.knowledge_base.view.jsp");
%>