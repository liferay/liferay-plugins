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

String content = BeanParamUtil.getString(kbStructure, request, "content");

String localizedLanguageId = ParamUtil.getString(request, "localizedLanguageId", defaultLanguageId);
long[] kbStructureFieldsIndexes = StringUtil.split(ParamUtil.getString(request, "kbStructureFieldsIndexes"), 0L);

List<KBStructureField> kbStructureFields = new ArrayList<KBStructureField>();

if (request.getParameter("kbStructureFieldsIndexes") != null) {
	for (int i = 0; i < kbStructureFieldsIndexes.length; i++) {
		kbStructureFields.add(new KBStructureFieldImpl());
	}
}
else {
	kbStructureFields = KBStructureContentUtil.getKBStructureFields(localizedLanguageId, content);
	kbStructureFieldsIndexes = new long[kbStructureFields.size()];

	for (int i = 0; i < kbStructureFields.size(); i++) {
		kbStructureFieldsIndexes[i] = i;
	}
}

if (kbStructureFields.isEmpty()) {
	kbStructureFields.add(new KBStructureFieldImpl());
	kbStructureFieldsIndexes = new long[] {0};
}
%>

<div class="kb-structure-content-editor">
	<aui:input name="kbStructureFieldsIndexes" type="hidden" value="<%= StringUtil.merge(kbStructureFieldsIndexes) %>" />

	<div id="<portlet:namespace />kbStructureFields">

		<%
		for (int i = 0; i < kbStructureFields.size(); i++) {
			request.setAttribute("structure_field_editor.jsp-kb_structure_field", kbStructureFields.get(i));
		%>

			<div id="<portlet:namespace />kbStructureField<%= kbStructureFieldsIndexes[i] %>">
				<liferay-util:include page="/admin/structure_field_editor.jsp" servletContext="<%= application %>">
					<liferay-util:param name="kbStructureFieldIndex" value="<%= String.valueOf(kbStructureFieldsIndexes[i]) %>" />
				</liferay-util:include>
			</div>

		<%
			request.removeAttribute("structure_field_editor.jsp-kb_structure_field");
		}
		%>

	</div>
</div>

<liferay-util:buffer var="structureFieldEditorHTML">
	<liferay-util:include page="/admin/structure_field_editor.jsp" servletContext="<%= application %>" />
</liferay-util:buffer>

<liferay-util:buffer var="structureOptionEditorHTML">
	<liferay-util:include page="/admin/structure_option_editor.jsp" servletContext="<%= application %>" />
</liferay-util:buffer>

<aui:script>
	function <portlet:namespace />addKBStructureField() {
		var kbStructureFieldsIndexesEl = document.getElementById("<portlet:namespace />kbStructureFieldsIndexes");
		var kbStructureFieldsIndexes = (kbStructureFieldsIndexesEl.value != "") ? kbStructureFieldsIndexesEl.value.split(",") : [];

		var kbStructureFieldIndex = 0;

		for (var i = 0; i < kbStructureFieldsIndexes.length; i++) {
			if (kbStructureFieldIndex < (parseInt(kbStructureFieldsIndexes[i]) + 1)) {
				kbStructureFieldIndex = (parseInt(kbStructureFieldsIndexes[i]) + 1);
			}
		}

		kbStructureFieldsIndexes.push(kbStructureFieldIndex);

		kbStructureFieldsIndexesEl.value = kbStructureFieldsIndexes.join();

		var html = "<%= UnicodeFormatter.toString(structureFieldEditorHTML) %>";

		html = html.replace(/<%= UnicodeFormatter.toString("[$KB_STRUCTURE_FIELD_INDEX$]") %>/gi, kbStructureFieldIndex);

		var kbStructureFieldEl = document.createElement("div");

		kbStructureFieldEl.id = "<portlet:namespace />kbStructureField" + kbStructureFieldIndex;
		kbStructureFieldEl.innerHTML = html;

		document.getElementById("<portlet:namespace />kbStructureFields").appendChild(kbStructureFieldEl);
	}

	function <portlet:namespace />addKBStructureOption(kbStructureFieldIndex) {
		var kbStructureOptionsIndexesEl = document.getElementById("<portlet:namespace />kbStructureOptionsIndexes" + kbStructureFieldIndex);
		var kbStructureOptionsIndexes = (kbStructureOptionsIndexesEl.value != "") ? kbStructureOptionsIndexesEl.value.split(",") : [];

		var kbStructureOptionIndex = 0;

		for (var i = 0; i < kbStructureOptionsIndexes.length; i++) {
			if (kbStructureOptionIndex < (parseInt(kbStructureOptionsIndexes[i]) + 1)) {
				kbStructureOptionIndex = (parseInt(kbStructureOptionsIndexes[i]) + 1);
			}
		}

		kbStructureOptionsIndexes.push(kbStructureOptionIndex);

		kbStructureOptionsIndexesEl.value = kbStructureOptionsIndexes.join();

		var html = "<%= UnicodeFormatter.toString(structureOptionEditorHTML) %>";

		html = html.replace(/<%= UnicodeFormatter.toString("[$KB_STRUCTURE_FIELD_INDEX$]") %>/gi, kbStructureFieldIndex);
		html = html.replace(/<%= UnicodeFormatter.toString("[$KB_STRUCTURE_OPTION_INDEX$]") %>/gi, kbStructureOptionIndex);

		var kbStructureOptionEl = document.createElement("div");

		kbStructureOptionEl.id = "<portlet:namespace />kbStructureOptions" + kbStructureFieldIndex + "kbStructureOption" + kbStructureOptionIndex;
		kbStructureOptionEl.innerHTML = html;

		document.getElementById("<portlet:namespace />kbStructureOptions" + kbStructureFieldIndex).appendChild(kbStructureOptionEl);
	}

	function <portlet:namespace />moveDown(containerId, indexesId, selId, selIndex) {
		var indexesEl = document.getElementById(indexesId);
		var indexes = (indexesEl.value != "") ? indexesEl.value.split(",") : [];

		for (var i = 0; i < indexes.length; i++) {
			if (indexes[i] == selIndex) {
				if (i == (indexes.length - 1)) {
					return;
				}

				indexes.splice(i, 1);
				indexes.splice(i + 1, 0, selIndex);

				break;
			}
		}

		var containerEl = document.getElementById(containerId);

		for (var i = 0; i < containerEl.childNodes.length; i++) {
			var childEl = containerEl.childNodes[i];

			if (childEl.nodeType == 3) {
				containerEl.removeChild(childEl);

				i = i - 1;
			}
		}

		var selEl = document.getElementById(selId);

		if (selEl.nextSibling) {
			containerEl.insertBefore(selEl, selEl.nextSibling.nextSibling);
			indexesEl.value = indexes.join();
		}
	}

	function <portlet:namespace />moveKBStructureFieldDown(kbStructureFieldIndex) {
		var containerId = "<portlet:namespace />kbStructureFields";
		var indexesId = "<portlet:namespace />kbStructureFieldsIndexes";
		var selId = "<portlet:namespace />kbStructureField" + kbStructureFieldIndex;

		<portlet:namespace />moveDown(containerId, indexesId, selId, kbStructureFieldIndex);
	}

	function <portlet:namespace />moveKBStructureOptionDown(kbStructureFieldIndex, kbStructureOptionIndex) {
		var containerId = "<portlet:namespace />kbStructureOptions" + kbStructureFieldIndex;
		var indexesId = "<portlet:namespace />kbStructureOptionsIndexes" + kbStructureFieldIndex;
		var selId = "<portlet:namespace />kbStructureOptions" + kbStructureFieldIndex + "kbStructureOption" + kbStructureOptionIndex;

		<portlet:namespace />moveDown(containerId, indexesId, selId, kbStructureOptionIndex);
	}

	function <portlet:namespace />moveKBStructureFieldUp(kbStructureFieldIndex) {
		var containerId = "<portlet:namespace />kbStructureFields";
		var indexesId = "<portlet:namespace />kbStructureFieldsIndexes";
		var selId = "<portlet:namespace />kbStructureField" + kbStructureFieldIndex;

		<portlet:namespace />moveUp(containerId, indexesId, selId, kbStructureFieldIndex);
	}

	function <portlet:namespace />moveKBStructureOptionUp(kbStructureFieldIndex, kbStructureOptionIndex) {
		var containerId = "<portlet:namespace />kbStructureOptions" + kbStructureFieldIndex;
		var indexesId = "<portlet:namespace />kbStructureOptionsIndexes" + kbStructureFieldIndex;
		var selId = "<portlet:namespace />kbStructureOptions" + kbStructureFieldIndex + "kbStructureOption" + kbStructureOptionIndex;

		<portlet:namespace />moveUp(containerId, indexesId, selId, kbStructureOptionIndex);
	}

	function <portlet:namespace />moveUp(containerId, indexesId, selId, selIndex) {
		var indexesEl = document.getElementById(indexesId);
		var indexes = (indexesEl.value != "") ? indexesEl.value.split(",") : [];

		for (var i = 0; i < indexes.length; i++) {
			if (indexes[i] == selIndex) {
				if (i == 0) {
					return;
				}

				indexes.splice(i, 1);
				indexes.splice(i - 1, 0, selIndex);

				break;
			}
		}

		var containerEl = document.getElementById(containerId);

		for (var i = 0; i < containerEl.childNodes.length; i++) {
			var childEl = containerEl.childNodes[i];

			if (childEl.nodeType == 3) {
				containerEl.removeChild(childEl);

				i = i - 1;
			}
		}

		var selEl = document.getElementById(selId);

		if (selEl.previousSibling) {
			containerEl.insertBefore(selEl, selEl.previousSibling);
			indexesEl.value = indexes.join();
		}
	}

	function <portlet:namespace />remove(containerId, indexesId, selId, selIndex) {
		var indexesEl = document.getElementById(indexesId);
		var indexes = (indexesEl.value != "") ? indexesEl.value.split(",") : [];

		for (var i = 0; i < indexes.length; i++) {
			if (indexes[i] == selIndex) {
				indexes.splice(i, 1);

				break;
			}
		}

		indexesEl.value = indexes.join();

		var containerEl = document.getElementById(containerId);
		var selEl = document.getElementById(selId);

		containerEl.removeChild(selEl);
	}

	function <portlet:namespace />removeKBStructureField(kbStructureFieldIndex) {
		var containerId = "<portlet:namespace />kbStructureFields";
		var indexesId = "<portlet:namespace />kbStructureFieldsIndexes";
		var selId = "<portlet:namespace />kbStructureField" + kbStructureFieldIndex;

		<portlet:namespace />remove(containerId, indexesId, selId, kbStructureFieldIndex);

		var indexesEl = document.getElementById(indexesId);
		var indexes = (indexesEl.value != "") ? indexesEl.value.split(",") : [];

		if (indexes.length == 0) {
			<portlet:namespace />addKBStructureField()
		}
	}

	function <portlet:namespace />removeKBStructureOption(kbStructureFieldIndex, kbStructureOptionIndex) {
		var containerId = "<portlet:namespace />kbStructureOptions" + kbStructureFieldIndex;
		var indexesId = "<portlet:namespace />kbStructureOptionsIndexes" + kbStructureFieldIndex;
		var selId = "<portlet:namespace />kbStructureOptions" + kbStructureFieldIndex + "kbStructureOption" + kbStructureOptionIndex;

		<portlet:namespace />remove(containerId, indexesId, selId, kbStructureOptionIndex);

		var indexesEl = document.getElementById(indexesId);
		var indexes = (indexesEl.value != "") ? indexesEl.value.split(",") : [];

		if (indexes.length == 0) {
			<portlet:namespace />addKBStructureOption(kbStructureFieldIndex);
		}
	}

	function <portlet:namespace />updateKBStructureFieldType(kbStructureFieldIndex, value) {
		var kbStructureOptionsEl = document.getElementById("<portlet:namespace />kbStructureOptions" + kbStructureFieldIndex);

		if ((value == "<%= KBStructureFieldConstants.TYPE_SELECT %>") || (value == "<%= KBStructureFieldConstants.TYPE_SELECT_MULTIPLE %>")) {
			var kbStructureOptionEl = kbStructureOptionsEl.lastChild;

			if (!kbStructureOptionEl.hasChildNodes()) {
				<portlet:namespace />addKBStructureOption(kbStructureFieldIndex);
			}
		}
		else {
			var kbStructureOptionsIndexesEl = document.getElementById("<portlet:namespace />kbStructureOptionsIndexes" + kbStructureFieldIndex);
			var kbStructureOptionsIndexes = (kbStructureOptionsIndexesEl.value != "") ? kbStructureOptionsIndexesEl.value.split(",") : [];

			for (var i = 0; i < kbStructureOptionsIndexes.length; i++) {
				<portlet:namespace />removeKBStructureOption(kbStructureFieldIndex, kbStructureOptionsIndexes[i]);
			}
		}
	}
</aui:script>