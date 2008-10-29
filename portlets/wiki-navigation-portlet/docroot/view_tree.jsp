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

<div class="portlet-wiki-navigation-tree">
	<%
		List<EntryDisplay> entries = (List<EntryDisplay>)renderRequest.getAttribute(WebKeys.ENTRIES);

		if (entries.isEmpty()) {
	%>
		<liferay-ui:message key="no-entries-found" />
	<%
		}
		else {
			StringBuilder sb = new StringBuilder();

			String ulClass = "treeview";
			String liClass = "wiki-title";

			_buildNavigationTree(entries , sb, ulClass, liClass);

			out.print(sb.toString());
		}
	%>
</div>

<script type="text/javascript">
	jQuery(document).ready(
		function() {
			var treeview = jQuery('#<%= renderResponse.getNamespace() %>wikiNavigation .treeview');

			treeview.treeview(
				{
					animated: 'fast'
				}
			);

			jQuery.ui.disableSelection(treeview);
		}
	);
</script>

<%!
private void _buildNavigationTree(List<EntryDisplay> entries, StringBuilder sb, String ulClass, String liClass) {
	for (EntryDisplay parent : entries) {
		String title = parent.getLabel();

		if (ulClass != null) {
			sb.append("<ul class=\"");
			sb.append(ulClass);
			sb.append("\">");
		}
		else {
			sb.append("<ul>");
		}
		if (liClass != null) {
			sb.append("<li class=\"");
			sb.append(liClass);
			sb.append("\">");
		}
		else {
			sb.append("<li>");
		}
		sb.append("<span>");

		String url = parent.getUrl();
		if (Validator.isNotNull(url)) {
			sb.append("<a ");
			if (parent.getExternalURL()) {
				sb.append("href=\"");
				sb.append(url);
				sb.append("\" target=\"_blank\">");
			}
			else {
				sb.append("href=\"javascript: submitForm(document.hrefFm, '");
				sb.append(url);
				sb.append("');\">");
			}
			sb.append(title);
			sb.append("</a>");
		}
		else {
			sb.append(title);
		}

		sb.append("</span>");

		_buildNavigationTree(parent.getChildren(), sb, null, null);

		sb.append("</li>");
		sb.append("</ul>");
	}
}
%>