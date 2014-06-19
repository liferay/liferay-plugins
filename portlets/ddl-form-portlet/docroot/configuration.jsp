<%--
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
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

<%@ include file="/init.jsp" %>

<%
int cur = ParamUtil.getInteger(request, SearchContainer.DEFAULT_CUR_PARAM);

String keywords = ParamUtil.getString(request, "keywords");

DDLRecordSet selRecordSet = null;

try {
	if (recordSetId > 0) {
		selRecordSet = DDLRecordSetLocalServiceUtil.getRecordSet(recordSetId);

		if (selRecordSet.getGroupId() != scopeGroupId) {
			selRecordSet = null;
		}
	}
}
catch (NoSuchRecordSetException nsrse) {
}
%>

<liferay-portlet:actionURL portletConfiguration="true" var="configurationActionURL" />

<liferay-portlet:renderURL portletConfiguration="true" varImpl="configurationRenderURL" />

<aui:form action="<%= configurationActionURL %>" method="post" name="fm1">
	<aui:input name="redirect" type="hidden" value="<%= configurationRenderURL.toString() %>" />

	<div class="alert alert-info">
		<span class="displaying-help-message-holder <%= (selRecordSet == null) ? StringPool.BLANK : "hide" %>">
			<liferay-ui:message key="please-select-a-list-entry-from-the-list-below" />
		</span>

		<span class="displaying-record-set-id-holder <%= (selRecordSet == null) ? "hide" : StringPool.BLANK %>">
			<liferay-ui:message key="displaying-list" />: <span class="displaying-record-set-id"><%= (selRecordSet != null) ? HtmlUtil.escape(selRecordSet.getName(locale)) : StringPool.BLANK %></span>
		</span>
	</div>

	<c:if test="<%= selRecordSet != null %>">
		<aui:fieldset label="templates">
			<aui:select helpMessage="select-the-form-template-used-to-add-records-to-the-list" label="form-template" name="formTemplateId" onChange='<%= "document." + renderResponse.getNamespace() + "fm." + renderResponse.getNamespace() + "formDDMTemplateId.value = this.value;" %>'>
				<aui:option label="default" value="<%= 0 %>" />

				<%
				long classNameId = PortalUtil.getClassNameId(DDMStructure.class);
				long classPK = selRecordSet.getDDMStructureId();

				List<DDMTemplate> ddmTemplates = DDMTemplateLocalServiceUtil.getTemplates(scopeGroupId, classNameId, classPK, DDMTemplateConstants.TEMPLATE_TYPE_FORM, DDMTemplateConstants.TEMPLATE_MODE_CREATE);

				for (DDMTemplate ddmTemplate : ddmTemplates) {
					boolean selected = false;

					if (formDDMTemplateId == ddmTemplate.getTemplateId()) {
						selected = true;
					}
				%>

					<aui:option label="<%= ddmTemplate.getName() %>" selected="<%= selected %>" value="<%= ddmTemplate.getTemplateId() %>" />

				<%
				}
				%>

			</aui:select>

			<aui:input helpMessage="check-to-allow-multiple-form-submissions-per-user" name="allow-multiple-submissions" onChange='<%= "document." + renderResponse.getNamespace() + "fm." + renderResponse.getNamespace() + "multipleSubmissions.value = this.checked;" %>' type="checkbox" value="<%= multipleSubmissions %>" />
		</aui:fieldset>
	</c:if>

	<aui:fieldset label="lists">
		<liferay-ui:search-container
			emptyResultsMessage="no-entries-were-found"
			iteratorURL="<%= configurationRenderURL %>"
			total="<%= DDLRecordSetLocalServiceUtil.searchCount(company.getCompanyId(), scopeGroupId, keywords, DDLRecordSetConstants.SCOPE_DYNAMIC_DATA_LISTS) %>"
		>
			<div class="form-search input-append">
				<liferay-ui:input-search autoFocus="<%= true %>" placeholder='<%= LanguageUtil.get(locale, "keywords") %>' title='<%= LanguageUtil.get(locale, "search-lists") %>' />
			</div>

			<liferay-ui:search-container-results
				results="<%= DDLRecordSetLocalServiceUtil.search(company.getCompanyId(), scopeGroupId, keywords, DDLRecordSetConstants.SCOPE_DYNAMIC_DATA_LISTS, searchContainer.getStart(), searchContainer.getEnd(), searchContainer.getOrderByComparator()) %>"
			/>

			<liferay-ui:search-container-row
				className="com.liferay.portlet.dynamicdatalists.model.DDLRecordSet"
				escapedModel="<%= true %>"
				keyProperty="recordSetId"
				modelVar="recordSet"
			>

				<%
				StringBundler sb = new StringBundler(7);

				sb.append("javascript:");
				sb.append(renderResponse.getNamespace());
				sb.append("selectRecordSet('");
				sb.append(recordSet.getRecordSetId());
				sb.append("','");
				sb.append(recordSet.getName(locale));
				sb.append("');");

				String rowURL = sb.toString();
				%>

				<liferay-ui:search-container-column-text
					href="<%= rowURL %>"
					name="id"
					orderable="<%= false %>"
					property="recordSetKey"
				/>

				<liferay-ui:search-container-column-text
					href="<%= rowURL %>"
					name="name"
					orderable="<%= false %>"
					property="name"
				/>

				<liferay-ui:search-container-column-text
					buffer="buffer"
					href="<%= rowURL %>"
					name="description"
					orderable="<%= false %>"
				>

					<%
					buffer.append(StringUtil.shorten(recordSet.getDescription(locale), 100));
					%>

				</liferay-ui:search-container-column-text>

				<liferay-ui:search-container-column-date
					href="<%= rowURL %>"
					name="modified-date"
					orderable="<%= false %>"
					value="<%= recordSet.getModifiedDate() %>"
				/>
			</liferay-ui:search-container-row>

			<div class="separator"><!-- --></div>

			<liferay-ui:search-iterator />
		</liferay-ui:search-container>
	</aui:fieldset>
</aui:form>

<aui:form action="<%= configurationActionURL %>" method="post" name="fm">
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />
	<aui:input name="redirect" type="hidden" value='<%= configurationRenderURL.toString() + StringPool.AMPERSAND + renderResponse.getNamespace() + "cur" + cur %>' />
	<aui:input name="preferences--recordSetId--" type="hidden" value="<%= recordSetId %>" />
	<aui:input name="preferences--formDDMTemplateId--" type="hidden" value="<%= formDDMTemplateId %>" />
	<aui:input name="preferences--multipleSubmissions--" type="hidden" value="<%= multipleSubmissions %>" />

	<aui:button-row>
		<aui:button type="submit" />
	</aui:button-row>
</aui:form>

<aui:script>
	Liferay.provide(
		window,
		'<portlet:namespace />selectRecordSet',
		function(recordSetId, recordSetName) {
			var A = AUI();

			document.<portlet:namespace />fm.<portlet:namespace />recordSetId.value = recordSetId;
			document.<portlet:namespace />fm.<portlet:namespace />formDDMTemplateId.value = '';

			A.one('.displaying-record-set-id-holder').show();
			A.one('.displaying-help-message-holder').hide();

			var displayRecordSetId = A.one('.displaying-record-set-id');

			displayRecordSetId.set('innerHTML', recordSetName + ' (<%= LanguageUtil.get(request, "modified") %>)');
			displayRecordSetId.addClass('modified');
		},
		['aui-base']
	);
</aui:script>