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

package com.liferay.webform.action;

import com.liferay.expando.kernel.exception.ColumnNameException;
import com.liferay.expando.kernel.exception.DuplicateColumnNameException;
import com.liferay.portal.kernel.portlet.DefaultConfigurationAction;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.webform.util.WebFormUtil;

import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.portlet.PortletPreferences;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Jorge Ferrer
 * @author Alberto Montero
 * @author Julio Camarero
 * @author Brian Wing Shun Chan
 */
public class ConfigurationActionImpl extends DefaultConfigurationAction {

	@Override
	public String getJspPath(HttpServletRequest request) {
		String cmd = ParamUtil.getString(request, Constants.CMD);

		if (cmd.equals(Constants.ADD)) {
			return "/edit_field.jsp";
		}
		else {
			return "/configuration.jsp";
		}
	}

	@Override
	public void processAction(
			PortletConfig portletConfig, ActionRequest actionRequest,
			ActionResponse actionResponse)
		throws Exception {

		validateFields(actionRequest);

		if (!SessionErrors.isEmpty(actionRequest)) {
			return;
		}

		Locale defaultLocale = LocaleUtil.getSiteDefault();
		String defaultLanguageId = LocaleUtil.toLanguageId(defaultLocale);

		boolean updateFields = ParamUtil.getBoolean(
			actionRequest, "updateFields");

		String portletResource = ParamUtil.getString(
			actionRequest, "portletResource");

		PortletPreferences preferences = actionRequest.getPreferences();

		LocalizationUtil.setLocalizedPreferencesValues(
			actionRequest, preferences, "title");

		Map<Locale, String> titleMap = LocalizationUtil.getLocalizationMap(
			actionRequest, "title");

		preferences.setValue("title", titleMap.get(defaultLocale));

		LocalizationUtil.setLocalizedPreferencesValues(
			actionRequest, preferences, "description");

		Map<Locale, String> descriptionMap =
			LocalizationUtil.getLocalizationMap(actionRequest, "description");

		preferences.setValue("description", descriptionMap.get(defaultLocale));

		if (updateFields) {
			int i = 1;

			String databaseTableName = WebFormUtil.getNewDatabaseTableName(
				portletResource);

			preferences.setValue("databaseTableName", databaseTableName);

			int[] formFieldsIndexes = StringUtil.split(
				ParamUtil.getString(actionRequest, "formFieldsIndexes"), 0);

			for (int formFieldsIndex : formFieldsIndexes) {
				Map<Locale, String> fieldLabelMap =
					LocalizationUtil.getLocalizationMap(
						actionRequest, "fieldLabel" + formFieldsIndex);

				if (Validator.isNull(fieldLabelMap.get(defaultLocale))) {
					continue;
				}

				String fieldType = ParamUtil.getString(
					actionRequest, "fieldType" + formFieldsIndex);
				boolean fieldOptional = ParamUtil.getBoolean(
					actionRequest, "fieldOptional" + formFieldsIndex);
				Map<Locale, String> fieldOptionsMap =
					LocalizationUtil.getLocalizationMap(
						actionRequest, "fieldOptions" + formFieldsIndex);
				Map<Locale, String> fieldParagraphMap =
					LocalizationUtil.getLocalizationMap(
						actionRequest, "fieldParagraph" + formFieldsIndex);
				String fieldValidationScript = ParamUtil.getString(
					actionRequest, "fieldValidationScript" + formFieldsIndex);
				String fieldValidationErrorMessage = ParamUtil.getString(
					actionRequest,
					"fieldValidationErrorMessage" + formFieldsIndex);

				if (Validator.isNotNull(fieldValidationScript) ^
					Validator.isNotNull(fieldValidationErrorMessage)) {

					SessionErrors.add(
						actionRequest, "validationDefinitionInvalid" + i);
				}

				updateModifiedLocales(
					"fieldLabel" + i, fieldLabelMap, preferences);
				updateModifiedLocales(
					"fieldOptions" + i, fieldOptionsMap, preferences);
				updateModifiedLocales(
					"fieldParagraph" + i, fieldParagraphMap, preferences);

				preferences.setValue(
					"fieldLabel" + i, fieldLabelMap.get(defaultLocale));
				preferences.setValue("fieldType" + i, fieldType);
				preferences.setValue(
					"fieldOptional" + i, String.valueOf(fieldOptional));
				preferences.setValue("fieldOptions" + i, StringPool.BLANK);
				preferences.setValue("fieldParagraph" + i, StringPool.BLANK);
				preferences.setValue(
					"fieldValidationScript" + i, fieldValidationScript);
				preferences.setValue(
					"fieldValidationErrorMessage" + i,
					fieldValidationErrorMessage);

				i++;
			}

			if (!SessionErrors.isEmpty(actionRequest)) {
				return;
			}

			// Clear previous preferences that are now blank

			String fieldLabel = LocalizationUtil.getPreferencesValue(
				preferences, "fieldLabel" + i, defaultLanguageId);

			while (Validator.isNotNull(fieldLabel)) {
				Map<Locale, String> fieldLabelMap =
					LocalizationUtil.getLocalizationMap(
						actionRequest, "fieldLabel" + i);

				for (Locale locale : fieldLabelMap.keySet()) {
					String languageId = LocaleUtil.toLanguageId(locale);

					LocalizationUtil.setPreferencesValue(
						preferences, "fieldLabel" + i, languageId,
						StringPool.BLANK);
					LocalizationUtil.setPreferencesValue(
						preferences, "fieldOptions" + i, languageId,
						StringPool.BLANK);
					LocalizationUtil.setPreferencesValue(
						preferences, "fieldParagraph" + i, languageId,
						StringPool.BLANK);
				}

				preferences.setValue("fieldLabel" + i, StringPool.BLANK);
				preferences.setValue("fieldType" + i, StringPool.BLANK);
				preferences.setValue("fieldOptional" + i, StringPool.BLANK);
				preferences.setValue(
					"fieldValidationScript" + i, StringPool.BLANK);
				preferences.setValue(
					"fieldValidationErrorMessage" + i, StringPool.BLANK);

				i++;

				fieldLabel = LocalizationUtil.getPreferencesValue(
					preferences, "fieldLabel" + i, defaultLanguageId);
			}
		}

		if (SessionErrors.isEmpty(actionRequest)) {
			preferences.store();
		}

		super.processAction(portletConfig, actionRequest, actionResponse);
	}

	protected void updateModifiedLocales(
			String parameter, Map<Locale, String> newLocalizationMap,
			PortletPreferences preferences)
		throws Exception {

		Map<Locale, String> oldLocalizationMap =
			LocalizationUtil.getLocalizationMap(preferences, parameter);

		List<Locale> modifiedLocales = LocalizationUtil.getModifiedLocales(
			oldLocalizationMap, newLocalizationMap);

		for (Locale locale : modifiedLocales) {
			String languageId = LocaleUtil.toLanguageId(locale);
			String value = newLocalizationMap.get(locale);

			LocalizationUtil.setPreferencesValue(
				preferences, parameter, languageId, value);
		}
	}

	protected void validateEmailFields(ActionRequest actionRequest) {
		String subject = getParameter(actionRequest, "subject");

		if (Validator.isNull(subject)) {
			SessionErrors.add(actionRequest, "subjectRequired");
		}

		String[] emailAdresses = WebFormUtil.split(
			getParameter(actionRequest, "emailAddress"));
		String emailFromAddress = GetterUtil.getString(
			getParameter(actionRequest, "emailFromAddress"));

		if ((emailAdresses.length == 0) || Validator.isNull(emailFromAddress)) {
			SessionErrors.add(actionRequest, "emailAddressRequired");
		}

		if (Validator.isNotNull(emailFromAddress) &&
			!Validator.isEmailAddress(emailFromAddress)) {

			SessionErrors.add(actionRequest, "emailAddressInvalid");
		}
		else {
			for (String emailAdress : emailAdresses) {
				emailAdress = emailAdress.trim();

				if (!Validator.isEmailAddress(emailAdress)) {
					SessionErrors.add(actionRequest, "emailAddressInvalid");

					break;
				}
			}
		}
	}

	protected void validateFieldNameLength(ActionRequest actionRequest) {
		Locale defaultLocale = LocaleUtil.getSiteDefault();

		int[] formFieldsIndexes = StringUtil.split(
			ParamUtil.getString(actionRequest, "formFieldsIndexes"), 0);
		boolean saveToDatabase = GetterUtil.getBoolean(
			getParameter(actionRequest, "saveToDatabase"));

		for (int formFieldsIndex : formFieldsIndexes) {
			Map<Locale, String> fieldLabelMap =
				LocalizationUtil.getLocalizationMap(
					actionRequest, "fieldLabel" + formFieldsIndex);

			for (Locale locale : fieldLabelMap.keySet()) {
				String fieldLabelValue = fieldLabelMap.get(locale);

				if (locale.equals(defaultLocale) &&
					Validator.isNull(fieldLabelValue)) {

					SessionErrors.add(
						actionRequest, ColumnNameException.class.getName());

					return;
				}

				if (Validator.isNotNull(fieldLabelValue) &&
					saveToDatabase && (fieldLabelValue.length() > 75)) {

					SessionErrors.add(
						actionRequest, "fieldSizeInvalid" + formFieldsIndex);

					return;
				}
			}
		}
	}

	protected void validateFields(ActionRequest actionRequest)
		throws Exception {

		boolean saveToDatabase = GetterUtil.getBoolean(
			getParameter(actionRequest, "saveToDatabase"));
		boolean saveToFile = GetterUtil.getBoolean(
			getParameter(actionRequest, "saveToFile"));
		boolean sendAsEmail = GetterUtil.getBoolean(
			getParameter(actionRequest, "sendAsEmail"));

		if (!saveToDatabase && !saveToFile && !sendAsEmail) {
			SessionErrors.add(actionRequest, "handlingRequired");
		}

		if (sendAsEmail) {
			validateEmailFields(actionRequest);
		}

		String successURL = getParameter(actionRequest, "successURL");

		if (Validator.isNotNull(successURL) && !Validator.isUrl(successURL)) {
			SessionErrors.add(actionRequest, "successURLInvalid");
		}

		validateFieldNameLength(actionRequest);
		validateUniqueFieldNames(actionRequest);
	}

	protected void validateUniqueFieldNames(ActionRequest actionRequest) {
		Locale defaultLocale = LocaleUtil.getSiteDefault();

		Set<String> localizedUniqueFieldNames = new HashSet<>();

		int[] formFieldsIndexes = StringUtil.split(
			ParamUtil.getString(actionRequest, "formFieldsIndexes"), 0);

		for (int formFieldsIndex : formFieldsIndexes) {
			Map<Locale, String> fieldLabelMap =
				LocalizationUtil.getLocalizationMap(
					actionRequest, "fieldLabel" + formFieldsIndex);

			if (Validator.isNull(fieldLabelMap.get(defaultLocale))) {
				continue;
			}

			for (Locale locale : fieldLabelMap.keySet()) {
				String fieldLabelValue = fieldLabelMap.get(locale);

				if (Validator.isNull(fieldLabelValue)) {
					continue;
				}

				String languageId = LocaleUtil.toLanguageId(locale);

				if (!localizedUniqueFieldNames.add(
						languageId + "_" + fieldLabelValue)) {

					SessionErrors.add(
						actionRequest,
						DuplicateColumnNameException.class.getName());

					return;
				}
			}
		}
	}

}