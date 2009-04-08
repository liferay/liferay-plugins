<%
/**
 * Copyright (c) 2000-2009 Liferay, Inc. All rights reserved.
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
	ReportDefinition definition = (ReportDefinition)renderRequest.getAttribute("currentDefinition");
	long definitionId = -1;
	String parameter = StringPool.BLANK;
	if (definition != null) {
		definitionId = definition.getDefinitionId();
		parameter = definition.getReportParameters();
	}
%>

<script type="text/javascript">

	jQuery(
		function() {
			<portlet:namespace />getParameter();
		}
	);

	jQuery(
		function() {
				jQuery("#<portlet:namespace />removeExisting").click(
					function() {
						var button = jQuery(this);
						var span = jQuery("#<portlet:namespace />existingFile");
						var file = jQuery("#<portlet:namespace />msgFile");

						button.remove();
						span.remove();
						file.show();
					}
				);
		}
	);

	function <portlet:namespace />saveDefinition() {
		document.<portlet:namespace />fm.<portlet:namespace />jspPage.value = "/definition/edit_definition.jsp";
		document.<portlet:namespace />fm.<portlet:namespace />redirect.value = "<%= currentURL %>";
		submitForm(document.<portlet:namespace />fm, '<portlet:actionURL windowState="<%= WindowState.MAXIMIZED.toString() %>"><portlet:param name="<%= ActionRequest.ACTION_NAME %>" value="addDefinition" /></portlet:actionURL>');
	}

	function <portlet:namespace />updateDefinition() {
		document.<portlet:namespace />fm.<portlet:namespace />jspPage.value = "/definition/edit_definition.jsp";
		document.<portlet:namespace />fm.<portlet:namespace />redirect.value = "<%= currentURL %>";
		submitForm(document.<portlet:namespace />fm, '<portlet:actionURL windowState="<%= WindowState.MAXIMIZED.toString() %>"><portlet:param name="<%= ActionRequest.ACTION_NAME %>" value="updateDefinition" /></portlet:actionURL>');
	}

	function <portlet:namespace />deleteDefinition() {
		document.<portlet:namespace />fm.<portlet:namespace />jspPage.value = "/view.jsp";
		document.<portlet:namespace />fm.<portlet:namespace />redirect.value = "<%= currentURL %>";
		submitForm(document.<portlet:namespace />fm, '<portlet:actionURL windowState="<%= WindowState.MAXIMIZED.toString() %>"><portlet:param name="<%= ActionRequest.ACTION_NAME %>" value="deleteDefinition" /></portlet:actionURL>');
	}


	function <portlet:namespace />getParameter() {
		var param = '<%=parameter%>';
		document.<portlet:namespace />fm.<portlet:namespace />parameter.value = param;
		var keyArray = new Array();
        keyArray = param.split(',');
		for (i = 0; i < keyArray.length; i++) {
			var keyvalue = keyArray[i].split('=');
			if(keyvalue[0] && keyvalue[1]){
				<portlet:namespace />addTag(keyvalue[0],keyvalue[1]);
			}
		}
	}

	function <portlet:namespace />addParameter() {
		var key = document.<portlet:namespace />fm.<portlet:namespace />key.value;
		var value = document.<portlet:namespace />fm.<portlet:namespace />value.value;
		if(key.length == 0 || value.length == 0){
			alert("can not null");
		}else{
			document.<portlet:namespace />fm.<portlet:namespace />parameter.value += ","+key+"="+value;
			<portlet:namespace />addTag(key,value);
		}
	}

	function <portlet:namespace />addTag(key,value){
		var innrtHTML ="<span id=\""+key+"\"class=\"admin_tag\">" ;
		innrtHTML += key+"="+value;
		innrtHTML += "<img src=\"<%= themeDisplay.getPathThemeImages() %>/arrows/02_x.png\" ";
		innrtHTML += "onClick=\"<portlet:namespace />removeParameter("+key+","+value+");\" />";
		innrtHTML += "<//span><br //>";
		jQuery("#report-tags").append(innrtHTML);
	}

	function <portlet:namespace />removeParameter(key,value) {
		var param = document.<portlet:namespace />fm.<portlet:namespace />parameter.value;
		var tem = "";
		var keyArray = new Array();
        keyArray = param.split(',');
		for (i = 0; i < keyArray.length; i++) {
			if(keyArray[i] && keyArray[i]!=key+"="+value){
				tem += keyArray[i]+",";
			}
		}
		document.<portlet:namespace />fm.<portlet:namespace />parameter.value = tem;
		jQuery("#"+key).remove();
	}
</script>

<form action="<portlet:actionURL windowState="<%= WindowState.MAXIMIZED.toString() %>"></portlet:actionURL>" enctype="multipart/form-data" method="post" name="<portlet:namespace />fm" >
<input name="<portlet:namespace />redirect" type="hidden" value="<portlet:renderURL windowState="<%= WindowState.MAXIMIZED.toString() %>"><portlet:param name="jspPage" value="/edit_template.jsp" /><portlet:param name="redirect" value="<%= currentURL %>" /></portlet:renderURL>" />
<input name="<portlet:namespace />definitionId" type="hidden" value="<%= definitionId %>" />
<input name="<portlet:namespace />jspPage" type="hidden" />


<table class="lfr-table">
	<tr>
		<td>
			<liferay-ui:message key="name" />:
		</td>
		<td>
			<liferay-ui:input-field model="<%= ReportDefinition.class %>" bean="<%= definition %>" field="name" />
		</td>
	</tr>
	<tr>
		<td>
			<liferay-ui:message key="description" />:
		</td>
		<td>
			<liferay-ui:input-field model="<%= ReportDefinition.class %>" bean="<%= definition %>" field="description" />
		</td>
	</tr>
	<tr>
		<td>
			<liferay-ui:message key="datasource-name" />:
		</td>
		<td>
			<liferay-ui:input-field model="<%= ReportDefinition.class %>" bean="<%= definition %>" field="dataSourceName" />			
		</td>
	</tr>
	<tr>
		<%
			boolean htmlSelected = false;
			boolean defaulted = true;
			if (definition != null) {
				defaulted = false;
				htmlSelected = (ReportFormat.parse(definition.getReportFormat()) == ReportFormat.HTML);
			}
		%>
		<td>
			<liferay-ui:message key="report-format" />:
		</td>
		<td>
			<select id="<portlet:namespace />format" name="<portlet:namespace />format">
				<% if (defaulted || htmlSelected) { %>
					<option selected="true" value="<%=ReportFormat.HTML.toString()%>">HTML</option>
					<option value="<%=ReportFormat.PDF.toString()%>">PDF</option>
				<% } else {%>
					<option value="<%=ReportFormat.HTML.toString()%>">HTML</option>
					<option selected="true" value="<%=ReportFormat.PDF.toString()%>">PDF</option>
				<% } %>
			</select>
		</td>
	</tr>
	<tr>
		<td>
			<liferay-ui:message key="template" />:
		</td>
		<td>
			<c:if test="<%= reportTemplate == null %>">
				<input id="<portlet:namespace />msgFile" name="<portlet:namespace />msgFile" size="70" type="file" />
			</c:if>
			<c:if test="<%= reportTemplate != null %>">
			<% 
			String[] existingAttachments = new String[0];
			
			existingAttachments = DLServiceUtil.getFileNames(definition.getCompanyId(), CompanyConstants.SYSTEM, "/");
			
			String existingName = "NA";
			if(existingAttachments.length!=0){
				existingName = StringUtil.extractLast(existingAttachments[0], StringPool.SLASH);
			}

			%>
				<span id="<portlet:namespace />existingFile">
					<input name="<portlet:namespace />existingPath" type="hidden" value="<%= existingName %>" />		
					<%= existingName %>
					<img id="<portlet:namespace />removeExisting" src="<%= themeDisplay.getPathThemeImages() %>/arrows/02_x.png" />
				</span>
				<input id="<portlet:namespace />msgFile" name="<portlet:namespace />msgFile" size="70" style="display: none;" type="file" />

			</c:if>
		</td>
	</tr>
	<tr>
		<td>
			<liferay-ui:message key="parameter" />:
		</td>
		<td>
			<input id="<portlet:namespace />parameter" name="<portlet:namespace />parameter" type="hidden" />
			<liferay-ui:message key="key" /><input id="<portlet:namespace />key" name="<portlet:namespace />key" type="text" size="20"/>
			<liferay-ui:message key="value" /><input id="<portlet:namespace />value" name="<portlet:namespace />value" type="text"  size="20" />
			<input type="button" value="<liferay-ui:message key="add" />" onClick="<portlet:namespace />addParameter();" /><br />
			<span id="report-tags"></span>
		</td>
	</tr>

</table>

<br />
<portlet:actionURL windowState="<%= WindowState.MAXIMIZED.toString() %>" name="searchDefinition" var="viewURL">
	<portlet:param name="jspPage" value="/view.jsp" />
	<portlet:param name="redirect" value="<%= currentURL %>" />
</portlet:actionURL>

<c:if test="<%= definition == null %>">
	<input type="button" value="<liferay-ui:message key="save" />" onClick="<portlet:namespace />saveDefinition();" />
</c:if>

<c:if test="<%= definition != null %>">
	<input type="button" value="<liferay-ui:message key="update" />" onClick="<portlet:namespace />updateDefinition();" />

	<input type="button" value="<liferay-ui:message key="generate-immdiately" />" />

	<input type="button" value="<liferay-ui:message key="add-schedule" />" />

	<input type="button" value="<liferay-ui:message key="delete" />" onClick="<portlet:namespace />deleteDefinition();" />
</c:if>



<input type="button" value="<liferay-ui:message key="cancel" />" onClick="location.href = '<%= viewURL %>';" />

</form>
