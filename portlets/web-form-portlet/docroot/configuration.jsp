<%
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
%>

<%@ include file="/init.jsp" %>

<%
String redirect = ParamUtil.getString(request, "redirect");

String title = PrefsParamUtil.getString(preferences, request, "title");
String description = PrefsParamUtil.getString(preferences, request, "description");
boolean requireCaptcha = PrefsParamUtil.getBoolean(preferences, request, "requireCaptcha");
String successURL = PrefsParamUtil.getString(preferences, request, "successURL");

boolean sendAsEmail = PrefsParamUtil.getBoolean(preferences, request, "sendAsEmail");
String subject = PrefsParamUtil.getString(preferences, request, "subject");
String emailAddress = PrefsParamUtil.getString(preferences, request, "emailAddress");

boolean saveToDatabase = PrefsParamUtil.getBoolean(preferences, request, "saveToDatabase");
String databaseTableName = preferences.getValue("databaseTableName", StringPool.BLANK);

boolean saveToFile = PrefsParamUtil.getBoolean(preferences, request, "saveToFile");
String fileName = PrefsParamUtil.getString(preferences, request, "fileName");

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

				<aui:input cssClass="lfr-input-text-container" name="title" value="<%= HtmlUtil.toInputSafe(title) %>" />

				<aui:input name="description" type="textarea" value="<%= HtmlUtil.toInputSafe(description) %>" wrap="soft" />

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
				}

				for (int formFieldsIndex : formFieldsIndexes) {
					String fieldLabel = PrefsParamUtil.getString(preferences, request, "fieldLabel" + formFieldsIndex);
					String fieldType = PrefsParamUtil.getString(preferences, request, "fieldType" + formFieldsIndex);
					boolean fieldOptional = PrefsParamUtil.getBoolean(preferences, request, "fieldOptional" + formFieldsIndex);
					String fieldOptions = PrefsParamUtil.getString(preferences, request, "fieldOptions" + formFieldsIndex);
					String fieldValidationScript = StringPool.BLANK;
					String fieldValidationErrorMessage = StringPool.BLANK;

					if (WebFormUtil.VALIDATION_SCRIPT_ENABLED) {
						fieldValidationScript = PrefsParamUtil.getString(preferences, request, "fieldValidationScript" + formFieldsIndex);
						fieldValidationErrorMessage = PrefsParamUtil.getString(preferences, request, "fieldValidationErrorMessage" + formFieldsIndex);
					}
				%>

					<div class="lfr-form-row" id="<portlet:namespace/>fieldset<%= formFieldsIndex %>">
						<div class="field-title">
							<c:choose>
								<c:when test='<%= fieldType.equals("paragraph") %>'>
									<span class="field-label"><liferay-ui:message key="paragraph" /></span>
								</c:when>
								<c:when test="<%= Validator.isNotNull(fieldLabel) %>">
									<span class="field-label"><%= fieldLabel %></span>
								</c:when>
								<c:otherwise>
									<liferay-ui:message key="field" /> <%= formFieldsIndex %>
								</c:otherwise>
							</c:choose>
						</div>

						<c:choose>
							<c:when test="<%= !fieldsEditingDisabled %>">
								<aui:input cssClass="lfr-input-text-container label-name" label="name" name='<%= "fieldLabel" + formFieldsIndex %>' onchange="AUI().one(this).get('parentNode.parentNode.parentNode.parentNode').one('.field-label').html(AUI().one(this).val())" size="50" value="<%= fieldLabel %>" />
							</c:when>
							<c:otherwise>
								<dl class="editing-disabled">
									<dt>
										<liferay-ui:message key="name" />
									</dt>
									<dd>
										<%= fieldLabel %>
									</dd>
							</c:otherwise>
						</c:choose>

						<c:choose>
							<c:when test="<%= !fieldsEditingDisabled %>">
								<aui:select label="type" name='<%= "fieldType" + formFieldsIndex %>'>
									<aui:option selected='<%= fieldType.equals("text") %>' value="text"><liferay-ui:message key="text" /></aui:option>
									<aui:option selected='<%= fieldType.equals("textarea") %>' value="textarea"><liferay-ui:message key="text-box" /></aui:option>
									<aui:option selected='<%= fieldType.equals("options") %>' value="options"><liferay-ui:message key="options" /></aui:option>
									<aui:option selected='<%= fieldType.equals("radio") %>' value="radio"><liferay-ui:message key="radio-buttons" /></aui:option>
									<aui:option selected='<%= fieldType.equals("paragraph") %>' value="paragraph"><liferay-ui:message key="paragraph" /></aui:option>
									<aui:option selected='<%= fieldType.equals("checkbox") %>' value="checkbox"><liferay-ui:message key="check-box" /></aui:option>
								</aui:select>
							</c:when>
							<c:otherwise>
									<dt>
										<liferay-ui:message key="type" />
									</dt>
									<dd>
										<liferay-ui:message key="<%= fieldType %>" />
									</dd>
							</c:otherwise>
						</c:choose>

						<c:choose>
							<c:when test="<%= !fieldsEditingDisabled %>">
								<aui:input cssClass="optional-control" inlineLabel="left" label="optional" name='<%= "fieldOptional" + formFieldsIndex %>' type="checkbox" value="<%= fieldOptional %>" />
							</c:when>
							<c:otherwise>
									<dt>
										<liferay-ui:message key="optional" />
									</dt>
									<dd>
										<liferay-ui:message key='<%= fieldOptional ? "yes" : "no" %>' />
									</dd>
							</c:otherwise>
						</c:choose>

						<c:choose>
							<c:when test="<%= !fieldsEditingDisabled %>">
								<aui:input cssClass="options lfr-input-text-container" helpMessage="add-options-separated-by-commas" label="options" name='<%= "fieldOptions" + formFieldsIndex %>' value="<%= fieldOptions %>" />
							</c:when>
							<c:when test="<%= Validator.isNotNull(fieldOptions) %>">
									<dt>
										<liferay-ui:message key="options" />
									</dt>
									<dd>
										<%= fieldOptions %>
									</dd>
							</c:when>
						</c:choose>

						<c:if test="<%= true %>">
							<c:choose>
								<c:when test="<%= !fieldsEditingDisabled %>">
									<div class="validation">
										<liferay-ui:error key='<%= "invalidValidationDefinition" + formFieldsIndex %>' message="please-enter-both-the-validation-code-and-the-error-message" />

										<aui:a cssClass="validation-link" href="javascript:;"><liferay-ui:message key="validation" /> &raquo;</aui:a>

										<div class='validation-input <%= Validator.isNull(fieldValidationScript) ? "aui-helper-hidden" : "" %>'>
											<aui:column columnWidth="50">
												<aui:input cssClass="validation-script" cols="80" label="validation-script" name='<%= "fieldValidationScript" + formFieldsIndex %>' style="width: 95%" type="textarea" value="<%= fieldValidationScript %>" wrap="off" />

												<aui:input cssClass="lfr-input-text-container" cols="80" label="validation-error-message" name='<%= "fieldValidationErrorMessage" + formFieldsIndex %>' size="80" value="<%= fieldValidationErrorMessage %>" />
											</aui:column>
											<aui:column columnWidth="50">
												<div class="syntax-help">
													<jsp:include page="/script_help.jsp" />
												</div>
											</aui:column>
										</div>
									</div>
								</c:when>
								<c:when test="<%= Validator.isNotNull(fieldValidationScript) %>">
										<dt class="optional">
											<liferay-ui:message key="validation" />
										</dt>
										<dd>
											<pre><%= fieldValidationScript %></pre>
										</dd>
										<dt class="optional">
											<liferay-ui:message key="validation-error-message" />
										</dt>
										<dd>
											<%= fieldValidationErrorMessage %>
										</dd>
								</c:when>
								<c:otherwise>
										<dt class="optional">
											<liferay-ui:message key="validation" />
										</dt>
										<dd>
											<liferay-ui:message key="this-field-does-not-have-any-specific-validation" />
										</dd>
								</c:otherwise>
							</c:choose>
						</c:if>

						<c:if test="<%= fieldsEditingDisabled %>">
							</dl>
						</c:if>
					</div>

				<%
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

	A.delegate('change', toggleOptions, webFields, 'select');
	A.delegate('click', toggleValidationOptions, webFields, '.validation-link');

	webFields.all('select').each(toggleOptions);

	<c:if test="<%= !fieldsEditingDisabled %>">
		new Liferay.AutoFields(
			{
				contentBox: webFields,
				fieldIndexes: '<portlet:namespace />formFieldsIndexes',
				sortable: true,
				sortableHandle: '.field-label'
			}
		).render();
	</c:if>
</aui:script>