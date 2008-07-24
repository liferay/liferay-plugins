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

String title = BeanParamUtil.getString(article, request, "title");
String parentTitle = BeanParamUtil.getString(article, request, "parentTitle");

boolean preview = ParamUtil.getBoolean(request, "preview");

boolean newArticle = false;
String content = BeanParamUtil.getString(article, request, "content");

if (article == null) {
	newArticle = true;
}

PortletURL viewArticleURL = renderResponse.createRenderURL();

viewArticleURL.setParameter("view", "view_article");
viewArticleURL.setParameter("title", title);

PortletURL editArticleURL = renderResponse.createRenderURL();

editArticleURL.setParameter("view", "edit_article");
editArticleURL.setParameter("title", title);
%>

<script type="text/javascript">
	function <portlet:namespace />initEditor() {
		return "<%= UnicodeFormatter.toString(content) %>";
	}

	function <portlet:namespace />previewArticle() {
		document.<portlet:namespace />fm.<portlet:namespace />preview.value = "true";

		if (window.<portlet:namespace />editor) {
			document.<portlet:namespace />fm.<portlet:namespace />content.value = window.<portlet:namespace />editor.getHTML();
		}

		submitForm(document.<portlet:namespace />fm);
	}

	function <portlet:namespace />saveArticle() {
		document.<portlet:namespace />fm.<portlet:namespace />actionName.value = "<%= Constants.UPDATE %>";

		if (window.<portlet:namespace />editor) {
			document.<portlet:namespace />fm.<portlet:namespace />content.value = window.<portlet:namespace />editor.getHTML();
		}

		submitForm(document.<portlet:namespace />fm);
	}

	function <portlet:namespace />saveAndContinueArticle() {
		document.<portlet:namespace />fm.<portlet:namespace />saveAndContinueRedirect.value = "<%= editArticleURL.toString() %>";
		<portlet:namespace />saveArticle();
	}
</script>

<c:if test="<%= preview && (article != null)%>">

	<liferay-ui:message key="preview" />:

	<div class="preview">
		<h1 class="article-title">
			<%= title %>
		</h1>
		<div class="knowledge-base-body">
			<%= content %>
		</div>
	</div>

	<br />
</c:if>

<form action="<portlet:actionURL />" method="post" name="<portlet:namespace />fm" onSubmit="<portlet:namespace />saveArticle(); return false;">
<input name="<portlet:namespace />actionName" type="hidden" value="" />
<input name="<portlet:namespace />redirect" type="hidden" value="<%= HtmlUtil.escape(redirect) %>" />
<input name="<portlet:namespace />parentTitle" type="hidden" value="<%= parentTitle %>" />
<input name="<portlet:namespace />format" type="hidden" value="html" />

<c:if test="<%= article != null %>">
	<input name="<portlet:namespace />version" type="hidden" value="<%= article.getVersion() %>" />
</c:if>

<input name="<portlet:namespace />preview" type="hidden" value="0" />
<input name="<portlet:namespace />saveAndContinueRedirect" type="hidden" value="" />

<liferay-ui:error exception="<%= ArticleTitleException.class %>" message="please-enter-a-valid-title" />
<liferay-ui:error exception="<%= ArticleVersionException.class %>" message="another-user-has-made-changes-since-you-started-editing-please-copy-your-changes-and-try-again" />
<liferay-ui:error exception="<%= EntryNameException.class %>" message="please-enter-a-valid-tag-name" />
<liferay-ui:tags-error />

<table class="lfr-table">

<tr>
	<td>
		<liferay-ui:message key="title" />
	</td>
	<td>
		<input name="<portlet:namespace />title" size="80" type="text" value="<%= title %>" />
	</td>
</tr>

<c:if test="<%= Validator.isNotNull(parentTitle) %>">
	<tr>
		<td>
			<liferay-ui:message key="parent" />
		</td>
		<td>
			<%= parentTitle %>
		</td>
	</tr>
</c:if>

</table>

<br />

<div>

	<liferay-ui:input-editor editorImpl="<%= null %>" width="100%" />

	<input name="<portlet:namespace />content" type="hidden" value="" />

</div>

<br />

<table class="lfr-table">

<tr>
	<td>
		<liferay-ui:message key="tags" />
	</td>
	<td>

		<%
		long classPK = 0;

		if (!newArticle) {
			classPK = article.getResourcePrimKey();
		}
		%>

		<liferay-ui:tags-selector
			className="<%= KBArticle.class.getName() %>"
			classPK="<%= classPK %>"
			hiddenInput="tagsEntries"
		/>
	</td>
</tr>
<tr>
	<td colspan="2">
		<br />
	</td>
</tr>
<tr>
	<td>
		<liferay-ui:message key="description" />
	</td>
	<td>
		<liferay-ui:input-field model="<%= KBArticle.class %>" bean="<%= article %>" field="description" />
	</td>
</tr>
<tr>
	<td colspan="2">
		<br />
	</td>
</tr>
<tr>
	<td colspan="2">
		<input name="<portlet:namespace />minorEdit" type="checkbox" />

		<liferay-ui:message key="this-is-a-minor-edit" />
	</td>
</tr>
</table>

<br />

<input type="submit" value="<liferay-ui:message key="save" />" />

<input type="button" value="<liferay-ui:message key="save-and-continue" />" onClick="<portlet:namespace />saveAndContinueArticle();" />

<input type="button" value="<liferay-ui:message key="preview" />" onClick="<portlet:namespace />previewArticle();" />

<input type="button" value="<liferay-ui:message key="cancel" />" onClick="document.location = '<%= HtmlUtil.escape(redirect) %>'" />

</form>

<script type="text/javascript">
	if (!window.<portlet:namespace />editor) {
		Liferay.Util.focusFormField(document.<portlet:namespace />fm.<portlet:namespace />title);
	}
</script>