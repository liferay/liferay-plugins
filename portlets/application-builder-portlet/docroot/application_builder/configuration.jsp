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

<%@ include file="/application_builder/init.jsp" %>

<%
String portletResourceNamespace = PortalUtil.getPortletNamespace(portletResource);
%>

<style type="text/css">
	#<portlet:namespace/>scriptOutputContainer {
		border: 1px solid #ccc;
		font-family: monospace;
		font-size: 12px;
		height: 200px;
		margin-top: 2em;
		overflow: auto;
		padding: 4px;
		width: 98%;
	}
</style>

<script type="text/javascript">
	function <portlet:namespace />test() {
		var script = <portlet:namespace />scriptEditor.getCode();

		jQuery.get(
			'<liferay-portlet:resourceURL portletName="<%= portletResource %>" />',
			{
				<%= portletResourceNamespace %>cmd: "exec",
				<%= portletResourceNamespace %>script: script
			},
			function(data) {
				if (!data.match(/^@ERROR@$/m)) {
					jQuery("#<portlet:namespace />scriptOutputContainer .scriptOutput").empty().append(data);
				}
				else {
					jQuery("#<portlet:namespace />scriptOutputContainer .scriptError").empty().text(data);
				}

				jQuery("#<portlet:namespace />scriptOutputContainer").slideDown();
			}
		);

		return false;
	}

	function <portlet:namespace />save() {
		var script = <portlet:namespace />scriptEditor.getCode();

		document.<portlet:namespace />fm.<portlet:namespace />script.value = script;

		return true;
	}
</script>

<form action="<liferay-portlet:actionURL portletConfiguration="true" />" method="post" name="<portlet:namespace />fm">
	<input  name="<portlet:namespace />script" type="hidden" value="" />

	<liferay-ui:message key="script" />
	<br />

	<textarea class="codepress ruby" id="<portlet:namespace />scriptEditor" name="<portlet:namespace />scriptEditor"
			  style="height: 300px; width: 98%;" wrap="off"><%= script %></textarea>

	<br /><br />

	<input type="submit" value="<liferay-ui:message key="save" />" onClick="<portlet:namespace />save();" />
	<input type="button" value="<liferay-ui:message key="test" />" onClick="<portlet:namespace />test();" />

	<div id="<portlet:namespace/>scriptOutputContainer" style="display: none">
		<div class="scriptOutput"><!--//--></div>
		<pre class="scriptError"><!--//--></pre>
	</div>

</form>

<script src="<%= themeDisplay.getPathContext() %>/html/js/editor/codepress/codepress.js" type="text/javascript"></script>