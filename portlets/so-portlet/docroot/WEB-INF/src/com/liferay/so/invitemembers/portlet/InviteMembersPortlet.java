/**
 * Copyright (c) 2008-2009 Liferay, Inc. All rights reserved.
 *
 * This file is part of Liferay Social Office. Liferay Social Office is free
 * software: you can redistribute it and/or modify it under the terms of the GNU
 * Affero General Public License as published by the Free Software Foundation,
 * either version 3 of the License, or (at your option) any later version.
 *
 * Liferay Social Office is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Affero General Public License
 * for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * Liferay Social Office. If not, see http://www.gnu.org/licenses/agpl-3.0.html.
 */

package com.liferay.so.invitemembers.portlet;

import com.liferay.mail.service.MailServiceUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.mail.MailMessage;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.User;
import com.liferay.portal.util.PortalUtil;
import com.liferay.util.bridges.mvc.MVCPortlet;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletPreferences;

/**
 * <a href="InviteMembersPortlet.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brett Swaim
 *
 */
public class InviteMembersPortlet extends MVCPortlet {

	public void sendEmail(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		PortletPreferences preferences = actionRequest.getPreferences();

		String[] emailAddresses = actionRequest.getParameterValues(
			"emailAddresses");

		if (Validator.isNull(emailAddresses)) {
			return;
		}

		User user = PortalUtil.getUser(actionRequest);

		InternetAddress from = new InternetAddress(
			user.getEmailAddress(), user.getFullName());

		String subject = getSubject(preferences);
		String body = getBody(preferences);

		body = StringUtil.replace(
			body,
			new String[] {
				"[$PORTAL_URL$]",
				"[$SENDER_EMAIL_ADDRESS$]",
				"[$SENDER_FULL_NAME$]"
			},
			new String[] {
				PortalUtil.getPortalURL(actionRequest),
				user.getEmailAddress(),
				user.getFullName()
			});

		MailMessage mailMessage = new MailMessage(from, subject, body, true);

		for (String emailAddress : emailAddresses) {
			try {
				InternetAddress to = new InternetAddress(emailAddress);

				mailMessage.setTo(to);

				MailServiceUtil.sendEmail(mailMessage);
			}
			catch (AddressException ae) {
				if (_log.isWarnEnabled()) {
					_log.warn("Invalid email address " + emailAddress);
				}
			}
		}

		int invitedMembersCount = ParamUtil.getInteger(
			actionRequest, "invitedMembersCount");

		actionResponse.setRenderParameter(
			"invitedMembersCount", String.valueOf(invitedMembersCount));
	}

	public void updateEmail(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		String body = ParamUtil.getString(actionRequest, "body");
		String subject = ParamUtil.getString(actionRequest, "subject");

		PortletPreferences preferences = actionRequest.getPreferences();

		preferences.setValue("body", body);
		preferences.setValue("subject", subject);

		preferences.store();
	}

	protected String getBody(PortletPreferences preferences) throws Exception {
		String body = preferences.getValue("body", null);

		if (Validator.isNotNull(body)) {
			return body;
		}

		return StringUtil.read(
			InviteMembersPortlet.class.getResourceAsStream(
				"dependencies/body.tmpl"));
	}

	protected String getSubject(PortletPreferences preferences)
		throws Exception {

		String subject = preferences.getValue("subject", null);

		if (Validator.isNotNull(subject)) {
			return subject;
		}

		return StringUtil.read(
			InviteMembersPortlet.class.getResourceAsStream(
				"dependencies/subject.tmpl"));
	}

	private static Log _log = LogFactoryUtil.getLog(InviteMembersPortlet.class);

}