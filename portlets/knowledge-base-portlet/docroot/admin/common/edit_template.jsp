<%--
/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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
--%>

<%@ include file="/admin/init.jsp" %>

<%
KBTemplate kbTemplate = (KBTemplate)request.getAttribute(WebKeys.KNOWLEDGE_BASE_KB_TEMPLATE);

long kbTemplateId = BeanParamUtil.getLong(kbTemplate, request, "kbTemplateId");

String content = BeanParamUtil.getString(kbTemplate, request, "content");
%>

<liferay-ui:header
	backURL="<%= redirect %>"
	title='<%= (kbTemplate != null) ? kbTemplate.getTitle() : "new-template" %>'
/>

<liferay-portlet:actionURL name="updateKBTemplate" var="updateKBTemplateURL">
	<portlet:param name="jspPage" value='<%= jspPath + "edit_template.jsp" %>' />
	<portlet:param name="redirect" value="<%= redirect %>" />
	<portlet:param name="kbTemplateId" value="<%= String.valueOf(kbTemplateId) %>" />
</liferay-portlet:actionURL>

<aui:form action="<%= updateKBTemplateURL %>" method="post" name="fm" onSubmit='<%= "event.preventDefault(); " + renderResponse.getNamespace() + "updateKBTemplate();" %>'>
	<aui:input name="<%= Constants.CMD %>" type="hidden" />

	<liferay-ui:error exception="<%= KBTemplateContentException.class %>" message="please-enter-valid-content" />
	<liferay-ui:error exception="<%= KBTemplateTitleException.class %>" message="please-enter-a-valid-title" />

	<aui:model-context bean="<%= kbTemplate %>" model="<%= KBTemplate.class %>" />

	<aui:fieldset>
		<aui:input name="title" />

		<aui:field-wrapper label="content">
			<liferay-ui:input-editor width="100%" />

			<aui:input name="content" type="hidden" />
		</aui:field-wrapper>

		<c:if test="<%= kbTemplate == null %>">
			<aui:field-wrapper label="permissions">
				<liferay-ui:input-permissions
					modelName="<%= KBTemplate.class.getName() %>"
				/>
			</aui:field-wrapper>
		</c:if>

		<aui:button-row cssClass="kb-submit-buttons">
			<aui:button type="submit" value="publish" />

			<aui:button href="<%= redirect %>" type="cancel" />
		</aui:button-row>
	</aui:fieldset>
</aui:form>

<aui:script>
	function <portlet:namespace />initEditor() {
		return "<%= UnicodeFormatter.toString(content) %>";
	}

	function <portlet:namespace />updateKBTemplate() {
		document.<portlet:namespace />fm.<portlet:namespace /><%= Constants.CMD %>.value = "<%= (kbTemplate == null) ? Constants.ADD : Constants.UPDATE %>";
		document.<portlet:namespace />fm.<portlet:namespace />content.value = window.<portlet:namespace />editor.getHTML();
		submitForm(document.<portlet:namespace />fm);
	}
</aui:script>