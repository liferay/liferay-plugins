<%--
  ~ Copyright (c) 2000-2009 Liferay, Inc. All rights reserved.
  ~
  ~ Permission is hereby granted, free of charge, to any person obtaining a copy
  ~ of this software and associated documentation files (the "Software"), to deal
  ~ in the Software without restriction, including without limitation the rights
  ~ to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
  ~ copies of the Software, and to permit persons to whom the Software is
  ~ furnished to do so, subject to the following conditions:
  ~
  ~ The above copyright notice and this permission notice shall be included in
  ~ all copies or substantial portions of the Software.
  ~
  ~ THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
  ~ IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
  ~ FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
  ~ AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
  ~ LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
  ~ OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
  ~ SOFTWARE.
  --%>

<%@ include file="/init.jsp" %>

<%
ReportDefinitionSearch searchContainer = (ReportDefinitionSearch)request.getAttribute("liferay-ui:search:searchContainer");
boolean showAddButton = GetterUtil.getBoolean((String)request.getAttribute("liferay-ui:search:showAddButton"));
ReportDefinitionDisplayTerms displayTerms = (ReportDefinitionDisplayTerms)searchContainer.getDisplayTerms();
%>

<liferay-ui:search-toggle
	id="toggle_id_report_definition_search"
	displayTerms="<%= displayTerms %>"
	buttonLabel="search"
>
	<table class="lfr-table">
	<tr>
		<td>
			<liferay-ui:message key="name" />
		</td>
		<td>
			<liferay-ui:message key="description" />
		</td>
	</tr>
	<tr>
		<td>
			<input name="<portlet:namespace /><%= displayTerms.NAME %>" size="20" type="text" value="<%= HtmlUtil.escape(displayTerms.getName()) %>" />
		</td>
		<td>
			
			<input name="<portlet:namespace /><%= displayTerms.DESCRIPTION %>" size="50" type="text" value="<%= HtmlUtil.escape(displayTerms.getDescription()) %>" />
		</td>
	</tr>
	</table>
</liferay-ui:search-toggle>

<%-- TBD Need to add permissions checks --%>
<div>
	<c:if test="<%= showAddButton %>">
		<input type="button" value="<liferay-ui:message key="add-definition" />" onClick="<portlet:namespace />addDefinition();" />
	</c:if>
</div>

<script type="text/javascript">
	function <portlet:namespace />addDefinition() {
		document.<portlet:namespace />fm.method = 'post';
		
		submitForm(document.<portlet:namespace />fm, '<portlet:actionURL windowState="<%= WindowState.MAXIMIZED.toString() %>"><portlet:param name="<%=ActionRequest.ACTION_NAME %>" value="ViewDefinition" /><portlet:param name="jspPage" value="/definition/edit_definition.jsp" /><portlet:param name="redirect" value="<%= currentURL %>" /></portlet:actionURL>');
	}

	<c:if test="<%= windowState.equals(WindowState.MAXIMIZED) %>">
		Liferay.Util.focusFormField(document.<portlet:namespace />fm.<portlet:namespace /><%= displayTerms.NAME %>);
		Liferay.Util.focusFormField(document.<portlet:namespace />fm.<portlet:namespace /><%= displayTerms.KEYWORDS %>);
	</c:if>
</script>