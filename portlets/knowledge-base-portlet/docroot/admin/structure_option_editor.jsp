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
KBStructureOption kbStructureOption = (KBStructureOption)request.getAttribute("structure_option_editor.jsp-kb_structure_option");

String kbStructureFieldIndex = ParamUtil.getString(request, "kbStructureFieldIndex", "[$KB_STRUCTURE_FIELD_INDEX$]");
String kbStructureOptionIndex = ParamUtil.getString(request, "kbStructureOptionIndex", "[$KB_STRUCTURE_OPTION_INDEX$]");

String kbStructureOptionId = ParamUtil.getString(request, "kbStructureField" + kbStructureFieldIndex + "kbStructureOptionId" + kbStructureOptionIndex, BeanPropertiesUtil.getString(kbStructureOption, "kbStructureOptionId"));
String label = ParamUtil.getString(request, "kbStructureField" + kbStructureFieldIndex + "kbStructureOptionLabel" + kbStructureOptionIndex, BeanPropertiesUtil.getString(kbStructureOption, "label"));
String value = ParamUtil.getString(request, "kbStructureField" + kbStructureFieldIndex + "kbStructureOptionValue" + kbStructureOptionIndex, BeanPropertiesUtil.getString(kbStructureOption, "value"));

String localizedLanguageId = ParamUtil.getString(request, "localizedLanguageId", defaultLanguageId);
%>

<div class="kb-structure-option-editor">
	<aui:input name='<%= "kbStructureField" + kbStructureFieldIndex + "kbStructureOptionId" + kbStructureOptionIndex %>' type="hidden" value="<%= kbStructureOptionId %>" />

	<div class="kb-block-labels kb-field-wrapper">
		<aui:input inlineField="<%= true %>" label="label" name='<%= "kbStructureField" + kbStructureFieldIndex + "kbStructureOptionLabel" + kbStructureOptionIndex %>' value="<%= label %>" />

		<aui:input inlineField="<%= true %>" label="value" name='<%= "kbStructureField" + kbStructureFieldIndex + "kbStructureOptionValue" + kbStructureOptionIndex %>' value="<%= value %>" />

		<aui:field-wrapper cssClass='<%= !localizedLanguageId.equals(defaultLanguageId) ? "yui3-aui-helper-hidden" : StringPool.BLANK %>' inlineField="<%= true %>" label="<%= StringPool.NBSP %>">

			<%
			String taglibURL = "javascript:" + renderResponse.getNamespace() + "moveKBStructureOptionUp(" + kbStructureFieldIndex + ", " + kbStructureOptionIndex + ");";
			%>

			<liferay-ui:icon
				image="../aui/circle-arrow-t"
				message="up"
				url="<%= taglibURL %>"
			/>

			<%
			taglibURL = "javascript:" + renderResponse.getNamespace() + "moveKBStructureOptionDown(" + kbStructureFieldIndex + ", " + kbStructureOptionIndex + ");";
			%>

			<liferay-ui:icon
				image="../aui/circle-arrow-b"
				message="down"
				url="<%= taglibURL %>"
			/>

			<%
			taglibURL = "javascript:" + renderResponse.getNamespace() + "addKBStructureOption(" + kbStructureFieldIndex + ");";
			%>

			<liferay-ui:icon
				image="../aui/circle-plus"
				message="add"
				url="<%= taglibURL %>"
			/>

			<%
			taglibURL = "javascript:" + renderResponse.getNamespace() + "removeKBStructureOption(" + kbStructureFieldIndex + ", " + kbStructureOptionIndex + ");";
			%>

			<liferay-ui:icon
				image="../aui/circle-minus"
				message="remove"
				url="<%= taglibURL %>"
			/>
		</aui:field-wrapper>
	</div>
</div>