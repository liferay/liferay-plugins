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
int index = ParamUtil.getInteger(renderRequest, "index", GetterUtil.getInteger((String)request.getAttribute("configuration.jsp-index")));
int formFieldsIndex = GetterUtil.getInteger((String)request.getAttribute("configuration.jsp-formFieldsIndex"));
boolean fieldsEditingDisabled = GetterUtil.getBoolean((String)request.getAttribute("configuration.jsp-fieldsEditingDisabled"));
String fieldLabelXml = GetterUtil.getString(LocalizationUtil.getLocalizationXmlFromPreferences(portletPreferences, renderRequest, "fieldLabel" + formFieldsIndex), StringPool.BLANK);
String fieldLabel = LocalizationUtil.getLocalization(fieldLabelXml, themeDisplay.getLanguageId());
String fieldType = PrefsParamUtil.getString(portletPreferences, renderRequest, "fieldType" + formFieldsIndex);
boolean fieldOptional = PrefsParamUtil.getBoolean(portletPreferences, renderRequest, "fieldOptional" + formFieldsIndex);

String fieldOptionsXml = GetterUtil.getString(LocalizationUtil.getLocalizationXmlFromPreferences(portletPreferences, renderRequest, "fieldOptions" + formFieldsIndex), StringPool.BLANK);

String fieldOptions = LocalizationUtil.getLocalization(fieldOptionsXml, themeDisplay.getLanguageId());

String fieldParagraphXml = GetterUtil.getString(LocalizationUtil.getLocalizationXmlFromPreferences(portletPreferences, renderRequest, "fieldParagraph" + formFieldsIndex), StringPool.BLANK);

String fieldParagraph = LocalizationUtil.getLocalization(fieldParagraphXml, themeDisplay.getLanguageId());

String fieldValidationScript = PrefsParamUtil.getString(portletPreferences, request, "fieldValidationScript" + formFieldsIndex);
String fieldValidationErrorMessage = PrefsParamUtil.getString(portletPreferences, request, "fieldValidationErrorMessage" + formFieldsIndex);

boolean ignoreRequestValue = (index != formFieldsIndex);
%>

<liferay-ui:error key='<%= "fieldSizeInvalid" + formFieldsIndex %>' message="please-enter-no-more-than-75-characters" />

<div class="field-row field-row">
	<div class="field-title">
		<c:choose>
			<c:when test='<%= fieldType.equals("paragraph") %>'>
				<span class="field-label"><liferay-ui:message key="paragraph" /></span>
			</c:when>
			<c:when test="<%= Validator.isNotNull(fieldLabel) %>">
				<span class="field-label"><%= HtmlUtil.escape(fieldLabel) %></span>
			</c:when>
			<c:otherwise>
				<liferay-ui:message key="new-item" />
			</c:otherwise>
		</c:choose>
	</div>

	<c:choose>
		<c:when test="<%= !fieldsEditingDisabled %>">
			<aui:input name='<%= "_field" + index %>' type="hidden" />

			<aui:field-wrapper cssClass="label-name" label="name">
				<liferay-ui:input-localized ignoreRequestValue="<%= ignoreRequestValue %>" name='<%= "fieldLabel" + index %>' xml="<%= fieldLabelXml %>" />
			</aui:field-wrapper>
		</c:when>
		<c:otherwise>
			<dl class="editing-disabled">
				<dt>
					<liferay-ui:message key="name" />
				</dt>
				<dd>
					<%= HtmlUtil.escape(fieldLabel) %>
				</dd>
		</c:otherwise>
	</c:choose>

	<c:choose>
		<c:when test="<%= !fieldsEditingDisabled %>">
			<aui:select ignoreRequestValue="<%= ignoreRequestValue %>" label="type" name='<%= "fieldType" + index %>' value="<%= fieldType %>">
				<aui:option label="text" />
				<aui:option label="text-box" value="textarea" />
				<aui:option label="options" />
				<aui:option label="radio-buttons" value="radio" />
				<aui:option label="paragraph" />
				<aui:option label="check-box" value="checkbox" />
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
			<aui:input cssClass="optional-control" ignoreRequestValue="<%= ignoreRequestValue %>" label="optional" name='<%= "fieldOptional" + index %>' type="checkbox" value="<%= fieldOptional %>" />
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
			<aui:field-wrapper cssClass='<%= "options" + ((Validator.isNull(fieldType) || (!fieldType.equals("options") && !fieldType.equals("radio"))) ? " hide" : StringPool.BLANK) %>' helpMessage="add-options-separated-by-commas" label="options">
				<liferay-ui:input-localized ignoreRequestValue="<%= ignoreRequestValue %>" name='<%= "fieldOptions" + index %>' xml="<%= fieldOptionsXml %>" />
			</aui:field-wrapper>
		</c:when>
		<c:when test="<%= Validator.isNotNull(fieldOptions) %>">
				<dt>
					<liferay-ui:message key="options" />
				</dt>
				<dd>
					<%= HtmlUtil.escape(fieldOptions) %>
				</dd>
		</c:when>
	</c:choose>

	<c:choose>
		<c:when test="<%= !fieldsEditingDisabled %>">
			<aui:field-wrapper cssClass='<%= "paragraph" + (Validator.isNull(fieldType) || !fieldType.equals("paragraph") ? " hide" : StringPool.BLANK) %>' label="paragraph">
				<liferay-ui:input-localized cssClass="lfr-editor-textarea" ignoreRequestValue="<%= ignoreRequestValue %>" name='<%= "fieldParagraph" + index %>' type="textarea" xml="<%= fieldParagraphXml %>" />
			</aui:field-wrapper>
		</c:when>
		<c:when test="<%= Validator.isNotNull(fieldParagraph) %>">
				<dt>
					<liferay-ui:message key="paragraph" />
				</dt>
				<dd>
					<%= HtmlUtil.escape(fieldParagraph) %>
				</dd>
		</c:when>
	</c:choose>

	<c:if test="<%= PortletPropsValues.VALIDATION_SCRIPT_ENABLED %>">
		<c:choose>
			<c:when test="<%= !fieldsEditingDisabled %>">
				<div class="validation">
					<liferay-ui:error key='<%= "validationDefinitionInvalid" + index %>' message="please-enter-both-the-validation-code-and-the-error-message" />

					<aui:a cssClass="validation-link" href="javascript:;"><liferay-ui:message key="validation" /> &raquo;</aui:a>

					<div class='validation-input <%= Validator.isNull(fieldValidationScript) ? "hide" : "" %>'>
						<aui:column columnWidth="50">
							<aui:input cols="80" cssClass="validation-script" ignoreRequestValue="<%= ignoreRequestValue %>" label="validation-script" name='<%= "fieldValidationScript" + index %>' style="width: 95%" type="textarea" value="<%= fieldValidationScript %>" wrap="off" />

							<aui:input cols="80" ignoreRequestValue="<%= ignoreRequestValue %>" label="validation-error-message" name='<%= "fieldValidationErrorMessage" + index %>' size="80" value="<%= fieldValidationErrorMessage %>" wrapperCssClass="lfr-input-text-container" />
						</aui:column>
						<aui:column columnWidth="50">
							<div class="syntax-help">
								<liferay-util:include page="/script_help.jsp" servletContext="<%= application %>" />
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