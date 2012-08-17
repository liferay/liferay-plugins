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

package com.liferay.calendar.action;

import com.liferay.portal.kernel.portlet.DefaultConfigurationAction;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portlet.PortletPreferencesFactoryUtil;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.portlet.PortletPreferences;

/**
 * @author Eduardo Lundgren
 * @author Fabio Pezzutto
 */
public class ConfigurationActionImpl extends DefaultConfigurationAction {

	@Override
	public void processAction(
			PortletConfig portletConfig, ActionRequest actionRequest,
			ActionResponse actionResponse)
		throws Exception {

		String tabs2 = ParamUtil.getString(actionRequest, "tabs2");

		if (tabs2.equals("email-from")) {
			validateEmailFrom(actionRequest);
		}
		else if (tabs2.equals("templates")) {
			validateTemplate(actionRequest);
		}
		else if (tabs2.equals("user-settings")) {
			updateUserSettings(actionRequest, actionResponse);
		}

		super.processAction(portletConfig, actionRequest, actionResponse);
	}

	protected void updateUserSettings(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		String portletResource = ParamUtil.getString(
			actionRequest, "portletResource");

		PortletPreferences preferences =
			PortletPreferencesFactoryUtil.getPortletSetup(
				actionRequest, portletResource);

		int defaultDuration = ParamUtil.getInteger(
			actionRequest, "defaultDuration");
		String defaultView = ParamUtil.getString(actionRequest, "defaultView");
		boolean isoTimeFormat = ParamUtil.getBoolean(
			actionRequest, "isoTimeFormat");
		String timeZoneId = ParamUtil.getString(actionRequest, "timeZoneId");
		boolean usePortalTimeZone = ParamUtil.getBoolean(
			actionRequest, "usePortalTimeZone");
		int weekStartsOn = ParamUtil.getInteger(actionRequest, "weekStartsOn");

		preferences.setValue(
			"defaultDuration", String.valueOf(defaultDuration));
		preferences.setValue("defaultView", defaultView);
		preferences.setValue("isoTimeFormat", String.valueOf(isoTimeFormat));
		preferences.setValue("timeZoneId", timeZoneId);
		preferences.setValue(
			"usePortalTimeZone", String.valueOf(usePortalTimeZone));
		preferences.setValue("weekStartsOn", String.valueOf(weekStartsOn));

		preferences.store();
	}

	protected void validateEmailFrom(ActionRequest actionRequest)
		throws Exception {

		String emailFromName = getParameter(actionRequest, "emailFromName");
		String emailFromAddress = getParameter(
			actionRequest, "emailFromAddress");

		if (Validator.isNull(emailFromName)) {
			SessionErrors.add(actionRequest, "emailFromName");
		}
		else if (!Validator.isEmailAddress(emailFromAddress)) {
			SessionErrors.add(actionRequest, "emailFromAddress");
		}
	}

	protected void validateTemplate(ActionRequest actionRequest)
		throws Exception {

		String notificationTemplateContentBodyParameterName =
			ParamUtil.getString(
				actionRequest, "notificationTemplateContentBodyParameterName");

		String notificationTemplateContentBody = getParameter(
			actionRequest, notificationTemplateContentBodyParameterName);

		String notificationTemplateContentSubjectParameterName =
			ParamUtil.getString(
				actionRequest,
			"notificationTemplateContentSubjectParameterName");

		String notificationTemplateContentSubject = getParameter(
			actionRequest, notificationTemplateContentSubjectParameterName);

		if (Validator.isNull(notificationTemplateContentBody)) {
			SessionErrors.add(
				actionRequest, "notificationTemplateContentSubject");
		}
		else if (Validator.isNull(notificationTemplateContentSubject)) {
			SessionErrors.add(actionRequest, "notificationTemplateContentBody");
		}
	}

}