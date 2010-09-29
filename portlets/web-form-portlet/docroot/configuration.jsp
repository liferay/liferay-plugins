<%--
/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
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
String redirect = ParamUtil.getString(renderRequest, "redirect");

String titleXml = LocalizationUtil.getLocalizationXmlFromPreferences(preferences, renderRequest, "title");
String descriptionXml = LocalizationUtil.getLocalizationXmlFromPreferences(preferences, renderRequest, "description");
boolean requireCaptcha = PrefsParamUtil.getBoolean(preferences, renderRequest, "requireCaptcha");
String successURL = PrefsParamUtil.getString(preferences, renderRequest, "successURL");

boolean sendAsEmail = PrefsParamUtil.getBoolean(preferences, renderRequest, "sendAsEmail");
String subject = PrefsParamUtil.getString(preferences, renderRequest, "subject");
String emailAddress = PrefsParamUtil.getString(preferences, renderRequest, "emailAddress");

boolean saveToDatabase = PrefsParamUtil.getBoolean(preferences, renderRequest, "saveToDatabase");
String databaseTableName = preferences.getValue("databaseTableName", StringPool.BLANK);

boolean saveToFile = PrefsParamUtil.getBoolean(preferences, renderRequest, "saveToFile");
String fileName = PrefsParamUtil.getString(preferences, renderRequest, "fileName");

boolean fieldsEditingDisabled = false;

if (WebFormUtil.getTableRowsCount(company.getCompanyId(), databaseTableName) > 0) {
	fieldsEditingDisabled = true;
}
%>

<liferay-portlet:actionURL portletConfiguration="true" var="configurationURL" />

<aui:form action="<%= configurationURL %>" method="post" name="fm">
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />
	<aui:input name="redirect" type="hidden" value="<%= redirect %>" />

	<liferay-ui:panel-container id="webFormConfiguration" extended="<%= Boolean.TRUE %>" persistState="<%= true %>">
		<liferay-ui:panel id="webFormGeneral" title='<%= LanguageUtil.get(pageContext, "form-information") %>' collapsible="<%= true %>" persistState="<%= true %>" extended="<%= true %>">
			<aui:fieldset>
				<liferay-ui:error key="titleRequired" message="please-enter-a-title" />

				<aui:field-wrapper cssClass="lfr-input-text-container" label="title">
					<liferay-ui:input-localized  name="title" xml="<%= titleXml %>" />
				</aui:field-wrapper>

				<aui:field-wrapper cssClass="lfr-textarea-container" label="description">
					<liferay-ui:input-localized name="description" type="textarea" xml="<%= descriptionXml %>" />
				</aui:field-wrapper>

				<aui:input name="requireCaptcha" type="checkbox" value="<%= requireCaptcha %>" />

				<aui:input cssClass="lfr-input-text-container" label="redirect-url-on-success" name="successURL" value="<%= HtmlUtil.toInputSafe(successURL) %>" />
			</aui:fieldset>
		</liferay-ui:panel>

		<liferay-ui:panel id='webFormData' title='<%= LanguageUtil.get(pageContext, "handling-of-form-data") %>' collapsible="<%= true %>" persistState="<%= true %>" extended="<%= true %>">
			<aui:fieldset cssClass="handle-data" label="email">
				<liferay-ui:error key="subjectRequired" message="please-enter-a-subject" />
				<liferay-ui:error key="handlingRequired" message="please-select-an-action-for-the-handling-of-form-data" />
				<liferay-ui:error key="emailAddressInvalid" message="please-enter-a-valid-email-address" />
				<liferay-ui:error key="emailAddressRequired" message="please-enter-an-email-address" />
				<liferay-ui:error key="fileNameInvalid" message="please-enter-a-valid-path-and-filename" />

				<aui:input inlineLabel="left" label="send-as-email" name="sendAsEmail" type="checkbox" value="<%= sendAsEmail %>" />

				<aui:input cssClass="lfr-input-text-container" name="subject" value="<%= subject %>" />

				<aui:input cssClass="lfr-input-text-container" name="emailAddress" value="<%= emailAddress %>" />
			</aui:fieldset>

			<aui:fieldset cssClass="handle-data" label="database">
				<aui:input name="saveToDatabase" type="checkbox" value="<%= saveToDatabase %>" />
			</aui:fieldset>

			<aui:fieldset cssClass="handle-data" label="file">
				<aui:input name="saveToFile" type="checkbox" value="<%= saveToFile %>" />

				<aui:input cssClass="lfr-input-text-container" label="path-and-file-name" name="filename" value="<%= fileName %>" />
			</aui:fieldset>
		</liferay-ui:panel>

		<liferay-ui:panel id='webFormFields' title='<%= LanguageUtil.get(pageContext, "form-fields") %>' collapsible="<%= true %>" persistState="<%= true %>" extended="<%= true %>">
			<aui:fieldset cssClass="rows-container webFields">
				<c:if test="<%= fieldsEditingDisabled %>">
					<div class="portlet-msg-alert">
						<liferay-ui:message key="there-is-existing-form-data-please-export-and-delete-it-before-making-changes-to-the-fields" />
					</div>

					<liferay-portlet:resourceURL var="exportURL" portletName="<%= portletResource %>">
						<portlet:param name="<%= Constants.CMD %>" value="export" />
					</liferay-portlet:resourceURL>

					<%
					String taglibExport = "submitForm(document.hrefFm, '" + exportURL + "');";
					%>

					<aui:button onclick="<%= taglibExport %>" value="export-data" />

					<liferay-portlet:actionURL var="deleteURL" portletName="<%= portletResource %>">
						<portlet:param name="<%= ActionRequest.ACTION_NAME %>" value="deleteData" />
						<portlet:param name="redirect" value="<%= currentURL %>" />
					</liferay-portlet:actionURL>

					<%
					String taglibDelete = "submitForm(document." + renderResponse.getNamespace() + "fm, '" + deleteURL + "');";
					%>

					<aui:button onclick="<%= taglibDelete %>" value="delete-data" />

					<br /><br />
				</c:if>

				<aui:input name="updateFields" type="hidden" value="<%= !fieldsEditingDisabled %>" />

				<%
				String formFieldsIndexesParam = ParamUtil.getString(renderRequest, "formFieldsIndexes") ;

				int[] formFieldsIndexes = null;

				if (Validator.isNotNull(formFieldsIndexesParam)) {
					formFieldsIndexes = StringUtil.split(formFieldsIndexesParam, 0);
				}
				else {
					formFieldsIndexes = new int[0];

					for (int i = 1; true; i++) {
						String fieldLabel = PrefsParamUtil.getString(preferences, request, "fieldLabel" + i);

						if (Validator.isNull(fieldLabel)) {
							break;
						}

						formFieldsIndexes = ArrayUtil.append(formFieldsIndexes, i);
					}

					if (formFieldsIndexes.length == 0) {
						formFieldsIndexes = ArrayUtil.append(formFieldsIndexes, -1);
					}
				}

				int index = 1;

				for (int formFieldsIndex : formFieldsIndexes) {
					request.setAttribute("configuration.jsp-index", String.valueOf(index));
					request.setAttribute("configuration.jsp-formFieldsindex", String.valueOf(formFieldsIndex));
					request.setAttribute("configuration.jsp-fieldsEditingDisabled", String.valueOf(fieldsEditingDisabled));
				%>

					<div class="lfr-form-row" id="<portlet:namespace/>fieldset<%= formFieldsIndex %>">
						<div class="row-fields">
							<liferay-util:include page="/edit_field.jsp" servletContext="<%= application %>"/>
						</div>
					</div>

				<%
					index++;
				}
				%>

			</aui:fieldset>
		</liferay-ui:panel>
	</liferay-ui:panel-container>

	<aui:button-row>
		<aui:button type="submit" />
	</aui:button-row>
</aui:form>

<%
String modules = "aui-base";

if (!fieldsEditingDisabled) {
	modules += ",liferay-auto-fields";
}
%>

<aui:script use="<%= modules %>">
	var toggleOptions = function(event) {
		var select = this;

		var formRow = select.ancestor('.lfr-form-row');
		var value = select.val();

		var optionsDiv = formRow.one('.options');

		if (value == 'options' || value == 'radio') {
			optionsDiv.all('label').show();
			optionsDiv.show();
		}
		else if (value == 'paragraph') {

			// Show just the text field and not the labels since there
			// are multiple choice inputs

			optionsDiv.all('label').hide();
			optionsDiv.show();
		}
		else {
			optionsDiv.hide();
		}

		var optionalControl = formRow.one('.optional-control');
		var labelName = formRow.one('.label-name');

		if (value == 'paragraph') {
			var inputName = labelName.one('input');

			inputName.val('<liferay-ui:message key="paragraph" />');
			inputName.fire('change');

			labelName.hide();
			optionalControl.hide();

			optionalControl.all('input[type="checkbox"]').attr('checked', 'true');
			optionalControl.all('input[type="hidden"]').attr('value', 'true');
		}
		else {
			optionalControl.show();
			labelName.show();
		}
	};

	var toggleValidationOptions = function(event) {
		this.next().toggle();
	};

	var webFields = A.one('.webFields');

	webFields.all('select').each(toggleOptions);

	<c:if test="<%= !fieldsEditingDisabled %>">
		A.delegate('change', toggleOptions, webFields, 'select');
		A.delegate('click', toggleValidationOptions, webFields, '.validation-link');

		A.delegate(
			'change',
			function(event) {
				var input = event.currentTarget;
				var row = input.ancestor('.field-row');
				var label = row.one('.field-title');

				if (label) {
					label.html(input.get('value'));
				}
			},
			webFields,
			'.label-name input'
		);

		<liferay-portlet:renderURL portletConfiguration="true" var="editFieldURL" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
			<portlet:param name="<%= Constants.CMD %>" value="<%= Constants.ADD %>" />
		</liferay-portlet:renderURL>

		new Liferay.AutoFields(
			{
				contentBox: webFields,
				fieldIndexes: '<portlet:namespace />formFieldsIndexes',
				sortable: true,
				sortableHandle: '.field-label',
				url: '<%= editFieldURL %>'
			}
		).render();
	</c:if>
</aui:script>