<%
/**
 * Copyright (c) 2000-2009 Liferay, Inc. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
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

if (WebFormUtil.getTableRowsCount(databaseTableName) > 0) {
	fieldsEditingDisabled = true;
}
%>

<style type="text/css">
	.portlet-web-form fieldset .handle-data{
		border: 1px solid #BFBFBF;
	}

	.portlet-web-form fieldset.rows-container {
		border: none;
		margin-top: 0;
	}

	.portlet-web-form table.editing-disabled {
		margin-left: 30px;
	}

	.portlet-web-form table.editing-disabled label {
		font-weight: bold;
		color: #999999;
	}

	.portlet-web-form table.editing-disabled td {
		padding: 3px;
	}

	.portlet-web-form .lfr-form-row .aui-ctrl-holder {
		margin-left: 2em;
	}

	.portlet-web-form .lfr-form-row .aui-ctrl-holder.optional-control {
		margin: 1.3em;
	}

	.portlet-web-form .lfr-form-row .aui-ctrl-holder.options, .portlet-web-form .lfr-form-row .validation .aui-ctrl-holder {
		clear: both;
		float: none;
	}

	.portlet-web-form .lfr-form-row .field-title {
		color: #000;
		font-size: 120%;
		font-weight: bold;
		padding: 3px;
	}

	.portlet-web-form .validation {
		clear: left;
	}
</style>

<liferay-portlet:actionURL portletConfiguration="true" var="configurationURL" />

<aui:form action="<%= configurationURL %>" cssClass="portlet-web-form" method="post" name="fm" onSubmit="submitForm(this); return false;">
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />
	<aui:input name="redirect" type="hidden" value="<%= redirect %>" />

	<liferay-ui:panel-container id='webFormConfiguration' extended="<%= Boolean.TRUE %>" persistState="<%= true %>">
		<liferay-ui:panel id='webFormGeneral' title='<%= LanguageUtil.get(pageContext, "form-information") %>' collapsible="<%= true %>" persistState="<%= true %>" extended="<%= true %>">
			<aui:fieldset>
				<liferay-ui:error key="titleRequired" message="please-enter-a-title" />

				<aui:input cssClass="lfr-input-text" name="title" value="<%= HtmlUtil.toInputSafe(title) %>" />

				<aui:input name="description" type="textarea" value="<%= HtmlUtil.toInputSafe(description) %>" wrap="soft" />

				<aui:input inlineLabel="<%= true %>" label="require-captcha" name="requireCaptcha" type="checkbox" value="<%= requireCaptcha %>" />

				<aui:input cssClass="lfr-input-text" label="redirect-url-on-success" name="successURL" value="<%= HtmlUtil.toInputSafe(successURL) %>" />
			</aui:fieldset>
		</liferay-ui:panel>

		<liferay-ui:panel id='webFormData' title='<%= LanguageUtil.get(pageContext, "handling-of-form-data") %>' collapsible="<%= true %>" persistState="<%= true %>" extended="<%= true %>">
			<aui:fieldset>
				<fieldset class="handle-data">
					<legend><liferay-ui:message key="email" /></legend>

					<liferay-ui:error key="subjectRequired" message="please-enter-a-subject" />
					<liferay-ui:error key="handlingRequired" message="please-select-an-action-for-the-handling-of-form-data" />
					<liferay-ui:error key="emailAddressInvalid" message="please-enter-a-valid-email-address" />
					<liferay-ui:error key="emailAddressRequired" message="please-enter-an-email-address" />
					<liferay-ui:error key="fileNameInvalid" message="please-enter-a-valid-path-and-filename" />

					<aui:input inlineLabel="<%= true %>" label="send-as-email" name="sendAsEmail" type="checkbox" value="<%= sendAsEmail %>" />

					<aui:input cssClass="lfr-input-text" name="subject" value="<%= subject %>" />

					<aui:input cssClass="lfr-input-text" label="email-address" name="emailAddress" value="<%= emailAddress %>" />
				</fieldset>

				<fieldset class="handle-data">
					<legend><liferay-ui:message key="database" /></legend>

					<aui:input inlineLabel="<%= true %>" label="save-to-database" name="saveToDatabase" type="checkbox" value="<%= saveToDatabase %>" />
				</fieldset>

				<fieldset class="handle-data">
					<legend><liferay-ui:message key="file" /></legend>

					<aui:input inlineLabel="<%= true %>" label="save-to-file" name="saveToFile" type="checkbox" value="<%= saveToFile %>" />

					<aui:input cssClass="lfr-input-text" label="path-and-file-name" name="filename" value="<%= fileName %>" />
				</fieldset>
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

					<aui:button value="export-data" onclick="<%= taglibExport %>" />

					<liferay-portlet:actionURL var="deleteURL" portletName="<%= portletResource %>">
						<portlet:param name="<%= ActionRequest.ACTION_NAME %>" value="deleteData" />
						<portlet:param name="redirect" value="<%= currentURL %>" />
					</liferay-portlet:actionURL>

					<%
					String taglibDelete = "submitForm(document." + renderResponse.getNamespace() + "fm, '" + deleteURL + "');";
					%>

					<aui:button value="delete-data" onclick="<%= taglibDelete %>" />

					<br /><br />
				</c:if>

				<aui:input name="updateFields" type="hidden" value="<%= !fieldsEditingDisabled %>" />

				<%
				String formFieldsIndexesParam = ParamUtil.getString(renderRequest, "formFieldsIndexes") ;

				int[] formFieldsIndexes = null;

				if (Validator.isNotNull(formFieldsIndexesParam)){
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
								<aui:input cssClass="lfr-input-text label-name" label="name" name='<%= "fieldLabel" + formFieldsIndex %>' onchange="jQuery(this).parent().parent().parent().find('.field-label').html(jQuery(this).val())" size="50" value="<%= fieldLabel %>"  />
							</c:when>
							<c:otherwise>
								<table class="editing-disabled">
								<tr>
									<td>
										<label><liferay-ui:message key="name" /></label>
									</td>
									<td>
										<%= fieldLabel %>
									</td>
								</tr>
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
								<tr>
									<td>
										<label><liferay-ui:message key="type" /></label>
									</td>
									<td>
										<liferay-ui:message key="<%= fieldType %>" />
									</td>
								</tr>
							</c:otherwise>
						</c:choose>

						<c:choose>
							<c:when test="<%= !fieldsEditingDisabled %>">
								<aui:input cssClass="optional-control" inlineLabel="<%= true %>" label="optional" name='<%= "fieldOptional" + formFieldsIndex %>' type="checkbox" value="<%= fieldOptional %>" />
							</c:when>
							<c:otherwise>
								<tr>
									<td>
										<label><liferay-ui:message key="optional" /></label>
									</td>
									<td>
										<liferay-ui:message key='<%= fieldOptional ? "yes" : "no" %>' />
									</td>
								</tr>
							</c:otherwise>
						</c:choose>

						<c:choose>
							<c:when test="<%= !fieldsEditingDisabled %>">
								<aui:input cssClass="options lfr-input-text" helpMessage="add-options-separated-by-commas" label="options" name='<%= "fieldOptions" + formFieldsIndex %>' value="<%= fieldOptions %>" />
							</c:when>
							<c:when test="<%= Validator.isNotNull(fieldOptions) %>">
								<tr>
									<td>
										<label><liferay-ui:message key="options" /></label>
									</td>
									<td>
										<%= fieldOptions %>
									</td>
								</tr>
							</c:when>
						</c:choose>

						<c:if test="<%= WebFormUtil.VALIDATION_SCRIPT_ENABLED %>">
							<c:choose>
								<c:when test="<%= !fieldsEditingDisabled %>">
									<div class="validation">
										<liferay-ui:error key='<%= "invalidValidationDefinition" + formFieldsIndex %>' message="please-enter-both-the-validation-code-and-the-error-message" />

										<aui:a cssClass="validation-link" href="javascript:;"><liferay-ui:message key="validation" /> &raquo;</aui:a>

										<div class="validation-input" style='<%= Validator.isNull(fieldValidationScript) ? "display:none" : "" %>'>
											<aui:column columnWidth="50">
												<aui:input  cssClass="validation-script" cols="80" label="validation-script" name='<%= "fieldValidationScript" + formFieldsIndex %>' style="width: 95%" type="textarea" value="<%= fieldValidationScript %>" wrap="off" />

												<aui:input  cssClass="lfr-input-text" cols="80" label="validation-error-message" name='<%= "fieldValidationErrorMessage" + formFieldsIndex %>' size="80" value="<%= fieldValidationErrorMessage %>" />
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
									<tr>
										<td>
											<label class="optional"><liferay-ui:message key="validation" /></label>
										</td>
										<td>
											<pre><%= fieldValidationScript %></pre>
										</td>
									</tr>
									<tr>
										<td>
											<label class="optional"><liferay-ui:message key="validation-error-message" /></label>
										</td>
										<td>
											<%= fieldValidationErrorMessage %>
										</td>
									</tr>
								</c:when>
								<c:otherwise>
									<tr>
										<td>
											<label class="optional"><liferay-ui:message key="validation" /></label>
										</td>
										<td>
											<liferay-ui:message key="this-field-does-not-have-any-specific-validation" />
										</td>
									</tr>
								</c:otherwise>
							</c:choose>
						</c:if>

						<c:if test="<%= fieldsEditingDisabled %>">
							</table>
						</c:if>
					</div>

				<%
				}
				%>

			</aui:fieldset>
		</liferay-ui:panel>
	</liferay-ui:panel-container>

	<aui:button-row>
		<aui:button type="submit" value="save" />

		<aui:button value="cancel" onclick="<%= redirect %>" />
	</aui:button-row>
</aui:form>

<script type="text/javascript">
	jQuery(
		function() {
			var selects = jQuery('.webFields select');

			var toggleOptions = function() {
				var select = jQuery(this);
				var div = select.parent().parent().next().next();
				var value = select.find('option:selected').val();

				if (value == 'options' || value == 'radio') {
					div.children().show();
					div.show();
				}
				else if (value == 'paragraph') {

					// Show just the text field and not the labels since there
					// are multiple choice inputs

					div.find("label").hide();
					div.show();
				}
				else {
					div.hide();
				}

				var optional = select.parent().parent().next();
				var divName = select.parent().parent().prev();

				if (value == 'paragraph') {
					var inputName = divName.children("input");

					inputName.val('<liferay-ui:message key="paragraph" />');
					inputName.trigger('change');

					divName.hide();
					optional.hide();

					optional.children("input[type='checkbox']").attr('checked', 'true');
				}
				else {
					optional.show();
					divName.show();
				}
			};

			selects.change(toggleOptions);
			selects.each(toggleOptions);

			jQuery('.validation-link').click(
				function(event){
					jQuery(this).next().toggle();
				}
			);

			<c:if test="<%= !fieldsEditingDisabled %>">
				new Liferay.AutoFields(
					{
						container: '.webFields',
						baseRows: '.webFields > .lfr-form-row',
						fieldIndexes: '<portlet:namespace />formFieldsIndexes',
						sortable: true,
						sortableHandle: '.field-label'
					}
				);
			</c:if>
		}
	);
</script>