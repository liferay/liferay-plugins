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
import com.liferay.util.bridges.jsp.JSPPortlet;

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
public class InviteMembersPortlet extends JSPPortlet {

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