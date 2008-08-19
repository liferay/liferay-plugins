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
request.setAttribute("article_iterator.type", "article_history");
%>

<script type="text/javascript">
	function <portlet:namespace />compare() {
		var rowIds = jQuery('input[@name=<portlet:namespace />rowIds]:checked');
		var sourceVersion = jQuery('input[@name="<portlet:namespace />sourceVersion"]');
		var targetVersion = jQuery('input[@name="<portlet:namespace />targetVersion"]');

		if (rowIds.length == 1) {
			sourceVersion.val(rowIds[0].value);
		}
		else if (rowIds.length == 2) {
			sourceVersion.val(rowIds[1].value);
			targetVersion.val(rowIds[0].value);
		}

		submitForm(document.<portlet:namespace />fmCompare);
	}

	function <portlet:namespace />inactivateRowIds(element) {
		var rowIds = jQuery('input[@name=<portlet:namespace />rowIds]');

		var found = 0;
		var totalChecked = jQuery('input[@name=<portlet:namespace />rowIds]:checked').length;

		for (i = 0; i < rowIds.length; i++) {
			if (rowIds[i].checked && (found < 2)) {
				found++;
			}
			else if (totalChecked == 0) {

				// Enable everything

				rowIds[i].checked = false;
				rowIds[i].disabled = false;
			}
			else if ((found == 0) && (totalChecked == 1)) {

				// Disable everything up to the first one

				rowIds[i].checked = false;
				rowIds[i].disabled = true;
			}
			else if ((found == 1) && (totalChecked >= 1)) {

				// Unselect everything after the first one

				rowIds[i].checked = false;
				rowIds[i].disabled = false;
			}
			else if ((found == 2) && (totalChecked >= 2)) {

				// Disable elements after the second one

				rowIds[i].checked = false;
				rowIds[i].disabled = true;
			}
		}
	}

	jQuery(document).ready(
		function() {
			jQuery('input[@name=<portlet:namespace />rowIds]').click(
				function() {
					<portlet:namespace />inactivateRowIds(this);
				}
			);
		}
	);
</script>

<jsp:include page="/views/article_tabs.jsp" />

<jsp:include page="/views/article_iterator.jsp" />