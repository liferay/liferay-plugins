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
KBStructureField kbStructureField = (KBStructureField)request.getAttribute("structure_field_editor.jsp-kb_structure_field");

String kbStructureFieldIndex = ParamUtil.getString(request, "kbStructureFieldIndex", "[$KB_STRUCTURE_FIELD_INDEX$]");
long[] kbStructureOptionsIndexes = StringUtil.split(ParamUtil.getString(request, "kbStructureOptionsIndexes" + kbStructureFieldIndex), 0L);

String kbStructureFieldId = ParamUtil.getString(request, "kbStructureFieldId" + kbStructureFieldIndex, BeanPropertiesUtil.getString(kbStructureField, "kbStructureFieldId"));
String name = ParamUtil.getString(request, "kbStructureFieldName" + kbStructureFieldIndex, BeanPropertiesUtil.getString(kbStructureField, "name"));
String label = ParamUtil.getString(request, "kbStructureFieldLabel" + kbStructureFieldIndex, BeanPropertiesUtil.getString(kbStructureField, "label"));
String type = ParamUtil.getString(request, "kbStructureFieldType" + kbStructureFieldIndex, BeanPropertiesUtil.getString(kbStructureField, "type", KBStructureFieldConstants.DEFAULT_TYPE));

String localizedLanguageId = ParamUtil.getString(request, "localizedLanguageId", defaultLanguageId);

List<KBStructureOption> kbStructureOptions = new ArrayList<KBStructureOption>();

if (request.getParameter("kbStructureOptionsIndexes" + kbStructureFieldIndex) != null) {
	for (int i = 0; i < kbStructureOptionsIndexes.length; i++) {
		kbStructureOptions.add(new KBStructureOptionImpl());
	}
}
else if (kbStructureField != null) {
	kbStructureOptions = kbStructureField.getKbStructureOptions();
	kbStructureOptionsIndexes = new long[kbStructureOptions.size()];

	for (int i = 0; i < kbStructureOptions.size(); i++) {
		kbStructureOptionsIndexes[i] = i;
	}
}
%>

<div class="kb-structure-field-editor">
	<aui:input name='<%= "kbStructureFieldId" + kbStructureFieldIndex %>' type="hidden" value="<%= kbStructureFieldId %>" />
	<aui:input name='<%= "kbStructureOptionsIndexes" + kbStructureFieldIndex %>' type="hidden" value="<%= StringUtil.merge(kbStructureOptionsIndexes) %>" />

	<div class="kb-block-labels kb-field-wrapper">
		<aui:input cssClass='<%= !localizedLanguageId.equals(defaultLanguageId) ? "aui-helper-hidden" : StringPool.BLANK %>' inlineField="<%= true %>" label="name" name='<%= "kbStructureFieldName" + kbStructureFieldIndex %>' value="<%= name %>" />

		<c:if test="<%= !localizedLanguageId.equals(defaultLanguageId) %>">
			<aui:input disabled="<%= true %>" inlineField="<%= true %>" label="name" name='<%= "disabledKBStructureFieldName" + kbStructureFieldIndex %>' value="<%= name %>" />
		</c:if>

		<aui:input inlineField="<%= true %>" label="label" name='<%= "kbStructureFieldLabel" + kbStructureFieldIndex %>' value="<%= label %>" />

		<%
		String taglibOnChange = renderResponse.getNamespace() + "updateKBStructureFieldType(" + kbStructureFieldIndex + ", this.value);";
		%>

		<aui:select cssClass='<%= !localizedLanguageId.equals(defaultLanguageId) ? "aui-helper-hidden" : StringPool.BLANK %>' ignoreRequestValue="<%= true %>" inlineField="<%= true %>" label="type" name='<%= "kbStructureFieldType" + kbStructureFieldIndex %>' onChange="<%= taglibOnChange %>">

			<%
			for (String curType : KBStructureFieldConstants.TYPES) {
			%>

				<aui:option label="<%= curType %>" selected="<%= type.equals(curType) %>" />

			<%
			}
			%>

		</aui:select>

		<c:if test="<%= !localizedLanguageId.equals(defaultLanguageId) %>">
			<aui:select disabled="<%= true %>" ignoreRequestValue="<%= true %>" inlineField="<%= true %>" label="type" name='<%= "disabledKBStructureFieldType" + kbStructureFieldIndex %>'>
				<aui:option label="<%= type %>" selected="<%= true %>" />
			</aui:select>
		</c:if>

		<aui:field-wrapper cssClass='<%= !localizedLanguageId.equals(defaultLanguageId) ? "aui-helper-hidden" : StringPool.BLANK %>' inlineField="<%= true %>" label="<%= StringPool.NBSP %>">

			<%
			String taglibURL = "javascript:" + renderResponse.getNamespace() + "moveKBStructureFieldUp(" + kbStructureFieldIndex + ");";
			%>

			<liferay-ui:icon
				image="../aui/circle-arrow-t"
				message="up"
				url="<%= taglibURL %>"
			/>

			<%
			taglibURL = "javascript:" + renderResponse.getNamespace() + "moveKBStructureFieldDown(" + kbStructureFieldIndex + ");";
			%>

			<liferay-ui:icon
				image="../aui/circle-arrow-b"
				message="down"
				url="<%= taglibURL %>"
			/>

			<%
			taglibURL = "javascript:" + renderResponse.getNamespace() + "addKBStructureField();";
			%>

			<liferay-ui:icon
				image="../aui/circle-plus"
				message="add"
				url="<%= taglibURL %>"
			/>

			<%
			taglibURL = "javascript:" + renderResponse.getNamespace() + "removeKBStructureField(" + kbStructureFieldIndex + ");";
			%>

			<liferay-ui:icon
				image="../aui/circle-minus"
				message="remove"
				url="<%= taglibURL %>"
			/>
		</aui:field-wrapper>
	</div>
	<div id="<portlet:namespace />kbStructureOptions<%= kbStructureFieldIndex %>">

		<%
		for (int i = 0; i < kbStructureOptions.size(); i++) {
			request.setAttribute("structure_option_editor.jsp-kb_structure_option", kbStructureOptions.get(i));
		%>

			<div id="<portlet:namespace />kbStructureOptions<%= kbStructureFieldIndex %>kbStructureOption<%= kbStructureOptionsIndexes[i] %>">
				<liferay-util:include page="/admin/structure_option_editor.jsp" servletContext="<%= application %>">
					<liferay-util:param name="kbStructureFieldIndex" value="<%= kbStructureFieldIndex %>" />
					<liferay-util:param name="kbStructureOptionIndex" value="<%= String.valueOf(kbStructureOptionsIndexes[i]) %>" />
				</liferay-util:include>
			</div>

		<%
			request.removeAttribute("structure_option_editor.jsp-kb_structure_option");
		}
		%>

	</div>
</div>