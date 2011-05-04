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
KBStructure kbStructure = (KBStructure)request.getAttribute(WebKeys.KNOWLEDGE_BASE_KB_STRUCTURE);

long kbStructureId = BeanParamUtil.getLong(kbStructure, request, "kbStructureId");

String localizedLanguageId = ParamUtil.getString(request, "localizedLanguageId", defaultLanguageId);
%>

<liferay-ui:header
	backURL="<%= redirect %>"
	title='<%= (kbStructure != null) ? kbStructure.getTitle(locale) : "new-structure" %>'
/>

<liferay-portlet:actionURL name="updateKBStructure" var="updateKBStructureURL">
	<portlet:param name="jspPage" value='<%= jspPath + "edit_structure.jsp" %>' />
	<portlet:param name="redirect" value="<%= redirect %>" />
	<portlet:param name="kbStructureId" value="<%= String.valueOf(kbStructureId) %>" />
</liferay-portlet:actionURL>

<aui:form action="<%= updateKBStructureURL %>" method="post" name="fm" onSubmit='<%= "event.preventDefault(); " + renderResponse.getNamespace() + "updateKBStructure();" %>'>
	<aui:input name="<%= Constants.CMD %>" type="hidden" />
	<aui:input name="localizedLanguageId" type="hidden" value="<%= localizedLanguageId %>" />

	<liferay-ui:error exception="<%= DuplicateKBStructureFieldLabelException.class %>" message="please-enter-a-unique-field-label" />
	<liferay-ui:error exception="<%= DuplicateKBStructureFieldNameException.class %>" message="please-enter-a-unique-field-name" />
	<liferay-ui:error exception="<%= DuplicateKBStructureOptionLabelException.class %>" message="please-enter-a-unique-option-label" />
	<liferay-ui:error exception="<%= DuplicateKBStructureOptionValueException.class %>" message="please-enter-a-unique-option-value" />
	<liferay-ui:error exception="<%= KBStructureFieldLabelException.class %>" message="please-enter-a-valid-field-label" />
	<liferay-ui:error exception="<%= KBStructureFieldNameException.class %>" message="please-enter-a-valid-field-name" />
	<liferay-ui:error exception="<%= KBStructureOptionLabelException.class %>" message="please-enter-a-valid-option-label" />
	<liferay-ui:error exception="<%= KBStructureOptionValueException.class %>" message="please-enter-a-valid-option-value" />
	<liferay-ui:error exception="<%= KBStructureTitleException.class %>" message="please-enter-a-valid-title" />

	<aui:model-context bean="<%= kbStructure %>" model="<%= KBStructure.class %>" />

	<aui:fieldset>
		<c:if test="<%= kbStructure != null %>">
			<div class="kb-block-labels kb-field-wrapper">
				<aui:select disabled="<%= true %>" ignoreRequestValue="<%= true %>" inlineField="<%= true %>" name="defaultLanguage">
					<aui:option label="<%= defaultLocale.getDisplayName(locale) %>" selected="<%= true %>" />
				</aui:select>

				<liferay-portlet:renderURL var="editKBStructureURL">
					<portlet:param name="jspPage" value='<%= jspPath + "edit_structure.jsp" %>' />
					<portlet:param name="redirect" value="<%= redirect %>" />
					<portlet:param name="kbStructureId" value="<%= String.valueOf(kbStructureId) %>" />
				</liferay-portlet:renderURL>

				<%
				String taglibOnChange = renderResponse.getNamespace() + "updateLocalizedLanguageId('" + editKBStructureURL + "&" + renderResponse.getNamespace() + "localizedLanguageId=' + this.value); this.value = '" + localizedLanguageId + "';";
				%>

				<aui:select ignoreRequestValue="<%= true %>" inlineField="<%= true %>" name="currentLanguage" onChange="<%= taglibOnChange %>">

					<%
					for (Locale curLocale : LanguageUtil.getAvailableLocales()) {
					%>

						<aui:option label="<%= curLocale.getDisplayName(locale) %>" selected="<%= localizedLanguageId.equals(LocaleUtil.toLanguageId(curLocale)) %>" value="<%= LocaleUtil.toLanguageId(curLocale) %>" />

					<%
					}
					%>

				</aui:select>

				<%
				taglibOnChange = renderResponse.getNamespace() + "updateLocalizedLanguageId('" + editKBStructureURL + "&" + renderResponse.getNamespace() + "localizedLanguageId=' + this.value); this.value = '';";
				%>

				<aui:select ignoreRequestValue="<%= true %>" inlineField="<%= true %>" name="translations" onChange="<%= taglibOnChange %>" showEmptyOption="<%= true %>">

					<%
					for (String languageId : kbStructure.getLanguageIds()) {
					%>

						<aui:option label="<%= LocaleUtil.fromLanguageId(languageId).getDisplayName(locale) %>" value="<%= languageId %>" />

					<%
					}
					%>

				</aui:select>
			</div>
		</c:if>

		<aui:input languageId="<%= localizedLanguageId %>" name="title" />

		<liferay-util:include page="/admin/structure_content_editor.jsp" servletContext="<%= application %>" />

		<c:if test="<%= kbStructure == null %>">
			<aui:field-wrapper label="permissions">
				<liferay-ui:input-permissions
					modelName="<%= KBStructure.class.getName() %>"
				/>
			</aui:field-wrapper>
		</c:if>

		<aui:button-row cssClass="kb-submit-buttons">
			<aui:button type="submit" value="publish" />

			<c:if test="<%= !localizedLanguageId.equals(defaultLanguageId) && ArrayUtil.contains(kbStructure.getLanguageIds(), localizedLanguageId) && KBStructurePermission.contains(permissionChecker, kbStructure, ActionKeys.DELETE) %>">
				<aui:button onClick='<%= renderResponse.getNamespace() + "deleteKBStructureLocalization();" %>' value="remove-translation" />
			</c:if>

			<aui:button onClick="<%= redirect %>" type="cancel" />
		</aui:button-row>
	</aui:fieldset>
</aui:form>

<aui:script>
	function <portlet:namespace />deleteKBStructureLocalization() {
		submitForm(document.<portlet:namespace />fm, "<liferay-portlet:actionURL name="deleteKBStructureLocalization"><portlet:param name="jspPage" value='<%= jspPath + "edit_structure.jsp" %>' /><portlet:param name="redirect" value="<%= redirect %>" /><portlet:param name="kbStructureId" value="<%= String.valueOf(kbStructureId) %>" /></liferay-portlet:actionURL>");
	}

	function <portlet:namespace />updateLocalizedLanguageId(editKBStructureURL) {
		if (confirm('<%= UnicodeLanguageUtil.get(pageContext, "changing-the-value-of-this-field-will-reload-the-page") %>')) {
			window.location = editKBStructureURL;
		}
	}

	function <portlet:namespace />updateKBStructure() {
		document.<portlet:namespace />fm.<portlet:namespace /><%= Constants.CMD %>.value = "<%= (kbStructure == null) ? Constants.ADD : Constants.UPDATE %>";
		submitForm(document.<portlet:namespace />fm);
	}
</aui:script>