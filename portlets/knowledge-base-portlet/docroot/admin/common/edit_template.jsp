<%--
/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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
int engineType = BeanParamUtil.getInteger(kbTemplate, request, "engineType", KBTemplateConstants.DEFAULT_ENGINE_TYPE);
boolean cacheable = BeanParamUtil.getBoolean(kbTemplate, request, "cacheable", true);
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

	<liferay-ui:error exception="<%= KBTemplateTitleException.class %>" message="please-enter-a-valid-title" />

	<liferay-ui:error exception="<%= KBTemplateContentException.class %>">

		<%
		KBTemplateContentException kbtce = (KBTemplateContentException)errorException;
		%>

		<c:choose>
			<c:when test="<%= Validator.isNotNull(kbtce.getMessage()) %>">
				<%= kbtce.getMessage() %>
			</c:when>
			<c:otherwise>
				<liferay-ui:message key="please-enter-valid-content" />
			</c:otherwise>
		</c:choose>
	</liferay-ui:error>

	<aui:model-context bean="<%= kbTemplate %>" model="<%= KBTemplate.class %>" />

	<aui:fieldset>
		<aui:input name="title" />

		<aui:input cssClass="kb-template-editor" name="content" type="textarea" wrap="off" />

		<%
		String taglibOnChange = renderResponse.getNamespace() + "updateEngineType(this.value);";
		%>

		<aui:field-wrapper label="engine">
			<aui:select inlineField="<%= true %>" label="" name="engineType" onChange="<%= taglibOnChange %>">

				<%
				for (int curEngineType : KBTemplateConstants.ENGINE_TYPES) {
				%>

					<aui:option label="<%= KBTemplateConstants.getEngineTypeLabel(curEngineType) %>" selected="<%= engineType == curEngineType %>" value="<%= curEngineType %>" />

				<%
				}
				%>

			</aui:select>

			<aui:select inlineField="<%= true %>" label="" name="cacheable">
				<aui:option label="enable-cache" selected="<%= cacheable %>" value="<%= true %>" />
				<aui:option label="disable-cache" selected="<%= !cacheable %>" value="<%= false %>" />
			</aui:select>
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

			<aui:button onClick="<%= redirect %>" type="cancel" />
		</aui:button-row>
	</aui:fieldset>
</aui:form>

<aui:script>
	function <portlet:namespace />updateEngineType(value) {
		var defaultFreeMarkerContent = "<%= UnicodeFormatter.toString(ContentUtil.get(PortletPropsValues.ADMIN_KB_TEMPLATE_CONTENT_FREEMARKER)) %>";
		var defaultVelocityContent = "<%= UnicodeFormatter.toString(ContentUtil.get(PortletPropsValues.ADMIN_KB_TEMPLATE_CONTENT_VELOCITY)) %>";

		var contentEl = document.<portlet:namespace />fm.<portlet:namespace />content;

		if ((contentEl.value == "") || (contentEl.value == defaultFreeMarkerContent) || (contentEl.value == defaultVelocityContent)) {
			if (value == "<%= KBTemplateConstants.ENGINE_TYPE_FREEMARKER %>") {
				document.<portlet:namespace />fm.<portlet:namespace />content.value = defaultFreeMarkerContent;
			}
			else if (value == "<%= KBTemplateConstants.ENGINE_TYPE_VELOCITY %>") {
				document.<portlet:namespace />fm.<portlet:namespace />content.value = defaultVelocityContent;
			}
		}
	}

	function <portlet:namespace />updateKBTemplate() {
		document.<portlet:namespace />fm.<portlet:namespace /><%= Constants.CMD %>.value = "<%= (kbTemplate == null) ? Constants.ADD : Constants.UPDATE %>";
		submitForm(document.<portlet:namespace />fm);
	}

	<portlet:namespace />updateEngineType("<%= engineType %>");
</aui:script>