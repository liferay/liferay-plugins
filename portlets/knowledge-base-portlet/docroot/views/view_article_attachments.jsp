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
KBArticle article = (KBArticle)request.getAttribute(KnowledgeBaseKeys.ARTICLE);

String[] attachments = article.getAttachmentsFiles();

PortletURL portletURL = renderResponse.createActionURL();

portletURL.setParameter("view", "view_article_attachments");
portletURL.setParameter("title", article.getTitle());

List<String> headerNames = new ArrayList<String>();

headerNames.add("file-name");
headerNames.add("size");
headerNames.add(StringPool.BLANK);

SearchContainer searchContainer = new SearchContainer(renderRequest, null, null, SearchContainer.DEFAULT_CUR_PARAM, SearchContainer.DEFAULT_DELTA, portletURL, headerNames, "this-article-does-not-have-any-file-attachments");

int total = attachments.length;

searchContainer.setTotal(total);

List results = ListUtil.fromArray(attachments);

results = ListUtil.subList(results, searchContainer.getStart(), searchContainer.getEnd());

searchContainer.setResults(results);

List resultRows = searchContainer.getResultRows();

for (int i = 0; i < results.size(); i++) {
	String fileName = (String)results.get(i);

	String shortFileName = FileUtil.getShortFileName(fileName);

	long fileSize = DLServiceUtil.getFileSize(company.getCompanyId(), CompanyConstants.SYSTEM, fileName);

	ResultRow row = new ResultRow(new Object[] {article, fileName}, fileName, i);

	ResourceURL rowURL = renderResponse.createResourceURL();

	rowURL.setParameter("actionName", "getArticleAttachment");
	rowURL.setParameter("title", article.getTitle());
	rowURL.setParameter("fileName", shortFileName);

	// File name

	StringBuilder sb = new StringBuilder();

	sb.append("<img align=\"left\" border=\"0\" src=\"");
	sb.append(themeDisplay.getPathThemeImages());
	sb.append("/document_library/");
	sb.append(_getFileExtension(shortFileName));
	sb.append(".png\">&nbsp;");
	sb.append(shortFileName);

	row.addText(sb.toString(), rowURL.toString());

	// Size

	row.addText(TextFormatter.formatKB(fileSize, locale) + "k", rowURL.toString());

	// Action

	row.addJSP("right", SearchEntry.DEFAULT_VALIGN, "/views/article_attachment_action.jsp", config.getServletContext(), request, response);

	// Add result row

	resultRows.add(row);
}
%>
<h1 class="article-title">
	<a class="return-to-article" href="<portlet:renderURL><portlet:param name="view" value="view_article" /><portlet:param name="title" value="<%= article.getTitle() %>" /></portlet:renderURL>"><%= article.getTitle() %></a>
</h1>

<c:if test="<%= KBPermission.contains(permissionChecker, portletGroupId, ActionKeys.ADD_ATTACHMENT) %>">
	<div>
		<input type="button" value="<liferay-ui:message key="add-attachments" />" onClick="location.href = '<portlet:renderURL><portlet:param name="view" value="edit_article_attachment" /><portlet:param name="title" value="<%= article.getTitle() %>" /><portlet:param name="redirect" value="<%= currentURL %>" /></portlet:renderURL>';" />
	</div>

	<br />
</c:if>

<liferay-ui:search-iterator searchContainer="<%= searchContainer %>" />

<%!
private String _getFileExtension(String name) {
	String extension = StringPool.BLANK;

	int pos = name.lastIndexOf(StringPool.PERIOD);

	if (pos != -1) {
		extension = name.substring(pos + 1, name.length()).toLowerCase();
	}

//	if (!_fileExtensions.contains(extension)) {
//		extension = _DEFAULT_FILE_EXTENSION;
//	}

	return extension;
}
%>