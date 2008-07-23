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
String redirect = ParamUtil.getString(request, "redirect");

WikiPage wikiPage = (WikiPage)request.getAttribute(WebKeys.WIKI_PAGE);
%>

<script type="text/javascript">
	jQuery(
		function() {
			new Liferay.Upload({
				allowedFileTypes: '<%= StringUtil.merge(PropsValues.DL_FILE_EXTENSIONS) %>',
				container: '#<portlet:namespace />fileUpload',
				fileDescription: '<%= StringUtil.merge(PropsValues.DL_FILE_EXTENSIONS) %>',
				fallbackContainer: '#<portlet:namespace />fallback',
				maxFileSize: <%= PropsValues.DL_FILE_MAX_SIZE %>,
				namespace: '<portlet:namespace />',
				uploadFile: '<liferay-portlet:actionURL windowState="<%= LiferayWindowState.POP_UP.toString() %>" doAsUserId="<%= user.getUserId() %>"><portlet:param name="struts_action" value="/wiki/edit_page_attachment" /><portlet:param name="<%= Constants.CMD %>" value="<%= Constants.ADD %>" /><portlet:param name="nodeId" value="<%= String.valueOf(node.getNodeId()) %>" /><portlet:param name="title" value="<%= wikiPage.getTitle() %>" /></liferay-portlet:actionURL><liferay-ui:input-permissions-params modelName="<%= WikiPage.class.getName() %>" />'
			});
		}
	);
</script>

<liferay-util:include page="/html/portlet/wiki/top_links.jsp" />

<liferay-util:include page="/html/portlet/wiki/page_tabs.jsp">
	<liferay-util:param name="tabs1" value="attachments" />
</liferay-util:include>

<form action="<portlet:actionURL><portlet:param name="struts_action" value="/wiki/edit_page_attachment" /></portlet:actionURL>" class="uni-form" enctype="multipart/form-data" method="post" name="<portlet:namespace />fm">
<input name="<portlet:namespace /><%= Constants.CMD %>" type="hidden" value="<%= Constants.ADD %>" />
<input name="<portlet:namespace />redirect" type="hidden" value="<%= HtmlUtil.escape(redirect) %>" />
<input name="<portlet:namespace />nodeId" type="hidden" value="<%= String.valueOf(node.getNodeId()) %>" />
<input name="<portlet:namespace />title" type="hidden" value="<%= wikiPage.getTitle() %>" />
<input name="<portlet:namespace />numOfFiles" type="hidden" value="3" />

<div class="lfr-upload-container" id="<portlet:namespace />fileUpload"></div>

<div class="lfr-fallback" id="<portlet:namespace />fallback">
	<fieldset class="block-labels">
		<legend><liferay-ui:message key="upload-files" /></legend>

		<div class="ctrl-holder">
			<label for="<portlet:namespace />file1"><liferay-ui:message key="file" /> 1</label>

			<input id="<portlet:namespace />file1" name="<portlet:namespace />file1" type="file" />
		</div>

		<div class="ctrl-holder">
			<label for="<portlet:namespace />file2"><liferay-ui:message key="file" /> 2</label>

			<input id="<portlet:namespace />file2" name="<portlet:namespace />file2" type="file" />
		</div>

		<div class="ctrl-holder">
			<label for="<portlet:namespace />file3"><liferay-ui:message key="file" /> 3</label>

			<input id="<portlet:namespace />file3" name="<portlet:namespace />file3" type="file" />
		</div>
	</fieldset>

	<div class="button-holder">
		<input type="submit" value="<liferay-ui:message key="save" />" />

		<input type="button" value="<liferay-ui:message key="cancel" />" onClick="parent.location = '<%= HtmlUtil.escape(redirect) %>';" />
	</div>

	<br />
</div>

</form>

<script type="text/javascript">
	jQuery(document).ready(
		function() {
			for (var i = 1; i < 4; i++) {
				jQuery("#<portlet:namespace />file" + i).change(
					function() {
						var value = jQuery(this).val();

						if ((value != null) && (value != "")) {
							var extension = value.substring(value.lastIndexOf(".")).toLowerCase();

							var validExtensions = new Array("<%= StringUtil.merge(PropsValues.DL_FILE_EXTENSIONS, "\", \"") %>");

							if (jQuery.inArray(extension, validExtensions) == -1) {
								alert('<liferay-ui:message key="document-names-must-end-with-one-of-the-following-extensions" /> <%= StringUtil.merge(PropsValues.DL_FILE_EXTENSIONS, ", ") %>');

								jQuery(this).val("");
							}
						}
					}
				).change();
			}
		}
	);
</script>