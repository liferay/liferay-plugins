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

KBArticle article = (KBArticle)request.getAttribute(KnowledgeBaseKeys.ARTICLE);
%>

<script type="text/javascript">
	jQuery(
		function() {
			new Liferay.Upload({
				allowedFileTypes: '<%= PropsUtil.get("dl.file.extensions") %>',
				container: '#<portlet:namespace />fileUpload',
				fileDescription: '<%= PropsUtil.get("dl.file.extensions") %>',
				fallbackContainer: '#<portlet:namespace />fallback',
				maxFileSize: <%= PropsUtil.get("dl.file.max.size") %>,
				namespace: '<portlet:namespace />',
				uploadFile: '<liferay-portlet:actionURL windowState="<%= LiferayWindowState.POP_UP.toString() %>" doAsUserId="<%= user.getUserId() %>"><portlet:param name="actionName" value="addAttachment" /><portlet:param name="resourcePrimKey" value="<%= String.valueOf(article.getResourcePrimKey()) %>" /></liferay-portlet:actionURL><liferay-ui:input-permissions-params modelName="<%= KBArticle.class.getName() %>" />'
			});
		}
	);
</script>

<jsp:include page="/views/article_tabs.jsp">
	<jsp:param name="tabs1" value="attachments" />
</jsp:include>

<form action="<portlet:actionURL><portlet:param name="actionName" value="addAttachment" /><portlet:param name="redirect" value="<%= HtmlUtil.escape(redirect) %>" /></portlet:actionURL>" class="uni-form" enctype="multipart/form-data" method="post" name="<portlet:namespace />fm">
<input name="<portlet:namespace />resourcePrimKey" type="hidden" value="<%= String.valueOf(article.getResourcePrimKey()) %>" />
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

							var validExtensions = new Array("<%= StringUtil.merge(StringUtil.split(PropsUtil.get("dl.file.extensions")), "\", \"") %>");

							if (jQuery.inArray(extension, validExtensions) == -1) {
								alert('<liferay-ui:message key="document-names-must-end-with-one-of-the-following-extensions" /> <%= PropsUtil.get("dl.file.extensions") %>');

								jQuery(this).val("");
							}
						}
					}
				).change();
			}
		}
	);
</script>