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
				<aui:input name="multipleSubmissions" type="hidden" value="<%= multipleSubmissions %>" />
				<aui:input name="workflowAction" type="hidden" value="<%= WorkflowConstants.ACTION_PUBLISH %>" />

				<liferay-ui:error exception="<%= DuplicateSubmissionException.class %>" message="you-may-only-submit-the-form-once" />
				<liferay-ui:error exception="<%= StorageFieldRequiredException.class %>" message="please-fill-out-all-required-fields" />

				<c:choose>
					<c:when test="<%= themeDisplay.isSignedIn() %>">
						<c:choose>
							<c:when test="<%= multipleSubmissions || !(DDLFormUtil.hasSubmitted(request, recordSet.getRecordSetId())) %>">
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

									<%= DDMXSDUtil.getHTML(pageContext, ddmStructure.getXsd(), locale) %>

									<aui:button-row>
										<aui:button onClick='<%= renderResponse.getNamespace() + "publishRecord();" %>' type="submit" value="send" />
									</aui:button-row>
								</aui:fieldset>
							</c:when>
							<c:otherwise>
								<div class="portlet-msg-info">
									<liferay-ui:message key="your-form-has-already-been-submitted" />
								</div>
							</c:otherwise>
						</c:choose>
					</c:when>
					<c:otherwise>
						<div class="portlet-msg-info">
							<liferay-ui:message key="you-must-be-authenticated-to-use-this-portlet" />
						</div>
					</c:otherwise>
				</c:choose>
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

boolean showAddListIcon = PortletPermissionUtil.contains(permissionChecker, plid, portletDisplay.getId(), ActionKeys.CONFIGURATION) && permissionChecker.hasPermission(scopeGroupId, "com.liferay.portlet.dynamicdatalists", scopeGroupId, ActionKeys.ADD_RECORD_SET);
boolean showAddTemplateIcon = (recordSet != null) && permissionChecker.hasPermission(scopeGroupId, "com.liferay.portlet.dynamicdatalists", scopeGroupId, ActionKeys.ADD_TEMPLATE);
boolean showEditTemplateIcon = (ddmTemplate != null) && (permissionChecker.hasOwnerPermission(ddmTemplate.getCompanyId(), DDMTemplate.class.getName(), ddmTemplate.getTemplateId(), ddmTemplate.getUserId(), ActionKeys.UPDATE) || permissionChecker.hasPermission(ddmTemplate.getGroupId(), DDMTemplate.class.getName(), ddmTemplate.getTemplateId(), ActionKeys.UPDATE));
boolean showSelectListIcon = PortletPermissionUtil.contains(permissionChecker, plid, portletDisplay.getId(), ActionKeys.CONFIGURATION);
%>

<c:if test="<%= themeDisplay.isSignedIn() && (showEditTemplateIcon || showSelectListIcon || showAddListIcon) %>">
	<div class="lfr-meta-actions icons-container">
		<div class="icon-actions">
			<c:if test="<%= showAddTemplateIcon %>">
				<liferay-portlet:renderURL portletName="<%= PortletKeys.DYNAMIC_DATA_MAPPING %>" var="addTemplateURL" windowState="<%= WindowState.MAXIMIZED.toString() %>">
					<portlet:param name="struts_action" value="/dynamic_data_mapping/edit_template" />
					<portlet:param name="portletResource" value="<%= portletDisplay.getId() %>" />
					<portlet:param name="redirect" value="<%= currentURL %>" />
					<portlet:param name="portletResourceNamespace" value="<%= renderResponse.getNamespace() %>" />
					<portlet:param name="groupId" value="<%= String.valueOf(scopeGroupId) %>" />
					<portlet:param name="structureId" value="<%= String.valueOf(recordSet.getDDMStructureId()) %>" />
					<portlet:param name="structureAvailableFields" value='<%= renderResponse.getNamespace() + "structureAvailableFields" %>' />
					<portlet:param name="ddmResource" value="<%= ddmResource %>" />
				</liferay-portlet:renderURL>

				<liferay-ui:icon
					image="add_template"
					message="add-form"
					url="<%= addTemplateURL %>"
				/>
			</c:if>
			<c:if test="<%= showEditTemplateIcon %>">
				<liferay-portlet:renderURL portletName="<%= PortletKeys.DYNAMIC_DATA_MAPPING %>" var="editTemplateURL" windowState="<%= WindowState.MAXIMIZED.toString() %>">
					<portlet:param name="struts_action" value="/dynamic_data_mapping/edit_template" />
					<portlet:param name="redirect" value="<%= currentURL %>" />
					<portlet:param name="portletResourceNamespace" value="<%= renderResponse.getNamespace() %>" />
					<portlet:param name="groupId" value="<%= String.valueOf(ddmTemplate.getGroupId()) %>" />
					<portlet:param name="templateId" value="<%= String.valueOf(ddmTemplate.getTemplateId()) %>" />
					<portlet:param name="structureId" value="<%= String.valueOf(ddmTemplate.getStructureId()) %>" />
					<portlet:param name="structureAvailableFields" value='<%= renderResponse.getNamespace() + "structureAvailableFields" %>' />
				</liferay-portlet:renderURL>

				<liferay-ui:icon
					image="../file_system/small/xml"
					message="edit-form"
					url="<%= editTemplateURL %>"
				/>
			</c:if>

			<c:if test="<%= showSelectListIcon %>">
				<liferay-ui:icon
					cssClass="portlet-configuration"
					image="configuration"
					message="select-list"
					method="get"
					onClick="<%= portletDisplay.getURLConfigurationJS() %>"
					url="<%= portletDisplay.getURLConfiguration() %>"
				/>
			</c:if>

			<c:if test="<%= showAddListIcon %>">
				<liferay-portlet:renderURL portletName="<%= PortletKeys.DYNAMIC_DATA_LISTS %>" var="addListURL" windowState="<%= WindowState.MAXIMIZED.toString() %>">
					<portlet:param name="struts_action" value="/dynamic_data_lists/edit_record_set" />
					<portlet:param name="redirect" value="<%= currentURL %>" />
					<portlet:param name="portletResource" value="<%= portletDisplay.getId() %>" />
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