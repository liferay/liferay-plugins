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
int index = ParamUtil.getInteger(renderRequest, "index", GetterUtil.getInteger((String)request.getAttribute("configuration.jsp-index")));
int formFieldsIndex = GetterUtil.getInteger((String)request.getAttribute("configuration.jsp-formFieldsIndex"));
boolean fieldsEditingDisabled = GetterUtil.getBoolean((String)request.getAttribute("configuration.jsp-fieldsEditingDisabled"));

String fieldLabelXml = LocalizationUtil.getLocalizationXmlFromPreferences(preferences, renderRequest, "fieldLabel" + formFieldsIndex);
String fieldLabel = LocalizationUtil.getLocalization(fieldLabelXml, themeDisplay.getLanguageId());
String fieldType = PrefsParamUtil.getString(preferences, renderRequest, "fieldType" + formFieldsIndex);
boolean fieldOptional = PrefsParamUtil.getBoolean(preferences, renderRequest, "fieldOptional" + formFieldsIndex);
String fieldOptionsXml = LocalizationUtil.getLocalizationXmlFromPreferences(preferences, renderRequest, "fieldOptions" + formFieldsIndex);
String fieldOptions = LocalizationUtil.getLocalization(fieldOptionsXml, themeDisplay.getLanguageId());
String fieldValidationScript = PrefsParamUtil.getString(preferences, request, "fieldValidationScript" + formFieldsIndex);
String fieldValidationErrorMessage = PrefsParamUtil.getString(preferences, request, "fieldValidationErrorMessage" + formFieldsIndex);
%>

<liferay-ui:error key='<%= "fieldSizeInvalid" + formFieldsIndex %>' message="please-enter-no-more-than-75-characters" />

<div class="aui-field-row field-row">
	<div class="field-title">
		<c:choose>
			<c:when test='<%= fieldType.equals("paragraph") %>'>
				<span class="field-label"><liferay-ui:message key="paragraph" /></span>
			</c:when>
			<c:when test="<%= Validator.isNotNull(fieldLabel) %>">
				<span class="field-label"><%= fieldLabel %></span>
			</c:when>
			<c:otherwise>
				<liferay-ui:message key="new-item" />
			</c:otherwise>
		</c:choose>
	</div>

	<c:choose>
		<c:when test="<%= !fieldsEditingDisabled %>">
			<aui:input type="hidden" name='<%= "_field" + index %>' />

			<aui:field-wrapper cssClass="label-name" label="name">
				<liferay-ui:input-localized name='<%= "fieldLabel" + index %>' xml="<%= fieldLabelXml %>" />
			</aui:field-wrapper>
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
			<aui:select label="type" name='<%= "fieldType" + index %>'>
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
			<aui:input cssClass="optional-control" inlineLabel="left" label="optional" name='<%= "fieldOptional" + index %>' type="checkbox" value="<%= fieldOptional %>" />
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
			<aui:field-wrapper cssClass='<%= "options" + ((Validator.isNull(fieldType) || (!fieldType.equals("options") && !fieldType.equals("radio"))) ? " aui-helper-hidden" : StringPool.BLANK) %>' helpMessage="add-options-separated-by-commas" label="options">
				<liferay-ui:input-localized name='<%= "fieldOptions" + index %>' xml="<%= fieldOptionsXml %>" />
			</aui:field-wrapper>
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
					<liferay-ui:error key='<%= "invalidValidationDefinition" + index %>' message="please-enter-both-the-validation-code-and-the-error-message" />

					<aui:a cssClass="validation-link" href="javascript:;"><liferay-ui:message key="validation" /> &raquo;</aui:a>

					<div class='validation-input <%= Validator.isNull(fieldValidationScript) ? "aui-helper-hidden" : "" %>'>
						<aui:column columnWidth="50">
							<aui:input cssClass="validation-script" cols="80" label="validation-script" name='<%= "fieldValidationScript" + index %>' style="width: 95%" type="textarea" value="<%= fieldValidationScript %>" wrap="off" />

							<aui:input cssClass="lfr-input-text-container" cols="80" label="validation-error-message" name='<%= "fieldValidationErrorMessage" + index %>' size="80" value="<%= fieldValidationErrorMessage %>" />
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