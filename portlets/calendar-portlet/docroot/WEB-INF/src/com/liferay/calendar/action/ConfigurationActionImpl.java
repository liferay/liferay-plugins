package com.liferay.calendar.action;

import com.liferay.portal.kernel.portlet.DefaultConfigurationAction;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;


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
		else if (tabs2.equals("booking-reminder-email")) {
			validateEmailBookingReminder(actionRequest);
		}
		else if (tabs2.equals("booking-notification-email")) {
			validateEmailBookingNotification(actionRequest);
		}

		super.processAction(portletConfig, actionRequest, actionResponse);
	}

	protected void validateEmailBookingNotification(
			ActionRequest actionRequest)
		throws Exception {

		String emailBookingNotificationSubject = getParameter(
			actionRequest, "emailBookingNotificationSubject");
		String emailBookingNotificationBody = getParameter(
			actionRequest, "emailBookingNotificationBody");

		if (Validator.isNull(emailBookingNotificationSubject)) {
			SessionErrors.add(actionRequest, "emailBookingNotificationSubject");
		}
		else if (Validator.isNull(emailBookingNotificationBody)) {
			SessionErrors.add(actionRequest, "emailBookingNotificationBody");
		}
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

	protected void validateEmailBookingReminder(
			ActionRequest actionRequest)
		throws Exception {

		String emailBookingReminderSubject = getParameter(
			actionRequest, "emailBookingReminderSubject");
		String emailBookingReminderBody = getParameter(
			actionRequest, "emailBookingReminderBody");

		if (Validator.isNull(emailBookingReminderSubject)) {
			SessionErrors.add(actionRequest, "emailBookingReminderSubject");
		}
		else if (Validator.isNull(emailBookingReminderBody)) {
			SessionErrors.add(actionRequest, "emailBookingReminderBody");
		}
	}

}
