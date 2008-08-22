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

<%@ include file="/html/knowledge_base/init.jsp" %>

<%
KBArticle article = (KBArticle) request.getAttribute(KnowledgeBaseKeys.ARTICLE);

// Portlet URLs

PortletURL editArticleURL = renderResponse.createRenderURL();

editArticleURL.setParameter("view", "edit_article");
editArticleURL.setParameter("tabs", "edit");
editArticleURL.setParameter("resourcePrimKey", String.valueOf(article.getResourcePrimKey()));

PortletURL viewAttachmentsURL = renderResponse.createRenderURL();

viewAttachmentsURL.setParameter("view", "view_article_attachments");
viewAttachmentsURL.setParameter("tabs", "attachments");
viewAttachmentsURL.setParameter("resourcePrimKey", String.valueOf(article.getResourcePrimKey()));

PortletURL viewHistoryURL = renderResponse.createRenderURL();

viewHistoryURL.setParameter("view", "view_article_history");
viewHistoryURL.setParameter("tabs", "history");
viewHistoryURL.setParameter("resourcePrimKey", String.valueOf(article.getResourcePrimKey()));

// Tabs

StringBuffer tabNames = new StringBuffer();

List<String> urls = new ArrayList<String>();

if (KBArticlePermission.contains(permissionChecker, article, ActionKeys.UPDATE)) {
	tabNames.append("edit,");
	urls.add(editArticleURL.toString());
}

if (!article.isTemplate()) {
	tabNames.append("attachments,");
	urls.add(viewAttachmentsURL.toString());
}

tabNames.append("history");
urls.add(viewHistoryURL.toString());

for (int i = urls.size(); i < 3; i++) {
	urls.add(StringPool.BLANK);
}
%>

<h1 class="article-title">
	<a class="return-to-article" href="<portlet:renderURL><portlet:param name="view" value="view_article" /><portlet:param name="title" value="<%= article.getTitle() %>" /></portlet:renderURL>"><%= article.getTitle() %></a>
</h1>

<liferay-ui:tabs
	names="<%= tabNames.toString() %>"
	param="tabs"
	url0="<%= urls.get(0) %>"
	url1="<%= urls.get(1) %>"
	url2="<%= urls.get(2) %>"
/>