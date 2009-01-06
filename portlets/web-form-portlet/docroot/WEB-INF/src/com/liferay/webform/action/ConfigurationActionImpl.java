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

package com.liferay.webform.action;

import com.liferay.portal.kernel.portlet.ConfigurationAction;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portlet.PortletPreferencesFactoryUtil;
import com.liferay.webform.util.WebFormUtil;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.portlet.PortletPreferences;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

/**
 * <a href="ConfigurationActionImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Jorge Ferrer
 * @author Alberto Montero
 * @author Julio Camarero
 * @author Brian Wing Shun Chan
 *
 */
public class ConfigurationActionImpl implements ConfigurationAction {

	public void processAction(
			PortletConfig portletConfig, ActionRequest actionRequest,
			ActionResponse actionResponse)
		throws Exception {

		String cmd = ParamUtil.getString(actionRequest, Constants.CMD);

		if (!cmd.equals(Constants.UPDATE)) {
			return;
		}

		String title = ParamUtil.getString(actionRequest, "title");
		String description = ParamUtil.getString(actionRequest, "description");
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

		preferences.setValue("title", title);
		preferences.setValue("description", description);
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
				String fieldLabel = ParamUtil.getString(
					actionRequest, "fieldLabel" + formFieldsIndex);

				if (Validator.isNull(fieldLabel)) {
					continue;
				}

				String fieldType = ParamUtil.getString(
					actionRequest, "fieldType" + formFieldsIndex);
				boolean fieldOptional = ParamUtil.getBoolean(
					actionRequest, "fieldOptional" + formFieldsIndex);
				String fieldOptions = ParamUtil.getString(
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

				preferences.setValue("fieldLabel" + i, fieldLabel);
				preferences.setValue("fieldType" + i, fieldType);
				preferences.setValue(
					"fieldOptional" + i, String.valueOf(fieldOptional));
				preferences.setValue("fieldOptions" + i, fieldOptions);
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

			String fieldLabel = preferences.getValue(
				"fieldLabel" + i, StringPool.BLANK);

			while (Validator.isNotNull(fieldLabel)) {
				preferences.setValue("fieldLabel" + i, StringPool.BLANK);
				preferences.setValue("fieldType" + i, StringPool.BLANK);
				preferences.setValue("fieldOptional" + i, StringPool.BLANK);
				preferences.setValue("fieldOptions" + i, StringPool.BLANK);
				preferences.setValue(
					"fieldValidationScript" + i, StringPool.BLANK);
				preferences.setValue(
					"fieldValidationErrorMessage" + i, StringPool.BLANK);

				i++;

				fieldLabel = preferences.getValue(
					"fieldLabel" + i, StringPool.BLANK);
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

		return "/configuration.jsp";
	}

}