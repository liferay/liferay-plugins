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

<%@ include file="/init.jsp" %>

<%
int cur = ParamUtil.getInteger(request, SearchContainer.DEFAULT_CUR_PARAM);

String redirect = ParamUtil.getString(renderRequest, "redirect");

String keywords = ParamUtil.getString(request, "keywords");

DDLRecordSet selRecordSet = null;

try {
	if (Validator.isNotNull(recordSetId)) {
		selRecordSet = DDLRecordSetLocalServiceUtil.getRecordSet(recordSetId);
	}
}
catch (NoSuchRecordSetException nsrse) {
}
%>

<liferay-portlet:actionURL portletConfiguration="true" var="configurationURL" />
<liferay-portlet:renderURL portletConfiguration="true" varImpl="portletURL" />

<aui:form action="<%= configurationURL %>" method="post" name="fm1">
	<aui:input name="redirect" type="hidden" value="<%= redirect %>" />

	<div class="portlet-msg-info">
		<span class="displaying-help-message-holder <%= selRecordSet == null ? StringPool.BLANK : "aui-helper-hidden" %>">
			<liferay-ui:message key="please-select-a-list-entry-from-the-list-below" />
		</span>

		<span class="displaying-record-set-id-holder <%= selRecordSet == null ? "aui-helper-hidden" : StringPool.BLANK %>">
			<liferay-ui:message key="displaying-list" />: <span class="displaying-record-set-id"><%= selRecordSet != null ? HtmlUtil.escape(selRecordSet.getName(locale)) : StringPool.BLANK %></span>
		</span>
	</div>

	<c:if test="<%= selRecordSet != null %>">
		<aui:fieldset label="templates">
			<aui:select helpMessage="select-the-detail-template-used-to-add-records-to-the-list" label="detail-template" name="detailTemplateId" onChange='<%= "document." + renderResponse.getNamespace() + "fm." + renderResponse.getNamespace() + "detailDDMTemplateId.value = this.value;" %>'>
				<aui:option label="default" value="<%= 0 %>" />

				<%
				long ddmStructureId = selRecordSet.getDDMStructureId();

				List<DDMTemplate> templates = DDMTemplateLocalServiceUtil.getTemplates(ddmStructureId, DDMTemplateConstants.TEMPLATE_TYPE_DETAIL);

				for (DDMTemplate template : templates) {
					boolean selected = false;

					if (detailDDMTemplateId == template.getTemplateId()) {
						selected = true;
					}
				%>

					<aui:option label="<%= template.getName() %>" selected="<%= selected %>" value="<%= template.getTemplateId() %>" />

				<%
				}
				%>

			</aui:select>
		</aui:fieldset>
	</c:if>

	<aui:fieldset label="lists">
		<br />

		<liferay-ui:search-container
			emptyResultsMessage="no-lists-were-found"
			iteratorURL="<%= portletURL %>"
		>
			<div>
				<aui:input inlineField="<%= true %>" id="keywords" label="" name="keywords" size="30" title="search-lists" type="text" />

				<aui:button type="submit" value="search" />
			</div>

			<br />

			<liferay-ui:search-container-results
				results="<%= DDLRecordSetLocalServiceUtil.search(company.getCompanyId(), scopeGroupId, keywords, searchContainer.getStart(), searchContainer.getEnd(), searchContainer.getOrderByComparator()) %>"
				total="<%= DDLRecordSetLocalServiceUtil.searchCount(company.getCompanyId(), scopeGroupId, keywords) %>"
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
					buffer.append(StringUtil.shorten(recordSet.getDescription(), 100));
					%>

				</liferay-ui:search-container-column-text>

				<liferay-ui:search-container-column-text
					buffer="buffer"
					href="<%= rowURL %>"
					name="modified-date"
					orderable="<%= false %>"
				>

					<%
					buffer.append(dateFormatDateTime.format(recordSet.getModifiedDate()));
					%>

				</liferay-ui:search-container-column-text>
			</liferay-ui:search-container-row>

			<liferay-ui:search-iterator />
		</liferay-ui:search-container>
	</aui:fieldset>
</aui:form>

<br />

<aui:form action="<%= configurationURL %>" method="post" name="fm">
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />
	<aui:input name="redirect" type="hidden" value='<%= portletURL.toString() + StringPool.AMPERSAND + renderResponse.getNamespace() + "cur" + cur %>' />
	<aui:input name="preferences--recordSetId--" type="hidden" value="<%= recordSetId %>" />
	<aui:input name="preferences--detailDDMTemplateId--" type="hidden" value="<%= detailDDMTemplateId %>" />

	<aui:fieldset cssClass="aui-helper-hidden">
		<aui:field-wrapper label="portlet-id">
			<%= portletResource %>
		</aui:field-wrapper>
	</aui:fieldset>

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
			document.<portlet:namespace />fm.<portlet:namespace />detailDDMTemplateId.value = "";

			A.one('.displaying-record-set-id-holder').show();
			A.one('.displaying-help-message-holder').hide();

			var displayRecordSetId = A.one('.displaying-record-set-id');

			displayRecordSetId.set('innerHTML', recordSetName + ' (<%= LanguageUtil.get(pageContext, "modified") %>)');
			displayRecordSetId.addClass('modified');
		},
		['aui-base']
	);
</aui:script>