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

long definitionId = ParamUtil.getLong(request, "definitionId");

WorkflowDefinition definition = null;

try {
	definition = WorkflowDefinitionServiceUtil.getDefinition(definitionId);
}
catch (NoSuchDefinitionException nsde) {
}
%>

<script type="text/javascript">
	function <portlet:namespace />saveDefinition() {
		submitForm(document.<portlet:namespace />fm);
	}
</script>

<form action="<portlet:actionURL windowState="<%= WindowState.MAXIMIZED.toString() %>" name="addDefinition"><portlet:param name="jspPage" value="/edit_definition.jsp" /></portlet:actionURL>" method="post" name="<portlet:namespace />fm" onSubmit="<portlet:namespace />saveDefinition(); return false;">
<input name="<portlet:namespace />redirect" type="hidden" value="<%= HtmlUtil.escape(redirect) %>" />
<input name="<portlet:namespace />definitionId" type="hidden" value="<%= definitionId %>" />

<liferay-ui:tabs names="definition" />

<liferay-ui:error exception="<%= DefinitionXmlException.class %>" message="an-error-occurred-while-parsing-your-xml-please-check-the-syntax-of-your-xml" />

<c:if test="<%= definition != null %>">
	<table class="lfr-table">
	<tr>
		<td>
			<liferay-ui:message key="name" />:
		</td>
		<td>
			<%= definition.getName() %>
		</td>
	</tr>
	<tr>
		<td>
			<liferay-ui:message key="version" />:
		</td>
		<td>
			<%= definition.getVersion() %>
		</td>
	</tr>
	</table>

	<br />
</c:if>

<liferay-ui:message key="enter-the-workflow-definition-below-in-xml-format" />

<br /><br />

<liferay-ui:input-field model="<%= WorkflowDefinition.class %>" bean="<%= definition %>" field="xml" /><br />

<c:if test="<%= definition == null %>">
	<br />

	<table class="lfr-table">
	<tr>
		<td>
			<liferay-ui:message key="permissions" />
		</td>
		<td>
			<liferay-ui:input-permissions
				modelName="<%= WorkflowDefinition.class.getName() %>"
			/>
		</td>
	</tr>
	</table>
</c:if>

<br />

<input type="submit" value="<liferay-ui:message key="save-new-version" />" />

<input type="button" value="<liferay-ui:message key="cancel" />" onClick="location.href = '<%= HtmlUtil.escape(redirect) %>';" />

</form>

<c:if test="<%= windowState.equals(WindowState.MAXIMIZED) %>">
	<script type="text/javascript">
		Liferay.Util.focusFormField(document.<portlet:namespace />fm.<portlet:namespace />xml);
	</script>
</c:if>