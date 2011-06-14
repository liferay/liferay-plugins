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

DDMTemplate ddmTemplate = null;

try {
	if (recordSetId > 0) {
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
							ddmTemplate = DDMTemplateLocalServiceUtil.getTemplate(detailDDMTemplateId);

							ddmStructure.setXsd(ddmTemplate.getScript());
						}
						catch (NoSuchTemplateException nste) {
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

boolean showAddTemplateIcon = (recordSet != null) && permissionChecker.hasPermission(scopeGroupId, "com.liferay.portlet.dynamicdatamapping", scopeGroupId, ActionKeys.ADD_TEMPLATE);
boolean showEditTemplateIcon = (ddmTemplate != null) && (permissionChecker.hasOwnerPermission(ddmTemplate.getCompanyId(), DDMTemplate.class.getName(), ddmTemplate.getTemplateId(), ddmTemplate.getUserId(), ActionKeys.UPDATE) || permissionChecker.hasPermission(ddmTemplate.getGroupId(), DDMTemplate.class.getName(), ddmTemplate.getTemplateId(), ActionKeys.UPDATE));
boolean showSelectArticleIcon = PortletPermissionUtil.contains(permissionChecker, plid, portletDisplay.getId(), ActionKeys.CONFIGURATION);
boolean showAddListIcon = PortletPermissionUtil.contains(permissionChecker, plid, portletDisplay.getId(), ActionKeys.CONFIGURATION) && permissionChecker.hasPermission(scopeGroupId, "com.liferay.portlet.dynamicdatalists", scopeGroupId, ActionKeys.ADD_RECORD_SET);
boolean showIconsActions = themeDisplay.isSignedIn() && (showEditTemplateIcon || showSelectArticleIcon || showAddListIcon);
%>

<c:if test="<%= showIconsActions %>">
	<div class="lfr-meta-actions icons-container">
		<div class="icon-actions">
			<c:if test="<%= showAddTemplateIcon %>">
				<liferay-portlet:renderURL windowState="<%= WindowState.MAXIMIZED.toString() %>" var="addTemplateURL" portletName="<%= PortletKeys.DYNAMIC_DATA_MAPPING %>">
					<portlet:param name="struts_action" value="/dynamic_data_mapping/edit_template" />
					<portlet:param name="portletResource" value="<%= portletDisplay.getId() %>" />
					<portlet:param name="redirect" value="<%= currentURL %>" />
					<portlet:param name="groupId" value="<%= String.valueOf(scopeGroupId) %>" />
					<portlet:param name="portletResourceNamespace" value="<%= renderResponse.getNamespace() %>" />
					<portlet:param name="structureId" value="<%= String.valueOf(recordSet.getDDMStructureId()) %>" />
					<portlet:param name="structureAvailableFields" value='<%= renderResponse.getNamespace() + "structureAvailableFields" %>' />
				</liferay-portlet:renderURL>

				<liferay-ui:icon
					image="add_template"
					message="add-template"
					url="<%= addTemplateURL %>"
				/>
			</c:if>
			<c:if test="<%= showEditTemplateIcon %>">
				<liferay-portlet:renderURL windowState="<%= WindowState.MAXIMIZED.toString() %>" var="editTemplateURL" portletName="<%= PortletKeys.DYNAMIC_DATA_MAPPING %>">
					<portlet:param name="struts_action" value="/dynamic_data_mapping/edit_template" />
					<portlet:param name="redirect" value="<%= currentURL %>" />
					<portlet:param name="groupId" value="<%= String.valueOf(ddmTemplate.getGroupId()) %>" />
					<portlet:param name="portletResourceNamespace" value="<%= renderResponse.getNamespace() %>" />
					<portlet:param name="structureId" value="<%= String.valueOf(ddmTemplate.getStructureId()) %>" />
					<portlet:param name="templateId" value="<%= String.valueOf(ddmTemplate.getTemplateId()) %>" />
					<portlet:param name="structureAvailableFields" value='<%= renderResponse.getNamespace() + "structureAvailableFields" %>' />
				</liferay-portlet:renderURL>

				<liferay-ui:icon
					image="../file_system/small/xml"
					message="edit-template"
					url="<%= editTemplateURL %>"
				/>
			</c:if>

			<c:if test="<%= showSelectArticleIcon %>">
				<liferay-ui:icon
					cssClass="portlet-configuration"
					image="configuration"
					message="select-list"
					method="get"
					url="<%= portletDisplay.getURLConfiguration() %>"
				/>
			</c:if>

			<c:if test="<%= showAddListIcon %>">
				<liferay-portlet:renderURL windowState="<%= WindowState.MAXIMIZED.toString() %>" var="addListURL" portletName="<%= PortletKeys.DYNAMIC_DATA_LISTS %>">
					<portlet:param name="struts_action" value="/dynamic_data_lists/edit_record_set" />
					<portlet:param name="portletResource" value="<%= portletDisplay.getId() %>" />
					<portlet:param name="redirect" value="<%= currentURL %>" />
				</liferay-portlet:renderURL>

				<liferay-ui:icon
					image="add_article"
					message="add-list"
					url="<%= addListURL %>"
				/>
			</c:if>
		</div>
	</div>
</c:if>