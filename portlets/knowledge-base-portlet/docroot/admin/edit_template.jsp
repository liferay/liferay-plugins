<%
/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */
%>

<%@ include file="/admin/init.jsp" %>

<%
String redirect = ParamUtil.getString(request, "redirect");

Template template = (Template)request.getAttribute(WebKeys.KNOWLEDGE_BASE_TEMPLATE);

long templateId = BeanParamUtil.getLong(template, request, "templateId");

String content = BeanParamUtil.getString(template, request, "content");
%>

<liferay-ui:header
	backURL="<%= redirect %>"
	title='<%= (template != null) ? template.getTitle() : "new-template" %>'
/>

<portlet:actionURL name="updateTemplate" var="updateTemplateURL">
	<portlet:param name="jspPage" value="/admin/edit_template.jsp" />
	<portlet:param name="redirect" value="<%= redirect %>" />
</portlet:actionURL>

<aui:form action="<%= updateTemplateURL %>" method="post" name="fm" onSubmit='<%= "event.preventDefault(); " + renderResponse.getNamespace() + "updateTemplate();" %>'>
	<aui:input name="templateId" type="hidden" value="<%= templateId %>" />

	<liferay-ui:error exception="<%= TemplateContentException.class %>" message="please-enter-valid-content" />
	<liferay-ui:error exception="<%= TemplateTitleException.class %>" message="please-enter-a-valid-title" />

	<aui:model-context bean="<%= template %>" model="<%= Template.class %>" />

	<aui:fieldset>
		<aui:input name="title" />

		<aui:field-wrapper label="content">
			<liferay-ui:input-editor width="100%" />

			<aui:input name="content" type="hidden" />
		</aui:field-wrapper>

		<c:if test="<%= enableTemplateDescription %>">
			<aui:input name="description" />
		</c:if>

		<c:if test="<%= template == null %>">
			<aui:field-wrapper label="permissions">
				<liferay-ui:input-permissions
					modelName="<%= Template.class.getName() %>"
				/>
			</aui:field-wrapper>
		</c:if>

		<aui:button-row>
			<aui:button type="submit" value="publish" />

			<aui:button onClick="<%= redirect %>" type="cancel" />
		</aui:button-row>
	</aui:fieldset>
</aui:form>

<aui:script>
	function <portlet:namespace />initEditor() {
		return "<%= UnicodeFormatter.toString(content) %>";
	}

	function <portlet:namespace />updateTemplate() {
		document.<portlet:namespace />fm.<portlet:namespace />content.value = window.<portlet:namespace />editor.getHTML();
		submitForm(document.<portlet:namespace />fm);
	}
</aui:script>