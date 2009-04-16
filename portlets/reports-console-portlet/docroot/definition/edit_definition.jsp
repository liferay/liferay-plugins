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
	PortletURL portletURL = renderResponse.createActionURL();
	portletURL.setWindowState(WindowState.NORMAL);
	portletURL.setParameter("tabs1", "report-definitions");
	portletURL.setParameter(ActionRequest.ACTION_NAME, "searchDefinition");
	portletDisplay.setURLBack(portletURL.toString());

	ReportDefinition definition = (ReportDefinition)request.getAttribute("definition");
	String reportParameters = StringPool.BLANK;
	boolean isNew = false;
	if(definition == null){
		isNew = true;
	}
	else{
		reportParameters = definition.getReportParameters();
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
						
						document.<portlet:namespace />fm.encoding = "multipart/form-data";
					}
				);
		}
	);

	function <portlet:namespace />saveDefinition() {
		document.<portlet:namespace />fm.encoding = "multipart/form-data";
		submitForm(document.<portlet:namespace />fm, '<portlet:actionURL windowState="<%= WindowState.MAXIMIZED.toString() %>"><portlet:param name="<%= ActionRequest.ACTION_NAME %>" value="addDefinition" /></portlet:actionURL>');
	}

	function <portlet:namespace />updateDefinition() {
		submitForm(document.<portlet:namespace />fm, '<portlet:actionURL windowState="<%= WindowState.MAXIMIZED.toString() %>"><portlet:param name="<%= ActionRequest.ACTION_NAME %>" value="updateDefinition" /></portlet:actionURL>');
	}

	function <portlet:namespace />deleteDefinition() {
		if (confirm('<%= UnicodeLanguageUtil.get(pageContext, "are-you-sure-you-want-to-delete-the-report-definition") %>')) {
			submitForm(document.<portlet:namespace />fm, '<portlet:actionURL windowState="<%= WindowState.MAXIMIZED.toString() %>"><portlet:param name="<%= ActionRequest.ACTION_NAME %>" value="deleteDefinition" /></portlet:actionURL>');
		}
	}


	function <portlet:namespace />getParameter() {
		var param = '<%=reportParameters%>';
		document.<portlet:namespace />fm.<portlet:namespace />reportParameters.value = param;
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
			document.<portlet:namespace />fm.<portlet:namespace />reportParameters.value += ","+key+"="+value;
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
		var param = document.<portlet:namespace />fm.<portlet:namespace />reportParameters.value;
		var tem = "";
		var keyArray = new Array();
        keyArray = param.split(',');
		for (i = 0; i < keyArray.length; i++) {
			if(keyArray[i] && keyArray[i]!=key+"="+value){
				tem += keyArray[i]+",";
			}
		}
		document.<portlet:namespace />fm.<portlet:namespace />reportParameters.value = tem;
		jQuery("#"+key).remove();
	}
</script>

<form action="<portlet:actionURL windowState="<%= WindowState.MAXIMIZED.toString() %>"></portlet:actionURL>" method="post" name="<portlet:namespace />fm" >
<input name="<portlet:namespace />redirect" type="hidden" value="<%= currentURL %>" />

<liferay-ui:error exception="<%= DefinitionNameException.class %>" message="please-enter-a-valid-name" />
<liferay-ui:error exception="<%= DefinitionFileException.class %>" message="please-enter-a-valid-file" />
<liferay-ui:error exception="<%= DefinitionFormatException.class %>" message="please-enter-a-valid-format" />


<table class="lfr-table">
	<tr>
		<td>
			<liferay-ui:message key="id" />:
		</td>
		<td>
			<c:choose>
				<c:when test="<%= isNew %>">
					<liferay-ui:message key="autogenerate-id" />
				</c:when>
				<c:otherwise>
					<input name="<portlet:namespace />definitionId" type="hidden" value="<%= definition.getDefinitionId() %>" />
					<%= definition.getDefinitionId() %>
				</c:otherwise>
			</c:choose>
		</td>
	</tr>
	<tr>
		<td>
			<liferay-ui:message key="definition-name" />:
		</td>
		<td>
			<liferay-ui:input-field model="<%= ReportDefinition.class %>" bean="<%= definition %>" field="definitionName" />
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
			String format = StringPool.BLANK;
			if(!isNew){
				format = definition.getReportFormat();
			}
		%>
		<td>
			<liferay-ui:message key="report-format" />:
		</td>
		<td>
			<select id="<portlet:namespace />format" name="<portlet:namespace />format">
				<%
				for(ReportFormat reportFormat:ReportFormat.values()){
				%>
				<option <%if(reportFormat.toString().equals(format)){ %> selected="selected" <% } %> value="<%=reportFormat%>"><%=reportFormat %></option>
				<%
				}
				%>
			</select>
		</td>
	</tr>
	<tr>
		<td>
			<liferay-ui:message key="template" />:
		</td>
		<td>
			<c:if test="<%= definition == null %>">
				<input id="<portlet:namespace />msgFile" name="<portlet:namespace />msgFile" size="70" type="file" />
			</c:if>
			<c:if test="<%= definition != null %>">
			<% 			
			String fileName = (String)request.getAttribute("fileName");
			%>
				<span id="<portlet:namespace />existingFile">
					<input name="<portlet:namespace />fileName" type="hidden" value="<%= fileName %>" />		
					<%= fileName %>
					<img id="<portlet:namespace />removeExisting" src="<%= themeDisplay.getPathThemeImages() %>/arrows/02_x.png" />
				</span>
				<input id="<portlet:namespace />msgFile" name="<portlet:namespace />msgFile" size="70" style="display: none;" type="file" />

			</c:if>
		</td>
	</tr>
	<tr>
		<td>
			<liferay-ui:message key="reportParameters" />:
		</td>
		<td>
			<input id="<portlet:namespace />reportParameters" name="<portlet:namespace />reportParameters" type="hidden" />
			<liferay-ui:message key="key" /><input id="<portlet:namespace />key" name="<portlet:namespace />key" type="text" size="20"/>
			<liferay-ui:message key="value" /><input id="<portlet:namespace />value" name="<portlet:namespace />value" type="text"  size="20" />
			<input type="button" value="<liferay-ui:message key="add" />" onClick="<portlet:namespace />addParameter();" /><br />
			<span id="report-tags"></span>
		</td>
	</tr>

</table>

<br />
<portlet:actionURL windowState="<%= WindowState.MAXIMIZED.toString() %>" var="viewURL">
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
