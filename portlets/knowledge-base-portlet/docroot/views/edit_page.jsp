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

WikiNode node = (WikiNode)request.getAttribute(KnowledgeBaseKeys.WIKI_NODE);
WikiPage wikiPage = (WikiPage)request.getAttribute(KnowledgeBaseKeys.WIKI_PAGE);

String title = BeanParamUtil.getString(wikiPage, request, "title");
String parentTitle = BeanParamUtil.getString(wikiPage, request, "parentTitle");

boolean newPage = false;
String content = StringPool.BLANK;

if (wikiPage == null) {
	newPage = true;
}
else {
	content = wikiPage.getContent();
}

PortletURL viewPageURL = renderResponse.createRenderURL();

viewPageURL.setParameter(Constants.CMD, "view_page");
viewPageURL.setParameter("title", title);
%>

<script type="text/javascript">
	function <portlet:namespace />initEditor() {
		return "<%= UnicodeFormatter.toString(content) %>";
	}

	function <portlet:namespace />savePage() {
		document.<portlet:namespace />fm.<portlet:namespace /><%= Constants.CMD %>.value = "<%= Constants.UPDATE %>";

		if (window.<portlet:namespace />editor) {
			document.<portlet:namespace />fm.<portlet:namespace />content.value = window.<portlet:namespace />editor.getHTML();
		}

		submitForm(document.<portlet:namespace />fm);
	}
</script>

<form action="<portlet:actionURL><portlet:param name="struts_action" value="/wiki/edit_page" /></portlet:actionURL>" method="post" name="<portlet:namespace />fm" onSubmit="<portlet:namespace />savePage(); return false;">
<input name="<portlet:namespace /><%= Constants.CMD %>" type="hidden" value="" />
<input name="<portlet:namespace />redirect" type="hidden" value="<%= HtmlUtil.escape(redirect) %>" />
<input name="<portlet:namespace />parentTitle" type="hidden" value="<%= parentTitle %>" />
<input name="<portlet:namespace />format" type="hidden" value="html" />

<c:if test="<%= wikiPage != null %>">
	<input name="<portlet:namespace />version" type="hidden" value="<%= wikiPage.getVersion() %>" />
</c:if>

<liferay-ui:error exception="<%= PageContentException.class %>" message="the-content-is-not-valid" />
<liferay-ui:error exception="<%= PageTitleException.class %>" message="please-enter-a-valid-title" />
<liferay-ui:error exception="<%= PageVersionException.class %>" message="another-user-has-made-changes-since-you-started-editing-please-copy-your-changes-and-try-again" />
<liferay-ui:tags-error />

<table class="lfr-table">

<tr>
	<td>
		<liferay-ui:message key="title" />
	</td>
	<td>
		<input name="<portlet:namespace />title" size="30" type="text" value="<%= title %>" />
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

		if (!newPage) {
			classPK = wikiPage.getResourcePrimKey();
		}
		%>

		<liferay-ui:tags-selector
			className="<%= WikiPage.class.getName() %>"
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
		<liferay-ui:message key="summary" />
	</td>
	<td>
		<input name="<portlet:namespace />summary" size="75" type="text" />
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

<input type="button" value="<liferay-ui:message key="cancel" />" onClick="document.location = '<%= HtmlUtil.escape(redirect) %>'" />

</form>

<script type="text/javascript">
	if (!window.<portlet:namespace />editor) {
		Liferay.Util.focusFormField(document.<portlet:namespace />fm.<portlet:namespace />title);
	}
</script>