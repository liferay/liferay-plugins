<%/**
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
 */%>

<%@ include file="/init.jsp" %>

<div class="portlet-wiki-navigation-menu">
	<%
		List<EntryDisplay> entries = (List<EntryDisplay>)renderRequest.getAttribute(WebKeys.ENTRIES);

		if (entries.isEmpty()) {
	%>
		<liferay-ui:message key="no-entries-found" />
	<%
		}
		else {
			StringBuilder sb = new StringBuilder();

			_buildNavigationMenu(entries, sb);

			out.print(sb.toString());
		}
	%>
</div>

<%!
private void _buildNavigationMenu(List<EntryDisplay> entries, StringBuilder sb) {

	for (EntryDisplay parent : entries) {
		String title = parent.getLabel();
		String href = parent.getUrl();
		sb.append("<h1><a name=\"");
		sb.append(title);
		sb.append("\" ");
		if (Validator.isNotNull(href)) {
			sb.append("href=\"");
			sb.append(href);
			sb.append("\"");
		}
		sb.append(">");
		sb.append(title);
		sb.append("</a></h1><ul>");
		for (EntryDisplay child : parent.getChildren()) {
			String label = child.getLabel();
			href = child.getUrl();
			sb.append("<li><a href=\"");
			sb.append(href);
			sb.append("\" ");
			if (child.getExternalURL()) {
				sb.append("target=\"_blank\"");
			} else {
				sb.append("title=\"");
				sb.append(label);
				sb.append("\"");
			}
			sb.append(">");
			sb.append(label);
			sb.append("</a></li>");
		}
		sb.append("</ul>");
	}
}
%>