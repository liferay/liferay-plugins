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

package com.liferay.webform.action;

import com.liferay.portal.kernel.portlet.BaseConfigurationAction;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portlet.PortletPreferencesFactoryUtil;
import com.liferay.webform.util.WebFormUtil;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import java.util.Locale;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.portlet.PortletPreferences;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

/**
 * @author Jorge Ferrer
 * @author Alberto Montero
 * @author Julio Camarero
 * @author Brian Wing Shun Chan
 */
public class ConfigurationActionImpl extends BaseConfigurationAction {

	public void processAction(
			PortletConfig portletConfig, ActionRequest actionRequest,
			ActionResponse actionResponse)
		throws Exception {

		String cmd = ParamUtil.getString(actionRequest, Constants.CMD);

		Locale defaultLocale = LocaleUtil.getDefault();
		String defaultLanguageId = LocaleUtil.toLanguageId(defaultLocale);

		String title = ParamUtil.getString(
			actionRequest, "title" + StringPool.UNDERLINE + defaultLanguageId);
		boolean requireCaptcha = ParamUtil.getBoolean(
			actionRequest, "requireCaptcha");
		String successURL = ParamUtil.getString(actionRequest, "successURL");

		boolean sendAsEmail = ParamUtil.getBoolean(
			actionRequest, "sendAsEmail");
		String subject = ParamUtil.getString(actionRequest, "subject");
		String emailAddress = ParamUtil.getString(
			actionRequest, "emailAddress");

		boolean saveToDatabase = ParamUtil.getBoolean(
			actionRequest, "saveToDatabase");

		boolean saveToFile = ParamUtil.getBoolean(actionRequest, "saveToFile");
		String fileName = ParamUtil.getString(actionRequest, "fileName");

		boolean updateFields = ParamUtil.getBoolean(
			actionRequest, "updateFields");

		String portletResource = ParamUtil.getString(
			actionRequest, "portletResource");

		PortletPreferences preferences =
			PortletPreferencesFactoryUtil.getPortletSetup(
				actionRequest, portletResource);

		if (Validator.isNull(title)) {
			SessionErrors.add(actionRequest, "titleRequired");
		}

		if (Validator.isNull(subject)) {
			SessionErrors.add(actionRequest, "subjectRequired");
		}

		if (!sendAsEmail && !saveToDatabase && !saveToFile) {
			SessionErrors.add(actionRequest, "handlingRequired");
		}

		if (sendAsEmail) {
			if (Validator.isNull(emailAddress)) {
				SessionErrors.add(actionRequest, "emailAddressRequired");
			}
			else if (!Validator.isEmailAddress(emailAddress)) {
				SessionErrors.add(actionRequest, "emailAddressInvalid");
			}
		}

		if (saveToFile) {

			// Check if server can create a file as specified

			try {
				FileOutputStream fos = new FileOutputStream(fileName, true);

				fos.close();
			}
			catch (SecurityException es) {
				SessionErrors.add(actionRequest, "fileNameInvalid");
			}
			catch (FileNotFoundException fnfe) {
				SessionErrors.add(actionRequest, "fileNameInvalid");
			}
		}

		if (!SessionErrors.isEmpty(actionRequest)) {
			return;
		}

		LocalizationUtil.setLocalizedPreferencesValues(
			actionRequest, preferences, "title");
		LocalizationUtil.setLocalizedPreferencesValues(
			actionRequest, preferences, "description");
		preferences.setValue("requireCaptcha", String.valueOf(requireCaptcha));
		preferences.setValue("successURL", successURL);
		preferences.setValue("sendAsEmail", String.valueOf(sendAsEmail));
		preferences.setValue("subject", subject);
		preferences.setValue("emailAddress", emailAddress);
		preferences.setValue("saveToDatabase", String.valueOf(saveToDatabase));
		preferences.setValue("saveToFile", String.valueOf(saveToFile));
		preferences.setValue("fileName", fileName);

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
				String fieldValidationScript = ParamUtil.getString(
					actionRequest, "fieldValidationScript" + formFieldsIndex);
				String fieldValidationErrorMessage = ParamUtil.getString(
					actionRequest,
					"fieldValidationErrorMessage" + formFieldsIndex);

				if ((Validator.isNotNull(fieldValidationScript) ^
					(Validator.isNotNull(fieldValidationErrorMessage)))) {

					SessionErrors.add(
						actionRequest, "invalidValidationDefinition" + i);
				}

				for (Locale locale : fieldLabelMap.keySet()) {
					String languageId = LocaleUtil.toLanguageId(locale);
					String fieldLabelValue = fieldLabelMap.get(locale);
					String fieldOptionsValue = fieldOptionsMap.get(locale);

					if (Validator.isNotNull(fieldLabelValue)) {
						LocalizationUtil.setPreferencesValue(
							preferences, "fieldLabel" + i, languageId,
							fieldLabelValue);
					}

					if (Validator.isNotNull(fieldOptionsValue)) {
						LocalizationUtil.setPreferencesValue(
							preferences, "fieldOptions" + i, languageId,
							fieldOptionsValue);
					}
				}

				preferences.setValue("fieldType" + i, fieldType);
				preferences.setValue(
					"fieldOptional" + i, String.valueOf(fieldOptional));
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
				}

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

			SessionMessages.add(
				actionRequest, portletConfig.getPortletName() + ".doConfigure");
		}
	}

	public String render(
			PortletConfig portletConfig, RenderRequest renderRequest,
			RenderResponse renderResponse)
		throws Exception {

		String cmd = ParamUtil.getString(renderRequest, Constants.CMD);

		if (cmd.equals(Constants.ADD)) {
			return "/edit_field.jsp";
		}
		else {
			return "/configuration.jsp";
		}
	}

}