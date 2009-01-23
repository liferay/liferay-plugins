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

	.portlet-web-form .ctrl-holder label, .portlet-web-form table label {
		font-weight: bold;
	}

	.portlet-web-form .lfr-form-row .ctrl-holder {
		clear: none;
		float: left;
		margin-left: 2em;
	}

	.portlet-web-form .lfr-form-row .ctrl-holder.optional-control {
		margin: 1.3em;
	}

	.portlet-web-form .lfr-form-row .ctrl-holder.options, .portlet-web-form .lfr-form-row .validation .ctrl-holder {
		clear: both;
		float: none;
	}

	.portlet-web-form .lfr-form-row .field-title {
		color: #000;
		font-size: 120%;
		font-weight: bold;
		padding: 3px;
	}

	.portlet-web-form .lfr-form-row .row-controls {
		right: 10px;
	}

	.portlet-web-form .lfr-form-row {
		border: none;
		border-bottom: 1px solid #CCC;
		margin-top: 10px;
		padding-top: 1px;
		width: 100%;
	}

	.portlet-web-form .lfr-form-row:hover {
		background-color: #DFFCCB;
		border-bottom: 1px solid #B2FF3A;
		border-top: 1px solid #B2FF3A;
		padding-top: 0;
	}

	.portlet-web-form fieldset.rows-container {
		border: none;
		margin-top: 0;
	}
</style>


<form action="<liferay-portlet:actionURL portletConfiguration="true" />" class="uni-form portlet-web-form" method="post" id="<portlet:namespace />fm" name="<portlet:namespace />fm" onSubmit="submitForm(this); return false;">
<input name="<portlet:namespace /><%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />
<input name="<portlet:namespace />redirect" type="hidden" value="<%= HtmlUtil.escape(redirect) %>" />

<liferay-ui:tabs
	names="general,form-fields"
	param="tabs1"
	refresh="<%= false %>"
>
	<liferay-ui:section>
		<fieldset class="block-labels">
			<legend><liferay-ui:message key="form-information" /></legend>

			<liferay-ui:error key="titleRequired" message="please-enter-a-title" />

			<div class="ctrl-holder">
				<label for="<portlet:namespace />title"><liferay-ui:message key="title" /></label>

				<input class="lfr-input-text" id="<portlet:namespace />title" name="<portlet:namespace />title" type="text" value="<%= HtmlUtil.toInputSafe(title) %>" />
			</div>

			<div class="ctrl-holder">
				<label for="<portlet:namespace/>description"><liferay-ui:message key="description" /></label>

				<textarea class="lfr-textarea" id="<portlet:namespace/>description" name="<portlet:namespace/>description" wrap="soft"><%= description %></textarea>
			</div>

			<div class="ctrl-holder">
				<label><liferay-ui:message key="require-captcha" /> <liferay-ui:input-checkbox param="requireCaptcha" defaultValue="<%= requireCaptcha %>" /></label>
			</div>

			<div class="ctrl-holder">
				<label for="<portlet:namespace />successURL"><liferay-ui:message key="redirect-url-on-success" /></label>

				<input class="lfr-input-text" id="<portlet:namespace />successURL" name="<portlet:namespace />successURL" type="text" value="<%= HtmlUtil.toInputSafe(successURL) %>" />
			</div>
		</fieldset>

		<fieldset class="block-labels">
			<legend><liferay-ui:message key="handling-of-form-data" /></legend>

			<fieldset class="block-labels">
				<legend><liferay-ui:message key="email" /></legend>

				<liferay-ui:error key="subjectRequired" message="please-enter-a-subject" />
				<liferay-ui:error key="handlingRequired" message="please-select-an-action-for-the-handling-of-form-data" />
				<liferay-ui:error key="emailAddressInvalid" message="please-enter-a-valid-email-address" />
				<liferay-ui:error key="emailAddressRequired" message="please-enter-an-email-address" />
				<liferay-ui:error key="fileNameInvalid" message="please-enter-a-valid-path-and-filename" />

				<div class="ctrl-holder">
					<label><liferay-ui:message key="send-as-email" /> <liferay-ui:input-checkbox param="sendAsEmail" defaultValue="<%= sendAsEmail %>" /></label>
				</div>

				<div class="ctrl-holder">
					<label for="<portlet:namespace />subject"><liferay-ui:message key="subject" /></label>

					<input class="lfr-input-text" id="<portlet:namespace />subject" name="<portlet:namespace />subject" type="text" value="<%= subject %>" />
				</div>

				<div class="ctrl-holder">
					<label for="<portlet:namespace />emailAddress"><liferay-ui:message key="email-address" /></label>

					<input class="lfr-input-text" id="<portlet:namespace />emailAddress" name="<portlet:namespace />emailAddress" type="text" value="<%= emailAddress %>" />
				</div>
			</fieldset>

			<fieldset class="block-labels">
				<legend><liferay-ui:message key="database" /></legend>

				<div class="ctrl-holder">
					<label><liferay-ui:message key="save-to-database" /> <liferay-ui:input-checkbox param="saveToDatabase" defaultValue="<%= saveToDatabase %>" /></label>
				</div>
			</fieldset>

			<fieldset class="block-labels">
				<legend><liferay-ui:message key="file" /></legend>

				<div class="ctrl-holder">
					<label><liferay-ui:message key="save-to-file" /> <liferay-ui:input-checkbox param="saveToFile" defaultValue="<%= saveToFile %>" /></label>
				</div>

				<div class="ctrl-holder">
					<label for="<portlet:namespace />filename"><liferay-ui:message key="path-and-file-name" /></label>

					<input class="lfr-input-text" id="<portlet:namespace />filename" name="<portlet:namespace />fileName" type="text" value="<%= fileName %>" />
				</div>
			</fieldset>
		</fieldset>
	</liferay-ui:section>

	<liferay-ui:section>
		<fieldset class="block-labels rows-container" id="<portlet:namespace/>webFields">

			<c:if test="<%= fieldsEditingDisabled %>">
				<div class="portlet-msg-alert">
					<liferay-ui:message key="there-is-existing-form-data-please-export-and-delete-it-before-making-changes-to-the-fields" />
				</div>

				<liferay-portlet:resourceURL var="exportURL" portletName="<%= portletResource %>">
					<portlet:param name="<%= Constants.CMD %>" value="export" />
				</liferay-portlet:resourceURL>

				<input type="button" value="<liferay-ui:message key="export-data" />" onclick="submitForm(document.hrefFm, '<%= exportURL %>');" />

				<liferay-portlet:actionURL var="deleteURL" portletName="<%= portletResource %>">
					<portlet:param name="<%= ActionRequest.ACTION_NAME %>" value="deleteData" />
					<portlet:param name="redirect" value="<%= currentURL %>" />
				</liferay-portlet:actionURL>

				<input type="button" value="<liferay-ui:message key="delete-data" />" onclick="submitForm(document.<portlet:namespace/>fm, '<%= deleteURL %>');" />

				<br /><br />
			</c:if>

			<input name="<portlet:namespace/>updateFields" type="hidden" value="<%= !fieldsEditingDisabled %>" />

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
							<div class="ctrl-holder">
								<label for="<portlet:namespace/>fieldLabel<%= formFieldsIndex %>"><liferay-ui:message key="name" /></label>

								<input class="lfr-input-text label-name" id="<portlet:namespace/>fieldLabel<%= formFieldsIndex %>" name="<portlet:namespace/>fieldLabel<%= formFieldsIndex %>" size="50" type="text" value="<%= fieldLabel %>" onchange="jQuery(this).parent().prev().children('.field-label').html(jQuery(this).val())" />
							</div>
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
							<div class="ctrl-holder">
								<label for="<portlet:namespace/>fieldType<%= formFieldsIndex %>"><liferay-ui:message key="type" /></label>

								<select id="<portlet:namespace/>fieldType<%= formFieldsIndex %>" name="<portlet:namespace/>fieldType<%= formFieldsIndex %>">
									<option <%= (fieldType.equals("text")) ? "selected" : "" %> value="text"><liferay-ui:message key="text" /></option>
									<option <%= (fieldType.equals("textarea")) ? "selected" : "" %> value="textarea"><liferay-ui:message key="text-box" /></option>
									<option <%= (fieldType.equals("options")) ? "selected" : "" %> value="options"><liferay-ui:message key="options" /></option>
									<option <%= (fieldType.equals("radio")) ? "selected" : "" %> value="radio"><liferay-ui:message key="radiobuttons" /></option>
									<option <%= (fieldType.equals("paragraph")) ? "selected" : "" %> value="paragraph"><liferay-ui:message key="paragraph" /></option>
									<option <%= (fieldType.equals("checkbox")) ? "selected" : "" %> value="checkbox"><liferay-ui:message key="checkbox" /></option>
								</select>
							</div>
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
							<div class="ctrl-holder optional-control">
								<label><liferay-ui:message key="optional" /> <input <c:if test="<%= fieldOptional %>">checked</c:if> id="<portlet:namespace/>fieldOptional<%= formFieldsIndex %>" name="<portlet:namespace/>fieldOptional<%= formFieldsIndex %>" type="checkbox" /></label>
							</div>
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
							<div class="ctrl-holder options" id="<portlet:namespace/>optionsGroup<%= formFieldsIndex %>">
								<label for="<portlet:namespace/>fieldOptions<%= formFieldsIndex %>"><liferay-ui:message key="options" /></label>

								<span>(<liferay-ui:message key="add-options-separated-by-commas" />)</span><br />

								<input class="lfr-input-text" id="<portlet:namespace/>fieldOptions<%= formFieldsIndex %>" name="<portlet:namespace/>fieldOptions<%= formFieldsIndex %>" type="text" value="<%= fieldOptions %>" />
							</div>
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

									<div class="ctrl-holder">
										<a class="validation-link" href="javascript: ;"><liferay-ui:message key="validation" /> &raquo;</a>
									</div>

									<div class="validation-input" style='<%= Validator.isNull(fieldValidationScript) ? "display:none" : "" %>'>
										<div class="ctrl-holder">
											<table>
											<tr>
												<td>
													<label for="<portlet:namespace/>fieldValidationScript<%= formFieldsIndex %>"><liferay-ui:message key="validation-script" /></label>

													<textarea class="lfr-textarea validation-script" cols="80" id="<portlet:namespace />fieldValidationScript<%= formFieldsIndex %>" name="<portlet:namespace />fieldValidationScript<%= formFieldsIndex %>" style="width: 95%" wrap="off"><%= fieldValidationScript %></textarea>
												</td>
												<td>
													<div class="syntax-help">
														<jsp:include page="/script_help.jsp" />
													</div>
												</td>
											</tr>
											</table>
										</div>

										<div class="ctrl-holder">
											<label for="<portlet:namespace/>fieldValidationErrorMessage<%= formFieldsIndex %>"><liferay-ui:message key="validation-error-message" /></label>

											<input class="lfr-input-text" id="<portlet:namespace />fieldValidationErrorMessage<%= formFieldsIndex %>" name="<portlet:namespace />fieldValidationErrorMessage<%= formFieldsIndex %>" size="80" type="text" value="<%= fieldValidationErrorMessage %>" />
										</div>
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

		</fieldset>
	</liferay-ui:section>
</liferay-ui:tabs>

<div class="button-holder">
	<input type="submit" value="<liferay-ui:message key="save" />" />

	<input type="button" value="<liferay-ui:message key="cancel" />" onclick="location.href = '<%= HtmlUtil.escape(redirect) %>';" />
</div>

</form>

<script type="text/javascript">
	jQuery(
		function() {
			var selects = jQuery('#<portlet:namespace/>webFields select');

			var toggleOptions = function() {
				var select = jQuery(this);
				var div = select.parent().next().next();
				var value = select.find('option:selected').val();

				if (value == 'options' || value == 'radio') {
					div.children().show();
					div.show();
				}
				else if (value == 'paragraph') {

					// Show just the text field and not the labels since there
					// are multiple choice inputs

					div.children().hide();
					div.children(".lfr-input-text").show();
					div.show();
				}
				else {
					div.hide();
				}

				var optional = select.parent().next();
				var divName = select.parent().prev();

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
					jQuery(this).parent().next().toggle();
				}
			);

			<c:if test="<%= !fieldsEditingDisabled %>">
				new Liferay.AutoFields(
					{
						container: '#<portlet:namespace />webFields',
						baseRows: '#<portlet:namespace />webFields > .lfr-form-row',
						fieldIndexes: '<portlet:namespace />formFieldsIndexes',
						sortable: true,
						sortableHandle: '.field-label'
					}
				);
			</c:if>
		}
	);
</script>