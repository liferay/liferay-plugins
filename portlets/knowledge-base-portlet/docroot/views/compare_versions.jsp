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

<%@ include file="/init.jsp" %>

<%
KBArticle article = (KBArticle) request.getAttribute(KnowledgeBaseKeys.ARTICLE);

String backURL = ParamUtil.getString(request, "backURL");

double sourceVersion = ParamUtil.getDouble(renderRequest, "sourceVersion");
double targetVersion = ParamUtil.getDouble(renderRequest, "targetVersion");
String type = ParamUtil.getString(renderRequest, "type", "escape");

KBArticle sourcePage = KBArticleServiceUtil.getArticle(article.getResourcePrimKey(), sourceVersion);
KBArticle targetPage = KBArticleServiceUtil.getArticle(article.getResourcePrimKey(), targetVersion);

String sourceContent = sourcePage.getContent();
String targetContent = targetPage.getContent();

if (type.equals("escape")) {
	sourceContent = HtmlUtil.escape(sourceContent);
	targetContent = HtmlUtil.escape(targetContent);
}
else if (type.equals("strip")) {
	sourceContent = HtmlUtil.extractText(sourceContent);
	targetContent = HtmlUtil.extractText(targetContent);
}

List<DiffResult>[] diffResults = DiffUtil.diff(
	new StringReader(sourceContent), new StringReader(targetContent));
%>

<liferay-ui:diff
	sourceName="<%= article.getTitle() + StringPool.SPACE + sourceVersion %>"
	targetName="<%= article.getTitle() + StringPool.SPACE + targetVersion %>"
	diffResults="<%= diffResults %>"
/>