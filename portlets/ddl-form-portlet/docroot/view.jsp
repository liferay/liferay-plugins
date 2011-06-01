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
String redirect = ParamUtil.getString(request, "redirect");

DDLRecordSet recordSet = null;

try {
	if (Validator.isNotNull(recordSetId)) {
		recordSet = DDLRecordSetLocalServiceUtil.getRecordSet(recordSetId);
	}
%>

	<c:choose>
		<c:when test="<%= (recordSet != null) %>">
			<portlet:actionURL var="saveDataURL">
				<portlet:param name="<%= ActionRequest.ACTION_NAME %>" value="saveData" />
			</portlet:actionURL>

			<aui:form action="<%= saveDataURL %>" cssClass="lfr-dynamic-form" method="post" name="fm">
				<aui:input name="redirect" type="hidden" value="<%= redirect %>" />
				<aui:input name="recordSetId" type="hidden" value="<%= recordSet.getRecordSetId() %>" />
				<aui:input name="workflowAction" type="hidden" value="<%= WorkflowConstants.ACTION_PUBLISH %>" />

				<liferay-ui:error exception="<%= StorageFieldRequiredException.class %>" message="please-fill-out-all-required-fields" />

				<aui:fieldset>

					<%
					DDMStructure ddmStructure = recordSet.getDDMStructure();

					if (detailDDMTemplateId > 0) {
						try {
							DDMTemplate ddmTemplate = DDMTemplateLocalServiceUtil.getTemplate(detailDDMTemplateId);

							ddmStructure.setXsd(ddmTemplate.getScript());
						}
						catch(NoSuchTemplateException nste) {
						}
					}
					%>

					<%= DDMXSDUtil.getHTML(pageContext, ddmStructure.getXsd()) %>

					<aui:button-row>
						<aui:button onClick='<%= renderResponse.getNamespace() + "publishRecord();" %>' type="submit" value="send" />
					</aui:button-row>
				</aui:fieldset>
			</aui:form>
		</c:when>
		<c:otherwise>

			<%
			renderRequest.setAttribute(WebKeys.PORTLET_CONFIGURATOR_VISIBILITY, Boolean.TRUE);
			%>

			<br />

			<div class="portlet-msg-info">
				<liferay-ui:message key="select-an-existing-list-or-add-a-list-to-be-displayed-in-this-portlet" />
			</div>
		</c:otherwise>
	</c:choose>

<%
}
catch (NoSuchRecordSetException nsrse) {
%>

	<div class="portlet-msg-error">
		<%= LanguageUtil.get(pageContext, "the-selected-list-no-longer-exists") %>
	</div>

<%
}
%>