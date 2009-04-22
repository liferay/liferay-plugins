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
String title = preferences.getValue("title", StringPool.BLANK);
String description = preferences.getValue("description", StringPool.BLANK);
boolean requireCaptcha = GetterUtil.getBoolean(preferences.getValue("requireCaptcha", StringPool.BLANK));
String successURL = preferences.getValue("successURL", StringPool.BLANK);
%>

<form action="<portlet:actionURL><portlet:param name="<%= ActionRequest.ACTION_NAME %>" value="saveData" /></portlet:actionURL>" class="uni-form" id="<portlet:namespace />fm" method="post" name="<portlet:namespace />fm">

<c:if test="<%= Validator.isNull(successURL) %>">
	<input name="<portlet:namespace/>redirect" type="hidden" value="<%= currentURL %>" />
</c:if>

<fieldset class="block-labels">
	<legend><%= HtmlUtil.escape(title) %></legend>

	<p class="description"><%= HtmlUtil.escape(description) %></p>

	<liferay-ui:success key="success" message="the-form-information-was-sent-successfully" />

	<liferay-ui:error exception="<%= CaptchaTextException.class %>" message="text-verification-failed" />
	<liferay-ui:error key="error" message="an-error-occurred-while-sending-the-form-information" />

	<c:if test='<%= WebFormUtil.VALIDATION_SCRIPT_ENABLED && SessionErrors.contains(renderRequest, "validation-script-error") %>'>
		<liferay-util:include page="/script_error.jsp" />
	</c:if>

	<%
	int i = 1;

	String fieldName = "field" + i;
	String fieldLabel = preferences.getValue("fieldLabel" + i, StringPool.BLANK);
	boolean fieldOptional = PrefsParamUtil.getBoolean(preferences, request, "fieldOptional" + i, false);
	String fieldValue = ParamUtil.getString(request, fieldName);
	String[] options = null;

	while ((i == 1) || Validator.isNotNull(fieldLabel)) {
		String fieldType = preferences.getValue("fieldType" + i, "text");
		String fieldOptions = preferences.getValue("fieldOptions" + i, "unknown");
		String fieldValidationScript = preferences.getValue("fieldValidationScript" + i, StringPool.BLANK);
		String fieldValidationErrorMessage = preferences.getValue("fieldValidationErrorMessage" + i, StringPool.BLANK);
	%>

		<liferay-ui:error key='<%= "error" + fieldLabel %>' message="<%= fieldValidationErrorMessage %>" />

		<c:if test='<%= Validator.isNotNull(fieldValidationScript) %>'>
			<div id="<portlet:namespace/>validationError<%= fieldLabel %>" style="display: none">
				<span class="portlet-msg-error"><%= fieldValidationErrorMessage %></span>
			</div>
		</c:if>

		<c:if test="<%= !fieldOptional %>">
			<div id="<portlet:namespace/>fieldOptionalError<%= fieldLabel %>" style="display: none">
				<span class="portlet-msg-error"><liferay-ui:message key="this-field-is-mandatory" /></span>
			</div>
		</c:if>

		<c:choose>
			<c:when test='<%= fieldType.equals("paragraph") %>'>
				<p class="lfr-webform" id="<portlet:namespace /><%= fieldName %>"><%= fieldOptions %></p>
			</c:when>
			<c:when test='<%= fieldType.equals("text") %>'>
				<div class="ctrl-holder">
					<label class='<%= fieldOptional ? "optional" : "" %>' for="<portlet:namespace /><%= fieldName %>"><%= HtmlUtil.escape(fieldLabel) %></label>

					<input class='<%= fieldOptional ? "optional" : "" %>' id="<portlet:namespace /><%= fieldName %>" name="<portlet:namespace /><%= fieldName %>" type="text" value="<%= HtmlUtil.escape(fieldValue) %>" />
				</div>
			</c:when>
			<c:when test='<%= fieldType.equals("textarea") %>'>
				<div class="ctrl-holder">
					<label class='<%= fieldOptional ? "optional" : "" %>' for="<portlet:namespace /><%= fieldName %>"><%= HtmlUtil.escape(fieldLabel) %></label>

					<textarea class='<%= fieldOptional ? "optional" : "" %>' id="<portlet:namespace /><%= fieldName %>" name="<portlet:namespace /><%= fieldName %>" wrap="soft"><%= HtmlUtil.escape(fieldValue) %></textarea>
				</div>
			</c:when>
			<c:when test='<%= fieldType.equals("checkbox") %>'>
				<div class="ctrl-holder <%= fieldOptional ? "optional" : "" %>">
					<label class='<%= fieldOptional ? "optional" : "" %>' for="<portlet:namespace /><%= fieldName %>"><input <%= Validator.isNotNull(fieldValue) ? "checked" : "" %> id="<portlet:namespace /><%= fieldName %>" name="<portlet:namespace /><%= fieldName %>" type="checkbox" /> <%= HtmlUtil.escape(fieldLabel) %></label>
				</div>
			</c:when>
			<c:when test='<%= fieldType.equals("radio") %>'>
				<div class="ctrl-holder <%= fieldOptional ? "optional" : "" %>">
					<label class='<%= fieldOptional ? "optional" : "" %>' for="<portlet:namespace /><%= fieldName %>"><%= HtmlUtil.escape(fieldLabel) %></label>

					<%
					options = WebFormUtil.split(fieldOptions);

					for (int j = 0; j < options.length; j++) {
						String optionValue = options[j];
					%>

						<label><input name="<portlet:namespace /><%= fieldName %>" <%= fieldValue.equals(optionValue) ? "checked=\"true\"" : "" %> type="radio" value="<%= HtmlUtil.escape(optionValue) %>" /> <%= HtmlUtil.escape(optionValue) %></label>

					<%
					}
					%>

				</div>
			</c:when>
			<c:when test='<%= fieldType.equals("options") %>'>
				<div class="ctrl-holder <%= fieldOptional ? "optional" : "" %>">
					<label class='<%= fieldOptional ? "optional" : "" %>' for="<portlet:namespace /><%= fieldName %>"><%= HtmlUtil.escape(fieldLabel) %></label>

					<%
					options = WebFormUtil.split(fieldOptions);
					%>

					<select id="<portlet:namespace /><%= fieldName %>" name="<portlet:namespace /><%= fieldName %>">

						<%
						for (int j = 0; j < options.length; j++) {
							String optionValue = options[j];
						%>

							<option <%= fieldValue.equals(optionValue) ? "selected" : "" %> value="<%= HtmlUtil.escape(optionValue) %>"><%= HtmlUtil.escape(optionValue) %></option>

						<%
						}
						%>

					</select>
				</div>
			</c:when>
		</c:choose>

	<%
		i++;

		fieldName = "field" + i;
		fieldLabel = preferences.getValue("fieldLabel" + i, "");
		fieldOptional = PrefsParamUtil.getBoolean(preferences, request, "fieldOptional" + i, false);
		fieldValue = ParamUtil.getString(request, fieldName);
	}
	%>

	<c:if test="<%= requireCaptcha %>">
		<portlet:resourceURL var="captchaURL">
			<portlet:param name="<%= Constants.CMD %>" value="captcha" />
		</portlet:resourceURL>

		<liferay-ui:captcha url="<%= captchaURL %>" />
	</c:if>

	<div class="button-holder">
		<input type="submit" value="<liferay-ui:message key="send" />" />
	</div>
</fieldset>

</form>

<script type="text/javascript">
	jQuery(document).ready(
		function() {
			jQuery('#<portlet:namespace />fm').submit(
				function() {
					var keys = [];
					var fieldLabels = {};
					var fieldOptional = {};
					var fieldValidationErrorMessages = {};
					var fieldValidationFunctions = {};
					var fieldsMap = {};

					<%
					int fieldIndex = 1;
					fieldLabel = preferences.getValue("fieldLabel" + fieldIndex, StringPool.BLANK);

					while ((fieldIndex == 1) || Validator.isNotNull(fieldLabel)) {
						fieldOptional = PrefsParamUtil.getBoolean(preferences, request, "fieldOptional" + fieldIndex, false);
						String fieldType = preferences.getValue("fieldType" + fieldIndex, "text");
						String fieldValidationScript = preferences.getValue("fieldValidationScript" + fieldIndex, StringPool.BLANK);
						String fieldValidationErrorMessage = preferences.getValue("fieldValidationErrorMessage" + fieldIndex, StringPool.BLANK);
					%>

						var key = "<%= HtmlUtil.escape(fieldLabel) %>";

						keys[<%= fieldIndex %>] = key;

						fieldLabels[key] = "<%= HtmlUtil.escape(fieldLabel) %>";
						fieldValidationErrorMessages[key] = "<%= fieldValidationErrorMessage %>";

						function fieldValidationFunction<%= fieldIndex %>(currentFieldValue, fieldsMap) {
							<c:choose>
								<c:when test='<%= Validator.isNotNull(fieldValidationScript) %>'>
									<%= fieldValidationScript %>
								</c:when>
								<c:otherwise>
									return true;
								</c:otherwise>
							</c:choose>
						};

						fieldOptional[key] = <%= fieldOptional %>;
						fieldValidationFunctions[key] = fieldValidationFunction<%= fieldIndex %>;

						<c:choose>
							<c:when test='<%= fieldType.equals("radio") %>'>
								fieldsMap[key] = jQuery("input[name='<portlet:namespace />field<%= fieldIndex %>']:checked").val();

								if (!fieldsMap[key]) {
									fieldsMap[key] = '';
								}
							</c:when>
							<c:otherwise>
								fieldsMap[key] = jQuery("#<portlet:namespace />field<%= fieldIndex %>")[0].value;
							</c:otherwise>
						</c:choose>

					<%
						fieldIndex++;
						fieldLabel = preferences.getValue("fieldLabel" + fieldIndex, "");
					}
					%>

					var validationErrors = false;

					for (var i = 1; i < keys.length; i++) {
						var key = keys [i];

						var currentFieldValue = fieldsMap[key];

						if (!fieldOptional[key] && currentFieldValue.match(/^\s*$/)) {
							validationErrors = true;

							jQuery(".portlet-msg-success").slideUp();
							jQuery("#<portlet:namespace />fieldOptionalError" + fieldLabels[key]).slideDown();
						}
						else if (!fieldValidationFunctions[key](currentFieldValue, fieldsMap)) {
							validationErrors = true;

							jQuery(".portlet-msg-success").slideUp();
							jQuery("#<portlet:namespace />fieldOptionalError" + fieldLabels[key]).slideUp();
							jQuery("#<portlet:namespace />validationError" + fieldLabels[key]).slideDown();
						}
						else {
							jQuery("#<portlet:namespace />validationError" + fieldLabels[key]).slideUp();
							jQuery("#<portlet:namespace />fieldOptionalError" + fieldLabels[key]).slideUp();
						}
					}

					if (validationErrors) {
						return false;
					}
				}
			);
		}
	);
</script>