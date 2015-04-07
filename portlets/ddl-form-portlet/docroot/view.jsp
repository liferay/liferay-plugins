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
String redirect = ParamUtil.getString(request, "redirect", currentURL);

DDLRecordSet recordSet = null;

DDMTemplate ddmTemplate = null;

try {
	if (recordSetId > 0) {
		recordSet = DDLRecordSetLocalServiceUtil.getRecordSet(recordSetId);

		if (recordSet.getGroupId() != scopeGroupId) {
			recordSet = null;
		}
	}

	if (formDDMTemplateId > 0) {
		ddmTemplate = DDMTemplateLocalServiceUtil.getTemplate(formDDMTemplateId);
	}
%>

	<c:choose>
		<c:when test="<%= (recordSet != null) %>">
			<portlet:actionURL var="saveDataURL">
				<portlet:param name="<%= ActionRequest.ACTION_NAME %>" value="saveData" />
			</portlet:actionURL>

			<aui:form action="<%= saveDataURL %>" cssClass="lfr-dynamic-form" enctype="multipart/form-data" method="post" name="fm">
				<aui:input name="redirect" type="hidden" value="<%= redirect %>" />
				<aui:input name="recordSetId" type="hidden" value="<%= recordSet.getRecordSetId() %>" />
				<aui:input name="defaultLanguageId" type="hidden" value="<%= themeDisplay.getLanguageId() %>" />
				<aui:input name="languageId" type="hidden" value="<%= themeDisplay.getLanguageId() %>" />
				<aui:input name="multipleSubmissions" type="hidden" value="<%= multipleSubmissions %>" />
				<aui:input name="workflowAction" type="hidden" value="<%= WorkflowConstants.ACTION_PUBLISH %>" />

				<liferay-ui:error exception="<%= DuplicateSubmissionException.class %>" message="you-may-only-submit-the-form-once" />

				<liferay-ui:error exception="<%= FileSizeException.class %>">
					<liferay-ui:message arguments="<%= PrefsPropsUtil.getLong(PropsKeys.DL_FILE_MAX_SIZE) / 1024 %>" key="please-enter-a-file-with-a-valid-file-size-no-larger-than-x" translateArguments="<%= false %>" />
				</liferay-ui:error>

				<liferay-ui:error exception="<%= StorageFieldRequiredException.class %>" message="please-fill-out-all-required-fields" />

				<c:choose>
					<c:when test="<%= (themeDisplay.isSignedIn() || multipleSubmissions) && permissionChecker.hasPermission(scopeGroupId, DDLRecordSet.class.getName(), recordSetId, ActionKeys.VIEW) %>">
						<c:choose>
							<c:when test="<%= !permissionChecker.hasPermission(scopeGroupId, DDLRecordSet.class.getName(), recordSetId, ActionKeys.ADD_RECORD) %>">
								<div class="alert alert-info">
									<liferay-ui:message key="you-do-not-have-the-required-permissions" />
								</div>
							</c:when>
							<c:when test="<%= multipleSubmissions || !(DDLFormUtil.hasSubmitted(request, recordSet.getRecordSetId())) %>">
								<aui:fieldset>
									<aui:translation-manager
										availableLocales="<%= new Locale[] {LocaleUtil.fromLanguageId(themeDisplay.getLanguageId())} %>"
										defaultLanguageId="<%= themeDisplay.getLanguageId() %>"
										id="translationManager"
									/>

									<%
									long classNameId = PortalUtil.getClassNameId(DDMStructure.class);

									long classPK = recordSet.getDDMStructureId();

									if (formDDMTemplateId > 0) {
										classNameId = PortalUtil.getClassNameId(DDMTemplate.class);

										classPK = formDDMTemplateId;
									}
									%>

									<liferay-ddm:html
										classNameId="<%= classNameId %>"
										classPK="<%= classPK %>"
										requestedLocale="<%= locale %>"
									/>

									<aui:button-row>
										<aui:button type="submit" value="send" />
									</aui:button-row>
								</aui:fieldset>
							</c:when>
							<c:otherwise>
								<div class="alert alert-info">
									<liferay-ui:message key="your-form-has-already-been-submitted" />
								</div>
							</c:otherwise>
						</c:choose>
					</c:when>
					<c:otherwise>
						<div class="alert alert-danger">
							<liferay-ui:message key="you-do-not-have-the-required-permissions" />
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

			<div class="alert alert-info">
				<liferay-ui:message key="select-an-existing-list-or-add-a-list-to-be-displayed-in-this-portlet" />
			</div>
		</c:otherwise>
	</c:choose>

<%
}
catch (NoSuchRecordSetException nsrse) {
%>

	<div class="alert alert-danger">
		<%= LanguageUtil.get(request, "the-selected-list-no-longer-exists") %>
	</div>

<%
}

boolean hasConfigurationPermission = PortletPermissionUtil.contains(permissionChecker, layout, portletDisplay.getId(), ActionKeys.CONFIGURATION);

boolean showAddListIcon = hasConfigurationPermission && permissionChecker.hasPermission(scopeGroupId, ddmPermissionHandler.getResourceName(scopeClassNameId), scopeGroupId, ActionKeys.ADD_RECORD_SET);
boolean showAddTemplateIcon = (recordSet != null) && permissionChecker.hasPermission(scopeGroupId, ddmPermissionHandler.getResourceName(scopeClassNameId), scopeGroupId, ActionKeys.ADD_TEMPLATE);
boolean showEditTemplateIcon = (ddmTemplate != null) && (permissionChecker.hasOwnerPermission(ddmTemplate.getCompanyId(), DDMTemplate.class.getName(), ddmTemplate.getTemplateId(), ddmTemplate.getUserId(), ActionKeys.UPDATE) || permissionChecker.hasPermission(ddmTemplate.getGroupId(), DDMTemplate.class.getName(), ddmTemplate.getTemplateId(), ActionKeys.UPDATE));
%>

<c:if test="<%= themeDisplay.isSignedIn() && (hasConfigurationPermission || showEditTemplateIcon || showAddListIcon) %>">
	<div class="icons-container lfr-meta-actions">
		<div class="lfr-icon-actions">
			<c:if test="<%= showAddTemplateIcon %>">
				<liferay-portlet:renderURL portletName="<%= PortletKeys.DYNAMIC_DATA_MAPPING %>" var="addTemplateURL" windowState="<%= WindowState.MAXIMIZED.toString() %>">
					<portlet:param name="struts_action" value="/dynamic_data_mapping/edit_template" />
					<portlet:param name="redirect" value="<%= currentURL %>" />
					<portlet:param name="portletResource" value="<%= portletDisplay.getId() %>" />
					<portlet:param name="portletResourceNamespace" value="<%= renderResponse.getNamespace() %>" />
					<portlet:param name="refererPortletName" value="<%= PortletKeys.DYNAMIC_DATA_LISTS %>" />
					<portlet:param name="groupId" value="<%= String.valueOf(scopeGroupId) %>" />
					<portlet:param name="classNameId" value="<%= String.valueOf(PortalUtil.getClassNameId(DDMStructure.class)) %>" />
					<portlet:param name="classPK" value="<%= String.valueOf(recordSet.getDDMStructureId()) %>" />
					<portlet:param name="structureAvailableFields" value='<%= renderResponse.getNamespace() + "getAvailableFields" %>' />
				</liferay-portlet:renderURL>

				<liferay-ui:icon
					cssClass="lfr-icon-action lfr-icon-action-add"
					iconCssClass="icon-plus"
					label="<%= true %>"
					message="add-form"
					url="<%= addTemplateURL %>"
				/>
			</c:if>
			<c:if test="<%= showEditTemplateIcon %>">
				<liferay-portlet:renderURL portletName="<%= PortletKeys.DYNAMIC_DATA_MAPPING %>" var="editTemplateURL" windowState="<%= WindowState.MAXIMIZED.toString() %>">
					<portlet:param name="struts_action" value="/dynamic_data_mapping/edit_template" />
					<portlet:param name="redirect" value="<%= currentURL %>" />
					<portlet:param name="portletResource" value="<%= portletDisplay.getId() %>" />
					<portlet:param name="portletResourceNamespace" value="<%= renderResponse.getNamespace() %>" />
					<portlet:param name="refererPortletName" value="<%= PortletKeys.DYNAMIC_DATA_LISTS %>" />
					<portlet:param name="templateId" value="<%= String.valueOf(formDDMTemplateId) %>" />
					<portlet:param name="structureAvailableFields" value='<%= renderResponse.getNamespace() + "getAvailableFields" %>' />
				</liferay-portlet:renderURL>

				<liferay-ui:icon
					cssClass="lfr-icon-action lfr-icon-action-edit-template"
					iconCssClass="icon-edit"
					label="<%= true %>"
					message="edit-form"
					url="<%= editTemplateURL %>"
				/>
			</c:if>

			<c:if test="<%= hasConfigurationPermission %>">
				<liferay-ui:icon
					cssClass="lfr-icon-action lfr-icon-action-configuration"
					iconCssClass="icon-cog"
					label="<%= true %>"
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
					<portlet:param name="groupId" value="<%= String.valueOf(scopeGroupId) %>" />
				</liferay-portlet:renderURL>

				<liferay-ui:icon
					cssClass="lfr-icon-action lfr-icon-action-add"
					iconCssClass="icon-plus"
					label="<%= true %>"
					message="add-list"
					url="<%= addListURL %>"
				/>
			</c:if>
		</div>
	</div>
</c:if>