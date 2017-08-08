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
String title = LocalizationUtil.getPreferencesValue(portletPreferences, "title", themeDisplay.getLanguageId());
String description = LocalizationUtil.getPreferencesValue(portletPreferences, "description", themeDisplay.getLanguageId());
boolean requireCaptcha = GetterUtil.getBoolean(portletPreferences.getValue("requireCaptcha", StringPool.BLANK));
String successURL = portletPreferences.getValue("successURL", StringPool.BLANK);
%>

<portlet:actionURL var="saveDataURL">
	<portlet:param name="<%= ActionRequest.ACTION_NAME %>" value="saveData" />
</portlet:actionURL>

<aui:form action="<%= saveDataURL %>" method="post" name="fm">
	<c:if test="<%= Validator.isNull(successURL) %>">
		<aui:input name="redirect" type="hidden" value="<%= currentURL %>" />
	</c:if>

	<aui:fieldset label="<%= HtmlUtil.escape(title) %>">
		<c:if test="<%= Validator.isNotNull(description) %>">
			<p class="description"><%= HtmlUtil.escape(description) %></p>
		</c:if>

		<liferay-ui:success key="success" message="the-form-information-was-sent-successfully" />

		<liferay-ui:error exception="<%= CaptchaMaxChallengesException.class %>" message="maximum-number-of-captcha-attempts-exceeded" />
		<liferay-ui:error exception="<%= CaptchaTextException.class %>" message="text-verification-failed" />
		<liferay-ui:error key="error" message="an-error-occurred-while-sending-the-form-information" />

		<c:if test='<%= PortletPropsValues.VALIDATION_SCRIPT_ENABLED && SessionErrors.contains(renderRequest, "validationScriptError") %>'>
			<liferay-util:include page="/script_error.jsp" />
		</c:if>

		<%
		int i = 1;

		String fieldName = "field" + i;
		String fieldLabel = LocalizationUtil.getPreferencesValue(portletPreferences, "fieldLabel" + i, themeDisplay.getLanguageId());
		boolean fieldOptional = PrefsParamUtil.getBoolean(portletPreferences, request, "fieldOptional" + i, false);
		String fieldValue = ParamUtil.getString(request, fieldName);

		while ((i == 1) || Validator.isNotNull(fieldLabel)) {
			String fieldType = portletPreferences.getValue("fieldType" + i, "text");
			String fieldOptions = LocalizationUtil.getPreferencesValue(portletPreferences, "fieldOptions" + i, themeDisplay.getLanguageId());
			String fieldParagraph = LocalizationUtil.getPreferencesValue(portletPreferences, "fieldParagraph" + i, themeDisplay.getLanguageId());
			String fieldValidationScript = portletPreferences.getValue("fieldValidationScript" + i, StringPool.BLANK);
			String fieldValidationErrorMessage = portletPreferences.getValue("fieldValidationErrorMessage" + i, StringPool.BLANK);
		%>

			<c:if test="<%= PortletPropsValues.VALIDATION_SCRIPT_ENABLED %>">
				<liferay-ui:error key='<%= "error" + fieldLabel %>' message="<%= fieldValidationErrorMessage %>" />

				<c:if test="<%= Validator.isNotNull(fieldValidationScript) %>">
					<div class="hide" id="<portlet:namespace />validationError<%= fieldName %>" role="alert">
						<span class="alert alert-error"><%= fieldValidationErrorMessage %></span>
					</div>
				</c:if>
			</c:if>

			<c:if test="<%= !fieldOptional %>">
				<div class="hide" id="<portlet:namespace />fieldOptionalError<%= fieldName %>" role="alert">
					<span class="alert alert-error"><liferay-ui:message key="this-field-is-mandatory" /></span>
				</div>
			</c:if>

			<c:choose>
				<c:when test='<%= fieldType.equals("paragraph") %>'>
					<p class="format-paragraph" id="<portlet:namespace /><%= fieldName %>"><%= HtmlUtil.escape(fieldParagraph) %></p>
				</c:when>
				<c:when test='<%= fieldType.equals("text") %>'>
					<aui:input cssClass='<%= fieldOptional ? "optional" : StringPool.BLANK %>' label="<%= HtmlUtil.escape(fieldLabel) %>" name="<%= fieldName %>" value="<%= HtmlUtil.escape(fieldValue) %>" />
				</c:when>
				<c:when test='<%= fieldType.equals("textarea") %>'>
					<aui:input cssClass='<%= (fieldOptional ? "optional" : StringPool.BLANK) %>' label="<%= HtmlUtil.escape(fieldLabel) %>" name="<%= fieldName %>" type="textarea" value="<%= HtmlUtil.escape(fieldValue) %>" wrap="soft" wrapperCssClass="lfr-textarea-container" />
				</c:when>
				<c:when test='<%= fieldType.equals("checkbox") %>'>
					<aui:input cssClass='<%= fieldOptional ? "optional" : StringPool.BLANK %>' label="<%= HtmlUtil.escape(fieldLabel) %>" name="<%= fieldName %>" type="checkbox" value="<%= GetterUtil.getBoolean(fieldValue) %>" />
				</c:when>
				<c:when test='<%= fieldType.equals("radio") %>'>
					<aui:field-wrapper cssClass='<%= fieldOptional ? "optional" : StringPool.BLANK %>' label="<%= HtmlUtil.escape(fieldLabel) %>" name="<%= fieldName %>">

						<%
						for (String fieldOptionValue : WebFormUtil.split(fieldOptions)) {
						%>

							<aui:input checked="<%= fieldValue.equals(fieldOptionValue) %>" label="<%= HtmlUtil.escape(fieldOptionValue) %>" name="<%= fieldName %>" type="radio" value="<%= HtmlUtil.escape(fieldOptionValue) %>" />

						<%
						}
						%>

					</aui:field-wrapper>
				</c:when>
				<c:when test='<%= fieldType.equals("options") %>'>
					<aui:select cssClass='<%= fieldOptional ? "optional" : StringPool.BLANK %>' label="<%= HtmlUtil.escape(fieldLabel) %>" name="<%= fieldName %>">

						<%
						for (String fieldOptionValue : WebFormUtil.split(fieldOptions)) {
						%>

							<aui:option selected="<%= fieldValue.equals(fieldOptionValue) %>" value="<%= HtmlUtil.escape(fieldOptionValue) %>"><%= HtmlUtil.escape(fieldOptionValue) %></aui:option>

						<%
						}
						%>

					</aui:select>
				</c:when>
			</c:choose>

		<%
			i++;

			fieldName = "field" + i;
			fieldLabel = LocalizationUtil.getPreferencesValue(portletPreferences, "fieldLabel" + i, themeDisplay.getLanguageId());
			fieldOptional = PrefsParamUtil.getBoolean(portletPreferences, request, "fieldOptional" + i, false);
			fieldValue = ParamUtil.getString(request, fieldName);
		}
		%>

		<c:if test="<%= requireCaptcha %>">
			<portlet:resourceURL var="captchaURL">
				<portlet:param name="<%= Constants.CMD %>" value="captcha" />
			</portlet:resourceURL>

			<liferay-ui:captcha url="<%= captchaURL %>" />
		</c:if>

		<aui:button onClick="" type="submit" value="send" />
	</aui:fieldset>
</aui:form>

<aui:script use="aui-base,selector-css3">
	var keys = [];

	var fieldLabels = {};
	var fieldOptional = {};
	var fieldValidationErrorMessages = {};
	var fieldValidationFunctions = {};

	var getFieldsMap = function() {
		var fieldsMap = {};

		keys.forEach(
			function(key) {
				var field = A.one('[name="<portlet:namespace />' + key + '"]');

				if (field && (field.attr('type') === 'hidden')) {
					field = A.one('[name="<portlet:namespace />' + key + 'Checkbox"]');
				}

				if (field && ((field.attr('type') === 'checkbox') || (field.attr('type') === 'radio'))) {
					if (field.attr('type') === 'checkbox') {
						field = A.one('[name="<portlet:namespace />' + key + 'Checkbox"]:checked');
					}
					else {
						field = A.one('[name="<portlet:namespace />' + key + '"]:checked');
					}

					fieldsMap[key] = '';

					if (field) {
						fieldsMap[key] = field.val();
					}
				}
				else {
					fieldsMap[key] = (field && field.val()) || '';
				}
			});

		return fieldsMap;
	}

	var validateField = function(key) {
		var fieldsMap = getFieldsMap();

		var field = A.one('[name="<portlet:namespace />' + key + '"]');

		if (field && (field.attr('type') === 'hidden')) {
			field = A.one('[name="<portlet:namespace />' + key + 'Checkbox"]');
		}
		else if (field && (field.attr('type') === 'radio')) {
			var uncheckedOptions = A.all('[name="<portlet:namespace />' + key + '"]:not(:checked)');

			uncheckedOptions.each(
				function(option) {
					option.removeAttribute('aria-invalid');
				}
			);

			field = A.one('[name="<portlet:namespace />' + key + '"]:checked');
		}

		var currentFieldValue = fieldsMap[key];

		var optionalFieldError = A.one('#<portlet:namespace />fieldOptionalError' + key);
		var validationError = A.one('#<portlet:namespace />validationError' + key);

		if (!fieldOptional[key] && currentFieldValue.match(/^\s*$/)) {
			A.all('.alert-success').hide();

			if (validationError) {
				validationError.hide();
			}

			if (optionalFieldError) {
				optionalFieldError.show();
				optionalFieldError.replace(optionalFieldError);
			}

			if (field) {
				field.attr('aria-invalid', true);
			}
		}
		else if (!fieldValidationFunctions[key](currentFieldValue, fieldsMap)) {
			A.all('.alert-success').hide();

			if (optionalFieldError) {
				optionalFieldError.hide();
			}

			if (validationError) {
				validationError.show();
				validationError.replace(validationError);
			}

			if (field) {
				field.attr('aria-invalid', true);
			}
		}
		else {
			if (optionalFieldError) {
				optionalFieldError.hide();
			}

			if (validationError) {
				validationError.hide();
			}

			if (field) {
				field.attr('aria-invalid', false);
			}

			return true;
		}

		return false;
	}

	<%
	int i = 1;

	String fieldName = "field" + i;
	String fieldLabel = portletPreferences.getValue("fieldLabel" + i, StringPool.BLANK);

	while ((i == 1) || Validator.isNotNull(fieldLabel)) {
		boolean fieldOptional = PrefsParamUtil.getBoolean(portletPreferences, request, "fieldOptional" + i, false);
		String fieldValidationScript = portletPreferences.getValue("fieldValidationScript" + i, StringPool.BLANK);
		String fieldValidationErrorMessage = portletPreferences.getValue("fieldValidationErrorMessage" + i, StringPool.BLANK);
	%>

		var key = '<%= fieldName %>';

		keys[<%= i %>] = key;

		fieldLabels[key] = '<%= HtmlUtil.escapeJS(fieldLabel) %>';
		fieldValidationErrorMessages[key] = '<%= HtmlUtil.escapeJS(fieldValidationErrorMessage) %>';

		function fieldValidationFunction<%= i %>(currentFieldValue, fieldsMap) {
			<c:choose>
				<c:when test="<%= PortletPropsValues.VALIDATION_SCRIPT_ENABLED && Validator.isNotNull(fieldValidationScript) %>">
					<%= fieldValidationScript %>
				</c:when>
				<c:otherwise>
					return true;
				</c:otherwise>
			</c:choose>
		};

		fieldOptional[key] = <%= fieldOptional %>;
		fieldValidationFunctions[key] = fieldValidationFunction<%= i %>;

	<%
		i++;

		fieldName = "field" + i;
		fieldLabel = portletPreferences.getValue("fieldLabel" + i, "");
	}
	%>

	keys.forEach(
		function(key) {
			var fields = A.all('[name="<portlet:namespace />' + key + '"]');

			var addOnBlurFieldValidation = function(field) {
				if (field && (field.attr('type') === 'hidden')) {
					field = A.one('[name="<portlet:namespace />' + key + 'Checkbox"]');
				}

				field.on(
					'blur',
					function(event) {
						if (!validateField(key)) {
							event.halt();
							event.stopImmediatePropagation();
						}
					}
				);
			};

			fields.each(addOnBlurFieldValidation);
		}
	);

	var form = A.one('#<portlet:namespace />fm');

	if (form) {
		form.on(
			'submit',
			function(event) {
				var validationErrors = false;

				for (var i = 1; i < keys.length; i++) {
					if (!validateField(keys[i])) {
						validationErrors = true;
					}
				}

				if (validationErrors) {
					event.halt();
					event.stopImmediatePropagation();
				}
			}
		);
	}
</aui:script>